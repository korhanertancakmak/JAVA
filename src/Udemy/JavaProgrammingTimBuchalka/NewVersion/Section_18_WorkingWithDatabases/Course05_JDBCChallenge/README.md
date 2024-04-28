# [JDBC Challenge]()
<div align="justify">

I'll leave the work bench open, coming back to it,
to verify the data as I run my code in Java.
I'll get back to IntelliJ.
I've got the same java file where we left off in the last section.
I'll create a new method, called _addOrder_.

```java  
private static int addOrder(Connection conn, String[] items) {

        int orderId = -1;
        String insertOrder = "INSERT INTO storefront.order (order_date) VALUES ('%s')";
        String insertDetail = "INSERT INTO storefront.order_details " + "(order_id, item_description) values(%d, %s)";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String orderDateTime = LocalDateTime.now().format(dtf);
        System.out.println(orderDateTime);
        String formattedString = insertOrder.formatted(orderDateTime);
        System.out.println(formattedString);

        String insertOrderAlternative = "INSERT INTO storefront.order (order_date) " + "VALUES ('%1$tF %1$tT')";
        System.out.println(insertOrderAlternative.formatted(LocalDateTime.now()));
        return orderId;
    }
```

I'll make this private, static, and it returns an int, 
which will be the auto incremented id I get back from MySQL.
This method will take a connection, and a String array, 
which will be the item descriptions, of the order details.
I'll start by declaring an order id variable, and initialize that to `-1`. 
I'll set up my insert SQL statement, which is `INSERT INTO storefront.order` 
followed by a list of column names in parents. 
In this case, I only need _order_date_.
After that, we specify _VALUES_, and another set of parentheses. 
I'll surround my format string specifier with single quotes. 
The insert for the `storefront.order_details` starts out similarly,
so insert into `storefront.order_details` in this case. 
I'll define two columns, _order_id_, and item description, 
and two specifiers in this string, an integer and a string.
For the string, I'll use _enquoteLiteral_ in my code that formats it, 
so I'll leave out the single quotes here.
I'll be returning _orderId_ from this method.
Next, I want to get the _order_date_. 
I'll start with a _DateTimeFormatter_ object. 
I can create a _Pattern_, 
and I'll use the one I showed you on the challenge briefing. 
I'll get the current date and time, using `LocalDateTime.now`,
and I'll chain the format method to that, 
passing it my date time formatter variable. 
I'll print that out.
I'll use this string to populate the insert order statement. 
I'll print that out too.
Now, I could have used date time specifiers in my formatted string, 
as an alternative to using the _DateTimeFormatter_.
In fact, let me review what that would look like.
The insert starts out the same way.
But the specifiers are a lot more cryptic.
One `$` sign in both cases means 
I can just pass a single date time variable for both of these values.
The `tF` will print the date in the format `yyyy-mm-dd`.
In the second specifier `tT` is the key 
to the time being printed in the format we want.
I'll print this out _usingLocalDateTime_ now as the argument.
So this is an alternative.
I'll run this code, since nothing will get inserted yet, 
so you can see what this output looks like right now.
I'll jump back up to the _main_ method,
and make a call to my new _addOrder_ method.

