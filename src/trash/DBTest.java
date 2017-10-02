package trash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String server = "localhost\\sqlexpress";
            int port = 61783;
            String user = "sa";
            String password = "Q1w2e3r4";
            String database = "Files2Go-DB2";
            String jdbcURL = "jdbc:sqlserver://"+server+":"+port+";user="+user+";password="+password+";databaseName="+database;

            System.out.println("Connecting to: "+jdbcURL);
            Connection con = DriverManager.getConnection(jdbcURL);

            if(con.isValid(5)) System.out.println("Connection is valid!!");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Files2Go-DB2].[dbo].[User]");
            if (rs.next()) System.out.println("first string");
            System.out.printf("OPa: %s",rs.getString(5));
            rs.next();
            System.out.printf("\nOPa: %s",rs.getString(8));
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
    }
}
