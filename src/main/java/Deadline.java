public class Deadline extends Task{
    String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(boolean mark, String name, String deadline) {
        super(mark, name);
        this.deadline = deadline;
    }

    @Override
    public String store() {
        return String.format("D|%s|%s", super.store(), deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
