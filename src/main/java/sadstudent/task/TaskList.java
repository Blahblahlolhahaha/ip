package sadstudent.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String mark(int index) {
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.mark();
        return task.toString();
    }

    public String unmark(int index) {
        if (index >= tasks.size() || index < 0) {
            return "";
        }
        Task task = this.tasks.get(index);
        task.unmark();
        return task.toString();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task removeTask(int index) {
        if (index >= tasks.size() || index < 0) {
            return null;
        }
        Task task = tasks.remove(index);
        return task;
    }

    public String storeString() {
        StringBuffer sb = new StringBuffer();
        for (Task task : tasks) {
            sb.append(task.store() + "\n");
        }
        return sb.toString();
    }

    public TaskList findTasks(String str) {
        TaskList res = new TaskList();
        for(Task x : this.tasks) {
            if(x.nameMatch(str)) {
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
