package io.pivonka.magicwands;

import io.pivonka.magicwands.commands.WandsCommand;
import io.pivonka.magicwands.listeners.PlayerListener;
import io.pivonka.magicwands.wands.WandsManager;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicWands extends JavaPlugin {

    @Override
    public void onEnable() {
        Server server = getServer();
        getLogger().info("MagicWands has been enabled!");

        this.registerCustomCommands();
        this.registerCustomEvents();

        WandsManager.registerWandRecipes(server);
    }

    @Override
    public void onDisable() {
        getLogger().info("Magic Wands has been disabled!");
    }

    private void registerCustomCommands() {
        getCommand("wands").setExecutor(new WandsCommand());
    }

    private void registerCustomEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
