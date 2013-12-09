package me.azubusan.EventHelper.commands.misc;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.IEventHelperCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements IEventHelperCommand {

	private EventHelper plugin;

	public ReloadConfigCommand(EventHelper plugin) {
		this.plugin = plugin;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			plugin.saveConfig();
			sender.sendMessage(ChatColor.GOLD + "EventHelper Config Saved");
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GOLD + "EventHelper Config Reloaded");
			plugin.saveConfig();
			sender.sendMessage(ChatColor.GOLD + "EventHelper Config Saved And Successfully Reloaded!");
			return true;
		}
		if (args.length > 0) {
			return false;
		}
		return true;
	}

	@Override
	public String getUsage() {
		// Shown when return is false
		return "/eh reload - Reloads and saves the config";
	}

	@Override
	public String getPermission() {
		return "eh.commands.reloadconfig";
	}

}
