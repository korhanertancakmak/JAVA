package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course04_ImmutableClasses;

//Part-1
/*                                                      Immutable Object

        So far in this section, I've covered several examples of how using immutable objects, can leave you open to unintended
    side effects. In this lecture, I'll show you one way to minimize these, the immutable Object.

        * An immutable object doesn't change state, once it's created.
        * An immutable object is a secure object, meaning calling code can't maliciously or mistakenly alter it.
        * An immutable object simplifies concurrency design, which we'll cover later.

        Here we can describe the strategies of creating a class, that when used, produces immutable objects.

        - Make instance fields private and final.
        - Do not define any setter methods.
        - Create defensive copies in any getters.
        - Use a constructor or factory method to set data, making copies of mutable reference data.
        - Mark the class final, or make all constructors private.

    In this lecture, I'll walk through the first four of these strategies in code. I'll discuss final classes and private
    constructors in upcoming lectures. I've created the Main class.Let's imagine that we're creating a genealogy program,
    and we're tracking people, names, birth dates and kids. I'll start out with a Person class.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-4
/*
        Since I didn't declare any constructors, the only one I can use is the default no args constructor. Jane will be
    one of the kids. I'll just set her name for now. And I'll do the same for jim. And Joe. These will be the three kids
    of John. Finally, John, will be the father. I'll set John's data by using the setters, first name, and date of birth.
    I'll use set Kids, to pass an array of Persons with an array initializer, passing jane, jim and joe. Finally, I want
    to print John's data. This is a perfectly reasonable way to create a class, though it's somewhat tedious to create
    a new person. If I run this,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe

    I see that I have John, date of birth is May 5th, 1900, and his kids were Jane, Jim and Joe. The object, John, is
    definitely not immutable. I can use the setters to change any data on the object. I'll change john's name to jacob,
    for example. I'll also use the set kids method, passing it two new instances of persons, constructed with no data.
    And I'll print john out. Running that,

                Jacob, dob = 05/05/1900, kids = null, null

    you can see that john is no longer named John, and his kids are now nameless, and there's only two of them. Again,
    depending on what the case for your genealogy application is, this may be a valid way to design your class. Let's at
    least create two constructors, to make the job of creating persons a little easier. Back on the person class,
*/
//End-Part-4

/*
        Person jane = new Person();
        jane.setName("Jane");
        Person jim = new Person();
        jim.setName("Jim");
        Person joe = new Person();
        joe.setName("Joe");
        Person john = new Person();
        john.setName("John");
        john.setDob("05/05/1900");
        john.setKids(new Person[]{jane, jim, joe});
        System.out.println(john);

        john.setName("Jacob");
        john.setKids(new Person[]{new Person(), new Person()});
        System.out.println(john);
*/

//Part-6
/*
        My code no longer compiles. I'm going to comment all this code out. I'll now create my Persons, using the new
    constructors. For the first instance, I'll pass Jane, and I'll include a birth date, Jan. 1, 1930.  For Jim, maybe
    he's two years younger than Jane, so Feb.2, 1932. Next, Joe, and let's use March 3, 1934. To set up John, I want to
    create an array of persons, for his kids. I'll set up an array variable, johnsKids, using an initializer with jane,
    jim and joe. I'll create John, with the constructor that takes 3 arguments, John for name, birthday 5 5 1900, and
    johnsKids.  I'll print John out. If I run that code,

                John, dob = 05/05/1900, kids = Jane, Jim, doe

    I get the same as before. The code is a little more succinct, and easier to read. I can't change name or date of birth,
    but I can still set the kids. Let me try that.
*/
//End-Part-6

        Person jane = new Person("Jane", "01/01/1930");
        Person jim = new Person("Jim", "02/02/1932");
        Person joe = new Person("Joe", "03/03/1934");

        Person[] johnsKids = {jane, jim, joe};
        Person john = new Person("John", "05/05/1900", johnsKids);

        System.out.println(john);

//Part-7
/*
        I'll set the kids to a new in place array, initialized to a new Person, Ann, born 4 4 1930. And I'll print John
    again. Running that,

                John, dob = 05/05/1900, kids = Jane, Jim, doe
                John, dob = 05/05/1900, kids = Ann

    you can see John's kids have been completely changed. Let's adjust the kids a bit more.
*/
//End-Part-7

        john.setKids(new Person[]{new Person("Ann", "04/04/1930")});
        System.out.println(john);

//Part-8
/*
        First, I'll set up a local variable, and assign it the result of calling getKids on Person. I'll set this first
    kid to my jim instance, And print John's information. Running that,

                John, dob = 05/05/1900, kids = Jane, Jim, doe
                John, dob = 05/05/1900, kids = Ann
                John, dob = 05/05/1900, kids = Jim

    John's kid has changed from Ann to Jim. This could be a problem. You might not expect your client to change the kids
    data, outside of Person's operations, to do it.
*/
//End-Part-8

        Person[] kids = john.getKids();
        kids[0] = jim;
        System.out.println(john);

//Part-9
/*
        What if I assign my local variable, kids, to null? Does that have any effect on John's kids? Running this code
    has no effect on Jim's kids, so that's a good thing. This means reassigning the reference, or setting it to null,
    from the client or calling code, doesn't change kids.
*/
//End-Part-9

        kids = null;
        System.out.println(john);

//Part-10
/*
        I'll call set kids, and pass the kids variable, which we know is null. And print John again. Running this code,

                John, dob = 05/05/1900, kids = Jane, Jim, doe
                John, dob = 05/05/1900, kids = Ann
                John, dob = 05/05/1900, kids = Jim
                John, dob = 05/05/1900, kids = Jim
                John, dob = 05/05/1900, kids = n/a

    you can see I've managed to remove all John's kids I could only do it, by calling the method on Person, so that's at
    least some control going back to the Person class. This is a very common kind of class, where some parts of the class
    is mutable. Next, I'll create a Person record, in comparison.
*/
//End-Part-10

        john.setKids(kids);
        System.out.println(john);
    }
}