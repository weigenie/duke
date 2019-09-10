package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;
import duke.models.Task;

public class EventCommand extends Command  {
    public EventCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);

        Task eventAdded = tasks.addEvent(txt);
        ui.showAddTask(eventAdded, tasks.getSize());
        storage.saveToDataFile(tasks);
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (txt.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!txt.contains("/at")) {
            throw new DukeException(" ☹ OOPS!!! Event input should include '/at'.");
        }
    }
}
