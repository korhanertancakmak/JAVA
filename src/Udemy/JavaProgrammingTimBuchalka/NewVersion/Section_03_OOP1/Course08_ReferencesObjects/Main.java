package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course08_ReferencesObjects;

/*
Course-38

                                        Reference vs Object vs Instance vs Class

        In this course, we'll talk about references, objects, instances and classes. By now, you've probably noticed that
    we use the words, reference, object, instance and class, quite a lot when talking about Java code. These new concepts
    may well be confusing at first. So we're going to try and go through the process, and show you exactly what each of
    these words means, in the context of Java programming.

        You might remember from earlier section of the course, we said that Object and Instance are interchangeable, and
    that we create an instance or object using a class. These new concepts may well be confusing at first, so let's talk
    about them.

        Let's use the analogy of building a house to understand classes. Now a class is basically a blueprint for the
    house. Using the blueprint, we can build as many houses as we like, based on those plans. So thinking back to the
    physical world, we use the plans for the house, to build many houses that have the same floor plan. Each house we build
    (in other words going back to programming terms, each house we instantiate using the new operator) is an object. This
    object can also be known as an instance, often we'll say it's an instance of the class. So we would have an instance of
    house in this example. Getting back to the physical world, each house we build has an address (a physical location).
    In other words, if we want to tell someone where we live, we give them our address (perhaps written on a piece of paper).
    This is known as a reference.

        So that piece of paper, with the address on it, this is known as a reference. Now, we can copy that reference as
    many times as we like, but there is still just one house that we're referring to. In other words, we're copying the
    paper that has the address on it, not the house itself. Or we're copying the paper that has the address on it, not
    the house itself. Now back to programming terms, we can pass references as parameters to constructors and methods.

        Let's now just go a bit deeper, to make this a little bit more clearer. We've got some code below:

                            public class House {
                                private String color;
                                public House(String color) {
                                    this.color = color;
                                }
                                public String getColor() {
                                    return color;
                                }
                                public void setColor(String color) {
                                    this.color = color;
                                }
                            }

    So, we've got the class House, with an instance variable, also known as a field called color. Now also we have got a
    main method in a Main class(all the way below). Now this code in the main method. is creating instances of the house
    class, changing the color, and printing out the result. So let's actually go through line by line, and see what happens
    when this code is executed.

            Line-1 :        House blueHouse = new House("blue");

        Alright, this code creates a new instance of the House class. Remember House is a blueprint, and we are assigning
    it to the blueHouse variable. In other words it is a reference to the object in memory. blueHouse is the variable,
    we're creating a new instance of the House class, and assigning it the color blue.

            Line-2 :        House anotherHouse = blueHouse;

        Alright, so this next line, that we've got, creates another reference, to the same object in memory. Here we have
    2 references pointing to the same object in memory. There is still one house, but 2 references to that 1 object. In
    other words we have 2 pieces of paper with the physical address of where the house is built (going back to our real
    world example), written down (if we came back to a real-world example to make that clearer).

            Line-3 :        System.out.println(blueHouse.getColor());       // prints blue
            Line-4 :        System.out.println(anotherHouse.getColor());    // blue

        So next on the right-hand side, I've got 2 println statements highlighted. So they print the value of the color
    variable, for blueHouse and also anotherHouse. Now in this scenario, both will print "blue" since we have 2 references
    to the same object.

            Line-5 :        anotherHouse.setColor("yellow");

        Alright, the next line is calling the method setColor, and setting the color to yellow. Now both blueHouse and
    anotherHouse have the same color. Why? Remember we have 2 references that point to the same object in memory. Once
    we change the color of one, both references still point to the same object. So consequently, they've both got the same
    value of yellow, in this example. In our real world example, there is still just one physical house at that one address,
    even though we have written the same address on 2 pieces of paper. So if we went ahead and painted the house yellow,
    then obviously, both references to the physical house, still point to a house that is now yellow.

            Line-6 :        System.out.println(blueHouse.getColor());       // yellow
            Line-7 :        System.out.println(anotherHouse.getColor());    // yellow

        Now, we've got a couple more println statements here. So these 2 println statements are printing the same color.
    Both now print "yellow" since we still have 2 references that point to the same object in memory.

            Line-8 :        House greenHouse = new House("green");

        Here we are creating another new instance of the House class with the color set to "green". Now we have 2 objects
    in memory but we have 3 references which are blueHouse, anotherHouse and greenHouse. The variable (reference) greenHouse
    points to a different object in memory, but blueHouse and anotherHouse point to the same object in memory.

            Line-9 :        anotherHouse = greenHouse;

        Here we assign greenHouse to anotherHouse. In other words, we are dereferencing anotherHouse. It will now point
    to a different object in memory. Before it was pointing to a house that had the "yellow" color, now it points to the
    house that has the "green" color. In this scenario we still have 3 references and 2 objects in memory but blueHouse
    points to 1 object while anotherHouse and greenHouse point to the same object in memory.

            Line-10 :       System.out.println(blueHouse.getColor());       // yellow
            Line-11 :       System.out.println(greenHouse.getColor());      // green
            Line-12 :       System.out.println(anotherHouse.getColor());    // green

        And finally, we have 3 println statements. The first will print "yellow" since the blueHouse variable(reference)
    points to the object in memory that has the "yellow" color, while the next 2 lines will print "green" since both
    anotherHouse and greenHouse point to the same object in memory.

        So keep in mind, that in Java, you always hava a reference to an object in memory. There's no way to access an
    object directly, everything is done using that reference.

        Finally, consider the code below for a moment:

                            new House("red);                        // house object gets created in memory
                            House myHouse = new House("beige");     // house object gets created in memory
                                                                    // and its location (reference) is assigned to myHouse
                            House redHouse = new House("red");      // House object gets created in memory
                                                                    // and its location (reference) is assigned to redHouse

        On the first line, we create a new House, and make it red. But we aren't assigning this to any variable. This
    compiles fine, and you can do this. This object is created in memory, but after that statement completes, our code has
    no way to access it. The object exists in memory, but we can't communicate with it, after that statement is executed.
    That's because we didn't create a reference to it.

        On the second line, we do create a reference to the house object we created. Our reference, the variable we call
    myHouse, let's us have access to that beige house, as long as our variable, myHouse, stays in scope, or until it gets
    reassigned to reference a different object.

        On the third line, we're creating a red house again, but this is a different object altogether, from the red house
    we created on line 1. This third statement is creating yet another house object in memory, which has no relationship to
    the one we created on the first line.

        So this code has 3 instances of house, but only 2 references. That first object will stay in memory, with no reference
    to it, until Java's automatic process (appropriately called garbage collection), figures out there is no running code
    with a reference to that object, and deletes it. In fact, that first object is said to be eligible for garbage collection,
    immediately after that first statement. It's useless to the code, because it's no longer accessible. There are times
    we might want to instantiate an object, and immediately call a method on it, and not assign the object to a variable
    reference, and we'll show you some reasons later on in the course. But 99% of the time, we'll want to reference the
    objects, we create. So we'll immediately assign our new instance to a variable, creating a reference to communicate
    with it.
*/


public class Main {
    public static void main(String[] args) {

        House blueHouse = new House("blue");
        House anotherHouse = blueHouse;

        System.out.println(blueHouse.getColor());       // prints blue
        System.out.println(anotherHouse.getColor());    // blue

        anotherHouse.setColor("yellow");
        System.out.println(blueHouse.getColor());       // yellow
        System.out.println(anotherHouse.getColor());    // yellow

        House greenHouse = new House("green");
        anotherHouse = greenHouse;

        System.out.println(blueHouse.getColor());       // yellow
        System.out.println(greenHouse.getColor());      // green
        System.out.println(anotherHouse.getColor());    // green

    }
}
