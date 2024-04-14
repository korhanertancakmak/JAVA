# [Terminal Operations Challenge]()
<div align="justify">

In the last section, I was working with the **StreamingStudents** project,
and I'll continue in that project now.
In the code in the _main_ method,
I use `Stream.generate` to generate 10 random students,
each taking both of my courses,
the python master class and the java master class.
I'll run this code:

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

So you can see those 10 random students,
and get a taste for the data you'll be querying.
My students have a country code, a year enrolled,
the age they were at enrollment,
a gender, and a programming experience flag.
In addition, you can get the current calculated age of a student
with the method getAge.
Course engagement is tracked with a **CourseEngagement** type,
for each course the student is taking,
and is in the student's engagement map field.
For this exercise, you can call the _getMonthsSinceActive_ method on the student,
to understand the last time they had any activity.
It returns zero if they've had activity in the current month,
one if it's been over a month, two if it's been longer than 2 months, and so on.
I'm going to remove that `Stream.generate` code, before I add any more code.

```java  
Course pymc= new Course("PYMC", "Python Masterclass");
Course jmc= new Course("JMC", "Java Masterclass");

var maleStudents = Stream.generate(() -> Student.getRandomStudent(jmc, pymc)).limit(1000);

maleStudents = maleStudents.filter(s -> s.getGender().equals("M"));
System.out.println("# of male students " + maleStudents.count());
```

To start with, I'll generate 1000 students, 
and then answer the question, 
how many male and female students do I have. 
I'll assign my stream, without the terminal operation, 
to a local variable. 
I'll use the same lambda as before, 
getting random students from the static method, 
`Student.getRandomStudent`. 
I'll pass both courses to every student, 
so each student is enrolled, and has activity for both courses. 
I want 1000 students for these tests. 
Next, I'll add an intermediate operation to that stream variable. 
I can continue to add intermediate operations 
before I call the terminal operation. 
I'll filter on the gender, where it equals _M_. 
The options are _M_ for male, _F_ for female, 
or _U_ for undeclared. 
I'll print out the number of male students 
by executing the terminal operation, count 
on my _maleStudents_ pipeline. 
If I run this:

```html  
# of male students 351
```

I usually get something like a third of the population, 
being male, so that's a good random distribution.
Look at the code here:

```java  
//maleStudents = maleStudents.filter(s -> s.getGender().equals("M"));
maleStudents.filter(s -> s.getGender().equals("M"));
```

And notice I'm reassigning my male students stream 
to the result of that intermediate operation. 
What happens if I don't do that? 
Let me change that and see what happens, 
because it seems like if that's a lazy operation, 
I wouldn't need to assign this result to this variable.
I'll remove _maleStudents_ equals from that statement. 
Running this code:

```html  
Exception in thread "main" java.lang.IllegalStateException : stream has already been operated upon or closed
```

I actually get an exception now. 
Turns out, when I called the intermediate operation on _maleStudents_, 
even though it's not a terminal operation, 
this does invalidate the existing stream reference. 
This means, If I'm setting up my stream pipeline, bit by bit, 
I need to always use the resulting reference to the new stream, 
returned from each intermediate operation.
IntelliJ does warn me of this, and I did want to point out this issue. 
I'll revert that change.
Next, I want to see how many female students I have,
but I already know that using this pipeline
again will give me an error.
Rather than creating a new stream of 1000 students
for each of these questions,
I really want to test the same set of students.
In an upcoming section, I'll show you how
to create a collection from a stream pipeline.
Right now though, I'll just do this with an array
before I execute my stream pipelines.
I'll insert this code on:

```java  
Student[] students = new Student[1000];
Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

var maleStudents2 = Arrays.stream(students)
        .filter(s -> s.getGender().equals("M"));

System.out.println("# of male students " + maleStudents2.count());
```

I'll instantiate an array of 1000 students. 
I'll use `arrays.setAll`, passing it my _students_ array. 
This lambda is an **IntFunction**, 
so I have to set up a parameter, and here,
_i_ stands for an integer, but, 
I don't really have to use it, 
and in this case I'll use the code I had before, 
calling `Student.getRandomStudent`, 
passing the 2 course instances. 
Now, instead of randomly generating my 1000 students each time, 
I'm doing that once, then I can ask all my questions,
about this population. 
For the male students, 
I'll remove that set of first statements all together. 
I'll start again, 
this time with a stream, using the _students_ array as my source. 
I can get a stream from an array, using `Arrays.stream`.
I'll use the same _filter_, but this time I'll just chain it. 
This compiles and runs, 
and I get basically the same results.
To query all the genders, 
I'll set up a loop to process a pipeline for each gender.

```java  
for (String gender : List.of("M", "F", "U")) {
var myStudents = Arrays.stream(students)
        .filter(s -> s.getGender().equals(gender));
System.out.println("# of " + gender + " students " + myStudents.count());
}
```

I'll loop through the possible options, 
using `List.of` to create an iterable collection. 
I'll set up a local variable in the loop, called _myStudents_, 
and do the same thing I did previously, 
creating a stream from my _students_ array. 
I'll filter this by the loop variable now. 
And I'll print the loop variable 
and the students filtered by that. 
That looks and feels more concise, and running that:

