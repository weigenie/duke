package duke;

import duke.exceptions.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.execution.commands.Command;
import duke.execution.Parser;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

import java.io.IOException;

/**
 * Main class to handle other classes.
 */
public class Duke extends Application {

    static final String TEXT_DOCUMENT = "./data.txt";

    /** Storage to read and write files. */
    private Storage storage;
    /** Collections of the tasks. */
    private TaskList tasks;
    /** Handles input and output. */
    private Ui ui;
    /** Action taken depending on input. */
    private Command command;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Aladdin.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Genie.jpg"));

    /**
     * Constructor for JavaFX implementation
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(TEXT_DOCUMENT);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Initialising of programme.
     * @param filePath Filepath of data stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (DukeException e) {
            ui.showError(e);
            return ui.getResponse();
        }
    }
}