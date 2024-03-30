# [Card Game Challenge]()
<div align="justify">


I'll create a new class in a **games** package 
under the same package, and call it **GameController**. 
Also, I'll add _main_ method in this class. 
Now, I'll create my card game class. 
I'm going to start building a **Poker game**, 
specifically five card draw.

This game usually has four or more players.

* The dealer shuffles the deck and asks another player to cut the deck.
* The dealer deals the cards one at a time to each player, 
starting with the player on the dealer's left, until each player has five cards.
* Each player evaluates his hand for certain card combinations, called card ranks.
* Each player can discard up to 3 cards.
* The dealer will replace discarded cards from the remaining pile, 
in the order they've been shuffled.
* Each player reevaluates his hand if he drew new cards, and bets on his hand.

This gives us quite a bit to work on, so let's get going. 
I'll be implementing this game up to the point where each 
hand is evaluated with a few of the possible card ranks. 
The first thing I want to do is set up some rankings for 
card combinations. 
Let me show you the rankings on a wiki-how page for this game. 
If you don't know how to play this game and want to learn, 
please search for it on [wikihow.com](www.wikihow.com). 
The think to notice here are what's called the card ranks, 
so the different combination of cards. 
The high card is the lowest rank, and five of a kind is the highest.
My own deck doesn't include a joker, 
so I'm never going to have five of a kind.
In my example, I won't be coding the straights or flushes. 
Getting back to the code, 
I want to create an enum that can represent these card ranks. 
I'll put this enum in a package called poker,
in the **games** package, and call it Ranking.

```java  
public enum Ranking {

    NONE, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND;

    @Override
    public String toString() {
        return this.name().replace('_',' ');
    }
}
```

I want to set up the _Hands_ with the worst hand 
in the first ordinal position, and that will be NONE, 
for nothing of value in a hand. 
Remember it's customary to make the enum constants all uppercase, 
and words separated by underscores.
I'll follow **NONE**, with **ONE PAIR**, **TWO PAIR**, 
**THREE of a KIND**, **FULL HOUSE**, 
and finally the best hand is **FOUR_OF_A_KIND**,
so it'll have the highest ordinal value. 
I want to end this constant list with a semicolon, 
because I'm going to override the _toString_ method on this. 
I'll insert a new line, and use the override features 
to generate a _toString_ method. I'll return the enum name value, 
which will be the constant name, 
but I want to replace any underscores with just spaces. 
That's all I need for my Ranking enum. 
Before I continue, I'm going to edit the Card record:

```java  
public static Comparator<Card> sortRankReversedSuit() {
    return Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
}
```

I'll add a public static method that returns a specific comparator for me. 
I'll insert that after the enum, and before the toString method. 
I'll make this public static with a type of Comparator, of type **Card**. 
The method will return some chained convenience methods, 
**Comparator.comparing**, using **Card**'s rank in a method reference, 
chained to reversed, so my higher-ranked cards will be listed first. 
I'll next chain then Comparing, using a method reference for the suit. 
Now I want to code a new class, called **PokerHand** in the same poker package.

```java  
public class PokerHand {
    private List<Card> hand;
    private List<Card> keepers;
    private List<Card> discards;
    private Ranking score = Ranking.NONE;
    private int playerNo;
}
```

A poker hand needs a list of cards, 
and I'm going to simply call that hand. 
This will be the hand that's dealt.
I want two other lists, one for the cards to keep, 
which I'll call keepers, 
and one for the cards I might to trade in, so discards. 
The dealer will be the last player,
the last hand to be dealt, in other words. 
This hand will also have a ranking, and that'll be my enum type, 
and I'll initialize that **NONE** to start. 
I also want a player number, an int, which is the order of the player, 
their placement from the dealer. 
_Player1_ is immediately next to the dealer, 
and then the dealer will be the last player, 
the last hand to be dealt, in other words.

```java  
public PokerHand( int playerNo, List<Card> hand) {
    hand.sort(Card.sortRankReversedSuit());      
    this.hand = hand;
    this.playerNo = playerNo;
    keepers = new ArrayList<>(hand.size());
    discards = new ArrayList<>(hand.size());
}
```