```html  
# of male students 351
# of M students 351
# of F students 308
# of U students 341
```

I get counts for all gender types. 
Totaling that up, equals my total population count.
In the next piece of the challenge,
I want to group my students by age, but my ages are ranges.

```java  
List<Predicate<Student>> list = List.of(
        (s) -> s.getAge() < 30,
        (Student s) -> s.getAge() >= 30 && s.getAge() < 60
);

long total = 0;
for (int i = 0; i < list.size(); i++) {
    var myStudents = Arrays.stream(students).filter(list.get(i));
    long cnt = myStudents.count();
    total += cnt;
    System.out.printf("# of students (%s) = %d%n", i == 0 ? " < 30" : ">= 30 & < 60", cnt);
}
System.out.println("# of students >= 60 = " + (students.length - total));
```

I'll do something similar 
to what I did with the gender groups, 
but instead of iterating over a series of string values, 
I'll iterate over several predicates. 
This means I want to set up a List 
for several lambda expression variables. 
I'll create a list for predicate variables, 
and these will operate on **Student**. 
Each will have a parameter,
which I'll call _s_, which will be a **Student** type. 
Here, I'll specify the **Student** as the type, 
just to remind you that this is another valid way 
to set up a lambda expression, 
and sometimes can help with readability. 
This time, I'm going to only use two conditions, 
because I can calculate the third,
without iterating through my set of students again. 
This may not matter with just 1000 students, 
but processing an additional stream pipeline
unnecessarily could be detrimental with large populations. 
I'll create a local variable for total, 
that will be long, since the count operation returns a **long**.
I'll use a traditional for loop here. 
I'll again get my students, but this time 
I'll pass the predicate from my list, 
using the get method with the current loop index. 
I'll assign the result of the count operation to a local long variable. 
I'll use that count to add to the total variable, 
keeping a running total of students matching my criteria. 
I'll print this out with a formatted string, 
and use a ternary operator to print the ranges. 
Finally, I'll print out my last category, which is what's remaining, 
the students greater than or equal to 60, 
and that's the array size minus the total here. 
Running this code:

```html  
# of students ( < 30) = 103
# of students (>= 30 & < 60) = 421
# of students >= 60 = 476
```

I get what looks like a skewed set of numbers, 
with my students who are over 60 always being the largest bucket, 
and the ones under 30 account for only about a 10th of my population. 
First, remember, all of these students,
at the time of enrollment, were 18 or older. 
In addition, this code isn't checking the enrollment age, 
it's checking the current age. Remember, 
some students enrolled over 5 to 8 years ago. 
Each bracket also spans a different number of years, 
so the first is really 18 through 29, or just 12 years.
The second bracket is 30 years, and the last may have ages 
that range from 75 to the late nineties, 
because of the elapsed years, so it's a much larger bucket.

```java  
var ageStream = Arrays.stream(students).
        mapToInt(Student::getAgeEnrolled);
System.out.println("Stats for Enrollment Age = " + ageStream.summaryStatistics());
```

The counts didn't seem like they were evenly distributed. 
To understand why, I can use the summary statistics terminal operations 
to give me more information. 
Let's use the enrollment age first, 
to figure out what age statistics I can get 
with the summaryStatistics operation. 
I'll set up a variable, _ageStream_, 
assigning it first `Arrays.stream` with students there. 
I'm going to map this whole stream to an **IntStream**. 
You'll remember I can only use _summaryStatistics_ 
on an **IntStream**, **DoubleStream** or **LongStream**. 
Because my enrollment age returns an **int**, 
I'll use **IntStream**. 
I need to pass that a lambda, that returns an **int**, 
and my input parameter is a student 
because I'm streaming students. 
Here, I'll use a method reference, so `Student::getAgeEnrolled`. 
Finally, I'll print out what I get back from calling that
terminal operation, summary statistics. 
If I run this:

```html  
Stats for Enrollment Age = IntSummaryStatistics{count=1000, sum=53258, min=18, average=53,258000, max=89}
```

I get statistics about my student population, 
with the minimum enrollment age of 18, 
and the maximum being 89, and the average in the low fifties.

```java  
var currentAgeStream = Arrays.stream(students).
        mapToInt(Student::getAge);
System.out.println("Stats for Current Age = " + currentAgeStream.summaryStatistics());
```

I'll copy and paste those three lines of code. 
I'll change the variable name to _currentAgeStream_. 
I can simply change my method reference to _getAge_ there. 
And I'll change the text in my output to show stats for current age, 
and change the stream from _ageStream_ to _currentAgeStream_. 
Running that:

```html  
Stats for Current Age = IntSummaryStatistics{count=1000, sum=57318, min=18, average=57,318000, max=96}
```

You can see that the student population has grown older, 
an average of 4 or 5 years older, since the earliest enrollment
started in 2015. 
This terminal operation is one of my favorites, 
and is a good way to start examining a data set. 
Ok, I still have four more questions to answer. 
The next one is, what countries are my students from?

