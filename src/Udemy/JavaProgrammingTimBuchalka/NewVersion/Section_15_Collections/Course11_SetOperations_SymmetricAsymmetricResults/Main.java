package CourseCodes.NewSections.Section_15_Collections.Course11_SetOperations_SymmetricAsymmetricResults;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

//Part-1
/*
                                                Set Operations

        In the last lecture, I used a HashSet to control what went in a set, and what didn't, by manipulating the equals
    and hashcode methods. In this lecture, I'll be explaining a little bit about Set Math, or Set Operations, first what
    they are, and second, why you'd want to use them. When you're trying to understand data in multiple sets, you might
    want to get the data that's in all the sets, that's in every set, or the data where there's no overlap. The collection
    interface's bulk operations (addAll, retainAll, removeAll, and containAll) can be used to perform these set operations.

                                         Representing Sets in a Diagram

        Sets are often represented as circles or ovals, with elements inside, on what is called a Venn Diagram. Here, I'm
    showing two sets that have no elements in common.

                                Set-A                               Set-B
                            Linus Van Pelt                        Daffy Duck
                            Lucy Van Pelt                         Mickey Mouse
                            Charlie Brown                         Minnie Mouse

    This venn diagram shows some of the cartoon characters of the Peanuts and Mickey Mouse cartoons. Because the characters
    are distinct for each set, the circles representing the sets don't overlap, or intersect.

                                Set-A           Intersection-Set            Set-B
                            Linus Van Pelt          Goofy               Daffy Duck
                            Lucy Van Pelt           Snoopy              Mickey Mouse
                            Charlie Brown                               Minnie Mouse

    This diagram shows two sets of characters that do overlap. Let's say that Goofy and Snoopy, have guest appearances in
    the other's holiday special show. The intersection of these sets is represented by the area where the two circles (sets)
    overlap, and contains the elements that are shared by both sets. Goofy and Snoopy are both in Set A and Set B, in other
    words. Venn Diagrams are an easy way to quickly see how elements in multiple sets relate to each other.

                                            Set Operations - Union A u B

        The union of two or more sets will return elements that are in any or all of the sets, removing any duplicates.
    These may look familiar, because these are the sets I had, when I left off in the code in the last lecture.

                 A:Emails             Intersection-Set         B:Phone Numbers         Union Results (C)
                Linus Van Pelt          Mickey Mouse            Lucy Van Pelt           Linus Van Pelt
                Daffy Duck              Minnie Mouse            Maid Marion             Daffy Duck
                                        Robin Hood              Charlie Brown           Mickey Mouse
                                                                                        Minnie Mouse
                                                                                        Robin Hood
                                                                                        Lucy Van Pelt
                                                                                        Maid Marion
                                                                                        Charlie Brown

    The table shown here is showing my two sets, names on an email list, and names on a phone numbers list. The overlap
    are names that are on both lists. In the example shown above, all names on the email list and phone list will be included
    in a union of the two sets, but Minnie, Mickie and Robin Hood, which are the only elements included in both sets, are
    included in the resulting set only once. If you have any experience with Structured Query Language, or SQL, you'll
    already be familiar with the union command, which joins two datasets in the same way described here. Java doesn't have
    a union method on Collections, but the addAll bulk function, when used on a Set, can be used to create a union of
    multiple sets.

        Getting back to the code from the last lecture, I left off with two sets, phone contacts and email contacts. A
    union of these two sets, should give us a set that consists of distinct contacts, by contact name. To perform a union,
    I can use the bulk operation, add All. I'll first create a new Set, called union A, B, a HashSet, with a no args constructor.
*/
//End-Part-1

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com",
        "RHood@sherwoodforest.org");
        System.out.println(robinHood);

//Part-2
/*
        I left off with two sets, phone contacts and email contacts. A union of these two sets, should give us a set that
    consists of distinct contacts, by contact name. To perform a union, I can use the bulk operation, add All. I'll first
    create a new Set, called union A, B, a HashSet, with a no args constructor. I'm going to stick to the convention on
    my notes, where A, is the emails set, and the B, is the phones. I'll use the add all method on the unionAB set, to
    add first email contacts. I'll do the same thing, union A B dot add all, passing it phone contacts. Lastly, I'll call
    printData, with a header saying this is the union of emails, A, and phones, B. There's a special ascii character, \u222A,
    that represents the union character, that looks like a U, and I'll include that. If I type \u222A in my text, Intelli-J
    highlights that unicode character, and prompts me to replace it with the actual character, so I'll do that Running
    this code,

                        ----------------------------------------------
                        (A ∪ B) Union of emails (A) with phones (B)
                        ----------------------------------------------
                        Linus Van Pelt: [lvpelt2015@gmail.com] []
                        Lucy Van Pelt: [] [(564) 208-6852]
                        Charlie Brown: [] [(333) 444-5555]
                        Maid Marion: [] [(123) 456-7890]
                        Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
                        Mickey Mouse: [mckmouse@gmail.com] []
                        Daffy Duck: [daffy@google.com] []
                        Minnie Mouse: [minnie@verizon.net] []

    I get the union of these sets, and there's no duplicates by name. These operations combined the two different sets of
    contacts, into a single set of unique contacts. Again, I've lost some information where I had multiples, the other
    phone numbers and emails I mean. But if you ever just need a distinct set of elements from multiple sets, this union
    operation can give you that information. At this point, it would be nice to know which contacts overlap the sets. This
    information might allow me to process those records in a different way, perhaps capturing the additional emails and
    phone numbers, for example.
*/
//End-Part-2

        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB);

