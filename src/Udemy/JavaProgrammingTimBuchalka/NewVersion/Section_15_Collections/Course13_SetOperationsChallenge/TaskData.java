package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course13_SetOperationsChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
            Infrastructure, Security, HIGH, In Progress
            Infrastructure, Password Policy, MEDIUM
            Data Design, Encryption Policy, HIGH
            Data Access, Write Views, Low, In Progress
            """;

    private static String carolsTasks = """
            Infrastructure, Logging, HIGH, In Progress
            Infrastructure, DB Access, MEDIUM
            Infrastructure, Password Policy, MEDIUM
            Data Design, Task Table, HIGH
            Data Access, Write Views, Low
            """;

    public static Set<Task> getTasks(String owner) {

        Set<Task> taskList = new HashSet<>();
        String user = ("ann,bob,carol".contains(owner.toLowerCase())) ? owner : null;

        String selectedList = switch (owner.toLowerCase()) {
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            default -> tasks;
        };

        for (String taskData : selectedList.split("\n")) {
            String[] data = taskData.split(",");
            Arrays.asList(data).replaceAll(String::trim);

            Status status = (data.length <= 3) ? Status.IN_QUEUE :
                    Status.valueOf(data[3].toUpperCase()
                            .replace(' ', '_'));

            Priority priority = Priority.valueOf(data[2].toUpperCase());
            taskList.add(new Task(data[0], data[1], user,
                    priority, status));
        }

        return taskList;
    }
}