```java  
Arrays.stream(students)
    .map(Student::getCountryCode)
    .distinct()
    .sorted()
    .forEach(s -> System.out.print(s + " "));
```

This one isn't really about a new terminal operation, 
I'll just use _forEach_ to do this, 
and I'll do it all in a single statement 
I'll again stream my array of students. 
I can use _map_ to get a stream of strings 
for just the country codes. 
I'll call distinct on the countries. 
And I'll print that out. 
Running that code:

```html  
AU CA CN GB IN UA US
```

I get the seven country codes I used 
to set up a random country for the student. 
Let's sort this. 
There are different ways to sort, 
but I'll use the sorted intermediate operation, 
after I do distinct. 
Running that, I get a sorted list of country codes. 
It might be nice to know student counts by country at this point,
and you could do that, like we did with age and gender, 
but hold off on that. 
This will be a new terminal operation I'll introduce you to, in a bit.
The next question in the challenge is, 
Are there any students that are still active 
that have been enrolled for more than 7 years?

```java  
System.out.println();
boolean longTerm = Arrays.stream(students)
        .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12));
System.out.println("longTerm students? " + longTerm);
```

This is a little more complex. 
First, you've got to decide what recent activity means. 
I'll just say a student is active 
if they've had some activity in the last 12 months, 
meaning the _getMonthsSinceActive_ is less than or equal to 12. 
I want to print a new line, 
since my previous code didn't include one. 
I'll start with getting a stream from the array. 
I'll call _anyMatch_, and pass it a multi conditional statement. 
First, I'll check that the age minus the _getAgeEnrolled_ 
is greater than or equal to 7 years, 
meaning has 7 years elapsed since this student enrolled?. 
Next, I want to use the _getMonthsSinceActive_ on **Student**, 
and see if it's less than 12. 
This is how I can check if a student had any course engagement in the past year. 
I'll print the result of that. 
Running that code:

```html  
longTerm students? true
```

I can confirm that I do have students that meet that criteria. 
You can see the _anyMatch_ operation, 
doesn't give you a lot of information. 
Sometimes, though, you really do want to ask your data 
a simple true or false question.
Let's see how many long-term students there really are,
using the count terminal operation. 
First, I'll copy the code and paste below.

```java  
long longTermCount = Arrays.stream(students)
        .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() < 12))
        .count();
System.out.println("longTerm students? " + longTermCount);
```

I'll change my variable to a long from a **boolean**, 
and call it _longTermCount_. 
I'll change _anyMatch_ to _filter_, 
and I'll insert a new line before that semicolon. 
I'll add the terminal operation, _count_ before that semicolon.
Finally, I'll change the output to print _longTermCount_, 
and not the _longTerm_ boolean value. 
Running that:

```html  
longTerm students? 53
```

The code gives me the number of long-term students, 
which could be useful info. 
Now let's change this to get just five of these students. 
First, I'll copy the code, and paste that right below.

```java  
Arrays.stream(students)
    .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&(s.getMonthsSinceActive() < 12))
    .filter(s -> !s.hasProgrammingExperience())
    .limit(5)
    .forEach(System.out::println);
```

I'll first remove the declaration and assignment, 
so I'll remove _longTermCount_ equals. 
I'll change count, to a _forEach_, 
and pass the method reference for a `System.out.println` call. 
Now, just for fun, I'll add another _filter_, 
on students who haven't had any previous programming experience. 
This wasn't part of the challenge, 
but I want you to see there's no limit to the way 
you can work with these operations. 
I can use the _hasProgrammingExperience_ method on **student**, 
negating that, meaning I don't want experienced students, 
for this selection. 
I'll limit this to five students.
I could have combined my filters, 
but having serial filters works the same, 
as one conditional statement using the and operator. 
The pipeline processing will determine the most efficient way
to execute the filters, 
and I can just list them in the most readable way.

```html  
Student{studentId=23, countryCode='CN', yearEnrolled=2016, ageEnrolled=45, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Kas 2022 Lecture 4 [13], PYMC=PYMC: Tem 2023 Lecture 36 [5]}}
Student{studentId=67, countryCode='AU', yearEnrolled=2016, ageEnrolled=23, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2023 Lecture 30 [4], PYMC=PYMC: Eyl 2017 Lecture 35 [75]}}
Student{studentId=151, countryCode='GB', yearEnrolled=2015, ageEnrolled=80, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Oca 2022 Lecture 7 [23], PYMC=PYMC: Eki 2023 Lecture 1 [2]}}
Student{studentId=188, countryCode='AU', yearEnrolled=2016, ageEnrolled=69, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Tem 2022 Lecture 35 [17], PYMC=PYMC: Eyl 2023 Lecture 1 [3]}}
Student{studentId=273, countryCode='IN', yearEnrolled=2016, ageEnrolled=45, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Tem 2016 Lecture 24 [89], PYMC=PYMC: Haz 2023 Lecture 17 [6]}}
```

I hope you can see there are a lot of ways to slice and dice the data, 
when you use streams, to get answers about your data. 
Again, this is very similar to structured query language, 
which is why we can talk about Java streams, as a data processing construct. 
</div>