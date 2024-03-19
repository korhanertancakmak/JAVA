package CourseCodes.OldSections.Section_03_ControlFlow.Course07_ParsingValuesfromaString;

public class Main {

    public static void main(String[] args) {

        String integerAsString = "2018";
        System.out.println("numberAsString= " + integerAsString);

        int integer = Integer.parseInt(integerAsString);
        System.out.println("number = " + integer);

	    String doubleAsString = "2018.125";
        System.out.println("numberAsString= " + doubleAsString);

        double number = Double.parseDouble(doubleAsString);
        System.out.println("number = " + number);
    }
}
