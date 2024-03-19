package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course12_TaskAndTaskDataCodeSetup;

import java.util.*;

//Part-1
/*
        Part of the upcoming challenge on Set Operations, will be using a Task class. This is a fairly simple class, with
    multiple attributes, some with enums. This will be very similar to the Contact Data set up lecture I did earlier, which
    produced lists. In this case, I'll be getting different sets of tasks, and instead of using Scanner to parse text block
    strings, I'll use methods on String. I'm also going to implement Comparable on Task. If you feel you need more experience
    working with Comparable or enums, then this is a good opportunity to do it. If you just want to concentrate on the
    set operations part of the challenge, you can skip this setup lecture.

                                            Set Operations Challenge

        You'll want to create a class that represents a Task. It should have:

    * an assignee.
    * a project name.
    * a task description.
    * a status (assigned, in progress, or not yet assigned).
    * a priority, High, Low, or Medium.

    Each of these attributes should be editable. A task is uniquely identified by its project name and description. The
    task should implement Comparable, so that tasks are sorted by project name and description. The TaskData class will
    be used to set up, and return some test data. If you want to use my data, it can be found in a csv file, and consists
    of the following:

    * All tasks identified by the manager.
    * Tasks identified by Ann, that she's working on or plans to work on.
    * Tasks which Bob says have been assigned to him.
    * Tasks Carol is doing, as reported by herself.

    This class should have a getData method, that returns a Set of Task. This method should take a String, either the name
    of one of the employees to get a specific set, or 'all' to get the full task set.

        I've created h a Main class and main method. Before I do anything with that, I'll start by creating a Task class.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

//Part-12
/*
        I'll create a Set, named tasks and call get tasks on task data, passing all as the owner. I'll invoke my sortAndPrint
    method, with a header, passing it tasks. Ok, so I can run this and see the full task list, sorted by my Comparable
    sort, project first, then description. I'll copy and paste those two lines of code. I'll change tasks to ann's tasks,
    and all to Ann, on the first statement. I'll change the header to Ann's Tasks, and I want to again change tasks to
    ann's Tasks, on the second statement. If I run that,

            __________________________________________________________________________________________
            All Tasks
            __________________________________________________________________________________________
            Data Access          Set Up Access Policy      LOW        null       IN_QUEUE
            Data Access          Set Up Users              LOW        null       IN_QUEUE
            Data Access          Write Views               LOW        null       IN_QUEUE
            Data Design          Cross Reference Tables    HIGH       null       IN_QUEUE
            Data Design          Employee Table            MEDIUM     null       IN_QUEUE
            Data Design          Encryption Policy         HIGH       null       IN_QUEUE
            Data Design          Task Table                MEDIUM     null       IN_QUEUE
            Infrastructure       DB Access                 MEDIUM     null       IN_QUEUE
            Infrastructure       Logging                   HIGH       null       IN_QUEUE
            Infrastructure       Password Policy           MEDIUM     null       IN_QUEUE
            Infrastructure       Security                  HIGH       null       IN_QUEUE
            __________________________________________________________________________________________
            Ann's Tasks
            __________________________________________________________________________________________
            Data Access          Write Views               LOW        Ann        IN_PROGRESS
            Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
            Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
            Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
            Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
            Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS

    I'll now get Ann's tasks printed out. For good measure, I'll change the way I want this sorted.
*/
//End-Part-12

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        //sortAndPrint("Ann's Tasks", annsTasks);
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

//Part-13
/*
        I'll add a local variable, a Comparator, with type argument task. I'll call it sort by priority. I'll assign it
    the result of calling comparing, on Comparator, with the method reference Task colon colon get Priority. I want to
    change the call to sort and Print on that last statement, where I print ann's tasks, and include the sort by priority
    as an additional argument. Rerunning the code,

                __________________________________________________________________________________________
                Ann's Tasks
                __________________________________________________________________________________________
                Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
                Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
                Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
                Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
                Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
                Data Access          Write Views               LOW        Ann        IN_PROGRESS

    I get Ann's task sorted by priority there, with the highest priority tasks list first. If I had set up my enum to go
    from Low to High, instead of High to Low, these would have been sorted in reverse. Ok, so these are the classes that
    you'll need to complete the Set Operations Challenge, which is coming up next.
*/
//End-Part-13


    }

//Part-11
/*
        I'm going to add a method that will sort and print tasks. I'll make this private and static, and call it sort and
    print. It will take a header or description of the collection, a collection containing tasks, and a comparator I'm
    calling sorter. I'll set up a 90 dashes, separator line asa variable. I'll print my 90 dashes, followed by the header,
    followed by another string of dashes. To sort, I'd need to use a sortable set, which I haven't covered yet, so I'm
    going to set up a list here, an array list and pass it the collection, the method argument. I can call sort directly
    on an ArrayList, and pass it the sorter, a comparator. And I'll just print out each element of the sorted list. I
    also want to add an overloaded version of this method, that doesn't take a Comparator. I'll insert this above the
    first method. I'll chain a call to the sort and print method, but pass null as the comparator, the last argument.
    Remember, I made the Task Comparable, so if I call list.sort with a null, and the list has elements that implement
    Comparable, it will get sorted using Comparable's compareTo method.
*/
//End-Part-11

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {

        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }
}
