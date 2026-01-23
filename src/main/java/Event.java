import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

public class Event extends Task{
    LocalDate from;
    LocalDate to;
    
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(boolean mark, String name, LocalDate from, LocalDate to) {
        super(mark, name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String store() {
        String fromStr = from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String toStr = to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("E|%s|%s|%s", super.store(), fromStr, toStr);
    }

    @Override
    public String toString() {
        String fromStr = from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String toStr = to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fromStr, toStr);
    }
}