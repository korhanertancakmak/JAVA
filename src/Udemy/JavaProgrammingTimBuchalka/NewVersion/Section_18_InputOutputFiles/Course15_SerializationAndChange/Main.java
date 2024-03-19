package CourseCodes.NewSections.Section_18_InputOutputFiles.Course15_SerializationAndChange;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

//Part-1
/**
        In the last lecture, I started with a pretty simple example, using a Player class, and an instance of that player,
 to first write the player object to a data file, and then to read that object back in to memory. How that object is
 structured in the output data, is part of Java's internals, so I don't have to worry about implementing a specific format,
 for writing or reading. I can have my class implement the Serializable interface, and then use readObject and writeObject
 with Object streams, and it all works. Well, that's only part of the truth. There are some things that are important to
 understand, and consider, if you're going to use Java's serialization. I want to look again at the Player class. It has
 three fields, a name, a top score, and a list of weapons. When we serialized an instance of this class, all three fields
 were serialized, including the ArrayList of weapons. That's because the ArrayList class itself implements serializable.
 Now let's say, I'm a developer, making improvements to the game, and I've provided a lot more ways for my player to
 score points. This means I need to change my top score to be a long to accommodate much higher scores, so I'll make that
 change. I'll change the field's type to long from int. I'll also change my constructor's second parameter, to be a long,
 instead of an int. Now, imagine that tim's data was stored on file previously, which we actually did do, in the last lecture.
 Now this player's coming back to play again, but we've since updated our application in the interim. I'll set this scenario
 up in my main method.
**/
//End-Part-1

class Player implements Serializable {

    private final static long serialVersionUID = 1L;
    private final static int version = 2;
    private String name;
    private long topScore;
    private long bigScore;
    private final transient long accountId;
    private List<String> collectedWeapons = new LinkedList<>();

