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
I'll generate a _toString_ method, with both of these fields.
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
And I'll call add on my _students_ list, 
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

## [f. Unmodifiable Collections Challenge]()
<div align="justify">

In an earlier challenge, we created an immutable **BankCustomer** 
and **BankAccount** class. 
We'll be building on that code here.
If you didn't do that challenge, 
let me encourage you to go back and work through it. 
We ended up with two classes as shown here.




                        _______________________________             _______________________________
                        | BankCustomer                |             | BankAccount                 |
                        |_____________________________|             |_____________________________|
                        | name: String                |<>-----------| accountType: AccountType    |
                        | customerId: int             |             | balance: double             |
                        | List<BankAccount> accounts  |             |_____________________________|
                        |_____________________________|

    The getters, constructors and two string methods, aren't shown on this diagram. These are the classes we'll be starting
    with, in this challenge. Now, let's Imagine that we work on a team, with different developers.

                                                                            _______________________________
                                                                            | Transaction                 |
                                                                            |_____________________________|
        * One team creates the data transfer objects (DTOs)                 | routingNumber: int          |
                                                                            | customerId: int             |
                                                                            | transactionId: long         |
                                                                            | transactionAmount: double   |
                                                                            |_____________________________|

    which will be used by a framework, to retrieve data from and commit data to, a database. Let's assume they use software
    to generate these classes, from their data model. We want to emulate this, so we first want to Create a "Transaction"
    class in a dto package, that might mirror a data table. This class should have the fields shown above. "Include getters
    and setters" for all fields. Data transfer objects generally have both, to support two way communication with database
    entities. "Include a constructor" that takes all fields, for ease of use.

                                   Current                              After Modifications (An Example)
                        _______________________________        _________________________________________________
                        | BankAccount                 |        | BankAccount                                   |
                        |_____________________________|        |_______________________________________________|
                        | accountType: AccountType    |        | accountType: AccountType                      |
                        | balance: double             |        | balance: double                               |
                        |_____________________________|        | Map<Long, Transaction>: transactions          |
                                                               |_______________________________________________|
                                                               | getTransactions: Map<Long, Transaction>       |
                                                               |   commitTransaction(int routingNumber,        |
                                                               |     long transactionId, String customerId,    |
                                                               |       double amount)                          |
                                                               |_______________________________________________|

        For this challenge, you'll modify your BankAccount class. First, you'll want to change the balance so that it's
    mutable. Include a Transaction Collection. I'm showing a Map on my class diagram, but if you want to use another
    collection, that's good too. Provide a getter, or accessor method, for the transaction data. Provide a method to
    adjust the balance, and add the transaction data to the transaction collection. I'll be doing this in a single method
    called commitTransaction as shown. You'll also need to Modify your BankCustomer class.

                                   Current                              After Modifications (An Example)
                        _______________________________        _________________________________________________
                        | BankCustomer                |        | BankCustomer                                  |
                        |_____________________________|        |_______________________________________________|
                        | name: String                |        | name: String                                  |
                        | customerId: int             |        | customerId: int                               |
                        | List<BankAccount> accounts  |        | List<BankAccount> accounts                    |
                        |_____________________________|        |_______________________________________________|
                                                               | getCustomerId: String                         |
                                                               | getAccounts: List<Bank Account>               |
                                                               | getAccount(AccountType type): Account         |
                                                               |_______________________________________________|

    Return the customer id as a 15 digit string, with leading zeros. Design this class, so that code in other packages
    can't instantiate a new Bank Customer. Return a defensive copy of the accounts, from the getAccounts method. Include
    a getAccount method to return just one account, based on account type, either savings or checking. Assume a customer
    will have one checking account and one savings account.

                        _________________________________________________
                        | Bank                                          |
                        |_______________________________________________|
                        | routingNumber: int                            |
                        | lastTransactionId: long                       |
                        | Map<String, BankCustomer> customers           |
                        |_______________________________________________|
                        | getCustomer(String id): BankCustomer          |
                        | addCustomer(String name,                      |
                        |             double checkingInitialDeposit,    |
                        |             double savingsInitialDeposits)    |
                        | doTransaction(String id, AccountType type,    |
                        |               double amount)                  |
                        |_______________________________________________|

        Next, you want to create a Bank class, that has a routing number, and a collection of customers, as well as an
    integer that holds the next transaction id to be assigned. You should be able to look up a customer by a customer id,
    a 15 character String. Transaction id's should be assigned, by using the lastTransactionId field, on this instance
    of the bank.  A negative amount is a withdrawal, and a positive amount is a deposit. Don't let the customer's account
    balance go below zero. In the Main class's main method. Create a bank instance, and add a customer. Let a client get
    a BankCustomer instance by a customer id, and review transactions from a single selected account. These transactions
    should "not be modifiable", or susceptible to side effects. You should only be able to perform a withdrawal or deposit
    of funds, through the Bank Instance, passing the customer id as a String, the type of account this transaction will
    be against, and the amount. In other words, the main method should not have access to the commit transaction code on
    the Bank Account itself.

               _________________________________________________             _________________________________________________
               | BankCustomer                                  |             | Bank                                          |
               |_______________________________________________|             |_______________________________________________|
               | name: String                                  |             | routingNumber: int                            |
               | customerId: int                               |             | lastTransactionId: long                       |
       |-----<>| List<BankAccount> accounts                    |-----------<>| Map<String, BankCustomer> customers           |
       |       |_______________________________________________|             |_______________________________________________|
       |       | getCustomerId: String                         |             | getCustomer(String id): BankCustomer          |
       |       | getAccounts: List<Bank Account>               |             | addCustomer(String name,                      |
       |       | getAccount(AccountType type): Account         |             |             double checkingInitialDeposit,    |
       |       |_______________________________________________|             |             double savingsInitialDeposits)    |
       |                                                                     | doTransaction(String id, AccountType type,    |
       |                                                                     |               double amount)                  |
       |                                                                     |_______________________________________________|
       |
       |       _________________________________________________             _______________________________
       |       | BankAccount                                   |             | Transaction                 |
       |       |_______________________________________________|             |_____________________________|
       |       | accountType: AccountType                      |             | routingNumber: int          |
       |       | balance: double                               |             | customerId: int             |
       |------ | List<BankAccount> accounts                    |<>---------- | transactionId: long         |
               |_______________________________________________|             | transactionAmount: double   |
               | getTransactions: Map<Long, Transaction>       |             |_____________________________|
               |   commitTransaction(int routingNumber,        |
               |     long transactionId, String customerId,    |
               |       double amount)                          |
               |_______________________________________________|

    You might want to leave this diagram up as you're coding your own solution, to help you see the big picture.
```java  

```

```html  

```

</div>



<div align="justify">


```java  

```

```html  

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
