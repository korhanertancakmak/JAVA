package CourseCodes.NewSections.Section_07_OOP1.Course02_ClassesUsingGetterMethods;

/*
Course-32

                                      Introduction to Classes, Using Getter Methods

        In the previous course, we created a Car class, i moved them into now CoursesFrom032To065.Course032 package, and we declared several
    private fields on it, as well as a method called describeCar.

        So the next thing we need to do is, we need to create an object from this class, because if you recall, the class
    is just the template. We need to create an object, which will sort of take that blueprint, that definition that we've
    defined in the class, and instantiate or create an object that we can then start using.

        So, to do that, what we need to do is go back to the main class, we started out with. And what we can do in here,
    is we can actually build an object, and base it on that Car class. So what we now need to do is create that object.
    So how do we do that? Did you guess that you'd use the new keyword? So let's do that.

                        // First we'll declare a variable of type Car, calling the variable simply, 'car', and then we'll
                        // assign it a new instance of the Car class:
                        Car car = new Car();
                        car.describeCar();
                        // So now what happens? If we run this, we get "0-Door null null null". So what's happening here.
                        // Why do we get null when we were expecting color, make, and model?

                                            What is null?

        null is a special keyword in Java, meaning the variable or attribute has a type, but no reference to an object.
    This means that no instance, or object, is assigned to the variable or field. Fields with primitive data types are
    never null.

        So why is the color, manufacturer, and model of the car null? Well, all of these fields were declared with the
    String data type, and we haven't assigned values to them. We know from previous lectures that Strings are really
    objects, not primitive data types, so Java assigned each a null reference. The other thing i want you to notice about
    the output we got, was that doors was printed with the value 0, here, and that the word Convertible, wasn't printed
    in the output. So how is that possible? We didn't set doors or convertible to any values in our class, but we didn't
    get any errors when running this code. And now, we're seeing another difference between local variables, and fields
    declared on a class. And this is, that a field with a primitive data type, will get assigned a default value by Java.

                                   Default Values for Fields on Classes

        Fields on classes are assigned default values, intrinsically by Java, if not assigned explicitly.

                                   Data type           Default value assigned

                                   booloean                  false
                                     byte                      0
                                     short                     0
                                     int                       0
                                     long                      0
                                     char                      0
                                    double                     0.0
                                    float                      0.0

    So, because we didn't assign any values to our fields, Java assigned some values for us, by default. So now, let's
    assign some of our own default values to these attributes in the Class Car:

                                private String make = "Tesla";
                                private String model = "Model X";
                                private String color = "Gray";
                                private int doors = 2;
                                private boolean convertible = true;

        This means that every object that's instantiated, will get assigned the default values we declared here, instead
    of Java's implicit values. And now, running our code in Description.txt, we get "2-Door Gray Tesla Model X Convertible".
    So every car object we create, will have these values by default. But that's not what we really want either.

        How do we get and set the make, model and color of our car, each time we create a car object? Since we made these
    fields private, we can't use the dot notation with the variable name, to set the value. Let me show you what i mean.
    Let's try accessing these fields directly in the main method:

                                car.make = "Porsche";
                                car.model = "Carrera";
                                car.color = "Red";

    You can see, when we try this, we get errors on all three of those lines. We've defined these fields as private on the
    Car class, and because of that, we can't set the values on any car object we create this way. We also can't access
    those values directly in an expression. For example, we couldn't just print that data out in the main method of this
    Main class:

                                System.out.println("make = " + car.make);
                                System.out.println("model = " + car.model);

    Again, we get compiler errors, saying that these fields have private access, and our Main class isn't allowed to access
    them. So, other than the describeCar method, how does outside code get access to this data? We could make all of these
    fields public, or package, but we've said this is bad practice, so we're not going to do that. What we're going to do,
    is allow access to this data, either to set it or get it, through methods on this class. These are a special set of
    method, called getters and setters. So what are getters and setters?

                                What are Getters and Setters? Why should we use them?

        A getter is a method on a class, that retrieves the value of a private field, and returns it. A setter is a method
    on a class, that sets the value of a private field. The purpose of these methods is to control, and protect, access to
    private fields. Another important aspect, is that the getter and setter method signatures are part of car's public
    interface, but the attribute names and types aren't. This means that we can change things internally, like the name
    or type of an attribute, but as long as we use the same getter and setter method, these changes should have no effect
    on code that uses our class. Our internal changes are hidden from our users. A getter method usually just returns the
    value of a private attribute. It's usual to name a getter method with the get prefix, followed by the field name, in
    lower camel case, but this is not required. You could have getter methods for attributes that are not really declared
    on your class, but that are derived in some way. A setter method may simply just assign the argument passed to the
    attribute, but it often contains code to validate data, check additional security requirements, ensure immutability
    of the field value, or any other code required to protect and validate an object's state. It's usual to name a setter
    method with the set prefix, followed by the field name, in lower camel case, but again, this is matter of form. There
    may be many cases where we won't have any setter methdos, for some of our private fields. Maybe this is data only needed
    within the class itself, and doesn't need to be exposed to the outside world.

        To add getter method, going back to the class car, we'll add a method, which will get the make on Car using a
    method:

                                        public String getMake() {
                                            return make;
                                        }

    This method is public, but notice we don't use the word static here. When writing methods that use non static fields,
    your method can't be declared static. And the getter will usually return the type the field is, so String in this case.
    And then we just return the field name, make. Even though make is declared on the class, we can refer to it from code
    in our method as we are showing here. To create the other getter methods, we could copy and paste the same code 4 times,
    and change the name and the attribute returned in each instance. But in general, you won't really be manually typing
    in the code for your getter methods. IntelliJ has features to generate code for you, including one to create a set of
    getter method, so let's do that for the next four attributes. From "Code" menu item, then "Generate", and you'll see
    there's a lot of options. We'll select "getter". Now all the attributes are listed here, that don't already have getters,
    so i'll select the remaining 4, by holding shift and pressing the down arrow key.

        After adding 4 getter methods, and if you run the Main class, you can see, we can now use the make and model data,
    in our own output string.

*/

public class Main {
    public static void main(String[] args) {

        Car car = new Car();
//        car.make = "Porsche";
//        car.model = "Carrera";
//        car.color = "Red";
//        System.out.println("make = " + car.make);
//        System.out.println("model = " + car.model);
        System.out.println("make = " + car.getMake());
        System.out.println("model = " + car.getModel());
        car.describeCar();
    }
}
