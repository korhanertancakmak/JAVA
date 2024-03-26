# [Section-8: Generics]()

## [a. Generics Part-I]()
<div align="justify">

By now, if you've been doing this course in order, 
you're familiar with generalizing, conceptually, your own class design. 
You pull out what things your classes have in common, 
so that you can think about them generally. 
Generics allow us to create classes, to design them, 
in a general way, without really worrying about the 
specific details of the elements they might contain. 
Java's ArrayLists are an example of a generic class. 
We can use an ArrayList for any type of object, 
because many of the methods on that class can be applied to any type. 
Let's jump in, and see how to create generic types, 
and examine how to use them and when you might choose to use them.

We actually used generic classes, like an ArrayList or a LinkedList, 
but we haven't looked at how this all works. 
Java supports generic types, such as classes, records, and interfaces. 
It also supports generic methods. Sound confusing? 
It's easier to talk about a generic class by looking at one in a bit of code. 
Below, I'm showing you a regular class declaration, 
next to a generic class.

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/images/image01.png?raw=true)

The thing to notice with the generic class 
is that the class declaration has angle brackets with a _<T>_ in them, 
directly after the class name. 
**T** is the placeholder for a type that will be specified later. 
This is called a type identifier, and it can be any letter or word, 
but **T** which is short for **Type** is commonly used. 
For the regular class above, I've declared a field with a type of String. 
But for the generic class, the field's type is that placeholder, just **T**, 
and this means it can be any type at all. 
The **T** in the angle brackets means it's the same type as the **T**, 
specified as the type of the field.

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/images/image02.png?raw=true)

Above, I have a variable declaration of the generic ArrayList.
In the declaration of a reference type that uses generics, 
the type parameter is declared in angle brackets. 
The reference type is ArrayList, the type parameter (or parameterized type) 
is String, which is declared in angle brackets, 
and listOfString is the variable name. Many of Java's libraries are written 
using generic classes and interfaces, so we'll be using them a lot by moving forward.
But it's still a good idea to learn to write your own generic class, 
to help you understand the concept.

Next, I want to walk through a non-generic class example, 
and then turn that class into a generic example. 
I'm going to create a BaseballPlayer record, in the Main.java source file. 
That record will only have two fields, name and position, 
which is the player's usual position.

```java  
record BaseballPlayer(String name, String position) {}
```

And now, I'm going to create a new class in this package, 
and call it BaseballTeam. 
Next, I'll add the fields on Baseball Team, practicing good encapsulation techniques, 
making them all private.

```java  
public class BaseballTeam {

    private String teamName;
    private List<BaseballPlayer> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    public BaseballTeam(String teamName) {
        this.teamName = teamName;
    }

    public void addTeamMember(BaseballPlayer player) {
        if (!teamMembers.contains(player)) {
            teamMembers.add(player);
        }
    }
    public void listTeamMembers() {
        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    }
    public void listTeamMembers() {
        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    }
}
```

Okay, so nothing exhilarating here. 
Notice we're actually using a generic interface, 
the List, as well as a generic class, the ArrayList. 
I've used **List** as the reference type, for teamMembers. 
Hopefully you'll remember that its best practice 
to use the interface type for the reference variable. 
In other words, use List and not ArrayList, 
on the left side of the assignment operator. 
And I'm making the type parameter 
on the left side of the assignment operator.
And I'm making the type parameter, Baseball Player, our record type. 
I'll add a constructor after these fields, 
and pick just team name.
I want to start adding a few methods. 
First, addTeamMember. 
If team members do not contain player, 
add the player.
Since a baseball player is a record, and records come with an implicit equals method, 
this method can test the equality of all the record's attributes. 
The contains method will check if one player's name
and position are equal to another player's same fields, 
and if it is, the player won't get added. 
It's case-sensitive, and I'll leave it this way for simplicity.
Now, I'll add listTeamMembers, and that one's easy enough. 
Teams often call their current members list, a roster, 
so I'll print that in the label along with the team name.
Then, I'm just using ArrayList's built-in functionality, 
so I can pass that list directly to the println statement.
Now, I also actually want to rank teams. 
This is how one team ranks against another.

```java  
public int ranking() {
    return (totalLosses * 2) + totalTies + 1;
}
```

The best team should have the highest rank. 
This means the team with the most wins is ranked number 1. 
I'll make losses count more, multiplying them by 2, then add ties, 
and if all of that is zero, I'll add 1, so the highest rank is never higher than 1. 
This method isn't the final ranking of a team in a league or group of teams, 
it's just used to determine how well one team is doing compared to another. 
You'd probably use a list to store the teams and then sort them, 
but we're just keeping it simple for now.

Next, I want a set Score method, this will return a String, 
whether this team lost or beat the other team, and it takes two arguments, 
ourScore and their Score:

```java  
public String setScore(int ourScore, int theirScore) {

    String message = "lost to";
    if (ourScore > theirScore) {
        totalWins++;
        message = "beat";
    } else if (ourScore == theirScore) {
        totalTies++;
        message = "tied";
    } else {
        totalLosses++;
    }
    return message;
}
```

This just checks if ourScore is greater than the other team's score, 
and if it is, it increments totalWins and returns the message **beat** from this method. 
Now we want to add the other two conditional expressions, for a loss and a tie. 
So I'll add an else if statement there, and check that ourScore is equal to theirScore. 
If that's true, increment totalTies, so ++ after that variable. 
And a set message = tied. Now an else, this happens when ourScore is less than theirScore, 
so I'll increment totalLosses in this case. 
So this code just figures out which bucket to increase, the total Wins, 
total Ties, totalLosses bucket. 
Next, I want to override the toString method for baseball team. 
I'll generate that, and that gives us the generated code that calls super's to String. 
I'll replace that with my own code.

```java  
@Override
public String toString() {
    return teamName + " (Ranked " + ranking() + ")";
}
```

Here, I just print out the team name with its rank. 
Going to the main class, main method, I'll create two baseball teams.

```java  
public class Main {
    public static void main(String[] args) {

        BaseballTeam phillies = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros = new BaseballTeam("Houston Astros");

        scoreResult(phillies, 3, astros, 5);
    }
}
```

Now, to score the result, I'll create a method in the Main class to handle this:

```java  
public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {

    String message = team1.setScore(t1_score, t2_score);
    team2.setScore(t2_score, t1_score);                         // will be ignored
    System.out.printf("%s %s %s %n", team1, message, team2);
}
```

We want to call scoreResult on the first team, and get the message from that. 
Then we set the score on the second team, but ignore that message, 
because we're referring to the first team's message in our final output. 
The Last line prints out team1, and how they scored against team2. 
And let me add some code to score a result in the main method.
If I run that code:

```java  
Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
```

You can see that the Philadelphia **Phillies are Ranked 3**, 
and they **lost to the Houston Astros who are Ranked 1**. 
Ok, that's good, Our scoring works. 
Now I want to actually add a couple of players to my _phillies_ team.

```java  
var harper = new BaseballPlayer("B Harper", "Right Fielder");
var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
phillies.addTeamMember(harper);
phillies.addTeamMember(marsh);
phillies.listTeamMembers();
```

I've got a couple of philly team members, and both are right fielders. 
I'm using var as the type, which infers the type as BaseballPlayer, 
just to make it easier to see this code on the screen. 
Our record could have had more fields, like batting average or years on the team, 
but I'm keeping it simple. 
If I run this code:

```java  
Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
Philadelphia Phillies Roster:
[BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]
```
                
We'll get the phillies roster printed with our two players. 
Ok, so we have a baseball team application, and let's imagine 
it sold really well, so well that a football team is interested in using it. 
What do we do?
</div>

## [b. Generics Part-II]()

### Solution-I (Duplicate Code)
<div align="justify">

In the last course, we built a Baseball Team class, and now we need 
to have a class that can handle football teams as well. 
One solution is to duplicate the code. 
We could copy and paste the BaseballTeam, and rename everything for FootballTeam, 
and create a FootballPlayer, as I'm showing below.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/images/image03.png?raw=true)

