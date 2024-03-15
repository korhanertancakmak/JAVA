# Input/Output in Java

## I/O Data Streams

In this section, we will learn about Input / Output streams in Java. 
You can think of data input/output operations in Java as data flowing from one place to another 
(in the form of byte arrays).

An input stream (Input Stream) is used to read data and an output stream (Output Stream) is used to write data.

```java  
class HelloWorld { 
    public static void main(String[] args) { 
        System.out.println("Hello World!");     
    }
}
```

For example, the first "Hello World!" 
In our example, we used "System.out" to output to the screen. 
Here, "System.out" is a type of output stream.

We should say that data streams in Java are divided into two main groups:

* Byte streams
* Character Streams

### Byte Streams

Byte stream is used to read and write single byte (8 bit) data. 
All streams that are not of character type, such as images, graphics, sounds, etc., are byte streams.

All byte stream classes derive from the abstract classes InputStream and OutputStream.

### Character Streams

Character stream is used to read and write a single character of data. 
Character streams, as the name suggests, input/output data of a character type. 
Since it uses Unicode, it adapts to the alphabets of different countries.

All character stream classes derive from the abstract classes Reader and Writer.

## File Class

The File class of the java.io package is used to perform various operations on files and directories.

There is another package called java.nio that can be used to work with files. 
However, in this tutorial, we will focus on the java.io package.

### Files and Directories

A file is a named location that can be used to store related information.
For example, main.java is a Java file that contains information about the Java program.

Directories are a collection of files and subdirectories. A directory within a directory is known as a subdirectory.
To create a File object, we first need to import the java.io.File package.

```java  
File file = new File(String pathName);
```

### File Methods

| Process       | Method          | Package            |
|---------------|-----------------|--------------------|
| File Creating | createNewFile() | java.io.File       |
| File Removing | delete()        | java.io.File       |
| File Reading  | read()          | java.io.FileReader |
| File Writing  | write()         | java.io.FileWriter |


### Creating File

We can use the createNewFile() method to create a new file. 
The method returns true if a new file is created, false if the file already exists in the specified location.

```java  
import java.io.File;

public class PatikaDev {
    public static void main(String[] args) {
        File file = new File("patika.txt");
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("New File created.");
            } else {
                System.out.println("The File is already exist.");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

### Removing File

We can use the delete() method of the File class to delete the specified file or directory.

Note: We can only delete empty directories.

```java  
import java.io.File;

public class PatikaDev {
    public static void main(String[] args) {
        File file = new File("patika.txt");
        boolean value = file.delete();
        if (value) {
            System.out.println("The File is deleted.");
        } else {
            System.out.println("The File is not deleted.");
        }

    }
}   
```

### File Indexing

Java File class provides mkdir() method to create a new directory. Method backwards

* true if a new directory is created,
* Returns false if the directory already exists.

```java  
import java.io.File;
public class PatikaDev {
    public static void main(String[] args) {
        File file = new File("patika/test");
        boolean value = file.mkdir();
        if(value) {
            System.out.println("The new directory is created.");
        }
        else {
            System.out.println("The directory already exists.");
        }
    }
}

```

### Listing Elements in the Index

```java  
import java.io.File;
public class PatikaDev {
    public static void main(String[] args) {
        File file = new File("test");
        String[] fileList = file.list();
        for(String str : fileList) {
            System.out.println(str);
        }
    }
}

```

## FileInputStream Class

### InputStream Class

The InputStream class is an abstract class that represents a byte stream and comes from the Java.io package.

Since InputStream is an abstract class, it is not useful on its own, so subclasses of InputStream are used 
to read data.

InputStream subclasses: FileInputStream, ByteArrayInputStream, ObjectInputStream

### FileInputStream

The FileInputStream class of the java.io package is used to read data (in bytes) from files.

InputStream inherits the Abstract class.

### Creating a FileInputStream

To create a file input stream, we must first import the java.io.FileInputStream package. 
After importing the package, here's how we can create a file input stream in Java.

### Using File Path

```java  
FileInputStream input = new FileInputStream(stringPath); 

