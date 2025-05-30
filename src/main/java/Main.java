import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();

        System.out.println("Введите имена студентов (до 10). Пустая строка — конец:");

        while (scheduler.students.size() < 10) {
            String name = scanner.nextLine();
            if (name.isEmpty()) break;

            scheduler.addStudent(new Student(name));
        }

        System.out.println("Добавьте уроки. Пример: ПОНЕДЕЛЬНИК 14:00. Пустая строка — конец:");

        while (scheduler.lessons.size() < 10) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                String[] parts = input.split(" ");
                String day = parts[0];
                LocalTime time = LocalTime.parse(parts[1]);

                scheduler.addLesson(day, time);
            } catch (Exception e) {
                System.out.println("Неверный формат!");
            }
        }

        System.out.println("\nВведите посещаемость студентов:");
        System.out.println("Формат: имя 14:00 2020-09-15 ЗДЕСЬ/НЕ_ЗДЕСЬ");
        System.out.println("Пустая строка — конец.");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;
            try {
                String[] parts = input.split(" ");
                String name = parts[0];
                LocalTime time = LocalTime.parse(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                AttendanceStatus status = AttendanceStatus.valueOf(parts[3].toUpperCase());
                scheduler.addAttendance(name, date, time, status);
            } catch (Exception e) {
                System.out.println("Неверный формат.");
            }
        }

        scheduler.showSchedule();
    }
}
