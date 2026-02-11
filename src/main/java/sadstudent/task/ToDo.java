package sadstudent.task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
        assert name != null : "ToDo name cannot be null";
    }

    public ToDo(boolean mark, String name, int priority) {
        super(mark, name, priority);
        assert name != null : "ToDo name cannot be null";
    }

    @Override
    public String store() {
        return String.format("T|%s", super.store());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
