package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_02_ControlFlow;

public class Course02_TraditionalSwitchChallenge {
    public static void main(String[] args) {

        char charValue = 'F';
        switch (charValue) {
            case 'A':
                System.out.println("Able for A");
                break;
            case 'B':
                System.out.println("Baker for B");
                break;
            case 'C':
                System.out.println("Charlie for C");
                break;
            case 'D':
                System.out.println("Dog for D");
                break;
            case 'E':
                System.out.println("Easy for E");
                break;
            default:
                System.out.println("Letter not found");
                break;
        }
    }
}
