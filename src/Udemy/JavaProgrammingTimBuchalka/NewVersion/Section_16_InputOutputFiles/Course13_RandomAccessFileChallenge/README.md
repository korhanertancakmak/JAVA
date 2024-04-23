# [RandomAccessFile Class Challenge]()
<div align="justify">

For this challenge, I've created a **Main** class. 
The first thing I want to do is create a record in this java file, for an employee. 
The fields will be employeeId, firstName, lastName, and salary. 

```java  
record Employee(int employeeId, String firstName, String lastName, double salary) {
};
```

The types match the types I specified on the challenge info, 
so _employeeId_ is an int, _firstName_ and _lastName_ is strings, 
and _salary_ is a double.

```java  
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
}
```

I'll create a private static variable for my map index. 
I know I want my key to be an Integer, for the _EmployeeId_,
and I want the value to be the long, the file position. 
I'll just use a hashmap. 
I'll again use a static initializer on my main class, 
to initially load up the index, when my class is first loaded. 
I'll Initialize a variable, _recordsInFile_, 
to store the number of records. 
I'll start with a _try-with-resources_ block. 
I'll open a **RandomAccessFile** for reading,
using the `employees.dat` file as the first argument. 
The second argument is the mode. 
Here I just want to read data, so I'll pass the literal _r_ to that. 
I'll catch any _IOException_, that might occur, during file operations. 
I'll print the stack trace if an exception occurs. 
Inside the _try_ block, I'll start by reading the first 4 bytes, 
which is the integer that has the record count in it. 
I'll print how many records are in the file. 
I'll use a traditional for loop, using the record count to tell me 
how many times I need to read the indexed values. 
I'll read the employee id as an int, and the file pointer position as a long, 
and put this key value pair into my map. 
And that's it. 
I can actually run this with nothing in the _main_ method, 
and I should see my record count.

```html  
25 records in file
```

The output says there are 25 records in this file, 
which is true, so this part looks good.
I'll next set up a private static method, named _readRecord_.

```java  
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
```

It returns an **Employee** and has a **RandomAccessFile** instance 
as the first parameter, and an _employeeId_ as the second. 
I'll use this method to get a record in the file, 
instantiating an **Employee** record, 
using the appropriate read methods to populate the fields. 
This time, I'm going to declare a _throws_ clause, 
and let the calling code deal with this exception. 
I'll use my _employeeId_ map index 
to get the file position for the employee id that's passed. 
I'll use _seek_ to jump to the position in the file 
for this specific employee. 
I'll read the employee ID, so 4 bytes. 
I'll read the employee's salary, a double. 
I'll read the employee's first name, which is a variable width string, 
and I do that with _readUTF_. 
I'll read the employee's last name, another variable width string. 
Next, I'll create an Employee object with the gathered data, 
passing my local variables, in the right order, to the employee constructor. 
I'll print the **Employee** details to the console. 
Finally, I'll return the **Employee** object from this method. 
Now, I can get back to the _main_ method.

```java  
public static void main(String[] args) {

    try (RandomAccessFile ra = new RandomAccessFile(employeesPath + "employees.dat", "rw")) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> ids = new ArrayList<>(indexedIds.keySet());
        Collections.sort(ids);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

I'll again start with a _try-with-resources_ block here. 
I'll open the **RandomAccessFile** for _read_ 
and _write_ operations this time, because I plan to write to it, 
updating the salary field. 
I'll use a scanner to solicit the employee id from the console. 
I'll create a list of **EmployeeIDs** from the **indexedIDs** map. 
I'll sort the list of IDs. 
I'll use this list to help prompt the user for a valid employee id. 
As usual, I need to catch any _IOException_. 
I'll throw a runtime exception with the error details.

```java  
public static void main(String[] args) {

    try (RandomAccessFile ra = new RandomAccessFile(employeesPath + "employees.dat", "rw")) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> ids = new ArrayList<>(indexedIds.keySet());
        Collections.sort(ids);

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
        
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

Next, I'll start a while loop, 
and I'll just loop until something breaks out of this while loop. 
I'll display the sorted list of **EmployeeIDs**. 
I'll prompt for an _EmployeeID_. 
If there's no input, I'll exit the loop. 
I'll read the input, _EmployeeID_, parsing it to an **int**. 
If the ID is less than 1, I'll exit the loop. 
For good measure, I'll make sure the id the user enters, 
is really in the file, by seeing if my sorted list contains it.
If it's not, I'll continue going back to the start of the while loop. 
Now that I have an employee id from the user, 
I'll read and display the Employee record, 
using my private method I created earlier. 
I'll prompt the user for a salary. 
I'm going to put this code in a _try-catch_, 
because if the user enters bad data for the salary, 
I'll just catch the exception, and ignore it. 
I don't want the program to crash here. 
I'll read and parse the new salary as a double. 
I next need to position the file pointer to the salary field, 
which you'll remember is after the id. 
I need to add 4 to the position that 
I got from the employee ID map. 
I'll write the new salary to the file. 
I'll read and display the updated Employee record. 
I'll catch and ignore any number format exception. 
Ok, we've got the code to interact with a user, so let's run this.

```html  
25 records in file
[21, 51, 90, 104, 139, 276, 287, 306, 352, 369, 503, 572, 622, 638, 640, 694, 702, 730, 742, 768, 849, 908, 910, 989, 999]
Enter an Employee Id or 0 to quit
```

I see again that there are 25 records in the file. 
Now, I've got a list of employee IDs, sorted, 
and the program is prompting me to enter a student id, 
or zero to quit. 
I'll just pick one, let's say `622`.

```html  
Employee[employeeId=622, firstName=Ralph, lastName=Black, salary=0.0]
Enter new salary, nothing if no change:
```

The program created an Employee record, 
and here you can see it printed out. 
The employee's name is _Ralph Black_, 
and right now he doesn't have any salary. 
I'll fix that and give him a salary of `55555`, 
so 55 thousand, 500 and 55 there.

```html  
Employee[employeeId=622, firstName=Ralph, lastName=Black, salary=55555.0]
[21, 51, 90, 104, 139, 276, 287, 306, 352, 369, 503, 572, 622, 638, 640, 694, 702, 730, 742, 768, 849, 908, 910, 989, 999]
Enter an Employee Id or 0 to quit
```

Again, the Employee record is retrieved and printed, 
and you can see this update worked. 
I'll just press zero to quit.
This employee's new salary is now in
what's called persistence storage, in this `.dat` file.
</div>

