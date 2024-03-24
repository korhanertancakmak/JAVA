package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course05_Iterators;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {


        var placesToVisit = new LinkedList<String>();
        placesToVisit.add("Sydney");
        placesToVisit.add(0,"Canberra");
        addMoreElements(placesToVisit);


        testIterator(placesToVisit);

    }

    private static void addMoreElements(LinkedList<String> list) {
        list.addFirst("Darwin");
        list.addLast("Hobart");
        // Queue methods
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");
        list.push("Alice Springs");
    }

    private static void testIterator(LinkedList<String> list) {

        var iterator = list.listIterator();                 // changed at Part-6 from "var iterator = list.iterator();"
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println(list);


        while (iterator.hasNext()) {
            if (iterator.next().equals("Brisbane")) {
                //iterator.remove();                   at the end of part-6, marked as comment
                //list.remove();
                iterator.add("Lake Wivenhoe");
            }
        }

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);


        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println(list);


        var iterator2 = list.listIterator(3);
        //System.out.println(iterator2.next());
        System.out.println(iterator2.previous());

    }
}
