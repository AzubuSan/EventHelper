package me.azubusan.EventHelper.commands.event;

import java.util.Arrays;
import java.util.List;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.CommandHandler;
import me.azubusan.EventHelper.util.RewardsMenu;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * EventHandler command to set parameters
 * 
 * @author AzubuSan
 *
 */

public class setCommand implements CommandHandler {
	
	private RewardsMenu menu;
	private EventHelper plugin;

	public setCommand(EventHelper plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// Entering the command without parameters is handled as a request for
		// help
		// So lets show them that
		if (args.length == 0) {
			sender.sendMessage(ChatColor.AQUA + "Event Helper Help Version " + ChatColor.GRAY + "[" + ChatColor.RED + plugin.getDescription().getVersion() + ChatColor.GRAY + "] " + ChatColor.AQUA + "by AzubuSan" );
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set eventarea " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Sets Event Area with worldedit wand");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set rewards " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Sets event end rewards");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set mobspawns " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Set the mob spawnpoints");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set time [time] " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Use this if you want the event to end after a time");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set name [name] " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Set the event name");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set finished " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Set the event to finished, this hands out all rewards");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set start " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Start the event");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set broadcasts [broadcast,broadcast] " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Adds broadcasts for event");
			sender.sendMessage(ChatColor.GOLD + "/eventhelper set type [type] " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Set event type (Timed or Manual)");
			return true;
		}
		
		if(args.length == 1 && !sender.hasPermission("eh.commands.set")) {
			sender.sendMessage(ChatColor.RED + "Sorry, you don't have enough permissions");
			return true;
		}
		if (args.length == 1 && args[0].equalsIgnoreCase("rewards") || args.length == 2 && args[0].equalsIgnoreCase("rewards") && args[1].equalsIgnoreCase("help")) {
			if (args.length == 1) {
				Player player = (Player) sender;
				menu.show(player);
				return true;
			}

			if (args.length == 2) {
				return false;
			}
			return true;
		}
			if(args.length == 1 && args[0].equalsIgnoreCase("name")) {
				sender.sendMessage(ChatColor.GOLD + "Usage: /eventhelper set name [id] [name] " + ChatColor.DARK_PURPLE + "|" + ChatColor.RED + " Set the event name. WARNING - Name & ID cannot include spaces!");
				return true;
			}
			
			if(args.length == 3) {
				plugin.getConfig().createSection("events.idname");
				List<String> idname = Arrays.asList(args[1], args[2]);
				plugin.getConfig().set("events.idname", idname);
				sender.sendMessage(ChatColor.GOLD + "Event name and ID set to: " + ChatColor.RED + args[1] + ChatColor.GRAY +" | " + ChatColor.RED + args[2] + ChatColor.GOLD + "!");
				plugin.saveConfig();
				return true;
			}
			if(args.length > 3) {
				return false;
			}
			return true;
		}

	@Override
	public String getUsage() { // Displays usage info if return false;
		return "/eventhelper set [param] - Sets event parameters";
	}

	@Override
	public String getPermission() { // Displays permission needed for usage of
									// command if sender.hasPermission() is null
		return "eh.commands.set";
	}

}
