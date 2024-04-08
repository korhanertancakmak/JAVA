# [The Final Section Challenge]()
<div align="justify">

Ok, so I'm back in the **GameConsole** Project,
and the first thing I'll do is, create the new enums.
All my types will be created in the **pirate** package.
The first enum is **Loot**:

```java  
public enum Loot {

    SILVER_COIN(5),
    GOLD_COIN(10),
    GOLD_RING(125),
    PEARL_NECKLACE(250),
    GOLD_BAR(500);

    private final int worth;

    Loot(int worth) {
        this.worth = worth;
    }

    public int getWorth() {
        return worth;
    }
}
```

And I'll set up five constants for now, 
each with its worth in parentheses. 
First, _SILVER_COIN_, for 5 points.
Next, a _GOLD_COIN_ for 10.
_GOLD_RING_ for 125. 
Now, a _PEARL_NECKLACE_ for 250. 
Lastly, a _GOLD_BAR_ for 500 points, 
and I'll end the list with a semicolon. 
I'll add a public final int field, called _worth_, 
after the constants declaration.
I'll generate the constructor with that field. 
Next I'll generate a getter. 
That's all I need for **Loot**. 
I'll just copy this enum, and paste it in the **pirate** package, 
calling the new enum **Feature**.

```java  
public enum Feature {

    ALLIGATOR(-45),
    ALOE(5),
    JELLYFISH (-10),
    PINEAPPLE(10),
    SNAKE(-25),
    SPRING(25),
    SUN_POISON(-15);

    private final int healthPoints;

    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
```

I'll use IntelliJ's replace function 
to change all references to the _worth_ field, 
to _healthPoints_, turning off the case matching, 
and selecting _replaceAll_. 
I'll just change the _H_ in get health points to uppercase. 
Next, I'll remove the constants from that. 
I'll add new constants for some of the different things 
a person might encounter or run into, on an island.
_ALLIGATOR_ will be minus 45,
_ALOE_, will be 5,
_JELLYFISH_ will be -10, 
and _PINEAPPLE_, will be plus 10.
_SNAKE_ will be minus 25, 
a _SPRING_ with freshwater will restore 25 points to our pirate's health, 
and lastly, let's say _SUN_POISON_ will be minus 15. 
That's really it for the **Feature** enum. 
Next, I want to create my **Combatant** class. 
I'm going to copy the **Pirate** class, and paste that, 
naming the new copy **Combatant**.

```java  
public sealed abstract class Combatant implements Player permits Islander, Pirate, Soldier {

    private final String name;
    private final Map<String, Integer> gameData;
    private Weapon currentWeapon;

    public Combatant(String name) {
        this.name = name;
    }

    public Combatant(String name, Map<String, Integer> gameData) {
        this.name = name;
        if (gameData != null) {
            this.gameData.putAll(gameData);
        }
    }
}
```

I'll make this abstract. 
Now, I'll go through and start removing members, 
that wouldn't apply to the other players.
I'll remove the _townsVisited_ field. 
If I look at the initializer, 
I think I'll just have the average _combatant_
have two mapped fields, _health_ and _score_. 
I'll remove level and town index, 
since those are specific to a pirate. 
I'll also remove the call to _visitTown_ here. 
Scrolling down, I can keep all these methods, 
all the way down to the _useWeapon_ method. 
I want to change that method:

```java  
boolean useWeapon(Combatant opponent) {

    System.out.print(name + " used the " + currentWeapon);
    if (new Random().nextBoolean()) {
        System.out.println(" and HIT *** " + opponent.name() + "! ***");
        opponent.adjustHealth(-currentWeapon.getHitPoints());
        System.out.printf("%s's health=%d, %s's health=%d%n",
                name, value("health"),
                opponent.name(), opponent.value("health"));
        adjustValue("score", 50);
    } else {
        System.out.println(" and missed!");
    }
    return (opponent.value("health") <= 0);
}
```

