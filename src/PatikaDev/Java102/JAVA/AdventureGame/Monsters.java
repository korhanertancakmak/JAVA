package PatikaDev.Java102.JAVA.AdventureGame;

import java.util.Random;

public class Monsters {
    private final int monsterID;
    private final String monsterName;
    private final int monsterDamage;
    private int monsterHealth;

    public Monsters(int monsterID, String monsterName, int monsterDamage, int monsterHealth) {
        this.monsterID = monsterID;
        this.monsterName = monsterName;
        this.monsterDamage = monsterDamage;
        this.monsterHealth = monsterHealth;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    protected void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }
}

class Zombie extends Monsters {
    public Zombie() {
        super(1, "Zombie", 3, 10);
    }
}

class Vampire extends Monsters {
    public Vampire() {
        super(2, "Vampire",4, 14);
    }
}

class Bear extends Monsters {
    public Bear() {
        super(3, "Bear",7, 20);
    }
}

class Snake extends Monsters {
    private static final int monsterDamage;
    static {
        Random rand = new Random();
        monsterDamage = rand.nextInt(3, 7);
    }
    public Snake() {
        super(4, "Snake", monsterDamage, 12);
    }
}



