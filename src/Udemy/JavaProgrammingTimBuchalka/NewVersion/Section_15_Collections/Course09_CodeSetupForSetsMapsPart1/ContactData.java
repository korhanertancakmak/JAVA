package CourseCodes.NewSections.Section_15_Collections.Course09_CodeSetupForSetsMapsPart1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Part-7
/*
        That would simulate a data reader of some kind. In our case, this is just going to read data from static strings,
    but you could imagine we could read this data from ta file, a database, or get it from some kind of service provider.
    Again, in the same package, I'll create a new class named ContactData. I'll create 2 static text fields, using text
    blocks, one for phone data, and one for email data. Initially, both fields will be empty. I'm just going to paste
    this data in.
*/
//End-Part-7

public class ContactData {

    private static final String phoneData = """
            Charlie Brown, 3334445555
            Maid Marion, 1234567890
            Mickey Mouse, 9998887777
            Mickey Mouse, 1247489758
            Minnie Mouse, 4567805666
            Robin Hood, 5647893000
            Robin Hood, 7899028222
            Lucy Van Pelt, 5642086852
            Mickey Mouse, 9998887777
            """;

    private static final String emailData = """
            Mickey Mouse, mckmouse@gmail.com
            Mickey Mouse, micky1@aws.com
            Minnie Mouse, minnie@verizon.net
            Robin Hood, rhood@gmail.com
            Linus Van Pelt, lvpelt2015@gmail.com
            Daffy Duck, daffy@google.com
            """;

//Part-8
/*
        Ok, so next, I want a method to read this data, emulating reading it from a file, or retrieving it from an external
    source. This is going to be a public static method called getData, it'll take a String, which will identify if it's
    retrieving phone or email data, and it'll return a List of contacts. I'll set up a local variable, data list, and
    instantiate a new array list. I'll return this local variable from the method.

                                        List<Contact> dataList = new ArrayList<>();
                                        return dataList;

    Now, I'll set up my scanner variable. If the type is phone, I'll pass in the phoneData text block to the constructor.
    If the type is email, I'll pass in the emailData text block. You've seen Scanner before with System.in, but it can
    also be used with any string, including a multi-string text block, which is what I'm doing here. There are other ways
    to split this data by new lines and so forth, and I'll show you these examples in future code. I wanted to write the
    code this way, as sort of mock file read if you will. Now I can use the Scanner functionality to treat the string
    like any input stream. I'll use a while loop to continue looping while hasNext on the scanner is true. I'll call the
    nextLine to get the next string and split that on a comma, passing it to a local variable, data, an array of strings.
    I'll pass the array to the asList method on Arrays, so that I can use the replaceAll method, passing it the method
    reference for the trim method on String.

                                        String[] data = scanner.nextLine().split(",");
                                        Arrays.asList(data).replaceAll(String::trim);

    This is going to trim leading and trailing whitespace, from each of the strings in my text block. I could have also
    used strip Indent there, but I've formatted my text block itself without indents, so I won't worry about it, for simplicity.
    Next, I want to create specific types of contacts, based on the type passed. If type is phone, I'll add a new Contact,
    using the constructor that takes name, which is the first comma delimited element, in both of my text block elements.
    I'll also pass it the phone number, which I first need to parse, using parseLong on the long wrapper. The phone number
    is the second comma delimited element, in my text block. If the type is email, I'll just pass the second comma delimited
    element as is, which is the email as a string. And that's it. That's the setup. I'll add a public static print method
    on the Main class.
*/
//End-Part-8

    public static List<Contact> getData(String type) {

        List<Contact> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(type.equals("phone") ? phoneData : emailData);
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split(",");
            Arrays.asList(data).replaceAll(String::trim);
            if (type.equals("phone")) {
                dataList.add(new Contact(data[0], Long.parseLong(data[1])));
            } else if (type.equals("email")) {
                dataList.add(new Contact(data[0], data[1]));
            }
        }
        return dataList;
    }
}
