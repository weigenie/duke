package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

public class HelpCommand extends Command {

    public HelpCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);

        if (txt.isEmpty()) {
            ui.showHelp();
        } else {
            ui.showHelp(txt);
        }
        return true;
    }

    @Override
    protected void checkValidity() throws DukeException {
        return;
    }
}
