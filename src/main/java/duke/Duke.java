package duke;

import duke.exceptions.DukeException;

import duke.execution.Command;
import duke.execution.Parser;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

public class Duke {

    static final String TEXT_DOCUMENT = "../../../data.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Command command;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        boolean shouldLoop = true;
        while (shouldLoop) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                shouldLoop = c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(TEXT_DOCUMENT).run();
    }
}