This means you'd have to make sure any changes you made to one team or player 
that made sense for the other team and player, had to be made in both sets of code. 
This is rarely a recommended approach, unless team operations are significantly different. 
The second solution is to use a Player interface.
</div>

### Solution-II (Use a Player Interface or Abstract Class)
<div align="justify">

We could change the Baseball team to simply **Team**, 
and use an interface type (or abstract or base class) called **Player**.
Below, I show a Team Class, and on this class, 
the members are a List of Players.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/images/image04.png?raw=true)

I've made Player an interface, and have BaseballPlayer and FootballPlayer 
implementing that interface. 
This is a better design than the previous one, but it's still got problems.

Let's explore it a little in code. 
Let's start by copying Baseball Team so that I create a new class named SportsTeam.
We have a team, but our type of element in the team is still BaseballPlayer. 
We could make an interface called Player and use that. 
I'll do that next, and I'll just add that interface in this **Main.java** 
source file above the BaseballPlayer record.
</div>

<div align="justify">

And I'm not even going to add any method in this interface. 
The reason I'm using an interface here is simply because my BaseballPlayer is a record, 
and records can implement interfaces, but can't extend any classes. 
There are other reasons to use an interface, especially if you have methods you want to implement, 
such as rankBestPlayer or something like that.

```java  
interface Player {}
```

