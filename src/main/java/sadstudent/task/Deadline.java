package sadstudent.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        assert name != null : "Deadline name cannot be null";
        assert deadline != null : "Deadline date cannot be null";
        this.deadline = deadline;
    }

    public Deadline(boolean mark, String name, LocalDate deadline) {
        super(mark, name);
        assert name != null : "Deadline name cannot be null";
        assert deadline != null : "Deadline date cannot be null";
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline othrDeadline) {
            return super.equals(othrDeadline) && this.deadline.equals(othrDeadline.deadline);
        }
        return false;
    }
}
