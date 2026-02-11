package sadstudent.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        assert task != null : "Cannot add null task to list";
        this.tasks.add(task);
        assert this.tasks.contains(task) : "Task was not added to list";
    }

    /**
     * Marks the task at given index, returns empty string if index is out of range
     * 
     * @param index
     * @return String representation of the task that is marked
     */
    public String mark(int index) {
        assert index >= -1 : "Index should be non-negative";
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.mark();
        assert task != null : "Task should not be null after retrieval";
        return task.toString();
    }

    /**
     * Unmarks the task at given index, returns empty string if index is out of
     * range
     * 
     * @param index
     * @return String representation of the task that is unmarked
     */
    public String unmark(int index) {
        assert index >= -1 : "Index should be non-negative";
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.unmark();
        assert task != null : "Task should not be null after retrieval";
        return task.toString();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Removes the task at the given index and returns it
     * 
     * @param index
     * @return Task removed
     */
    public Task removeTask(int index) {
        if (index >= tasks.size() || index < 0) {
            return null;
        }
        Task task = tasks.remove(index);
        return task;
    }

    /**
     * Converts all the tasks to a format to be stored in a file
     * 
     * @return Storable String format of all tasks stored in the list, separated by
     *         \n
     */
    public String storeString() {
        StringBuffer sb = new StringBuffer();
        for (Task task : tasks) {
            sb.append(task.store() + "\n");
        }
        return sb.toString();
    }

    public TaskList findTasks(String str) {
        TaskList res = new TaskList();
        for (Task x : this.tasks) {
            if (x.nameMatch(str)) {
                res.addTask(x);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            sb.append(String.format("%d:%s\n", i, task.toString()));
        }
        return sb.toString().strip();
    }
}
