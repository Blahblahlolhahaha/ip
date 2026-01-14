public class Task {
    private static int taskCount = 1;
    private int id;
    private String name;

    public Task(String name) {
        this.id = taskCount;
        this.name = name;
        taskCount += 1;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", id, name);
    }
}
