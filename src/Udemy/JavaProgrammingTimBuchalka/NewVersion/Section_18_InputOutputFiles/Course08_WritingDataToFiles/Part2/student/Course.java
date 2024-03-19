package CourseCodes.NewSections.Section_18_InputOutputFiles.Course08_WritingDataToFiles.Part2.student;

public record Course(String courseCode, String title) {

    public int getLectureCount() {
        return 15;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}
