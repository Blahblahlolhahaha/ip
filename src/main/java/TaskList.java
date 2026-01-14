import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task parseTask(String input) {
        String taskType = input.split(" ")[0];

        switch (taskType) {
            case "todo":
                return parseTodo(input.substring(input.indexOf("todo ") + 5));
            default:
                return null;
        }
    }

    public String mark(int index) {
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.mark();
        return task.toString();
    }

    public String unmark(int index) {
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.unmark();
        return task.toString();
    }

    private ToDo parseTodo(String input) {
        ToDo todo = new ToDo(input);
        tasks.add(todo);
        return todo;
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
