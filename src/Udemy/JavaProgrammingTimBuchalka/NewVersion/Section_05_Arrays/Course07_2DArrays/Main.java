package CourseCodes.NewSections.Section_09_Arrays.Course07_2DArrays;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

/*
Part-1
                                                     2D Arrays

        An array element can actually be an array.  It's known as a nested array, or an array assigned to an outer array's
    element. This is how Java supports two and 3D arrays, of varying dimensions. A two-dimensional array can be thought
    of, as a table or matrix of values, with rows and columns. You can use an array initializer for this, which I'm showing
    on below.

            int[][] array = {
                                {1, 2, 3},
                                {11, 12, 13},
                                {21, 22, 23},
                                {31, 32, 33}
                                };

            int[][] array = {{1, 2, 3}, {11, 12, 13}, {21, 22, 23}, {31, 32, 33}};

    Notice the two sets of square brackets on the left side of the assignment, in the declaration. Using this type of
    declaration, tells Java we want a 2D array of integers. Here we show the same declaration with array initializers,
    that mean the same thing. The first example,  just uses white space to make it more readable. In second example, all
    the nested arrays, have the same length.

            int[][] array = {
                                {1, 2},
                                {11, 12, 13},
                                {21, 22, 23, 24, 25}
                                };

    A 2-dimensional array doesn't have to be a uniform matrix though. This means the nested arrays can be different sizes,
    as we show with this next initialization statement. Here, we have an array with 3 elements. Each element is an array
    of integers (a nested array). Each nested array is a different length.

            int[][] array = new int[3][3];

    You can initialize a two-dimensional array, and define the size of the nested arrays, as shown here. This statement
    says we have an array of 3 nested arrays, and each nested array will have three ints.

            _____________________________
            |___|__"0"__|__"1"__|__"2"__|
            |_0_|___0___|___0___|___0___|
            |_1_|___0___|___0___|___0___|
            |_2_|___0___|___0___|___0___|

    The result of this initialization is shown in the table on above. Java knows we want a 3x3 matrix of ints, and defaults
    the values of the nested arrays to zeros, as it would for any array.

            int[][] array = new int[3][];

    You can initialize a two-dimensional array, without specifying the size of the nested arrays. Here, we're specifying
    only the outer array size, by specifying the length, only in the first set of square brackets. We've left the second
    set of square brackets empty. The result of this initialization is an array of 3 null elements. We are limited to
    assigning integer arrays to these elements, but they can be any length.

            int[][] myDoubleArray;
            int[] myDoubleArray[];

    There are a lot of ways to declare a two-dimensional array. I'll just cover the two most common ways here. The most
    common, and in my opinion, clearest way, to declare a two-dimensional array, is to stack the square brackets as shown
    in the first example. You can also split up the brackets as shown in the second example, and you'll likely come across
    this in Java code out in the wild.
End-Part-1
*/

        int[][] array2 = new int[4][4];
        System.out.println(Arrays.toString(array2));
        System.out.println("array2.length = " + array2.length);

/*
Part-2
        Ok, so here, I'm using 2 [], after the primitive type,int, and naming the array variable, array2. This instantiates
    an array that has 4 elements. Each element in the outer array gets assigned a nested array with 4 elements, each an
    int, with a default value of 0. I'll print out the value returned, from a call to arrays.toString, passing array2.
    And print out that array2's length. And we can run this code:

            [[I@6acbcfc0, [I@5f184fc6, [I@3feba861, [I@5b480cf9]
            array2.length = 4

    We can see the length of array2 is still 4, but when we used the Arrays.toString method, we see 4 elements printed.
    You might remember this odd-looking printout, which is really the result of just calling toString, on an integer array,
    without using the helper class method. We would get this, if we printed out each element in the array, individually,
    so let's do that next.
End-Part-2
*/

        for (int[] outer : array2) {
            System.out.println(outer);
            System.out.println(Arrays.toString(outer));
        }

/*
Part-3
        Define our for each loop, print outer. In this code, I'm using the for each loop, but notice what our loop element
    is, it's an array of integers. This loops through the outer array, and each element is an array of 4 integers. And if
    we run this,

        [I@6acbcfc0
        [I@5f184fc6
        [I@3feba861
        [I@5b480cf9

    we have similar output to before, but now, each array is printed on its own line. But if we change the code, and instead
    of just printing each loop element, which we know each is an array, we can pass it to the Arrays.toString method instead:

        [0, 0, 0, 0]
        [0, 0, 0, 0]
        [0, 0, 0, 0]
        [0, 0, 0, 0]

    we can see each nested array gets printed. And we can confirm now, that each of the elements in the nested arrays, is
    initialized to zeros. You can print all these elements out, using a nested for loop. Let's do that next, because you'll
    probably see code processing nested arrays like this a lot.

        When we access a one dimensional array element, we do it with square brackets, and an index value.
So this code sets the first element in the array to 50:
To access elements in a two-dimensional array, we use two indices, so this code sets the first element in the first array to 50.
This next code sets the second element, in the second array to 10.

The code on this slide is similar to the code we have in our class, using nested traditional for loops.
In this case, we're not using any local variables, but accessing array elements and variables directly.

This table shows the indices, which are used, to access the elements in the two-dimensional array in our code sample.
When we loop through the outer loop, we're accessing each row of elements.
We've highlighted the first row, which would be the elements accessed, when i = 0 for the outer for loop.
When we loop through the inner loop, we're accessing each cell in the array.
A cell in this matrix can be any type.
In our code, each is an integer value, and we know they've all been initialized to zero.
End-Part-3
*/

        for (int i = 0; i < array2.length; i++) {
            var innerArray = array2[i];
            for (int j = 0; j < innerArray.length; j++) {
                System.out.print(array2[i][j] + " ");
                //array2[i][j] = (i * 10) + (j + 1);
            }

            System.out.println();
        }
