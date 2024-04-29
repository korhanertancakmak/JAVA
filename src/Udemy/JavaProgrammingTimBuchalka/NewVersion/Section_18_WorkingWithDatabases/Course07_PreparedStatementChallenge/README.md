# [PreparedStatement Challenge]()
<div align="justify">

I've created a new class, which I've called **Challenge2**.
I'll start by copying the _main_ method,
from the **Main** class in the previous section,
pasting that in my new class.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3306);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));

    try (Connection conn = dataSource.getConnection()) {
        
/*
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData.getSQLStateType());
        if (!checkSchema(conn)) {
            System.out.println("storefront schema does not exist");
            setUpSchema(conn);
        }

        deleteOrder(conn, 2);
        //int newOrder = addOrder(conn, new String[]{"shoes", "shirt", "socks"});
        //System.out.println("New Order = " + newOrder);
*/
        
        String alterString = "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
        Statement statement = conn.createStatement();
        statement.execute(alterString);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll remove all the code in the try block.
Next, I'll set up an `ALTER TABLE` statement.
Hopefully you were able to research that,
and found that the way to change a table, in the database 
is to use the `ALTER` DDL Statement.
In this case, I'll alter `storefront.order_details`,
and `ADD COLUMN quantity INT`. 
There are options you can include for the column, 
but let's keep this simple. 
Next, I'll get a statement, and call execute, 
passing the alter string.
So you might be asking why I didn't use a **PreparedStatement** here.
In general, you don't use **PreparedStatements** 
for _Data Definition Language or DDL_ statements in Java.
**PreparedStatements** are typically used to execute 
_Data Manipulation Language or DML_ statements,
where the same SQL statement may be executed multiple times 
with different parameter values.
DDL statements, on the other hand, like this one, 
wouldn't be run twice, and you wouldn't get
the benefit of the precompiled statement.
If you haven't already done so,
remember to set your environment variables
for the _MYSQL_USER_ and _MYSQL_Password_,
in the run configuration for this class.
Let's run this:

```html  
Process finished with exit code 0
```

Now, there's no output, but we don't get an exception either.
I'll open MySQL Workbench, and choose the _development_ session.

![image65](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image65.png?raw=true)

In the _schemas_ panel, I'll expand the _storefront_ database, 
then _tables_, and highlight _order_details_.
This time I'll select the _info icon_, and then select the _columns_ tab.
Here you can see that _quantity_ is now a column in this table.
I'll go back to my Java code.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3306);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));

    try (Connection conn = dataSource.getConnection()) {
/*
        String alterString = "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
        Statement statement = conn.createStatement();
        statement.execute(alterString);
*/
        
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

We only have to do this once, so I'll now comment out this code.
So next, I'll write the code to read the data from the file 
that has the order data.
Let me open this file a minute.

```html  
order,2023-11-01 06:01:00
item,5,Apple
item,2,Orange
item,3,Banana
item,1,Turkey
item,1,Milk
order,2023-11-01 06:02:00
item,1,Bunch Celery
item,2,Onion
item,7,Banana
item,1,Package Ground Beef
item,1,Nuttin' Honey Cereal
order,2023-11-01 06:03:00
item,3,loaves of bread
item,1,milk
item,2,juice
item,1,bag of dog food
item,10,cans of cat food
order,2023-11-31 05:04:00
item,1,milk
item,1,dozen eggs
item,1,lettuce
item,1,cookies
order,2023-11-01 06:05:00
item,1,milk
item,1,dozen eggs
item,1,lettuce
item,1,cookies
item,5,Apple
item,2,Orange
item,3,Banana
item,1,Turkey
item,1,Milk
item,1,Bunch Celery
item,2,Onion
item,7,Banana
item,1,Package Ground Beef
item,1,Nuttin' Honey Cereal
```

In this file, the order data has the keyword **order** in the first column, 
whereas the details have the keyword **item** in that column.
Notice that the order records have dates already formatted 
the way we want them to be.
Also notice, the time changes by 1 minute,
you can use this to recognize the orders.
I'll use a different approach in this code,
just to show you an alternative.
First, I'll set up two records, one for **Order**,
and one for **OrderDetails**, in the `Challenge2.java` source file.
I'll start with the **OrderDetail**.

```java  
record OrderDetail(int orderDetailId, String itemDescription, int qty) {

    public OrderDetail(String itemDescription, int qty) {
        this(-1, itemDescription, qty);
    }
}

record Order(int orderId, String dateString, List<OrderDetail> details) {

    public Order(String dateString) {
        this(-1, dateString, new ArrayList<>());
    }

    public void addDetail(String itemDescription, int qty) {
        OrderDetail item = new OrderDetail(itemDescription, qty);
        details.add(item);
    }
}

public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3306);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));

    try (Connection conn = dataSource.getConnection()) {
/*
        String alterString = "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
        Statement statement = conn.createStatement();
        statement.execute(alterString);
*/
        
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

This record has three fields, _orderDetailId_, an **int**, 
the _itemDescription_, a **string**, and _quantity_, an **int**. 
I'll create a custom constructor for this, 
because I won't have an _orderId_, 
as I'm reading the data in from the file. 
So I want a constructor with just _itemDescription_ and _quantity_. 
And this has to call the canonical constructor, 
so I'll pass `-1` as the ID.
Next, I'll create a record for the order.
This will have _orderID_, date string, and list of order details. 
And this one will have a custom constructor too, 
for just the date string. 
So when I call the canonical constructor,
I'll pass `-1` for the _orderID_, 
and I'll create a new _ArrayList_ for the details here.
The only other thing I'll add is an _addDetail_ method, 
that'll take an _itemDescription_, and a _quantity_. 
I'll create an instance of the _OrderDetail_, 
pass that the description, and the quantity.
And I'll add that to the _details_ array.
Now that I have types to put the data in,
I'll write the _readData_ method.

```java  
private static List<Order> readData() {

    List<Order> vals = new ArrayList<>();

    String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course07_PreparedStatementChallenge/Orders.csv";
    try (Scanner scanner = new Scanner(Path.of(pathName))) {

        scanner.useDelimiter("[,\\n]");
        var list = scanner.tokens().map(String::trim).toList();

        for (int i = 0; i < list.size(); i++) {

            String value = list.get(i);
            if (value.equals("order")) {
                var date = list.get(++i);
                vals.add(new Order(date));
            } else if (value.equals("item")) {
                var qty = Integer.parseInt(list.get(++i));
                var description = list.get(++i);
                Order order = vals.get(vals.size() - 1);
                order.addDetail(description, qty);
            }
        }
        vals.forEach(System.out::println);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return vals;
}
```

This will return a list of orders.
I'll initialize an array list of orders to start with. 
This time, I'll use a **Scanner**. 
If you used **Files** or some other method, that's fine. 
I'm really choosing this method just to remind you
that you can use **scanner** to read data from a file. 
I'm putting this in a _try-with-resources_ block.
Unlike `Files.readLines`, the _scanner_ won't get closed automatically, 
so if I put it in this type of _try_ statement, 
I don't have to remember to close it. 
I'll set the delimiter to be either a comma, or a new line. 
This means, instead of splitting the data by line, 
it's going to split the data by commas. 
I'll create a list of strings by calling `scanner.tokens`. 
That gives me a stream of strings, 
so I'll use _map_ to _trim_ each value,
then terminate the stream, collecting it into a list.
I'll print each value in my _Order_ list.
And I'll return my order list from this method.
This won't compile, so I'll use IntelliJ to add a _catch_ clause.
I'm not done yet, 
because I have to create the _Order_, and _OrderDetails_.
I'll loop through every column value, one at a time.
I'll get the column value from the list.
If that token has the value order,
then I know I need to set up an order.
The date is the next place in the list, 
so I'll do a pre-increment, then get the next value.
I'll create and _Order_, and add it to the order list.
Similarly, I'll check for the keyword item,
which indicates the tokens that follow are item fields.
I'll parse the next field, the quantity. 
Now, in a normal world, you'd want to do more validation,
but again, I'll keep this simple. 
The item description is next. 
I can get the order that goes with the details, 
by just getting the last order added.
And I'll add the detail to that order.
I'll test this code out by calling this method.
I'll insert a call to this method before I open a database connection.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQL_USER"));
    dataSource.setPassword(System.getenv("MYSQL_PASS"));

    List<Order> orders = readData();
    
    try (Connection conn = dataSource.getConnection()) {
        addOrders(conn, orders);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
```

So first, I'll create a **List** variable, called orders, 
and assign that the value of _readData_.
I'll run this now, to make sure I get each order, as a coherent unit.

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
```

And you can see, this code has `5` orders, 
and all the information is stored now in this array of _Orders_.
Next, I'll create the _addOrder_ method. 

```java  
private static void addOrder(Connection conn, PreparedStatement psOrder, PreparedStatement psDetail, Order order)
        throws SQLException {

    try {
        conn.setAutoCommit(false);
        
        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}
```

This method will take a _connection_, two different **PreparedStatement**, and an _order_.
I'll set up the prepared statements outside of this method, 
so they can be reused.
This will throw an _SQLException_. 
Here, I'll use a regular _try_, 
because I don't want to close any resources if this code fails. 
Instead, I'll be ignoring the error, 
and continuing to process other orders. 
I do want the code in this method to be part of a single transaction,
so that starts by setting _autocommit_ to **false**.
I'll leave some space for the code here. 
I'll end the _try_ block with a _commit_ statement. 
I'll catch the _SQLException_, and _rollback_ any changes,
if I do get one. 
Then, I'll rethrow the exception.
And I do want a _finally_ clause, 
because I want to be sure _autocommit_ is set to **true** in any case.
That's the setup for a transaction.

```java  
private static void addOrder(Connection conn, PreparedStatement psOrder, PreparedStatement psDetail, Order order)
        throws SQLException {

    try {
        conn.setAutoCommit(false);
        int orderId = -1;
        psOrder.setString(1, order.dateString());
        if (psOrder.executeUpdate() == 1) {
            var rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
                System.out.println("orderId = " + orderId);
                
            }
        }
        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}
```

Between the first _autocommit_ and the _commit_ method, 
I need jdbc code, so I'll start by initializing an _orderID_ variable, to minus 1.
I'll now set the only parameter on the _preparedStatement_ for the order. 
I can use _setString_, even when the field is a date time field.
I'll call the _executeUpdate_ method on that, inside an if condition,
checking to make sure only one record was inserted.
So if that's true, I know I can get the generated keys, 
from the prepared statement.
And you've seen me do this often enough,
so I'll just get that, and print it out.
Next, I'll add the code to batch up the details for each order, 
before executing the whole batch.
I'll insert this code in the nested _if_.

```java  
private static void addOrder(Connection conn, PreparedStatement psOrder, PreparedStatement psDetail, Order order)
        throws SQLException {

    try {
        conn.setAutoCommit(false);
        int orderId = -1;
        psOrder.setString(1, order.dateString());
        if (psOrder.executeUpdate() == 1) {
            var rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
                System.out.println("orderId = " + orderId);

                if (orderId > -1) {
                    psDetail.setInt(1, orderId);
                    for (OrderDetail od : order.details()) {
                        psDetail.setString(2, od.itemDescription());
                        psDetail.setInt(3, od.qty());
                        psDetail.addBatch();
                    }
                    int[] data = psDetail.executeBatch();
                    int rowsInserted = Arrays.stream(data).sum();
                    if (rowsInserted != order.details().size()) {
                        throw new SQLException("Inserts don't match");
                    }
                }
            }
        }
        conn.commit();
    } catch (SQLException e) {
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}
```

If the _orderId_ is greater than `-1`, then I know my order, 
the parent record was created ok.
I'll use the prepared statement for detail,
and I'll set the _orderId_, which is the first placeholder. 
Now, I'll loop through the `order.details`.
And set the second and third parameters, 
that's item description, and quantity. 
I'll call add batch on that prepared statement. 
After I've batched up all the statements, 
I'll call _executeBatch_, which returns an array of integers. 
And I'll sum those values up with a quick stream, 
which I did in the last section.
I'll check if the _rowsInserted_ is different from the size of the details.
If they're different, I'll throw an exception.
Did you notice something here?
That I set the order id outside the loop, `int orderId = -1;`.
Because I'm reusing the _preparedStatement_,
the parameters I used previously are still set, 
so here I can just set _orderId_ once, and it will stay set.
The other thing is that I didn't set the flag on the connection,
the _setContinueBatchOnError_ flag.
This makes this code a little less efficient,
so if you did add it, that's great.
I'll just mention it, but I'll leave the code as is.
We're almost done.

```java  
private static void addOrders(Connection conn, List<Order> orders) {

    String insertOrder = "INSERT INTO storefront.order (order_date) VALUES (?)";
    String insertDetail = "INSERT INTO storefront.order_details (order_id, item_description, quantity) values(?, ?, ?)";

    try (PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psDetail = conn.prepareStatement(insertDetail, Statement.RETURN_GENERATED_KEYS);
    ) {

        orders.forEach((o) -> {
            try {
                addOrder(conn, psOrder, psDetail, o);
            } catch (SQLException e) {
                System.err.printf("%d (%s) %s%n", e.getErrorCode(), e.getSQLState(), e.getMessage());
                System.err.println("Problem: " + psOrder);
                System.err.println("Order: " + o);
            }
        });
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I need a method, which I'll call _addOrders_.
That'll take the connection and the list of orders.
I'll set up a string for the insert order.
Now remember, you need to specify parameters 
with a question mark, and not a string specifier.
I only have one placeholder, and that's for the order date. 
And I'll set up the string for insert detail, 
and that's very similar, but it has three placeholders. 
I'll use a _try-with-resources_, creating my prepared statements here. 
So _psOrder_ is the prepared statement for the insert order string.
For prepared statements, I specify if I want generated keys back at this point,
and not in the _executeUpdate_ method.
I'll create _psDetail_ the same way.
This won't compile, so I'll have IntelliJ generate the _catch_ clause.
Now it's time to put it all together.
I'll loop through the orders using _forEach_,
and start a multi-line lambda. 
I'll include a _try-catch_ clause in this lambda. 
I'll execute _addOrder_, passing the connection, 
the two different prepared statements, and the order.
Here, I'll catch the _SQLException_, 
because I want to handle it, and continue processing. 
And just to reinforce some of the lecture material, 
I'll print the error code, the _SQLState_, and _the message_.
I'll print the state of the prepared statement for the order. 
And I'll print the order itself.
Finally, I just have to call this from the _main_ method.

```java  
public static void main(String[] args) {

    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQL_USER"));
    dataSource.setPassword(System.getenv("MYSQL_PASS"));

    List<Order> orders = readData();
    
    try (Connection conn = dataSource.getConnection()) {
        addOrders(conn, orders);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
```

I'll add this inside the _try-catch_ clause.
And that's it.
I'll run that:

```html  
Order[orderId=-1, dateString=2023-11-01 06:01:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:02:00, details=[OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:03:00, details=[OrderDetail[orderDetailId=-1, itemDescription=loaves of bread, qty=3], OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=juice, qty=2], OrderDetail[orderDetailId=-1, itemDescription=bag of dog food, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cans of cat food, qty=10]]]
Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
Order[orderId=-1, dateString=2023-11-01 06:05:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Apple, qty=5], OrderDetail[orderDetailId=-1, itemDescription=Orange, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=3], OrderDetail[orderDetailId=-1, itemDescription=Turkey, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Bunch Celery, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Onion, qty=2], OrderDetail[orderDetailId=-1, itemDescription=Banana, qty=7], OrderDetail[orderDetailId=-1, itemDescription=Package Ground Beef, qty=1], OrderDetail[orderDetailId=-1, itemDescription=Nuttin' Honey Cereal, qty=1]]]
orderId = 3
orderId = 4
orderId = 5
1292 (22001) Data truncation: Incorrect datetime value: '2023-11-31 05:04:00' for column 'order_date' at row 1
Problem: com.mysql.cj.jdbc.ClientPreparedStatement: INSERT INTO storefront.order (order_date) VALUES ('2023-11-31 05:04:00')
Order: Order[orderId=-1, dateString=2023-11-31 05:04:00, details=[OrderDetail[orderDetailId=-1, itemDescription=milk, qty=1], OrderDetail[orderDetailId=-1, itemDescription=dozen eggs, qty=1], OrderDetail[orderDetailId=-1, itemDescription=lettuce, qty=1], OrderDetail[orderDetailId=-1, itemDescription=cookies, qty=1]]]
orderId = 6
```

So what I didn't tell you in the beginning,
was there was an error in the data.
I did this on purpose, so it would test the requirements of this challenge.
The challenge was to make sure the order would be rolled back, 
but orders after the bad order would still get processed.
So you can see the problem was with the fourth order, 
and that's because I had a bad date there.
This format is the year, the month, then the day, 
so I have November 31st here, which isn't a valid date.
You can see the MySQL error code was `1292`,
the _SQLState_ code was `22001`, 
and the message says _Incorrect datetime value_.
And then the prepared statement for the order is printed out.
That's followed by all the order information.
I'll switch back to MySQL Workbench and look at this data there.

![image66](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image66.png?raw=true)

I'll highlight the order and then pick the grid icon.
Here, you see four orders added, which confirms the Java output.
I'll do the same for the _order details_.



And you can see order
details for those four orders.
So hopefully you got a lot out of this challenge.
There was a lot to it, I know.
This challenge included a DDL statement,
prepared statements, batch processing
and transactional processing.
In the next video, I'll be talking
about using callable statements,
which are used to call server side methods,
or functions, which are sometimes generically
called stored procedures, in a database.
So I'll see you in that next video.
</div>