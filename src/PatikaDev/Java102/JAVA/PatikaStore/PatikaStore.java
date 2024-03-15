package PatikaDev.Java102.JAVA.PatikaStore;

import java.util.ArrayList;

public class PatikaStore {

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        products.add(new HuaweiMatebook14());
        products.add(new LenovoV14IGL());
        products.add(new ASUSTufGaming());
        products.add(new SamsungGalaxyA51());
        products.add(new iPhone1164GB());
        products.add(new RedmiNote10Pro8GB());

        printProducts(products);
    }

    public static void printProducts(ArrayList<Product> products) {
        // Print notebooks in the Store
        System.out.println();
        System.out.println("Notebook List");
        System.out.println("-".repeat(99));
        System.out.printf("| %-4s| %-30s| %-10s| %-10s| %-10s| %-10s| %-10s|%n",
                "ID", "Codes.PatikaStore.Product Name", "Price", "Brand", "Storage", "Screen", "RAM");
        System.out.println("-".repeat(99));
        for (Product product : products) {
            if (product instanceof Notebook p) {
                System.out.printf("| %-4d| %-30s| %-10.1f| %-10s| %-10d| %-10.1f| %-10d|%n",
                        p.getProductID(), p.getProductName(), p.getProductPrice(),
                        p.getProductBrand(), p.getNotebookStorage(), p.getNotebookScreen(),
                        p.getNotebookRam());
            }
        }
        System.out.println("-".repeat(99));
        System.out.println();

        // Print mobile phones in the Store
        System.out.println("Mobile Phone List");
        System.out.println("-".repeat(135));
        System.out.printf("| %-4s| %-30s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|%n",
                "ID", "Codes.PatikaStore.Product Name", "Price", "Brand", "Storage", "Screen", "Camera",
                "Battery", "RAM", "Color");
        System.out.println("-".repeat(135));
        for (Product product : products) {
            if (product instanceof MobilePhone p) {
                System.out.printf("| %-4s| %-30s| %-10.1f| %-10s| %-10d| %-10.1f| %-10d| %-10.1f| %-10d| %-10s|%n",
                        p.getProductID(), p.getProductName(), p.getProductPrice(),
                        p.getProductBrand(), p.getMobilePhoneStorage(), p.getMobilePhoneScreen(),
                        p.getMobilePhoneCamera(), p.getMobilePhoneBattery(), p.getMobilePhoneRam(),
                        p.getMobilePhoneColor());
            }
        }
        System.out.println("-".repeat(135));
    }
}