I want to generate a constructor and pick _hand_ 
and _playerNo_ fields for that. 
I'll reverse the order of the parameters 
because I prefer to have the _playerNo_ first. 
Next, I want to sort the hand that was dealt, 
so I'll just call sort on my list, 
and use my new static method on **Card**, 
_sortRankReversedSuit_, which gives me a Comparator. 
In addition to sorting and initializing hand there,
I'll initialize both keepers and discards to a new ArrayList, 
the same size as the hand.

```java  
@Override
public String toString() {
    return "%d. %-16s Rank:%d %-40s %s".formatted(
            playerNo, score, score.ordinal(), hand,
            (discards.size() > 0) ? "Discards:" + discards : "");
}
```

Finally, I want to generate a _toString_ method on this class, 
and instead of overriding toString, 
I'll generate it with IntelliJ's default way of doing that, 
but will select none. 
I'll rewrite the return part by using formatted String.
And I'll pass the _playerNo_, the _score_, our enum, 
which will print the score name, so two _Pair_, or 2 of a _kind_ 
for example. 
I'll also print the ordinal value of the score. 
I'll print the hand, and finally 
if there are any cards in the discard list, 
I'll print those out. 
Ok, now before going any further, 
I want another class, which will be the game, 
called **PokerGame**, also in the poker package.

```java  
public class PokerGame {
    private final List<Card> deck = Card.getStandardDeck();
    private int playerCount;
    private int cardsInHand;
    private List<PokerHand> pokerHands;
    private List<Card> remainingCards;

    public PokerGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);
    }
}
```

This game will have a deck of cards, 
which I'll initialize to a standard deck,
using the static method on **Card** to do that. 
Each poker game has a player count, 
the number of players playing, 
as well as a field called cards in hand, 
because different poker games are played
with a different number of cards. 
Maybe another variable option would be whether the game 
is draw or stud as an example.
I'll leave that for another day, 
and assume the poker games will always be played with draw.
I also want to maintain a list of the player's hands, 
and another list that will be the deck of remaining cards. 
I'll generate a constructor for this 
with the playerCount and cards in hand there. 
I'll initiate my poker Hands list in this constructor as well,
by creating a new array list, setting the capacity 
to cards in the hand.

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    Collections.rotate(deck, 26);
    Card.printDeck(deck);
}
```

I want a method on this game, which I'll call start play. 
It takes no parameters and returns no data, so just
public void. 
I'll shuffle the deck here, 
and print that out.
Now, let's say I want to provide a way to cut the deck next. 
When you cut the deck, you split the deck, 
often near the middle, and put the top part that you cut, 
under the lower part. 
I'll call **collections.rotate**, passing the deck, and 26, 
the exact center of the deck, and I'll again print the deck.
Let's run and test this from the GameController's main method.

```java  
public class GameController {

    public static void main(String[] args) {
        PokerGame fiveCardDraw = new PokerGame(4, 5);
        fiveCardDraw.startPlay();
    }
}
```

I'll instantiate the Poker Game with four players and five cards, 
and assign that to a local variable I'm calling five card draw, 
because that's the poker game I'm going to play. 
Then I'll call startPlay on that. 
If I run that code:

```java  
---------------------------
Current Deck
7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4) 5♦(3)
K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10) 3♥(1)
4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3) Q♦(10)
5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5) 9♣(7)
---------------------------
Current Deck
4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3) Q♦(10)
5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5) 9♣(7)
7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4) 5♦(3)
K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10) 3♥(1)
```
                
You can see I get the deck printed twice. 
The first is my shuffled deck, and the second is the result of splitting
and cutting my deck, exactly in half. 
From this, I hope you can see that the first two rows in the first display of
the deck are now the last two rows in the second output. 
Would anyone really split the deck perfectly in the middle?
Probably not, so I'll just randomize that cut a bit. 
Going back to the startPlay method, in PokerGame,

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    //Collections.rotate(deck, 26);
    //Card.printDeck(deck);
    int randomMiddle = new Random().nextInt(15, 35);
    Collections.rotate(deck, randomMiddle);
    Card.printDeck(deck);
}
```

I'll generate a random int, let's say somewhere between 
the 15th or 35th card in the deck. 
I'll insert a statement that calls _nextInt_ on a new Random instance 
with 15 and 35 passed to that method, 
assigning the result, to a local variable called _randomMiddle_. 
I'll replace 26 in the _rotate_ call with the value in _randomMiddle_.
If I run this code from the main method again:

