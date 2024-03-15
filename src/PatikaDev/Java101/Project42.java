/* Pratice42 -  Salary Codes.InterfaceJava.Calculator

In Java, you should write a class called "Employee" that represents factory employees and calculates the salaries of the employees with its methods.
This class will have 4 attributes and 5 methods.

Class Fields
name            : Name and surname of the employee
salary          : Employee's salary
workHours       : Weekly working hours
hireYear        : Year of start of employment

Methods of the Class
Employee(name, salary, workHours, hireYear)     : It is a constructor method and will take 4 parameters.
tax()                                           : It will calculate the tax applied to the salary.
                                                  If the employee's salary is less than 1000 TL, no tax will be applied.
                                                  If the employee's salary is more than 1000 TL, a tax of 3% of his salary will be applied.
bonus()                                         : If the employee works more than 40 hours a week, bonus wages will be calculated as 30 TL for each extra hour worked.
raiseSalary()                                   : It will calculate the salary increase based on the employee's starting year. Take the current year as 2024.
                                                  If the employee has been working for less than 10 years, his salary will be increased by 5%.
                                                  If the employee has been working for more than 9 years and less than 20 years, his salary will be increased by 10%.
                                                  If the employee has been working for more than 19 years, there will be a 15% raise.
toString()                                      : It will print the employee information on the screen.

Example :
Name                        : Ted
Salary                      : 2000.0
Working Hours               : 45
Start Year                  : 1985
Tax                         : 60.0
Bonus                       : 150.0
Salary Increase             : 300.0
Salary with Tax and Bonuses : 2090.0
Total Salary                : 2390.0

*/

public class Project42 {
    
    public static void main(String[] args) {

        Employee mike = new Employee("Ted", 2000, 45, 1985);
        
        System.out.printf(mike.toString());
    }
}

class Employee {

    private final String name;
    private final int salary;
    private final int workHours;
    private final int hireYear;

    public Employee(String name, int salary, int workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }

    public String getName() {
        return this.name;
    }

    public int getWorkHours() {
        return this.workHours;
    }

    public double tax() {
        if (this.salary < 1000.0) {
            return 0.0;
        } else {
            return (double) ((this.salary) * 3) / 100;
        }
    }

    public double bonus() {
        double bns = 0.0;
        if (this.workHours > 40) {
            bns = (this.workHours - 40) * 30;
        }
        return bns;
    }
    public double raiseSalary() {
        double ratio;
        int years = 2024 - this.hireYear;
        if (years < 10) {
            ratio = 0.05;
        } else if (years < 20) {
            ratio = 0.1;
        } else {
            ratio = 0.15;
        }
        return ratio * this.salary;
    }

    @Override
    public String toString() {
        double SalWithTaxBonus = this.salary + this.bonus() - this.tax();
        double totalSalary = SalWithTaxBonus + raiseSalary();

        return "Name" + "                        : " + this.name + "%n" + 
        "Salary" + "                      : " + this.salary + "%n" + 
        "Working Hours" + "               : " + this.getWorkHours() + "%n" + 
        "Start Year" + "                  : " + this.hireYear + "%n" + 
        "Tax" + "                         : " + this.tax() + "%n" + 
        "Bonus" + "                       : " + this.bonus() + "%n" + 
        "Salary Increase" + "             : " + raiseSalary() + "%n" + 
        "Salary with Tax and Bonuses : " + SalWithTaxBonus + "%n" +
        "Total Salary" + "                : " + totalSalary + "%n";
    }
}

