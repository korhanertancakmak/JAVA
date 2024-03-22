package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course20_StringClassMethods2;


public class Main {
    public static void main(String[] args) {

        String birthDate = "02/09/1990";
        int startingIndex = birthDate.indexOf("1990");
        System.out.println("startingIndex = " + startingIndex);
        System.out.println("Birth year = " + birthDate.substring(startingIndex));

        System.out.println("Month = " + birthDate.substring(3,5));

        String newDate = String.join("/", "02", "09", "1990");
        System.out.println("newDate = " + newDate);

        newDate = "02";
        newDate = newDate.concat("/");
        newDate = newDate.concat("09");
        newDate = newDate.concat("/");
        newDate = newDate.concat("1990");
        System.out.println("newDate = " + newDate);

        newDate = "02" + "/"  + "09" + "/" + "1990";
        System.out.println("newDate = " + newDate);

        newDate = "02".concat("/").concat("09").concat("/")
                .concat("1990");
        System.out.println("newDate = " + newDate);

        System.out.println(newDate.replace('/', '-'));
        System.out.println(newDate.replace("2", "00"));

        System.out.println(newDate.replaceFirst("/", "-"));
        System.out.println(newDate.replaceAll("/", "---"));

        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(20));

        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(20));

        System.out.println("    ABC\n".repeat(3).indent(-2));
        System.out.println("-".repeat(20));
    }
}
