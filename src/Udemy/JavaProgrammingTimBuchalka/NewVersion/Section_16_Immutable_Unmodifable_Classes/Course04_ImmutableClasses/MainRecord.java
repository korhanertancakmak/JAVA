package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses;

public class MainRecord {

    public static void main(String[] args) {

        PersonRecord jane = new PersonRecord("Jane", "01/01/1930");
        PersonRecord jim = new PersonRecord("Jim", "02/02/1932");
        PersonRecord joe = new PersonRecord("Joe", "03/03/1934");

        PersonRecord[] johnsKids = {jane, jim, joe};
        PersonRecord john = new PersonRecord("John", "05/05/1900", johnsKids);

        System.out.println(john);

//Part-12
/*
        I want to copy the code in the Main class, main method, and paste it in Main Record. I want to replace all references
    to Person with PersonRecord. Running this code,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe

    I get the same output I did originally when using the Person class.
*/
//End-Part-12

        PersonRecord johnCopy = new PersonRecord("John", "05/05/1900");
        System.out.println(johnCopy);

//Part-13
/*
        I'll create another john variable, johnCopy, and assign that a new PersonRecord, with the same name and date of
    birth as the first John, but no kids. Running that,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,

    you can see it looks a little weird because I'm printing 19 commas and no kids names, because of the way I have it
    coded. I initialized kids to be an array of 20 Person records, and each array element is initialized to a null reference.
    I can't set the kids on this record, but I can change them, which is why I set this up this way. Let me do that.
*/
//End-Part-13

        PersonRecord[] kids = johnCopy.kids();
        kids[0] = jim;
        kids[1] = new PersonRecord("Ann", "04/04/1936");
        System.out.println(johnCopy);

//Part-14
/*
        I'll create a new local variable, a person record array called kids, and assign that johnCopy's kids. I'll set
    the first element to jim, and the second to a new person record, ann. I'll print john copy. And now, running this code,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
                John, dob = 05/05/1900, kids = Jim, Ann, , , , , , , , , , , , , , , , , , ,

    you can see I can set and change the kids in the kids array on my record, meaning these instances are mutable. For
    this reason, you can't assume, just because you're using a record, or setting up a record, that the record is immutable.
    If the fields were all immutable types, yes, but if you're using arrays or collections, or mutable types, then you
    can't use a record, and prevent side effects, without implementing some defensive measures. A record satisfies several
    of the requirements for an immutable class design. It uses private final instance fields, it has a constructor to set
    the data, and it doesn't have any setters. What's missing is, it's not creating defensive copies. Let's add this, to
    the Person record.
*/
//End-Part-14

        johnsKids[0] = new PersonRecord("Ann", "04/04/1936");
        System.out.println(john);

//Part-16
/*
        Consider this minor change, where I'll change my local variable johnsKids, element 0 to a new Person, say Ann,
    born April 4, 1936. This code happens long after I created John, with his kids. If I run this code,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
                John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
                John, dob = 05/05/1900, kids = Ann, Jim, Joe

    you can see I'm able to change John's data, his kids array, by changing the array variable, I used to construct the
    record. This means I need a constructor that will create a copy of the array passed, before I assign it. I won't do
    this here, for this record, but I wanted you to see, there are several reasons why a record might not be truly immutable.
    Of course, there are many cases where you want to support some mutability, but this should be design, not by accident.
    If your class must be mutable, you should still use some of these techniques to minimize mutability!

        So we looked at a pretty normal PoeJoe first, with getters and setters, and a no arguments constructor. I compared
    that to a record, which has a lot of built in support for the design of immutable classes. A record, though, is still
    not perfect, because the getters, if not altered, will return references to mutable objects. In addition, I showed
    you how I can pass a reference to the constructor, and then change data, using that reference, afterwards, producing
    possibly unwanted side effects. I'll now create a truly immutable class. You might still be asking, don't I want the
    ability to change the genealogy data? Maybe, but let's say our use case is simply to print the data we get, or process
    the data for some statistical program. We're not in the business of writing a genealogy program, just a web page that
    will display the information or statistics. If the data's mutable, it might effect our results badly. I'm going to
    copy the Person class, the PoeJoe, and create a copy from that class, named PersonImmutable.
*/
//End-Part-16
    }
}
