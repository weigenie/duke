package duke.execution;

import duke.exceptions.DukeException;
import duke.execution.commands.*;
import duke.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Parser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Parses a line to return a Command.
     * @param line Line to be parsed.
     * @return Command after parsing.
     */
    public static Command parse(String line) throws DukeException {
        assert line != null;
        String[] split = line.split(" ");
        String rest = getRestOfInput(split);
        return parseForCommand(split[0], rest);
    }

    /**
     * Checks whether a string can be formatted to be a Date.
     * @param txt String to be checked.
     * @return Boolean whether the string can be formatted to be a Date.
     */
    public static boolean isDate(String txt) {
        assert txt != null;
        try {
            DATE_FORMAT.parse(txt);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Get Date in desired format.
     * @param txt String of date to be formatted.
     * @return Formatted Date.
     */
    public static String getFormattedDate(String txt) {
        try {
            Date date = DATE_FORMAT.parse(txt);
            assert date != null;
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String ordinalIndicator;

            int intDay = Integer.parseInt(day);
            if (intDay >= 11 && intDay <= 13) {
                ordinalIndicator = "th";
            } else if (intDay % 10 == 1) {
                ordinalIndicator = "st";
            } else if (intDay % 10 == 2) {
                ordinalIndicator = "nd";
            } else if (intDay % 10 == 3) {
                ordinalIndicator = "rd";
            } else {
                ordinalIndicator = "th";
            }

            String outputDate = intDay + ordinalIndicator + " of " + month + " " + year + ", " + time;
            return outputDate;
        } catch (ParseException e) {
            System.out.println("isDate has bugs");
            System.out.println(e.getMessage());
            return "";
        }
    }

    /**
     * Parse String for a Command to be returned.
     * @param action String specifying the Command type.
     * @param txt String containing the description for the Command to be executed.
     * @return Command after parsing.
     * @throws DukeException If action String not identifiable.
     */
    private static Command parseForCommand(String action, String txt) throws DukeException {
        switch (action) {
        case "bye":
            return new ByeCommand(txt);

        case "list":
            return new ListCommand(txt);

        case "done":
            return new DoneCommand(txt);

        case "todo":
            return new TodoCommand(txt);

        case "deadline":
            return new DeadlineCommand(txt);

        case "event":
            return new EventCommand(txt);

        case "delete":
            return new DeleteCommand(txt);

        case "find":
            return new FindCommand(txt);

        case "help":
            return new HelpCommand(txt);

        default:
            System.out.println("ERROR_LOG: txt: " + txt);
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Seperates the rest of the line from the first word in a splited input string.
     * @param split Splited input string.
     * @return Concatenated String without the first word.
     */
    private static String getRestOfInput(String[] split) {
        String rest = "";
        for (int i = 1; i < split.length; i++) {
            rest += split[i];
            rest += " ";
        }
        return rest;
    }
}
