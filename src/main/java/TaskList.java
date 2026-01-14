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

    public String mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task.getName();
    }

    public String unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return task.getName();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            sb.append(String.format("%d:%s\n", i, task.toString()));
        }
        return sb.toString().strip();
    }
}
