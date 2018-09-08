package net.altillimity.offonline;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MysqlUtils {

    public static void setup() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String sql = "CREATE TABLE IF NOT EXISTS users (user_name VARCHAR(100), user_pass VARCHAR(100));";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    public static boolean isUserAllowed(String name) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean result = false;
        String sql = "SELECT EXISTS(SELECT * FROM users WHERE user_name='" + name + "');";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        return result;
    }

    public static void addUser(String name, String pass) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String sql = "INSERT INTO users (user_name, user_pass) VALUES('"+name+"', '"+pass+"' );";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    public static void delUser(String name) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String sql = "DELETE FROM users WHERE user_name='"+name+"'";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    public static String getPass(String name){
        MysqlConnect mysqlConnect = new MysqlConnect();
        String pass = null;
        String sql = "SELECT * FROM users WHERE user_name='"+name+"';";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            pass = rs.getString("user_pass");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        return pass;
    }
}