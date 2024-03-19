package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-19

        So, in the last challenge, we used a traditional switch statement, to translate a letter into NATO's keyword, that
    represented that letter. In this next challenge, we're going to use the enhanced switch expression. Let's look at these
    statements again, but this time, we're going to make the enhanced switch an expression, by assigning it to a variable.

                                    switch (month) {
                                        case "JANUARY":
                                        case "FEBRUARY":
                                        case "MARCH":
                                            return "1st";
                                        case "APRIL":
                                        case "MAY":
                                        case "JUNE":
                                            return "2nd";
                                        case "JULY":
                                        case "AUGUST":
                                        case "SEPTEMBER":
                                            return "3rd";
                                        case "OCTOBER":
                                        case "NOVEMBER":
                                        case "DECEMBER":
                                            return "4th";
                                    }
                                    return "bad";

        The version on the above, uses colons (:) to start a case block, which makes it a traditional switch. This means
    it permits fall through to occur, and cannot be used as an expression. But we can use it in a method, with return
    statements as we show above on the traditional version.

                                    return switch (month) {
                                        case "JANUARY", "FEBRUARY", "MARCH" -> { yield "1st"; }
                                        case "APRIL", "MAY", "JUNE" -> "2nd";
                                        case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
                                        case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
                                        default -> {
                                            String badResponse = month + " is bad";
                                            yield badResponse;
                                        }
                                    };

        On the enhanced one, where the arrow (->) token replaces the colon (:) in the case label, you not only don't need
    the return statement, you can just put the value being returned there. If you do use a code block as we show in the
    first case label, "{ yield "1st"; }" , the keyword yield is required as shown.

                                            Day of the Week Challenge

    1. Create a method called printDayOfWeek, that takes an int parameter called day, but doesn't return any values.

        * Use the enhanced switch statement, to return the name of the day, based on the parameter passed to the switch
        statement, so that 0 will return "Sunday", 1 will return "Monday", and so on. Any number not between 0 and 6,
        should return "Invalid Day".
        * Use the enhanced switch statement as an expression, returning the result to a String named dayOfTheWeek.
        * Print both the day variable and the dayOfTheWeek variable.

    2.  In the main method, call this method for the values 0 through 7.

    3. Bonus : Create a second method called printWeekDay, that uses an if-then-else statement, instead of a switch, to
    produce the same output.

*/
public class Course03_DayOfWeekChallenge {
    public static void main(String[] args) {
        printDayOfWeek(0);
        printDayOfWeek(1);
        printDayOfWeek(2);
        printDayOfWeek(3);
        printDayOfWeek(4);
        printDayOfWeek(5);
        printDayOfWeek(6);
        printDayOfWeek(7);

        printWeekDay(0);
        printWeekDay(1);
        printWeekDay(2);
        printWeekDay(3);
        printWeekDay(4);
        printWeekDay(5);
        printWeekDay(6);
        printWeekDay(7);
    }

    public static void printDayOfWeek(int day) {
        String dayOfTheWeek = switch (day) {
            case 0 -> { yield "Sunday"; }
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Invalid day";
        };

        System.out.println(day + " stands for " + dayOfTheWeek);
    }

    public static void printWeekDay(int day) {
        String dayOfTheWeek = "Invalid day";
        if (day == 0) {
            dayOfTheWeek = "Sunday";
        } else if (day == 1) {
            dayOfTheWeek = "Monday";
        } else if (day == 2) {
            dayOfTheWeek = "Tuesday";
        } else if (day == 3) {
            dayOfTheWeek = "Wednesday";
        } else if (day == 4) {
            dayOfTheWeek = "Thursday";
        } else if (day == 5) {
            dayOfTheWeek = "Friday";
        } else if (day == 6) {
            dayOfTheWeek = "Saturday";
        }

        System.out.println(day + " stands for " + dayOfTheWeek);
    }
}
