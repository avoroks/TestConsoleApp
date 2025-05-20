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


    }
}
