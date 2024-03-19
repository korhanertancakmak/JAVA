package CourseCodes.NewSections.Section_08_OOP2.Course08_InstanceOfOperator;

public class Main {
    public static void main(String[] args) {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();
        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();
        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();
        var plane = new Comedy("Airplane");
        plane.watchComedy();
/*
Part-1
                           Testing the run-time type using the instanceOf operator

        We've said that you can always assign an expression to a type, without casting, if you're always assigning it to
    a parent class, or a base class type. In this course, we're going to show you how to test what the runtime type of an
    object really is. And how do we test what the runtime type, of a variable really is at runtime, if the declared type
    is something else? We can test to see what type the actual object is, at runtime, in several different ways. First,
    we can use an if statement to see what the class name of the object, coming back from that method is, when this code
    is running. Getting back to the main method, we'll add another call to the Movie.getMovie method. We'll assign that
    to an Object reference, called unknownObject.
End-Part-1
*/
        Object unkownObject = Movie.getMovie("S", "Star Wars");
        if (unkownObject.getClass().getSimpleName() == "Comedy") {
            Comedy c = (Comedy) unkownObject;
            c.watchComedy();
        } else if (unkownObject instanceof Adventure) {
            ((Adventure) unkownObject).watchAdventure();
        } else if (unkownObject instanceof ScienceFiction syfy) {
            syfy.watchScienceFiction();
        }

/*
Part-2
        Here, in this if statement, we're using a method called getClass. We've used this getClass method before, in the
    Movie class's, watchMovie method. But in that case, we used it with the keyword "this", whereas here, we're simply
    calling it, on the local variable reference, unknownObject. This method is available to any instance, because it's a
    method on Object. And getSimpleName, is a method that returns the class name of our instance here. This means we're
    testing if the object, coming back from that factory method, has a class name that's Comedy. And if it does, we can
    cast the object to Comedy, and assign it to a Comedy variable. Then we can call any method on Comedy. The reason to
    cast to a Comedy class here is, we want to execute the method that's specific to Comedy, watchComedy. Without casting
    to a Comedy class, we couldn't execute that method. And you'll remember, that we didn't have to cast air-plane to Comedy
    class, on "var airplane = Movie.getMovie("C", "Airplane");", to run watchMovie, and what I mean is, running the Comedy
    class's own version of watchMovie. This is because of polymorphism, which works in this case, through the use of
    overridden methods. Without polymorphism, you'd be in this world of having to test, at runtime, what your object really
    is every time, to execute code. This code shows you 1 way to test for the runtime type, but it's not the best way.

        Let's look at another way, testing if the object coming back might be an Adventure type. We'll insert a couple of
    statements, before that last closing bracket, for the else-if statement: instanceOf. Here is something new, and this
    is the instanceof operator. The instanceof operator, lets you test the type of an object or instance. The reference
    variable you are testing, is the left operand. The type you are testing for, is the right operand. It's important to
    see that Adventure is not in quotes, meaning we're not testing the type name, but the actual type. This operator returns
    true, if unknownObject is an instance of Adventure. In our code here, we're testing if unknownObject is really an Adventure
    object, and then if it is, if that's true, we want to cast unknownObject to Adventure, and call watchAdventure, a method
    that's only on the Adventure class. And this next statement is different from anything I've shown you before, but perfectly
    valid. This set of outer parentheses, is the result of the Adventure type, "(Adventure) unknownObject)", and we can
    chain a method directly on that, as we do here. In other words, we don't have to assign the result of the cast, to
    a local variable.

        Ok, now lastly, let's look at 1 more way to do this: "else if (unkownObject instanceof ScienceFiction syfy)". Here,
    we can see we're using a slightly different version of the instanceof operator. This is called pattern matching support,
    for the instanceof operator. If the JVM can identify that the object matches the type, it can extract data from the
    object, without casting. For this operator, the object can be assigned to a binding variable, which here is called
    "syfy". The variable "syfy" (if the instanceof method returns true) is already typed as a ScienceFiction variable. You
    can see in our code, "syfy.watchScienceFiction();", we don't have to create the variable in the block statement, and
    we don't have to cast it. Let's run this code through a couple of tests. First we'll run this code as it is.
        We're interested in the last statement of the output. And that gives us the last output statement, "Watching an
    Adventure!". Now, let's test a ScienceFiction movie, making the type an S, and the title Star-Wars. And that gives
    us the output: "Watching a Science Fiction Thriller!". Ok, those are a few ways to test the runtime type of an object
    in our code. You can see that this newest feature in Java, makes a job a lot easier.

End-Part-2
*/
    }
}
