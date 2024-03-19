package CourseCodes.NewSections.Section_12_Generics.Course09_StaticMethodsMultipleUpperBounds.util;

//Part-2
/*
        This will have one method, that when implemented, will let us match an instance by one of its field values. The
    method returns a boolean, is named Match Field Value and has two string parameters. Next, I'll create a new class,
    which will also be in the util package, and called QueryList.
*/
//End-Part-2
public interface QueryItem {

    public boolean matchFieldValue(String fieldName, String value);
}