Next, I'll change all **BaseballPlayer** variable names with 
just **Player** in _SportsTeam.java_ source file. 
And also the BaseballPlayer record below, I'll change it as _implements to Player_. 
That's one advantage to using an interface over an abstract class, 
interfaces work with records. 
Now, we have two classes that can work with baseball players, 
the BaseballTeam class (I'll remove from this package for clarity), 
as well as this new SportsTeam class. 
Going back to the main method, I'll copy those first three statements, 
and paste them right below.

```java  
SportsTeam phillies = new SportsTeam("Philadelphia Phillies");
SportsTeam astros = new SportsTeam("Houston Astros");
scoreResult(phillies, 3, astros, 5);

var harper = new BaseballPlayer("B Harper", "Right Fielder");
var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
phillies.addTeamMember(harper);
phillies.addTeamMember(marsh);
phillies.listTeamMembers();
```

And now, I'll change BaseballTeam to SportsTeam, in those two statements. 
This means our phillies and astros teams
are SportsTeam instances, and not just BaseballTeam instances. 
So now my code compiles and when I run it:

```java  
Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
Philadelphia Phillies Roster:
[BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]
```

And the results are the same, but now with this new class SportsTeam, 
we can create a FootballTeam with football players. 
Let me do that now. 
I'll create FootballPlayer in Main.java, by copying the BaseballPlayer record, 
and then changing that to FootballPlayer. 
And FootballPlayer will also implement Player. 
And then, I can create a new team with FootballPlayer.

```java  
SportsTeam afc = new SportsTeam("Adelaide Crows");
var tex = new FootballPlayer("Tex Walker", "Centre half forward");
afc.addTeamMember(tex);
afc.listTeamMembers();
```

Running this code,

```java  
.... (same)
Adelaide Crows Roster:
[FootballPlayer[name=Tex Walker, position=Centre half forward]]
```

We get our Adelaide Crows football team members printed out, 
and there's just 1, but you get the idea. 
So this was one solution. 
We created a team that has a list of Players, and as long as we have our classes, 
or records, implement the Player interface, we can use this class. 
And that's all well and good. 
We've got a team that will support any kind of player. 
But this team has a problem.

First, there's no type checking when it comes to team members. 
Let me show you an example of what I mean.

```java  
var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
afc.addTeamMember(guthrie);
afc.listTeamMembers();
```

Here, I'm adding a baseball player, a Phillies center fielder, 
named _D Gutrie_, to my Adelaide Crows football team.
And the compiler lets me do it. Running that,

```java  
... (same)
Adelaide Crows Roster:
[FootballPlayer[name=Tex Walker, position=Centre half forward], BaseballPlayer[name=D Guthrie, position=Center Fielder]]
```
                
We see that on the last line, our _Adelaide Crows_ team (supposedly a football team) 
has a football player and a baseball player. 
This is not exactly what we'd want. 
We could leave the rules up to whoever is using this code. 
Or we could build in some rules. 
Generics give us this solution by creating a generic team, 
meaning a team class, which has a type parameter.

Next, I want to copy the SportsTeam class, and really make it generic, 
by naming it just Team. 
And we have an exact duplicate of SportsTeam, except the name. 
The first thing we need to do to make a class generic is to set up type parameters.
</div>

### Generic Type Parameters
<div align="justify">

I've already shown you that one way to declare a generic class is 
to include a type parameter which I show here, in the angle brackets.

```java  
public class Team<T> {}
```

Now, using T is just a convention, short for whatever type you want 
to use this Team class for. 
But you can put anything you want in there. 
Single letter types are the convention, however, 
and they're a lot easier to spot in the class code, 
so let me encourage you to stick to this convention. 
You can have more than one type parameter, so we could do T1, T2, T3.

```java  
public class Team<T1, T2, T3> {}
```

But again, the convention says that instead of using type parameters like this, 
it's easier to read the code with alternate letter selections. 
And these are usually S, U, and V, in that order. 
If we had three types, we'd probably want to declare 
this class as shown here, with T, S, and U.

```java  
public class Team<T, S, U> {}
```
            
A few letters are reserved for special use cases. 
The most commonly used type parameter identifiers are:

* E for Element (used extensively by the Java Collections Framework).
* K for Key (used for mapped types).
* N for Number.
* T for Type.
* V for Value.
* S, U, V etc. for second, third, fourth types.

Going back to our Generic Team, I'll add the type parameter, 
angle brackets with a T inside <T>, after the class name.

```java  
public class Team <T>{

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addTeamMember(T t) {

        if (!teamMembers.contains(t)) {
            teamMembers.add(t);
        }
    }

    public void listTeamMembers() {

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    }

    public int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore) {

        String message = "lost to";
        if (ourScore > theirScore) {
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore) {
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }
        return message;
    }

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }

}
```

That's our first generic class, Team with one type parameter. 
Now, you'll remember, this class was dealing with Player instances, 
predominantly, as the team members. 
So T in the cases we've been looking at, would really stand for player, 
either a football player or a baseball player. 
I want to replace any reference to the Player class, paying attention 
to exact match on a case and change them to the type T. 
This changed our List to have T in its <>. 
And the addTeamMember method was changed, the type of the parameter is T. 
The parameter is player can be also changed to t.
And that's our first generic class. 
Let's switch back to the main method.

```java  
Team<BaseballPlayer> phillies1 = new Team<>("Philadelphia Phillies");
Team<BaseballPlayer> astros1 = new Team<>("Houston Astros");
scoreResult(phillies1, 3, astros1, 5);
```

And similar to what I did before, I'm going to copy the three statements, 
starting SportsTeam phillies, and paste them right below them. 
I'll rename the first three statements as _phillies_ to _phillies1_, 
_astros_ to _astros1_, respectively. 
And then in the pasted statements, I want to change SportsTeam to just Team. 
And again, I'll make a copy of the scoreResult method, and change the types to just Team. 
This code compiles, but it's got warnings. 
It says _Raw use of parameterized class **Team**_. 
I can run this code after this change:

```java  
... (same)
Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
Philadelphia Phillies Roster:
[BaseballPlayer[name=B Harper, position=Right Fielder], BaseballPlayer[name=B Marsh, position=Right Fielder]]
```
                
And we get the same result for all of these types of Team classes. 
Leaving it this way in the code means we're really 
implementing it with the raw use of the class.
</div>

### Raw Usage of Generic Classes

<div align="justify">

When you use generic classes, either referencing them or instantiating them, 
it's definitely recommended that you include a type parameter. 
But you can still use them without specifying one. 
This is called the **Raw Use** of the reference type. 
The raw use of these classes is still available for backwards compatibility, 
but it's discouraged for several reasons:

* Generics allow the compiler to do compile-time type checking 
when adding and processing elements in the list.
* Generics simplify code because we don't have to do our own type checking and casting, 
as we would, if the type of our elements was Object.

You may forget to include the type parameter, 
but IntelliJ will try to identify this for you. 
As you can see, IntelliJ is warning us, with the yellow highlights, 
that there's something we might want to review in this code. 
Let's change the code in our main method, from:

```java  
Team phillies = new Team("Philadelphia Phillies");
Team astros = new Team("Houston Astros");
scoreResult(phillies, 3, astros, 5);     
```

to     
                          
```java  
Team<BaseballPlayer> phillies = new Team<>("Philadelphia Phillies");
Team<BaseballPlayer> astros = new Team<>("Houston Astros");
scoreResult(phillies, 3, astros, 5);
```

to include type parameters, for our three new statements.

```java  
var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
afc1.addTeamMember(guthrie1);
var rory = new FootballPlayer("Rory Laird", "Midfield");
afc1.addTeamMember(rory);
afc1.listTeamMembers();
```

I also want to go up to the adelaide crows to copy and paste that statement here, 
and do something similar, renaming the SportsTeam afc to afc1. 
And then changing SportsTeam to just Team on the pasted code. 
With these changes, you'll notice that I've got a compile error, 
where I'm trying to add D Guthrie, a baseball player, to the Adelaide crows team.
And this is actually a good thing, and what we really want, 
because we want our teams to have all the same kind of player. 
Let me comment guthrie, and I'll create another football player, Rory Laird. 
And this code compiles and runs:

```java  
Adelaide Crows Roster:
[FootballPlayer[name=Tex Walker, position=Centre half forward], FootballPlayer[name=Rory Laird, position=Midfield]]
```
                
Now, maybe you've noticed, in the Main class, 
IntelliJ's been trying to get our attention on that last scoreResult method, 
and I've been ignoring it.
And it's the _Raw Use of parametrized class **Team**_. 
I'm going to continue to ignore this warning, as I work through the rest of this example. 
Generics can be tricky when they're used in method parameters, 
and the rules are different for a static method vs. an instance method. 
I want to cover these rules thoroughly in a future course. 
For now, I'll leave my code with these warnings, 
as we learn the basics of creating a generic class.
</div>

## [c. Generics Part-III]()
<div align="justify">

So far, we created a generic team class, and used it in our main method, 
declaring the type parameter there for our teams. 
We first typed Team with a Baseball Player in <> 
to create two teams of baseball players. 
And we also typed a Team with FootballPlayer in <>, 
which supported a team of football players. 
And because of this, we couldn't add a baseball player 
to a football team, so Java's compiler recognized this was a problem, 
and gave us an error.

Next, I'll add another team and team member.

```java  
Team<String> adelaide = new Team<>("Adelaide Storm");
adelaide.addTeamMember("N Roberts");
adelaide.listTeamMembers();
```

This team will be **Adelaide Storm**, and I'll add the team member **N Robers**, 
and then I'll print it out. 
In this case, I'm saying the type for our team member is just String. 
I don't use a Player at all. 
And this code compiles and runs:

```java  
Adelaide Storm Roster:
[N Roberts]
```
                    
And we can see our player, which is just String value, **N Roberts**, 
listed under the Adelaide Storm Roster. 
The Team class, the way we have it set up, can completely really work with any type. 
And now I'll add another team, the first was really an Australian Volleyball team, 
so I'll create another volleyball team. 
I'll add a member, list the members, then score a game.

```java  
var canberra = new Team<String>("Canberra Heat");
canberra.addTeamMember("B black");
canberra.listTeamMembers();
scoreResult(canberra, 0, adelaide, 1);
```

If I run that:

```java  
Canberra Heat Roster:
[B black]
Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)
```
                    
You can see our methods work, regardless of what type we use for the type parameter, 
with a couple of exceptions. 
We can't use a generic class, any generic class, with a primitive data type.

```java  
Team<int> melbourneVB = new Team<>("Melbourne Vipers");
```

You can see that gives an error. 
Luckily, we have autoboxing, and this isn't a big problem. 
We can use the wrapper instead. 
And I'll change int to Integer, in the <> on the left.

```java  
Team<Integer> melbourneVB = new Team<>("Melbourne Vipers");
```

Ok, now let's say; we really don't want _Team_ to be used for any class under the sun. 
Instead, we want it to only work for things that implement the Player interface. 
This requires one small change to the Team class.

After the generic type _T_, I'm going to type extends Player, still in the <>. 

```java  
public class Team <T extends Player>{
}
```

Before we talk about this, let's see what it did to our code, 
in the main method of the Main class.

In the two cases, where we use Team for String and Integer, 
I'm getting compiler errors now. 
If I hover over String, we get the message that

```java  
Type parameter 'java.lang.String' is not within its bound; should implement 'Player'
```

What does not within its bounds mean?

**NOTE**: Generic classes can be bounded, limiting the types that can use it.

So _T_, our type parameter is followed by the word extends, 
and then the class Player. 
This extends keyword doesn't have the same meaning as extends, 
when it's used in a class declaration.

```java  
public class Team<T extends Player> {}
```
                    
This isn't saying our type _T_ extends Player, although it could. 
This is saying the parameterized type _T_, has to be a _Player_, 
or a **subtype** of _Player_. 
Now _Player_ in this case could have been either a class or an interface, 
the syntax would be the same. 
This declaration establishes what is called an **upper bound**, 
on the types that are allowed to be used with this class. 
This means that only subtypes of **Player** or a **Player** itself 
(if it were a class and not an instance) can be used with this class. 
In this case, this code doesn't care if **Player** is an interface or a class. 
We use the _extends_ keyword for either. 
There are good reasons for specifying an upper bound. 
We saw one already that we can limit what types can be used 
by the generic class. 
But there's another advantage. 
Going back to the Main class,

```java  
interface Player {

    String name();
}
```

First, I want to change my interface, to have one abstract method. 
And you'll remember, any method we add without a method body is 
implicitly public and static on an interface. 
First of all, I know my records, _BaseballPlayer_ and _FootballPlayer_, 
all have an implicit name accessor method already, 
so this doesn't force a change to those records.
They'll already have this method implemented on them. 
Going back to Team,

```java  
public class Team<T> {}
```

I want to revert that last change for a moment, 
so that this class has no bound. 
And then, I want to change the listTeamMembers code.

```java  
public void listTeamMembers() {

    System.out.println(teamName + " Roster:");
    System.out.print(teamName + " Roster:");
    System.out.println((affiliation == null ? "" : " AFFILIATION: " + affiliation));
    for (T t : teamMembers) {
        System.out.println(t.name());
    }
}
```

Instead of using the default print out, I want to print each member on its own line. 
I'll write the code with a for each loop. 
And our code should run like this. 
Going back to the main method, and running the code again:

```java  
Philadelphia Phillies Roster:
BaseballPlayer[name=B Harper, position=Right Fielder]
BaseballPlayer[name=B Marsh, position=Right Fielder]
BaseballPlayer[name=D Guthrie, position=Center Fielder]
Adelaide Crows Roster:
FootballPlayer[name=Tex Walker, position=Centre half forward]
FootballPlayer[name=Rory Laird, position=Midfield]
Adelaide Storm Roster:
N Roberts
Canberra Heat Roster:
B black
Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)
```
                    
The only difference is the members are printed on separate lines. 
But I really only want the player's name to be printed. 
If I go back to the Generic Team Class, listTeamMembers method, 
I'll just use the name accessor method to do that, 
by rewriting println(t) as println(t.name()).

After changing _println(t)_ to _println(t.name())_, we've got a problem. 
This code doesn't like that method being called on the generic type, _T_. 
Remember, at this point, generic type _T_ can be anything under the sun, 
and as we've shown, it could be a String, or an Integer, or anything. 
And these types may not have a name method on them, and in fact they don't, 
so we can't try to use a method in our class, that's not on _java.lang.Object_. 
When we don't specify an upper bound, the upper bound is implicitly _java.lang.Object_, 
meaning that's the only functionality we can use on our type parameter, 
without first casting. 
In this case, this is really too generic for our purposes. 
Leaving this method for a moment and going back to the class declaration, 
I want to get more specific, and again, add back the _extends Player_. 
This lets us use the name method now, in listTeamMembers, 
and the Team class compiles. 
Going back to the main method,

We have several errors, and again, now we've said _Team_ can 
only be used for a type of Player, not for any class, like String or Integer.
First, I'll comment out those last statements, that for Integer and Strings. 

```java  
/*
Team<String> adelaide = new Team<>("Adelaide Storm");
adelaide.addTeamMember("N Roberts");
adelaide.listTeamMembers();

var canberra = new Team<String>("Canberra Heat");
canberra.addTeamMember("B black");
canberra.listTeamMembers();
scoreResult(canberra, 0, adelaide, 1);

Team<int> melbourneVB = new Team<>("Melbourne Vipers");           // Commented due to error

Team<Integer> melbourneVB = new Team<>("Melbourne Vipers");
*/
```

And let me real quickly add a Volleyball Player record in this Main.java source file.

```java  
Team<VolleyballPlayer> adelaide = new Team<>("Adelaide Storm");
adelaide.addTeamMember("N Roberts");
adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
adelaide.listTeamMembers();

var canberra = new Team<VolleyballPlayer>("Canberra Heat");
canberra.addTeamMember(new VolleyballPlayer("B black", "Opposite"));
canberra.listTeamMembers();
scoreResult(canberra, 0, adelaide, 1);
```

Now I want to use the VolleyballPlayer, at the first commented code,
which was for the type String but will be now for the GenericTeam that I created.

```java  
Team<String> adelaide = new Team<>("Adelaide Storm");
adelaide.addTeamMember("N Roberts");
adelaide.listTeamMembers();
```
                
And I want to create actual volleyball players,
I'll do that directly in the call to addTeamMember. 
First for the Adelaide team, and N Roberts, whose position will be _Setter_. 
Then for the Canberra team and B Black, the Opposite. 
Let's run this code:

```java  
... (same)
Philadelphia Phillies Roster:
B Harper
B Marsh
D Guthrie
Adelaide Crows Roster:
Tex Walker
Rory Laird
Adelaide Storm Roster:
N Roberts
Canberra Heat Roster:
B black
Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)
```
                
And we have it running and just printing out the player's names.
We can use our generic team with any type of class, as long as it implements Player.

Let's review why you'd want to use this _extends_ keyword with a type parameter, 
remembering that it's declaring an upper bound. 
There are two reasons to specify an upper bound:

1) An upper-bound permits access to the bounded type's functionality.
2) An upper-bound limits the kind of type parameters you can use when using a generic class. 
The type used must be equal to, or a subtype of the bounded type.

