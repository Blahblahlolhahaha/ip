package sadstudent;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import sadstudent.exceptions.SadStudentException;
import sadstudent.parser.Parser;
import sadstudent.storage.Storage;
import sadstudent.task.TaskList;
import sadstudent.ui.UI;

public class SadStudent {

    private UI ui;
    private TaskList list;
    private Storage storage;

    public SadStudent() {
        this.ui = new UI();
        this.storage = new Storage();
        this.list = storage.loadFile();
        if (list == null) {
            throw new SadStudentException("An error occured opening the file ;-;");
        }
    }

    public void run() {
        ui.greet();
        while (true) {
            String input = ui.getInput();
            String response = getResponse(input);
            ui.showMessage(response);
        }
    }

    public String getResponse(String input) {
        try {
            String res = Parser.parseCommand(input, list);
            if (res.isBlank()) {
                return "Alright I go and cry myself to sleep T.T";
            }
            storage.storeFile(list);
            return res;
        } catch (DateTimeParseException e) {
            return "Please indicate a valid date!";
        } catch (NumberFormatException e) {
            return "Please indicate a valid task!";
        } catch (IOException f) {
            return "An error occured saving tasks to file ;-;";
        } catch (SadStudentException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new SadStudent().run();
    }
}
