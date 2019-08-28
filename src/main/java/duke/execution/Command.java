package duke.execution;

import duke.exceptions.DukeException;

import duke.models.Task;

public class Command {

    private String action;
    private String txt;

    public Command(String action, String txt) {
        this.action = action;
        this.txt = txt;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (action) {
        case "bye":
            ui.exit();
            return false;

        case "list":
            ui.listTasks(tasks);
            break;

        case "done":
            if (txt.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
            }
            Task taskDone = tasks.markDone(Integer.parseInt(txt));
            ui.showDone(taskDone);
            storage.save(tasks);
            break;

        case "todo":
            if (txt.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todoAdded = tasks.addTodo(txt);
            ui.showAddTask(todoAdded, tasks.getSize());
            storage.save(tasks);
            break;

        case "deadline":
            if (txt.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (!txt.contains("/by")) {
                throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
            }
            Task deadlineAdded = tasks.addDeadline(txt);
            ui.showAddTask(deadlineAdded, tasks.getSize());
            storage.save(tasks);
            break;

        case "event":
            if (txt.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
            } else if (!txt.contains("/at")) {
                throw new DukeException(" ☹ OOPS!!! Event input should include '/at'.");
            }
            Task eventAdded = tasks.addEvent(txt);
            ui.showAddTask(eventAdded, tasks.getSize());
            storage.save(tasks);
            break;

        case "delete":
            if (txt.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
            }
            Task deletedTask = tasks.deleteTask(txt);
            ui.showDeleted(deletedTask, tasks.getSize());
            storage.save(tasks);
            break;

        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

}
