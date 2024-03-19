package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Streams.Course09_TerminalOperationsChallenge1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

//Part-1
/*
        In this challenge, you'll use the terminal operations shown on the table below, in combination with some of the
    intermediate operations you've learned about.

                Return Type             Terminal Operations

                   long                      count()
              DoubleStatistics          summaryStatistics()
                  boolean               allMatch(Predicate<? super T> predicate)
                  boolean               anyMatch(Predicate<? super T> predicate)
                  boolean               noneMatch(Predicate<? super T> predicate)

    You'll be using these, to answer some questions about a series of students. It contains a Student class with demographic
    data. This class has a factory method, to generate a new instance of a Student, using random data. This factory method
    will also generate some random activity, for each course passed as an argument, to the Student constructor.

        Your challenge is to Create a source for a stream of Students.

    - Use the static method on Student as the Supplier.
    - Use a large enough number to get a variety of Student data, no less than 1000 students for example.
    - Use a combination of the intermediate and terminal operations we've covered so far, to answer the following questions.
        > How many male and female students are in the group?
        > How many students fall into the three age ranges, less than age 30, between 30 and 60, and lastly, over 60 years
          old. Use summaryStatistics on student's age, to get a better idea of how old the student population is.
        > What countries are the students from? Print a distinct list of the country codes.
        > Are there students that are still active, that have been enrolled for more than 7 years? Use one of the match
          terminal operations to answer this question.

        Next, select 5 of the students above, and print their information out. Imagine that maybe you'd like to send them
    a special coupon, for being long time learners, and clients of yours. Don't forget you can use peek, to help you
    understand your stream processing, if you don't feel confident about your results.

        In the last lecture, I was working with the StreamingStudents project, and I'll continue in that project now. In
    the code in the main method, I use Stream.generate to generate 10 random students, each taking both of my courses,
    the python master class and the java master class. I'll run this code,


    so you can see those 10 random students, and get a taste for the data you'll be querying. My students have a country
    code, a year enrolled, the age they were at enrollment, a gender, and a programming experience flag. In addition, you
    can get the current calculated age of a student, with the method getAge. Course engagement is tracked with a CourseEngagement
    type, for each course the student is taking, and is in the student's engagement map field. For this exercise, you can
    just call the getMonthsSinceActive method on the student, to understand the last time they had any activity. It returns
    zero if they've had activity in the current month, one if it's been over a month, two if it's been longer than 2 months,
    and so on. I'm going to remove that Stream.generate code, before I add any more code.
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        var maleStudents = Stream.generate(() -> Student.getRandomStudent(jmc, pymc)).limit(1000);

        maleStudents = maleStudents.filter(s -> s.getGender().equals("M"));
        //maleStudents.filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students " + maleStudents.count());

//Part-2
/*
        To start with, I'll generate 1000 students, and then answer the question, how many male and female students do I
    have. I'll assign my stream, without the terminal operation, to a local variable. I'll use the same lambda as before,
    getting random students from the static method, Student.getRandomStudent. I'll pass both courses to every student,
    so each student is enrolled, and has activity for both courses. I want 1000 students for these tests. Next, I'll add
    an intermediate operation to that stream variable. I can continue to add intermediate operations, before I call the
    terminal operation. I'll filter on the gender, where it equals M. The options are M for male, f for female, or U for
    undeclared. I'll print out the number of male students, by executing the terminal operation, count, on my maleStudents
    pipeline. If I run this,

                    # of male students 351

    I usually get something like a third of the population, being male, so that's a good random distribution. Look at the
    code here,

                    maleStudents = maleStudents.filter(s -> s.getGender().equals("M"));
                                                    to
                    maleStudents.filter(s -> s.getGender().equals("M"));

    and notice I'm reassigning my male students stream to the result of that intermediate operation. What happens if I don't
    do that? Let me change that and see what happens, because it seems like if that's a lazy operation, I wouldn't need
    to assign this result to this variable. I'll remove maleStudents equals from that statement. Running this code,

                Exception in thread "main" java.lang.IllegalStateException : stream has already been operated upon or closed

    I actually get an exception now. Turns out, when I called the intermediate operation on maleStudents, even though it's
    not a terminal operation, this does invalidate the existing stream reference. This means, If I'm setting up my stream
    pipeline, bit by bit, I need to always use the resulting reference to the new stream, returned from each intermediate
    operation. IntelliJ does warn me of this, and I did want to point out this issue. I'll revert that change.
*/
//End-Part-2

        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        var maleStudents2 = Arrays.stream(students)
                .filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students " + maleStudents2.count());

