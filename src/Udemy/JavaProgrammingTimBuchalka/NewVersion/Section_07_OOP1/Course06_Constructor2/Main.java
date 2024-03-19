package CourseCodes.NewSections.Section_07_OOP1.Course06_Constructor2;

/*
Course-36

                                                 Constructors (Part-II)

        In the last course, we talked about constructors, including the default constructor, which can be explicitly declared,
    and the rules for when it is implicitly declared by Java. We talked about declaring constructors with parameters, and
    passing arguments to the "new" keyword, to set fields on the object being created. We talked about constructor overloading,
    which is similar to method overloading, where you can declare multiple constructors with different parameters.

        So, in this course, we'll start with the concept of constructor chaining, which is the process of calling one overloaded
    constructor from another.

                                                Constructor chaining with "this()"

        Constructor chaining is when one constructor explicitly calls another overloaded constructor. You can call a constructor
    only from another constructor. You must use the special statement "this()" to execute another constructor, passing it
    arguments if required. And "this()" must be the first executable statement, if it's used from another constructor.

        Let's look at another example, and this time we'll use constructor chaining, which we've said is, calling one constructor,
    from another constructor. That may sound a little bit confusing, but we'll cover some reasons why you would want to do
    this next.

                public Account() {
                    System.out.println("Empty constructor called");
                }

                public Account(int number, double balance, String customerName, String email, String phone) {
                    System.out.println("Account constructor with parameters called");
                    this.number = number;
                    this.balance = balance;
                    this.customerName = customerName;
                    customerEmail = email;
                    customerPhone = phone;
                }

        First, we'll use the default constructor to instantiate an object, and pass it some default values. In other words,
    from the constructor with no parameters, we'll call the one with 5 parameters, and pass in literal values. So to do
    that, we type, this, followed by parentheses. Which constructor is called is determined by the values we pass. So we'll
    add a call to this in the no args constructor, and we'll just pass some literals as arguments. The type and number of
    arguments we pass, must match one of our constructors. Since we only have 1 other constructor declared, and it only has
    5 parameters, we'll pass 5 arguments. But the types must match the order of the types, that were declared in the
    constructor. So, the parameters are strings in the second constructor, customerName, email and phone:

                public Account() {
                this(1234567, 2.5, "Default name", "Default address", "Default phone");
                System.out.println("Empty constructor called");
            }

    So what we're doing there with the "this()", is a special use of this, which you won't see used anywhere else. This is
    calling another constructor within a constructor. So what we're saying here is "look, if you try and create an object
    from this class, and you don't give me any parameters, set this new object up with these values, by calling this other
    (second) constructor." Constructor chaining is optional, meaining it's not something you have to do, but there can be
    situations where you want to do this.

        Now one other thing to keep in mind is, using "this", to call another constructor, is that you have to be sure that
    it's very first line that's executed. In other words, we couldn't have System.out.println, as the first line in the
    constructor. If we try that, we will have a compiler error, which says, "Call to "this()" must be first statement in
    constructor body". So the rules are pretty strict, using the "this()" statement with parameters, can only be called
    in a constructor, and it has to be the very first line that's called.

        Let's go back to our Main class, and we'll change the code to just call the empty constructor. And running that:

                            Account acc = new Account();

                            System.out.println("Account number = " + acc.getNumber());
                            System.out.println("Account balance = $" + acc.getBalance());

    gives:

                            Account constructor with parameters called
                            Empty constructor called
                            Account number = 1234567
                            Account balance = $2.5

    you can see, that we get the System.out.println statement from both constructors. So they're actually both called as
    you can see there. The reason why you see it in that order, makes sense if you think about it. If you come back here
    to the Account.java, you see, the very first line of the "no arguments" account constructor, called the other constructor
    with 5 arguments. So the statement in the 5 argument constructor was printed first. The fields were set to the values
    passed, and then the code returned to the no-args constructor. It then executed the line following the call to this,
    which printed out, "Empty constructor called". So as you can see, 1234567 and 2.5, were actually passed, and these
    are printed out. So it's obviously working, the default constructor is making a call to the 5 argument constructor,
    which sets the fields to the values we specified.

        Let's look again the second constructor with 5 parameters:

                            public Account(int number, double balance, String customerName, String email, String phone) {
                                System.out.println("Account constructor with parameters called");
                                this.number = number;
                                this.balance = balance;
                                this.customerName = customerName;
                                customerEmail = email;
                                customerPhone = phone;
                            }

    You may have noticed, looking at this code, is that we've actually updated the fields directly. We didn't call the
    setter methods from the constructors. So there's an alternative, what we could have done, is we actually could have
    done something like "setNumber(number)". If we had some validation in that setter, that was testing for valid numbers,
    and those types of things, we could actually, execute that code as well.

        Now in Java, there are conflicting opinions as to which is the best approach. Because you'll find out in following
    courses, when we start talking about inheritance, and creating subclasses, these calls to setter methods might not work.
    So the general rule of thumb is, it's always better, to assign the values directly to the field, rather than calling
    the setter, in a constructor. Because as you'll see in the next course, there can be scenarios where this code:

                            public void setNumber(int number) {
                                this.number = number;
                            }

    that's in this setter isn't executed. So by going back and actually coding it directly, in other words, going back and
    setting it to "this", and whatever the field name is, you're guaranteed that the field values will be initialized. So
    my general rule of thumb is, with constructors:

    ==>  "don't call setters or any other method, other than another constructor within those constructors."

    And the other reason for that is, this is the point in the code where the object is being created. So consequently,
    some aspects of the initialization, may not have been finished while you're in the constructor. And that's the other
    reason that there's an opinion out there, that suggests that you shouldn't be calling other methods, or even the setters
    within the constructor code. But we can talk more about that later if needed.

        Let's now assume that we wanted to create another constructor, and for this one, we only want to pass the customer
    name, email address, and phone number. So we could do that by creating another constructor, by cutting and pasting and
    editing an existing constructor. But IntelliJ gives us yet another code generation tool, this one for constructors.
    So let's position our cursor after the second constructor, on line 25, after a couple of additional empty lines. Next,
    we'll click on "Code" on the menu, and select "Generate" as the menu option. Then we'll select the first option which
    is constructor, and it asks which field do you want to include in the constructor? So which ones are we going to have
    passed to us. In other words, which fields do we want the constructor to set? Let's pick 3 we talked about, customer
    name, email address, and the phone number.

                                public Account(String customerName, String customerEmail, String customerPhone) {
                                    this.customerName = customerName;
                                    this.customerEmail = customerEmail;
                                    this.customerPhone = customerPhone;
                                }

    When we hit ok, we get a new constructor, as above, generated for us, setting the instance fields to the parameters
    passed. So there's our third constructor. And you can see, it's only setting 3 of 5 instance fields. So that's one way
    of doing it, buf of course, the disadvantage here, is that our account number and our balance, aren't included. But
    we could call the 5 argument constructor, and pass a couple of default values, so let's do that. We'll also comment
    out the initialization code, because we'll be initializing them in the constructor with 5 parameters:

                                public Account(String customerName, String customerEmail, String customerPhone) {
                                    this(99999, 100.55, customerName, customerEmail, customerPhone);
                                    // this.customerName = customerName;
                                    // this.customerEmail = customerEmail;
                                    // this.customerPhone = customerPhone;
                                }

    So you can see what we've done there is, we've defaulted 2 parameters, the account number to be 99999, and the default
    balance to $100.55. So we've come up with what the default is, because they weren't specified. And we've still gone back
    and called our major constructor. This is the one that actually updates all the fields. So you'll find, as you start
    creating and writing more complex code, It's not unusual to see multiple constructors like this. And in that situation,
    often you do all your initialization in the one constructor, like you can see here:

                            public Account(int number, double balance, String customerName, String email, String phone) {
                                System.out.println("Account constructor with parameters called");
                                this.number = number;
                                this.balance = balance;
                                this.customerName = customerName;
                                customerEmail = email;
                                customerPhone = phone;
                            }

    All other constructors can call this major constructor, passing default values or null references, as arguments. That's
    a good way of doing things, and it often leads to really good coding, because you're not having to duplicate code, or
    duplicating initialization in more than one place.

        So how do we call this new constructor, when creating an account? We would call that very much the same, as we've
    been doing before. Let's create a new object here, using this 3 argument constructor. So, going back to the Main class
    in Description.txt, and adding our code just before the last bracket of the main method block:

                            Account korhansAccount = new Account("Korhan",
                                    "korhanertancakmak@gmail.com", "12345");
                            System.out.println("AccountNo: " + korhansAccount.getNumber() +
                                    "; name " + korhansAccount.getCustomerName());

    Let's just run that to make sure that it's working.

                            Account constructor with parameters called
                            Empty constructor called
                            Account number = 1234567
                            Account balance = $2.5
                            Account constructor with parameters called
                            AccountNo: 99999; name Korhan

    And you can see the last line in the output is from our new Account, korhansAccount. 99999 was the default account
    number, that we used in 3 argument constructor, in the Account class, as you can see. And the name was Korhan, which
    of course was what we passed here.

        So that's constructors. You'll see those used extensively in Java. And of course, we'll be using them a lot in
    this course as we move forward, because they're a very important part of creating objects from classes.
*/


public class Main {
    public static void main(String[] args) {

        Account acc = new Account();

        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());

        Account korhansAccount = new Account("Korhan",
                "korhanertancakmak@gmail.com", "12345");
        System.out.println("AccountNo: " + korhansAccount.getNumber() +
                "; name " + korhansAccount.getCustomerName());
    }
}
