import java.time.LocalDate;

public class AttendanceRecord {
    Student student;
    Lesson lesson;
    LocalDate date;
    AttendanceStatus status;

    AttendanceRecord(Student student, Lesson lesson, LocalDate date, AttendanceStatus status) {
        this.student = student;
        this.lesson = lesson;
        this.date = date;
        this.status = status;
    }
}
