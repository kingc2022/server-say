package org.kingc.server_say;

import org.bukkit.plugin.java.JavaPlugin;
import org.kingc.server_say.commands.command;

public final class Server_say extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Server Say] Server Say plugin enabled");
        System.out.println("[Server Say] By kingc");
        saveDefaultConfig();
        getCommand("ssay").setExecutor(new command());
    }

    @Override
    public void onDisable() {
        System.out.println("[Server Say] Server Say plugin disabled");
        System.out.println("[Server Say] Thanks for using");
    }
}