First to take a **Combatant** type, an _opponent_. 
I'll remove the current statements that are there. 
Now I'll add code, that will let _opponents_ use weapons 
against each other. 
I'll set up an if condition next. 
Here, I'll use another method on the **Random** class, 
that just gives me a random boolean value back. 
If that's true, I'll say my player was _HIT_, 
and print the _opponent_'s name. 
Next, I'll adjust the _opponent_'s health, 
I'm going to make that negative, 
so it will subtract the _weapon_'s hit points 
from the _opponent_'s health. 
I'll print out the _name_ and _health_ of both _opponents_. 
I'll add 50 points to the _combatant_'s score. 
If false came back from the _nextBoolean_ method, 
I'll print that the player missed. 
I'll return the _opponent_'s health from this method. 
If the _opponent_ is killed, 
this will return true. 
Right below this method, I'll remove the _visitTown_ method. 
That's a method specific to a pirate, 
so it shouldn't be here. 
I'll also remove both the _toString_ method, 
and the _visitNextTown_, the last two methods in this class. 
I want the combatant _toString_ to be much simpler, 
printing just the name, 
so I'll generate that. 
I'll replace the statement generated,
with just return name.
In addition to the _toString_ method, 
I want another, and I'd like it to print out more detailed information. 
I'll make it public, return a String and name it information,
with no parameters. 
I want to make one more change to this class, 
and what I want is a second constructor:

```java  
public Combatant(String name) {
    this.name = name;
}

public Combatant(String name, Map<String, Integer> gameData) {
    this.name = name;
    if (gameData != null) {
        this.gameData.putAll(gameData);
    }
}
```

So I'll scroll up and generate one below the current one. 
I'll select the first two fields highlighted. 
I've got an error on this constructor 
because I'm trying to assign a value to _gameData_, 
but it's final and initialized by the instance initializer. 
What I really want to do is add keyed elements that can be mapped. 
I'll remove that assignment statement. 
Instead of assigning data here, 
I'll first check that the argument's not null. 
If it isn't, I'll add the map passed to my current game data. 
This is mutating the gameData map by adding new key values, 
and in this case, it's a desired effect.
Finally, I need to change the modifiers on any method 
that sets or adjusts the game data, so I'll make all of those protected. 
This lets my subclasses use them.
Ok, that's the **Combatant** class. 
This should simplify the **Pirate** class quite a bit, 
so I'll edit that next.

```java  
public final class Pirate extends Combatant {

    private final List<Town> townsVisited = new LinkedList<Town>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate(String name) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();
    }
}
```

First, I want this class to extend **Combatant**.
I'll replace implements **Player** 
with _extends_ **Combatant** to do that.
Again, I'm going to go through
and delete a bunch of stuff. 
First, the fields. 
I'll remove all but _townsVisited_.
I'll add a couple of new fields 
after the _townsVisited_ field, a list of **Loot**, 
a list of **opponents**, 
and a list of **features**. 
These will get updated every time a pirate moves from town to town, 
so they'll be changing. 
I'll next delete the constructor, which has errors. 
I'll regenerate a constructor, selecting only name.
I'm going to change this constructor's call to super.
I want to include a second argument,
a map of additional fields a pirate will have.
I'll pass a call to `Map.of`, and include level,
with an initial value of zero, and a town index,
also initially zero.
I'll also call _visitTown_ from that constructor.
I'll remove the instance initializer altogether.
I can remove all the methods that are on pirate,
so I'll remove the name method next.
I'll remove the getter and setter for the current weapon.
I'll remove the methods that manipulate game data,
so value, set value, adjust value and adjust health.

```java  
boolean useWeapon() {

    int count = opponents.size();
    if (count> 0) {
        int opponentIndex = count - 1;
        if (count > 1) {
            opponentIndex = new Random().nextInt(count + 1);
        }
        Combatant combatant = opponents.get(opponentIndex);
        if (super.useWeapon(combatant)) {
            opponents.remove(opponentIndex);
        } else {
            return combatant.useWeapon(this);
        }
    }
    return false;
}
```
 
