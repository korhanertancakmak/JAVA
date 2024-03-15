package JAVA.AdventureGame;

import java.util.Random;
import java.util.Scanner;

public abstract class Locations {

    private Player player;
    private String locationName;

    protected static Scanner input = new Scanner(System.in);

    public Locations(Player player, String locationName) {
        this.player = player;
        this.locationName = locationName;
    }

    public Locations() {

    }

    abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public String getLocationName() {
        return locationName;
    }

}

abstract class NormalLoc extends Locations {

    public NormalLoc(Player player, String normalLocName) {
        super(player, normalLocName);
    }

    @Override
    boolean onLocation() {
        return true;
    }
}

class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    boolean onLocation() {
        System.out.println("You are at the Safe House!");
        System.out.println("Your health is recharged!");
        if (this.getPlayer().getCharName().equalsIgnoreCase("Samurai")) {
            Samurai samurai = new Samurai();
            this.getPlayer().setPlayerHealth(samurai.getCharHealth());
        } else if (this.getPlayer().getCharName().equalsIgnoreCase("Archer")) {
            Archer archer = new Archer();
            this.getPlayer().setPlayerHealth(archer.getCharHealth());
        } else if (this.getPlayer().getCharName().equalsIgnoreCase("Knight")) {
            Knight knight = new Knight();
            this.getPlayer().setPlayerHealth(knight.getCharHealth());
        }
        return true;
    }
}

class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    boolean onLocation() {
        System.out.println("-----------------Welcome to the Tool Store!-----------------");
        System.out.println("-----------------1 - Armors");
        System.out.println("-----------------2 - Weapons");
        System.out.println("-----------------3 - Back to Location List");
        System.out.println("-----------------Pick one!");
        byte selectCase = input.nextByte();
        while (selectCase < 1 || selectCase > 3) {
            System.out.println("Invalid value, please enter a valid value!");
            selectCase = input.nextByte();
        }
        switch (selectCase) {
            case 1 -> printArmor();
            case 2 -> printWeapon();
            case 3 -> {
                System.out.println("See you again!");
                return true;
            }
        }
        return true;
    }

    protected void printArmor() {
        System.out.println("-----------------Armors-----------------");
        Armor[] armors = new Armor[3];
        armors[0] = new Armor(1);
        armors[1] = new Armor(2);
        armors[2] = new Armor(3);
        for (Armor a : armors) {
            System.out.println(a.getArmorID() + " - " +
                    a.getArmorName() + " <Price : " +
                    a.getPrice() + " , Damage : " +
                    a.getBlock() + ">");
        }
        System.out.println("----------------------------------------");
        buyArmor(armors);
    }

    protected void printWeapon() {
        System.out.println("-----------------Weapons-----------------");
        Weapon[] weapons = new Weapon[3];
        weapons[0] = new Weapon(1);
        weapons[1] = new Weapon(2);
        weapons[2] = new Weapon(3);
        for (Weapon w : weapons) {
            System.out.println(w.getWeaponID() + " - " +
                    w.getWeaponName() + " <Price : " +
                    w.getPrice() + " , Damage : " +
                    w.getDamage() + ">");
        }
        System.out.println("----------------------------------------");
        buyWeapon(weapons);
    }

    protected void buyArmor(Armor[] armors) {
        System.out.print("Select an armor : ");
        byte selectArmor = input.nextByte();
        while (selectArmor < 1 || selectArmor > armors.length) {
            System.out.println("Invalid value, select again : ");
            selectArmor = input.nextByte();
        }
        if (armors[selectArmor - 1].getPrice() > this.getPlayer().getPlayerInventory().getMoney()) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println(armors[selectArmor - 1].getArmorName() + " is purchased!");
            int balance = this.getPlayer().getPlayerInventory().getMoney() - armors[selectArmor - 1].getPrice();
            this.getPlayer().getPlayerInventory().setMoney(balance);
            System.out.println("New balance : " + this.getPlayer().getPlayerInventory().getMoney());
            this.getPlayer().getPlayerInventory().setArmor(armors[selectArmor - 1]);
            System.out.println("Your new Armor : " + this.getPlayer().getPlayerInventory().getArmor().getArmorName());
        }
    }
    protected void buyWeapon(Weapon[] weapons) {
        System.out.print("Select a weapon : ");
        byte selectWeapon = input.nextByte();
        while (selectWeapon < 1 || selectWeapon > weapons.length) {
            System.out.println("Invalid value, select again : ");
            selectWeapon = input.nextByte();
        }
        if (weapons[selectWeapon - 1].getPrice() > this.getPlayer().getPlayerInventory().getMoney()) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println(weapons[selectWeapon - 1].getWeaponName() + " is purchased!");
            int balance = this.getPlayer().getPlayerInventory().getMoney() - weapons[selectWeapon - 1].getPrice();
            this.getPlayer().getPlayerInventory().setMoney(balance);
            System.out.println("New balance : " + this.getPlayer().getPlayerInventory().getMoney());
            this.getPlayer().getPlayerInventory().setWeapon(weapons[selectWeapon - 1]);
            System.out.println("Your new Weapon : " + this.getPlayer().getPlayerInventory().getWeapon().getWeaponName());
        }
    }
}

