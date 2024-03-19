package CourseCodes.NewSections.Section_09_Arrays.Course08_MultiDimensionalArrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                              Multi-dimensional Arrays

        There is no requirement that the nested arrays be any particular size, or even have any values at all. In fact,
    even though we've initialized an array, as a 4x4 matrix of integers, we're not restricted to staying with that. We can
    actually assign any array of integer, to one of the outer array's element, and I'll demonstrate that now.
End-Part-1
*/

        int[][] array2 = new int[4][4];

        array2[1] = new int[] {10, 20, 30};
        System.out.println(Arrays.deepToString(array2));

/*
Part-2
        I'll assign array2's second element, to a new int array, containing 3 elements. Now I'll print the value, returned
    from a call to Arrays.toDeepToString, for array2. On the first line, we set the 2nd element of the outer array, to a
    new integer array. We're using an array initializer with new, int, and [], and then a list of different values, in {}.
    Now notice, there's only 3 elements in this array. This code compiles without issue. And if we run it,

        [[0, 0, 0, 0], [10, 20, 30], [0, 0, 0, 0], [0, 0, 0, 0]]

    we can see that we still have an array of 4 arrays, but now, our second nested array is a list of 3 integers, 10, 20
    and 30. One other thing I want to point out, with this code, is that we can't use an anonymous array, in this assignment.
    What do i mean by that? Let's see what happens, if we remove the new int [] in that last assignment. And you can see
    IntelliJ is flagging that as an error, that an array initializer can't be used here. This means we need the array creation
    part of the code, in other words the new, int and [].

        When we declare multi-dimensional arrays, the declared type can itself be an array, and this is how Java supports
    two-dimensional arrays. We can take that even further, the outer array can have references to any kind of array itself.
    In this example, we have an outer array with three elements.

        Object[] multiArray = new Object[3];
        multiArray[0] = new Dog[3];
        multiArray[1] = new Dog[3][];
        multiArray[2] = new Dog[3][][];

    The first element is itself a single-dimension array. The second element is a two-dimensional array. And lastly, the
    third element is a three-dimensional array.

        The easiest way to examine this multi-dimensional aspect, is to create an array of Object, as I showed above. I've
    said everything can be referred to as an object, including arrays themselves, so I'll use this to help you to understand,
    how Java implements arrays simply as nested arrays.
End-Part-2
*/


        Object[] anyArray = new Object[3];
        System.out.println(Arrays.toString(anyArray));

/*
Part-3
        Setting up our Object array, with 3 non-initialized elements. And finally, I'll print the value returned from
    Arrays.toString, passing our array. I'll run the code,

            [null, null, null]

    you can see we have an array of 3 elements, and they're all initialized to null. Now, we can really assign anything
    we want, to any of these 3 elements, including an array, so let's do that.
End-Part-3
*/

        anyArray[0] = new String[] {"a", "b", "c"};
        System.out.println(Arrays.deepToString(anyArray));

/*
Part-4
        I'll assign element zero on our array, to a new string array. And print the value, returned from a call to Arrays.deepToString.
    And running this,

            [[a, b, c], null, null]

    we can now see that we have a nested array, and that we've nested the ABC String array, in our outer array. At this
    point, you could say we have a 2D array. But let's keep going with this. Let's set element 2 of our array, to a 2D
    array.
End-Part-4
*/

        anyArray[1] = new String[][]{
                {"1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8", "9"}
        };
        System.out.println(Arrays.deepToString(anyArray));

/*
Part-5
        Adding numbers, 1 through 9, as separate strings. And let's print out anyArray,

            [[a, b, c], [[1, 2], [3, 4, 5], [6, 7, 8, 9]], null]

    Finally, I'll initialize the last element.
End-Part-5
*/

        anyArray[2] = new int[2][2][2];
        //        anyArray[2] = "Hello";
        System.out.println(Arrays.deepToString(anyArray));

/*
Part-6
        I'll assign element 3, to a 3D array, and another print of anyArray. Ok, so let's run this,

            [[a, b, c], [[1, 2], [3, 4, 5], [6, 7, 8, 9]], [[[0, 0], [0, 0]], [[0, 0], [0, 0]]]]

    So this is a bit hard to look at, and figure out, which arrays are nested in which. We'll print these nested arrays,
    by using a for each loop.
End-Part-6
*/

        for (Object element : anyArray) {
            System.out.println("Element type = " + element.getClass().getSimpleName());
            System.out.println("Element toString() = " + element);
            System.out.println(Arrays.deepToString((Object[]) element));
        }

/*
Part-7
        Notice, in this particular for each loop, we're using Object as the type of our element, in the loop declaration.
    First, we print out the class name of each element in this array. Second, we print out the toString representation,
    of each array element. And then we want to print out each element contained in this multi-dimensional array. Now, we
    happen to know that each element is an array, but the Java compiler doesn't know that.

        The "Arrays.deepToString" method takes an array, so we need to cast our element to an array of Object, before we
    pass it to this method. Now, if we'd assigned something other than an array, this would break the code, but we didn't.
    We'll talk more about this issue later. Running this,

            Element type = String[]
            Element toString() = [Ljava.lang.String;@3feba861
            [a, b, c]
            Element type = String[][]
            Element toString() = [[Ljava.lang.String;@5b480cf9
            [[1, 2], [3, 4, 5], [6, 7, 8, 9]]
            Element type = int[][][]
            Element toString() = [[[I@b4c966a
            [[[0, 0], [0, 0]], [[0, 0], [0, 0]]]

    we can see each type printed out, so the type for the first element, the element type is String with a single "[a, b, c]".
    The second element is a 2D string array, "[[1, 2], [3, 4, 5], [6, 7, 8, 9]]". And the last one is a 3D integer array,
    "[[[0, 0], [0, 0]], [[0, 0], [0, 0]]]", with all its values set to the default zero. And we can also see the elements
    printed out in each case.

        In general, it's not good practice to create Object arrays like this. This is because the compiler won't do any
    type checking, meaning anything can be assigned to this outer array, and this code wouldn't work if we simply assigned
    a String to anyArray[2], as an example. Let's just do to see what happens.

            anyArray[2] = "Hello";

    I'm going to type this in a statement. You can see this above as "commented". Now, with this change in place, the
    Arrays.deepToString method still works, the one that we call immediately after that. But the call to the deepToString
    in the for loop, will give us an exception if we run it.

        We're trying to cast something that's a String to an Array of Object, and we can't do that. When using arrays, you
    should really stick to more strictly typed arrays, as a general rule. This will prevent any calling code, from inserting
    object types that you don't expect, and can't handle, just like we did here.
End-Part-7
*/
    }
}
