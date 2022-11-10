package me.megabot.paranoia;

import me.megabot.paranoia.executor.ParanoiaConfigExecutor;
import me.megabot.paranoia.listener.PlayerBlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Paranoia extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerBlockListener(), this);
        getServer().getPluginCommand("paranoia").setExecutor(new ParanoiaConfigExecutor());
    }

    @Override
    public void onDisable() {
    }
}