//Part-3
/*
        Next, I want to see how many female students I have, but I already know that using this pipeline again will give
    me an error. Rather than creating a new stream of 1000 students for each of these questions, I really want to test
    the same set of students. In an upcoming lecture, I'll show you how to create a collection from a stream pipeline.
    Right now though, I'll just do this with an array, before I execute my stream pipelines. I'll insert this code on

                Student[] students = new Student[1000];
                Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

    I'll instantiate an array of 1000 students. I'll use arrays.setAll, passing it my students array. This lambda is an
    Int Function, so I have to set up a parameter, and here, I stands for an integer, but, I don't really have to use it,
    and in this case I'll use the code I had before, calling Student.getRandomStudent, passing the 2 course instances.
    Now, instead of randomly generating my 1000 students each time, I'm doing that once, then I can ask all my questions,
    about this population. For the male students, I'll remove that set of first statements all together. I'll start again,
    this time with a stream, using the students array as my source. I can get a stream from an array, using Arrays.stream.
    I'll use the same filter, but this time I'll just chain it. This compiles and runs, and I get basically the same results.
    To query all the genders, I'll set up a loop to process a pipeline for each gender.
*/
//End-Part-3

        for (String gender : List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));
            System.out.println("# of " + gender + " students " + myStudents.count());
        }

//Part-4
/*
        I'll loop through the possible options, using List.of to create an iterable collection. I'll set up a local variable
    in the loop, called myStudents, and do the same thing I did previously, creating a stream from my students array. I'll
    filter this by the loop variable now. And I'll print the loop variable and the students filtered by that. That looks
    and feels more concise, and running that,

                    # of male students 351
                    # of M students 351
                    # of F students 308
                    # of U students 341

    I get counts for all gender types. Totalling that up, equals my total population count. In the next piece of the challenge,
    I want to group my students by age, but my ages are ranges.
*/
//End-Part-4

        List<Predicate<Student>> list = List.of(
                (s) -> s.getAge() < 30,
                (Student s) -> s.getAge() >= 30 && s.getAge() < 60
        );

        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            var myStudents = Arrays.stream(students).filter(list.get(i));
            long cnt = myStudents.count();
            total += cnt;
            System.out.printf("# of students (%s) = %d%n",
                    i == 0 ? " < 30" : ">= 30 & < 60", cnt);
        }
        System.out.println("# of students >= 60 = " + (students.length - total));

