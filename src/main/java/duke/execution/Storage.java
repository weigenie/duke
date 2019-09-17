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
        FileReader fileReader;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR_LOG: file not found: " + filepath);
            File file = new File(filepath);
            createNewFile(file);
            fileReader = new FileReader(file);
        }

        readFromData(new BufferedReader(fileReader), tasks);
        return tasks;
    }

    /**
     * Saves tasks into specified file.
     * @param taskList Tasks to be saved.
     */
    public void saveToDataFile(TaskList taskList) {
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
    
    private ArrayList<Task> createNewFile(File file) throws IOException {
        if (file.createNewFile()) {
            System.out.println("data file is created");
        } else {
            System.out.println("data file is not created");
        }
        return null;
    }

    private void readFromData(BufferedReader bufferedReader, ArrayList<Task> tasks) throws IOException, DukeException {
        assert  bufferedReader != null;
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] split = line.split("\\|");
            addToTaskList(tasks, split);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private void addToTaskList(ArrayList<Task> tasks, String[] split) throws DukeException {
        switch (split[0]) {
        case "T":
            Todo newTodo = new Todo(split[2]);
            if (Boolean.parseBoolean(split[1])) {
                newTodo.markAsDone();
            }
            tasks.add(newTodo);
            break;
        case "D":
            Deadline newDeadline = new Deadline(split[2], split[3]);
            if (Boolean.parseBoolean(split[1])) {
                newDeadline.markAsDone();
            }
            tasks.add(newDeadline);
            break;
        case "E":
            Event newEvent = new Event(split[2], split[3]);
            if (Boolean.parseBoolean(split[1])) {
                newEvent.markAsDone();
            }
            tasks.add(newEvent);
            break;
        default:
            throw new DukeException("error data formatting in data.txt");
        }
    }
}
