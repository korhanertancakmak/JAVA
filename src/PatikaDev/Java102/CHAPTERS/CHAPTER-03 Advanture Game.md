# Adventure Game

## Game Introduction and Class Designs

We are making a text-based adventure game in Java.

### Characters

| Character | ID | Damage | Health | Money |
|-----------|----|--------|--------|-------|
| Samurai   | 1  | 5      | 21     | 15    |
| Archer    | 2  | 7      | 18     | 20    |
| Knight    | 3  | 8      | 24     | 25    |

### Monsters

| Monster | ID | Damage | Health | Money |
|---------|----|--------|--------|-------|
| Zombie  | 1  | 3      | 10     | 4     |
| Vampire | 2  | 4      | 14     | 7     |
| Bear    | 3  | 7      | 20     | 12    |

### Weapons

| Weapon | ID | Damage | Money |
|--------|----|--------|-------|
| Gun    | 1  | 2      | 25    |
| Sword  | 2  | 3      | 35    |
| Rifle  | 3  | 7      | 45    |

### Armors

| Armor  | ID | Block | Money |
|--------|----|-------|-------|
| Light  | 1  | 1     | 15    |
| Normal | 2  | 3     | 25    |
| Heavy  | 3  | 5     | 40    |

### Locations

| Location   | ID        | Monster(Quantity) | Feature       | Goods            |
|------------|-----------|-------------------|---------------|------------------|
| Safe House | NormalLoc | -                 | Healing       | -                |
| Cave       | BattleLoc | Zombie(1-3)       | Battle + Loot | Food             |
| Forest     | BattleLoc | Vampire(1-3)      | Battle + Loot | Firewood         |
| River      | BattleLoc | Bear(1-3)         | Battle + Loot | Water            |
| Store      | NormalLoc | -                 | Buying        | Weapons & Armors |

### Class Diagram

![Class Diagram](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/advgame-1/figures/class-diagram.jpg)

## Algorithm Path

This is NOT one and only solution to this problem for sure. However, i would like to show you mine. Future enhancements are possible as always.

### Character Classes

1. We create "JAVA.MyList.Main" Class with a main method to be able to run Game.start() method by creating a Game object.

2. We create a Game Class with initially "start" method that create a Player object and ask for an user name input to the user.

    | $~~~~~~~~~~~~~Game$                |
    | :--------------------------------------- |
    | **${\color{green}+Player: Player}$**     |
    | **${\color{green}+location: Location}$** |
    | **${\color{red}+start(): void}$**        |

3. We began creating the Classes in the Game Class. Firstly, Player Class for the game user.  

    | $~~~~~~~~~~~~~~~~~~~Player$                     |
    | :---------------------------------------------- |
    | **${\color{green}+userName: String}$**          |
    | **${\color{green}+playerDamage: int}$**         |
    | **${\color{green}+playerHealth: int}$**         |
    | **${\color{green}+playerInvetory: Inventory}$** |
    | **${\color{red}+selectChar(): void}$**          |
    | **${\color{red}+initChar(): void}$**            |
    | **${\color{red}+printInfo(): void}$**           |
    | **${\color{red}+moveLoc(): void}$**             |

    a. Player object will have userName, damage, health and inventory fields. These are:

        userName : name of the user
        playerDamage   : will be set to the charDamage of a char that is chosen by the user  
        playerHealth   : will be set to the charHealth of a char that is chosen by the user  
        playerInventory: will be set to the charInventory of a char that is chosen by the user  

    b. The constructor will only set "userName" field but we will add all getter and setter methods for all the fields. Be careful here! All setters are prohibited according to the principle of encapsulation.  

4. According to the first method in Player, we will need to create a Characters Class. Therefore, We create a Character class which is an abstract class because all our subClasses, which are Samurai, Archer and Knight, have the same features such as charID, charName, charDamage, charHealth and charInventory.  

    | $<<abstract>>Characters$                     |
    | :-------------------------------------------- |
    | **${\color{green}+charID: int}$**             |
    | **${\color{green}+charName: String}$**        |
    | **${\color{green}+charDamage: int}$**         |
    | **${\color{green}+charHealth: int}$**         |
    | **${\color{green}+charInvetory: Inventory}$** |

    We don't need to have any methods in Characters Class other than a constructor and getter & setter methods.

