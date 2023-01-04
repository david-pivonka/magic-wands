package io.pivonka.magicwands.helpers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticlesHelper {
    public static void shootParticle(Player player, Particle particle, double velocity) {
        Location location = player.getEyeLocation();
        Vector direction = location.getDirection();

        player
            .getWorld()
            .spawnParticle(
                particle,
                location.getX(),
                location.getY(),
                location.getZ(),
                0,
                (float) direction.getX(),
                (float) direction.getY(),
                (float) direction.getZ(),velocity,
                null
            );
    }
}
