package CourseCodes.NewSections.Section_07_OOP1.Course01_AccessModifiers;

/*
Course-31

                                        Object-Oriented Programming (PART-I)

        In this part of OOP, you're going to learn about the fundamentals of object oriented programming, starting first
    with the structures we'll be using, specifically Classes, Objects, and Constructors. Along with these structures, we'll
    be learning Inheritance, Encapsulation, Polymorphism, and Composition, which are all fundamentals features of OOP.

        Part-I covers only Inheritance, Part-II covers the others.

                                        What is Object Oriented Programming?

        Object oriented programming is a way to model real world objects, as software objects, which contain both data and
    code. OOP is a common acronym for Object Oriented Programming. And in particular, we'll be talking about classes, which
    is a fundamental component of OOP in Java, and other languages for that matter. It's sometimes called Class-based
    programming. Class-based programming starts with classes, which become the blueprints for objects.

                                        Real World Object Exercise

        Just have a look around, in the area you're siting in right now. And if you do that, you'll find that there's many
    examples of real world objects. For example, I'm sitting here and I can see:

      - A computer
      - a keyboard
      - a microphone
      - shelves on the wall
      - a door

    All of these are examples of real world objects. Real world objects have 2 major components:

      - state
      - behavior

    State, in terms of a computer object, might be:

      - The amount of RAM it has
      - The operating system it's running
      - The hard drive size
      - The size of the monitor

    These are characteristics about the item, that can describe it. We could also describe animate objects, like people
    or animals, or even insects, like an ant. For an ant, the state might be:

      - The age
      - The number of legs
      - The conscious state
      - whether the ant is sleeping or is awake

        In addition to state, objects may also have behavior, or actions that can be performed by the objects, or upon the
    object. Behavior, for a computer, might be things like:

      - Booting up
      - Shutting down
      - Beeping, or outputting some form of sound
      - Drawing something on the screen, and so on

    All of these could be described as behaviors for a computer. For an ant, behavior might be:

      - Eating
      - Drinking
      - Fighting
      - Carrying food, those types of things

    So modelling real world objects as software objects, is a fundamental part of Object Oriented Programming. Now, a
    software object stores its state in fields, which can also be called variables, or attributes. And Objects expose their
    behavior with methods, which we've talked about before. So where does a class fit in? Well, think of a class as a
    template, or a blueprint for creating objects. Let's take another look at the class.

                                        The Class as the blueprint

        The class describes the data (fields), and the behavior (methods), that are relevant to the real world object we
    want to describe. These are called class members : Instance Members and Static Members. A class member can be a field,
    or a method, or some other type of dependent element.

        If a field is static, there is only one copy in memory, and this value is associated with the class, or template,
    itself. A static method can't be dependent on any one object's state, so it can't reference any instance members.

        If a field is not static, it's called an instance field, and each object may have a different value stored for
    this field. Any method that operates on instance fields, needs to be non-static.

        So these class or member fields, can be thought of as variables, but it's more common to call them fields or
    attributes. Now, we'll be looking at instance fields to describe our objects. Later, we'll be describing static members
    in greater detail.

                            public class CoursesFrom007To031.Course031 {
                                public static void main(String[] args) {

                                }
                            }

        You can actually see "public class CoursesFrom007To031.Course031", that's actually a statement which describes a
    class in Java. So, why do we need to bother about classes? Or what benefit do classes give us in our everyday programming?

        Well, think back to the basic data types that we've worked on, the primitive data types that we've explored so far,
    such as int, short, and those types of things. They're all basic data types, but they're fairly limiting. There's only
    so much you can do with them. So, a case could be made here, that a class could be thought of as a powerful user-defined
    data type. That's not really correct in the true meaning but it gives you an idea of what classes are. They really
    enable you to have sort of a powerful user-defined type, like a regular data type on steroids if you will. So, to take
    this a little bit further, let's create our first real class.

        Everytime we create a new Project, we've been creating a new class, usually one called CourseXXX, with capital C,
    and we generally add a method, called main, which i've already done below. When you're creating classes in Java, the
    first letter should be an upper case letter. We haven't talked about how classes are organized, so let's just look at
    that briefly, before we talk about access.

                                            Organizing classes

        Classes can be organized into logical groupings, which are called packages. You declare a package name in the class
    using the package statement. If you don't declare a package, the class implicitly belongs to the default package. The
    default package is all we've used so far, because we haven't yet introduced the package statement. We're not going to
    get too deep into packages just yet, there's more on them later. But you do need to understand that classes are grouped
    into packages, to understand access modifiers.

                                            Access modifiers for the class

        A class is said to be a top-level class, if it is defined in the source code file, and not enclosed in the code
    block of another class, type, or method. A top-level class has only 2 valid access modifier options: public or none.

        public : public means any other class in any package can access this class.
   no-modifier : When the modifier is omitted, this has special meaning, called package access, meaning the class is accessible
               only to classes in the same package.

    If there is no modifier specified at all, Java by default implicitly allows package-private access. This means that
    classes grouped into the same package, can access the class.

        When we're designing our class, there are some things we want the public to know, and some things that aren't
    necessary for the public to know. We can have a public interface. This is only the information the outside world needs
    to know, to use our class. But we'll also have a non-public interface. This is information we may want to share but
    not always, and not with everyone. We may need to share some information with our own company and other departments,
    while other data might need to be shared with our manufacturers and dealers, but not with the public. Java gives us
    the ability to have this kind of control, by specifying different access modifiers for each member in a class.

                                            Access modifiers for class members

        An access modifier at the member level, allows granular control over class members. The valid access modifiers are
    shown in this table from the least restrictive(public), to the most restrictive(private).

        public : public means any other class in any package can access this class.
     protected : allows classes in the same package, and any subclasses in other packages, to have access to the member.
   no-modifier : When the modifier is omitted, this has special meaning, called package access, meaning the class is accessible
               only to classes in the same package.
       private : means that no other class can access this member.

       Here, public is still an option for class members, like it was for the class, and this means there is unrestricted
    access to the member. We also still have no modifier, which means package, or package-private by default, so that any
    class in the same package, can access this member. We can also use private, which is basically the opposite of public,
    and that's where no code, outside the class, can use this field or method. And finally, there's the protected modifier.
    This one also allows package access, but it also permits subclasses to access this member.

        As a general rule, all your fields should be private, unlike the class, where we'll usually use public. So why would
    we want to make all the fields on a class private? Doesn't this mean that nobody can access them? This practice has a
    name, encapsulation, and it's a key fundamental rule of Object Oriented Programming.

                                                    Encapsulation

        Encapsulation in OOP usually has 2 meanings. One is the bundling of behavior and attributes on a single object. The
    other is the practice of hiding fields, and some methods, from public access.

        In general, when we talk about encapsulation, we're taking about information hiding, or hiding the internal workings
    of a particular object. When we make our attributes private, we can then create methods to access the data, each with
    different degrees of access allowed, as needed.


*/
public class Main {

