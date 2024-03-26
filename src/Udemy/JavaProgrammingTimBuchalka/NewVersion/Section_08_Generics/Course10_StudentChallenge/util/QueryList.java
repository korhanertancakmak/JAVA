package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.util;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course10_StudentChallenge.model.Student;

import java.util.ArrayList;
import java.util.List;

//Part-2
/*
        The first thing we want to do is change the QueryList class and extend ArrayList. This means we won't have to
    manage all the elements, we can let the ArrayList's functionality handle all of that. I'll add extends ArrayLists,
    after the type parameter.

                public class QueryList <T extends Student & QueryItem>
                                                to
                public class QueryList <T extends Student & QueryItem> extends ArrayList<T>

    Notice that IntelliJ is showing us a warning on ArrayList when we do that, and by now you should be getting used to
    this message, raw use of parameterized type. Even when we're extending classes, we want to use a type argument. Can
    you guess what we'd use there? Well, just T, which is the type parameter for this class, and will be the type argument,
    when we extend ArrayList. Now, I want to remove the private field, items, actually I'll just comment that out.
*/
//End-Part-2

public class QueryList <T extends Student & QueryItem> extends ArrayList<T> {

    //private List<T> items;

//Part-3
/*
        This leaves me with a couple of errors in this class, the first is in the constructor. I'll comment out that
    statement. Instead of assigning the argument to items, I'll instead call the super constructor, and pass items as
    its argument. I also want to add a no args constructor for this class, I'll insert that as the first constructor.
    The next error is in the getMatches method, where we're looping through the items.
*/
//End-Part-3

    public QueryList() {

    }

    public QueryList(List<T> items) {

        super(items);
        //this.items = items;
    }

//Part-4
/*
        Instead of looping through another field, called items, I can just change the reference, replacing items, with
    the "this" keyword.

                        for (var item : items)
                                to
                        for (var item : this)

    I also want to return a QueryList type,

                        public List<T> getMatches(String field, String value)
                                                to
                        public QueryList<T> getMatches(String field, String value)

    this will let me chain calls to getMatches, which I'll implement later.

                        List<T> matches = new ArrayList<>();
                                        to
                        QueryList<T> matches = new QueryList<>();

    That's it for the QueryList class. This class has all the the functionality of an ArrayList, but includes functionality
    to search the data by field values. The next thing on the list is to add student id to Student, so let's do that.
*/
//End-Part-4

    public QueryList<T> getMatches(String field, String value) {

        QueryList<T> matches = new QueryList<>();
        for (var item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

}
