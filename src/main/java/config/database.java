package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private static Connection connection;

    public database() throws SQLException {  //mysql://localhost:3306/
        //链接本地数据库，本地127.0.0.1；链接外界要填它的ip
        String url = "jdbc:mysql://localhost:3306/stumanage";
        String user = "root";
        String password = "06170018";
        connection = DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() {
        return connection;
    }
}
