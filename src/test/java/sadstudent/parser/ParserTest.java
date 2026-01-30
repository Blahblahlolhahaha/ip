package sadstudent.parser;

import org.junit.jupiter.api.Test;
import sadstudent.task.Deadline;
import sadstudent.task.ToDo;
import sadstudent.task.Event;
import sadstudent.task.TaskList;
import sadstudent.exceptions.SadStudentException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseDeleteCommand_success() {
        TaskList list = new TaskList();
        list.addTask(new Deadline("finish assignment",
                LocalDate.parse("2026-02-16")));
        list.addTask(new ToDo("crying myself to sleep tonight"));
        list.addTask(new Event("see fren", LocalDate.parse("2026-02-12"),
                LocalDate.parse("2026-02-13")));
        assertEquals(list.getNumberOfTasks(), 3);
        assertEquals(Parser.parseCommand("delete 1", list),
                String.format("Task removed: %s\nOut of sight out of mind :D",
                        new Deadline("finish assignment",
                                LocalDate.parse("2026-02-16")).toString()));
        assertEquals(list.getNumberOfTasks(), 2);
        assertEquals(Parser.parseCommand("list", list), list.toString());
    }

    @Test
    public void parseMarkCommandandUnmarkCommand_success() {
        TaskList listt = new TaskList();
        listt.addTask(new Deadline("finish assignment",
                LocalDate.parse("2026-02-16")));
        listt.addTask(new Event("see fren", LocalDate.parse("2026-02-12"),
                LocalDate.parse("2026-02-13")));
        listt.addTask(new ToDo("crying myself to sleep tonight"));
        ToDo todo = new ToDo("crying myself to sleep tonight");
        todo.mark();
        assertEquals(Parser.parseCommand("mark 3", listt),
                String.format("Yay, one task closer to sleeping! :D\n%s", todo.toString()));
        todo.unmark();
        assertEquals(Parser.parseCommand("unmark 3", listt),
                String.format("Awwww please dont sike me :(\n%s", todo.toString()));
    }

    @Test
    public void parseMarkCommand_indexOutOfRange_exceptionThrown() {  
        TaskList listt = new TaskList();
        listt.addTask(new Deadline("finish assignment",
                LocalDate.parse("2026-02-16")));
        listt.addTask(new Event("see fren", LocalDate.parse("2026-02-12"),
                LocalDate.parse("2026-02-13")));
        listt.addTask(new ToDo("crying myself to sleep tonight"));
        ToDo todo = new ToDo("crying myself to sleep tonight");
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Parser.parseCommand("unmark 4", listt);
        });
        assertEquals(sad.getMessage(), "Nuuuu index out of range :(");
        sad = assertThrows(SadStudentException.class, () -> {
            Parser.parseCommand("unmark 0", listt);
        });
        assertEquals(sad.getMessage(), "Nuuuu index out of range :(");
    }

    @Test
    public void parseFindCommand_haveResults_success() {
        TaskList searchList = new TaskList();
        searchList.addTask(new ToDo("crying myself to sleep tonight"));
        assertEquals(Parser.parseCommand("find crying", 
                searchList),
                String.format("tada! Here are the matches for \"crying\":\n%s", searchList.toString()));
    }

    @Test
    public void parseFindCommand_noResults_success() {
        TaskList searchList = new TaskList();
        searchList.addTask(new ToDo("crying myself to sleep tonight"));
        assertEquals(Parser.parseCommand("find die", searchList), "There are no matches for: die ;-;");
    }
}
