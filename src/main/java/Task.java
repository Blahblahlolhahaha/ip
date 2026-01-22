public abstract class Task {
    private String name;
    private boolean mark = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }

    public String store() {
        String marked = "X";
        if(!mark) {
            marked = " ";
        }
        return String.format("%s|%s", marked, name);
    }

    @Override
    public String toString() {
        String header = "[";
        if (mark) {
            header += "X";
        }
        header += "]";
        return String.format("%s %s", header, name);
    }

    public static Task parseTask(String task) {
        String[] params = task.split("|");
        switch (params[0]) {
            case "T":
                return new ToDo(params[1]);
            case "D":
                return new Deadline(params[1], params[2]);
            case "E":
                return new Event(params[1], params[2], params[3]);
            default:
                return null;
        }
    } 
}