```java  
public static void main(String[] args) {
    
    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));
   
    try (Connection conn = dataSource.getConnection()) {
   
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData.getSQLStateType());
        if (!checkSchema(conn)) {
            System.out.println("storefront schema does not exist");
            setUpSchema(conn);
        }
   
        int newOrder = addOrder(conn, new String[]{"shoes", "shirt", "socks"});
        System.out.println("New Order = " + newOrder);
   
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll pass that the connection, and an array of strings, 
and I'll just use clothing in my _storefront_ order, 
so my items are _shoes_, _shirt_, and _socks_. 
I'll assign the id I get back to the _newOrder_ variable. 
And I'll print the _newOrder_ id out.
I'll run this:

```html  
2
2024-04-28 05:40:55
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 05:40:55')
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 05:40:55')
New Order = -1
```

You can see the formatted date, using date time formatter, which gave us a string.
I used that string as an argument to the `%s` specifier, 
in my first insert order string.
In the second example, I used date time specifiers instead or `%t`,
and you can see both give me the same result.
Now let's actually use this insert method.
I'll get back to the _addOrder_ method,
and set up my _try-with-resources_ block.

```java  
//private static int addOrder(Connection conn, String[] items) {
private static int addOrder(Connection conn, String[] items) throws SQLException {

    int orderId = -1;
    String insertOrder = "INSERT INTO storefront.order (order_date) VALUES ('%s')";
    String insertDetail = "INSERT INTO storefront.order_details " + "(order_id, item_description) values(%d, %s)";

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String orderDateTime = LocalDateTime.now().format(dtf);
    System.out.println(orderDateTime);
    String formattedString = insertOrder.formatted(orderDateTime);
    System.out.println(formattedString);

    String insertOrderAlternative = "INSERT INTO storefront.order (order_date) " + "VALUES ('%1$tF %1$tT')";
    System.out.println(insertOrderAlternative.formatted(LocalDateTime.now()));

    try (Statement statement = conn.createStatement()) {
        
        conn.setAutoCommit(false);
        int inserts = statement.executeUpdate(formattedString, Statement.RETURN_GENERATED_KEYS);

        if (inserts == 1) {
            var rs = statement.getGeneratedKeys();          // ResultSet
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        }

        int count = 0;
        for (var item : items) {
            formattedString = insertDetail.formatted(orderId, statement.enquoteLiteral(item));
            inserts = statement.executeUpdate(formattedString);
            count += inserts;
        }

        if (count != items.length) {
            orderId = -1;
            System.out.println("Number of records inserted doesn't equal items received");
            conn.rollback();
        } else {
            conn.commit();
        }
        conn.setAutoCommit(true);
    } catch (SQLException e) {
        conn.rollback();
        throw new RuntimeException(e);
    }
    return orderId;
}
```

I'll wrap a new statement in this _try_. 
Since we need a **transaction**, I'll set _autocommit_ to **false**. 
I'll set up a variable, _inserts_, then call _executeUpdate_,
passing that my _formattedString_, 
and since I want the generated key back, 
I can pass `Statement.RETURN_GENERATED_KEYS` as the second argument. 
I'll call _commit_ after this. 
And then set _autocommit_ to **true**. 
In the _catch_ clause, If I get an exception, 
I want to roll the transaction back.
Then I'll throw a _RuntimeException_.
Notice that I've got an error on rollback,
and that's because that throws an _SQLException_ too, 
and I need to handle it.
I'll hover over that and select,
add exception to method signature.
I'm not done yet, because I still have to insert the order detail records.
If I get a one back from inserting the order,
I know my order was added successfully,
and only in this case, do I want to proceed. 
So next, I need to get the generated key back 
from the insert order statement, 
and don't forget that's retrieved by calling _RETURN_GENERATED_KEYS_. 
That returns a _resultSet_.
The first record in that _resultSet_ should have the key I want. 
The key will be at index 1. 
I'll set up another local variable, _count_, 
which I'll use to test how many detail items get inserted. 
I'll loop through the items passed to this method. 
I'll format my string, using the _orderId_ as the first argument.
Here, I'll enquote the item string, using `statement.enquoteLiteral`.
I'll call `statement.executeUpdate`, with this _formatted_ string,
returning the result to my _inserts_ variable. 
I'll add the value in inserts to my count variable. 
If we get an exception saving the records, the data will be rolled back, 
and a runtime exception thrown. 
But some database problems are silent,
meaning we may not get an exception, 
but something may not go as we expected, 
because of problems with the logic in our queries. 
I'll add a test, in this code, to make sure the results are 
what I expected them to be. 
I'll check if the value in _count_, or the records inserted, 
is the same as the number of items passed to this method. 
If it's not, I'll set _orderId_ back to `-1`. 
And I'll print out the issue.
I'll roll back the _transaction_ if this happens. 
I'll wrap an _else_ statement around the _commit_ statement. 
Ok, time to test this out, so I'll run this code:

```html  
2
2024-04-28 07:09:58
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 07:09:58')
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 07:09:58')
New Order = 1
```

I see the two insert strings again,
but now I see new order equals `1`.
That's a good sign.
I'll open up MySQL Workbench.
I'll click on the _order_ table, 
and select the grid or spreadsheet like icon.

![image52](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image52.png?raw=true)

This will open an SQL editor pane showing an executed select statement,
with a result grid below, so you should see order 1, with the order date listed there.
I'll do the same for _order details_, clicking the table like icon there.

![image53](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image53.png?raw=true)

Here I can see all three of the order details
listed, associated with _order 1_, so that's it.
Hopefully, you were able to do that, 
and you figured out how to persist some data of your own.
The second part of the challenge was to delete an order, 
which includes its line items.
Getting back to IntelliJ, I'll add a new method to do this, 
and I'll insert this method after the _addOrder_ method.

```java  
private static void deleteOrder(Connection conn, int orderId) throws SQLException {

    String deleteOrder = "DELETE FROM storefront.order where order_id=%d".formatted(orderId);
    String deleteQuery = deleteOrder.formatted(orderId);

    try (Statement statement = conn.createStatement()) {
        int deletedRecords = statement.executeUpdate(deleteQuery);
        System.out.printf("%d child records deleted%n", deletedRecords);
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } 
}
```

This will be private, static, void, and called _deleteOrder_.
It'll take a _connection_, and the _orderId_, of the order to be deleted.
I'll start with a _formatted_ string, 
so `DELETE FROM %s where order_id=%d`. 
I'll format that, using the _orderId_.
I'll get a new statement in a _try-with-resources_ block.
I'll set up a variable called _deleteRecords_, 
and assign that the value of _executeUpdate_.
I'll pass that, my _deleteQuery_ string. 
I'll print how many records got deleted.
That should be one in every case. 
If I get an exception, I'll throw a _RuntimeException_.
Getting back to the _main_ method:

```java  
public static void main(String[] args) {
    
    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));
   
    try (Connection conn = dataSource.getConnection()) {
   
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData.getSQLStateType());
        if (!checkSchema(conn)) {
            System.out.println("storefront schema does not exist");
            setUpSchema(conn);
        }

        deleteOrder(conn, 1);
        //int newOrder = addOrder(conn, new String[]{"shoes", "shirt", "socks"});
        //System.out.println("New Order = " + newOrder);
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll comment out the add order code, and the _println_ below that.
I'll make a call to the _delete_ method before that code. 
I want to delete order id 1, in this case.
I'll run this code:

