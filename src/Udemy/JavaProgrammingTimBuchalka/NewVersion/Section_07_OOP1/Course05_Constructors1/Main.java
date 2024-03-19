package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_07_OOP1.Course05_Constructors1;

/*
Course-35

                                                 Constructors (Part-I)

        A constructor is used in the creation of an object, that's an instance of a class. It is a special type of code
    block that has a specific name and parameters, much like a method. It has the same name as the class itself, and it
    doesn't return any values. You never include a return type from a constructor, not even void. You can, and should,
    specify an appropriate access modifier, to control who should be able to create new instances of the class.

                                public class Account { // This is the class declaration
                                         public Account () { // This is the constructor declaration
                                               // Constructor code is the code to be executed as the object is created.
                                         }
                                }

        So you can essentially do all the statements we've done here,

                                acc.setNumber(1234567);
                                acc.setBalance(1000d);
                                acc.setName("Korhan Çakmak");
                                acc.setEmail("korhanertancakmak@gmail.com");
                                acc.setPhoneNumber(3827846);

    to set the initial parameters, or the initial values of the fields. And you can include any other initialization code,
    you want to perform, in the constructor. So let's see whether we can replace all this code.

        First, we'll edit our Account.java class, and add a constructor. It turns out that a constructor is actually
    created for you implicitly by Java. When we say things are implicit in Java, we mean you can't see the code in the
    source, but it's in the byte code, generated during the compilation process. So, when you actually type new, and the
    name of the class, and then parentheses, this is actually calling the constructor.

                                Account acc = new Account();

    We didn't explicitly create a constructor in the Account class, so Java created one for us. This is called the default
    constructor.

                                           The Default Constructor

        If a class contains no constructor declarations, then a default constructor is implicitly declared. This constructor
    has no parameters, and is often called the no-args (no arguments) constructor. If a class contains any other constructor
    declarations, then a default constructor is NOT implicitly declared. There are other rules for the default constructor,
    which we'll talk about, as we get more familiar with more advanced topics. For now, it's important to just understand
    that a constructor exists, whether you explicitly declare one or not. This is why creating an object with the "new"
    keyword, and passing no arguments in the parentheses is supported in nearly all cases.

        So let's create our own constructor in the Account class next:

                                        public Account() {
                                            System.out.println("Empty constructor called");
                                        }

    So again, the rules for a constructor are:

    - its name has to be the same as the class
    - it has no return type, not even void
    - Also we use an access modifier, here we use public

    We'll talk about when you'd want to use other access modifiers later. So if we run this now, what we should see at the
    top, before any other output, is the message we put in the constructor, "Empty constructor called". So when we type,
    new Account with parentheses and no parameters, that actually was Java's cue to call the constructor that we just added.
    So the purpose of the constructor is, to essentially initialize the object that we're creating, and do whatever else
    we need to happen, while the object is being instantiated. So it's only ever called once, at the start, when we're
    creating the object.

        A class can have 1 or many constructors, one of which can be a No Args constructor. So now we'll add another
    constructor, and this time we'll declare some parameters. Doing this will let us pass values to the constructor. We
    can then use these values, to assign data to our fields, instead of calling a whole bunch of setter. This time, it
    will have 5 parameters, one for each of the fields on the Account class.

                             public Account(int number, double balance, String customerName, String email, int phone) {
                                    System.out.println("Account constructor with parameters called");
                                    this.number = number;
                                    this.balance = balance;
                                    this.customerName = customerName;
                                    customerEmail = email;
                                    customerPhone = phone;
                             }

    Here i just want to point out a few things with this constructor. First, we're passing 2 parameters, email and phone,
    where the parameter name doesn't match the field name, which are customerEmail and customerPhone. It's common practice
    to make the parameter names the same as the field names, but it's not required. If you do make them the same, using
    the "this." notation is required, just like it was in the setter methods. So here, you can see, we're setting
    "this.number = number". This means we're assigning the number attribute on the instance, that's being created (known
    as "this.number"), to the argument value, "number", passed in the "number" parameter. But we don't need the "this."
    notation for customerEmail, or customerPhone, because these field names are different than the parameter names.

        So, if we go back to Description.txt, we can now change the way we instantiate "acc", passing all the values to the call
    to the "new" keyword. Let's comment all the setter methods, to show they're not needed anymore. They're being replaced
    with a single statement.

                Account acc = new Account(1234567,1000d,
                         "Korhan Çakmak","korhanertancakmak@gmail.com",3827846);

       //        acc.setNumber(1234567);
       //        acc.setBalance(1000d);
       //        acc.setName("Korhan Çakmak");
       //        acc.setEmail("korhanertancakmak@gmail.com");
       //        acc.setPhoneNumber(3827846);

    And running this, we can confirm that we executed the new constructor. You can see the first statement, in the output,
    "Account constructor with parameters called". Running with some System.out.println statements:

                System.out.println("Account number = " + acc.getNumber());
                System.out.println("Account balance = $" + acc.getBalance());

    shows us that the number account number printed, as well as the initial deposit amount, which we set to $1000. So having
    multiple constructors as we've done here, is called "constructor overloading". It looks a lot like method overloading
    doesn't it?

                                            Constructor Overloading

        Constructor overloading is declaring multiple constructors, with different formal parameters. The number of parameters
    can be different between constructors. Or if the number of parameters is the same between 2 constructors, their types
    or order of the types must differ.
*/

public class Main {
    public static void main(String[] args) {

        Account acc = new Account(1234567,1000d,
                "Korhan Çakmak","korhanertancakmak@gmail.com",3827846);

        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());

//        acc.setNumber(1234567);
//        acc.setBalance(1000d);
//        acc.setName("Korhan Çakmak");
//        acc.setEmail("korhanertancakmak@gmail.com");
//        acc.setPhoneNumber(3827846);

/*        System.out.println("Account number = " + acc.getNumber());
        System.out.println("Account balance = $" + acc.getBalance());
        System.out.println("Account name = " + acc.getName());
        System.out.println("Account email = " + acc.getEmail());
        System.out.println("Account phone number = " + acc.getPhoneNumber());*/

        acc.depositFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(255.25);
        acc.withdrawFunds(1000);
    }
}
