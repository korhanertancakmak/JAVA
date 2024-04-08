# [Section-12: Immutable and Sealed Classes]()

## [a. Final Modifier]()
<div align="justify">

In this section, I want to review the terms mutable and immutable, 
and talk about issues related to these two terms.
Objects have state, which is the data stored in instance fields. 
State can change after an object is created, either intentionally 
or unintentionally. 
When state remains constant throughout the lifetime of the object, 
and code is prevented from changing the state, 
this object is called an immutable object. 
An immutable object is an object whose internal state remains constant. 
A mutable object is an object whose internal state does not remain constant.
Which is better?
Well, like anything else, that depends.
Working with immutable objects has some advantages. 
An immutable object isn't subject to unwanted, 
unplanned and unintended modifications, known as side effects. 
This means we don't have to write defensive or protective code for these objects 
to protect them from possible mutations. 
An immutable class is inherently thread-safe, because no threads at all can change it 
once it's been constructed. 
This allows us to use more efficient collections and operations, 
which don't have to manage synchronization of access to this object. 
We'll talk more about this when we get to the threading and synchronization section of the course. 
These are two of the most important advantages. 
Working with immutable objects has some disadvantages. 
An immutable object can't be modified after it's been created. 
This means that when a new value is needed, you're probably going to need 
to make a copy of the object with the new value. 
You'll remember the discussion comparing String vs. StringBuilder. 
If you're constantly needing to alter text values, it's more efficient to use a mutable object 
like a StringBuilder instance, then generating tons of new String objects. 
I'll be talking about immutable class design coming up. 
It's important to understand that POJOs and JavaBeans, which many code generation tools create, 
are not by design, immutable, and therefore this architecture may not be the preferred design 
for your class.
This all sounds rather simple, yet there are many topics related to it. 
One of the most useful tools in our arsenal to build immutable classes 
is the final access modifier.

When we use the final modifier, we prevent any further modifications to that component. 
* A final method means it can't be overridden by a subclass. 
* A final field means an object's field can't be reassigned 
or given a different value after its initialization. 
* A final static field is a class field that can't be reassigned,
or given a different value, after the class's initialization process.
* A field declared on an Interface is always public, static and final. 
* A final class can't be overridden, meaning no class can use it in the _extends_ clause. 
* A final variable, in a block of code, means that once it's assigned a value, 
any remaining code in the block can't change it. 
* A final method parameter means we can't assign a different value to that parameter 
in the method code block. 
In this section, I'll review all of these uses of the final modifier in more detail.

You can use the _final_ modifier on methods. 
Using _final_ with methods only makes sense in the context of wanting to restrict 
what your subclasses can override or hide. 
Using _final_ on an _instance_ method means subclasses can't **override** it. 
Using _final_ on a _class_ (static) method means subclasses can't **hide** it. 
Let's switch over to some code, and examine these two different scenarios. 
I've created a **Main** class and _main_ method. 
Before I include anything there, I'm going to create a class called **BaseClass**, 
in a **generic** package.

```java  
public class BaseClass {
    public void recommendedMethod() {

        System.out.println("[BaseClass.recommendedMethod]: Best Way to Do it");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod() {
        System.out.println("[BaseClass.optionalMethod]: Customize Optional Method");
    }

    private void mandatoryMethod() {
        System.out.println("[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!");
    }
}
```

Let's imagine that I've been tasked to write a **library** class, 
and although it should be extensible, I don't want everything to be customizable. 
This class will have three methods. 
The first, a public method, is there to control the work flow, 
and its expected this method will always be called. 
I'm going to make it public and void, and call it _recommendedMethod_. 
I'll print that this is on the Base class, and this is the best way to do it. 
This code has a method where optional code could go, or extensions could be made. 
This will go in a _optionalMethod_ method. 
This will be protected, meaning the only classes 
that can call it are the subclasses, or in the same package as this class. 
I'll just print that this is on **BaseClass**, 
and it's meant to be customizable or optional.
There's also a _mandatoryMethod_, which this base class expects
to be executed for every instance.
This one is private because I don't want subclasses 
to alter or override this code. 
I'll print that this method is on **BaseClass**, and this code is non-negotiable. 
This feels like a solid way to allow a little bit of customization,
but still have most subclasses execute the same work flow. 
Now, I'm going to emulate a class that might use my library class. 
I'll create a new class, calling that **ChildClass**. 
To keep it more real, I'll put this in completely different package structure. 
I'll expand the project panel, and highlight my new class name is **consumer.specific.ChildClass**,
and I want it to extend **BaseClass** here. 

```java  
public class ChildClass extends BaseClass {
    
}
```

Hopefully it finds the generic. 
BaseClass for you, otherwise you'll have to manually import that. 
Doing nothing else, I should be able to create instances of both classes, 
executing the _recommendedMethod_ on both. 
I'll do this in the main method in my **Main** class.

```java  
public class Main {

    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println("--------------------");
        childReferredToAsBase.recommendedMethod();
        System.out.println("--------------------");
        child.recommendedMethod();
    }
}
```

I'll create an instance of a **BaseClass**, and assign it to a **BaseClass** reference, 
named _parent_. 
Then I'll create an instance of **ChildClass**, assigning it to a **ChildClass** reference,
and I'll call that _child_. 
I also want an instance of a **ChildClass**, 
but I'm going to assign it to a **BaseClass** reference type, 
so I'll call that **childReferredToAsBase**, to keep it straight. 
Next, I'll call my _recommendedMethod_ on each of those instances. 
So first, I'll call _recommendedMethod_ on the parent instance. 
I'll put a separator line between output. 
I'll call the same method on my _child_ instance, 
that's assigned to a base class reference. 
And I'll repeat that for the _child_ instance. 
If I run this code:

```html  
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
```
                    
The results are all the same, as you'd expect, for all three instances. 
Now, let's say the designer of the **child** subclass reads our specification, 
and learns they should override the _optionalMethod_ 
if they want some custom functionality. 
I'll go to the **Child** class,
and use IntelliJ's override tools to generate this method. 

```java  
@Override
protected void optionalMethod() {

    System.out.println("[Child:optionalMethod] EXTRA Stuff Here");
    super.optionalMethod();
}
```

I'll customize this. 
I'm ok with the code that's on the **BaseClass**, 
but I want to include some extra functionality before that executes. 
I'll just add a statement that some extra stuff happened. 
I'll go back to the **Main** class and re-run this.

```html  
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
```
            
You can see, for the two child instances, the last two output segments show 
that the _optionalMethod_ on **Child** was executed, and the statements, 
child, _extra stuff here_, is printed. 
Our library class is being used as designed, 
and this is what we used to call, **S-W-A-D**, _System works as designed_. 
But now, let's say the subclass designer decides 
to override the recommended method, on his own class. 
I'll make the change.

```java  
@Override
public void recommendedMethod() {

    System.out.println("[Child:recommendedMethod]: I'll do things my way");
    optionalMethod();
}
```

It is, after all, only recommended. 
Again, I'll use the override tools 
to generate an override for _recommendedMethod_.
The designer of this class didn't read the specification, 
or maybe we forgot to mention that if you do this, 
you should always call super's recommended method. 
Now, I'm going to completely replace that statement with my own code.
I'll just print that I'll do things my way. 
And I'll remember to call _optionalMethod_. 
Back to the **Main** class, I'll re-run again.

```html  
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[Child:recommendedMethod]: I'll do things my way
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
--------------------
[Child:recommendedMethod]: I'll do things my way
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
```
            
The two child instances demonstrate that 
they're not following our best practices suggestion. 
They've customized the entire work flow, and even worse, 
the non-negotiable method is never run. 
Since it can't be accessed by subclasses, 
they can't even invoke it. 
What choices do you have now? 
You could make the non-negotiable method, _mandatoryMethod_,
be protected, so subclasses can call it, 
but that still puts the onus of invoking it, on the subclasses. 
You could recommend that designer of subclasses, 
copy and paste the _mandatoryMethod_ into the subclass. 
That takes all the control of that functionality away from the **BaseClass**. 
If the **BaseClass** makes a change to its private _mandatoryMethod_, 
how will it broadcast to all the subclasses, 
that they should change their copies? 
The better solution is to make the _recommendedMethod_ **final** on the **BaseClass**. 
Let me go back to the BaseClass,
and do that, adding final to the _recommendedMethod_.

```java  
public final void recommendedMethod() {

    System.out.println("[BaseClass.recommendedMethod]: Best Way to Do it");
    optionalMethod();
    mandatoryMethod();
}
```

The subclass, **ChildClass**, doesn't compile now.
When you make a method final, a subclass can't override it. 
I'll comment out this method. 

```java  
/*
@Override
public void recommendedMethod() {

    System.out.println("[Child:recommendedMethod]: I'll do things my way");
    optionalMethod();
}
*/
```

Now, the only code the subclass can override is the _optionalMethod_, 
which is exactly what I want. 
I now have control over the functionality of subclasses, where it's important. 
I've used _final_, and designed the methods accessed 
by the _final_ method in various ways, as I showed you here. 
For code that's non-negotiable, I can make that private, 
or alternatively I could make that _final_. 
What happens if I add final to a private method? 
Let me go back to the **BaseClass** and try that,

```java  
private final void mandatoryMethod() {
    System.out.println("[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!");
}
```

Adding _final_ to my mandatory method. 
Notice that IntelliJ has grayed out the final keyword in this case. 
I'll come back to that in just a minute. 
I want to first jump back to the **child** class, 
and override this method, _mandatoryMethod_.

```java  
private void mandatoryMethod() {
    System.out.println("[Child:mandatoryMethod]: My own important stuff");
}
```

If you try to do this using IntelliJ's override help, 
you won't see this method listed. 
I'll type this method out, using the same signature. 
I'll add a print statement, so Child's mandatory method, 
my own important stuff. 
Ok, there's a couple of things I want to point out here. 
* First, this isn't an override 
you can't override methods that are private on a **BaseClass**. 
*  Second, I was able to create this method,
even though I added _final_ on the **BaseClass**'s method. 

The modifiers private and _final_ are somewhat redundant. 
When a method is private, no other class, including subclasses,
has access to it, and therefore any code that calls it 
will only execute the code on the class where it's declared.
To put it another way, using the private modifier means 
this method will never be **polymorphic**. 
That means if I run my code now, 
even though I have my own private method called _mandatoryMethod_, 
on the child, it's never going to be the method executed 
from the _recommendedMethod_, on **BaseClass**. 
I'll go back to the Main class and rerun this.

```html  
[BaseClass.recommendedMethod]: Best Way to Do it
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
```

And you can see for all instances the non-negotiable statement, 
meaning the private method on **BaseClass**, 
was executed every time. 
Getting back to the **BaseClass**,
let's see why IntelliJ has grayed that out. 
Although the message isn't great, 
_private method declared final_, 
the suggestion is _to remove the final modifier_. 
It's redundant for private methods, 
but not a compiler error. 
I'll remove that keyword from this method. 
Now, I want to compare instance methods, 
with static methods, for a few minutes.
I'm going to create three static methods on my **BaseClass**.

```java  
public static void recommendedStatic() {

    System.out.println("[BaseClass.recommendedStatic] BEST Way to Do it");
    optionalStatic();
    mandatoryStatic();
}

protected static void optionalStatic() {
    System.out.println("[BaseClass.optionalStatic]: Optional");
}

private static void mandatoryStatic() {
    System.out.println("[BaseClass.mandatoryStatic]: MANDATORY");
}
```

These will be similar to the instance methods, 
so first a public one, I'll call _recommendedStatic_.
It will print that this is base class _recommendStatic_, 
and _the best way to do it_. 
I'll create the _optionalStatic_ method next, 
and that's protected and static.
This will print that it's the _optionalStatic_ method on base class. 
Lastly, I need the _mandatoryStatic_ method,
and that will be private and static. 
This will print that it's the _mandatoryStatic_ method on base class. 
Getting back to the _main_ method on the **Main** class,

```java  
System.out.println("--------------------");
parent.recommendedStatic();
System.out.println("--------------------");
childReferredToAsBase.recommendedStatic();
System.out.println("--------------------");
child.recommendedStatic();
```

I'll insert a separator line. 
I'll copy the statements above, 
pasting that at the end of the _main_ method. 
I'll change each of the method calls to call the static methods, 
so I'll replace method with static on there.
Ok, so maybe you're wondering why I'm calling static methods, 
using instance references, meaning using parent and child, 
rather than **BaseClass** and **ChildClass**. 
Although I can do this, the code compiles,
IntelliJ is highlighting each of these calls.
I've discussed this previously, 
and IntelliJ is good at reminding us 
that calling a static method, 
using an instance reference, isn't a great idea. 
I'm going to leave the code this way, and run it anyway.

```html  
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
--------------------
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
--------------------
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
```

Because these methods are only on **BaseClass** right now, 
all three segments in the output are the same. 
The last three segments in the output are the results 
from executing those static methods, 
using the variable references. 
Because these static methods are declared only 
on **BaseClass** right now, 
all three segments have the same output. 
What I'll do next is open up the **BaseClass**, 
and copy the entire public static method, 
called _recommendedStatic_, and going back to the **ChildClass**, 
I'll paste that at the end of the class.

```java  
public static void recommendedStatic() {

    System.out.println("[Child.recommendedStatic] BEST Way to Do it");
    optionalStatic();
    //mandatoryStatic();
}
```

I'll change **BaseClass** to **Child** in the printed statement. 
The _mandatoryStatic_ method is a private static method on the **BaseClass**,
which means I can't call it from this subclass, 
or any class for that matter, 
so I'll comment out the last statement, which invokes it. 
Ok, so what am I really doing here? 
I've created a static method with 
the same signature of a static method, on the **BaseClass**. 
If you guessed overriding, you'd be in good company, 
but this is really something else. 
This is called **hiding a method**. 
First, you might have noticed that static methods 
won't show up in IntelliJ's override method help. 
The behavior of a **hidden static method** is different 
from the behavior you might expect when overriding a method. 
I'll run this, and then I want to compare the first three 
segments of output, with the last 3.

```html  
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedMethod]: Best Way to Do it
[Child:optionalMethod] EXTRA Stuff Here
[BaseClass.optionalMethod]: Customize Optional Method
[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!
--------------------
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
--------------------
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
--------------------
[Child.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
```

First, you can see, in the second and third segments, 
the text, _child, extra stuff here_. 
This tells me the overridden method, the method on child, 
was executed for both of my child class instances, 
so the one I called child, and the one I called _childReferredToAsBase_. 
It doesn't matter what the reference type of the variable is. 
In other words, _childReferredToAsBase_, a **BaseClass** variable, 
exhibited the same behavior as child, a **ChildClass** variable. 
Compare that to the results of the static method being called, 
on the same three instances. 
Only the output from the last call is different. 
This is the output of the _child_ instance, 
assigned to a **Child** variable. 
Only this variable executed the static method declared on **Child**. 
Why didn't the other child instance variable call 
that method declared on the **Child** class? 

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image01.png?raw=true)

I have a **Base** class, with one static method, called _doClassMethod_. 
I also have one instance method, called _doObjectMethod_. 
The **child** class has its own copies of these methods, 
with the same signatures. 
The **child** class hides the base class's static method, _doClassMethod_. 
The **child** overrides the base class's instance method, _doObjectMethod_.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image02.png?raw=true)

Now, consider we have three declarations. 
_X_ is declared as a **BaseClass**, and refers to an instance of a **BaseClass**.
_Y_ is declared as a **BaseClass**, and is referring to an instance of a **Child** class. 
In this case, **BaseClass** is the reference type, 
and **Child** is the instance or runtime type. 
_Z_ is declared as a **Child**, and is referring to an instance of a **Child** class. 
When you execute a static method on an instance, 
the reference type determines which method is called, 
the one on **BaseClass**, or the one on **Child**. 
What this means is, even though _Y_ is really a child instance, 
it doesn't matter for static methods. 
Static methods are based on the reference, so in this example, 
_X_ and _Y_ would both execute the static method declared on the **Base** class. 
Instance methods are called, based on the runtime type of the instance, 
and not the declared type. 
This means that _Y_ and _Z_ execute the _doObjectMethod_ on **Child**, 
because they're both instances of the **Child** class.

**Recommendation**: Always use the type to invoke the static method

Best practice recommends always using the type reference 
when executing a static method. 
If you're hiding a static method on a parent class, 
make sure you understand what the implications are for doing this. 
If you stick to using a qualifier, the type reference, 
to execute the specific static method, 
you'll avoid the confusion shown in the previous paragraph. 
Ok, so now I want to go back to my code. 
Going main method,

```java  
System.out.println("--------------------");
BaseClass.recommendedStatic();
ChildClass.recommendedStatic();
```

Even though IntelliJ really wants me to change this code, 
I'll leave it the way it is.
The message that I get is, 
_static member is accessed via instance reference_, 
and this behavior, as I just mentioned, isn't recommended. 
How should I call this code? 
Well, I can simply use the class types. 
I'll output another dashed line first. 
I'll simply call the _recommendedStatic_ method on the **BaseClass**. 
I'll also do the same with that method on **ChildClass**. 
And running that:

```html  
--------------------
[BaseClass.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
[BaseClass.mandatoryStatic]: MANDATORY
[Child.recommendedStatic] BEST Way to Do it
[BaseClass.optionalStatic]: Optional
```
                    
You can see each method was called, on the qualifying type, 
with a lot less confusion this time. 
You can also make a static method _final_. 
Let's do this on the **BaseClass**, 
to the _recommendedStatic_ method.
Remember this is the method that the **ChildClass** is hiding. 
I'll include the _final_ keyword on the declaration of 
that method on **BaseClass**. 
Now I'll go back to the **Child** class, and just like 
when I made an instance method _final_, 
I get an error on this static method. 
Making the static method _final_ on the **BaseClass** means 
subclasses can't hide that method.
If I look at that error on the **ChildClass**, 
it says _I can't override this method_. 
Why doesn't it say hide? 
Well, the IDE doesn't really know if I'm trying to override 
or hide this method. 
If I remove the static keyword, 
I get the exact same message, but for a completely different reason. 
This is because I can't create a method on a subclass, 
an instance method, when the parent's method is static. 
Let me revert that change, so that this method is static again, 
and I'll go back over to the **BaseClass**. 
IntelliJ is highlighting the _final_ keyword, 
and gives me a pretty uninformative message, 
_static method declared final_. 
This is not a compiler error, just a warning, and in fact, 
there could be valid reasons for making a static method _final_ sometimes, 
preventing subclasses from creating their own versions.
If you do see this warning though, use it as a reminder to think about 
other ways to design your classes. 
For example, maybe making the whole class _final_ is a better option, 
and I'll be covering _final_ classes in just a bit. 
For now, I'll remove the _final_ keyword, 
so that my **ChildClass** can hide this method. 
Next, I want to revisit _final_ variables. 
I discussed these a little bit when I talked about using _final_, 
or _effectively final_ variables, in lambda expressions.

```java  
private static void doXYZ(String x, int y) {

    final String c = x + y;
    System.out.println("c = " + c);
}
```

I'll start with declaring a _final_ local variable in a block of code. 
To demonstrate this, I'll create a new method on the **Main** class, 
private static void, _doXYZ_. 
It'll have two parameters, a string I'll call _x_, and an int I'll call _y_. 
I'll use the _final_ keyword, before my local variable declaration, 
so final String _c_, and I'll assign _x + y_ to that. 
And I'll print out _c_. 
I'll call this from the main method.

```java  
String xArgument = "This is all I've got to say about Section ";
doXYZ(xArgument, 12);
```

I'll set up a local variable, _xArgument_, setting that to the text. 
_This is all I've got to say about Section_.
I'll pass that variable, and the integer 16, 
to the _doXYZ_ method. 
Ok, so running this code:

```html  
c = This is all I've got to say about Section 12
```

There are not really any surprises there. 
It prints _This is all I've got to say about Section 12_. 
This code would work the same, 
whether I declared this variable _final_ or not, 
and that's because the local variable _c_ is _effectively final_. 
That just means I'm not assigning a different value to _c_, 
after the initial assignment.
If I add code to assign _c_ to _x_, for example, 
as the last statement in this method. 
You can see this is a problem, and I get the error, 
**cannot assign a value to final variable _c_**. 
I'll revert to that last change, removing that statement. 
You might be wondering why you'd want to use the _final_ keyword, 
when the compiler can figure out that it's _effectively final_? 
Well, maybe you plan to use the variable in lambda expressions. 
By specifying _final_, you're informing future developers 
that this variable needs to remain unchanged. 
In addition to local variables, I can make method parameters _final_ as well. 
Let me do that next.

```java  
private static void doXYZ(String x, int y) {

    final String c = x + y;
    System.out.println("c = " + c);
    x = c;
}
```

Now, I'm going to assign a new value to the variable _x_. 
I'll assign _c_ to _x_ here. 
Again, I can't do this if the method parameter is declared as _final_. 
I'll remove the final keyword from the method parameter. 
The truth is, declaring immutable type parameters _final_, 
like I just did here, isn't going to buy much. 
Let me show you why.

```java  
String xArgument = "This is all I've got to say about Section ";
//doXYZ(xArgument, 16);

System.out.println("After Method, xArgument: " + xArgument);
```

I'll print out the value of the _xArgument_ variable, 
after the call to this method. 
Ok, so if I run this:

```html  
After Method, xArgument: This is all I've got to say about Section
```

You can see that the value in _xArgument_ didn't change. 
Its value is the same before, and after the method call. 
In the method, I reassigned the value of the method argument _x_, 
but it had no effect outside of this method. 
This reference, _x_ will go out of scope when the method ends. 
Let's see what happens if I do something similar, 
with a mutable object I've shown you this before, 
but let me set this up.

```java  
private static void doXYZ(String x, int y, StringBuilder z) {

    final String c = x + y;
    System.out.println("c = " + c);
    x = c;
    z.append(y);
    z = new StringBuilder("This is a new reference");
}
```

