package PatikaDev.Java102.JAVA.AdventureGame;

public class Armor extends Inventory {
    private final int armorID;
    private final String armorName;
    private final int block;
    private final int price;

    public Armor(int armorID) {
        this.armorID = armorID;
        if (this.armorID == 1) {
            this.armorName = "Light";
            this.block = 1;
            this.price = 15;
        } else if (this.armorID == 2) {
            this.armorName = "Normal";
            this.block = 3;
            this.price = 25;
        } else if (this.armorID == 3) {
            this.armorName = "Heavy";
            this.block = 5;
            this.price = 40;
        } else {
            this.armorName = "Clothes";
            this.block = 0;
            this.price = 0;
        }
    }

    public int getArmorID() {
        return armorID;
    }

    public String getArmorName() {
        return armorName;
    }

    public int getBlock() {
        return block;
    }

    public int getPrice() {
        return price;
    }

}