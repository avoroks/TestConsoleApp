import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
    DayOfWeek dayOfWeek;
    LocalTime time;

    Lesson(DayOfWeek dayOfWeek, LocalTime time) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }
}