class ExitGame extends NormalLoc {

    public ExitGame(Player player) {
        super(player, "Exit");
    }

    @Override
    boolean onLocation() {
        System.out.println("Quitting the Game!");
        return false;
    }
}

abstract class BattleLoc extends Locations {

    private Monsters monster;
    private int maxMonsters;

    private final Random rand = new Random();

    private final Scanner in = new Scanner(System.in);

    public BattleLoc(Player player, String locationName, Monsters monster, int maxMonsters) {
        super(player, locationName);
        this.monster = monster;
        this.maxMonsters = maxMonsters;
    }

    BattleLoc() {
        super();
    }

    @Override
    boolean onLocation() {
        System.out.println("You are here right now : " + this.getLocationName());
        int numMonsters = rand.nextInt(maxMonsters) + 1;
        System.out.println("BE CAREFUL! Here " + numMonsters + " " + this.getMonster().getMonsterName() + " lives!");
        System.out.println("<W>ar or <Q>uit to Locations :");
        String selectCase = in.nextLine();
        if (selectCase.equalsIgnoreCase("w")) {
            return combat(numMonsters);
        } else {
            System.out.println("You're going back to Location List!");
        }
        return true;
    }

    public boolean combat(int numMonsters) {
        int initialMonsterHealth = this.getMonster().getMonsterHealth();
        for (int i = 1; i <= numMonsters; i++) {
            int firstStrike = rand.nextInt(0, 101);
            this.getMonster().setMonsterHealth(initialMonsterHealth);
            while (true) {
                Loot loot;
                if (firstStrike >= 50) {
                    System.out.println("Player's striking first!");
                    int newMonsterHealth = this.getMonster().getMonsterHealth() - this.getPlayer().getPlayerDamage();
                    if (newMonsterHealth > 0) {
                        this.getMonster().setMonsterHealth(newMonsterHealth);
                    } else {
                        loot = new Loot(this.getPlayer(), this.getMonster().getMonsterID());
                        System.out.println("Bravo! " + getMonster().getMonsterName() + " is dead!");
                        break;
                    }
                    System.out.println("Monster's striking now!");
                    int newPlayerHealth = this.getPlayer().getPlayerHealth() - this.getMonster().getMonsterDamage();
                    if (newPlayerHealth > 0) {
                        this.getPlayer().setPlayerHealth(newPlayerHealth);
                    } else {
                        System.out.println("Ohh noooo! You are dead!");
                        return false;
                    }
                } else {
                    System.out.println("Monster's striking first!");
                    int newPlayerHealth = this.getPlayer().getPlayerHealth() - this.getMonster().getMonsterDamage();
                    if (newPlayerHealth > 0) {
                        this.getPlayer().setPlayerHealth(newPlayerHealth);
                    } else {
                        System.out.println("Ohh noooo! You are dead!");
                        return false;
                    }
                    System.out.println("Player's striking now!");
                    int newMonsterHealth = this.getMonster().getMonsterHealth() - this.getPlayer().getPlayerDamage();
                    if (newMonsterHealth > 0) {
                        this.getMonster().setMonsterHealth(newMonsterHealth);
                    } else {
                        loot = new Loot(this.getPlayer(), this.getMonster().getMonsterID());
                        System.out.println("Bravo! " + getMonster().getMonsterName() + " is dead!");
                        break;
                    }
                }
                this.getPlayer().playerInfo();
                monsterInfo(i);
            }
        }
        return true;
    }

