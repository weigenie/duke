package execution;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser with static methods to parse strings.
 */
public class Parser {

    /** Standard date format to parse date & time */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Parses an input.
     * @param line Input string.
     * @return Command based on input.
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
     * Checks whether a text is in the correct date format.
     * @param txt Date and time to be parsed.
     * @return Boolean whether the text can be parsed into a date.
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
     * Parses and formats a date in our standard form.
     * @param txt String containing a date to be parsed.
     * @return String containing the date in our standard form.
     */
    public static String getFormattedDate(String txt) {
        try {
            Date date = DATE_FORMAT.parse(txt);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yy").format(date);
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
            return "";
        }
    }
}