Before I bring this course to a close, I want to add another type to our Generic Class.

```java  
public class Team <T extends Player, S>{

    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;
    private S affiliation;
    
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, S affiliation) {
        this.teamName = teamName;
        this.affiliation = affiliation;
    }

    public void addTeamMember(T t) {

        if (!teamMembers.contains(t)) {
            teamMembers.add(t);
        }
    }

    public void listTeamMembers() {

        //System.out.println(teamName + " Roster:");                    // commented via Part-12
        System.out.print(teamName + " Roster:");
        System.out.println((affiliation == null ? "" : " AFFILIATION: " + affiliation));
        for (T t : teamMembers) {
            System.out.println(t.name());
        }
    }

    public int ranking() {
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore) {

        String message = "lost to";
        if (ourScore > theirScore) {
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore) {
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }
        return message;
    }

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }

}
```

You might remember, I said a second type parameter, 
by convention will be _S_, _U_, or _V_, so I'll use _S_, 
because I don't want to confuse it with the static methods type parameter. 
And this particular type is going to represent the city the team is affiliated with. 
And I'll add an attribute, I'll call it affiliation, 
so that our team could be associated with different kinds of teams, 
like city teams, country teams or school teams. 
Next, I want to add a constructor to include this type. 
I'll add that after the one that's already here:

```java  
public Team(String teamName) {
    this.teamName = teamName;
}

public Team(String teamName, S affiliation) {
    this.teamName = teamName;
    this.affiliation = affiliation;
}
```

And let's actually print out the affiliation in the listTeamMembers method. 
I'll change println to print, on the first statement, and then add a second statement,
so these get printed on a single output line. 
Now, this could be _null_ if we don't pass it on the constructor,
so I want to use a ternary operator to print it, if it's not _null_.

So what is Affiliation? 
Well, it could be anything, a String, a StringBuilder, an interface,
or a class or record. 
I'll create a record, called Affiliation in this _Team.java_ source file.

```java  
record Affiliation(String name, String type, String countryCode) {

    @Override
    public String toString() {
        return name + " (" + type + " in " + countryCode + ")";
    }
}
```

And I'll override the toString method. 
And I'll replace "null" with code to print out the fields on this class.

```java
return name + " (" + type + " in " + countryCode + ")";
```
               
Now, I could have made this a base or abstract class, but right now, 
for simplicity, I'm just going to use this record, whether a team 
is affiliated with a city or a town, or even a country. 
Getting back to the main method, you can see I have quite a few compiler
errors, because now _Team_ expects two type parameters to be declared.

```java
public static void main(String[] args) {

    var philly = new Affiliation("city", "Philadelphia, PA", "US");

    Team<FootballPlayer, String> afc1 = new Team<>("Adelaide Crows", "City of Adelaide, South Australia, in AU");
    var tex1 = new FootballPlayer("Tex Walker", "Centre half forward");
    afc1.addTeamMember(tex1);

    var rory = new FootballPlayer("Rory Laird", "Midfield");
    afc1.addTeamMember(rory);
    afc1.listTeamMembers();

    Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
    //adelaide.addTeamMember("N Roberts");
    adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
    adelaide.listTeamMembers();

    var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Heat");
    canberra.addTeamMember(new VolleyballPlayer("B black", "Opposite"));
    canberra.listTeamMembers();
    scoreResult(canberra, 0, adelaide, 1);
}
```

First, I'll do a global replacement, replacing _Player>_ with _Player, Affiliation>_. 
I'll create an affiliation for the city of _Philadelphia_ 
in the state of Pennsylvania in the US, 
at the very first line of the main method.

```java
var philly = new Affiliation("city", "Philadelphia, PA", "US");
```

Again, I'm using "var" instead of the Affiliation type, 
just to save some screen space. 
I'll include that in the constructor for my phillies team. 
And running this code:

```java
Philadelphia Phillies (Ranked 3) lost to Houston Astros (Ranked 1)
Philadelphia Phillies Roster: AFFILIATION: city (Philadelphia, PA in US)                <<<<<<<<
B Harper
B Marsh
D Guthrie
Adelaide Crows Roster:
Tex Walker
Rory Laird
Adelaide Storm Roster:
N Roberts
Canberra Heat Roster:
B black
Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)
```
            
You can see the affiliation. For the second type parameter, 
we didn't include an upper bound, so we could pass a simple 
string, or another instance of any class, to another team. 
Let's do that for adelaide crows.

```java
//Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies");
Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Astros");
scoreResult(phillies, 3, astros, 5);

var harper = new BaseballPlayer("B Harper", "Right Fielder");
var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
phillies.addTeamMember(harper);
phillies.addTeamMember(marsh);
phillies.addTeamMember(guthrie);
phillies.listTeamMembers();
```

Running after the change,

```java
... (same)
Adelaide Crows Roster: AFFILIATION: City of Adelaide, South Australia, in AU            <<<<<<<<<
Tex Walker
Rory Laird
Adelaide Storm Roster:
N Roberts
Canberra Heat Roster:
B black
Canberra Heat (Ranked 3) lost to Adelaide Storm (Ranked 1)
```
            
You can see we have an affiliation for Adelaide crows now, 
even though we passed a String. 
There may be times when you want upper bounds, 
and other times when you don't as we've shown here. 
We haven't covered multiple types in the upper bounds declaration, 
using lower bounds, and working with overridden methods, 
with these more complex bounded types. 
For the most part, you're far more likely to be using classes, 
with type parameters, and venturing into the complexities I've just mentioned.
</div>

## [d. Generics Challenge](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_08_Generics/Course04_Generics_Challenge/README.md)

<div align="justify">

