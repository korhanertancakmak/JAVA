package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course03_SetterMethodsObjects;

/*
Course-33

                                      Classes, Using Setter and Creating Objects

        So best practices for fields, that in general, fields on classes should be private, and a getter method should be
    created to access those fields. This provides encapsulation of the internals of our class, and supports maintenance
    of a public interface that doesn't have to change, even though our class might.

        In this course, we're going to be setting up, and talking about setter methods, which set data on the objects.
    We'll end this course by creating more objects out of the Car class, and talking more about that process. We'll be
    using the code from the last code, where we ended it, with the Main and Car classes in CoursesFrom032To065.Course033 package(folder).

        So now, let's add the setter methods, in the Car class:

                                    public void setMake(String make) {
                                        this.make = make;
                                    }

    So, we have public methods that doesn't return anything, they're void, because remember, the setter method sets data,
    it doesn't retrieve it. And the parameter is going to be String, because that's what we're setting. For example the
    field "make" is a String, and we'll use String as our parameter type. And what we want to do is, assign the field on
    our object, "make" in this case, to the value passed as an argument to the method. But is that what this is doing?

                                                    this

        "this" is a special keyword in Java. What it really refers to is the instance that was created when the object
    was instantiated. So "this" is a special reference name for the object or instance, which it can use to describe itself.
    And we can use "this" to access fields on the class.

        So, because we're trying to set make, the field, here on the left side of the assignment operator, we want to specify
    that the make field we want to use, to assign a value to, is the make field on the object, and not the make method
    parameter. So, that's a way of updating the make attribute on Car, using a method, instead of trying to access it directly.
    Also, in main method, we can add this setter:

                                            car.setMake("Porsche");

    We can see that our car isn't using the default "make", but is using the "make" we set it to, Porsche, in both of these
    cases, when we look at its output. So again, we don't really want to manually code all of these setters, like we did
    this first one. By using IntelliJ code generation, it can create the rest of the setters for us. Adding other setters
    into Main class:

                                            car.setModel("Carrera");
                                            car.setDoors(2);
                                            car.setConvertible(true);
                                            car.setColor("black");

    we get the values we set on the car; and not the defaults.

        So let's talk about why we'd want to use getters and setters? What's advantage of setMake? By using setMake,
   what we could do is, for example, we could go back to our Car.java. We can have a look at the setMake method, our setter,
   and we could add some validation:

                                            public void setMake(String make) {

                                                if (make == null) make = "Unknown";
                                                String lowercaseMake = make.toLowerCase();
                                                switch (lowercaseMake) {
                                                    case "holden" , "porsche", "tesla" -> this.make = make;
                                                    default -> {
                                                        this.make = "Unsupported ";
                                                    }
                                                };
                                            }

        So, first in this code, we check if the argument being passed is null. If it is, we set the make to "Unknown".
    Next, we're using a method on String to test the argument being passed. So now, this expression:

                                            String lowercaseMake = make.toLowerCase();

    shouldn't be completely unfamiliar to you. We know the make parameter is a String, or more correctly, it's an object
    of type String, and because of that, we have access to many methods on instances of String. One of these methods is
    this one, toLowerCase, which returns a new string, that's all lowercase. We'll use the switch statement on this variable,
    and test if it's one of the three makes we support. These are holden, porsche, and tesla. If the make matches one of
    those, we'll set the make field to the argument passed. But if it doesn't, we use the default case, and set the make
    field to "Unsupported".

        So now we've built a rule, that we're supporting only three manufacturers. And we'll enforce that rule, so that
    if anything else is passed, we set make to "Unsupported". But the argument is null, we set it to "Unknown". Go back
    to Description.txt and test this by the changing Porsche to Maserati. And after running the code, you can see that "Unsupported"
    not "Maserati".

        So you can see how it's very useful to have this type of functionality, the validation. So what you can do with
    the setter methods, is you can set up all the rules, related to that class, what is valid, and what is not valid. You
    can have all that functionality set up, within the Car class itself, so that these rules are in place as we're creating
    the objects. What that means is, the code that's creating objects, can't make invalid objects so to speak. In other
    words, it can't assign a make, that we haven't defined as being valid in our Car class. So that's the reason, and that's
    really the whole concept of encapsulation, is that we're not allowing people to access the field directly. We force
    them to go through a controlled way of setting up the data on the object. Using a setter method, we can really make
    sure that the data in our objects is valid data.

        So we've covered setter and getter methods, and why you might want to use them. Now, let's just talk a little bit
    about declaring variables using classes. I want to show you what happens, if we don't do this first line here:

                                            Car car = new Car();

    if i comment that second bit out here, = new Car, so we've just got a variable. So comment that out and just define
    the variable so that we've not initialized it:

                                            Car car; // = new Car();

    We haven't included the rest (= new Car();) part. Already, IntelliJ is saying, "Variable car may not have been initialized",
    where we are attempting to call the setter method, on the car variable. You can't use an uninitialized variable, which
    car is, because we haven't assigned any object reference to it. But now, consider what happens, if we instead assign
    null, to car:

                                            Car car = null; // = new Car();

    So IntelliJ is not showing any errors when we do this, but let's try running it. We actually get an exception,
    NullPointerException, and the additional information that we can't call a method, or invoke a method(an instance method)
    on a null instance. And what that essentially means, is we've defined a variable, called car, but it doesn't have a
    reference to a valid instance of a Car. So we can't run a method on null, and we couldn't set, or get attributes on
    null.

        So there's a distinction here i want to point out, between an uninitialized variable, and a variable with a null
    reference.An uninitialized variable, as we saw in the first instance, causes a compile time error. But variable with
    a null reference can be used in code, without compiler errors, but will throw an exception at runtime.

        So, in both of these scenarios, we haven't created an object from the Car template, which of course is the class.
    So that's why you need to make sure that new is always executed. The bottom line I'm trying to say here is that, make
    sure when you're creating objects, you always use the keyword new, and then include the name of the class, and then
    follow it with the parentheses.

*/

public class Main {
    public static void main(String[] args) {

        Car car = new Car();
        //car.setMake("Porsche");
        car.setMake("Maserati");
        car.setModel("Carrera");
        car.setDoors(2);
        car.setConvertible(true);
        car.setColor("black");
        System.out.println("make = " + car.getMake());
        System.out.println("model = " + car.getModel());
        car.describeCar();
    }
}
