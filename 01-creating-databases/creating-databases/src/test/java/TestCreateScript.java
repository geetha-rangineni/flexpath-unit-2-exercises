import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestCreateScript {
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
            TestCreateScript.class.getResource("/create-database.sql").openStream()
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
    public void testBookTable() throws Exception {
        checkTable("books", new String[] {
            "book_id", "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "int(11)", "varchar(255)", "int(11)", "int(11)", "int(11)"
        }, new String[] {
            "PRI", "", "MUL", "MUL", ""
        }, new String[] {
            "auto_increment", "", "", "", ""
        });
        checkForeignKey("books", "author_id", "authors", "author_id");
        checkForeignKey("books", "genre_id", "genres", "genre_id");

        checkTableRecord("books", new String[] {
            "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "The Great Gatsby", "1", "1", "1925"
        });
        checkTableRecord("books", new String[] {
            "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "To Kill a Mockingbird", "2", "2", "1960"
        });
        checkTableRecord("books", new String[] {
            "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "1984", "3", "3", "1949"
        });
        checkTableRecord("books", new String[] {
            "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "Pride and Prejudice", "4", "4", "1813"
        });
        checkTableRecord("books", new String[] {
            "title", "author_id", "genre_id", "publication_year"
        }, new String[] {
            "The Catcher in the Rye", "5", "5", "1951"
        });
    }

    @Test
    public void testAuthorTable() throws Exception {
        checkTable("authors", new String[] {
            "author_id", "name", "birth_year", "country"
        }, new String[] {
            "int(11)", "varchar(255)", "int(11)", "varchar(255)"
        }, new String[] {
            "PRI", "", "", ""
        }, new String[] {
            "auto_increment", "", "", ""
        });

        checkTableRecord("authors", new String[] {
            "name", "birth_year", "country"
        }, new String[] {
            "F. Scott Fitzgerald", "1896", "USA"
        });
        checkTableRecord("authors", new String[] {
            "name", "birth_year", "country"
        }, new String[] {
            "Harper Lee", "1926", "USA"
        });
        checkTableRecord("authors", new String[] {
            "name", "birth_year", "country"
        }, new String[] {
            "George Orwell", "1903", "UK"
        });
        checkTableRecord("authors", new String[] {
            "name", "birth_year", "country"
        }, new String[] {
            "Jane Austen", "1775", "UK"
        });
        checkTableRecord("authors", new String[] {
            "name", "birth_year", "country"
        }, new String[] {
            "J.D. Salinger", "1919", "USA"
        });
    }

    @Test
    public void testGenreTable() throws Exception {
        checkTable("genres", new String[] {
            "genre_id", "name"
        }, new String[] {
            "int(11)", "varchar(255)"
        }, new String[] {
            "PRI", ""
        }, new String[] {
            "auto_increment", ""
        });

        checkTableRecord("genres", new String[] {
            "name"
        }, new String[] {
            "Fiction"
        });
        checkTableRecord("genres", new String[] {
            "name"
        }, new String[] {
            "Mystery"
        });
        checkTableRecord("genres", new String[] {
            "name"
        }, new String[] {
            "Dystopian"
        });
        checkTableRecord("genres", new String[] {
            "name"
        }, new String[] {
            "Romance"
        });
        checkTableRecord("genres", new String[] {
            "name"
        }, new String[] {
            "Coming of Age"
        });
    }

    @Test
    public void testMemberTable() throws Exception {
        checkTable("members", new String[] {
            "member_id", "name", "join_date"
        }, new String[] {
            "int(11)", "varchar(255)", "date"
        }, new String[] {
            "PRI", "", ""
        }, new String[] {
            "auto_increment", "", ""
        });

        checkTableRecord("members", new String[] {
            "name", "join_date"
        }, new String[] {
            "Alice Smith", "2020-01-01"
        });
        checkTableRecord("members", new String[] {
            "name", "join_date"
        }, new String[] {
            "Bob Johnson", "2020-01-15"
        });
        checkTableRecord("members", new String[] {
            "name", "join_date"
        }, new String[] {
            "Charlie Brown", "2020-02-01"
        });
    }

    @Test
    public void testLoansTable() throws Exception {
        checkTable("loans", new String[] {
            "loan_id", "book_id", "member_id", "loan_date", "return_date"
        }, new String[] {
            "int(11)", "int(11)", "int(11)", "date", "date"
        }, new String[] {
            "PRI", "MUL", "MUL", "", ""
        }, new String[] {
            "auto_increment", "", "", "", ""
        });
        checkForeignKey("loans", "book_id", "books", "book_id");
        checkForeignKey("loans", "member_id", "members", "member_id");

        checkTableRecord("loans", new String[] {
            "book_id", "member_id", "loan_date", "return_date"
        }, new String[] {
            "1", "1", "2020-01-01", "2020-01-15"
        });
        checkTableRecord("loans", new String[] {
            "book_id", "member_id", "loan_date", "return_date"
        }, new String[] {
            "2", "2", "2020-01-15", "2020-02-01"
        });
        checkTableRecord("loans", new String[] {
            "book_id", "member_id", "loan_date", "return_date"
        }, new String[] {
            "3", "3", "2020-02-01", null
        });
    }

    private void checkTable(String table, String[] columns, String[] types, String[] keys, String[] extra) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW COLUMNS FROM " + table);
            while (resultSet.next()) {
                String column = resultSet.getString("Field");
                boolean found = false;
                for (int i = 0; i < columns.length; i++) {
                    String c = columns[i];
                    if (column.equals(c)) {
                        if (!resultSet.getString("Type").equals(types[i])) {
                            throw new Exception("Column " + table + "." + column + " has wrong type - Expected: " + types[i] + " Found: " + resultSet.getString("Type"));
                        }
                        if (!resultSet.getString("Key").equals(keys[i])) {
                            throw new Exception("Column " + table + "." + column + " has wrong key type - Expected: " + keys[i] + " Found: " + resultSet.getString("Key"));
                        }
                        if (!resultSet.getString("Extra").equals(extra[i])) {
                            throw new Exception("Column " + table + "." + column + " has wrong extra - Expected: " + extra[i] + " Found: " + resultSet.getString("Extra"));
                        }
                        columns[i] = null;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new Exception("Column " + table + "." + column + " not expected");
                }
            }

            for (String c : columns) {
                if (c != null) {
                    throw new Exception("Column " + table + "." + c + " not found");
                }
            }
        }
    }

    private void checkForeignKey(String table, String column, String refTable, String refColumn) throws Exception {
        String query = """
                SELECT *
                FROM information_schema.KEY_COLUMN_USAGE
                WHERE TABLE_NAME = ?
                    AND COLUMN_NAME = ?
                    AND REFERENCED_TABLE_NAME = ?
                    AND REFERENCED_COLUMN_NAME = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, table);
            statement.setString(2, column);
            statement.setString(3, refTable);
            statement.setString(4, refColumn);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new Exception("Foreign key constraint " + table + "." + column + " -> " + refTable + "." + refColumn + " not found");
            }
        }
    }

    private void checkTableRecord(String table, String[] columns, String[] values) throws Exception {
        String query = "SELECT * FROM " + table + " WHERE " + columns[0] + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, values[0]);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new Exception("Record " + table + "." + columns[0] + " = " + values[0] + " not found");
            }
            for (int i = 1; i < columns.length; i++) {
                if (values[i] == null && resultSet.getString(columns[i]) == null) {
                    continue;
                }
                if (!resultSet.getString(columns[i]).equals(values[i])) {
                    throw new Exception("Record " + table + "." + columns[i] + " has wrong value - Expected: " + values[i] + " Found: " + resultSet.getString(columns[i]));
                }
            }
        }
    }
}
