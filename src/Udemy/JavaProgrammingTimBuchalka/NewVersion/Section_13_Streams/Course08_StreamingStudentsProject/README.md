# [Streaming Student Project]()
<div align="justify">

Ok, so let's build this.
I've created the usual **Main** class.
I'll start with the **Course**,
and that's going to be a record.

```java  
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
```

I'm going to put this in a student. 
I'll add my three fields, 
a _courseCode_, which is the identifier for the course,
a _title_, and a lecture count. 
I'm going to include two different types of constructors. 
First, I'll generate a custom constructor for just course code and title. 
I'll change zero to 40, making that the default lecture count.
I'll insert a compact constructor next, 
just to add a little validation on _lectureCount_. 
I'll add this above my custom constructor. 
Now, I'll just add code to check 
if the lecture count argument is less than or equal to zero, 
and if it is, I'll make it one. 
I'm going to use this to check percentages, 
and I don't ever want to get a divide by zero errors.
I'll generate the _toString_ method, 
and as usual select **None**, 
and replace the generated return statement there. 
I'll replace that with a formatted String, 
that prints the _courseCode_, 
and _title_ of the course. 
That's all I need for my **Course** record. 
Next, I'll set up a **CourseEngagement** class.

```java  
public class CourseEngagement {

    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }
}
```

I want this to be mutable to some degree, 
but I still want to use encapsulation 
to control the mutations. 
I've said I want five fields in this class. 
The first is the course, 
that this engagement activity pertains to, 
and I'll make that final. 
It'll get set on construction only. 
The second field is also final, 
and it's the _enrollmentDate_,
and that's a **LocalDate**. 
I've used this class a couple of times, 
usually to get the current year or month. 
For this code, I'm not going to do a lot of date processing. 
I really just care about year and month, 
but it's still convenient to use this class. 
_engagementType_, right would start with enrollment, 
then track progress. 
_lastLecture_ will keep track of the last lecture the student watched. 
I'll use another **LocalDate** for the _lastActivityDate_.

I'll generate a constructor, picking the first three fields. 
I'll insert a new line after the _enrollmentDate_,
so that the whole signature is visible. 
I'll assign the _lastActivityDate_ information 
to be equal to the enrollment date, to start with. 
I can include this in an assignment chain, 
so I'll insert equals _lastActivityDate_, 
after `this.enrollmentDate` equals, 
and before that last _enrollmentDate_. 
I'll generate getters for all my fields. 
I'm going to change some of these, 
starting with _getCourse_.

```java  
public String getCourseCode() {
    return course.courseCode();
}

public int getEnrollmentYear() {
    return enrollmentDate.getYear();
}

public String getEngagementType() {
    return engagementType;
}

public int getLastLecture() {
    return lastLecture;
}

public int getLastActivityYear() {
    return lastActivityDate.getYear();
}

public String getLastActivityMonth() {
    return "%tb".formatted(lastActivityDate);
}

public double getPercentComplete() {
    return lastLecture * 100.0 / course.lectureCount();
}

public int getMonthsSinceActive() {

    LocalDate now = LocalDate.now();
    var months = Period.between(lastActivityDate, now).toTotalMonths();
    return (int) months;
}
```

Instead of _getCourse_, I want it to just be, 
_getCourseCode_, so that will return a **String**, 
and not a course. 
And I'll return the _courseCode_ on the course field. 
Next, Instead of _getEnrollmentDate_, 
I just want to return the year.
I'll return an **int**, instead of a local date, 
and I'll change the method name to _getEnrollmentYear_, 
and just return the year there. 
I want to do the same thing with _getLastActivityDate_, 
so it will return an **int**.
I'll rename it to _getLastActivityYear_, 
and return the _getYear_ result from that. 
I'm going to add some other getters now. 
The next one is _getLastActivityMonth_, 
and instead of an **int**, 
I'm going to have this return a **String**, 
which will be the month's abbreviation. 
I can use a time specifier `%t` for time. 
`%tb` is the way to get the three character month abbreviation.
The next method is _getPercentComplete_, 
and that returns a **double**. 
This will divide the _lastLecture_, 
by the total lectures, multiplying that by 100. 
I'll make 100 a decimal here, 
so that this will be processed as a **double**, 
and not truncated to an **int**. 
The next method is _getMonthsSinceActive_, 
and takes no parameters, but returns an **int**, 
again I'll make it _public_. 
This method will tell me how many months have elapsed, 
since a student last had any activity, for this course. 
There are different ways to get elapsed time. 
I'll cover date time processing in a lot of details 
in an upcoming section, 
but here, I'll just show you one way to get the number of months, 
between two date fields. 
First, I'll get the current date, 
and you've seen me do this, 
using the now method on **LocalDate**. 
There's a class called **Period**, introduced in JDK 8, 
that has a static method on it, named between, 
that takes two dates. 
This returns a **Period** instance,
which you can then query in different ways. 
This has a method called _toTotalMonths_, 
and that's what I want here.
This returns a **long** though, 
but I can safely cast that to an **int**, 
because my elapsed months in this code 
aren't going to exceed an integer's range. 
The next method I want to add is _watchLecture_.

