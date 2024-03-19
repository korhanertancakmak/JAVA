package CourseCodes.NewSections.Section_19_Concurrency.Course05_MultithreadingAndMemory;

public enum ThreadColor {

    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_WHITE("\u001b[37m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_RED("\u001B[31m"),
    ANSI_YELLOW("\u001b[33m");

    private final String color;

    ThreadColor(String color) {
        this.color = color;
    }

    public String color() {
        return color;
    }
}

//Part-2
/**
        Before I add my enum constants, I'll first add a private field, a String, for color, that will consist of ANSI
 unicode characters. I've got an error on that, and if I hover over that error, IntelliJ suggests I add a constructor
 parameter. I'll do that. Now, I've got a constructor, that takes a string, the color, and sets my enum's color field to
 that. Notice also the semi colon, that got added by IntelliJ, before the declaration of my field. I'll get rid of that.
 I'll add my enum constant values now, with the constructor declared for each, that will define a String. This string,
 when printed, will make all the following output be that color. Each of the strings I'll be using are ansi representations,
 of a special combination of characters, that for most operating systems, will change the color of the console's text.
 I can reset the color as well, so I'll start my values with an ANSI_RESET. I'll next set up ANSI BLACK, and ANSI WHITE,
 if you're using a dark theme, you might want white text. The rest of the colors, I'll list in alphabetical order, starting
 with ANSI BLUE, then ANSI cyan, which is a bright greenish blue. I'll include green, And ANSI PURPLE, Next, I'll have
 ANSI RED, And finally, ANSI YELLOW. This should give you enough colors, depending on your dark or light theme, to be
 able to see multiple threads output statements, printed in different colors. One other thing I want here, is an accessor
 method for this color field. I'm going to use the record's way of creating an accessor, and skip the get prefix. This
 needs to be public and return a String. And I'll just return color. Next, I'll create a class, in addition to the Main
 class, and I'm just going to add this in the Main.java source file.
 **/
//End-Part-2