package sadstudent.task;

import org.junit.jupiter.api.Test;
import sadstudent.exceptions.SadStudentException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskTest {

    @Test
    public void parseTaskTest(){
        String correctDeadlineInput = "deadline finish assignment /by 2026-02-16";
        assertEquals(Task.parseTask(correctDeadlineInput), new Deadline("finish assignment", LocalDate.parse("2026-02-16")));

        String differentDateFormat = "deadline finish assignment /by 16/02/2026";
        assertEquals(Task.parseTask(differentDateFormat), new Deadline("finish assignment", LocalDate.parse("2026-02-16")));

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
        assertEquals(Task.parseTask(correctEventInput), new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
    
        String differentOrder = "event see fren /to 13/02/2026 0800 /from 12/02/2026 0800";
        assertEquals(Task.parseTask(correctEventInput), new Event("see fren", LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));

        String missingFrom = "event see fren /to 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingFrom);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>", sad.getMessage());

        String missingTo = "event see fren /from 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingTo);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>", sad.getMessage());

        String missingBoth = "event see fren";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(missingBoth);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>", sad.getMessage());

        String justEvent = "event";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(justEvent);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());

        String weirdInput = "event /from 13/02/2026 0800";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(weirdInput);
        }, "SadStudentException was expected");
        assertEquals("ehhh Event requires from and to field!\nUsage: event <task> /from <from> /to <to>", sad.getMessage());

        String correctTodoString = "todo crying myself to sleep tonight";
        assertEquals(Task.parseTask(correctTodoString), new ToDo("crying myself to sleep tonight"));

        String missingTask = "todo";
        sad = assertThrows(SadStudentException.class, () -> {
            Task.parseTask(justEvent);
        }, "SadStudentException was expected");
        assertEquals("you specified tasktype but there was no task T.T. You cheated on me!!!", sad.getMessage());
    }

    
}
