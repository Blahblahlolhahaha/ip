import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String input) {
        Task task = new Task(input);
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Task task : tasks) {
            sb.append(task.toString() + "\n");
        }
        return sb.toString().strip();
    }
}
