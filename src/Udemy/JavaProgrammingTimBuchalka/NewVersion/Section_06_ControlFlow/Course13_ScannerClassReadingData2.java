package CourseCodes.NewSections.Section_06_ControlFlow;

import java.util.Scanner;

/*
Course-29

                                        Reading Input with Scanner

        We're going to continue with our class, and testing the Scanner method to read input, but we'll first write a
    validation method. This method should make sure that the date of birth the user enters, should not be any later than
    the current year, which we have set to 2023.

        We should also make sure that the year that's entered, is greater than the current year minus 125 years, because
    we'll assume the oldest living human, won't be older than 125 years old. This validation naturally addresses negative
    years as well. So let's create a new method called checkDate. We'll pass it the current year we've set up, in the main
    method, and the date of birth the user entered.In this method, we'll parse that information with Integer.parseInt.

                            do {
                                System.out.println("Enter a year of birth >= " +
                                        (currentYear - 125) + " and <= " + currentYear);
                                age = CheckData(currentYear, cs.nextLine());
                                validDOB = age < 0 ? false : true;
                            } while (!validDOB);

        After we created the method called CheckData, and implemented it to our new getInputFromScanner method, what happens
    if the user enters in a non-numeric value. You can try "196c", and you can see, that our application sort of crashes
    with an error, another exception called "NumberFormatException". We have no checks in place for this kind of bad data.
    So let's add that, but this time, let's try another example of the try statement.

                            do {
                                System.out.println("Enter a year of birth >= " +
                                        (currentYear - 125) + " and <= " + currentYear);
                                try {
                                    age = CheckData(currentYear, cs.nextLine());
                                    validDOB = age < 0 ? false : true;
                                } catch (NumberFormatException badUserData) {
                                    System.out.println("Characters not allowed!!! Try again.");
                                }
                            } while (!validDOB);

        So here, we're catching the exception, which we just saw, was NumberFormatException. Now the reason you create a
    variable, in the parentheses, of the catch phrase, is if you wanted to access information about the exception, you could
    use that variable. We'll explore that more later, when we cover exceptions much more thoroughly, but it's required to
    set up a variable this way, even if we aren't going to use it. In this case, all we're going to do is print an extra
    statement that they entered characters.


*/
public class Course13_ScannerClassReadingData2 {
    public static void main(String[] args) {

        int currentYear = 2023;

        System.out.println(getInputFromScanner(currentYear));
    }

    public static String getInputFromScanner(int currentYear) {

        Scanner cs = new Scanner(System.in);

        System.out.println("Hi, What's your name? ");
        String name = cs.nextLine();

        System.out.println("Hi " + name + ", Thanks for taking the course!");

        System.out.println("What year were you born? ");

        boolean validDOB = false;
        int age = 0;
        do {
            System.out.println("Enter a year of birth >= " +
                    (currentYear - 125) + " and <= " + currentYear);
            try {
                age = CheckData(currentYear, cs.nextLine());
                validDOB = age < 0 ? false : true;
            } catch (NumberFormatException badUserData) {
                System.out.println("Characters not allowed!!! Try again.");
            }
        } while (!validDOB);


        return "So you are " + age + " years old";
    }

    public static int CheckData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear - 125;

        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }

        return (currentYear - dob);
    }
}
