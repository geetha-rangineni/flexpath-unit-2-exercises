import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public void test01GetLAUsers() throws Exception {
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/01-get-la-users.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (!results.getString("city").equals("Los Angeles")) {
                Assertions.fail("City is not Los Angeles");
            }
            i++;
        }
        assertEquals(2, i);
    }

    @Test
    public void test02UniqueUserCities() throws Exception {
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/02-unique-user-cities.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && !results.getString("city").equals("Los Angeles")) {
                Assertions.fail("First city is not Los Angeles");
            }
            if (i == 1 && !results.getString("city").equals("New York")) {
                Assertions.fail("Second city is not New York");
            }
            i++;
        }
        assertEquals(2, i);
    }

    @Test
    public void test03RevenuePerProduct() throws Exception {
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/03-revenue-per-product.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && (
                results.getInt("ProductId") != 1 ||
                !results.getString("ProductName").equals("Smartphone") ||
                !results.getBigDecimal("TotalRevenue").equals(new BigDecimal("1399.98"))
            )) {
                Assertions.fail("First product expected to be Smartphone with revenue 1399.98");
            }
            if (i == 1 && (
                results.getInt("ProductId") != 3 ||
                !results.getString("ProductName").equals("T-Shirt") ||
                !results.getBigDecimal("TotalRevenue").equals(new BigDecimal("19.99"))
            )) {
                Assertions.fail("Second product expected to be T-Shirt with revenue 19.99");
            }
            if (i == 2 && (
                results.getInt("ProductId") != 4 ||
                !results.getString("ProductName").equals("Mystery Novel") ||
                !results.getBigDecimal("TotalRevenue").equals(new BigDecimal("25.98"))
            )) {
                Assertions.fail("Third product expected to be Mystery Novel with revenue 25.98");
            }
            i++;
        }
        assertEquals(3, i);
    }

    @Test
    public void test04FrequentlyOrdered() throws Exception {
        var sql = Files.readString(
            Path.of(ExerciseTests.class.getResource("/04-frequently-ordered.sql").toURI())
        );

        var results = connection.prepareStatement(sql).executeQuery();
        var i = 0;

        while (results.next()) {
            if (i == 0 && (
                results.getInt("ProductId") != 1 ||
                results.getInt("TotalQuantityOrdered") != 2
            )) {
                Assertions.fail("First product expected to be product 1 with quantity of 2");
            }
            if (i == 2 && (
                results.getInt("ProductId") != 4 ||
                results.getInt("TotalQuantityOrdered") != 2
            )) {
                Assertions.fail("Third product expected to be product 4 with quantity of 2");
            }
            i++;
        }
        assertEquals(2, i);
    }
}
