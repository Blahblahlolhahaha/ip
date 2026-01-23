import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SadStudent {

    private UI ui;

    public SadStudent() {
        this.ui = new UI();
    }

    public void run() {
        ui.greet();
        Storage storage = new Storage();
        TaskList list = storage.loadFile();
        if (list == null) {
            ui.showMessage("An error occured opening the file ;-;");
            return;
        }
        while (true) {
            try {
                String input = ui.getInput();
                if (input.equals("list")) {
                    ui.showMessage(list.toString());
                } else if (input.equals("bye")) {
                    ui.showMessage("Alright I go and cry myself to sleep T.T");
                    break;
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = list.removeTask(index);
                    if (task != null) {
                        ui.showMessage(
                                String.format("Task removed: %s\n Out of sight out of mind :D", task.toString()));

                    } else {
                        ui.showMessage("Nuuuu index out of range :(");
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
                        ui.showMessage("Nuuuu index out of range :(");
                    } else {
                        ui.showMessage(String.format("%s\n%s", ending, res));
                    }
                } else {
                    Task task = Task.parseTask(input);
                    if (task != null) {
                        list.addTask(task);
                    }
                    if (task != null) {
                        ui.showMessage(String.format("added: %s\nYou have %d tasks now!", task.toString(),
                                list.getNumberOfTasks()));
                    }
                }
                storage.storeFile(list);
            } catch (DateTimeParseException e) {
                ui.showMessage("Please indicate a valid date!");
            } catch (NumberFormatException e) {
                ui.showMessage("Please indicate a valid task!");
            } catch (IOException f) {
                ui.showMessage("An error occured saving tasks to file ;-;");

            }
        }
    }

    public static void main(String[] args) {
        new SadStudent().run();
    }
}
