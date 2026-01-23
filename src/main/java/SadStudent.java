import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SadStudent {
    public static void main(String[] args) {
        String text = "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░██████░░░░░░░░██████░░░░░░\r\n" + //
                "░░░░██░░░░░░░░░░░░░░░░░░░░██░░░░\r\n" + //
                "░░██░░░░░░░░░░░░░░░░░░░░░░░░██░░\r\n" + //
                "░░░░░░████░░░░░░░░░░░░████░░░░░░\r\n" + //
                "░░░░░░████░░░░░░░░░░░░████░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░░░░░████████████░░░░░░░░░░\r\n" + //
                "░░░░░░░░████████████████░░░░░░░░\r\n" + //
                "░░░░░░████░░░░░░░░░░░░████░░░░░░\r\n" + //
                "░░░░░░██░░░░░░░░░░░░░░░░██░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" + //
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";
        System.out.println(text + "Hello I'm a sad student\nHow can I help you today?");
        Storage storage = new Storage();
        TaskList list = storage.loadFile();
        if (list == null) {
            System.out.println("An error occured opening the file ;-;");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {

            try {
                String input = scanner.nextLine().strip();
                if (input.equals("list")) {
                    System.out.println(list.toString());
                } else if (input.equals("bye")) {
                    System.out.println("Alright I go and cry myself to sleep T.T");
                    scanner.close();
                    break;
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = list.removeTask(index);
                    if (task != null) {
                        System.out.println(
                                String.format("Task removed: %s\n Out of sight out of mind :D", task.toString()));

                    } else {
                        System.out.println("Nuuuu index out of range :(");
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
                        System.out.println("Nuuuu index out of range :(");
                    } else {
                        System.out.println(String.format("%s\n%s", ending, res));
                    }
                } else {
                    Task task = Task.parseTask(input);
                    if (task != null) {
                        list.addTask(task);
                    }
                    if (task != null) {
                        System.out.println(String.format("added: %s\nYou have %d tasks now!", task.toString(),
                                list.getNumberOfTasks()));
                    }
                }
                storage.storeFile(list);
            } catch (DateTimeParseException e) {
                System.out.println("Please indicate a valid date!");
            } catch (NumberFormatException e) {
                System.out.println("Please indicate a valid task!");
            } catch (IOException f) {
                System.out.println("An error occured saving tasks to file ;-;");

            }
        }

    }
}
