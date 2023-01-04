package io.pivonka.magicwands.wands;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public interface WandInterface {
    public String getWandName();

    public ItemStack createWandItem();

    public Recipe getWandRecipe();

    public void onRightClick(PlayerInteractEvent event, JavaPlugin plugin);
}
