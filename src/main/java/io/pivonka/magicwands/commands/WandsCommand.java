package io.pivonka.magicwands.commands;

import io.pivonka.magicwands.wands.WandInterface;
import io.pivonka.magicwands.wands.WandsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WandsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
        @NotNull CommandSender sender,
        @NotNull Command command,
        @NotNull String label,
        @NotNull String[] args
    ) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Please specify a wand name!");
            player.sendMessage("Available wands: " + WandsManager.getAvailableWandsAsString());
            return false;
        }

        String requestedWandName = args[0];
        WandInterface wand = WandsManager.getWandByName(args[0]);

        if (wand == null) {
            player.sendMessage("Wand " + requestedWandName + " does not exist!");
            return false;
        }

        WandsManager.giveWandToPlayer(wand, player);

        return true;
    }
}
