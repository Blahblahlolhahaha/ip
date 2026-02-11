package sadstudent.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sadstudent.exceptions.SadStudentException;
import sadstudent.task.Task;
import sadstudent.task.TaskList;

public class Storage {
    private File file = new File("./tasks.txt");

    public TaskList loadFile() {
        try(Scanner scanner = new Scanner(file);){
            if (!file.exists()) {
                file.createNewFile();
            }
            TaskList taskList = new TaskList();
            assert taskList != null : "TaskList cannot be null after creation";
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                assert taskLine != null : "Task line cannot be null";
                Task task = Task.parseSavedTask(taskLine);
                if (task != null) {
                    taskList.addTask(task);
                    assert taskList.getNumberOfTasks() > 0 : "Task list should not be empty after adding";
                }
                else {
                    throw new SadStudentException("Save file is corrupted!");
                }
            }
            scanner.close();
            assert taskList != null : "TaskList cannot be null before return";
            return taskList;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Stores the tasklist in a file
     * @param taskList List to be stored
     * @throws IOException if I/O error occurs while saving the list
     */
    public void storeFile(TaskList taskList) throws IOException {
        assert taskList != null : "TaskList cannot be null";
        String res = taskList.storeString();
        assert res != null : "Storable string cannot be null";
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(res);
        bw.close();
        assert file.exists() : "File should exist after writing";
    }
}
