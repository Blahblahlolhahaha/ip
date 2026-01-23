import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(boolean mark, String name, LocalDate deadline) {
        super(mark, name);
        this.deadline = deadline;
    }

    @Override
    public String store() {
        String deadlineStr = deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("D|%s|%s", super.store(), deadlineStr);
    }

    @Override
    public String toString() {
        String deadlineStr = deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), deadlineStr);
    }
}
