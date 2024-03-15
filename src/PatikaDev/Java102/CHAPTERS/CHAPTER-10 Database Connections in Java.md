# Database Connections in Java

## What is Java Database Connectivity (JDBC)?

JDBC is a library that emerged to connect to databases using the Java language, run queries, 
and develop database-interactive applications. 
It is included in Java Standard Edition (JavaSE). 
Therefore, it comes ready-to-use in JDK by default.

JDBC API enables database operations using driver libraries written for each database 
management system. 
Although there are many other abstractions that can be used to communicate with the database, 
JDBC is at the core of them all. 
Therefore, it is important to learn JDBC's standard.

When you write codes that interact with the database in Java, 
the driver library enables its use without requiring any changes, 
even if it is transferred from MySQL to the Oracle database system, for example. 
Thus, you can work with any database system you want without changing the codes 
you write to perform database operations with Java. 
Providing such high abstraction increases the re-usability of Java codes. 
You can create a database connection with the JDBC API and run queries on the tables. 
You can query, update data, delete or add new records.

![JDBC Enabled Applications](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/jdbc/figures/JDBC_Architecture.jpg)

### Using JDBC in 5 steps

Database interaction with JDBC consists of roughly 5 steps.

1. We can start by registering the database driver class. 
The JDBC API must know which database driver it will work with. 
Therefore, it is necessary to specify this information when implementing the software.

    ```java  
    Class.forName("com.mysql.jdbc.Driver");
    ```

    We specify which database driver we will use with the “forName” function in the Class "class." 
    For example, we said that we would use the “MySQL” driver here.

2. A database connection is established immediately after the driver class is determined. 
We mentioned that modern database management systems consist of client-server architecture.

    ```java  
    Connection dbConnection = DriverManager.getConnection(
            "jdbc:mysql://remotemysql.com:3306/S9HHYQdP81?useSSL=false<Server name>", 
            "S9HHYQdP81<User name>", 
            "7mR2jSrEgT<password>");
    ```
    
    We open a connection to the database with the “getConnection” function in the DriverManager class. 
    We will give this function our database server address on a remote server. 
    This address can be in the form of IP and Hostname. 
    Here I say that I will connect to the MySQL server on the “remotemysql.com” server.
    Then, I provide the username and password of the user who will connect. 
    Thus, I open a connection to the database server.

3. After the connection is established, we can now run queries with the JDBC API.

    ```java  
    Statement statement = dbConnection.createStatement();
    ```
    
    The object named “dbConnection” is the connection object between us and the database server. 
    We get an object of type "Statement" from which we can prepare a query 
    using the "createStatement" function. 
    We will make our SQL query through this query.

4. Since our query object is ready, we prepare an SQL statement and 
run this query on the database server, and the query result is returned 
with an object of type "ResultSet."

    ```java  
    ResultSet resultSet = statement.executeQuery("select * from employees");   
    while(resultSet.next()) {  
        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));  
    } 
    ```
    
    We sent a simple “SELECT” query to the database server with the “executeQuery” function. 
    As a result, the records returned from the database in an object of type "ResultSet." 
    We can operate and access these records with a “while” loop. 
    Each time the “next” function is called, it returns a row of records from the result set. 
    We can access the columns on this row by index or by giving the column names directly.

5. When we are done, we close our connection with the database server.

```java  
dbConnection.close();
```

We created our database connection with an object of the “Connection” type class. 
Likewise, we close the connection with the “close” function on this object.

