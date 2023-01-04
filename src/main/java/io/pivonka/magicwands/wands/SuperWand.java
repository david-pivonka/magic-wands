package io.pivonka.magicwands.wands;

import io.pivonka.magicwands.helpers.ParticlesHelper;
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
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperWand implements WandInterface {

    @Override
    public String getWandName() {
        return "super";
    }

    @Override
    public ItemStack createWandItem() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component itemName = Component
                .text("Mega Super Hůlka")
                .color(TextColor.fromCSSHexString("#C91431"))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(itemName);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public Recipe getWandRecipe() {
        NamespacedKey namespacedKey = new NamespacedKey("magicwands", "super_wand");
        ItemStack itemStack = this.createWandItem();
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, itemStack);

        recipe.shape(
            "ABA",
            "BCB",
            "ABA"
        );

        recipe.setIngredient('A', Material.DIAMOND);
        recipe.setIngredient('B', Material.EMERALD);
        recipe.setIngredient('C', Material.BLAZE_ROD);

        return recipe;
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, JavaPlugin plugin) {
        Player player = event.getPlayer();

        Sound boomSound = Sound.sound(Key.key("entity.dragon_fireball.explode"), Sound.Source.PLAYER, 1f, 1.1f);
        player.playSound(boomSound);

        ParticlesHelper.shootParticle(player, Particle.FLAME, 1);

        Block block = player.getTargetBlock(null, 100);
        Location lookingAtLocation = block.getLocation();

        player
            .getWorld()
            .createExplosion(
                lookingAtLocation.getX(),
                lookingAtLocation.getY(),
                lookingAtLocation.getZ(),
                5f,
                true,
                true
            );

        player
            .getWorld()
            .strikeLightning(lookingAtLocation);
    }
}
