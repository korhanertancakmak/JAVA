package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course12_POJOAndOverriddenMethods;

/*
Course-41

                                        Plain Old Java Object (The POJO)

         A plain old Java object(whose acronym is POJO) is a class that generally only has instance fields. It's used to
    house data, and pass data, between functional classes. It usually has few, if any methods other than getters and setters.
    Many database frameworks use POJO's to read data from, or to write data to, databases, files or streams. You'll remember,
    we have said for several times that a class can be thought of as a super data type. A POJO is really just that. It lets
    you extend, and combine, your definition of data types.

        A POJO also might be called a bean, or a JavaBean. Maybe you've heard of the term JavaBean. A JavaBean is just a
    POJO, with some extra rules applied to it. These rules are in place, so that Java frameworks, have a standard way to
    manipulate, and manage these objects. A POJO is sometimes called an Entity, because it mirrors database entities. Another
    acronym is DTO, for Data Transfer Object. It's a description of an object, that can be modeled as just data.

        There are many generation tools, that will run a data model into generated POJO's or JavaBeans. You've seen an example
    of similar code generation in IntelliJ, which allowed us to generate getters, setters, and constructors in a uniform
    way, based on our class's fields.

        Let's make an example of a POJO code, that helps us to read data out of a database, that has a table of Student
    records. So, we need 4 fields: id, name, date of birth, and class list. We're going to make all of these String for
    this exercise. Later in the course, we'll make this a lot more interesting, with dates, arrays, and custom types.

                                public class Student {
                                    private String id;
                                    private String name;
                                    private String dateOfBirth;
                                    private String classList;
                                }

        So now, let's make this a Plain Old Java Object. A POJO in its simples form, requires a way to populate data, and
    we can do this with a constructor. We'll use IntelliJ's code generation tool, to create a constructor first with all
    of these fields. Select 'Code' from menu, select 'Generate' next, and select 'Constructor', and select all the fields,
    then press OK:

                                public Student(String id, String name, String dateOfBirth, String classList) {
                                    this.id = id;
                                    this.name = name;
                                    this.dateOfBirth = dateOfBirth;
                                    this.classList = classList;
                                }

        So, this class, even without getters and setters, lets us create and populate new Student objects, using just this
    constructor. And let's really do that now, in the main method of the Main class. Let's have some fun with this exercise,
    and put some of the Java skills we've learnt to date to work. We'll create 5 students in a loop. We'll use the loop
    variable to create a unique id. At first, we'll hard code the name as Mary, as well as set dateOfBirth, and classList,
    to some literal values.

                                for (int i = 1; i <= 5; i++) {
                                    Student s = new Student("S92300" + i,
                                            "Mary",
                                            "05/11/1985",
                                            "Java MasterClass");
                                }

    Okay, so here, we create 5 student objects, we pass an id that starts with S92300, then appends the loop index("i"),
    so each student has a different id, and the rest of the values are literals. Let's now replace Mary. We're going to
    put a switch expression, that'll give us a different name, based on the value of the loop index i:

                                for (int i = 1; i <= 5; i++) {
                                    Student s = new Student("S92300" + i,
                                            switch  (i) {
                                                case 1 -> "Mary";
                                                case 2 -> "Carol";
                                                case 3 -> "Korhan";
                                                case 4 -> "Harry";
                                                case 5 -> "Lisa";
                                                default -> "Anonymous";
                                            },
                                            "05/11/1985",
                                            "Java MasterClass");
                                }

    So we're using a switch expression as a parameter to a constructor, which is pretty neat, you have to admit. Maybe you
    noticed that i have a default case label defined, although in this loop, it won't ever be true. This is actually a
    requirement for the switch expression, when it's used with a numeric switch value. It requires that all possible values
    be resolved, and with a numeric switch value, the only way to really do this is, to use a default label, like we did
    here. The last 2 parameters are simply hard-coded to the same birth date, and the class name "Java Masterclass". So,
    we can run this, but since there's no output, it's not terribly interesting. I want to show you another generation
    option on IntelliJ, this one to print out data. When we get to inheritance, I'll explain how this method actually works,
    but for now, let's just use it, because it really makes life a lot easier.

        So, back to our Student POJO, put your cursor before the last closing brace of the class. We'll pick Code, and
    Generate, and now look for the option that says "toString()", and select that. Select all fields, then press OK.

                                @Override
                                public String toString() {
                                    return "Student{" +
                                            "id='" + id + '\'' +
                                            ", name='" + name + '\'' +
                                            ", dateOfBirth='" + dateOfBirth + '\'' +
                                            ", classList='" + classList + '\'' +
                                            '}';
                                }

    And now you see, we have the method called toString, which will print out all the attributes in our class, in a nicely
    formatted way. So the "toString()" method is a special method in Java. We can implement this method in any class we
    create, and doing this lets us print out the current state of our object. IntelliJ, when it generates this code, adds
    a special statement, as you can see, "@override", displayed above the method.

                                                Annotations

        Anytime, you see a statement like this, which starts with an at symbol, this is called an "annotation". Annotations
    are a type of metadata. Metadata is a way of formally describing additional information about our code. Annotations
    are more structured, and have more meaning, than comments. This is because, they can be used by the compiler, or other
    types of pre-processing functions, to get information about the code. And don't worry, we'll be covering the annotations,
    in a later section of the course, as well as introducing you to the most commonly used. Metadata doesn't affect how
    the code runs, so this code will still run, with or without the annotation.

        This particular annotation we see in our code, @Override, is one of the most common annotations in Java that you'll
    use. It tells the compiler, that this is a special type of method in Java, an overridden method.

                                                Overridden Method

        An overridden method is not the same thing as an overloaded method. And overridden method is a special method in
   Java, that other classes can implement, if they use a specified method signature. So, similar to how we create the main
   method, with its special signature, we have to create the toString method a certain way. When we do that, we have access
   to special Java processing, which I'll show you next.

        Popping back over to the Main class, and main method, we can simply print out each one of our student objects,
   using the System.out.println statement:

                                System.out.println(s);

    You might notice, that we didn't actually even call the toString method on the Student, the s variable in this loop,
    in the System.out.println statement. We didn't have to. This is another built-in feature from Java. Every object, when
    passed to System.out.println, will have the toString method implicitly executed, if you've created such a method on
    your class. And now, running this code, we get output:

                            Student{id='S923001', name='Mary', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                            Student{id='S923002', name='Carol', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                            Student{id='S923003', name='Korhan', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                            Student{id='S923004', name='Harry', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                            Student{id='S923005', name='Lisa', dateOfBirth='05/11/1985', classList='Java MasterClass'}

    and we can see the data in our 5 Student objects, or records. That's another pretty handy code generation tool, to know
    we can use. So, this was a contrived, but you can imagine, if you were reading data from a database, or a comma delimited
    file, how you could create a whole set of POJO's, to collect all the data elements, in all your records. Once you have
    all this information in the POJO, you can pass these objects to whatever code would process it, that would need to do
    something with it, perhaps generate a mailing list or whatever.

        So, for good measure, let's add the setters and getters to the POJO. Going back to the Student class, we'll generate
    these. I think you may know how to do it. And now, our code has all we need to manipulate data, setting, updating and
    retrieving data. So, we're lucky we have IntelliJ's code generation tool, to do so much of this work for us. This kind
    of code has a name, it's called boilerplate(basmakalıp) code. It's code that's repetitive and follows a pattern, which
    is why code generation tools can create it for us. But it's still a lot of code to look at, and we only have 4 fields
    in our POJO. Wouldn't it be nice if we had a type that did all this for us, and we didn't have to put all this code
    in our class. Well we do! Java introduced a new type called the "record", which officially became part of Java, in
    JDK 16. 
*/

public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            Student s = new Student("S92300" + i,
                    switch  (i) {
                        case 1 -> "Mary";
                        case 2 -> "Carol";
                        case 3 -> "Korhan";
                        case 4 -> "Harry";
                        case 5 -> "Lisa";
                        default -> "Anonymous";
                    },
                    "05/11/1985",
                    "Java MasterClass");
            System.out.println(s);
        }
    }
}
