import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    void addAttendance(Student student, Lesson lesson, LocalDate date, LocalTime time, AttendanceStatus status) {


    }

    void showSchedule() {

    }
}
