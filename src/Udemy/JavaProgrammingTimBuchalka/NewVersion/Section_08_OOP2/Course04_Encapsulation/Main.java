package CourseCodes.NewSections.Section_08_OOP2.Course04_Encapsulation;

/*
Course-55

                                              Encapsulation

        In this course, we talk about another fundamental OOP concept, which is Encapsulation. We've already talked about
    it briefly, when we first introduced getter methods. In Java, encapsulation means hiding things, by making them private,
    or inaccessible. Why would we want to hide things in Java?

  - To make the interface simpler, we may want to hide unnecessary details.
  - To protect the integrity of data on an object, we may hide or restrict access to some of the data and operations.
  - To decouple the published interface from the internal details of the class, we may hide actual names and types of class
    members. This gives us more flexibility, if we have to change the class in the future.

    And you might wonder what we mean by interface here? Although Java has a type called interface, which is important,
    and we'll be covering it in a later section, that's not what we're talking about here. When we talk about a class's
    public or published interface, we're really talking about the class members that are exposed to, or can be accessed
    by the calling code. Everything else in the class is internal, or private to it. An application programming interface,
    or API, is the public contract, that tells others how to use the class.

        What we're going to do is, create a class that doesn't use encapsulation, to show why this feature's important.
    We're going to start off with the class that doesn't use it, and then we'll create another enhanced class, that does
    use it. We'll start with the non-encapsulated class, called Player.

                                         _______________________________________
                                         |Player =>                             |
                                         |        name:String                   |
                                         |        health:int                    |
                                         |        weapon:String                 |
                                         |--------------------------------------|
                                         |        loseHealth(int damage)        |
                                         |        restoreHealth(int extraHealth)|
                                         |        healthRemaining(): int        |
                                         |______________________________________|

    Hopefully you're getting used to seeing classes on a diagram now, and can appreciate looking at a class this way, before
    we even build it. This is the model for a Player class, as in a player in a computer game. And The Player will have
    3 variables: name, health, and weapon. And this class will have 3 methods, loseHealth(), restoreHealth(), and
    healthRemaining(), which I'll explain in a bit. And we're going to create this class without using encapsulation.

        To do this, we'll create new Java class and call it Player. And let's add the variables or fields, and we've said
    these are the player's name, the player's health, and the weapon:

                            public class Player {
                                public String name;
                                public int health;
                                public String weapon;
                            }

    Now, what I want you to notice is that here, we've actually used a public access modifier. We've discussed this before,
    and we've said, it's usually a good idea to use private. But we're using public for a reason here, and that'll become
    clear shortly. We're not actually going to create a constructor for this class, but we'll create our 3 methods next.
    The first one's called loseHealth. This gets called if the player loses some health for some reason, like they fell
    off a cliff, or they got damaged in some way. We pass an integer value to this method, and that's subtracted from the
    player's health. If this brings the player's health down to a value less than zero, we'll print that the player's been
    knocked out of the game:

                            public void loseHealth(int damage) {
                                health -= damage;
                                if (health <= 0) {
                                    System.out.println("Player knocked out of game");
                                }
                            }

    There we have the first method, which is going to subtract health from our player. The second method is going to return
    the amount of health that's available, or what's remaining. We're calling this method healthRemaining. And for now,
    it's simply going to return our variable, health, which is our field in this class, so it's really a type of getter,
    or accessor method.

                            public int healthRemaining() {
                                return health;
                            }

    Finally, we'll add a way for the player to have this health restored, with the restoreHealth method. This method might
    be called in the game, if the Player finds health tokens, or does something healthy. This method will add extraHealth
    points to the health field.

                            public void restoreHealth(int extraHealth) {
                                health += extraHealth;
                            }

    Now, let's just make a rule, that health should never go over 100. If health is greater than 100, we'll just set it
    back to be 100, and print that the player was completely restored.

                            public void restoreHealth(int extraHealth) {
                                health += extraHealth;
                                if (health > 100) {
                                    System.out.println("Player restored to 100%");
                                    health = 100;
                                }
                            }

    The Player class is done now, and we can go back to our main class. And we'll create a new Player.

                            Player player = new Player();

    We didn't create a constructor in the Player class, but if you recall a default constructor with no parameters, gets
    created for us by Java. In other words, we don't now have a constructor to pass player data, when we create a new player.
    This means the calling code needs to manually set the data in these fields.

                            player.name = "Korhan";
                            player.health = 20;
                            player.weapon = "Sword";

    That's the way we need to initialize those fields. And of course, as you can see, even though we're not in that class,
    we can access those fields directly. That's because we set the access to be public, for those fields. If we'd set
    those to private, we wouldn't be able to do this.

        Next, let's test the methods on the Player class:

                            int damage = 10;
                            player.loseHealth(damage);
                            System.out.println("Remaining health = " + player.healthRemaining());

    Looking at this code, you can see we first made the player health = 20 above this, and here we've got 10 damage. Then
    we're calling the player.loseHealth method, which actually reduces the player's health by the damage done. And if
    we run that,

                            Remaining health = 10

    you can see the result. Ok, let's add some more game play with a few more statements:

                            player.loseHealth(11);
                            System.out.println("Remaining health = " + player.healthRemaining());

    And running this:

                            Remaining health = 10
                            Player knocked out of game
                            Remaining health = -1

    we can see that the second call to loseHealth, this prints that the player got knocked out, because after the call,
    the remaining health is now equal to -1. That's obviously working, but I'm wondering whether you can see a possible
    problem here. We've created a method in here, loseHealth, which is the code that should manage the process of the
    player losing health. This is where we want to put in all our formulas, for making sure that we're calculating health
    correctly, when some damage happens. But what happens if the calling code decides to just access the player's health
    directly any time it wants? And it just sets the player health here, above the second call to the loseHealth method:

                            player.health = 200;

    Now, here, the main method has kind of gone rogue, because it's setting the player's health manually, outside of the
    normal game play method calls. This code actually takes control away from the Player class, for managing the Player's
    health. It didn't call the restoreHealth method, which has the additional control in place, that player health can
    never exceed 100. This is an example of the first problem, with a class that's not encapsulated properly.

                                                  Problems(when no encapsulations)

    1. Allowing direct access to data on an object, can potentially bypass checks, and additional processing, your class
    has in place to manage the data. By being able to access fields directly like this, we're really potentially opening
    up our application, to be accessed in ways that it shouldn't be. Maybe we don't ever want the code to be able to change
    the health like that, because we really wanted the restoreHealth method to be called, and the right set of conditions
    to be set. But because these fields are public to everyone, we can't control when they get accessed.

    2. Allowing direct access to fields, means calling code would need to change, when you edit any of the fields. If we
    go back to our Player class again, and let's just say we've decided that we want to change the field for the name of
    our player. Maybe now, we think name isn't descriptive enough, and we want to be a bit much clearer. We're going to
    change name to fullName, and that's going to be the variable name that we want to use, from this point on. This is
    really just an internal change to the Player class, and in theory it shouldn't affect any other class. We should be
    able to call class variables anything we want, and be able to change them if we want to. But if we go back to the Main
    class now, suddenly we've got an error. And it's quite rightly saying that it can't find that field anymore, because
    we're trying to access these fields directly, we've got an error here. Now, this might not seem like the end of the
    world. But what it means it, that anytime that we change this field's name, we have to also make the change here in
    the Main class. And in a small application like this of course, it's not going to be a problem. It's a quick change.
    But when you're talking about big applications, it does become a problem. Because the first thing here, looking at
    the Main class, you have to figure out what's the new correct name of the field. This means you need to go back into
    the code, and have a look and see what the new name is. And maybe this isn't really a problem as much when it's your
    own code, but if you're deploying this kind of code in a library for example, you're setting yourself up for a lot of
    problems.

    3. Omitting a constructor, that would accept initialization data, may mean the calling code is responsible for setting
    up this data, on the new object. The third issue with this approach is, we're manually initializing our object with
    these calls at the start. This means the calling code is responsible for making all the right method calls, to initialize
    a player, at the beginning of the play. But the code might actually forget to initialize this all together. And consequently,
    when the class is called for the first time, the player may be starting out with health = 0. In other words, we're not
    guaranteeing or ensuring that to access the player class, we can only access it, when the data is valid. Now we can do
    that with a constructor. We can actually make sure that the data is valid, and that the object is valid before the game
    starts. But when you're allowing people to manually access the fields, there's no real way to guarantee that the player
    health is set.

        And this is what encapsulation actually does for us, and why we don't want to code like this. We want the ability
    to ensure certain conditions are met before playing, and that access to the player data during the game, is controlled
    and protected. Now that you've seen some of the bad things, the bad ways of doing it, let's actually comment this code
    out.

        Let's create a new class that actually has got proper encapsulation by creating a new class, named EnhancedPlayer,
    and show you the right way of doing it. The difference here is, we're going to create our 3 fields as private.

                        private String name;
                        private int health;
                        private String weapon;

    And here, we have the name of the Player, the health and the weapon, they're all declared as private. Next, we do want
    to create a constructor. Placing the cursor below those fields, we'll first create a constructor with 3 fields.

                        public EnhancedPlayer(String name, int health, String weapon) {
                            this.name = name;
                            this.health = health;
                            this.weapon = weapon;
                        }

    And what we're going to do is, use this constructor to make sure the health field stays in the range of 1 to 100, with
    an if-else statement. Let's edit this constructor a bit, if a user passes a value that's less than 1, we'll just make
    the health 1. And if we get a value greater than 100, than doesn't make sense either.

                        this.name = name;
                        if (health < 0) {
                            this.health = 1;
                        } else if (health > 100) {
                            this.health = 100;
                        } else {
                            this.health = health;
                        }
                        this.weapon = weapon;

    This constructor gives us a little more control, about how a new Player is created. Now, we could put more validation
    in there, to check the length of the name, or to make sure the name we get isn't null for example, and likewise for
    the weapon. In other words, we could do some additional validation, to make sure that it's valid. And actually, let's
    create an overloaded constructor, that doesn't have health or weapon, because we'll set these to some default values.
    Generating another constructor for name:

                        public EnhancedPlayer(String name) {
                            this(name, 100, "Sword");
                        }

    This will make creating a new player easy, and we can make the default value for health be 100, meaning the Player
    starts out completely healthy. And we're guaranteeing now, that the name, the health, and the weapon, are initialized
    when the class is created. Now, let's just copy the 3 methods we had for Player. Ok, now we have a better Player class,
    which we're calling EnhancedPlayer. And if we go back now to the Main class and main method, we can create a new
    EnhancedPlayer:

                        EnhancedPlayer korhan = new EnhancedPlayer("Korhan");
                        System.out.println("Initial health is " + korhan.healthRemaining());

    Now, obviously if we run that, we should get initial health is 100. But what if we use the other constructor, and pass
    200 as the health, and sword as the weapon? Let's do that and see what happens:

                        EnhancedPlayer korhan = new EnhancedPlayer("Korhan", 200, "Swrod");
                        System.out.println("Initial health is " + korhan.healthRemaining());

    If we run that:

                        Initial health is 100

    Hopefully, you can see, straight away, we've got more control. There's no other way for the calling code to change
    the health, except to call EnhancedPlayer's method to do it. This gives all the control back to the EnhancedPlayer
    class. And just to be clear, this is encapsulation. This is what we're doing, by making our fields private, we're
    making sure that the fields within the class aren't accessible to any classes that our outside.

        And now, let's say I wanted to change the field we use for the player's name, changing it to fullName from name,
    like we did with Player. But this time, I want to show you another feature of IntelliJ that's really cool, and enables
    you to do a quick rename. If you actually click on what you want to rename, name in this case, and right-click, go to
    re-factor, then choose rename. And now if I go back and change this, the part that's in blue, I change that to fullName,
    you see it turns pink. Before hitting the Enter key, we can scroll down, and see every instance of name has been
    replaced with fullName. And if we press Enter, we get a popup that asks us if we want to change the parameter names
    of the constructors, and this really is up to you. But this time I'll select all, and hit ok, and it changes the name
    to fullName in our code. And for good measure, let's change the name of the field health, to healthPercentage. The
    point is, we've now changed 2 fields names and the parameter name(health) has no effect on how the code works. But
    check out the code in the main method of the Main class. It didn't have to change at all. It runs exactly as it did
    before. The structure to create a new player hasn't changed, and the method name to get the health didn't change.
    This means the code here, the calling code, has no idea what the internal naming was, for the fields. And it doesn't
    need to. We're just calling this healthRemaining method, and it's doing the rest of us. This is another cool feature
    of encapsulation, which is, we can make all 3 changes to this enhanced player class, we can create private fields and
    private functions, that we don't want to be exposed to any other class. And we can change those names at any time in
    this code, without affecting any other code.

                                            Benefits of Encapsulation

        That's really one of the huge benefits of encapsulation, is that you're not actually affecting any other code. It's
    sort of like a black box in many ways. What we're saying here is, the only way to create an enhanced player object,
    is to call the constructor with these parameters. And also we got some rules in there for what health can be. But the
    other thing is we can't bypass the EnhancedPlayer's method, and change the health field directly. We haven't provided
    a mechanism to change the health by any other means, except to use the methods, loseHealth, or restoreHealth. We can
    pass the initial amount of health in the constructor, but we can't change it after that, like we did in the other
    class. Using the Player class, we could set the value, and we actually changed the value midpoint when we shouldn't
    have. But the EnhancedPlayer class has more control over its data. This is why we want to use encapsulation.

        We really want to protect access to our object's data. We protect the members of the class, and some methods,
    from external access. This prevents calling code from bypassing the rules and constraints, we've built into the class.
    When we create a new instance, it's initialized with valid data. But likewise, we're also making sure that there's no
    direct access to the fields. That's why you want to always use encapsulation. It's something that you should really
    get used to .

        To create an encapsulated class, you want to:

  - Create constructors for object initialization, which enforces that only objects with valid data will get created.
  - Use the private access modifier for your fields.
  - Use setter and getter methods sparingly, and only as needed.
  - Use access modifiers that aren't private, only for the methods that the calling code needs to use.

    You definitely don't want to be creating code like the Player class, when you're creating public fields. In the majority
    of cases, you don't want to give access to other classes because, as you saw, it's more work to make those changes when
    you create, or when you change a field name, or something of that nature.

*/


public class Main {
    public static void main(String[] args) {

//        Player player = new Player();
//        player.name = "Korhan";
//        player.health = 20;
//        player.weapon = "Sword";
//        int damage = 10;
//        player.loseHealth(damage);
//        System.out.println("Remaining health = " + player.healthRemaining());
//        player.health = 200;
//        player.loseHealth(11);
//        System.out.println("Remaining health = " + player.healthRemaining());

        EnhancedPlayer korhan = new EnhancedPlayer("Korhan", 200, "Swrod");
        System.out.println("Initial health is " + korhan.healthRemaining());
    }
}
