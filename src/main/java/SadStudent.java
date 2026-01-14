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
        TaskList list = new TaskList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().strip();
            if (input.equals("list")) {
                System.out.println(list.toString());
            } else if (input.equals("bye")) {
                scanner.close();
                break;
            } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                try {
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
                } catch (NumberFormatException e) {
                    System.out.println("Please mark a valid task!");
                }
            } else {
                Task task = list.parseTask(input);
                if (task != null) {
                    System.out.println("added: " + task.toString());
                }
            }
        }
        System.out.println("Alright I go and cry myself to sleep T.T");
    }
}
