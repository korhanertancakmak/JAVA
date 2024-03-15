/* Pratice40 -  Course Content Management System

Course Class:
Fields : name, code, prefix, note, Teacher
Methods : Course() , addTeacher() , printTeacher()

Teacher Class :
Fields : name, mpno, branch
Methods : Teacher()

Codes.PriorityQueueJava.Student Class :
Fields : name, stuNo, classes, course1, course2, course3, avarage,isPass
Methods : Codes.PriorityQueueJava.Student(), addBulkExamNote(), isPass(), calcAvarage(), printNote()

Homework
Enter the verbal grade section of the course into the Course class and indicate its effect on the average separately for each course.
Include verbal grades with their percentage impact on the average.

Example: If the effect of the oral grade of the Physics course on the average is 20%, the effect of the exam grade is 80%.

If the student gets 90 in the oral exam and 60 in the exam, the effect of that course on the overall average is calculated as follows:
Physics Average: (90 * 0.20) + (60 * 0.80);

*/

public class Project40 {
    
    public static void main(String[] args) {

        Course math = new Course("Math", "MAT101", "MAT");
        Course physics = new Course("Physics", "PHY101", "PHY");
        Course chemistry = new Course("Chemistry", "CHE101", "CHE");

        Teacher t1 = new Teacher("Newton", "90550000000", "MAT");
        Teacher t2 = new Teacher("Einstein", "90550000001", "PHY");
        Teacher t3 = new Teacher("Shrödinger", "90550000002", "CHE");

        math.addTeacher(t1);
        physics.addTeacher(t2);
        chemistry.addTeacher(t3);

        Student s1 = new Student("Ted", 4, "140144015", math, physics, chemistry);
        s1.addBulkExamNote(50,20,40);
        s1.isPass();

        Student s2 = new Student("Jacob", 4, "2211133", math, physics, chemistry);
        s2.addBulkExamNote(100,50,40);
        s2.isPass();

        Student s3 = new Student("Maria", 4, "221121312", math, physics, chemistry);
        s3.addBulkExamNote(50,20,40);
        s3.isPass();

    }
}

class Course {
    Teacher courseTeacher;
    String name;
    String code;
    String prefix;
    int note;

    public Course(String name, String code, String prefix) {
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.note = 0;
    }

    public void addTeacher(Teacher t) {
        if (this.prefix.equals(t.branch)) {
            this.courseTeacher = t;
            System.out.println("Teacher is assigned to their classes");
        } else {
            System.out.println(t.name + "This teacher cannot lecture this class");
        }
    }

}

class Teacher {
    String name;
    String mpno;
    String branch;

    public Teacher(String name, String mpno, String branch) {
        this.name = name;
        this.mpno = mpno;
        this.branch = branch;
    }

}

class Student {
    String name,stuNo;
    int classes;
    Course math;
    Course pyhsics;
    Course chemistry;
    double avarage;
    boolean isPass;

    Student(String name, int classes, String stuNo, Course math,Course pyhsics,Course chemistry) {
        this.name = name;
        this.classes = classes;
        this.stuNo = stuNo;
        this.math = math;
        this.pyhsics = pyhsics;
        this.chemistry = chemistry;
        calcAvarage();
        this.isPass = false;
    }

    public void addBulkExamNote(int math, int pyhsics, int chemistry) {

        if (math >= 0 && math <= 100) {
            this.math.note = math;
        }

        if (pyhsics >= 0 && pyhsics <= 100) {
            this.pyhsics.note = pyhsics;
        }

        if (chemistry >= 0 && chemistry <= 100) {
            this.chemistry.note = chemistry;
        }

    }

    public void isPass() {
        if (this.math.note == 0 || this.pyhsics.note == 0 || this.chemistry.note == 0) {
            System.out.println("Notes are not completely added.");
        } else {
            this.isPass = isCheckPass();
            printNote();
            System.out.println("Average : " + this.avarage);
            if (this.isPass) {
                System.out.println("Passed. ");
            } else {
                System.out.println("Failed.");
            }
        }
    }

    public void calcAvarage() {
        this.avarage = (double) (this.math.note + this.pyhsics.note + this.chemistry.note) / 3;
    }

    public boolean isCheckPass() {
        calcAvarage();
        return this.avarage > 55;
    }

    public void printNote(){
        System.out.println("=========================");
        System.out.println("Öğrenci : " + this.name);
        System.out.println("Matematik Notu : " + this.math.note);
        System.out.println("Fizik Notu : " + this.pyhsics.note);
        System.out.println("Kimya Notu : " + this.chemistry.note);
    }

}

