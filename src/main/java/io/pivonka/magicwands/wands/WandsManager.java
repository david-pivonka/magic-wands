package io.pivonka.magicwands.wands;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WandsManager {
    public static List<WandInterface> getWands() {
        return List.of(
                new SuperWand(),
                new TeleportationWand()
        );
    }

    public static WandInterface getWandByName(String wandName) {
        return getWands()
                .stream()
                .filter(wand -> wand.getWandName().equals(wandName))
                .findFirst()
                .orElse(null);
    }

    public static void registerWandRecipes(Server server) {
        getWands().forEach(wand -> server.addRecipe(wand.getWandRecipe()));
    }

    public static String getAvailableWandsAsString() {
        return String.join(", ", getWands().stream().map(WandInterface::getWandName).toList());
    }

    public static void giveWandToPlayer(WandInterface wand, Player player) {
        ItemStack wandItem = wand.createWandItem();
        player.getInventory().addItem(wandItem);
    }

    public static boolean isWandItem(ItemStack itemStack) {
        return getWands().stream().anyMatch(wand -> wand.createWandItem().isSimilar(itemStack));
    }

    public static WandInterface getWandByItem(ItemStack itemStack) {
        return getWands()
                .stream()
                .filter(wand -> wand.createWandItem().isSimilar(itemStack))
                .findFirst()
                .orElse(null);
    }
}
