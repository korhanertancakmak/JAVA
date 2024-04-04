package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev2_RecordConstructor;

//Part-2
/*
        As you know, this is all we need to have an operational class, whose fields(name and dob) are final. A constructor
    is inserted by the compiler, when it generates the byte code or class file. It takes the same arguments in the same
    order as those described in the Person record. This constructor is called the canonical constructor. Notice that if
    I want to generate a constructor, IntelliJ wants me to select from one of three options. Record Constructors come in
    three flavors.

        The Canonical, or Long constructor is the implicitly generated constructor. You can explicitly declare your own,
    which means the implicit one won't get generated. If you do declare your own, you must make sure fields all get
    assigned a value.

        The Custom constructor is just an overloaded constructor. It must explicitly call the canonical constructor as
    it's first statement.

        The Compact, or Short constructor is a special kind of constructor, used only on records. It's a succinct way of
    explicitly declaring a canonical constructor. Ok, so let's see what we can, and can't do with these.
*/
//End-Part-2

public record Person(String name, String dob) {

//Part-3
/*
        Getting back to my code, I'm going to generate the canonical constructor. If this is all you're going to do, you
    don't need to generate it, but maybe you want to do some transformations or validation, before the assignments. In
    my case, I want to replace any dashes in my dob argument, with forward slashes, before assigning this value to my
    dob field. To test this out, I'll create an instance of this record in the main method and print that out.
*/
//End-Part-3

/*
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob.replace('-', '/');
        //this.dob = this.dob.trim();
    }
*/

//Part-5
/*
        So what happens if I don't actually assign a value to the dob field, in this canonical constructor? I'll comment
    out that second line, in my constructor. IntelliJ flags that parameter, dob, in the Record parameter list, as having
    a problem. Hovering over that, it reads, "Record component dob might not be initialized in canonical constructor".
    Maybe I could try an instance initializer to set it.
*/
//End-Part-5

/*
    {
        this.dob = "01/01/1900";
    }
*/

//Part-6
/*
        An instance initializer starts with a block of code at the class level. Here, I'll try to assign a string literal,
    to the dob field. Now, I've got a different problem, and that's because instance initializers aren't allowed in records.
    The only place to make field assignments, is in this canonical constructor only. Let me comment those last two changes.

        Let's say that now, I want to trim this string next, and I'll just reassign this value back to the dob field.

                                    this.dob = this.dob.trim();

        This isn't permitted either, because all fields on a record are final, so I can't do this. I'll revert that,
    commenting that extra statement.
*/
//End-Part-6

    public Person(Person p) {
        this(p.name, p.dob);
    }

//Part-7
/*
        I'll generate another constructor. Note that the 3 options for Constructors do not popup here. For now, I'll push
    the Select None button and will explain why we don't get 3 options later in this lecture. I'll pass a Person argument
    to this constructor. I want this to be a copy constructor, in other words. Ok, there's another problem, and it's that
    a non-canonical record constructor, like this one, must delegate to another constructor. I'll do that, passing name
    from the person argument, and the dob as well. Going back to the Main class,
*/
//End-Part-7


