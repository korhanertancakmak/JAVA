# Clone of Patika

## Java Swing GUI Usage

In this chapter, we will write an application with "graphical user interface" (GUI). 
For this, we will use "swing" library.
You can think this application just like the website of Patika.

In our application, there will be users. 
These users are operators that manage the patika side, teachers and students.
These operators will create patika on the system, create paths, and
assign teachers to these paths. 
The teachers will teach the contents to the students.
Just like you are here, students will attend to the paths. 

We will use a database on the back end. 
We will write a program that describes it. 

NOTE: We will use IntelliJ for this project.

### Creating a "Swing UI Designer" File

After creating a package named "com.patikadev",
we create a GUI. 
We right-click on the package, choose new, choose "Swing UI Desinger",
and choose "GUI Form" just like below.

![Step-1](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step1.png?raw=true)

After this, we will have here:

![Step-2](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step2.png?raw=true)

You should choose GridLayoutManager from here. 
Others are too old, that's why they are not responsive for any screen, any more.
Let's say our Form name is "Example."
Eventually, we will have two files in the package

1. Example.java
2. Example.form

This form file is the interface that we design. 
The java file is the reflection of what we are doing in here.

### Components of GUI Form

In Swing, there are JFrames and JPanels. 
JFrame is kind of a windows of the app. 
We can place panels on the JFrame.
Jpanel is just one component. 
And these have properties just like below:

![Step-3](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step3.png?raw=true)

Let's say field name of the main JPanel as "wrapper."
When we write its name, our java file is updates as:

```java  
package com.patikadev;
import javax.swing.*;
public class Example {
    private JPanel wrapper;
}
```

### Creating a Frame from The Application

Next, we want this java file extend from JFrame,
because this class has to be inherited by Swing form file.
But we can do it in these two ways:
1. First, we can right-click on mouse and choose form main.
When we follow this option, we will have:

    ```java  
    package com.patikadev;
    import javax.swing.*;
    public class Example {
        private JPanel wrapper;
        public static void main(String[] args) {
            JFrame frame = new JFrame("Example");
            frame.setContentPane(new Example().wrapper);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
    ```

2. Otherwise, we can use "extend" keyword. 
By using this, we can remove the main method above, 
and must insert a constructor:

    ```java  
    package com.patikadev;
    import javax.swing.*;
    public class Example extends JFrame{
        private JPanel wrapper;
        public Example() {
            
        }
    }
    ```

Here, we can create everything we want in this constructor.
Let's first add the wrapper that is our main JFrame object.

```java  
// First option
this.add(wrapper);
// Second option
setContentPane(wrapper);
```

We can give a width:

```java  
setSize(600, 400);
```

We can set title:

```java  
setTitle("Application Name");
```

We can make the frame visible by using:

```java  
setVisible(true);
```

We can create a new Main class with the main method from com package.
And we can create an object of our Example class.

```java  
package com.patikadev;

import com.patikadev.Test.Example;

public class Main {
   public static void main(String[] args) {
      Example example = new Example();
   }
}
```

Now, when we run this Main class, we will have:

![Step-4](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step4.png?raw=true)

### Designing GUI

Here, we created our first app. 
We can change background by clicking background on the property section,
and then choosing the color and copying it on the JFrame:

![Step-5](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step5.png?raw=true)

We run the app, but the app is still working without an end.
To stop the app, we can write this:

```java  
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
```

Our frame is being created in the top-left corner of our first-screen.
We can change its position by this:

```java  
int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
setLocation(x, y);
```

These "x" and "y" points are the center of my screen.
You can change it if you want.

Every new created panel must have a name.
Let's design a login frame.
Since the JPanels are working with a grid system,
we can divide the main panel into two separate panels.
From the Palette section, we can choose another JPanel,
and dragging it to the main JPanel. 
We name it as "wTop."
We add another and name it as "wBottom."
The final view of our app is :

![Step-6](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step6.png?raw=true)

We can add any texts by adding JLabel component.

![Step-7](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step7.png?raw=true)

Also, we can change the text from the text on property section.
Automatically, we have a "vertical spacer" component in the first JPanel, now.
Because if we don't have it, our JLabel will take place 
at the center of the upper JPanel which wraps the JLabel.

We can also change the alignments of any components.
From the property section, we change horizontal align of the wTop JPanel as "Center" 
for the text can take place at the center of the frame.
We can change its Font from font property.
Let's choose it as Arial, its Style as "Bold.", and its Size as "20."

![Step-8](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step8.png?raw=true)

When we run the app :

![Step-9](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step9.png?raw=true)

We can add another JLabel for texting as "User Login."
First, we remove the vertical spacer. 
And then, align the grid with parent. 
And then, choose vertical align of two JLabels in wTop as "Top."
And then, choose horizontal align of them as "Center."

![Step-10](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step10.png?raw=true)

We can also play with margins for this top wrapper by 10 into
the first element of margins[topMarg,leftMarg,bottomMarg,rightMarg].
This is also a method to get to center the label by margins.
And we run it again:

![Step-11](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step11.png?raw=true)

Next, we will play with the bottom wrapper.
First, we add a JLabel on it with a vertical spacer named username,
and right below this JLabel we add a JTextField; that will be used
for the input of the username, named "fld_username."
Second, we add another JLabel on it with a vertical spacer named password,
and right below this JLabel we add a JPasswordField; that will be used
for the input of the user password, named "fld_password."
And lastly, we add a JButton at bottom.
When we run this:

![Step-12](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step12.png?raw=true)

All panels in wBottom can be aligned to be at center,
and this can look like better than now it is.
We can do this by adding two HSpacer on the two sides of
JPanels in the wBottom. 
And now we run it again:

![Step-13](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step13.png?raw=true)

We can turn the resizable feature off if we want:

```java  
package com.patikadev;
import javax.swing.*;
import java.awt.*;
public class Example extends JFrame{
    private JPanel wrapper;
    private JPanel wTop;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    public Example() {
        add(wrapper);
        setSize(600,400);
        setTitle("Application Name");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);
    }
}
```

And now, you cannot resize the coming frame.

We can also change the theme of the button that we created.
There is UIManager class for this purpose.
However, let's show you how many different themes we can get first.
We add the code below at the top line of Example() constructor.

```java  
for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    System.out.println(info.getName() + " => " + info.getClassName());
}
```

This code shows us we can call these themes by their names:

```java  
Metal => javax.swing.plaf.metal.MetalLookAndFeel
Nimbus => javax.swing.plaf.nimbus.NimbusLookAndFeel
CDE/Motif => com.sun.java.swing.plaf.motif.MotifLookAndFeel
Windows => com.sun.java.swing.plaf.windows.WindowsLookAndFeel
Windows Classic => com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
```

Let's use "Nimbus" for our button.
For this, we must rewrite the for Each loop like this:

```java  
for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    if ("Nimbus".equals(info.getName())) {
        try {
            UIManager.setLookAndFeel(info.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
```

When we run it again:

![Step-14](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step14.png?raw=true)

Now, we have a better button theme.

### Entering Inputs on the GUI Frame(Listeners)

How can we do if a user enters their information and click on the button?
All the components in our Palette have their specified listeners.
If we right-click on the button object, we can choose "Create Listener."
When we click on it, all available listeners will be listed on the screen.

![Step-15](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step15.png?raw=true)

These are some of the commonly used listener interfaces in the context of JButton 
components in Java Swing:

* ***ActionListener***: This interface is used to handle action events on a button. 
It listens for clicks or activations of the button.
* ***MouseListener***: Similar to the JList component, this interface can be used to 
listen for mouse events on a button. 
This includes events like mouse clicks, mouse presses, mouse releases, etc. 
* ***FocusListener***: This interface can be used to listen for focus events on a 
button component. 
It typically listens for events related to the component gaining or losing focus.
* ***KeyListener***: Although less common for buttons, this interface can be used 
to listen for keyboard events on a button component. 
This includes events like key presses, key releases, etc.

We need here ActionListener.
When we click on it, it will create the code we need automatically in our constructor.

```java  
btn_login.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button is clicked!");
    }
});
```

Here, "btn_login" is the object of our button. 
This code calls "addActionListener" method by a new ActionListener object, 
which is actually an interface, with an overridden method "actionPerformed."

![Step-16](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step16.png?raw=true)

We can use lambda expression in the code above.

```java  
btn_login.addActionListener(e -> {
    if(fld_username.getText().length() == 0 || fld_password.getText().length() == 0) {
        JOptionPane.showMessageDialog(null,"Please fill all the fields!",
              "Error", JOptionPane.INFORMATION_MESSAGE);
    } else {
        System.out.println(fld_username.getText());
    }
});
```

Firstly, we want to check whether the username or password is empty.
Second, if one of them is empty, static method of JOptionPane will pop out
a message that says "Please fill all the fields!" with a title as "Error."
Otherwise, we will print the username text to the console.
Let's see this :

![Step-17](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step17.png?raw=true)

## Project Files and Management of User

