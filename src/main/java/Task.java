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

    @Override
    public String toString() {
        String header = "[";
        if (mark) {
            header += "X";
        }
        header += "]";
        return String.format("%s %s", header, name);
    }
}
