package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;
import duke.models.Task;

public class DeadlineCommand extends Command  {
    public DeadlineCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);

        Task deadlineAdded = tasks.addDeadline(txt);
        ui.showAddTask(deadlineAdded, tasks.getSize());
        storage.saveToDataFile(tasks);
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (txt.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!txt.contains("/by")) {
            throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
        }
    }
}