```java  
void watchLecture(int lectureNumber, LocalDate currentDate) {

    lastLecture = Math.max(lectureNumber, lastLecture);
    lastActivityDate = currentDate;
    engagementType = "Lecture " + lastLecture;
}

@Override
public String toString() {
    return "%s: %s %d %s [%d]".formatted(course.courseCode(),
            getLastActivityMonth(), getLastActivityYear(), engagementType,
            getMonthsSinceActive());
}
```

I'll make this _package-private_, 
it'll only get called through the **student** instance. 
It takes a lecture number, and another date. 
I can use a static method on **Math**, 
called _max_ that will give me 
the highest of the two numbers I pass it. 
Students can review earlier lectures at any time,
but I don't want to set last lecture in that example.
I'll set the _lastActivityDate_ to the date passed. 
If these were a real app, I'd want the current date, 
but I'm going to mock up past activity, 
and pass the date in. 
The engagement type will just reflect 
the maximum lecture the student's watched. 
Finally, I want a _toString_ method, 
so I'll generate that with no fields. 
I'll replace that return statement.
I'll add my own return statement, and as usual, 
I'll use a formatted **String**, 
so it will print out the course code first.
Then, the active month, active year, the engageType, 
and the number of elapsed months, since the last activity. 
Ok, so that's if for the **CourseEngagement** class. 
Now, I'll build the **Student** class, 
and finish with a stream of randomly generated students. 
This will be the launching point, 
to your challenge on terminal operations, 
and will also provide many opportunities, 
to give stream operations a good workout.

```java  
public class Student {

    private static long lastStudentId = 1;
    private final static Random random = new Random();

    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;

    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience, Course... courses) {
        studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for (Course course : courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }
}
```

The first thing I'll include is a static long, 
to help do my student id assignments, 
and call it _lastStudentId_, setting that to 1. 
I'm also going to set up a private final static,
random variable, that I'll use for all the randomization 
I want to perform when I create random students. 
For my instance fields, I'm going to make them all private and final. 
I've got a _studentId_, a **long**.
_countryCode_ will be a **String**. 
_yearEnrolled_ is an **int**, 
and will be the year of the first enrollment. 
_ageEnrolled_ is an **int**, 
the age of the student when they enrolled. 
_Gender_ is a **String**. 
And I have a **boolean** for _programmingExperience_. 
I've also got a **Map** of **CourseEngagement** records, 
keyed by the course code, a **String**. 
I'll instantiate it here, as a **hashmap**. 
It doesn't need to be ordered. 
Next, I'll generate a constructor.
That includes all the fields, except for _studentId_, 
from the list there.
I'll insert a new line after _gender_.
I'll add a variable argument for courses, 
so I can set up a new student with one or more courses. 
In the constructor, 
I want my student id to be the value of last student id, 
and I'll increment that static field, 
after the assignment occurs. 
Since _courses_ is a variable argument, 
I can use it in an enhanced for loop, 
which I'll do next. 
For each course passed in, 
I'll call an _addCourse_ method,
which I'll be implementing in a minute. 
I'm going to pass the course and also a date. 
I can build a date 
from its parts, meaning, year, month, day, using `LocalDate.of`. 
In this case, all students will get enrolled 
on the first month of the year, and on the first day.

```java  
public void addCourse(Course newCourse) {
    addCourse(newCourse, LocalDate.now());
}

public void addCourse(Course newCourse, LocalDate enrollDate) {

    engagementMap.put(newCourse.courseCode(), new CourseEngagement(newCourse, enrollDate, "Enrollment"));
}
```

