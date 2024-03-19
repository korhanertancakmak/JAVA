package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course04_CollectionMethodsPart3;

import java.util.*;

//Part-1
/*
        In the last lecture, I started demonstrating some of the methods on Collections, like fill, n copies, addAll and
    copy. I'm going to pick up that discussion here, where I left off in the code with a look at another method on collections,
    that's fun to use when you have a deck of cards, and that's shuffle. I've already got a variable set up, called deck,
    that contains my standard deck of cards.

                                        List<Card> deck = Card.getStandardDeck();
                                        Card.printDeck(deck);
*/
//End-Part-1

public class Main {

    public static void main(String[] args) {

        //List<Card> deck = Card.getStandardDeck();
        //Card.printDeck(deck);

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

//Part-2
/*
        I'll comment these at the top of the main method, and copy-paste it right below. Now, I want to shuffle my deck.
*/
//End-Part-2

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

//Part-3
/*
        Now, I want to shuffle my deck. I could write some random function to do it for me, but even easier, I can use
    the shuffle method on Collections. And I'll print my deck with a header of "Shuffled Deck", and in 4 rows. Running
    this code:

                ---------------------------
                Shuffled Deck
                J♥(9) 6♠(4) 5♦(3) 2♠(0) 9♣(7) 5♠(3) J♦(9) Q♣(10) A♦(12) 8♥(6) A♣(12) 10♠(8) K♠(11)
                2♣(0) A♠(12) 3♦(1) 3♣(1) Q♠(10) 9♥(7) 2♥(0) 6♥(4) 7♦(5) 8♠(6) A♥(12) 5♥(3) 10♥(8)
                Q♦(10) 4♠(2) 10♣(8) K♥(11) J♠(9) 8♣(6) J♣(9) 8♦(6) 9♦(7) 3♠(1) 10♦(8) K♦(11) 9♠(7)
                4♦(2) K♣(11) 5♣(3) 6♣(4) 2♦(0) 3♥(1) 7♥(5) Q♥(10) 4♣(2) 7♣(5) 6♦(4) 4♥(2) 7♠(5)

    you can see that my deck got shuffled. This is a useful method if you're writing a card game certainly, and a good
    tool in your arsenal, if you ever want to randomize a list of existing elements for some reason. Another method is
    the reverse method, and it's easier to see what that's doing after the shuffle method, so let me add that next.
*/
//End-Part-3

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck" , 4);

//Part-4
/*
        I'll call the reverse method on Collections, passing it my deck. I'll print the deck again, after this method.
    Running this code:

                ---------------------------
                Reversed Deck of Cards:
                7♠(5) 4♥(2) 6♦(4) 7♣(5) 4♣(2) Q♥(10) 7♥(5) 3♥(1) 2♦(0) 6♣(4) 5♣(3) K♣(11) 4♦(2)
                9♠(7) K♦(11) 10♦(8) 3♠(1) 9♦(7) 8♦(6) J♣(9) 8♣(6) J♠(9) K♥(11) 10♣(8) 4♠(2) Q♦(10)
                10♥(8) 5♥(3) A♥(12) 8♠(6) 7♦(5) 6♥(4) 2♥(0) 9♥(7) Q♠(10) 3♣(1) 3♦(1) A♠(12) 2♣(0)
                K♠(11) 10♠(8) A♣(12) 8♥(6) A♦(12) Q♣(10) J♦(9) 5♠(3) 9♣(7) 2♠(0) 5♦(3) 6♠(4) J♥(9)

    you see that this method simply reverses the order of the elements. It doesn't reorder it first, so the first element
    in the shuffled deck, J♥(9), becomes the last element in reversed deck, and vice versa. Since I mentioned sorting,
    let's talk about that next. You may run across legacy code that uses Collections to sort. There are 2 flavors much
    like the sort method on the List interface. One requires your elements in the list to implement Comparable, and one
    doesn't. Right now, I'll look at the one that doesn't. The collections.sort method takes your list as the first argument.
    If your elements implement Comparable, that would be it. But if they don't, or you want to force an alternative sort,
    you can pass a Comparator, as we've seen in previous lectures. My Card record doesn't implement Comparable, and let's
    just say, for whatever reason, I don't want it to.
*/
//End-Part-4

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck of Cards:", 4);

//Part-5
/*
        Instead I'll sort with a Comparator, which I'll set up as a local variable, because I think I may want to use this
    sort in different scenarios. Hopefully you remember the easy way of using the convenience methods on Comparator, which
    let me do this. I'll call the comparing method on Comparator, passing it a method reference for Card rank. I'll chain
    the thenComparing method to that, this time passing Card::suit. You can see, by looking at IntelliJ hints, I'm getting
    a Comparator<Card> back from those chained methods. Now I can pass my sortingAlgorithm variable to the Collections.sort
    method, so first pass the deck, then sorting algorithm. I'll call Card.printDeck, with a header specifying how the
    deck is sorted, but this time I want to print 13 rows. You'll see why in a minute. Running this code:

                ---------------------------
                Standard Deck sorted by rank, suit
                2♣(0) 2♦(0) 2♥(0) 2♠(0)
                3♣(1) 3♦(1) 3♥(1) 3♠(1)
                4♣(2) 4♦(2) 4♥(2) 4♠(2)
                5♣(3) 5♦(3) 5♥(3) 5♠(3)
                6♣(4) 6♦(4) 6♥(4) 6♠(4)
                7♣(5) 7♦(5) 7♥(5) 7♠(5)
                8♣(6) 8♦(6) 8♥(6) 8♠(6)
                9♣(7) 9♦(7) 9♥(7) 9♠(7)
                10♣(8) 10♦(8) 10♥(8) 10♠(8)
                J♣(9) J♦(9) J♥(9) J♠(9)
                Q♣(10) Q♦(10) Q♥(10) Q♠(10)
                K♣(11) K♦(11) K♥(11) K♠(11)
                A♣(12) A♦(12) A♥(12) A♠(12)

    you can see my cards are now ordered by the rank (and therefore their face value), from lowest to highest. This view
    is a little easier to see, that a standard deck of cards contains a two of every suit, and so on. Of course, I could
    have just called sort on Deck, and passed it that sortingAlgorithm. You might be asking which sort method should you
    use. I'll point out that IntelliJ has flagged this method in my code, and if I hover over that, we get the answer to
    that question, Collections.sort could be replaced with List.sort. In fact, if I control click on sort, and bring up
    the declaration of that method, you'll see that it simply calls list.sort anyway. When the sort method was added to
    the List interface, the underlying code on the Collections class was changed to leverage it to support backwards
    compatibility of code that still uses the Collections class sort. In other words, there's no need to rush out and
    change the legacy code, if you run across code still using Collections.sort. I won't change it in this code, but in
    the future, I'll be using List's sort method.
*/
//End-Part-5

        var sortingAlgorithm = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "Standard Deck sorted by rank, suit", 13);

//Part-6
/*
        Next, I'll call collections.reverse again so that my cards are sorted by highest to lowest rank. And I'll print
    that again, using 13 rows. Running that code:

                ---------------------------
                Sorted by rank, suit reversed:
                A♠(12) A♥(12) A♦(12) A♣(12)
                K♠(11) K♥(11) K♦(11) K♣(11)
                Q♠(10) Q♥(10) Q♦(10) Q♣(10)
                J♠(9) J♥(9) J♦(9) J♣(9)
                10♠(8) 10♥(8) 10♦(8) 10♣(8)
                9♠(7) 9♥(7) 9♦(7) 9♣(7)
                8♠(6) 8♥(6) 8♦(6) 8♣(6)
                7♠(5) 7♥(5) 7♦(5) 7♣(5)
                6♠(4) 6♥(4) 6♦(4) 6♣(4)
                5♠(3) 5♥(3) 5♦(3) 5♣(3)
                4♠(2) 4♥(2) 4♦(2) 4♣(2)
                3♠(1) 3♥(1) 3♦(1) 3♣(1)
                2♠(0) 2♥(0) 2♦(0) 2♣(0)

    you'll see the standard deck sorted by rank and suit reversed.
*/
//End-Part-6

