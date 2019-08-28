package execution;

import exceptions.DukeException;
import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int no) {
        tasks.remove(no - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public Task markDone(int n) {
        tasks.get(n - 1).markAsDone();
        return tasks.get(n - 1);
    }

    public Task addTodo(String title) {
        this.tasks.add(new Todo(title));
        return tasks.get(tasks.size() - 1);
    }

    public Task addDeadline(String title) throws DukeException {
        String[] split = title.split("/by ");
        if (!Parser.isDate(split[1])) {
            throw new DukeException(" ☹ OOPS!!! The date format inputted is in the wrong format!");
        }
        Deadline deadlineAdded = new Deadline(split[0], Parser.getFormattedDate(split[1]));
        tasks.add(deadlineAdded);
        return deadlineAdded;
    }

    public Task addEvent(String title) throws DukeException{
        String[] split = title.split("/at ");
        if (!Parser.isDate(split[1])) {
            throw new DukeException(" ☹ OOPS!!! The date format inputted is in the wrong format!");
        }
        Event eventAdded = new Event(split[0], Parser.getFormattedDate(split[1]));
        tasks.add(eventAdded);
        return eventAdded;
    }

    public Task deleteTask(String txt) {
        int deleteNum = Integer.parseInt(txt.trim()) - 1;
        return tasks.remove(deleteNum);
    }
}
