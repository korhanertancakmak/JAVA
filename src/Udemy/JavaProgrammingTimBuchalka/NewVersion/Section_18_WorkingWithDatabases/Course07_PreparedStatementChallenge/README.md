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



In the _schemas_ panel, I'll expand the _storefront_ database, 
then _tables_, and highlight _order_details_.
This time I'll select the _info icon_, and then select the _columns_ tab.
Here you can see that _quantity_ is now a column in this table.
I'll go back to my Java code.

We only have to do this once,
so I'll now comment out this code.
So next, I'll write the code to read the data
from the file, that has the order data.
Let me open this file a minute.
In this file, the order data has the key word
order in the first column, whereas the details
have the key word item, in that column.
Notice that the order records have dates,
already formatted the way we want them to be.
Also notice, the time changes by 1 minute,
you can use this to recognize the orders.
I'll use a different approach in this code,
just to show you an alternative.
First I'll set up two records,
one for Order, and one for OrderDetails,
in the Challenge2.java source file.
I'll start with the OrderDetail.
This record has three fields, order detail id,
an int, the item description, a string, and
quantity, an int. I'll create a custom constructor
for this, because I won't have an order id, as
I'm reading the data in from the file. So I want
a constructor with just item description and
quantity. And this has to call the canonical
constructor, so I'll pass -1 as the ID.
Next, I'll create a record for the order.
This will have order ID, date string, and
list of order details. And this one will
have a custom constructor too, for just the date
string. So when I call the canonical constructor,
I'll pass -1 for the order id, and I'll
create a new ArrayList for the details here.
The only other thing I'll add, is an addDetail
method, that'll take an item description,
and a quantity. I'll create an instance of the
Order Detail, pass that the description, and the quantity.
And I'll add that to the details array.
Now that I have types to put the data in,
I'll write the read data method.
This will return a list of orders.
I'll initialize an array list of orders to start
with. This time, I'll use a Scanner. If you used
Files or some other method, that's fine. I'm
really choosing this method just to remind you,
that you can use scanner, to read data from a
file. I'm putting this in a try with resources block.
Unlike Files.readLines, the scanner won't
get closed automatically, so if I put it in this
type of try statement, I don't have to remember
to close it. I'll set the delimiter to be either
a comma, or a new line. This means, instead of
splitting the data by line, it's going to split
the data by commas. I'll create a list of strings,
by calling scanner.tokens. That gives me a stream
of strings, so I'll use map to trim each value,
then terminate the stream, collecting it into a list.
I'll print each value, in my Order list.
And I'll return my order list from this method.
This won't compile, so I'll use
IntelliJ to add a catch clause.
Obviously I'm not done yet, because I have
to create the Order, and OrderDetails.
I'll loop through every column value,
one at a time.
I'll get the column value, from the list.
If that token has the value order,
then I know I need to set up an order.
The date is the next place in the list, so I'll do a
pre-increment, then get the next value.
I'll create and Order, and add it to the order list.
Similarly, I'll check for the keyword item,
which indicates the tokens that follow are item fields.
I'll parse the next field, the quantity. Now, in a
normal world, you'd want to do more validation,
but again, I'll just keep this simple. The item
description is next. I can get the order that goes
with the details, by just getting the last order added.
And I'll add the detail to that order.
I'll test this code out, by calling this method.
I'll insert a call to this method, before
I open a database connection. So first,
I'll create a List variable, called orders,
and assign that the value of read data.
I'll run this now, to make sure I

```html  

```

