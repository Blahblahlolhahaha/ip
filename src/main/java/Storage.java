import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file = new File("./tasks.txt");

    public Storage() throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
    }

    public TaskList loadFile() throws FileNotFoundException {
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

    public void storeFile(TaskList taskList) throws IOException {
        String res = taskList.storeString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(res);
        bw.close();
    } 
}
