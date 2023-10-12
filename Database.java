import java.sql.*;
import javax.naming.spi.DirStateFactory.Result;

public class Database {
    private static Connection conn = null;

    // public Database(){}

    public static void openJDBC() {
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_10b_db";

        try {
            conn = DriverManager.getConnection(dbConnectionString, "csce315_910_devrpatel04", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void closeJDBC() {
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
    }

    public static ResultSet getData(String cmd) {
        try {
            Statement statement = conn.createStatement();
            ResultSet result = conn.createStatement().executeQuery(cmd);
            return result;
        } catch (Exception e) {
            System.out.println("ERROR:");
            e.printStackTrace();
            // System.err.println(e.getClass().getName()+": "+e.getMessage());
            return null;
            // System.exit(0);
        }
    }

    public static void main(String args[]) {
        openJDBC();

        try {
            Statement createStmt = conn.createStatement();
            ResultSet result = conn.createStatement().executeQuery("SELECT COUNT(*) FROM OrderHistory WHERE Date = '2023-09-30';");
            result.next();
            System.out.println(result.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        closeJDBC();
    }
}
