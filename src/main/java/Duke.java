import exceptions.DukeException;
import execution.*;

/**
 * Main class to handle other classes.
 */
public class Duke {

    /** Filepath to data document */
    static final String TEXT_DOCUMENT = "../../../data.txt";

    /** Storage to read and write files */
    private Storage storage;
    /** Collections of the tasks */
    private TaskList tasks;
    /** Handles input and output */
    private Ui ui;
    /** Action taken depending on input */
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

    /**
     * Initialise all the other working objects for the application.
     */
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

    /**
     * Main method
     * @param args Arguments
     */
    public static void main(String[] args) {
        new Duke(TEXT_DOCUMENT).run();
    }
}