package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_11_Collections.Course05_CollectionMethodsPart4;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Card Collection with Aces added", 2);

        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Card Collection with Kings copied", 2);

        cards = List.copyOf(kingsOfClubs);
        Card.printDeck(cards, "List Copy of Kings", 1);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck" , 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck of Cards:", 4);

        var sortingAlgorithm = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "Standard Deck sorted by rank, suit", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, "Sorted by rank, suit reversed:", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4, 8));
        Card.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16, 20));
        Card.printDeck(tens, "Tens in deck", 1);

        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("sublist index for tens = " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));

        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("disjoint = " + disjoint);

        boolean disjoint2 = Collections.disjoint(kings, tens);
        System.out.println("disjoint = " + disjoint2);

//Part-1
/*
        In this lecture, I'll use the same code from the last lecture, and we'll finish up with the last few methods that
    I want to cover on the Collections class. Again, like the Arrays class that gave us a binarySearch method for arrays,
    the Collections method supports a binary search method for lists. Both method require the elements to be sorted first,
    and neither guarantees what index is returned if you've got duplicates. The binarySearch method on Collections is
    overloaded like the sort method. I can pass a List of Comparable elements, or I can pass a list with a separate Comparator,
    which should match the way the elements are sorted. Both methods take an element, the element that will be searched
    for, which should be the same type as the elements in the List passed. I'll use this method on my deck of cards next.

        First I want to create a ten of hearts card, which will be the card I want to search for in my deck. I'll set up
    a ten of hearts variable, and assign it what I get from the static method Card.getNumericCard with Hearts as the suit,
    and the face value of 10. Now, I want a local variable, an int, which I'll call foundIndex, and assign that the result
    of calling binary search on the Collections class. I'm going to pass my deck, a list, and this new tenOfHearts. Lastly,
    I'll pass the sorting algorithm I set up earlier in this method, which I used to sort with, and I called that sorting
    Algorithm. Print the resulting index. Print the matched element at that index. However, running this code gives me
    an error.

            Exception in thread "main" java.lang.IndexOutOfBoundsException Create breakpoint : Index -53 out of bounds for length 52

    The index that came back was -53, not -1, but it still means the ten of hearts wasn't found. Can you guess why? If
    you scroll up and review my code, you'll notice that on line 50, I reversed the original sort. Now, I'm attempting
    to use binary search on a list that isn't sorted the way I specified. This is important. Your list must be sorted,
    before you can execute binarySearch on it. If I'm passing a comparator, my list has to be sorted that way before
    executing this method. I can fix this, by calling sort again on my deck with the same comparator I passed to the
    binary search. I'll insert "deck.sort(sortingAlgorithm)" statement before I create the tenOfHearts card. Re-running
    my code now,

                ---(same)
                foundIndex = 34
                10♥(8)

    I get a found index of 34, and sure enough it prints out the 10 of hearts element in the deck of cards, which is good,
    since that's the card I was looking for. Remember, you can use List's index of method to do this same thing, without
    the list being required to be in sorted order. The contains method on List, uses the IndexOf method to return it's
    result as well. Let me show you the indexOf method here for comparison. I'll add another println statement by calling
    deck.indexOf, passing it tenOfHearts variable. Running that:

                ---(same)
                foundIndex = 34
                foundIndex = 34
                10♥(8)

    you can see that the found index is still 34, when using this method on the cards list. But now let's see what happens,
    if I comment out that the first statement, deck.sort there. Running this code now:

            ---(same)
            foundIndex = -53
            foundIndex = 17
            Exception in thread "main" java.lang.IndexOutOfBoundsException Create breakpoint : Index -53 out of bounds for length 52

    you can see that the found index of the tenOfHearts is 17 in the second instance. The indexOf method on List can
    find the ten of hearts in my list, sorted or not. The binarySearch gives us a result, but not on that is reliable,
    and that last statement again gives me an exception, when I try to retrieve an element with that index = -53. I'll
    revert that last change, where I commented out the sort, so this code compiles and runs. Ok then, you might be asking,
    which method should you use? The basic rule of thumb is, if your lists contain a small number of elements, or if your
    list is unsorted or may contain duplicates, then using the indexOf method, or its cohort, the lastIndexOf method, will
    provide better performance. If your list is already sorted, and contains a large amount of elements than the binary
    search method may provide performance improvements.
*/
//End-Part-1

        deck.sort(sortingAlgorithm);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex = " + foundIndex);
        System.out.println("foundIndex = " + deck.indexOf(tenOfHearts));
        System.out.println(deck.get(foundIndex));

