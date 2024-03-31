package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course09_CodeSetupForSetsMapsPart1;

import java.util.Collection;
import java.util.List;

//Part-1
/*
                                      The Setup Challenge - The Contact Class



                        ________________________________________________________________
                        | Contact                         Phone Format: (nnn) nnn-nnnn |
                        |______________________________________________________________|
                        | Fields:                                                      |
                        | Set<String> emails = new HashSet<>();                        |
                        | Set<String> phones = new HashSet<>();                        |
                        |______________________________________________________________|
                        | Constructors:                                                |
                        | Contact(String name)                                         |
                        | Contact(String name, String email)                           |
                        | Contact(String name, long phone)                             |
                        | Contact(String name, String email, long phone)               |
                        |______________________________________________________________|
                        | Methods:                                                     |
                        | String getName()                                             |
                        | String toString()                                            |
                        | Contact mergeContactData(Contact contact)                    |
                        |______________________________________________________________|

    This last constructor should do the following:
    * Add the email argument to the emails set, if email is not null.
    * Transform the phone argument, a long, (if it's not zero), to a string in the format (123) 456-7890, for example.
    * Add the transformed phone to the phones set.

    This class should also include 3 public instance methods. A getter for name, an override for the toString method will
    will print the name, set of emails, and set of phones in a simple form. Finally, include a method called mergeContactData,
    that takes a contact, and returns a new Contact instance, which merges the current instance with the Contact passed.
    I've only just started the conversation about Sets and HashSets, but you should be able to setup emails and phones
    as shown for this challenge.

        The ContactData class is going to emulate getting data from an external source, but instead of an external source,
    I just want you to set this data up with two different text blocks, in the format shown below. This data purposely
    has duplicates.

                           Phone Data                               Email Data
                    Charlie Brown, 3334445555               Mickey Mouse, mckmouse@gmail.com
                    Maid Marion, 1234567890                 Mickey Mouse, micky1@aws.com
                    Mickey Mouse, 9998887777                Minnie Mouse, minnie@verizon.net
                    Mickey Mouse, 1247489758                Robin Hood, rhood@gmail.com
                    Minnie Mouse, 4567805666                Linus Van Pelt, lvpelt2015@gmail.com
                    Robin Hood, 5647893000                  Daffy Duck, daffy@google.com
                    Robin Hood, 7899028222
                    Lucy Van Pelt, 5642086852
                    Mickey Mouse, 9998887777

    "Create a Method named getData", that "takes a String type" (either “phone” or “email”), and returns a List of Contact.
    Now, I'm going to use Scanner to parse the data in these text blocks. My reason for using Scanner, is two fold.

        First I want to demonstrate this variation, and second I want you to imagine this data coming from an input file
    or a database. IO and database access are for a later section, but this code simulates getting data from an external
    source. However, you do it, you want to parse this data, creating a contact for each row, and return the list. You
    want to return the lists separately, meaning return a list of phones, or a list of emails, based on the type passed
    to this method. Ok, so that's what I'll be doing in the next part of this lecture, and I'll use the resulting code
    for the upcoming educational content in the lectures for sets and maps. Now, I'll create the Main Class and main method
    and then I'll create the Contact class,
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {


//Part-10
/*
        In the main method, I'll create a List, called emails, and assign that the result of calling getData on my ContactData
    class with emails as the type. I'll repeat that for phone, invoking the same method, but passing phone as the type.
    I'll execute my new printData method to print first the emails, and then the phones. Running that:

                ----------------------------------------------
                Phone List
                ----------------------------------------------
                Charlie Brown: [] [(333) 444-5555]
                Maid Marion: [] [(123) 456-7890]
                Mickey Mouse: [] [(999) 888-7777]
                Mickey Mouse: [] [(124) 748-9758]
                Minnie Mouse: [] [(456) 780-5666]
                Robin Hood: [] [(564) 789-3000]
                Robin Hood: [] [(789) 902-8222]
                Lucy Van Pelt: [] [(564) 208-6852]
                Mickey Mouse: [] [(999) 888-7777]
                ----------------------------------------------
                Email List
                ----------------------------------------------
                Mickey Mouse: [mckmouse@gmail.com] []
                Mickey Mouse: [micky1@aws.com] []
                Minnie Mouse: [minnie@verizon.net] []
                Robin Hood: [rhood@gmail.com] []
                Linus Van Pelt: [lvpelt2015@gmail.com] []
                Daffy Duck: [daffy@google.com] []

    I'm getting the list of phone contacts, and the list of email contacts, printed out neatly You can see I have several
    Mickey Mouses in both lists, and a couple of Robin Hoods in the phone list. Ok, so that was the setup challenge. I'll
    be using this code to explore sets, and merging contacts using sets. Let's move onto the next lecture.
*/
//End-Part-10

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

    }

//Part-9
/*
        I'll call it printData, and it'll take a string, a header, as well as a Collection, with a type of Contact. This
    prints the header between some separator lines, and then uses forEach to print each contact. Next, I'll test this out.
*/
//End-Part-9

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
