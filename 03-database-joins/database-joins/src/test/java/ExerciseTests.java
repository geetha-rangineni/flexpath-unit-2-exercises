import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTests {
    private static DB db;
    private static Connection connection;

    @BeforeAll
    public static void setup() throws Exception {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/library";
        String username = "root";
        String password = "root"; // replace if needed

        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, username, password);

        java.io.Reader reader = new java.io.InputStreamReader(
                ExerciseTests.class.getResource("/create-database.sql").openStream()
        );

        ScriptRunner sr = new ScriptRunner(connection);
        sr.setStopOnError(true);
        sr.setLogWriter(null);
        sr.setErrorLogWriter(null);
        sr.runScript(reader);
    }
    @AfterAll
    public static void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            if (db != null) {
                db.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderDetails() throws Exception {
        connection.prepareStatement("UPDATE Orders SET UserID = 1 WHERE OrderID = 1").executeUpdate();
        connection.prepareStatement("UPDATE Orders SET UserID = 2 WHERE OrderID = 2").executeUpdate();
        connection.prepareStatement("UPDATE Orders SET UserID = 3 WHERE OrderID = 3").executeUpdate();
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/01-order-details.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && (
                results.getInt("OrderID") != 1 ||
                !results.getString("FirstName").equals("John") ||
                !results.getString("LastName").equals("Doe")
            )) {
                Assertions.fail("Order #1 details are not correct");
            } else if (i == 1 && (
                results.getInt("OrderID") != 2 ||
                !results.getString("FirstName").equals("Jane") ||
                !results.getString("LastName").equals("Smith")
            )) {
                Assertions.fail("Order #2 details are not correct");
            }
            i++;
        }
        assertEquals(2, i, "Number of orders is not correct");
    }

    @Test
    public void testProductsWithCategory() throws Exception {
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/02-products-with-category.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && (
                results.getInt("ProductID") != 1 ||
                !results.getString("ProductName").equals("Smartphone") ||
                !results.getString("CategoryName").equals("Electronics")
            )) {
                Assertions.fail("Smartphone details are not correct");
            } else if (i == 1 && (
                results.getInt("ProductID") != 2 ||
                !results.getString("ProductName").equals("Laptop") ||
                results.getString("CategoryName") != null
            )) {
                Assertions.fail("Laptop details are not correct");
            } else if (i == 2 && (
                results.getInt("ProductID") != 3 ||
                !results.getString("ProductName").equals("T-Shirt") ||
                !results.getString("CategoryName").equals("Clothing")
            )) {
                Assertions.fail("T-Shirt details are not correct");
            } else if (i == 3 && (
                results.getInt("ProductID") != 4 ||
                !results.getString("ProductName").equals("Mystery Novel") ||
                !results.getString("CategoryName").equals("Books")
            )) {
                Assertions.fail("Mystery Novel details are not correct");
            }
            i++;
        }
        assertEquals(4, i, "Number of products is not correct");
    }

    @Test
    public void testOrdersWithUser() throws Exception {
        var sql = Files.readString(
                Path.of(ExerciseTests.class.getResource("/03-orders-with-user.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && (
                    results.getInt("OrderID") != 1 ||
                            results.getString("ShippingAddress") != null ||
                            !results.getString("UserName").equals("alicejones")
            )) {
                Assertions.fail("Order #3 details are not correct");
            } else if (i == 1 && (
                    results.getInt("OrderID") != 2 ||
                            !results.getString("ShippingAddress").equals("123 Main St") ||
                            !results.getString("UserName").equals("johndoe")
            )) {
                Assertions.fail("Order #1 details are not correct");
            } else if (i == 2 && (
                    results.getInt("OrderID") != 3 ||
                            !results.getString("ShippingAddress").equals("456 Oak Ave") ||
                            !results.getString("UserName").equals("janesmith")
            )) {
                Assertions.fail("Order #2 details are not correct");
            }
            i++;
        }
        assertEquals(3, i, "Number of orders is not correct");
    }

}
