package net.altillimity.offonline;

import java.io.IOException;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffOnline extends Plugin implements Listener {
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new ConnectionListener());
		MysqlConnect mysqlConnect = new MysqlConnect();
                String sql = "CREATE TABLE IF NOT EXISTS users (user_name VARCHAR(100), user_id VARCHAR(100), user_pass VARCHAR(100));";
                try {
                        PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
                        statement.execute();
                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        mysqlConnect.disconnect();
				}
		System.out.println(MysqlUtils.isUserAllowed("Aang23"));
	}
}