The _useWeapon_ method has a compiler error 
because _currentWeapon_ is private on the parent.
I'll replace that with a call to `super.getCurrentWeapon`. 
I'll leave _visitTown_ alone for the moment.
That brings me to the _toString_ method.

```java  
public String information() {
    var current = ((LinkedList<Town>) townsVisited).getLast();
    String[] simpleNames = new String[townsVisited.size()];
    Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
    return "---> " + current +
            "\n" + super.information() +
            "\n\ttownsVisited=" + Arrays.toString(simpleNames);
}
```

I'm going to remove the annotation 
and rename this method to _information_. 
And there's errors on _name_ and _gameData_,
I'll replace this statement. 
These changes made the **Pirate** class a lot simpler. 
I'm going to make this class _final_, 
one of my three options if I'm extending a sealed class. 
Before I make any further changes to this class, 
I'll go seal the **Combatant** class. 
I'll add the sealed keyword before the abstract modifier.
If I hover over the error I get, 
I can select, _Add missing subclasses to the **permits** clause_. 
That'll generate the clause for me. 
Right now I only have one class extending **Combatant**, 
and that's my **Pirate**. 
Before I add any others, 
I'll first go to my **PirateGame** class, 
and override the print player method, 
which just prints the _toString_ method on a player.

```java  
@Override
public boolean printPlayer(int playerIndex) {

    System.out.println(getPlayer(playerIndex).information());
    return false;
}
```

I want it to use the new pirate's information method instead. 
First, I'll just return false. 
Next I'll print the data I get from the information method. 
I'll get the player using the index, 
and chain a call to the information method.
Now, I should be able to play my **PirateGame**. 
It won't be able to change towns, 
since I changed the _useWeapon_ method,
and removed _visitNextTown_ from that. 
In this challenge, the pirate will visit a new town 
after finding all the possible loot for that town, 
but I'll be implementing that a bit later. 
Before I run this code, 
I'll uncomment the last three lines in the _main_ method.

```java  
var console = new GameConsole<>(new PirateGame("The Pirate Game"));
int playerIndex = console.addPlayer();
console.playGame(playerIndex);
```

Running the Main class's main method,

```html  
Loading Data...
Finished Loading Data.
Enter your playing name: Tim
```

I'll enter _Tim_ for the player _name_.

```html  
Welcome to The Pirate Game, Tim!
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Print Player Info (I)
    Quit Game (Q)
Enter Next Action: I
```

I'll select _I_,

```html  
-------------------------------------------
---> Bridgetown, Barbados
Tim {health=100, score=0, level=0, townIndex=0}
    townsVisited=[Bridgetown]
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Print Player Info (I)
    Quit Game (Q)
Enter Next Action: q
```

That gives me the current town, 
and _Tim_'s game data, as it did before. 
The basics are still working, so that's good, 
and I'll select _q_

```html  
-------------------------------------------
Sorry to see you go, Tim
```
                        
To quit out of that. 
Next, I'll create two more classes that extend player. 
First, I want a class named **Islander**.

```java  
public final class Islander extends Combatant {
    public Islander(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
```

I'll add extends **Combatant** to that declaration. 
I get an error on this because I need to have a constructor. 
I'll generate that, selecting _Create_ constructor matching super, 
picking just name for the field. 
I'm going to add a _Weapon_ parameter here, 
as the second parameter in this constructor. 
I'll call the _setCurrentWeapon_ after the call to super.
I still have an error because **Combatant** is sealed. 
I'll choose to add **Islander** to the _permits_ list. 
Next, I have to decide if this is going to be sealed, non-sealed or final. 
I'll take IntelliJ's hint, and make it final. 
I don't plan to extend it any further. 
Next, I'm just going to copy this class and paste it, naming it _Soldier_.

```java  
public final class Soldier extends Combatant {
    public Soldier(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
```

