# [Set Operations Challenge]()
<div align="justify">

```java  
public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);


        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);
    }
}
```

I've set up a local variable called tasks, 
which I retrieved from the Task Data get Tasks method, 
passing that a string, _all_. 
This set of tasks represents the tasks our manager remembered 
or thought they knew about. 
As with most data, it might contain some mistakes, 
meaning it might have tasks that haven't been assigned, 
or it might be missing tasks altogether. 
I've executed the sort and print method, 
passing the text _all tasks_, and this set to it. 
In addition, I got ann's task in much the same way, 
calling get Data but passing it the name Ann there.
In this case, I'm printing the set 
with an overloaded version of sort and print that takes a **Comparator**. 
I've created a local variable, comparator, 
that uses Comparator's methods to create one 
that will sort by the task's _priority_ field. 
If I run this code here:

```java  
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
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Data Access          Write Views               LOW        Ann        IN_PROGRESS
```

I get the boss's list, sorted by project and description. 
I also get Ann's self-reported list, sorted by priority. 
Now the challenge suggests I create three methods in this class,
so I'm going to do that before I do anything else.

```java  
private static Set<Task> getUnion(List<Set<Task>> sets) {
    Set<Task> union = new HashSet<>();
    for (var taskSet : sets) {
        union.addAll(taskSet);
    }
    return union;
}
```

I'll make these all private and static, and all will return a Set 
with a type argument of a task. 
The first will be named getUnion, and that's going to take a List. 
The List's type argument is going to be a Set of tasks.
Next, I'll set up a local variable, a new HashSet. 
Remember that I don't want to mutate any of the sets 
passed as arguments when I execute this operation. 
I'll loop through the list of sets. 
I'll execute add all on my local variable set,
passing it each set in the list. 
Finally, I'll return this new set, 
which is the union of all the sets in the list. 
Since sets won't include duplicate values,
this method will give me a distinct collection of all tasks, 
from all the sets passed. 
The next method is getIntersect.

```java  
private static Set<Task> getIntersect(Set<Task> a, Set<Task> b) {

    Set<Task> intersect = new HashSet<>(a);
    intersect.retainAll(b);
    return intersect;
}
```

Now, you can do an _intersect_ on more than two sets, 
but for the questions I need to answer for this challenge,
I'm more interested in comparing only two sets at a time. 
This method is called get Intersect, 
and I'll have sets _a_ and _b_.
I'll again create a new Set, this time I'll initialize it with set _a_. 
To do an _intersect_, I use the _retainAll_ method. 
It's important to call this method on the local variable, 
and not the method argument set itself. 
Remember, I don't want it to mutate. 
And I'll return my local variable, 
the result of an intersect operation on two sets.

```java  
private static Set<Task> getDifference(Set<Task> a, Set<Task> b) {

    Set<Task> result = new HashSet<>(a);
    result.removeAll(b);
    return result;
}
```

The last method is _getDifference_, 
and that will also have sets _a_ and _b_, 
both Sets with Task as the type argument.
I'll again create a local variable, a new set, 
assigning that a new hash set instance, 
again passing it the first set, set _a_. 
I use _removeAll_, importantly on the result variable, 
passing it the second set, _b_. 
This does an asymmetric set difference, 
subtracting _b_'s elements from _a_'s. 
I return the local variable result. 
These methods will make the next bit of code a bit clearer; 
this is the code to answer the four questions on the challenge side.
First, What is the full task list? 
How would you go about getting that? 
If it was a perfect world, it would just be tasks,
the list we got from our boss. 
But never assume anything is perfect or right, trust but verify. 
To do this, I probably want a union of all four sets,
the boss's tasks, and each of the team member's tasks. 
First, I'll get bob and Carol's tasks. 
I'll do this in the main method:

```java  
Set<Task> bobsTasks = TaskData.getTasks("Bob");
Set<Task> carolsTasks = TaskData.getTasks("Carol");
List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);
```

I'll create a local variable, 
first for Bob's tasks and assign it 
the result of TaskData dot get tasks, 
passing Bob in quotes. 
I'll do the same for Carol, so Carol's Tasks, 
and I'll pass carole in quotes to that method. 
Then I'll create a list of these sets. 
I'm going to create a union of these three sets. 
That union is going to be useful in answering several questions. 
I'll set up a new local variable, a set of tasks, 
calling it assigned tasks. 

