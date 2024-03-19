package CourseCodes.NewSections.Section_06_ControlFlow;

/*
Course-27

                                The class, the object, static & instance fields and methods

        In the last course, we talked about local variables and scope. Local variables are a way to store and manipulate
    temporary data. In addition to local variables, we can set up data to be defined, and used as part of a class, or an
    object.

                                             A class
        A class can be describes as:

      - a custom data type.
      - a special code block that contains method.

        A class is like an empty form that gets copied and handed out. It describes information, or placeholders, for data
    that'll be filled in, when that form is given to a unique individual.

        The class may have a field for name, the object will have a value in the name field, which will be unique to the
    object. The class may have a field for address, the object will have values for the address field, and so on. The process
    of copying that empty form, and then delivering it to some process or person to fill in the blanks, is a loose analogy
    to what happens when you create an object. The empty form (the class) is the template for the data to be collected.
    The populated form(the object) may be completely different each time, because of the values used to fill in the data.
    But the data being collected each time, is determined by the class, or the form in this analogy.

                                             An Object

        An object is called an instance of a particular class. We'll often use the word "instance" interchangeably with
    "object". This means an object is created by instantiating a class. There are multiple ways do that, and we'll be
    talking about that more in a bit. There is no limit to the number of objects you can create from a class. A class is
    sometimes compared to a cookie cutter, and the cookies are your objects. The class provides a shape, or framework, that
    describes the object being created. A template is another word you'll hear used, when trying to describe a class's
    relationship to an abject.

                                Declaring and instantiating a new object from a class

        The most common way to create an object, is to use the new keyword. The new keyword creates an instance, and you
    can sometimes pass data, when creating an instance, to set up data on that object.

        The String is special because we can create a String, just by using a literal which we've seen.

                                    String s = "Hello";

        But we could also use new:

                                    String s = new String("Hello");

        Like other data types, you can assign this objects memory location, also called a reference, to a local variable,
    as we've done with this String. We've assigned to the local variable s, an instance of String. All manipulation of the
    object's data and methods, are then done using the local variable name.

        In both of the statements for String above, we're creating a new object of type String, and initializing it with
    the text "Hello", and assigning it to a String variable named s. The second statement makes this a bit clearer. When
    we create an object, we can pass initial data to be associated with it in parentheses.

        We've stated previously, that the class can be thought of as a special data type. This is because you can create
    variables on classes. These are called fields, or attributes, on the class or object. There are two ways to create
    fields on classes: One is with the "static" keyword, and one is without the "static" keyword.

                                            Static and Instance Fields

                        Static Field                                          Instance Field

       - Requires 'static' keyword when declared                     - Omits 'static' keyword when declared
         on the class                                                  on the class.
       - Value of the field is stored in special                     - Value of the field is not allocated any memory
         memory location and only in one place.                        and has no value until the object is created.
       - Value is accessed by ClassName.fieldname                    - Value is accessed by ObjectVariable.fieldname
         Ex: Integer.MAX_VALUE                                         Ex: myObject.myFieldName
                                                                       (myObject is our variable name for an object
                                                                       we create and myFieldName is an attribute on
                                                                       the class.)

        When the "static" keyword is used, it's called a static field on the class. This means the value of that field
    always stays with the class.
        It's stored in a special memory location for values that aren't changing constantly, unlike local variables and
    objects. In our form analogy, this would be a field that is pre-populated on the form, and would not change for any
    of the copied forms. But unlike the form, this type of field in a class, doesn't really get copied down to the object.
    It maintains it's single value on the master copy, the class. The field in memory is accessed differently, because
    we can access that field using the class name with dot notation, and we've done that already (Integer.MAX_VALUE).
    This data was stored on the class (INTEGER), and not on an instance of the class.

        When the static key word isn't used, it's called an instance field. Until the class is instantiated, and an object
    created, the instance field has no place in memory. These instance fields can have different values for every instance
    created. This field is accessed using the variable name for the object, and the dot notation used with the field name.
    In the same way that there are static fields and instance fields, there are static methods and instance methods.


                                            Static and Instance Methods

                        Static Method                                          Instance Method

       - Requires 'static' keyword when declared                     - Omits 'static' keyword when declared
         on the class                                                  on the class.
       - Method is accessed by ClassName.methodName                  - Method is accessed by ObjectVariable.methodName
         Ex: Integer.parseInt("123);                                   Ex: "hello".toUpperCase();
         (A method called parseInt is called directly)                 (A method called toUpperCase is called on the
          from the Class, Integer.)                                    instance of a String with value "hello".)

        A static method can be called directly, using the Class name and dot notation. In other words, you don't need an
    instance, to use this method.
        An instance method requires an instance exists first, and the method be called on that instance. String class has
    many instance methods defined on it, which we can use to manipulate Strings. One of these is the toUpperCase method,
    which simply returns a String with all upper case letters, so the result of this call would be a new String, with the
    value "HELLO", all in capital letters.

        The most important difference to remember right now, is that to use an instance method, you have to create an
    instance, or object, first.

        We're going to be going over them in much more detail very shortly.
*/
public class Course11_ClassObjectStaticMethods {
}
