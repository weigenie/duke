package duke.execution;

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
    public static Command parse(String line) {
        String[] split = line.split(" ");
        String rest = "";
        for (int i = 1; i < split.length; i++) {
            rest += split[i];
            rest += " ";
        }
        String actionWord = split[0];
        return new Command(actionWord, rest);
    }

    /**
     * Checks whether a string can be formatted to be a Date.
     * @param txt String to be checked.
     * @return Boolean whether the string can be formatted to be a Date.
     */
    public static boolean isDate(String txt) {
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
}
