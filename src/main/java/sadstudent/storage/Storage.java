package sadstudent.storage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sadstudent.task.Task;
import sadstudent.task.TaskList;

public class Storage {
    private File file = new File("./tasks.txt");

    public TaskList loadFile() {
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            TaskList taskList = new TaskList();
            while(scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                Task task = Task.parseSavedTask(taskLine);
                if(task != null) {
                    taskList.addTask(task);
                }
            }
            scanner.close();
            return taskList;
        } catch(IOException e) {
            return null;
        }
    }

    /**
     * Stores the tasklist in a file
     * @param taskList List to be stored
     * @throws IOException if I/O error occurs while saving the list
     */
    public void storeFile(TaskList taskList) throws IOException {
        String res = taskList.storeString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(res);
        bw.close();
    } 
}
