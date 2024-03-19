package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-18

        In the previous courses, we looked at the switch statement, and how we can use it in a similar way, to an "if"
    statement. We talked about the traditional version, which you'll see in older applications. We've also looked at the
    enhanced version, which i would recommend if you're using JDK 14 or greater, and if you also have the luxury of not
    having to be compatible with older versions.

        In this course, we'll give you a chance to work out a challange, using the traditional switch statement. Let's
    look at the syntax of both again:

                                    switch (switchValue) {
                                        case 1:
                                            System.out.println("Value was 1");
                                            break;
                                        case 2:
                                            System.out.println("Value was 2");
                                            break;
                                        case 3: case 4: case 5:
                                            System.out.println("Was a 3, a 4, or a 5");
                                            System.out.println("Actually it was a " + switchValue);
                                            break;
                                        default:
                                            System.out.println("Was not 1, 2, 3, 4 or 5");
                                            break;
                                    }

    and the enhanced one here;

                                    switch (switchValue) {
                                        case 1 -> System.out.println("Value was 1");
                                        case 2 -> System.out.println("Value was 2");
                                        case 3, 4, 5 -> {
                                            System.out.println("Was a 3, a 4, or a 5");
                                            System.out.println("Actually it was a " + switchValue);
                                        }
                                        default -> System.out.println("Was not 1, 2, 3, 4 or 5");
                                    }

        The previous version uses colons (:) to start a case block, case 1 : for example, which makes it a traditional
    switch. This means it permits fall through to occur, and it can't be used as an expression. Therefore, in this type
    of switch, it's important to ensure you include a "break" statement in each case block, to prevent accidental fall
    through.

        On the new version, where the arrow (->) token replaces the colon (:) in the case label, the break statement is
    part of the new syntax, and you shouldn't use it.

                                            Traditional Switch Challenge

        In this challenge, we'll be using the NATO alphabet to replace a character or letter, with NATO's standardized
    word for that letter.

        A = Able, B = Baker, C = Charlie, D = Dog, E = Easy, F = Fox, G = George, H = How, I = Item, J = Jig,
        K = King, L = Love, M = Mike, N = Nan, O = Oboe, P = Peter, Q = Queen, R = Roger, S = Sugar, T = Tare,
        U = Uncle, V = Victor, W = William, X = X-ray, Y = Yoke, Z = Zebra.

        In radio transmissions, the word car, "C", "A", "R", would be read, "Charlie Able Roger", for clarity. We'll take
    a single character, and return the matching word from the NATO phonetic alphabet, shown above. We'll just do this for
    the letter A, through E.

        To do this:

    1. Create a new char variable.
    2. Use the traditional switch statement (with a colon in case labels) that tests the value in the variable from Step-1.
       * Create cases for the characters, A, B, C, D, and E.
       * Display a message in each case block, with the letter and the NATO word, then break.
       * Add a default block, which displays the letter with a message saying not found.

 */
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
