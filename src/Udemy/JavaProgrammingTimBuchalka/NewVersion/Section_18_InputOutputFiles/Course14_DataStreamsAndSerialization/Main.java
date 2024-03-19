package CourseCodes.NewSections.Section_18_InputOutputFiles.Course14_DataStreamsAndSerialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//Part-1
/**
        In the last lecture, I reviewed the RandomAccessFile, to write some information to a binary data file. I used
 writeInt, writeLong, and writeUTF to write integers, longs, and Strings respectively. This is one way to write binary
 data to an output file. In this lecture I'll be exploring another way. I'll first create a private static void method,
 called writeData.
**/
//End-Part-1

class Player implements Serializable {
    private String name;
    private int topScore;
    private List<String> collectedWeapons = new ArrayList<>();

    public Player(String name, int topScore, List<String> collectedWeapons) {
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
        //return STR."Player{name='\{name}\{'\''}, topScore=\{topScore}, collectedWeapons=\{collectedWeapons}\{'}'}";  // This is same with the code above
    }
}

//Part-10
/**
        And it'll have three fields, name, a topScore, and a list of weapons, which this user found while playing. I'll
 generate the constructor for this, so alt insert, and select constructor, and I'll pick all the fields. I'll also generate
 a toString method, using the same mechanism. Here, I just want to make sure, I use the String concat template, and have
 all 3 fields selected. In the main method,
 **/
//End-Part-10

public class Main {

    public static void main(String[] args) {

        String dataPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course14_DataStreamsAndSerialization/";
        Path dataFile = Path.of( dataPath + "data.dat");
        writeData(dataFile);


//Part-4
/**
        I'll execute this method, but first I'll create a Path variable. I'll create a file called data.dat. I'll pass
 this to my writeData method. I'll run this code,

                     writeInt writes 4

 And I'll see, writeInt writes 4, which means this method, wrote 4 bytes out to the file. Getting back to my writeData
 method,
 **/
//End-Part-4

        readData(dataFile);

//Part-9
/**
        I'll pass dataFile to my readData method. If I run this,

                 myInt = 17
                 myLong = 100000000000000
                 myBoolean = true
                 myChar = Z
                 myFloat = 77.7
                 myDouble = 98.6
                 myString = Hello World

 I see that all the data was read in accurately, and the values all match the values I output to this data file. Now, I
 could use this kind of stream, to write an object to a file. An object after all, eventually comes down to primitive
 types or strings, so I could write methods like these, to write out my fields. You can imagine that wouldn't be very
 pretty, especially if you had 20 fields, and complex types or collections. Fortunately, we don't have to do that. Java
 provides us with another pair of classes to make this a bit easier. There's an Object Output Stream to write Objects
 out, and an Object Input Stream, to read that data directly back into an object. The process of translating a data
 structure or object, into a format that can be stored on a file, is called serialization. Only instances of Serializable
 classes can be serialized, meaning the class must implement the Serializable interface. This interface doesn't have any
 methods, it's just used to mark the class as serializable. All subtypes of a serializable class are themselves also
 serializable. The default serialization mechanism, writes the class of the object, the class signature, and the values
 of non-static fields. These elements are used to restore the object, and it's state, during the read operation. This
 process is called reconstituting the data, or deserialization. To demonstrate a simple example, I'll create a second
 class in my Main class's java file. I'll call this class Player,
 **/
//End-Part-9

        Player tim = new Player("Tim", 100_000_010, List.of("knife", "machete", "pistol"));
        System.out.println(tim);

//Part-11
/**
        I'll create a new Player, and I'll call this player Tim. So new Player, name is Tim, the top score will be 100
 thousand and 10. My weapons list will have a knife, a machete, and pistol. I'll print the tim instance out here. Next,
 I need to write the code, that'll write this object, to an output stream. I'll call the first method writeObject,
 **/
//End-Part-11

        Path timFile = Path.of(dataPath + "tim.dat");
        writeObject(timFile, tim);
        Player reconstitutedTim = readObject(timFile);
        System.out.println(reconstitutedTim);

//Part-14
/**
        I'll set up a path instance, my file will be tim.dat. I'll write my player, Tim here. I'll read the player out
 of the generated file, with readObject, and pass that back to a variable I'm calling, reconstituted Tim. I'll print this
 deserialized object out. Running this code,

         Player{name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}
         Player{name='Tim', topScore=100000010, collectedWeapons=[knife, machete, pistol]}

 I can see the two lines of output, are exactly the same. The first was from the tim player I initially created, with the
 constructor code. The second statement comes from the reconstituted Tim, and the output is identical. This means I was
 able to read a serialized player object back into memory, from a flat file, with a single readObject method call, so
 that's seems like a pretty neat feature. There's a lot more complexity to this serialization process, than this code
 suggests. That's what I want to review with you, in the next lecture.
 **/
//End-Part-14
    }

//Part-2
/**
        This method will take a Path as an argument. I'll next create an instance of another IO class, and this one's
 called DataOutputStream. A DataOutputStream lets an application write primitive Java data types to an output stream, in
 a portable way. An application can then use a DataInputStream to read the data back in. I'll instantiate this new DataOutputStream,
 inside a try with resources block. My local variable name will be dataStream. I'll instantiate a new Data Output Stream.
 That will wrap a buffered output stream. Which in turn will wrap a file output stream, because I'll be writing to a file.
 I really didn't need to wrap this in a buffered output stream, because my data will be just a few bytes, but I wanted
 to show you the standard way of doing this. Most files will benefit from being wrapped in a BufferedOutputStream. I can
 use a helper method on the Path interface, named toFile, to get a file instance from my path, which I can pass to this
 IO class. Before I do anything else, there's a problem with that new FileOutputStream call. Hovering over that, I see
 that I need to add a FileNotFoundException clause, so I'll add that. Actually, I don't need both, so I can remove the
 FileNotFoundException, since it's a child of IO Exception.
**/
//End-Part-2

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

//Part-3
/**
        I'll create a series of local variables, one for each primitive data type, as well as a String. I'll create my Int,
 and set that to 17. Next, a long, myLong with a value of 100 billion. I'll set up a boolean equal to true, a char set
 to the letter z, a float with the value 77.7, and a double, with 98.6 there. Finally, I'll have a string set to Hello
 World. Next, I'll create a long variable, position, which will help track how many bytes each operation is writing. I'll
 call dataStream.writeInt, passing my first local variable, myInt. I'll print out that the method writeInt writes, and
 then I'll calculate how many bytes get written. I can do this by taking the current size of the data stream, and subtracting
 the value in position. I'll then update position, to the stream's current size. Jumping back up to the main method,
 **/
//End-Part-3

