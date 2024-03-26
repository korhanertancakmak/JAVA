package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course08_GenericMethodsWildcards;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course08_GenericMethodsWildcards.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        //printList(students);
        printMoreList(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        //printList(lpaStudents);
        printMoreList(lpaStudents);

        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));
    }

    public static void printMoreList(List<? extends Student> students) {

        //Student last = students.get(students.size() - 1);
        //students.set(0, last);

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

/*
    public static <T extends Student> void printList(List<T> students) {

        for (var student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
        }
        System.out.println();
    }
*/

/*                                                                                          >>> Commented via Part-6
    public static void testList(List<String> list) {

        for (var element : list) {
            System.out.println("String: " + element.toUpperCase());
        }
    }

    public static void testList(List<Integer> list) {

        for (var element : list) {
            System.out.println("Integer: " + element.floatValue());
        }
    }
*/

    public static void testList(List<?> list) {

        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }
}

