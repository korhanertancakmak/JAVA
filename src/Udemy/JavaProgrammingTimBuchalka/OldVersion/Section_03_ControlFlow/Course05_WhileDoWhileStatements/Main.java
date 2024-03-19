package CourseCodes.OldSections.Section_03_ControlFlow.Course05_WhileDoWhileStatements;

public class Main {

    public static void main(String[] args) {
/*
//  While loop statement
	    int count = 6;
        while (count != 6) {
            System.out.println("Count value is " + count);
            count++;
        }

//  For loop statement
        for (int i = 6; i != 6; i++) {
            System.out.println("Count value is " + count);
        }

//  Do-While loop statement
        count = 6;
        do {
            System.out.println("Count value was " + count);
            count++;

            if (count > 100) {
                break;
            }

        } while(count != 6);

//  Conditional(if) While loop statement
        int number = 5;
        int finishNumber = 20;
        while (number <= finishNumber) {
            if(!isEvenNumber(number)) {
                number++;
                continue;
            }
            System.out.println("Even number " + number);
            number++;
        }
*/

//      Modify the while code above. Make it also record the total number of even numbers it has found and break once 5
//  are found and at the end, display the total number of even numbers found

        int number = 5;
        int finishNumber = 20;
        int evenNumbersFound = 0;
        while(number <= finishNumber) {
            if(!isEvenNumber(number)) {
                number++;
                continue;
            }

            System.out.println("Even number " + number);
            number++;

            evenNumbersFound++;
            if(evenNumbersFound >= 5) {
                break;
            }
        }

        System.out.println("Total even numbers found = " + evenNumbersFound);
    }
/*
        Create a method called isEvenNumber that takes a parameter of type int. Its purpose is to determine if the argument
    passed to the method is an even number or not. Return true if an even number, otherwise return false.
*/
    public static boolean isEvenNumber(int number) {
        if((number % 2)  == 0) {
            return true;
        } else {
            return false;
        }
    }

}
