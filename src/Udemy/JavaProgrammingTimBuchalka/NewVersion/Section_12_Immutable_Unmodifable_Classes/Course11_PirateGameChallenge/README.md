# [Pirate Game Challenge]()
<div align="justify">

I'll create the **Weapon** enum first.

```java  
public enum Weapon {

    KNIFE(0, 10),
    AXE(0, 30),
    MACHETE(1, 40),
    PISTOL(1, 50);

    private final int minLevel;
    private final int hitPoints;

    Weapon(int minLevel, int hitPoints) {
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public static Weapon getWeaponByChar(char firstInitial) {

        for (Weapon w : values()) {
            if (w.name().charAt(0) == firstInitial) {
                return w;
            }
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponsByLevel(int levelOfPlay) {

        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.minLevel > levelOfPlay));
        return weapons;
    }
}
```

So I have _KNIFE_, 
and that will be available at the first level, 
so 0, and it will take off 10 points 
if a knife injures a player. 
Next, will be _AXE_, again level 0, 
and this time 30 hit points.
_MACHETE_ won't be available 
until the second level, 
and that will hurt, so 40 points. 
Next is a _PISTOL_, 
that will also come into play at level 2, 
and take away 50 health points.
Next, I need my two fields, 
both will be private and final, 
since they won't change. 
The first is _minLevel_, an int. 
And the hit points, another int. 
I'll generate the constructor. 
Then the getters for both. 
I'll add some helper methods. 
The first method is going
to get a weapon from the enum values, 
using a single character. 
It'll be public and static, 
returning a **Weapon**. 
I'll call it _getWeaponByChar_, 
and it'll take a **char**, 
I'll call that _firstInitial_. 
I'll loop through each **Enum** constant,
so through the values. 
I'll get the first character 
from the constant's name, 
and if it matches _firstInitial_, 
I'll return it from this method. 
If a match wasn't found, 
I'll return the first weapon in the enum.
The next method will get a list of weapons available, 
based on the level of play. 
Again, this will be public and static, 
and I'll call it get _WeaponsByLevel_, 
and it'll take an integer, the level of play. 
I'll first set up a new array list of all weapons, 
using enum set's _allOf_ method to populate it. 
Next, I'll just call the remove 
if method on that list, and pass it a predicate lambda, 
a conditional statement in other words. 
I'll compare min level to the user's level of play. 
If the weapon's min level is greater than a player's level, 
it'll get removed from this list.
I'll return the remaining weapons, 
meaning those that have a minimum level, 
less than or equal to the player's current level. 
Getting back to the **Main** class,

```java  
public class Main {

    public static void main(String[] args) {

        Weapon weapon = Weapon.getWeaponByChar('P');
        System.out.println("weapon = " + weapon + ", hitPoints=" + weapon.getHitPoints() + ", minLevel=" + weapon.getMinLevel());

        var list = Weapon.getWeaponsByLevel(1);
        list.forEach(System.out::println);
    }
}
```

I'll remove the code from the previous section 
for the **Shooter game** test. 
First, I want to test getting a weapon by a character. 
I'll create a local variable weapon, 
and assign that the result of calling 
my _getWeaponByChar_ method, with the letter _P_. 
And I'll print the weapon out, 
with its hit points and min level. 
Running that,

```html  
weapon = PISTOL, hitPoints = 50, minLevel = 1
```
                    
You can see I get a _Pistol_, its _hitPoints_ is 50, 
and the minimum level equals 1. 
I'll test the next method, _getWeaponsByLevel_.
I'll just use var here as the type, variable name is _list_, 
and that gets the result of calling 
my _getWeaponsByLevel_ method, passing that 1. 
I'll print the weapons I get back.

```html  
weapon = PISTOL, hitPoints = 50, minLevel = 1
KNIFE
AXE
MACHETE
PISTOL
```
                    
And you can see, I can use four weapons at level 2, 
the knife, axe, machete and pistol. 
Ok, so all good so far. 
I'll work on the **Pirate** Class next.
I'll create that in my **pirate** package too.
This will implement the **Player** interface.
This code doesn't compile until I implement the abstract method,
which for player is just a method, called _name_.
I'll replace _null_ with _name_,
the field, in that return statement.

