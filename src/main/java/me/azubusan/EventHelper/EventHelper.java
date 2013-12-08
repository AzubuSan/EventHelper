package me.azubusan.EventHelper;

import me.azubusan.EventHelper.commands.EventHelperCommand;
import me.azubusan.EventHelper.commands.SudoCommand;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 
 * A plugin designed to make running events easier.
 * 
 * @author AzubuSan
 * 
 */

public class EventHelper extends JavaPlugin {
	
	private EconomyWrapper economy = null;

	@Override
	public void onEnable() {
		
		if (getServer().getPluginManager().getPlugin("Worldedit") == null) {
			this.getLogger().info("Worldedit not found! Plugin cannot load!");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		saveDefaultConfig();

		new BukkitRunnable() {
			@Override
			public void run() {
				setupEconomy();
			}
		}.runTaskLater(this, 20);

		// Register Commands from external Classes.
		getCommand("eventhelper").setExecutor(new EventHelperCommand(this));
		getCommand("sudo").setExecutor(new SudoCommand(this));
	}

	private void setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return;
		}
		@SuppressWarnings("unused")
		EconomyWrapper economy = new EconomyWrapper(rsp.getProvider());
	}

	public EconomyWrapper getEconomy() {
		return economy;
	}
}