First, I'll add a parameter to the _doXYZ_ method, 
**StringBuilder** I'll call _z_. 
I'll add a statement at the end of this method, that appends _y_, 
the integer, to the string builder argument. 

```java  
StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
doXYZ(xArgument, 12, zArgument);
System.out.println("After Method, xArgument: " + xArgument);
System.out.println("After Method, zArgument: " + zArgument);
```

In the main method, I'll set up a **StringBuilder**,
calling it _zArgument_, initializing that to the text, 
only saying this Section. 
I'll pass the _zArgument_ to my method. 
I'll then print the value after the method. 
If I run this:

```html  
After Method, zArgument: Only saying this: Section 12
```
                    
The output says that _after method, zArgument:  
Only saying this: Section 12_. 
Before the method, it did not include 12. 
This means the method had a side effect on my **StringBuilder** variable. 
After the method finishes execution, my _zArgument_ is different. 
What would happen if I made that third parameter _final_? 
Let me do that, I'll put final before the **StringBuilder** type. 

```java  
private static void doXYZ(String x, int y, final StringBuilder z) {

    final String c = x + y;
    System.out.println("c = " + c);
    x = c;
    z.append(y);
    //z = new StringBuilder("This is a new reference");
}
```

I don't have any compiler errors. 
Maybe you expected to see one on that last statement in this method.
If I run this code, we can see; nothing's changed I get the exact same result. 
There are still side effects, and my _zArgument_ value was changed. 
What good is _final_ then when it's used with a method parameter?
First, I'll comment out that last line. 

```java  
//z = new StringBuilder("This is a new reference");
```

Instead of using the append method on the _zArgument_, 
I'll reassign it to a new **StringBuilder** instance, with different text. 
Now I've got a compiler error. 
Like before, I can't assign a new variable, meaning I can't reassign it, 
to the method parameter _z_. 
I'll comment out that last line, 
and I'll uncomment the line above that, 
leaving this method with side effects. 
Using _final_ with a method parameter isn't going to change 
whether a method has side effects or not. 
It will simply produce a compiler error 
if you try to reassign a method parameter's value. 
This is an important distinction. 
It's important to understand when you use _final_ 
it doesn't mean the variable is immutable at that point. 
It means you can't assign or reassign a new instance,
or variable or expression to it, after the initialization. 
If you use _final_ for a local variable in a code block, 
you can only initialize it fully, or assign it a value, just once. 
Any other additional assignments will result in a compiler error. 
If you use final for method parameters, 
this means you cannot assign any values 
to the method parameters in the code.
Remember that method arguments get initialized implicitly 
when the method is invoked. 
I'll cover _final_ fields and _final_ classes, 
when I cover topics more specific to designing immutable classes. 
Now, let's talk about why change isn't always a good thing.

We have made an example of a mutating method, a method with side effects. 
I passed a **StringBuilder**object, a mutable object, and the method changed its value. 
Before I try another example, for the sake of completeness, 
I wanted to mention that the warning on the parameter _y_, for this method, 
is not related to our discussion about the _final_ keyword. 
If I hover over _y_ now, you can see the warning is 
about _y's value never changing_. 
That's because we call the _doXYZ_ method once only, with a literal value of 12. 
Right, let's try another example. 
I'll create a **Logger** class to emulate a class from an external library.

```java  
public class Logger {
    public static void logToConsole(CharSequence message) {

        LocalDateTime dt = LocalDateTime.now();
        System.out.printf("%1$tD %1$tT : %2$s%n", dt, message);
        if (message instanceof StringBuilder sb) {
            sb.setLength(0);
        }

    }
}
```

I want a method on this class. 
This is going to be public, static, and void, called _logToConsole_, 
and take a **CharSequence** called _message_. 
This means it can take either a **String** or **StringBuilder** argument. 
I'll use the **LocalDateTime** class, which is like **LocalDate**, 
but it maintains information about both date and time, not just date alone. 
It also has a now method to get the current date and time. 
I'll print a formatted string, using this date time field and the _message_, 
the method argument. 
Let me pause here to show you a useful page on formatting date and time, 
and where to get information about more format specifiers.

**R**: Time formatted for the 24-hour clock as `%tH:%tM`.  
**T**: Time formatted for the 24-hour clock as `%tH:%tM:%tS`.  
**r**: Time formatted for the 12-hour clock as `%tI:%tM:%tS %Tp`. 
The location of the morning or afternoon marker (`%Tp`)  
**D**: Date formatted as `%tm/%td/%ty`.  
**F**: ISO 8601 complete date formatted as `%tY-%tm-%td`.  
**c**: Date and time formatted as `%ta %tb %td %tT %tZ %tY`, e.g. `Sun Jul 20 16:17:00 EDT 1969`.  

There are many ways to format date and time. 
A couple of standardized ones are shown here. 
These apply to the formatted method on **String**, as well as the _printf_ method. 
The specifier for any date or time data is `%t`. 
That is followed by any one of these conversion characters, 
and you can see there are many. 
Capital _D_ and Capital _T_ are also easy to remember, 
so I'm going to use these. 
Capital _T_ gives you the time formatted for the 24-hour clock, 
in the format of the hour colon, the minute colon, and the second. 
Capital _D_ gives you the Date as month slash day slash year.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image03.png?raw=true)

This explains the code I'm using in a bit more detail. 
It's common when using date time conversions, 
to use the argument index feature, which is called _Explicit Indexing_. 
This lets you list your date time variable just once as an argument. 
You then use an index to tell the formatter, 
which argument is used for which specifier. 
If you do use an argument index, you need to use it for all specifiers,
which I'm showing on this example, using 2 for the message, String argument. 
I thought it was important to review the format specifiers a bit, 
especially for date time. 
This seemed like a good place to do it, 
even though it's a bit off the topic. 
Let's get back to the code, and back on the topic.

```java  
LocalDateTime dt = LocalDateTime.now();
System.out.printf("%1$tD %1$tT : %2$s%n", dt, message);
if (message instanceof StringBuilder sb) {
    sb.setLength(0);
}
```

I'll add one more bit of code to this method. 
I'll next check instance of operator to see if it's a string builder.
If it is, it gets assigned to the variable, _sb_. 
Next, I'll call set length on this, passing _0_. 
This truncates the data in that stringBuilder. 
Ok, so wait; why would a logging statement do this? 
Well, hopefully it never would. 
The point is, unless you examine every line of code, 
you don't really know if a method you're using
might have side effects. 
I'll now call this from my main method.

```java  
StringBuilder tracker = new StringBuilder("Step 1 is abc");
Logger.logToConsole(tracker);
tracker.append(", Step 2 is xyz.");
Logger.logToConsole(tracker);
System.out.println("After logging, tracker = " + tracker);
```

First, I'll create a new **StringBuilder** variable, _tracker_, 
and assign that a new instance, constructing it with the text, 
_step 1 is abc_. 
I'll call _logToConsole_, on **Logger**, passing it _tracker_. 
I'll continue processing, appending data to the _tracker_ variable. 
I'll execute _logToConsole_ again. 
Then I'll print the text, after logging, and print out _tracker_. 
If you were just reading this code, you might guess 
that you'd get _Step 1 is abc_, a comma, 
then _Step 2 is xyz_, printed out, after logging messages. 
Let me run this:

```html  
12/03/23 22:20:16 : Step 1 is abc
12/03/23 22:20:16 : , Step 2 is xyz.
After logging, tracker =
```

There you can see the logged statements, 
with date and time in common formats, and after that, the message. 
After _logging_, _tracker_ variable is empty. 
That could be a pretty confusing result. 
The _logToConsole_ method has a pretty nasty side effect. 
It clears my **StringBuilder** instance, 
after each log statement is output. 
Are you really going to have 
to worry about every library class you use? 
Probably not, but there are some steps you can take, 
which is called defensive coding, to minimize risk. 
One simple thing I can do with this code is pass 
a string to the log message, and not the reference. 
Let me change how I invoke those methods, 
and instead of passing tracker, I'll pass _tracker.toString_. 

```java  
StringBuilder tracker = new StringBuilder("Step 1 is abc");
Logger.logToConsole(tracker.toString());
tracker.append(", Step 2 is xyz.");
Logger.logToConsole(tracker.toString());
System.out.println("After logging, tracker = " + tracker);
```

Rerunning this code now:

```html  
12/03/23 22:20:16 : Step 1 is abc
12/03/23 22:20:16 : Step 1 is abc, Step 2 is xyz.
After logging, tracker = Step 1 is abc, Step 2 is xyz.
```
                    
My results make a lot more sense. 
After logging, tracker equals _Step 1 is abc, Step 2 is xyz_, 
so this code had no unintended consequences, or side effects, 
from calling logToConsole. 
It's straightforward to forget that method arguments are copies of references. 
This is especially easy to overlook when you're dealing with collections and arrays. 
I'll be talking about making defensive copies in an upcoming section, 
but right now, let's explore another problem caused by mutable objects. 
I'll create a new class and call it MainMailer:

```java  
public class MainMailer {

    public static void main(String[] args) {
        String[] names = {"Ann Jones", "Ann Jones Ph.D.", "Bob Jones M.D.",
                "Carol Jones", "Ed Green Ph.D.", "Ed Green M.D.", "Ed Black"};
    }
}
```

And I'll add a _main_ method with the `pvsm` shortcut. 
In this class, I'll have a list containing duplicate names,
then use a map to keep track of the counts for each distinct name. 
I'll also write some code to standardize names, 
which would be called prior to mailing,
to clean up names before they get printed on an envelope. 
I'll start with an array of names, an array of Strings. 
I'll include two versions of Ann Jones, one with,
and one without the PhD suffix, and I'll have Bob Jones, MD. 
Next a simple Carol Jones, and then two Ed Greens, 
one's a PhD and one's an MD, and finally Ed Black. 
I'll set up a private static method, 
that creates a random list of these names.

```java  
private static List<StringBuilder> getNames(String[] names) {

    List<StringBuilder> list = new ArrayList<>();
    int index = 3;
    for (String name : names) {
        for (int i = 0; i < index; i++) {
            list.add(new StringBuilder(name));
        }
        index++;
    }
    return list;
}
```

It'll return a List of **StringBuilder** objects, 
I'll call it _getNames_, and it takes an array of strings 
I'll set up a local variable, this is the _list_ I'll return,
and I want it to be an Arraylist. 
I'll use an index to determine how many names to add to my _list_ 
for each distinct name. 
I'll return the _list_ from this method.
In this loop, I'm going to add a number of the same names to my _list_, 
determined by the value in my index variable. 
I could just add five of each, for example, 
but this will make the output a bit more interesting. 
A nested loop will loop from 0 to the current index value. 
I'll add a new **StringBuilder** instance to the _list_, 
using the name to construct it. 
I'll increment the index after each name, 
so that I get a different number of names for each name in the array. 
Getting back to the main method:

```java  
List<StringBuilder> population = getNames(names);
Map<StringBuilder, Integer> counts = new TreeMap<>();
population.forEach(s -> {
    counts.merge(s, 1, Integer::sum);
});
System.out.println(counts);
```

I'll first create a local variable, _population_, 
again a list of _StringBuilder_. 
I'll assign that the result of calling my _getNames_ method, 
passing it the _names_ array. 
That should give me a long list of names, 
with a lot of duplicates.
Next, I'll set up a map of the counts of duplicate names. 
The key will be a _StringBuilder_, 
and the value's going to be an integer. 
I'll call this variable counts, 
and set it to a new instance of a tree map, 
so this map will be **SORTED**.
To populate the map, I'll loop through _population_. 
Using map's merge method lets me 
add a new name with a value of 1 if it's a name not yet in the map, 
or increment the value already in the map if it is there. 
After this, I'll just print my map. 
If I run that:

```html  
{Ann Jones=3, Ann Jones Ph.D.=4, Bob Jones M.D.=5, Carol Jones=6, Ed Black=9, Ed Green M.D.=8, Ed Green Ph.D.=7}
```
                    
I get counts for each distinct name. 
Remember the suffixes are included in the name, 
and that makes them unique. 
My population has 3 Ann Jones, but 4 Ann Jones that have PhD's, 
so some pretty smart Ann Jones. 
You can see each name has a different count 
because of the way I set up my population list. 
Just to confirm, I'll print out the number of PhD Ann Jones 
I have on the map.

```java  
StringBuilder annJonesPhd = new StringBuilder("Ann Jones Ph.D.");
System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);
```

First, I'll set up a local string builder, setting that to Ann Jones Ph.D. 
I'll use the get method to get the count of how many ann Jones with PhD  
there are in my population. 
Running that:

```html  
There are 4 records for Ann Jones Ph.D.
```

I can see that I get four records for Ann Jones PhD, so that's good. 
Ok, now let's say it's our job, to mail a flyer to this population. 
It's our company's policy to remove suffixes, 
before printing the name on the envelope, for privacy purposes, 
so I'll create a method called standardize names.

```java  
private static List<StringBuilder> standardizeNames(List<StringBuilder> list) {

    List<StringBuilder> newList = new ArrayList<>();
    for (var name : list) {
        for (String suffix : new String[]{"Ph.D.", "M.D."}) {
            int startIndex = -1;
            if ((startIndex = name.indexOf(suffix)) > -1) {
                name.replace(startIndex - 1, startIndex + suffix.length(), "");
            }
        }
        newList.add(name);
    }
    return newList;
}
```

I'll make it private static, it returns a list, and takes a _list_. 
I'll set up a new ArrayList. 
I'll loop through the list, the method argument. 
I'll add code here in just a minute. 
I'll return my new list of standardized names back.
I want to loop through a list of possible suffixes.
Right now I only have a PhD and MD, but you can imagine others could be added. 
I need a local variable to hold an index. 
I'll check if the suffix is in the name, and pass that index back.
If it's greater than - 1, there's a matching suffix, 
and I want to strip that out. 
I'll replace the suffix with an empty string. 
And I'll add the cleaned-up name to my new list.
I'll call this from the main method, and print out the cleaned names.

```java  
List<StringBuilder> cleanedNames = standardizeNames(population);
System.out.println(cleanedNames);
```

Running this,

```html  
[Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Ann Jones, Bob Jones, Bob Jones, Bob Jones, Bob Jones, Bob Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Carol Jones, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Green, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black, Ed Black]
```
        
You can see I have a long list of names, 
and no suffixes on any of them. 
If I was working with real mailing data, 
I'd have an address somewhere, and I'd address the envelope 
with this simplified name, and the address. 
Let's say I've successfully mailed my population, 
and now I want to target all Ann Jones PhD records again, 
for some reason. 
In fact, I'm just going to copy that statement
and paste it below.

```java  
System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);
```

If I run

```html  
There are null records for Ann Jones Ph.D.
```
                
That, I would expect to get the same amount I got 
before I standardized the names, but I'm getting null back. 
I'll print out my counts map next.

```java  
System.out.println(counts);
```

Running that,

```html  
{Ann Jones=3, Ann Jones=4, Bob Jones=5, Carol Jones=6, Ed Black=9, Ed Green=8, Ed Green=7}
```
                    
I see something very weird. 
My tree map's names have all lost their suffixes. 
Not only that, it looks like I have duplicate keys in my tree map. 
There are two entries for Ann Jones, each with a different count, 
and also Ed Green is showing the same thing. 
That's ugly. 
What will I get if I try to look up just Ann Jones?

```java  
StringBuilder annJones = new StringBuilder("Ann Jones");
System.out.println("There are " + counts.get(annJones) + " records for " + annJones);
```

I'll create a new String builder variable, called ann jones, 
and assign that a new instance with just the text ann jones. 
I'll print out the same message as before, 
but use this new variable to get the counts from the treemap. 
Running that

```html  
{Ann Jones=3, Ann Jones=4, Bob Jones=5, Carol Jones=6, Ed Black=9, Ed Green=8, Ed Green=7}
There are 4 records for Ann Jones
```
                
You can see I do get _counts_, 
but it's the _counts_ for only 
one of the Ann Jones entries. 
This map is really messed up. 
Let me try something else. 
I'll loop through the map entries and see what I get.

```java  
System.out.println("-----------------------");
counts.forEach((k, v) -> System.out.println(k + " : " + v));
```

I'll print a separator line. 
I'll call for each on counts, and remember that means 
I have key and value as the arguments, and I'll print those. 
If I run that:

```html  
-----------------------
Ann Jones : 3
Ann Jones : 4
Bob Jones : 5
Carol Jones : 6
Ed Black : 9
Ed Green : 8
Ed Green : 7
```
                    
I do get each entry, each key, with individual counts.
Now, let me loop through the key set instead.

```java  
System.out.println("-----------------------");
counts.keySet().forEach(k -> System.out.println(k + " : " + counts.get(k)));
```

I'll print another separator line. 
I can use keySet to loop through my map's keys. 
I'll print k, the key, then use it to get counts for that key. 
Now running that:

```html  
-----------------------
Ann Jones : 3
Ann Jones : 4
Bob Jones : 5
Carol Jones : 6
Ed Black : 9
Ed Green : 8
Ed Green : 7
-----------------------
Ann Jones : 4
Ann Jones : 4
Bob Jones : 5
Carol Jones : 6
Ed Black : 9
Ed Green : 8
Ed Green : 8
```
                    
I see that I get two different results 
I get different keys and values 
when I loop through the map's entries, 
comparing that to when I looped through the keys, 
and use that key to get the values. 
That's not good. 
The standardized Names method, 
which seemed harmless enough, 
has produced a very ugly side effect on my map of StringBuilders. 
It didn't matter what collection my StringBuilders were in. 
They were all referring to the same group of instances in memory.
A change to one variable in any collection will change that instance in memory. 
If that instance's a key to a mapped collection, you get into this ugly situation. 
This is why you should use an immutable object for keys in a map, 
so that this never happens. 
Ok, so these are two examples of side effects, 
and the dangers that are possible if you're using mutable objects 
but not programming defensively. 
The good news is that there are strategies for managing change, 
so you can reduce or eliminate side effects.

Java provides mechanisms to control changes and extensibility of your code, 
at many different levels. 
You can prevent:

* Changes to data in Instance fields, 
which is called the state of the object, 
by not allowing clients or subclasses to have access to these fields.
* Changes to methods by not allowing code to override or hide existing functionality.
* Your classes from being extended. 
You can also prevent Instantiation of your classes.

I'll be reviewing each of these, in turn, over the next couple of sections.
</div>

## [b. Immutable Classes]()
<div align="justify">

So far, I've covered several examples of 
how using immutable objects can leave you open 
to unintended side effects. 
In this section, I'll show you one way to minimize these, 
the immutable Object.

* An immutable object doesn't change state once it's created.
* An immutable object is a secure object, 
meaning calling code can't maliciously or mistakenly alter it.
* An immutable object simplifies concurrency design, 
which we'll cover later.

Here we can describe the strategies of creating a class 
that when used, produces immutable objects.

* Make instance fields private and final.
* Do not define any setter methods.
* Create defensive copies in any getters.
* Use a constructor or factory method to set data, 
making copies of mutable reference data.
* Mark the class final, or make all constructors private.

I'll walk through the first four of these strategies in code. 
I'll discuss final classes and private constructors in upcoming sections. 
I've created the **Main** class. 
Let's imagine that we're creating a genealogy program, 
and we're tracking people, names, birthdays and kids. 
I'll start out with a **Person** class.

```java  
public class Person {

    private String name;
    private String dob;
    private Person[] kids;

    @Override
    public String toString() {

        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + dob + ", kids = " + kidString;
    }
}
```

I'll add some fields, using basic encapsulation techniques. 
For this exercise, I'm just going to have _name_, 
date of birth, _dob_, and I'll just make that a string. 
And I'll have a field, _kids_, an array of my person class. 
I'll generate both getters and setters for all the fields. 
I'll also generate a _toString_ method, but select none for the fields.
I'll add some code to print out the person data. 
First, I want to deal with printing out the _kids_ information.
If there are no kids, I'll print `n/a`, for not applicable. 
If there are kids, I'll set up a _names_ array, 
because I only want to print a list of the kids names. 
I'll return name, date of birth and the kid string. 
Next, I'll add the code to populate the _names_ array, 
and build the kid string. 
I'll use the _setAll_ method on the **Arrays** helper class, 
to populate my _names_ array. 
The parameter for the lambda is an integer, an _index_. 
I'll first use that to get the child from the kids array. 
That could be _null_, so I'll use a ternary to check for that, 
and set the name to an empty String, otherwise I'll set it to the kid's name. 
I'll use the _join_ method on **String**, joining all the kids names by a comma. 
Ok, so this is a typical class, pretty well encapsulated. 
The only way to access the data is with getters and setters. 
I didn't even set up a constructor. 
I'll create a couple of persons for my genealogy database, 
in the _main_ method.

```java  
Person jane = new Person();
jane.setName("Jane");
Person jim = new Person();
jim.setName("Jim");
Person joe = new Person();
joe.setName("Joe");
Person john = new Person();
john.setName("John");
john.setDob("05/05/1900");
john.setKids(new Person[]{jane, jim, joe});
System.out.println(john);
```

Since I didn't declare any constructors, 
the only one I can use is the default no args constructor. 
Jane will be one of the kids. 
I'll just set her name for now. 
And I'll do the same for jim. 
And Joe. 
These will be the three kids of John. 
Finally, John, will be the father. 
I'll set John's data by using the setters, first name, and date of birth.
I'll use set Kids to pass an array of **Persons** with an array initializer, 
passing jane, jim and joe. 
Finally, I want to print John's data. 
This is a perfectly reasonable way to create a class, 
though it's somewhat tedious to create a new person. 
If I run this:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
```

I see that I have John, date of birth is May 5th, 1900, 
and his kids were Jane, Jim and Joe. 
The object, John, is definitely not immutable. 
I can use the setters to change any data on the object. 

```java  
john.setName("Jacob");
john.setKids(new Person[]{new Person(), new Person()});
System.out.println(john);
```

I'll change John's name to jacob, for example. 
I'll also use the set kids method, 
passing it two new instances of persons, 
constructed with no data. 
And I'll print john out. 
Running that:

```html  
Jacob, dob = 05/05/1900, kids = null, null
```

You can see that john is no longer named John, 
and his kids are now nameless, and there's only two of them. 
Again, depending on what the case for your genealogy application is, 
this may be a valid way to design your class. 
Lets at least create two constructors, 
to make the job of creating _persons_ a little easier. 
Back on the person class,

```java  
public Person(String name, String dob, Person[] kids) {
    this.name = name;
    this.dob = dob;
    this.kids = kids;
}

