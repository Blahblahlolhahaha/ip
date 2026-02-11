package sadstudent.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import sadstudent.exceptions.SadStudentException;
import sadstudent.task.Task;
import sadstudent.task.TaskList;

public class Storage {
    private Path path = Paths.get("./tasks.txt");

    public TaskList loadFile() {
        File file = path.toFile();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (Stream<String> lines = Files.lines(path)) {
                TaskList taskList = new TaskList();
                lines.map(line -> Task.parseSavedTask(line))
                    .forEach(task -> taskList.addTask(task));
                return taskList;
            }
        }
        catch (IOException e) {
            return null;
        }
    }

    /**
     * Stores the tasklist in a file
     * 
     * @param taskList List to be stored
     * @throws IOException if I/O error occurs while saving the list
     */
    public void storeFile(TaskList taskList) throws IOException {
        assert taskList != null : "TaskList cannot be null";
        String res = taskList.storeString();
        File file = path.toFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(res);
        bw.close();
        assert file.exists() : "File should exist after writing";
    }
}
