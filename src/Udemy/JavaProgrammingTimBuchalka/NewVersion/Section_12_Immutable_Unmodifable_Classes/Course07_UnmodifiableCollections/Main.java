package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_12_Immutable_Unmodifable_Classes.Course07_UnmodifiableCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

/*
        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));

        students.forEach(System.out::println);
        System.out.println("-----------------------");
*/

        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));
        bobsNotes.append("Bob was one of my first students.");

        students.forEach(System.out::println);
        System.out.println("-----------------------");


        List<Student> studentsFirstCopy = new ArrayList<>(students);

        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");


        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
        studentsFirstCopy.sort(Comparator.comparing(Student::getName));
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");


        StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonniesNotes.append("Bonnie is taking 3 of my courses");
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("-----------------------");


        List<Student> studentsSecondCopy = List.copyOf(students);

        studentsSecondCopy.forEach(System.out::println);
        System.out.println("-----------------------");


        //studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
        //studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));
        //studentsSecondCopy.sort(Comparator.comparing(Student::getName));


        List<Student> studentsThirdCopy = Collections.unmodifiableList(students);
        //studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));

        students.add(new Student("Bonnie", new StringBuilder()));

        studentsThirdCopy.forEach(System.out::println);
        System.out.println("-----------------------");

    }
}