```java  
Set<Task> assignedTasks = getUnion(sets);
sortAndPrint("Assigned Tasks", assignedTasks);
```

I'll assign that the result of calling my getUnion method, 
on my list of sets, which is the list of Ann's, Bob's, and Carol's tasks. 
I'll print those tasks, the assigned tasks, 
in the natural sorted order. 
Running this code,

```java  
__________________________________________________________________________________________
Assigned Tasks
__________________________________________________________________________________________
Data Access          Write Views               LOW        Ann        IN_PROGRESS
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Data Design          Task Table                HIGH       Carol      IN_QUEUE
Infrastructure       DB Access                 MEDIUM     Carol      IN_QUEUE
Infrastructure       Logging                   HIGH       Carol      IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
```

That gives me the answer to the second question, 
which tasks have been assigned to a team member. 
To answer the first question, 
I could include the full tasks in the list 
I pass to my get Union method. 
In fact, let me do that.

```java  
Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
sortAndPrint("The True All Tasks", everyTask);
```

I'll set up another local variable, a Set, 
called everyTask, and call get Union, 
with a list of two sets, tasks, the boss's set, and assignedTasks, 
the union of tasks all my team member's claim to be assigned to. 
And I'll print that. 
I'm going to call this the True All Tasks. 
Running that code,

```java  
__________________________________________________________________________________________
The True All Tasks
__________________________________________________________________________________________
Data Access          Set Up Access Policy      LOW        null       IN_QUEUE
Data Access          Set Up Users              LOW        null       IN_QUEUE
Data Access          Write Views               LOW        null       IN_QUEUE
Data Design          Cross Reference Tables    HIGH       null       IN_QUEUE
Data Design          Employee Table            MEDIUM     null       IN_QUEUE
Data Design          Encryption Policy         HIGH       null       IN_QUEUE
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Data Design          Task Table                MEDIUM     null       IN_QUEUE
Infrastructure       DB Access                 MEDIUM     null       IN_QUEUE
Infrastructure       Logging                   HIGH       null       IN_QUEUE
Infrastructure       Password Policy           MEDIUM     null       IN_QUEUE
Infrastructure       Security                  HIGH       null       IN_QUEUE
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
```
                
I get a list of a lot of tasks. 
I could try to eyeball that list with the boss's set, 
but I don't have to. 
I can use a set operation to give me the information, 
what's missing from the boss's list. 
I'll set up a local variable, a set, missing tasks:

```java  
Set<Task> missingTasks = getDifference(everyTask, tasks);
sortAndPrint("Missing Tasks", missingTasks);
```

And now I'm going to call my getDifference method, 
passing it the every task set, and the boss's set, tasks. 
And I'll print that out with the header missing tasks. 
Running that,

```java  
__________________________________________________________________________________________
Missing Tasks
__________________________________________________________________________________________
Data Design          Project Table             MEDIUM     Ann        IN_QUEUE
Research             Cloud solutions           MEDIUM     Ann        IN_PROGRESS
```
                
I get two tasks that weren't on the boss's radar,
but Ann is supposedly assigned to them. 
Ok, so that answers the first two questions of the challenge. 
The next question is to figure out which tasks haven't been assigned. 
Do you know how you'd go about doing that? Did you guess the difference 
between the boss's set and the union we created of all the team member's tasks? 
That's one way to do it, and the way I'll code it now.

```java  
Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);
```

I want a set variable called unassigned tasks. 
That's going to be assigned to the result of get Difference,
passing first the tasks the boss says needs to get done, 
and then the union of all the tasks my teammates say their
working on, the assigned tasks set. 
This time I want to print these tasks, 
the Unassigned tasks, not by the default sort, 
but in priority order. 
Running that code,

```java  
__________________________________________________________________________________________
Unassigned Tasks
__________________________________________________________________________________________
Data Design          Cross Reference Tables    HIGH       null       IN_QUEUE
Data Design          Employee Table            MEDIUM     null       IN_QUEUE
Data Access          Set Up Access Policy      LOW        null       IN_QUEUE
Data Access          Set Up Users              LOW        null       IN_QUEUE
```