    // 5 private fields for a car class(we didn't change the name of class)
    private String make;
    private String model;
    private String color;
    private int doors;
    private boolean convertible;
    // These are the fields, because they're defined in the class's code block, or the body of the class, and not in a method.
    // When we create an object from this class, then the values we assign to these fields represent the state of the object.
    // Unlike local variables, class variables should have some type of access modifier declared for it.
    // If you don't declare one, Java declares the default one (package private), implicitly.
    // So, here, we've set the access modifier to be private in all cases, which we've said help us encapsulate this class.
    // We'll want to control access to these fields, and this starts by making them private.
    // Later we'll add the method to access them.
    // The other thing you might have noticed is that we're not actually assigning any values yet.
    // This is because, at this point, we don't know what these values might be, and they'll likely be different for each
    // instances anyway. If we were creating a real application, we'd likely have a lot more fields, but we'll keep this
    // simple to start with.
    // Let's add a method now that will print out this information about the car object.
    // We'll call this method describeCar and make the method public. This method is not static, because we're accessing
    // instance fields on the class. Methdos, unlike fields, will often be public, because we want to give users a way
    // to interact with the object.

    public void describeCar() {

        System.out.println(doors + "-Door " +
                color + " " +
                make + " " +
                model + " " +
                (convertible ? "Convertible" : ""));
    }

    // So we've created our first real template class, called Car, and we've set up some attributes on car. This feels like
    // a good place to end this course.
}