Next, I'll add the _addCourse_ method 
so this code compiles, and that'll be _public_ and _void_. 
It'll take a course, I'll call that _newCourse_, 
and a date, _enrollDate_. 
This adds an entry to the engagement map, 
keyed by course code.
The _map_ value is a **CourseEngagement** instance, 
and that takes a course, an enrollment date, 
and an engagement type which will be enrollment to start. 
I also want an overloaded version of this, 
that doesn't take any date. 
I'll put this one above the other. 
For this one, I'll pass the current date to the overloaded method. 
Next, I'll generate a bunch of getters, all public, 
because this is the data I'll be using, 
for many of the stream code samples coming up.
I'll generate getters for every field, 
except the first one, the static _lastStudentId_.

```java  
public long getStudentId() {
    return studentId;
}

public String getCountryCode() {
    return countryCode;
}

public int getYearEnrolled() {
    return yearEnrolled;
}

public int getAgeEnrolled() {
    return ageEnrolled;
}

public String getGender() {
    return gender;
}

public boolean hasProgrammingExperience() {
    return programmingExperience;
}

public Map<String, CourseEngagement> getEngagementMap() {
    return Map.copyOf(engagementMap);
}

public int getYearsSinceEnrolled() {
    return LocalDate.now().getYear() - yearEnrolled;
}

public int getAge() {
    return ageEnrolled + getYearsSinceEnrolled();
}

public int getMonthsSinceActive(String courseCode) {

    CourseEngagement info = engagementMap.get(courseCode);
    return info == null ? 0 : info.getMonthsSinceActive();
}
    
public int getMonthsSinceActive() {

    int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
    for (String key : engagementMap.keySet()) {
        inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
    }
    return inactiveMonths;
}
```

I want to change the getter that's got the name, 
_isProgrammingExperience_, to _hasProgrammingExperience_. 
I'll make a change to the _getEngagementMap_ method, 
and that's because I want to return a defensive copy.
I'll return a copy of the _engagementMap_ using `Map.copyOf`. 
I'm going to next, add getters for some calculated fields,
that'll be useful.
I'll create a method called _getYearsSinceEnrolled_. 
This returns an **int**. 
It returns the years between the current year,
which I'll get from `LocalDate.now`, 
and the enrolled year. 
After this, I'll add _getAge_, 
which also returns an **int**.
This one will return the enrolled age, 
plus the years since enrolled. 
Next, I need a method 
that will give me the elapsed months, 
from the last activity. 
It should return an **int**, 
and be called _getMonthsSinceActive_, 
taking a **String** for the course code. 
I'll get the **CourseEngagement** record from the map,
using _courseCode_. 
I'll use a ternary operator to avoid a null pointer exception, 
so if the _course_ wasn't found, it'll return 0 months. 
Otherwise, I'll call the _getMonths_ from active method, 
on the **courseEngagement** record. 
Next, I want to get the overall _MonthsSinceActive_,
so this has the same name, returns an **int**, 
but doesn't take any parameters. 
In this method, I'll first set up a variable,
an int, _inactiveMonths_. 
I'll initialize this to the maximum number of months possible, 
getting the current year and subtracting 2014, 
and multiplying 12 to get months. 
Any activity for a student should be less than this number. 
I'll loop through each key of the engagement map 
for each course code. 
I'll use another static method on **Math**, called _min_.
It gives me the lesser of the two numbers. 
This means I'll get back the months elapsed 
for the most recent activity.
I'll add one more getter method on this class, 
a method called _getPercentComplete_.

```java  
public double getPercentComplete(String courseCode) {

    var info = engagementMap.get(courseCode);
    return (info == null) ? 0 : info.getPercentComplete();
}

public void watchLecture(String courseCode, int lectureNumber, int month, int year) {

    var activity = engagementMap.get(courseCode);
    if (activity != null) {
        activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
    }
}
```

The **CourseEngagement** class has a method of the same name, 
so in this case, I'll invoke that, 
so I'll need course code as the input, 
and it will return a **double**. 
I'll use the course code, 
to get a course engagement record. 
If that doesn't exist, I'll just return zero, 
otherwise I'll call _getPercentComplete_ on it. 
I said in the introduction that
I wanted a method called _watchLecture_, 
that takes a course code, a lecture number, 
and a month and year for the date.
This is public, 
because you can imagine a course management program, 
calling this code, when a student logs into a course, 
and listens to a lecture. 
This takes a month and a year, both integers. 
Again, I'm passing this, 
because these will be randomly generated. 
I'll get the activity record from the engagement map, 
using the course code. 
If I found an engagement record, 
I'll call _watchLecture_ on that, 
passing it the lecture number. 
I can construct a date from the year, month, and day 1,
and pass that next. 
I use one as the day 
because I really don't care about days in the code ahead.