//Part-3
/*
                                            Set Operations - Intersect A n B

        The intersection of two or more sets, will return only the elements the sets have in common.

                 A:Emails             Intersection-Set         B:Phone Numbers         Intersection Results (C)
                Linus Van Pelt          Mickey Mouse            Lucy Van Pelt               Mickey Mouse
                Daffy Duck              Minnie Mouse            Maid Marion                 Minnie Mouse
                                        Robin Hood              Charlie Brown               Robin Hood

    These are shown in the overlapping area of the sets on this table, the intersect and includes Mickey and Minnie Mouse,
    and Robin Hood.

        Let's try this out with our same two sets. I'll start out by creating a local variable,a Set I'm calling intersectAB.
    I'll again assign that a new instance of HashSet, but this time I'm just going to pass my email contacts set directly
    to that constructor. I'll execute retainAll on the intersect set, passing it the phone contacts. And I'll print the
    result. The ascii character for the intersect operator is \u2229, it looks like an upside down u, and I want to include
    that as part of my message, A intersect B. I'll again take IntelliJ's suggestion, and just convert the unicode escape
    sequence to the actual character. On the first line, this time, I'm simply passing my first set to the constructor
    of my new set. In fact, if I control click HashSet there, the code will be displayed in another editor session, showing
    me how it's implemented. And you can see, this constructor simply calls addAll there. You maybe also noticed it's
    creating a new HashMap, assigning it to a map field on this HashSet class, in the first statement. I said before that
    the HashSet uses a HashMap in its implementation, and this is obvious here. These two classes are tightly interwoven
    in current versions of Java. But regardless of the underlying way the HashSet implements it's collection, and there's
    never any guarantee in Java that the implementation won't change, the behavior of the HashSet will be consistent. This
    means duplicates aren't supported, the collection won't be ordered, and hashing will be used to provide close to constant
    time access. Ok, so getting back to the main method, the other thing to see there is that I used retain all, and passed
    it the email contacts. Running that code,

                    ----------------------------------------------
                    (A ∩ B) Intersect emails (A) and phones (B)
                    ----------------------------------------------
                    Robin Hood: [RHood@sherwoodforest.org, rhood@gmail.com] []
                    Mickey Mouse: [mckmouse@gmail.com] []
                    Minnie Mouse: [minnie@verizon.net] []

    I get what's called the intersection, where the two sets intersect, and the result is the elements the sets have in
    common. This gives me the 3 contacts that are on both lists, Mickey, Minnie, and Robin Hood. Notice that the contacts
    all have emails, and that's because multiple records weren't added, and it's the first record that's added, that remains
    in the set. In other words, a duplicate element won't replace the current element. In this case, when I added the phone
    contacts for Mickey, Minnie, and Robin Hood, those records were ignored, because the set already had records for them
    in it. I can switch the order of the way I intersect around, and I'll show that to you next.
*/
//End-Part-3

        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        printData("(A ∩ B) Intersect emails (A) and phones (B)", intersectAB);

//Part-4
/*
        I'll copy those last 3 statements and paste them below. I'll change my set name to intersectBA, in all three
    statements. I'll pass phone contacts to the constructor this time, so those are the first set of elements included.
    I'll change phone contacts to email contacts when I call the retainAll method. I'll also change my message to say
    this is B intersect A, Intersect of phones (Bin parentheses) and emails, (A, in parentheses). Running that code,

                    ----------------------------------------------
                    (B ∩ A) Intersect phones (B) and emails (A)
                    ----------------------------------------------
                    Robin Hood: [] [(564) 789-3000]
                    Mickey Mouse: [] [(999) 888-7777]
                    Minnie Mouse: [] [(456) 780-5666]

    I get the same set of contacts, the same set of three cartoon characters. I mean, Mickey, Robin, and Minnie. The extra
    data, the data that wasn't included in the equality test, is different here though, so now I get phone numbers. That's
    because the first contact records added for these three, had the phone data on them. Even though I flipped the sets
    in my intersection set, the result was the same, returning Mickey, Robin, and Minnie.

                                    Set Operations - Symmetric Operations

        The ability to evaluate sets, A intersect B and get the same result as B intersect A, means that the intersect
    operation is a symmetric set operation. Union is also a symmetric operation. It doesn't matter if you do A Union B,
    or B union A, the final set of elements will all be the same set of names. Another useful evaluation might be to
    identify which elements are in one set, but not the other. This is called a set difference.
*/
//End-Part-4

        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
        intersectBA.retainAll(emailContacts);
        printData("(B ∩ A) Intersect phones (B) and emails (A)", intersectBA);

