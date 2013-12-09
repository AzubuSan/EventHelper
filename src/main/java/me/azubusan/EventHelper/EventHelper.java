package me.azubusan.EventHelper;

import java.io.IOException;

import me.azubusan.EventHelper.commands.EventHelperCommand;
import me.azubusan.EventHelper.listeners.DevLoginListener;
import me.azubusan.EventHelper.util.Metrics;
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
	private String WEurl = " http://:dev.bukkit.org/bukkit-plugins/worldedit ";

	@Override
	public void onEnable() {

		if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
			this.getLogger().info(
					"WorldEdit not found! Download it at" + WEurl
							+ "Plugin cannot load until it is installed!");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {

			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				// Failed to submit the stats :-(
			}

			saveDefaultConfig();

			new BukkitRunnable() {
				@Override
				public void run() {
					setupEconomy();
				}
			}.runTaskLater(this, 20);

			// Register External Listeners
			new DevLoginListener().registerEvents(this);

			// Register Commands from external Classes
			getCommand("eventhelper").setExecutor(new EventHelperCommand(this));
		}
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
