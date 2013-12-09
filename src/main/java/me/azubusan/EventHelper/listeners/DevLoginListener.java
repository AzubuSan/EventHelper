package me.azubusan.EventHelper.listeners;

import me.azubusan.EventHelper.EventHelper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class DevLoginListener implements Listener {
	
	@SuppressWarnings("unused")
	private EventHelper plugin;
	
	public void registerEvents(EventHelper plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDevLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if(player.getName().equals("AzubuSan") || player.getName().equals("Travis506")) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "A plugin Developer has logged on, it was " + ChatColor.DARK_PURPLE + player.getName());
		}
	}

}