If I hover over the error on **Combatant**, 
I'll pick, Add _Soldier_ to permits list. 
Ok, so now I have three subclasses for my sealed **Combatant** class. 
If I examine that class, 
you can see that IntelliJ did indeed 
add **Islander** and **Soldier** to the _permits_ clause. 
Now, I'll create a **Town** record, 
and start making the pirate game more interesting.
I'll create a new record called town in the **pirate** package.

```java  
public record Town(String name, String island, int level,
                   List<Loot> loot,
                   List<Feature> features,
                   List<Combatant> opponents) {

    private <T> List<T> randomReduced(List<T> list, int size) {

        Collections.shuffle(list);
        return list.subList(0, size);
    }
}
```

This record will have six fields, 
the first the name of the town, 
the second the island name, 
the third will be an integer, 
the level at which the town will be played.
I'm going to use that to determine how many treasures, 
features and opponents will be included in each town. 
The other three fields are lists. 
There's a List of Loot buried on the island, 
a list of town features, and a list of Combatants, 
opponents who'll give our pirate a fight. 
One part of this challenge was to create a compact constructor, 
to initialize the _loot_, _features_, and _opponents_ lists. 
Before I do that, I'll create a method that randomizes elements in a list, 
and returns a sublist of those elements.
This is going to be a generic method, and private, 
and I'll call it _randomReduced_. 
It will return a list of type _T_, 
and take a list of type _T_, as well as an integer, 
the size of the resulting list. 
This method will shuffle the list, using `Collections.shuffle`. 
Then it will return a sublist of the shuffled items. 
I'm not going to return a defensive copy 
because I plan to pass a new list to each call. 
Next, I'll generate a constructor. 
I'll insert that above this method.

```java  
public Town {
    loot = randomReduced(new ArrayList<>(EnumSet.allOf(Loot.class)), level + 2);
    features = randomReduced(new ArrayList<>(EnumSet.allOf(Feature.class)), level + 3);

    opponents = new ArrayList<>();
    if (level == 0) {
        opponents.add(new Islander("Joe", Weapon.KNIFE));
    } else {
        opponents.add(new Islander("Joe", Weapon.MACHETE));
        opponents.add(new Soldier("John", Weapon.PISTOL));
    }
}

public Town(String name, String island, int level) {
    this(name, island, level, null, null, null);
}
```

First, I'll pick _canonical_, 
so you can see what that looks like for this record.
You can see the generated constructor,
with all six assignments. 
It's not really so bad when you have a code generator, 
but imagine if you had 15 or 20 fields, or more. 
It's just a lot of boilerplate code, 
and maybe you really only want to do something different,
for one or two fields. 
I'll revert that last change, 
getting rid of that constructor. 
I'll generate constructor again,
but this time I'm going to pick the compact option. 
Now, I have a _compact_ constructor, with no parentheses, 
and no code in the block. 
Here, I'm going to add the code to create randomized loot and features. 
I'll assign loot to the result of calling the **randomReduced** method. 
I'll pass that method a new ArrayList, 
created by using `EnumSet.allOf` with the **Loot** class, 
so it's a new list of all possible **Loot** items. 
I'll use the level, to set the size of the randomized list, 
passing level plus 2 as the size.
This means the first level will have two treasures, 
the second three, and so on.
I'll do the same, with the features, using `Feature.class` here. 
I'll include an extra feature, so I'll use level plus 3. 
The next thing I want to do is create some opponents. 
I'll instantiate a new list of opponents.
If it's the first level, 
I'll make it easier and have only one opponent. 
This will be an Islander, Joe, with a Knife. 
For the other levels I'll have two opponents. 
The first is again an Islander, Joe, 
but he'll have a Machete this time. 
And then a Soldier, John, with a pistol. 
Ok, so notice, I'm not using _this_ keyword here, 
not assigning things to `this.loot` and `this.features`, for example.
Instead, this code is reassigning 
the values of the constructor parameters. 
The assignment to the record fields happens after this code. 
I could change this to only assign this data 
if the data passed is _null_, 
but I'll leave it like this, to keep it simple. 
Next, I want a custom constructor. 
I'll put that after the compact constructor.
This constructor will take the first three fields, 
name, island, and level. 
I'll generate a _toString_ method:

