package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course06_CollectionsChallenge_CardGamePart1.games;

import Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_15_Collections.Course06_CollectionsChallenge_CardGamePart1.games.poker.PokerGame;

//Part-1
/*
                                           The Card Game Challenge

        In the last lecture, I introduced you to quite a few methods on the java.util.Collections class, and showed you
    examples, using a deck of cards. Now, it's your turn. Think for a moment about a card game that you'd enjoy building,
    and one you know some of the rules for.
        1. "Create a deck of cards", either a standard deck, or a deck that's specialized to the card game you want to
        create. Feel free to use my Card record from the last several lectures as a place to start, or if you want to start
        from scratch with your own Card class, by all means, try that out.
        2. "Shuffle your deck." Maybe you want to include cutting the deck, after shuffling.
        3. "Deal your players' hands." Pick the number of players playing, and figure out how you'll deal the cards, one
        at a time to each hand, or some other way. For example, in pinochle, it's common to deal the cards out 3 or 4 at
        a time to each player, until the deck is exhausted.
        4. "Evaluate your players' hands" for card combinations that are important to the game.
        5. Use a combination of java.util.Collections and List methods, to achieve your results.

*/
//End-Part-1

//Part-2
/*
        I'll create a new class in a games package under the same package, and call it GameController. Also I'll add main
    method in this class. Now, I'll create my card game class. I'm going to start building a Poker game, specifically 5
    card draw.

                                                    Poker : Five Card Draw

        This game usually has four or more players.

     * The dealer shuffles the deck, and asks another player to cut the deck.
     * The dealer deals the cards one at a time to each player, starting with the player on the dealer's left, until each
       player has 5 cards.
     * Each player evaluates his hand for certain card combinations, called card ranks.
     * Each player can discard up to 3 cards.
     * The dealer will replace discarded cards from the remaining pile, in the order they've been shuffled.
     * Each player reevaluates his hand if he drew new cards, and bets on his hand.

    This gives us quite a bit to work on, so let's get going. I'll be implementing this game up to the point where each
    hand is evaluated with a few of the possible card ranks. The first thing I want to do is set up some rankings for
    card combinations. Let me show you the rankings on a wiki-how page, for this game. If you don't know how to play this
    game, and want to learn, please search for it on wikihow.com. The think to notice here are what's called the card
    ranks, so the different combination of cards. The high card is the lowest rank, and 5 of a kind is the highest. My
    own deck doesn't include a joker, so I'm never going to have 5 of a kind. In my example, I won't be coding the straights
    or flushes. Getting back to the code, I want to create an enum that can represent these card ranks. I'll put this enum
    in a package called poker, in the games package, and call it Ranking.
*/
//End-Part-2

public class GameController {

    public static void main(String[] args) {

//Part-10
/*
        I'll instantiate the Poker Game with 4 players and 5 cards, and assign that to a local variable I'm calling five
    card draw, because that's the poker game I'm going to play. Then I'll call startPlay on that. If I run that code:

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

    you can see I get the deck printed twice. The first is my shuffled deck, and the second is the result of splitting
    and cutting my deck, exactly in half. From this, I hope you can see, that the first 2 rows in the first display of
    the deck, are now the last two rows in the second output. Would anyone really split the deck perfectly in the middle?
    Probably not, so I'll just randomize that cut a bit. Going back to the startPlay method, in PokerGame,
*/
//End-Part-10

        PokerGame fiveCardDraw = new PokerGame(4, 5);
        fiveCardDraw.startPlay();

    }
}
