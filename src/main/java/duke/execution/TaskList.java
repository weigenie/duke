package duke.execution;

import duke.exceptions.DukeException;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initialises a new colelctions of tasks.
     */
    public TaskList() {
        System.out.println("initiating new tasklist");
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises a collections of tasks from an ArrayList.
     * @param tasks Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the collections.
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert tasks != null;
        tasks.add(task);
    }

    /**
     * Deletes a task from the collections.
     * @param no Position of task to be deleted.
     */
    public void delete(int no) {
        assert tasks != null;
        tasks.remove(no - 1);
    }

    /**
     * Gets collections of tasks.
     * @return Collections of tasks.
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null;
        return this.tasks;
    }

    /**
     * Gets number of tasks in collections.
     * @return Number of tasks in collections.
     */
    public int getSize() {
        assert tasks != null;
        return this.tasks.size();
    }

    /**
     * Gets a task.
     * @param num Position of task to be retrieved.
     * @return Task associated with num.
     */
    public Task get(int num) {
        assert tasks != null;
        return this.tasks.get(num);
    }

    /**
     * Marks a task as done.
     * @param n Position of task to be marked as done.
     * @return Task that has been marked as done.
     */
    public Task markDone(int n) {
        assert tasks != null;
        tasks.get(n - 1).markAsDone();
        return tasks.get(n - 1);
    }

    /**
     * Adds a Todo Task.
     * @param title Title of Todo.
     * @return Todo that has been added.
     */
    public Task addTodo(String title) {
        assert tasks != null;
        this.tasks.add(new Todo(title));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Adds a Deadline Task.
     * @param title Input for creation of Deadline.
     * @return Deadline that has been added.
     * @throws DukeException If met with error with the input.
     */
    public Task addDeadline(String title) throws DukeException {
        String[] split = title.split("/by ");
        if (split.length == 1) {
            throw new DukeException(" ☹ OOPS!!! Input should include 'by'!");
        }
        if (!Parser.isDate(split[1])) {
            throw new DukeException(" ☹ OOPS!!! The date format inputted is in the wrong format!");
        }
        Deadline deadlineAdded = new Deadline(split[0], Parser.getFormattedDate(split[1]));
        assert tasks != null;
        tasks.add(deadlineAdded);
        return deadlineAdded;
    }

    /**
     * Adds an Event Task.
     * @param title Input for creation of Event.
     * @return Event that has been added.
     * @throws DukeException If met with error with the input.
     */
    public Task addEvent(String title) throws DukeException {
        String[] split = title.split("/at ");
        if (split.length == 1) {
            throw new DukeException(" ☹ OOPS!!! Input should include 'at'!");
        }
        if (!Parser.isDate(split[1])) {
            throw new DukeException(" ☹ OOPS!!! The date format inputted is in the wrong format!");
        }
        Event eventAdded = new Event(split[0], Parser.getFormattedDate(split[1]));
        assert tasks != null;
        tasks.add(eventAdded);
        return eventAdded;
    }

    /**
     * Deletes a task in the collections.
     * @param txt Text containing position of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(String txt) {
        int deleteNum = Integer.parseInt(txt.trim()) - 1;
        assert tasks != null;
        return tasks.remove(deleteNum);
    }

    /**
     * Finds all the tasks containing the text.
     * @param txt Specified text to be found in the tasks.
     * @return TaskList containing the found tasks.
     */
    public TaskList find(String txt) {
        ArrayList<Task> list = new ArrayList<>();
        assert tasks != null;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(txt)) {
                list.add(tasks.get(i));
            }
        }
        return new TaskList(list);
    }

    /**
     * Checks whether the collections is empty.
     * @return Boolean value whether the collections is empty.
     */
    public boolean isEmpty() {
        assert tasks != null;
        return tasks.isEmpty();
    }
}
