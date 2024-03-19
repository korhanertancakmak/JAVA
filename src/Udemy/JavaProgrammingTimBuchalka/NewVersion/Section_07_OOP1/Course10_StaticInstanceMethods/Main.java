package CourseCodes.NewSections.Section_07_OOP1.Course10_StaticInstanceMethods;

/*
Course-40

                                        Static vs Instance Methods

        Static methods are declared using a static modifier. Static methods can't access instance methods and instant variables
    directly. They're usually used for operations that don't require any data from an instance of the class (from "this").
    if you remember, the "this" keyword is the current instance of a class. So inside a static method, we can't use the
    "this" keyword. Whenever you see a method that doesn't use instance variables, that method should probably be declared
    as a static method. For example, main is a static method, and it's called by the Java virtual machine when it starts
    the Java application.

                        class Calculator {

                            public static void printSum(int a, int b) {
                                System.out.println("sum= " + (a + b));
                            }
                        }

                        public class Main {

                            public static void main(String[] args) {
                                Calculator.printSum(5, 10);
                                printHello();                           // shorter from of Main.printHello();
                            }
                        }

                        public static void printHello() {
                            System.out.println("Hello");
                        }

        In this example above, we've got a class called Calculator, with a static method called print sum. And it just prints
    the sum of 2 integer numbers. Then we've got the main class with 2 static methods, main and printHello. Now inside main,
    we're calling the method printSum from the calculator class. So as you can see, to call the printSum method, we just
    need to type the class name, in this case, Calculator, and then the method name, printSum. Or in the second example,
    in the case of printHello, we can just type the method name with parentheses, which will automatically call the
    printHello static method, because it's being invoked from a static method itself. So static methods don't require an
    instance to be created. We can just type the class name, and use the dot notation, with the method name to access them.

                                                    Instance Methods

        Instance methods belong to an instance(a specific instance), of a class. To use an instance method, we have to
    instantiate the class first, usually by using the "new" keyword. Instance methods can access instance methods and
    instance variables directly. Instance methods can also access static methods and static variables directly. What i mean
    by directly, is that we don't usually have to use the keyword "this.", to use them. And we don't have to use the
    ClassName.StaticVariables to access static variables, though that can help with clarity.

                        class Dog {

                            public void bark() {
                                System.out.println("woof");
                            }
                        }

                        public class Main {

                            public static void main(String[] args) {

                                Dog rex = new Dog();               // create instance
                                rex.bark();                        // call instance method
                            }
                        }

    So in this example, we've got a class called Dog, with a method bark. So notice here, how the method bark, is not using
    the static keyword this time. So in other words, that method is just a standard instance method. Then we've got our
    main class with a method main. Now inside main, the main method, we first need to create an instance of the dog class,
    and that's done with the line, Dog rex = new Dog(). As you can see, we're using the "new" keyword, to create an instance
    of that class. After we've got the instance, we can call the instance method bark, in this case, by typing "rex.bark".
    So the hard part here could be deciding when to create an instance, or when to create a static method. So let's see
    some basic rules, that should help you decide.

                                            Should a method be static?

                                                       ↓

                           Does it use any fields(instance variables) or instance methods?

                   ↓↓↓[YES]↓↓↓                                                          ↓↓↓[NO]↓↓↓

        It should probably be an instance method                             It should probably be a static method.

    So here, we've got a small diagram, that should help us decide, whether we need an instance or a static method. Now
    instance methods are created more often than static methods, but let's see how to follow this diagram. So the first
    question we'd ask ourselves is, should the method be static? After that question, the next question would be, does it
    use any fields? Instance variables in other words, or instance methods of this object? And remember, we're asking
    these questions about the proposed method we plan to write, so if that's true, in other words, it does use some fields
    and/or instance methods. Then we'd want to make it an instance method. In other scenario if the method doesn't use,
    or is not proposed to use, any instance variables or instance methods, in that case, then we'd probably consider writing
    it, as a static method.

        So generally speaking, if we're not using any fields, or instance methods, with the new proposed method, we should
    consider making that method static, instead of a regular instance method. So that's the main differences between static
    and instance methods.
*/

public class Main {
    public static void main(String[] args) {

    }
}
