package CourseCodes.NewSections.Section_17_Streams.Course08_StreamingStudentsProject;

//Part-2
/*
        I'm going to put this in a student. I'll add my three fields, a courseCode, which is the identifier for the course,
    a title, and a lecture count. I'm going to include two different types of constructors. First, I'll generate a custom
    constructor for just course code and title. I'll change zero to 40, making that the default lecture count.
    I'll insert a compact constructor next, just to add a little validation on lectureCount. I'll add this above my custom
    constructor. Now, I'll just add code to check if the lecture count argument is less than or equal to zero, and if it
    is, I'll make it one. I'm going to use this to check percentages, and I don't ever want to get a divide by zero error.
    I'll generate the toString method, and as usual select None, and replace the generated return statement there. I'll
    replace that with a formatted String, that prints the courseCode, and title of the course. That's all I need for my
    Course record. Next, I'll set up a CourseEngagement class.
*/
//End-Part-2

public record Course(String courseCode, String title, int lectureCount) {

    public Course {
        if (lectureCount <= 0) {
            lectureCount = 1;
        }
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}
