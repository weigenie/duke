package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

public class ByeCommand extends Command  {
    public ByeCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        checkValidity();

        ui.exit();
        return false;
    }

    @Override
    protected void checkValidity() throws DukeException {
        return;
    }
}
