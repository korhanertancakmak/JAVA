package CourseCodes.NewSections.Section_09_Arrays.Course03_ReferenceAndValueTypes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                     Reference Types vs Value Types

        In a previous video, I talked about the differences between a Reference vs. an Object, vs. an Instance, vs. a Class.
    I want to revisit this a little, and talk about why this matters, when we're talking about arrays. When you assign an
    object to a variable, the variable becomes a reference to that object. This is true of arrays, but the array has yet
    another level of indirection, if it's an array of objects. This means every array element is also a reference.

End-Part-1
*/

        int[] myIntArray = new int[5];
        int[] anotherArray = myIntArray;

/*
Part-2
        A reference is an address to the object in memory, but not the object itself. Stated differently, the variable
    myIntArray, holds a reference or address, to an array in memory. And we use a reference to access and control the object
    in memory. Here, in the second, I'm declaring another reference to the same array in memory, so now we've got 2 references,
    pointing to the same array in memory. In other words, both myIntArray and anotherArray, hold the same address. One way
    to know it's a reference type, is the new operator, because that creates a new object in memory, and we're using the
    new operator on line 20, where we created an array of 5 elements. Basically new here means, new object. On the second
    line, we haven't used the "new" keyword. We've just used the equal sign, which means we're assigning 1 reference to
    another. Let's print these array reference out.
End-Part-2
*/

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));
/*
Part-3
        We've got all zeros, because all we've done is initialized the array, with a size and the default values. Let's
    actually see what happens, if we make a change, to one array:
End-Part-3
*/
        anotherArray[0] = 1;

        System.out.println("after change myIntArray = "
                + Arrays.toString(myIntArray));
        System.out.println("after change anotherArray = " +
                Arrays.toString(anotherArray));

/*
Part-4
        I'll add a call to Arrays.toString, for myIntArray, and print what is returned. And do the same for anotherArray.
    And printing out both reference variables again, after we make the change. Will the anotherArray be changed? When we
    run that:

        myIntArray = [0, 0, 0, 0, 0]
        anotherArray = [0, 0, 0, 0, 0]
        after change myIntArray = [1, 0, 0, 0, 0]
        after change anotherArray = [1, 0, 0, 0, 0]

    And you can see what's happened, with those last 2 output statements. Both variables are referencing the same array
    in memory, so there's only one copy of the array. When you use 1 reference variable to make changes to the object in
    memory, it's like making that change, with the other variable.
End-Part-4
*/

    }
}