In the Interface Challenge, we created a Mappable Interface, 
and I introduced you to different Map geometry types, 
_POINT_, _LINE_, and _POLYGON_. 
The challenge then created a map marker or icon, and a map label, 
but didn't do anything with locations. 
In this challenge, you'll use another Mapping example, 
but use location data in the output. 
As you are probably aware, you can use Google Maps 
to determine the location of any point on a map.

If you google on googleMaps for a point in the Grand Canyon National Park, 
in the US, called _Mather Point_. 
You can see the point is by default a red marker, and if you right-click that, 
you'll see numbers listed at the top, 2 numbers, 36.06492, and -112.10780. 
These numbers represent the x and y points for this map, or latitude and longitude.
We'll use this set of double values to identify our location of a point. 
If you click on those numbers, they get copied to your clipboard. 
If you want to use your own locations for this challenge, 
you can retrieve them this way. 
Again, we won't be mapping our points on a map, but generating code 
that would allow a map program to do it.

In this challenge, you'll start with a _Mappable Interface_ 
that has one abstract method, render. 
You'll create two classes _Point_ and _Line_ 
that implement this interface. 
You'll create _two specific classes_ 
that extend each of these, for a mappable item of interest.

I'll be mapping US National Parks and a couple of major rivers in the US, 
so the parks will be points, and the rivers will be lines. 
The data I'll be using is shown here.

<table>
    <thead>
        <tr>
            <th colspan=3>US National Parks & selected locations </th>
            <th colspan=3>US Rivers & selected locations</th>
        </tr>
    </thead>
    <thead>
        <tr>
            <th> Name </th>
            <th> Type </th>
            <th> Googled Locations </th>
            <th> Name </th>
            <th> Type </th>
            <th> Googled Locations </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Yellowstone</td>
            <td>National Park</td>
            <td>44.4882, -110.5916</td>
            <td rowspan=3>Mississippi</td>
            <td rowspan=3>River</td>
            <td>47.2160, -95.2348</td>
        </tr>
        <tr>
            <td>Grand Canyon</td>
            <td>National Park</td>
            <td>36.0636, -112.1079</td>
            <td>35.1556, -90.0659</td>
        </tr>
        <tr>
            <td>Yosemite</td>
            <td>National Park</td>
            <td>37.8855, -119.5360</td>
            <td>29.1556, -89.2495</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td rowspan=2>Missouri</td>
            <td rowspan=2>River</td>
            <td>45.9239, -111.4983</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td>38.8146, -90.1218</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td rowspan=4>Colorado</td>
            <td rowspan=4>River</td>
            <td>40.4708, -105.8286</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td>36.1015, -112.0892</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td>34.2964, -114.1148</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td>31.7811, -114.7724</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td rowspan=2>Delaware</td>
            <td rowspan=2>River</td>
            <td>42.2026, -75.00836</td>
        </tr>
        <tr>
            <td colspan=3></td>
            <td>39.4955, -75.5592</td>
        </tr>
    </tbody>
</table>


I'll be creating a _Park_ class that extends Point, and a _River_ class that extends Line, 
to support this data. 
You should have constructors or methods, to support adding a couple of attributes, 
and some location data, to your two specific classes.

|             Name           |  Googled Location of a Point |
|----------------------------|------------------------------|
|  Yellowstone National Park |  44.4882, -110.5916          |

You can pass the location data of a point type, as a String, or a set of double values, 
representing latitude and longitude. You can pass the multiple locations of a line, 
as a set of strings, or a two-dimensional array of doubles 
that represents the multiple points on your line.

| Name                       | Googled Locations of Points in a River |
|----------------------------|----------------------------------------|
| Mississippi River          | 47.2160, -95.2348                      |
|                            | 35.1556, -90.0659                      |
|                            | 29.1556, -89.2495                      |

In addition to these classes, you'll create a _generic class_ called _Layer_. 
Layers in maps represent a set of data which can be layered on top of one another, 
to present many different sets of data on a single map. 
Your Layer class should have _one type parameter_, 
and should only _allow Mappable elements_ as that type. 
This generic class should have a _single private field_, a "_list of elements_" 
to be mapped. 
This class should have a method or constructor, or both, to add elements. 
You should create a method, called renderLayer, that loops through all elements, 
and executes the method render, on each element.

Your main method should create some instances of your specific classes, 
which include some location data. 
These should get added to a typed Layer, and the render Layer method called on that. 
Sample output is shown here:

```java  
Render Grand Canyon National Park as POINT ([40.1021, -75.4231])
Render Mississippi River as LINE ([[47.2160, -95.2348], [35.1556, -90.0659], [29.1556, -89.2495]])
```
</div>

## [e. Comparable & Comparator (Interfaces for Sorting)]()
<div align="justify">

Now that I've covered interfaces and generic classes, 
I want to review in more detail, interfaces I mentioned in previous lectures. 
The first is Comparable. 
For an array, we can simply call _Arrays.sort_, and pass it an array, 
but as I have previously mentioned, the elements in the array, need to implement Comparable. 
Types like String, or primitive wrapper classes like Integer or Character are sortable, 
and this is because they do implement this interface.

The interface declaration in Java:

```java  
public interface Comparable<T> {
    int compareTo(T o);
}
```
                        
It's a generic type, meaning it's parameterized. 
Any class that implements this interface needs to implement the **compareTo** method. 
This method takes one object as an argument, shown above as the letter **o**, 
and compares it to the current instance, shown as this. 
The table below shows what the results of the compareTo method should mean, 
when implemented. 
This method returns an integer.

| Resulting Value | Meanin    |
|-----------------|-----------|
| zero            | 0 == this |
| negative value  | this < 0  |
| positive value  | this > 0  |

It should return zero if the two objects being compared are equal. 
It should return a negative value if this is less than **o**, 
or a positive value if this is greater than **o**. 
The best way to get familiar with this method is probably to look at it 
for types you're very familiar with. 
I'll start by comparing Integers, since it's straightforward to understand 
if one number is greater than another.

```java  
public class Main {

    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};
    }
    
    for (Integer i :others) {
        int val = five.compareTo(i);
        System.out.printf("%d %s %d: compareTo=%d%n", five, (val == 0 ? "==" : (val < 0) ? "<" : ">"), i, val);
    }
}
```

In this code, I plan to compare the number five to each number in that array. 
Let me add that code, using a for each loop. 
I'll print out a formatted string with multiple specifiers. 
I'll use a nested ternary, so first, if val equals 0, then I can say these two numbers are equal, 
and output a double equals sign. 
If val less than 0, I want a less than sign, otherwise a greater than sign.
If I run this code:

```java  
5 > 0: compareTo=1 
5 == 5: compareTo=0
5 < 10: compareTo=-1
5 > -50: compareTo=1
5 < 50: compareTo=-1
```

You can see that for Integers, the compareTo method is returning 
only three unique values, -1, 0, 1. 
These values are displayed at the end of each of these output lines. 
If the value is 0, the numbers are equal, and we see that with _5 == 5_ on the second line, 
and the return value is zero. 
If the value comes back as -1, that means five is less than the array element, 
so five is less than 10 on line 3, and less than 50 on line 5 above. 
If we get 1 back, five is greater than the array value, 
so five is greater than 0 and -50, as shown on the first and fourth lines.

Ok, now let's look at how Strings have implemented this same method. 
I'll set up the scenario in the same way with a variable called banana.

```java  
String banana = "banana";
String[] fruit = {"apple", "banana", "pear", "BANANA"};

for (String s : fruit) {
    int val = banana.compareTo(s);
    System.out.printf("%s %s %s : compareTo = %d%n", banana, (val == 0 ? "==" : (val < 0 ? "<" : ">")), s, val);
}
```

Running this code,

```java  
banana > apple : compareTo = 1
banana == banana : compareTo = 0
banana < pear : compareTo = -14
banana > BANANA : compareTo = 32
```
                    
