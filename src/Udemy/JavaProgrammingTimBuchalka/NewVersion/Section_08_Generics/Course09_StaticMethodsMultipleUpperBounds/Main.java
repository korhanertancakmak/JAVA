package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.model.*;
import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_08_Generics.Course09_StaticMethodsMultipleUpperBounds.util.*;

import java.util.ArrayList;
import java.util.List;

record Employee(String name) implements  QueryItem {

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        printMoreLists(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        printMoreLists(lpaStudents);

        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));

        var queryList = new QueryList<>(lpaStudents);
        var matches = queryList.getMatches("Course", "Python");
        printMoreLists(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        //var students2021 = QueryList.<Student>getMatches(new ArrayList<>(), "YearStarted", "2021");
        printMoreLists(students2021);

        //QueryList<Employee> employeeList = new QueryList<>();
    }

    public static void printMoreLists(List<? extends Student> students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

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

