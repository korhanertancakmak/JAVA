package CourseCodes.NewSections.Section_15_Collections.Course12_TaskAndTaskDataCodeSetup;

//Part-2
/*
        I'm going to include two enums in this source file. You'll remember I can do this, as long as I don't make them
    public. I'll call the first enum, Priority, and its constants will be HIGH, MEDIUM, and LOW, in that order. My second
    enum is Status, and that's going to have in queue, which will be the default status of any new task. I'll also include
    ASSIGNED, and in progress. Right now, I'm not going to worry about other statuses, like completed. I've said I want
    5 fields on this task class, and they'll all be private. The first three are strings, and the first of those, I'm
    going to call project. Each task will belong to some project. Next, description, which just describes a unit of work.
    then, the assignee or the employee completing the task. Priority, with my Priority enum type for that, so this could
    be low, medium or high. Status will be the type Status, my other enum type.
*/
//End-Part-2

enum Priority {HIGH, MEDIUM, LOW}

enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS}

public class Task implements Comparable<Task> {

    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;

//Part-3
/*
        I'll generate some constructors, the first will have all 5 fields. The second constructor will have all fields
    except status. I'm going to remove all the statements in this generated constructor. I want to replace all that, by
    chaining a call to the 5 argument constructor. I'll simply pass along the arguments for the first four. For the status,
    if the assignee is null, I'll say it's IN_QUEUE; otherwise, it's ASSIGNED. I'll create another constructor, my third,
    and for this one, I want the fields, project, description, and priority. Again, I'll remove the statements in there.
    I'll chain a constructor call here too, this time to the four argument constructor, passing null as the third argument
    to that, which is the assignee. That chained constructor will in turn, set the status to in queue, because the assignee
    is null. I'll generate getters and setters for all five fields. I may want to use the getters as field references for
    other sorting mechanisms, from outside of this class. You can also imagine task fields changing quite a bit over time,
    so we'll want a way to set data on a task.
*/
//End-Part-3

    public Task(String project, String description, String assignee, Priority priority,
                Status status) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public Task(String project, String description, String assignee, Priority priority) {
        this(project, description, assignee, priority,
        assignee == null ? Status.IN_QUEUE : Status.ASSIGNED);
    }

    public Task(String project, String description, Priority priority) {
        this(project, description, null, priority);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//Part-4
/*
        I'll also generate a toString method. I'm going to replace that generated code, returning a formatted string instead,
    with all five fields passed as parameters. I'll print first project, then description, then priority. Finally the
    assignee and the status. I said I wanted my tasks to be unique by project and description. This means I want to
    generate the equals and hashCode methods, using only those two fields, so I'll need to deselect all other fields on
    the first popup. I want to select them on the last popup, because they should never be null.
*/
//End-Part-4

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(project, description, priority, assignee, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!getProject().equals(task.getProject())) return false;
        return getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProject().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

//Part-5
/*
        Finally, I want to implement Comparable on this class, so I'll scroll up to the Task declaration and include
    implements Comparable. I should always specify a type argument when I implement comparable, and here it should be
    Task, the current class. I've got an error, because I haven't yet implemented the abstract method on Comparable.
    I'll use IntelliJ's help to do that.

        That gives me the default implementation, which just "returns 0". I want to compare two fields on this class. I'll
    start by creating a local variable, int result, and assign that the comparison of the project fields, on this and on
    the argument passed. if the result is zero, the project names are the same. In that case, I want to then sort by the
    task description. I'll assign result to be the comparison of the description fields on these objects. And I'll return
    the result. That finishes the code for the task class, at this point. Setting up all these methods manually can be
    quite tedious, so Thank goodness for IntelliJ's tools here, that help us with this boiler plate code. Now that I've
    got the Task class, I want to load up some data, into tasks, and put them in sets. I'll do this with a TaskData class,
    so I'll create that.
*/
//End-Part-5

    @Override
    public int compareTo(Task o) {

        int result = this.project.compareTo(o.project);
        if (result == 0) {
            result = this.description.compareTo(o.description);
        }
        return result;
    }
}
