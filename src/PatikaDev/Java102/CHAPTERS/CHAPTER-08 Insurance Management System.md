# Insurance Management System

Imagine you making software for an insurance company.
The insurance company has two types of customer 
profiles: "Individual" and "Enterprise."
Design an abstract class named "Account" for the customer profile.
Account class is the account information where all information of the customer is kept 
after logging into the system.
There is an object reference of type "User" within the "Account" class. 
(As Aggregation relationship)

The "User" class represents the customer's contact information. In the "User" class, 
following information of the customer is available: 

* name (String),
* surname (String),
* e-mail (String),
* password (String),
* profession (String),
* age (int),
* address list (ArrayList < Address >)
* Last login date to the system (Date)

Additionally, the "User" class contains a list of ArrayList type addresses.
Address information is of two types. 
Design two classes: Home address ("HomeAddress") and Business Address ("BusinessAddress"). 
These address classes will inherit from an interface called "Address." 
However, you will decide which functions should be in this interface.

Design a class called "AddressManager" that is responsible for adding and removing customer addresses. 
Define two static functions in this class that can add or remove elements to the address list of the "User" object. 
You determine these function names.

Define a "final" type function in the "Account" class that prints customer information on the screen, 
does not return a value, and is named "showUserInfo."
Keep a list of the insurances taken out by customers in the "Account" class. 

Design an abstract class named "Insurance" that represents insurance. 
In this abstract class will have variables such as: 

* name of the insurance (String),
* insurance fee (double)
* start-end date of insurance

Additionally, an abstract function named "calculate" will be defined. 
This abstract function will be populated in the inherited classes below.

Derived from this abstract class, design 4 subclasses.

* "HealthInsurance" (private health insurance),
* "ResidenceInsurance" (home insurance),
* "TravelInsurance" (travel insurance),
* "CarInsurance" (car insurance)

Each subclass will calculate the insurance fee on its own by overriding the abstract function called "calculate."

Considering the above definitions, the abstract class "Account" should contain the following.

* user's login status (AuthenticationStatus)
* user object (User)
* List of insurances the user has taken out (ArrayList)
* Define an enum of type AuthenticationStatus. 
Define two constants named SUCCESS and FAIL in the enum. 
SUCCESS will be used if the login process is successful. 
FAIL will be used if there is no login.
* A function will be defined for the user to log in to his account. 
This function will receive email and password information 
and compare the incoming email password information with the email and password in the User class. 
If the information entered is correct, the login process will be successful. 
And the user's login status will be pulled to SUCCESS. 
If the login process is unsuccessful, it will throw an error of type "InvalidAuthenticationException." 
You will need to write this error class yourself, inheriting from the Java class called Exception.
* A non-abstract function will be defined where the user can add addresses.
A non-abstract function will be defined where the user can remove addresses. 
A function that returns a value indicating the login status of the user will be defined.
* An abstract function will be defined so that the user can add an insurance policy. 
This abstract class will be filled by overriding it in subclasses named "Individual" and "Enterprise." 
Because the profit margin applied to the prices of packages added by individual and corporate customers will be different.

Design a class named AccountManager. 
Let this class keep a data list of type TreeSet. 
Keep the individual and corporate accounts you create in this list. 
Define a function named login in this class. 
This function takes the email and password information given externally, navigates through the Account list, 
and if it finds a suitable login process, it will return the Account object to the place where it was called. 
This function will call the "login" function on the Account object. 
Remember, this function could throw an error of type "InvalidAuthenticationException." 
Therefore, remember to set up a try-catch mechanism here.

Since we will keep objects from the "Account" class in the TreeSet, 
make sure that it inherits from the "Comparable" interface. 
Also, remember to fill in the "hashCode" and "equals" functions of the "Account" class.

Design a class that receives email and password information from the keyboard. 
Call the "login" function in the AccountManager class with the email and password information received from the keyboard. 
If you are logged in with a valid user, this function will return you an object of type Account.
