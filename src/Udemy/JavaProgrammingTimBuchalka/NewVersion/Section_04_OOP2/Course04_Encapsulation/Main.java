package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_04_OOP2.Course04_Encapsulation;

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
