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



I have a **Base** class, with one static method, called _doClassMethod_. 
I also have one instance method, called _doObjectMethod_. 
The child class has its own copies of these methods, with the same signatures. 
The child class hides the base class's static method, _doClassMethod_. 
The child overrides the base class's instance method, _doObjectMethod_.

        Now, consider we have three declarations. X is declared as a BaseClass, and refers to an instance of a BaseClass.
    Y is declared as a BaseClass, and is referring to an instance of a Child class. In this case, BaseClass is the reference
    type, and Child is the instance or runtime type. Z is declared as a Child, and is referring to an instance of a Child
    class. When you execute a static method on an instance, the reference type determines which method is called, the one
    on BaseClass, or the one on Child. What this means is, even though Y is really a child instance, it doesn't matter
    for static methods. Static methods are based on the reference, so in this example, X and Y would both execute the static
    method declared on the Base class. Instance methods are called, based on the runtime type of the instance, and not
    the declared type. This means that Y and Z execute the doObjectMethod on Child, because they're both instances of the
    Child class.

                        Recommendation: Always use the type, to invoke the static method

        Best practice recommends always using the type reference, when executing a static method. If you're hiding a
    static method on a parent class, make sure you understand what the implications are, for doing this. If you stick
    to using a qualifier, the type reference, to execute the specific static method, you'll avoid the confusion shown on
    the previous paragraph. Ok, so now I want to go back to my code. Going main method,
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
