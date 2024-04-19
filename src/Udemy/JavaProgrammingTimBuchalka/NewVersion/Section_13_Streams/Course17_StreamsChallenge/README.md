# [Stream Challenge]()
<div align="justify">


I'm starting out in the **StreamingStudents** project, 
and I have the **Student** class open.
I've said the first thing I want to do,
for this challenge is, 
randomize the courses each student gets.
I'll do this by creating an extra method on **Student**.
I'll put that right above the _getRandomStudent_ method.

```java  
private static Course[] getRandomSelection(Course... courses) {

    int courseCount = random.nextInt(1, courses.length + 1);
    List<Course> courseList = new ArrayList<>(Arrays.asList(courses));
    Collections.shuffle(courseList);
    List<Course> selectedCourses = courseList.subList(0, courseCount);
    return selectedCourses.toArray(new Course[0]);
}
```

This will be private and static, return an array of courses, 
I'll call it _getRandomSelection_,
and that'll have a variable argument for courses.
I'll get a random number between one and the `courses.length + 1`.
This number is going to be used to get a sub list from an array, 
so the index is one higher than the last element I want. 
I'm going to create a modifiable list, of the _courses_.
I'll use `Collections.shuffle` 
to change up the order of the courses each time.
I'll get a sublist from that, starting at the first element,
index-0, and selecting up to the randomly generated _courseCount_.
I'll get at least one course, up to the maximum number of courses available.
I'll return an array by calling _toArray_ on the list, 
and I can pass that an empty _Course_ array.
Now, I want to call this code in the _getRandomStudent_ method.
I'll make a call to that before I instantiate a new student.