    public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
        this.accountId = accountId;
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + accountId + ", " +
                "name='" + name + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {

        /*stream.defaultReadObject();
        bigScore = (bigScore == 0) ? 1_000_000_000L : bigScore;*/

        var serializedVer = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
        name = stream.readUTF();
        //topScore = stream.readInt();
        topScore = (serializedVer == 1) ? stream.readInt() : stream.readLong();
    }

//Part-3
/**
        This is a special method, whose signature doesn't change. At the time of this lecture, I'm not aware of any generation
 tools in IntelliJ, to help us with these methods, so I'll just type this out. When you do create this method, you'll
 often want to call, the default serialization process first. That's done by invoking the default ReadObject method, on
 the stream argument. One thing we can do is make changes to the object that got de-serialized, so here, I'm going to make
 some compensation, for losing the players top score, by defaulting all players caught in this situation, to a default
 score of one billion. Before I run this code, let's see what IntelliJ's warning is for this method. I'll hover over that,
 and I can see IntelliJ is suggesting I annotate this method, so I'll select that. This annotation informs all interested
 parties, that this method will be used by the serialization process. Ok, I'll run this now.

             Player{name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{name='Tim', topScore=1000000000, collectedWeapons=[knife, machete, pistol]}

 You can see the reconstituted player has a score of a billion. This might be one way of dealing with any players, caught
 in this upgrade problem. Before I talk about more options, I next want to talk to you about the transient modifier. I
 mentioned it before about compatible and incompatible changes, but I haven't really said what this modifier is. The
 transient modifier is used to indicate that a field should not be serialized. This can be useful for variables that contain
 sensitive information, or just variables that don't need to be persisted, for other reasons.

        Let's say I've got an accountId for my player's, to manage billing and so on. I'll include this as a private final
 field on my Player, and I'll add the transient modifier here as well. I'll include this field, in my constructor, as the
 first parameter. I'll also assign the field to that method argument. Next, I'll add it to my two String method. I've got
 an error in my main method, where I'm constructing the first player instance. I need to include an account id in this
 constructor, so I'll pass 555, as the account id, in the first argument. I'll run this code.

             Player{id=555, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{id=0, name='Tim', topScore=1000000000, collectedWeapons=[knife, machete, pistol]}

 Notice that I didn't get any errors, so adding a transient field doesn't cause any incompatibility between versions. This
 wasn't a great test though, of the transient modifier, because my tim dot dat file, never had the account id, in it at
 all. I'll serialize a second player with this new class structure, and then immediately de-serialize that player.
**/
//End-Part-3

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {

        System.out.println("--> Customized Writing");
        stream.writeInt(version);
        stream.writeObject(collectedWeapons);
        stream.writeUTF(name);
        stream.writeLong(topScore);
    }

//Part-5
/**
        First, I'm going to create a new private static field on this class and call it version. Like the read Object
 method, I can get a hook into how Java writes data, with the write Object method. After the read Object method, I'll
 add the code for this next method. This method is private void, named writeObject, and has one parameter, an Object
 Output Stream. And it throws an IO exception. I'll print something to the console, so that I know when this code is
 executed. I'm going to write out my static version number. Normally a static field wouldn't get serialized, but since
 I'm customizing this code, I can do whatever I want. I am going to store my own version number here. I can write the
 fields in any order I want, so I'll write the collected Weapons first, then the name. Lastly, I'll write out the topScore,
 as an Int. Again, IntelliJ gives me a warning on this method, so I'll select to annotate that. Now, I need to redo the
 readObject method. When you write a customized writeObject method, as I just did, you can't simply delegate to the
 defaultReadObject method, which is what I'm doing right now in this method. If I don't change this method, my writes
 will be out of sync with my reads, and the code will fail. I'll comment out the two statements that are there. I want
 to read everything in, mirroring the way I wrote it out. I'll use readInt to get the version. I'll set this to a local
 variable. I'll do the other reads, so the object first to get the list of weapons, and I need to cast that to a List of
 String. I'll get the player name with readUTF. And I'll get the top score, using readInt. IntelliJ is broadcasting a
 warning about this cast, but since I know this really is a list of string, and this is what I want, I can add another
 annotation, to suppress this warning. The annotation is SuppressWarnings, and I can pass that the string literal unchecked.
 I'll cover annotations a bit later, but this will get rid of that warning. I'll change my constructor, so that topScore
 is once again, an int. I'll set top score inside the constructor, changing this.bigScore to this.topScore. I want to
 print top score out, in my two String method, so again I'll change bigScore to topScore here. Next, getting back to my
 main method, I'll uncomment the line that writes out the first tim object. I'll run this.

             Player{id=555, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=0, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=556, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}
             Player{id=0, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}

 I can see my Customized Writing method was called during the two de-serialization processes. I can also see, they both
 have their topScores reconstituted correctly. Now notice what happens, if I make topScore transient. I'll scroll up to
 the Player class, and add the "transient" keyword, after the private access modifier, before the int type. I'll run my
 code again.

             Player{id=555, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=0, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=556, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}
             Player{id=0, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}

 Even though top score is transient, it didn't matter, because my methods and my rules, now override Java's rules. I'll
 remove the transient modifier, and now, I'm going to make this a long. I'll also update my static version number for this
 change, to a 2. Next, I'll change my writeObject code, to write a long, not an int, when it's writing out the top score.
 Before I re-run the code, I'll make sure to comment out the first writeObject method call again, in my main method, the
 one that writes out tim. I want to keep the version that was written when my topScore was declared an int. And again I'll
 run this.

             Player{id=555, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{id=0, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=556, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}
             Player{id=0, name='Joe', topScore=0, collectedWeapons=[crossbow, rifle, pistol]}

 It runs successfully, even though I changed my field from an int to a long. My serial UID didn't change, it's always
 been 1L. Because I'm reading and writing using my custom code, I didn't get an exception reading the data in. But, there's
 a problem. Joe's score is 0. The reason it's 0 is because, we're reading his score in as an int, which worked fine for
 Tim, who's code was version 1. But it's not so great for version 2, because the score's data, is stored in 8 bytes.
 When I read the first four, using readInt, I just get zeros. I'll need to change my read method, but I don't want to
 change it for version 1. This is where my version number comes in handy. I can change my readObject code, to do something
 different, based on the version I read in, from the serialized object. I can fix this problem with a ternary operator,
 testing the value of the local variable, serialized Ver. If it's 1, I'll read an Int, if it's 2, I'll read a long. I'll
 run this again.

             Player{id=555, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{id=0, name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             --> Customized Writing
             Player{id=556, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}
             Player{id=0, name='Joe', topScore=75, collectedWeapons=[crossbow, rifle, pistol]}

 Happily, at last, my code works for both versions of the serialized data. This was a lot more work certainly, and required
 me to implement tracking my own version number. Why didn't I just change the serialVersionUID? Let's do that. I'll change
 it to 2L. Running my code,

             Exception in thread "main" java.lang.RuntimeException: java.io.InvalidClassException:
                                        .Player; local class incompatible: stream classdesc serialVersionUID = 1, local class serialVersionUID = 2
             at .Main.readObject(Main.java:452)
             at .Main.main(Main.java:216)
             Caused by: java.io.InvalidClassException:
                                        .Player; local class incompatible: stream classdesc serialVersionUID = 1, local class serialVersionUID = 2


 I get the exception, with the message, local class incompatible, because the serial Version UID's are out of sync. I'll
 revert that last change. Now, there are use cases, where you might want to invalidate all stored session data like this.
 But if you hope to recover the data, in the event of changes, writing your own readObject and writeObject methods is one
 option, although it doesn't prevent all Invalid Class Exceptions.  In fact, the only reason this example worked, was
 because on the version 1 class of Player, the topScore field was originally transient. If I had not serialized the tim
 instance, with the topScore field transient, this code would have failed. Even though I'm manually writing this field
 out, Java's serialization checks still get exercised before the process runs, and this change, without the transient
 modifier, would still be an incompatible change. The serialization would have failed. Serialization, out of the box,
 is a powerful tool, but I hope this exercise, showed you some of its complexity, and how important it is to use it, in
 a planned and well thought out fashion. We've covered a lot of ground in the section, and this lecture ends this section
 on Java's IO. In the next section, I'll be starting a brand new topic, talking about concurrency and threads.
 **/
//End-Part-5
}

