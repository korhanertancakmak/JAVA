package CourseCodes.NewSections.Section_15_Collections.Course08_HashCodes_Sets_Maps;

//Part-7
/*
        This time I'll create my own class, again a playing card, called PlayingCard in the same package. I'll add 3 fields,
    suit and face as strings, and an integer I'll call internalHash. I'll generate a constructor with only 2 arguments.
    For the moment, I'll add a statement to set the internalHash field to one.

                                        this.internalHash = 1

    I'll explain this field in a little bit. I'll quickly add a toString method, creating the default override. I'll return
    the face and the suit, in place of super toString. Ok, so that's the playing card. I'll go back to the main method
    on the main class, and now set up a couple of cards,
*/
//End-Part-7

public class PlayingCard {

    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        //this.internalHash = 1;                                        >>> Commented via Part-12
        this.internalHash = (suit.equals("Hearts")) ? 11 : 12;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

//Part-11
/*
        Now, instead of returning super.equals in the equals method, which we know just calls the equals method on Object,
    I'll just return true, so that all my objects will be equal no matter what.

                                            return super.equals(o);
                                                    to
                                            return true;

    But first I want to print out something from this method, like checking playing card equality, so I can see when this
    method is called. Ok, I'll run the code now. This gives me the same result as before, but it doesn't appear the equals
    method on Playing card was ever executed, because my statement I just added was never printed. I want to change the
    hashCode method here next,
*/
//End-Part-11

/*
    @Override
    public boolean equals(Object o) {
        //return super.equals(o);                                       >>> Commented via Part-13

        System.out.println("--> Checking Playing Card equality");
        return true;
    }
*/

//Part-12
/*
        This time, I'm just going to return my internal hash field, which is currently just set to 1. Now, I want to run
    it again:

                ---(same)
                Ace of Hearts: 1
                King of Clubs: 1
                Queen of Spades: 1
                --> Checking Playing Card equality
                Found a duplicate for King of Clubs
                --> Checking Playing Card equality
                Found a duplicate for Queen of Spades
                [Ace of Hearts]

    you can see, the hashCode is 1, for all 3 cards. But now you can see, the code is calling the equals method during
    the second and third iteration of the loop, and it indicates it's finding duplicates in each case. The final result
    is that it considered every other playing card a duplicate, and so only one card is in the set, the first in our list,
    the ace of hearts. The only time the equals method is called when adding elements to the hash set is when the hash
    code algorithm returns the same bucket identifier, which is true here, for our hash code = 1.

        Let's go up, and change the internal hash to return an 11 if the suit is hearts, and 12 if it doesn't. Let's run
    this:

                ---(same)
                Ace of Hearts: 11
                King of Clubs: 12
                Queen of Spades: 12
                --> Checking Playing Card equality
                Found a duplicate for Queen of Spades
                [Ace of Hearts, King of Clubs]

    you can see the hash code for the ace of hearts is 11, but in other cases it's 12. Where there is a duplicate hash
    code, the equals method is called, which always returns true, so the third element, the Queen of Spades, doesn't
    get added. Let's comment these 2 overridden methods, hashCode and equals, and instead use IntelliJ's code generation
    tools to regenerate them. The first time you go into this tool, the Template won't show IntelliJ default, so select
    that like so.
*/
//End-Part-12

/*
    @Override
    public int hashCode() {
        //return super.hashCode();                                      >>> Commented via Part-13
        return internalHash;
    }
*/

//Part-13
/*
        Let's comment these 2 overridden methods, hashCode and equals, and instead use IntelliJ's code generation tools
    to regenerate them. The first time you go into this tool, the Template won't show IntelliJ default, so select that
    like so. We want to use getters so tick that box. Leave the other checkbox unchecked. Click next. For fields to be
    included in equals, ensure only suit and face are selected and click next. For fields to be included in hashcode,
    select box fields, and Next again. You actually now want to check both boxes. But I want to show you the code that
    is generated if this is not checked. So I'll ensure both are de-selected here and click Create. Looking at the hash
    code, we have these extra tests for null. Which we really don't need. So what I am going to do is undo this with ctrl
    + Z, and go through the creating of the methods again. Note that this time IntelliJ remembers my Template selection
    and also that I checked the use getters during code generation. I'll click Next. I'll de-select internal Hash like
    last time and click next. For hashcode I'll leave both fields selected and click Next. This time for non-null fields,
    I'll check both, as we know these won't be null and click Create again. This is as good as any implementation for
    these methods.
*/
//End-Part-13

//Part-14
/*
        Notice that the equals method first checks for equal references, because 2 references pointing to the same object
    is the surest test of equality. Then it compares the result of the getClass methods on both objects, and if they're
    equal, it will cast the object passed to a Playing Card. It then checks if the suits are equal. If they are, it will
    check if the faces are equal. If they are, it will check if the faces are equal.
*/
//End-Part-14

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayingCard that = (PlayingCard) o;

        if (!suit.equals(that.suit)) return false;
        return face.equals(that.face);
    }

//Part-15
/*
        You can see the hashCode implementation that was generated. It starts with the hashCode for the string for the
    suit. It then uses that, multiplying it by 31, a small prime number, and adding the hash code for the string, the
    face string's hash code. Running this code:

                ---(same)
                Ace of Hearts: -1834509066
                King of Clubs: 2023815418
                Queen of Spades: -269088580
                [Ace of Hearts, King of Clubs, Queen of Spades]

    you see our set has all 3 cards in it, and the hash codes for each card is unique.

                                            Creating HashCode Method

        You don't have to use the generated algorithm as I did here. You could create your own, but your code should stick
    to the following rules.

        1. It should be very fast to compute.
        2. It should produce a consistent result each time it's called. For example, you wouldn't want to use a random
           number generator, or a date time based algorithm that would return a different value each time the method is
           called.
        3. Objects that are considered equal should produce the same hashCode.
        4. Values used in the calculation should not be mutable.

    It is common practice to include a small prime number as a multiplicative factor (although some non-prime numbers also
    provide good distributions). This helps ensure a more even distribution for the bucket identifier algorithm, especially
    if your data might exhibit clustering in some way. IntelliJ and other code generation tools use 31, but other good
    options could be 29, 33 (not prime but shown to have good results), 37, 43 and so on. You want to avoid the single
    digit primes, because more numbers will be divisible by those, and may not produce the even distribution that will
    lend itself to improved performance. For those who like to understand how things really work, let me encourage you
    to do some research on this topic. For the rest of you, remember that if you are using your own classes in hashed
    collections, you'll want to override both the equals and the hashCode methods. I'll be covering this quite a bit over
    the next couple of lectures, so you'll have more time to get exposed to these concepts. Java supports four hashed
    collections implementation, which we'll be looking at coming up. These are the HashSet, LinkedHashSet, the HashMap,
    and LinkedHashMap. In addition there's one legacy implementation, the HashTable, which I won't be covering, since
    there are more efficient implementations which replace this legacy class.
*/
//End-Part-15

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + face.hashCode();
        return result;
    }
}
