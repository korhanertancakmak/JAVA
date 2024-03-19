package CourseCodes.NewSections.Section_16_Immutable_Unmodifable_Classes.Course14_FinalChallengeOfSection.pirate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

//Part-14
/*
        This record will have 6 fields, the first the name of the town, the second the island name, the third will be an
    integer, the level at which the town will be played. I'm going to use that to determine how many treasures, features
    and opponents, will be included on each town. The other three fields are lists. There's a List of Loot buried on the
    island, a list of town features, and a list of Combatants, opponents who'll give our pirate a fight. One part of this
    challenge, was to create a compact constructor, to initialize the loot, features, and opponents lists. Before I do
    that, I'll create a method that randomizes elements in a list, and returns a sublist of those elements.
*/
//End-Part-14

public record Town(String name, String island, int level,
                   List<Loot> loot,
                   List<Feature> features,
                   List<Combatant> opponents) {

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

//Part-16
/*
        First, I'll pick canonical, so you can see what that looks like for this record. You can see the generated constructor,
    with all six assignments. It's not really so bad when you have a code generator, but imagine if you had 15 or 20
    fields, or more. It's just a lot of boiler plate code, and maybe you really only want to do something different, for
    one or two fields. I'll revert that last change, getting rid of that constructor. I'll generate constructor again,
    but this time I'm going to pick the compact option. Now, I have a compact constructor, with no parentheses, and no
    code in the block. Here, I'm going to add the code to create randomized loot and features. I'll assign loot to the
    result of calling the randomReduced method. I'll pass that method a new ArrayList, created by using EnumSet.allOf
    with the Loot class, so it's a new list of all possible Loot items. I'll use the level, to set the size of the randomized
    list, passing level plus 2 as the size. This means the first level will have 2 treasures, the second three, and so on.
    I'll do the same, with the features, using Feature.class here. I'll include an extra feature, so I'll use level plus
    3. The next thing I want to do is create some opponents. I'll instantiate a new list of opponents. If it's the first
    level, I'll make it easier and have only one opponent This will be an Islander, Joe, with a Knife. For the other levels
    I'll have two opponents. The first is again an Islander, Joe, but he'll have a Machete this time. And then a Soldier,
    John, with a pistol. Ok, so notice, I'm not using the this keyword here, not assigning things to this.loot and this.features,
    for example. Instead, this code is reassigning the values of the constructor parameters. The assignment to the record
    fields happens after this code. I could change this to only assign this data, if the data passed is null, but I'll
    leave it like this, to keep it simple. Next, I want a custom constructor. I'll put that after the compact constructor.
    This constructor will take the first three fields, name, island, and level. I'll generate a two String method,
*/
//End-Part-16

    private <T> List<T> randomReduced(List<T> list, int size) {

        Collections.shuffle(list);
        return list.subList(0, size);
    }

//Part-15
/*
        This is going to be a generic method, and private, and I'll call it randomReduced. It will return a list of type
    T, and take a list of type T, as well as an integer, the size of the resulting list. This method will shuffle the list,
    using Collections.shuffle. Then it will return a sublist of the shuffled items. I'm not going to return a defensive
    copy, because I plan to pass a new list to each call. Next, I'll generate a constructor. I'll insert that above this
    method.
*/
//End-Part-15

    @Override
    public String toString() {
        return name + ", " + island;
    }

//Part-17
/*
        And I'll select none for the fields as usual, since it's easier to edit. I'll replace the literal and instead,
    return name with a comma followed by island. I'll add an information method, to this record as well, which is a more
    detailed data string. This will return all of the Town name, the loot list. On a following line, after a tab, it will
    have the town's features, and again a new line and tab, this will print out the opponents.
*/
//End-Part-17

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

//Part-18
/*
        I'm going to create my own accessor methods for the three lists, because I want to return defensive copies of each
    list. These will be assigned to the Pirate's data, and used to manipulate play, so I don't want unmodifiable copies,
    but I also don't want to change the data on the original record. I'll return null if loot is null, otherwise. I'll
    return a new ArrayList that contains the elements in this record's list of loot. The next method will be similar, it
    returns a list of Combatant, and the method name is opponents. I'll use the same kind of ternary, returning null if
    opponents is null, otherwise returning a new ArrayList, from this record's opponents list. The last one is features,
    so the return type is a list of Feature, and the method name is features. And the same thing here, a ternary operator,
    returning null or a new array list. I'll test my new Town Record real quick. I'll go to the Main class's main method,
*/
//End-Part-18
}