```java  
@Override
public String toString() {
    return "Student{" +
            "studentId=" + studentId +
            ", countryCode='" + countryCode + '\'' +
            ", yearEnrolled=" + yearEnrolled +
            ", ageEnrolled=" + ageEnrolled +
            ", gender='" + gender + '\'' +
            ", programmingExperience=" + programmingExperience +
            ", engagementMap=" + engagementMap +
            '}';
}
```

After this, I want to generate a _toString_ method, 
using all the fields. 
I went through this code to set up **Student** pretty quickly, 
because it's a bit tedious to set up all the getters and so on. 
I've written a lot of code without testing anything,
yet so it's time for a bit of code, 
in the _main_ method of the **Main** class.

```java  
Course pymc= new Course("PYMC", "Python Masterclass");
Course jmc= new Course("JMC", "Java Masterclass");
Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
System.out.println(tim);
```

I'll create a _pymc_ variable 
for the python master class with a course code of _PYMC_. 
That has the title Python masterclass. 
I'll do the same for the _jmc_ course, 
this time it's _JMC_, 
and Java masterclass for the title. 
I'll create a student _tim_, a new student, 
and country code, _AU_ for Australia. 
The year enrolled is 2019, 
and my age I'll say was 30, and M for male. 
I'll say true, I had programming experience, 
and the last parameter is the variable arguments, 
so I can pass in these two classes. 
And I'll print that student out. 
Running the code:

```html  
Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Oca 2019 Enrollment [59], PYMC=PYMC: Oca 2019 Enrollment [59]}}
```
            
You can see I've got a lot of information there. 
The student id is 1, and the student demographics are there,
country code, year enrolled, age, gender. 
_Tim_ is signed up for two classes, 
and right now the only engagement is enrollment, 
both happened in January 2019, and there, are the months elapsed,
in square brackets after that.

```java  
tim.watchLecture("JMC", 10, 5, 2019);
tim.watchLecture("PYMC", 7, 7, 2020);
System.out.println(tim);
```

I'll next call the _watchLecture_, first for the **JMC** class. 
I'll send it lecture number 10, in the fifth month of 2019.
Then for the python master class, 
let's say tim watched lecture 7, 
in the seventh month of 2020. 
I'll print the tim instance out again. 
Running this code:

```html  
Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Oca 2019 Enrollment [59], PYMC=PYMC: Oca 2019 Enrollment [59]}}
Student{studentId=1, countryCode='AU', yearEnrolled=2019, ageEnrolled=30, gender='M', programmingExperience=true, engagementMap={JMC=JMC: May 2019 Lecture 10 [55], PYMC=PYMC: Tem 2020 Lecture 7 [41]}}
```

My **courseEngagement** records are reflecting the additional data 
about what the last lecture watched was on each course.
The **student** class has a lot of different data elements on it. 
I planned it this way, so there would be a lot of diversities 
in the kinds of things we can do with this type, in our stream pipelines.

```java  
private static String getRandomVal(String... data) {
    return data[random.nextInt(data.length)];
}

public static Student getRandomStudent(Course... courses) {

    int maxYear = LocalDate.now().getYear() + 1;

    Student student = new Student(
            getRandomVal("AU", "CA", "CN", "GB", "IN", "UA", "US"),
            random.nextInt(2015, maxYear),
            random.nextInt(18, 90),
            getRandomVal("M", "F", "U"),
            random.nextBoolean(),
            courses);

    for (Course c : courses) {
        int lecture = random.nextInt(1, c.lectureCount());
        int year = random.nextInt(student.getYearEnrolled(), maxYear);
        int month = random.nextInt(1, 13);
        if (year == (maxYear - 1)) {
            if (month > LocalDate.now().getMonthValue()) {
                month = LocalDate.now().getMonthValue();
            }
        }
        student.watchLecture(c.courseCode(), lecture, month, year);
    }
    return student;
}
```