```

### Using object

```java  
FileInputStream input = new FileInputStream(File fileObject);
```

### FileInputStream Methods

Methods of the FileInputStream class:

* ***read()*** : Reads single byte data from the file.
* ***read(byte[] array)*** : Reads data from the file in bytes and stores it in the specified array

```java  
import java.io.FileInputStream;
public class PatikaDev { 
    public static void main(String[] args) { 
        try { 
            FileInputStream input = new FileInputStream("input.txt"); 
            System.out.println("Datas in the file: "); 
            // first it reads byte
            int i = input.read(); 
            while (i != -1) { 
                // Byte to char 
                System.out.print((char) i); 
                // it reads next byte from the file                 
                i = input.read();
            }
            input.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

***available();***

```java  
package stream; 
import java.io.FileInputStream; 
public class PatikaDev { 
    public static void main(String[] args) { 
        try { 
            FileInputStream input = new FileInputStream("input.txt"); 
            // Returns the number of bytes available
            System.out.println("The number of bytes available : " + input.available()); 
            // Reads 3 bytes of data from file          
            input.read();
            input.read();
            input.read();
            // Returns the number of bytes available
            System.out.println("The number of bytes available : " + input.available()); 
            input.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

***skip();***

We can use skip() method to discard and skip the specified number of bytes. For example,

```java  
import java.io.FileInputStream; 
public class PatikaDev { 
    public static void main(String[] args) { 
        try { 
            FileInputStream input = new FileInputStream("input.txt"); 
            // 5 byte will be skipped.
            input.skip(5); 
            System.out.println("5 byte data is skipped : "); 
            int i = input.read(); 
            while (i != -1) { 
                System.out.print((char) i);                 
                i = input.read();
            }
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

## FileOutputStream Class

The FileOutputStream class of the java.io package can be used to write data (in bytes) to files.
Extends the OutputStream abstract class.

### Creating a FileOutputStream

To create a file output stream, we must first import the java.io.FileOutputStream package.

### Using File Path

```java  
// Including the boolean parameter
FileOutputStream output = new FileOutputStream(String path, boolean value);

// Not including the boolean parameter
FileOutputStream output = new FileOutputStream(String path);
```

An optional boolean parameter is sent. If this parameter is set to True, new data will be appended to the end of existing data in the file. Otherwise, the new data will overwrite the existing data in the file.

### Using a File Object

```java  
FileOutputStream output = new FileOutputStream(File fileObject);
```

Example:

```java  
import java.io.FileOutputStream; 
public class PatikaDev {
    public static void main(String[] args) { 
        String data = "I'm learning Java with Patika !!"; 
        try {
            FileOutputStream output = new FileOutputStream("output.txt"); 
            byte[] array = data.getBytes();       
            output.write(array);
            output.close();
        } 
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

## ByteArrayInputStream and ByteArrayOutputStream Class

### ByteArrayInputStream

The ByteArrayInputStream class of the java.io package can be used to read an array of input data (in bytes).

In ByteArrayInputStream, the input stream is created using a byte array. 
It contains an internal array to store the data of this byte array.

To create a byte array input stream, we must first import the java.io.ByteArrayInputStream package.

```java  
// Creates a ByteArrayInputStream that reads the entire array
ByteArrayInputStream input = new ByteArrayInputStream(byte[] arr); 
// Creates a ByteArrayInputStream that reads a part of the array
ByteArrayInputStream input = new ByteArrayInputStream(byte[] arr, int start, int length);
```

Here the input stream reads the number of bytes equal to the length from the array, 
starting from the starting position.

```java  
import java.io.ByteArrayInputStream; 
public class PatikaDev { 
    public static void main(String[] args) { 
        byte[] array = {1, 2, 3, 4}; 
        try { 
            ByteArrayInputStream input = new ByteArrayInputStream(array); 
            System.out.println("Available bytes at the beginning: " + input.available()); 
            System.out.println("The bytes read from the input stream: "); 
            for (int i = 0; i < array.length; i++) { 
                int data = input.read(); 
                System.out.print(data + ", ");             
            }
            System.out.println("Available bytes at the beginning: " + input.available());             
            input.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

```java  
import java.io.ByteArrayInputStream;

public class PatikaDev {
    public static void main(String[] args) {
        byte[] array = {1, 2, 3, 4};
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(array);
            
            // 2 bytes of data will be skipped
            input.skip(2);

            int data = input.read();
            while (data != -1) {
                System.out.print(data + ", ");
                data = input.read();
            }

            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

### ByteArrayOutputStream

The ByteArrayOutputStream class of the java.io package can be used to write an array of output data (in bytes).
Extends the OutputStream abstract class.

ByteArrayOutputStream has an internal byte array to store data.

```java  
// Creates a ByteArrayOutputStream of default size 
ByteArrayOutputStream out = new ByteArrayOutputStream(); 
// Creating a ByteArrayOutputStream of specified size
ByteArrayOutputStream out = new ByteArrayOutputStream(int size);
```

```java  
import java.io.ByteArrayOutputStream;
public class PatikaDev {
    public static void main(String[] args) {
        String data = "I'm learning with Patika";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = data.getBytes();
            out.write(array);
            String streamData = out.toString();
            System.out.println("Output Stream : " + streamData);
            out.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

```java  
import java.io.ByteArrayOutputStream;
public class PatikaDev {
    public static void main(String[] args) {
        String data = "Java Classes with Patika";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = data.getBytes();
            out.write(array);
            String stringData = out.toString();
            System.out.println("\nData using toString(): " + stringData);
            out.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

## Serialization and ObjectStream Classes

### Serialization

Java is known to be an object-oriented language. 
However, sometimes it may be necessary to use objects outside the JVM. 
However, when we want to use an object that we have used outside, 
inside again, we cannot find out what type of values used in the object are. 
In other words, when we produce an object from any class, write it to a file, 
and read it from the file again, we have the problem of not knowing the type 
information of the values. 
In this case, Java Serialization API comes to our aid.

Thanks to the Java Serialization API, we can store an exact copy of an object outside the Java platform. 
With this mechanism, we can later retrieve the object from the stored location and 
continue using it with the same state and properties. 
This entire system is called Object Serialization.

The only thing that needs to be done to serialize objects is 
to specify at the beginning of the class declaration that the object to be serialized is serializable, 
thanks to the tagging interface.

To serialize objects, the Java platform provides two base classes. 
With these two classes called ObjectInputStream and ObjectOutputStream, 
we can serialize any class that implements the Serializable interface. 
The first of these two classes, ObjectInputStream, implements the ObjectInput interface 
and is used to read the serialized object back from the stream. 
The other class called ObjectOutputStream implements the ObjectOutput interface 
and is used to print any object to the stream.

```java  
import java.io.Serializable;

public class Car implements Serializable {
    
    private String brand;
    private String model;

    Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
```

### ObjectOutputStream

The ObjectOutputStream class of the java.io package can be used to write objects that can be read by ObjectInputStream. 
Extends the OutputStream abstract class.

Essentially, ObjectOutputStream encodes Java objects using the class name and object values 
and creates the corresponding streams. 
This process is known as serialization.

Converted streams can be stored in files and transferred between networks.

Note: The ObjectOutputStream class only writes objects that implement the Serializable interface. 
This is because objects need to be serialized as they are written to the stream.

To create an object output stream, we must first import the java.io.ObjectOutputStream package.

```java  
// Creates a FileOutputStream to which objects in the ObjectOutputStream are written
FileOutputStream fileStream = new FileOutputStream(String file);

// Creates ObjectOutputStream
ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
```

```java  
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerialTest {
    public static void main(String[] args) {
        try {
            Car car = new Car("Hyundai", "Getz");
            FileOutputStream file = new FileOutputStream("output.txt");
            ObjectOutputStream write = new ObjectOutputStream(file);
            write.writeObject(car);
            write.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### ObjectInputStream

The ObjectInputStream class of the java.io package can be used to read objects previously written by ObjectOutputStream.
Extends the InputStream abstract class.

```java  
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
public class SerialTest { 
	public static void main(String[] args) { 
        try { 
            Car car = new Car("Hyundai", "Getz"); 
            FileOutputStream file = new FileOutputStream("output.txt");
            ObjectOutputStream write = new ObjectOutputStream(file);        
            write.writeObject(car);
            // Reading object 
            FileInputStream fileIn = new FileInputStream("output.txt"); 
            ObjectInputStream read = new ObjectInputStream(fileIn); 
            // Reads the objects       
            Car newCar = (Car) read.readObject();
            System.out.println("Car Brand : " + newCar.getBrand()); 
            System.out.println("Car Model: " + newCar.getModel()); 
            read.close();
            write.close();
        } catch (Exception e) {       
            System.out.println(e.getMessage());
        }
    }
}
```

## BufferedInputStream and BufferedOutputStream Classes

### BufferedInputStream

The BufferedInputStream class from the java.io package is used with other input streams to 
read data (in bytes) more efficiently. 
Extends the InputStream abstract class.

BufferedInputStream keeps an internal buffer(memory) of 8192 bytes. 
During the read operation in BufferedInputStream, a chunk of bytes is read from the disk 
and stored in the internal buffer. 
Bytes are also read individually from the internal buffer. 
Thus, the number of communications with the disk is reduced. 
This is why reading bytes using BufferedInputStream is faster.

```java  
// FileInputStream
FileInputStream file = new FileInputStream(String path);

// BufferedInputStream
BufferedInputStream buffer = new BufferInputStream(file);
```

```java  
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class PatikaDev {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("input.txt");
            BufferedInputStream input = new BufferedInputStream(file);
            input.skip(2);
            System.out.println("Available byte : " + input.available());
            int i = input.read();
            while (i != -1) {
                System.out.print((char) i);
                i = input.read();
            }
            System.out.println("Available byte : " + input.available());
            input.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### BufferedOutputStream

The BufferedOutputStream class from the java.io package is used with other output streams to 
write data (in bytes) more efficiently. 
Extends the OutputStream abstract class.

BufferedOutputStream maintains an internal buffer of 8192 bytes. 
During the writing process, bytes are written to the internal buffer instead of to the disk. 
After the buffer is filled or the stream is closed, the entire buffer is written to the disk. 
Thus, the number of communications with the disk is reduced. 
This is why writing bytes using BufferedOutputStream is faster.

```java  
// FileOutputStream 
FileOutputStream file = new FileOutputStream(String path); 
// BufferedOutputStream 
BufferedOutputStream buffer = new BufferOutputStream(file);
```

```java  
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
public class PatikaDev {
    public static void main(String[] args) {
        String data = "Java102 Classes with Patika";
        try {
            // FileOutputStream
            FileOutputStream file = new FileOutputStream("output.txt");
            // BufferedOutputStream
            BufferedOutputStream output = new BufferedOutputStream(file);
            byte[] array = data.getBytes();
            output.write(array);
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

## PrintStream Class

The Java.io package's PrintStream class can be used to write output data in a commonly readable format (text) 
rather than bytes.

Extends the abstract OutputStream class.

Unlike other output streams, PrintStream converts primitive data (integer, character) into text format instead 
of bytes. 
It then writes this formatted data to the output stream.

Additionally, the PrintStream class does not throw any input/output exceptions. 
Instead, we need to use the checkError() method to find any errors in it.

```java  
// FileOutputStream 
FileOutputStream file = new FileOutputStream(String file); 
// PrintStream 
PrintStream output = new PrintStream(file, autoFlush);
```

The PrintStream object that we constantly use in the System class:

```java  
class Main { 
    public static void main(String[] args) { 
        String data = "Hello World.";         
        System.out.print(data);
    }
}
```

We can produce the same object ourselves:

```java  
import java.io.PrintStream; 
class Main { 
    public static void main(String[] args) { 
        String data = "Hello World."; 
        try { 
            PrintStream output = new PrintStream("output.txt"); 
            output.print(data);
            output.close();
        } catch(Exception e) {             
            e.getStackTrace();
        }
    }
}
```

## Reader Class

The Reader class of the java.io package is an abstract superclass that represents a character stream.
Since Reader is an abstract class, it is not useful on its own. 
However, its subclasses can be used to read data.

### InputStreamReader Class

The InputStreamReader class of the java.io package can be used to convert data in bytes to data in characters. 
Extends the abstract Reader class. 
The InputStreamReader class works with other input streams. 
It is also known as a bridge between byte streams and character streams. 
This is because InputStreamReader reads bytes in the input stream as characters.
For example, some characters required two bytes to be stored in the storage. 
To read such data, we can use InputStreamReader class which reads two bytes together 
and converts them into corresponding character.

```java  
// InputStream 
FileInputStream file = new FileInputStream(String path); 
// InputStreamReader 
InputStreamReader input = new InputStreamReader(file);
```

```java  
import java.io.InputStreamReader;
import java.io.FileInputStream;
public class PatikaDev {
    public static void main(String[] args) {
        char[] array = new char[100];
        try {
            // FileInputStream
            FileInputStream file = new FileInputStream("input.txt");
            // InputStreamReader
            InputStreamReader input = new InputStreamReader(file);
            int data = input.read();
            while (data != -1) {
                System.out.print((char) data);
                data = input.read();
            }
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

## Writer Class

The Writer class of the java.io package is an abstract superclass that represents a stream of characters.
Since Writer is an abstract class, it is not useful on its own. 
However, its subclasses can be used to write data.

### OutputStreamWriter Class

The OutputStreamWriter class of the java.io package can be used to convert data in character 
format to data in byte format. 
It extends the Writer abstract class.

The OutputStreamWriter class works with other output streams. 
It is also known as a bridge between byte streams and character streams. 
This is because OutputStreamWriter converts its characters to bytes.

For example, some characters require two bytes to store in storage. 
To write such data, we can use the OutputStreamWriter class, 
which converts the character into corresponding bytes and stores the bytes together.

```java  
import java.io.FileOutputStream; 
import java.io.OutputStreamWriter; 
public class PatikaDev { 
    public static void main(String[] args) { 
        String data = "Java102 Classes with Patika"; 
        try { 
            // FileOutputStream 
            FileOutputStream file = new FileOutputStream("output.txt"); 
            // OutputStreamWriter 
            OutputStreamWriter output = new OutputStreamWriter(file); 
            output.write(data);
            output.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

The getEncoding() method can be used to get the encoding type used to write data to the output stream. 
For example,

```java  
import java.io.FileOutputStream; 
import java.io.OutputStreamWriter; 
import java.nio.charset.StandardCharsets; 
public class PatikaDev { 
    public static void main(String[] args) { 
        try { 
            FileOutputStream file = new FileOutputStream("output.txt"); 
            OutputStreamWriter output1 = new OutputStreamWriter(file); 
            OutputStreamWriter output2 = new OutputStreamWriter(file, StandardCharsets.UTF_8); 
            System.out.println("Character encoding of output1: " + output1.getEncoding()); 
            System.out.println("Character encoding of output2: " + output2.getEncoding()); 
            output1.close();
            output2.close();
        } catch(Exception e) {             
            e.getStackTrace();
        }
    }
}
```

## FileReader and FileWriter Classes

### FileReader Class

The FileReader class of the java.io package can be used to read data (as characters) from files. 
Extends the InputStreamReader class.

```java  
import java.io.FileReader; 
public class PatikaDev { 
    public static void main(String[] args) { 
        try { 
            FileReader input = new FileReader("input.txt"); 
            int data = input.read(); 
            while (data != -1) { 
                System.out.print((char) data);                 
                data = input.read();
            }
            input.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

### FileWriter Class

The FileWriter class of the java.io package can be used to write data (as characters) to the files.
Extends the OutputStreamWriter class.

```java  
import java.io.FileWriter; 
public class PatikaDev { 
    public static void main(String[] args) { 
        String data = "Java102 Classes with Patika"; 
        try { 
            FileWriter output = new FileWriter("output.txt");             
            output.write(data);
            output.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

## BufferedReader and BufferedWriter Classes

### BufferedReader Class

The BufferedReader class from the java.io package can be used with other readers to 
read data (as characters) more efficiently. 
Extends the abstract Reader class.

BufferedReader keeps an internal buffer(memory) of 8192 characters. 
During the read operation in BufferedReader, a bunch of characters are read from the disk and 
stored in the internal buffer, and characters are read individually from the internal buffer. 
Thus, the number of communications with the disk is reduced. 
This is why reading characters using BufferedReader is faster.

```java  
import java.io.BufferedReader; 
import java.io.FileReader; 
public class PatikaDev { 
    public static void main(String[] args) { 
        try {
            FileReader file = new FileReader("input.txt"); 
            BufferedReader input = new BufferedReader(file); 
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            } 
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
```

### BufferedWriter Class

The BufferedWriter class from the java.io package can be used with other writers to 
write data (as characters) more efficiently. 
It extends the Writer abstract class.

```java  
import java.io.BufferedWriter; 
import java.io.FileWriter; 
public class PatikaDev { 
    public static void main(String[] args) { 
        String data = "Java 102 Classes"; 
        try {
            FileWriter file = new FileWriter("output.txt"); 
            BufferedWriter output = new BufferedWriter(file); 
            output.write(data);
            output.close();
        } catch (Exception e) { 
            e.getStackTrace();
        }
    }
}
```

## PrintWriter Class

The PrintWriter class of the java.io package can be used to write output data in a commonly readable format (text).
It extends the Writer abstract class.

Unlike other Writer classes, PrintWriter converts primitive data (int, float, char, etc.) to text format. 
It then transfers this formatted data to the Writer.

Additionally, the PrintWriter class does not throw any input/output exceptions. 
Instead, we need to use checkError() method to find any errors in it.

```java  
import java.io.PrintWriter;
public class PatikaDev { 
    public static void main(String[] args) { 
        String data = "Java 102 Classes."; 
        try { 
            PrintWriter output = new PrintWriter("output.txt");             
            output.print(data);
            output.close();
        } catch (Exception e) {             
            e.getStackTrace();
        }
    }
}
```

## Test

1. Which of the following is used to perform all input and output operations in Java?  

    a. Streams  
    b. Variables  
    c. Classes  
    d. Methods  

2. Which of the following is a stream type in Java?

   a. integer stream  
   b. short stream  
   c. byte stream  
   d. long stream

3. Byte streams use which of the following classes for input and output processing?

   a. InputStream  
   b. InputOutputStream  
   c. reader  
   d. All  

4. Which of the following classes is used to read data from a file?

   a. InputStream  
   b. BufferedInputStream  
   c. FileInputStream  
   d. BufferedFileInputStream

5. Byte streams use which of the following classes for input and output operations?

   a. InputStream  
   b. Writer  
   c. ReadStream  
   d. OutputStream

6. Which of the following classes is used to read data from a byte array?

   a. InputStream  
   b. BufferedInputStream  
   c. ArrayInputStream  
   d. ByteArrayInputStream

7. Which of the following is the process of writing the state of an object into a byte stream?

   a. Serialization  
   b. Externalization  
   c. File Filtering  
   d. All

8. Which stream class provides serialization in Java?

   a. ObjectOutputStream  
   b. ByteArrayOutputStream  
   c. BufferedOutputStream  
   d. FileOutputStream

9. The _____ class is used to increase the efficiency of input operations.

   a. DataInputStream  
   b. FileInputStream  
   c. BufferedInputStream  
   d. PipeInputStream

10. Which of the following classes contains print() & println() methods?

   a. System  
   b. System.out  
   c. BufferedOutputStream  
   d. PrintStream

11. Which is wrong about InputStreamReader?

   a. The InputStreamReader class can be used to convert data in bytes to data in characters.  
   b. Extends the abstract Writer class.  
   c. The InputStreamReader class works with other input streams.  
   d. It is also known as a bridge between byte streams and character streams.

12. What is the default character set for OutputStreamWriter?

   a. UTF-8  
   b. The default character set of the host machine.  
   c. UTF-12  
   d. ANSI

13. Which of the following statements is correct?

   a. Characters are read from files with FileReader.  
   b. Characters are written to files with FileReader.  
   c. You can read integer and float values with FileReader.  
   d. None

14. You can use _______________ to write characters to files.

   a. FileWriter  
   b. FileReader  
   c. ObjectWriter  
   d. CharWriter  

Answers: 1.a, 2.c, 3.a, 4.c, 5.d, 6.d, 7.a, 8.a, 9.c, 10.d, 11.b, 12.b, 13.a, 14.a
