package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_17_Streams.Course12_TerminalOperationsChallenge2;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Part-1
/*
        Create a new class called MainChallenge, with a main method that does the following:
            - Copy the two courses, jmc and pymc, from the MainCollect's main method, passing both an additional argument
              for the lecture count, so 50 for pymc, and 100 for jmc.
            - Add a third course, titled "Creating Games in Java". You don't have to pass a lecture count for this one.
            - Use Stream.generate or Stream.iterate to generate 5000 random students, and create a list of these.
            - Use your getPercentComplete method, to calculate the average percentage completed for all students for just
              the Java Masterclass, using the reduce terminal operation.

    Hint: DoubleStream, LongStream, and IntStream have both an average and a sum terminal operation. For this challenge,
    I'd like you to try to use reduce instead, to get a sum of the percentages. You can divide that number, by the student
    population, to get the overall average.

            - Use this result, multiplying it by 1.25, to collect a group of students (either as a list, or a set). These
              would be the students who've completed more than three quarters of that average percentage.
            - Sort by the longest enrolled students who are still active, because you're going to offer your new course
              to 10 of these students, for a trial run.
            - Add the new course to these ten students. Make one change to the Student's getRandomStudent method, using
              a minimum lecture of 30. This will mean more students will have a completion rate greater than 50% and will
              reduce the qualifying students considerably. Imagine thanking these students, as well as getting their opinion
              about the new course, by inviting them to try this new course for free.
*/
//End-Part-1

public class MainChallenge {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass", 50);
        Course jmc= new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

/*
        List<Student> students = Stream
                .iterate(1, s -> s <= 5000, s -> s + 1)
                .map(s -> Student.getRandomStudent(jmc, pymc))
                .toList();
*/

//Part-2
/*
        I have the code up from the last lecture, so the StreamingStudents project. First, I'll create a MainChallenge
    class, with a main method. Next, I'll open up the MainCollect class, and copy lines, which are Course objects. I'll
    paste those in my new class's main method. The challenge said to include the lecture count, in the constructor arguments,
    so I'll pass 50 to pymc, and 100 to jmc. I'll next add a new Course, and I'll insert that after the other two courses.
    This one doesn't need the lecture count. I'll create a local variable, jgames, and that's a new course, with the course
    code JGAME, and the title is Creating games in Java. Finally, I want to change the getRandomStudent method in Student
    java file. I'll change the minimum bound for getting a random lecture from 1 to 30. I'm doing this, so I can get an
    average overall percent complete for students, that is greater than 50 percent, and actually that should bump it up
    quite a bit. That's all I need for Student. Getting back to the main method, I want to generate 5000 students and put
    them in a list. I'll start with a local variable, List type Student, called students, and this time, I'll use the
    Stream.iterate method, just to show you how you might do this. I'll use the three parameter version, which kind of
    resembles a traditional for loop's parts, if that helps you remember how this is structured. First, I need an initial
    value, called the seed, which I'll make one. The second parameter of iterate, is the lambda for the condition. I want
    5000 iterations, so I'll check if this is less than or equal to 5000. The third parameter is how the seed gets incremented,
    so just one at a time. Next, I'll use map, and call my getRandomStudent here, passing it the JMC and PYMC courses.
    I'll use the toList terminal operation, to return an unmodifiable list. You might be wondering, why did I use iterate
    here? I really just wanted to show you another way, to create a whole bunch of stream elements. This creates a finite
    list of 5000 integers. If for some reason, I wanted to use that index in the supplier code, for example, this would
    have been a way to do that. There's another thing I want to caution you about. I've been saying the iterate parameters,
    look like a traditional for loop's expression parts. Let me show you what happens though, if I use a post increment
    operator, in the third argument,

                                    .iterate(1, s -> s <= 5000, s -> s + 1)
                                    .iterate(1, s -> s <= 5000, s -> s++)

    of this method. If I run this code, it never completes. That's because the post increment operator in this method,
    only does the incrementing after the value is returned as a result. In other words, this will always be 1. You can
    see this if you peek at this code.
*/
//End-Part-2

/*
        List<Student> students1 = Stream
                .iterate(1, s -> s <= 5000, s -> s++)
                .limit(10)
                .peek(System.out::println)
                .map(s -> Student.getRandomStudent(jmc, pymc))
                .toList();
*/

//Part-3
/*
        First, I'll limit this pipeline to 10 elements. I'll add peek and print out the value of the stream element, which
    is the generated integer value. Running this,

                            1
                            1
                            1
                            1
                            1
                            1
                            1
                            1
                            1
                            1

    you'll see I just get 10 ones printed out. Although these parameters, in this method, look like a traditional for loop's
    expression, you don't ever want to use a post increment operator here. A traditional for loop can use a post increment
    operator, because it's iteration variable, is in the same scope as the loop. I'll revert all these changes, so that
    I'm back to using s plus one.
*/
//End-Part-3

        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

//Part-4
/*
        Now, I can also just replace this Stream.iterate, with IntStream.rangeClosed, so I'll show you that next. First,
    I'll replace Stream, with IntStream. Then I'll change iterate to rangeClosed. I can get rid of the second and third
    parameters, the lambdas, and just make the second parameter 5000. Lastly, I need to map the Stream to an Object, so
    I have to use mapToObj, instead of just map. This code does the same thing, but is more readable I think. Ok, let's
    use this list to answer a couple of questions about these students.
*/
//End-Part-4