```java  
@Override
public String toString() {
    return name + ", " + island;
}
```

And I'll select none of the fields as usual, 
since it's easier to edit. 
I'll replace the literal and instead,
return name with a comma followed by island. 
I'll add an information method to this record as well, 
which is a more detailed data string. 
This will return all the Town name the loot list. 
On the following line, after a tab, 
it will have the town's features, 
and again a new line and tab, 
this will print out the opponents.

```java  
public String information() {

    return "Town: " + this + "\n\tloot=" + loot +
            "\n\tfeatures=" + features +
            "\n\topponents=" + opponents;
}

public List<Loot> loot() {
    return (loot == null) ? null : new ArrayList<>(loot);
}

public List<Combatant> opponents() {
    return (opponents == null) ? null : new ArrayList<>(opponents);
}

public List<Feature> features() {
    return (features == null) ? null : new ArrayList<>(features);
}
```

I'm going to create my own accessor methods for the three lists, 
because I want to return defensive copies of each list. 
These will be assigned to the Pirate's data, 
and used to manipulate play, 
so I don't want unmodifiable copies, 
but I also don't want to change the data on the original record. 
I'll return null if loot is null, otherwise. 
I'll return a new ArrayList 
that contains the elements in this record's list of loot. 
The next method will be similar, 
it returns a list of **Combatant**, and the method name is opponents. 
I'll use the same kind of ternary, returning _null_ 
if _opponents_ is null, otherwise returning a new ArrayList, 
from this record's _opponents_ list. 
The last one is features,
so the return type is a list of **Feature**, 
and the method name is features. 
And the same thing here, a ternary operator,
returning null or a new array list.
I'll test my new Town Record real quickly. 
I'll go to the **Main** class's _main_ method:

```java  
Town bridgetown = new Town("BridgeTown","Barbados", 0);
System.out.println(bridgetown);
System.out.println(bridgetown.information());
```

And comment the last three lines out momentarily. 
I'll set up a town, the variable will be Bridgetown, 
and the town name is Bridgetown on the island of Barbados, 
and the level is zero. 
I'll print out the town, bridgetown. 
I'll print the town's information next. 
Running that code:

```html  
BridgeTown, Barbados
Town: BridgeTown, Barbados
    loot=[SILVER_COIN, PEARL_NECKLACE]
    features=[SNAKE, SPRING, ALLIGATOR]
    opponents=[Joe]
```

You can see I've got a town with two pieces of loot, 
three features, some good, some bad, and a single opponent. 
If I run it again:

```html  
BridgeTown, Barbados
Town: BridgeTown, Barbados
    loot=[PEARL_NECKLACE, SILVER_COIN]
    features=[SNAKE, SPRING, JELLYFISH]
    opponents=[Joe]
```

I get a different list of loot and features. 
The opponent won't change until I change levels. 
I'll remove the last three statements I added, 
but leave the last three statements commented out.
Next, I have to change my **PirateGame**, 
to use a nested list of town, not just Strings.

```java  
//private static final List<List<String>> levelMap;
private static final List<List<Town>> levelMap;
```

IntelliJ has a re-factor function 
that makes this a little easier to change. 
I'll go to my _levelMap_ field on the **PirateGame**. 
I'll click on **String** here.
This should be changed to **Town**, 
wherever this is used. 
I'll right-click to bring up the menu items. 
I'll select refactor.
We have a lot of options, but the one I want is type **Migration**. 
Once I select that, it gives me a form field. 
I'm going to change `java.lang.String` to just **Town**, 
with a capital _T_. 
I'll then click the refactor button. 
I'll select Ignore on this next popup, 
and this will fix all but the two statements that were on that popup. 
The first thing I need to do 
after these changes is fix the _loadData_ method.

