package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course10_CodeSetupForSetsMapsPart2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Part-1
/*
                                            Introduction Sets & HashSet

        A Set is not implicitly ordered. A Set contains no duplicates. A Set may contain a single null element. Sets can
    be useful because operations on them are very fast. In actual fact, the lack of duplicates is the most important
    differentiator, as there are ordered sets, such as the LinkedHashSet, and TreeSet, that I'll review in a later lecture.

        The set interface defines the basic methods "add", "remove" and "clear", to maintain the items in the set. We can
    also check if a specific item is in the set using the contains method. Interestingly enough, there's no way to retrieve
    an item from a set. You can check if something exists, using contains, and you can iterate over all the elements in
    the set, but attempting to get the 10th element, for example, from a set isn't possible, with a single method.

                                                The HashSet Class

        The best-performing implementation of the Set interface is the "HashSet class". This class uses hashing mechanisms
    to store the items. This means the hash code method is used to support even distributions of objects in the set. Oracle
    describes this class as offering constant time performance for the basic operations (add, remove, contains and size).
    This assumes the hash function disperses the elements properly among the buckets. Constant time has the Big O Notation
    O(1). Although I haven't covered the Map and HashMap types yet, the HashSet actually uses a HashMap in it's own implementation,
    as of JDK 8. Later, when I cover maps and hash maps, I'll swing back and explain how the Hash Set uses a hash map under
    its covers.

        In the lecture called Set Up for Sets and Maps, I created code for a Contact class, as well as code that simulated
    getting phone lists and email lists, from an external source. I have two classes set up in this code:

        The Contact class that consists of a name, a set of phones, which are strings, and a set of emails, also strings.
    This class has only three methods on it. The first two are a getter for name, as well as an overridden two String method.
    There's also a merge Contact Data method, that has a contact as an argument, and creates a new contact. It populates
    the new contact, first with the data on the current instance, and then merges emails and phone numbers, from the contact
    that's passed to this method.

        The second class, ContactData, has one method on it, getData, that takes a type, a String, and will return a list
    of Contact instances. The type can be phone or email. If I pass phone, I get a list of contacts each with a single
    phone number. If I pass email, I get a list of contacts with a single email. The data's stored as text blocks, simulating
    what each record might look like, in a comma delimited file. Data is read by a Scanner instance, constructed with one
    of these text blocks. Every time I call getData on this class, I'll get a new list with new instances of contacts.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {


//Part-2
/*
        The main method has a few lines of code in it, calling getData on the ContactData class. It then uses a method on
    the Main class, called printData, to print this data out, in a neatly formatted way. And if I run this code,

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

    I get the two lists from the ContactData, and the contact info printed out. Ok, so now imagine I've got a list of names
    with phone numbers, with my mobile phone contacts, and also a list of email contacts from an internet provider. Now,
    I want to combine these contacts, merging any duplicates into a single contact, with multiple emails and phone numbers,
    on a single record. To do this, I'll create two Sets,
*/
//End-Part-2

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

//Part-3
/*
        Two HashSets to be specific, one for the phone data, and one for the email data. Like Lists, I start with the interface
    type as the variable type, so Set here, and a type argument of Contact, in both cases. The first variable, email contacts
    will be assigned a new Hash set, constructed by passing the emails list to it. The phone contacts is the same, a new
    Hashset but constructed using the phones list. Most constructors of classes implementing the Collection interface,
    support a constructor that accepts a Collection. You can see, this lets me very quickly create anew hashSet from a
    list, with one line of code. I'll again use my printData method on this class, passing a header, and the phone or
    email contacts. The printData method, will take any Collection type, meaning any instance of a class that implements
    Collection. This lets me pass any list or a set to this method to get printed. Let me run this.

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


    Here, I hope you can see, there are still duplicates in both of these sets, but the order is not the same as the order
    that was in the list. Can you guess why there's duplicates in these sets, even though I just said earlier, that HashSets
    won't have duplicates? Well, duplicates are determined, for hashed collections, first by the hash code, and then the
    equals method. In this instance, both the hash code method, and the equals method, are using Object's implementation.
    This means each of these instances of contacts is considered unique, by that definition. In most cases, this is probably
    a good thing. But since these are personal contacts, I'm going to make a rule, that contacts that have the same name,
    are really the same person, but with different data. To implement this rule, I want to go to the Contact class, and
    generate both an equals method, and a hash code method.
*/
//End-Part-3

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

//Part-7
/*
        But I want to first get Robin Hood from the list of email contacts. I can do this by first creating a new Contact,
    using just the name Robin Hood now. I can pass this new contact, to the index-of method on the emails list, which
    returns the integer index, where that element is located the list. Then I can use the get method on the list to return
    the original email contact, which is what I want. Now that I've got the original Robin Hood contact, I'll add the company
    email to this record, calling the addEmail method with the company name, Sherwood Forest. I'll add that statement before
    the system dot out dot println statement. If I run that,

            ---(same)
            ----------------------------------------------
            Email Contacts
            ----------------------------------------------
            Linus Van Pelt: [lvpelt2015@gmail.com] []
            Robin Hood: [rhood@gmail.com] []
            Mickey Mouse: [mckmouse@gmail.com] []
            Daffy Duck: [daffy@google.com] []
            Minnie Mouse: [minnie@verizon.net] []
            Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []

    you can see my method works, and now I have two emails for Robin Hood, one is r hood at sherwood forest dot com. So
    what happens if I call that method again? Actually, before I do that, I want to change the add email method on contact.
*/
//End-Part-7

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        //System.out.println(robinHood);

//Part-9
/*
        I'll copy that first addEmail statement, and paste a copy right below it. Running this,

                    ---(same)
                    Robin Hood now has email RHood@sherwoodforest.com
                    Robin Hood already has email RHood@sherwoodforest.com

    you can see that the first time, Rhood at sherwoodforest.com was added, but in the second instance, the add wasn't
    successful, because that email is already there. A better approach would have been to use the contains method, before
    I did all the work of generating the email string. I'll do this in the next method I want to add to Contact Class,
*/
//End-Part-9

        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);

//Part-11
/*
        I'll add this statement after the second add Email call. Running that code,

                    ---(same)
                    Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []

    you can see that I successfully replaced the company email, with the dot org version, for Robin hood. Unlike lists,
    the hash set implementation doesn't include a replace, or replaceAll method. Those are the basic functions on set,
    add, remove, and contains. Now, there's no get method on a set. If you want to get an element from the set, you'll
    have to iterate through every element, and look for a match manually. And remember your HashSet's not going to be
    ordered or sorted. Sets are valuable for groups of elements, when you'll be adding elements, removing duplicates,
    checking if an element is in the list, or other set operations I'll be covering shortly. This isn't the collection
    you'd use, if you mostly want to get elements from your collection, and manipulating values. In the next lecture,
    I'll talk about set operations, which are ways to evaluate relationships of elements, in different sets.
*/
//End-Part-11
    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
