package CourseCodes.OldSections.Section_04_OOP1.Course01_Classes;


public class Car {

    // Fields
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;


    // Getter & Setter Methods
    public void setModel(String model) {
        String validModel = model.toLowerCase();
        if(validModel.equals("carrera") || validModel.equals("commodore")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getModel() {
        return this.model;
    }
}
