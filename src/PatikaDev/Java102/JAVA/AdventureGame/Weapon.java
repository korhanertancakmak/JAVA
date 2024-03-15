package JAVA.AdventureGame;

public class Weapon extends Inventory {
    private final int weaponID;
    private final String weaponName;
    private final int damage;
    private final int price;

    public Weapon(int weaponID) {
        this.weaponID = weaponID;
        if (this.weaponID == 1) {
            this.weaponName = "Gun";
            this.damage = 2;
            this.price = 25;
        } else if (this.weaponID == 2) {
            this.weaponName = "Sword";
            this.damage = 3;
            this.price = 35;
        } else if (this.weaponID == 3) {
            this.weaponName = "Rifle";
            this.damage = 7;
            this.price = 45;
        } else {
            this.weaponName = "Punch";
            this.damage = 1;
            this.price = 0;
        }
    }

    public int getWeaponID() {
        return weaponID;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

}

