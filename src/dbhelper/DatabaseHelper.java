package dbhelper;

import usermodel.UserModel;

import java.sql.*;
import java.util.ArrayList;

interface UserTableOperations {
    ArrayList<String> allUsers();

    UserModel viewUser(String regno);

    boolean checkUserExists(String regno, String password);

    boolean createUser(UserModel user);

    void updateUser();

    boolean deleteUser(String regno, String password);

}

public class DatabaseHelper implements UserTableOperations {
    private static final String url = "jdbc:postgresql://localhost:5432/testdb";
    private static final String driverName = "org.postgresql.Driver";
    private static final String username = "postgres";
    private static final String password = "Test@123";
    private static final String createTableQuery = "create table student(regno varchar(10) PRIMARY KEY,name varchar(20),department int,dob varchar(15),gender varchar(10),email varchar(30),mobile varchar(10),password varchar(50))";
    private static Connection connection;


    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Test Database");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static void createTable() throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        stmt.executeUpdate(createTableQuery);
        System.out.println("Table student created");
        conn.close();
    }


    public boolean tableExists() {

        try {

            Connection con = DatabaseHelper.getConnection();

            ResultSet tables = con.getMetaData().getTables(null, null, "student", null);
            if (tables.next()) {
                System.out.println("Table exists");
                con.close();
                return true;
            } else {
                System.out.println("Table doesn't exist");
                createTable();

            }
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<String> allUsers() {
        ArrayList<String> list = new ArrayList<String>();
        tableExists();
        try {
            Connection con = DatabaseHelper.getConnection();
            String selectAllQuery = "select regno from student";
            PreparedStatement stmt = con.prepareStatement(selectAllQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
//                System.out.println(rs.getString(1).getClass());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public UserModel viewUser(String regno) {
        UserModel user = null;
        tableExists();
        try {
            Connection con = DatabaseHelper.getConnection();
            String selectUserQuery = String.format("select * from student where regno='%s'", regno);
            PreparedStatement stmt = con.prepareStatement(selectUserQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
//                System.out.println(rs.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean checkUserExists(String regno, String password) {
        tableExists();
        try {
            String selectQuery = String.format("select * from student where regno='%s' and password='%s'", regno, password);
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery();
            conn.close();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Exception occured" + e.getMessage());
        }


        return false;

    }

    @Override
    public boolean createUser(UserModel user) {
        tableExists();
        String insertQuery = String.format("insert into student values('%s','%s',%d,'%s','%s','%s','%s','%s')", user.getRegNo(), user.getName(), user.getDepartment(), user.getDob(), user.getGender(), user.getEmail(), user.getMobile(), user.getPassword());

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery);

            stmt.executeUpdate();
            System.out.println("user record inserted");
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public void updateUser() {

    }

    @Override
    public boolean deleteUser(String regno, String password) {
        tableExists();
        try {
            Connection conn = getConnection();
            String deleteQuery = String.format("delete from student where regno='%s' and password='%s'", regno, password);
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("Exception : "+e.getMessage());
        }
        return false;
    }

}