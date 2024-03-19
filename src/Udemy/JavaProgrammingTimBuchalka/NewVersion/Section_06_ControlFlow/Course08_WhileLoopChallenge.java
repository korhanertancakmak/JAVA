package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-24

                                            The while loop challenge

        Create a method called isEvenNumber, that takes a parameter of type int. Its purpose is to determine if the argument
    passed to the method is an even number or not. Return true from the method, if it's an even number, otherwise return
    false. Next, use a while loop to test a range of numbers, from 5, up to and including 20, but printing out only the
    even numbers, determined by the call to the isEvenNumber method.

        int i = 4;
        while (i <= 20) {
            i++;
            if (!isEvenNumber(i)) {
                continue;
            }
            System.out.println("Number " + i + " is an even number.");
        }
        if (countEvenNumbers == 5) {
            System.out.println("5 even numbers are already found.");
            break;
        }

        After that, modify th while code to make it also record the total number of even numbers it has found. Break out
    of the loop, once 5 even numbers are found. Finally, display the total number of odd and even numbers found.


*/
public class Course08_WhileLoopChallenge {
    public static void main(String[] args) {

        int i = 4, evenCount = 0, oddCount = 0;
        while (i <= 20) {
            i++;
            if (!isEvenNumber(i)) {
                oddCount++;
                continue;
            }
            System.out.println("Even number " + i);
            evenCount++;
        }
        System.out.println("Total odd numbers found = " + oddCount);
        System.out.println("Total even numbers found = " + evenCount);
    }

    public static boolean isEvenNumber(int number) {
        return (number % 2 == 0) ? true : false;
    }
}
