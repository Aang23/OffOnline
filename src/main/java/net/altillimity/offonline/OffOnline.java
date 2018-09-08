package net.altillimity.offonline;

import java.io.IOException;

import net.altillimity.offonline.commands.offonlineCommand;
import net.altillimity.offonline.commands.LoginCommand;
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
import java.util.HashMap;

public class OffOnline extends Plugin implements Listener {
	public static HashMap<String, Boolean> loggeds = new HashMap<String, Boolean>();

	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new ConnectionListener());
		getProxy().getPluginManager().registerCommand(this, new offonlineCommand());
		getProxy().getPluginManager().registerCommand(this, new LoginCommand());
		MysqlUtils.setup();
	}
}
