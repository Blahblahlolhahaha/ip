import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task parseTask(String input) {
        String taskType = input.split(" ")[0];
        String params = input.replaceFirst(taskType, "");
        params = params.strip();
        
        switch (taskType) {
            case "todo":
                return parseTodo(params);
            case "deadline":
                return parseDeadline(params);
            case "event":
                return parseEvent(params); 
            default:
                System.out.println("I cant support this task type now ;-; Please choose from (todo, deadline, event)");
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

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task removeTask(int index) {
        if (index >= tasks.size() || index < 0) {
            return null;
        }
        Task task = tasks.remove(index);
        return task;
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
        tasks.add(deadline);
        return deadline;
    }

    private Event parseEvent(String input) {
        if (input.isBlank()) {
            System.out.println("you specified tasktype but there was no task T.T. You cheated on me!!!");
            return null;
        }
        String[] params = input.split(" /");
        String name = params[0];
        String from = "";
        String to = "";
        for (int i = 1; i < params.length; i++) {
            if (params[i].startsWith("from ")) {
                from = params[i].replaceFirst("from ", "");
            }
            else if (params[i].startsWith("to ")) {
                to = params[i].replaceFirst("to ", "");
            }
        }
        if (to.isBlank() || from.isBlank()) {
            System.out.println("ehhh Event requires from and to field!\n Usage: event <task> /from <from> /to <to>");
            return null;
        }
        Event event = new Event(name, from, to);
        tasks.add(event);
        return event;
    }

    public String storeString() {
        StringBuffer sb = new StringBuffer();
        for(Task task : tasks) {
            sb.append(task.store() + "\n");
        }
        return sb.toString();
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