        Collections.reverse(deck);
        Card.printDeck(deck, "Sorted by rank, suit reversed:", 13);

//Part-7
/*
        The next couple of methods let you compare sub lists to full lists. I'll carve out a couple of smaller lists from
    my standard deck here, using list's sublist method, and passing them to a new array list constructor. My First sublist
    will be kings, and since my deck is ordered, I know my kings are in the second row of my last output, so the starting
    index of the sublist is 4, and the ending index is 8, which is an exclusive index. And I'll print this new list in a
    single row. I'm going to copy and paste those 2 statements. I'll change kings to tens, and the indices used from 4 to
    8, to 16 and 20, because this should be a list of my 10 cards. Ok, running that:

                 ---------------------------
                Kings in deck
                K♠(11) K♥(11) K♦(11) K♣(11)
                ---------------------------
                Tens in deck
                10♠(8) 10♥(8) 10♦(8) 10♣(8)

    you can see I have 2 lists, one with kings, and the other with tens. Now I can use these lists to test a couple of
    other methods on Collections.
*/
//End-Part-7

        List<Card> kings = new ArrayList<>(deck.subList(4, 8));
        Card.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16, 20));
        Card.printDeck(tens, "Tens in deck", 1);

//Part-8
/*
        The first is indexOfSubList that returns an integer if it finds a sublist in the collection passed to it. The first
    argument, I'll make the deck, will be searched. The second argument, I'll make that the tens list. subListIndex will
    return an integer if tens is found in the deck, or a "-1" if it wasn't. And I'll print the result of that method call.
    Running that:

                ---(same)
                sublist index for tens = 16

    you can see this method told me where I could find the sublist in the full list, starting at index 16, which we already
    knew, but this method cold be useful if you ever needed to identify if some portion of a list already exists in a
    bigger list. Unlike contains, the elements in the sublist must be contiguously found in the full list. I'll add contains
    here next. I'll print contains equals, then call deck.containsAll, and pass tens there. Running that:

                ---(same)
                Contains = true

    you can see I got the result. So the list contains all the elements in the sublist. Now, I want to shuffle the cards,
    before executing these 2 tests, so I'll add that, Collections.shuffle and pass it deck, and running that:

                sublist index for tens = -1
                Contains = true

    you can see the sublist index is -1, meaning the sublist wasn't found in the full list, but contains all, is still
    returning true, so it found the four elements. I'll revert that last change, and comment the call to shuffle. Have
    you noticed that IntelliJ is highlighting containsAll, and if I hover over that, it gives me the message that containsAll
    might have a poor performance. The suggestion is to wrap deck in a HashSet constructor. Don't worry about this suggestion
    right now. Obviously, our deck only has 52 cards, and I'm only showing you the containsAll method, in comparison to
    the index of subList method. But this hint is a good teaser of things to come. After I finish covering the Collections
    methods, we'll be talking about Sets and HashSets.
*/
//End-Part-8

        //Collections.shuffle(deck);
        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("sublist index for tens = " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));

