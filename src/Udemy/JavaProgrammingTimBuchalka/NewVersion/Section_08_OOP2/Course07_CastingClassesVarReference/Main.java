package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_OOP2.Course07_CastingClassesVarReference;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                           Casting with Classes, and using Object and var references

        Last course, we showed you an example of polymorphism in action. There was 2 lines in that code, they're really
    important for you to understand. These are the lines:
End-Part-1
*/
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();
/*
Part-2
        Now let's talk about these 2 lines of code, because there are several things happening here. First, we're creating
    a variable with the Movie type, and we've called it movie. We then assigned the result of this static method, Movie.getMovie,
    to that variable. We know we could've done this several other ways. Let's use that method and try to assign it to an
    Adventure variable, since we know that we'll really get an Adventure Movie, if we call it this way.
End-Part-2
*/
        //Adventure jaws = Movie.getMovie("A", "Jaws");
/*
Part-3
        Now, this code doesn't compile. IntelliJ tells us that the "Required Type is Adventure", and "Provided is Movie."
    Why is this a problem? First of all, the compiler isn't going to run the code to figure out what will really happen.
    It has to be satisfied with making assumptions about the code, based on how we write the code. In this case, we declared
    that the method, getMovie, is going to return a Movie Class. We didn't say it was going to return an instance of the
    Adventure Class. The compiler asks the question, can every kind of Movie (which is the return type of this method),
    be called an Adventure, meaning, can every kind of Movie be assigned to an Adventure variable? And here, the answer
    is "no". The Adventure reference, jaws, would not be able to handle a Comedy movie, if that got returned, for example.
    That's because we can't say a Comedy is an Adventure. But we absolutely know that when we pass the letter A as the type,
    that we'll get an Adventure movie back. But the compiler can't figure that out, without executing code, and it's not
    going to do that. Now, we can give the compiler more detailed instructions, to get around this issue. One way to do
    is casting. Like casting with primitives, we can cast instances, so let's do that here.
End-Part-3
*/
        //Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        //jaws.watchMovie();
/*
Part-4
        We'll cast the result of the Movie.getMovie, to an Adventure type. And we'll also add the call to watchMovie here.
    Ok, now this code compiles, and we can give it a go, and run it.

        Jaws is a Adventure film
        .. Pleasant Scene
        .. Scary Music
        .. Something Bad Happens
        Jaws is a Adventure film
        .. Pleasant Scene
        .. Scary Music
        .. Something Bad Happens

        And we can see that it runs just like it did, in the 2 lines above these. But what happens if we make a mistake,
    and we pass a C as the type?
End-Part-4
*/
        //Adventure jaws = (Adventure) Movie.getMovie("C", "Jaws");
        //jaws.watchMovie();
/*
Part-5
        We know that doing that, should give us a comedy movie. But let's not change anything else, and just see what happens.
    We've told the compiler, with this cast, that we're smarter than it, and to just leave this line alone. So this code
    compiles. But if we run it?

        Exception in thread "main" java.lang.ClassCastException: class CoursesFrom032To065.Course058.Comedy cannot be cast to class CoursesFrom032To065.Course058.
        Adventure (CoursesFrom032To065.Course058.Comedy and CoursesFrom032To065.Course058.Adventure are in unnamed module of loader 'app')
	at CoursesFrom032To065.Course058.Main.main(Description.txt:54)

        We get a special kind of exception, a ClassCastException. And the message is pretty informative, that we got a
    Comedy object, when an Adventure object was expected. This is a bad situation, to have your code compile, but then get
    exceptions at runtime. Ok, maybe you're asking, when can you assign an object of one type, to a reference, with a different
    type? Well, first of all, you can assign any object to a reference that is of type Object. We'll try that next. First,
    we'll revert that last change.
End-Part-5
*/
        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        //comedy.watchMovie();
        //comedy.watchComedy();
