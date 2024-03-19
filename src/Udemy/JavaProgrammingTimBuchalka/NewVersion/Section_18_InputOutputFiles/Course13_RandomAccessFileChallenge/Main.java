package CourseCodes.NewSections.Section_18_InputOutputFiles.Course13_RandomAccessFileChallenge;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

//Part-1
/**                                             RandomAccessFile Challenge

        In this challenge, you'll be using an indexed file, that contains a series of employee records. This indexed file
 must be in the same directory of your Main.java. This file starts with a total count of employee records that are in the
 file, an integer value. This is followed by a series of key-value pairs. The key is an integer value, representing the
 employee id. The value is a long value, that's the file position of the employee record in the file.

        For this challenge, open a RandomAccessFile class with appropriate permissions. Load the employee index into memory.
 List your employee IDs in order. Retrieve an Employee Record from the file, using an employee id, to locate the position
 of that record in the file. Print the employee record information to the console.

        Next, update the selected Employee's salary in the file. And Finally, Retrieve the record from the file again,
 and print the information to the console, confirming that the salary was persisted. Each employee record in the file
 consists of the following information, and in this order:

        - Employee ID, an int.
        - Salary, a double.
        - Name, a string with a variable width.
        - LastName also with a variable width string.

 The difference is that now, you'll be updating a field in the record that's in the file. This is just a matter of finding
 where the salary is, in that file, seeking it in other words, and then writing the value of a new salary, as a double.
 **/
//End-Part-1

record Employee(int employeeId, String firstName, String lastName, double salary) {
};

//Part-2
/**
        For this code, I've created a new project called RandomAccessFileChallenge, with a Main class. The first thing I
 want to do is create a record in this java file, for an employee. The fields will be employeeId, firstName, lastName,
 and salary. The types match the types I specified on the challenge info, so employee Id is an int, first name and last
 name are strings, and salary is a double.
 **/
//End-Part-2

public class Main {

    private static final Map<Integer, Long> indexedIds = new HashMap<>();

    private static final String employeesPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course13_RandomAccessFileChallenge/";