```java  
---------------------------
Current Deck
7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4) 5♦(3)
K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10) 3♥(1)
4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3) Q♦(10)
5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5) 9♣(7)
---------------------------
Current Deck
3♥(1) 4♦(2) J♠(9) 4♣(2) A♠(12) 10♦(8) Q♠(10) A♦(12) 10♠(8) A♣(12) Q♣(10) 2♣(0) 5♥(3)
Q♦(10) 5♠(3) 10♥(8) 7♠(5) 8♥(6) 2♠(0) 5♣(3) 3♦(1) 2♦(0) K♠(11) 4♠(2) J♥(9) 7♥(5)
9♣(7) 7♦(5) 8♣(6) 9♥(7) 10♣(8) 9♦(7) 2♥(0) K♣(11) J♦(9) 9♠(7) 4♥(2) 8♠(6) 6♣(4)
5♦(3) K♦(11) 3♣(1) J♣(9) 8♦(6) 7♣(5) K♥(11) A♥(12) 3♠(1) 6♦(4) 6♥(4) 6♠(4) Q♥(10)
```
            
It's a bit harder to see where the cards were rotated, 
but it works in the same way.
Now I need to deal my deck. 
I've said I want to deal these one cards at a time. 

```java  
private void deal() {

    Card[][] hands = new Card[playerCount][cardsInHand];
    for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
        for (int j = 0; j < playerCount; j++) {
            hands[j][i] = deck.get(deckIndex++);
        }
    }

    int playerNo = 1;
    for (Card[] hand : hands) {
        pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
    }
}
```

