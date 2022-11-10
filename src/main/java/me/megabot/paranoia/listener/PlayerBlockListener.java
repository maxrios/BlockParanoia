package me.megabot.paranoia.listener;

import me.megabot.paranoia.config.ParanoiaConfig;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerBlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!ParanoiaConfig.isPluginEnabled() ||
                !ParanoiaConfig.isPlayerParanoid(event.getPlayer().getUniqueId()))
            return;

        int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
        if (randomNum > ParanoiaConfig.getPercentageChance()) return;

        Block block = event.getBlock();
        block.getWorld().spawnEntity(block.getLocation(), ParanoiaConfig.getRandomEntity());
    }
}