public Person(String name, String dob) {
    this(name, dob, null);
}
```

I'll generate the first with all three fields. 
The second constructor will just have name and date of birth, dob.
I want to remove those statements. 
Instead, I'll chain a call to the other constructor, 
and pass null as the _kids_ argument. 
Now let's say, I really don't want the code 
to change name or date of birth, 
after the _person_ object is constructed. 
To protect against this, I'll remove the setters for those two fields. 
First, I'll remove the _setName_ method, 
then I'll remove the _setdob_ method. 
Getting back to the _main_ method in **Main**,

```java  
Person jane = new Person("Jane", "01/01/1930");
Person jim = new Person("Jim", "02/02/1932");
Person joe = new Person("Joe", "03/03/1934");

Person[] johnsKids = {jane, jim, joe};
Person john = new Person("John", "05/05/1900", johnsKids);

System.out.println(john);
```

My code no longer compiles. 
I'm going to comment all this code out. 
I'll now create my _Persons_, 
using the new constructors. 
For the first instance, I'll pass Jane, 
and I'll include a birthdate, Jan. 1, 1930. 
For Jim, maybe he's two years younger than Jane, so Feb. 2, 1932. 
Next, Joe, and let's use March 3, 1934. 
To set up John, I want to create an array of _persons_ for his kids. 
I'll set up an array variable, _johnsKids_, 
using an initializer with jane, jim and joe. 
I'll create John, with the constructor that takes three arguments, 
John for name, birthday 5.5.1900, and _johnsKids_. 
I'll print John out. 
If I run that code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, doe
```

I get the same as before. 
The code is a little more succinct, and easier to read. 
I can't change the name or date of birth, 
but I can still set the kids. 
Let me try that.

```java  
john.setKids(new Person[]{new Person("Ann", "04/04/1930")});
System.out.println(john);
```

I'll set the kids to a new in place array, initialized to a new Person, 
Ann, born 4.4.1930. 
And I'll print John again. 
Running that:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, doe
John, dob = 05/05/1900, kids = Ann
```

You can see John's kids have been completely changed. 
Let's adjust the kids a bit more.

```java  
Person[] kids = john.getKids();
kids[0] = jim;
System.out.println(john);
```

First, I'll set up a local variable, 
and assign it the result of calling getKids in **Person**. 
I'll set this first kid to my jim instance, 
and print John's information. 
Running that:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, doe
John, dob = 05/05/1900, kids = Ann
John, dob = 05/05/1900, kids = Jim
```

John's kid has changed from Ann to Jim. 
This could be a problem. 
You might not expect your client to change the kids data, 
outside of Person's operations, to do it.

```java  
kids = null;
System.out.println(john);
```

What if I assign my local variable, kids, to null? 
Does that have any effect on John's kids? 
Running this code has no effect on Jim's kids, so that's a good thing. 
This means reassigning the reference, or setting it to null,
from the client or calling code, doesn't change kids.

```java  
john.setKids(kids);
System.out.println(john);
```

I'll call set kids, and pass the kids variable, 
which we know is null. 
And print John again. 
Running this code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, doe
John, dob = 05/05/1900, kids = Ann
John, dob = 05/05/1900, kids = Jim
John, dob = 05/05/1900, kids = Jim
John, dob = 05/05/1900, kids = n/a
```

You can see I've managed to remove all John's kids I could only do it, 
by calling the method on Person, 
so that's at least some control going back to the **Person** class. 
This is a very common kind of class, 
where some parts of the class are mutable. 
Next, I'll create a **Person** record, in comparison.

```java  
public record PersonRecord(String name, String dob, PersonRecord[] kids) {

    public PersonRecord(String name, String dob) {
        this(name, dob, new PersonRecord[20]);
    }

    @Override
    public String toString() {

        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + dob + ", kids = " + kidString;
    }
}
```

A record solves a lot of the problems 
for designing an immutable object, but not all.
My record, **PersonRecord**, will have the same three fields 
as the **Person** class, _name_, _dob_, and _kids_, but for the _kids_, 
the type will be **Person** record. 
We want a constructor for just name and date of birth. 
I'll generate a constructor using the standard IntelliJ tools, 
but watch what happens when I generate a constructor in a Record. 
We get these three options, Compact Constructor, Canonical Constructor, and Custom. 
We're going to ignore the first two options, and select Custom here, 
which will lead us to the standard dialog you normally see. 
Later in this section, I'll discuss the first two options. 
Instead of passing a zero element array, I'll change 0 to 20. 
This means I have a placeholder for 20 possible kids. 
Now, I'm going to copy the _toString_ method I had on **Person**,
and paste it here, on this record. 
Ok, so that's all I need to do right now. 
I'll create a new class called **MainRecord**, and add a _main_ method.

```java  
public class MainRecord {

    public static void main(String[] args) {

        PersonRecord jane = new PersonRecord("Jane", "01/01/1930");
        PersonRecord jim = new PersonRecord("Jim", "02/02/1932");
        PersonRecord joe = new PersonRecord("Joe", "03/03/1934");

        PersonRecord[] johnsKids = {jane, jim, joe};
        PersonRecord john = new PersonRecord("John", "05/05/1900", johnsKids);

        System.out.println(john);
    }
}
```

I want to copy the code in the **Main** class, _main_ method, 
and paste it in **Main** record. 
I want to replace all references to **Person** with _PersonRecord_. 
Running this code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
```
                
I get the same output I did originally when using the **Person** class.

```java  
PersonRecord johnCopy = new PersonRecord("John", "05/05/1900");
System.out.println(johnCopy);
```

I'll create another john variable, _johnCopy_, 
and assign that a new _PersonRecord_, 
with the same name and date of birth as the first John, but no kids. 
Running that:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
```
                
You can see it looks a little weird 
because I'm printing 19 commas and no kids names, 
because of the way I have it coded. 
I initialized kids to be an array of 20-Person records, 
and each array element is initialized to a null reference. 
I can't set the kids on this record, but I can change them, 
which is why I set this up this way. 
Let me do that.

```java  
PersonRecord[] kids = johnCopy.kids();
kids[0] = jim;
kids[1] = new PersonRecord("Ann", "04/04/1936");
System.out.println(johnCopy);
```

I'll create a new local variable, a person record array called kids, 
and assign that johnCopy's kids. 
I'll set the first element to jim, and the second to a new person record, ann. 
I'll print John's copy. 
And now, running this code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
John, dob = 05/05/1900, kids = Jim, Ann, , , , , , , , , , , , , , , , , , ,
```

You can see I can set and change the kids in the kids array on my record, 
meaning these instances are mutable. 
For this reason, you can't assume, just because you're using a record, 
or setting up a record, that the record is immutable.
If the fields were all immutable types, yes, 
but if you're using arrays or collections, or mutable types, 
then you can't use a record, and prevent side effects, 
without implementing any defensive measures. 
A record satisfies several of the requirements for an immutable class design. 
It uses private final instance fields, it has a constructor to set the data, 
and it doesn't have any setters. 
What's missing is, it's not creating defensive copies. 
Let's add this to the **PersonRecord**.

```java  
@Override
public PersonRecord[] kids() {
    return kids == null ? null : Arrays.copyOf(kids, kids.length);
}
```

I'll add a getter, picking the _kids_ field. 
I'll replace return kids with a statement that first checks 
if the kids are null. 
If so it returns null, 
otherwise it will return a copy of the array, I'll use `Arrays.copyOf`, 
that takes an array, and the length of the array we want to copy. 
If I rerun my code in the **MainRecord** class,

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
```
                
You can see, whatever I'm doing in the client code, 
it's not changing the value of the kids array on _JohnCopy_. 
Now, you may feel like you've created a type that's immutable, 
but don't congratulate yourself just yet. 
Going back to **MainRecord** _main_ method,

```java  
johnsKids[0] = new PersonRecord("Ann", "04/04/1936");
System.out.println(john);
```

Consider this minor change, 
where I'll change my local variable johnsKids, 
element 0 to a new Person, say _Ann_, born April 4, 1936. 
This code happens long after I created John, with his kids. 
If I run this code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
John, dob = 05/05/1900, kids = , , , , , , , , , , , , , , , , , , , ,
John, dob = 05/05/1900, kids = Ann, Jim, Joe
```
                
You can see I'm able to change John's data, his kids array, 
by changing the array variable, I used to construct the record. 
This means I need a constructor that will create a copy of the array passed 
before I assign it. 
I won't do this here, for this record, but I wanted you to see 
there are several reasons why a record might not be truly immutable.
Of course, there are many cases where you want to support some mutability, 
but this should be designed not by accident.
If your class must be mutable, you should still use some of these techniques 
to minimize mutability!

So we looked at a pretty normal Joe first, with getters and setters, 
and a no argument constructor. 
I compared that to a record, which has a lot of built-in support 
for the design of immutable classes. 
A record, though, is still not perfect, because the getters, 
if not altered, will return references to mutable objects. 
In addition, I showed you how I can pass a reference to the constructor, 
and then change data, using that reference, afterward, 
producing possibly unwanted side effects. 
I'll now create a truly immutable class. 
You might still be asking; 
don't I want the ability to change the genealogy data? 
Maybe but let's say our use case is simply 
to print the data we get, or process the data 
for some statistical program. 
We're not in the business of writing a genealogy program, 
just a web page that will display the information or statistics. 
If the data's mutable, it might affect our results badly. 
I'm going to copy the **Person** class, the Joe, 
and create a copy from that class, named **PersonImmutable**.

```java  
public class PersonImmutable {

    private final String name;
    private final String dob;
    protected final PersonImmutable[] kids;
}
```

The first suggestion listed I've shown several times, 
the strategies for creating an immutable class, 
is to make all fields private, which they already are, 
and _final_, which I'll do next. 
I'll make all three of my private fields _final_. 
This causes a compiler error in the _setKids_ method. 
Once the kids field is initialized, it can't be reassigned,
and that's what it's trying to do in the set method here. 
I'll get rid of this setter method next, 
which is actually the second suggestion, 
to eliminate setters in your class. 
By making your fields final, the compiler can check 
if your methods do attempt to mutate these fields. 
The third recommendation is to make defensive copies 
in all of your getters. 
Does this make sense in getters that return immutable types? 
No, probably not, but I do have a getter 
that returns a reference to an array.

```java  
public PersonImmutable[] getKids() {
    return kids == null ? null : Arrays.copyOf(kids, kids.length);
}
```

I've already established, this is mutable, 
in both the **Person** and **PersonRecord** examples. 
Like I did in the person record example previously, 
I'll change this code. 
I'm going to return null if the array is null, 
or a copy of the array if it isn't. 
The next strategy is to use constructors to populate data, 
and this class already does that. 
I did show you, with the record example that even 
that might not be good enough. 
You'll want to make a defensive copy of the input if it's mutable, 
which prevents the client from maintaining a reference to it, 
and altering it later. 
I demonstrated that scenario with my _johnsKids_ array, 
that I passed to the constructor.
To prevent this, I'll do something similar in the constructor, 
to what I did in the getter.

```java  
public PersonImmutable(String name, String dob, PersonImmutable[] kids) {
    this.name = name;
    this.dob = dob;
    this.kids = kids == null ? null : Arrays.copyOf(kids, kids.length);
}

public PersonImmutable(String name, String dob) {
    this(name, dob, null);
}
```

I'll return the result of the same ternary operation 
I used in the getter, returning null if the argument is null,
or an array copy if not. 
Let's set up some of these. 
First, I'll add a new package, and call that **external**. 
I'm going to copy the **MainRecord** class, 
and paste that in this new package, **MainImmutable**.

```java  
class MainImmutable {

    public static void main(String[] args) {

        PersonImmutable jane = new PersonImmutable("Jane", "01/01/1930");
        PersonImmutable jim = new PersonImmutable("Jim", "02/02/1932");
        PersonImmutable joe = new PersonImmutable("Joe", "03/03/1934");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("John", "05/05/1900", johnsKids);

        System.out.println(john);

        PersonImmutable johnCopy = new PersonImmutable("John", "05/05/1900");
        System.out.println(johnCopy);


        PersonImmutable[] kids = johnCopy.kids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(johnCopy);


        johnsKids[0] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);
    }
}
```

I'll replace **PersonRecord** with **PersonImmutable**. 
I have one error here, where I'm referencing _johnCopy_ 
and trying to access kids, with the record accessor method. 

```java  
//PersonImmutable johnCopy = new PersonImmutable("John", "05/05/1900");
//System.out.println(johnCopy);

//PersonImmutable[] kids = johnCopy.kids();
PersonImmutable[] kids = john.getKids();
kids[0] = jim;
kids[1] = new PersonImmutable("Ann", "04/04/1936");
//System.out.println(johnCopy);
System.out.println(john);

johnsKids[0] = new PersonImmutable("Ann", "04/04/1936");
System.out.println(john);
```

I actually want to change _johnCopy_ to _john_ here, 
and _kids_ to _getKids_.
I'll just print john. 
In fact, I don't need _johnCopy_ for this exercise, 
so I'll remove the two statements, so it's less confusing. 
I used this copy because my array had been initialized to 20 
null records in the constructor, 
I could reassign any of those elements, 
but that's not applicable here. 
Running this code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = Jane, Jim, Joe
```

I need to make sure I'm running _main_ on this **MainImmutable** class, 
you can see that John's kids don't get changed by any of this code, 
once I initially constructed John with the kids, Joe, Jim and Jane. 
It didn't matter if I changed the variable _johnsKids_ afterward,
or if I assigned a local variable to the result of get kids, 
and made changes to that. 
Using defensive copies in the constructor, and in the getter, 
has protected my array of kids on this class, from side effects. 
Now, do I have an immutable class? 
Well, of course, the answer to that is always, it depends. 
Let's now say, I've handed this class off to someone else, 
who wants to build a subclass, 
let's say a **LivingPerson** class. 
First, this developer will make two changes 
to our **PersonImmutable** class, without consulting us.
One of the changes is to add a constructor, 
what's called a copy constructor, 
which takes as an argument, an object of the same type. 
Our new developer is going to make this a protected constructor, 
so that's a good thing, and he'll assign its fields 
to those values on the person instance, passed to this constructor. 
I'll set:

```java  
this.name = person.name
this.dob = person.dob
this.kids = person.kids
```

With this code, a subclass can easily construct a new person, 
using another person to do it, basically making a copy of the method argument. 
Now this developer wants to be able to access kids directly from the subclass, 
so he'll change the access modifier on kids to _protected_ from _private_.

```java  
private final PersonImmutable[] kids;
protected final PersonImmutable[] kids;
```
                                                
For good measure, I'll change the two string methods, 
and use the get method for _dob_, which will allow 
my subclasses to override it. 
There doesn't seem to be any real harm in these minor changes. 
In fact, if I try to create a new **PersonImmutable** instance, 
using this constructor from my **MainImmutable** class, I can't do it. 
Let me demonstrate that.

```java  
PersonImmutable johnCopy = new PersonImmutable(john);
```

I'll try to set up a new variable, _johnCopy_, 
and instantiate that using the new constructor, passing john to it.
I can't do this, since **MainImmutable** isn't in the same package, 
and it's not a subclass, nor will it be. 
I'll revert that last statement, removing it. 
Now, let's imagine this developer creates a subclass, 
called **LivingPerson**, and let's put this in a **domain** package.

```java  
public class LivingPerson extends PersonImmutable {
    
    public LivingPerson(String name, PersonImmutable[] kids) {
        super(name, null, kids == null ? new PersonImmutable[10] : Arrays.copyOf(kids, 10));
    }

    public LivingPerson(PersonImmutable person) {
        super(person);
    }

    @Override
    public String getDob() {
        return null;
    }
}
```

This class is going to extend **PersonImmutable**, 
but have special functionality for a **LivingPerson**. 
In fact, this class will hide _birthdate_ data for a living person. 
A date of birth is considered personally identifiable information,
and needs to be handled differently for living persons. 
Rather than have to deal with all these requirements, 
this class is going to make sure that _birthdate_ is null.
I'll generate two constructors, picking the one with all three arguments, 
and then the copy constructor, the one 
that takes person immutable as an argument. 
This gives me two constructors, that simply make calls to the super constructors, 
with the same set of arguments. 
I want to change the first constructor, first removing _dob_ as a parameter. 
Since I want the ability to add a new child on this class,
I'm going to change the _kids_ array a little bit.
The way I'll do this is, if the kids are _null_, 
I'll set kids to a new array of 10 persons. 
If the kids aren't _null_,
I'll copy the _kids_ into a 10-element array.
Next, I'll override the getter method, for date of birth. 
I'll replace the call to the super getter, 
and just return _null_ instead, 
I don't want to expose the date of birth, in other words. 
Getting back to the **MainImmutable** class,

```java  
LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
System.out.println(johnLiving);
```

I'll create a living person, well, 
I'll just say John is an extraordinary person, and he's still living. 
I'll create a variable called _johnLiving_ 
and assign that a new instance of a **LivingPerson**, 
passing John's names and kids to that. 
And I'll print _johnLiving_ out. 
If I run this:

```html  
John, dob = null, kids = Jane, Jim, Joe, , , , , , ,
```
                
The goal's been achieved, I have a living person, 
and I'm not including birthdate in the output. 
You can also see the extra commas there, 
which indicates there are null entries, waiting for new kids. 
Let's say I want to add a child to my living person as well, 
in the case of a new child being born or adopted.
I'll create a new method on **LivingPerson**:

```java  
public void addKid(PersonImmutable person) {
    for (int i = 0; i < kids.length; i++) {
        if (kids[i] == null) {
            kids[i] = person;
            break;
        }
    }
}
```

Public void, _addKid_, that takes a **PersonImmutable** type. 
I'll loop through the _kids_. 
As soon as I find a _null_ entry,
I'll assign that to the _person_ argument. 
And I'll break out of this. 
I'll test this out, getting back to the **MainImmutable** class.

```java  
LivingPerson anne = new LivingPerson("Ann", null);
johnLiving.addKid(anne);
System.out.println(johnLiving);
```

First, I'll set up a new **LivingPerson**, _Ann_, 
passing _null_ as her kids. 
Next, I'll call the _addKid_ method on _johnLiving_ 
and pass _anne_. 
And I'll print _johnLiving_ again. 
Running that:

```html  
John, dob = null, kids = Jane, Jim, Joe, Ann, , , , , ,
```
                
You can see the last statement, and it now includes _Ann_ 
as the fourth element. 
This code works as designed, and all is well. 
But is it really? 
Let's now say another developer comes along,
maybe these classes went out as a library or something. 
Anyway, this developer is going to create a new subclass, 
extending **PersonImmutable**. 
I'll create a new class, **PersonOfInterest** 
in a package called **hacker**.

```java  
public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }

    @Override
    public PersonImmutable[] getKids() {
        return super.kids;
    }
}
```

I'll have this class extend **PersonImmutable**. 
I'll insert a constructor, the copy constructor. 
I also want to override the _getKids_ method. 
I'll make one minor, but important change. 
I'm not going to return `super.getkids`.
Instead, because I'm a subclass and _kids_ is protected, 
I can access _kids_ directly from this subclass, 
so I'll just return kids, the field on super. 
This is all my subclass needs to get access to my data. 
Let me show you how, in the **MainImmutable** class.

```java  
PersonOfInterest johnCopy = new PersonOfInterest(john);
System.out.println(johnCopy);
```

First, I'll create a Person of Interest, calling that _johnCopy_, 
and assign that to a new instance of **PersonOfInterest**, 
and print that out. 
Running that:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
```

You can see the last statement, which has all the same data as _John_, 
the original, as you might expect.

```java  
kids = johnCopy.getKids();
kids[1] = anne;
System.out.println(johnCopy);
System.out.println(john);
```

