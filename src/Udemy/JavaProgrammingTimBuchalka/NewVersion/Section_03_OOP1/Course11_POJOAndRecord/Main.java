package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course11_POJOAndRecord;


/*
Course-42

                                            The POJO vs. The Record

         In the last video, we talked about the Plain Old Java Object, and we showed you how it comes with a lot of what
    we call boilerplate code. It's code that's repetitive and follows certain rules. In our case, we had a constructor with
    parameters for every field on our class, as well as getters and setters for each field. We also set up a toString method
    on this class, so we could print out all the fields on our class in a nicely formatted way. This is code that's repeated
    over and over, for every POJO, or for every JavaBean, if you're using JavaBeans to leverage Java frameworks that use
    them. Once created, this code is rarely looked at, or modified. In fact, there are tools that'll just regenerate all
    of this code, if your underlying data, or domain model changes. Even better though, Java introduced a new type, the
    record, which became part of the official language, in JDK 16.

                                                The Record Type

        The record was introduced in JDK 14, and became officially part of Java in JDK 16. Its purpose is to replace the
    boilerplate code of the POJO, but to be more restrictive. Java calls them "plain data carriers". The word carrier is
    an important term, because it means the record has more rules built-in then a POJO would. The record is a special class
    that contains data, that's not meant to be altered. In other words, it seeks to achieve immutability, for the data in
    its members. It contains only the most fundamentals methods, such as constructors and accessors (or getters). Best of
    all, you the developer, don't have to write or generate any of this code.

        We're back to our last course. In that course, we created a Student class with a constructor and some getters and
    setters, then we used a for loop to create 5 students. Let's create a record now. As we create a default class, but at
    this time, I'm going to pick Record, instead of the default Class, we've been using all along up until now. You'll see
    there are others, like Interface, Enum and Annotation, but don't worry, we'll be talking about those eventually. Right
    now, we care about the Record type. And I'll give it a name, LPAStudent, LPA stands for my own company name, Learn
    Programming Academy, and then Student.

                                    public record LPAStudent() {
                                    }

        Now you can see, this (LPAStudent.java file) looks like a class, it starts with a public access modifier, but instead
    of the "class" keyword, it's using the "record" keyword. Where's the magic you ask? It's hard to see at first. But,
    different from a class, you'll notice that, in this code, there are a set of parentheses after the name of this record,
    LPAStudent. Similar to a constructor, we can set up some parameters within those parentheses, so let's set some up, so
    that the parameters are the same as our Student class parameters. In fact, let me just copy that parameter list, from
    the Student class constructor. I'll pop over to Student.java, copy the parameter list I've set up in that first constructor,
    "String id, String name, String dateOfBirth, String classList", then i'll come back over to LPAStudent.java, and paste
    those parameters in the parentheses. And that's it! We're ready to use this record now, in our code. It's really that
    simple. Let's go to Description.txt, and show you.

        Before we make any changes, let's run the main code as it is, and you'll remember we get the output, from the
    toString() method we'd generated in the Student class.

                        Student{id='S923001', name='Mary', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                        Student{id='S923002', name='Carol', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                        Student{id='S923003', name='Korhan', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                        Student{id='S923004', name='Harry', dateOfBirth='05/11/1985', classList='Java MasterClass'}
                        Student{id='S923005', name='Lisa', dateOfBirth='05/11/1985', classList='Java MasterClass'}

    Now let's use our new record. I'm simply going to use LPAStudent, in place of Student in the code here. Where i have
    Student, I'll modify it to be LPAStudent, the name of our record. I'll use LPAStudent as the variable, or reference
    type. And on the same line, I'll use LPAStudent after the new keyword, instead of Student.

                        for (int i = 1; i <= 5; i++) {
                            LPAStudent s = new LPAStudent("S92300" + i,
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

    This code compiles, and if we run it:

                        LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]

    We get output, that's very similar, to the output we got before. The bracket type is different. We've replaced the
    58 lines of code, that we have in Student.java, with these 4 lines of code in LPAStudent.java. Now, the truth is, the
    record, LPAStudent doesn't have or support setter methods, but the other functionality, calling the constructor with
    4 parameters, and printing the data out, is implicitly part of the record. What's really happening here?

                                Implicit or Generated Code that Java provides

        What does Java tell us about what is implicitly created, when we declare a record as we did in this code?

                        public record LPAStudent(String id, String name, String dateOfBirth, String classList) {}

    First, it's important to understand that the part that's in parentheses, this entire part is called the record header.
    The record header consists of components, a comma delimited list of components. For each component in the header, Java
    generates:

  - A field with the same name and declared type as the record component. So for example, it sets up fields for us, as we
  have them in the parentheses. These become the fields of the record.
  - The field is declared private and final. We'll be talking about "final" more later, but simply put, it means the field
  can't be modified.
  - The field is sometimes referred to as a component field.

    Java generates a toString() method that prints out each attribute in a formatted String. In addition to creating a private
    final field for each component, Java generates a public accessor method for each component. This method has the same
    name and type of the component, but it doesn't have any kind of special prefix, no get, or is, for example. The accessor
    method, for id in this example, is simply id(). This is easier to show you in the code. Let's create 2 Students, in
    main method, one will use our POJO class, and one will use our record:

                        Student pojoStudent = new Student("S923006", "Ann",
                                "05/11/1985", "Java MAsterclass");
                        LPAStudent recordStudent = new LPAStudent("S923007", "Bill",
                                "05/11/1985", "Java Masterclass");

    Now let's print some information out about each of these students:

                        System.out.println(pojoStudent);
                        System.out.println(recordStudent);

    And if we run that:

                        LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java Masterclass'}
                        LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]

    We can see our 2 new students at the end of the output, and notice the minor difference in the output. This shows us,
    that like IntelliJ's generated method, toString, the record is printing information out similarly, though it doesn't
    include single quotes around Strings, and the brackets are different.

        Now let's just print out a couple of the attributes ourselves, using the accessor (or getter) methods. We'll do
    this first for our POJO student:

                        System.out.println(pojoStudent.getName() + " is taking " +
                                pojoStudent.getClassList());
                        System.out.println(recordStudent.getName() + " is taking " +
                                recordStudent.getClassList());

    Now, you can see we have compiler errors for getName and getClassList, on that new line of code. We stated that Java's
    implicit code doesn't include the prefix get, and simply uses the same name as the component, or field. Let's remove
    the prefix, and change the case of Name and ClassList, to use lower camel case:

                        System.out.println(pojoStudent.getName() + " is taking " +
                                pojoStudent.getClassList());
                        System.out.println(recordStudent.name() + " is taking " +
                                recordStudent.classList());

    And we can run that:

                        LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java MAsterclass'}
                        LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]
                        Ann is taking Java Masterclass
                        Bill is taking Java Masterclass

    And we get the output Anne, is taking Java Masterclass, and Bill, is taking Java Masterclass. That's how we use accessor
    methods, which we've called getters up until now, because traditionally, the prefix get was used for the accessor method.
    And finally, let's see what happens, if we try to set data on these 2 types of students. Let's say they've both added
    a class, my Java OCP Exam 829 course, for example.


                        pojoStudent.setClassList(pojoStudent.getClassList() + ", Java OCP Exam 829");
                        recordStudent.setClassList(recordStudent.classList() + ", Java OCP Exam 829");

                        System.out.println(pojoStudent.getName() + " is taking " +
                                pojoStudent.getClassList());
                        System.out.println(recordStudent.name() + " is taking " +
                                recordStudent.classList());

    And here, you can see the line for "recordStudent" doesn't compile. It doesn't have a setter named setClassList. In
    fact, it doesn't have a setter at all. There is no way to set the class list, other than by passing the value in on
    the record header, or through the use of constructors. This is by design because a record's goal is to be immutable.
    Let's comment out that last line of code, and run our Description.txt class again.

                        LPAStudent[id=S923001, name=Mary, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923002, name=Carol, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923003, name=Korhan, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923004, name=Harry, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        LPAStudent[id=S923005, name=Lisa, dateOfBirth=05/11/1985, classList=Java MasterClass]
                        Student{id='S923006', name='Ann', dateOfBirth='05/11/1985', classList='Java Masterclass'}
                        LPAStudent[id=S923007, name=Bill, dateOfBirth=05/11/1985, classList=Java Masterclass]
                        Ann is taking Java Masterclass, Java OCP Exam 829
                        Bill is taking Java Masterclass

    And here you can see, for our POJO student, we could modify the class list.

  - Why have an immutable record?

        There are more use cases for immutable data transfer objects, and keeping them well encapsulated. You want to protect
    the data from unintended mutations.

                                                    POJO vs. Record

        If you want to modify data on your class, you won't be using the record. You can use the code generation options
    for the POJO, as we showed in the earlier. But if you're reading a whole lot of records, from a database or file source,
    and simply passing this data around, then the record is a big improvement.

        We've only touched on some of the features of the record, to give you an introduction. When we do talk more about
    the "final" keyword, and immutability of data, especially as it may be affected by concurrent threads, we'll be revisiting
    this type. We'll also be showing it to you in action, when we get to the Database and I/O sections of this course.

*/


public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            LPAStudent s = new LPAStudent("S92300" + i,
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

        Student pojoStudent = new Student("S923006", "Ann",
                "05/11/1985", "Java Masterclass");
        LPAStudent recordStudent = new LPAStudent("S923007", "Bill",
                "05/11/1985", "Java Masterclass");

        System.out.println(pojoStudent);
        System.out.println(recordStudent);

        pojoStudent.setClassList(pojoStudent.getClassList() + ", Java OCP Exam 829");
        //recordStudent.setClassList(recordStudent.classList() + ", Java OCP Exam 829");

        System.out.println(pojoStudent.getName() + " is taking " +
                pojoStudent.getClassList());
        System.out.println(recordStudent.name() + " is taking " +
                recordStudent.classList());
    }
}