The last thing I want to do is, 
create a public static method on **student**, 
that will generate a new instance,
populated with a lot of random data. 
Before I do that, 
I'll create a little helper method that picks a random item
from an array of **Strings**. 
This will be private and static, 
and return a **String**. 
I'll call it _getRandomVal_, 
and it'll take a variable argument of strings. 
This will return an element from the array, 
using the index picked. 
It'll call _nextInt_ on random, 
passing the length of this array as the upper-bound number.
And then, I'll create the _getRandomStudent_ method, 
and that'll be public and static. 
It returns a **Student**, 
and the only data it will take is a variable argument for courses. 
First, I want the maximum bound for any generated year. 
This will be the current year + 1. 
I'll create a new **student**. 
I'll first pass a random country code, from a short list, 
using the helper method I just created, 
and passing a list of country codes. 
I'll pass an enrollment year between 2015, 
so the first year I started offering courses. 
After that, I'll use _maxYear_, 
so this will give me a year between 2015,
and the current year included. 
Next, I need an age, so I'll make that between 18 and 89. 
To get gender, I'll use _getRandomVal_, with three possibilities, 
_M_ for male, _F_ for female, or _U_ for unselected. 
I'll generate a random **boolean** for the _programmingExperience_ flag. 
Finally, I'll just pass the courses straight into this constructor. 
I can get a random student with this data, 
but I also want to create some random course activity, 
and I'll code this next. 
I'll loop through the courses that were passed. 
For each course, I want a random lecture number. 
I want a random year, but it should not be less than the year enrolled. 
I'll get a random month. 
If the year I got back is the current year, 
I need to make sure the month isn't ever greater than the current month. 
If it is, I'll just set it to the current month.
I'll execute _watchLecture_. 
This will update the engagement record, 
for this course, with some activity, other than enrollment. 
Now I'll get back the _main_ method:

```java  
Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
        .limit(10)
        .forEach(System.out::println);
```

And use **Stream**'s _generate_ method 
to create as many random students as I want. 
My lambda expression has no parameters,
it's a **Supplier**, so it supports no parameters, 
but that doesn't mean I can't pass arguments 
to the method I want to call. 
In this case, I'm going to call the static method, 
_getRandomStudent_ on the **Student** class, 
and pass it my _jmc_, and _pymc_ variables.
These have to be effectively final, which they are. 
I'll limit this to 10 random students at the moment. 
And I'll print each student to see how each student looks. 
Before I run it, I'll comment all the code above `Stream.generate`, 
except for setting up the two courses. 
Running this:

```html  
Student{studentId=1, countryCode='CN', yearEnrolled=2017, ageEnrolled=20, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Mar 2021 Lecture 33 [33], PYMC=PYMC: Ara 2018 Lecture 7 [60]}}
Student{studentId=2, countryCode='CN', yearEnrolled=2017, ageEnrolled=52, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Ağu 2018 Lecture 2 [64], PYMC=PYMC: Mar 2019 Lecture 20 [57]}}
Student{studentId=3, countryCode='AU', yearEnrolled=2018, ageEnrolled=22, gender='U', programmingExperience=true, engagementMap={JMC=JMC: Tem 2019 Lecture 16 [53], PYMC=PYMC: Haz 2021 Lecture 32 [30]}}
Student{studentId=4, countryCode='GB', yearEnrolled=2015, ageEnrolled=25, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Eyl 2023 Lecture 27 [3], PYMC=PYMC: Nis 2015 Lecture 17 [104]}}
Student{studentId=5, countryCode='CA', yearEnrolled=2017, ageEnrolled=72, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Tem 2023 Lecture 18 [5], PYMC=PYMC: Ara 2018 Lecture 15 [60]}}
Student{studentId=6, countryCode='AU', yearEnrolled=2015, ageEnrolled=63, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2019 Lecture 38 [52], PYMC=PYMC: Oca 2015 Lecture 11 [107]}}
Student{studentId=7, countryCode='US', yearEnrolled=2016, ageEnrolled=63, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Oca 2021 Lecture 19 [35], PYMC=PYMC: Şub 2016 Lecture 21 [94]}}
Student{studentId=8, countryCode='AU', yearEnrolled=2017, ageEnrolled=75, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Eki 2022 Lecture 37 [14], PYMC=PYMC: Eyl 2023 Lecture 5 [3]}}
Student{studentId=9, countryCode='AU', yearEnrolled=2018, ageEnrolled=23, gender='U', programmingExperience=true, engagementMap={JMC=JMC: May 2022 Lecture 18 [19], PYMC=PYMC: Nis 2021 Lecture 17 [32]}}
Student{studentId=10, countryCode='CN', yearEnrolled=2020, ageEnrolled=34, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2022 Lecture 36 [16], PYMC=PYMC: Oca 2020 Lecture 31 [47]}}
```

You can see I get 10 students back. 
Your own data will be different, 
but now we've got students with plenty
of variety in the data, 
and that's what's going to let us have some fun with this. 
That's it, we're ready to put some stream operations to work. 
The next section is a challenge, which uses this code, 
to test what you learned about 
the first set of terminal operations I covered.
</div>