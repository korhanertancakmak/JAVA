package CourseCodes.NewSections.Section_15_Collections.Course12_TaskAndTaskDataCodeSetup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Part-6
/*
        I'll set up an empty text block, private and static on this class, called tasks. I'm going to paste some data in
    here, in just a minute, but first I want to copy that empty text block, and paste it, renaming it, ann's Tasks. I'll
    paste that again, and name this one bobsTasks. And one more time, pasting that, and renaming it to carols tasks. I'm
    going to use the data from the csv file, and paste all tasks in the first text block. I'll repeat that process for
    Ann's tasks. Next, I'll copy and paste Bobs tasks. Finally, I'll repeat that for Carols tasks. Any time you get data
    from anywhere, you should spend a few minutes, getting familiar with it. You'll notice this data, comes in two different
    ways. For the full task list, there are three comma delimited fields, the first is project, and it appears the list
    is sorted by this field. The second field is description, or task description. The third is a priority, obviously in
    text, meaning we have to get this text to match our priority enum values when we load the data. Scrolling down to
    look at ann's and bob's data, you can see there's a fourth field, a status, and that's in mixed case. Again, we'll
    have to transform that string into the enum type we've got for Status. Also, there's no assignee field in this data.
    That's going to have to be derived by the field itself. In other words, ann's Task, will all need to default Ann as
    the assignee. Now that we've got an understanding of this data, I want to create the getTasks method.
*/
//End-Part-6
public class TaskData {

    private static String tasks = """
            Infrastructure, Logging, HIGH
            Infrastructure, DB Access, MEDIUM
            Infrastructure, Security, HIGH
            Infrastructure, Password Policy, MEDIUM
            Data Design, Task Table, MEDIUM
            Data Design, Employee Table, MEDIUM
            Data Design, Cross Reference Tables, HIGH
            Data Design, Encryption Policy, HIGH
            Data Access, Write Views, Low
            Data Access, Set Up Users, Low
            Data Access, Set Up Access Policy, Low
            """;

    private static String annsTasks = """
            Infrastructure, Security, HIGH, In Progress
            Infrastructure, Password Policy,MEDIUM, In Progress
            Research, Cloud solutions, MEDIUM, In Progress
            Data Design, Encryption Policy, HIGH
            Data Design, Project Table, MEDIUM
            Data Access, Write Views, Low, In Progress
            """;

    private static String bobsTasks = """
            Infrastructure, Security, High, In Progress
            Infrastructure, Password Policy, Medium
            Data Design, Encryption Policy, High
            Data Access, Write Views, Low, In Progress
            """;

    private static String carolsTasks = """
            Infrastructure, Logging, High, In Progress
            Infrastructure, DB Access, Medium
            Infrastructure, Password Policy, Medium
            Data Design, Task Table, High
            Data Access, Write Views, Low
            """;

//Part-8
/*
        It's going to be public and static, so users can call it by qualifying the class name, TaskData. It's going to
    return a Set of tasks, and it's going to take a String, the owner, or assignee. I'll set up a local variable, that's
    the variable that'll get returned from this method, so it's declared as a Set, but it's going to be a hash set instance.
    Since I have a limited set of users, I'll just list their names in a comma delimited string, and use the contains
    method to determine if the name passed, ignoring case, is one of those names. If it is, I'll use the method argument
    as the user or the assigned person, otherwise I'll set that to null. I want to return my local variable at the end
    of this method. Of course, I'm not done with this method. There's a bit more work to do.
*/
//End-Part-8

    public static Set<Task> getTasks(String owner) {

        Set<Task> taskList = new HashSet<>();
        String user = ("ann,bob,carol".contains(owner.toLowerCase())) ? owner : null;

//Part-9
/*
        I need to figure out which text block to use, and that's again determined by the method argument, the owner. This
    time I'll switch on owner, again, calling to lower case. If Ann was passed to this, I'll set selected List to ann's
    Tasks. If it was Bob, I'll pass back bobs Tasks. If Carol, then it's carol's tasks. and if it wasn't any of those,
    I'll pass back the full task list, so just tasks.
*/
//End-Part-9

        String selectedList = switch (owner.toLowerCase()) {
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            default -> tasks;
        };

//Part-10
/*
        Now that I've got the right data, based on the owner, meaning I have the text block I want to process, I need to
    split each text block by lines. I did this in an earlier example, using a Scanner instance and its nextLine method.
    Here I'm simply going to use the split method on String, and pass it the escape sequence for a new line, backslash n.
    In a future lecture, I'll show you another way to do this, with backslash backslash R, but I want to cover various
    ways, so I'll use backslash n here, which is a common way you might see, when you're looking at other people's code.
    I'm going to loop through every line, which I get from this split method. After I have the line, I want to split by
    commas to get the field data. I'll trim every field's data, using replace all, which you've seen me do before. Ok,
    so I've got all my data in an array. Before I can create an instance of a Task, I want to transform status, which
    you'll remember was in the 4th place in a record that had a status. If there's less than 3 fields, I'll default the
    status to the in queue constant. Otherwise, I will pass the value of the status data field to the Status enums value
    of method. I want to make the string passed all upper case, and replace spaces with underscores. Getting the priority
    enum constant is similar, but a little simpler. All records should have this field and I just want it to be upper
    case. Now, I can create the task, passing it the first field, project, the second field, description, the user variable,
    and my priority and status variables, which are now both enum constants. I'm creating the task and adding it to the
    task list set at the same time. There's a lot of things that could go wrong with this method, but again I'm just keeping
    it simple here for the sake of time. To make it more robust, you'd want to check the data for nulls, and empty strings,
    or lines that don't have enough data, and so on. With this code, we can get data from our source, with a parameterized
    call of a static method on this class. Getting back to the main class,
*/
//End-Part-10

        for (String taskData : selectedList.split("\n")) {
            String[] data = taskData.split(",");
            Arrays.asList(data).replaceAll(String::trim);

            Status status = (data.length <= 3) ? Status.IN_QUEUE :
                    Status.valueOf(data[3].toUpperCase()
                            .replace(' ', '_'));

            Priority priority = Priority.valueOf(data[2].toUpperCase());
            taskList.add(new Task(data[0], data[1], user, priority, status));
        }

        return taskList;
    }
}