//Part-2
/*
         Moving on, I want to examine just a couple more method on the Collections class. First, this class has a replaceAll
    method like the List interface. You'll remember the replaceAll method on List, allowed us to write a function, a lambda
    expression to do a global replacement of all elements. The method on this collections class is much more limited,
    because it requires you to replace one or more instances with another. I'll use the replaceAll method of Collections
    to replace the tenOfClubs with a tenOfHearts in my deck. My tens row is now lower in the list, since I re-sorted it,
    so I'm using elements 32 through 36, just to print out the tens in the list.

                ---------------------------
                Tens row
                10♥(8) 10♦(8) 10♥(8) 10♠(8)

    The output shows that I now have 2 tensOfHearts. This method will replace more than one element, if it finds multiple
    matches.
*/
//End-Part-2

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32, 36), "Tens row", 1);

//Part-3
/*
         I'll copy those last 2 statements, and pass a copy below. This time, I want the tenOfHearts as the first argument,
    and the tenOfClubs as the second argument, so this is doing the reverse now, replacing any 10s of hearts with a tensOfClubs.
    Running that:

                ---------------------------
                Tens row
                10♣(8) 10♦(8) 10♣(8) 10♠(8)

    you can see it replaced both 10s of hearts with 10s of clubs. Now, IntelliJ wants our attention on both of those
    replaceAll methods, and it's indicating that "the result of 'Collections.replaceAll() is ignored". That's because this
    method returns a boolean value, true if the list was really changed, meaning one or more elements was replaces, or
    false if not.
*/
//End-Part-3

        Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
        Card.printDeck(deck.subList(32, 36), "Tens row", 1);

//Part-4
/*
         I'll add one more call to this method, duplicating the call previously, but wrapping it in an if statement. If
    the method returns true, I'll print that cards were replaced, otherwise, I'll print that no matching tens of hearts
    were found. Running this code:

                ---(same)
                No tens of hearts found in the list

    you can see my output, no tens of hearts found in the list. That's because I replaced them all, in the replace call
    just previous to this call, with tens of clubs. I'll leave those other warnings from IntelliJ. You should continue to
    pay attention to any warnings you see, since IntelliJ has had a lot more time to learn the rules than you have. I will
    sometimes purposely ignore these warnings, as I am showing you the ropes, but let me encourage you to trust and use
    IntelliJ's suggestions, while you're learning on your own.
*/
//End-Part-4

        if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of hearts replaced with tens of clubs");
        } else {
            System.out.println("No tens of hearts found in the list");
        }

//Part-5
/*
         Another interesting function is the frequency method, which allows you to check for duplicates in your collection.
    I'll make a call to this method, directly in a println statement. I'll pass it my deck of cards, and the ten of clubs
    cards. Running this:

            ---(same)
            Ten of Clubs Cards = 2

    I get that 2 tens of clubs was found in my list.
*/
//End-Part-5

        System.out.println("Ten of Clubs Cards = " + Collections.frequency(deck, tenOfClubs));

//Part-6
/*
         Collections also give us both min an max methods, which take a collection, and will return the last or first
    element. Like sort and binarySearch, you can use one of the 2 overloaded versions, the first if your class implements
    Comparable, which I'm purposely not doing for Card. The second takes a Comparator, and that's the one I'll use here,
    passing my sortingAlgorithm variable, to each of these methods. I'll call these from within a println statement, first
    max, passing deck and sorting algorithm. And now min, again passing deck and sorting algorithm. When I run that:

            ---(same)
            Best Card = A♠(12)
            Worst Card = 2♣(0)

    it identifies the best card as the ace of spades, and the worst card is a 2 of clubs.
*/
//End-Part-6

        System.out.println("Best Card = " + Collections.max(deck, sortingAlgorithm));
        System.out.println("Worst Card = " + Collections.min(deck, sortingAlgorithm));

//Part-7
/*
         Finally, let's look at the rotate method. First I want to sort my deck by suit, and then rank, as it was in the
    beginning. I could just make another call to getStandardDeck, but instead I'll just create a new Comparator, and call
    sort on deck, and then print that out. Running that code:

                ---(same)
                ---------------------------
                Sorted by Suit, Rank
                2♣(0) 3♣(1) 4♣(2) 5♣(3) 6♣(4) 7♣(5) 8♣(6) 9♣(7) 10♣(8) 10♣(8) J♣(9) Q♣(10) K♣(11)
                A♣(12) 2♦(0) 3♦(1) 4♦(2) 5♦(3) 6♦(4) 7♦(5) 8♦(6) 9♦(7) 10♦(8) J♦(9) Q♦(10) K♦(11)
                A♦(12) 2♥(0) 3♥(1) 4♥(2) 5♥(3) 6♥(4) 7♥(5) 8♥(6) 9♥(7) J♥(9) Q♥(10) K♥(11) A♥(12)
                2♠(0) 3♠(1) 4♠(2) 5♠(3) 6♠(4) 7♠(5) 8♠(6) 9♠(7) 10♠(8) J♠(9) Q♠(10) K♠(11) A♠(12)

    my deck is again sorted by suits, and then rank.
*/
//End-Part-7

        var sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Sorted by Suit, Rank", 4);

