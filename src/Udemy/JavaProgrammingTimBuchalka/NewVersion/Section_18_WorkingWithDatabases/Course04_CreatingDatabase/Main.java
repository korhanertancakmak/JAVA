package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static String USE_SCHEMA = "USE storefront";

    private static int MYSQL_DB_NOT_FOUND = 1049;

    public static void main(String[] args) {

        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));


/*
        try (Connection conn = dataSource.getConnection()) {

            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/

/*
        try (Connection conn = dataSource.getConnection()) {

            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/

        try (Connection conn = dataSource.getConnection()) {

            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
                setUpSchema(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //private static boolean checkSchema(Connection conn) {
    private static boolean checkSchema(Connection conn) throws SQLException{

/*
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
*/

/*
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
*/

        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();

            //return false;
            if (conn.getMetaData().getDatabaseProductName().equals("MySQL") && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;
            } else throw e;
        }
        return true;
    }


    private static void setUpSchema(Connection conn) throws SQLException {

        String createSchema = "CREATE SCHEMA storefront";

        String createOrder = """
            CREATE TABLE storefront.order (
            order_id int NOT NULL AUTO_INCREMENT,
            order_date DATETIME NOT NULL,
            PRIMARY KEY (order_id)
            )""";

        String createOrderDetails = """
            CREATE TABLE storefront.order_details (
            order_detail_id int NOT NULL AUTO_INCREMENT,
            item_description text,
            order_id int DEFAULT NULL,
            PRIMARY KEY (order_detail_id),
            KEY FK_ORDERID (order_id),
            CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
            REFERENCES storefront.order (order_id) ON DELETE CASCADE
            )""";

        try (Statement statement = conn.createStatement()) {
            System.out.println("Creating storefront Database");
            statement.execute(createSchema);
            if (checkSchema(conn)) {
                statement.execute(createOrder);
                System.out.println("Successfully Created Order");
                statement.execute(createOrderDetails);
                System.out.println("Successfully Created Order Details");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
