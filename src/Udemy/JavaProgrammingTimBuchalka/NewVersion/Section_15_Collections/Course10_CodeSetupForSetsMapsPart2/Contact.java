package CourseCodes.NewSections.Section_15_Collections.Course10_CodeSetupForSetsMapsPart2;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    public Contact(String name, String email, long phone) {
        this.name = name;
        if (email != null) {
            emails.add(email);
        }
        if (phone > 0) {
            String p = String.valueOf(phone);
            p = "(%s) %s-%s".formatted(p.substring(0, 3), p.substring(3, 6),
                    p.substring(6));
            phones.add(p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phones);
    }

    public Contact mergeContactData(Contact contact) {

        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }

//Part-4
/*
        Instead of selecting the IntelliJ default, I want to select java.util.Objects.equals and hashCode (java 7 +). I'll
    accept the defaults for the first 2 screens, where all fields are selected. On the third dialogue, where it says select
    all non-nulls fields, I'm going to just select name here. Now I want to examine these generated methods, in light of
    what I've previously talked about. First, let's look at the equals method. We've said before that if the references
    are equal, they're the same instance, and we can return true in this case. Next, the code checks if the object passed
    is a null, or if the classes (or types) of the objects differ. If either of these conditions is true, then these are
    definitely not equal. Since the class type was previously checked, and found to be the same type as the current instance,
    we can safely cast the object o, to a contact. Ok, so notice, that for name, which is the only field I said was non
    null, the code is directly calling the equals method, against the getName results on both objects. For the other two
    fields, the code is using another utility class, called Objects, a class in the java util package. If I press control
    click on that class name, Objects, I can see the comments in the code, explaining that this is a utility class, introduced
    in JDK 7. This class provides static utility methods to handle nulls, to generate equals results, and hash codes.
    Getting back to the code, you could guess that a contact is equal, based on the name, and if the other attributes
    aren't null, by matching on emails and phones.

        Now, look at the hash code method. Here again, it's using the Objects class, calling a hash method on that. I'll
    press control click on the hash method next. You can see here, this method takes a variable arguments parameter, and
    this code simply passes those off to the hash Code method, on java dot util dot Arrays. And continuing, I'll press
    control click on hashCode there. From this, you see that Java, like IntelliJ, creates a hash code using 31, as a
    multiplier for each values' hash code. Ok, so there's no right way to generate these methods, a lot will depend on
    your own organizations' standards. I'll run my code again, with these methods in place now.

                    ----------------------------------------------
                    Phone Contacts
                    ----------------------------------------------
                    Mickey Mouse: [] [(124) 748-9758]
                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]
                    Robin Hood: [] [(789) 902-8222]
                    Minnie Mouse: [] [(456) 780-5666]
                    Robin Hood: [] [(564) 789-3000]
                    Mickey Mouse: [] [(999) 888-7777]
                    Lucy Van Pelt: [] [(564) 208-6852]
                    ----------------------------------------------
                    Email Contacts
                    ----------------------------------------------
                    Mickey Mouse: [mckmouse@gmail.com] []
                    Mickey Mouse: [micky1@aws.com] []
                    Minnie Mouse: [minnie@verizon.net] []
                    Robin Hood: [rhood@gmail.com] []
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Daffy Duck: [daffy@google.com] []

    My set still contains duplicate names, but Mickey Mouse only has one entry now, for the triple 9, triple 8, double 7
    double 7 phone number. This means my contacts are distinct by the name, all the emails, and all the phone numbers.
*/
//End-Part-4

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

//Part-5
/*
        Going back to the Contact class, I'll again delete both the equals and hash code method. I'll regenerate these,
    using Intelli-J's default template, but in every instance, on each dialogue window, I want to just check the name.
    Make sure to select name on the last dialogue, where it's not selected as a default. Now, here, the hashCode doesn't
    use a multiplier, but simply returns the name's hash code. This means a Contact instance, and a String, which have
    the value Mickey Mouse, will result in the same hash code. This is ok, but it's usually a good idea, to have objects
    which aren't the same class type, return different hash codes, so I'm going to add my own multiplier here. I'll make
    it 33, a composite number, but some research indicates, this multiplier can produce consistent results similar to 31.
    As with almost everything in software engineering, there's debate over what the best multiplier might be. I don't want
    you to think that 33 is better than 31, it's just a different choice. I know this will drive a couple of my students
    to go and research that debate, and that's a good thing. For my own small sets in this code, it isn't really going
    to matter. Now, if I run the code again,

                    ----------------------------------------------
                    Phone Contacts
                    ----------------------------------------------
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]
                    Robin Hood: [] [(564) 789-3000]
                    Mickey Mouse: [] [(999) 888-7777]
                    Minnie Mouse: [] [(456) 780-5666]
                    ----------------------------------------------
                    Email Contacts
                    ----------------------------------------------
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Robin Hood: [rhood@gmail.com] []
                    Mickey Mouse: [mckmouse@gmail.com] []
                    Daffy Duck: [daffy@google.com] []
                    Minnie Mouse: [minnie@verizon.net] []

    I get only distinct records by name in each set. My sets no longer have multiple records for a single contact name.
    Using this method to create my sets, however, means I've lost a couple of phone numbers and emails in the process.
    To resolve that, I'm going to add two methods to the Contact class.
*/
//End-Part-5

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

//Part-6
/*
        The first method will generate and add, a company email to the current instance's email set. This will be public,
    void, called addEmail, and take a string, company name. This code splits the contact name into an array of strings,
    splitting on a space. The email is made up of the first character of the name, then the last string in the array,
    which I'm going to assume is the last name. That's followed by the company name, removing any spaces in it, and finally
    appending dot com to that. Then I execute just the add method on the emails set, passing this new String. To test this
    out, I'll add a call to this method in the main method.
*/
//End-Part-6

    public void addEmail(String companyName) {

        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(name.charAt(0), names[names.length - 1],
                companyName.replaceAll(" ", "").toLowerCase());
        //emails.add(email);
        if (!emails.add(email)) {
            System.out.println(name + " already has email " + email);
        } else {
            System.out.println(name + " now has email " + email);
        }
    }

//Part-8
/*
        The add method on any collection instance returns a boolean, if the element is added successfully. I want to test
    that result here. I'll remove the emails.add statement, and replace it with an if statement, that executes the add
    method on this set, and tests the value returned, the negated value. If add returns false, I print that emails already
    has that value in its set, other wise, I'll print that it was added. Getting back to the main method,
*/
//End-Part-8

    public void replaceEmailIfExists(String oldEmail, String newEmail) {

        if (emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }

//Part-10
/*
        I'm going to call, replaceEmailIfExists, a public method with a void return type. This method takes two Strings,
    an old email, and a new email, both strings. It checks the set to see if the old email is in the set, using the contains
    method. If the old email is in the set, then it removes that old email, and adds the new email. In other words, the
    new email only gets added if the old email is found in the set first, so it's really a replace. Let's say I made a
    mistake, and now need to fix robin hood's email, changing it from sherwood forest dot com, to sherwood forest dot org.
    Going to the main method,

*/
//End-Part-10

}
