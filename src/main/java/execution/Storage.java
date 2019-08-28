package execution;

import exceptions.DukeException;
import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage to handle reading and writing files.
 */
public class Storage {

    /** String containing the file path to the desired file */
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads a file and loads it into an ArrayList.
     * @return Loaded ArrayList.
     * @throws DukeException If cannot read file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splited = line.split("\\|");
                switch (splited[0]) {
                    case "T":
                        Todo newTodo = new Todo(splited[2]);
                        if (Boolean.parseBoolean(splited[1])) {
                            newTodo.markAsDone();
                        }
                        tasks.add(newTodo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(splited[2], splited[3]);
                        if (Boolean.parseBoolean(splited[1])) {
                            newDeadline.markAsDone();
                        }
                        tasks.add(newDeadline);
                        break;
                    case "E":
                        Event newEvent = new Event(splited[2], splited[3]);
                        if (Boolean.parseBoolean(splited[1])) {
                            newEvent.markAsDone();
                        }
                        tasks.add(newEvent);
                        break;
                    default:
                        throw new DukeException("error data formatting in data.txt");
                }
            }
            File file = new File(filepath);
            bufferedReader.close();
            return tasks;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Writes the tasks into file.
     * @param taskList ArrayList containing tasks to be written.
     */
    public void save(TaskList taskList) {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task: tasks) {
                String taskData = task.getData();
                bufferedWriter.write(taskData);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
