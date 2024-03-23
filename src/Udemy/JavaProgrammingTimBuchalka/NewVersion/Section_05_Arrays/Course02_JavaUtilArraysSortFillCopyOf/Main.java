package CourseCodes.NewSections.Section_09_Arrays.Course02_JavaUtilArraysSortFillCopyOf;

import java.util.Arrays;
import java.util.Random;
public class Main {
    public static void main(String[] args) {

/*
Part-1
                                     Using java.util.Arrays(sort, fill, copyOf)

        We use arrays to manage many items of the same type. Some common behavior for arrays would be sorting, initializing
    values, copying the array, and finding an element. For an array, this behavior isn't on the array instance itself,
    but it's provided on a helper class, java.util.Arrays. Also we saw an example that using toString method.

        The first thing I want to do is, create another method on this class, which will return an array of random integers.
    I'll call this method getRandomArray, and it'll take the length of the array to be created, as the only argument.
End-Part-1
*/

        int[] firstArray = getRandomArray(10);
        System.out.println(Arrays.toString(firstArray));

/*
Part-3
        Here, we create a variable of type int array, and assign it the result of our getRandomArray method. And we call
    Arrays.toString, because it's a static method on Arrays, and we pass our array to it. And running this, you can see,
    we get 10 random numbers there.

        [78, 94, 93, 99, 9, 19, 51, 81, 22, 70]

    Your own numbers will be different, and they'll be different every time you run it. This is because they're randomly
    generated. And maybe the first thing you'd like to do, when you get data from any source, is sort it, if it's not already
    sorted. Java provides a sort method for us in this case, on this same Arrays class. Let's give that a try. I'll call
    Arrays.sort passing first array and print out the value returned from a call too:
End-Part-3
*/

        Arrays.sort(firstArray);
        System.out.println(Arrays.toString(firstArray));

/*
Part-4
        We call sort directly using the class name, Arrays, and pass it firstArray. This method doesn't return anything,
    it's void, so we're not getting a new instance of an array back. The actual array we pass to it actually gets sorted.
    And running that:

        [49, 13, 37, 58, 3, 70, 48, 56, 84, 73]
        [3, 13, 37, 48, 49, 56, 58, 70, 73, 84]

    we can see our numbers get ordered, from low to high, which is called the natural order, for numeric values. Let's
    say, we want to set all the values in an array, to our own initial value, before processing. We want something besides
    zeros, but we don't want to use an array initializer, because we want them all initialized with the same value. We
    can do this with the Arrays.fill method. I'm going to create a new array for this:
End-Part-4
*/

        int[] secondArray = new int[10];
        System.out.println(Arrays.toString(secondArray));
        Arrays.fill(secondArray, 5);
        System.out.println(Arrays.toString(secondArray));

/*
Part-5
        This first statement creates a zero filled array, because zero is Java's default, for elements in an integer array.
    But on the 3rd line, where we call the fill method, it sets every element to 5. And running this:

        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        [5, 5, 5, 5, 5, 5, 5, 5, 5, 5]

    we can confirm elements start out as all zeros, then it's all 5's. Now, maybe you don't really want these changes being
    made to your original array. You can create a copy of the array, and apply operations to the copy. Let's look at how
    we do that:
End-Part-5
*/

        int[] thirdArray = getRandomArray(10);
        System.out.println(Arrays.toString(thirdArray));

        int[] fourthArray = Arrays.copyOf(thirdArray, thirdArray.length);
        System.out.println(Arrays.toString(fourthArray));

/*
Part-6
        Again, we're creating a random number array there, on that first line. Then we call Arrays.copyOf, which takes the
    array, and a size as the second argument. Since we want an exact copy, we just pass the current size of the array, we
    want copied. Running that:

        [34, 16, 86, 70, 49, 46, 13, 57, 60, 91]
        [34, 16, 86, 70, 49, 46, 13, 57, 60, 91]

    we see that the first and second array, have the same elements in the same order. And now, what if we sort the elements
    in this copied array, in the fourth array? Will this have any effect on the third array, the array we made a copy of?
    Let's test that:
End-Part-6
*/

        Arrays.sort(fourthArray);
        System.out.println(Arrays.toString(thirdArray));
        System.out.println(Arrays.toString(firstArray));

/*
Part-7
        And running that:

        [6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
        [6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
        [6, 40, 78, 55, 90, 91, 76, 83, 10, 68]
        [2, 2, 14, 36, 44, 49, 63, 70, 84, 96]

    we see that fourthArray is sorted, but the array we copied the data from, is still in its original state. An array
    copy creates a new array, a new instance of an array, and copies the array elements over to the new array. For primitives,
    the values get copied. For objects, the object references get copied. Performing operations on the copied array, like
    sort and fill, don't impact the original array. Let's see what happens when the length we pass, is less than the number
    of elements in the array.
End-Part-7
*/

        int[] smalllerArray = Arrays.copyOf(thirdArray, 5);
        System.out.println(Arrays.toString(smalllerArray));

/*
Part-8
        Now here, we're passing 5 as the second argument. When we run this,

        [39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
        [39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
        [39, 34, 49, 26, 73, 53, 81, 18, 87, 27]
        [19, 24, 24, 40, 48, 49, 54, 73, 79, 92]
        [39, 34, 49, 26, 73]

    you can see this gives us only the first 5 elements, in the copied array. And let's test 1 more example, we'll pass a
    value, greater than the size of the array:

End-Part-8
*/

        int[] largerArray = Arrays.copyOf(thirdArray, 15);
        System.out.println(Arrays.toString(largerArray));

/*
Part-9
        Now here, the length of our array is 10, but we want an array of 15 elements. Is this going to work? Let's run
    it:

        [1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
        [1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
        [1, 47, 14, 2, 77, 17, 48, 4, 90, 71]
        [3, 13, 31, 45, 57, 60, 65, 89, 93, 94]
        [1, 47, 14, 2, 77]
        [1, 47, 14, 2, 77, 17, 48, 4, 90, 71, 0, 0, 0, 0, 0]

    You can see, from this output, we get our 10 elements copied over, in the order they were when we made the call, but
    we have 5 more elements in this array, initialized to zero. This one method, can be used for 3 different types of
    copies, a full copy, a partial copy, and a copy plus additional elements.
End-Part-9
*/
/*
Part-10
                                                Finding a match

        You can imagine if you were going to start writing code to do this, you might start looping from start to finish,
    and check each element, to see it equals what you're looking for. If you find a match, you'd stop looping, and return
    that a match was found, either with the position you found the element at, or just a boolean value, true if it was
    found, and false if not. This is called a "linear search", or "sequential", because you're stepping through the elements,
    one after another. If your elements are sorted though, using this type of linear search, is unnecessarily inefficient.

        Imagine looking for the word middle, in the dictionary, starting at the first page, and looking on every sequential
    page, until you finally find the page that has the word middle. That's not really how you'd look for it though, is it,
    because you'd waste a lot of time. You'd pick a spot in the dictionary, based on the word you're trying to find, that
    you think might be close to where your word might be. In this case, it might just be somewhere in the middle of the
    dictionary. You might divide the pages in half, and determine which half included the word middle. In other words,
    you'd figure out which section of the 2, to keep looking in.

        Using intervals to Search:

        You split each section up, testing the values at the boundaries, and based on that, split again into smaller sections,
    narrowing the number of elements to test, each time. This type of searching, in software, is called "interval searching."
    Within these 2 categories, sequential and interval, there are numerous existing algorithms in each. One of the most
    common interval searches, is the "binary search", which is why Java provides this search, on so many of its collection
    classes. In this search, "intervals" are continually "split into 2", hence the word binary. If you're a software engineer
    at heart, you'll have some fun, researching these algorithms, and how they differ, and studying the code behind their
    implementations. For most of us though, we just need to understand, that Java gives us methods for searching, and when
    and how we should use those methods.

        Arrays.binarySearch:

        The static method, binarySearch, is on the Arrays class. We can use this method to test if a value is already in
    our array, but there are some "important" things to remember:

  - First, the array has to be "sorted".
  - Second, if there are duplicate values in the array, there's no guarantee which one it'll match on.
  - Finally, elements must be comparable. Trying to compare instances of different types, may lead to errors and invalid
    results.

    This methods returns:
  - "The position of a match" if found.
  - It returns a "-1" when no match was found.
  - It's important to remember, that a positive number "may not be the position of the first match."
  - If your array has duplicate values, and you need to find the first element, other methods should be used.
End-Part-10
*/

        String[] sArray = {"Able", "Jane", "Mark", "Ralph", "David"};
        Arrays.sort(sArray);
        System.out.println(Arrays.toString(sArray));
        if (Arrays.binarySearch(sArray, "Mark") >= 0) {
            System.out.println("Found Mark in the list");
        }
/*
Part-11
        So, Let's look at this method. I'll create a String array called sArray and initialize it with 5 string literals.
    We sort this array, because the binary search only works on sorted arrays. This is really important and can't be stated
    enough. What good is a dictionary, if it's not sorted after all? The dictionary allows your brain to do interval searching,
    because the words are sorted alphabetically. A binarySearch can do interval searching successfully, because the elements
    are sorted. A negative number returned from the binarySearch method, means the item wasn't found. Otherwise, the item
    was found in the array, and it returns the position of a match, though it doesn't guarantee which match. And running
    that code,

        [Able, David, Jane, Mark, Ralph]
        Found Mark in the list

    we can confirm that "Mark" is in the list. What if we want to compare if 2 arrays are equal? 2 arrays are considered
    equal, if both arrays, contain the same number of elements, and all elements in the same position in both arrays, are
    equal.
End-Part-11
*/

        int[] s1 = {1, 2, 3, 4, 5};
        int[] s2 = {1, 2, 3, 4, 5};

        if (Arrays.equals(s1, s2)) {
            System.out.println("Arrays are equal");
        } else {
            System.out.println("Arrays are not equal");
        }

/*
Part-12
        I'll create 2 arrays of numbers. I'll call them s1 and s2, and initialize these arrays with the values of 1 through
    5. Now I'll use the equals method on Arrays. The equals method on Arrays, takes 2 args, 2 arrays, whose types have to
    match, so you have to compare 2 int arrays, or 2 boolean arrays, etc. This method returns true or false depending on
    the arrays are equal or not. Let's run this. And we get both arrays are equal. But what if one of those arrays have
    the values in different order?

        In the second array, we'll just switch where 1 and 5 are located. Now, if we run it, we see Arrays are not equal.
    This method takes the order of the array elements, into account as well.
End-Part-12
*/
    }

    private static int[] getRandomArray(int len) {

        Random random = new Random();
        int[] newInt = new int[len];
        for (int i = 0; i < len; i++) {
            newInt[i] = random.nextInt(100);
        }

        return newInt;
    }

/*
Part-2
        The first thing you notice here is the first statement in the method, where we are creating a variable of type
    Random. Random is another class in the java.util library, that can be very useful. This class has methods on it, to
    return random numbers, which we'll be using shortly. You can see we're also creating a new array, and setting it to
    the size, that was passed in, as the methods, argument. This new array gets returned from this method. Next, I want
    to assign a random number, to each element in the array.

        Since we're assigning values to the array elements, we use traditional for loop. Each element gets assigned the
    result of the random.nextInt method, which returns a random integer values. If you don't pass any argument to this
    nextInt method, it returns any number, from 0 to the maximum integer value. But we want to limit the random numbers,
    so we pass 100. This argument is called the exclusive upper bound. This method returns a random number, between 0 and
    99, and it excludes 100. If we wanted 100 to be included in the random selection, we'd specify 101, and so on. Our
    new method, getRandomArray, returns a new array, of the length we specify, and fills it with random numbers. Going back
    to the main method, I want to call this method, to get a random array and then print it out.
End-Part-2
*/
}
