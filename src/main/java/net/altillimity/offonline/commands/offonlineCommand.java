package net.altillimity.offonline.commands;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.altillimity.offonline.MysqlUtils;
import net.md_5.bungee.api.ChatColor;

public class offonlineCommand extends Command {
    public offonlineCommand() {
        super("offonline");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(strings[0].equals("add")){
            MysqlUtils.addUser(strings[1], strings[2]);
        } else if(strings[0].equals("del")){
            MysqlUtils.delUser(strings[1]);
        }
    }
}