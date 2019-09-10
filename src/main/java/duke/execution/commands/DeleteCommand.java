package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;
import duke.models.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);

        Task deletedTask = tasks.deleteTask(txt);
        ui.showDeleted(deletedTask, tasks.getSize());
        storage.saveToDataFile(tasks);
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (txt.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
        }
    }
}
