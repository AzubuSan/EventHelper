package me.azubusan.EventHelper.commands.event;

import java.util.List;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.IEventHelperCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class getCommand implements IEventHelperCommand {

	private EventHelper plugin;
	
	public getCommand(EventHelper plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!sender.hasPermission("eh.commands.get") && args.length == 1) {
			sender.sendMessage(ChatColor.RED + "Sorry, you don't have enough permissions.");
			return true;
		}
		
		if (args.length == 1 && args[0].equalsIgnoreCase("name")) {
			if(!plugin.getConfig().contains("events.idname"))  {
				sender.sendMessage(ChatColor.GOLD + "No events found");
				return true;
			}
			List<String> idname = plugin.getConfig().getStringList("events.idname");
			sender.sendMessage(ChatColor.GOLD + "Event name: " + ChatColor.RED + idname);
			return true;
		}
		return true;
	}

	@Override
	public String getUsage() {
		return "/eh get [param]";
	}

	@Override
	public String getPermission() {
		return "eh.commands.get";
	}
	

}
