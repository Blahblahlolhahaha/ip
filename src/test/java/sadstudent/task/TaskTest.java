package sadstudent.task;

import org.junit.jupiter.api.Test;
import sadstudent.exceptions.SadStudentException;
import sadstudent.parser.Parser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {

    @Test
    public void parseTaskTest() {
        String correctDeadlineInput = "deadline finish assignment /by 2026-02-16";
        assertEquals(Task.parseTask(correctDeadlineInput),
                new Deadline("finish assignment", LocalDate.parse("2026-02-16")));

        String differentDateFormat = "deadline finish assignment /by 16/02/2026";
        assertEquals(Task.parseTask(differentDateFormat),
                new Deadline("finish assignment", LocalDate.parse("2026-02-16")));

        String noBy = "deadline finish assignment /by";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(noBy);
        }, "SadStudentException was expected");
        assertEquals("ehhh Deadline requires by field!\nUsage: deadline <task> /by <by>", sad.getMessage());

        String onlyTaskType = "deadline";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(onlyTaskType);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());

        String correctEventInput = "event see fren /from 12/02/2026 0800 /to 13/02/2026 0800";
        assertEquals(Task.parseTask(correctEventInput),
                new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));

        String differentOrder = "event see fren /to 13/02/2026 0800 /from 12/02/2026 0800";
        assertEquals(Task.parseTask(correctEventInput),
                new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));

        String missingFrom = "event see fren /to 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingFrom);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());

        String missingTo = "event see fren /from 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingTo);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());

        String missingBoth = "event see fren";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingBoth);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());

        String justEvent = "event";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(justEvent);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());

        String weirdInput = "event /from 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(weirdInput);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());

        String correctTodoString = "todo crying myself to sleep tonight";
        assertEquals(Task.parseTask(correctTodoString), new ToDo("crying myself to sleep tonight"));

        String missingTask = "todo";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(justEvent);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());
    }

    @Test
    public void parseCommandTest() {
        TaskList list = new TaskList();
        list.addTask(new Deadline("finish assignment", LocalDate.parse("2026-02-16")));
        list.addTask(new ToDo("crying myself to sleep tonight"));
        list.addTask(new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));

        Parser parser = new Parser();
        assertEquals(list.getNumberOfTasks(), 3);
        assertEquals(parser.parseCommand("delete 1", list),
                String.format("Task removed: %s\nOut of sight out of mind :D",
                        new Deadline("finish assignment", LocalDate.parse("2026-02-16")).toString()));
        assertEquals(list.getNumberOfTasks(), 2);
        assertEquals(parser.parseCommand("list", list), list.toString());

        TaskList listt = new TaskList();
        listt.addTask(new Deadline("finish assignment", LocalDate.parse("2026-02-16")));
        listt.addTask(new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        listt.addTask(new ToDo("crying myself to sleep tonight"));
        ToDo todo = new ToDo("crying myself to sleep tonight");
        todo.mark();
        assertEquals(parser.parseCommand("mark 3", listt),
                String.format("Yay, one task closer to sleeping! :D\n%s", todo.toString()));
        todo.unmark();
        assertEquals(parser.parseCommand("unmark 3", listt),
                String.format("Awwww please dont sike me :(\n%s", todo.toString()));
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            parser.parseCommand("unmark 4", listt);
        });
        assertEquals(sad.getMessage(), "Nuuuu index out of range :(");
        sad = assertThrows(SadStudentException.class, () -> {
            parser.parseCommand("unmark 0", listt);
        });
        assertEquals(sad.getMessage(), "Nuuuu index out of range :(");

        TaskList listtt = new TaskList();
        listtt.addTask(new ToDo("crying myself to sleep tonight"));
        listtt.addTask(new Deadline("finish assignment", LocalDate.parse("2026-02-16")));
        listtt.addTask(new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        ToDo todo2 = new ToDo("crying myself to sleep tonight");
        todo2.mark();
        assertEquals(parser.parseCommand("mark 1", listtt),
                String.format("Yay, one task closer to sleeping! :D\n%s", todo2.toString()));
        todo2.unmark();
        assertEquals(parser.parseCommand("unmark 1", listtt),
                String.format("Awwww please dont sike me :(\n%s", todo2.toString()));

        TaskList searchList = new TaskList();
        searchList.addTask(new ToDo("crying myself to sleep tonight"));
        assertEquals(parser.parseCommand("find crying",listtt), String.format("tada! Here are the matches for \"crying\":\n%s", searchList.toString()));

        assertEquals(parser.parseCommand("find die",listtt), "There are no matches for: die ;-;");
    }

}