Now I'll get the kids from this copy, 
and assign that to a variable I set up earlier, _kids_. 
I'll assign this variable's first element to be _anne_. 
I'll print the _johnCopy_ again. 
And for good measure, I'll print _john_ now too. 
Running that code:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = Jane, Ann, Joe
John, dob = 05/05/1900, kids = Jane, Ann, Joe
```

You can see, something bad has happened. 
_Ann_ is listed as the second child of both _johnCopy_ and _John_, 
in the last two output statements. 
This means that both **PersonImmutable** and **PersonOfInterest** are mutable. 
Maybe we don't care about **PersonOfInterest**, 
but for **PersonImmutable**, we spent some time trying to design it, 
so it wouldn't be. 
Our new developer made two pretty big mistakes. 
First, opening a field up for access, even for subclasses, 
needs some extra thought, especially when you're dealing with reference types 
to mutable data. 
What would have happened if the new copy constructor, 
simply chained to another constructor? 
Let's go change that, in the **PersonImmutable** class.

```java  
protected PersonImmutable(PersonImmutable person) {
    this.name = person.name;
    this.dob = person.dob;
    this.kids = person.kids;
}
```

I'll remove the three assignment statements.

```java  
protected PersonImmutable(PersonImmutable person) {
    this(person.getName(), person.getDob(), person.getKids());
}
```

I'll replace that by chaining to the constructor, 
using the getters on person to get the data from the method arguments.
Running the **MainImmutable** class again:

```html  
John, dob = 05/05/1900, kids = Jane, Jim, Joe
John, dob = 05/05/1900, kids = Jane, Ann, Joe
John, dob = 05/05/1900, kids = Jane, Jim, Joe
```
                    
You can see that change has protected _John_, 
the original John's kids, 
because of the defensive copy made in the constructor
that it was chained to. 
The code can still get a reference and 
change the **PersonOfInterest**'s kids. 
Another protection mechanism would be to make the method _getKids_ **final**, 
in the **PersonImmutable** class, 
so I'll do that. 
Going back to the **PersonOfInterest** class:

```java  
public final PersonImmutable[] getKids() {
    return kids == null ? null : Arrays.copyOf(kids, kids.length);
}
```

You can see that I have an error on this method, 
so I can't override it. 
I'll remove that method. 
It's true I could create another named method to return _super.kids_, 
but this method wouldn't be **polymorphic**, 
used by some other unsuspecting code. 
Later I'll be introducing you to sealed classes, 
which gives you more control over who can subclass our classes. 
But you should be aware that allowing your classes to be subclassed, 
and providing access to fields through the use of a protected modifier, 
can provide opportunities for unwanted side effects. 
What really is the definition of a defensive copy?
The next challenge will go into detail about 
the definition of a defensive copy.
</div>

## [c. Immutable Classes Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/Course05_ImmutableClassesChallenge/README.md#immutable-classes-challenge)
<div align="justify">

In this challenge, you should create a **BankAccount** class. 
This should have a type, indicating the type of account,
like **Checking** or **Savings** or some other type. 
It should have a balance, the initial dollar amount in the account. 
You should also create a **BankCustomer** that has a customer name, 
a customer id, and a List of accounts. 
You should use the techniques I discussed in the last section, 
to design these classes. 
Create a couple of instances of bank customers, 
confirming that you can't change a **Customer**'s data at all, 
after it's initialized. 
Create a subclass of the bank customer,
and confirm that the subclass can't tamper 
with the customer's data as well.

I'm going to use these two classes in challenges coming up, 
so make sure to either create your own classes, 
or walk through it with me. 
</div>

## [d. Defensive Copies]()
<div align="justify">

So far, I've shown you examples of returning defensive copies, of a list and an array,
from getter methods. 
I also made a defensive copy of an array, 
before I assigned it to my array field, in a constructor.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image04.png?raw=true)

This demonstration is using a defensive copy as input.
When you pass mutable types to an immutable object, 
a defensive copy should be made. 
The defensive copy should then be assigned 
to the instance field.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image05.png?raw=true)

This demonstration's using a defensive copy as output. 
When you retrieve data, you should first make a defensive copy, 
and pass the defensive copy back to the calling code. 
Making a copy sounds easy enough, but unfortunately, 
that too isn't always perfectly straightforward.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image06.png?raw=true)

This demonstration is showing you two very different copies of an array. 
A shallow copy only makes a copy of the structure, 
and not a copy of the elements in the structure.
A deep copy makes a copy of both the structure, 
and copies of each element in that structure.

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image07.png?raw=true)

When you use copy methods on interfaces and helper classes, 
the copy that's made will probably be a shallow copy.
A shallow copy of an array means a new array structure is created 
with the same number of indexed positions. 
Each indexed position is assigned the same value in the previous array, 
at that same position. 
A copy of the referenced element isn't made. 
You can see in the diagram above that both arrays have indexed references, 
pointing to the same set of instances in memory.

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image08.png?raw=true)

Deep copies usually have to be manually implemented if you need it. 
This slide demonstrates a deep copy of an array.
Each array element has been cloned for the array copy. 
Deep copies may need to be applied to arrays and collections,
as well as composite classes, to ensure immutability. 
It's not just arrays and collections you need to worry about.
A class can be composed of other classes, 
meaning its fields are instances of classes.

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image09.png?raw=true)

When you clone or copy this type of object, 
you may also need to copy or clone the class's more complex fields.
This diagram above shows a shallow copy of an instance of a composite class, 
comparing it to a deep copy.

![image10](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image10.png?raw=true)

Nesting of a composite class can be multi-leveled, 
as I show here with the JKL instance. 
_JKL_ is a field on **XYZ**, which is a field on our Object. 
The deep copy created new instances of **ABC**, and **XYZ**, 
the fields on the object, as well as a copy Object. 
It didn't, however, make a deep copy of **XYZ**, 
which is why _JKL_ is still referencing the same instance, 
in the original **XYZ**. 
Hopefully you can see that when we start making deep copies, 
we may need to recursively copy fields which contain other fields. 
Let's get back to code, and start looking at making copies, 
and different ways to do this. 
I've created the usual **Main** class and _main_ method.
First, I want to create a record, named **Person**, 
and right now I just want to have two fields, _name_ and _dob_, 
for date of birth, both **Strings**. 
I'll just put this record in the Main.java file.

```java  
record Person (String name, String dob, Person[] kids) {

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {
        Person joe = new Person("Joe", "01/01/1961");
        Person jim = new Person("Jim", "02/02/1962");
        Person jack = new Person("Jack", "03/03/1963");
        Person jane = new Person("Jane", "04/04/1964");
        Person jill = new Person("Jill", "05/05/1965");

        Person[] persons = {joe, jim, jack, jane, jill};
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References" + persons[i]);
            }
        }
    }
}
```

I'll create five instances of these persons. 
I'll start with the first, _Joe_, 
and make his birthday Jan 1, 1961.
I'm going to copy that and paste it four times. 
The first time, I'll change _joe_ to _jim_, 
both the variable name, 
and the name passed to the construction of the record, 
and I'll change the date to Feb. 2, 1962. 
The next one, I'll change to _jack_, 
and the date March 3, 1963. 
After that, I want _jane_, and birthdate April 4, 1964. 
Finally, _jill_, birthday, May 5, 1965. 
Now I want an array of these persons, 
and I'll just use an array initializer, 
with my five-person instances above. 
I'll now create a copy, a shallow copy of my _Persons_ array. 
This should be familiar to you 
if you followed the last section. 
The **Person** record has two fields, both **Strings**. 
Here, I'm making a shallow copy. 
A deep copy of the record would be unnecessary since 
there's no way to mutate these person records. 
To confirm that the two arrays are referencing the same records, 
I'll set up a for loop. 
I'll loop through the five elements. 
I'll compare the element in the person array, 
with the element in _personsCopy_, at the same indexed position. 
I'll use `==`, the equality operator, 
which checks whether the references are equal, 
and not whether the values are equal. 
If the references are equal, I'll print that out. 
If I run that code:

```html  
Equal References Person[name = Joe, dob=01/01/1961]
Equal References Person[name = Jim, dob=01/01/1962]
Equal References Person[name = Jack, dob=01/01/1963]
Equal References Person[name = Jane, dob=01/01/1964]
Equal References Person[name = Jill, dob=01/01/1965]
```

You can see that both arrays are referencing 
the same object for all five elements. 
Now, I'm going to change my **Person** record.

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
Person[] personsCopy = Arrays.copyOf(persons, persons.length);

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}
```

Like before, I'll include an additional field, 
_kids_, an array of **Person**. 
I'll also generate a _toString_ method, 
and I only want to include _name_ and _kids_ in that. 
Next, in the _main_ method, I need to include _kids_, 
for each of my _person_ records. 
First, I'll include nulls for _joe_, _jim_, and _jane_. 
Although the _birthdates_ are wrong, 
I'll make _joe_ and _jim_ the children of both _jack_ and _jill_. 
Again, running this code:

```html  
Equal References Person{name='Joe', kids=null}
Equal References Person{name='Jim', kids=null}
Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Equal References Person{name='Jane', kids=null}
Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
```

I get the data that all the references are still the same. 
I do have kids for _jack_ and _jill_, the same kids, _Joe_ and _Jim_.
What this output means is 
that I still have a shallow copy of my array, the copy I made on:

```java  
Person[] personsCopy = Arrays.copyOf(persons, persons.length);
```

Now, I'll get jill's kids, 
_Jill_ is the last element in my copied array, so element 4, 
and I'll chain the kids accessor method to that. 

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
Person[] personsCopy = Arrays.copyOf(persons, persons.length);

var jillsKids = personsCopy[4].kids();
jillsKids[1] = jane;

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}
```

I'll change the second child for _Jill_, from _jim_ to _jane_. 
Running this code:

```html  
Equal References Person{name='Joe', kids=null}
Equal References Person{name='Jim', kids=null}
Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Equal References Person{name='Jane', kids=null}
Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```

All the references are still the same, 
but what I want you to see is that 
Jill's second child is listed as _Jane_ now.
My code prints the reference from the original array, 
so these aren't the copies. 
Hopefully, this example helps you see 
why shallow copies of arrays could cause real problems 
when your array elements are mutable. 
You know how to make this record immutable, 
but let's just say, for some reason, 
we don't want to make this record immutable. 
Instead, I want to make a deep copy of my array. 
How would I go about doing that?

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
//Person[] personsCopy = Arrays.copyOf(persons, persons.length);
Person[] personsCopy = new Person[5];

for (int i = 0; i < 5; i++) {
    Person current = persons[i];
    var kids = current.kids();
    personsCopy[i] = new Person(current.name(), current.dob(), kids);
}

var jillsKids = personsCopy[4].kids();
jillsKids[1] = jane;

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}
```

I could do it manually, which I'll do first. 
I'll comment out the `Arrays.copyOf` statement.
I'll create a new persons array, calling it _personsCopy_ as before, 
and initialize that to a new array, with five elements. 
I'll loop from `i = 0` to i less than 5. 
I'll set up a couple of local variables, 
so current will be the current array element, 
and I'll set kids to `current.kids`. 
I'll create a new **person** record, passing it currents name, 
date of birth, and the kids variable.
That gets assigned to my _personsCopy_ array element 
at the same index. 
Ok, so I have a new record for every array element. 
Running this code:

```html  

```

What I want you to see is that 
I don't have any output at all. 
Remember, my code only prints records 
if the references in the arrays are the same. 
Since nothing is printed, 
this confirms I've created new instances 
for my copied array's indexed elements. 
In other words, I've made a deep copy of the array. 
But what does my data look like?

```java  
System.out.println(persons[4]);
System.out.println(personsCopy[4]);
```

I'll print the last element of each array. 
First persons, with index 4, 
then persons copy, 
using index 4 again.
Running my code again:

```html  
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```

I can see my data is the same for both records. 
But I made a copy of the **person** record, 
so why did the change I made, changing Jill's kid to _Jane_, 
show up in both of these records? 
Well, even though my person records are different,
Jill's kids were not cloned when I made a copy of _Jill_. 
This means _Jill_ and _Jill_'s copy are referencing 
the same array of kids. 
This is an example of the problem 
I showed you on the diagrams, where nesting can be quite deep. 
I really made a shallow copy of _Jill_,
but I need to make a deep one.

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
Person[] personsCopy = new Person[5];

for (int i = 0; i < 5; i++) {
    Person current = persons[i];
    //var kids = current.kids();
    var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);
    personsCopy[i] = new Person(current.name(), current.dob(), kids);
}

var jillsKids = personsCopy[4].kids();
jillsKids[1] = jane;

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}

System.out.println(persons[4]);
System.out.println(personsCopy[4]);
```

I'll change the statement 
where I'm setting kids in my for loop, 
to give me the result of a ternary operation.
If the kids array is _null_, I'll return _null_, 
otherwise, I'll return a copy of the _kids_ array. 
Running that:

```html  
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```

I now have code that doesn't have side effects 
for my original array of _persons_. 
The original wasn't changed, only the copy of _Jill_, 
with it's an own copy of kids, got changed. 
I have other options for creating deep copies. 
I could create a copy constructor, 
which I discussed earlier. 
I'll go to my **person** record:

```java  
record Person (String name, String dob, Person[] kids) {

