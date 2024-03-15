package PatikaDev.Java102.JAVA.AdventureGame;
public abstract class Characters {
    private final int charID;
    private final String charName;
    private final int charDamage;
    private final int charHealth;
    private final Inventory inventory;

    public Characters(int id, String charName, int charDamage, int charHealth, Inventory inventory) {
        this.charID = id;
        this.charName = charName;
        this.charDamage = charDamage;
        this.charHealth = charHealth;
        this.inventory = inventory;
    }

    public int getCharID() {
        return charID;
    }

    public String getCharName() {
        return charName;
    }

    public int getCharDamage() {
        return charDamage;
    }

    public int getCharHealth() {
        return charHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }

}

class Samurai extends Characters {
    public Samurai() { super(1 ,"Samurai", 5, 18, new Inventory(1)); }
}

class Archer extends Characters {
    public Archer() {super(2, "Archer", 7, 21, new Inventory(2)); }
}

class Knight extends Characters {
    public Knight() {super(3,"Knight", 8, 24, new Inventory(3)); }
}