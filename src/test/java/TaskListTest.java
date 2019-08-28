import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.execution.TaskList;

public class TaskListTest {

    @Test
    public void addDeadline_wrongDateFormat_exceptionThrown() {
        try {
            new TaskList().addDeadline("Submission /by 2 Jan 2019");
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! The date format inputted is in the wrong format!", e.getMessage());
        }

        try {
            new TaskList().addDeadline("2 Jan 2019");
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! Input should include 'by'!", e.getMessage());
        }
    }
}
