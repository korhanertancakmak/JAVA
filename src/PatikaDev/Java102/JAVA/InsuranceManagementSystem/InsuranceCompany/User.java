package PatikaDev.Java102.JAVA.InsuranceManagementSystem.InsuranceCompany;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 The "Codes.InsuranceManagementSystem.User" class represents the customer's contact information.
 In the "Codes.InsuranceManagementSystem.User" class, following information of the customer is available:

 name (String),
 surname (String),
 e-mail (String),
 password (String),
 profession (String),
 age (int),
 address list (ArrayList < Codes.InsuranceManagementSystem.Address >)
 Last login LocalDate to the system (LocalDate)
**/
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String profession;
    private int age;
    protected ArrayList<Address> addressList;
    private LocalDate lastLoginLocalDate;

    Scanner kb = new Scanner(System.in);

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String email, String password,
                String profession, int age, ArrayList<Address> addressList) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.profession = profession;
        this.age = age;
        this.addressList = addressList;
    }

    public void settings() {
        while (true) {
            System.out.println("Settings : ");
            System.out.println("-".repeat(25));
            System.out.println("00 - Go back to Main Menu");
            System.out.println("01 - Change user name");
            System.out.println("02 - Change user surname");
            System.out.println("03 - Change user email");
            System.out.println("04 - Change user password");
            System.out.println("05 - Change user profession");
            System.out.println("06 - Change user age");
            System.out.println("07 - Add an address");
            System.out.println("08 - Remove an address");
            System.out.println("-".repeat(25));
            System.out.print("Your choice : ");
            byte chosen1 = kb.nextByte();
            switch (chosen1) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("Enter new name : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setName(kb1.nextLine());
                    System.out.println("User's name is changed successfully!");
                }
                case 2 -> {
                    System.out.println("Enter new surname : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setSurname(kb1.nextLine());
                    System.out.println("User's surname is changed successfully!");
                }
                case 3 -> {
                    System.out.println("Enter new email : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setEmail(kb1.nextLine());
                    System.out.println("User's email is changed successfully!");
                }
                case 4 -> {
                    System.out.println("Enter new password : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setPassword(kb1.nextLine());
                    System.out.println("User's password is changed successfully!");
                }
                case 5 -> {
                    System.out.println("Enter new profession : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setProfession(kb1.nextLine());
                    System.out.println("User's profession is changed successfully!");
                }
                case 6 -> {
                    System.out.println("Enter new age : ");
                    Scanner kb1 = new Scanner(System.in);
                    this.setAge(kb1.nextInt());
                    System.out.println("User's age is added successfully!");
                }
                case 7 -> {
                    System.out.println("-".repeat(25));
                    System.out.println("Choose an address type : ");
                    System.out.println("0 - Go back");
                    System.out.println("1 - Home address");
                    System.out.println("2 - Business address");
                    System.out.println("-".repeat(25));
                    System.out.print("Your choice : ");
                    byte chosen2 = kb.nextByte();
                    switch (chosen2) {
                        case 1 -> {
                            System.out.println("Enter new address in the format (Street, City, State, " +
                                    "PostalCode) : ");
                            Scanner kb2 = new Scanner(System.in);
                            HomeAddress address = new HomeAddress(kb2.nextLine(), kb2.nextLine(),
                                    kb2.nextLine(), kb2.nextLine());
                            this.addressList.addFirst(address);
                        }
                        case 2 -> {
                            System.out.println("Enter new address in the format (Street, City, State, " +
                                    "PostalCode, companyName) : ");
                            Scanner kb2 = new Scanner(System.in);
                            BusinessAddress address = new BusinessAddress(kb2.nextLine(), kb2.nextLine(),
                                    kb2.nextLine(), kb2.nextLine(), kb2.nextLine());
                            this.addressList.getLast().setAddress(address);
                        }
                    }
                    System.out.println("User's address is added successfully!");
                }
                case 8 -> {
                    System.out.println("-".repeat(25));
                    System.out.println("Choose an address type : ");
                    System.out.println("0 - Go back");
                    System.out.println("1 - Home address");
                    System.out.println("2 - Business address");
                    System.out.println("-".repeat(25));
                    System.out.print("Your choice : ");
                    byte chosen2 = kb.nextByte();
                    switch (chosen2) {
                        case 1 -> this.addressList.removeFirst();
                        case 2 -> this.addressList.removeLast();
                    }
                    System.out.println("User's address is removed successfully!");
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    protected void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    protected void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Address> getAddressList() {
        return addressList;
    }

    public LocalDate getLastLoginLocalDate() {
        return lastLoginLocalDate;
    }

    protected void setLastLoginLocalDate() {
        this.lastLoginLocalDate = LocalDate.now();
    }
}
