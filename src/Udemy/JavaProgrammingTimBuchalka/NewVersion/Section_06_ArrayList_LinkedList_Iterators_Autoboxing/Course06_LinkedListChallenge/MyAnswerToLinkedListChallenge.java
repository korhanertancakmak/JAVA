package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_06_ArrayList_LinkedList_Iterators_Autoboxing.Course06_LinkedListChallenge;

import java.util.*;

public class MyAnswerToLinkedListChallenge {
    public static void main(String[] args) {

/*
Part-1
                                                LinkedList Challenge

        It's now time for a LinkedList challenge. I'm going to ask you to use LinkedList functionality, to create a list
    of places, ordered by distance from the starting point. And we want to use a ListIterator, to move, both backwards and
    forwards, through this ordered itinerary of places.

        First, create a type that has a town or place name, and a field for storing the distance from the start. Next,
    create an itinerary of places or towns to visit, much like we've been doing in the last few courses.

                            |       Town        | Distance from Sydney(in km) |
                            |     Adalaide      |           1374              |
                            |   Alice Springs   |           2771              |
                            |     Brisbane      |            917              |
                            |      Darwin       |           3972              |
                            |     Melbourne     |            877              |
                            |       Perth       |           3923              |

    But this time, instead of Strings, you'll want to create a LinkedList of your place or town type. Here we show a list
    of a few places in Australia, and their distances from Sydney. You'll create a LinkedList, ordered by the distance
    from the starting point, in this case Sydney. Sydney should be the first element in your list. You don't want to allow
    duplicate places to be in your list, for this data set.

        In addition, you'll create an interactive program with the following menu item options. The menu will have options
    to move forwards and backwards your itinerary, to list the itinerary, and print menu options and quit the program.

                            Available actions (select word or letter):
                            (F)orward
                            (B)ackward
                            (L)ist Places
                            (M)enu
                            (Q)uit

    You'll want to use a Scanner, and the nextLine method, to get input from the console. You'll use a ListIterator, to
    move forwards and backwards, through the list of places on your itinerary.
End-Part-1
*/

        LinkedList<Places> placesToVisit = new LinkedList<Places>();
        boolean flag = true;
        addPlaces(placesToVisit);
        sortPlaces(placesToVisit);
        var iterator = placesToVisit.listIterator();

        while (flag) {
            printActions();
            System.out.println();
            switch (scanner.nextLine()) {
                case "F", "f", "Forward"  -> {
                    if (iterator.hasNext())
                        System.out.println("We're at the destination : " + iterator.next().getPlace());
                    else {
                        System.out.println("No more next destination!");
                    }
                }
                case "B", "b", "Backward" -> {
                    if (iterator.hasPrevious())
                        System.out.println("Next destination is : " + iterator.previous().getPlace());
                    else {
                        System.out.println("No more previous destination!");
                    }
                }
                case "L", "l", "List Places" -> listPlaces(placesToVisit);
                case "M", "m", "Menu" -> printActions();
                default -> flag = false;
            }
            System.out.println();
        }

    }

    private static Scanner scanner = new Scanner(System.in);

    private static void printActions() {
        String textBlock = """
                Available actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";
        System.out.print(textBlock + " ");
    }

    private static void listPlaces(LinkedList<Places> list) {
        for (int i = 1; i < list.size(); i++) {
            System.out.printf("%d. destination : %-15s %d km%n", i, list.get(i).getPlace(), list.get(i).getDistance());
        }
    }

    private static void addPlaces(LinkedList<Places> list) {
        list.addFirst(new Places("Sydney", 0));
        list.push(new Places("Adalaide", 1374));
        list.push(new Places("Alice Springs", 2771));
        list.push(new Places("Brisbane", 917));
        list.push(new Places("Darwin", 3972));
        list.push(new Places("Melbourne", 877));
        list.push(new Places("Perth", 3923));
    }

    private static LinkedList<Places> sortPlaces(LinkedList<Places> list) {

        for (int i = 0; i < list.size(); i++) {
            int dist = list.get(i).getDistance();
            for (int j = i + 1; j  < list.size(); j++) {
                int dist2 = list.get(j).getDistance();
                if (dist2 < dist) {
                    var place = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, place);
                    dist = dist2;
                }
            }
        }
        return list;
    }

}

class Places {
    private String place;
    private int distance;

    public Places(String place, int distance) {
        this.place = place;
        this.distance = distance;
    }

    public String getPlace() {
        return place;
    }

    public int getDistance() {
        return distance;
    }
}