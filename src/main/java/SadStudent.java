import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SadStudent {

    private UI ui;
    private Parser parser;

    public SadStudent() {
        this.ui = new UI();
        this.parser = new Parser();
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
                String res = parser.parseCommand(input, list);
                if(res.isBlank()) {
                    ui.showMessage("Alright I go and cry myself to sleep T.T");
                    break;
                }
                ui.showMessage(res);
                storage.storeFile(list);
            } catch (DateTimeParseException e) {
                ui.showMessage("Please indicate a valid date!");
            } catch (NumberFormatException e) {
                ui.showMessage("Please indicate a valid task!");
            } catch (IOException f) {
                ui.showMessage("An error occured saving tasks to file ;-;");
            } catch (SadStudentException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new SadStudent().run();
    }
}