```java  
public class Pirate implements Player {

    private final String name;
    private final Map<String, Integer> gameData;
    private final List<String> townsVisited = new LinkedList<>();
    private Weapon currentWeapon;

    public Pirate(String name) {
        this.name = name;
    }

    //------------------------------------------------
    {
        gameData = new HashMap<>(Map.of(
                "health", 100,
                "score", 0,
                "level", 0,
                "townIndex", 0
        ));
        visitTown();
    }

    @Override
    public String name() {
        return name;
    }
}
```

Right now, I don't yet have a field for _name_. 
I'll add that next, and a few other fields as well. 
_name_ will be private, final, and a string. 
I want some _gameData_, like the score, 
the health, the current level, and the current town index. 
I could make all these integer fields, 
but I'll just create a map, 
with string as the key, and integer as the value. 
If I find I want to add more pirate data later, 
then it's pretty straightforward, to just add a new mapping. 
I'll keep a list of the _townsVisited_ while playing. 
This will include towns across all levels,
and I'll make it a linked list, so it'll be in insertion order, 
meaning the order the pirate visits the towns. 
I'll also add a field to keep track of the pirate's current weapon. 
I'll generate a constructor, with just the _name_. 
I want to initialize my _gameData_, 
and I could do that here in this constructor, 
but I think this is a good opportunity 
to use an _instance initializer_. 
At this point, the initialization process 
will be the same for every player. 
Maybe some day I'd want to store 
and restore a session, 
but that's for a later date. 
If I use an _instance initializer_, 
this code will get executed, 
regardless of what constructor is used. 
I'll put this block of code immediately 
after the constructor.
First, I'll just add a comment, just some dashes, 
so it's a bit easier to see 
this code is separate from the constructor. 
I'll assign _gameData_ to a new instance of a hash map, 
since order doesn't matter for this information.
We can create a map by specifying a series of key value pairs, 
using the `Map.of` method. 
That's what I'll do here.
I'll start passing the list of key value pairs, 
so first health, with 100. 
Then score, with an initial value zero.
Next, the playing level, and that starts at zero.

```java  
public Weapon getCurrentWeapon() {
    return currentWeapon;
}

void setCurrentWeapon(Weapon currentWeapon) {
    this.currentWeapon = currentWeapon;
}

int value(String name) {
    return gameData.get(name);
}

private void setValue(String name, int value) {
    gameData.put(name, value);
}
```

Next, I'll generate a getter 
and setter for the current weapon. 
I'll remove the modifier on _setCurrentWeapon_ 
so only classes in this package can call it.
For the user data, 
I want to create some setter 
and getter methods of my own.
First, I'll create a _value_ method. 
This should be package-private, 
so the _PirateGame_ can call it. 
It'll return an **int**, 
and take a **string**, 
which will be the _key_ 
to the pirate data element on _gameData_. 
This can just return the map _value_, 
based on the _key_ passed. 
Next, there'll be a _setValue_ method. 
This one will be private, 
only the **Pirate** class will be able 
to set this _value_. 
The return type is void, 
and it'll take a **String** for the key, 
and an _integer_ for the new _value_. 
This method will just put the new _value_ in the map, 
using the _name_ passed.

```java  
private void adjustValue(String name, int adj) {
    gameData.compute(name, (k, v) -> v += adj);
}
```

The next method will adjust a value in this data map. 
This will be private, again encapsulated, 
so that only this **Pirate** class 
can adjust its own data. 
Its return type is void, 
and it should take a field name, 
and an adjustment value, an _integer_. 
I'll use map's _compute_ method here, 
using a lambda, that simply adds 
the adjusted amount to the existing _value_.

```java  
private void adjustHealth(int adj) {

    int health = value("health");
    health += adj;
    health = (health < 0) ? 0 : ((health > 100) ? 100 : health);
    setValue("health", health);
}
```

I'll add a special method 
just for a player's health though, 
called _adjustHealth_, 
and that's because health should not 
ever go below 0 or above 100.
This method will be private and void, 
and take an adjustment argument, an integer.
First, I want to get the current value of health. 
The adjusted value can simply be added 
because the adjustment could be negative.
Next, I'll use a nested ternary operator. 
If the adjustment took health's value below zero, 
it will return 0, or above 100, it returns 100. 
I'll use _setValue_ to put this back in the map. 
IntelliJ may give you a hint to change this code 
to a simpler form, using `Math.min`. 
You're welcome to take that suggestion, 
but since I haven't reviewed the **Math** class, 
I'm going to ignore this for now. 
I'll be covering library classes like **Math**, 
in some detail in a later section, 
but that shouldn't stop you from exploring it 
on your own if you're curious.

