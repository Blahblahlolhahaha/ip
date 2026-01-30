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
     * @throws SadStudentException when: index provided for delete/mark/unmark is out of range or command is unsupported
     */
    public static String parseCommand(String input, TaskList list) throws SadStudentException{
        if (input.equals("list")) {
            return list.toString();
        } else if (input.equals("bye")) {
            // ui.showMessage("Alright I go and cry myself to sleep T.T");
            return "";
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = list.removeTask(index);
            if (task != null) {
                return String.format("Task removed: %s\nOut of sight out of mind :D", task.toString());
            } else {
                throw new SadStudentException("Nuuuu index out of range :(");
            }
        } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            String ending = "Yay, one task closer to sleeping! :D";
            String res = "";
            if (input.startsWith("un")) {
                ending = "Awwww please dont sike me :(";
                res = list.unmark(index);
            } else {
                res = list.mark(index);
            }
            if (res == "") {
                throw new SadStudentException("Nuuuu index out of range :(");
            } else {
                return String.format("%s\n%s", ending, res);
            }
        } else if(input.startsWith("find ")) {
            String search = input.replaceFirst("find ", "");
            TaskList searchList = list.findTasks(search);
            if(searchList.getNumberOfTasks() == 0) {
                return String.format("There are no matches for: %s ;-;", search);
            }
            return String.format("tada! Here are the matches for \"%s\":\n%s", search, searchList.toString());
        } else {
            Task task = Task.parseTask(input);
            if (task != null) {
                list.addTask(task);
                return String.format("added: %s\nYou have %d tasks now!", task.toString(),
                        list.getNumberOfTasks());
            }
            throw new SadStudentException("An error occured parsing the input ;-;");
        }
    }
}
