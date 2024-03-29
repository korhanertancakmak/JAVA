package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course09_CodeSetupForSetsMapsPart1;

import java.util.HashSet;
import java.util.Set;

//Part-2
/*
        And I'll put it in the same package. I want 3 fields on this class, which I described on the challenge info. name
    as string, emails and phones both declared as sets, and assigned new HashSet instances. I'm going to make emails and
    phones both HashSets, because I don't want duplicates in my contact data.
*/
//End-Part-2

public class Contact {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();

//Part-3
/*
        Next, I want a series of constructors. I'm going to set most of these up manually, because my constructor arguments
    aren't going to match up with my fields. I'll use IntelliJ's tools to generate for the first one, which has an argument
    for just name. But I don't want to set the name field here. Instead, I want to chain another constructor that I haven't
    yet created, one with 2 arguments, and I'll pass name, and null for email. I'll add another constructor, has 2 arguments,
    the new argument is going to be String and email, and I'm going to chain this to another constructor call, to a 3
    arguments constructor, defaulting zero as the phone as the 3rd argument. I'll add another constructor again, I'll add
    phone as a long in the parameters, and then I'll add it to the 3 constructor call. For the last constructor, I'm just
    going to manually type this one out, and talk through it. As I stated in my challenge info, this will have name and
    email, both strings, and a long for phone.
*/
//End-Part-3

    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

//Part-4
/*
        Ok, name is easy, I'm just assigning it to the method argument. Next, I want to add the email passed to this constructor,
    to the emails set on this class, only if email is not equal to null. Now, adding the phone is a bit more complicated,
    first of all because it's a long, and it needs to be a String, formatted in a specific way. If the phone number isn't
    greater than 0, I won't do anything. If it is, I'll first create a String out of it, using the value of method, passing
    it a long, the phone. I'll now set up a formatted string, and pass the first 3 digits, then the next 3, and the final
    four, which is how phone numbers are formatted in some US entries. Then, I'll simply add that local variable to the
    phones set. Ok, so that's all the constructors. Now I have 3 methods I need to code.
*/
//End-Part-4

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

//Part-5
/*
        First a getter for name, so I'll generate that. I also want a toString method. I'll use the override feature in
    IntelliJ, to generate that. I want to replace that return line, which is "return super.toString()" with my own formatted
    String. Like lists, I can pass sets right to println, or to the formatted method, as I show here.
*/
//End-Part-5

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phones);
    }

//Part-6
/*
        Ok, I've got 1 last method. That's public returns a Contact, I'll name it mergeContactData, and takes a Contact
    as an argument. I'll create a local variable, also a contact, and assign that a new Contact instance, passing the name
    on the current instance to that constructor. That gets returned from this method.

                                        Contact newContact = new Contact(name);
                                        return newContact;

    But I also want to include the emails and phones from both contacts, so I'll set the emails on the new contact to be
    a new hash set, passing it the current instance's emails. I'll do the same thing for the phones, passing it the phones
    which are on the current instance.

                                        newContact.emails = new HashSet<>(this.emails);
                                        newContact.phones = new HashSet<>(this.phones);

    At this juncture, this code is really just cloning the data, by using all the data from the current instance, to create
    a new contact. To make it a merge, I want to add the emails and phones from the contact passed to this method. To do
    that, I can just call "addAll" on the new Contact's emails field, and pass it the emails on the Contact that is passed
    to this method. I'll do the same thing for the phones. Ok, so that completes the Contact class. Now I want to do the
    second part of the code setup, and that's to create a ContactData class,
*/
//End-Part-6

    public Contact mergeContactData(Contact contact) {

        Contact newContact = new Contact(name);
        newContact.emails = new HashSet<>(this.emails);
        newContact.phones = new HashSet<>(this.phones);
        newContact.emails.addAll(contact.emails);
        newContact.phones.addAll(contact.phones);
        return newContact;
    }
}
