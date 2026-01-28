package sadstudent.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;

import sadstudent.exceptions.SadStudentException;

public abstract class Task {
    private String name;
    private boolean mark = false;
    private static String[] dateFormats = { "yyyy-MM-dd", "dd/MM/yyyy", "yyyy-MM-dd HHmm", "dd/MM/yyyy HHmm" };
    private static HashSet<String> taskTypes = new HashSet<>(List.of("todo", "deadline", "event"));

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

    /**
     * Converts the task into a String format to be stored in a file
     * @return Converted task
     */
    public String store() {
        String marked = "X";
        if (!mark) {
            marked = " ";
        }
        return String.format("%s|%s", marked, name);
    }

    /**
     * Parses the task that is loaded from a file and returns a Task corresponding
     * to the input provided
     * 
     * @param task input to be parsed
     * @return The parsed task
     */
    public static Task parseSavedTask(String task) {
        String[] params = task.split("\\|");
        boolean mark = params[1].equals("X");
        switch (params[0]) {
            case "T":
                return new ToDo(mark, params[2]);
            case "D":
                LocalDate by = Task.parseDate(params[3]);
                return new Deadline(mark, params[2], by);
            case "E":
                LocalDate to = Task.parseDate(params[3]);
                LocalDate from = Task.parseDate(params[4]);
                return new Event(mark, params[2], from, to);
            default:
                return null;
        }
    }

    /**
     * Parses the task to be created and returns it
     * @param input The task to be parsed and created
     * @return The parsed task
     * @throws SadStudentException If the task type provided is not supported or the format provided is incorrect
     */
    public static Task parseTask(String input) throws SadStudentException{
        String taskType = input.split(" ")[0];
        if (!Task.taskTypes.contains(taskType)) {
            throw new SadStudentException(
                    "I cant support this task type now ;-; Please choose from (todo, deadline, event)");
        }
        String params = input.replaceFirst(taskType, "");
        params = params.strip();
        switch (taskType) {
            case "todo":
                return parseTodo(params);
            case "deadline":
                return parseDeadline(params);
            case "event":
                return parseEvent(params);
        }
        return null;
    }

    private static ToDo parseTodo(String input) {
        if (input.isBlank()) {
            throw new SadStudentException("you specified tasktype but there was no task T.T. You cheated on me!!!");
        }
        ToDo todo = new ToDo(input);
        return todo;
    }

    private static Deadline parseDeadline(String input) {
        if (input.isBlank()) {
            throw new SadStudentException("you specified tasktype but there was no task T.T. You cheated on me!!!");
        }
        String[] params = input.split(" /");
        String name = params[0];
        String byStr = "";
        for (int i = 1; i < params.length; i++) {
            if (params[i].startsWith("by ")) {
                byStr = params[i].replaceFirst("by ", "");
            }
        }
        if (byStr.isBlank()) {
            throw new SadStudentException("ehhh Deadline requires by field!\nUsage: deadline <task> /by <by>");
        }
        LocalDate by = Task.parseDate(byStr);
        Deadline deadline = new Deadline(name, by);
        return deadline;
    }

    private static Event parseEvent(String input) {
        if (input.isBlank()) {
            throw new SadStudentException("you specified tasktype but there was no task T.T. You cheated on me!!!");
        }
        String[] params = input.split(" /");
        String name = params[0];
        String fromStr = "";
        String toStr = "";
        for (int i = 1; i < params.length; i++) {
            if (params[i].startsWith("from ")) {
                fromStr = params[i].replaceFirst("from ", "");
            } else if (params[i].startsWith("to ")) {
                toStr = params[i].replaceFirst("to ", "");
            }
        }
        if (toStr.isBlank() || fromStr.isBlank()) {
            throw new SadStudentException(
                    "ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>");
        }
        LocalDate to = Task.parseDate(toStr);
        LocalDate from = Task.parseDate(fromStr);
        Event event = new Event(name, from, to);
        return event;
    }

    private static LocalDate parseDate(String dateString) {
        int index = 0;
        while (index < dateFormats.length) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormats[index]));
            } catch (DateTimeParseException e) {
                index++;
            }
        }
        throw new DateTimeParseException(dateString, dateString, 0);
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task task) {
            return this.mark == task.mark && this.name.equals(task.name);
        }
        return false;
    }
}
