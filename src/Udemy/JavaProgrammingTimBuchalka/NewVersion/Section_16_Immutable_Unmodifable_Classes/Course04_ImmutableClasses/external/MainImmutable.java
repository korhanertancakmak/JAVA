package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.external;

import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.*;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.hacker.PersonOfInterest;
import CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course04_ImmutableClasses.external.domain.LivingPerson;

//Part-20
/*
        I'll replace PersonRecord with PersonImmutable. I have one error here, where I'm referencing johnCopy and trying
    to access kids, with the record accessor method. I actually want to change johnCopy to john here, and kids to get kids.
    I'll just print john. In fact, I don't need john copy for this exercise, so I'll remove the two statements

                        PersonRecord johnCopy = new PersonRecord("John", "05/05/1900");
                        System.out.println(johnCopy);

    so it's less confusing. I used this copy to show that, because my array had been initialized to 20 null records in
    the constructor, I could reassign any of those elements, but that's not applicable here. Running this code,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = Jane, Jim, Joe

    I need to make sure I'm running main on this MainImmutable class, you can see, that john's kids don't get changed by
    any of this code, once I initially constructed John with the kids, Joe, Jim and Jane. It didn't matter if I changed the
    variable johnsKids afterwards, or if I assigned a local variable to the result of get kids, and made changes to that.
    Using defensive copies in the constructor, and in the getter, has protected my array of kids on this class, from side
    effects. Now, do I have an immutable class? Well, of course, the answer to that is always, it depends. Let's now say,
    I've handed this class off to someone else, who wants to build a subclass, let's say a LivingPerson class. First,
    this developer will make two changes to our PersonImmutable class, without consulting us.
*/
//End-Part-20

public class MainImmutable {

    public static void main(String[] args) {

        PersonImmutable jane = new PersonImmutable("Jane", "01/01/1930");
        PersonImmutable jim = new PersonImmutable("Jim", "02/02/1932");
        PersonImmutable joe = new PersonImmutable("Joe", "03/03/1934");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("John", "05/05/1900", johnsKids);

        System.out.println(john);

        //PersonImmutable johnCopy = new PersonImmutable(john);

//Part-22
/*
        I'll try to set up a new variable, johnCopy, and instantiate that using the new constructor, passing john to it.
    I can't do this, since MainImmutable isn't in the same package, and it's not a subclass, nor will it be. I'll revert
    that last statement, removing it.Now, let's imagine this developer creates a subclass, called Living Person, and
    let's put this in a domain package.
*/
//End-Part-22

        PersonImmutable[] kids = john.getKids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);

        johnsKids[0] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);

//Part-25
/*
        I'll create a living person, well, I'll just say John is an extraordinary person, and he's still living. I'll
    create a variable called john living and assign that a new instance of a LivingPerson, passing john's names and kids
    to that. And I'll print john Living out. If I run this,

                John, dob = null, kids = Jane, Jim, Joe, , , , , , ,

    the goal's been achieved, I have a living person, and I'm not including birth date in the output. You can also see
    the extra commas there, which indicates there are null entries, waiting for new kids. Let's say I want to add a child
    to my living person as well, in the case of a new child being born or adopted. I'll create a new method on Living person,
*/
//End-Part-25

        LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
        System.out.println(johnLiving);

        LivingPerson anne = new LivingPerson("Ann", null);
        johnLiving.addKid(anne);
        System.out.println(johnLiving);

//Part-27
/*
        First, I'll set up a new Living Person, Ann, passing null as her kids. Next, I'll call the add kid method on john
    Living and pass anne. And I'll print john living again. Running that,

                John, dob = null, kids = Jane, Jim, Joe, Ann, , , , , ,

    you can see the last statement, and it now includes Ann as the 4th element. This code works as designed, and all is
    well. But is it really? Let's now say another developer comes along, maybe these classes went out as a library or
    something. Anyway, this developer is going to create a new subclass, extending Person Immutable. I'll create a new
    class, Person Of Interest in a package called hacker.
*/
//End-Part-27

        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);

//Part-29
/*
        First, I'll create a Person of Interest, calling that john copy, and assign that to a new instance of Person of
    interest, and print that out. Running that,

                    John, dob = 05/05/1900, kids = Jane, Jim, Joe

    you can see the last statement, which has all the same data as John, the original, as you might expect.
*/
//End-Part-29

        kids = johnCopy.getKids();
        kids[1] = anne;
        System.out.println(johnCopy);
        System.out.println(john);

//Part-30
/*
        Now I'll get the kids from this copy, and assign that to a variable I set up earlier, kids. I'll assign this variable's
    first element to be anne. I'll print the johnCopy again. And for good measure, I'll print john now too. Running that
    code,

                John, dob = 05/05/1900, kids = Jane, Jim, Joe
                John, dob = 05/05/1900, kids = Jane, Ann, Joe
                John, dob = 05/05/1900, kids = Jane, Ann, Joe

    you can see, something bad has happened. Ann is listed as the second child of both John copy and John, in the last
    two output statements. This means that both PersonImmutable and PersonOfInterest are mutable. Maybe we don't care
    about PersonOfInterest, but for PersonImmutable, we spent some time trying to design it, so it wouldn't be. Our new
    developer made two pretty big mistakes. First, opening a field up for access, even for subclasses, needs some extra
    thought, especially when you're dealing with reference types to mutable data. What would have happened, if the new
    copy constructor, simply chained to another constructor? Let's go change that, in the PersonImmutable class.
*/
//End-Part-30
    }
}