```java  
private static void loadData() {

    // Level 1 Towns
    levelMap.add(new ArrayList<Town>(List.of(
            new Town("Bridgetown", "Barbados", 0),
            new Town("Fitts Village", "Barbados", 0),
            new Town("Holetown", "Barbados", 0)
    )));
    // Level 2 Towns
    levelMap.add(new ArrayList<Town>(List.of(
            new Town("Fort-de-France", "Martinique", 1),
            new Town("Sainte-Anne", "Martinique", 1),
            new Town("Le Vauclin", "Martinique", 1)
    )));
}
```

I'm going to remove the six Strings in that code. 
Now I'll replace those with new Town instances,
using the same town names, and islands. 
I'll type in all six towns into the level 1-town area, 
and then move them 3 level 2 towns after. 
The first set of towns will have level 0.
The second set of towns will have level 1.
Before I can get this to compile, 
I need to fix the two problems on **Pirate**. 
Both of these are in the _information_ method.

```java  
//var current = ((LinkedList<String>) townsVisited).getLast();
var current = ((LinkedList<Town>) townsVisited).getLast();
```

In the first case, I can just change the cast from a LinkedList of String to Town.
The _setAll_ method can now be simplified. 
I'll remove the call to the split method, and so on, with just name. 
I'll pop back to the **Main** class's _main_ method:

```java  
PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
System.out.println("------------------------------------------------------");
PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));
```

And in here, I'll change the method reference to a lambda. 
This will take t as an argument, standing for the town, 
and this will print the town's information method instead. 
Running this,

```html  
Town: Bridgetown, Barbados
    loot=[PEARL_NECKLACE, GOLD_BAR]
    features=[SNAKE, SPRING, SUN_POISON]
    opponents=[Joe]
Town: Fitts Village, Barbados
    loot=[GOLD_BAR, GOLD_COIN]
    features=[JELLYFISH, PINEAPPLE, SUN_POISON]
    opponents=[Joe]
Town: Holetown, Barbados
    loot=[GOLD_BAR, PEARL_NECKLACE]
    features=[SNAKE, PINEAPPLE, SUN_POISON]
    opponents=[Joe]
------------------------------------------------------
Town: Fort-de-France, Martinique
    loot=[PEARL_NECKLACE, SILVER_COIN, GOLD_COIN]
    features=[JELLYFISH, ALOE, SUN_POISON, PINEAPPLE]
    opponents=[Joe, John]
Town: Sainte-Anne, Martinique
    loot=[GOLD_BAR, SILVER_COIN, GOLD_RING]
    features=[JELLYFISH, ALLIGATOR, PINEAPPLE, ALOE]
    opponents=[Joe, John]
Town: Le Vauclin, Martinique
    loot=[GOLD_RING, SILVER_COIN, PEARL_NECKLACE]
    features=[SNAKE, SPRING, SUN_POISON, ALOE]
    opponents=[Joe, John]
```

There are six towns listed, all with random loot and features. 
At level one, each town has only two items of loot, 
and three features, and one opponent. 
For the level two towns, all on Martinique, 
there are three treasures, four features, and two opponents. 
Getting back to the pirate class, I'll change the _visitTown_ method.

```java  
boolean visitTown() {

    List<Town> levelTowns = PirateGame.getTowns(value("level"));
    if (levelTowns == null) return true;
    Town town = levelTowns.get(value("townIndex"));
    if (town != null) {
        townsVisited.add(town);
        loot = town.loot();
        opponents = town.opponents();
        features = town.features();
        return false;
    }
    return true;
}
```

I'll set the pirate's loot, opponents, and features fields here, 
every time a new town is visited. 
Remember, these are modifiable copies of the data on the town record. 
I'll add two helper methods. 
The first is called hasExperiences, and will return true 
if there are additional features to experience. 
Checks if features field isn't null,
and the size is greater than zero, returns true if so.
The second one is similar, and called has Opponents, also returning a boolean.
If the _opponents_ field isn't null, 
and the size is greater than zero, this will return true, otherwise false. 
Now, I need to implement the _findLoot_ method on **Pirate**:

