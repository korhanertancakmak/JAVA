package JAVA.AdventureGame;

import java.util.Scanner;

public class Player {
    private String charName;
    private int PlayerDamage;
    private int PlayerHealth;
    private Inventory playerInventory;
    private final boolean[] visitedLocations = new boolean[7];
    private final Scanner input = new Scanner(System.in);

    public Player(String userName) {
        System.out.printf("%nDear " + userName + " Welcome to the Adventure Game!%n");
        System.out.println("Please choose a character!");
        selectChar();
    }

    public void selectChar() {
        Samurai samurai = new Samurai();
        Archer archer = new Archer();
        Knight knight = new Knight();
        Characters[] charList = {samurai, archer, knight};
        boolean flag = true;
        while (flag) {
            System.out.println("-----------------------------------------------------------------------------------------");
            for (Characters gameChar : charList) {
                System.out.println("ID :" + gameChar.getCharID() +
                        "\t\t Character: " + gameChar.getCharName() +
                        "\t\t Damage: " + gameChar.getCharDamage() +
                        "\t\t Health: " + gameChar.getCharHealth() +
                        "\t\t Money: " + gameChar.getInventory().getMoney());
            }
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Please pick a character!");
            int charID = input.nextByte();
            switch (charID) {
                case 1 -> initChar(samurai);
                case 2 -> initChar(archer);
                case 3 -> initChar(knight);
                default -> System.out.println("There is no other characters! Please choose one on the list!");
            }
            if (charID == 1 || charID == 2 || charID == 3) {
                flag = false;
            }
        }
        playerInfo();
    }

    public void initChar(Characters charac) {
        this.setCharName(charac.getCharName());
        this.setPlayerDamage(charac.getCharDamage());
        this.setPlayerHealth(charac.getCharHealth());
        this.setPlayerInventory(charac.getInventory());
    }

    public void playerInfo() {
        System.out.println("Chosen character: " + this.charName +
                "=> Defense: " + this.playerInventory.getArmor().getArmorName() +
                "\t Block: " + this.getPlayerInventory().getArmor().getBlock() +
                "\t Health: " + this.getPlayerHealth() +
                "\t Weapon: " + this.playerInventory.getWeapon().getWeaponName() +
                "\t Damage: " + this.getPlayerDamage() +
                "\t Money: " + this.playerInventory.getMoney());
    }

    @SuppressWarnings("unused")
    public void moveLoc() {
        Locations location;
        boolean isPlayerDead = true;
        while (isPlayerDead) {
            System.out.println("Regions");
            System.out.println("0 - Quit Game");
            System.out.println("1 - Safe House - This is heal-recharge location!");
            System.out.println("2 - Tool Store - This is purchase location!");
            System.out.println("3 - Cave - Careful! 1-3 Zombie can be here!");
            System.out.println("4 - Forest - Careful! 1-3 Vampire can be here!");
            System.out.println("5 - River - Careful 1-3 Bear can be here!");
            System.out.println("6 - Mine - Careful 1-5 Snake can be here!");
            System.out.println("Please select the region that you want to go : ");
            byte selectLoc = input.nextByte();

            if (visitedLocations[selectLoc] && selectLoc > 2) {
                System.out.println("You have already visited this location. Choose another one.");
                continue;
            }

            switch (selectLoc) {
                case 0 -> location = new ExitGame(this);
                case 1 -> location = new SafeHouse(this);
                case 2 -> location = new ToolStore(this);
                case 3 -> location = new Cave(this);
                case 4 -> location = new Forest(this);
                case 5 -> location = new River(this);
                case 6 -> location = new Mine(this);
                default -> location = null;
            }

            if (selectLoc == 0 && !location.onLocation()) {                             // If the player exit the game!
                System.out.println("Hope to see you soon again!");
                System.exit(1);
            }

            if (location != null && selectLoc > 0 && selectLoc < 7) {                   // If the player is in a region!
                if (location.onLocation()) {
                    visitedLocations[selectLoc] = true;
                } else {                                                                // If the player is not in a region, means he is dead!
                    System.out.println("You are dead!");
                    System.out.println("Game is Over!");
                    isPlayerDead = false;
                }
            } else if (location == null) {                                              // If the player enters something other than the regions!
                System.out.println("Enter a valid region!!!");
                continue;
            } else if (location == null && selectLoc == 0) {                            // Quitting the game
                System.out.println("Hope to see you soon again!");
                break;
            }

            // Check for victory condition
            if (checkVictory()) {
                System.out.println("Congratulations! You have visited all locations successfully. You win!");
                break;
            }

            playerInfo();
        }
    }

    // Helper method to check if all locations are visited
    private boolean checkVictory() {
        for (int i = 3; i <= 6; i++) {
            if (!visitedLocations[i]) {
                return false;
            }
        }
        return true;
    }

    public String getCharName() {
        return charName;
    }

    protected void setCharName(String charName) {
        this.charName = charName;
    }

    public int getPlayerDamage() {
        return PlayerDamage;
    }

    protected void setPlayerDamage(int PlayerDamage) {
        this.PlayerDamage = PlayerDamage;
    }

    public int getPlayerHealth() {
        return PlayerHealth;
    }

    protected void setPlayerHealth(int PlayerHealth) {
        this.PlayerHealth = PlayerHealth;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    protected void setPlayerInventory(Inventory PlayerInventory) {
        this.playerInventory = PlayerInventory;
    }
}
