import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Duke {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final String TAB = "\t____________________________________________________________";

    private static Scanner sc = new Scanner(System.in);

    private static List<Task> tasks = new ArrayList<>();
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
                        System.out.println(TAB);
                        System.out.println("\t Here are the tasks in your list");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("\t " + (i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println(TAB + "\n");
                        break;
                    case "done":
                        rest = sc.nextLine();
                        if (rest.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
                        }
                        int doneNum = Integer.parseInt(rest) - 1;
                        tasks.get(doneNum).markAsDone();
                        print(" Nice! I've marked this task as done:\n\t   " + tasks.get(doneNum));
                        break;
                    case "todo":
                        rest = sc.nextLine();
                        if (rest.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addTodo(rest);
                        print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                                tasks.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        rest = sc.nextLine();
                        if (rest.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else if (!rest.contains("/by")) {
                            throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
                        }
                        addDeadline(rest);
                        print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                                tasks.size() + " tasks in the list.");
                        break;
                    case "event":
                        rest = sc.nextLine();
                        if (rest.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
                        } else if (!rest.contains("/at")) {
                            throw new DukeException(" ☹ OOPS!!! Deadline input should include '/at'.");
                        }
                        addEvent(rest);
                        print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                                tasks.size() + " tasks in the list.");
                        break;
                    case "delete":
                        rest = sc.nextLine();
                        if (rest.isEmpty()) {
                            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
                        }
                        int deleteNum = Integer.parseInt(sc.next()) - 1;
                        Task toDelete = tasks.get(deleteNum);
                        print(" Noted. I've removed this task: \n\t   " + toDelete + "\n\t Now you have " +
                                (tasks.size() - 1) + " tasks in the list.");
                        tasks.remove(deleteNum);
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
        System.out.println(TAB);
        System.out.println("\t" + txt);
        System.out.println(TAB + "\n");
    }


    static void addTodo(String title) {
        tasks.add(new Todo(title));
    }

    static void addDeadline(String line) {
        String[] split = line.split("/by ");
        String desc;
        if (isDate(split[1])) {
            desc = getFormattedDate(split[1]);
        } else {
            desc = split[1];
        }
        tasks.add(new Deadline(split[0], desc));
    }

    static void addEvent(String line) {
        String[] split = line.split("/at ");
        String desc;
        if (isDate(split[1])) {
            desc = getFormattedDate(split[1]);
        } else {
            desc = split[1];
        }
        tasks.add(new Event(split[0], desc));
    }

    static boolean isDate(String text) {
        try {
            DATE_FORMAT.parse(text);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    static String getFormattedDate(String text) {
        try {
            Date date = DATE_FORMAT.parse(text);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String ordinalIndicator;

            int int_day = Integer.parseInt(day);
            if (int_day >= 11 && int_day <= 13) {
                ordinalIndicator = "th";
            } else if (int_day % 10 == 1) {
                ordinalIndicator = "st";
            } else if (int_day % 10 == 2) {
                ordinalIndicator = "nd";
            } else if (int_day % 10 == 3) {
                ordinalIndicator = "rd";
            } else {
                ordinalIndicator = "th";
            }

            String outputDate = int_day + ordinalIndicator + " of " + month + " " + year + ", " + time;
            return outputDate;
        } catch (ParseException e) {
            System.out.println("isDate has bugs");
            System.out.println(e.getMessage());
        }
        return "";
    }
}