```java  
public static Student getRandomStudent(Course... courses) {

    int maxYear = LocalDate.now().getYear() + 1;
    Course[] randomCourses = getRandomSelection(courses);

    Student student = new Student(
            getRandomVal("AU", "CA", "CN", "GB", "IN", "UA", "US"),
            random.nextInt(2015, maxYear),
            random.nextInt(18, 90),
            getRandomVal("M", "F", "U"),
            random.nextBoolean(),
            //courses);
            randomCourses);

    //for (Course c : courses) {
    for (Course c : randomCourses) {
        int lecture = random.nextInt(30, c.lectureCount());
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

Next, I'll change the two places I use courses, 
to use random courses instead.
I'll test if that's working in a little bit.
Next, I'll create a new class, **MainFinalChallenge**.
For this code, I'll start by copying code from the first challenge, 
**MainChallenge**, so I have three courses.
My first two courses specify the lecture
count, so 50 for the python class,
and 100 for the java class.

```java  
public class MainFinalChallenge {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        int currentYear = LocalDate.now().getYear();
        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc, jgames))
                .filter(s -> s.getYearEnrolled() >= (currentYear - 4))
                .limit(10000)
                .toList();

        System.out.println(students
                .stream()
                .mapToInt(Student::getYearEnrolled)
                .summaryStatistics());
    }
}
```

For the third, I'll just let that have the default, which was 40.
I'll generate a list of 10,000 students,
but in this case, I want all 10,000 
to have enrolled no later than 4 years ago.
I'll start by getting the currentYear from `LocalDate.now`, 
and set a local variable to that.
Next, I'll set up a local variable for the _students_. 
I'll call _generate_, using my **Supplier** method, 
passing all three courses to this method. 
Since I want every record to be a student 
who enrolled in the past four years, I'll add a _filter_
to check that the year enrolled is greater than or equal to, 
the current year less than 4 years.
I'll limit the students after that filter to 10 thousand. 
And I'll put those on a list.
To test my code, I can use summary statistics 
to look at the students I get back.
I'm just going to pass this stream pipeline directly 
to `System.out.println`. 
I need to map to an **IntStream**, 
so I'll use the _getYearEnrolled_ method, 
to get an int from every stream value.
And I'll call _summaryStatistics_.
Running this code:

```html  
IntSummaryStatistics{count=10000, sum=20219899, min=2020, average=2021,989900, max=2024}
```

I can see that the minimum year is 4 years less than the current year, 
which is in the max value,
and that I do really have ten thousand students.
How can I check if the randomization of the number of classes is working?
I could print 10 out and examine them, and I'll do that next.
You might be tempted to use a stream for this,
but that's probably one of those cases
where a stream may not be the preferred approach.
I'll just use a sublist on my students, 
and print a few of them out.

```java  
students.subList(0, 10).forEach(System.out::println);
```

I'll call sublist on students, using 0 to 10, 
and print each student in the sub list.

```html  
Student{studentId=2, countryCode='US', yearEnrolled=2022, ageEnrolled=74, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Tem 2022 Lecture 44 [21], JGAME=JGAME: Şub 2022 Lecture 38 [26], PYMC=PYMC: Mar 2024 Lecture 44 [1]}}
Student{studentId=3, countryCode='US', yearEnrolled=2024, ageEnrolled=49, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Nis 2024 Lecture 85 [0], JGAME=JGAME: Nis 2024 Lecture 38 [0], PYMC=PYMC: Şub 2024 Lecture 42 [2]}}
Student{studentId=4, countryCode='GB', yearEnrolled=2020, ageEnrolled=55, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Nis 2024 Lecture 31 [0], JGAME=JGAME: Eki 2021 Lecture 33 [30], PYMC=PYMC: Oca 2022 Lecture 47 [27]}}
Student{studentId=5, countryCode='US', yearEnrolled=2023, ageEnrolled=81, gender='M', programmingExperience=false, engagementMap={PYMC=PYMC: May 2023 Lecture 48 [11]}}
Student{studentId=9, countryCode='GB', yearEnrolled=2020, ageEnrolled=72, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Nis 2024 Lecture 84 [0], PYMC=PYMC: Eyl 2023 Lecture 48 [7]}}
Student{studentId=10, countryCode='AU', yearEnrolled=2020, ageEnrolled=66, gender='M', programmingExperience=true, engagementMap={PYMC=PYMC: Şub 2022 Lecture 47 [26]}}
Student{studentId=12, countryCode='CN', yearEnrolled=2021, ageEnrolled=19, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ara 2021 Lecture 69 [28], JGAME=JGAME: Nis 2021 Lecture 34 [36], PYMC=PYMC: Eki 2021 Lecture 31 [30]}}
Student{studentId=13, countryCode='AU', yearEnrolled=2020, ageEnrolled=78, gender='U', programmingExperience=true, engagementMap={JMC=JMC: May 2022 Lecture 66 [23], JGAME=JGAME: Ara 2022 Lecture 39 [16], PYMC=PYMC: Nis 2022 Lecture 36 [24]}}
Student{studentId=15, countryCode='IN', yearEnrolled=2023, ageEnrolled=79, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Oca 2024 Lecture 81 [3], PYMC=PYMC: Nis 2024 Lecture 38 [0]}}
Student{studentId=20, countryCode='IN', yearEnrolled=2021, ageEnrolled=63, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Şub 2021 Lecture 55 [38], JGAME=JGAME: Kas 2021 Lecture 31 [29]}}
```

I can see a variety of courses listed.
Some students only have one class, and some have two, 
and I can also see a couple with three, so that's good.
If I rerun that, I should continue
to see different variations.
I could look at summary statistics for this too.
I'll copy the code for `System.out.println`, 
and paste that a right below.

```java  
System.out.println(students
    .stream() //Stream<Student>
    .mapToInt(s ->s.getEngagementMap().size()) //IntStream
    .summaryStatistics());
```

I'll replace that method reference with a lambda
that returns the size of the engagement map, 
which tells me how many courses each student is taking.
Running this code:

```html  
IntSummaryStatistics{count=10000, sum=20067, min=1, average=2,006700, max=3}
```

I get more statistics that the min is 1, 
meaning every student is taking at least one course.
The max is three, and the average is 2, 
so that seems like a good distribution.
Now, I want to know how many students are in each class.
How can I figure this out?
Well, I can look at all the Course Engagement records as a whole set,
and use that to figure out my counts.
Let me show you this.

```java  
var mappedActivity = students.stream() //Stream<Student>
        .flatMap(s -> s.getEngagementMap().values().stream()) //Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getCourseCode, Collectors.counting()));

