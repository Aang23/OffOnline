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
        if (MysqlUtils.isUserAllowed(connection.getName())) {
            System.out.println("Allowing " + connection.getName() + " to connect in OFFLINE mode.");
            connection.setOnlineMode(false);
            OffOnline.loggeds.put(connection.getName(), false);
        } else {
            System.out.println("Connecting " + connection.getName() + " in ONLINE mode.");
            connection.setOnlineMode(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(ServerConnectEvent e) {
        String name = e.getPlayer().getName();
        ServerInfo target = ProxyServer.getInstance().getServerInfo("login");
        if (OffOnline.loggeds.containsKey(name)) {
            if (!OffOnline.loggeds.get(name))
                e.setTarget(target);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        String name = p.getName();
        if (OffOnline.loggeds.containsKey(name)) {
            if (!OffOnline.loggeds.get(name)) {
                if (!e.getMessage().startsWith("/login"))
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerDisconnectEvent e) {
        String name = e.getPlayer().getName();
        if(OffOnline.loggeds.containsKey(name)) OffOnline.loggeds.remove(name);
    }
}