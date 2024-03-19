package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course13_SealedClasses.sealed;

//Part-3
/*
        I'll add the sealed and abstract modifiers to this class. And right away, I'm in a position where it's expected,
    that I'll know what my subclasses are, and I need to declare them for this sealed class. Before I add any subclasses
    in this package, I want to first include a nested class. I'll declare a class called Kid here, and I'll have that
    extend the outer class, the Special Abstract class. I get an error on the Kid declaration, with the message that kid
    has to be declared final, sealed or non-sealed, so I'll make that final here. Now this code compiles, but why is that?
    I haven't included a permits clause, so why is this ok? Well, there's one exception, where we don't have to declare
    a permits clause, and that's if all your subclasses are nested classes. You can optionally still include it. I'll add
    it here, and specify Kid in the clause. But that's giving me an error, Cannot resolve symbol, Kid. As it turns out,
    when you use a subclass in a permits clause, you have to use the qualifying name, meaning you have to qualify it with
    the outer class name, which I'll do now. I'll reference Kid as SpecialAbstractClass.kid. Now this compiles, and all
    is well. Next, I'll create the three different flavors of subclasses, external to this class next. First I'll create
    a FinalKid class in the same package, so in sealed. I'll include the final modifier, and have it extend the Special
    Abstract class. For now that's not going to compile, until I add this class to the permits clause on SpecialAbstractClass,
    which I'll do in just a minute. I'll create the other two classes first. Next will be SealedKid. I'll add the sealed
    modifier on this one, and have it extend Special Abstract class. The last one, will be calledNonSealedKid. I'll add
    the non-sealed modifier, and again extend theSpecialAbstractClass. Ok, so I'll now go add these three, to the permits
    clause, on theSpecialAbstractClass. If I hover over that error on the class name, IntelliJ gives me the options to
    add missing subclasses to the permits clause. I'll add a new line after the comma here, so you can see the entire
    declaration. Notice that I have an error on SealedKid, because it's sealed. I have to specify a permits clause on
    that class. I'll go to the SealedKid class.
*/
//End-Part-3

public sealed abstract class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    final class Kid extends SpecialAbstractClass {

    }

}

//Part-5
/*
        Let's see what happens if I now try to remove this class's nested class, from the permits clause. I'll do that
    now. Actually, I can't do that now. Omitting the permits clause or a class from the clause, only works if you don't
    have other non-nested classes extending this sealed class. Ok, these rules seem complicated, but the main thing to
    remember is, there's a circular relationship between a sealed class, and its subclasses. A sealed class has to have
    knowledge of its subclasses, which an unsealed class doesn't. An unsealed class can have many unknown subclasses.
    I'll revert that last change. Maybe you noticed that I didn't have to declare Grand kid in this clause. Only direct
    subclasses are required to be named, in the permits clause. Ok, so the last thing it would be nice to understand about
    sealed classes, is this business about allowing a non-sealed subclass. Let's create one more class, FreeGrandKid.
    I'll have this extend the NonSealedKid. Now, this is allowed. That seems kind of weird doesn't it? Let's see if we
    can figure out why Java allowed this. First of all, there's probably always an exception to every rule, and often
    these exceptions are discovered very late. Having the ability to change a subclass to non-sealed, is a way to control
    a branch of your hierarchy. Remember the subclass has to either be part of your own package or module. This means,
    that when you permit a non-sealed class, you're making a very conscious decision, to allow extensibility for that
    specific branch. When would you do this? I guess I could come up with a couple of use cases if I tried hard enough.
    Maybe you might allow a trusted vendor, access to this non-sealed branch This would enable them to include value-adds,
    on top of your base set of widgets. You could provide the non-sealed class. Before I end this lecture, I need to talk
    about sealed interfaces. Like a class, you can seal an interface, specifying who gets to implement your interface.
    I'll create a sealed interface, called SealedInterface, in the sealed package.
*/
//End-Part-5
