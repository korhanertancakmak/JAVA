package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course06_Polymorhism;

/*
Course-57

                                                Polymorphism

        Simply stated, polymorphism, means many forms. Well, how does this apply to code? Polymorphism lets us write to
    call a method, but at runtime, this method's behavior can be different, for different objects. This means the behavior
    that occurs, while the program is executing, depends on the runtime type of the object. And the runtime type, might
    be different from the declared type in the code. The declared type has to have some kind of relationship to the runtime
    type, and inheritance is one way to establish this relationship. There are other ways, but in this video, we'll talk
    about how to use inheritance, to support polymorphism.

        We've given you a taste of this in the inheritance courses, but this time we're going to look at polymorphism
    specifically.
                                         __________________________
                                         |Movie =>                |
                                         |        title: String   |
                                         |------------------------|
                                         |        watchMovie()    |
                                         |________________________|
                      _______________________________↑____________________________________
                      |                              |                                   |
            __________|_________           ____________________            ___________________________
            |Adventure =>      |           |Comedy =>         |            |ScienceFiction =>         |
            |------------------|           |------------------|            |--------------------------|
            |__________________|           |__________________|            |__________________________|

    This time, we're going to look at a polymorphism example, using movies. We'll have a base class of Movie, which has
    the title of the movie. And Movie will have 1 method, watchMovie. We'll have 3 subclasses, each a different kind of
    movie. We'll have an Adventure film, a Comedy, and a ScienceFiction movie. These are the different categories, so we'll
    use these as the subclasses. All of these will override, and implement unique behavior, for the watchMovie method.

        After creating Movie class with its own constructor and 1 method, watchMovie:

                        public class Movie {
                            private String title;
                            public Movie(String title) {
                                this.title = title;
                            }
                            public void watchMovie() {
                                String instanceType = this.getClass().getSimpleName();
                                System.out.println(title + "is a " + instanceType + " film");
                            }
                        }

    Now, here on the first line of this method is something new. You've seen the use of the keyword "this" before, which
    refers to the current instance, and now we're calling a method on that called "getClass()".

        The method getClass() is on Java linked object, which we've talked about. This method returns class type information
    about the runtime instance on which this method is executing. And from that we can get the name of the class using
    the getSimpleName method. And here again, we're just using method chaining for convenience. This prints out the class
    which will be moving if we execute this method on a runtime movie object. But when we implement the subclasses and run
    this method, the runtime object could be an instance of one of those classes, The Adventure class, for example. This
    will hopefully make more sense when we run this code. Next in this code, we print out the title of the movie and the
    top of the movie runtime instance. And that's the movie class.

        Let's go to the main method, and test out the watchMovie method:

                        Movie theMovie = new Movie("Star Wars");
                        theMovie.watchMovie();

    And running this,

                        Star Wars is a Movie film

    We got this output. And that's because the runtime instance of the movie variable is the "Movie" class. It's the object
    we created here in the main method. And we did new Movie, which means it's really an instance of a Movie. Here we've
    created the object using the "new Movie" statement and past it the title of "Star Wars". And then we assigned our movie
    instance to a "movie" reference variable, and here we just called it the "Movie". Ok, so no surprises here, I hope.

        What we'll do next is add some subclasses of movies that represent different genres or classifications of Movies.
    You can think of these as the way a streaming company would group different movies. And we'll start with the Adventure
    movie type. We'll add this class to the Movie.java source file, and we won't give it an access modifier, meaning it
    has package or default access. But we're going to have it extend Movie:

                        class Adventure extends Movie {
                        }

    This doesn't compile yet, because we need to add a constructor. Adding it:

                        public Adventure(String title) {
                            super(title);
                        }

    And we generated constructor which uses movies field, title as a parameter, and makes a call to "super", using that
    argument. Now we got the constructor and an Adventure movie will get created with just the title of the movie.

        Next, we want to implement or override the watch movie method. When you're overriding a method, it's a pretty good
    idea to start with IntelliJ's override generation tool. This ensures that your method signature is right, so let's do
    that for this class.

                        @Override
                        public void watchMovie() {
                            super.watchMovie();
                        }

    And that gives us the IntelliJ's default generated code, which includes that Override annotation, which we've seen
    before. And this method simply calls the method on the Movie class, which is the super or base class for Adventure.
    We want to do that, but we want to include extra functionality that's specific to the adventure class, like some of
    the major plot stages of an Adventure film, for example. And we'll just use the printf method as well as the repeat
    method on String to print out the plot stages. 3 stages on 3 lines:

                        System.out.printf(".. %s%n".repeat(3),
                                "Pleasant Scene",
                                "Scary Music",
                                "Something Bad Happens");

    Let's talk about this code a minute, because here we're using the format specifiers "%s" and "%n". The format specifier
    "%s" is used to replace any String which is not as commonly used as others, but it will work well here. And we've set
    before that "%n", puts it a new line there. Now, this string gets repeated 3 times with this repeat method before the
    formatting takes place. This means that all these stage plots gets printed each on its own line.

        Now let's go back to the main method and test the make Movie method on an Adventure object this time. And what we
    can do here is simply replace this "new Movie" statement with "new Adventure" instead. We don't have to change the type
    of "theMovie" variable. It can stay as Movie.

                        Movie theMovie = new Adventure("Star Wars");
                        theMovie.watchMovie();

    And this is because adventure is really a type of Movie, a subclass. And inheritance lets us say "Adventure is a Movie,
    and it's ok, we can do this, we can assign an Adventure object to a Movie variable". This code compiles and we can run
    it.

                        Star Wars is a Adventure film
                        .. Pleasant Scene
                        .. Scary Music
                        .. Something Bad Happens

    And look what happened. The code ran, the watchMovie method on the Adventure class. That's because at runtime, the method
    that gets run, is determined by the Java Virtual machine (JVM) based on the runtime object and not this variable type.
    And that's our first test of polymorphism in this code. We've declared a variable of type Movie and assigned it an
    object that's really an Adventure type of movie. And when we called watchMovie on that, the behavior was the Adventure
    movies behavior. It wasn't just the base class behavior. Now, the method that's on the Adventure class, first calls
    Movie's method, which is why we see that first statement, "Star Wars is a Adventure film". But this time, we get in the
    text that "Adventure", and this is the actual type of object at runtime as "instanceType". And then we have the plot
    stages for an Adventure Film, pleasant scene, scary music, and something bad happens. That's a really simple example
    of polymorphism.

        Getting back to the movie.java source file, and creating the "comedy" subclass next:

                        class Comedy extends Movie {
                            public Comedy(String title) {
                                super(title);
                            }
                            @Override
                            public void watchMovie() {
                                super.watchMovie();
                                System.out.printf(".. %s%n".repeat(3),
                                        "Something funny happens",
                                        "Something even funnier happens",
                                        "Happy Ending");
                            }
                        }

    Ok, we have 2 of our movie subclasses built, but let's do the last one, which we're calling Science-Fiction.

                        class ScienceFiction extends Movie {
                            public ScienceFiction(String title) {
                                super(title);
                            }
                            @Override
                            public void watchMovie() {
                                super.watchMovie();
                                System.out.printf(".. %s%n".repeat(3),
                                        "Bad Alien do Bad Stuff",
                                        "Space Guys Chase Aliens",
                                        "Planet Blows up");
                            }
                        }

    And that's the ScienceFiction class, which means now we've built the movie class and oll of its subclasses.

        Up to now, we've only assigned an adventure movie instance to a Movie variable and saw that when it ran, Java figured
    it out which method to run, not on the compile time code, but on the runtime instance's method. But this time, we'll
    create a method on the Movie class, that the calling code can execute, that will return a movie instance for us. We'll
    make this method public and static, which means anybody can call this method to get a movie instance, based on the
    parameter type being passed in, a title. Let's type this method our partially.

                        public static Movie getMovie (String type, String title) {
                            return switch (type.toUpperCase().charAt(0)) {
                                default -> new Movie(title);
                            };
                        }

    In this code, the parameters of this method are the type of the movie, and the title. And we're going to return an
    instance of the Movie class, or a subclass of a Movie. And this gets returned by the switch expression. But what are
    we really doing here with the switch expression? Well, we're taking whatever was passed to us, making it uppercase,
    then just getting the first letter. In our case, each of our subclasses has a unique letter for its class name, so we
    can use that to figure out the right kind of movie to create. And just for now, we're returning a generic movie instance.
    Now, let's go back to the main method, and test that out. We'll call the static method on Movie, to get an Adventure
    movie.

                        Movie theSecondMovie = Movie.getMovie("Adventure", "Star Wars");
                        theSecondMovie.watchMovie();

    Now, notice if we run this,

                        Star Wars is a Movie film

    it works, but we're still not really getting "a Adventure" movie. That's because we haven't really completed the getMovie
    method. Going back to the Movie Class, we want to add the code to run return the different subclasses, based on the
    type argument:

                        public static Movie getMovie (String type, String title) {
                            return switch (type.toUpperCase().charAt(0)) {
                                case 'A' -> new Adventure(title);
                                case 'C' -> new Comedy(title);
                                case 'S' -> new ScienceFiction(title);
                                default -> new Movie(title);
                            };
                        }

    Here, our switch expression is really evaluating a char, a single character. We get this character from the String method,
    charAt() that we're using in the switch expression. That's going to give us the first letter of the type. And if it's
    an 'A', we'll return a new Adventure instance, if it's 'C', we'll return a Comedy, and if it's 'S', that means we want
    to create a new Science-Fiction Movie. If it's not one of those, then we'll just return the base class, an instance of
    Movie. By providing this method, the code in the main method (the calling code), doesn't really need to know anything
    about any of Movie's subclasses. It can just pass in the type, and get a different type of Movie subclasses. This kind
    of method, that returns a new instance object, is known as a factory method, in software programming design patters.
    Factory methods give us a way to get an object, without having to know the details of how to create a new one, or specify
    the exact class we want. Actually, if we run this code now, from the main method,

                        Star Wars is a Adventure film
                        .. Pleasant Scene
                        .. Scary Music
                        .. Something Bad Happens

    we see that we really do get an Adventure object back, because you can see Adventure film there on the first line. The
    getMovie method returned an instance which we maybe didn't know what it was, and maybe we don't really care. But because
    the runtime object was an instance of the Adventure class, the method on that class was executed. And now we can see
    the output for an Adventure instance, with the title Star-Wars, that it's an Adventure Film. And then we also get the
    3 plot stages, for the Adventure Class.

        Ok, next, we'll change the main method, passing ScienceFiction, because really Star-Wars is more of a ScienceFiction
    Movie. And really, we don't want to type in the full classname, we can just put in Science, or even 'S', if we wanted.

                        Movie theSecondMovie = Movie.getMovie("S", "Star Wars");
                        theSecondMovie.watchMovie();

    And if we run this,

                        Star Wars is a ScienceFiction film
                        .. Bad Alien do Bad Stuff
                        .. Space Guys Chase Aliens
                        .. Planet Blows up

    we get different type name and different plot stages. With this method, the calling code doesn't need to know about
    each subclass, or how to create different instances of movies. We can just call this method, passing the type and name,
    and the right object type is instantiated, and returned, but it's assigned to a variable with the Movie type, so this
    code will work for any Movie, or any of its subclasses, including subclasses that haven't even been created yet. This
    keeps all the information about Movie and it's subclasses, in the control of the Movie class, and simplifies the work
    that needs to be done, by the calling code. That sounds like a good encapsulation technique, doesn't it?

        Ok, going back to our main method, we'll use polymorphism, to watch a variety of movies. This time, we'll make the
    code interactive, using the Scanner class we've seen before. We'll let the user enter the type of movie, and then the
    title of the movie they want to watch. First we'll comment out the first 4 lines of code, then add the code for a new
    Scanner.

                        import java.util.Scanner;
                        public class Main {
                            public static void main(String[] args) {
                                Scanner s = new Scanner(System.in);
                            }
                        }

    In the Exception course, I showed you how to manually add import lines. Because we still have "Auto Imports enabled",
    you just saw how IntelliJ added the import for us automatically. Pretty nice, right? Essentially, IntelliJ adds and
    manages the imports for you as much as it can with the Auto import option turned on. So that's "Auto-import". These
    are the types of little automations, that collectively, make you a much more productive programmer.

        Ok, next, we'll create a loop, so we'll keep getting information from the user, until they quit out of the loop.

                        while (true) {
                            System.out.println("Enter Type (A for Adventure, C for Comedy, "+
                                    "S for Science Fiction, or Q to quit): ");
                            String type = s.nextLine();
                        }

    In this code, we're using a while loop, with a condition that's always true. That's really an infinite loop, and you
    definitely don't want to run this code yet. But first, we'll prompt the user for the type of Movie, enter A for Adventure,
    C for Comedy, Q for quitting. After that, we'll get the user's response, using nextLine method on Scanner. We want to
    get all the data they entered, up do and including the new line. The nextLine method gets the type of the movie they
    want to watch, but we're not doing anything with it yet, except assigning it to type. If we ran this now, we'd be stuck
    in an infinite loop. We need to add the code, to break out of the loop next.

                        if ("Qq".contains(type)) {
                            break;
                        }

    Ok, what is this code doing? This "if" statement uses the contains method on a String, which we've covered briefly in
    the String courses. "Qq", here we have a String literal, that has an uppercase and lowercase Q in it, and we're using
    contains to test what the user entered. This means, if the user enters a single character, either an uppercase or lowercase
    Q, then the code will break out of the loop. The contains method will determine if the String literal, "Qq", contains
    the single letter q, and returns true. Now that we can get out of the loop, we next want the code to get the title of
    the movie. And after that, we'll use the factory method on the Movie class, to get the right kind of instance:

                        System.out.print("Enter Movie Title: ");
                        String title = s.nextLine();
                        Movie movie = Movie.getMovie(type, title);
                        movie.watchMovie();

    Here again, we prompt the user to enter the movie title, and we'll read that in, using the nextLine method on scanner.
    After that, we can call Movie.getMovie to get an instance of a movie, passing the type, and title, that the user entered.
    And regardless of what comes back from this method, we're going to assign it to a variable with a type that we've said
    is Movie. So as long as whatever is being returned from the getMovie method, is a Movie, or a subclass of Movie, we
    can assign it to a Movie variable. And because it's a Movie variable, we can call any of Movie's methods on it. And
    that method is watchMovie.

        And running this code now:

                        Enter Type (A for Adventure, C for Comedy, S for Science Fiction, or Q to quit):
                        S
                        Enter Movie Title: Star-Wars
                        Star-Wars is a ScienceFiction film
                        .. Bad Alien do Bad Stuff
                        .. Space Guys Chase Aliens
                        .. Planet Blows up

    we get prompted to Enter the Type, so let's just enter an S, for ScienceFiction, and press enter. And now we'll put
    in the title, and I'll type in, Star-Wars, but you can enter anything you want there. And look at the output for the
    Star-Wars ScienceFiction movie. The code called the method watchMovie() using a Movie reference variable. But at runtime,
    the Movie wasn't really a Movie, it was an instance of the subclass, the ScienceFiction class. And it was the method
    watchMovie(), that's actually declared on the ScienceFiction class, that really got executed. This is polymorphism
    in action. Our compiled code, in the main method of the Main class, never knew anything about the ScienceFiction class,
    or any of the other subclasses. But at runtime, we got an object of type ScienceFiction back from the factory method.
    And when the method watchMovie() was called on that, it called watchMovie on the ScienceFiction class. I hope you agree
    with me that that's pretty neat. And don't forget, the watchMovie method on ScienceFiction, first called the method
    on Movie, and we can see that first output statement, "Star-Wars is a ScienceFiction film". This got printed out,
    because we called super.watchMovie(), when we overrode that method, on the ScienceFiction class.

        This is the ability to execute different behavior, for different types, which are determined at runtime. And yet
    we did it with just 2 statements, in the main method, as shown here:

                        Movie movie = Movie.getMovie(type, title);
                        movie.watchMovie();

    Polymorphism enables you to write generic code, based on the base class, or a parent class. And this code, in the main
    method, is extendable, meaning it doesn't have to change, as new subclasses become available. This code can handle any
    instances that are a Movie, or a subclass of movie, that are returned from the factory method, even ones that don't
    exist yet.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Movie theMovie = new Adventure("Star Wars");
//        theMovie.watchMovie();
//        Movie theSecondMovie = Movie.getMovie("Science", "Star Wars");
//        theSecondMovie.watchMovie();

        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Type (A for Adventure, C for Comedy, "+
                    "S for Science Fiction, or Q to quit): ");
            String type = s.nextLine();
            if ("Qq".contains(type)) {
                break;
            }
            System.out.print("Enter Movie Title: ");
            String title = s.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
    }
}