//Part-8
/*
         Now I'll set up an example of using the rotate method. First I want a copy of the first suit in my deck. I'll
    create a new List variable, called copied, and that will be a new ArrayList. I'll pass a call to deck.sublist, from
    0 to 13, to the ArrayList constructor, so this should create a list of just my clubs cards. The rotate method is a
    lot easier to understand if we look at the results while discussing it. Call rotate, passing it my new list, and the
    number 2. Print the unrotated sublist from my deck. Print the rotate copied list. Running that:

                ---(same)
                UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
                Rotated 2: [Q♣(10), K♣(11), 2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9)]

    you can see that the last 2 elements in the original list(the first list printed), my Queen and King of clubs were
    moved to be the first 2 elements in the following rotated list. A positive number passed to this method moves that
    number of elements in the list from the back of the list to the front of the list, as you can see here. Notice that
    the order of the elements that were rotated is maintained, so Q is still before K, in the rotated list.
*/
//End-Part-8

        List<Card> copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, 2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated " + 2 + ": " + copied);

//Part-9
/*
         Now I'll try a negative number, I'll copy those last 4 statements and paste them directly below. I'll remove the
    type, List with Card there, in front of copied, so I'm just re-declaring this list. Then I'll change 2 to -2 on the
    second and 4th lines. Running this code:

                ---(same)
                UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
                Rotated 2: [Q♣(10), K♣(11), 2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9)]
                UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
                Rotated -2: [4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11), 2♣(0), 3♣(1)]

    I want you to compare the output from the two different calls to rotate. In the first instance, the last two elements
    were moved to the start of the list, so the King and Ace of clubs were moved to the start of the list. Using a negative
    number though, you can see it moves the elements from the start to the end of the list. Here, the 2 and 3 of clubs
    were rotated to the end of the list. Like shuffle, and reverse, it's good to know these methods exist, so you don't
    have to spend time writing your own algorithm to do these things.
*/
//End-Part-9

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, -2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated " + -2 + ": " + copied);

//Part-10
/*
         Finally, I want to look at the swap method. You'll remember one of the previous challenges had you swap characters
    in a character array, reversing the characters in a string. Now, I'll show you how to use the swap method on the
    collections class to do something similar. I'll reverse the order of my suit of cards. Again, I'll make a copy of my
    first 13 cards, the club cards in the deck. Hopefully you'll remember that when swapping elements to do a full reverse,
    you only need to swap half the elements. My for loop is from 0 to copied size divided by 2. Passing the copied list,
    and an element i, which is the for loop parameter, and j, which is from the first half of my copied list to second half
    into the Collections.swap. Print out the copied array. Running that:

                ---(same)
                UnRotated: [2♣(0), 3♣(1), 4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11)]
                Rotated -2: [4♣(2), 5♣(3), 6♣(4), 7♣(5), 8♣(6), 9♣(7), 10♣(8), 10♣(8), J♣(9), Q♣(10), K♣(11), 2♣(0), 3♣(1)]
                Manual reverse :[K♣(11), Q♣(10), J♣(9), 10♣(8), 10♣(8), 9♣(7), 8♣(6), 7♣(5), 6♣(4), 5♣(3), 4♣(2), 3♣(1), 2♣(0)]

    you can see I simply reversed the sublist elements. I could have simply done this, by using the reverse method on this
    sublist(UnRotated).
*/
//End-Part-10

        copied = new ArrayList<>(deck.subList(0, 13));
        for (int i = 0; i < copied.size() / 2; i++) {
            Collections.swap(copied, i, copied.size() - 1 - i);
        }
        System.out.println("Manual reverse :" + copied);

//Part-11
/*
         Let me do that for completeness. First I'll copy and paste the array list again. And then call collections.reverse,
    passing my copied list, print that out.

                ---(same)
                Using reverse :[K♣(11), Q♣(10), J♣(9), 10♣(8), 10♣(8), 9♣(7), 8♣(6), 7♣(5), 6♣(4), 5♣(3), 4♣(2), 3♣(1), 2♣(0)]

    And that does the same thing. Having access to the swap method allows you to have a lot more granularity in the ways
    you might use it. Ok, so those are a lot of the methods on Collections, some of which you might find some use for,
    and it's good to know they exist, if you might need this kind of functionality. Others are better replaced by List's
    default method, under most circumstances. This class contains a lot of methods for transforming collections into
    other collection types, such as viewable, immutable, checkable, and empty collections, which I'm not going to review
    here. Many of these have been replaced with additional implementations in the Collections Frameworks, and I'll be
    covering these in sections they're more relevant.
*/
//End-Part-11

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.reverse(copied);
        System.out.println("Using reverse :" + copied);

    }
}