```java  
boolean useWeapon() {

    System.out.println("Used the " + currentWeapon);
    return visitNextTown();
}

boolean visitTown() {

    List<String> levelTowns = PirateGame.getTowns(value("level"));
    if (levelTowns == null) return true;
    String town = levelTowns.get(value("townIndex"));
    if (town != null) {
        townsVisited.add(town);
        return false;
    }
    return true;
}
```

Now, I want some operational methods, 
meaning game related methods. 
These will be the methods, 
_useWeapon_ and _visitTown_.
I'll make _useWeapon_ package-private, 
so my **Pirate Game** can call it, 
and it'll return a **boolean**. 
For now, this will simply print 
the current weapon out. 
It will return false to start with. 
The _visitTown_ method will be 
package-private also, 
and return a **boolean**. 
I'll set the town 
to a _dummy_ variable right now. 
Later I'll get the right town 
from data on the **Pirate Game**. 
If the town is not _null_, 
I'll add it to the _townsVisited_ field. 
I'll return false, 
so if the game uses this, the play will continue. 
If a town couldn't be retrieved, 
the game needs to end, so I'll return true.
I'm going to make a call
to this method in my initializer code.

```java  
@Override
public String toString() {

    var current = ((LinkedList<String>) townsVisited).getLast();
    String[] simpleNames = new String[townsVisited.size()];
    Arrays.setAll(simpleNames, i -> townsVisited.get(i).split(",")[0]);
    return "---> " + current +
            "\nPirate "+ name + " " + gameData +
            "\n\ttownsVisited=" + Arrays.toString(simpleNames);
}
```

Finally, I want to include a _toString_ method, 
so I'll generate that with no data to start. 
I'll be replacing that 
return statement with code 
to print out my pirate's information. 
First, I'll get the list of towns visited, 
and cast that to a linked list. 
This lets me use linked list methods. 
Specifically, I want to use the _getLast_ method 
to retrieve the current town. 
Next, I just want a list of simple town names, 
without island names, 
so I'll set up a new string array. 
I'll use `arrays.setAll`, 
to just get the simplified town names, 
without the Island included.
I'll return the current town, 
plus the pirate name and data. 
I'll include the towns visited for good measure. 
I can test this 
by creating a **Pirate** in my _main_ method, 
so I'll do that.

```java  
Pirate tim = new Pirate("Tim");
System.out.println(tim);
```

I'll create a new pirate named _Tim_. 
And I'll just print _Tim_ out. 
Running that code:

```html  
---> Bridgetown, Barbados
Pirate Tim {health=100, score=0, level=0, townIndex=0}
townsVisited=[Bridgetown]
```

I get my pirate's information. 
You can see it first prints the current town's data, 
in this case My Town, somewhere. 
Next, I get the pirate's game data, 
and a list of the towns the pirates visited, 
which of course is just My Town. 
Now, I'll create the **Pirate Game** class, 
and use the **Game Console** to play it. 
I'll create this class in the **pirate** package.

```java  
public class PirateGame extends Game<Pirate> {

    public PirateGame(String gameName) {
        super(gameName);
    }
    
    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }
    
    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {

        return null;
    }
}
```

This will extend the **Game** class, 
and I'll make the type argument for the **Game**, 
my **Pirate** player. 
I need to implement **Game**'s abstract methods, 
and I'll use IntelliJ to do that. 
For the _createNewPlayer_ method, 
I want to return a new **Pirate** instance, 
passing name to the **Pirate** constructor. 
I'll hold off on 
finishing the _getGameActions_ method for now.
I still have an error with this class, 
and if I hover over that, 
you can see there's no default constructor, 
and I can pick, _Create_ constructor matching _super_. 
This constructor fixes the last of my compiler errors 
for this class, so next, 
I'm going to set up some static fields.

```java  
private static final List<List<String>> levelMap;
```