The first thing I want you to see is, I'm not just getting _-1_,_0_ and _1_ back for Strings. 
Comparing banana and apple, returns 1, and banana to itself is 0, 
so that might look like the same result as Integers. 
But look at **banana** compared to **pear**. 
I've got a _-14_, so the code is saying banana is less than pear, 
but it's not a _-1_. 
And comparing _banana_ in lowercase to _BANANA_ in all uppercase gives me _32_ back, 
which means lower case banana is greater than uppercase banana, 
but again we've got a value that's something other than _1_, 
here we have _32_. 
I want to sort this list of strings and print it out.

```java  
Arrays.sort(fruit);
System.out.println(Arrays.toString(fruit));
```

Running this code,

```java  
[BANANA, apple, banana, pear]
```
                    
You can see how the strings have been sorted. 
Are you wondering what these numbers (1, 0, -14, 32) coming back 
from the compareTo method mean? 
Let me add a couple lines of code, which might help you understand what's happening here.

```java  
System.out.println("A:" + (int)'A' + " " + "a:" + (int)'a');
System.out.println("B:" + (int)'B' + " " + "b:" + (int)'b');
System.out.println("P:" + (int)'P' + " " + "p:" + (int)'p');
```

Running this code,

```java  
A:65 a:97
B:66 b:98
P:80 p:112
```
                    
Here, I'm printing out the capital letter A, and it's underlying integer value. 
You may remember, chars are stored in memory as positive integer values, 
and that's what this is showing. 
Capital **A** is stored as 65. 
Lowercase **a** is stored as 97. 
When we use the compareTo method on Strings, 
we're really comparing the integer values of the characters in the strings. 
The method will compare the first characters, and if they're the same, 
it next compares the second characters, and so on, 
returning the difference between the character's underlying integer values. 
In this example, all my strings start with a different letter, 
so only the first letter will be compared. 
If we compare **banana** with **apple**, we're comparing 98 (the value for b) 
with 97 (the value for a), and the compareTo method returns the numeric difference, which is 1. 
If we compare **banana** to **pear**, we're comparing 98 with 112, 
and that gives us the difference, **-14**. 
And the same with comparing lowercase **banana** with uppercase **BANANA**, 
we get _98–66_, which is _32_. 
This is how Java implemented the compareTo method on the String class.

Now, I'll create my own class, I'll just call it **Student**, 
and put it in the _Main.java_ source file.

```java  
class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
```

I also want to generate a toString method. 
I'll do that by overriding toString. 
And I'll replace **super.toString**with name. 
Now, I'll go back to the main method, and set up a test 
for a few of these Student instances.

```java  
Student tim = new Student("Tim");
Student[] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};

Arrays.sort(students);
System.out.println(Arrays.toString(students));
```

That's the setup, and I'm going to compare the student **Tim**, 
to a series of other students. 
Before we set up for loop, let's call **Arrays.sort** on this array, 
and print the sorted Students array out.
This code compiles, so let me run that:

```java  
Exception in thread "main" java.lang.ClassCastException: class "Student" cannot be cast to class "Comparable"
        "Student" is in unnamed module of loader 'app'; "Comparable" is in module java.base of loader 'bootstrap'

```
            
And you can see, I get a ClassCastException. 
We get that because our class **Student** can't be cast to Comparable. 
This is an example of not being able to use **Arrays.sort**, on just any class or type we want. 
Your class has to be derived in some way from Comparable, 
meaning it has to implement Comparable, or an interface that extends Comparable. 
I'll do that now. 

```java  
class Student implements Comparable {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return name.compareTo(other.name);
    }
}
```

I'll add "**implements Comparable**" to Student which force me to override compareTo method. 
I'll accept the default implementation, and ignore IntelliJ's warnings on 
that class for the moment. 
If I run the code now:

```java  
[Zach, Tim, Ann]
```
            
I don't get an error, but you can see my students aren't sorted either, 
at least not alphabetically by name, which would be the natural order 
for this class at this point. 
That's because my compareTo method on Student always returns 0, 
so one Student is never less than or greater than another. 
That's not a good implementation of this method. 
I want to compare students by their names, so going back to the Student class, 
I want to look at the compareTo method, it's got an argument with the type Object.

If the argument is really going to be a Student, 
we have to cast this argument to **Student** if we want to compare names.

| Before    | After                                                                 |
|-----------|-----------------------------------------------------------------------|
| return 0; | Student other = (Student) 0; <br/> return name.compareTo(other.name); |

In this code, I cast the method parameter o, to a Student type, 
and assign it to a Student variable, called other. 
This lets me compare other to the current instance, 
so I'll compare the name fields on each, using String's **compareTo** method. 
Now, If I run the code:

```java  
[Ann, Tim, Zach]
```
                
My list gets sorted alphabetically by name, which is good. 
Although you can write the compareTo method this way, you shouldn't. 
When I showed you the declaration of the Comparable interface above, 
you saw that it was a generic type, but here

```java  
return name.compareTo(other.name);
```
                
I'm using the raw version. 
It works, but now I want to discourage you from coding 
your **compareTo** method this way.
Going back to the main method, I'm going to try 
to compare my _Tim_ Student to a String literal, Mary.

```java  
System.out.println("result = " + tim.compareTo("Mary"));
```

This code compiles, but if I run it:

```java  
Exception in thread "main" java.lang.ClassCastException:
class java.lang.String cannot be cast to class "Student" (java.lang.String is in module java.base of loader 'bootstrap';
        "Student" is in unnamed module of loader 'app')
```
            
I get another ClassCastException, because my compareTo method on the Student class 
is trying to cast a **String** to a **Student**, and that's not a good cast for a **String** argument. 
When you implement Comparable on a class, you should specify a type parameter.
So far, we used the raw version of Comparable.
Let's change that. 
Going to the Student class,

```java  
class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
```

I'll include the type parameter, and set it to the current type, **Student**. 
Because of this change, my code doesn't compile anymore. 
I've got the error that the compareTo method isn't implemented. 
But we have it implemented, below. 
Well, not really. 
We have a method called compareTo, yes, 
but now its signature doesn't match the one we need to match,
because it's not typed correctly. 
I need to re-implement this method, and 
I'll again use IntelliJ's feature, to automatically add that.

```java  
@Override
public int compareTo(Object o) {
    Student other = (Student) o;
    return name.compareTo(other.name);
}
```

Notice now the argument in this method has a type of Student, not an Object. 
Now, I'll replace the "_return 0_" statement. 
I'll make this return "_name.compareTo(o.name)_". 
The only difference between these two methods is, I don't have to cast in this one 
because the argument has the Student type already. 
But now, I'm still encountering another error which may be new to you. 
IntelliJ is telling us that _both methods have the same erasure, yet neither overrides the other_.

But now, I'm still encountering another error which may be new to you. 
IntelliJ is telling us that _both methods have the same erasure, yet neither overrides the other_. 
This is a bit complicated, and I'll be talking about this in a later lecture. 
In summary, though, this error means that Java at runtime
can't figure out which method here should get called, the one with Object as an argument, 
or the one with Student. 
I'm going to comment out this entire method, the one that has Object as the argument, 
which will solve the problem for now.

And now, this class compiles, but in the main method, 
I've got a compiler error on the last line.

```java  
//System.out.println("result = " + tim.compareTo("Mary"));
```

And this is actually a good thing that we get errors now, before we run the code, 
because we don't really want to compare a String to a Student object. 
I'll change that comparison to an instance of Student instead, 
passing it my name, all in caps.

```java  
System.out.println("result = " + tim.compareTo(new Student("TIM")));
```

Running this code now:

```java  
----(same)
        [Ann, Tim, Zach]
result = 32
```
                
You can see the array is sorted as before, so that's working. 
And the last line which compares tim in lowercase, to TIM in all uppercases, 
gives us a result equals 32. 
And 32 is the difference between any uppercase letter and lowercase letter, as we saw earlier. 
I've spent a little extra time on this one interface 
to hopefully help you understand it as thoroughly as possible. 
Sorting and comparing objects, meaning instances of your own classes 
will be something that you'll be doing a lot. 
You will use Comparable, when something has a natural order, 
as we saw here with student names. 
Natural order means that your object's **compareTo** method will return a zero 
if one object is considered equal to another, or the equals method returns true, 
when the compareTo method returns 0. 
If you had a list of Students who could be uniquely identified by name, 
then this could be true. 
It's probably more likely that you'd have a Student ID and use Comparable **compareTo** method, 
to sort by student id, for example.

