public abstract class Task {
    private String name;
    private boolean mark = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(boolean mark, String name) {
        this.name = name;
        this.mark = mark;
    }

    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }

    public String store() {
        String marked = "X";
        if(!mark) {
            marked = " ";
        }
        return String.format("%s|%s", marked, name);
    }

    public static Task parseSavedTask(String task) {
        String[] params = task.split("\\|");
        boolean mark = params[1].equals("X");
        switch (params[0]) {
            case "T":
                return new ToDo(mark, params[2]);
            case "D":
                return new Deadline(mark, params[2], params[3]);
            case "E":
                return new Event(mark, params[2], params[3], params[4]);
            default:
                return null;
        }
    } 

    public static Task parseTask(String input) {
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
    
    private static ToDo parseTodo(String input) {
        if (input.isBlank()) {
            System.out.println("you specified tasktype but there was no task T.T. You cheated on me!!!");
            return null;
        }
        ToDo todo = new ToDo(input);
        return todo;
    }

    private static Deadline parseDeadline(String input) {
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

    private static Event parseEvent(String input) {
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
        return event;
    }

    @Override
    public String toString() {
        String header = "[";
        if (mark) {
            header += "X";
        }
        header += "]";
        return String.format("%s %s", header, name);
    }
}