public class Main {

    public static void main(String[] args) {

        Player tim = new Player(555, "Tim", 100_000_010, List.of("knife", "machete", "pistol"));
        System.out.println(tim);

        String dataPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course15_SerializationAndChange/";
        Path timFile = Path.of(dataPath + "tim.dat");
        //writeObject(timFile, tim);
        Player reconstitutedTim = readObject(timFile);
        System.out.println(reconstitutedTim);

//Part-2
/**
        This code would most likely be called when Tim exits the game session, or at intervals during the play. It wouldn't
 be back to back with a readObject call, like I had it here. If I run this code at this point, I get a runtime exception,
 InvalidClassException, local class incompatible. Notice here that the message is saying it found a serial Version UID,
 and it printed a great long number there, but the local class has a different value. What's a serial Version UID, and
 why are they so totally different here?

        The serialVersionUID field is a runtime field, that the compiler will implicitly create, if it's not explicitly
 declared, for classes that are serializable. It's based on class details such as the number of fields, their types, and
 declarations. Changing a field, like we did in this example, will generate a different id, which is what happened. When
 we read an object from a stream, the runtime checks the stored serialVersionUID. This is stored with the object written
 to our dat file in this example, and compared to the one contained within the compiled class file. If they don't match,
 then there's a compatibility problem and the runtime will throw this invalid class exception. In addition, it's possible
 that different compilers will generate this implied field differently. If you got a new version of Java for example,
 between writing the file and reading it, it's possible you might not be able to deserialize your data, because of a
 mismatch in this implicitly generated id. To ensure this doesn't happen, it is strongly recommended that you include
 this as a private static field, as shown below.

             private final static long serialVersionUID = 1L;

 I'll add this field, to my Player class, and see if it fixes my problem. I'll make the value 1L, because it needs to be
 a long. You can think of it as a sort of a version number for the class. If I run my code, I get the same error, only
 this time, my local class has a serial version UID of 1. The IDs are still different though, so I can't deserialize this
 data. Let's revert the top score back to an int. I'll change my field back to an int, and then my constructor parameter
 type. I'm going to also uncomment the line that writes my object, and start this process over. I'll re-run that,

             Player{name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}

 and everything looks good. Both the write Object and read Object methods worked. I'll again comment out the writeObject
 call in my main method. I'll again, change topScore to a long, in the field type, and the parameter type of the constructor.
 I'll re-run my code. Unfortunately, this doesn't work either. I do get a different exception message though, which tells
 me more specifically, what the problem really is. I've got incompatible types for the field, topScore. The object I
 serialized, with an int for the topScore, can't be reconstructed, using a long instead. Now, maybe you thought this
 change was harmless. After all, an int value should fit into a long value, and your players' scores are all ints. But
 actually, you've broken each user's ability, to restore their session data from a serialized file. This is a minor change
 in code, with some ugly repercussions. It's very important to understand the rules you need to play by, if you're going
 to rely on Java's serialization of objects.

        First, it's important to understand, what constitutes an Incompatible Change? Some of the following items shown
 below, will invalidate your class, and prevent you from deserializing.

    * Changing the declared type of a primitive field, is an incompatible change. You've just seen an example of this.
      When I changed my top score, from an int to a long. This is one change that's flagged as an incompatible change.
      This is because of the way primitives are written, specifically taking up a certain width, and if that changes,
      reading the data in will either read too many, or too few bytes.
    * Deleting fields, is another incompatible change.
    * Changing a non-static field to static, or a non-transient field to transient are also incompatible changes.

 When relying on default serialization, these changes are equivalent to deleting a field from the class. I'll be explaining
 what transient means shortly. There are other more complicated changes, such as moving a class within its hierarchy,
 changing the writeObject and readObject methods after you've used them to serialize previously, and a few others.

    The good news is that not all changes you make to your class are going to invalidate the serialization process. Some
 changes are compatible changes and won't cause an InvalidClassException on de-serialization, of an earlier version of
 the class. These include

    * Adding fields. When the class being reconstituted has a field that isn't on the stream, that field in the object
      gets initialized to the default value for its type.
    * Adding writeObject and readObject methods is another compatible change. I'll be showing you an example of these two
      methods coming up.
    * Changing the access to a field. The access modifiers public, package, protected, and private have no effect on the
      ability of serialization to assign values to the fields.
    * Changing a field from static to non static, or transient to non-transient are also considered compatible changes.
      These changes are somewhat equivalent to adding a field to the class. Earlier serialized classes, when deserialized,
      can simply ignore the new field.

        Once again, I'll revert my topScore field from a long to an int. I'll leave the parameter in my constructor the way it is.
 This time I'll add a field called bigScore, a long. I'll change my constructor to set bigScore, to the topScore argument.
 I'll run this.

 Player{name='Tim', bigScore=100000010, collectedWeapons=[knife, machete, pistol]}
 Player{name='Tim', bigScore=0, collectedWeapons=[knife, machete, pistol]}

 The good news is that this code ran, without any exceptions, but look at the output. The reconstituted Tim has a bigScore
 of zero. There's a lot going on here truthfully, so let's unpack this. First, adding a field "private long bigScore;"
 doesn't break the de-serialization code. That's a bit of good news. Maybe you thought, because you had a constructor,
 that fit the bill for either a long or an int, such that the de-serialization code, would call this constructor.

                 public Player(String name, long topScore, List<String> collectedWeapons) {
                     this.name = name;
                     this.bigScore = topScore;
                     this.collectedWeapons = collectedWeapons;
                 }

 Actually, the de-serialization code doesn't call the constructor at all, any constructor, on the class that's been serialized.
 This means, if you've got code in the constructor to pass your topScore to the new field, bigScore, this isn't going to
 happen, when the object is reconstituted. Your new field won't break the deserialization process, but it won't get populated
 by anything but the default value, which in this case, is 0.

        Next, let's make another change. This time, I'm going to change my list from an ArrayList to a LinkedList.

                         private List<String> collectedWeapons = new ArrayList<>();
                                                    to
                         private List<String> collectedWeapons = new LinkedList<>();

 I'll run this code again.

                 Player{name='Tim', bigScore=100000010, collectedWeapons=[knife, machete, pistol]}
                 Player{name='Tim', bigScore=0, collectedWeapons=[knife, machete, pistol]}

 This code runs without any exceptions. I can also see from the second statement, that my collectedWeapons list, has all
 the values that were originally written out. Does this result kind of surprise you? I serialized a field, an object using
 an ArrayList, but successfully reconstituted that same object, into a linked list collection instead. The reconstructed
 object seemed to take that change totally in stride. Even though I changed my List type, it was able to load up my collectedWeapons
 from the file, just the same. If I show you that incompatible changes chart again,

     * Changing the declared type of a "primitive" field
     * Deleting fields
     * Changing a non-static field to static or a non-transient field to transient

 Notice that the first bullet point, says the incompatible change is when you change the declared type of a primitive field.
 What happens when it's not a primitive type, is sort of complicated, and I won't get into it too deeply here. The serialization
 process, for an object field, includes information about the object's type, and object's super type. For the ArrayList
 and LinkedList, they share a super type, so the deserialization went smoothly here.

        I hope what you're starting to understand is, that serializing objects, and relying on Java's internals, may seem
 convenient and easy. Let me caution you against leaning on it too heavily, without a thorough understanding of what
 constitutes a problem. The default out of the box serialization, takes control of the process, out of your hands. This
 can lead to problems, if your class structure is going to be changing. Next, I'll talk about how to implement certain
 customizations, which are hooks into the serialization process, that can let you control some, or all, of the process
 to manage some of these problems we've seen.

        Up to now, we explored compatible and incompatible changes to a serializable class. Incompatible changes to a class,
 means you're going to break the reconstitution process, for previously serialized data. There are use cases where this
 is the desired behavior, but there are other circumstances, like the game player's session data I've set up.

        There are two methods, called readObject and writeObject, that are hooks into customizations. These aren't methods
 we override though. If we implement them, on our serializable class, much like the serializable UID, the serialization
 process will use our explicit versions. We have to stick to the signatures of these methods, as they're identified, in
 the serialization documentation. I'll start by demonstrating the read Object method, creating this on my serializable
 Player class.
 **/
//End-Part-2

        Player joe = new Player(556, "Joe", 75, List.of("crossbow", "rifle", "pistol"));
        Path joeFile = Path.of(dataPath + "joe.dat");
        writeObject(joeFile, joe);
        Player reconstitutedJoe = readObject(joeFile);
        System.out.println(joe);
        System.out.println(reconstitutedJoe);

//Part-4
/**
        I'll call this player Joe, account id 556, name Joe, top score is 75 because he's only played once. His weapons
 can be a crossbow, a rifle and a pistol. I'll serialize his data to a joe.dat file. I'll write the joe object to that
 file. I'll immediately deserialize the joe instance, assigning that to the reconstitutedJoe variable. I'll print the
 original object. Finally, I'll print the deserialized object. Running this code

             Player{id=555, name='Tim', bigScore=100000010, collectedWeapons=[knife, machete, pistol]}
             Player{id=0, name='Tim', bigScore=1000000000, collectedWeapons=[knife, machete, pistol]}
             Player{id=556, name='Joe', bigScore=75, collectedWeapons=[crossbow, rifle, pistol]}
             Player{id=0, name='Joe', bigScore=75, collectedWeapons=[crossbow, rifle, pistol]}

 You can still see the id is zero for Joe, because the account id wasn't written out for this player, and that's because
 I declared it transient. Now, let's see if we can leverage Java's serialization, but wrap some of our version control
 around parts of it.
 **/
//End-Part-4
    }

