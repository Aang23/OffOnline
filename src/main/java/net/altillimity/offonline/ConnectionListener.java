package net.altillimity.offonline;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ConnectionListener implements Listener {
    @EventHandler(priority = 64)
    public void onPreLogin(PreLoginEvent e) {
        PendingConnection connection = e.getConnection();
        if(MysqlUtils.isUserAllowed(connection.getName())){
            System.out.println("Allowing "+connection.getName()+" to connect in OFFLINE mode.");
            connection.setOnlineMode(false);
        } else {
            System.out.println("Connecting "+connection.getName()+" in ONLINE mode.");
            connection.setOnlineMode(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(ServerConnectEvent e) {
        String name = e.getPlayer().getName();
        ServerInfo target = ProxyServer.getInstance().getServerInfo("login");
        if(MysqlUtils.isUserAllowed(name)){
            e.setTarget(target);
        } 
    }
}