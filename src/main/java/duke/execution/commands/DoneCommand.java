package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

import duke.models.Task;

public class DoneCommand extends Command  {

    public DoneCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        checkValidity();

        Task taskDone = tasks.markDone(Integer.parseInt(txt));
        ui.showDone(taskDone);
        storage.saveToDataFile(tasks);
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (txt.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
        }
    }
}
