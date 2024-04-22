# [File Writing Challenge]()
<div align="justify">

The first thing I want to do is, show you how to create your own to **String** template.
I'll start in the **StudentDemographics** record,
with my pointer just below the _toString_ method.

```java  
public record StudentDemographics(String countryCode, int enrolledMonth,
                                  int enrolledYear, int ageAtEnrollment, String gender,
                                  boolean previousProgrammingExperience ) {

    @Override
    public String toString() {
        return "%s,%d,%d,%d,%s,%b".formatted(countryCode,
                enrolledMonth,enrolledYear, ageAtEnrollment,gender,
                previousProgrammingExperience);
    }
}
```

I'll press alt+Insert, or you could select Code from the file menu, then generate.

![image13a](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13a.png?raw=true)

Either way, select the _toString_ method as the item to generate.
All the fields in the dialog window, should be selected by default.
Here, next to the **Template** select list, is a button, **Settings**.
I'll select that.

![image13b](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13b.png?raw=true)

On this window, I want to select the **Templates** tab.

![image13c](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13c.png?raw=true)

Here, on the left, I can see the list of existing templates, 
each generates the _toString_ method, in different ways.
You may have a different list of templates.
I'll select the **StringJoiner** template.
The template code is displayed on the right.
This code uses a combination of Java, and a scripting language used by the IDE,
called the _Velocity Template Language_.
What I'll do next, is make a copy of this coding template.
I can select the **copy** icon, at the top of the left pane.

![image13d](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13d.png?raw=true)

This prompts for a new template name.
I'll call it **JsonBuilder**, with **StringJoiner** in parentheses, 
and I'll click OK.

![image13e](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13e.png?raw=true)

This code uses **StringJoiner**, so all fields are separated by the delimiter,
declared as the first argument, a comma.
The prefix is the second argument to the **StringJoiner** constructor.
Here you see, the dollar sign class name.
The two hash signs just mean the statement is continued on the next line.
Then the `.class.getSimpleName`, and that's appended to an opening square bracket.
All of this code just outputs the simple class name.

![image13f](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13f.png?raw=true)

I don't really want this in my output, 
so I'll remove all of this I'll change the square brackets to curly braces.
Next, this code loops through all the class members, 
here you can see a foreach statement.
This is followed by an if statement.
This statement is saying, if it's not a static member, 
it will add information about this field, to the **StringJoiner** instance.

![image13g](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13g.png?raw=true)

First it will add the field name followed by an equals sign.
In Json, field names are the keys, and the keys should be wrapped in double quotes.

![image13h](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13h.png?raw=true)

This means I want to add a backslash double quote, after the first double quote there.
I'll also add a backslash after the dot name there.
That equals sign needs to be a colon, for Json, so I'll change that.

![image13i](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13i.png?raw=true)

I'll skip over the if statement for a primitive array or object array,
and go down to the next else if.
If this field is a string, the code will wrap the value in single quotes.

![image13j](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13j.png?raw=true)

Here, I want to change the single quotes, to backslash double quotes, in both cases.
Finally, for good measure, I'll go up and change the name from _toString_ to _JSON_.
I'll click OK to save this.

![image13k](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/images/image13k.png?raw=true)

It's now selected in my template drop down.
In the bottom left corner of this dialog, 
I want to **uncheck** _Insert @Override_, 
because in this case I'm not overriding any method.
I can press OK after that.

```java  
public record StudentDemographics(String countryCode, int enrolledMonth,
                                  int enrolledYear, int ageAtEnrollment, String gender,
                                  boolean previousProgrammingExperience ) {

    @Override
    public String toString() {
        return "%s,%d,%d,%d,%s,%b".formatted(countryCode,
                enrolledMonth,enrolledYear, ageAtEnrollment,gender,
                previousProgrammingExperience);
    }

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
}
```

This inserts the new _toJson_ method in the **StudentDemographics** code.
The benefit of this exercise isn't totally apparent,
unless we use this template more than a couple of times.
I'll go over to my **Student** class next, and find it's _toString_ method.

```java  
public String toJSON() {
    return new StringJoiner(", ", "{", "}")
            .add("\"studentId\":" + studentId)
            .add("\"demographics\":" + demographics)
            .add("\"coursesEnrolled\":" + courses)
            .add("\"engagementMap\":" + engagement)
            .toString();
}
```

Underneath that I'll generate the _toString_ method,
but select the Json Builder template here.
I'll make sure to uncheck the insert Override checkbox before I hit OK.
And that gives me a _toJSON_ method in the **Student** class.

```java  
public String toJSON() {
    return new StringJoiner(", ", "{", "}")
            .add("\"studentId\":" + studentId)
            .add("\"demographics\":" + demographics.toJSON())
            //.add("\"coursesEnrolled\":" + courses)
            //.add("\"engagementMap\":" + engagement)
            .toString();
}
```

I'm going to comment out the two add statements for _coursesEnrolled_ 
and _EngagementMap_ right now.
Finally, for the demographics, 
I really want to pass the result of the _toJSON_ method on that class.
To see if this works, I'll create a new class called **Challenge**.

```java  
public class Challenge {

    public static void main(String[] args) {
        
        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");

        //List<Student> = Stream
        List<String> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                //.limit(25)
                .limit(2)
                .map(Student::toJSON)
                .toList();
        students.forEach(System.out::println);
    }
}
```

