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

![Step-1](https://i.ibb.co/jVG6gmX/Ekran-g-r-nt-s-2024-03-14-102202.png)

After this, we will have here:

![Step-2](https://i.ibb.co/MsF08HV/Ekran-g-r-nt-s-2024-03-14-102939.png)

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

![Step-3](https://i.ibb.co/WDh433m/Step3.png)

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

![Step-4](https://i.ibb.co/QMX3GgQ/Step4.png)

### Designing GUI

Here, we created our first app. 
We can change background by clicking background on the property section,
and then choosing the color and copying it on the JFrame:

![Step-5](https://i.ibb.co/44FXRWx/Step5.png)

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

![Step-6](https://i.ibb.co/3Tf8RwG/Step6.png)

We can add any texts by adding JLabel component.

![Step-7](https://i.ibb.co/s6J41bk/Step7.png)

Also, we can change the text from the text on property section.
Automatically, we have a "vertical spacer" component in the first JPanel, now.
Because if we don't have it, our JLabel will take place 
at the center of the upper JPanel which wraps the JLabel.

We can also change the alignments of any components.
From the property section, we change horizontal align of the wTop JPanel as "Center" 
for the text can take place at the center of the frame.
We can change its Font from font property.
Let's choose it as Arial, its Style as "Bold.", and its Size as "20."

![Step-8](https://i.ibb.co/ncb4kzQ/Step8.png)

When we run the app :

![Step-9](https://i.ibb.co/129WLtm/Step9.png)

We can add another JLabel for texting as "User Login."
First, we remove the vertical spacer. 
And then, align the grid with parent. 
And then, choose vertical align of two JLabels in wTop as "Top."
And then, choose horizontal align of them as "Center."

![Step-10](https://i.ibb.co/PcNNRzV/Step10.png)

We can also play with margins for this top wrapper by 10 into
the first element of margins[topMarg,leftMarg,bottomMarg,rightMarg].
This is also a method to get to center the label by margins.
And we run it again:

![Step-11](https://i.ibb.co/Jq3ssgR/Step11.png)

Next, we will play with the bottom wrapper.
First, we add a JLabel on it with a vertical spacer named username,
and right below this JLabel we add a JTextField; that will be used
for the input of the username, named "fld_username."
Second, we add another JLabel on it with a vertical spacer named password,
and right below this JLabel we add a JPasswordField; that will be used
for the input of the user password, named "fld_password."
And lastly, we add a JButton at bottom.
When we run this:

![Step-12](https://i.ibb.co/GsnX59D/Step12.png)

All panels in wBottom can be aligned to be at center,
and this can look like better than now it is.
We can do this by adding two HSpacer on the two sides of
JPanels in the wBottom. 
And now we run it again:

![Step-13](https://i.ibb.co/Vxv6wdW/Step13.png)

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

![Step-14](https://i.ibb.co/60Gq2qS/Step14.png)

Now, we have a better button theme.

### Entering Inputs on the GUI Frame(Listeners)

How can we do if a user enters their information and click on the button?
All the components in our Palette have their specified listeners.
If we right-click on the button object, we can choose "Create Listener."
When we click on it, all available listeners will be listed on the screen.

![Step-15](https://i.ibb.co/ySy8599/Step15.png)

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

![Step-16](https://i.ibb.co/F7nPwNn/Step16.png)

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

![Step-17](https://i.ibb.co/2PXJdwv/Step17.png)

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

![Step-18](https://i.ibb.co/25GtN7G/Step18.png)

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

![Step-19](https://i.ibb.co/SRLFRCC/Step19.png)

So now, we can list our users.
To be able to do this, we have to add a user to the database,
and then we have to look for the output of them.
Firstly, we need to connect to the database.

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