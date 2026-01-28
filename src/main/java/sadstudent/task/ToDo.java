package sadstudent.task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    public ToDo(boolean mark, String name) {
        super(mark, name);
    }

    @Override
    public String store() {
        return String.format("T|%s", super.store());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
