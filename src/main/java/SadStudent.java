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
        while(true){
            String input = scanner.nextLine();
            if(input.equals("list")) {
                
                System.out.println(list.toString());
            }
            else if(input.equals("bye")) {
                scanner.close();
                break;
            }
            else {
                list.addTask(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Alright I go and cry myself to sleep T.T");
    }
}
