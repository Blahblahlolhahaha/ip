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
            case "deadline":
                return parseDeadline(input.substring(input.indexOf("deadline ") + 9));
            default:
                System.out.println("I cant support this task type now ;-; Please choose from (todo, deadline)");
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
        if (input.isBlank()) {
            System.out.println("you specified tasktype but there was no task T.T. You cheated on me!!!");
            return null;
        }
        ToDo todo = new ToDo(input);
        tasks.add(todo);
        return todo;
    }

    private Deadline parseDeadline(String input) {
        if (input.isBlank()) {
            System.out.println("you specified tasktype but there was no task T.T. You cheated on me!!!");
            return null;
        }
        String[] params = input.split(" /");
        String name = params[0];
        String by = "";
        for (int i = 1; i < params.length; i++) {
            if (params[i].startsWith("by ")) {
                by = params[i].replaceFirst("by ", "");
            }
        }
        if (by.isBlank()) {
            System.out.println("ehhh Deadline requires by field!\n Usage: deadline <task> /by <by>");
            return null;
        }
        Deadline deadline = new Deadline(name, by);
        return deadline;
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
