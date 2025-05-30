import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Scheduler {
    List<Student> students = new ArrayList<>();
    List<Lesson> lessons = new ArrayList<>();
    List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    void addStudent(Student student) {
        if (student.name.length() > 10) {
            System.out.println("Имя слишком длинное!");
            return;
        }
        students.add(student);
    }

    void addLesson(String day, LocalTime time) {
        if (time.isBefore(LocalTime.of(13, 0)) || time.isAfter(LocalTime.of(18, 0))) {
            System.out.println("Неверное время!");
            return;
        }

        DayOfWeek dayOfWeek = getDayOfWeek(day);

        if (dayOfWeek == null) {
            System.out.println("Неверный день недели!!");
            return;
        }

        if (lessons.size() >= 10) {
            System.out.println("Максимум 10 занятий в неделю.");
            return;
        }

        Lesson lesson = new Lesson(dayOfWeek, time);
        lessons.add(lesson);
    }

    private static DayOfWeek getDayOfWeek(String name) {
        return switch (name.toUpperCase()) {
            case "ПОНЕДЕЛЬНИК" -> DayOfWeek.MONDAY;
            case "ВТОРНИК" -> DayOfWeek.TUESDAY;
            case "СРЕДА" -> DayOfWeek.WEDNESDAY;
            case "ЧЕТВЕРГ" -> DayOfWeek.THURSDAY;
            case "ПЯТНИЦА" -> DayOfWeek.FRIDAY;
            case "СУББОТА" -> DayOfWeek.SATURDAY;
            case "ВОСКРЕСЕНЬЕ" -> DayOfWeek.SUNDAY;
            default -> null;
        };
    }

    void addAttendance(String studentName, LocalDate date, LocalTime time, AttendanceStatus status) {
        Student student = students.stream().filter(s -> s.name.equals(studentName)).findFirst().orElse(null); // Это stream api, можно написать и без него через цикл for и проверку if внутри цикла (ниже есть пример)

        if (student == null) {
            System.out.println("Студент не найден.");
            return;
        }

        DayOfWeek day = date.getDayOfWeek();
        Lesson lesson = lessons.stream().filter(l -> l.dayOfWeek == day && l.time.equals(time)).findFirst().orElse(null); //Это stream api, можно написать и без него вот так:
//        Lesson lesson = null;
//        for (Lesson l : lessons) {
//            if (l.dayOfWeek == day && l.time.equals(time)) {
//                lesson = l;
//                break;
//            }
//        }

        if (lesson == null) {
            System.out.println("Занятие не найдено.");
            return;
        }

        attendanceRecords.add(new AttendanceRecord(student, lesson, date, status));
    }

    void showSchedule() {
        // Собираем уникальные даты занятий
        TreeSet<LocalDate> dates = new TreeSet<>();
        for (AttendanceRecord record : attendanceRecords) {
            dates.add(record.date);
        }

        // Упорядоченные пары (дата + занятие)
        List<String> headers = new ArrayList<>();
        List<Lesson> orderedLessons = new ArrayList<>();
        List<LocalDate> orderedDates = new ArrayList<>();

        for (LocalDate date : dates) {
            for (Lesson lesson : lessons) {
                // Фильтруем по дню недели
                if (lesson.dayOfWeek == date.getDayOfWeek()) {
                    headers.add(String.format("%02d.%02d %s", date.getDayOfMonth(), date.getMonthValue(), lesson.time));
                    orderedLessons.add(lesson);
                    orderedDates.add(date);
                }
            }
        }

        // Печать заголовка
        System.out.printf("%-10s", "Студент");
        for (String header : headers) {
            System.out.printf("| %-15s", header);
        }
        System.out.println();
        System.out.println("-".repeat(11 + headers.size() * 18));

        // Печать строк по студентам
        for (Student student : students) {
            System.out.printf("%-10s", student.name);
            for (int i = 0; i < orderedLessons.size(); i++) {
                Lesson lesson = orderedLessons.get(i);
                LocalDate date = orderedDates.get(i);

                AttendanceRecord record = attendanceRecords.stream()
                        .filter(r -> r.student.equals(student)
                                && r.lesson.equals(lesson)
                                && r.date.equals(date))
                        .findFirst()
                        .orElse(null);

                String cell = (record != null) ? record.status.name() : "—";
                System.out.printf("| %-15s", cell);
            }
            System.out.println();
        }
    }
}
