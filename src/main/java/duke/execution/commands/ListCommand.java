package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

public class ListCommand extends Command  {
    public ListCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        checkValidity();

        ui.listTasks(tasks);
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        return;
    }
}