I'm going to create a new private method, again void, 
called deal with no parameters. 
I'll first set up a 2D array of Cards, 
so one array of cards for each player. 
Each player's array will contain the set of cards in the hand. 
To deal the card one at a time, I'll cycle through the deck, 
one card at a time, for each card in a hand.
Then I'll loop through each player, 
assigning each card in each player's hand a unique card off the deck, 
using the deckIndex variable, to keep track of the place in the full deck. 
I want to create my poker hands from this data.
So first, I want a variable for the player number. 
I'll loop through this 2D array, and instantiate a new Poker Hand for each player, 
passing it each player's array of cards, using **Arrays.asList** in the constructor. 
Back in the startPlay method.

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    //Collections.rotate(deck, 26);
    //Card.printDeck(deck);
    
    int randomMiddle = new Random().nextInt(15, 35);
    Collections.rotate(deck, randomMiddle);
    Card.printDeck(deck);
    
    deal();
    System.out.println("---------------------------");
    pokerHands.forEach(System.out::println);
}
```

Call the _deal_ method. 
Print each poker hand. 
Running this code now,

```java  
---------------------------
1. NONE             Rank:0 [A♠(12), 10♠(8), 7♠(5), 5♥(3), 3♥(1)]
2. NONE             Rank:0 [A♣(12), Q♦(10), 10♦(8), 8♥(6), 4♦(2)]
3. NONE             Rank:0 [Q♣(10), Q♠(10), J♠(9), 5♠(3), 2♠(0)]
4. NONE             Rank:0 [A♦(12), 10♥(8), 5♣(3), 4♣(2), 2♣(0)]
```
                
You can see the cards in each player's hands. 
The rank is zero for all hands, because I still have to write the code 
to evaluate the cards. 
It's a little hard to confirm whether 
I've dealt these correctly, so for just a minute, 
I'll comment out the sort in the poker hand constructor. 

```java  
public PokerHand( int playerNo, List<Card> hand) {
    //hand.sort(Card.sortRankReversedSuit());
    this.hand = hand;
    this.playerNo = playerNo;
    keepers = new ArrayList<>(hand.size());
    discards = new ArrayList<>(hand.size());
}
```

And I'll run that again.

```java  
---------------------------
Current Deck
4♦(2) 5♥(3) 2♠(0) K♥(11) 8♠(6) 8♥(6) 9♦(7) 5♠(3) Q♦(10) J♥(9) 10♥(8) 9♣(7) J♣(9)
K♣(11) 6♠(4) J♠(9) 3♠(1) Q♣(10) 2♦(0) 6♦(4) 8♣(6) 6♣(4) 3♣(1) 9♥(7) 3♦(1) K♠(11)
2♥(0) 3♥(1) 4♠(2) 2♣(0) 4♣(2) 7♣(5) A♠(12) 10♠(8) 10♣(8) 4♥(2) Q♥(10) 5♣(3) 9♠(7)
J♦(9) 7♦(5) 8♦(6) 7♠(5) A♥(12) 7♥(5) A♦(12) 10♦(8) K♦(11) Q♠(10) 6♥(4) A♣(12) 5♦(3)
---------------------------
Current Deck
3♦(1) K♠(11) 2♥(0) 3♥(1) 4♠(2) 2♣(0) 4♣(2) 7♣(5) A♠(12) 10♠(8) 10♣(8) 4♥(2) Q♥(10)
5♣(3) 9♠(7) J♦(9) 7♦(5) 8♦(6) 7♠(5) A♥(12) 7♥(5) A♦(12) 10♦(8) K♦(11) Q♠(10) 6♥(4)
A♣(12) 5♦(3) 4♦(2) 5♥(3) 2♠(0) K♥(11) 8♠(6) 8♥(6) 9♦(7) 5♠(3) Q♦(10) J♥(9) 10♥(8)
9♣(7) J♣(9) K♣(11) 6♠(4) J♠(9) 3♠(1) Q♣(10) 2♦(0) 6♦(4) 8♣(6) 6♣(4) 3♣(1) 9♥(7)
---------------------------
1. NONE             Rank:0 [3♦(1), 4♠(2), A♠(12), Q♥(10), 7♦(5)]
2. NONE             Rank:0 [K♠(11), 2♣(0), 10♠(8), 5♣(3), 8♦(6)]
3. NONE             Rank:0 [2♥(0), 4♣(2), 10♣(8), 9♠(7), 7♠(5)]
4. NONE             Rank:0 [3♥(1), 7♣(5), 4♥(2), J♦(9), A♥(12)]
```

Now, it's a little easier to see that my first player's 
hand starts with the first card, 
then it has the fifth card in the deck, 
because the other three cards went to the other player's hands, 
and then it has the ninth card and so on. 
But I want to keep my hand sorted, 
so I'll revert that last change I made to the pokerHand. 

We're about halfway through building my card game, 
a poker game called five card draw. 
I've dealt the hands, but I haven't done anything with them. 
Before I do the evaluation of the cards in a poker hand, 
I'll create a new List for the remaining cards the ones not dealt. 
I'll do this in my _startPlay_ method, in **PokerGame**. 

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    //Collections.rotate(deck, 26);
    //Card.printDeck(deck);
    
    int randomMiddle = new Random().nextInt(15, 35);
    Collections.rotate(deck, randomMiddle);
    Card.printDeck(deck);

    deal();
    System.out.println("---------------------------");
    pokerHands.forEach(System.out::println);

    int cardsDealt = playerCount * cardsInHand;
    int cardsRemaining = deck.size() - cardsDealt;

    //remainingCards = new ArrayList<>(cardsRemaining);
    remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
    remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
    Card.printDeck(remainingCards, "Remaining Cards", 2);
}
```

