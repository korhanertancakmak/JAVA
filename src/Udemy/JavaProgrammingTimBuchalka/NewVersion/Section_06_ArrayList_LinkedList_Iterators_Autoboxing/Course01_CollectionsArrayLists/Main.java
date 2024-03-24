package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course01_CollectionsArrayLists;

import java.util.ArrayList;
import java.util.Arrays;


record GroceryItem(String name, String type, int count) {

    public GroceryItem(String name) {

        this(name, "DAILY", 1);
    }

    @Override
    public String toString(){

        return String.format("%d %s in %s", count, name.toUpperCase(), type);
    }
}

public class Main {
    public static void main(String[] args) {

//        Object[] groceryArray = new Object[3];
//        groceryArray[0] = new GroceryItem("milk");
//        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
//        groceryArray[2] = "5 oranges";
//        System.out.println(Arrays.toString(groceryArray));


        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        // groceryArray[2] = "5 oranges";                                                   // compiler error
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));


        ArrayList objectList = new ArrayList();



        objectList.add(new GroceryItem("Butter"));
        objectList.add("Yogurt");


    //ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();
        ArrayList<GroceryItem> groceryList = new ArrayList<>();


        //groceryList.add("Yogurt");
        groceryList.add(new GroceryItem("Butter"));

        System.out.println(groceryList);


        groceryList.add(new GroceryItem("milk"));
        groceryList.add(new GroceryItem("oranges", "PRODUCE", 5));
        System.out.println(groceryList);

//        groceryList.add(0, new GroceryItem("apples", "PRODUCE", 6));
//        System.out.println(groceryList);


        groceryList.set(0, new GroceryItem("apples", "PRODUCE", 6));
        System.out.println(groceryList);



        groceryList.remove(1);
        System.out.println(groceryList);

    }
}