We will use a database for operators in this part.
So, to be able to demonstrate a database, we will be using [this](https://www.appserv.org/en/)
application, because it helps me to create databases on php.

### Creating a Database

We create a new database named "patika."
We choose "utf8_general_ci" for characters.
We create a new table in it named "user."
This user will have :
* id as primary in int type,
* name in VARCHAR type with length 255,
* uname(for username) in VARCHAR type with length 255,
* pass in VARCHAR type with length 255,
* type in ENUM with a set of "operator, teacher, student"

After we created the table, we add a data as:
* id = 1,
* name = korhan cakmak
* uname = kcakmak
* pass = 1234
* type = "operator"

For now, it will show as:

![Step-18](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step18.png?raw=true)

Now, we need to call this from the application.
We will create two package focuses in "com.patikadev."
One is named "Model," the other is named "View."
In the "Model" package, we need to transfer data from the user table
to an object.
The purpose of doing this is to objectify the coming data from the database.
So, we will create a user class for this.
This class must include id, name, uname, pass, and type as its fields.
After creating its constructor with parameters and empty constructor,
we create an Operator class that extends User class.
And then, we create a GUI Form named "OperatorGUI" in "View" package.
Just like we have done in the last section, we extend this class to JFrame,
and we add a field for the main wrapper.
In its constructor, we can do what we have done before.
However, to centralize the frame like the codes we had in the last section:

```java  
int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
setLocation(x, y);
```

We will have plenty of code lines that will be the same.
We shouldn't write them again and again for readability.
So, we can create a Helper class in Helper package just for this purpose.
This class will have static methods to help us in the classes of Model.
For example, for centering the output frame of the application we can use:

```java  
package com.patikadev.Helper;
import java.awt.*;
public class Helper {
    public static int screenCenterPoint(String axis, Dimension size) {
        int point = 0;
        switch (axis) {
            case "x" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }
}
```

Also, we can use another class named "Config" for the constants such as the title of the JFrame.

```java  
package com.patikadev.Helper;
public class Config {
    public static final String PROJECT_TITLE = "Patika.Dev";
}
```

So our OperatorGUI class's constructor will be like this:

```java  
public OperatorGUI() {
    add(wrapper);
    setSize(1000, 500);
    setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);
}
```

We can also add another static method into Helper class to set layout for the theme:

```java  
public static void setLayout() {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            try {
                UIManager.setLookAndFeel(info.getClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                    hrow new RuntimeException(e);
            }
            break;
        }
    }
}
```

And we can call this from a main method that will be created next:

```java  
Helper.setLayout();
```

Also, we need to add a new field for an operator into the constructor:

```java  
private final Operator operator;

public OperatorGUI(Operator operator) {
    this.operator = operator;
    add(wrapper);
    setSize(1000, 500);
    setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);
}
```

Let's create a main method and try to run it:

```java  
public static void main(String[] args) {
    Helper.setLayout();
    Operator op = new Operator();
    op.setId(1);
    op.setName("korhan cakmak");
    op.setPass("123");
    op.setType("operator");
    op.setUname("kcakmak");
    OperatorGUI opGUI = new OperatorGUI(op);
}
```

Now, we can design OperatorGUI form file.
We want to create a tab menu by using JTabledPane.
After the things, which we learned from the first section, we have done
the last view of our frame is:

![Step-19](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step19.png?raw=true)

### Connecting to this Database to Print the User List

So now, we can list our users.
To be able to do this, we have to add a user to the database,
and then we have to look for the output of them.
Firstly, we need to connect to the database.
To be able to connect this database :
1. Since We used phpMyAdmin to create a database,
we have to download [this](https://dev.mysql.com/downloads/file/?id=525082)
and copy the jar file in our src file of the project in IntelliJ.
2. We have to add this path to the project library from Project Structure in the File Section.
3. Since we will repeatedly use this connection, first we can add our database url,
username and password into the Config java file.

   ```java  
   public static final String DB_URL = "jdbc:mysql://localhost/patika";
   public static final String DB_USERNAME = "root";
   public static final String DB_PASSWORD = "58265826";
   ```

4. And secondly, since we have to use getConnection static method in DriverManager class, 
we can create a DBConnector class for this purpose in Helper package.

   ```java  
   package PatikaDev.Java102.JAVA.com.patikadev.Helper;
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;
   public class DBConnector {
       private Connection connect = null;
       public Connection connectDB() {
           try {
               this.connect = DriverManager.getConnection(Config.DB_URL, 
                            Config.DB_USERNAME, Config.DB_PASSWORD);
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return this.connect;
       }
       public static Connection getInstance() {
           DBConnector db = new DBConnector();
           return db.connectDB();
       }
   }
   ```

5. Since we want an arrayList in return to be able to see a list on the out frame,
we can place this method in the User class as static.

    ```java  
    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try (Statement st = DBConnector.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    ```
6. We are ready to get the list, but first we should get the front end ready.
We will use JTable named "tbl_userList" in a JScrollPane named "scrl_userList"
in the OperatorGUI form file.

7. These tables are working with models named "DefaultTableModel."
There are two parameters for this model class.
One is for row, and another is for column.
We will use its constructor, which is taking in Object type.
We can keep our lists as row and column in an Object array.
So first we add mdl_user_list field for the model class,
and then we add the Object array field into Operator GUI class's constructor.
And secondly, we will add the coming information from the database like this:

   ```java  
   // ModelUserList
   mdl_user_list = new DefaultTableModel();
   Object[] col_userList = {"ID", "Name Surname", "User Name", "Password", "User Type"};
   mdl_user_list.setColumnIdentifiers(col_userList);
   for (User obj: User.getUserList()) {
       Object[] row = new Object[col_userList.length];
       row[0] = obj.getId();
       row[1] = obj.getName();
       row[2] = obj.getUname();
       row[3] = obj.getPass();
       row[4] = obj.getType();
       mdl_user_list.addRow(row);
   }
   tbl_userList.setModel(mdl_user_list);
   tbl_userList.getTableHeader().setReorderingAllowed(false);
   ```
8. First, we define the column identifiers by getting the information 
we get from the database.
Second, we use the static method that makes the query in the database
and gets the information of our userList.
Third, by using a for each loop, we bury the users into the table.
Lastly, we can turn reordering between the columns disabled for future purposes.

9. If we want to reflect a new row to this list frame,
then we can simply use addRow static method of our model.

   ```java  
   Object[] firstRow = {"1", "korhan cakmak", "kcakmak", "58265826", "operator"};
   mdl_user_list.addRow(firstRow);
   ```

10. Let's add a new user to the database, and then run the application.

![Step-20](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step20.png?raw=true)

## Database Operations

### Redesigning GUI Form File

We can insert and subtract users into the form that we created in the last section.
What we need to change in the OperatorGUI.form:

1. Add a new JPanel named "pnl_user_form."
2. This JPanel will have 4 JLabels named "Name Surname," "User Name," 
"Password," "User Type" respectively.
3. Below each JPanel except the last one, we must have a JTextField named
"fld_userName," "fld_userUName," "fld_userPassword," and a JComboBox named
"cmb_userType." Right below this combo box, we add a button named "btn_userAdd."
4. Only JComboBox is not one of what we have seen so far. This is a dropdown list.
We can add objects in this combo box by clicking to the model from the property section.
Since this is a user type, we have operator, teacher and student options.
5. Right below this button, we add another JLabel-JTextField-JButton with names
"User ID," "fld_userID," and "btn_userDelete."

Eventually, our OperatorGUI form file will look like:

![Step-21](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step21.png?raw=true)

Our front end is done.
Now, we need to write methods into the java files that write the data to or
delete it from the database. 
But first, we need to add some helper methods into the Helper class for messages 
that we get from adding or deleting. 
These are the methods we will use maybe for a million times.

### Reflecting New Data on GUI

First, we add the "isFieldEmpty" method:

```java  
public static boolean isFieldEmpty(JTextField field) {
   return field.getText().trim().isEmpty();
}
```

This method checks the fields in the add-delete Panel, and returns if they are
empty as boolean.
And we can write a result pane method:

```java  
public static void optionPageEng(boolean isDone) {
   if (isDone) {
      UIManager.put("OptionPane.okButtonText", "DONE");
   } else {
      UIManager.put("OptionPane.okButtonText", "TRY AGAIN");
   }
}
```

This will be the button text coming after we add or delete the data.
If our operation is successful, we write "DONE" into the button, otherwise
we write "TRY AGAIN."
Actually, there can be 4 cases that we can have here. 
1. One of the fields might be empty.
This is one "error" case.
2. Operation can be successful.
This is the "done" case.
3. There can be an undesired case.
This is another "error" case.
4. And by default, we can pop out what we want in other cases, such as
adding a data which is already added before.

```java  
public static void showMsg(String str) {
   String msg;
   String title;
   switch (str) {
      case "fill":
         msg = "Please fill all fields!";
         title = "Error!";
         optionPageEng(false);
         break;
      case "done":
         msg = "Operation is successful";
         title = "Result";
         optionPageEng(true);
         break;
      case "error":
         msg = "Something goes wrong...";
         title = "Error";
         break;
      default:
         msg = str;
         title = "Message!";
   }
   JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
}
```

Now, we need to define fetch method in the user class, 
because we want the new list we get from the database every time 
after we add or delete data on the database.

```java  
public static User getFetch(String uname) {
    User obj = null;
    String query = "SELECT * FROM user WHERE uname = ?";     
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setString(1, uname);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setUname(rs.getString("uname"));
            obj.setPass(rs.getString("pass"));
            obj.setType(rs.getString("type"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return obj;
}
```

This method creates a new user, and then taking the user matches the uname that is passed to it.
And checking the result set has a next or not.
If it has, it adds the user's data into the user's object, and finally returns it.
It's time to insert "add" and "delete" methods into the User class.

```java  
public static boolean add(String name, String uname, String pass, String type) {
    String query = "INSERT INTO user (name, uname, pass, type) VALUES (?,?,?,?)";
    User findUser = User.getFetch(uname);
    if (findUser != null) {
        Helper.showMsg("This user is already added before. Please enter different user name!");
        return false;
    }
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setString(1, name);
        pr.setString(2, uname);
        pr.setString(3, pass);
        pr.setString(4, type);
        int response = pr.executeUpdate();
        if (response == -1) {
            Helper.showMsg("error");
        }
        return response != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

This method first calls the getFetch method with the new user to be added.
If the returning user object is null, then it means we already have this user,
so it opens an error pane.
Otherwise, it creates a preparedStatement to update the database with this user.
If the response from the update is a failure, then it creates an error pane.
If the response from the update is a success, then it adds and returns true.

```java  
public static boolean delete(int id) {
    String query = "DELETE FROM user WHERE id = ?";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setInt(1, id);
        return pr.executeUpdate() != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

Similarly, "delete" method creates a PreparedStatement and updates by deleting the user id
that we passed to it.

The first of the things we need to do for calling these methods in OperatorGUI java file is 
to rewrite ModelUserList part of the constructor. 
Because we will use it and its for-loop for filling the list every time we call it.

```java  
// ModelUserList
mdl_user_list = (DefaultTableModel) isCellEditable(row, column) -> {
    if (column == 0) {
        return false;
    }
    return super.isCellEditable(row, column);
};
Object[] col_userList = {"ID", "Name Surname", "User Name", "Password", "User Type"};
mdl_user_list.setColumnIdentifiers(col_userList);
row_userList = new Object[col_userList.length];

loadUserModel();
tbl_userList.setModel(mdl_user_list);
tbl_userList.getTableHeader().setReorderingAllowed(false);
```

Since all the columns are editable, and we will make the changes with respect to the
first column, we need to disable the ability of being edited for that column.
This is done by casting it as DefaultTableModel.
Instead of the for-loop that we used before, now we have a new static method in 
OperatorGUI java file. 
The name of the method is loadUserModel:

```java  
public void loadUserModel() {
    DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
    clearModel.setRowCount(0);
    for (User obj: User.getUserList()) {
        int i = 0;
        row_userList[i++] = obj.getId();
        row_userList[i++] = obj.getName();
        row_userList[i++] = obj.getUname();
        row_userList[i++] = obj.getPass();
        row_userList[i++] = obj.getType();
        mdl_user_list.addRow(row_userList);
    }
}
```

This method will be adding the new rows to the frame each time that is called.
We can add a couple lines of code to be able to get the id into the UserID field
of the frame by selecting it from the list.

```java  
tbl_userList.getSelectionModel().addListSelectionListener(e -> {
    try {
        String selectUserID = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString();
        fld_userID.setText(selectUserID);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
});
```

This code will listen to our table on the frame, and when we click on any users on the list,
it will set the text of the userID's field to the user's id that we selected.
Now we can call the add and delete methods in the User class.
Firstly, we write for adding an user:

```java  
btn_userAdd.addActionListener(e -> {
    if (Helper.isFieldEmpty(fld_userName) ||
            Helper.isFieldEmpty(fld_userUName) ||
            Helper.isFieldEmpty(fld_userPassword)) {
        Helper.showMsg("fill");
    } else {
        String name = fld_userName.getText();
        String uname = fld_userUName.getText();
        String pass = fld_userPassword.getText();
        String type = cmb_userType.getSelectedItem().toString();
        if (User.add(name, uname, pass, type)) {
            Helper.showMsg("done");
            loadUserModel();
            fld_userName.setText(null);
            fld_userUName.setText(null);
            fld_userPassword.setText(null);
        }
    }
});
```

This code will listen to the "Add" button, and if it's clicked firstly, 
it will look for the texts whether they are empty or not. 
If they are empty, it will throw an error pane.
If they are filled, it will take the data and pass it to the "add" method,
and if it's been added, it will call the "operation is done" pane message.
After that, it will delete the texts from the fields that are already added.

Now we can call the delete method: 

```java  
btn_userDelete.addActionListener(e -> {
    if (Helper.isFieldEmpty(fld_userID)) {
        Helper.showMsg("fill");
    } else {
        int userID = Integer.parseInt(fld_userID.getText());
        if (User.delete(userID)) {
            Helper.showMsg("done");
            loadUserModel();
        } else {
            Helper.showMsg("error");
        }
    }
});
```

Similarly, this code will listen to the "Delete" button, and if it's clicked firstly,
it will look for the userID field whether it's empty or not.
If it's empty, it will throw an error pane.
If it's filled, it will take the data and pass it to the "delete" method,
and if it's been deleted, it will call the "operation is done" pane message.
If it couldn't be found in the list, the "delete" method will fail, and we
will throw another error message here.

When it is added or deleted, this code calls "loadUserModel" method to refresh
the list on the frame once again.
Let's give a try now what we can get from the application. 
When we run it: 

![Step-22](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step22.png?raw=true)

We see that we have three users in the database.
Let's first add a new user to the database with this information:  

Name Surname : Albert Einstein
User Name    : emc2
Password     : relativity
User Type    : educator

and then press "Add":

![Step-23](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step23.png?raw=true)

After we click on the "DONE" button, the pane closes and our new list of users
takes place on the frame.

![Step-24](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step24.png?raw=true)

As you can see, we have done adding successfully.
Let's try now deleting one of the users from the list.
We try to delete the userID = 3 by clicking on it.

![Step-25](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step25.png?raw=true)

We see its user id is written directly into the text box to delete.
We click on the "delete" button:

![Step-26](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step26.png?raw=true)

It seems our operation is successful.
Let's click on the "Done" button:

![Step-27](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step27.png?raw=true)

And yeah, our data is gone!

## Searching for Data in Tables

We have done adding and deleting user data from the list. 
Now, we will look for updating it on the database by just editing on the frame.
To be able to do that, we need to listen to any changes on the table.
This time, let's write the calling code first.

```java  
tbl_userList.getModel().addTableModelListener(e -> {
    if (e.getType() == TableModelEvent.UPDATE) {
        int user_id = Integer.parseInt(tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString());
        String name = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 1).toString();
        String userName = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 2).toString();
        String userPass = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 3).toString();
        String userType = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 4).toString();
        
        if (User.update(user_id, name, userName, userPass, userType)) {
            Helper.showMsg("done");
        } else {
            Helper.showMsg("error");
        }
        loadUserModel();
    }
});
```

This code listens to the table if the operation on the table is an update operation,
then it assigns the changes to data variables such as name, userName, userPass and userType.
It calls the update method that we will write below by passing these parameters into it.
If return is true, it opens a pane as done.
Otherwise, it opens an error pane.
The update method in the User class is below:

```java  
public static boolean update(int id, String name, String uname, String pass, String type) {
    String query = "UPDATE user SET name=?, uname=?, pass=?, type=? WHERE id = ?";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setString(1, name);
        pr.setString(2, uname);
        pr.setString(3, pass);
        pr.setString(4, type);
        pr.setInt(5, id);
        return pr.executeUpdate() != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

This method creates an update query that new id, name, username, password, and type parameters
into the database.
However, like we did in "add" method, if we enter the same data here that already is in the table,
it does not give any errors for now.
We can add this feature to the application by getting the same codes from the beginning of "add" method
and paste it on the "update" method.
This code is:

```java  
User findUser = User.getFetch(uname);
ArrayList<String> possibleTypes = new ArrayList<>();
possibleTypes.add("operator");
possibleTypes.add("educator");
possibleTypes.add("student");
if (findUser != null && findUser.getId() != id || !possibleTypes.contains(type)) {
    Helper.showMsg("This user is already added before. Please enter different user name!");
    return false;
}
```

This blocks the "update" method if the change matches one data which is already in the table.
But this "getFetch" method checks only the username if we want to change the name of the user,
we won't be able to do this change. 
So we need to add another condition into the if condition which is second condition.

The Next problem is here, changing the type parameter.
If we change the type into a type that is not in our enum variables of the database which are
operator, educator and student, we will need to throw another error box.
This is the third condition in the if statement.

Now we can add search bar into the GUI frame.

![Step-28](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step28.png?raw=true)

Here we added another JPanel into the panel of userList.
It includes 3 JLabel that have named as "Name Surname," "User Name," "User Type."
Each has their own JTextField right below it.
User Type has a JComboBox instead of JTextField.
And there is another button for "Search" to be clicked.

We add a Listener by right-clicking onto the "Search" button,
and we write this code into the OperatorGUI java file.

```java  
// Search button Listener
btn_userSearch.addActionListener(e -> {
    String name = fld_shUserName.getText();
    String uname = fld_shUserUName.getText();
    String type = cmb_shUserType.getSelectedItem().toString();
    String query = User.searchQuery(name, uname, type);
    loadUserModel(User.searchUserList(query));
});
```

We need to add three more methods, two into User class and another into OperatorGUI 
class as we can see from here. 
These are searchQuery, searchUserList, and overloaded loadUserModel methods.

Let's begin searchQuery method :

```java  
public static String searchQuery(String name, String uname, String type){
    String query = "SELECT * FROM user WHERE uname LIKE '%{uname}' OR name LIKE '%{name}'";
    query = uname.isEmpty() ? query.replace("{uname}", " ") : query.replace("{uname}", uname);
    query = name.isEmpty() ? query.replace("{name}", " ") : query.replace("{name}", name);
    if (!type.isEmpty()) {
        query += " OR type='{type}'";
        query = query.replace("{type}", type);
    }
    return query;
}
```

This method takes three parameters and creates a query with respect to them.
This method is also very functional to use for future purposes.

Next, we can write a similar method to the getUserList method in User class,
which is searchUserList:

```java  
public static ArrayList<User> searchUserList(String query){
   ArrayList<User> userList = new ArrayList<>();
   User obj;
   try (Statement st = DBConnector.getInstance().createStatement()) {
      ResultSet rs = st.executeQuery(query);
      while(rs.next()) {
         obj = new User();
         obj.setId(rs.getInt("id"));
         obj.setName(rs.getString("name"));
         obj.setUname(rs.getString("uname"));
         obj.setPass(rs.getString("pass"));
         obj.setType(rs.getString("type"));
         userList.add(obj);
      }
      rs.close();
   } catch (SQLException e) {
      e.printStackTrace();
   }
   return userList;
}
```

This method creates a user list as an ArrayList by using the query, 
that is created by the searchQuery method.
This list will be the list that we search for.

And lastly, we write the overloaded loadUserModel method:

```java  
public void loadUserModel(ArrayList<User> list) {
   DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
   clearModel.setRowCount(0);
   for (User obj: list) {
      int i = 0;
      row_userList[i++] = obj.getId();
      row_userList[i++] = obj.getName();
      row_userList[i++] = obj.getUname();
      row_userList[i++] = obj.getPass();
      row_userList[i++] = obj.getType();
      mdl_user_list.addRow(row_userList);
   }
}
```

This method takes the list coming from searchUserList method and creates a
new list that we want.

Let's give a try this, now.
Initially, we have 3 data in the database.

![Step-29](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step29.png?raw=true)

When we directly click to the Search button:

![Step-30](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step30.png?raw=true)

We get nothing, because there is no such data in our database.
However, if we try the ones in the database firstly giving name and surname:

![Step-31](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step31.png?raw=true)

and by giving its username :

![Step-32](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step32.png?raw=true)

and by setting the type :

![Step-33](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step33.png?raw=true)

These works as we desired.
Finally, we want to write 1-line code to get logout button functional.
We simply add another listener to that button:

```java  
btn_logout.addActionListener(e -> {
    dispose();
});
```

This code disposes the JFrame totally.

## Management of Patika

In this section, we will create patikas, in each patika we will create courses, and
each course has its own content.

We add another table into our database named as "Patika."
For now, it has two columns that are id and name.
The first data in our Patika table is "Java Backend Path."
Now, we should add this into our GUI form.
We simply add pnl_patikaList into our tab_operator JTabbedPane as shown below.

![Step-34](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step34.png?raw=true)

We need to create a model of these patikas in our Model package.
So, we create a Class named as "Patika."
After creating its fields, constructor, and its getter & setter methods,
we get back to the GUI form file to create JScrollPane into the pnl_patikaList 
just as we have done in the last section.
Because we want the screen to be scrollable.
And then, we add another JTable onto it.

![Step-35](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step35.png?raw=true)

We can now create the fields we need in our OperatorGUI java file.

```java  
private DefaultTableModel mdl_patikaList;
private final Object[] row_patikaList;
```

And we model our patika list same as we did for the user list.

```java  
// Model patika list
mdl_patikaList = new DefaultTableModel(); 
Object[] col_patikaList = {"ID", "Patika Name"};
mdl_patikaList.setColumnIdentifiers(col_patikaList);
row_patikaList = new Object[col_patikaList.length];
loadPatikaModel();
tbl_patikaList.setModel(mdl_patikaList);
tbl_patikaList.getTableHeader().setReorderingAllowed(false);
```

Here we need to write loadPatikaModel() method, 
but first let's write getList method in the Patika java file:

```java  
public static ArrayList<Patika> getList() {
    ArrayList<Patika> patikaList = new ArrayList<>();
    String query = "SELECT * FROM patika";
    Patika obj;
    try (Statement st = DBConnector.getInstance().createStatement()) {
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            obj = new Patika(rs.getInt("id"), rs.getString("name"));
            patikaList.add(obj);
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return patikaList;
}
```

This method is very similar to the getUserList method in the user class.
It creates a statement to get all the paths from the patika table in the same database.
And then, it adds the Patika objects into the patikaList arrayList, and returns it.
Now, we can write loadPatikaModel() method in OperatorGUI class:

```java  
private void loadPatikaModel() {
    DefaultTableModel clearModel = (DefaultTableModel) tbl_patikaList.getModel();
    clearModel.setRowCount(0);
    int i;
    for (Patika obj: Patika.getList()) {
        i = 0;
        row_patikaList[i++] = obj.getId();
        row_patikaList[i++] = obj.getName();
        mdl_patikaList.addRow(row_patikaList);
    }
}
```

This is also very similar to the loadUserModel() method.
And it sets the rows coming from the arrayList into the DefaultTableModel,
which is mdl_patikaList field.
When we check it whether it works :

![Step-36](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step36.png?raw=true)

As you can see, the size of these two columns is too big with respect to the columns
in the user table.
We can set the size of the first column, which is patika id, by this :

```java  
tbl_patikaList.getColumnModel().getColumn(0).setMaxWidth(75);
```

When we check :

![Step-37](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step37.png?raw=true)

Now we can add new paths to the patika list.
Again, first we need to design GUI form file.
We add a new JPanel, JLabel, JTextField and button for adding.

![Step-38](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step38.png?raw=true)

We will create an action listener for the "Add" button.
But, we need to create an add() method in the Patika class.

```java  
public static boolean add(String name) {
    String query = "INSERT INTO patika (name) VALUES (?)";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setString(1, name);
        int response = pr.executeUpdate();
        if (response == -1) {
            Helper.showMsg("error", "Something goes wrong...");
        }
        return response != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

Similarly, we create add method into the Patika class this time.
So, we need to write the action listener code now:

```java  
btn_patikaAdd.addActionListener(e -> {
    if (Helper.isFieldEmpty(fld_patikaName)) {
        Helper.showMsg("fill", null);
    } else {
        if (Patika.add(fld_patikaName.getText())) {
            Helper.showMsg("done", null);
            loadPatikaModel();
            fld_patikaName.setText(null);
        } else {
            Helper.showMsg("error", null);
        }
    }
});
```

After we run it and add "Front-end Path," we get :

![Step-39](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step39.png?raw=true)

Now, we want to be able to right-click on the paths in the table to update or delete it.
This can be done with the components called as "JPopUpMenu" and "JMenuItem."

```java  
private JPopupMenu patikaMenu;
```

After adding this patikaMenu field into the OperatorGUI java file,
we create JPopupMenu and its 2 items that are update and delete:

```java  
patikaMenu = new JPopupMenu();
JMenuItem updateMenu = new JMenuItem("Update"); 
JMenuItem deleteMenu = new JMenuItem("Delete"); 
patikaMenu.add(updateMenu); 
patikaMenu.add(deleteMenu);
         
tbl_patikaList.setComponentPopupMenu(patikaMenu);
```

And then we add this menu into the table by using setComponentPopupMenu static method.
When we check it whether it works or not:

![Step-40](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step40.png?raw=true)

As you can see when we select one and right-click, it pops up the menu,
but it doesn't select what we select.
So it looks like we click on the table completely.
We need a mouse listener to get the mouse's position.

```java  
tbl_patikaList.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        int selected_row = tbl_patikaList.rowAtPoint(point);
        tbl_patikaList.setRowSelectionInterval(selected_row, selected_row);
    }
});
```

There are many listeners for the mouse,
but we want MousePressed to override it.
Here it takes a Point object.
This is the mouse location, when we click on it.
We can take this location and pass into the rowAtPoint method.
This method gives us the location of the mouse is in which row.
And simply, we give a selection on the list.
This makes :

![Step-41](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step41.png?raw=true)

As you can see, now the first row is highlighted as white,
since we chose it.

Now we want to open a new window when we click on the "delete" from the pop-up menu.
This means that we will create a new GUI form file named as "UpdatePatikaGUI" in the View package.
After just creating a wrapper JPanel, we directly fill the UpdatePatikaGUI java file with this:

```java  
private JPanel wrapper;
private Patika patika; // This is the selected patika object in here

public UpdatePatikaGUI(Patika patika) {     
    this.patika = patika;
    add(wrapper);
    setSize(300, 150);
    setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);
}
```

Since this is totally the same with the other GUI form file that we created,
I don't write the details about it here again.
After this, we can design our new GUI form file.

![Step-42](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step42.png?raw=true)

Just to be able to test this, we create a new main method into the UpdatePatikaGUI java file.
Since we need a Patika to be able to call this window, we create one in it:

```java  
public static void main(String[] args) {
    Patika p = new Patika(1, "Frontend");
    UpdatePatikaGUI up = new UpdatePatikaGUI(p);
}
```

When we run it:

![Step-43](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step43.png?raw=true)

We can change its layout in the main method:

```java  
Helper.setLayout();
```

We can set the text field of the patika name by calling the selected patika in the constructor:

```java  
fld_patikaName.setText(patika.getName());
```

And we can create a new method in the patika class for adding:

```java  
public static boolean update(int id, String name) {
    String query = "UPDATE patika SET name = ? WHERE id = ?";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setString(1, name);
        pr.setInt(2, id);
        return pr.executeUpdate() != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

Now we can create the action listener for the update button in the constructor of UpdatePatikaGUI
java file:

```java  
btn_update.addActionListener(e -> {
    if (Helper.isFieldEmpty(fld_patikaName)) {
        Helper.showMsg("fill", null);
    } else {
        if (Patika.update(patika.getId(), fld_patikaName.getText())) {
            Helper.showMsg("done", null);
        }
        dispose();
    }
});
```

And we can check whether it works :

![Step-44](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step44.png?raw=true)

Of course, we can see the result in the database, 
since we don't have a window that shows the database list for now.

![Step-45](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step45.png?raw=true)

### Transition between 2 GUI forms

First, we need to write getFetch method for Patika class.

```java  
public static Patika getFetch() {
   Patika obj = null;
   String query = "SELECT * FROM patika WHERE id = ?"
   try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
      pr.setInt(1, id);
      ResultSet rs = pr.executeQuery(query);
      if (rs.next()) {
         obj = new Patika(rs.getInt("id"), rs.getString("name"));
      }
   } catch (SQLException e) {
      System.out.println(e.getMessage());
   }
   return obj;
}
```

This method gets the data from the database and returns it in type of Patika,
as we did for User.

Right after adding the Patika Update Item into the Patika Menu in the 
OperatorGUI java file's constructor, we can add another action listener
for the update item this time.

```java  
updateMenu.addActionListener(e -> {
    int selectID = Integer.parseInt(tbl_patikaList.getValueAt(tbl_patikaList.getSelectedRow(), 0).toString());
    UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(selectID));
});
```

When we run it and click update from the menu :

![Step-46](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step46.png?raw=true)

We see the path name in the text box field. 
When we click the "Update" button :

![Step-47](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step47.png?raw=true)

The operation works.
However, we don't see the updated new list on the Operator GUI window.
As we listen to the updateMenu, we can listen to this UpdatePatikaGUI JFrame if it closes.
So we add this listener inside the updateMenu listener:

```java  
updateMenu.addActionListener(e -> {
    int selectID = Integer.parseInt(tbl_patikaList.getValueAt(tbl_patikaList.getSelectedRow(), 0).toString());
    UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(selectID));
    updateGUI.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            loadPatikaModel();
        }
    });
});
```

This added code listens to the Update window. 
When it closes, it just refreshes the Patika table.
When we test this by changing the first path into a "test" path:

![Step-48](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step48.png?raw=true)

When we click "DONE" button :

![Step-49](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step49.png?raw=true)

As you can see, we have the updated list in the window.

We remove the main method from the UpdatePatikaGUI java file,
since we don't need it to test anymore.

Before our last operation, which is to delete, we can add a functionality
that is to ask "Are you sure to delete?" window that can pop up.
We haven't yet added this functionality to userList.

```java  
// Delete button Listener
btn_userDelete.addActionListener(e -> {
    if (Helper.isFieldEmpty(fld_userID)) {
        Helper.showMsg("empty field", null);
    } else {
        if (Helper.confirm("sure")) {
            int userID = Integer.parseInt(fld_userID.getText());
            if (User.delete(userID)) {
                Helper.showMsg("done", null);
                loadUserModel();
            } else {
                Helper.showMsg("error", "Delete operation is unsuccessful");
            }
        }
    }
});
```

The only thing we added here is the if statement that has Helper.confirm() in it.
The confirm method will do the confirmation whether the operation is not buy an accident.

```java  
public static boolean confirm(String str) {
    String msg;
    switch (str) {
        case "sure":
            msg = "Are you sure for this operation?";
            break; 
        default:
            msg = str;
    }
    return JOptionPane.showConfirmDialog(null, msg, "Last Decision?", JOptionPane.YES_NO_OPTION) == 0;
}
```

Here we create a conditional switch statement for the string we want to print.
And then, we pop out another JOptionPane, which asks "Yes" or "No," if its value
is "yes" then it returns true, otherwise false.

![Step-50](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step50.png?raw=true)

Here, we tried to delete the third user, and after we click on the "delete" button, we have another
pane that pops up asking yes or no.

Now, we can deal with our last operation, which is to delete listener for the "delete menu item" 
in the patikas tab.
Firstly, we need to create the "delete" method in Patika class:

```java  
public static boolean delete(int id) {
    String query = "DELETE FROM patika WHERE id = ?";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setInt(1, id);
        return pr.executeUpdate() != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

And we call this method from the action listener in OperatorGUI java file:

```java  
deleteMenu.addActionListener(e -> {
    if(Helper.confirm("sure")) {
        int select_id = Integer.parseInt(tbl_patikaList.getValueAt(tbl_patikaList.getSelectedRow(), 0).toString());
        if (Patika.delete(select_id)) {
            Helper.showMsg("done", null);
            loadPatikaModel();
        } else {
            Helper.showMsg("error", null);
        }
    }
});
```

After we test it by deleting the third path:

![Step-51](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step51.png?raw=true)

and after we look at our new updated list:

![Step-52](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step52.png?raw=true)

we see that the operation is successful.

## Dynmaic Forms and Management of Courses

In this section, we will be managing courses in the patikas.
First, we create a new table in the database named "course."
This table has id, user_id, patika_id, name, and lang variables.
Now we need to create its Model class. 

```java  
package PatikaDev.Java102.JAVA.com.patikadev.Model;
public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika = Patika.getFetch(patika_id);
        this.educator = User.getFetch(user_id);
    }
}
```

Additionally, we create Patika and User objects, but we don't take it into the constructor.
Instead, we can use their class's getFetch methods. 
However, User class's getFetch method does not take id, it takes username.
Let's overload it:

```java  
public static User getFetch(int id) {
    User obj = null;
    String query = "SELECT * FROM user WHERE id = ?";

    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            obj = new User();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setUname(rs.getString("uname"));
            obj.setPass(rs.getString("pass"));
            obj.setType(rs.getString("type"));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return obj;
}
```

The only change is in the parameter type and at which it's been used.
We added getter & setter methods into the Course class.

Now we can prepare our GUI form :

![Step-53](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step53.png?raw=true)

Simply we create a new tab panel named as Courses.
And we create another table onto it named as tbl_courseList.
And we create Add panel right side of the tab.

Now, we can create this table in the database.
We add initially only 1 course which is Java102 with this information into the database:

* id : 1
* user_id : 3
* patika_id : 1
* name : Java102 Courses
* lang : Java

Let's get this data from OperatorGUI java file to fetch it onto the Courses tab.
First, we create fields:

```java  
private DefaultTableModel mdl_courseList;
private Object[] row_courseList;
```

Actually, we are doing the same things with the things we have done for other tabs.
This model and row list object array will be initialized in the constructor.

```java  
mdl_courseList = new DefaultTableModel();
Object[] col_courseList = {"ID", "Course Name", "Programming Language", "Patika", "Educator"};
mdl_courseList.setColumnIdentifiers(col_courseList);
row_courseList = new Object[col_courseList.length];
loadCourseModel();
tbl_courseList.setModel(mdl_courseList);
tbl_courseList.getColumnModel().getColumn(0).setMaxWidth(75);
tbl_courseList.getTableHeader().setReorderingAllowed(false);
```

Here, we initialize them with the information in the database.
And we set the table's components with these identifiers as we did before.
Here, we need to create loadCourseModel() method. 
But first, we need to create getCourseList() method in Course class.

```java  
public static ArrayList<Course> getCourseList() {
    ArrayList<Course> courseList = new ArrayList<>();
    String query = "SELECT * FROM course";
    Course obj;
    try (Statement st = DBConnector.getInstance().createStatement()) {
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            int id = rs.getInt("id");
            int user_id = rs.getInt("user_id");
            int patika_id = rs.getInt("patika_id");
            String name = rs.getString("name");
            String lang = rs.getString("lang");
            obj = new Course(id, user_id, patika_id, name, lang);
            courseList.add(obj);
        }
        rs.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());;
    }
    return courseList;
}
```

Now, we have the data as an arraylist from the database.
We can load it onto the GUI frame by loadCourseModel() method:

```java  
public void loadCourseModel() {
    DefaultTableModel clearModel = (DefaultTableModel) tbl_courseList.getModel();
    clearModel.setRowCount(0);
    int i;
    for (Course obj: Course.getCourseList()) {
        i = 0;
        row_courseList[i++] = obj.getId();
        row_courseList[i++] = obj.getName();
        row_courseList[i++] = obj.getLang();
        row_courseList[i++] = obj.getPatika().getName();
        row_courseList[i++] = obj.getEducator().getName();
        mdl_courseList.addRow(row_courseList);
    }
}
```

When we run the OperatorGUI java file again :

![Step-54](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step54.png?raw=true)

As you can see, we can get the data from the database.
On the right side, we have the "add course" panel.
However, we have 2 combo boxes for Patika and Educator.
For now, inside them are empty.
We have to get the id values from the patika and user classes.
To do that, we need a class named Item in the Helper package.

```java  
private int key;
private String value;
```

These are the fields of Item class.
And we need a toString method for this class to be able to
see names of the patikas instead of their class names in the text box.
Also, we need to write another method in OperatorGUI java file:

```java  
public void loadPatikaCombo() {
    cmb_coursePatika.removeAllItems();
    for (Patika obj : Patika.getPatikaList()) {
        cmb_coursePatika.addItem(new Item(obj.getId(), obj.getName()));
    }
}
```

Here, we set "cmb_coursePatika" field with Items 
that are actually my patikas in the database.
When we test this:

![Step-55](https://github.com/korhanertancakmak/JAVA/blob/master/src/PatikaDev/Java102/CHAPTERS/Images/CHAPTER13/Step55.png?raw=true)

We can see the patikas in the combo box of add panel's combo box.
However, if we add a new patika, we will not be able to see the reflection here.
To add this functionality, we should go to the listener of adding a new patika,
and have to call once more loadPatikaCombo method here.
Actually, we can call loadPatikaCombo method at wherever we delete and add patikas.

We need to do the same thing for educator combo box.
First, we need to create a new method:

```java  
public void loadEducatorCombo() {
    cmb_courseUser.removeAllItems();
    for (User obj : User.getUserList()) {
        if (obj.getType().equals("educator")) {
            cmb_courseUser.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
}
```

Again, we need to call this at every update we do for the user list to be able to get
the right educator list.

After we added loadEducatorCombo() method everywhere we need, 
we create add method in the Course class:

```java  
public static boolean add(int user_id, int patika_id, String name, String lang) {
    String query = "INSERT INTO course (user_id, patika_id, name, lang) VALUES (?,?,?,?)";
    try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
        pr.setInt(1, user_id);
        pr.setInt(2, patika_id);
        pr.setString(3, name);
        pr.setString(4, lang);
        return pr.executeUpdate() != -1;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
}
```

Now, we create an action listener for adding button:

```java  
btn_courseAdd.addActionListener(e -> {
    Item patikaItem = (Item) cmb_coursePatika.getSelectedItem();
    Item userItem = (Item) cmb_courseUser.getSelectedItem();
    if (Helper.isFieldEmpty(fld_courseName) || Helper.isFieldEmpty(fld_courseLang)) {
        Helper.showMsg("fill", null);
    } else {
        if (Course.add(userItem.getKey(), patikaItem.getKey(), fld_courseName.getText(), fld_courseLang.getText())) {
            Helper.showMsg("done", null);
            loadCourseModel();
            fld_courseLang.setText(null);
            fld_courseName.setText(null);
        } else {
            Helper.showMsg("error", null);
        }
    }
});
```

Lastly, we add loadEducatorCombo() method for educator label.

```java  
public void loadEducatorCombo() {
    cmb_courseUser.removeAllItems();
    for (User obj : User.getUserList()) {
        if (obj.getType().equals("educator")) {
            cmb_courseUser.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
}
```

We need to use this in every listener that we update in the users and patikas.
Because our Patika and Educator combo box must be updated afterward.

Now, we got a problem that if we add a course by assigning a patika and an educator to it,
there will be an error case when we delete that patika or educator from the database.
Because that patika or educator(user) will be deleted but that course will not be deleted with them.
And the same patika or educator will still be assigned to that course, which is a problem.
This is valid for updates, as well.
Because we can update any users or patikas, but the recorded data for courses must be updated, as well.

***NOTE***: This solution will not be included here, but you can find it in the corresponding java files.

## Login Screen and Other Requirements

We need to create a new GUI form for the login screen.
This screen should call our OperatorGUI screen :

![Step-56]()
![Step-3]()
![Step-3]()
![Step-3]()
![Step-3]()
![Step-3]()
![Step-3]()
![Step-3]()
![Step-3]()

```java  

```
```java  

```
```java  

```
```java  

```
```java  

```
```java  

```