# Local and Anonymous Class Challenge

<div align="justify">

At the moment it has no fields, but I need to add 3, 
so let me add those. 
I've put this in a different package, and I want to remind you, 
the challenge was not to touch or amend this record, 
but to use a local class, to manipulate data, and add functionality. 
Now, I want to go to the main class and set up some employees.

```java  
public record Employee(String first, String last, String hireDate) {
}
```

```java  
Employee e1 = new Employee("Minnie", "Mouse", "01/02/2015");
Employee e2 = new Employee("Mickie", "Mouse", "05/08/2000");
Employee e3 = new Employee("Daffy", "Duck", "11/02/2011");
Employee e4 = new Employee("Daisy", "Duck", "05/03/2013");
Employee e5 = new Employee("Goofy", "Dog", "23/07/2020");

List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));
```

After creating the list of _Employee_, 
I'll set up my method that will have both the local and anonymous classes.
Ok, I'm passing a list of Employee, and I also have one other parameter, 
which is sortField. 
If this field indicates to sort by name, I'll sort by name, 
otherwise by years worked. 
Next, I want to get the current year, using the simple date function 
I gave you as a hint.

```java  
public static void printOrderedList(List<Employee> eList, String sortField) {

    int currentYear = LocalDate.now().getYear();

    class MyEmployee {

        final Employee containedEmployee;
        final int yearsWorked;
        final String fullName;

        public MyEmployee(Employee containedEmployee) {
            this.containedEmployee = containedEmployee;
            yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);
            fullName = String.join(" ", containedEmployee.first(), containedEmployee.last());
        }
        
        @Override
        public String toString() {
            return "%s has been an employee for %d years".formatted(fullName, yearsWorked);
        }
    }

    List<MyEmployee> list = new ArrayList<>();
    for (Employee employee : eList) {
        list.add(new MyEmployee(employee));
    }

    var comparator = new Comparator<MyEmployee>() {

        @Override
        public int compare(MyEmployee o1, MyEmployee o2) {

            if (sortField.equals("name")) {
                return o1.fullName.compareTo(o2.fullName);
            }
            return o1.yearsWorked - o2.yearsWorked;
        }
    };

    list.sort(comparator);

    for (MyEmployee myEmployee : list) {
        System.out.println(myEmployee);
    }
}
```

Now, I'll set up my local class. In this code, I'm calling the class myEmployee. 
I'm not extending the Employee class because it's a record, 
and we can't use a record in the _extends_ class, 
but this isn't a problem for what we want to do here. 
I've got a contained Employee field, which will just reference 
the original employee instance. 
I'll have two other fields, _yearsWorked_, an integer, and _fullName_, a string. 
I'll add a constructor.
In addition to assigning contained employee to my containedEmployee field, 
I'll derive the years worked next.
In this code, I'm splitting my string by the forward slash "/", 
and then the third split should be the year. 
I run that through _Integer.parseInt_ to get the year as an integer, 
and subtract it from the current year. 
And now for the full name, I'll use _String.join_, 
because I want to keep reminding you of the many options on String. 
Lastly, I want toString method for my local class.
I'll generate this by generating an override. 
I'll replace the call to _super.toString_ in that method. 
Here, I'll just print the full name, and the years worked by the employee. 
And that's the local class, so it's not too complicated.

And I want a list of these now, so I'll loop through the list of Employees, 
and create a list of **MyEmployees**. 
Ok, now I have a list of my employees, but I want to sort them in two different ways, 
based on the method argument, **sortField**. 
Let me set that up.

```java  
var comparator = new Comparator<MyEmployee>() {
    @Override
    public int compare(MyEmployee o1, MyEmployee o2) {
        if (sortField.equals("name")) {
            return o1.fullName.compareTo(o2.fullName);
        }
        return o1.yearsWorked - o2.yearsWorked;
    }
};
```

Unlike the local class, which we used to create many instances of, 
we really only need one instance of this class,
for a very finite purpose, to change the way these employees are sorted. 
This doesn't compile yet because it implements the Comparator interface, 
so I have to implement the compare method. 
Let me generate that code. 
Now, what's fun about a class generated in a method is, we can use method arguments, 
or local variables, to determine the behavior that will happen in the anonymous class. 
I'm going to use my sortField method argument to determine how to compare the employees.

```java  
list.sort(comparator);

for (MyEmployee myEmployee : list) {
    System.out.println(myEmployee);
}
```

Because sortField never changes value, it's effectively final, 
and I can use it in my anonymous class code. 
If **sortField** is **name**, the sort will use the full name field on the local class to sort by. 
Otherwise, it will sort by the years worked on the local class. 
Here, I just want to give you a little something extra to think about how local variables 
and method arguments can influence what is happening in these temporary classes. 
Now that I have a comparator, I can sort my list, and I'll follow that up 
with an enhanced for loop to print each employee instance on a separate line. 
Now, I just need to invoke this method from the main method.

```java  
public static void main(String[] args) {

    Employee e1 = new Employee("Minnie", "Mouse", "01/02/2015");
    Employee e2 = new Employee("Mickie", "Mouse", "05/08/2000");
    Employee e3 = new Employee("Daffy", "Duck", "11/02/2011");
    Employee e4 = new Employee("Daisy", "Duck", "05/03/2013");
    Employee e5 = new Employee("Goofy", "Dog", "23/07/2020");

    List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

    printOrderedList(list, "name");
}
```

Running that code:

```java  
Daffy Duck has been an employee for 12 years
Daisy Duck has been an employee for 10 years
Goofy Dog has been an employee for 3 years
Mickie Mouse has been an employee for 23 years
Minnie Mouse has been an employee for 8 years
```
                
I get my employees printed out by their full name. 
Now I'll try this with a year sort, so in the main method, 
I'll add a separator line, and then invoke that method again, with year, 
but I could really put anything there, as long as it wasn't name.

```java  
public static void main(String[] args) {

    Employee e1 = new Employee("Minnie", "Mouse", "01/02/2015");
    Employee e2 = new Employee("Mickie", "Mouse", "05/08/2000");
    Employee e3 = new Employee("Daffy", "Duck", "11/02/2011");
    Employee e4 = new Employee("Daisy", "Duck", "05/03/2013");
    Employee e5 = new Employee("Goofy", "Dog", "23/07/2020");

    List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

    printOrderedList(list, "name");
    System.out.println();
    printOrderedList(list, "year");
}
```

Running that code:

```java  
Daffy Duck has been an employee for 12 years
Daisy Duck has been an employee for 10 years
Goofy Dog has been an employee for 3 years
Mickie Mouse has been an employee for 23 years
Minnie Mouse has been an employee for 8 years

Goofy Dog has been an employee for 3 years
Minnie Mouse has been an employee for 8 years
Daisy Duck has been an employee for 10 years
Daffy Duck has been an employee for 12 years
Mickie Mouse has been an employee for 23 years
```
                
I get the list printed again, but sorted by the year. 
Ok, so this challenge allowed you to create both a local class 
and an anonymous class.
That's the end of this section.
</div>
