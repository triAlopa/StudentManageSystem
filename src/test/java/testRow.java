import config.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testRow {

    public static void main(String[] args) throws SQLException {
        int num = getRowCount("select class_name from class");
        System.out.println(num);
    }
    public static int getRowCount(String querySql) throws SQLException {
        new database();
        Connection connection = database.getConnection();
        int rowCount=0;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){ rowCount++;}
            rs.close();
            rs.beforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rowCount);
        return rowCount;
    }
}