    public Person {
        if (dob == null) throw new IllegalArgumentException("Bad data");
        dob = dob.replace('-', '/');
        //dob = this.dob.replace('-', '/');

        //this.dob = dob;
    }


//Part-9
/*
        Lets see what happens when I try to generate another constructor. I don't have the 3 choices I had before. I now
    don't have the option to create a compact, or canonical constructor. I can only generate a custom contractor. I'll
    close that popup without generating anything. What happened to my other options? Let me comment out that first constructor,
    the explicit canonical constructor. Now I'll press Alt Insert again. All my options are back. So why is this? When
    you declare an explicit canonical constructor, you can't create another constructor. You also can't create a compact
    constructor, since it's intertwined with the implicitly created constructor.

        In this case, I now want to review the compact constructor, so I'll select that. What do you notice about this
    constructor? It's missing parentheses, and that's what makes this significant and different. This code gets inserted
    into the implicit canonical constructor, before any final fields are assigned. The advantage of using this compact
    constructor, is that you can just focus on just the custom bit of code, and leave the boiler plate code to be generated.
    Let's say in this case, I want to check for null, before I replace any dashes in the date of birth variable. I'll
    throw an Illegal Argument Exception, if date of birth is null. Otherwise I'll replace the dashes, with slashes. Notice
    here, that I'm referring to constructor arguments that aren't explicitly declared by this compact constructor. This
    constructor has access to all the named arguments of the canonical constructor, so in this case, name and dob. It can
    reassign values to these argument variables. All of this is occurring without explicitly assigning these values, to
    the instance fields. I never assign these arguments to the fields in other words, but I'm not getting any errors.
    What happens if I try to do that, meaning if I actually do the assignment.

                            this.dob = dob;

        I get the error message, cannot assign a value to final variable dob. You can only edit or validate the method
    arguments, because the final fields have yet to be assigned. I'll comment that last statement. I'll next change that
    last statement, and instead of just dob, I'll try to execute replace on this.dob.

                            dob = dob.replace('-', '/');
                                        to
                            dob = dob.replace('-', '/');

    This gives me another error, that this dot d o b might not have been initialized. I can't use the final fields in any
    of my code here, for the reason that this code will get called, before they actually get set. I'll revert that too,
    and now I want to show you a summary of these rules.

                                            The Compact Constructor

       - You can't have both a compact constructor and an explicit canonical constructor.
       - This constructor is declared with no parentheses, so no arguments.
       - It has access to all the arguments of the canonical constructor. Don't confuse the arguments with the instance
         fields!
       - You can't do assignments to the instance fields in this constructor.
       - The implicit canonical constructor's assignments occur after the execution of this code.

       Ok, I want to pause here a moment, to introduce you to another command line tool, packaged with java.

                                        The Java Class File Disassembler

       This is "javap". It lists class members, by default just public and protected members in the class file. This helps
    us 'see' implicit code in the compiled class file. You can see all the options we can use on this tool on oracle's
    site. The only one I'll be demonstrating is -p, which is short for -private, and will show all members on a class,
    regardless of the access modifiers. The default mode would only print the public and protected members. This tool can
    be useful with types like records and enums, that do have quite a bit of implicit code. Let's play with this for just
    a minute.

        First, I need to identify where my class files are being created, and you might remember, we talked about this
    when you were setting up IntelliJ. I'll expand the project panel, and show you that my class files are generated
    in an out folder, under the project folder. I'll continue to expand these folders, and you'll see I have three files
    under the folder that matches my package structure. These are the compiled classes. What I'll do next, is open a
    terminal session inside IntelliJ's IDE. I can do this by selecting the tab at the bottom of the screen where my output
    is displayed. I'll select Terminal there. Note that my Terminal window is expanded to show more output. You may want
    to do the same by expanding the window in the usual way. I am going to execute javap from this shell, using the
    argument dash p then specifying the location of the class file.

          javap -p out/production/CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course09_ConstructorReviews.Rev2_RecordConstructor/Person

    I'm going to first show you the Person class, which is my Record. dash p shows all classes and members, including
    private ones. Note that this path needs to exactly match the path we say when I expanded the out folder earlier,
    including the right case for the project name. Note also that the forward slashes will work on all operating systems,
    including windows. Running this,

            public final class Rev2_RecordConstructor.Person extends Rev2_RecordConstructor.Record {
                private final java.lang.String name;
                private final java.lang.String dob;
                public Rev2_RecordConstructor.Person(java.lang.String, java.lang.String);
                public Rev2_RecordConstructor.Person(Rev2_RecordConstructor.Person);
                public final java.lang.String toString();
                public final int hashCode();
                public final boolean equals(java.lang.Object);
                public java.lang.String name();
                public java.lang.String dob();
            }

    you can see all the hidden implicit code, that gets generated for us on a record. You can see the private final fields,
    first the name, then dob. The next two statements are the constructors, the first is my custom constructor, but the
    second is the implicit canonical constructor. After that, there are the methods, toString, hashCode and equals, as
    well as the accessor methods. Ok, so you can see this output is helpful to understand what code is being implicitly
    generated by the compiler. If you don't get this output on your computer, but instead get an error about javap not
    being recognised or found, it means that javap is not in your computers path, and you need to add it. Or more specifically,
    the folder containing the javap executable is not in the path. This is most likely a Windows issue, as in Mac and
    Linux will add the path automatically when the JDK is installed. The folder you want to add to the path is the bin
    subfolder in the Java development kit you installed way back in the early parts of this video. I'll open up Windows
    11 Settings here, and type "envir", which is short for environment and choose Edit environment variables for your
    account at the bottom. Click Path and click Edit if you want to add the path. We'll discuss the enum and its constructors
    in the next lecture.
*/
//End-Part-9

}