I'll go to the **Main** class, and copy the code that creates my list of students.
I don't want the header, so starting at the declaration of the _jmc_ course,
through the _toList_ operation.
I'll paste that in the _main_ method of my **Challenge** class.
I'll change 25 to just 2 in the limit operation for now, 
while I'm testing out my code.
Next, I'm going to change my **List** of **Students** to a **List** of **Strings**.
That's because, I'll insert a _map_ operation, that will take a student, 
mapping it to a string, using the _toJson_ method.
I can do that with a method reference.
I'll print each element in this
list to the console to start.
Running this:

```html  
{"studentId":1, "demographics":{"countryCode":"CN", "enrolledMonth":9, "enrolledYear":2019, "ageAtEnrollment":51, "gender":"F", "previousProgrammingExperience":false}}
{"studentId":2, "demographics":{"countryCode":"GB", "enrolledMonth":2, "enrolledYear":2015, "ageAtEnrollment":22, "gender":"U", "previousProgrammingExperience":false}}
```

And maybe that's right, but it's kind of hard to tell.
I'll copy the last student record in my console, 
and I'll pull up a _JSON linter_.
A **linter** is a software development tool, 
that will analyze source code for potential errors, and styling issues.
I'm showing a link of a popular one [here](https://jsoonlint.com).
These tools enable you to paste text into a text area, 
and both nicely format, and validate, that the text is well-formed.
I'll open that link up now.
I can paste my clipboard text, right into this text area.
I can press the validate button which is below this text block.



This formats the JSON, and validates it, 
the results are shown below the validate button.
Now I know I've created Valid
JSON for this one student record.
OK, so that's all good.
This video is getting long, and I still haven't
written a single character to an output file yet.
Luckily, this part is pretty easy.
I'll be doing this with Files dot
writeString, passing it one very large string.
How do I do this?
Getting back to the Challenge code,
I'll first change my stream pipeline.
I'll make the result a String, and not a list.
Next, I'll remove both the two List statement,
and that last statement, that
prints each student json record.
Instead of two List, I want to use
the collect terminal operation.
I can use Collectors.joining which uses
a StringJoiner underneath the covers.
The first argument is the delimiter. I'll make
that a comma. The next argument is a prefix which
for this array of students, needs to be an opening
square bracket, and my suffix is a closing square bracket.
I'll print this to the console as well.
There's actually one more change I want to make,
to help with the readability
of the output a little bit.
I want to make my delimiter, between
students, also include a line separator.
I'll set up a local variable called
delimiter, and set that to a comma,
plus the System.lineSeparator.
I'll pass that variable as the
first argument, instead of just a comma.
Ok, now it's time to write that out to a file.
I'll start with a try block. I'll call
Files.writeString, passing it a new Path,
for a file names students.json,
and I'll pass my students string.
I'll catch the IOException. And just wrap
that in a runtime exception and throw it.
I'll run this, again with

```html  

```

just 2 records, to test it.
I'll see my students printed in the console.
Notice that I have an opening square bracket
and ending square bracket, and at the end
of the first student, there's a comma.
This is what the Collectors.joining did.
But I also see students.json listed in my
project panel,
at the project root, and I can open that up.
There you can see my students json
code, as it was in the console.
I'll copy the entire contents of that file,
and again test it in the Jayson linter.
I'll paste the contents of
my file into the text window.
I'll scroll down and hit the
validate JSON button again.
I can see my text gets formatted,
and it's valid JSON for this array of two students.
Getting back to my code, I can
change my number of students to 1000.
I'll re-run it, and examine
that output file again.
You can see this code had no trouble writing
1000 records to the file, and console.
I'll quick go to the Course record,
and add the two Jayson method to it.
I'll go to the CourseEngagement class,
and do the exact same thing, again right
under the existing two String method.
Ok, so that was easy.
I want to get back to
the Student class, and the two JSON method.
Because the coursesEnrolled and engagementMap
types are part of collections, I
need to create a json array for each.
I'll show you two ways to do this.
The first, is just to use the StringJoiner class.
I'll set up a local variable, a String Joiner
type called courses, and create a new instance,
with a delimiter of comma. The prefix
and suffix will be square brackets.
I'll loop through coursesEnrolled.
And add each courses JSON to the StringJoiner.
for the engagement map, I'll use a stream, on its
values. I'll map to the CourseEngagement JSON.
Like I did in the Challenge's main method, I'll
collect the strings using Collectors.joining,
with a comma, and square brackets
again for the prefix and suffix.
Finally I'll uncomment the two adds
I have commented out below, changing
coursesEnrolled as the variable to courses,
and I'll change engagementMap to engagement.
I'll re-run the code.
Again, it's rather hard to see
it, in this students.json file
I'll copy that last statement.
I'll pop back over to the JSON Lint site.
I can use the clear button to erase
that text area, using the clear button.
I'll paste my Jayson in there.
Since I grabbed the last row,
there's an extra ending square bracket at
the end of this line, so I'll remove it.
I'll hit the validate button.
It formats it and tells me I have valid JSON.
Now, look at this formatted
JSON for the 1000th student.
You can see I now have coursesEnrolled,
and that's an array of two courses.
I have engagementMap, and array
of two engagement records.
Ok, so how'd you do?
I didn't really expect you to
play too much with the two string templates.
I did however, want to let you know that
feature existed, if you ever want to
customize string functions that way.
Without it, editing JSON can be tedious.
As I said, there are some popular external
libraries, that will also help
you do this kind of thing.
This code gave me the opportunity to
review the StringJoiner class with you,
and how to use it with and without streams.
Now you've read data from,
and written data to, a file.
Next, I want to talk to you about transferring
between files, and cover a few loose ends.
Afterwards, I'll launch into
slightly more advanced topics,
so I'll see you in that next video.
</div>