```html  
2
1 child records deleted
```

The output says one record was deleted.
I'll verify the results in MySQL WorkBench by re-executing the select statements.
First in _Order_ and the result grid has no data.
I'll do the same on _Order details_.
I can see no longer have either the order or the order detail records.
Because of the _cascade delete_ defined in the order detail table, 
I didn't have to include the delete _order details_ code in Java.
This was optional for this scenario.
We could include it in a transaction.
This is a valid solution as well.
I'll change my code a little, to do it that way.
First, I'll uncomment the code that creates a new order:

```java  
deleteOrder(conn, 1);
int newOrder = addOrder(conn, new String[]{"shoes", "shirt", "socks"});
System.out.println("New Order = " + newOrder);
```

And re-run my code, so I have an order to delete.

```html  
2
0 child records deleted
2024-04-28 07:27:29
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 07:27:29')
INSERT INTO storefront.order (order_date) VALUES ('2024-04-28 07:27:29')
New Order = 2
```

This will set up order number `2`, as you can see.
I'll change the number from `1` to `2` in the _deleteOrder_.

```java  
deleteOrder(conn, 2);
```

Next, I'll start by adding the transaction code to my _delete_ method.

```java  
private static void deleteOrder(Connection conn, int orderId) throws SQLException {

    //String deleteOrder = "DELETE FROM storefront.order where order_id=%d".formatted(orderId);
    String deleteOrder = "DELETE FROM %s where order_id=%d";
    //String deleteQuery = deleteOrder.formatted(orderId);
    String parentQuery = deleteOrder.formatted("storefront.order", orderId);
    String childQuery = deleteOrder.formatted("storefront.order_details", orderId);

    try (Statement statement = conn.createStatement()) {
        conn.setAutoCommit(false);
        //int deletedRecords = statement.executeUpdate(deleteQuery);
        int deletedRecords = statement.executeUpdate(childQuery);
        System.out.printf("%d child records deleted%n", deletedRecords);
        deletedRecords = statement.executeUpdate(parentQuery);

        if (deletedRecords == 1) {
            conn.commit();
            System.out.printf("Order %d was successfully deleted%n", orderId);
        } else {
            conn.rollback();
        }
        
    } catch (SQLException e) {
        conn.rollback();
        throw new RuntimeException(e);
    } finally {
        conn.setAutoCommit(true);
    }
}
```

