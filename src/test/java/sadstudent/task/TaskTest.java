package sadstudent.task;

import org.junit.jupiter.api.Test;
import sadstudent.exceptions.SadStudentException;
import sadstudent.parser.Parser;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {

    @Test
    public void insertDeadline_success() {
        String correctDeadlineInput = "deadline finish assignment /by 2026-02-16";
        assertEquals(Task.parseTask(correctDeadlineInput),
                new Deadline("finish assignment", LocalDate.parse("2026-02-16")));
    }

    @Test
    public void insertDeadline_differentDateFormat_success() {
        String differentDateFormat = "deadline finish assignment /by 16/02/2026";
        assertEquals(Task.parseTask(differentDateFormat),
                new Deadline("finish assignment", LocalDate.parse("2026-02-16")));
    }

    @Test
    public void insertDeadline_missingBy_exceptionThrown() {
        String noBy = "deadline finish assignment /by";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(noBy);
        }, "SadStudentException was expected");
        assertEquals("ehhh Deadline requires by field!\nUsage: deadline <task> /by <by>", sad.getMessage());
    }
    
    @Test
    public void insertDeadline_missingTask_exceptionThrown() {
        String onlyTaskType = "deadline";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(onlyTaskType);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());
    }

    @Test
    public void insertEvent_success() {
        String correctEventInput = "event see fren /from 12/02/2026 0800 /to 13/02/2026 0800";
        assertEquals(Task.parseTask(correctEventInput),
                new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
    }

    @Test
    public void insertEvent_differentOrder_success() {
        String differentOrder = "event see fren /to 13/02/2026 0800 /from 12/02/2026 0800";
        assertEquals(Task.parseTask(differentOrder),
                new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
    }

    @Test
    public void insertEvent_missingFrom_exceptionThrown() {
        String missingFrom = "event see fren /to 13/02/2026 0800";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingFrom);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());
    }

    @Test
    public void insertEvent_missingTo_exceptionThrown() {
        String missingTo = "event see fren /from 13/02/2026 0800";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingTo);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());
    }

    @Test
    public void insertEvent_missingBoth_exceptionThrown() {
        String missingBoth = "event see fren";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingBoth);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());
    }

    @Test
    public void insertEvent_missingTask_exceptionThrown() {
        String justEvent = "event";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(justEvent);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());
    }

    @Test
    public void insertEvent_WeirdInput_exceptionThrown() {
        String weirdInput = "event /from 13/02/2026 0800";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(weirdInput);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>",
                sad.getMessage());
    }

    @Test
    public void insertTodo_success() {
        String correctTodoString = "todo crying myself to sleep tonight";
        assertEquals(Task.parseTask(correctTodoString), new ToDo("crying myself to sleep tonight"));
    }

    @Test
    public void insertTodo_missingTask_exceptionThrown() {
        String missingTask = "todo";
        SadStudentException sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingTask);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());
    }

   

}