![JDBC Interfaces](https://raw.githubusercontent.com/Kodluyoruz/taskforce/main/java102/jdbc/figures/jdbc-interfaces.png)

## Database Connection

Whichever database you will use in your application, you need to download the 
JDBC Driver for that database.

You can download the Oracle database from [here](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html).

You can download the MySql database from [here](https://www.mysql.com/products/connector/).

The following steps are followed when creating a JDBC application:

* import "import java.sql.*" package.
* Registering drivers.
* Open connection. The "DriverManager.getConnection()" method is required, 
which provides a physical connection to the database and creates the Connection object.
* Running a query. 
To send an SQL statement to the database, an object of type Statement is required.
* Data processing based on SQL statement.
* Turning off the source in use.

```java  
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class DBConnect { 
    public static final String DB_URL = "jdbc:mysql://localhost/dbName"; 
    public static final String DB_USERNAME = "username"; 
    public static final String DB_PASSWORD = "password"; 
    public static void main(String[] args) { 
        Connection conn = null; 
        try {             
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage()); 
            System.out.println("SQLState: " + ex.getSQLState()); 
            System.out.println("VendorError: " + ex.getErrorCode());         
        }
    }
}
```

## Database Transactions and Statement Interface

We run SQL commands on database tables with the Statement interface. 
Which function we will use may vary depending on the SQL command. 
These are listed below.

1. ***public ResultSet executeQuery(String sql)***: If data are queried on the table 
with the “SELECT” SQL command, this function should be used. 
As a result of the query, an object of type “ResultSet” will be returned. 
This object will contain records returned from the queried table.
2. ***public int executeUpdate(String sql)***: DML and DDL type SQL commands can be executed. 
Commands that change table data such as INSERT, UPDATE, DELETE can be executed. 
Commands such as CREATE and DROP that cause structural changes in the table or database 
can also be run.
3. ***public boolean execute(String sql)***: If the SQL command we will run will return 
more than one result, we can use this function.

### Querying Records from the Database

With the SELECT SQL command, we could run a query on the table we had authority over 
from the database. 
Here is a small example of how to do this with the Java JDBC API. 
We will write a Java code that pulls all records from the “employees_auto_inc” table.

```java  
import java.sql.*; 
public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/school";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                System.out.println("ID : " + resultSet.getInt("student_id"));
                System.out.println("Name : " + resultSet.getString("student_fname"));
                System.out.println("Surname : " + resultSet.getString("student_lname"));
                System.out.println("Class : " + resultSet.getInt("student_class"));
                System.out.println("#################");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
```

The ResultSet interface contains certain functions to access the records received 
as a result of the query.

1. ***“next”*** : This function advances to the next line when called. 
Thus, it allows reading on that line. 
It returns "false" when there are no more records to read.
2. ***“first”*** : When this function is called, it accesses the first 
element in the query result set.
3. ***“last”*** : when this function is called, it accesses the last element in the 
query result set.
4. ***“absolute”*** : With this function, the desired element in the query result 
set is directly pointed out.

### Adding a record to the database table

```java  
import java.sql.*; 
public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/school";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            st = conn.createStatement();
            // Insert operation with Statement
            String updateQuery = "INSERT INTO student (student_fname,student_lname,student_class) " +
                    "VALUES ('Kemal' , 'Sunal' , '1')";
            st.executeUpdate(updateQuery);
            // Insert operation with PreparedStatement
            PreparedStatement pr = conn.prepareStatement(
                    "INSERT INTO student (student_fname,student_lname,student_class) " +
                            "VALUES (?,?,?)");
            pr.setString(1, "Harry");
            pr.setString(2, "Potter");
            pr.setString(3, "2");
            pr.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
```

“PreparedStatement” can be used to add a new record to the table. 
In queries created with “PreparedStatement,” the values of the parameters 
to be taken from outside are marked “?” is marked with the character. 
Like this, "?" Data can be dynamically assigned to available locations. 
The data to be replaced instead of "?" is given in order. 
Adding a record operation is done with the “executeUpdate” method.

### Update record in database table

```java  
import java.sql.*; 
public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/school";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            st = conn.createStatement();
            // Update operation with Statement
            String updateQuery = "UPDATE student SET student_class = '1'  " +
                    "WHERE student_id = 3";
            st.executeUpdate(updateQuery);
            // Update operation with PreparedStatement
            PreparedStatement pr = conn.prepareStatement("UPDATE student SET student_class = ?  " +
                    "WHERE student_id = ?");
            pr.setString(1, "6");
            pr.setInt(2, 2);
            pr.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
```

The Java codes used in record updating are the same as adding records.

### Delete record in database table

```java  
import java.sql.*; 
public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/school";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            st = conn.createStatement();
            // Delete operation with Statement
            String updateQuery = "DELETE FROM student " +
                    "WHERE student_id = 1";
            st.executeUpdate(updateQuery);
            // Delete operation with PreparedStatement
            PreparedStatement pr = conn.prepareStatement("DELETE FROM student " +
                    "WHERE student_id = ?");
            pr.setInt(1, 2);
            pr.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
```

The Java codes for deleting a record are the same as adding and updating a record.

## Transaction Method with JDBC

The applications we write can sometimes operate on one or more tables with a transaction call, 
and may require running one or more queries sequentially. 
In these cases, a query may fail at some point in the process as successive operations occur. 
In these cases, we may want to undo other transactions in the process. 
In these cases, going to the tables and reversing the operations we have done will be quite 
challenging and complicated.

If we want to explain this with a diagram

![Transaction Method](https://i.ibb.co/PZm27hg/Transaction-Method1.png)

Let's say we have a process like. 
In other words, the user will press a button and these operations will occur sequentially.

![Transaction Method 2](https://i.ibb.co/hyNtFpk/Transaction-Method2.png)

As seen in the diagram, an error occurred in Query 3 for some reason. 
In this case, we need to roll back the transaction to ensure the integrity and accuracy of the data. 
To do this, JDBC provides us with some functions.

In databases, if a database transaction is successful, it is "committed," otherwise it is "rolled back." 
When "committed," the changes are sent to the database permanently. 
If "rollback" is performed, all changes made so far are undone.

```java  
import java.sql.*; 
public class DBConnect { 
	public static final String DB_URL = "jdbc:mysql://localhost/school"; 
	public static final String DB_USERNAME = "root"; 
	public static final String DB_PASSWORD = ""; 
    public static void main(String[] args) { 
	    Connection conn = null; 
	    try {       
		    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(false); 
            // Insert Operation with PreparedStatement 
    	    PreparedStatement pr = conn.prepareStatement(
                    "INSERT INTO student (student_fname,student_lname,student_class) " +
                            "VALUES (?,?,?)"); 
	        pr.setString(1, "Harry"); 
	        pr.setString(2, "Potter");
	        pr.setString(3, "2");       
	        pr.executeUpdate();
            if (1 == 1) { 
		        throw new SQLException();
            }
            // Insert Operation with PreparedStatement 
	        pr.setString(1, "Ron"); 
	        pr.setString(2, "Weasley"); 
	        pr.setString(3, "1");       
	        pr.executeUpdate();
	        conn.commit();
	        conn.close();
        } catch (SQLException ex) {
    	    try {
	            if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("SQLException: " + ex.getMessage()); 
        	System.out.println("SQLState: " + ex.getSQLState()); 
	        System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
```

Transactions are automatically committed in “JDBC” database connections. 
Automatic commit process can be turned off with the “setAutoCommit” function. 
Thus, the software developer must undertake Transaction management.

In the example above, we took over the Transaction management by setting false. 
We can permanently send the changes by calling the “commit()” function.

We symbolically added code that throws an error into an if block. 
When we open the part that throws that error, we call the "rollback" function 
because an error is received even though the command to add a record to the 
database has been executed. 
Thus, we ensure that the changes made so far are undone.

### Test

1. What are the main components of JDBC?

    a. DriverManager, Driver, Connection, Statement, and ResultSet  
    b. DriverManager, Driver, Connection, and Statement  
    c. DriverManager, Statement, and ResultSet  
    d. DriverManager, Connection, Statement, and ResultSet  

2. Which of the following is not one of the steps of running a query in the database?

   a. Saving driver class  
   b. Getting a connection  
   c. Creating a statement  
   d. Making rollback  
   e. Closing the connection  

3. Which of the following is one of the advantages of using PreparedStatement in Java?

   a. Better performance  
   b. Promotes SQL injection  
   c. Prevents SQL injection  
   d. More memory usage  

4. Which one is used to create parameterized queries?

   a. ParameterizedStatement  
   b. PreparedStatement  
   c. CallableStatement and ParameterizedStatement  
   d. Statements  

5. With which function can we ensure that the transaction is recorded and applied in the 
tables when the connection feature in JDBC, which allows automatic recording of transactions, 
is disabled?

   a. accept()  
   b. acceptAll()  
   c. apply()  
   d. commit()  
   e. make()  

6. Which feature in JDBC controls the automatic recording of connection operations and their 
application to tables?

   a. autoAccept  
   b. acceptMod  
   c. autoCommit  
   d. applyMod  
   e. makeMod

Answers: 1.a, 2.c, 3.c, 4.b, 5.d, 6.c