First, I'll create a couple of local variables. 
_cardsDealt_ is just the player count, times the cards in hand fields. 
_CardsRemaining_ is the full deck size, minus the cards dealt.
I'm going to use _nCopies_ on the **Collections** class 
to create a new List. 
This is not the easiest way to do this,
but I do want to just revisit this method for a minute. 
I'll instantiate a new ArrayList. 
Passing a sublist of deck to the constructor is probably 
the most straightforward way to do this. 
But instead, I want to make a call to the _nCopies_ method on **Collections**, 
passing it the number of cards remaining as the first argument, 
and null as the second argument. 
This basically fills my list with the number of elements specified 
and null references. 
Why would I want to do this? 
Well, remember, if we just create an array list with no arguments,
or a capacity only, that means we've only got a zero element list, 
so we couldn't use the _fill_ method on collections, 
or the replaceAll method on list to quickly populate our list 
with elements based on some function. 
I'll use _replaceAll_ here next to show you what I mean.
It gets the current index in the remainingCards list, 
then adds cardsDealt to that to pick the card from the full deck.
I'll add a lambda expression that will loop through 
all the elements in my list (which now consists of 32 null references). 
And print the remaining cards. 
You might ask why I'd do something so convoluted.

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    //Collections.rotate(deck, 26);
    //Card.printDeck(deck);
    
    int randomMiddle = new Random().nextInt(15, 35);
    Collections.rotate(deck, randomMiddle);
    Card.printDeck(deck);

    deal();
    System.out.println("---------------------------");
    pokerHands.forEach(System.out::println);

    int cardsDealt = playerCount * cardsInHand;
    int cardsRemaining = deck.size() - cardsDealt;

    remainingCards = new ArrayList<>(cardsRemaining);
    //remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
    remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
    Card.printDeck(remainingCards, "Remaining Cards", 2);
}
```

Let me insert another statement, where I simply instantiate 
remaining cards to a new ArrayList 
with a capacity of the remaining cards number. 
And I'll comment out the next statement with respect to this statement. 
If I run that:

```java  
---------------------------
Remaining Cards
```

I don't get an exception, but I don't get remaining cards printed either, 
and that's because I'm trying to use _replaceAll_ on an empty list. 
If you plan to use _replaceAll_ with a function 
that would make your code simpler, then this option, 
using _nCopies_ to fill the list with nulls,
is one way to prevent the empty list scenario I just showed you. 
Let me revert to those last two changes. 

```java  
//remainingCards = new ArrayList<>(cardsRemaining);
remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
```

Running that code with four players,

```java  
---------------------------
Remaining Cards
K♣(11) 8♠(6) 3♣(1) 5♦(3) 4♦(2) 6♦(4) 10♦(8) 10♣(8) 6♠(4) J♦(9) 9♠(7) Q♥(10) K♦(11) A♦(12) A♠(12) 9♥(7)
9♣(7) 6♥(4) 4♣(2) 4♥(2) 10♥(8) 5♥(3) Q♦(10) J♠(9) 7♥(5) 2♣(0) A♣(12) K♠(11) 6♣(4) J♥(9) 5♣(3) A♥(12)
```

I get 32 cards as the remaining cards, because I used 20 cards for my 4 players, 
it's true, I could have just passed the sublist of deck to the array list constructor, 
but since one of my objectives was to use as many methods on Collections, 
I thought I'd revisit that particular one. 
Ok, so lastly, I'm going to write the code that can evaluate each player's hand.
In a real game, you maybe would only do this 
if you had some automated competitors, or if you're teaching someone how to play. 
I'm going to evaluate only half of the valid hand ranks, 
the ones associated with duplicate face cards in a hand, for simplicity.
Ok, so getting back to the **PokerHand**, I want to create a method called _setRank_.

```java  
private void setRank(int faceCount) {
    switch (faceCount) {
        case 4 -> score = Ranking.FOUR_OF_A_KIND;
        case 3 -> {
            if (score == Ranking.NONE) score = Ranking.THREE_OF_A_KIND;
            else score = Ranking.FULL_HOUSE;
        }
        case 2 -> {
            if (score == Ranking.NONE) score = Ranking.ONE_PAIR;
            else if (score == Ranking.THREE_OF_A_KIND) score = Ranking.FULL_HOUSE;
            else score = Ranking.TWO_PAIR;
        }
    }
}
```

This will take an integer parameter, I'm calling _faceCount_, 
which is the frequency of a face card, for example,
how many kings are in my hand. 
I'll use that to determine the ranking and score of the hand.
I'll start with a switch on the method argument. 
Case 4 means I have _4 of a kind_, 
so I'll set the score to that rank.
Next, I want to look at case 3, meaning I have _3 of a kind_ 
but if I've already found _2 of a kind_, 
then I have a full house, which is 
both _3 of a kind_ and _2 of a kind_ in one hand. 
I'll check if the current score is **NONE**, 
and if it is, I'll set the score is _3 of a kind_, otherwise, 
I'll set it to _FULL HOUSE_. 
Similarly, when I find two cards that have the same face card, 
I want to check if some other match was found. 
First, I'll check if the current score is _NONE_, 
which means this is the first pair found, 
so I'll set the score to _ONE_PAIR_. 
Next, I'll check if a _3 of a kind_ was already 
identified in the hand, and if so, 
I'll set the score to _FULL HOUSE_. 
Otherwise, this means I have two pairs,
and I'll set the score to that in the last else. 
Now that I've got a method to set score, 
I can set up an _evalHand_ method.

```java  
public void evalHand() {

    List<String> faceList = new ArrayList<>(hand.size());
    hand.forEach(card -> faceList.add(card.face()));

    List<String> duplicateFaceCards = new ArrayList<>();
    faceList.forEach(face -> {
        if (!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1) {
            duplicateFaceCards.add(face);
        }
    });

    for (String duplicate : duplicateFaceCards) {
        int start = faceList.indexOf(duplicate);
        int last = faceList.lastIndexOf(duplicate);
        setRank(last - start + 1);
        List<Card> sub = hand.subList(start, last + 1);
        keepers.addAll(sub);
    }
}
```

This is going to be a public method 
that takes no arguments and returns no data. 
I'm making it public 
because I may want to allow clients to use it. 
The first thing I want to do is to create a list of Strings, 
I'll call it face list, the same size as the number of cards in a hand. 
And I'll use the _forEach_ method on hand, 
to loop through each card in the hand,
and populate the list with the face card attributes. 
Next, I'll create another List of Strings, 
and this will just be the face cards 
where multiples are found. 
Later, when I cover Sets, 
I'll show you a simpler way to remove duplicates, 
but for now, I'll check if this list already has the face card, 
using the contains method. 
If it doesn't, then I'll also use the frequency method on Collections, 
passing it my face list, and the current face value. 

```java  
!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1
```

This will let me check 
if this face card has duplicates, 
meaning the frequency is greater than 1. 
If both those conditions are true,
then the face card will get added to the duplicate Face Cards list. 
Now that I have a list of face cards that have duplicates,
I can loop through this list to start ranking my hand.
I'll get the index of the first face card in the series of the group, 
using the indexOf method on the face list.
I'll assign that to a variable called start. 
The last index is the last face card in the series of multiple cards 
of the same face card value, 
and retrieved using lastIndexOf. 
I can call set Rank, getting the number of cards of the same face value, 
using simple math, so "_last - start + 1_".
I'll retrieve the sublist, using the start and last indices I got. 
I'll add the sublist to my keepers list, or the cards
I want to keep in my hand. Ok, that's how 
I'm going to evaluate and score each player's hand.
Now I'll get back to the PokerGame, and the startPlay method.

```java  
public void startPlay() {

    Collections.shuffle(deck);
    Card.printDeck(deck);
    //Collections.rotate(deck, 26);
    //Card.printDeck(deck);
    
    int randomMiddle = new Random().nextInt(15, 35);
    Collections.rotate(deck, randomMiddle);
    Card.printDeck(deck);

    deal();
    System.out.println("---------------------------");
    pokerHands.forEach(pokerHands.forEach(checkHand.andThen(System.out::println)););

    int cardsDealt = playerCount * cardsInHand;
    int cardsRemaining = deck.size() - cardsDealt;

    remainingCards = new ArrayList<>(cardsRemaining);
    //remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
    remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
    Card.printDeck(remainingCards, "Remaining Cards", 2);
}
```

I want to insert a new statement after this separator line, 
before the _forEach_ call on pokerHands. 
For fun, I'll set up a consumer lambda expression, 
a method reference for the eval hand method I just created, 
so _PokerHand::evalHand_.
After that, I want to change what I have in the call 
to the _forEach_ method on the next line, 
to use the _checkHand_ lambda, chaining _andThen_ 
to that with the method reference to print the hand.

In a single statement, I can evaluate the hand and print it. 
Now, running this code:

```java  
---------------------------
Current Deck
4♦(2) Q♥(10) 8♠(6) 3♦(1) 6♦(4) 8♣(6) K♣(11) 7♣(5) Q♠(10) 6♠(4) 10♣(8) 3♥(1) 10♥(8)
J♦(9) A♦(12) 9♠(7) 4♠(2) 4♥(2) K♠(11) 5♥(3) 8♦(6) 4♣(2) J♥(9) 9♣(7) 2♠(0) 7♠(5)
7♦(5) 5♣(3) Q♦(10) 6♥(4) 10♠(8) 2♦(0) 5♠(3) 5♦(3) 9♦(7) Q♣(10) 10♦(8) 2♥(0) 6♣(4)
A♥(12) 8♥(6) J♠(9) K♥(11) 3♣(1) J♣(9) 9♥(7) 7♥(5) A♠(12) 3♠(1) A♣(12) K♦(11) 2♣(0)
---------------------------
1. ONE PAIR         Rank:1 [Q♠(10), 10♥(8), 6♦(4), 4♦(2), 4♠(2)]
2. NONE             Rank:0 [Q♥(10), J♦(9), 8♣(6), 6♠(4), 4♥(2)]
3. ONE PAIR         Rank:1 [A♦(12), K♣(11), K♠(11), 10♣(8), 8♠(6)]
4. ONE PAIR         Rank:1 [9♠(7), 7♣(5), 5♥(3), 3♦(1), 3♥(1)]
```
                    
You can see the Player's hands. 
You might have to run it a couple of times 
to see some interesting scores. 
However, hopefully you can see _ONE PAIR_, and if I keep running it
_TWO PAIRS_, I might get to see a _3 of a kind_ or a _full house_. 
Going to GameController Class,

```java  
public class GameController {

    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(8, 5);
        fiveCardDraw.startPlay();
    }
}
```

I'll change my players, to let's say eight players in the GameController, 
and run it again which lets us see more hands.

```java  
---------------------------
1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]
2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]
3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]
4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]
5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]
6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]
7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]
8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]
```
                
And I can keep running that, until I see more interesting hands show up. 
If you're lucky, you may see even better hands 
like a _full house_ or _four of a kind_. 
Ok, so this code meets all the challenge requirements.
Next, for good measure, I'm going to populate the discard pile for each player. 
This will be a suggested list of cards 
to discard based on cards that
aren't part of a card rank as well as lower ranked cards. 
I'll do this in PokerHands.

```java  
private void pickDiscards() {

    List<Card> temp = new ArrayList<>(hand);
    temp.removeAll(keepers);
    int rankedCards = keepers.size();
    Collections.reverse(temp);
    int index = 0;
    for (Card c : temp) {
        if (index++ < 3 && (rankedCards > 2 || c.rank() < 9)) discards.add(c);
        else keepers.add(c);
    }
}
```

I'll create a new method on the _PokerHand_, 
called _pickDiscards_, again private and
void with no parameters. 
I'll create a local variable called temp 
that will be a copy of my hand,
so new Array list and pass it the hand. 
I'll follow that by calling _removeAll_ on the list, 
removing anything that's in the _keepers_ list. 
This might be good enough in some circles, 
but you may not want to throw away higher ranked cards, 
like an _Ace_ or _King_, 
so I'm going to check for these scenarios. 
I'll set up a local variable for 
how many of my cards are part of a card rank,
and I'll call that _rankedCards_, equal to the size of keepers. 
I want to reverse the order of my temporary list of remaining cards, 
using **Collections.reverse**, 
so that the lowest ranked cards are first. 
I'll set up a local variable index, 
starting at 0, then loop through each card in the temp list. 
I only want to add the card to the discard pile 
if I don't yet already have 3, 
and if the number of rankedCards is 3 or more, 
or the rank is less than 9. 
Otherwise, the card gets added back to the _keepers_ pile. 
I'll make a call to this at the end of the evalHand method.

```java  
public void evalHand() {

    List<String> faceList = new ArrayList<>(hand.size());
    hand.forEach(card -> faceList.add(card.face()));

    List<String> duplicateFaceCards = new ArrayList<>();
    faceList.forEach(face -> {
        if (!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1) {
            duplicateFaceCards.add(face);
        }
    });

    for (String duplicate : duplicateFaceCards) {
        int start = faceList.indexOf(duplicate);
        int last = faceList.lastIndexOf(duplicate);
        setRank(last - start + 1);
        List<Card> sub = hand.subList(start, last + 1);
        keepers.addAll(sub);
    }
    
    pickDiscards();
}
```

And running this code:

```java  
---------------------------
1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]     Discards:[4♥(2), 6♠(4)]
2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]   Discards:[4♣(2), 9♣(7), 10♣(8)]
3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]   Discards:[7♦(5), 10♥(8)]
4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]      Discards:[3♠(1), 5♦(3), 9♥(7)]
5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]     Discards:[4♦(2), 5♥(3)]
6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]     Discards:[3♣(1), 9♦(7)]
7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]    Discards:[2♣(0), 4♠(2), 10♦(8)]
8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]    Discards:[3♦(1), 9♠(7), 10♠(8)]
```
            
Now you'll see the preliminary recommendation of cards to discard.
Again, maybe you'd do this for the hands 
that are automated, or if you're teaching someone new how to play.
For hands that are two pairs, 
you'd only have one card in a discard pile, 
because you'll want to keep both pairs, presumably. 
For one pair of hands, 
you'll have three cards to discard, 
but cards that are 10 or higher won't be included in the discards. 
Finally, I want to use the min and max methods on collections, 
to print the hand's highest and lowest cards. 
I could do this, by just getting the first and last card in my sorted hand, 
it's true, but let's imagine we have a different way of evaluating this,
and want a different order.
I'll go to the _toString_ method on my **PokerHand**.

```java  
@Override
public String toString() {
    return "%d. %-16s Rank:%d %-40s Best:%-7s Worst:%-6s %s".formatted(
            playerNo, score, score.ordinal(), hand,
            Collections.max(hand, Comparator.comparing(Card::rank)),
            Collections.min(hand, Comparator.comparing(Card::rank)),
            (discards.size() > 0) ? "Discards:" + discards : "");
}
```

I'll add some additional text to the format String 
and two specifiers, and I'll insert those before 
the final _%s_ specifier, so _Best:%-7s_, and _Worst:%-6s_. 
And I'll have two more variables in the list passed, 
so before the discards part. 
The first will call max on the Collections class, 
passing the hand, but sorting by rank only. 
And then I'll call _min_ with the same sort. 
And If I run the code now:

```java  
---------------------------
1. ONE PAIR         Rank:1 [K♠(11), 6♠(4), 4♥(2), 2♦(0), 2♠(0)]     Best:K♠(11)  Worst:2♦(0)  Discards:[4♥(2), 6♠(4)]
2. NONE             Rank:0 [A♥(12), K♦(11), 10♣(8), 9♣(7), 4♣(2)]   Best:A♥(12)  Worst:4♣(2)  Discards:[4♣(2), 9♣(7), 10♣(8)]
3. ONE PAIR         Rank:1 [A♣(12), A♦(12), J♣(9), 10♥(8), 7♦(5)]   Best:A♣(12)  Worst:7♦(5)  Discards:[7♦(5), 10♥(8)]
4. ONE PAIR         Rank:1 [9♥(7), 8♦(6), 8♥(6), 5♦(3), 3♠(1)]      Best:9♥(7)   Worst:3♠(1)  Discards:[3♠(1), 5♦(3), 9♥(7)]
5. ONE PAIR         Rank:1 [K♥(11), 6♦(4), 6♥(4), 5♥(3), 4♦(2)]     Best:K♥(11)  Worst:4♦(2)  Discards:[4♦(2), 5♥(3)]
6. ONE PAIR         Rank:1 [Q♠(10), 9♦(7), 7♥(5), 7♠(5), 3♣(1)]     Best:Q♠(10)  Worst:3♣(1)  Discards:[3♣(1), 9♦(7)]
7. NONE             Rank:0 [Q♥(10), J♥(9), 10♦(8), 4♠(2), 2♣(0)]    Best:Q♥(10)  Worst:2♣(0)  Discards:[2♣(0), 4♠(2), 10♦(8)]
8. NONE             Rank:0 [A♠(12), J♦(9), 10♠(8), 9♠(7), 3♦(1)]    Best:A♠(12)  Worst:3♦(1)  Discards:[3♦(1), 9♠(7), 10♠(8)]
```
                
You can see the best card and the worst card listed there. 
In some card games, one suit may rank higher 
when it becomes a trump suit, for example, 
and you might need another way of determining 
what the best card in your hand is. 
Ok, I'll end this challenge code here. 
I tried to use as many of the Collections methods as possible, 
even in cases where maybe their use was questionable,
but I wanted to give you a good review. 
I used _shuffle_, _reverse_, _min_ and _max_, _nCopies_,
_frequency_, and _rotate_ in this code. 
Perhaps you found ways to use swap or index of sublist, for example.
</div>
