import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    static String TEXT_DOCUMENT = "D:/weikendotcom/Documents/Uni/Computing/Y2S1/CS2103T/projects/project duke/data.txt";
    static List<Task> lst = new ArrayList<>();
    static String tab = "\t____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        try {
            FileReader fileReader = new FileReader(TEXT_DOCUMENT);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("current line is: " + line);
                    String[] splited = line.split("\\|");
                    System.out.println("split: ");
                    for (String s: splited) {
                        System.out.println(s);
                    }
                    switch (splited[0]) {
                        case "T":
                            Todo newTodo = new Todo(splited[2]);
                            if (Boolean.parseBoolean(splited[1])) {
                                newTodo.markAsDone();
                            }
                            lst.add(newTodo);
                            break;
                        case "D":
                            Deadline newDeadline = new Deadline(splited[2], splited[3]);
                            if (Boolean.parseBoolean(splited[1])) {
                                newDeadline.markAsDone();
                            }
                            lst.add(newDeadline);
                            break;
                        case "E":
                            Event newEvent = new Event(splited[2], splited[3]);
                            if (Boolean.parseBoolean(splited[1])) {
                                newEvent.markAsDone();
                            }
                            lst.add(newEvent);
                            break;
                        default:
                            throw new DukeException("error data formatting in data.txt");
                    }
                }
                File file = new File(TEXT_DOCUMENT);
                bufferedReader.close();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        print("Hello! I'm Duke\n\tWhat can I do for you?");
        boolean quit = false;
        while (!quit) {
            String input = sc.next();
            String rest;
            String[] split;
            try {
                switch (input) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    quit = true;
                    writeData();
                    break;
                case "list":
                    System.out.println(tab);
                    System.out.println("\t Here are the tasks in your list");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println("\t " + (i + 1) + ". " + lst.get(i));
                    }
                    System.out.println(tab + "\n");
                    break;
                case "done":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
                    }
                    int doneNum = Integer.parseInt(rest) - 1;
                    lst.get(doneNum).markAsDone();
                    print(" Nice! I've marked this task as done:\n\t   " + lst.get(doneNum));
                    break;
                case "todo":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTodo(rest);
                    print(" Got it. I've added this task:\n\t   " + lst.get(lst.size() - 1) + "\n\tNow you have " +
                            lst.size() + " tasks in the list.");
                    break;
                case "deadline":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (!rest.contains("/by")) {
                        throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
                    }
                    addDeadline(rest);
                    print(" Got it. I've added this task:\n\t   " + lst.get(lst.size() - 1) + "\n\tNow you have " +
                            lst.size() + " tasks in the list.");
                    break;
                case "event":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (!rest.contains("/at")) {
                        throw new DukeException(" ☹ OOPS!!! Deadline input should include '/at'.");
                    }
                    addEvent(rest);
                    print(" Got it. I've added this task:\n\t   " + lst.get(lst.size() - 1) + "\n\tNow you have " +
                            lst.size() + " tasks in the list.");
                    break;
                case "delete":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
                    }
                    int deleteNum = Integer.parseInt(sc.next()) - 1;
                    Task toDelete = lst.get(deleteNum);
                    print(" Noted. I've removed this task: \n\t   " + toDelete + "\n\t Now you have " +
                            (lst.size() - 1) + " tasks in the list.");
                    lst.remove(deleteNum);
                    break;
                default:
                    sc.nextLine();
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                print (e.getMessage());
            }
        }
    }

    static void print(String txt) {
        System.out.println(tab);
        System.out.println("\t" + txt);
        System.out.println(tab + "\n");
    }

    static void addTodo(String title) {
        lst.add(new Todo(title));
    }

    static void addDeadline(String line) {
        String[] split = line.split("/by ");
        lst.add(new Deadline(split[0], split[1]));
    }

    static void addEvent(String line) {
        String[] split = line.split("/at ");
        lst.add(new Event(split[0], split[1]));
    }

    static void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(TEXT_DOCUMENT);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task: lst) {
                String taskData = task.getData();
                System.out.println("writing: " + taskData);
                bufferedWriter.write(taskData);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