5. Now we need to create Inventory Class which will have money, armor, weapon, loot materials such as food, water and firewood, and the loot that will be earned from the battle. So, it can be additional money, armor or weapon as well.  

    NOTE - 1: We will create the loot Class at the end of this project.  

    | $~~~~~~~Inventory$                   |
    | :----------------------------------- |
    | **${\color{green}+charID: int}$**    |
    | **${\color{green}+money: int}$**     |
    | **${\color{green}+weapon: Weapon}$** |
    | **${\color{green}+armor: Armor}$**   |
    | **${\color{green}+Loot: loot}$**     |

    Here, we have weapon, armor and character-dependent money in the inventory. So, we can add these with 2 additional classes below:  

    | $~~~~~~~Armor$                     |
    | :--------------------------------- |
    | **${\color{green}+armorID: int}$** |
    | **${\color{green}+name: String}$** |
    | **${\color{green}+block: int}$**   |
    | **${\color{green}+price: int}$**   |

    and

    | $~~~~~~~Weapon$                     |
    | :---------------------------------- |
    | **${\color{green}+weaponID: int}$** |
    | **${\color{green}+name: String}$**  |
    | **${\color{green}+damage: int}$**   |
    | **${\color{green}+price: int}$**    |

    after adding the getter & setter method for each classes, we turn back to creating characters in the Characters Class. These are Samurai, Archer, and Knight. Actually, these are just setting fields of the Characters abstract class.  

    | $~~~~~~~~~~~~~~~~Samurai$                  |
    | :----------------------------------------- |
    | **${\color{green}+charID = 1}$**           |
    | **${\color{green}+charName = "Samurai"}$** |
    | **${\color{green}+charDamage = 5}$**       |
    | **${\color{green}+charHealth = 18}$**      |
    | **${\color{green}+charInvetory = 15}$**    |

    | $~~~~~~~~~~~~~~~~~Archer$                 |
    | :---------------------------------------- |
    | **${\color{green}+charID = 2}$**          |
    | **${\color{green}+charName = "Archer"}$** |
    | **${\color{green}+charDamage = 7}$**      |
    | **${\color{green}+charHealth = 21}$**     |
    | **${\color{green}+charInvetory = 20}$**   |

    | $~~~~~~~~~~~~~~~~~~Knight$                 |
    | :----------------------------------------- |
    | **${\color{green}+charID = 1}$**           |
    | **${\color{green}+charName = "Knight"}$**  |
    | **${\color{green}+charDamage = 8}$**       |
    | **${\color{green}+charHealth = 24}$**      |
    | **${\color{green}+charInvetory = 25}$**    |

    The money of all characters will be set in the inventory class. Now, we can keep going on Locations abstract ancestor Class.  

### Location Classes

1. We create a Location ancestor Class with abstract onLocation method.

    | $<<abstract>> Location$                     |
    | :------------------------------------------ |
    | **${\color{green}+Player : player}$**       |
    | **${\color{green}+locationName : String}$** |
    | **${\color{red}+Location : void}$**         |
    | **${\color{red}-onLocation : boolean}$**    |

2. We create NormalLoc Class in Location Class. Here and its subclasses, we return "true" from the onLocation method, because our player cannot die at normal locations.

    | $~~~~~~~~NormalLoc$                            |
    | :--------------------------------------------- |
    | **${\color{green}+Player : player}$**          |
    | **${\color{green}+normalLocName : String}$**   |
    | **${\color{red}+onLocation = true}$**       |

