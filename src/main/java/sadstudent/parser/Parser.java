package sadstudent.parser;

import sadstudent.exceptions.SadStudentException;
import sadstudent.task.Task;
import sadstudent.task.TaskList;

public class Parser {

    /**
     * Parses the command inputted by the user and performs the action based on the
     * command on the TaskList provided
     * Supported commands are ["list", "bye", "delete", "mark", "unmark"]
     * If command is not in supported command list, a task will be added to TaskList
     * provided instead
     * Supported task types are ["deadline", "event", "todo"]
     * 
     * @param input User command to be parsed
     * @param list  List of Tasks by the user
     * @return Response of the bot
     * @throws SadStudentException when: index provided for delete/mark/unmark is
     *                             out of range or command is unsupported
     */
    public static String parseCommand(String input, TaskList list) throws SadStudentException {
        assert input != null : "Input cannot be null";
        assert list != null : "TaskList cannot be null";
        assert !input.isBlank() : "Input cannot be blank";
        String[] params = input.split(" ");
        switch (params[0]) {
        case "list":
            return list.toString();
        case "bye":
            return "";
        case "delete":
            return deleteTask(params, list);
        case "mark":
        case "unmark":
            return markTask(params, list);
        case "find":
            return findTask(input, list);
        case "priority":
            return setPriority(params, list);
        default:
            return addTask(input, list);
        }
    }

    private static String deleteTask(String[] params, TaskList list) {
        int index = Integer.parseInt(params[1]) - 1;
        Task task = list.removeTask(index);
        if (task != null) {
            return String.format("Task removed: %s\nOut of sight out of mind :D", task.toString());
        } else {
            throw new SadStudentException("Nuuuu index out of range :(");
        }
    }

    private static String markTask(String[] params, TaskList list) {
        int index = Integer.parseInt(params[1]) - 1;
        String ending = "Yay, one task closer to sleeping! :D";
        String res = "";
        if (params[0].startsWith("un")) {
            ending = "Awwww please dont sike me :(";
            res = list.unmark(index);
        } else {
            res = list.mark(index);
        }
        if (res == "") {
            throw new SadStudentException("Nuuuu index out of range :(");
        } else {
            assert !res.isBlank() : "Mark/unmark result should not be blank";
            return String.format("%s\n%s", ending, res);
        }
    }

    private static String findTask(String input, TaskList list) {
        String search = input.replaceFirst("find ", "");
        assert !search.isBlank() : "Search query should not be blank";
        TaskList searchList = list.findTasks(search);
        assert searchList != null : "Search result list cannot be null";
        if (searchList.getNumberOfTasks() == 0) {
            return String.format("There are no matches for: %s ;-;", search);
        }
        return String.format("tada! Here are the matches for \"%s\":\n%s", search, searchList.toString());
    }

    private static String addTask(String input, TaskList list) {
        Task task = Task.parseTask(input);
        if (task != null) {
            list.addTask(task);
            return String.format("added: %s\nYou have %d tasks now!", task.toString(),
                    list.getNumberOfTasks());
        }
        throw new SadStudentException("An error occured parsing the input ;-;");
    }

    private static String setPriority(String[] params, TaskList list) {
        try {
            if(params.length < 3) {
                throw new SadStudentException("Please enter a valid task/priority! (They should be numeric!)");
            }
            int index = Integer.parseInt(params[1]) - 1;
            int priority = Integer.parseInt(params[2]);
            String res = list.setPriority(index, priority);
            if (res == "") {
                throw new SadStudentException("Nuuuu index out of range :(");
            }
            assert !res.isBlank() : "Set priority result should not be blank";
            return String.format("OMG you are prioritising tasks :0. Task changed: %s", res);
        } catch(NumberFormatException e) {
            throw new SadStudentException("Please enter a valid task/priority! (They should be numeric!)");
        }
    } 
}
