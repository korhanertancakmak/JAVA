package CourseCodes.NewSections.Section_07_OOP1.Course15_Inheritance3;

public class Dog extends Animal {

    private String earShape;
    private String tailShape;
    public Dog() {
        super("Mutt", "Big", 50);
    }

    public Dog(String type, double  weight) {
        this(type, weight, "Perky", "Curled");
    }

    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight < 15 ? "small" : (weight < 35 ? "medium" : "large"), weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "earShape='" + earShape + '\'' +
                ", tailShape='" + tailShape + '\'' +
                "} " + super.toString();
    }

    public void makeNoise() {

        if (type == "Wolf") {
            System.out.println("Ow Woooo! ");
        }
        bark();
    }

    @Override
    public void move(String speed) {
        super.move(speed);
        //System.out.println("Dogs walk, run and wag their tail ");
        if (speed == "slow") {
            walk();
            wagTail();
        } else {
            run();
            bark();
        }
    }

    private void bark() {
        System.out.println("Woof! ");
    }

    private void run() {
        System.out.println("Dog running ");
    }

    private void walk() {
        System.out.println("Dog walking ");
    }

    private void wagTail() {
        System.out.println("Tail wagging ");
    }
}
