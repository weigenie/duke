package duke.execution.commands;

import duke.exceptions.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

public class ExceptionCommand extends Command {
    public ExceptionCommand(String txt) {
        super(txt);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    protected void checkValidity() throws DukeException {
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