//Part-5
/*
        I'll do something similar to what I did with the gender groups, but instead of iterating over a series of string
    values, I'll iterate over several predicates. This means, I want to set up a List, for several lambda expression
    variables. I'll create a list for predicate variables, and these will operate on Student. Each will have a parameter,
    which I'll call s, which will be a Student type. Here, I'll specify the Student as the type, just to remind you, that
    this is another valid way to set up a lambda expression, and sometimes can help with readability. This time, I'm
    going to only use two conditions, because I can calculate the third, without iterating through my set of students
    again. This may not matter with just 1000 students, but processing an additional stream pipeline unnecessarily could
    be detrimental with large populations. I'll create a local variable for total, that will be long, since the count
    operation returns a long. I'll use a traditional for loop here. I'll again get my students, but this time I'll pass
    the predicate from my list, using the get method with the current loop index. I'll assign the result of the count
    operation, to a local long variable. I'll use that count, to add to the total variable, keeping a running total of
    students matching my criteria. I'll print this out with a formatted string, and use a ternary operator to print the
    ranges. Finally, I'll print out my last category, which is what's remaining, the students greater than or equal to
    60, and that's the array size minus the total here. Running this code,

                    # of students ( < 30) = 103
                    # of students (>= 30 & < 60) = 421
                    # of students >= 60 = 476

    I get what looks like a skewed set of numbers, with my students who are over 60 always being the largest bucket, and
    the ones under 30 account for only about a 10th of my population. First, don't forget, all of these students, at the
    time of enrollment, were 18 or older. In addition, this code isn't checking the enrollment age, it's checking the
    current age. Remember, some students enrolled over 5 to 8 years ago. Each bracket also spans a different number of
    years, so the first is really 18 through 29, or just 12 years. The second bracket is 30 years, and the last may have
    ages that range from 75 to the late nineties, because of the elapsed years, so it's a much larger bucket.
*/
//End-Part-5

        var ageStream = Arrays.stream(students).
                mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for Enrollment Age = " + ageStream.summaryStatistics());

//Part-6
/*
        The counts didn't seem like they were evenly distributed. To understand why, I can use the summary statistics terminal
    operations, to give me more information. Let's use the enrollment age first, to figure out what age statistics I can
    get, with the summaryStatistics operation. I'll set up a variable, ageStream, assigning it first Arrays.stream with
    students there. I'm going to map this whole stream to an IntStream. You'll remember I can only use summaryStatistics
    on an IntStream, DoubleStream or LongStream. Because my enrollment age returns an int, I'll use IntStream. I need to
    pass that a lambda, that returns an int, and my input parameter is a student because I'm streaming students. Here,
    I'll use a method reference, so Student::getAgeEnrolled. Finally, I'll print out what I get back from calling that
    terminal operation, summary statistics. If I run this,

                Stats for Enrollment Age = IntSummaryStatistics{count=1000, sum=53258, min=18, average=53,258000, max=89}

    I get statistics about my student population, with the minimum enrollment age of 18, and the maximum being 89, and
    the average in the low fifties.
*/
//End-Part-6

        var currentAgeStream = Arrays.stream(students).
                mapToInt(Student::getAge);
        System.out.println("Stats for Current Age = " +
                currentAgeStream.summaryStatistics());

//Part-7
/*
        I'll copy and paste those three lines of code. I'll change the variable name to currentAgeStream. I can simply
    change my method reference to getAge there. And I'll change the text in my output to show stats for current age, and
    change the stream from ageStream to currentAgeStream. Running that

                Stats for Current Age = IntSummaryStatistics{count=1000, sum=57318, min=18, average=57,318000, max=96}

    you can see that the student population has grown older, an average of 4 or 5 years older, since the earliest enrollment
    started in 2015. This terminal operation is one of my favorites, and is a good way to start examining a data set. Ok,
    I still have four more questions to answer. The next one is, What countries are my students from?
*/
//End-Part-7

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

//Part-8
/*
        This one isn't really about a new terminal operation, I'll just use forEach to do this, and I'll do it all in a
    single statement I'll again stream my array of students. I can use map to get a stream of strings for just the country
    codes. I'll call distinct on the countries. And I'll print that out. Running that code,

                AU CA CN GB IN UA US

    I get the seven country codes I used, to set up a random country, for the student. Let's sort this. There are different
    ways to sort, but I'll use the sorted intermediate operation, after I do distinct. Running that, I get a sorted list
    of country codes. It might be nice to know student counts by country at this point, and you could do that, like we
    did with age and gender, but hold off on that. This will be a new terminal operation I'll introduce you to, in a bit.
    The next question in the challenge is, Are there any students that are still active, that's been enrolled for more
    than 7 years?
*/
//End-Part-8

        System.out.println();
        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12));
        System.out.println("longTerm students? " + longTerm);