mappedActivity.forEach((k, v) -> System.out.println(k + " " + v));
```

I'll set up a variable called _mappedActivity_,
and start that out with a stream. 
I'll use _flatMap_, to get all the engagement records
for each student these are the entry values of the engagement map. 
I can call stream on that collection. 
Once I have a stream of course engagement records, 
I can create a new map of all of that data, 
using `Collectors.groupingBy`.
I'll pass the course code as the key to the map.
I could have just done that, 
and got a map that had my ten thousand students,
grouped by each course, but I really just care about the counts.
I'll call `Collectors.counting`,
as the second argument to that _groupingBy_ call. 
In this code, I'm using **Collectors** 
as the qualifying name on these methods, 
rather than use a static import.
I'll print this out.
If I run this:

```html  
JMC 6713
JGAME 6627
PYMC 6727
```

It looks like there's an even distribution of students taking each class.
Let's get the counts of students taking one, two or three classes next.
I'll copy the last bunch of codes, and paste that just below.

```java  
var classCounts = students.stream()  
        .collect(Collectors.groupingBy(s -> s.getEngagementMap().size(), Collectors.counting())); // :Map<Integer, Long>

classCounts.forEach((k, v) -> System.out.println(k + " " + v));
```

I'll change the name to _classCounts_, from _mappedActivity_.
I'm going to remove the _flatMap_ operation altogether.
That's because I'm going to group by the engagement map size, 
and count the students in each.
Running this code:

```html  
1 3317
2 3299
3 3384
```

This is telling me that I have one third of my students, 
taking one class, another third taking 2, 
and the final third are taking four classes.
The next part of the challenge was to figure out 
the average percent complete for each class.
I'll copy the same bunch of codes, and paste
a copy of that at the end of this method.

```java  
import java.util.stream.Collectors;

var percentages = students.stream() // Stream<Student>
        .flatMap(s -> s.getEngagementMap().values().stream()) // Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getCourseCode, Collectors.averagingDouble())); // Map<String, Double>

percentages.forEach((k, v) ->System.out.println(k +" "+v));
```

I'll change the name _mappedActivity_ to _percentages_.
This time, instead of counting, 
I can use a method called _averagingDouble_,
and pass a method reference to that.
That method reference will be _CourseEngagement_,
_getPercentComplete_.
Running this code:

```html  
JMC 64,57604651310862
JGAME 86,21736875192395
PYMC 78,86338622990233
```

I'll get the average percentage complete for each class.
Because I changed up the lecture counts, these will differ.
You can see that JGAME, which had the default of only 40 lectures,
has the highest percent complete, and that's followed by PYMC.
JMC has the lowest percentage complete, 
because it had the most lectures, 100 lectures.
I can even get a map of statistics.

```java  
var percentages = students.stream() // Stream<Student>
        .flatMap(s -> s.getEngagementMap().values().stream()) // Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                Collectors.summarizingDouble(CourseEngagement::getPercentComplete))); // Map<String, Double>

percentages.forEach((k, v) -> System.out.println(k + " " + v));
```

I'll switch out `Collectors.averagingDouble`, 
and call _summarizingDouble_ instead.
Now, if I run this code:

```html  
JMC DoubleSummaryStatistics{count=6713, sum=433499,000000, min=30,000000, average=64,576046, max=99,000000}
JGAME DoubleSummaryStatistics{count=6627, sum=571362,500000, min=75,000000, average=86,217368, max=97,500000}
PYMC DoubleSummaryStatistics{count=6727, sum=530514,000000, min=60,000000, average=78,863386, max=98,000000}
```

I get even more data about each courses' percent complete.
Because of the way I wrote the randomization code,
no student is ever less than 30%, or is ever 100% done.
The final part of this challenge was to create a nested map, 
using the course code as the key to the top level map, 
with last activity year as the key for the nested map.
I'll copy and paste the code to the end of this method.

```java  
var yearMap = students.stream()                                                //Stream<Student>
        .flatMap(s -> s.getEngagementMap().values().stream())                  //Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                Collectors.groupingBy(CourseEngagement::getLastActivityYear,
                        Collectors.counting())));                              //Map<String,Map<...>>