        double totalPercent = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);

        double avePercent = totalPercent / students.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avePercent);

//Part-5
/*
        The first thing in the challenge is, get the average percent complete for the Java master class. I'll create a
    local variable, a double, called totalPercent, and assign that, to start with a stream I'll get from the students list.
    I want to use map on each student, to get a double. I'll call getPercentComplete method, passing the JMC course code.
    The reduce method, with two parameters takes a seed first, so zero. Think of this as the total variable, you might
    create, if you were doing this in a for loop. The second parameter is the accumulator, so it's a function that's going
    to keep adding the averages to that seed value, to get a running total. Double's sum method will do that. After that
    completes, to get the overall average, I can divide the result by the number of students I have. I'll print out the
    overall average. Running this code,

                    Average Percentage Complete = 64,11%

    my overall class average will be somewhere around 2 thirds, so something close to 65 percent.
*/
//End-Part-5

/*
        double totalPercent2 = students2.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .sum();

        double avePercent2 = totalPercent2 / students2.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avePercent2);
*/

//Part-6
/*
        The challenge specified that you should use reduce here, but in practice, you'd probably just use the sum terminal
    operation, when you've got a numeric stream. I can simply replace the reduce operation with sum, with no parameters.
    Running that code,

                    Average Percentage Complete = 64,11%

    I get the same behavior. I did just want to show you both ways. Now, I'll be introducing you to the average terminal
    operation, on primitive streams. I wouldn't have to do this last bit of math manually if I used average, but I'll
    give you a demonstration of that, after I introduce you to the Optional class.
*/
//End-Part-6

        int topPercent = (int) (1.25 * avePercent);
        System.out.printf("Best Percentage Complete = %d%% %n", topPercent);

//Part-7
/*
        The next piece of the challenge, was to use this number to help filter the students. I need to use this to get a
    collection of students, that exceed this number another quarter percent. I didn't really say how to define active, I
    left this up to you. First, I'll add another local variable, which will be the minimum percentage complete, that I
    want these students to have achieved. I'll call this topPercent, I want it to be an integer. This will be one and a
    quarter times the average percent I got, and I'll cast it to an int, so the fractional part is truncated. I'll print
    that, to confirm my math is right. Running this,

                Best Percentage Complete = 80%

    I'll get a Best Percentage complete somewhere near 80 percent. This is the minimum threshold for the students I want
    to select. Now, I'll create a collection of students that exceed this threshold, and who are active.
*/
//End-Part-7

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .toList();

        System.out.println("hardWorkers = " + hardWorkers.size());