    public void monsterInfo(int i) {
        System.out.println("Monster : " + i + ". " + this.getMonster().getMonsterName() +
                "\t Health: " + this.getMonster().getMonsterHealth() +
                "\t Damage: " + this.getMonster().getMonsterDamage());
    }

    public Monsters getMonster() {
        return monster;
    }

}

class Cave extends BattleLoc {

    public Cave(Player player) {
        super(player, "Cave", new Zombie(), 3);
    }
}

class Forest extends BattleLoc {

    public Forest(Player player) {
        super(player, "Forest", new Vampire(), 3);
    }
}

class River extends BattleLoc {

    public River(Player player) {
        super(player, "River", new Bear(), 3);
    }
}

class Mine extends BattleLoc {

    public Mine(Player player) {
        super(player, "Mine", new Snake(), 5);
    }
}

class Loot extends BattleLoc {

    private final Player player;

    private String lootName;

    private final Random fortuneLoot = new Random();

    public Loot(Player player, int lootType) {
        this.player = player;
        if (lootType == 1) {
            player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 4);
            this.lootName = "Food";
            player.getPlayerInventory().setAward("Food");
        } else if (lootType == 2) {
            player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 7);
            this.lootName = "Firewood";
            player.getPlayerInventory().setAward("Firewood");
        } else if (lootType == 3) {
            player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 12);
            this.lootName = "Water";
            player.getPlayerInventory().setAward("Water");
        } else if (lootType == 4) {
            fortuneLoot();
        }
    }

    public void fortuneLoot() {
        double type = fortuneLoot.nextDouble(0, 101);
        double amount = fortuneLoot.nextDouble(0, 101);
        if (type <= 45) {
            System.out.println("There is no loot dropout!");
            this.lootName = "Nothing!";
        } else if (type > 45 && type <= 70) {
            System.out.println("The dropout will be money!");
            if (amount <= 50) {
                this.lootName = "1-Money";
                System.out.println("1 is looted as money!");
                player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 1);
            } else if (amount > 50 && amount <= 80) {
                this.lootName = "5-Money";
                System.out.println("5 is looted as money!");
                player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 5);
            } else if (amount > 80 && amount <= 100) {
                this.lootName = "10-Money";
                System.out.println("10 is looted as money!");
                player.getPlayerInventory().setMoney(player.getPlayerInventory().getMoney() + 10);
            }
        } else if (type > 70 && type <= 85) {
            System.out.println("The dropout will be armor!");
            if (amount <= 50) {
                this.lootName = "light-armor";
                System.out.println("light is looted as armor!");
                player.getPlayerInventory().setArmor(new Armor(1));
            } else if (amount > 50 && amount <= 80) {
                this.lootName = "normal-armor";
                System.out.println("normal is looted as armor!");
                player.getPlayerInventory().setArmor(new Armor(2));
            } else if (amount > 80 && amount <= 100) {
                this.lootName = "heavy-armor";
                System.out.println("heavy is looted as armor!");
                player.getPlayerInventory().setArmor(new Armor(2));
            }
        } else if (type > 85 && type <= 100) {
            System.out.println("The dropout will be weapon'");
            if (amount <= 50) {
                this.lootName = "gun-weapon";
                System.out.println("gun is looted as weapon!");
                player.getPlayerInventory().setWeapon(new Weapon(1));
            } else if (amount > 50 && amount <= 80) {
                this.lootName = "sword-weapon";
                System.out.println("sword is looted as weapon!");
                player.getPlayerInventory().setWeapon(new Weapon(2));
            } else if (amount > 80 && amount <= 100) {
                this.lootName = "rifle-weapon";
                System.out.println("rifle is looted as weapon!");
                player.getPlayerInventory().setWeapon(new Weapon(3));
            }
        }
    }

}