```java  
boolean findLoot() {

    if (loot.size() > 0) {
        Loot item = loot.remove(0);
        System.out.println("Found " + item + "!");
        adjustValue("score", item.getWorth());
        System.out.println(name() + "'s score is now " + value("score"));
    }
    if (loot.size() == 0) {
        return visitNextTown();
    }
    return false;
}
```

Which will return a boolean. 
I'll make this package private, it'll be called by the **PirateGame**. 
If the pirate's list of loot has items, 
this code will remove the first element, 
assigning the removed item to a local variable. 
I'll print a statement that an item was found. 
I'll adjust the score, adding the item's worth. 
I'll print the score.
Finally, if all the loot is found, meaning this list is zero, 
I'll execute the _visitNextTown_ method, returning its boolean value. 
Otherwise, false is returned, meaning the game continues.

```java  
boolean experienceFeature() {

    if (features.size() > 0) {
        Feature item = features.remove(0);
        System.out.println("Ran into " + item + "!");
        adjustHealth(item.getHealthPoints());
        System.out.println(name() + "'s health is now " + value("health"));
    }
    return (value("health") <= 0);
}
```

The next method is similar, again package private, returning a boolean. 
I'll call this one _experienceFeature_.
If the pirate's copy of features has any elements left in it, 
this code will remove the first element, 
returning the reference to a local variable. 
It'll print that the pirate ran into the feature. 
The pirate's health will be adjusted by this feature's health points. 
And the health will be printed out.

```java  
boolean useWeapon() {

    int count = opponents.size();
    if (count> 0) {
        int opponentIndex = count - 1;
        if (count > 1) {
            opponentIndex = new Random().nextInt(count + 1);
        }
        Combatant combatant = opponents.get(opponentIndex);
        if (super.useWeapon(combatant)) {
            opponents.remove(opponentIndex);
        } else {
            return combatant.useWeapon(this);
        }
    }
    return false;
}
```

I'll also change the code in the _useWeapon_ method on this class, 
to make it more interesting. 
I'll remove the code figure out what opponent to fight. 
First, it'll get the pirate's copy of opponents and get the size. 
If this is greater than zero, then there are still living opponents. 
I'll set the default opponent index to the `count - 1`.
If the count is greater than one, 
I'll use a random number to randomly pick the opponent. 
I'll get the combatant from the _opponents_ list using the random index. 
I'll call _useWeapon_ on the **super** class, 
passing it this combatant. 
If this method returns true, it means the combatant died. 
If that's the case, I'll remove him from the list. 
Otherwise, it's the opponent's turn to use his weapon, 
and I'll return the result of that.
If there aren't any components, this code will return false,
meaning the game continues. 
Ok, that was a lot of setup. 
It should now all pay off, with a couple of minor additions 
to the **PirateGame** class. 
Switching to that class,

```java  
private boolean findLoot(int playerIndex) {
    return getPlayer(playerIndex).findLoot();
}
```

I'll add a wrapper method for the pirate's findLoot method. 
Hopefully you remember that any game action methods have 
to return a boolean and take an index. 
I'll call this findLoot. 
I'll get the player from the player list, using the index, 
and chain the findLoot method on player, 
which is pirate in this case, and return its value. 
I'll do the same thing for the pirate's _experienceFeature_ method.

```java  
private boolean experienceFeature(int playerIndex) {
    return getPlayer(playerIndex).experienceFeature();
}
```

I'll use the _experienceFeature_ method name. 
I'll delegate to the _experienceFeature_ method on my pirate. 
Lastly, I want to adjust the game actions.