yearMap.forEach((k, v) -> System.out.println(k + " " + v));
```

I'm going to start out the same way as I did with percentages.
I'll rename this variable, from _percentages_ to _yearMap_.
For the _percentages_ map, I collected a Map of **DoubleSummaryStatistics**,
but now I want to collect a **Map** of **Maps**.
To do this, I'll nest a call to a second `Collectors.groupingBy` call,
as the additional parameter.
My nested map will be keyed by or grouped 
by the _lastActivityYear_on **CourseEngagement**.
I'm just going to get counts, 
and not a list or set of students.
I'll copy the _forEach_ call, 
to print the map values, and I'll change the name
from _percentages_ to _yearMap_ there.
Ok, so I'll run this and see what I get:

```html  
JMC {2020=282, 2021=578, 2022=1055, 2023=1695, 2024=3103}
JGAME {2020=266, 2021=602, 2022=1027, 2023=1681, 2024=3051}
PYMC {2020=269, 2021=616, 2022=1007, 2023=1677, 2024=3158}
```

And there you go I can see, for every course, 
for students who enrolled in the past 4 years, 
what kind of activity I have.
This year's count is much higher, 
because it's cumulative of some of the previous years, 
and it will include all the students enrolled this year.
Remember, the random code only creates activity after the enrollment date.
Maybe knowing how many enrolled each year would be kind of useful, 
as you're studying these counts.
I'll quickly add that, in case some of you might be wondering 
if these numbers seem off.
Instead of assigning this to a variable, 
I'll just use the stream to print data directly.

```java  
students.stream()                                                              //Stream<Student> 
        .flatMap(s -> s.getEngagementMap().values().stream())                  //Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                 Collectors.counting())))                                      //Map<Integer,Long>
        .forEach((k, v) -> System.out.println(k + ": " + v));
```

Again, I'll only want to look at course engagement records, 
so _flatmap_ let me do that.
I'll group by the enrollment year, 
which is on the **CourseEngagement** record, as well as _student_.
On **student**, it's the earliest enrollment of any course, 
on **CourseEngagement**, it's the actual year enrolled for this course.
I'll count that group. 
I'll tack on a _forEach_ to the resulting map.
Running this:

```html  
2020: 4030
2021: 3983
2022: 4131
2023: 3898
2024: 4025
```

I can see the counts by enrollment year,
which looks like an even distribution.

```java  
students.stream()                                                              //Stream<Student> 
        .flatMap(s -> s.getEngagementMap().values().stream())                  //Stream<CourseEngagement>
        .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                 Collectors.groupingBy(CourseEngagement::getCourseCode,
                 Collectors.counting())))                                      //Map<Integer,Map<...>>
        .forEach((k, v) -> System.out.println(k + ": " + v));
```

I can quickly add another level to this,
by inserting another grouping by call, after the first.
I'll group by course code within the enrollment year.
The only other thing I need is an extra parentheses 
after the final one.
Running this:

```html  
2020: {JMC=1372, JGAME=1333, PYMC=1325}
2021: {JMC=1301, JGAME=1319, PYMC=1363}
2022: {JMC=1391, JGAME=1387, PYMC=1353}
2023: {JMC=1326, JGAME=1263, PYMC=1309}
2024: {JMC=1323, JGAME=1325, PYMC=1377}
```

I'll get enrollments by course in each year.
This last bit wasn't part of the challenge, 
but I wanted you to see that you can use streams,
to very quickly see your data in a variety of ways.
</div>