//Part-9
/*
        This is a little more complex. First, you've got to decide what recent activity means. I'll just say a student
    is active if they've had some activity in the last 12 months, meaning the getMonthsSinceActive is less than or equal
    to 12. I want to print a new line, since my previous code didn't include one. I'll start with getting a stream from
    the array. I'll call any match, and pass it a multi conditional statement. First, I'll check that the age minus the
    getAgeEnrolled is greater than or equal to 7 years, meaning has 7 years elapsed since this student enrolled?. Next,
    I want to use the getMonthsSinceActive on Student, and see if it's less than 12. This is how I can check if a student
    had any course engagement in the past year. I'll print the result of that. Running that code,

                longTerm students? true

    I can confirm that I do have students that meet that criteria. You can see the any match operation, doesn't give you
    a whole lot of information. Sometimes though, you really do just want to ask your data, a simple true or false question.
    Let's see how many long term students there really are, using the count terminal operation. First, I'll copy the code
    and paste below.
*/
//End-Part-9

        long longTermCount = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .count();
        System.out.println("longTerm students? " + longTermCount);

//Part-10
/*
        I'll change my variable to a long from a boolean, and call it longTermCount. I'll change anyMatch to filter, and
    I'll insert a new line before that semi-colon. I'll add the terminal operation, dot count before that semi-colon.
    Finally, I'll change the output to print longTermCount, and not the longTerm boolean value. Running that

                longTerm students? 53

    code gives me the number of long term students, which could be useful info. Now let's change this to get just 5 of
    these students. First, I'll copy the code, and paste that right below.
*/
//End-Part-10

        Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .forEach(System.out::println);

//Part-11
/*
        I'll first remove the declaration and assignment, so I'll remove longTermCount equals. I'll change count, to a
    forEach, and pass the method reference for a System.out.println call. Now, just for fun, I'll add another filter, on
    students who haven't had any previous programming experience. This wasn't part of the challenge, but I want you to
    see there's no limit to the way you can work with these operations. I can use the hasProgrammingExperience method on
    student, negating that, meaning I don't want experienced students, for this selection. I'll limit this to 5 students.
    I could have combined my filters, but having serial filters works the same, as one conditional statement using the
    and operator. The pipeline processing will determine the most efficient way to execute the filters, and I can just
    list them in the most readable way.

            Student{studentId=23, countryCode='CN', yearEnrolled=2016, ageEnrolled=45, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Kas 2022 Lecture 4 [13], PYMC=PYMC: Tem 2023 Lecture 36 [5]}}
            Student{studentId=67, countryCode='AU', yearEnrolled=2016, ageEnrolled=23, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Ağu 2023 Lecture 30 [4], PYMC=PYMC: Eyl 2017 Lecture 35 [75]}}
            Student{studentId=151, countryCode='GB', yearEnrolled=2015, ageEnrolled=80, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Oca 2022 Lecture 7 [23], PYMC=PYMC: Eki 2023 Lecture 1 [2]}}
            Student{studentId=188, countryCode='AU', yearEnrolled=2016, ageEnrolled=69, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Tem 2022 Lecture 35 [17], PYMC=PYMC: Eyl 2023 Lecture 1 [3]}}
            Student{studentId=273, countryCode='IN', yearEnrolled=2016, ageEnrolled=45, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Tem 2016 Lecture 24 [89], PYMC=PYMC: Haz 2023 Lecture 17 [6]}}

    I hope you can see there's a lot of ways to slice and dice the data, when you use streams, to get answers about your
    data. Again, this is very similar to structured query language, which is why we can talk about Java streams, as a data
    processing construct. Ok, so I hope you had some fun with this. In the next lecture, I'll be talking about how to get
    data out of the stream, and into other types. We had a little taste of this, with operations that returned a numeric
    value or a boolean, but we're not limited to just returning simple types. There's a lot more we can do.
*/
//End-Part-11
    }
}
