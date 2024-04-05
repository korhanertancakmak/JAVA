# [Immutable Classes Challenge]()
<div align="justify">

I've created the usual **Main** class and _main_ method.

```java  
public class Main {
    public static void main(String[] args) {
        
    }
}
```

Before I do anything there, I'll start with the **BankAccount** class.
I'll put it in a different package, _bank_.

```java  
public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private final double balance;

    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }
}
```

I'll include a basic enum for my account types. 
You can imagine this might grow with different account types.
I'll include this as a nested type, 
and just set it up with _Checking_ and _Savings_ to start. 
Next I'll set up my two fields, account type and balance, 
and I'll make these private and final. 
An enum value is an immutable type, 
so neither of these fields is subject to side effects, once assigned. 
This isn't going to compile unless I assign values, 
either directly with an assignment here, 
which wouldn't make any sense for these fields, 
or in a constructor. 
I'll generate the constructor, using both of my fields, 
and leave that as is. 
I'll generate some getters for both fields. 
Next, I want to generate the _toString_ method,
generating that with no fields, so I'll select `none`. 
I'll return a formatted string 
that prints the account type and the balance, 
with two decimal places. 
I'll test this out, in my **Main** class.

```java  
public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
        System.out.println(account);
    }
}
```

I'll create a local variable, type **BankAccount**, named _account_. 
I'll assign that a new Bank account instance, 
passing the _Checking_ enum, 
and _500_ as the initial deposit. 
I'll print that account. 
Running that:

```html  
CHECKING $500.00
```

I see that I have a checking account, with 500 dollars in it. 
This class can be called immutable. 
Once created, I can't change the type or balance. 
Now, this may not be very realistic 
if I'm writing a banking application. 
On the other hand, if I'm writing a service 
that will pass my bank's accounts 
to some sort of clearing house, for example, 
I wouldn't want these classes to be tampered with.
Next, I'll create the **BankCustomer** class, 
also in the same **bank** package.

```java  
public class BankCustomer {

    private static int lastCustomerId = 10_000_000;

    private final String name;
    private final int customerId;
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    @Override
    public String toString() {

        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".formatted(name, customerId,
                String.join("\n\t", accountStrings));
    }
}
```

I want to set up a private static field. 
I'll call it _lastCustomerId_, 
setting that to 10 million. 
This will be my customer id generator, 
and I just want them to start with eight digits. 
I'll add two instance fields, _name_, and _customerId_, 
and I'll make these both private and final. 
The last field will be a list of bank accounts. 
I'll make this private and final, calling that _accounts_, 
and I'll make this a new ArrayList.
I'll generate a constructor, I only want 
to pass the customer name for this constructor. 
The customer id will be generated, 
and I don't want to pass a list to this constructor either. 
I'll change this constructor to include a checking account balance, 
as well as a savings-account balance. 
I'll add a double called checking amount, 
and another double, _savingsAmount_. 
Now I'll set up the rest of my fields in this constructor. 
First, I'll assign _lastCustomerId_ to my customer id field, 
and increment that with a post-fix increment. 
I'll add a new bank account, a checking account, 
with the checking amount. 
This gets added to my _accounts_ list. 
I'll do the same thing for a saving account, 
passing the _savingsAmount_, 
adding this to my _accounts_ list. 
I'll add getters by generating them 
for the _name_ and _accounts_ fields. 
I'll leave these as is for the moment, and first, 
include a _toString_ method, 
selecting the select `none` button. 
I'll remove the return statement. 
I want to include all the accounts in this string. 
I'll start with a string array, called account strings, 
and that will be a new array, 
the same size as my _accounts_ list. 
I'll populate each element in that array, 
with the string representation of each account. 
I'll do this by calling `Arrays.setAll`, 
get the account using the lambda expressions index, 
and return the string value of the element at that index. 
My formatted string will contain the customer name, 
the customer id in parentheses, prefixed with zeroes up to 15 characters. 
This will be followed by a new line and tab, 
and then I'll print my account strings, 
which will be joined by a newline and tab, 
so each account will print on a separate line, with an indent. 
I'll test this now, in my _main_ method.

```java  
BankCustomer joe = new BankCustomer("Joe", 500.00, 10000.00);
System.out.println(joe);
```

First, I'll comment out the code for the account data. 
I'll create _joe_ next, so a new **BankCustomer** named _joe_, 
and that's a new Bank customer, name is _joe_, 
checking balance is 500, and savings is ten thousand. 
I'll print Joe out.
When I run this:

```html  
Customer: Joe (id:000000010000000)
CHECKING $500,00
SAVINGS $10000,00
```
                        
You can see _Joe_, with his 15-digits id, prefixed with zeros, 
and his two accounts. 
Let's get _Joe_'s accounts and see 
if _Joe_'s Customer record is really immutable.

```java  
List<BankAccount> accounts = joe.getAccounts();
accounts.clear();
System.out.println(joe);
```

I'll get the account data. 
I'll call clear on it, 
and I'll print it out. 
Running that:

```html  
Customer: Joe (id:000000010000000)
CHECKING $500,00
SAVINGS $10000,00

Customer: Joe (id:000000010000000)
```
                    
You can see I was able to successfully clear the accounts. 
That's probably not good. 
I can also add accounts this way:

```java  
accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 150000));
System.out.println(joe);
```

I'll create a new Bank Account, a checking account, 
with one hundred and fifty thousand dollars, 
and add that to the _accounts_ variable. 
I'll again print _joe_ here. 
And running that:

```html  
Customer: Joe (id:000000010000000)
CHECKING $150000,00
```
                        
You can see I made **Joe** a lot richer than he was initially. 
I can prevent this last behavior fairly easily, 
by making Bank Account's constructor package private, 
so let me do that, removing the accessor altogether, 
removing public there. 
This gives me an error now in my main method, 
which is good, so I'll remove those last two statements.
Also, if I want to create a subclass of Bank Account, 
look what happens if I try. 
You can see I can't do this,
because there is no default constructor available on BankAccount. 
And let's say I add a constructor, 
that matches the signature of Bank Account's constructor, 
and simply try to call it. 

```java  
class MyAccount extends BankAccount {

    MyAccount(AccountType accountType, double balance) {
        super(accountType, balance);
    }
}
```

Here in super too, 
I have an error, on that call to super, 
that it's not public, and it's also not protected. 
This means I can't create a subclass of **BankAccount**
from any other package. 
Ok, that's a good thing.
I'll remove that class from my Main.java file. 
Ok, I'm partially there. 
I can create accounts from any classes, 
not in the same package as the **BankAccount** class, 
but I need to fix my get method on the **BankCustomer**.

```java  
public List<BankAccount> getAccounts() {
    return new ArrayList<>(accounts);
}
```

I'll return a new ArrayList, 
initialized with the current _accounts_ data. 
If I run this code:

```html  
Customer: Joe (id:000000010000000)
CHECKING $500,00
SAVINGS $10000,00

Customer: Joe (id:000000010000000)
CHECKING $500,00
SAVINGS $10000,00
```

I see the results look better. 
I'm still calling clear, on the _accounts_ data 
I get back from the **BankCustomer** class,
but it's a copy. 
It doesn't really change the data on the **BankCustomer**. 
Now, I've got a **BankCustomer** with _accounts_.
I can view the data and get a copy of some data, 
but I can't modify it. 
In the next section, I'll be talking more about defensive copies, 
both shallow and deep, and I'll follow that with an introduction 
to unmodifiable collections, so keep this code handy. 
We'll be coming back to it in other challenges coming up.
</div>