    static {
        int recordsInFile = 0;
        try (RandomAccessFile ra = new RandomAccessFile(employeesPath + "employees.dat", "r");) {

            recordsInFile = ra.readInt();
            System.out.println(recordsInFile + " records in file");
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readInt(), ra.readLong());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//Part-3
/**
        I'll create a private static variable for my map index. I know I want my key to be an Integer, for the EmployeeId,
 and I want the value to be the long, the file position. I'll just use a hashmap. I'll again use a static initializer on
 my main class, to initially load up the index, when my class is first loaded. I'll Initialize a variable, recordsInFile,
 to store the number of records. I'll start with a Try with resources block. I'll open a RandomAccessFile for reading,
 using the employees.dat file as the first argument. The second argument is the mode. Here I just want to read data, so
 I'll pass the literal r to that. I'll catch any IOException, that might occur, during file operations. I'll Print the
 stack trace, if an exception occurs. Inside the try block, I'll start by reading the first 4 bytes, which is the integer
 that has the record count in it. I'll print how many records are in the file. I'll use a traditional for loop, using the
 record count to tell me how many times I need to read the indexed values. I'll read the employee id as an int, and the
 file pointer position as a long, and put this key value pair into my map. And that's it. I can actually run this, with
 nothing in the main method, and I should see my record count.

                 25 records in file

 The output says there's 25 records in this file, which is true, so this part looks good. I'll next set up a private static
 method, named readRecord.
 **/
//End-Part-3

    public static void main(String[] args) {

        try (RandomAccessFile ra = new RandomAccessFile(employeesPath + "employees.dat", "rw")) {

            Scanner scanner = new Scanner(System.in);
            List<Integer> ids = new ArrayList<>(indexedIds.keySet());
            Collections.sort(ids);

//Part-5
/**
        I'll again start with a try with resources block here. I'll open the RandomAccessFile for read and write operations
 this time, because I plan to write to it, updating the salary field. I'll use a scanner, to solicit the employee id from
 the console. I'll create a list of Employee IDs from the indexed IDs map. I'll sort the list of IDs. I'll use this list
 to help prompt the user for a valid employee id. As usual, I need to catch any I/O exception. I'll throw a runtime exception
 with the error details.
 **/
//End-Part-5

            while (true) {
                System.out.println(ids);
                System.out.println("Enter an Employee Id or 0 to quit");
                if (!scanner.hasNext()) break;
                int employeeId = Integer.parseInt(scanner.nextLine());
                if (employeeId < 1) {
                    break;
                }
                if (!ids.contains(employeeId)) {
                    continue;
                }
                Employee e = readRecord(ra, employeeId);
                System.out.println("Enter new salary, nothing if no change:");
                try {
                    double salary = Double.parseDouble(scanner.nextLine());
                    ra.seek(indexedIds.get(employeeId) + 4);
                    ra.writeDouble(salary);
                    readRecord(ra, employeeId);
                } catch (NumberFormatException ignore) {
                    // If the entered input is not a valid number, I'll ignore it.
                }
            }

//Part-6
/**
        Next, I'll start a while loop, and I'll just loop until something breaks out of this while loop. I'll display the
 sorted list of Employee IDs. I'll prompt for an Employee ID. If there's no input, I'll exit the loop. I'll read the input,
 Employee ID, parsing it to an int. If the ID is less than 1, I'll exit the loop. For good measure, I'll make sure the
 id the user enters, is really in the file, by seeing if my sorted list contains it. If it's not, I'll continue, going
 back to the start of the while loop. Now that I have an employee id from the user, I'll read and display the Employee
 record, using my private method I created earlier. I'll prompt the user for a salary. I'm going to put this code in a
 try catch, because if the user enters bad data for the salary, I'll just catch the exception, and ignore it. I don't
 want the program to crash here. I'll read and parse the new salary as a double. I next need to position the file pointer
 to the salary field, which you'll remember is after the id. I need to add 4, to the position that I got from the employee
 ID map. I'll write the new salary to the file. I'll read and display the updated Employee record. I'll catch and ignore
 any number format exception. Ok, we've got the code to interact with a user, so let's run this.

         25 records in file
         [21, 51, 90, 104, 139, 276, 287, 306, 352, 369, 503, 572, 622, 638, 640, 694, 702, 730, 742, 768, 849, 908, 910, 989, 999]
         Enter an Employee Id or 0 to quit

 I see again that there's 25 records in the file. Now, I've got a list of employee IDs, sorted, and the program is prompting
 me to enter a student id, or zero to quit. I'll just pick one, let's say 622.

         Employee[employeeId=622, firstName=Ralph, lastName=Black, salary=0.0]
         Enter new salary, nothing if no change:

 The program created an Employee record, and here you can see its printed out. The employee's name is Ralph Black, and
 right now he doesn't have any salary. I'll fix that, and give him a salary of 55555, so 55 thousand, 500 and 55 there.

         Employee[employeeId=622, firstName=Ralph, lastName=Black, salary=55555.0]
         [21, 51, 90, 104, 139, 276, 287, 306, 352, 369, 503, 572, 622, 638, 640, 694, 702, 730, 742, 768, 849, 908, 910, 989, 999]
         Enter an Employee Id or 0 to quit

 Again, the Employee record is retrieved and printed, and you can see this update worked. I'll just press zero to quit.
 This employee's new salary is now in what's called persistence storage, in this .dat file. In the next lecture, I'll be
 talking about serialization, which is another mechanism of writing your java objects to disk as binary data, so I'll see
 you in that next lecture.
 **/
//End-Part-6

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Employee readRecord(RandomAccessFile ra, int employeeId) throws IOException {
        ra.seek(indexedIds.get(employeeId));
        int id = ra.readInt();
        double salary = ra.readDouble();
        String first = ra.readUTF();
        String last = ra.readUTF();

        Employee e = new Employee(id, first, last, salary);
        System.out.println(e);
        return e;
    }

//Part-4
/**
        It returns an Employee, and has a random access file instance as the first parameter, and an employeeId as the
 second. I'll use this method to get a record in the file, instantiating an Employee record, using the appropriate read
 methods to populate the fields. This time, I'm going to declare a throws clause, and let the calling code deal with this
 exception. I'll use my employeeId map index, to get the file position for the employee id that's passed. I'll use seek,
 to jump to the position in the file for this specific employee. I'll read the employee ID, so 4 bytes. I'll read the
 employee's salary, a double. I'll read the employee's first name, which is a variable width string, and I do that with
 readUTF. I'll read the employee's last name, another variable width string. Next, I'll create an Employee object with
 the gathered data, passing my local variables, in the right order, to the employee constructor. I'll print the Employee
 details, to the console. Finally, I'll return the Employee object from this method. Now, I can get back to the main
 method.
 **/
//End-Part-4
}