//Part-9
/*
        The next method I want to look at is, the disjoint method. This method returns true if the two lists have no elements
    in common. This method takes two collections, and returns true if the two collections don't share elements, or false
    if they do. I'll call this method, passing it my deck, and tens sublist, and I'll assign the result to a boolean variable
    I'll call disjoint. And I'll print the value of disjoint. Running this code:

                ---(same)
                disjoint = false

    you see that disjoint is false. In this code, I'm comparing the full list with just tens list, and since tens list is
    really just a sublist of deck, we know there's elements in common, so they aren't disjoint collections. Whether the
    tens are contiguous or not, disjoint should return false, meaning it found at least one of the elements in tens in
    the cards list.
*/
//End-Part-9

        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("disjoint = " + disjoint);

//Part-10
/*
        I'll copy and paste those two statements, I'll change the name of my variable to disjoint2 in that first statement,
    and change deck to my kings list. And in the second statement, I'll change disjoint to disjoint2 in both cases. Running
    this code:

                ---(same)
                disjoint = true

    you see that disjoint2 is true, meaning kings and tens don't have any elements in common. Now that we've covered shuffle,
    reverse, sort, indexOfSubList, and disjoint. In the next lecture, I want to finish up with the last few methods, which
    are included on the Collections class.
*/
//End-Part-10

        boolean disjoint2 = Collections.disjoint(kings, tens);
        System.out.println("disjoint = " + disjoint2);
    }
}
