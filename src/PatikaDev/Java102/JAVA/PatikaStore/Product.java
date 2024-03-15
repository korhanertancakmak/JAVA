package JAVA.PatikaStore;

public abstract class Product {
    private final int productID;
    private final String productName;
    private final double productPrice;
    private final String productBrand;
    public Product(int productID, String productName, double productPrice, String productBrand) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }
}

class Notebook extends Product{

    private final int notebookStorage;
    private final double notebookScreen;
    private final int notebookRam;

    public Notebook(int productID, String productName, double productPrice, String productBrand,
                    int storage, double screen, int ram) {
        super(productID, productName, productPrice, productBrand);
        this.notebookStorage = storage;
        this.notebookScreen = screen;
        this.notebookRam = ram;
    }

    public int getNotebookStorage() {
        return notebookStorage;
    }

    public double getNotebookScreen() {
        return notebookScreen;
    }

    public int getNotebookRam() {
        return notebookRam;
    }
}

class HuaweiMatebook14 extends Notebook{
    public HuaweiMatebook14() {
        super(1, "HUAWEI Matebook 14", 7000.0,
                "Huawei", 512, 14.0, 16);
    }
}

class LenovoV14IGL extends Notebook{

    public LenovoV14IGL() {
        super(2, "LENOVO V14 IGL", 3699.0,
                "Lenovo", 1024, 14.0, 8);
    }
}

class ASUSTufGaming extends Notebook {

    public ASUSTufGaming() {
        super(3, "ASUS Tuf Gaming", 8199.0,
                "Asus", 2048, 15.6, 32);
    }
}

class MobilePhone extends Product{

    private final int mobilePhoneStorage;
    private final double mobilePhoneScreen;
    private final int mobilePhoneCamera;
    private final double mobilePhoneBattery;
    private final int mobilePhoneRam;
    private final String mobilePhoneColor;

    public MobilePhone(int productID, String productName, double productPrice, String productBrand,
                       int storage, double screen, int camera, double battery, int ram, String color) {
        super(productID, productName, productPrice, productBrand);
        this.mobilePhoneStorage = storage;
        this.mobilePhoneScreen = screen;
        this.mobilePhoneCamera = camera;
        this.mobilePhoneBattery = battery;
        this.mobilePhoneRam = ram;
        this.mobilePhoneColor = color;
    }

    public int getMobilePhoneStorage() {
        return mobilePhoneStorage;
    }

    public double getMobilePhoneScreen() {
        return mobilePhoneScreen;
    }

    public int getMobilePhoneCamera() {
        return mobilePhoneCamera;
    }

    public double getMobilePhoneBattery() {
        return mobilePhoneBattery;
    }

    public int getMobilePhoneRam() {
        return mobilePhoneRam;
    }

    public String getMobilePhoneColor() {
        return mobilePhoneColor;
    }
}

class SamsungGalaxyA51 extends MobilePhone {
    public SamsungGalaxyA51() {
        super(1, "SAMSUNG GALAXY A51", 3199.0,
                "Samsung", 128, 6.5, 32, 4000.0, 6, "Black");
    }
}

class iPhone1164GB extends MobilePhone {
    public iPhone1164GB() {
        super(2, "iPhone 11 64 GB", 7379.0,
                "Apple", 64, 6.1, 5, 3046.0, 6, "Blue");
    }
}

class RedmiNote10Pro8GB extends MobilePhone {
    public RedmiNote10Pro8GB() {
        super(3, "Redmi Note 10 Pro 8GB", 4012.0,
                "Xiaomi", 128, 6.5, 35, 4000.0, 12, "White");
    }
}