    public Person(Person p) {
        this(p.name, p.dob, p.kids == null ? null : Arrays.copyOf(p.kids, p.kids.length));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}
```

And add a custom constructor selecting no fields. 
I'll add an argument, a **Person** type, 
and call it just _p_, to save some room. 
You'll notice that's giving me a compiler error.
We'll talk about record constructors a little more coming up, 
but to make this work, I have to call what's called the canonical,
or the generated, constructor. 
I'll call this passing _name_, and _dob_ from the method argument, 
and I'll pass that ternary operation, 
passing _null_ if _kids_ is _null_, otherwise, 
doing a copy of the kids array on _p_. 
Now, I can get back to the _main_ method.

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
Person[] personsCopy = new Person[5];

for (int i = 0; i < 5; i++) {
    //Person current = persons[i];
    //var kids = current.kids() == null ? null : Arrays.copyOf(current.kids(), current.kids().length);
    //personsCopy[i] = new Person(current.name(), current.dob(), kids);
    personsCopy[i] = new Person(persons[i]);
}

var jillsKids = personsCopy[4].kids();
jillsKids[1] = jane;

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}

System.out.println(persons[4]);
System.out.println(personsCopy[4]);
```

I can remove the first statements in the loop 
where I'm copying the person data.
Now, I'll use the copy constructor I just created, 
so I can call new **Person**, 
and pass the element on the _persons_ array at the same index.
If I run my code:

```html  
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```
                
I get the same result as before. 
I can simplify my code even further, 
and instead of the for loop, 
I can use the `Arrays.setAll` method.

```java  
Person joe = new Person("Joe", "01/01/1961", null);
Person jim = new Person("Jim", "02/02/1962", null);
Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
Person jane = new Person("Jane", "04/04/1964", null);
Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

Person[] persons = {joe, jim, jack, jane, jill};
Person[] personsCopy = new Person[5];
Arrays.setAll(personsCopy, i -> new Person(persons[i]));

/*
for (int i = 0; i < 5; i++) {
    personsCopy[i] = new Person(persons[i]);
}
*/

var jillsKids = personsCopy[4].kids();
jillsKids[1] = jane;

for (int i = 0; i < 5; i++) {
    if (persons[i] == personsCopy[i]) {
        System.out.println("Equal References " + persons[i]);
    }
}

System.out.println(persons[4]);
System.out.println(personsCopy[4]);
```

I'll remove the for loop. 
I'll insert a statement there.

```java  
Person[] personsCopy = new Person[5];
Arrays.setAll(personsCopy, i -> new Person(persons[i]));
```

Running that:

```html  
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```

Again, I get the same results. 
Finally, there's a clone method on arrays. 
I'll comment out the two statements I have here.

```java  
//Person[] personsCopy = new Person[5];
//Arrays.setAll(personsCopy, i -> new Person(persons[i]));

Person[] personsCopy = persons.clone();
```

Instead, I'll simply call clone on my _persons_ array. 
Running that code:

```html  
Equal References Person{name='Joe', kids=null}
Equal References Person{name='Jim', kids=null}
Equal References Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
Equal References Person{name='Jane', kids=null}
Equal References Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
```

You can see this clone method on the array, 
only performs a shallow copy. 
Ok, so hopefully this cleared up any questions 
you might have about what shallow and deep copies really are. 
It's important to try to figure out 
when you need to create a deep copy and how deep 
when you're creating defensive copies. 
</div>

## [e. Unmodifiable Collections]()
<div align="justify">

In the last couple of sections, 
I've been using mostly arrays, 
to demonstrate side effects, 
and to discuss mutable and immutable instances, 
as well as deep and shallow copies. 
Now, I want to focus on Collections, 
rather than Arrays, and talk about issues unique 
to these types. 
For this session, 
I'll create a class called Student.

```java  
public class Student {

    private final String name;
    private final StringBuilder studentNotes;

    public Student(String name, StringBuilder studentNotes) {
        this.name = name;
        this.studentNotes = studentNotes;
    }

    public String getName() {
        return name;
    }

    public StringBuilder getStudentNotes() {
        return studentNotes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentNotes=" + studentNotes +
                '}';
    }
}
```

I'll add two fields, a **String** for _name_, 
and a **StringBuilder** for _studentNotes_. 
I'll make both private and final.
I've said before that when we use the _final_ modifier,
we need to initialize the fields. 
I'll be talking about alternate initialization methods later, 
but the usual method is a constructor, 
and I'll generate that here, with both fields.
I want getters for my two fields, so I'll generate those. 
I'll generate a _toString_ method with both of these fields.
Ok, that's my **Student** class, 
and now I'll get back to the **Main** class, _main_ method.

```java  
public class Main {

    public static void main(String[] args) {
        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));

        students.forEach(System.out::println);
        System.out.println("-----------------------");
    }
}
```

First, I'll create a couple of **StringBuilder** objects, 
one for _bobsNotes_, and I'll just create an empty **stringBuilder**.
I'll set up _billsNotes_, with a note that _Bill struggles with generics_. 
Now, I'll use these to create my **Student** instances. 
First, a new student, named _bob_, and I'll include _bobsNotes_. 
Next, _Bill_, and I'll pass _billsNotes_ this time. 
I want a list of my two students, so I'll set up a new ArrayList, 
and pass that my two students, using the `List.Of` method. 
And I'll just print my students out, 
including a separation line. 
Running this:

```html  
Student{name='Bob', studentNotes=}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```
                    
There shouldn't be any surprises here.
Next, I'll insert a statement 
after I create the list, 
but before I print the information, 
appending some information to Bob's notes. 

```java  
bobsNotes.append("Bob was one of my first students.");
```

I'll add that Bob was one of my first students. 
Running this code now,

```html  
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```

You can see I successfully changed Bob's information, 
several lines after I created the instance of Bob. 
This, by definition, means **Student** is mutable. 
Hopefully you know how to fix this, 
making a defensive copy in the constructor of Student, 
but right now, I want to leave it like this.

```java  
List<Student> studentsFirstCopy = new ArrayList<>(students);

studentsFirstCopy.forEach(System.out::println);
System.out.println("-----------------------");
```

I'm going to create a copy of my student list. 
I'll do this by simply creating a new array list, 
passing the previous list to it. 
List of **Student**, I'll call it _studentsFirstCopy_, 
and set that to a _new_. 
I'll copy the last two statements,
where I'm printing students, and paste a copy just below, 
and just change _students_ to _studentsFirstCopy_. 
If I run this again:

```html  
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```
                
You'll see the same behavior. 
Bob has notes in the copy as well, 
even though I made my copy of the list 
before I made this edit. 
Passing a list to a constructor of a new list 
isn't going to perform a deep copy of your elements, 
it's a shallow copy. 
But doing this gives you a list that you can edit, 
adding new elements, or clearing elements,
so it's a modifiable list. 
I'll add a new student to my new list, 
the students First copy.

```java  
studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
studentsFirstCopy.sort(Comparator.comparing(Student::getName));
studentsFirstCopy.forEach(System.out::println);
System.out.println("-----------------------");
```

I'll add an element to my copied list, 
passing a new Student Instance, 
with _Bonnie_ as the name, 
and an empty **stringBuilder** for notes. 
If I run this code:

```html  
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bonnie', studentNotes=}
-----------------------
```
                
My second list has _Bonnie_, 
but my first list is unchanged. 
But again, because _student_ isn't immutable, 
I can change bonnie's notes after the construction of bonnie, 
and this change will affect the list.

```java  
StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
bonniesNotes.append("Bonnie is taking 3 of my courses");
studentsFirstCopy.forEach(System.out::println);
System.out.println("-----------------------");
```

I'll set up a new **StringBuilder** variable 
called _bonniesNotes_. 
That gets assigned notes from the third element 
on the copied list, so this is Bonnie's notes. 
I'll make a change to this local variable, 
and append a note that _Bonnie is taking 3 of my courses_. 
Running my code again,

```html  
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
-----------------------
```
                
You can see this note is part of my Bonnie object in my second list. 
To get an unmodifiable collection, 
I'll use the `List.copyOf` method.

```java  
List<Student> studentsSecondCopy = List.copyOf(students);

studentsSecondCopy.forEach(System.out::println);
System.out.println("-----------------------");
```

I'll set up another variable, students second copy, 
and assign that the result of calling `list.copyOf`, 
with my _students_ list as an argument to that. 
I'll again copy and paste the last two statements, 
and I'll change _studentsFirstCopy_ to _studentsSecondCopy_. 
And running this code:

```html  
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```
                    
You can see I again have a copy of my first list.
There's a very significant difference 
between _studentsFirstCopy_ and _studentsSecondCopy_.

```java  
studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
```

I'll copy the **stringBuilder** statement for _studentsFirstCopy_ 
and paste a copy directly below it, 
changing _studentsFirstCopy_ to _studentsSecondCopy_. 
This code compiles, but if I run it:

```html  
Exception in thread "main" java.lang.UnsupportedOperationException
at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
```
                
It throws an exception, unsupported operation exception. 
For an unmodifiable list, I really can't make any modifications
to the list. 
For example, in addition to being unable to remove or add elements, 
I also cannot reassign an element.

```java  
//studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));
studentsSecondCopy.sort(Comparator.comparing(Student::getName));
```

I'll modify that last statement, instead of adding _Bonnie_, 
I'll try to replace element 0 with this new student. 
Running this:

```html  
Exception in thread "main" java.lang.UnsupportedOperationException
at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
```
                
I get the same exception.
I'll comment that line out. 

```java  
//studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
//studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));
studentsSecondCopy.sort(Comparator.comparing(Student::getName));
```

Next, I want to try and sort my students in this copy. 
I'll pass a Comparator, since _student_ doesn't implement Comparable, 
and I want to sort by the student name. 
Once again, running this:

```html  
Exception in thread "main" java.lang.UnsupportedOperationException
at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
```

I still get an unsupported operation exception, 
so I can't even sort an immutable collection. 
You might have noticed the package name in the stack trace,
`java.util.ImmutableCollections`. 
This behavior, and these errors, 
would have you believe that this copy of the list is immutable, 
but in truth, I did mutate data in one of the elements again 
this is the notes on bob by using _append_ method. 
Although the unmodifiable list prevents many alterations, 
it can't prevent all the data from mutating. 
If the element itself has mutable fields, 
code can get references to that data, 
as I've shown you in this example.

**NOTE**: Unmodifiable collections are NOT immutable collections.

It is crucial to understand that 
unmodifiable collections are NOT immutable collections. 
They become immutable collections, 
if the elements in the collections themselves are fully immutable. 
They are collections with limited functionality 
that can help us minimize mutability. 
You can't remove, add or clear elements 
from an immutable collection. 
You also can't replace or sort elements. 
Mutator methods will throw an _UnsupportedOperationException_.
You can't create this type of collection with _nulls_. 

```java  
//studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
//studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));
//studentsSecondCopy.sort(Comparator.comparing(Student::getName));
studentsFirstCopy.sort(Comparator.comparing(Student::getName));
```

I want to change that last statement, 
from sorting _studentsSecondCopy_ to _studentsFirstCopy_. 
This code runs:

```html  
-----------------------
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```
                
And you can see the second list is sorted by name.
There's one more method I want to look at in comparison, 
and that's the unmodifiable list on java util collections.

```java  
List<Student> studentsThirdCopy = Collections.unmodifiableList(students);
studentsThirdCopy.forEach(System.out::println);
System.out.println("-----------------------");
```

I'll set up a list, _studentsThirdCopy_, 
and assign that to `Collections.unmodifiableList`, 
and pass it my students. 
Once again, I'll copy and paste the last two statements in my _main_ method, 
this time changing _studentsSecondCopy_ to _studentsThirdCopy_. 
Running this code:

```html  
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bonnie', studentNotes=Bonnie is taking 3 of my courses}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
```
                
My last list looks just like my first and third, 
so it's an accurate copy. 
Now, let's try a couple of operations on this copy. 

```java  
studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));
```

I'll uncomment set method for studentsSecondCopy, 
and change _studentsSecondCopy_ to _studentsThirdCopy_.
I'll run this:

```html  
Exception in thread "main" java.lang.UnsupportedOperationException
at java.base/java.util.Collections$UnmodifiableList.set
```

And here again, I get _UnsupportedOperationException_. 
Is there any difference between these copies?

```java  
List<Student> studentsSecondCopy = List.copyOf(students);
List<Student> studentsThirdCopy = Collections.unmodifiableList(students);
```
                
And the one I got back,

```java  
studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));
```
                
From calling copy of on List? 
Or is it just a legacy method on Collections 
you don't need to care about? 
Actually, there's a significant difference between 
what you get back from these two methods. 
I'll again comment that last line out. 

```java
students.add(new Student("Bonnie", new StringBuilder()));
```

I'm going to add _Bonnie_ to my original _list_ here next. 
I'll insert this before I edit _bobsNotes_. 
And I'll call _add_ on my _students_ list, 
the original list, adding _Bonnie_. 
Now, running this code:

```html  
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
-----------------------
Student{name='Bob', studentNotes=Bob was one of my first students.}
Student{name='Bill', studentNotes=Bill struggles with generics}
Student{name='Bonnie', studentNotes=}
-----------------------
```
                
What do you notice about the last two lists in the output? 
One has Bonnie, and one doesn't. 
When we use the copy of method, 
we get a copy of the original list. 
This means changes to the original list 
aren't going to have any effects on our copy. 
Again, that's with the warning that the elements are purely immutable.
That aside, you can see that adding _Bonnie_ to _students_ 
did not add _Bonnie_ to _studentsSecondCopy_. 
We manually added _Bonnie_ to _studentsFirstCopy_, 
another copy earlier. 
But the last list, the list we got back from `Collections.unmodifiable` list, 
_studentsThirdCopy_, has _Bonnie_ in it. 
This method doesn't return a copy it actually returns a view, 
an unmodifiable view, of the backing list. 
This list, _studentsThirdCopy_, is always going to reflect 
what's in the **Students** list, 
but I could pass this view to consumers. 
This feature creates a kind of window, if you will, 
to the most current state of a list, 
without allowing a client to modify it.
In other words, you can pass a reference to a mutating list, 
but prevent changes through that particular reference. 
Let me show you a table now, with the unmodifiable collections available to us.

|      | Unmodifiable Copy of Collection                                 | Unmodifiable View of Collection                                                                              |
|------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| List | List.copyOf<br/>List.of                                         | Collections.unmodifiableList                                                                                 |
| Set  | Set.copyOf<br/>Set.of                                           | Collections.unmodifiableSet<br/>Collections.unmodifiableNavigableSet<br/>Collections.unmodifiableSortedSet   |
| Map  | Map.copyOf<br/>Map.entry(K k, V v)<br/>Map.of<br/>Map.ofEntries | Collections.unmodifiableMap<br/>Collections.unmodifiableNavigableMap<br/>Collections.unmodifiableSortableMap |

The three primary Collection interfaces, **List**, **Set** or **Map**, 
have methods to get an unmodifiable copy on the specific interface,
related to the collection type, as shown. 
In addition, the `java.util.Collections` class offers methods 
to get unmodifiable views as shown. 
These methods allow us to get closer to the ideal of immutability 
if it's needed. 
</div>

## [f. Unmodifiable Collections Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/Course08_UnmodifiableCollectionsChallenge/README.md)
<div align="justify">

In an earlier challenge, we created an immutable **BankCustomer** 
and **BankAccount** class. 
We'll be building on that code here.
If you didn't do that challenge, 
let me encourage you to go back and work through it. 
We ended up with two classes as shown here.

![image11](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image11.png?raw=true)

The getters, constructors, and _toString_ methods 
aren't shown on this diagram. 
These are the classes we'll be starting with, in this challenge. 
Now, let's Imagine that we work on a team, with different developers.

![image12](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image12.png?raw=true)

One team creates the data transfer objects (DTOs)
which will be used by a framework 
to retrieve data from and commit data to a database. 
Let's assume they use software to generate these classes 
from their data model. 
We want to emulate this, so we first want 
to create a **Transaction** class in a _dto_ package 
that might mirror a data table. 
This class should have the fields shown above. 
_Include getters and setters_ for all fields. 
Data transfer objects generally have both 
to support two-way communication with database entities. 
_Include a constructor_ that takes all fields for ease of use.

![image13](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image13.png?raw=true)

For this challenge, you'll modify your **BankAccount** class. 
First, you'll want to change the balance so that it's mutable. 
Include a transaction Collection. 
I'm showing a Map on my class diagram, 
but if you want to use another collection, that's good too. 
Provide a getter, or accessor method, for the transaction data. 
Provide a method to adjust the balance, 
and add the transaction data to the transaction collection. 
I'll be doing this in a single method called **commitTransaction** as shown. 
You'll also need to modify your **BankCustomer** class.

![image14](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image14.png?raw=true)

Return the customer id as a 15-digit string, with leading zeros. 
Design this class, so that code in other packages 
can't instantiate a new **BankCustomer**. 
Return a defensive copy of the accounts from the _getAccounts_ method. 
Include a _getAccount_ method to return just one account, 
based on an account type, either savings or checking. 
Assume a customer will have one checking account and one savings account.

![image15](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image15.png?raw=true)

Next, you want to create a **Bank** class that has a routing number, 
and a collection of customers, as well as an integer 
that holds the next transaction id to be assigned. 
You should be able to look up a customer by a customer id, 
a 15-character **String**. 
Transaction id's should be assigned, by using the _lastTransactionId_ field, 
on this instance of the bank. 
A negative amount is a withdrawal, and a positive amount is a deposit. 
Don't let the customer's account balance go below zero. 
In the **Main** class's _main_ method. 
Create a bank instance and add a customer. 
Let a client get a **BankCustomer** instance by a customer id, 
and review transactions from a single selected account. 
These transactions should **not be modifiable**, 
or susceptible to side effects. 
You should only be able to perform a withdrawal 
or deposit of funds, through the **Bank** Instance, 
passing the customer id as a **String**, 
the type of account this transaction will be against, and the amount. 
In other words, the _main_ method should not have access 
to the commit transaction code on the **BankAccount** itself.

![image16](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image16.png?raw=true)

You might want to leave this diagram up 
as you're coding your own solution, 
to help you see the big picture.
</div>

## [g. Constructors]()
<div align="justify">

Ok, what's left to know about constructors? 
Let me encourage you not to skip this section. 
Maybe you were surprised to learn in this section 
that you can create private or package private constructors, 
and this prevents classes from extending your class. 
Perhaps you didn't know that using a _final_ modifier 
on your instance fields is going to force you 
to assign a value either in a constructor, 
or in another code block I haven't covered yet, 
an **initializer**.
These topics are a bit more advanced, 
and now that you've mastered the basics, 
it's time to look at these additional features. 
In this section, I'll be covering:

* Initializing final fields.
* Initializers, both static and instance.
* Record and enum constructors.

```java  
public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent();
        Child child = new Child();
    }
}
```

I've created **Main** class, 
and I'll create another class called **Parent**.
This is all I need to create an instance of this class, 
and I'll do that in my _main_ method. 
I have a new object, not particularly useful, 
an instance of the **Parent** type. 
Maybe you remember that 
Java has created an implicit constructor for me, 
the no args constructor. 
This invisible constructor is kind of important. 
Let me show you why. 
Next, I'll create a **child** class, 
and I'll put this in a different package, **external**.

```java  
public class Child extends Parent {
    
}
```

I'll have this class extend the Parent class. 
This class compiles, and again, 
I can create a new instance of this **child** class 
in my _main_ method. 
The point here is you don't ever have 
to create a constructor to instantiate a class.
It's only when you start doing so that it gets complicated. 
I'll add some fields to the **Parent** class.

```java  
public class Parent {

    private String name;
    private String dob;
}
```

I'll make them private, so _name_, 
and date of birth or _dob_ for short, 
both strings at the moment. 
I'll quickly add getters and setters for both fields.
I'll also include a _toString_ method, 
selecting none for fields. 
I'll change this, replacing that return statement, 
with one that prints the name and date of birth. 
Now my objects are a little more interesting. 
If I go to the _main_ method:

```java  
Parent parent = new Parent();
Child child = new Child();

System.out.println("Parent: " + parent);
System.out.println("Child: " + child);
```

I'll print both the _child_ and _parent_ out. 
I can run this code:

```html  
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920'
```
                    
And I see that my name and date of birth, 
for both the **Parent** and **Child** have been 
initialized to _null_.

```java  
public class Parent {

    private final String name;
    private final String dob;
}
```

Now, I'll make my fields final.
Those two changes give me four compiler errors.
First, I've got errors on the field declarations themselves. 
Second, I've got errors in my setter methods. 
This is a reminder that when you use final fields, 
you must initialize them. 
When they weren't _final_, they were initialized to _null_, 
but now I can't run this code without specifically assigning 
values to these two fields. 
I also can't ever reassign them, 
so these setter methods, which do that, 
aren't going to compile. 
First, I'll remove the two setter methods. 
Now, I'm going to add a code block directly 
after the declaration of these two fields.
I'll assign the name _John Doe_ to _name_. 
I'll set the date of birth to January 1st, 1900.

```java  
public class Parent {

    private final String name;
    private final String dob;

    {
        name = "John Doe";
        dob = "01/01/1900";
        System.out.println("In Parent Initializer");
    }
}
```

Before I talk about this, 
first notice this eliminated my compiler errors, 
and I can run my main method.

```html  
Parent: name='Jane Doe', dob='01/01/1900'
Child: name='Jane Doe', dob='02/02/1900'
```

My _parent_ and _child_ now both have those values in their fields. 
What is this random block of code, 
thrown in at the class level like this? 
This is an **instance initializer**.

An _instance initializer_ is a block of code declared 
directly in a class body. 
This code gets executed when an instance of the class is created. 
_Instance initializers_ are executed 
before any code in class constructors is executed.
You can have multiple initializer blocks. 
They will be executed in the order they are declared. 
I'm going to now add a constructor to this **Parent** class. 
I'll include that after the initializer block there:

```java  
public class Parent {

    private final String name;
    private final String dob;

    {
        name = "John Doe";
        dob = "01/01/1900";
        System.out.println("In Parent Initializer");
    }

    public Parent(String name, String dob) {
        //this.name = name;
        //this.dob = dob;
        System.out.println("In Parent Constructor");
    }
}
```

And I'll generate it, selecting both fields.
But this constructor has an error on both assignments. 
Hovering over name, you can see it says, 
_name might already have been assigned to_. 
That's because it was in the initializer block.
I'll comment out both of those assignments in the constructor. 
I'll next include a statement to the console in the initializer. 
I'll just print _In Parent Initializer_. 
I'll put one in the constructor too. 
This time, I'll print _in parent constructor_. 
Getting back to the main code:

```java  
public class Main {

    public static void main(String[] args) {

        Parent parent = new Parent();
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);
    }
}
```

You'll notice I have a compiler error, 
where I'm trying to instantiate the **Parent** class. 
What happened to my implicit no args constructor? 
Remember, once you create an explicit constructor, 
Java will no longer provide you with the implicit one. 
If I want a no args constructor now, 
I have to manually add it. 
Let me do that, getting back to the **Parent** class, 

```java  
public class Parent {

    private final String name;
    private final String dob;

    {
        name = "John Doe";
        dob = "01/01/1900";
        System.out.println("In Parent Initializer");
    }

    public Parent() {
        System.out.println("In Parent's No Args Constructor");
    }

    public Parent(String name, String dob) {
        //this.name = name;
        //this.dob = dob;
        System.out.println("In Parent Constructor");
    }
}
```

I'll generate and insert it above the one with two parameters. 
I'll add a `system.out.println` statement into this constructor. 
I'll print that this is _the parent's no args constructor_. 
Running this code:

```html  
In Parent Initializer
In Parent's No Args Constructor
In Parent Initializer
In Parent's No Args Constructor
Parent: name='Jane Doe', dob='01/01/1900'
Child: name='Jane Doe', dob='02/02/1900'
```
                        
What I want you to notice is, that first, 
both the initializer, and the no args constructor were run.
They were run for both the **Parent** and the **Child** classes.
Importantly, the initializer block ran first. 
The _instance initializer_ might be a good place 
to initialize default values, 
but it's probably not a great place to initialize 
final instance fields. 
By doing this, I can't now change those values in my constructors. 
Think about that for a minute. 
It's probably likely that's exactly 
what I want to do in my constructors, initialize instance fields, 
from the constructor arguments.
I'll comment out the two assignments in the initializer block for now. 

```java  
public class Parent {

    private final String name;
    private final String dob;

    {
        //name = "John Doe";
        //dob = "01/01/1900";
        System.out.println("In Parent Initializer");
    }

    /*
    public Parent() {
        System.out.println("In Parent's No Args Constructor");
    }
    */

    public Parent(String name, String dob) {
        this.name = name;
        this.dob = dob;
        System.out.println("In Parent Constructor");
    }
}
```


And I'll uncomment the ones in my constructor.
I still have a problem though, a compiler error on my final fields. 
I can't assign values to the fields, only in one constructor, 
and not the other. 
The truth is, I do not really want that no args constructor, 
so I'll comment that out I'll get back to the _main_ method, 
and pass in data for the Parent.

```java  
public class Main {

    public static void main(String[] args) {

        //Parent parent = new Parent();
        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);
    }
}
```

But I have another problem, and that's with my **Child** class. 
Let's go to that class, and see what IntelliJ is telling us here.
_There's no default constructor on **Parent**_. 
When I added a _custom constructor_, 
the implicit one wasn't generated, 
and I commented out the one I generated. 
Without it, we can't have a subclass 
that does not declare a constructor.
Let me add an explicit constructor 
that looks like the one Java would generate for me, 
so this is a little easier to understand.

```java  
public class Child extends Parent {

    public Child() {
        super();
    }
}
```
                                    
This constructor has the same problem, but it's more obvious. 
Calling super with no arguments is the problem. 
Hovering over that, it says, _Expected two arguments but found zero_.

```java  
public class Child extends Parent {

    public Child() {
        super("Jane Doe", "02/02/1920");
    }
}
```

I'll pass some default values here, passing Jane Doe, 
and this time February 2, 1920. 
Now my code compiles again and runs.

```html  
In Parent Initializer
In Parent Constructor
In Parent Initializer
In Parent Constructor
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920'
```

I want to add another field to Parent:

```java  
protected final int siblings;

public Parent(String name, String dob, int siblings) {
    this.name = name;
    this.dob = dob;
    this.siblings = siblings;
    System.out.println("In Parent Constructor");
}
```

Called number of kids, and I'll make this protected, 
so my subclasses can directly access it. 
It will be an int, and I'll make it final. 
I don't want a setter because it's final, 
but I do want to add this to my one constructor.
I'll add siblings to the parameter list of this constructor. 
I'll assign that to my instance field.
`this.siblings = siblings;`. 
Now, I'll go back to the **Child** class.

```java  
public class Child extends Parent {

    private final int birthOrder = getBirthOrder();
    private final String birthOrderString;

    {
        if (siblings == 0) {
            birthOrderString = "Only";
        } else if (birthOrder == 1) {
            birthOrderString = "First";
        } else if (birthOrder == (siblings + 1)) {
            birthOrderString = "Last";
        } else {
            birthOrderString = "Middle";
        }
        System.out.println("Child: Initializer, birthOrder = " + birthOrder +
                ", birthOrderString = " + birthOrderString);
    }

    public Child() {
        super("Jane Doe", "02/02/1920",5);
        System.out.println("Child: Constructor");
    }

    private final int getBirthOrder() {

        if (siblings == 0) return 1;
        return new Random().nextInt(1, siblings + 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + birthOrderString + " child";
    }
}
```

I want two private and final fields in this class, an integer, 
birth order, and a birth order string. 
Again, this class won't compile unless these final fields are initialized. 
Another way to initialize a final instance field is to call a final method. 
I'll create a private final method called _getBirthOrder_ that returns an int. 
If the number of siblings is 0, I'll just return 1.
I'll return a new random number, between one and the siblings. 
I'll add two to the sibling number, so that I get a number 
between one and the number of actual kids, the parents might have had. 
Notice here I'm accessing number of kids, a field this class inherits from **Parent**. 
I can access this field during construction or initialization, 
because the parent's constructor is called first, 
before this code is executed, so this field should be assigned a value by this point. 
I can now assign my birth Order value to what I get back from this method.

Now that I have the birth order, 
I want to set up the _BirthOrderString_, such as _First_ child, 
_Only_ child, _Middle_ child or _Last_ child. 
I'll do this with an _instance initializer_ block. 
I'll check if the number of siblings is equal to 0. 
If it is, I'll set _birthOrderString_ to _Only_. 
Now, notice that although I have code setting _birthOrderString_ in this initializer, 
I still have a compiler error, on the declaration of that field. 
And the message is the same, _**birthOrderString** might not have been initialized_. 
Why do I get this, when I have code to initialize it, in this initializer code block?

Well, consider what happens if the number of siblings is not 0. 
In that case, _birthOrderString_ has no value, and since it's final, 
this is an invalid situation, and the compiler recognizes that. 
I need to fully initialize _birthOrderString_ by providing a value 
in all possible conditions. 
I could complete this with an if-else, but I want multiple conditions,
so I'll continue. 
I'll add a condition to see if the birth order is 1. 
And if so, that means this will be the first born, 
so I'll set birth order string to first. 
Again, I still have the error. 
Because I used else if, there are still other possible unmet conditions. 
I'll continue with the rest of the conditions.
If the birth order is equal to the number of siblings plus 1, 
then this means the _birthOrderString_ should be _last_. 
For any other condition, this must be a _middle_ child. 
Now, when I added that last else condition, 
the compiler error was resolved. 
I want to add a println statement in this initializer code. 
I'll print that this is the initializer code in the child, 
and print the birth order, and the birth order string. 
Now, my constructor needs to include a number of siblings, 
so I'll change it.
In the no args constructor, 
I'll pass 5 as the number of siblings for _Jane Doe_.
I want to include a println statement as well.
I'll print that this is the **Child** Constructor.

And I'll add a _toString_ method, using Alt+Ins again, and select _none_. 
And I'll just replace the generated code, returning _super.toString_, 
and concatenating the _birthOrder_ String to that. 
Getting back to my main method,
I have to include the number of siblings for the parent instance:

```java  
public class Main {

    public static void main(String[] args) {

        //Parent parent = new Parent();
        //Parent parent = new Parent("Jane Doe", "01/01/1950");
        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);
    }
}
```

And for that I'll just put 4 in there. 
Running this code,

```html  
In Parent Initializer
In Parent Constructor
In Parent Initializer
In Parent Constructor
Child: Initializer, birthOrder = 6, birthOrderString = Last
Child: Constructor
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920', Last child
```

I want you to notice a couple of things. 
The first two statements were generated 
when I created the parent instance. 
The next two statements were generated 
when I created the child instance, 
so the parent's initializer is called, 
and then the constructor. 
After this, the child's initializer's code is executed, 
and then the code in the child constructor. 
The child initializer was not called 
before the birth order initialization though. 
Any assignments that occur 
before the declaration of the initialization block 
get assigned before that block is executed. 
I can confirm this because I was able 
to use birth order in the initialization block code. 
Now I'll move that statement:

I'll cut it, and paste it right below Child no args constructor.
Notice the compiler errors I'm getting, in the initializer block, 
on this field in the if statements.
If I hover over one of those, it says Illegal forward reference.
I can't reference a field that has not been initialized 
in my initializer block. 
I'll revert that change, putting the birth order statement back top. 
In addition to an instance initializer block, 
there's also a static initializer.
This block of code lets us initialize static variables, 
and is called as part of the Class construction. 
You'll remember there are no static constructors, 
so this is a way to include code you want to occur, 
while the class itself is being initialized. 
I'll add a _static initializer_ to the **Parent** class.

```java  
static {
    System.out.println("Parent static initializer: class being constructed");
}
```

The _static_ keyword is crucial, 
and the only differentiator between a _static initializer_ 
and an _instance initializer_. 
I'll just print out that this is the Parent's _static initializer_, 
and the **Parent** Class is being constructed.
Running the code with this addition:

```html  
Parent static initializer: class being constructed
In Parent Initializer
In Parent Constructor
In Parent Initializer
In Parent Constructor
Child: Initializer, birthOrder = 2, birthOrderString = Middle
Child: Constructor
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920', Middle child
```
                            
You see that statement was only printed once, 
just before I used that class for any reason. 
A _static initializer_ is called the first time 
a class is referenced or constructed. 
A class can have any number of static initialization blocks. 
They can be declared anywhere in the class body. 
They're called in the order they appear in the source code.
You might use this to set up some environment data or log information 
related to the class before it can be used. 
Remember, this will get executed only during the class's construction 
and not each instance's construction. 
We covered final fields, initialization of final fields, 
and the use of instance and static initializers. 
You've seen that the no args constructor is implicitly created 
only when an explicit constructor isn't declared. 

Now, I want to look at record and enum constructors in some detail. 
I'll create a record, and call it **Person**, and include _toStrings_, 
a name and a date of birth, _dob_.

```java  
public record Person(String name, String dob) {
    
}
```

As you know, this is all we need to have an operational class, 
whose fields (name and dob) are final. 
The compiler inserts a constructor 
when it generates the bytecode or class file. 
It takes the same arguments in the same order 
as those described in the **Person** record. 
This constructor is called the _canonical constructor_. 
Notice that if I want to generate a constructor, 
IntelliJ wants me to select from one of three options. 
**Record** Constructors come in three flavors.

* The _Canonical_, or _Long_ constructor is the implicitly 
generated constructor. 
You can explicitly declare your own, 
which means the implicit one won't get generated. 
If you do declare your own, you must make sure 
all fields get assigned a value.

* The _Custom_ constructor is just an overloaded constructor. 
It must explicitly call the canonical constructor as its first statement.

* The _Compact_, or _Short_ constructor is a special kind of constructor, 
used only on records. 
It's a succinct way of explicitly declaring a _canonical_ constructor. 

```java  
public record Person(String name, String dob) {
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob.replace('-', '/');
    }
}
```

Ok, so let's see what we can, and can't do with these.
Getting back to my code, 
I'm going to generate the _canonical_ constructor. 
If this is all you're going to do, 
you don't need to generate it, 
but maybe you want to do some transformations or validation, 
before the assignments.
In my case, I want to replace any dashes in my _dob_ argument, 
with forward slashes, before assigning this value to my _dob_ field. 
To test this out, 
I'll create an instance of this record in the _main_ method 
and print that out.

```java  
Person joe = new Person("Joe", "01-01-1950");
System.out.println(joe);
```

I'll pass Joe as the name, 
and a date of birth string, with some dashes. 
Running this code:

```html  
Parent static initializer: class being constructed
In Parent Initializer
In Parent Constructor
In Parent Initializer
In Parent Constructor
Child: Initializer, birthOrder = 3, birthOrderString = Middle
Child: Constructor
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920', Middle child
Person[name=Joe, dob=01/01/1950]
```
                            
I get the default **String** printed for a record; 
the _toString_ method is another implicitly generated method. 
Getting back to the record,

```java  
public record Person(String name, String dob) {
    public Person(String name, String dob) {
        this.name = name;
        //this.dob = dob.replace('-', '/');
    }
}
```

So what happens if I don't assign a value to the dob field 
in this canonical constructor? 
I'll comment out that second line, in my constructor. 
IntelliJ flags that parameter, _dob_, in the **Record** parameter list, 
as having a problem.
Hovering over that, it reads, 
_Record component dob might not be initialized in canonical constructor_.
Maybe I could try an _instance initializer_ to set it.

```java  
public record Person(String name, String dob) {
    public Person(String name, String dob) {
        this.name = name;
        //this.dob = dob.replace('-', '/');
    }

    {
        this.dob = "01/01/1900";
    }
}
```

An _instance initializer_ starts with a block of code at the class level. 
Here, I'll try to assign a string literal to the _dob_ field. 
Now, I've got a different problem, 
and that's because _instance initializers_ aren't allowed in records.
The only place to make field assignments is 
in this canonical constructor only. 
Let me comment those last two changes.

```java  
public record Person(String name, String dob) {
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob.replace('-', '/');
        //this.dob = this.dob.trim();
    }
/*
    {
        this.dob = "01/01/1900";
    }
*/

    public Person(Person p) {
        this(p.name, p.dob);
    }
}
```

Let's say that now, I want to trim this string next, 
and I'll just reassign this value back to the dob field.
This isn't permitted either,
because all fields on a record are final, so I can't do this. 
I'll revert that, commenting that extra statement.
I'll generate another constructor. 
Note that the three options for _Constructors_ do not pop up here. 
For now, I'll push the select _None_ button 
and will explain why we don't get three options later in this section. 
I'll pass a **Person** argument to this constructor. 
I want this to be a copy constructor, in other words. 
Ok, there's another problem, and it's that a non-canonical record constructor, 
like this one, must delegate to another constructor. 
I'll do that, passing _name_ from the person argument, 
and the _dob_ as well. 
Going back to the **Main** class,

```java  
Person joeCopy = new Person(joe);
System.out.println(joeCopy);
```

I'll set up a test case in my main method. 
I'll create a new local variable, joe copy, 
and assign that a new instance of Person, 
passing my joe variable to that constructor. 
I'll print this joe copy out.
Running that:

```html  
Person[name=Joe, dob=01/01/1950]
Person[name=Joe, dob=01/01/1950]
```
                
You can see this works, 
and I've got a copy of Joe's data in my new variable. 
Getting back to my **Person** record,

```java  
/*
    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob.replace('-', '/');
        //this.dob = this.dob.trim();
    }
*/

public Person {
    if (dob == null) throw new IllegalArgumentException("Bad data");
    dob = dob.replace('-', '/');
}
```

Let's see what happens when I try to generate another constructor. 
I don't have the three choices I had before. 
I now can't create a compact or canonical constructor.
I can only generate a custom contractor. 
I'll close that popup without generating anything. 
What happened to my other options? 
Let me comment out that first constructor,
the explicit canonical constructor.
Now I'll press Alt+Ins again. 
All my options are back. 
So why is this? 
When you declare an explicit canonical constructor, 
you can't create another constructor. 
You also can't create a compact constructor, 
since it's intertwined with the implicitly created constructor.
In this case, I now want to review the compact constructor, 
so I'll select that. 
What do you notice about this constructor? 
It's missing parentheses `()`, 
and that's what makes this significant and different. 
This code gets inserted into the implicit canonical constructor 
before any final fields are assigned. 
The advantage of using this compact constructor is that 
you can focus on just the custom bit of code, 
and leave the boilerplate code to be generated.
Let's say in this case, I want to check for null 
before I replace any dashes in the date of birth variable. 
I'll throw an _IllegalArgumentException_ if date of birth is null. 
Otherwise, I'll replace the dashes with slashes. 
Notice here that I'm referring to constructor arguments 
that aren't explicitly declared by this compact constructor. 
This constructor has access to all the named arguments of the canonical constructor,
so in this case, name and dob.
It can reassign values to these argument variables. 
All of this is occurring without explicitly assigning these values 
to the instance fields. 
I never assign these arguments to the fields in other words, 
but I'm not getting any errors. 
What happens if I try to do that, meaning if I actually do the assignment.

```java  
public Person {
    if (dob == null) throw new IllegalArgumentException("Bad data");
    dob = dob.replace('-', '/');

    //this.dob = dob;
}
```

I get the error message, cannot assign a value to final variable _dob_. 
You can only edit or validate the method arguments 
because the final fields have yet to be assigned. 
I'll comment that last statement. 
I'll next change that last statement, and instead of just dob, 
I'll try to execute _replace_ on `this.dob`.

```java  
public Person {
    if (dob == null) throw new IllegalArgumentException("Bad data");
    dob = dob.replace('-', '/');
    //dob = this.dob.replace('-', '/');

    //this.dob = dob;
}
```

This gives me another error, that `this.dob` might not have been initialized. 
I can't use the final fields in any of my code here, 
for the reason that this code will get called,
before they actually get set. 
I'll revert that too, and now I want to show you a summary of these rules.

* You can't have both a compact constructor 
and an explicit canonical constructor.
* This constructor is declared with no parentheses, 
so no arguments.
* It has access to all the arguments of the canonical constructor. 
Don't confuse the arguments with the instance fields!
* You can't do assignments to the instance fields in this constructor.
* The implicit canonical constructor's assignments occur
after the execution of this code.

Ok, I want to pause here a moment, 
to introduce you to another command line tool, packaged with java.

Java class file disassembler is **javap**. 
It lists class members, by default just public 
and protected members in the class file. 
This helps us _see_ implicit code in the compiled class file. 
You can see all the options we can use on this tool on oracle's site. 
The only one I'll be demonstrating is `-p`, 
which is short for `-private`, 
and will show all members of a class,
regardless of the access modifiers. 
The default mode would only print the public and protected members. 
This tool can be useful with types like records and enums, 
that do have quite a bit of implicit code. 
Let's play with this for just a minute.

First, I need to identify where my class files are being created, 
and you might remember we talked about this when you were setting up IntelliJ. 
I'll expand the project panel, 
and show you that my class files are generated in an out folder, 
under the project folder. 
I'll continue to expand these folders, 
and you'll see I have three files under the folder 
that matches my package structure. 
These are the compiled classes. 
What I'll do next is open a terminal session inside IntelliJ's IDE. 
I can do this by selecting the tab at the bottom of the screen 
where my output is displayed. 
I'll select Terminal there. 
Note that my Terminal window is expanded to show more output. 
You may want to do the same by expanding the window in the usual way. 
I am going to execute javap from this shell, 
using the argument dash p then specifying the location of the class file.

```java  
javap -p out/production/.../Person
```
          
I'm going to first show you the **Person** class, 
which is my Record.
`-p` shows all classes and members, 
including private ones. 
Note that this path `...` needs to exactly match the path 
we say when I expanded the out folder earlier,
including the right case for the project name. 
Note also that the forward slashes will work on all operating systems,
including windows. 
Running this:

```java  
public final class Rev2_RecordConstructor.Person extends Rev2_RecordConstructor.Record {
    private final java.lang.String name;
    private final java.lang.String dob;
    public Rev2_RecordConstructor.Person(java.lang.String, java.lang.String);
    public Rev2_RecordConstructor.Person(Rev2_RecordConstructor.Person);
    public final java.lang.String toString();
    public final int hashCode();
    public final boolean equals(java.lang.Object);
    public java.lang.String name();
    public java.lang.String dob();
}
```
            
You can see all the hidden implicit code 
that gets generated for us on a record. 
You can see the private final fields, 
first the _name_, then _dob_. 
The next two statements are the constructors, 
the first is my custom constructor, 
but the second is the implicit canonical constructor. 
After that, there are the methods, _toString_,
_hashCode_ and _equals_, as well as the accessor methods. 
Ok, so you can see this output is helpful 
to understand what code is being implicitly generated by the compiler.
If you don't get this output on your computer, 
but instead get an error about javap not being recognised or found, 
it means that javap is not in your computer path, 
and you need to add it. 
Or more specifically, 
the folder containing the javap executable is not in the path. 
This is most likely a Windows issue, 
as in Mac and Linux will add the path automatically 
when the JDK is installed. 
The folder you want to add 
to the path is the bin subfolder in the Java development kit you installed. 
I'll open up Windows 11 Settings here, and type "envir", 
which is short for environment and choose Edit environment variables 
for your account at the bottom. 
Click **Path** and click **Edit** if you want to add the path. 

Now, I want to look again at the enum type. 
I didn't get too deep into them before, 
because for the most part, 
you'll probably be using them in their simplest form. 
Usually, you'll use an enum as a list of named constants, 
and sometimes the ordinal values are important. 
I did show you how to include a method 
in the body of the enum, 
and use that method in code as well, 
but I didn't cover constructors. 
What does it mean if I set up a constructor on an enum? 
Let me set this up. 
First, I'll create a new Java class, 
I'll pick enum as the type, and call that **Generation**.

```java  
public enum Generation {

    GEN_Z,
    MILLENNIAL,
    GEN_X,
    BABY_BOOMER,
    SILENT_GENERATION,
    GREATEST_GENERATION;
    
    Generation() {
        System.out.println(this);
    }
}
```

I'll add my constants. 
I'm going to set these up, one on each line. 
You'll understand why in just a minute. 
Each one of these represents a generational name, 
so a range of birthdates, 
that describe if a person is in one of these groups. 
Hopefully you're somewhat familiar with these labels. 
I have _GEN_Z_, and _MILLENNIAL_, _GEN_X_, then _BABY_BOOMER_,
_SILENT_GENERATION_, and finally the _GREATEST_GENERATION_. 
Each of these represents a range of birth years. 
If you've got a young child, he or she is probably a _GEN_Z_, 
and a grandparent might be a _BABY_BOOMER_, 
or from the _SILENT_GENERATION_.
Maybe you're a _MILLENNIAL_, or a _GEN_X_. 
Like any class, I can generate a constructor for this enum. 
I'll insert it after my constants list, and before the class's closing brace.
Notice, after that's inserted, 
that IntelliJ added a semicolon 
after the _GREATEST_GENERATION_ constant. 
Any time you add code to an enum,
other than the constants, 
you must include this semicolon. 
Ok, so first, I'll just print a statement in this constructor. 
I'll have the code print out the value of _this_. 
Getting back to the main method:

```java  
Generation g;
```

Let's see what happens if I use this enum. 
I won't even use it, 
I'll just set up a local variable with that type to start. 
If I run that code:

```html  
Parent static initializer: class being constructed
In Parent Initializer
In Parent Constructor
In Parent Initializer
In Parent Constructor
Child: Initializer, birthOrder = 3, birthOrderString = Middle
Child: Constructor
Parent: name='Jane Doe', dob='01/01/1950'
Child: name='Jane Doe', dob='02/02/1920', Middle child
Person[name=Joe, dob=01/01/1950]
Person[name=Joe, dob=01/01/1950]
```
                    
There's no evidence that the enum constructor was ever called. 

```java  
//Generation g;
Generation g = Generation.BABY_BOOMER;
```

Next, I'll assign a value to that local variable, 
assigning it _BABY_BOOMER_. 
Running this code,

```html  
Person[name=Joe, dob=01/01/1950]
Person[name=Joe, dob=01/01/1950]
GEN_Z
MILLENNIAL
GEN_X
BABY_BOOMER
SILENT_GENERATION
GREATEST_GENERATION
```

You can see, the constructor didn't just run for the _BABY_BOOMER_, 
it ran for each one of the enum constants. 
When an Enum is initialized, 
all its constants are initialized, 
which means the constructor was called for each constant.
Now, let's use the javap tool to examine what an enum really is, 
under the hood. 
I'll type in javap, then dash p,
which means I want all members printed, 
including private and package private ones. 
I'll include the path, 
where the class file is, and then just type **Generation**, 
and then enter. 
Running this:

```html  
Compiled from "Generation.java"
public final class Rev3_EnumConstructor.Generation extends java.lang.Enum<Rev3_EnumConstructor.Generation> {
    public static final Rev3_EnumConstructor.Generation GEN_Z;
    public static final Rev3_EnumConstructor.Generation MILLENNIAL;
    public static final Rev3_EnumConstructor.Generation GEN_X;
    public static final Rev3_EnumConstructor.Generation BABY_BOOMER;
    public static final Rev3_EnumConstructor.Generation SILENT_GENERATION;
    public static final Rev3_EnumConstructor.Generation GREATEST_GENERATION;
    private static final Rev3_EnumConstructor.Generation[] $VALUES;
    public static Rev3_EnumConstructor.Generation[] values();
    public static Rev3_EnumConstructor.Generation valueOf(java.lang.String);
    private Rev3_EnumConstructor.Generation();
    private Rev3_EnumConstructor.Generation[] $values();
    static {}; 
    }
```
                    
You can see that **Generation** is a final class. 
I'll be covering final classes shortly.
This class extends a special class in Java, 
a `java.lang.Enum`. 
Now, what's so interesting is, 
you can see that each one of my constants 
is really a final static field 
that has the same type as this enum, _Generation_. 
Notice also that there's a constructor, 
it's private, near the bottom of this output. 
Let's see what else we can do here.

```java  
private final int startYear;
private final int endYear;
```

It would be nice if I could include the corresponding year ranges, 
associated with each enum constant. 
I could set up a method, 
as I did in a previous example, 
and return the result of a switch expression, 
which could be the year range. 
But I really want the range to be two fields, 
a start year, and an end year. 
Turns out, I can add additional fields to an enum, 
and use a constructor to populate them.
As with most classes, I can add instance fields, 
so I'll add a _startYear_ and an _endYear_. 
I'll insert these before my constructor,
but after my constant list. 
I'm going to pass these as arguments 
to the constructor next. 
I'll assign the instance fields. 

```java  
Generation(int startYear, int endYear) {
    this.startYear = startYear;
    this.endYear = endYear;
    System.out.println(this + " " + startYear + " - " + endYear);
    System.out.println(this);
}
```

I'll also print that year range out, 
concatenating the two variables to my println statement.
Now, look what these changes did 
to my enum constant declarations. 
Every single one is showing an error. 
Because I created this explicit constructor, 
the implicit no args one doesn't get created. 
This means I have to pass values to the constructor 
for each of these constants. 
How do I do that? 
Well, I can just define the arguments in parentheses, 
after each constant name.

```java  
public enum Generation {

    GEN_Z(2001, 2025),
    MILLENNIAL(1981, 2000),
    GEN_X(1965, 1980),
    BABY_BOOMER(1946, 1964),
    SILENT_GENERATION(1927, 1945),
    GREATEST_GENERATION(1901, 1926);

    Generation() {
        System.out.println(this);
    }
}
```

I'll add 2001 and 2025 to _GEN_Z_, 
these are the birthdate years, 
the range of years, 
that get a person labeled as part of the _GEN_Z_ generation. 
I'll set _MILLENNIAL_ to be 1981 to 2000.
_GEN_X_ is 1965 to 1980.
_BABY_BOOMER_ is 1946 to 1964. 
The _SILENT_GENERATION_ was born between 1927 and 1945. 
Finally, the _GREATEST_GENERATION_ is 1901 to 1926.
Now, my code compiles, and I can run it.

```html  
GEN_Z 2001 - 2025
MILLENNIAL 1981 - 2000
GEN_X 1965 - 1980
BABY_BOOMER 1946 - 1964
SILENT_GENERATION 1927 - 1945
GREATEST_GENERATION 1901 - 1926
```

You can see that each one got constructed, 
and includes the year range. 
Let's run javap again. 
I can just press the up arrow on that terminal session.

```java  
private int startYear;
private int endYear;
private Rev3_EnumConstructor.Generation(int, int);
```

This hasn't changed much, 
except it has the two instance fields, _startYear_ and _endYear_. 
And there, on the third to last line, 
is the new signature for the constructor. 
You can see each parameter type is an int.
It's also private, even though I didn't include an access modifier. 
In fact, let me add the public modifier to that constructor.

```java  
public Generation(int startYear, int endYear) {
```

That gives me an error, so I'll hover over that, 
and I see _the modifier public is not allowed here_. 
Why not?
Well, a constructor on an enum is implicitly private, 
whether we declare it that way or not. 
We can't declare a constructor here as either public or protected. 
I'll revert that last statement. 
The other thing that you should be aware of, 
now that you know these constructors are private, 
is that I can't create an instance of an enum. 
Let me go back to the _main_ method, and demonstrate that.

```java  
Generation h = new Generation();
Generation h = new Generation.BABY_BOOMER(1900, 2000);
```

I'll set up another local variable 
and try to instantiate a new **Generation**. 
IntelliJ tells me enum types can't be instantiated. 
What about an enum constant? 
I'll try to instantiate a new **Generation** Baby Boomer, 
passing it a different range of values. 
And that, too, isn't permissible. 
So I'll just remove that last statement altogether.
Getting back to the **Generation** enum,

```java  
private final int startYear;
private final int endYear;
```

I want those instance fields to be _final_. 
There won't be any need to change those values, 
so I'll add final to each of those declaration statements. 
I'll print that date ranges out in the _toString_ method:

```java  
@Override
public String toString() {
    return this.name() + " " + startYear + " - " + endYear;
}
```

So I'll generate that, selecting none for fields. 
I'll return `this.name`, and the year range from this method.
I'll also change that println statement in the constructor, 
just printing this alone again. 
Running this code:

```html  
GEN_Z 2001 - 2025
MILLENNIAL 1981 - 2000
GEN_X 1965 - 1980
BABY_BOOMER 1946 - 1964
SILENT_GENERATION 1927 - 1945
GREATEST_GENERATION 1901 - 1926
```

I get the same results. 
You can have more than one constructor in an enum. 
Again I'll generate a constructor:

```java  
Generation() {
    this(2001, LocalDate.now().getYear());
}
```

Insert it before this other constructor, 
and select _none_ for the fields. 
Now I've got an explicit no args constructor. 
Like other classes, I can chain constructors, 
so I'll chain the two args constructors, 
passing 2001, and the current year, 
which I get using `LocalDate.now`, with _getYear_. 
Now, for the Gen z constant, I can remove the arguments,
so that it'll execute the no args constructor. 
I can define this with empty parentheses, 
or I can remove them all together. 
Running this code:

```html  
GEN_Z 2001 - 2023
MILLENNIAL 1981 - 2000
GEN_X 1965 - 1980
BABY_BOOMER 1946 - 1964
SILENT_GENERATION 1927 - 1945
GREATEST_GENERATION 1901 - 1926
```
                        
My gen z end year is the current year. 
Now that you know that each constant 
is really a Generation class, 
you can actually include a class body for each, 
or any of these.

```java  
GEN_Z {
    {
        System.out.println("-- SPECIAL FOR " + this + " --");
    }
},
```

I'll do this with Gen z, adding a class body, 
by including a starting and ending curly brace.
Ok, so what happens if I just add a `System.out.println` statement there? 
I want this to print out, special for gen z, 
but this doesn't compile. 
If you thought about GEN Z as a class, 
you'd realize that what I'm really doing here is,
inserting a line of code in a class body. 
I can't do that, but I can wrap it in another code block. 
I'll add another set of starting and ending curly braces.
This compiles, and what do you think I'm doing here? 
Well, it's an instance initializer,
for the GEN Z instance. 
Let's run this code now:

```html  
GEN_Z 2001 - 2023
-- SPECIAL FOR GEN_Z 2001 - 2023 --
MILLENNIAL 1981 - 2000
GEN_X 1965 - 1980
BABY_BOOMER 1946 - 1964
SILENT_GENERATION 1927 - 1945
GREATEST_GENERATION 1901 - 1926
```

And here you can see that statement. 
You can also declare methods in these constant class bodies. 
It's generally not a good idea to make your enum too complex.
If you need that much complexity, 
maybe a different approach would make more sense.
Ok, so I think that covers constructors for records and enums. 
</div>

## [h. Game Console Challenge Setup]()
<div align="justify">

In this challenge, I'll set up some code 
I plan to use in the remaining challenges and sections. 
I'll again set this up as an independent challenge 
if you want to try it on your own. 

This code will be using generic types and lambda expressions, 
and will be available as a resource in the upcoming sections. 
The game console will be a container to execute some scanner code, 
to drive a text-based game's play. 
It'll collect a username, creating a player from that.
It will start a while loop, displaying a menu of options for a user, 
then solicit a user's response. 
It'll execute a game or player method, based on a user's selected action, 
and end the game if the action indicates the game is over.
</div>

### GameConsole Class
<div align="justify">

This **GameConsole** class is a container for a game,
so it needs a type argument for a game field.
It should also have a static scanner field,
which uses `System.in` to get keyboard input.
You should implement two methods in this class.
The _addPlayer_ method will prompt a user for their name,
read in the response from the scanner,
and delegate to the Game's _addPlayer_ method.
The _playGame_ method will display all available game options,
soliciting user input in a while loop,
then calling execute the action associated with the input.
The constructor should take a new instance of a **Game**.
</div>

### The GameAction Record
<div align="justify">

You'll also want to create a **GameAction** record with three fields.
There should be a key, a _char_ field,
which is the key a user would press to select the action.
Next, include a prompt, which is displayed to the user
to describe the specific action.
There should be an action field for a lambda expression or method reference.
I'll be using _Predicate_ with an Integer type argument.
The integer is the player's index in the player list.
A predicate always returns a boolean result.
This will be used to continue or end the play.
</div>

### The Player Interface
<div align="justify">

Next, you'll want to create a **Player** interface.
The **Player** interface will have a single abstract method,
_name_, that returns a **String**.
A game's player should implement this type.
Use this type as a type parameter for **Game**.
</div>

### The Game Class
<div align="justify">

The **Game** class should be _abstract_ and _generic_,
the type parameter should be a type of **Player**.
This class should have three fields, a _gameName_,
a list of players, and a map of game actions.
Your **Game** class should have **two abstract methods**
you want any custom game to implement.

* The method createNewPlayer will return a new instance of the type used for a player.
* The method getGameActions will return a map
  that associates a character a user would enter, with a prompt and an action to be taken.

For example, if a user selects **Q**,
this should map to a GameAction record,
that has **QuitProgram** as the prompt,
and a lambda expression, calling the quit method on the game,
with a method reference, `this::quitGame`.

This class should have _concrete methods_,
some of which might be overridden by subclasses.
The _addPlayer_ method takes a string for _name_,
creates a **player** instance,
adds it to the **Game**'s player list,
and returns that index.
The executeGameAction will call the **Predicate**'s test method
on the lambda expression in the action field,
returning the boolean result.
The _printPlayer_ and _quitGame_ methods,
are the methods referenced in the **GameAction** records.
Include getter and helper methods as appropriate.
Finally, create your own simple game, and player type,
and test some methods on the **GameConsole**.

![image17](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image17.png?raw=true)

Here is a model of the types I just explained,
which I'll be building now.
</div>

### Creating Setup
<div align="justify">

I'm going to start with the Player interface, in **game** package.

```java  
public interface Player {

    String name();
}
```

I'll include one method, _name_, that would match a record's accessor method,
if it had a field called _name_.
I'm going to reference this method in **Game**, for any **Player** type.
That's all I need for the interface.
This type gives me a way to describe all kinds of game players.
Next I'll create the **GameAction** record:

```java  
public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
```

Again in the same **game** package.
I only want three fields, _key_, _prompt_, and _action_.
I've said that _key_ should be a **char**, and _prompt_ a **String**.
_Action_ is going to be a functional interface type.
I could've chosen from several different ones,
but I chose **Predicate**,
because I want the game to continue until some condition is met.
Any one of these game options could end the game.
I'm using **Integer** as the type,
because that lets me get a player from the _players_ list,
and execute methods on **Player** as part of a game's method.
This will become clearer later, as I set up the data.
Now, I want to build the **Game** class, in the same package.

```java  
public abstract class Game<T extends Player> {

    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, GameAction> standardActions = null;
}
```

I'll make this both _abstract_ and _generic_.
I'lI have to define the **type**,
and I'll put that in angle brackets, using _T_ as my type parameter,
and that needs to extend **Player**.
My game will be a container for _players_
if that helps you think about it that way.
I'll add my three fields.
I'll have a _gameName_.
I'll set up and **ArrayList** for _players_, and initialize it here.
I want a standard set of actions that every game might have,
like quit, or info.
For now, I'll initialize this to _null_.
The first two will be _final_
because I want to use the techniques we've been discussing,
making this class as immutable as possible.
The third, the key map, will vary by the type of game and user.

```java  
public Game(String gameName) {
    this.gameName = gameName;
}

public String getGameName() {
    return gameName;
}

public Map<Character, GameAction> getStandardActions() {
    if (standardActions == null) {
        standardActions = new LinkedHashMap<>(Map.of(
                'I',
                new GameAction('I', "Print Player Info", i -> this.printPlayer(i)),
                'Q',
                new GameAction('Q', "Quit Game", this::quitGame)
        ));
    }
    return standardActions;
}

public abstract T createNewPlayer(String name);

public abstract Map<Character, GameAction> getGameActions(int playerIndex);
```

Now, I'll generate a constructor, using just the _gameName_.
I'll generate a getter for _gameName_ and _standardActions_.
I'll create my two abstract methods:

* The first method, _createNewPlayer_ has a return type of just _T_,
  and that takes a string _name_.
* Next, the method returns a map; _key_ is **character**, _value_ is a **GameAction**.
  The name is _getGameActions_, and takes a _playerIndex_.
  This means game actions could be unique for each player.

Any subclasses extending this class have to write
their own custom implementations for these methods.

Next, I'll focus on the _getStandardActions_ method
that I generated earlier.
I'll insert an if statement before the return statement.
This will check if the _standardActions_ map is _null_ first.
If it is, it will populate it with two standard actions,
_I_ for information,
and that will call a _printPlayer_ method on the game,
and _Q_ to quit, which will call _quitGame_ on the game.
I'll make this a **LinkedHashMap**,
so instructions get printed in insertion order.
I'll use `Map.of`, which lets me pass a series of keys and values, in order.
It takes first a key, then value, then the next key,
and the next value, and so on.
The first key is _I_.
This will be a key to the map, as well as a field on the **GameAction** record.
The prompt for this will be _Print Player Info_,
and the method will be _printPlayer_, called on the current **game** instance.
I'll use a regular lambda expression here,
so you can see the parameter is an integer,
and I'll call a method on this class,
that takes an integer and returns a boolean, the _printPlayer_ method.

Next, I'll have _Q_ to quit the game.
The prompt is _quit game_, and the method will be _quitGame_.
The lambdas I'm using here won't compile,
until I include those methods on this class.
I'll do that in just a bit.
First, I'll add the _addPlayer_ method:

```java  
final int addPlayer(String name) {

    T player = createNewPlayer(name);
    if (player != null) {
        players.add(player);
        return players.size() - 1;
    }
    return -1;
}
```

That takes a _name_ and returns an integer, the _playerIndex_.
I don't want subclasses to have their own versions,
so I'll make it _final_.
I want my game console to have access to it,
so I'll make it package private,
meaning no access modifier.
I'll first call the abstract method, _createNewPlayer_,
and assign that to the _T_ type parameter.
Each subclass will have to implement the _createNewPlayer_ method,
producing the right type of player for the game.
If this isn't _null_, I'll add it to the _players_ list.
To return the player's index, I'll get the last index.
If we were multithreading,
this wouldn't be a safe way to get a player's index.
Otherwise, I'll return -1.

```java  
protected final T getPlayer(int playerIndex) {
    return players.get(playerIndex);
}

public boolean executeGameAction(int player, GameAction action) {
    return action.action().test(player);
}

public boolean printPlayer(int playerIndex) {

    Player player = players.get(playerIndex);
    System.out.println(player);
    return false;
}

public boolean quitGame(int playerIndex) {

    Player player = players.get(playerIndex);
    System.out.println("Sorry to see you go, " + player.name());
    return true;
}
```

Next, I want a method to get a player, by the _playerIndex_.
This method will be _protected_,
so subclasses can use it, but _final_,
so they can't override it.
I'll return the player, from the _players_ list, for that index.

Continuing on, I'll add the _executeGameAction_ method,
making it public and not final,
because subclasses may want additional functionality.
This will be executing lambda expressions or method references
that return a boolean and take an integer.
I'll have this method do the same,
but I'll also include a game action parameter.
I'll return the result of the predicate lambda in the action record.
A predicate method is _test_,
and it takes the type we declared, so an integer,
but I can pass an int, because of auto boxing there.

Finally, I want to implement the two methods in my game action records.
First, the _printPlayer_ method, which has to match the Boolean Predicate,
returning a boolean and taking an integer.
I'll get the player from the private players list, using player index.
I'll print the player string, and just return false.
Requesting info should probably never end the game.

Next, I need the _quitGame_, with the same return type and integer parameter.
Again, I'll get the player by index.
I'll print sorry to see you go,
using the player's name.
I'll return true every time,
because this should end the game.
Ok, that's everything except the **GameConsole** class itself.
Ok, now it's time to build the **GameConsole**.
I'll put this class in the **game** package again.

```java  
public class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {

        System.out.print("Enter your playing name: ");
        String name = scanner.nextLine();

        System.out.printf("Welcome to %s, %s!%n".formatted(game.getGameName(), name));
        return game.addPlayer(name);
    }
}
```

This class has one type argument, so _T_,
and that extends **Game**.
Since **Game** is also generic,
I need to identify valid ranges of types for this **Game**.
Here, I'll use a _wildcard_, to specify the type,
since I'm using the **Game**, and not just declaring it.
I'll use an upper-bound wildcard, so extends **Player** there.

This class has one instance field, the _game_, type _T_,
and that'll be _private_ and _final_.
I'll make the _scanner_ private static and final here.
I'll generate a constructor, and pass the **game** instance,
so I'll pick game there.

Next, I want to code the _addPlayer_ method,
which has to get the player's name from the console.
This will return an int, again the player's index in the player list.
First, this should prompt the user for their playing name.
Then wait for the user to respond.
Once I have the _name_, I'll print out a welcome message.
Then I'll return what comes back
from the `game.addPlayer` method.

```java  
public void playGame(int playerIndex) {

    boolean done = false;
    while (!done) {
        var gameActions = game.getGameActions(playerIndex);
        System.out.println("Select from one of the following Actions: ");
        for (Character c : gameActions.keySet()) {
            String prompt = gameActions.get(c).prompt();
            System.out.println("\t" + prompt + " (" + c + ")");
        }
        System.out.print("Enter Next Action: ");

        char nextMove = scanner.nextLine().toUpperCase().charAt(0);
        GameAction gameAction = gameActions.get(nextMove);

        if (gameAction != null) {
            System.out.println("-------------------------------------------");
            done = game.executeGameAction(playerIndex, gameAction);
            if (!done) {
                System.out.println("-------------------------------------------");
            }
        }
    }
}
```

After this, I want to implement _playGame_.
This is public and void, and takes a player index.
I want to set a local variable, _done_, to false.
My while loop will keep going until this becomes true.
I'll get the _gameActions_ from the _game_.
These may change for a player as play continues,
so I'll call this inside the while loop.
The first thing I need to do, in the loop,
is show the available options.
I'll print out a statement that
these are the actions to choose from.
I'll loop through the keys of my game actions map.
I'll get the prompt.
I'll print that prompt, and the key,
which is the keystroke that will execute that action.
After all the options are listed,
I'll prompt the user to pick an action.

Next, I'll get the letter from the user,
you've seen me do this multiple times,
I'll use nextLine on scanner,
making it upper case, and getting the first character.
The input is the key to get the action from the map.
If I find game action in the map for the key entered,
I'll first print a separator line.
Then I execute game action on that.
If that returns a false, it means the game isn't yet over.
I'll print another separator line,
so it separates the action that just occurred
from the options to be picked from.
Ok, that's my generic game console.
I'll quickly test this by creating a **shooter** game
that will have shooters as players.

```java  
public record Shooter(String name) implements Player {

    boolean findPrize() {

        System.out.println("Prize found, score should be adjusted.");
        return false;
    }

    boolean useWeapon(String weapon) {

        System.out.println("You Shot your " + weapon);
        return false;
    }
}
```

I'll make the **shooter** a record,
putting it in the **game** package.
This record has one field,
a _name_ and implements **Player**.
I'll include two additional methods
that would emulate some kind of shooter action in a game.
I'll make these _package-private_,
so that only classes in this package can call these methods.
First, I'll code _findPrize_, this will return a boolean.
I'll just print that a prize was found,
and score needs to be adjusted.
I'll return false, because we don't want the game to end
if the player finds a prize.
The next one will be _useWeapon_,
and return a boolean, but it'll take a weapon.
I'll just print out what weapon it is.
An again return false.
Ok, that's good enough for the shooter test.
Finally, I'll add a new class, the **ShooterGame**.

```java  
public class ShooterGame extends Game<Shooter> {

    public ShooterGame(String gameName) {
        super(gameName);
    }

    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {

        var map = new LinkedHashMap<>(Map.of(
                'F',
                new GameAction('F', "Find Prize", this::findPrize),
                'S',
                new GameAction('S', "Use your gun", this::useWeapon)
        ));
        map.putAll(getStandardActions());
        return map;
    }
}
```

I'll have that extend the **Game**,
with a type argument of **Shooter**.
I have an error,
and that's because I have to implement Game's abstract methods.
If I hover over that, I can pick implement methods.
I'll pick both methods in the popup.
I want to replace return _null_, in the _createNewPlayer_ method.
I'll return a new instance of the **Shooter** record,
passing that _name_.
For the _getGameActions_ method,
I'll replace that return statement altogether.
In this method, I'll add some **GameAction** records,
as well as include the standard ones, set up on **Game**.
I'll first create a new map variable, using var,
rather than be specific about that somewhat complicated type,
and call it map.
I'll assign that a new **LinkedHashMap** instance,
and again I want to use the `Map.of` method,
passing it key value pairs, as separate elements.
If a user presses _f_, they'll be looking for a prize,
and that will call this game's find method,
which I'll add shortly.
If a user presses _S_, they'll be shooting
or using their weapon, and I'll call the _useWeapon_ method,
again I'll add that in a minute.

Next, I want to add the standard actions.
Remember, these are quit and information,
so I still want this.
I want them listed after my own options,
which is why I'm using **LinkedHashMap;**
otherwise, the order wouldn't be guaranteed.
Finally, I'll return this new map.
I've still got an error there
that there's no default constructor,
so I'll select create a constructor matching super.
Finally, I'll implement the two methods
I used in those game options,
starting with _findPrize_ first.

```java  
public boolean findPrize(int playerIndex) {
    return getPlayer(playerIndex).findPrize();
}

public boolean useWeapon(int playerIndex) {
    return getPlayer(playerIndex).useWeapon("pistol");
}
```

All my methods on **Game**,
that are actionable menu items,
have to return a boolean, and take an integer,
I'll get the player by index,
and chain the shooter's _findPrize_ method to that.
Lastly, I'll implement the _useWeapon_ method.
Much like the _findPrize_ method.
This time, I'll call _useWeapon_ on the **shooter**,
passing it _pistol_.
The methods on any of the player implementations
don't have to have the same return type,
or parameters required of the game's actionable methods.
Here, you can see that my shooter's _useWeapon_ method
takes a string representing a weapon.
Ok, that was a bit of setup,
but I'll be using this in the future.
Let's test this out.
I'll go to the **Main** class _main_ method:

```java  
public class Main {

    public static void main(String[] args) {

        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));

        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
```

And set up a new game _console_.
I'll take advantage of type inference,
and use _var_, call the variable console,
and assign that a new **GameConsole**,
passing a new **ShooterGame**, with the game title.
I'll call console add player, and pass the index
back to a player index variable.
Lastly, I'll call play game on console,
passing it that player index.
If I run this:

```html  
Enter your playing name:
```

I can see the console prompting me for my player name.
I'll enter Tim here:

```html  
Welcome to The Shootout Game, Tim!
Select from one of the following Actions:
    Use your gun (S)
    Find Prize (F)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action:
```

And now I see the welcome message,
and I can see all the options available,
so that's kind of neat.
I'll try finding a prize, so _F_.

```html  
-------------------------------------------
Prize found, score should be adjusted.
-------------------------------------------
Select from one of the following Actions:
    Use your gun (S)
    Find Prize (F)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action:
```

Now I get the message that a prize was found,
and the score should be adjusted,
and my options are printing again.
Let me try shooting _S_.

```html  
-------------------------------------------
You Shot your pistol
-------------------------------------------
Select from one of the following Actions:
    Use your gun (S)
    Find Prize (F)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action:
```

And now the message,
you shot your pistol.
Now, I'll select _I_,
to get the shooter information.

```html  
-------------------------------------------
Shooter[name=Tim]
-------------------------------------------
Select from one of the following Actions:
    Use your gun (S)
    Find Prize (F)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action:
```

That just prints my record's default string representation,
so you can see the player's a shooter, with my name.
And now I'll quit, _q_.

```html  
-------------------------------------------
Sorry to see you go, Tim
```

That does quit, so that's good,
and the game tells me it's _sorry to see me go_.
That's our game console class.
I'm going to use this in the next couple of sections.
```java  

```
```html  

```
</div>

## [i. Pirate Game Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/Course11_PirateGameChallenge/README.md#pirate-game-challenge)
<div align="justify">

In this challenge, you'll want to create 
a game using the **Game Console**, 
from the previous section. 
This game will be a **Pirate Game**.

This should extend the **Game** class 
we created previously. 
It should have a **Pirate** class, 
which implements the **Player** interface. 
Your game will have different levels, 
and each level will have a list of towns, 
which can be **Strings** for now. 
Use an enum for the **Weapon** options. 
**Weapon** should have two fields.

* Hit points will get deducted from a player 
hit by this weapon.
* Level will be the minimum level needed 
to be able to use the weapon.

Use an _enum constructor_ to set this up. 
I also want you to think about 
how you could use instance 
or static initializers, 
as you build these types.

![image18](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image18.png?raw=true)

This diagram shows a model of the main entities 
for my own **Pirate** game. 
I'm going to create **Weapon**, as an enum, 
as suggested. 
The **Pirate** class will implement 
the **Player** interface, from the last section. 
Finally, I'll create a **PirateGame** class 
that extends **Game**.

I haven't included methods in the diagram, 
but this gives you a road map for what I'll be building. 
I'll be using the **Game Console** 
from the previous setup section. 
That code included a **Player** interface, 
an abstract generic **Game** class 
that takes a type that implements **Player**, 
as a type argument. 
It also includes a **Game Console** 
that solicits input from the console, 
to determine what actions to take.
Any custom game actions should be setup 
on the new game class, 
as methods that take an integer, 
and return a boolean. 
This means I can store its method reference 
in the game action's field, 
which is set up as a **Predicate**, 
with an **Integer** type. 
I'm going to put this new game, 
and related types, in the **pirate** package. 
</div>

## [j. Final Classes]()
<div align="justify">

In an earlier section, 
I talked about _final_ methods 
which can't be overridden, 
and _final_ variables 
which need to be initialized 
but then can't be reassigned. 
**Final Classes** are a similar concept. 
Using the _final_ keyword on a class 
means it can't be extended. 
You declare a class **final** 
if its definition is complete, 
and no subclasses are desired or required.
**Enums** and **Records** are _final_ classes, 
as I showed you, 
when I used the java class disassembler tool on those. 
I've also showed you examples of 
how subclasses can take advantage of mutable fields on parent classes,
if the parent classes aren't implementing defensive code. 
One of the easiest ways to prevent this, 
is to make your class _final_. 
I'll be using the **GameConsole** project 
from the last challenges in this section.

In this project, I already have a record, **GameAction**, 
and if you followed along in the last challenge, 
you know I have a **Weapon** enum in the **pirate** package. 
I'm going to create another class, and call this one **MainFinal**.

```java  
class SpecialGameAction extends GameAction {
    public SpecialGameAction(char key, String prompt, Predicate<Integer> action) {
        super(key, prompt, action);
    }
}
```

The first thing I'll test is 
whether I can extend the **GameAction** record. 
I'll include this as an additional class 
in the java source file, and not a nested class. 
I'll call it **SpecialGameAction**, 
and that'll extend **GameAction**.
IntelliJ is flagging this as an error, 
and if I hover over that, 
it says _there's no default constructor available_.
That's maybe not the message you might have expected, 
so now I'll actually try to run this code, 
which will attempt to compile it first.

```html  
java: cannot inherit from final GameAction
```
                
Here, I get java's message, 
_cannot inherit from final GameAction_. 
**GameAction**'s a record, 
so I can't use it in any `extends` clause for another class. 
If I hover over **GameAction** again, 
I'll now select _Create_ constructor matching _super_.
And that will generate a constructor for me.
Now I get the error from IntelliJ, 
that makes more sense, 
_cannot inherit from final .game.GameAction_. 
I'll revert that last change, removing that constructor. 
I'll change **GameAction** to **Weapon**, 
my enum from the last challenge:

```java  
class SpecialGameAction extends Weapon {}
```

And again, I get the same message from IntelliJ, 
about no _default_ constructor. 
If I attempt to run it:

```html  
java: cannot inherit from final .pirate.Weapon
```
                
I get the same compiler error 
as I did with the record, 
just a different type. 
Ok, so hopefully 
there are no surprises there. 
We can't extend records or enums. 
I'll remove that statement. 
Next, I'll create another class 
in this source file, **SpecialGameConsole**, 
that extends **GameConsole**.

```java  
class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {
    public SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}
```

Since **GameConsole** is generic, 
I want this to also be generic, 
so I'll declare it in the same way as **GameConsole**,
but when I extend it, I specify the type argument, 
a **Game**, with the same wildcard for player.
Ok, here, I'm getting the same error 
as when I tried to use a record in the `extends` class, 
but this time, the message is accurate in this case, 
so I'll just click on _Create_ constructor matching _super_. 
I'll set up a local variable 
to use this new class in my _main_ method.

```java  
public class MainFinal {
    public static void main(String[] args) {
        SpecialGameConsole<PirateGame> game = new SpecialGameConsole<>(new PirateGame("Pirate Game"));
    }
}
```

I'll set the type argument to my **PirateGame**, 
the name of the variable is simply _game_. 
I'll make it a new instance of the **SpecialGameConsole**, 
passing it the new **PirateGame**. 
I can run this code.

```html  
Loading Data...
Finished Loading Data.
```

I'll see the _Loading Data_, 
and _Finished loading data_ messages 
from the **PirateGame**. 
I'll make the constructor on **SpecialGameConsole**, 
to have a private access modifier.

```java  
class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {
    //public SpecialGameConsole(Game<? extends Player> game) {
    private SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}
```

I'm sure I showed you this before, 
but changing the access modifier on a constructor 
lets you control who can create instances of your class. 
If you make it _private_, like I did here, 
nobody can instantiate an instance of this class. 
You can see the error in the _main_ method of **MainFinal**, 
that this class has _private_ access. 
Making a constructor _private_ has the same effect, 
as far as initializing new instances, 
as making your class abstract. 
I'll revert that change to the constructor, 
and next add the abstract modifier to my class.

```java
//class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {
abstract class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {
    public SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}
```

You can see I still have an error 
when I try to instantiate **SpecialGameConsole**, 
but this time the error is 
_the class is abstract, it can't be instantiated_. 
Again, I'll revert that last change, 
removing the _abstract_ modifier from the **SpecialGameConsole**. 
I've also shown you that making a constructor package-private, 
has much the same effect, outside the package.

```java
//public class GameConsole<T extends Game<? extends Player>> {
class GameConsole<T extends Game<? extends Player>> {
```

If I go to my **GameConsole** class, 
and I remove the _public_ access modifier. 
Jumping back to the **SpecialGameConsole** class, 
I've got an error on the constructor in that class, 
and it's that the constructor is not _public_. 
Making all your constructors package-private is 
effectively making your class _final_, 
because your subclasses can't chain 
any of super's constructors at that point. 
If I go back to **GameConsole** 
and make that _protected_, 
as recommended by that IntelliJ popup.

```java
//public class GameConsole<T extends Game<? extends Player>> {
//class GameConsole<T extends Game<? extends Player>> {
protected class GameConsole<T extends Game<? extends Player>> {
```

That cleans up the problem for my subclass, 
and I can again create my subclass without errors. 
This does create a problem in my **Main** class, 
which instantiates a **GameConsole**. 

```java
//var console = new GameConsole<>(new ShooterGame("The Shootout Game"));
//int playerIndex = console.addPlayer();
//console.playGame(playerIndex);

//var console = new GameConsole<>(new PirateGame("The Pirate Game"));
//int playerIndex = console.addPlayer();
//console.playGame(playerIndex);
```

I'll comment that code out right now. 
I'll go back to the **GameConsole** class, 
and next I'll add the _final_ keyword to the class declaration.

```java
//public class GameConsole<T extends Game<? extends Player>> {
//class GameConsole<T extends Game<? extends Player>> {
//protected class GameConsole<T extends Game<? extends Player>> {
protected final class GameConsole<T extends Game<? extends Player>> {
```

Popping back over to our custom class, 
there's an error in **SpecialGameConsole** class,
and because I've already got a constructor declared, 
I get the message which really is the crux of the problem. 
_Cannot inherit from final GameConsole_. 
If you're not purposely planning 
to allow your classes to be extended,
its best practice to make them _final_. 
I'm going to comment out my **SpecialGameConsole** class. 
I'll change my _main_ method:

```java
public class MainFinal {
    public static void main(String[] args) {
        //SpecialGameConsole<PirateGame> game = new SpecialGameConsole<>(new PirateGame("Pirate Game"));
        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("Pirate Game"));
    }
}
```

To use the **GameConsole** 
and not the **SpecialGameConsole**. 
Because I made the constructor protected, 
I can't create a new **GameConsole** from this class, 
as we saw earlier in the **Main** class. 

```java
//public class GameConsole<T extends Game<? extends Player>> {
//class GameConsole<T extends Game<? extends Player>> {
//protected class GameConsole<T extends Game<? extends Player>> {
//protected final class GameConsole<T extends Game<? extends Player>> {
public final class GameConsole<T extends Game<? extends Player>> {
```

I'll switch back to **GameConsole** and make that public again. 
My code compiles and runs as before.
Making the class _final_ doesn't prevent client code 
from creating instances of the class or using the class. 
It just means no other class can extend it. 
Next, I want to jump over to the **Game** class.

```java
//public Game(String gameName) {this.gameName = gameName;}
private Game(String gameName) {this.gameName = gameName;}
```

I declared this class _public_ and _abstract_, 
and gave it a _public_ constructor. 
Let's see what happens if I make that constructor private.
I don't get any errors on this class, 
but if I open up either the **PirateGame** 
or **ShooterGame**, 
you can see these both have errors, 
on the call to the _super_ constructor. 
In this case, I've created an _abstract_ class 
that nobody can extend, by making that constructor _private_. 
That's probably not what I really wanted to happen. 
You might have some _private_ constructors on an _abstract_ class, 
but you don't want them all to be _private_, 
which is the situation I've created here.
Instead, I'll revert that last change on **Game**, 
making the constructor _public_ again. 
I could make it _protected_, 
but it's not really necessary to make it more restrictive, 
since an _abstract_ class won't ever be instantiated. 
Only a subclass will. 
Now, what happens if I add the _final_ keyword to this class?

```java
//public abstract class Game<T extends Player> {
public final abstract class Game<T extends Player> {
```

IntelliJ doesn't like that. 
_Illegal combination of modifiers, **final** and **abstract**_. 
What this means is, the two modifiers mean the exact opposite thing.
A final modifier means your class is complete, 
and it not only doesn't need to be extended,
but you don't want it to be. 
An abstract modifier means your class isn't complete, 
it probably has methods that aren't complete, 
and they'll only be implemented by subclasses. 
I'll revert that last change, 
removing the _final_ modifier.

| Operations                              | Final Class | Abstract Class | Private Constructors Only | Protected Constrıctors Only                           |
|-----------------------------------------|-------------|----------------|---------------------------|-------------------------------------------------------|
| Instantiate a new instance              | yes         | no             | no                        | yes, but only subclasses, and classes in same package |
| A subclass can be declared successfully | no          | yes            | no                        | yes                                                   |


I'll show a quick summary on a table. 
_Private_ constructors will prevent 
both a new instance and a new subclass 
from being created.
_Protected_ constructors will prevent 
an instance from being created 
outside a subclass or the package. 
The _final_ and _abstract_ modifiers are incompatible 
and wouldn't be used in the same declaration. 
You can see that if you don't want your class 
to be instantiated, you can either make it _abstract_ 
or use a more restrictive access modifier on the class. 
Now, let's consider the **Game** class for a moment. 
It's abstract, which means I intend it to be subclassed. 
But what if I only wanted this class extended by my own games, 
and wanted to disallow it for all others? 
How would I go about doing this? 
I can't do it with access modifiers. 
Later I'll be talking about the modular JDK, 
and controlling visibility of code with module techniques. 
There's still another method, 
and that's with a feature that became available in JDK 17, 
called **Sealed Classes**. 
</div>

## [k. Sealed Classes]()
<div align="justify">

JDK17 introduced a new modifier, **sealed**, 
for our classes and interfaces. 
This modifier can be used for 
both outer types and nested types. 
When used, a **permits** clause is also required in most cases,
which lists the allowed subclasses.
Subclasses can be nested classes, 
classes declared in the same file, 
classes in the same package, 
or if using Java's modules, in the same module. 
I'll be covering Java's modules later in the course. 
What this means, though, for this specific conversation, 
is that all our code is so far, since JDK9, 
is part of what's called, the unnamed default module.
Because of this, I can't use subclasses in the _permits_ clause 
that are in other packages. 
A sealed class and its direct subclasses create a circular reference. 
I'll be explaining what that means in a bit. 
I want to go back to the **Game** class, 
in the **GameConsole** project I've been using.

```java  
//public abstract class Game<T extends Player> {
sealed abstract class Game<T extends Player> {
```

I'll add the modifier **sealed** on the **Game** class. 
IntelliJ doesn't like that change. 
Hovering over that, I get the message 
_Sealed class permits clause must contain all subclasses_. 
I have two subclasses already, **ShooterGame** and **PirateGame**,
so I'll add a _permits_ clause manually, 
and include both of those. 
The _permits_ clause should be the last clause of the class declaration, 
listed after any _extends_ or _implements_ clauses. 
I've got errors though, one on **PirateGame**, 
and one on the **ShooterGame** in this clause. 
If I hover over **PirateGame**, 
I get the message 
_Class is not allowed to extend sealed class from another package_. 
Our **PirateGame** class is in the **pirate** package, 
so this isn't permitted, 
but **ShooterGame** is so let's see what that error says. 
I'll hover over that. 
This message tells me that 
_all sealed class subclasses must either be final, sealed or non-sealed_. 
Actually, If I try to run my **Main** File _main_ method here, 
I get the compiler error that is a bit more enlightening, 
that _the code is all in the unnamed module_. 
If I'm going to use the sealed class functionality, 
I have to do it for a class in the same package as **Game**. 
This message confirms that our code is in an _unnamed_ module, 
as I mentioned before. 
Now, I'm going to copy my **Game** class, 
and create a new class from that,
also in the **game** package. 
I want to call the copy, **SealedGame**. 
I'll go back to the original **Game** class 
and revert the last changes, 
removing the _sealed_ modifier 
and the _permits_ clause. 
Now, going to my new **SealedGame** class,

I'll first remove the **PirateGame** 
from the _permits_ clause. 
Because **PirateGame** is in another package, 
I can't include it here. 
I can include the **ShooterGame**, 
but first I need to change the declaration 
to extend the **SealedGame**, and not **Game**. 
When I do that, I get the error 
all sealed, non-sealed or final modifiers expected.
IntelliJ is suggesting I make **ShooterGame** _final_, 
and I'll do that. 
This clears up my errors for the **ShooterGame**, 
and **SealedGame**. 
You can't make a class sealed, 
without first adding a modifier to a subclass 
if you haven't already included 
one of the three valid ones. 
Are you confused by these interdependencies?

Using the _sealed_ keyword, 
requires the parent class 
to _declare its subclasses_, 
using a _permits_ clause.
This means the parent class has to know 
about every direct subclass, 
and these have to exist, 
in the same package in this case. 
In addition, the _sealed_ keyword puts 
a requirement on all the subclasses 
that were declared in the _permits_ clause.
It requires each subclass 
to declare one of the three valid modifiers 
for a class extending a sealed class.
These are _final_, _sealed_ or _non-sealed_.

![image19](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/images/image19.png?raw=true)

On this diagram, I have a parent class **X**, 
declared as a sealed class, 
and permitting only classes **A**, **B** and **C** 
to subclass it. 
For that reason, class **D**, 
which may exist and extends **X**, 
but won't compile. 
You'll have to either add **D** 
to the _permits_ clause on **X**, 
or remove **D** from the hierarchy in some way. 
As I've stated several times, 
all subclasses declared in the _permits_ clause 
must be declared as _final_, _sealed_ or _non-sealed_. 
Declaring a class _final_ means no other subclasses 
can extend that class, as I show with class **A**, 
on this diagram. 
A subclass declared with a _sealed_ modifier, 
shown here with class **B**, must in turn use a _permits_ clause. 
Its subclasses, in turn, have to use one of the three valid modifiers. 
Lastly, a subclass can use the non-sealed modifier, 
as shown with class **C**.
This means it's basically unsealing itself for all its subclasses. 
This one at first sounds like a bit of a mystery.
Why would you allow subclasses to unseal 
what you said should be sealed?. 
We'll look at each of these in turn. 
I think the scenario we've currently set up 
is the easiest to understand. 
Basically, we're allowing a few classes, 
in the same package, to extend our special **SealedGame**, 
and that's where the subclassing stops. 
I'm going to go to my **ShooterGame**,
And revert it, so that it again extends 
the unsealed **Game** class. 
I'll leave the _final_ modifier there. 
Next, I'll just delete the **SealedGame** class. 
I'll create a new class in _sealed_ package, 
calling it _SpecialAbstractClass_.

```java  
public sealed abstract class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {
    final class Kid extends SpecialAbstractClass {
    }
}
```

I'll add the _sealed_ and _abstract_ modifiers to this class. 
And right away, I'm in a position where it's expected,
that I'll know what my subclasses are, 
and I need to declare them for this sealed class. 
Before I add any subclasses in this package, 
I want to first include a nested class. 
I'll declare a class called **Kid** here, 
and I'll have that extend the outer class, 
the **SpecialAbstractClass** class. 
I get an error on the **Kid** declaration, 
with the message that 
_kid has to be declared final, sealed or non-sealed_, 
so I'll make that _final_ here. 
Now this code compiles, but why is that?
I haven't included a _permits_ clause, 
so why is this ok? 
Well, there's one exception, 
where we don't have to declare a _permits_ clause, 
and that's if all your subclasses are nested classes. 
You can optionally still include it. 
I'll add it here, and specify **Kid** in the clause. 
But that's giving me an error, 
_Cannot resolve symbol, **Kid**_. 
As it turns out, 
when you use a subclass in a _permits_ clause, 
you have to use the qualifying name, 
meaning you have to qualify it 
with the outer class name, 
which I'll do now. 
I'll reference **Kid** as **SpecialAbstractClass.kid**. 
Now this compiles, and all is well. 
Next, I'll create the three different flavors of subclasses, 
external to this class next.

```java  
public final class FinalKid extends SpecialAbstractClass {
}
```

First, I'll create a **FinalKid** class 
in the same package, so in sealed. 
I'll include the _final_ modifier, 
and have it extend the **SpecialAbstractClass** class. 
For now, that's not going to compile, 
until I add this class to the _permits_ 
clause on **SpecialAbstractClass**,
which I'll do in just a minute. 
I'll create the other two classes first.

```java  
public sealed class SealedKid extends SpecialAbstractClass {
}
```

Next will be **SealedKid**. 
I'll add the sealed
modifier on this one, 
and have it extend **SpecialAbstractClass**. 

```java  
public non-sealed class NonSealedKid extends SpecialAbstractClass {
}
```

The last one will be called **NonSealedKid**. 
I'll add the _non-sealed_ modifier, 
and again extend the **SpecialAbstractClass**. 
Ok, so I'll now go add these three, 
to the _permits_ clause, on the **SpecialAbstractClass**. 
If I hover over that error on the class name, 
IntelliJ gives me the options 
to add missing subclasses to the _permits_ clause. 
I'll add a new line after the comma here, 
so you can see the entire declaration. 
Notice that I have an error on **SealedKid**, 
because it's sealed. 
I have to specify a _permits_ clause on that class. 
I'll go to the **SealedKid** class.

```java  
final class GrandKid extends SealedKid {

}
```

Instead of adding a _permits_ clause here, 
I'll add a nested class **GrandKid**. 
I'll make the class _final_, 
and have it extend **SealedKid**. 
If I go back to the **SpecialAbstractClass**:

```java  
public sealed abstract class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {
    final class Kid extends SpecialAbstractClass {
    }
}
```

Let's see what happens if I now try 
to remove this class's nested class 
from the _permits_ clause. 
I'll do that now. 
Actually, I can't do that now. 
Omitting the _permits_ clause 
or a class from the clause, 
only works if you don't have 
other non-nested classes extending 
this sealed class. 
Ok, these rules seem complicated, 
but the main thing to remember is, 
there's a circular relationship 
between a sealed class, and its subclasses. 
A sealed class has to have knowledge of its subclasses, 
which an unsealed class doesn't. 
An unsealed class can have many unknown subclasses.
I'll revert that last change. 
Maybe you noticed that I didn't have 
to declare Grand kid in this clause. 
Only direct subclasses are required 
to be named in the _permits_ clause. 
Ok, so the last thing it would be nice 
to understand about sealed classes 
is this business about allowing a non-sealed subclass. 
Let's create one more class, **FreeGrandKid**.

```java  
public class FreeGrandKid extends NonSealedKid {
}
```

I'll have this extend the **NonSealedKid**. 
Now, this is allowed. 
That seems kind of weird, doesn't it? 
Let's see if we can figure out why Java allowed this. 
First of all, there's probably always an exception to every rule, 
and often these exceptions are discovered very late. 
Having the ability to change a subclass to non-sealed, 
is a way to control a branch of your hierarchy. 
Remember the subclass has to either be part of your own package or module. 
This means that when you permit a non-sealed class, 
you're making a very conscious decision, 
to allow extensibility for that specific branch. 
When would you do this? 
I guess I could come up with a couple of use cases 
if I tried hard enough.
Maybe you might allow a trusted vendor, 
access to this non-sealed branch. 
This would enable them to include value-adds,
on top of your base set of widgets. 
You could provide the non-sealed class. 
Now, I need to talk about _sealed_ interfaces. 
Like a class, you can seal an interface, 
specifying who gets to implement your interface.
I'll create a sealed interface, 
called **SealedInterface**, in the **sealed** package. 

```java  
public sealed interface SealedInterface permits BetterInterface, StringChecker {

    boolean testData(Predicate<String> p, String... strings);
}
```

I'll add a single abstract method, 
which will take a predicate functional interface for a String, 
and a variable argument with _strings_. 
This method could be used 
to test some kind of relationship for a set of strings.
I'll create a class that implements this, 
and I'll call that _StringChecker_ again in the same package. 
I'll add the implements **SealedInterface** to the class declaration. 
I'll implement the method using IntelliJ tools. 
That's all I need for my purposes. 
I'll also extend my _SealedInterface_ with a **BetterInterface**. 
That will extend **SealedInterface**.
Now, I'll seal my **SealedInterface**, 
adding the _sealed_ keyword. 
I'll hover over that and select the link
in that popup to add missing subclasses. 
Now notice, it added a _permits_ clause, 
and here it has the interface that extends **SealedInterface**. 
That's **BetterInterface**, 
but it also has my **StringChecker** class, 
that implements the **SealedInterface**. 
Similarly to a sealed class, 
I have to go back to both those types, 
and decide if they're final, sealed or non-sealed.
For an interface, the final modifier isn't valid. 
An interface, by its nature, is a contract of abstracted methods.
For the **BetterInterface**, 
I can only choose between sealed or non-sealed. 
I'll go with non-sealed here, for simplicity's sake. 
For the class that implements **SealedInterface**, 
I have all three options. 
I'll go to my **StringChecker** class, and make that final. 
Ok, so that's one of Java's newer features. 
I've demonstrated, in this section of the course  
that extensibility might inadvertently expose your code 
to problematic subclasses. 
This access could provide access 
to the internal state of your objects, 
leading to intentional or unintentional mutability. 
Using sealed types gives you another level of control, 
by allowing you to choose which types can leverage your code. 
This lecture ends our section on immutability, 
but I have one last challenge for you. 
I'll be again visiting the **PirateGame** for this.
</div>

## [l. The Final Section Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_12_Immutable_Unmodifable_Classes/Course14_FinalChallengeOfSection/README.md#the-final-section-challenge)
<div align="justify">

In this challenge, you'll be adding functionality 
to the **PirateGame** we've been working on for several sections.
You'll be adding hidden treasure, 
town features, and opponents. 
A town feature will affect the health of your pirate.
For example, if your pirate runs into an alligator, 
you may want to subtract health points. 
If he finds a fresh water spring, 
you'll probably increase his health. 
As your pirate finds loot, each item should increase his score. 
Each town will have different loot and different features.

In this challenge, I want you to abstract 
some functionality of your Pirate player to a Combatant class.
This means you should make a copy of Pirate, 
calling it Combatant, and placing it in the pirate package.
This class should be abstract, and contain most of Pirate's fields 
(like name, weapon, and game data), 
as well as methods related to these fields. 
The Pirate class should extend Combatant.
You'll also create a couple of other Combatant classes, 
such as Islander and Soldier. 
You should seal the Combatant class. 
For the town loot and features. 
You'll create two enums, both with constructors. 
The first, Loot, defines pirate treasure such as gold coins or pearl necklaces, 
each with its own worth, that when found, will increase your pirate's score. 
The second enum, Feature, should describe some town features 
that will either add to the pirate's health, or subtract from it. 
Features should have a health value (positive or negative), 
so that when a pirate runs into this feature, 
his health is adjusted accordingly. 
Some examples of features might be an Alligator 
with a large negative health value, or a Pineapple with a small positive adjustment. 
Use a record to create a Town with the fields: 
name, island, level, loot, features, and opponents. 
The last three can be any Collection. 
Create a compact constructor to initialize randomized lists of loot and features.
These lists should contain only a portion of the ones available on each enum. 
You should also add an opponent or two in this constructor. 
Next, you'll add a custom constructor that takes a name, 
and additional items to be tracked, in the game data map. 
At least one of the opponents should use their weapon 
when your pirate uses his. 
Randomize part of the use weapon method,
so that opponents only occasionally hit each other, 
and deduct points from each's health accordingly. 
Change PirateGame's loadData method 
to load up a List of Town, instead of Strings. 
Change your game's menu items to include a Find Loot option, 
and an Experience Town Feature option. 
Change your code so that a pirate only visits a new town 
after they've found all the loot in the current town.
</div>





<div align="justify">


```java  

```
```html  

```
</div>