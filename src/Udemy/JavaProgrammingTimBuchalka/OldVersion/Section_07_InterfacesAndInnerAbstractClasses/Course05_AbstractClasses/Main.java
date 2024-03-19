package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_07_InterfacesAndInnerAbstractClasses.Course05_AbstractClasses;

public class Main {

    public static void main(String[] args) {
	    Dog dog = new Dog("Yorkie");
        dog.breathe();
        dog.eat();

        Parrot parrot = new Parrot("Australian ringneck");
        parrot.breathe();
        parrot.eat();
        parrot.fly();

        Penguin penguin = new Penguin("Emperor");
        penguin.fly();


        String stringData = "Darwin Brisbane Perth Melbourne Canberra Adelaide Sydney Canberra";

        String[] data = stringData.split(" ");
        for (String s : data) {
            // create new item with value set to the string s
        }
    }
}
