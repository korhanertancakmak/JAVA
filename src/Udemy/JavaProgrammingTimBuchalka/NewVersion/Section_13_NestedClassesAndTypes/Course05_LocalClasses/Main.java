package CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course05_LocalClasses;

import CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course05_LocalClasses.domain.Employee;
import CourseCodes.NewSections.Section_13_NestedClassesAndTypes.Course05_LocalClasses.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

//Part-1
/*
                                                Local Classes

        Local classes are inner classes, but declared directly in a code block, usually a method body. Because of that,
    "they don't have access modifiers", and are only accessible to that method body while it's executing. Like an inner
    class, they have access to all fields and methods on the enclosing class. They can also access local variables and
    method arguments, that are final or effectively final.

        Let's review in this code. Let's have a little fun with this new type of nested class. When I was a kid, we spoke
    in a code called pig Latin, so I want to create a method, that creates a special class of StoreEmployee. This Employee
    will include a special pig latin name, and we'll then sort on that. For those who don't know, pig Latin strips off
    the first letter, and tacks it onto the end of the name or word, and adds the letters aye and y. So my name, Tim,
    would become im-Tay, and Jane would become ane-Jay, for example. Although this is a contrived example, I want you to
    imagine that there might be times where you have access to data, but want to create a computed attribute of your own,
    or perform a specialized operation of your own. This is one way to do it. Later, when we get to streams, we'll learn
    more elegant and easier ways. But let's see what this looks like. I'll create a new static method on the Main class,
    called add PigLatinName, that will accept a List.
*/
//End-Part-1

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018) ));

        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target") ));

        var genericEmployee = new StoreEmployee();
        var comparator = genericEmployee.new StoreComparator<>();
        storeEmployees.sort(comparator);

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }

        System.out.println("With Pig LAtin Names");
        addPigLatinName(storeEmployees);

//Part-5
/*
        First, I'll print out a title, then I'll call the method. If I run that:

                    ...(same)
                    With Pig LAtin Names
                    Macys   10105 Tom      2020 omTay
                    Target  10322 Bud      2016 udBay
                    Target  10015 Meg      2019 egMay
                    Walmart 10215 Marty    2018 artyMay
                    Walmart 10515 Joe      2021 oeJay

    you can see my calculated Pig Latin name, printed out for each employee. But let's sort by this name now. How would
    I go about doing that? You might say Comparator, since we've spent so much time with that interface. But this time,
    I'm going to have this local class implement Comparable. These local classes can implement interfaces, just like any
    other class. I'll go back to this class, and add, implements Comparable, and make the type argument equals to this
    new class DecoratedEmployee.

                                class DecoratedEmployee extends StoreEmployee
                                                        to
                class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee>

    So my compareTo method will compare these specific types. This means I can use the Pig Latin name directly in this
    method without casting.
*/
//End-Part-5
    }

//Part-2
/*

        Hopefully, you'll remember, this is a generics wild card that specifies an upper bound for this method parameter's
    type argument. This means this method will only accept a List containing StoreEmployees, or any subtype of StoreEmployee.
    Next, I'm going to declare the local class, right here inside the method block. I'll name this local class decoratedEmployee.
    Notice, there is no modifiers. In fact, if I tried to use any here, I'd get a compiler error. When i try to make it
    "public class", I'd get an error which says "modifier 'public' not allowed here". But like any class, as I'm showing
    here, it can extend. I can add attributes, which I'll do now. I'm making these private. However, the enclosing code
    in this method can still access this class's private fields, even after this class declaration's closing bracket.
    First, I have the field that will hold the pigLatinName, and another field that gets assigned the original StoreEmployee
    instance. Now, I want to include a constructor. I'll generate that, and for this constructor, I only want these 2
    additional fields, not the StoreEmployee fields.
*/
//End-Part-2

    public static void addPigLatinName(List<? extends StoreEmployee> list) {

//Part-8
/*
        I'm first going to set up a local variable in my method, and call that last name. In the local class, I'm going
    to use this local variable in the class constructor code. I'll add it to the end of the generated pig latin name.

                    this.pigLatinName = pigLatinName;
                                        to
                    this.pigLatinName = pigLatinName + " " + lastName;

    This is pretty neat that I can use local variables in the method, or even method arguments to influence whatever I
    want to happen in this local class. If I run this code now:

                    With Pig LAtin Names
                    Walmart 10215 Marty    2018 artyMay Piggy
                    Target  10015 Meg      2019 egMay Piggy
                    Walmart 10515 Joe      2021 oeJay Piggy
                    Macys   10105 Tom      2020 omTay Piggy
                    Target  10322 Bud      2016 udBay Piggy

    You can see Piggy as the last name for these pig latin names. Let me go down to the last for loop,
*/
//End-Part-8

        String lastName = "Piggy";

        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {

            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                //this.pigLatinName = pigLatinName;
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                //return super.toString();
                return originalInstance.toString() + " " + pigLatinName;
            }

//Part-6
/*
        Here, I want to compare the pig latin names on the current instance with the one passed to it.
*/
//End-Part-6

            @Override
            public int compareTo(DecoratedEmployee o) {
                //return 0;
                return pigLatinName.compareTo(o.pigLatinName);
            }

//Part-3
/*
        Ok, so next, I want to include the toString using the override features in IntelliJ. I'll replace that super, and
    I really want to return the StoreEmployee's string representation by calling its toString method, and appending this
    additional field, pigLatinName. You can see I've created an entire class, all within my method body. Why would you
    want a class in a method? Well, it's possible, even probable, that you really just want the ability to create a class
    for just a single purpose. In this case, the only purpose of this class is to add a derived field, the pigLatinName,
    to an existing set of instances of StoreEmployees. I don't want to reinvent the wheel, or add this class to my library
    of classes. I don't want anyone else to use it, or extend it. I only want it to exist for this one specific purpose.
    Now, I want to create a list of these DecoratedEmployee types, and print them out. I'm going to do this in this same
    method, directly after the class declaration I just added.
*/
//End-Part-3
        }

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());

        for (var employee : list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin, employee));
        }

