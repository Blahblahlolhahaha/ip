public abstract class Task {
    private String name;
    private boolean mark = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(boolean mark, String name) {
        this.name = name;
        this.mark = mark;
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
        String[] params = task.split("\\|");
        boolean mark = params[1].equals("X");
        switch (params[0]) {
            case "T":
                return new ToDo(mark, params[2]);
            case "D":
                return new Deadline(mark, params[2], params[3]);
            case "E":
                return new Event(mark, params[2], params[3], params[4]);
            default:
                return null;
        }
    } 
}
