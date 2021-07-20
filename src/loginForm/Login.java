package loginForm;

import dbhelper.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static void main(String[] args) throws SQLException {
        LoginFrame frame  = new LoginFrame();
//        Connection con  = DatabaseHelper.getConnection();
//
//        ResultSet tables = con.getMetaData().getTables(null,null,"test",null);
//        if(tables.next()){
//            System.out.println("Table exists");
//        }
//        else{
//            System.out.println("Table doesn't exist");
//            DatabaseHelper.createTable(con,"test");
//        }


    }
}