//Part-7
/*
        Finally, I want to make a call to sort my list of DecoratedEmployee, before I print them out. Remember, if I pass
    null to the sort method on a list, it will use the Comparable's compareTo method. This is the same as if I had passed
    the result of the Comparator.naturalOrder method to sort. Running that:

                    With Pig LAtin Names
                    Walmart 10215 Marty    2018 artyMay
                    Target  10015 Meg      2019 egMay
                    Walmart 10515 Joe      2021 oeJay
                    Macys   10105 Tom      2020 omTay
                    Target  10322 Bud      2016 udBay

    you can see that StoreEmployee have been sorted by the pig latin names, in alphabetical order, so Marty, whose pig
    latin name is artyMay is first. This local class decorated on existing class, meaning it added extra functionality
    to the class, like this new derived field, and also provided a way to sort by this new field. This class both extended
    a class and implemented an interface. It overrode the toString method and implemented the abstract method compareTo.
    Ok, now, let me show you a couple of other things about local classes.
*/
//End-Part-7

        newList.sort(null);

//Part-4
/*
        First, I'll create a new list of DecoratedEmployees, and I'll pass the size of the list, as the initial capacity.
    Then I'll loop through the list that was passed to this method, which is my list of store employees, and I'll set up
    instances of the new type of employee, adding them to my new list. This code sets up a list of my local class, the
    DecoratedEmployee, which you can think of as a wrapper class to the StoreEmployee with one additional field. This code
    shows how the pig latin name is derived. The first character is removed, with the subString method, and starting with
    1 as the index. And that first character is then retrieved with charAt, and 0 as the index, and appended. Finally, I
    end with A and Y, tacked on to that. I'll print out each of my decorated employees with an enhanced for loop. Ok, so
    that's the entire method. I want to execute this from the main method.
*/
//End-Part-4

        for (var dEmployee : newList) {
            //System.out.println(dEmployee);
            System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);
        }

//Part-9
/*
        And this time, I'm just going to print the original name and the pig latin name.

                    System.out.println(dEmployee);
                                        to
                    System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);

    you might be asking, why is this interesting? Because here, in both cases, I'm accessing private attributes on the
    local class, the pigLatinName field, and the original Instance field. I'm accessing these outside of the class
    declaration. A method that has a local class can access any of that class's fields, private or not, on any instances
    used in the method. You may also be wondering why I'm using getName, on the original Instance, and not on my Decorated
    Employee instance. Well, I never actually set name on the decorated employee instances themselves, or any of the other
    store employee fields that were inherited. Most of those fields were private with no getters or setters, so my class,
    even though it was a subtype, had no access to these fields. Finally, the last thing you need to understand is final
    and effectively final variables. This is because local variables can only be used in a local class, if they are final
    or effectively final. So what does this mean?

                                "Captured Variables" of the Local Classes

        When you create an instance of a local class, referenced variables used in the class, from the enclosing code,
    are 'captured'. This means a copy is made of them, and the copy is stored with the instance. This is done because the
    instance is stored in a different memory area, than the local variables in the method. For this reason, if a local
    class uses local variables, or method arguments, from the enclosing code, these must be final or effectively final.

                                Final Variables and Effectively Final

        The code sample on below shows:

                class ShowFinal {
                    private void doThis(final int methodArgument) {
                        final int Field30 = 30;
                    }
                }

    - A method parameter, called methodArgument in the doThis method, declared as final.
    - And a local variable, in the method block, Field30, also declared with the key word final.

    In both these cases, this means you can't assign a different value, once these are initialized. These are "explicitly
    final", and any of these could be used in a local class, because of this.

                                        Effectively Final

        In addition to explicitly final variables, you can also use "effectively final" variables in your local class.
    A local variable or a method argument are effectively final, if a value is assigned to them, and then never changed
    after that. Effectively final variables can be used in a local class.

        In my code, I can use last name in my local class's constructor, because I assigned it a value and never changed
    it. But what if I assign it a different value, let's say at the end of the method? Then, I'll get a compiler error
    where I use the local variable in the constructor of my local class. If I hover over that error, IntelliJ tells me
    that "lastName needs to be final or effectively final". Because I made changes to the value of that variable in the
    method, even after the class declaration, this means the variable isn't effectively final, and for that reason I
    can't use it in my class. I'll revert that last change, so the code compiles.

        You may have noticed that IntelliJ has been giving us some warnings, or indication, for some of our fields and
    variables that they might be final. We'll be talking more about when to use final and when not to, when we review
    the final modifier in an upcoming section. For local classes, it's just important to recognize when a variable is not
    final or effectively final, because you can't use it in the local class directly, as I've demonstrated here.

                                        Additional Local Types

        As of JDK 16, you can also create a local record, interface and enum type, in your method block. These are all
    implicitly static types, and therefore aren't inner classes, or types, but static nested types. The record was introduced
    in JDK16. Prior to that release, there was no support for a local interface or enum in a method block either. You may
    see older tutorials saying you can't do this, but just know that this functionality is now available. I'll be demonstrating
    an example of this later.
*/
//End-Part-9
    }
}