I want the first to be private, 
static and final, a **List**. 
This will actually be a two-dimensional list, 
the first level will represent the level of play. 
What I mean by this is, because it's a list, 
the data at element zero, is the first level's data,
the data at element one, 
is the second level's data, and so on. 
This list will contain a nested list of towns, 
so it's type argument for the list, 
is another list, with type String. 
I could've created a **Level** class,
with a list of towns on it. 
That's a perfect way to go, 
because you can expand that class 
to include a lot more variety by each level. 
For now, I'm trying to keep this code relatively simple. 
This isn't going to compile yet, 
because I've made it final, without initializing it. 
Here, I'll use a _static initializer_ to set this data.

```java  
//------------------------------------------------------------
static {
    levelMap = new ArrayList<>();
    System.out.println("Loading Data...");
    loadData();

    if (levelMap.size() == 0) {
        throw new RuntimeException("Could not load data, try later");
    }
    System.out.println("Finished Loading Data.");
}
//------------------------------------------------------------
```

I'll add this comment line, 
so it's easier to recognize 
the _static initializer_ code block. 
A _static initializer_ starts with the word _static_ 
before the opening curly brace. 
First, I'll initialize _levelMap_ to a new array list.
I'll print a message, that _data is loading_. 
I'll call a method called _loadData_, 
which will populate my _levelMap_ with data. 
If that map comes back and has no data in it, 
size is zero in other words, 
I'll throw a runtime exception. 
This means nobody can use this **PirateGame** class 
if this process fails. 
Otherwise, I'll print that the data finished loading. 
I'll include another comment line. 
This won't compile until I implement the _loadData_ method.

```java  
private static void loadData() {

    // Level 1 Towns
    levelMap.add(new ArrayList<>(List.of(
            "Bridgetown, Barbados",
            "Fitts Village, Barbados",
            "Holetown, Barbados"
    )));
    // Level 2 Towns
    levelMap.add(new ArrayList<>(List.of(
            "Fort-de-France, Martinique",
            "Sainte-Anne, Martinique",
            "Le Vauclin, Martinique"
    )));
}
```

Because it's being called from a _static initializer_, 
this method needs to be _static_, 
and I'll make it _private_, and _void_, with no arguments. 
Each of my levels will represent a different _Caribbean_ island, 
so I'll set up towns by what island they're on. 
I'm only going to set up two levels right now. 
I'll include a comment that the first group is level 1 towns. 
I want to add an array list of town to my level map.
The first town is _Bridgetown_, _Barbados_. 
Next will be _Fitts Village_. 
And the last town on this level is _Holetown_. 
I'll copy those six lines, and paste that copy right below. 
I'll change the comment to say Level 2 towns. 
I'll change _Bridgetown_, _Barbados_, 
to _Fort-de-france_, _martinique_.
The second town will be _Sainte-Anne Martinique_. 
The last town on level 2 will be _Le-Vauclin Martinique_. 
Before I test this, I want to include another static method, 
to return towns for a specified level.

```java  
public static List<String> getTowns(int level) {

    if (level <= (levelMap.size() - 1)) {
        return levelMap.get(level);
    }
    return null;
}
```

I'll make this _public_ and _static_, 
and take the playing _level_ as a parameter. 
First, I'll check that the _level_ has towns set up for it. 
If so, It'll return those towns for that _level_. 
If the player's _level_ is greater than the game's _level_, 
it'll return _null_. 
I'll test this in the **Main** class's _main_ method.

```java  
PirateGame.getTowns(0).forEach(System.out::println);
System.out.println("-----------------------------------------------------");
PirateGame.getTowns(1).forEach(System.out::println);
```

I'll add a call to _getTowns_ on the **PirateGame** class, 
and first pass it 0, for the first level. 
I'll print a separator line,
and print the towns for the second level. 
Running that:

```html  
Loading Data...
Finished Loading Data.
---> Bridgetown, Barbados
Pirate Tim {health=100, score=0, level=0, townIndex=0}
    townsVisited=[Bridgetown]
Bridgetown, Barbados
Fitts Village, Barbados
Holetown, Barbados
-----------------------------------------------------
Fort-de-France, Martinique
Sainte-Anne, Martinique
Le Vauclin, Martinique
```
                    
First, you can see the loading data 
and finished load data messages 
from the _static initializer_ code. 
I only see those printed once, 
because that's only called once. 
A _static initializer_'s code is usually called 
the first time a class member is referenced, 
as I'm doing in the _main_ method. 
After that, I get three towns for the first level, 
_Bridgetown_, _Fitts Village_, and _Holetown_, all in _Barbados_. 
The second level is all towns on the island of _Martinique_.
This code was all run without having 
an instance of the **PirateGame** created. 
You can imagine loading this data from a database or a file, 
and if it failed, you'd want to prevent anything further from happening. 
Next, I'll get back to the **PirateGame**.

