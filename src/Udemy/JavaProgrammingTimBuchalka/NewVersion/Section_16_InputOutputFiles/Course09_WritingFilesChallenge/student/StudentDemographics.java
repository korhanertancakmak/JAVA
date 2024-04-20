package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course09_WritingFilesChallenge.student;

import java.util.StringJoiner;

public record StudentDemographics(String countryCode, int enrolledMonth,
                                  int enrolledYear, int ageAtEnrollment, String gender,
                                  boolean previousProgrammingExperience ) {

    @Override
    public String toString() {
        return "%s,%d,%d,%d,%s,%b".formatted(countryCode,
                enrolledMonth,enrolledYear, ageAtEnrollment,gender,
                previousProgrammingExperience);
    }


//Part-2
/**
     With the same way generating toString method, open the dialog window, which should be selected by default. And,
 next to the Template select list, Settings. I'll select that. On that window, select the Templates tab, and you can see
 the list of existing templates, each generates the toString method in different ways. You may have a different list of
 templates. I'll select the StringJoiner template. This code uses a combination of Java, and scripting language used by
 the IDE, called the Velocity Template Language. Next, I'll select the copy icon at the top of the left pane. This prompts
 for a new template name. I'll call it JsonBuilder, with StringJoiner  in parentheses, and I'll click OK. Now, this code
 uses StringJoiner,

                                         public java.lang.String toJSON() {
                                             return new java.util.StringJoiner(", ", "{", "}")
                                             #foreach($member in $members)
                                             #if(!$member.modifierStatic)
                                             .add("\"$member.name\":##
                                             #if ($member.primitiveArray || $member.objectArray)
                                             " + java.util.Arrays.toString($member.name)##
                                             #elseif ($member.string)
                                             \"" + $member.accessor + "\""##
                                             #else
                                             " + $member.accessor ##
                                             #end
                                             )
                                             #end
                                             #end
                                             .toString();
                                         }

 so all fields are separated by the delimiter, declared as the first argument, a comma. The prefix is the second argument
 to the StringJoiner constructor. After the className there are 2 hash signs just mean the statement is continued on the
 next line. Then the .class.getSimpleName, and that's appended to an opening square bracket. All of this code

                                      ", ", $classname##.class.getSimpleName() + "[", "]"
                                                            to
                                                      ", ", "{", "}"

 just outputs the simple class name. I don't really want this in my output, so I'll remove all of this. I'll change the
 square brackets to curly braces. Next, this code loops through all the class members, you can see foreach statement. An
 if statement is followed by, and it says that if it's not a static member, it will add information about this field to
 the StringJoiner instance. "$member.name=##, first it will add the field name followed by an equals sign. In Json, field
 names are the keys, and the keys should be wrapped in double quotes.

                                                    .add("$member.name=##
                                                            to
                                                 .add("\"$member.name\":##

 This means I want to add a backslash double quote, after the first double quote there. I'll also add a backslash after
 the .name there. That equals sign needs to be a colon, for Json, so I'll change that. I'll skip over the if statement
 for a primitive array or object array, and go down to the next else if. If this field is a string, the code will wrap
 the value in single quotes. Here,

                                                '" + $member.accessor + "'"##
                                                             to
                                              \"" + $member.accessor + "\""##

 Here, I want to change the single quotes, to back slash double quotes, in both cases. Finally, for good measure, I'll go
 up and change the name from toString to JSON.

                                            public java.lang.String toJSON() {
                                                            to
                                            public java.lang.String toJSON() {

 I'll click OK to save this. It's now selected in my template drop down. In the bottom left corner of this dialog, I want
 to uncheck Insert @Override, because in this case I'm not overriding any method. I can press OK after that.
**/
//End-Part-2
    public String toJSON() {
        return new StringJoiner(", ", "{", "}")
                .add("\"countryCode\":\"" + countryCode + "\"")
                .add("\"enrolledMonth\":" + enrolledMonth)
                .add("\"enrolledYear\":" + enrolledYear)
                .add("\"ageAtEnrollment\":" + ageAtEnrollment)
                .add("\"gender\":\"" + gender + "\"")
                .add("\"previousProgrammingExperience\":" + previousProgrammingExperience)
                .toString();
    }

//Part-2
/**
        This inserts the new toJSON method in the StudentDemographics code. The benefit of this exercise isn't totally
 apparent, unless we use this template more than a couple of times. I'll go over to my Student class next,
**/
//End-Part-2
}
