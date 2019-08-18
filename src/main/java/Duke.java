import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String tab = "\t____________________________________________________________";

        System.out.println(tab + "\t\n\tHello! I'm Duke\n\tWhat can I do for you?\n" + tab);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(tab);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(tab);
                break;
            } else {
                System.out.println(tab);
                System.out.println("\t" + input);
                System.out.println(tab);
            }
        }
    }
}
