package org.kingc.server_say.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Plugin config = org.kingc.server_say.Server_say.getProvidingPlugin(org.kingc.server_say.Server_say.class);
        String prefix = config.getConfig().getString("prefix").replace('&', ChatColor.COLOR_CHAR);
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GRAY + "================ " + ChatColor.GOLD + "Server Say " + ChatColor.GRAY + "================");
            sender.sendMessage(ChatColor.GOLD + "/ssay help " + ChatColor.GRAY + "展示帮助菜单");
            sender.sendMessage(ChatColor.GOLD + "/ssay reload " + ChatColor.GRAY + "重新加载配置文件");
            sender.sendMessage(ChatColor.GOLD + "/ssay <message> " + ChatColor.GRAY + "发送消息");
            sender.sendMessage(ChatColor.GRAY + "================ " + ChatColor.GOLD + "Server Say " + ChatColor.GRAY + "================");
        } else {
            if (args[0].equals("reload")) {
                config.reloadConfig();
                sender.sendMessage(prefix + ChatColor.GREEN + "已重新加载配置文件");
            } else if (args[0].equals("help")) {
                sender.sendMessage(ChatColor.GRAY + "================ " + ChatColor.GOLD + "Server Say " + ChatColor.GRAY + "================");
                sender.sendMessage(ChatColor.GOLD + "/ssay help " + ChatColor.GRAY + "展示帮助菜单");
                sender.sendMessage(ChatColor.GOLD + "/ssay reload " + ChatColor.GRAY + "重新加载配置文件");
                sender.sendMessage(ChatColor.GOLD + "/ssay <message> " + ChatColor.GRAY + "发送消息");
                sender.sendMessage(ChatColor.GRAY + "================ " + ChatColor.GOLD + "Server Say " + ChatColor.GRAY + "================");
            } else {
                if (sender instanceof ConsoleCommandSender) {
                    String message = "";
                    for (String arg : args) {
                        message = message + arg + " ";
                    }
                    String format = config.getConfig().getString("format").replace('&', ChatColor.COLOR_CHAR).replace("{{msg}}", message);
                    Bukkit.getServer().broadcastMessage(format);
                } else {
                    if (sender.isOp()) {
                        sender.sendMessage(prefix + ChatColor.RED + "必须从控制台执行此命令");
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "你没有权限");
                    }
                }
            }
        }
        return false;
    }
}
