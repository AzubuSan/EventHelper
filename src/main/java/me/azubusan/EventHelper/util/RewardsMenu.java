package me.azubusan.EventHelper.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * 
 * The menu shown when executing the /eh set rewards command
 * 
 * @author AzubuSan
 *
 */
public class RewardsMenu implements Listener {
	
	private Inventory inv;
	
	public RewardsMenu(Plugin p) {
		inv = Bukkit.getServer().createInventory(null, 9, ChatColor.GOLD + "Event Rewards");
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
	}
	
	public void show(Player p) {
		p.openInventory(inv);
	}
}