3. We create SafeHouse and ToolStore classes that extend NormalLoc. Here we can use onLocation method to print some fency outputs. Also we create additional Class here for quitting the game.  

    | $~~~~~~~~~~~~~~~~~~~~~~SafeHouse$                  |
    | :------------------------------------------------- |
    | **${\color{green}+Player : player}$**              |
    | **${\color{green}+normalLocName = "Safe House"}$** |
    | **${\color{red}+onLocation = true}$**              |

    | $~~~~~~~~~~~~~~~~~~~~~~ToolStore$                  |
    | :------------------------------------------------- |
    | **${\color{green}+Player : player}$**              |
    | **${\color{green}+normalLocName = "Tool Store"}$** |
    | **${\color{red}+onLocation = true}$**              |
    | **${\color{red}+printArmor : void}$**              |
    | **${\color{red}+printWeapon : void}$**             |
    | **${\color{red}+buyArmor : void}$**                |
    | **${\color{red}+buyWeapon : void}$**               |

    | $~~~~~~~~~~~~~~~~~~~~~~ExitGame$                      |
    | :---------------------------------------------------- |
    | **${\color{green}+Player : player}$**                 |
    | **${\color{green}+normalLocName = "Quitting Game"}$** |
    | **${\color{red}+onLocation = false}$**                |

4. We add moveLoc method in Player Class in a while loop. This while loop quits if onLocation is false which means my character is dead or the user wants to exit if he/she did not enter invalid output such as a character or a string.  

    NOTE - 2: If they did, then the application outputs a warning and exits again for now.

5. We design onLocation method in SafeHouse accoding to the character's initial healths by setting player's health to it.
6. We design onLocation method in ToolStore according to its features such as buying weapons and armors. To be able to this, first we print armors and weapon with printArmor and printWeapon methods and then buy the chosen one with buyArmor and buyWeapon in the ToolStore Class.
7. Purchasing an armor or a weapon will need to update our player's inventory money, defense and damage values. This can be done by using our Inventory Class.
8. We add printInfo method in Player Class to get updated Player info sequentially after each buy and battle.

### Dangerous Regions and Monsters

