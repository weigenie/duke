package duke.execution;

import duke.exceptions.DukeException;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

import java.io.*;

import java.util.ArrayList;

public class Storage {

    String filepath;

    /**
     * Storage to access data file.
     * @param filepath File path to data file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from file.
     * @return Loaded list of tasks.
     * @throws DukeException If file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filepath);
        assert file != null;
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (IOException e) {
            System.out.println(file);
            if (file.createNewFile()) {
                System.out.println("data file is created");
            } else {
                System.out.println("data file is not created");
            }
            return null;
        }
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
        bufferedReader.close();
        return tasks;
    }

    /**
     * Saves tasks into specified file.
     * @param taskList Tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            FileWriter fileWriter = new FileWriter(filepath);
            assert fileWriter != null;
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
