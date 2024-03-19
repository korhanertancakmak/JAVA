package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course19_MapFunctionalityMore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-----------------------------");

        Map<String, Contact> contacts = new HashMap<>();

        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("-----------------------------");
        System.out.println(contacts.get("Charlie Brown"));

        System.out.println(contacts.get("Chuck Brown"));

        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

        System.out.println("-----------------------------");
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate != null) {
//                System.out.println("duplicate = " + duplicate);
//                System.out.println("current = " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("-----------------------------");
        contacts.clear();

        for (Contact contact : fullList) {
            contacts.putIfAbsent(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("-----------------------------");
        contacts.clear();

        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("-----------------------------");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData
                ));
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-1
/*
        In the last lecture, I covered the put, putIfAbsent, and merge methods, Somewhat similar to those, are the compute,
    computeIfPresent, and computeIfAbsent. Like the merge method, these are default methods that were added to the Map
    interface with JDK 8. The compute and computeIfAbsent methods take two values, for their BiFunction as well, but these
    represent the key and the value, not two contacts.
*/
//End-Part-1

        System.out.println("-----------------------------");
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            //contacts.compute(contactName, (k, v) -> new Contact(k));
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-2
/*
        Let's look at this in action. This time, I don't want to clear my map. I'll loop through an array of names. One
    name, Daffy Duck, is already a contact in my list, but the other two aren't. And this method, takes a BiFunction, so
    I've got a lambda expression, that has parameters, k for key, and v for value. I'll just return a new Contact, using
    the constructor that only takes a name, passing that my k argument, the key in other words. Running this code,

                    -----------------------------
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Duck: [] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see, I've got two new contacts, Daisy Duck and Scrooge McDuck, with no emails or phone numbers. But now look
    at Daffy Duck, I've also erased his previous information, and replaced it with a brand new contact record. Compute is
    like the put method in this way, replacing what's in the map with the result of the Bi Function, or lambda expression.
    Maybe that's not what I really want to do. I can replace my compute method, with a computeIfAbsent call there.

                            contacts.compute(contactName, (k, v) -> new Contact(k));
                                                    to
                            contacts.computeIfAbsent(contactName, (k, v) -> new Contact(k));

    But that doesn't compile. The computeIfAbsent method, only takes a key value as an argument, so I need to change
    this code once more. I'll just use k as the parameter, with my lambda expression. I could keep the parentheses around
    that, but I kind of prefer it with no parentheses, when it's a single parameter like this.

                            contacts.computeIfAbsent(contactName, (k, v) -> new Contact(k));
                                                    to
                            contacts.computeIfAbsent(contactName, k -> new Contact(k));

    And notice, I can replace that with a method reference, but I don't want to do that right now. This code compiles now,
    and running it,

                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Duck: [] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see Daisy and Scrooge there hasn't been touched, but Daffy Duck has a new record. Going back to the map main
    method, I want to use computeIfPresent, for my same list of duck contacts. I'm going to say all my duck contacts,
    work at a family owned business called fun place. I'll copy and paste the codes.
*/
//End-Part-2

        System.out.println("-----------------------------");
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun Place"); return v; });
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-3
/*
        I want to remove the statement in the for loop, replacing that with a call to computeIfPresent. Like the first
    compute method, the first parameter is the key, and the next is a function, and like compute, it's a bi function that
    takes both the key and the value, so I'll put parens there, and add v. Here, I've got a multi-line lambda, and I'll
    execute add Email on the element, passing it Fun Place as the company name, and I'll return the updated contact.
    Running this code,

                    -----------------------------
                    Daisy Duck now has email DDuck@funplace.com
                    Daffy Duck now has email DDuck@funplace.com
                    Scrooge McDuck now has email SMcDuck@funplace.com
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Duck: [DDuck@funplace.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DDuck@funplace.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see that now all my duck contacts had a new email added, but my email logic's not very good, since Daisy and
    Daffy have the same email. The compute methods give you a lot of functionality, for adding elements that aren't in
    the map, replacing values already keyed, resetting all elements in the map to some default value, or executing some
    code on the map elements that do exist. Next, I want to fix the two Ducks with the same email, using yet another
    default method on the Map interface, this one called replaceAll. This method is similar to the replaceAll method on
    the List interface, except for a map, this takes a bi function that has two arguments. It takes the key and value,
    and the function should return an object the same type as the value.
*/
//End-Part-3

        System.out.println("-----------------------------");
        contacts.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-4
/*
        I'll start with a separator line, followed by calling replaceAll on my contacts map. I'll call my parameters k
    and v, for key and value, as usual, and start a multi-line lambda. I want a new email, that's made up of the contact
    name with spaces removed, then appends ampersand fun place dot com to the name, which will make it a unique email
    name for my contacts. And then I execute the replaceEmailIfExists method on contact, passing the old email, and the
    new email. Finally, I return that resulting contact, which is my lambda parameter, v. Ok, so this code isn't really
    very efficient, since I'm calling replaceAll on every single entry in the map, when I really only want to replace the
    email of two contacts. I did want to show you, however, that you can execute this method on the entire map, just like
    the list's replaceAll method. Running this code,

                -----------------------------
                key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                key=Daisy Duck, value= Daisy Duck: [DaisyDuck@funplace.com] []
                key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
                key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see that Daisy and Daffy's emails have been updated, but all other contact emails are untouched. In addition
    to replace all, I can replace just a single element in the map, by either matching on key alone, or both key and value.
    Let's say I have a contact where the name is Daisy Jane Duck, with an email of daisyj@duck.com.
*/
//End-Part-4

        System.out.println("-----------------------------");
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");

        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-5
/*
        I'll set up that record here. I'll create a new contact, daisy, and pass that Daisy Jane Duck as the name, and
    daisyj@duck.com as the email. Now, I want to add this contact to my map, for Daisy Duck, ignoring any middle name or
    initial. I'll call replace on my contacts map, passing the key, Daisy Duck, and my new contact daisy. I'll assign the
    result to a variable called replacedContact. And I'll print Daisy. Then the replacedContact. Finally, all the key
    value pairs in my map. Running that code,

                    -----------------------------
                    daisy = Daisy Jane Duck: [daisyj@duck.com] []
                    replacedContact = Daisy Duck: [DaisyDuck@funplace.com] []
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Jane Duck: [daisyj@duck.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see the new daisy contact, and the replaced Contact was the one that was in the map already. But this code
    replaced that in the map, with the new daisy, with the name Daisy Jane Duck. There's no rule that says the contact
    name has to match the key, and right now, they don't for this record. The replace method has an overloaded version,
    which lets you specify that you only want to replace the value in the map, if both the keys and values match.
*/
//End-Part-5

        System.out.println("-----------------------------");
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        //boolean success = contacts.replace("Daisy Duck", replacedContact, updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if (success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", replacedContact));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-6
/*
        To set this up, I'll add a separator line, then I'm going to merge my two daisy contacts, daisy and replaced contact,
    assigning the result to a variable, updated daisy. Then I'll print updated daisy. Next, I want a boolean flag called
    success, and I'll assign that the result of calling contacts dot replace, with Daisy Duck still as my key, and replacedContact
    as the second argument, and updatedDaisy as the third. I'll add an if then else statement, based on that success variable.
    If success is true, meaning the replace was successful, I'll print that the code successfully replaced element. Otherwise,
    I'll print that the code didn't match both key and value, and print those both out. I'll end by printing out the
    contacts in my map. Running that code,

                    -----------------------------
                    updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
                    Did not match on both keys: Daisy Duck and value: Daisy Duck: [DaisyDuck@funplace.com] []
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Jane Duck: [daisyj@duck.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    you can see the merged contact, updated Daisy, with the name Daisy Duck, but with 2 emails. You can see that the code
    to replace Daisy, testing the key Daisy Duck and the record that's in replaced Contact, wasn't successful. Remember
    that a contact is considered equal, if they have the same name. Our map's daisy record now has Daisy Jane Duck as the
    name, and that doesn't match the name of the replaced contact, which was the original record. You can see that when
    I printed out the map, the key says Daisy Duck, but the value shows Daisy Jane Duck. I'll change my arguments, swapping
    replacedContact with just daisy, the contact that has Daisy Jane. Running that code,

                    -----------------------------
                    updatedDaisy = Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
                    Successfully replaced element
                    key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                    key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                    key=Daisy Duck, value= Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
                    key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                    key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                    key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
                    key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                    key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                    key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                    key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    I see that this successfully replaced element. And look at the data in the Daisy Duck record, I am back to just Daisy
    Duck as the name, and I have two emails, so this is the merged contact that replaced the previous record. Those are
    the two overloaded replace methods. Similarly, there are also two overloaded versions of the remove method. The first
    remove method takes a key, and returns the value that was removed, or null, if a value doesn't exist for that key.
    The second remove method takes both a key and a value. It only removes the element from the map, if the key is in the
    map, and the element to be removed equals the value passed. This method returns a boolean. Let's look at this one next.
*/
//End-Part-6

        System.out.println("-----------------------------");
        success = contacts.remove("Daisy Duck", daisy);
        if (success) {
            System.out.println("Successfully removed element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", daisy));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

//Part-7
/*
        This is going to look very similar to the code above. I'll print a separator line. I'll assign the result of the
    remove method that takes two arguments to the success variable. I'm going to pass the key "Daisy Duck", and the contact
    record, daisy, which I set up with the name Daisy Jane Duck. If that comes back true,I'll print out that the code
    successfully removed the element. Otherwise, I'll print out that it didn't match, and print both the key and value.
    And I'll end by again printing all key value elements in the map. Running that code,

                -----------------------------
                Did not match on both key: Daisy Duck and value: Daisy Jane Duck: [daisyj@duck.com] []
                key=Linus Van Pelt, value= Linus Van Pelt: [lvpelt2015@gmail.com] []
                key=Lucy Van Pelt, value= Lucy Van Pelt: [] [(564) 208-6852]
                key=Daisy Duck, value= Daisy Duck: [daisyj@duck.com, DaisyDuck@funplace.com] []
                key=Minnie Mouse, value= Minnie Mouse: [minnie@verizon.net] [(456) 780-5666]
                key=Maid Marion, value= Maid Marion: [] [(123) 456-7890]
                key=Daffy Duck, value= Daffy Duck: [daffy@google.com, DaffyDuck@funplace.com] []
                key=Robin Hood, value= Robin Hood: [rhood@gmail.com] [(789) 902-8222, (564) 789-3000]
                key=Charlie Brown, value= Charlie Brown: [] [(333) 444-5555]
                key=Scrooge McDuck, value= Scrooge McDuck: [SMcDuck@funplace.com] []
                key=Mickey Mouse, value= Mickey Mouse: [micky1@aws.com, mckmouse@gmail.com] [(124) 748-9758, (999) 888-7777]

    I get the statement that they did not match on the key and the value. The value in the map doesn't include Jane in
    the name, so it doesn't match. Like the replace method that returns a boolean, this version of the remove method,
    must find the key in the map. It won't remove the element though, unless it's considered equal to the element passed
    as the second argument. Ok, this feels like a good place to end this lecture, since I now want to change gears a little
    bit. In the next lecture, I want to talk about the three collections we can get from the map, and methods for using them.
*/
//End-Part-7

    }
}
