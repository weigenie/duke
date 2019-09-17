package duke.execution.commands;

import duke.exceptions.DukeException;

import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;
import duke.models.Task;

public abstract class Command {

    protected String txt;

    /**
     * Command to be executed by programme.
     * @param txt Description of command.
     */
    public Command(String txt) {
        this.txt = txt;
    }

    /**
     * Executes the command.
     * @param tasks Associated TaskList.
     * @param ui Associated Ui.
     * @param storage Associated Storage.
     * @return Boolean whether the command has been ran.
     * @throws DukeException If error was met.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        checkValidity();

        return true;
    }

    protected abstract void checkValidity() throws DukeException;

}