    private static void writeData(Path dataFile) {

        try (DataOutputStream dataStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile.toFile())))) {
            int myInt = 17;
            long myLong = 100_000_000_000_000L;
            boolean myBoolean = true;
            char myChar = 'Z';
            float myFloat = 77.7f;
            double myDouble = 98.6;
            String myString = "Hello World";

            long position = 0;
            dataStream.writeInt(myInt);
            System.out.println("writeInt writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeLong(myLong);
            System.out.println("writeLong writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeBoolean(myBoolean);
            System.out.println("writeBoolean writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeChar(myChar);
            System.out.println("writeChar writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeFloat(myFloat);
            System.out.println("writeFloat writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeDouble(myDouble);
            System.out.println("writeDouble writes " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeUTF(myString);
            System.out.println("writeUTF writes " + (dataStream.size() - position));
            position = dataStream.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readData(Path dataFile) {

        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(dataFile))) {
            System.out.println("myInt = " + dataStream.readInt());
            System.out.println("myLong = " + dataStream.readLong());
            System.out.println("myBoolean = " + dataStream.readBoolean());
            System.out.println("myChar = " + dataStream.readChar());
            System.out.println("myFloat = " + dataStream.readFloat());
            System.out.println("myDouble = " + dataStream.readDouble());
            System.out.println("myString = " + dataStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeObject(Path dataFile, Player player) {
        try (ObjectOutputStream objStream = new ObjectOutputStream(Files.newOutputStream(dataFile))) {
            objStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Player readObject(Path dataFile) {
        try (ObjectInputStream objStream = new ObjectInputStream(Files.newInputStream(dataFile))) {
            return (Player) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