Next, I want to review Comparator, another interface for sorting and comparing, 
and talk about the differences between those two interfaces.
</div>

### The Comparator Interface
<div align="justify">

The Comparator interface is similar to the Comparable interface, 
and the two can often be confused with each other. 
Its declaration and primary abstract method are shown here, 
in comparison to Comparable. 
You'll notice that the method names are different, **compare** vs. **compareTo**.

| Comparator                                                                                          | Comparable                                                                               |
|-----------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| public interface Comparator<T> {<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int compare (T o1, T o2);<br/>} | public interface Comparable<T> {<br/>&nbsp;&nbsp;&nbsp;&nbsp; int compareTo(T o);<br/>}  |

The compare method takes two arguments vs. one for compareTo, 
meaning that it will compare the two arguments to one another, and not one object to the instance itself. 
We'll review Comparator in code, but in a slightly manufactured way. 
It's common practice to include a Comparator as a nested class, 
which we'll talk more about in the next section of the course. 
But I think it's valuable to talk about these interfaces together, 
and when to choose or use one or the other, or both in your class.

I want to get back to the Student class, and add a couple of fields.

```java  
class Student implements Comparable<Student> {

    private String name;
    private static int LAST_ID = 1000;
    private static final Random random = new Random();
    private final int id;
    protected double gpa;

    public Student(String name) {
        this.name = name;
        id = LAST_ID++;
        gpa = random.nextDouble(1.0, 4.0);
    }

    @Override
    public String toString() {
        return "%d - %s (%.2f)".formatted(id, name, gpa);
    }
    
    @Override
    public int compareTo(Student o) {
        return Integer.compare(id, o.id);
    }
```

First, I'll add a couple of private static fields, 
these fields will exist only in memory of the Student class. 
I'm making them private because they're only needed inside the Student methods. 
I'll add the instance fields, student id, and GPA, which stands for Grade Point Average, 
which is how well the student is doing overall.
Notice gpa is protected and not private. 
I'll explain why in a minute. 
Next, I'll change the constructor, and assign values to id and gpa, using my static fields.
Because LAST_ID is a static, there's only one copy in memory. 
And instance that increments it, increments that one copy, 
meaning no two students should get the same id, 
since we're keeping the last id in a central place.
A grade point average can be any value from 0 to 4.0. 
4.0 is **A**, 3.0 is **B**, 2.0 is **C**, and so on. 
Next, I'll change the compareTo method, 
so I'm comparing id's since this is the field that makes our student unique, 
and we'll sort by student id, as the natural sort.
Since my id field is a primitive int, I'll need to box those in wrappers 
to compare the id's. 
I'll do this manually.
I'll compare the id field, using _Integer.valueOf_. 
You may ask why I just didn't use an int calculation here, returning **id - o.id**, 
but it's less error-prone to use Java's comparison of Integers,
so I'll just leverage the _compareTo_ method on _Integer_. 
And I want to print out all the fields I have in the toString method.

I'll return formatted string for id, name and gpa. 
This will print out the student id, student name, and their gpa, 
with two decimal places. 
Let's run the code:

```java  
....(same)
[1001 - Zach (3,18), 1002 - Tim (2,83), 1003 - Ann (3,74)]
result = -1
```
                
The students are sorted by the assigned student id, as you can see, lowest id to highest. 
And the result of comparing a new TIM student with the existing tim student instance, 
is a mines one, and this is because the first tim's id is always less than the new Tim's id. 
Ok, so we've got a comparable and sortable student. 
But what if we want to sort by gpa, to figure out who our best students are? 
We don't want to touch the compareTo method. 
We could write our own mechanism, but we don't have to. 
We just have to create a class that implements Comparator, comparing two Students. 
I'll do this next. 
I'll add this class above the Student class in this file.

```java  
class StudentGPAComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student o1, Student o2) {
        return (o2.gpa + o2.name).compareTo(o1.gpa + o1.name);
    }
}
```

Ok, so I've got a class called StudentGPAComparator, 
and that implements Comparator with a Student type parameter.
And I've implemented the compare method. 
To be technically correct, because I am overriding the compare method, 
I'll add the override annotation above the method. 
I want to compare gpa scores, but if there's a tie, meaning two students have the same GPA, 
I'll sort alphabetically after that.

```java  
class StudentGPAComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student o1, Student o2) {
        return (o2.gpa + o2.name).compareTo(o1.gpa + o1.name);
    }
}
```

Now notice, I have a compiler error, and that's because the name is private on Student. 
For this example, I'll change it.
In the next section, we're going to make this comparator class a nested type. 
However, for now, it's outside the Student class, 
and the only way it can access this field is 
if we make the name field protected or package private.
I'll make it package private, meaning I won't specify an access modifier at all. 
You might want to make this protected, like I did for gpa. 
I wanted to show you that either way will work, in your comparator class. 
The protected modifier would allow subtypes of Student, outside this package, 
to access the field as well. 
But really, in this example, I won't have a subtype, so I'll use this variation. 
That change means our custom Comparator class compiles, but how do I use it? 
Well, it turns out the **Arrays.sort** method has an overloaded version 
that takes a comparator as the second argument. 
I'll create a variable of this type in the main method, then call sort on my students using it.

```java  
Comparator<Student> gpaSorter = new StudentGPAComparator();
Arrays.sort(students, gpaSorter); 
System.out.println(Arrays.toString(students));
```

Ok, so I've got a new instance of my Comparator, notice my reference type there, 
Comparator with <Student>. 
I could have used var for simplicity, but this is the explicit type. 
And next, I call sort, passing it the gpaSorter. 
Now, the sort method won't use the Comparable compareTo method. 
It will instead use this Comparator's compare method when sorting, so that's pretty neat. 
Running this:

```java  
....(same)
[1002 - Tim (2,29), 1001 - Zach (3,47), 1003 - Ann (3,95)]
```
                
You can see my students are sorted by gpa, lowest to highest. 
But that's not what I want, I want to be sorted highest to lowest. 
Now, I could change my Comparator's compare method. 
Let me show you that.

```java  
class StudentGPAComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student o1, Student o2) {
        return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);
    }
}
```

I'm going to swap o1 with o2 in that return statement. And running it this way:

```java  
....(same)
[1002 - Tim (2,13), 1001 - Zach (1,95), 1003 - Ann (1,68)]
```
                
You can see I get my students by the GPA in descending order, 
but you don't really want to do this. 
You want this method to return things in order of lowest to highest, 
and we've reversed that. 
Let me revert to the last change. 
The Comparator interface, unlike Comparable, comes with many other methods, 
most of them static helper methods, but some are default methods. 
Many of these are useful streams, so we'll hold on discussing them until that section, 
but one of them is very convenient for what I want to do here. 
And that's the reversed default method. 
I can call that on the gpaSorter as I pass it to the Arrays.sort method, 
in the main method.

```java  
Arrays.sort(students, gpaSorter.reversed());
System.out.println(Arrays.toString(students));
```

Running that:

```java  
[1003 - Ann (3,31), 1001 - Zach (2,92), 1002 - Tim (2,61)]
```
                
I get my students in reverse gpa order. 
Let me summarize the differences between these interfaces.

| Comparator (int compare(T o1, T o2);)                                                                   | Comparable (int comapreTo(T o);)                                                     |
|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| Compares two arguments of the same type with each other.                                                | Compares the argument with the current instance.                                     |
| Called from an instance of Comparator.                                                                  | Called from the instance of the class that implements Comparable.                    |
| Does not require the class itself to implement Comparator, though you could also implement it this way. | Best practice is to have this.compareTo(o) == 0 result in this.equals(0) being true. |
| Array.sort(T[] elements, Comparator<T>) does not require T to implement Comparable.                     | Arrays.sort(T[] elements) requries T to implement Comparable.                        |

We'll be revisiting Comparator in our nested types discussion, 
and we'll have plenty of opportunity to use both when we explore more of Java's collection types.
</div>