```java  
private boolean useWeapon(int playerIndex) {
    return getPlayer(playerIndex).useWeapon();
}

@Override
public boolean executeGameAction(int player, GameAction action) {

    getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
    return super.executeGameAction(player, action);
}
```

I want a method here 
that will delegate to the _useWeapon_ method 
I created on the **Pirate** class. 
In this class, the method needs to follow a pattern, 
because of the way I set up the **GameConsole**. 
These methods need to return a **boolean** and take an **integer**, 
the player's index. 
This new method will be _private_, 
and named _useWeapon_. 
The player is retrieved from the player's list, 
and the player's _useWeapon_ is chained to that. 
This method returns a _boolean_,
so I can just return that value here. 
To make this work, 
I'm going to first override the _executeGameAction_ method,
that's on the **Game** class. 
The only reason I want to override this method is, 
so I can set the weapon on my pirate player,
before executing the _useWeapon_ method. 
I'll get player from the list, 
and then set the current weapon on that player. 
I can get the weapon by using the action's key 
that's passed to this method, 
which is the key the user entered at the console. 
Finally, I have to set up the menu options, 
which will make everything work. 
This gets done in the _getGameActions_, 
which was the abstract method on **Game**. 
I've already included this on my **PirateGame**.

```java  
@Override
public Map<Character, GameAction> getGameActions(int playerIndex) {

    Pirate pirate = getPlayer(playerIndex);
    System.out.println(pirate);
    List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

    Map<Character, GameAction> map = new LinkedHashMap<>();
    for (Weapon weapon : weapons) {
        char init = weapon.name().charAt(0);
        map.put(init, new GameAction(init, "Use " + weapon, this::useWeapon));
    }
    map.putAll(getStandardActions());
    return map;
}
```

Now I need to add some code in there. 
I'll replace the return _null_ statement. 
I'll first get the pirate information and print it. 
I'll get the weapons for the current level. 
I'll create a local map, with a _key_ of **Character**, 
and _value_ of **GameAction**, 
which is the return type of this method. 
I'll make that a new linked hash map, 
because I want these to stay in the order I add them. 
This map will get returned from the method. 
But I also need to add a way for the user 
to select which weapon to use. 
I'll add code to loop through the weapons 
available at the player's level. 
I'll get the first initial of the weapon's name, 
which will be the menu key.
I'll add a new entry to the map,
using the key, and creating a new game action. 
The prompt I create will include the weapon name. 
The method reference for any weapon will be `this::useWeapon`. 
Finally, I'll add all the standard actions from the **Game**'s class. 
These actions were quit the game, or get player information. 
Before we can play, 
I need to fix the _visitTown_ method on my **Pirate** class.

```java  
boolean visitTown() {

    List<String> levelTowns = PirateGame.getTowns(value("level"));
    if (levelTowns == null) return true;
    String town = levelTowns.get(value("townIndex"));
    if (town != null) {
        townsVisited.add(town);
        return false;
    }
    return true;
}
```

I'll replace the town assignment statement. 
First, I want to get the towns for pirate's playing level, 
and assign that to a local variable, levelTowns. 
If there are no towns, meaning, levelTowns is _null_, 
the game is over, so I'll return true here. 
I'll get the town using the player's town index, 
and leave the rest of the code the way I had it.
And that's it, we should be able to play this pirate game,
and use some weapons. 
I'll go back to the **Main** class:

```java  
var console = new GameConsole<>(new PirateGame("The Pirate Game"));
int playerIndex = console.addPlayer();
console.playGame(playerIndex);
```

And set up a new variable called _console_, 
using _var_ as its type, 
and instantiate a new **GameConsole**. 
I'll pass a new **PirateGame** to the new console. 
I'll add a player, returning the index 
and assigning it to _playerIndex_. 
And I'll play the game, 
using this current player's index, 
meaning it's his turn to play. 
If I run that:

```html  
Welcome to The Pirate Game, Tim!
---> Bridgetown, Barbados
Pirate Tim {score=0, health=100, level=0, townIndex=0}
    townsVisited=[Bridgetown]
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Print Player Info (I)
    Quit Game (Q)
Enter Next Action: K<<
```

