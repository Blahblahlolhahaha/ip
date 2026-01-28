package sadstudent.ui;
import java.util.Scanner;

public class UI {
    
    Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays message to console
     * @param output Message to be displayed
     */
    public void showMessage(String output) {
        System.out.println(output);
    }

    /**
     * Displays opening banner
     */
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

    /**
     * Gets an input line from user and returns it
     * @return The line inputted by the user
     */
    public String getInput() {
        String input = scanner.nextLine().strip();
        return input;
    }

    public void close() {
        scanner.close();
    }
}
