package CourseCodes.NewSections.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds.util;

import CourseCodes.NewSections.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds.model.Student;

import java.util.ArrayList;
import java.util.List;

//Part-3
/*
        This list will allow users to query or search the list, looking for matches, when they specify a field, and a
    value in that field. For example, if I want to get a list of all students taking the Java course, I can pass course
    as the field to use, and pass Java as the value to match on. In this class, I want include a type parameter that extends
    the interface I just created. I'll add a field, the List of that type. And I'll generate a constructor, using items
    as the argument. Now, I aso want to add a method, that will take a field name and a value, and try to find a match
    in the list. And I'm going to loop through all items. I'll test for the field value to match field value. If it is
    true, add the item to matches. Finally, I want to return matches.
*/
//End-Part-3
public class QueryList <T extends Student & QueryItem> {

//Part-12
/*
                    public class QueryList <T extends QueryItem>
                                        to
                    public class QueryList <T extends Student & QueryItem>

    Here, I'm saying that any type that uses this class, must be a Student or subtype of the Student class, and it also
    must implement the QueryItem interface.

                    public class GenericClass<T extends AbstractClassA & InterfaceA & InterfaceB> {}

          - "Extends" for class & interface
          - "Class must be listed first
          - "&" means any type must be subtype of ALL
          - Interface(s) follow class

    You can use multiple types to set a more restrictive upper bound, with the use of an ampersand between types. The
    conditions require a type argument, to implement all interfaces declared, and to be a subtype of any class specified.
    You can extend only one class at most, and zero to many interfaces. You use extends for either a class or an interface
    or both. If you do extend a class as well as an interface or two, the class must be the first type listed.

        Now notice what happens, if I add another interface, at the start of this upper bound expression. I'll add Comparable.

                    public class QueryList <T extends Comparable & Student & QueryItem>

    And you can see, I've got an error on Student, by doing this, and I get the message, "interface expected here". This
    is the same as saying Student, because it's a class, must come first. I'll revert that last change, and leave the
    upper bound as Student & QueryItem. Let me create a record in the Main.java source file real quick, to test this
    restriction. I'll create a record Employee.
*/
//End-Part-12

    private List<T> items;

    public QueryList(List<T> items) {
        this.items = items;
    }

//Part-4
/*
        This method sets up a new List, of type T. It loops through the current items, and then leverages the method on
    the interface. This means it expects any item to have implemented this method, matchFieldValue. Now I need to go to
    my Student class, and have it implement Query Item.
*/
//End-Part-4

    public List<T> getMatches(String field, String value) {

        List<T> matches = new ArrayList<>();
        for (var item :items) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

//Part-9
/*
        I'm going to copy the getMatches method, and paste it right below. I want this to be static, and include a parameter
    for any list to be passed, items, as the first parameter, which will be a List.

                    public static List<T> getMatches(List<T> items, String field, String value)

    Now, you'll notice that T's underlined in all cases. Hovering over that, IntelliJ gives us the information,

                    'QueryList.this' cannot be referenced from a static context

    What does that mean? Well, it means that the class's type parameter can't be used for a static method. The generic
    class's type parameter only has meaning for an instance, and therefore for an instance method. At the class level,
    this is unknown. When the generic class is loaded into memory, it's not loaded with any type parameter, so you can't
    use it in a static method, which is what I'm really trying to do here. But, I can make this a generic method, which
    I covered in the previous lecture. I'll now add a type parameter, which goes right before the return type, the List
    of the type being returned.

                           Before                                                     After
                public static List<T> getMatches                      public static <T> List<T> getMatches

    But I still have to make an upper bound for this, that extends the QueryItem, so I'll do that.

                            Before                                                     After
              public static <T> List<T> getMatches                    public static <T extends QueryItem> List<T> getMatches

    Ok, so what does this really mean again? In this case, what this actually means, is that this T, this type parameter,
    is a totally different type, completely separate from the type parameter, declared for the class itself. In fact, this
    type either gets specified, or inferred, when you invoke this static method on the class. Let me go back to the main
    method, and now call this static method.
*/
//End-Part-9

    public static <S extends QueryItem> List<S> getMatches(List<S> items, String field, String value) {

        List<S> matches = new ArrayList<>();
        for (var item :items) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

//Part-11
/*
        I want to reiterate that the type on that generic method, is not the same type as the class itself. In fact, let's
    change the type on the method to S, in all cases.

                public static <T extends QueryItem> List<T> getMatches(List<T> .....
                List<T> matches = new ArrayList<>();
                                           to
                public static <S extends QueryItem> List<S> getMatches(List<S>
                List<S> matches = new ArrayList<>();

    And that code compiles and works as before, so even though our generic class doesn't have an S type, it doesn't matter.
    A generic method's type is unrelated to the type declared on the generic class. Now, let's say we really only want
    this QueryList class, to work for Students, and subtypes of students, as well as only those types, that implement
    the QueryItem interface. We can do this by specifying multiple upper bounds. Let me do that, then I'll talk about a
    few rules for this.
*/
//End-Part-11

}