## [f. Generic Reference Types]()
<div align="justify">

My **student** class needs a few fields. 
I'm interested in a student's name, the course they're taking, 
and the year they signed up for the course.

```java  
public class Student {

    private final String name;
    private final String course;
    private final int yearStarted;
    protected static Random random = new Random();
    private static final String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Korhan"};
    private static final String[] courses = {"C++", "Java", "Python"};

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2024);

    }

    @Override
    public String toString() {
        return "%-15s %-15s %d".formatted(name, course, yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }
}
```

I'm going to spend a little extra time setting up this Student class,
with a bit of extra functionality and complexity.
I'll be using this over the next couple of lectures, 
so bear with me for a couple of minutes while I set this up. 
I want three more fields, which I'll use to create random data for a set of Students. 
I'll set up a random field to get random numbers. 
This is protected because I want subclasses to be able to access this helper field. 
These fields will help me create a lot of students with different data. 
I've made them static because I don't want each instance to have this data, 
it can be stored with the class instance instead. 
I'm doing this because I will eventually want a larger set of students. 
Next, I'll create my constructor with no arguments, 
because all the student data will get generated.

I want to randomly generate a single character for the last name, 
so 65 is the integer value for capital A. 
90 is integer value for the capital Z, so I use 91 for the upper bound, 
because the number generated will be exclusive of this upper bound. 
I randomly pick an integer from 0 to 5, to get a first name from my _names_ array, 
then append a space and the last name index to that. 
I randomly get an integer from 0 to 2 to pick a course from the course list.
And the year started, will be a random integer from 2018 to 2023, 
again because its exclusive of the upper bound of 2024. 
I want to create a 2-String method for this next.

And I'll change that code just to return a single line. 
The returned string is formatted and includes name, course, and year started. 
I want name and course to be left justified, so -15 in both cases, 
in the format specifiers, where negative is the indicator to left justify, 
and 15 is the allotted width. 
Finally, I'll generate a getter for one of the fields, yearStarted.

Ok, this is enough code to give me many unique students, 
so I'll go back to the main method in the main class,
and set up a quick test.

```java  
public class Main {

    public static void main(String[] args) {
        
    }

    public static void printList(List students) {

        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}
```

First, I want a method, a static method that I'll call from the main method, 
that'll print my list of Students out. 
This method takes a List of Students. 
Notice I'm not specific about the type of List, so I'm not putting ArrayList
there in the method parameter, but just List. 
Next, I want to generate 10 students, so in the main method, 
I'll set up a for loop.

```java  
int studentCount = 10;
List<Student> students = new ArrayList<>();
for (int i = 0; i < studentCount; i++) {
    students.add(new Student());
}
printList(students);
```

Here, I start with the number of students I want, so that's 10 for now, 
and then I set up an ArrayList of Students.
I assign that to the _students_ variable, which I've declared as a List, 
with a type argument of Student in angle brackets.
Then I loop from 0 to that count, and add a new Student each time to the list. 
And lastly, I want to invoke the printList method. 
I'll pass students to this method. 
If I run this:

```java  
John H          Java            2022
Ann M           C++             2020
Cathy A         Java            2019
Cathy A         Java            2020
Ann R           C++             2022
Ann A           Python          2020
John I          Java            2018
Cathy R         Java            2022
Bill C          Python          2022
Korhan R        Python          2020
```
                
You can see 10 students, whose name, course year started, were randomly generated. 
Ok, so that's the set up for a variety of students.
I next want to create a subclass of Student, which I'll call LPAStudent, 
also in the model package.

```java  
public class LPAStudent extends Student {
    private final double percentComplete;
    public LPAStudent() {

        percentComplete = random.nextDouble(0, 100.001);
    }
}
```

And I'll have that extend Student. 
I'll add an extra field, which isPercentComplete, which indicates how far along
the student is in the course. 
This will be a double. 
And I'll type in my constructor, which again will be no args constructor. 
This code will execute the super constructor implicitly, and that's going 
to generate data for the other fields. 
This class has one additional field, so I want to randomly generate data for it. 
Here, I'm using the random field from the Student class, 
and I get a random number from 0 to 100 percent. 
Next, I'll generate an override for the toString method, 
and I'll include this new field in a formatted string.

```java  
@Override
public String toString() {
    return "%s %8.1f%%".formatted(super.toString(), percentComplete);
}

public double getPercentComplete() {
    return percentComplete;
}
```

Notice; in this formatted String, I have 2 percent signs after the specified percent _8.1f_. 
This is how you print out a percent sign in the output, so it's a specifier for a percent sign. 
After creating getter for PercentComplete, go back to the main method,


```java  
List<LPAStudent> lpaStudents = new ArrayList<>();
for (int i = 0; i < studentCount; i++) {
    lpaStudents.add(new LPAStudent()); 
}
printList(lpaStudents);
```

I'll copy the code above and past it below, I'll change Student to LPAStudent. 
But we have a problem, you can see that, on the last statement, 
the call to the static method here. 
Java tells us _required type is a List of Student, and we're providing an ArrayList of LPAStudent_. 
Isn't this valid? 
Well, no, it's not. 
And this gets pretty confusing, I know.

**NOTE**: This isn't inheritance

We know LPAStudent inherits from Student, and we can pass an instance of LPA Student to any method, 
or assign it to any reference type, declared with the type Student. 
We also know that ArrayList implements List, and we can pass an ArrayList to a method or assign it 
to a reference of the List type. 
And we saw this in both cases for our Student ArrayList. 
But why can't we pass an ArrayList of LPAStudent to the method parameter 
that's declared as a List of **Student**?

![image06]()

Surely, if an LPAStudent is a Student, a List of LPAStudent is ultimately a List of Student. It's very natural to
    assume that a method that takes a List with Students should accept a List with LPAStudents, because LPAStudent is a
    Student after all. But that's not how it works. When used as reference types, a container of one type has no relationship
    to the same container of another type, even if the contained types do have a relationship.

        Let's explore this just a little further, because this concept is sure to trip you up. It's important to understand
    that this restriction has to do with variable reference types and method parameters. First, I'll comment out "lpaStudents.add"
    call for the moment. And I'll use "students.add" to add an LPAStudent to that list in the for loop. And that compiles
    and runs:

                        Ann B           Java            2020
                        Korhan K        Python          2019
                        John Q          C++             2018
                        Bill L          C++             2018
                        Bill G          Java            2021
                        Cathy X         C++             2023
                        Cathy E         Python          2018
                        John P          Python          2023
                        Ann N           Python          2023
                        John L          C++             2021
                        Ann K           C++             2019     50,4%
                        John B          C++             2022     56,9%
                        John V          C++             2018     60,4%
                        Bill U          Java            2020     44,1%
                        Cathy L         Java            2019     55,1%
                        Ann M           Python          2021     98,7%
                        Cathy N         Python          2021     90,6%
                        Ann W           Python          2018     93,8%
                        Ann Q           Java            2020     78,5%
                        Ann S           Java            2018     64,6%

    you can see my last students are printed out with percentage complete, indicating these students are LPAStudents. This
    confirms we can add any type of Student to this List. But consider another next change. I'm going to put LPAStudent
    in <>(diamond) operator, on the right side of the assignment of our students variable.

                            Before                                                  After
          List<Student> students = new ArrayList<>();              List<Student> students = new ArrayList<LPAStudent>();

    And now, we've got a very similar error to the one we had with the printList method. Even if I change List to ArrayList
    in the reference type,

                            Before                                                  After
      List<Student> students = new ArrayList<LPAStudent>();      ArrayList<Student> students = new ArrayList<LPAStudent>();

    I have the same problem. The problem isn't that I'm assigning an ArrayList to a List reference. The problem is the
    type argument in the references. When we specify Student as a type argument to a generic class or container, only
    Student, and not one of its subtypes is valid for this container. And although we can add Students of any type to the
    container, we can't pass a List typed as LPAStudent to a reference variable of List typed with Student. I'll revert
    the last 2 changes, putting back our List of Student to the way it was.

        Now, I'll show you different ways to handle this situation. I'll uncomment the statements below.
</div>


<div align="justify">


</div>


