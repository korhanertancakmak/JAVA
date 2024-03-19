package CourseCodes.NewSections.Section_07_OOP1.Course09_StaticInstanceVariables;

/*
Course-39

                                        Static vs Instance Variables

        Let's discuss the differences now between static variables, and instance variables. So firstly a static variable
    is declared by using the keyword "static". Static variables are also known as static member variables. Every instance
    of the class shares the same static variable. So if changes are made to that variable, all other instances of that class
    will see the effect of that change.

        It is considered best practice to use the Class name, and not a reference variable to access a static variable.

                    class Dog {

                        static String genus = "Canis";

                        void printData() {

                            Dog d = new Dog();
                            System.out.println(d.genus);      // Confusing!
                            System.out.println(Dog.genus);    // Clearer!
                        }
                    }

    This makes it clearer that the variable is associated with the Class and therefore shared, and the value is not stored
    with the instance. An instance isn't required to exist, to access the value of a static variable.

                    class Dog {

                        static String genus = "Canis";
                    }

                    class Main {

                        public static void main(String[] args) {
                            System.out.println(Dog.genus);       // No instance of Dog needs to exist, in order to access
                                                                 // a static variable
                        }
                    }

    Static variables aren't used very often, but can sometimes be very useful. They can be usud for:

  - Storing counters.
  - Generating unique ids.
  - Storing a constant value that doesn't change, like PI for example.
  - Creating, and controlling access, to a shared resource.

    Some example of shared resources might include, a log file, a database, or some other type of input or output stream.

                    class Dog {

                        private static String name;

                        public Dog(String name) {
                            Dog.name = name;
                        }
                        public void printName() {
                            System.out.println("name = " + name); // Using Dog.name would have made this code less confusing.
                        }
                    }

                    public class Main {

                        public static void main(String[] args) {

                            Dog rex = new Dog("rex");               // create instance (rex)
                            Dog fluffy = new Dog("fluffy");         // create instance (fluffy)
                            rex.printName();                        // prints fluffy
                            fluffy.printName();                     // prints fluffy
                        }
                    }

    In this example, we've got a class called Dog, and it's got a static variable called "name". Now, there's a constructor
    that sets the static variable, to the parameter value passed to the constructor. And we've got a method there, called
    printName, which isn't static. So that's a pretty simple class, and inside Main, we're creating 2 instances of the Dog
    class, with the line, Dog rex = new Dog("rex"). We're creating an instance of the Dog class, and then we're passing
    the String rex, as a parameter, and that'll be the name of the dog. In the next line, we've got a similar situation,
    just passing the parameter, which will be used as the name, fluffy. So then we call the printName method, on both of
    the instances. So they're just regular instance methods, because they aren't defined using static. So both method
    call will print fluffy. You might be wondering, why is that the case? Why would both methods here print fluffy?

        Well, remember that static variables are shared between instances. So in other words, once we change the static
    variable, all instances will see that change we made. So, when we called the constructor with parameter "fluffy", it
    modified the static variable name, because both instances are sharing that variable. That's why it prints fluffy twice.
    So you could also say that all dogs have the same name, but that's logically incorrect. So hopefully, now you can see
    how static variables can be used inappropriately sometimes, as in this example. Probably, you were assuming that the
    dog's name would be associated with each instance of the Dog, and therefore would be different for each one. One Dog
    would be named Fluffy, and the other would be Rex.

        So this is a scenario, where using a static variable, probably wouldn't be a good idea, and using a regular instance
    variable, would make a lot more sense, in this particular example. Alright, so let's move on now to instance variables.

                                                Instance Variables

        They don't use the static keyword when you're defining them. They're also known as fields, or member variables.
    Unlike a static variable, Instance variables belong to a specific instance of a class. Each instance has its own copy
    of an instance variable. Every instance can have a different value. Instance variables represent the state, of a specific
    instance of a class.

                    class Dog {

                        private String name;

                        public Dog(String name) {
                            this.name = name;
                        }
                        public void printName() {
                            System.out.println("name = " + name);
                        }
                    }

                    public class Main {

                        public static void main(String[] args) {

                            Dog rex = new Dog("rex");               // create instance (rex)
                            Dog fluffy = new Dog("fluffy");         // create instance (fluffy)
                            rex.printName();                        // prints rex
                            fluffy.printName();                     // prints fluffy
                        }
                    }

    So in this example, we've again got very similar code to what we looked at earlier, but this time, the variable "name"
    in the dog class isn't static. So it's just a regular instance variable. So once again, the constructor is setting the
    value, from the parameter passed, to that instance variable. But now the code will print rex, and on the next line
    fluffy, and that's because we're using instance variables. Each instance of the class has its own state, or its own
    values, for any variables that have been defined. So in other words, now every dog has got its own copy of the name
    field. We can also say, that it's basically not shared like it was before, in the earlier example, which was using
    a static variable.

        So in most cases, you'd probably want to use instance variables, but there'll be scenarios when it can be useful,
    to use a static variable like in the earlier examples.
*/



public class Main {
    public static void main(String[] args) {

    }
}