            dataStream.writeLong(myLong);
            System.out.println("writeLong writes " + (dataStream.size() - position));
            position = dataStream.size();

//Part-5
/**
        I'll copy those last three statements. I'll be pasting this multiple times and changing the statements slightly,
 to print each of my local variables out. This will be a little tedious to set up, but bear with me for a few minutes.
 For this copied set's first statement, I'll change writeInt to WriteLong, and pass it the myLong variable. In the println
 statement, I'll change writeInt to writeLong. I'll paste another copy.
 **/
//End-Part-5

            dataStream.writeBoolean(myBoolean);
            System.out.println("writeBoolean writes " + (dataStream.size() - position));
            position = dataStream.size();

//Part-6
/**
        This time, I'll change writeInt to writeBoolean, and myInt to myBoolean, on the first line. On the next line, I'll
 change writeInt to writeBoolean in the output. I'll repeat these steps for the next four statements, so that I'm printing
 each local variable, using the appropriate write method for its type.
 **/
//End-Part-6

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

//Part-7
/**
        Ok, let's run this again now.

                 writeInt writes 4
                 writeLong writes 8
                 writeBoolean writes 1
                 writeChar writes 2
                 writeFloat writes 4
                 writeDouble writes 8
                 writeUTF writes 13

 Here, you can see that writeLong wrote 8 bytes, the boolean method wrote 1, char writes 2, writeFloat writes 4, and write
 double outputs 8 bytes of data. The writeUTF wrote 13. Now, if you do the math, you'll realize that Hello World has only
 11 characters. You'll remember that I said in the last lecture, the writeUTF uses the first 2 bytes to record the number
 of characters written, so that's how we get 13 here. Now that I've got data in a binary file, I can read it, by using a
 DataInputStream. I'll again create a method for this, private static void, named readData,
 **/
//End-Part-7
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

//Part-8
/**
        And that too will take a path for the dataFile. I'll start setting this up in a try with resources block. I'll
 declare a DataInputStream, called dataStream, and assign that to a new DataInputStream. In this case, instead of passing
 in a new instance of a BufferedOutputStream, I'll pass what I get back from the method, Files.newInputStream. This is a
 special NIO 2 input stream. I'll include the catch block. Now, in the try block, I'll be reading the data in, from my
 file. For a DataInputStream, this means I have to know the data types I'll be reading in, and the same order the types
 were output in the file. I'll print out each data type, and the local variable name I used when I output it, so I'll
 output myInt equals, plus what I get, from calling readInt on the dataStream. Next, myLong, =, dataStream.readLong.
 myBoolean, gets its value from read boolean. my char and read char are next, then my float, and read Float. my double
 will get its value from the readDouble method, and finally, I'll call readUTF, which should be hello world, if this is
 all set up right. I'll call this method, from the main method.
**/
//End-Part-8

    private static void writeObject(Path dataFile, Player player) {
        try (ObjectOutputStream objStream = new ObjectOutputStream(Files.newOutputStream(dataFile))
        ) {
            objStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Part-12
/**
        it's private static void, and takes both a path argument, and a player. I'll set up a try with resources block,
 and create an Object Output Stream variable. I can set that to a new Object OutputStream, And pass that what I get from
 calling Files.newOutputStream, with my dataFile path. Writing an object is easy, I just call writeObject on the stream,
 and pass it the object. And of course, I have to deal with the IO Exception. And that's it, that's all I have to do, for
 the write. That's a lot easier than calling a bunch of different write methods, by each type. Now, I'll write the readObject
 method,
 **/
//End-Part-12
    }

    private static Player readObject(Path dataFile) {
        try (ObjectInputStream objStream = new ObjectInputStream(Files.newInputStream(dataFile))) {
            return (Player) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//Part-13
/**
        So again private static. This time I want to return a Player, and for this one, I just need to pass the path.
 This set up is very similar to the Object Output Stream, except its an Object Input Stream. And I can get the player
 stored in the data file, by calling readObject, but I have to cast that, to a Player. I'll return this from the method.
 This code won't compile without a catch block, so I'll add that, for the first error, that I have on newInputStream.
 I still have an error on the read Object method, so again, I'll add the clause. This time I'm prompted to add
 ClassNotFoundException to catch with IOException, so I'll pick that. Hopefully you'll remember this syntax, which lets
 me catch either of these exceptions, using a single catch clause, and exception variable. Next, I need to call these
 methods in my main method.
 **/
//End-Part-13
}