//Part-5
/*
                                            Set Operations - Asymmetric Differences

        A difference subtracts elements in common from one set and another, leaving only the distinct elements from the
    first set as the result.

           A:Emails             Intersection-Set         B:Phone Numbers     Set Difference (A - B)    Set Difference (A - B)
         Linus Van Pelt          Mickey Mouse            Lucy Van Pelt           Linus Van Pelt            Lucy Van Pelt
           Daffy Duck            Minnie Mouse            Maid Marion             Daffy Duck                Maid Marion
                                 Robin Hood              Charlie Brown                                     Charlie Brown

    This is an asymmetric operation because if we take Set A and subtract Set B from it, we'll end up with a different set
    of elements than if we take Set B and subtract Set A. The sets from these two operations won't result in the same
    elements.

        Let's see what this looks like in our code. I'm going to copy the intersectAB's 3 statements, and paste a copy
    at the end of the main method. I'll change intersectAB to AMinusB on all three lines. On the second line, I'll change
    retainAll to removeAll. On the third line, I'll change the intersect character to a minus sign, and I'll just say
    this is emails, A, minus phones, B. Running this code,

                    ----------------------------------------------
                    (A - B) emails (A) - phones (B)
                    ----------------------------------------------
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Daffy Duck: [daffy@google.com] []


    This tells us that Linus and Daffy are the only two records, in the email list, that aren't in the phones list.
*/
//End-Part-5

        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) emails (A) - phones (B)", AMinusB);

//Part-6
/*
        Now let me copy those last 3 statements and paste them right below. I want to change the Set name A, Minus B, in
    all three statements to B Minus A. I'll also change the first statement, and pass it phone contacts, instead of email
    contacts, to the constructor. The second statement, I want to pass email contacts there, instead of phone contacts,
    so I'm reversing the direction if you will. I'll change my header to say B minus A, phones (B) - emails (A). Running
    that code,

                    ----------------------------------------------
                    (B - A) phones (B) - emails (A)
                    ----------------------------------------------
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]

    you now see that the set of contacts is a different set of characters all together, Lucy Van Pelt, Charlie Brown, and
    Maid Marion.
*/
//End-Part-6

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) phones (B) - emails (A)", BMinusA);

//Part-7
/*
                                            Set Operations - Symmetric Differences

        Now, let's look at what's called the set symmetric difference. This is really the union of the two asymmetric
    set differences. You can think of the set symmetric difference, as the elements from all sets that don't intersect.

           A:Emails             Intersection-Set         B:Phone Numbers     Symmetric Difference ([Union of A and B] - [Intersection of A and B])
         Linus Van Pelt          Mickey Mouse            Lucy Van Pelt                          Linus Van Pelt
           Daffy Duck            Minnie Mouse            Maid Marion                            Daffy Duck
                                 Robin Hood              Charlie Brown                          Maid Marion
                                                                                                Charlie Brown

    On this table, these are the elements that are represented in the intersection. This data can be retrieved in one of
    two ways. The first would be to do a union of our two difference sets, AMinusB and BMinusA, so I'll do that next. I'll
    set up a new set, and call it symmetric Diff, assigning it a new HashSet, and passing A, minus B to that constructor.
    I'll call add all, on the symmetricDiff set, and pass it B Minus A. I'll print out that this is the Symmetric Difference
    of phones and emails. Running that,

                    ----------------------------------------------
                    Symmetric Difference: phones and emails
                    ----------------------------------------------
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]
                    Daffy Duck: [daffy@google.com] []

    I get the five contacts that don't intersect, the union of my two asymmetric differences set. The other way to get
    these same set of elements, is to take the unioned set, and subtract the intersected set. I'll first create a set,
    called symmetric diff2, a hash set constructed by passing the unionAB set, to it. I'll execute remove all on that
    set, passing it the intersectAB set. And I'll print the same text, as previously, but pass this new set, symmetric
    Diff2. And running that,

                    ----------------------------------------------
                    Symmetric Difference: phones and emails
                    ----------------------------------------------
                    Linus Van Pelt: [lvpelt2015@gmail.com] []
                    Lucy Van Pelt: [] [(564) 208-6852]
                    Charlie Brown: [] [(333) 444-5555]
                    Maid Marion: [] [(123) 456-7890]
                    Daffy Duck: [daffy@google.com] []

    I get the same results this way. Ok, so that covers the most common set operations, and the difference between symmetric
    operations and asymmetric operation. I hope you can start to imagine how you might use these combinations, to drive
    processing, based on the relationships of the data in multiple sets. In the next lecture, I'll give you a set operations
    challenge to test what you learned, so let's move on to that.
*/
//End-Part-7

        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric Difference: phones and emails", symmetricDiff);

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric Difference: phones and emails", symmetricDiff2);

    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
