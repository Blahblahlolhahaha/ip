import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file = new File("./tasks.txt");

    public Storage() throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
    }

    public TaskList LoadFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        TaskList taskList = new TaskList();
        while(scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            Task task = Task.parseTask(taskLine);
            if(task != null) {
                taskList.addTask(task);
            }
        }
        scanner.close();
        return taskList;
    }
}
