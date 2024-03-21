package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_03_OOP1.Course17_InheritanceChallenge;

/*
Course-47

                                                 Inheritance Challenge

        In the previous courses, we introduced you to inheritance, and the way Java supports inheritance with the use of
    the "extends" keyword. Now, it's time for a challenge, to solidify your understanding, of what inheritance is. For
    this challenge, I'm going to show you a class diagram, like we worked with in the previous courses. This describes a
    lot of the challenge in a diagram.

                                               Worker   =>
                                                            name: String
                                                            birthDate: String
                                                            endDate: String
                                                            ---------------------
                                                            int getAge()
                                                            double collectPay()
                                                            terminate(String endDate)
                                                       ↑
                                               Employee =>
                                                            employeeId:long
                                                            hireDate:String
                                                            ----------------
                                                       ↑
                           |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|
                SalariedEmployee =>                                          HourlyEmployee =>
                                    annualSalary: double                                        hourlyPayRate: double
                                    isRetired: boolean                                          ----------------------
                                    ----------------------                                      getDoublePay()
                                    retire()

        This diagram starts with Worker at the top of the hierarchy, this the base, or super class. It shows one subclass,
    Employee, but you could imagine other types of workers, perhaps Contractors, and Interns, for example. From Employee,
    we have 2 subclasses, these are Salaried Employee, and HourlyEmployee.

        Your challenge is to create the Worker class, the Employee class, and either the SalariedEmployee, and HourlyEmployee
    class. You don't have to do both, but if you want to, go right ahead. For each class, create the attributes and methods
    shown on this diagram. Note that Employee has no methods of its own. Create a main method that will create either a
    SalariedEmployee or HourlyEmployee, and call the methods, getAge, collectPay, and the method shown for the specific
    type of class you decide to implement. So if you implement SalariedEmployee, then execute retire(). If you implement
    HourlyEmployee, then execute getDoublePay().

        Logically, we'd start at the top of the hierarchy, which is our most generic class or base class, this is our super
    class, and in this case, that's the Worker class. After creating Worker class, since our diagram identified at least
    3 attributes that a Worker might have, name, birthdate, and endDate, which would be the employment end date.

                    private String name;
                    private String birthDate;
                    protected String endDate;

    I made these attributes private, except for endDate. The endDate will get set by a method, either on this class or a
    subclass, so making it protected, will give a subclass the flexibility to operate on it. Next, we'll create at least
    1 constructor. Let's use IntelliJ's code generation for this, let's pick just name and birthdate. It's unlike if when
    we create a new Worker, we'd know the endDate, so I'm not going to include it here.

                    public Worker(String name, String birthDate) {
                        this.name = name;
                        this.birthDate = birthDate;
                    }

    We also want to add a default constructor on Worker, which gives our subclasses a little more flexibility.

                    public Worker() {
                    }

    We could add getters and setters at this point, but we don't really need them for this challenge. Next, let's add the
    method, and generate a toString method for this class. Taking another look at the Worker class.

                    Worker   =>
                                 name: String
                                 birthDate: String
                                 endDate: String
                                 ---------------------
                                 int getAge()
                                 double collectPay()
                                 terminate(String endDate)

    We can see the methods are getAge(), collectPay(), and terminate(). Starting with getAge(). This should return an int,
    that represents the age of the person, using the currentYear and the birthDate, which is a String.

                    public int getAge() {
                        int currentYear = 2025;
                        return currentYear-Integer.parseInt(birthDate.substring(6));
                    }

    This code starts out by creating local variable for the currentYear, which we arbitrarily set to 2025. We're going to
    assume dates(birth date, hire date, and end date) will be in the format, MM/DD/YYYY. We'll use a method on String called
    "substring", to get the birth year in birthDate. For this method, substring, we pass the start position of the String
    we want to extract, and since indices start with zero, the birt year starts at position 6. If we only pass the start
    position, and not the end position, the String returned will be whatever is from the start position, to the end of the
    String. Then we use the Integer wrapper's parseInt method, to turn a String into an integer, which we've done before.
    Lastly, we return the difference between currentYear, and the birthYear, to estimate age. That's the getAge() method.

        Let's add the collectPay method, this will just return a double, representing pay, that will be received for a work
    period. We'll just return 0.0 for Worker's collectPay method.

                    public double collectPay() {
                        return 0d;
                    }

    This method is one which should be overridden by subclasses, that can figure out the right pay to return, based on the
    type of worker, etc. And now let's add the last method, terminate, which is terminating employment really. This will
    take a date, and set the endDate to that day.

                    public void terminate(String endDate) {
                        this.endDate = endDate;
                    }

    It looks like a setter, doesn't it? We could've just created a setter, but creating a terminate method, is a bit clearer
    for the business logic. And subclasses might want to override it, and add additional code, that's specific to terminating
    employment, of a certain type of Worker.

        Finally, let's generate the toString method for Worker, so we'll pick Code from the Menu, Generate, then toString,
    and all the fields:

                    @Override
                    public String toString() {
                        return "Worker{" +
                                "name='" + name + '\'' +
                                ", birthDate='" + birthDate + '\'' +
                                ", endDate='" + endDate + '\'' +
                                '}';
                    }

    Ok, so that's our super class. Now, let's create the Employee. Let's look at that class again.

                    Employee =>
                                employeeId:long
                                hireDate:String
                                ----------------

    For this class, we have specific Employee attributes, employeeId, and hireDate. For simplicity's sake, we haven't included
    any methods specific to an Employee. But you could probably think of some, like getaJobReview, or takeaVacation for
    example.

        Let's build this. After creating a new class named "Employee", we also add "extends Worker":

                    public class Employee extends Worker{
                        private long employeeId;
                        private String hireDate;
                    }

    And so we have a subclass with its own fields. Let's generate the constructor with all fields. You'll notice this time
    that we can pick which constructor, on the super class, will get called from this constructor. Let's pick the one with
    2 fields.

                    public Employee(String name, String birthDate, long employeeId, String hireDate) {
                        super(name, birthDate);
                        this.employeeId = employeeId;
                        this.hireDate = hireDate;
                    }

    You can see the call to super constructor, as the first statement in this constructor. You'll remember this has to be
    the first statement, otherwise we'll get a compiler error. This constructor has 4 fields, 2 fields that were declared
    by the Worker class, and other 2 fields declared on the Employee class. Let's add toString next. Remember to include
    the super class toString method as well, if it's not selected by default.

                    @Override
                    public String toString() {
                        return "Employee{" +
                                "employeeId=" + employeeId +
                                ", hireDate='" + hireDate + '\'' +
                                "} " + super.toString();
                    }

    And now, let's test the code we have so far. We'll open up the Description.txt file, and add some code to the main method.

                    Employee korhan = new Employee("Korhan", "02/09/1990",
                            77001, "01/01/2020");
                    System.out.println(korhan);
                    System.out.println("Age = " + korhan.getAge());
                    System.out.println("Pay = " + korhan.collectPay());

    And running this code:

                    Employee{employeeId=77001, hireDate='01/01/2020'} Worker{name='Korhan', birthDate='02/09/1990', endDate='null'}
                    Age = 35
                    Pay = 0.0

    we get Korhan's information all printed out, when we pass Korhan to System.out.println. And then we've printed out age,
    which was calculated to be 35, if the year is 2025, and the pay is 0. This is all good, but let's not pass employeeId
    on the constructor. Let's generate it. We can do this by setting a static field called employeeNumber, on Employee.
    Let's set that to 1, so our first Employee is Employee 1.

                    private static int employeeNo = 1;

    And now let's simplify our constructor, removing the employeeId argument. And we'll set employeeId here, but use that
    static employee number field we just created:

                    public Employee(String name, String birthDate, String hireDate) {
                        super(name, birthDate);
                        this.employeeId = Employee.employeeNo++;
                        this.hireDate = hireDate;
                    }

    Using a class name when using a static field, helps people reading this code, understand what's occurring. Notice here,
    that we're using the post increment operator(++). And now we've caused an error in the main method, so let's go back
    to that, and we don't have to pass that "77001" now, because our constructor is going to build an employee id for us.
    So let's remove that:

                    Employee korhan = new Employee("Korhan", "02/09/1990", "01/01/2020");

    and we run it:

                    Employee{employeeId=1, hireDate='01/01/2020'} Worker{name='Korhan', birthDate='02/09/1990', endDate='null'}
                    Age = 35
                    Pay = 0.0

    we get the same output, except Korhan's employeeId is 1. Let's just add another employee, so you can see how the static
    field is working.

                    Employee joe = new Employee("Joe", "11/11/1990",
                            "03/03/2020");
                    System.out.println(joe);

    And if we run that:

                    Employee{employeeId=1, hireDate='01/01/2020'} Worker{name='Korhan', birthDate='02/09/1990', endDate='null'}
                    Age = 35
                    Pay = 0.0
                    Employee{employeeId=2, hireDate='03/03/2020'} Worker{name='Joe', birthDate='11/11/1990', endDate='null'}

    check out the employee id for joe, is 2. That's because employee number, that static field, now has the number of the
    next employee's employee number. Remember, a static field is a place that lets you share data, among all your instances.
    When you're generating an id for a new employee, it's a place to find the next id to use.

        Now, it's time to build a more specific type of Employee, one that's Salaried, or one that's Hourly. A salaried
    employee is paid based on some percentage of his or her salary. If this person is retired, then the salary may be 100
    percent, but it is generally reduced somewhat. An hourly employee, is paid by the hours worked, and the hourly rate
    they agreed to work for. An hourly employee may also get double pay, if they work over a certain amount of hours. The
    challenge asked us to just pick 1 type of Employee, and build it out. I'm going to create the SalariedEmployee next,
    so let's focus on just that class:

                    SalariedEmployee =>
                                    annualSalary: double
                                    isRetired: boolean
                                    ----------------------
                                    retire()

    We see that we have 2 new attributes that are specific to a SalariedEmployee, these are annualSalary, and a flag, isRetired,
    a boolean. This means our retired person will still get paid, but not his or her full salary. We'll have 1 method retire,
    that will set the isRetired field to true. Let's create a new class and name it SalariedEmployee with extends keyword
    for Employee class.

                    public class SalariedEmployee extends Employee{
                    }

    And that won't compile, as we see, without a constructor declaration that calls Employee's constructor. But first, let's
    add our fields for this class and generating the constructor which only has annualSalary:

                    double annualSalary;
                    boolean isRetired;

                    public SalariedEmployee(String name, String birthDate, String hireDate,
                                            double annualSalary) {
                        super(name, birthDate, hireDate);
                        this.annualSalary = annualSalary;
                    }

    Remember that isRetired is false by default, and annualSalary is initialized to zero. Now we can create a SalariedEmployee
    with just 1 extra field than we did with Employee, the annualSalary. Now, we're ready to hire a salaried employee. In
    fact, let's just change our main method make Joe, our second employee, a salaried employee:

                    SalariedEmployee joe = new SalariedEmployee("Joe", "11/11/1990",
                                    "03/03/2020", 35000);

    Running this will give us the same output, because we haven't overridden toString, and actually, we don't want to. We
    wouldn't want our employee's salary to inadvertently get out, so we'll keep it well encapsulated, and we won't print
    that out on the to String method. We do however want to override the collectPay method on Worker, so Joe can get paid.
    In the SalariedEmployee class, let's add that method, let's say salaried employees get paid every other week, so we'll
    want to divide Joe's annual salary, by 26 weeks, to get his biweekly pay. We'll cast that to an int, to remove any decimal
    part for now, for simplicity's sake. By the end of this section, we'll cover several options available for formatting
    strings. But for now, we'll just pay in whole dollar amounts.

                    @Override
                    public double collectPay() {
                        return (int) annualSalary / 26;
                    }

    And let's make a call to that method in the main method.

                    System.out.println("Joe's Paycheck = $" + joe.collectPay());

    And running that:

                    .... (above part is the same with previous)
                    Joe's Paycheck = $1346.0

    we see that "Joe's Paycheck = $1346.0". Now let's implement a path to retirement with the method retire on SalariedEmployee.
    Let me type that out, then we'll talk about it:

                    public void retire() {
                        terminate("12/12/2025");
                        isRetired = true;
                    }

    Ok, so first we're calling a method called terminate. Do you remember which class's method that is? It's on Worker,
    the grandparent of this class, or the base class(after Object). As long as the parent class doesn't override its parent's
    methods, then these methods can be called from any descendants. Next, we set the isRetired field to true. Before we
    retire Joe, let's edit our collectPay method. Let's say the maximum pension, can only ever be 90 percent of their final
    salary:

                    @Override
                    public double collectPay() {

                        double paycheck = annualSalary / 26;
                        double adjustedPay = isRetired ? 0.9 * paycheck : paycheck;
                        return  (int) adjustedPay;
                    }

    And going back to the calling code, the main method on Description.txt:

                    joe.retire();
                    System.out.println("Joe's Pension check = $" + joe.collectPay());

    And now running that:

                    .... (same above)
                    Joe's Pension check = $1211.0

    we find out that Joe's pension pay will be $1211. For the Salaried Employee, we overrode collectPay, a method declared
    on Worker, and we implemented it using code that was unique, for a Salaried employee. We also implemented a method that
    wasn't found on either Worker or Employee, because it really only made sense for a SalariedEmployee, which was retire().

        Let's examine the Hourly Employee class briefly, now:

                    HourlyEmployee =>
                                        hourlyPayRate: double
                                        ----------------------
                                        getDoublePay()

    On this one, we have one additional field, hourlyPayRate, which is what this worker will get paid, for each hour worked.
    We'll also implement a method called getDoublePay. Let's create a new class called HourlyEmployee.

                    public class HourlyEmployee extends Employee{
                        double hourlyPayRate;
                    }

    As we did with SalariedEmployee, we'll have a compiler error, until we implement a constructor:

                    public HourlyEmployee(String name, String birthDate, String hireDate,
                                          double hourlyPayRate) {
                        super(name, birthDate, hireDate);
                        this.hourlyPayRate = hourlyPayRate;
                    }

    And that gets rid of our compiler error. In this case, we'll be passing the hourlyRate, for our HourlyEmployee. Let's
    add our 2 methods real quick. We want to implement collectPay for this class, overriding the one on Worker, just as
    we did for the SalariedWorker, but with a different calculation. In this case, let's just assume our hourly worker
    gets paid weekly, and that he or she works 40 hours in a week.

                    @Override
                    public double collectPay() {
                        return 40 * hourlyPayRate;
                    }

    And now we'll implement getDoublePay. We'll make this one simple too, for the sake of time, and just return double,
    of the normal pay:

                    public double getDoublePay() {
                        return 2 * collectPay();
                    }

    Now, this call to the method collectPay will call the collectPay on this class, and not the one method on Worker. Moving
    over to the main method, we'll create an HourlyEmployee, named Mary, and we'll make her hourly rate $15. First, we'll
    create the employee, and just print out the employee data.

                    HourlyEmployee mary = new HourlyEmployee("Mary", "05/05/1970",
                                    "03/03/2021", 15);
                    System.out.println(mary);

    Next, we'll print out both collectPay and getDoublePay.

                    System.out.println("Mary's Paycheck = $" + mary.collectPay());
                    System.out.println("Mary's Holiday Pay = $" + mary.getDoublePay());

    And running the code:

                    .... (above same)
                    Employee{employeeId=3, hireDate='03/03/2021'} Worker{name='Mary', birthDate='05/05/1970', endDate='null'}
                    Mary's Paycheck = $600.0
                    Mary's Holiday Pay = $1200.0

    We get the information for mary. You can see her employee id is 3, her weekly pay is $600. Her double time pay would
    be double that, or $1200. That was a quick implementation of the HourlyEmployee class.
*/


public class Main {
    public static void main(String[] args) {

        Employee korhan = new Employee("Korhan", "02/09/1990", "01/01/2020");
        System.out.println(korhan);
        System.out.println("Age = " + korhan.getAge());
        System.out.println("Pay = " + korhan.collectPay());

        SalariedEmployee joe = new SalariedEmployee("Joe", "11/11/1990",
                "03/03/2020", 35000);
        System.out.println(joe);
        System.out.println("Joe's Paycheck = $" + joe.collectPay());

        joe.retire();
        System.out.println("Joe's Pension check = $" + joe.collectPay());

        HourlyEmployee mary = new HourlyEmployee("Mary", "05/05/1970",
                "03/03/2021", 15);
        System.out.println(mary);
        System.out.println("Mary's Paycheck = $" + mary.collectPay());
        System.out.println("Mary's Holiday Pay = $" + mary.getDoublePay());
    }
}