I'll get prompted to enter my username in there, 
so I'll type in _Tim_. 
Then I get, _Welcome to the Pirate Game Tim_.
I can see I'm in _Bridgetown_, _Barbados_ to start. 
Information about the player is printed, and here, 
I can see the player data, all initialized,
health = 100, score = 0, level = 0, and townIndex = 0, as well. 
I've also got my custom menu of actions printed here,
_Use KNIFE_ if I enter a _K_, 
or _Use AXE_, if I enter _A_, 
as you can see. 
First, I'll use the _KNIFE_, 
so I'll enter _K_ at the prompt:

```html  
-------------------------------------------
---> Fitts Village, Barbados
Pirate Tim {score=50, health=100, level=0, townIndex=1}
    townsVisited=[Bridgetown, Fitts Village]
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Print Player Info (I)
    Quit Game (Q)
Enter Next Action: q<<
```

And there you can see it called the use weapon method, 
but it knows I selected _KNIFE_ to use, so that's kind of nice.
If I enter I next, I get information, first about the town I'm in, 
and then about my own status. 
Right now, I'm not doing anything with health or score, 
or changing levels. 
I'll press _q_ to quit:

```html  
-------------------------------------------
Sorry to see you go, Tim
```
                
And the game says, _Sorry to see you go Tim_. 
If I add one more method to the **Pirate** class, 
it'll make this game a bit more interesting.

```java  
private boolean visitNextTown() {

    int townIndex = value("townIndex");
    var towns = PirateGame.getTowns(value("level"));
    if (towns == null) return true;
    if (townIndex >= (towns.size()-1)) {
        System.out.println("Leveling up! Bonus: 500 points!");
        adjustValue("score", 500);
        adjustValue("level", 1);
        setValue("townIndex", 0);
    } else {
        System.out.println("Sailing to next town! Bonus: 50 points!");
        adjustValue("townIndex", 1);
        adjustValue("score", 50);
    }
    return visitTown();
}
```

I'll make this _private_,
and call it _visitNextTown_. 
It will return a **boolean**. 
First, I'll get the current town index. 
Next, I'll get the _towns_ 
for the player's current level. 
If I don't get any towns back, 
I'll return true, so the game will end. 
I'll return the result of calling _visitTown_ 
from this method. 
Remember this gets the town, and returns true, 
meaning the game is over 
if no more towns can be found. 
After I get the towns for the level, 
I'll check if my player's town index is 
greater than or equal to 
the number of towns on this level, minus one. 
If this is true, 
the pirate's visited every town on the level, 
so he'll level up. 
I'll print that he's leveled up, 
and getting bonus points. 
I'll use my _adjustValue_ method 
to add 500 points to the score. 
I'll bump up the level by one. 
I'll reset the town index to zero, 
so it starts at the next level's first town. 
If the pirate's not leveling up, 
I just want to go to the next town, 
so I'll add an else statement. 
I'll add a message that
our pirate's _sailing to the next town_, 
with 50 bonus points. 
I'll increase the town index by 1. 
I'll add 50 to the score. 
For now, I'll simply call this method 
every time the pirate uses his weapon. 
Instead of returning false, 
I'll return the value from calling _visitNextTown_. 
Later we can build different conditions, 
like finding treasure, 
or shooting a certain number of other players, 
or something like that. 
I'll rerun the code, entering _Tim_ for the name. 
I'll enter _K_, and now you can see an extra message.

```html  
-------------------------------------------
Used the KNIFE
Sailing to next town! Bonus: 50 points!
-------------------------------------------
```

It says _Sailing to next town_, 
and _bonus is 50 points_. 
I'll enter _I_, 
I'll see my score is now 50, 
my town index is 1, 
and I have two towns in the _townsVisited_ list. 
Let's keep going. 
I'll select an A next. 
The message says used the axe, and again, 
sailing to the next town. 
I'll select _K_ again. 
Now, I see that I've leveled up, 
with 500 bonus points, 
but I've also got more weapons now. 
I now can select machete or pistol. 
If I select _I_, you can see, sure enough, 
I'm now in a town on the island of Martinique, 
my score is 600, and my level is 1. 
If I continue to play, 
the game will end, after I run out of towns. 
That's it for this challenge. 
</div>