//Part-8
/*
        I'm going to say active means they've had activity this month, so getMonthsSinceActive should be zero for the JMC
    course. I'll create a list of students called hard workers, and stream my students. The first thing I'll check is the
    activity, so getMonthsSinceActive, for JMC, is equal to zero. Then, I want the percent complete, to be greater than
    the topPercent, greater than 80% for example, so this is the group who are in the top 25% of those taking the course
    as far as completion. I'm just going to use toList here to create this list. I'll print out how many students fall
    into this category. Running this code,

                hardWorkers = 48

    the result will change a little bit, but generally I'll get some where around 300 students or so. The next thing, is
    to sort these students, and pick 15, using those who've been the students the longest, as the criteria for getting
    selected.
*/
//End-Part-8

/*
        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        hardWorkers.forEach(s -> {
            s.addCourse(jgames);
            //System.out.println(s);
            System.out.print(s.getStudentId() + " ");
        });
*/


//Part-9
/*
        I'll do this using the same stream pipeline. First, I'll set up a comparator variable, inserting that above the
    pipeline. I'll call it longTermStudent. It'll use the get Year enrolled method, to sort students. Next, I'll insert
    the sorted operation into my pipeline. I'll pass it my comparator. I'll limit the students to 10. After this, I'll
    loop through these selected students, and add the course, then print the student data, to confirm all of this code
    is working. I'll remove that last println statement first. I'll call forEach on hard Workers. I'll add the course to
    each of my students. I'll print the data for each student. If I run this,

                Student{studentId=8594, countryCode='UA', yearEnrolled=2015, ageEnrolled=57, gender='M', programmingExperience=false, engagementMap={JMC=JMC: Ara 2023 Lecture 92 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: May 2019 Lecture 41 [55]}}
                Student{studentId=5977, countryCode='CA', yearEnrolled=2016, ageEnrolled=66, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Ara 2023 Lecture 88 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Kas 2023 Lecture 44 [1]}}
                Student{studentId=8115, countryCode='AU', yearEnrolled=2016, ageEnrolled=41, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Ara 2023 Lecture 87 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Ağu 2021 Lecture 33 [28]}}
                Student{studentId=5116, countryCode='US', yearEnrolled=2017, ageEnrolled=60, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ara 2023 Lecture 83 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Haz 2020 Lecture 48 [42]}}
                Student{studentId=9061, countryCode='IN', yearEnrolled=2017, ageEnrolled=76, gender='F', programmingExperience=true, engagementMap={JMC=JMC: Ara 2023 Lecture 86 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Tem 2017 Lecture 45 [77]}}
                Student{studentId=5170, countryCode='GB', yearEnrolled=2018, ageEnrolled=51, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Ara 2023 Lecture 99 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Ağu 2023 Lecture 40 [4]}}
                Student{studentId=6108, countryCode='IN', yearEnrolled=2018, ageEnrolled=36, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Ara 2023 Lecture 89 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Haz 2023 Lecture 30 [6]}}
                Student{studentId=7221, countryCode='US', yearEnrolled=2018, ageEnrolled=20, gender='F', programmingExperience=false, engagementMap={JMC=JMC: Ara 2023 Lecture 80 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Ara 2021 Lecture 44 [24]}}
                Student{studentId=5083, countryCode='US', yearEnrolled=2019, ageEnrolled=60, gender='U', programmingExperience=false, engagementMap={JMC=JMC: Ara 2023 Lecture 89 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Mar 2022 Lecture 31 [21]}}
                Student{studentId=8669, countryCode='US', yearEnrolled=2019, ageEnrolled=40, gender='M', programmingExperience=true, engagementMap={JMC=JMC: Ara 2023 Lecture 83 [0], JGAME=JGAME: Ara 2023 Enrollment [0], PYMC=PYMC: Ağu 2023 Lecture 41 [4]}}

    I get my selected students printed out. I get the oldest enrolled students, so those who enrolled in 2015, who matched
    this criteria printed first, you can see that by the enrolledYear. You can also see all my students are enrolled in
    3 classes, but that JGAME is still only at the Enrollment engagement stage. Once again, this is an unmodifiable list
    coming back from toList. This didn't prevent me from modifying the elements in the list though, as you just saw. Ok,
    so that's the end of this challenge. Maybe you used the collect terminal operation to return a modifiable list, or a
    set, using Collectors.toList or Collectors.toSet. Or maybe you used the collect operation with three parameters. I'm
    going to first change the output, to print the list of student i.d's on a single line. I'll change the println to
    print, and print s.getStudentId, plus a space.

                        System.out.println(s);
                                    to
                        System.out.print(s.getStudentId() + " ");

    I'll print a new line next. I'll copy the code, pasting that at the end of the method.
*/
//End-Part-9