/*
Part-6
        And then we'll create a comedy instance, but assign it to an Object reference. Here, now we've created a variable
    called comedy, but we've said its type is Object. And this code doesn't compile, but not on the first line where we're
    doing the assignment, but on the second statement. Can you figure out why? The compiler won't use the method return
    type, to figure out what comedy really is, after you make this assignment. It just assumes it's an Object, and this
    variable only has access to Object's functionality. This is because, at any time in the code, an instance of Object
    itself could be assigned to this variable. And the code has to work for whatever object gets assigned, to this variable.
    This means that when you try to call a Movie method on this object reference, you'll get an error. This is because
    the compiler can't locate that method, watchMovie, on the Object class. Let's pause here a minute, to add a method,
    on each of the subclasses, and we'll make each method unique to the class.

        Going back to the Adventure class, we'll add a simple method, that's only on that class.

        public void watchAdventure() {
            System.out.println("Watching an Adventure!");
        }

    And let's copy that method and paste it in the Comedy class. And name it for Comedy. Same, we do it for the ScienceFiction
    Class as well.

        And if we try to run watchComedy on that Object variable, we have the same problem that we had with watchMovie.
    It doesn't see that method watchComedy on the Object class, and it's not going to compile. Let's revert that back for
    the moment. Now, you could assign every instance to an Object reference like this, but you wouldn't be able to do much
    with them, without casting them to other typed references. In other words, to run watchMovie on comedy (which has the
    type Object), we'd have to cast it to a Movie. And we can do that. We'll cast the object reference to a movie reference,
    and then we'll execute watchMovie on that new reference variable.
End-Part-6
*/
        //Movie comedyMovie = (Movie) comedy;
        //comedyMovie.watchComedy();
/*
Part-7
        What I want you to see by this, is that using references that are too generic, like Object, means you'll be doing
    a lot of casting. But even now, since we cast to a Movie, check out what happens, if I want to execute one of the methods
    we just added, watchComedy. This doesn't compile either. For the same reason we couldn't execute watchMovie on an
    Object reference, we can't run watchComedy on just a Movie reference. The compiler will only look at the reference type,
    to determine if that method is on that type, and watchComedy, is not on the Movie class. In this case, we'd need to
    actually cast to a more specific type, Comedy. And let's do that.
End-Part-7
*/
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();
/*
Part-8
        And now all of that works, so that's good. For good measure, let's try one other thing.
End-Part-8
*/
        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();
/*
Part-9
        This code compiles, but here is something new. We haven't used this before, you're probably asking, what is this
    "var", word mean? Well, "var is a special contextual keyword in Java, that lets our code take advantage of Local
    Variable Type Inference. By using "var" as the type, we're telling Java to figure out the compile-time type for us.
    Since the Movie class was declared as the return type, of the static method getMovie, then Java can infer, that the
    type of this variable, Airplane, should be a Movie. You can see that in the hints, if you've configured IntelliJ to
    show them. Now why didn't it infer that it was a Comedy Class? Nothing about the signature of the method, indicated
    that a Comedy instance might be returned, from the method. Only that a Movie would be.

        Ok, let's try another example of using type inference, this time by just assigning a new instance:
End-Part-9
*/
        var plane = new Comedy("Airplane");
        plane.watchComedy();
/*
Part-10
        In this case, the compiler had an easier job, to infer the type, because we're simply assigning a new instance of
    Comedy to this variable, plane. But you can see now, that using this plane variable, we can execute methods, specific
    to Comedy, without compile time errors. One of the benefits of Local Variable Type Inference(LVTI) is to help the
    readability of the code, and to reduce boilerplate code.

  - It can't be used in field declarations on a class.
  - It can't be used in method signatures, either as a parameter type or a return type.
  - It can't be used without an assignment, because the type can't be inferred in that case.
  - It can't be assigned a null literal, again because a type can't be inferred in that case.

        Are you still confused about the difference between run-time and compile-time typing? You can think of the compile
    time type as the declared type. This type is declared either as a variable reference, or a method return type, or a
    method parameter, for example. In the case of LVTI, we don't declare a type for the compiled reference type, it gets
    inferred, but the bytecode is the same, as if we had declared it. In many cases, the compile time type is the declared
    type to the left of the assignment operator. What is returned on the right side of the assignment operator, from
    whatever expression or method is executed, sometimes can only be determined at runtime, when the code is executing
    conditionally, through the statements in the code. You can assign a runtime instance, to a different compile time
    type, only if certain rules are followed. In this course, up to now, we've looked at only one rule that applies, and
    that's the inheritance rule. We can assign an instance to a variable of the same type, or a parent type, including
    java.lang.Object, the ultimate base class.

        Why are run-time types different from compile-time types? Because of polymorphism. Polymorphism lets us write code
    once, in a more generic fashion, like the code we started this lecture with. We saw that those 2 lines of code, using
    single compile time type of Movie, actually supported 4 different runtime types. Each type was able to execute behavior
    unique to the class.
End-Part-10
*/
    }
}