get each order, as a coherent unit.
And you can see, this code has 5
orders, and all the information
is stored now, in this array of Orders.
Next, I'll create the add Order method.
This method will take a connection, two
different prepared statements, and an order.
I'll set up the prepared statements outside
of this method, so they can be reused.
This will throw an SQL Exception. Here, I'll use
a regular try, because I don't want to close any
resources, if this code fails. Instead I'll be
ignoring the error, and continuing to process
other orders. I do want the code in this
method to be part of a single transaction,
so that starts by setting auto commit to false.
I'll leave some space for the code here. I'll
end the try block with a commit statement. I'll
catch the SQL Exception, and rollback any changes,
if I do get one. Then, I'll rethrow the exception.
And I do want a finally clause, because I want to
be sure auto commit is set to true in any case.
That's the set up for a transaction.
Between the first auto commit, and the commit
method, I need jdbc code, so I'll start by
initializing an order ID variable, to minus 1.
I'll now set the only parameter, on the prepared
Statement for the order. I can use setString,
even when the field is a date time field.
I'll call the executeUpdate method on that, inside an
if condition,
checking to make sure only 1 record was inserted.
So if that's true, I know I can get
the generated keys, from the prepared statement.
And you've seen me do this often enough,
so I'll just get that, and print it out.
Next, I'll add the code to batch up the details
for each order, before executing the whole batch.
I'll insert this code in the nested if.
If the order id is greater than minus 1, then I
know my order, the parent record, was created ok.
I'll use the prepared statement for detail,
and I'll set the order id, which is the first place
holder. Now, I'll loop through the order details.
And set the 2nd and 3rd parameters, that's item
description, and quantity. I'll call add batch
on that prepared statement. After I've batched
up all the statements, I'll call execute Batch,
which returns an array of integers. And I'll sum
those values up with a quick stream, which I did
in the last video. I'll check if the rowsInserted,
is different from the size, of the details.
If they're different, I'll throw an exception.
Did you notice something here?
That I set the order id outside of the loop.
Because I'm reusing the preparedStatement,
the parameters I used previously
are still set, so here I can
just set order id once, and it will stay set.
The other thing is that I didn't set the flag on
the connection, thesetContinueBatchOnError flag.
This makes this code a little less efficient,
so if you did add it, that's great.
I'll just mention it, but I'll leave
the code as is.
We're almost done.
I need a method, which I'll call add Orders.
That'll take the connection,
and the list of orders.
I'll set up a string for the insert order.
Now don't forget, you need to specify
parameters with a question mark, and not a string specifier.
I only have one placeholder, and that's
for the order date. And I'll set up the string for
insert detail, and that's very similar, but it has
three placeholders. I'll use a try with resources,
creating my prepared statements here. So ps Order
is the prepared statement for the insert order string.
For prepared statements, I specify
if I want generated keys back at this point,
and not in the execute Update method.
I'll create ps Detail the same way.
This won't compile, so I'll have
IntelliJ generate the catch clause.
Now it's time to put it all together.
I'll loop through the orders using for each,
and start a multi line lambda. I'll include a
try catch clause in this lambda. I'll execute add
Order, passing the connection, the two different
prepared statements, and the order.
Here,I'll catch the SQL exception, because I want to
handle it, and continue processing. And just to
reinforce some of the lecture material, I'll print
the error code, the S Q L state, and the message.
I'll print the state of the prepared statement
for the order. And I'll print the order itself.
Finally, I just have to call
this from the main method.
I'll add this inside the try catch clause.
And that's it.
I'll run that.

```html  

```

So what I didn't tell you in the beginning,
was there was an error in the data.
I did this on purpose, so it would
test the requirements of this challenge.
The challenge was to make sure the order
would be rolled back, but orders after
the bad order, would still get processed.
So you can see the problem was with the fourth
order, and that's because I had a bad date there.
This format is the year, the month,
then the day, so I have November
31st here, which isn't a valid date.
You can see the MySQL error code was 1292,
the SQL State code was 22 001, and the
message says Incorrect datetime value.
And then the prepared statement
for the order is printed out
That's followed by all the order information.
I'll switch back to MySQL Workbench,
and look at this data there.
I'll highlight order and then pick the grid icon.
Here, you see 4 orders added,
which confirms the Java output.
I'll do the same for the Order details.
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

```html  

```

</div>