/*
Part-4
        We're going through the loop (which in this case, we'll use to traverse the outer array), using the i index. We'll
    loop from 0, to one less than the length of the array. And right now, we're not doing anything, except assigning each
    element to a variable in the loop, we called innerArray. Here, I'm using "var" as the type, because the JVM can infer
    the type of our elements, which in this case, is an integer array.

        Next, I want to loop through the elements, that are in each of these nested arrays. Add a for loop for inner array.
    We're looping through the inner Array's elements, using a nested for loop, and a different look index, j. And j start
    with 0, because arrays are always indexed, starting at zero. we'll print each element, using the indices for the outer
    loop and the inner loop.

        array[0] = 50;
        twoDarray[0][0] = 50;

        When we access a one dimensional array element, we do it with square brackets, and an index value. So this code
    sets the first element in the array to 50: To access elements in a two-dimensional array, we use two indices, so this
    code sets the first element in the first array to 50.

        twoDarray[1][1] = 10;

    This next code sets the second element, in the second array to 10. The code on below is similar to the code we have
    in our class, using nested traditional for loops. In this case, we're not using any local variables, but accessing
    array elements and variables directly.

        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2[i].length; j++) {  //while i = 0, j will loop from 0 to 3
                System.out.print(array2[i][j] + " ");
            }
        }

        This table below shows the indices, which are used, to access the elements in the two-dimensional array in our code
    sample. When we loop through the outer loop, we're accessing each row of elements.

            _________________________________________________________
            |_______|__"j_=_0"__|__"j_=_1"__|__"j_=_2"__|__"j_=_3"__|
            |_i_=_0_|__[0][0]___|__[0][1]___|__[0][2]___|__[0][3]___|
            |_i_=_1_|__[1][0]___|__[1][1]___|__[1][2]___|__[1][3]___|
            |_i_=_2_|__[2][0]___|__[2][1]___|__[2][2]___|__[2][3]___|

    We've highlighted the first row, which would be the elements accessed, when i = 0 for the outer for loop. When we loop
    through the inner loop, we're accessing each cell in the array. A cell in this matrix can be any type. In our code,
    each is an integer value, and we know they've all been initialized to zero.
End-Part-4
*/

        for (var outer : array2) {
            for (var element : outer) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println(Arrays.deepToString(array2));

/*
Part-5
        Now getting back to our code, we've learned that we can use nested for loops, with indices, to print each of our
    elements. We can use for each loops as well, so let's add code that does the same thing, but with the enhanced versions.
    The loop can take an array as the right-sided component, and here we use our 2D array, array2. On the left side of the
    colon, in the parentheses of this for loop declaration, we're setting up the element we're looping over. In this case
    it's a 1D int array, and we're going to call the local variable, outer, in this loop. Ang again, to make our jobs easier,
    we don't have to declare the type, because Java can infer it if we use the var keyword.

        Next we nest a second for-each loop, which will loop through every integer, in our matrix of data. We use the outer
    variable now as the right-sided component, in the loop declaration. The for loop variable in this nested loop, which
    we're calling element, is simple an int, but again, we'll just use var here. And we're just printing each element out,
    with a space between the elements, and in between rows, we'll add a new line. We want after the inner loop completes,
    but in the outer loop. And running this,

            0 0 0 0
            0 0 0 0
            0 0 0 0
            0 0 0 0

    you can see that it gives us the same output as before, but the output is a bit easier to read. We already know we can
    use toString, on the Arrays class, to print out a nicely formatted 1D array. As it turns out, the Arrays class provides
    us with another method, to print multi-dimensional arrays, called deepToString,

            System.out.println(Arrays.deepToString(array2));

    I'm calling Arrays.deepToString(), passing array2, and print the value returned, and if we run that,

            [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]

    we can see our 2D array, printed on a single line. Here you can see the outer square brackets, which represents the
    outer array, and the inner brackets, each mark the inner arrays.
End-Part-5
*/
    }
}