So first, I'll set _autocommit_ to **false**.
I'll call _commit_ as the last statement in the _try_ block.
I'll execute a _rollback_ if I get an exception.
I'll again over hover the error I get on _rollback_,
and select to have the _SQLException_ added to the methods' throw clause.
I'll add a _finally_ clause in this case, 
because I am throwing an exception in the catch clause.
In either case, I want to set _autocommit_ back to **true**.
Putting that in a _finally_ clause ensures it always happens.
Now, I'm going to make the _deleteOrder_ string more generic, 
and put a placeholder in for the table name.
I'll change the name of the variable _deleteQuery_ to _parentQuery_.
Then, I'll pass the table name, `storefront.order`, as the first argument here.
Next, I'll create a _childQuery_, using the same formattable string,
this time passing the _order_details_ table, 
and I'll still delete by _orderId_. 
Remember, the child table has this id in it as a foreign key.
I'll change the name of the query in the executeUpdate to be _childQuery_.
I'll change my print statement to say how many child records were printed.
Now, I just need to add the code to delete the parent record.
I'll _executeUpdate_ on the _parentQuery_ 
and return results to the same variable, _deleteRecords_.
Again, it's possible something could go a miss, and not throw an exception, 
so I want to confirm one and only one order is deleted in this case.
I'll print that _my order was successfully deleted_.
I'll include an _else_ statement, and rollback if my result wasn't one. 
I probably should have a statement printed here too, but you get the gist.
Before I run this, I'll comment out the code that adds a new order.

```java  
public static void main(String[] args) {
    
    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));
   
    try (Connection conn = dataSource.getConnection()) {
   
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData.getSQLStateType());
        if (!checkSchema(conn)) {
            System.out.println("storefront schema does not exist");
            setUpSchema(conn);
        }

        deleteOrder(conn, 2);
        //int newOrder = addOrder(conn, new String[]{"shoes", "shirt", "socks"});
        //System.out.println("New Order = " + newOrder);
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

Ok, I'll run this.

```html  
2
3 child records deleted
Order 2 was successfully deleted
```

I'll see that three child records were deleted,
and then _order 2 was successfully deleted_.
I'll pop back over to MySQL Workbench, 
and re-execute the queries that were generated
when I displayed the table results. 
These are currently showing as no data from last time.
Remember, we have created records again and deleted them 
before coming back here.
I'll start with Order, and be running that select again. 
In the result grid below, I should have no data again, 
so I know this code worked.
I'll do the same for order_details, 
opening that tab, and executing that select query again.
And I get no data, so that's good.
So Should you manually delete child records in
your Java code, or depend on cascade deletion?
The answer to that question is as usual, it depends.
If you're trying to be database agnostic,
meaning your application is going to be deployed
in many environments with different databases.
In this scenario, you will probably be working 
with varying degrees of database support,
so then maybe you'd manually code the _deleteChild_ records, 
and you'd have confidence your code is always cleaning up the child records,
regardless of how the database is configured.
Doing this might be simpler than having to publish requirements, 
on how the database needs to be set up, with cascade deletes implemented.
If you're deleting a large amount of data,
it's probably more efficient to take advantage of the table's _cascade deletes_,
done on the database server's implementation.
All the major RDBMS vendors support cascade deletes, 
so that's a positive, but there may be some vendors 
or lightweight RDBMS's that don't.
Also, remember your JDBC backend could even be files, 
like csv or json files, so in that case, 
you'd want to implement the _delete order_detail_ functionality yourself.
In the _delete order_detail_ code, you can see I parameterized the SQL statement,
with the item description.
Ultimately, though, I passed a static string to the _executeUpdate_ method.
</div>