I can see the tasks that aren't yet assigned. 
Included in this is a high-priority task,
so I need to figure out who to give this to.
Before I do that, I have to understand 
if there's been any overlap. 
Do I have different team members assigned to the same task? 
In real world development teams, this is probably a valid use case, 
but in my case, my tasks should be assigned to only one team member at a time.
If I did an _intersect_ of all my team members' tasks, 
would this be the right answer? 
What do you think? 
No, that would only answer the question, 
which task is assigned to all three team members. 
That's not the question I need to answer. 
To answer my question, which tasks are assigned 
to different team members, 
I'm going to do a series of _intersects_. 
I'll intersect each combination of two team members,
so I'll first intersect Ann and Bob, then Bob and Carols tasks, 
and finally Ann and Carols. 
Remember, intersects are asymmetric, 
so luckily I don't have to do the operations in reverse, 
like Bob to Carol.
After I get this information, I want to union these three results, 
and this will give me a set of all tasks where there's overlap. 
Let me show you this. 
Instead of setting up a bunch of local variables, 
I'll nest the calls, each call to getIntersect, 
directly inside a getUnion invocation, and inside a list creation method.

```java  
Set<Task> overlap = getUnion(List.of(
        getIntersect(annsTasks, bobsTasks),
        getIntersect(carolsTasks, bobsTasks),
        getIntersect(annsTasks, carolsTasks)
));
sortAndPrint("Assigned to Multiples", overlap, sortByPriority);
```

I'll set up one variable, a set, overlap, 
and assign that the result of get Union, 
which I'm going to pass a call to _List.of_. 
My first set is the intersection of _annsTasks_ and _bobsTasks_, 
coming back from the getIntersect method.
My second set is carolsTasks intersected with bobs Tasks. 
The last intersected set is Ann's tasks and Carol's tasks.
Finally, I'll print these, calling this information Assigned to Multiples, 
and I'll again sort by priority. 
Running this code,

```java  
__________________________________________________________________________________________
Assigned to Multiples
__________________________________________________________________________________________
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Data Access          Write Views               LOW        Ann        IN_PROGRESS
```
                    
I discover that four of my tickets are assigned to multiple team members. 
Now, the name here represents only the first person assigned to the task. 
Interestingly enough, it looks like Anne is assigned to all the duplicate tasks.
If Ann were a manager or an intern, that might be ok, but she's not. 
I'd like to get a bit more information about these duplicates or overlaps.

```java  
List<Task> overlapping = new ArrayList<>();
for (Set<Task> set : sets) {
    Set<Task> dupes = getIntersect(set, overlap);
    overlapping.addAll(dupes);
}

Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
sortAndPrint("Overlapping", overlapping, priorityNatural);
```

Because I don't want to remove duplicates, 
I'll set up another variable, a list of **Task**, 
and call that overlapping, a new Array list. 
I'll loop through each team member's set of tasks. 
And now I'll set up a local variables, dupes, 
assigning that the result of get intersect, 
passing it the set, the full set of tasks, 
and the overlap identified.
This will give me the task assigned to this team member 
that's also assigned to somebody else. 
I'll add that to my overlapping list. 
Now, I want to print this out, but I want another sort, 
that's first by priority, but then sorted in the natural order within priority. 
I'll set up another Comparator variable, 
and call that priority Natural, and assign that the sortByPriority comparator, 
but chain the then comparing method, 
passing it _Comparator.natural_ order.
This will sort by priority, then project, then description. 
Since my sort and print method takes a collection, 
I can use it to print lists too, so I'll call it, 
with Overlapping as the header, pass it my overlapping list, 
and my new priorityNatural comparator. 
Running this code,

```java  
__________________________________________________________________________________________
Overlapping
__________________________________________________________________________________________
Data Design          Encryption Policy         HIGH       Ann        IN_QUEUE
Data Design          Encryption Policy         HIGH       Bob        IN_QUEUE
Infrastructure       Security                  HIGH       Ann        IN_PROGRESS
Infrastructure       Security                  HIGH       Bob        IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Ann        IN_PROGRESS
Infrastructure       Password Policy           MEDIUM     Bob        IN_QUEUE
Infrastructure       Password Policy           MEDIUM     Carol      IN_QUEUE
Data Access          Write Views               LOW        Ann        IN_PROGRESS
Data Access          Write Views               LOW        Bob        IN_PROGRESS
Data Access          Write Views               LOW        Carol      IN_QUEUE
```
                    
That gives me a list of tasks 
that I'll most likely need to reassign. 
Ann and Bob, my strongest team members, are assigned 
to the same tickets in all of these cases. 
Carol, a new member, has some overlapping tickets, 
but she hasn't started any. 
Ok, so this code answered all the challenge questions.
I like set operations, probably because I like to look at data 
from many angles. 
</div>