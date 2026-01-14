import java.util.ArrayList;
import java.util.Scanner;

public class SadStudent {
    public static void main(String[] args) {
        System.out.println("Hello I'm a sad student! ｡°(°.◜ᯅ◝°)°｡\nHow can I help you today?");
        ArrayList<Task> tasks = new ArrayList<>();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("list")) {
                StringBuffer sb = new StringBuffer();
                for(Task task : tasks) {
                    sb.append(task.toString() + "\n");
                }
                System.out.println(sb.toString().stripTrailing());
            }
            else if(input.equals("bye")) {
                scanner.close();
                break;
            }
            else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Alright I go and cry myself to sleep T.T");
    }
}
