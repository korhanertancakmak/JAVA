package CourseCodes.NewSections.Section_07_OOP1.Course16_JavaLangObject;

/*
Course-46

                                              What is Java.lang.Object?

        In the last couple of courses, we introduced you to inheritance, and how it works with a class hierarchy. We talked
    about the base class, also called the super class, and subclasses. We demonstrated overriding methods, and we gave you
    a quick look at how inheritance plays into polymorphism in Java. Maybe, you're thinking that Inheritance looks kind of
    interesting, but when would we really use it?

        Well, it turns out, in Java, we've been using Inheritance all along, without even knowing it. This is because every
    class you create in Java, intrinsically extends a special Java class. This class is named Object, and it's in the
    java.lang package. Ok, that's confusing, a class called Object? Let's see what Java has to say about this class.

        From the link to Java's Application Programming Interface(API) for this class, you can see the oracle's page. Class
    Object is the root of the class hierarchy. Every class has Object as a superclass. All objects, including arrays, implement
    the methods of this class. Whether you knew it or not, your classes were extending this class, Object. They all inherit
    from Object. And what that means is, all of your classes, have functionality built in that you can use or override,
    the minute you create them. Let's explore the methods on java.lang.Object. We can select the method tab from the Summary
    list. And here, you can see some of the methods on Object, like "clone()" and "equals()". And also here, is one we just
    showed in the last couple of courses, the "toString()" method.

        Every class we create or use, is automatically inheriting from this Java-supplied class, called the Object class.
    Let's get back to some code in IntelliJ, and see what this means, and how it works. We've said every class inherits
    from Object, and this actually includes the Main class. How can we tell? We can use the Code generation tools, and
    you can see that, by opening up your Generate command, if you go to Override Methods. When you've done that, select
    methods to override or to implement. And you can see, all the methods. The M stands for method. And these are all methods
    that are inherited from java.lang.Object, which is a class that classes you create, automatically extend. That's equivalent,
    essentially, to typing extends Object, java.lang.Object, so let's do that in our Main class:

                        public class Main extends java.lang.Object{
                            public static void main(String[] args) {

                            }
                        }

    We could also just omit the package name, java.lang, and just say our class extends Object. At the end of this section
    of the lecture, we'll be talking more about packages. Let me do that:

                        public class Main extends Object{
                            public static void main(String[] args) {

                            }
                        }

    For now, I'll just say that Java has a way of implicitly doing things, that make our jobs easier. One of these is to
    include all Objects from its core libraries automatically, so we can refer to them like we do here, as simply Object
    for example. The other is to implicitly have all classes extend this Object class, that do not explicitly extend from
    another class. Just to be clear, we can show this on a class diagram:

                        Object =>
                                    -------------------------
                                    clone() : Object
                                    equals(obj:Object) : boolean
                                    hashCode() : int
                                    toString() : String
                                              ↑
                      |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|
                   Main =>                                            String =>
                           --------------                                       -------------
                           static main()                                        charAt(int,index): char
                                                                                ...
                                                                                equals(obj:Object): boolean
                                                                                ...
                                                                                toString(): String
                                                                                ...
                                                                                static valueOf(Object obj): String

    This diagram shows that our Main class inherits from, or is a subclass of Object, as also is String, a class we are
    pretty familiar with already. This shows the methods on Object, Main, and just a few of the methods on String. The
    String class has over 60 methods! In alphabetical order, these methods start with one called charAt, and end with one
    called valueOf. The String class overrides several method on Object, 2 of which are equals(), which we'll be discussing
    in our lecture on all things String, and toString(), which we used in the previous lectures. All objects, including
    arrays, inherit the methods of the Object class. That's really important to know this happens automatically.

        Let's explore toString a little more, using Description.txt. Let's add a new class in Description.txt itself, and we'll call
    it Student. Remember, only one class in a Java source file can be public, and since we've already made Main public,
    we won't make Student public:

                    class Student {
                        private String name;
                        private int age;

                    }

    That's a simple class named Student, with a name, and an age. Let's add a constructor so we can quickly pass data to
    the class, when we create it:

                    Student(String name, int age) {
                        this.name = name;
                        this.age = age;
                    }

    Now, let's create an instance of Student in our main method, and print Student out:

                    Student max = new Student("Max", 21);
                    System.out.println(max.toString());

    And running the code:

                    Student@5f184fc6

    You can get something different, but that's fine, and I'll explain it soon. What is that? Well, the code in the toString
    method in Object class, prints out the classname(which in our case is Student), followed by an at sign, then the hashCode
    of the object. A hashCode is an integer, that is unique to an instance(in the currently executing code). When an instance
    is created, it's assigned a hashCode, and that hashCode is what can tell us if our multiple references, are pointing
    to a single instance. It's a mechanism for comparison, in other words. This really is like an address for a house, which
    we talked about in our examples, to explain the difference between references and objects or instances. Anyway, this
    isn't what we want printed out, we really want to print out the name and age of our student. And we've done this before,
    but now, I hope it's becoming clear what we're doing. We're overriding the toString method on Object. Let's do that on
    Student.

                    @Override
                    public String toString() {
                        return super.toString();
                    }

    And now we have a toString method, that simply calls super.toString(). Do you think that does anything different?
    Maybe you'll remember, we've said that calling a method on super like this, without doing anything else, is kind of
    redundant. Let's confirm that by running the code:

                    Student@5f184fc6

    And we get the same result. We've really just explicitly called the code, that Java would have implicitly done for us.
    Let's comment that code out, and try the other option next.

                    @Override
                    public String toString() {
                        return "Student{" +
                                "name='" + name + '\'' +
                                ", age=" + age +
                                '}';
                    }

    We've done this before, we're simply specifying, for this class, what we want to print out. Running this code now gives
    us:

                    Student{name='Max', age=21}

    The Student class name still, but now in brackets, we can see this object has name equals Max, and age = 21. Let's change
    this and just simplify it. We don't really care that the class name is Student. We'll comment out the default generated
    code, and simply return the student name, and say how old they are:

                    @Override
                    public String toString() {

                        return name + " is " + age;
                //        return "Student{" +
                //                "name='" + name + '\'' +
                //                ", age=" + age +
                //                '}';
                    }

    And if we run that:

                    Max is 21

    we get. You make this code to be whatever you want it to be, when the toString method is called on your class. You may
    also remember, that i told you Java implicitly calls the toString method on an object, if you simply pass your object
    to System.out.println. Let's confirm that, by changing System.out.println, and removing the .toString()

                    Student max = new Student("Max", 21);
                    System.out.println(max.toString());

    Here, we simply pass the max Student object, to the println method on System.out. And if we run that,

                    Max is 21

    we can confirm that this code is really calling the toString method on Student, and Max is 21 is still printed out.
    And what happens, if we now have another class, that extends Student? Let's create a PrimarySchoolStudent class, which
    would be a student who is age 5 to 12 for example, in the elementary or primary school. And this student will have a
    parent name associated with it. For the sake of simplicity, we'll just include one parent. Let's look at the class
    diagram:

                Object                =>
                                        ------------------------
                                        clone():Object
                                        equals(obj:Object):boolean
                                        hashCode():int
                                        toString():String
                   ↑
                Student               =>
                                        name:String
                                        age:int
                                        -------------------------
                                        toString:String
                   ↑
                PrimarySchoolStudent  =>
                                        parentName:String
                                        -------------------------
                                        toString:String

    We've already built Student, which inherits from Object implicitly. Next, we'll build PrimarySchoolStudent, which will
    inherit from Student. Let's add this new class:

                    class PrimarySchoolStudent extends Student {
                        private String parentName;
                        public PrimarySchoolStudent(String name, int age, String parentName) {
                            super(name, age);
                            this.parentName = parentName;
                        }
                    }

    Hopefully, you understand what we're doing here with this code. We've created a PrimarySchoolStudent class, that extends
    Student. We've added an attribute, specific to a primarySchoolStudent, and called it parentName. And we've set up a
    constructor, with 3 fields, 2 fields we had for student, name and age, and now parent's name as well. We call super(),
    with name and age in parentheses, to call Student's constructor. And we set the parentName to the argument with the
    same name. Let's create a PrimarySchoolStudent in the main class.

                    PrimarySchoolStudent jimmy = new PrimarySchoolStudent("Jimmy", 8, "Carole");
                    System.out.println(jimmy);

    And running that:

                    Max is 21
                    Jimmy is 8

    we see that we get "Jimmy is 8", as the output for this code. This code inherited Student's toString method code. And
    now we can override toString again, this time on PrimarySchoolStudent.

                    @Override
                    public String toString() {
                        return super.toString();
                    }

    Here, we'll return the parentName, and then call the toString method on Student with the super keyword.

                    @Override
                    public String toString() {
                        return parentName + "'s kid, " + super.toString();
                    }

    And when we run that:

                    Max is 21
                    Carole's kid, Jimmy is 8

    We get the output, "Carole's kid, Jimmy is 8". In this case, super.toString didn't call Object's toString method, it
    called Student's. But it still inherits Object's other functionality, indirectly through Student.

        Java only supports one class in the extends class. For example, we couldn't type Student, then Object:

                    class PrimarySchoolStudent extends Student, Object {
                                    ...//
                    }

    This is a compile error. The inheritance tree is cumulative, meaning that PrimarySchoolStudent inherits both Student
    members, and Object members. Object members are accessible, as long as Student doesn't override them. Because Student
    overrode toString(), we no longer can simply call the toString implementation on Object, from PrimarySchoolStudent.

        We learned that all classes, which do not explicitly extend another class, will implicitly extend a Class named
    Object. Object is the base class, or root class, of every class in Java, which means all class's can use, or override,
    Object's methods.
*/


public class Main extends Object{
    public static void main(String[] args) {

        Student max = new Student("Max", 21);
        System.out.println(max.toString());

        PrimarySchoolStudent jimmy = new PrimarySchoolStudent("Jimmy", 8, "Carole");
        System.out.println(jimmy);
    }
}

class Student {

    private String name;
    private int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }

    @Override
    public String toString() {

        return name + " is " + age;
//        return "Student{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
    }
}

class PrimarySchoolStudent extends Student {

    private String parentName;

    public PrimarySchoolStudent(String name, int age, String parentName) {
        super(name, age);
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return parentName + "'s kid," + super.toString();
    }
}