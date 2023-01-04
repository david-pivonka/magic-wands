package io.pivonka.magicwands.wands;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportationWand implements WandInterface {

    @Override
    public String getWandName() {
        return "teleportation";
    }

    @Override
    public ItemStack createWandItem() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component itemName = Component
                .text("Teleportační Hůlka")
                .color(TextColor.fromCSSHexString("#00FF00"))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(itemName);

        this.makeItemStackEnchanted(itemStack, itemMeta);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public Recipe getWandRecipe() {
        NamespacedKey namespacedKey = new NamespacedKey("magicwands", "teleportation_wand");
        ItemStack itemStack = this.createWandItem();
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, itemStack);

        recipe.shape(
                "ABA",
                "BCB",
                "ABA"
        );

        recipe.setIngredient('A', Material.DIAMOND);
        recipe.setIngredient('B', Material.ENDER_PEARL);
        recipe.setIngredient('C', Material.ENDER_EYE);

        return recipe;
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, JavaPlugin plugin) {
        Player player = event.getPlayer();

        Sound teleportationSound = Sound.sound(Key.key("entity.enderman.teleport"), Sound.Source.PLAYER, 1f, 2f);
        player.playSound(teleportationSound);

        Block block = player.getTargetBlock(null, 100);
        Location lookingAtLocation = block.getLocation();

        lookingAtLocation.getWorld().spawnParticle(Particle.PORTAL, lookingAtLocation, 100);

        player.sendMessage("Budeš teleportován za 0.5 sekundy!");

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.teleport(lookingAtLocation);
            Sound afterTeleportationSound = Sound.sound(Key.key("entity.hoglin.converted_to_zombified"), Sound.Source.PLAYER, 1f, 2f);
            player.playSound(afterTeleportationSound);
        }, 10); // 1 sekunda = 20 (ticků)
    }

    private void makeItemStackEnchanted(ItemStack itemStack, ItemMeta itemMeta) {
        itemStack.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }
}
