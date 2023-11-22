package lk.ijse.Fusion.Database;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private Connection  connection;
    private static DBConnection dbconnection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
      /*  connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/fusiontech?useSSL=false&allowPublicKeyRetrieval=true", "root", "1234");*/
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/fusiontech?createDatabaseIfNotExist=true", "root", "1234");
    }

    public static  DBConnection getInstance() {
        if (dbconnection==null){
            try {
                dbconnection=new DBConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dbconnection;
    }


}