/*
        students2.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList()
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
*/

//Part-10
/*
        First, I'll remove the variable declaration and assignment at the start. I'll also chain the forEach to the resulting
    collection, so I'll remove the semi-colon after the toList. This is the same code really, just in one long chain,
    and if I run it,

                9572 6878 5767 8141 5627 7722 8335 6667 6710 5397
                9572 6878 5767 8141 5627 7722 8335 6667 6710 5397

    I'll get the same result.
*/
//End-Part-10

/*
        students2.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(Collectors.toList())
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
*/

//Part-11
/*
        Now I'll comment out toList, and instead add the collect method, calling the Collectors.toList method. I get a
    warning, but I can run this

                9572 6878 5767 8141 5627 7722 8335 6667 6710 5397
                9572 6878 5767 8141 5627 7722 8335 6667 6710 5397

    and get the same results. The only difference between this and the previous version, is that the collection that's
    returned is modifiable.
*/
//End-Part-11

/*
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(Collectors.toSet())
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
*/

//Part-12
/*
        I'll again comment that out, and insert collect again, but this time I'll use the toSet method on Collectors.
    If I run this, I get the same students, just not sorted. Remember, this gives us back a HashSet, so it has no order.
    The sorted intermediate operation is still needed to pick the right set of students, the ones who've been enrolled
    the longest, that meet the other criteria. Now, let's see what happens if we get back a TreeSet.
    more terminal operations I'll cover,after I introduce you to this new class.
*/
//End-Part-12

/*
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(() -> new TreeSet<>(longTermStudent), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
*/

//Part-13
/*
        Again, I'll comment out that collect operation, and add another right below. The first parameter is a lambda. I
    have to pass a comparator if I want to use TreeSet on instances that don't implement Comparable, so I'll try using
    the comparator I used for the sort, longTermStudent. The next two parameters are the accumulator and the combiner,
    and I'll use TreeSet's add and addAll methods there. This compiles and I can run it.

                    1768 4385 244 1002 1054 1386 1500 2116 1031 4079

    Now though, there's a big difference. I only get a few of the students. Do you know why this is? The comparator I'm
    using for the sort, isn't a good one to use, for the TreeSet. The tree set code will use it to sort but also to
    determine the uniqueness of the student. In this case, our student's aren't unique by the enrollment age, so that's
    why we don't get all ten. I'll set up another comparator.
*/
//End-Part-13

/*
        System.out.println();

        Comparator<Student> uniqueSorted = longTermStudent.thenComparing(Student::getStudentId);

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(() -> new TreeSet<>(uniqueSorted), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                s.addCourse(jgames);
                System.out.print(s.getStudentId() + " ");
            });
*/

//Part-14
/*
        I can use the previous one, and chain the thenComparing method on that. I'll now use getStudentId so that these
    are unique. I'll change the TreeSet constructor to use this new variable, uniqueSorted. Running this,

                2553 4133 2182 987 15 251 4968 1489 2264 3324
                2553 4133 2182 987 15 251 4968 1489 2264 3324

    I get the same students again, as I did from the original two List code. Those are four different ways to collect stream
    elements into either a list or set collection. In fact, for this code, I didn't really need to collect the elements into a collection at all.
    I could comment out the collect terminal operation altogether, and get the same results, which I'll do. Running this,
    I get the same data. Ok, so I hope showing you all these different combinations, showed you that you have many different
    options, with these terminal operations. In the next video, I want to talk about the Optional class. This is the result type of several
    more terminal operations I'll cover,after I introduce you to this new class.
*/
//End-Part-14


    }
}
