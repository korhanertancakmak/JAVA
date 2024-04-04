# [Section-11: Immutable and Sealed Classes]()

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

                                               Format Specifier Description
                                        %[argument_index$][flags][width]conversion

    This explains the code I'm using in a bit more detail. It's common when using date time conversions, to use the
    argument index feature, which is called "Explicit Indexing". This lets you list your date time variable just once as
    an argument. You then use an index, to tell the formatter, which argument is used for which specifier. If you do use
    an argument index, you need to use it for all specifiers,

                                "%1$tD %1$tT : %2$s %n".formatted(LocalDateTime.now(), message)

    which I'm showing on this example, using 2 for the message, String argument. I thought it was important to review the
    format specifiers a bit, especially for date time. This seemed like a good place to do it, even though it's a bit off
    topic. Let's get back to the code, and back on topic.
</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>



<div align="justify">


```java  

```

</div>
