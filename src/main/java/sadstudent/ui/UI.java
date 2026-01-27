package sadstudent.ui;
import java.util.Scanner;

public class UI {
    
    Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showMessage(String output) {
        System.out.println(output);
    }

    public void greet() {
        String text = """                        
            %..............%            
         ......................         
      *..........................#      
     ..............................     
   +........   %........%   ........#   
   ........     #......+     ........:  
  ......... @@@ %......*-@@@ .........  
  .........-@@@@........@@@@..........: 
 *....................................% 
  ....................................: 
  ....................................  
   .............%@....@@..............  
   =.........%............@.........*   
     .......+...............@......     
      +..........................*      
         ......................         
            *..............#            
                """;
        System.out.println(text + "Hello I'm a sad student\nHow can I help you today?");
    }

    public String getInput() {
        String input = scanner.nextLine().strip();
        return input;
    }

    public void close() {
        scanner.close();
    }
}