```java  
@Override
public Map<Character, GameAction> getGameActions(int playerIndex) {

    Pirate pirate = getPlayer(playerIndex);
    System.out.println(pirate);
    List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

    Map<Character, GameAction> map = new LinkedHashMap<>();
    if (pirate.hasOpponents()) {
        for (Weapon weapon : weapons) {
            char init = weapon.name().charAt(0);
            map.put(init, new GameAction(init, "Use " + weapon,
                    this::useWeapon));
        }
    }

    map.put('F', new GameAction('F', "Find Loot", this::findLoot));
    if (pirate.hasExperiences()) {
        map.put('X', new GameAction('X', "Experience Town Feature",
                this::experienceFeature));
    }
    map.putAll(getStandardActions());
    return map;
}
```

To start with, I'll only print the weapons 
if the pirate has opponents. 
I'll check the hasOpponents method on pirate. 
I'll need to copy and paste the Weapon for loop code. 
I'll do that in a bit. 
I'll include a finding loot action.
A pirate can't move to the next town until he finds all the loot for the level. 
The keystroke will be an F, 
and the game action will be the find Loot method, on this **PirateGame** class. 
Next, I'll allow a user to select an experiences 
if he hasn't already run into them all. 
The keystroke will be an X, 
and the game action will be the experience feature method I just created. 
I'll move the Weapon for the loop now. 
I'll comment out all the code, 
except the last three statements in my **Main** class's _main_ method.

```java  
var console = new GameConsole<>(new PirateGame("The Pirate Game"));
int playerIndex = console.addPlayer();
console.playGame(playerIndex);
```

Running this,

```html  
Loading Data...
Finished Loading Data.
Enter your playing name: Tim
```

I get prompted for my name, so I'll type in _Tim_.

```html  
Welcome to The Pirate Game, Tim!
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: A
```

You can see I can choose from the two weapons, 
and now I've got to find loot, and Experience Town Feature. 
I want to use a weapon to see what happens, so I'll enter _A_.

```html  
-------------------------------------------
Tim used the AXE and missed!
Joe used the KNIFE and missed!
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: F
```

I get the information that I used the axe. 
Since this is random, I may or may not have hit my opponent, 
and he may or may not have hit me.
The health values are printed out if either one of us hits the other. 
Next I'll select _F_, to find some loot.

```html  
-------------------------------------------
Found SILVER_COIN!
Tim's score is now 5
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: X
```

There I found one of the loot items, 
and my score changed, so it's printed out. 
Next I'll try _X_. 
Since this too is random, 
this might be a good thing for my health, or it might not be.

```html  
-------------------------------------------
Ran into PINEAPPLE!
Tim's health is now 100
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: F
```

This shows me what I ran into,
and what my health is after that experience. 
I'll select F this time:

```html  
-------------------------------------------
Found GOLD_BAR!
Tim's score is now 505
Sailing to next town! Bonus: 50 points!
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: I
```

And this time, I should see that I'm sailing to a new town. 
Level one towns only have two items to find. 
I'll press _I_ to see what's going on.

```html  
-------------------------------------------
---> Fitts Village, Barbados
Tim {score=555, health=100, level=0, townIndex=1}
    townsVisited=[Bridgetown, Fitts Village]
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Experience Town Feature (X)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action: X
```

I get the town I'm in, and my data, 
as well as all the towns I visited. 
At this point, I should have three more experiences
for this town, so I'll select X three times, 
and hope it doesn't kill me first.

```html  
-------------------------------------------
Ran into SNAKE!
Tim's health is now 20
-------------------------------------------
Tim
Select from one of the following Actions:
    Use KNIFE (K)
    Use AXE (A)
    Find Loot (F)
    Quit Game (Q)
    Print Player Info (I)
Enter Next Action:
```

Once I use up the experiences for the town, 
the X option goes away, so I don't see that as an option now.
I won't see it again until I sail to another town. 
I could keep playing this game, to try to kill off my opponents. 
If I play long enough, and use my axe, on the first level, 
I should in theory be able to kill Joe off. 
I'll keep using the axe and see if I can do that. 
Once I eliminate all my opponents, I can't use any of the weapons. 
I'll quit the game here. 
</div>