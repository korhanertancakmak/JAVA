# [CallableStatement Challenge]()
<div align="justify">

Before I do anything else, I'll open my developer session in MySQL Workbench.
I'll select the second icon on the _toolbar_ menu 
that executes a SQL Script,
and I'll pick the `addOrder.sql` file, 
which you can download from the package folder.

~~~~sql  
USE `storefront`;
DROP procedure IF EXISTS `storefront`.`addOrder`;

DELIMITER $$
USE `storefront`$$
CREATE DEFINER=`devuser`@`localhost` PROCEDURE `addOrder`(IN orderDate DATETIME, IN orderDetails JSON, OUT orderId INT, OUT insertedRecords INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE item_description VARCHAR(255);
    DECLARE qty INT;
    SET num_items = JSON_LENGTH(orderDetails);
    
    SELECT order_id INTO orderId FROM storefront.order WHERE  order_date = orderDate;
	
    IF orderId IS NULL THEN
    
        START TRANSACTION;
        -- Insert a new order
        INSERT INTO storefront.order (order_date) VALUES (orderDate);
        
        -- Get order_id of last order inserted
        SELECT LAST_INSERT_ID() INTO orderId;
	
        -- Loop through the JSON order details Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET item_description = JSON_UNQUOTE(JSON_EXTRACT(orderDetails, CONCAT('$[', i, ']','.itemDescription')));	
            SET qty =  CAST(JSON_EXTRACT(orderDetails, CONCAT('$[', i, ']','.qty')) AS UNSIGNED);
            -- Insert a new song, track number is assigned here.
            INSERT INTO order_details (order_id, item_description, quantity) VALUES (orderId, item_description, qty); 
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END IF;
    SET insertedRecords = i;   
END$$
DELIMITER ;
~~~~

Once this script loads, I'll execute it using the lightning bolt icon.
Refreshing the schema panel, I'll see this procedure under the stored procedures' node.
You can open this up, using the tool icon, and examine the structure.

~~~~sql  
CREATE DEFINER=`devUser`@`localhost` PROCEDURE `addOrder`(IN orderDate DATETIME, IN orderDetails JSON, OUT orderId INT, OUT insertedRecords INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE item_description VARCHAR(255);
    DECLARE qty INT;
    SET num_items = JSON_LENGTH(orderDetails);
    
	SELECT order_id INTO orderId FROM storefront.order WHERE  order_date = orderDate;
	
	IF orderId IS NULL THEN
    
        START TRANSACTION;
        -- Insert a new order
        INSERT INTO storefront.order (order_date) VALUES (orderDate);
        
        -- Get order_id of last order inserted
        SELECT LAST_INSERT_ID() INTO orderId;
	
        -- Loop through the JSON order details Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET item_description = JSON_UNQUOTE(JSON_EXTRACT(orderDetails, CONCAT('$[', i, ']','.itemDescription')));	
            SET qty =  CAST(JSON_EXTRACT(orderDetails, CONCAT('$[', i, ']','.qty')) AS UNSIGNED);
            -- Insert a new song, track number is assigned here.
            INSERT INTO order_details (order_id, item_description, quantity) VALUES (orderId, item_description, qty); 
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END IF;
    SET insertedRecords = i;   
END
~~~~

You'll see that it includes two input parameters and two output parameters, 
as I described in the challenge briefing.
I won't get into how this code works,
except to say it's similar to the _addAlbum_ procedure I walked through previously.
In this case, it reads from a **Json** object this time, not just a **Json** array.
A stored procedure is a black box in most cases 
to the person writing the JDBC code, and how it works is less important
than what parameters are required.
Now that I've got the stored procedure ready to use, 
I'll get back to IntelliJ.
I'll be changing code in the **Challenge2** class.
You might remember that in this class's source file, 
I also included two records, the **Order** and the **OrderDetail** record.
First, I'll add my _JSON_ method, using the _toString_ functionality, 
to the **OrderDetail** record.

```java  
record OrderDetail(int orderDetailId, String itemDescription, int qty) {

    public OrderDetail(String itemDescription, int qty) {
        this(-1, itemDescription, qty);
    }

    public String toJSON() {
        return new StringJoiner(", ", "{", "}")
                .add("\"itemDescription\":\"" + itemDescription + "\"")
                .add("\"qty\":" + qty)
                .toString();
    }
}
```

I'll put my cursor after the constructor, but before the class's closing brace.
And I'll press the key combination, alt+insert to generate code.
From this menu, I'll select toString() 
I'll select the drop-down option on the template field.
And I'll scroll to the bottom of the list shown.
If you followed along in that earlier section, 
you'll have the **jsonBuilder** template we created back then.
I'll select that, which prompts me with another dialog.
Here, I just want to output _itemDescription_ and _quantity_, 
so I'll select those two fields.
This inserts a _toJson_ method.
I'll remove the override annotation.
It was included there, because it thinks we're overriding a _toString_ method,
but we aren't really, so I do want to remove it.
Next, I'll add a method on the **Order** record.

```java  
record Order(int orderId, String dateString, List<OrderDetail> details) {

    public Order(String dateString) {
        this(-1, dateString, new ArrayList<>());
    }

    public void addDetail(String itemDescription, int qty) {
        OrderDetail item = new OrderDetail(itemDescription, qty);
        details.add(item);
    }

    public String getDetailsJson() {
        StringJoiner jsonString = new StringJoiner(",", "[", "]");
        details.forEach((d) -> jsonString.add(d.toJSON()));
        return jsonString.toString();
    }
}
```

I'll call it _getDetailsJson_.
I'll make this method public, and have it return a string. 
Next, I'll create a **StringJoiner** variable, 
joining with a comma, and starting with a square bracket, 
and also ending with a square bracket. 
I'll loop through the order _details_, 
and use the add method on the **StringJoiner**,
adding the json string I get back, 
from calling my _toJson_ method, on each order _detail_ record.
Finally, I'll return this string.
In the _main_ method, I'll comment out the add orders call, 
which was the previous way we inserted the orders.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQL_USER"));
    dataSource.setPassword(System.getenv("MYSQL_PASS"));
    List<Order> orders = readData();

    try (Connection conn = dataSource.getConnection()) {

        //addOrders(conn, orders);
        
        orders.forEach((o) -> {
            System.out.println(o.getDetailsJson());
        });
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

After this line, I'll add code to loop through the orders.
I'll start by first just printing the json string for the order details.
I'll quickly run this to test out what my json detail strings look like.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
[{"itemDescription":"Apple", "qty":5},{"itemDescription":"Orange", "qty":2},{"itemDescription":"Banana", "qty":3},{"itemDescription":"Turkey", "qty":1},{"itemDescription":"Milk", "qty":1}]
[{"itemDescription":"Bunch Celery", "qty":1},{"itemDescription":"Onion", "qty":2},{"itemDescription":"Banana", "qty":7},{"itemDescription":"Package Ground Beef", "qty":1},{"itemDescription":"Nuttin' Honey Cereal", "qty":1}]
[{"itemDescription":"loaves of bread", "qty":3},{"itemDescription":"milk", "qty":1},{"itemDescription":"juice", "qty":2},{"itemDescription":"bag of dog food", "qty":1},{"itemDescription":"cans of cat food", "qty":10}]
[{"itemDescription":"milk", "qty":1},{"itemDescription":"dozen eggs", "qty":1},{"itemDescription":"lettuce", "qty":1},{"itemDescription":"cookies", "qty":1}]
[{"itemDescription":"milk", "qty":1},{"itemDescription":"dozen eggs", "qty":1},{"itemDescription":"lettuce", "qty":1},{"itemDescription":"cookies", "qty":1},{"itemDescription":"Apple", "qty":5},{"itemDescription":"Orange", "qty":2},{"itemDescription":"Banana", "qty":3},{"itemDescription":"Turkey", "qty":1},{"itemDescription":"Milk", "qty":1},{"itemDescription":"Bunch Celery", "qty":1},{"itemDescription":"Onion", "qty":2},{"itemDescription":"Banana", "qty":7},{"itemDescription":"Package Ground Beef", "qty":1},{"itemDescription":"Nuttin' Honey Cereal", "qty":1}]
```

The _readData_ method prints out the order details, but after that, 
you can see the json array strings that were created.
It's an array, so in square brackets, and each element in this array, 
has an item description and a quantity, so that's good.
Now, I'll set up my **CallableStatement** variable.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQL_USER"));
    dataSource.setPassword(System.getenv("MYSQL_PASS"));
    List<Order> orders = readData();

    try (Connection conn = dataSource.getConnection()) {

        //addOrders(conn, orders);

        CallableStatement cs = conn.prepareCall("{ CALL storefront.addOrder(?, ?, ?, ?) }");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("G yyyy-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
        
        orders.forEach((o) -> {
            //System.out.println(o.getDetailsJson());

            try {
                LocalDateTime localDateTime = LocalDateTime.parse("AD " + o.dateString(), formatter);
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                cs.setTimestamp(1, timestamp);
                cs.setString(2, o.getDetailsJson());
                
            } catch (Exception e) {
                System.out.printf("Problem with %s : %s%n", o.dateString(), e.getMessage());
            }
        });
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll insert this before looping through all the orders, 
because I want to reuse this callable statement for each record in my data.
I'll assign the variable, the result of calling _prepareCall_ on the **connection** object. 
Here, I'll use an escape sequence in the string I'll pass to it. 
You'll remember this is optional, but I did want to demonstrate it here.
This stored procedure has four parameters, 
so those get populated with the placeholders which are just question marks. 
I'll also set up my **DateTimeFormatter** object before the loop using the pattern I showed you.
Ok, now it's time to set up the parameters on the callable statement.
First, I'll remove the `System.out.println` statement in the for loop.
I'll start with a _try_ block next. 
This means if I get an exception on any one order, 
the code will continue to process the other orders.
I'll set up a _localDateTime_ variable, which was one of my hints. 
I'll create this by calling the _parse_ method, 
and passing the _dateString_, and the _formatter_. 
I can then get a `java.sql.Timestamp`, 
using the `Timestamp.valueOf` method, passing it the _localDateTime_. 
The first parameter in the procedure is the datetime parameter, 
but the callable statement doesn't have a _setDateTime_ method. 
Instead, we use _setTimestamp_. 
Next, I'll set the second parameter to the json string coming out of the method 
I created, on the order, so _getDetailsJson_.
I'll catch any kind of _Exception_ here, 
because I know I might have bad dates in my data. 
And if I get an exception, I'll print out that there was a problem 
with both the date and the error message.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
```

Ok, so now I've set up the input parameters,
but not the output parameters yet.
You'll remember, if I want to retrieve the data, I need to register the out parameters.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQL_USER"));
    dataSource.setPassword(System.getenv("MYSQL_PASS"));
    List<Order> orders = readData();

    try (Connection conn = dataSource.getConnection()) {

        CallableStatement cs = conn.prepareCall("{ CALL storefront.addOrder(?, ?, ?, ?) }");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        orders.forEach((o) -> {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(o.dateString(), formatter);
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                cs.setTimestamp(1, timestamp);
                cs.setString(2, o.getDetailsJson());

                cs.registerOutParameter(3, Types.INTEGER);
                cs.registerOutParameter(4, Types.INTEGER);
                cs.execute();
                System.out.printf("%d records inserted for %d (%s)%n",
                        cs.getInt(4),
                        cs.getInt(3),
                        o.dateString());
            } catch (Exception e) {
                System.out.printf("Problem with %s : %s%n", o.dateString(),
                        e.getMessage());
            }
        });
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

Both are integers, so I'll register parameter three as an Integer. 
And the same for parameter `4`.
Now, I'll _execute_ the callable statement. 
After the execution, I'll print out the number of records inserted, 
and the order id, as well as the date string, using a formatted string. 
Passing that, the number of records, which I get from parameter `4`. 
And The order id, I can get from parameter `3`. 
In both cases, I use the _getInt_ method. 
And to print the date, I'll just pass the date string on the order.
And we are done.
Before I run this, I'll go back to the MySQL Workbench session I had open, 
and delete the orders.

~~~~sql  
DELETE FROM storefront.order;
ALTER TABLE storefront.order AUTO_INCREMENT = 1;
ALTER TABLE storefront.order_details AUTO_INCREMENT = 1;
~~~~

For good measure, I'll also set the _AUTO_INCREMENT_ value back to `1` on both tables.
I'll execute these statements.
Then switching back to IntelliJ, I'll run my code.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
5 records inserted for 1 (2023-11-01 06:01:00)
5 records inserted for 2 (2023-11-01 06:02:00)
5 records inserted for 3 (2023-11-01 06:03:00)
4 records inserted for 4 (2023-11-31 05:04:00)
14 records inserted for 5 (2023-11-01 06:05:00)
```

You can see the last `5` lines of output are the result of the new code.
All five orders were inserted successfully, 
but make sure you look at the fourth statement.
Maybe you'll remember that this order has an invalid date,
and you can see it in this output _November 31_ isn't a valid date, 
so why did this even work?

~~~~sql  
SELECT * FROM storefront.order;
~~~~

I'll go back to MySQL Workbench, and query the order table, 
so select all from `storefront.order`.
If I execute that:

| order_id | order_date          |
|----------|---------------------|
| 1        | 2023-11-01 06:01:00 |
| 2        | 2023-11-01 06:02:00 |
| 3        | 2023-11-01 06:03:00 |
| 4        | 2023-11-30 05:04:00 |
| 5        | 2023-11-01 06:05:00 |

Note that the date on order id four is _November 30th;_
Not only did Java's `LocalDateTime.parse` method not throw an error, 
it actually changed the date.
This is due to a _JDK.8_ feature called a _Resolver_.
This too is a bit complicated, 
but there are three ways the _parse_ method could resolve a date, 
strict, smart, and lenient.
By default, the setting is smart, 
which means Java will adjust the date accordingly,
under certain circumstances, but not all.
The circumstances aren't perfectly straightforward, 
but here, we benefited if you think this date change was 
actually a benefit from this _smart resolver_,
because it adjusted the date to November 30, 
which you can see in this grid.
Parsing a text string occurs in two phases.

_Phase 1_ is a basic text parse, according to the fields, added to the builder.
_Phase 2_ resolves the parsed field-value pairs into date and/or time objects.
_Phase 2_ resolving can be changed from its default value of _SMART_, 
to either _STRICT_ or _LENIENT_.
I'll take a minute here, to explore this a little bit more
for those of you who are curious about this.

It may be you really don't want Java to adjust your dates smartly, 
and you want to investigate invalid dates further, 
or just log them or process them differently.
First, I'll remove the semicolon, after the of _Pattern_ method.

```java  
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
        .withResolverStyle(ResolverStyle.STRICT);
```

Now, I'll chain a method on the next line.
The method name is with _ResolverStyle,_ 
and I'll pass a value from the enum _ResolverStyle_, in this case _STRICT_.
I'll again clean out my orders in MySQL WorkBench, 
running the same three statements.
Now, back to IntelliJ, I'll re-run my code:

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
5 records inserted for 1 (2023-11-01 06:01:00)
5 records inserted for 2 (2023-11-01 06:02:00)
5 records inserted for 3 (2023-11-01 06:03:00)
Problem with 2023-11-31 05:04:00 : Text '2023-11-31 05:04:00' could not be parsed: Invalid date 'NOVEMBER 31'
14 records inserted for 4 (2023-11-01 06:05:00)
```

In this case, you can see that an exception was thrown, 
and the order with the `November 31` date wasn't inserted.
Again, this may be something you want to control,
so you might not want to use _Smart_ resolving.
If I query MySQL Workbench again.

| order_id | order_date          |
|----------|---------------------|
| 1        | 2023-11-01 06:01:00 |
| 2        | 2023-11-01 06:02:00 |
| 3        | 2023-11-01 06:03:00 |
| 4        | 2023-11-01 06:05:00 |

I will see that only `4` orders were inserted this time, 
which confirms what we saw, in the IntelliJ output.
Getting back to IntelliJ, I want to take an extra minute here, 
just to show you one more thing.

```java  
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
```

I'll change my format from `uuuu` to `yyyy`.
Going back to MySQL Workbench, I'll delete my orders.
And back to IntelliJ, I'll run the code with this one minor change.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Problem with 2023-11-01 06:01:00 : Text '2023-11-01 06:01:00' could not be parsed: Unable to obtain LocalDateTime from TemporalAccessor: {YearOfEra=2023, DayOfMonth=1, MonthOfYear=11},ISO resolved to 06:01 of type java.time.format.Parsed
Problem with 2023-11-01 06:02:00 : Text '2023-11-01 06:02:00' could not be parsed: Unable to obtain LocalDateTime from TemporalAccessor: {YearOfEra=2023, DayOfMonth=1, MonthOfYear=11},ISO resolved to 06:02 of type java.time.format.Parsed
Problem with 2023-11-01 06:03:00 : Text '2023-11-01 06:03:00' could not be parsed: Unable to obtain LocalDateTime from TemporalAccessor: {YearOfEra=2023, DayOfMonth=1, MonthOfYear=11},ISO resolved to 06:03 of type java.time.format.Parsed
Problem with 2023-11-31 05:04:00 : Text '2023-11-31 05:04:00' could not be parsed: Unable to obtain LocalDateTime from TemporalAccessor: {YearOfEra=2023, DayOfMonth=31, MonthOfYear=11},ISO resolved to 05:04 of type java.time.format.Parsed
Problem with 2023-11-01 06:05:00 : Text '2023-11-01 06:05:00' could not be parsed: Unable to obtain LocalDateTime from TemporalAccessor: {YearOfEra=2023, DayOfMonth=1, MonthOfYear=11},ISO resolved to 06:05 of type java.time.format.Parsed
```

Now, I've got an error on every order, that the string could not be parsed.
The message shows what was parsed, and unfortunately, 
gives you little of a hint about what is wrong.
As it turns out, in strict mode, if you use the `yyyy` pattern, 
you need to specify the _era_.

```java  
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("G yyyy-MM-dd HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
```

I can do this easily enough in this example, 
by adding the pattern `G`, at the start of my pattern.

```java  
//LocalDateTime localDateTime = LocalDateTime.parse(o.dateString(), formatter);
LocalDateTime localDateTime = LocalDateTime.parse("AD " + o.dateString(), formatter);
```

I also have to include the _era_, `AD` in my date string.
I'm not going to change the file, 
but I can just pre-pend `AD` to the string I pass to the _parse_ method.
I'll rerun my code.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
5 records inserted for 1 (2023-11-01 06:01:00)
5 records inserted for 2 (2023-11-01 06:02:00)
5 records inserted for 3 (2023-11-01 06:03:00)
Problem with 2023-11-31 05:04:00 : Text 'AD 2023-11-31 05:04:00' could not be parsed: Invalid date 'NOVEMBER 31'
14 records inserted for 4 (2023-11-01 06:05:00)
```

The code now acts the same as if I'd used `uuuu`.
This is really more of a cautionary tale to make sure you test your date 
parsing code thoroughly, with good and bad dates, so you know what to expect.
Smart parsing is the default, but it may not have the desired effect.
</div>