1. To be able to create BattleLoc abstract Class in Locations abstract Class which has only Monster as a field, we begin with creating Monster class.  

    | $~~~~~~~~~~~~~~~~Monsters$                  |
    | :------------------------------------------ |
    | **${\color{green}+monsterID : int}$**       |
    | **${\color{green}+monsterName : String}$**  |
    | **${\color{green}+monsterDamage : int"}$**  |
    | **${\color{green}+monsterHealth : int"}$**  |

    After including getter & setter, we can continue with creting each monster Classes:

    | $~~~~~~~~~~~~~~~~Zombie$                     |
    | :------------------------------------------- |
    | **${\color{green}+monsterID = 1}$**          |
    | **${\color{green}+monsterName = "Zombie"}$** |
    | **${\color{green}+monsterDamage = 3}$**      |
    | **${\color{green}+monsterHealth = 10}$**     |
    | **${\color{green}+maxMonsters : 3}$**        |
    | **${\color{green}+monsterAward = 4}$**       |

    | $~~~~~~~~~~~~~~~~Vampire$                    |
    | :------------------------------------------- |
    | **${\color{green}+monsterID = 1}$**          |
    | **${\color{green}+monsterName = "Zombie"}$** |
    | **${\color{green}+monsterDamage = 4}$**      |
    | **${\color{green}+monsterHealth = 14}$**     |
    | **${\color{green}+maxMonsters : 3}$**        |
    | **${\color{green}+monsterAward = 4 7 12}$**  |

    | $~~~~~~~~~~~~~~~~Bear$                       |
    | :------------------------------------------- |
    | **${\color{green}+monsterID = 1}$**          |
    | **${\color{green}+monsterName = "Zombie"}$** |
    | **${\color{green}+monsterDamage = 7}$**      |
    | **${\color{green}+monsterHealth = 20}$**     |
    | **${\color{green}+maxMonsters : 3}$**        |
    | **${\color{green}+monsterAward = 12}$**      |

    Note - 3: For the monsterAward field I want to create "Loot" Class first. And this can be done at the end.  

    Here, we are ready for creating the BattleLoc Class now. Since we have a random probability to get how many monsters in each battle locations, we need to add additional field for this.

    | $~~~~~~~~~~~~~~~~BattleLoc$                 |
    | :------------------------------------------ |
    | **${\color{green}+monster : Monster}$**     |
    | **${\color{green}+maxMonsters : int}$**     |
    | **${\color{red}+BattleLoc(Monster~~m)"}$**  |
    | **${\color{red}+onLocation : void}$**       |
    | **${\color{red}+combat() : void}$**         |

2. We add Forest, Cave, River and Mine Classes under hierarchy of BattleLoc Class. Mine Class is an additional battle class. It includes additional monster Snakes.

    | $~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Snake$                |
    | :---------------------------------------------------------------------------- |
    | **${\color{green}+monsterID = 4}$**                                           |
    | **${\color{green}+monsterName = "Snake"}$**                                   |
    | **${\color{green}+monsterDamage = 3-7}$**                                     |
    | **${\color{green}+monsterHealth = 12}$**                                      |
    | **${\color{green}+maxMonsters : 5}$**                                         |
    | **${\color{green}+monsterAward = random(money~~1-5-10, ~~armor, ~~weapon)}$** |

3. Loot Class is created here for all enemy's awards and fortuneAward method is included for the Snake monster Class as a subclass of BattleLoc.  

    | $~~~~~~~~~~~~~~~~Loot$                   |
    | :--------------------------------------- |
    | **${\color{green}+lootType : int}$**     |
    | **${\color{green}+lootName : String}$**  |
    | **${\color{green}+player : Player"}$**   |
    | **${\color{red}+fortuneLoot : void"}$**  |

    This class is called right after every monster is dead in each battle locations. That's why it is a subclass of BattleLoc. Also, Snake monster Class needs it to set which type of award will be given if the monster is dead and how much its amount will be.

### War Algorithm

1. We allow 2 options after the selection of regions. They are strike or run. If the user selects "w", the game does not stop until one is dead, player or all the monsters in that locations.  
2. The strike sequence is a random decision with a possibility of %50. But the results are solid. Whenever one side is lost, the game is over, the player wins or the player is dead, repectively.
3. After the battle, if the user is survived the game turns back to the location list console again asking the new location the user wants to go.
4. If the user picks the locations which they entered before, then a warning comes up as "you cannot re-enter this location." that wants them to choose another.
5. Until all the locations are done with 0 monsters, the game is over and the player wins the game!

## Additional Features

1. In order to complete the game, the region-specific reward must be added to the player's inventory after all enemies in the war zones are cleared. If the player can collect all the rewards and return to the "Safe House", he wins the game. Additionally, it is not possible to re-enter the region where the reward was earned. Region-specific rewards:

    * Cave   => Food
    * Forest => Firewood
    * River  => Water

2. 50% chance of determining who will make the first move when the player encounters a monster. (In the current situation, it is always the player who strikes first.)
3. A new war zone should be added. The purpose of this area is to provide the opportunity to earn random money, weapons or armor from defeated opponents.

    * Region name : Mine
    * Monster     : Snake (1-5) : id = 4, damage = random(3-6), health = 12, money = nope(instead, a possibility of winning goods)
    * Feature     : Battle + Awards
    * Goods       : Money, weapon or armor

   | Loot Type | Low   | Mid    | High  |
   |-----------|-------|--------|-------|
   | -         | -     | -      | -     |
   | Money     | 1     | 5      | 10    |
   | Armor     | Light | Normal | Heavy |
   | Weapon    | Gun   | Sword  | Rifle |

4. Dropout goods from a beaten enemy:

    * Possibility of winning weapon           : 15%  
        * Possibility of winning rifle             : 20%  
        * Possibility of winning sword             : 30%  
        * Possibility of winning gun               : 50%  
    * Possibility of winning armor            : 15%  
        * Possibility of winning heavy-armor       : 20%  
        * Possibility of winning normal-armor      : 30%  
        * Possibility of winning light-armor       : 50%  
    * Possibility of winning money            : 25%  
        * Possibility of winning 10-money          : 20%  
        * Possibility of winning 5-money           : 30%  
        * Possibility of winning 1-money           : 50%  
    * Possibility of winning nothing          : 45%  
