public class Event extends Task{
    String from;
    String to;
    
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(boolean mark, String name, String from, String to) {
        super(mark, name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String store() {
        return String.format("E|%s|%s|%s", super.store(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}