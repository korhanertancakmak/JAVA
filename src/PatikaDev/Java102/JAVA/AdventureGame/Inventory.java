package JAVA.AdventureGame;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private int money;
    private String award;

    public Inventory() {
    }

    public Inventory(int charID) {
        this.armor = new Armor(0);
        this.weapon = new Weapon(0);
        if (charID == 1) {
            this.money = 15;
        } else if (charID == 2) {
            this.money = 20;
        } else if (charID == 3) {
            this.money = 25;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    protected void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    protected void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getMoney() {
        return money;
    }

    protected void setMoney(int money) {
        this.money = money;
    }

    protected void setAward(String award) {
        this.award = award;
    }
}

