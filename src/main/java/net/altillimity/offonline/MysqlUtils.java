package net.altillimity.offonline;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MysqlUtils {

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

}