package net.altillimity.offonline.commands;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.altillimity.offonline.MysqlUtils;
import net.md_5.bungee.api.ChatColor;
import net.altillimity.offonline.OffOnline;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

public class LoginCommand extends Command {
    public LoginCommand() {
        super("login");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        String name = p.getName();
        if (strings[0].contains(MysqlUtils.getPass(name))) {
            OffOnline.loggeds.replace(name, false, true);
            p.sendMessage(TextComponent.fromLegacyText("Logged in. Sending you to the lobby"));
            ServerInfo target = ProxyServer.getInstance().getServerInfo("lobby");
            p.connect(target);
        } else
            p.sendMessage(TextComponent.fromLegacyText("Wrong password !"));
    }
}