import config.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.database.getConnection;

public class queryCollege {
    static Connection connection;
    queryCollege() throws SQLException {

    }
    public static void main(String[] args) throws SQLException {
        new database();
        selectAllCollege();
    }

    public static void selectAllCollege()  {
        connection=database.getConnection();
       // String queryAllCollege="SELECT college_id, college_name FROM  college ORDER BY college_id ASC";
        String queryAllCollege="SELECT * FROM  college ";

        try {
            PreparedStatement ps = connection.prepareStatement(queryAllCollege);
            if(connection==null){
                System.out.println("null");
            }else {
                System.out.println("not null");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("college_id"));
                System.out.println(rs.getString("college_name"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//public static void selectAllCollege() {
//    // 使用带排序的SQL查询
//    String queryAllCollege = "SELECT college_id, college_name FROM college ORDER BY college_id ASC";
//
//    try (Connection conn = getConnection();  // 假设有获取连接的方法
//         PreparedStatement ps = conn.prepareStatement(queryAllCollege);
//         ResultSet rs = ps.executeQuery()) {
//
//
//        // 遍历结果集
//        while (rs.next()) {
//            String collegeId = rs.getString("college_id");
//            String collegeName = rs.getString("college_name");
//            System.out.printf("%-8s\t%s%n", collegeId, collegeName);
//        }
//
//    } catch (SQLException e) {
//        // 更友好的错误处理
//        System.err.println("查询学院信息时出错: " + e.getMessage());
//        e.printStackTrace();
//    }
//}
}
