import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);
    static List<Task> lst = new ArrayList<>();
    static String tab = "\t____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

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
                        break;
                    case "list":
                        System.out.println(tab);
                        System.out.println(" Here are the tasks in your list");
                        for (int i = 0; i < lst.size(); i++) {
                            System.out.println("\t " + (i + 1) + ". " + lst.get(i));
                        }
                        System.out.println(tab + "\n");
                        break;
                    case "done":
                        int doneNum = Integer.parseInt(sc.next()) - 1;
                        lst.get(doneNum).markAsDone();
                        print(" Nice! I've marked this task as done:\n\t   " + lst.get(doneNum));
                        break;
                    case "todo":
                        String desc = sc.nextLine();
                        if (desc.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        lst.add(new Todo(desc));
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
                        split = rest.split("/by ");
                        lst.add(new Deadline(split[0], split[1]));
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
                        split = rest.split("/at ");
                        lst.add(new Event(split[0], split[1]));
                        print(" Got it. I've added this task:\n\t   " + lst.get(lst.size() - 1) + "\n\tNow you have " +
                                lst.size() + " tasks in the list.");
                        break;
                    case "delete":
                        int deleteNum = Integer.parseInt(sc.next()) - 1;
                        Task toDelete = lst.get(deleteNum);
                        print(" Noted. I've removed this task: \n\t   " + toDelete + "\n\t Now you have " +
                                (lst.size() - 1) + " tasks in the list.");
                        lst.remove(deleteNum);
                        break;
                    default:
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
}
