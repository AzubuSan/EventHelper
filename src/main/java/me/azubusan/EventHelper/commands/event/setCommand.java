package me.azubusan.EventHelper.commands.event;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.CommandHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class setCommand implements CommandHandler {

	@SuppressWarnings("unused")
	private EventHelper plugin;

	public setCommand(EventHelper plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// Entering the command without parameters is handled as a request for help
		// So lets show them that
		if(args.length == 0) {
			sender.sendMessage(ChatColor.GOLD + "/eventhandler set [eventarea,teleports,mobspawns,rewards,time,goal,name,finished,start] - Sets event parameters; After every ',' is a new parameter");
			return true;
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("rewards") || args.length == 2 && args[0].equalsIgnoreCase("rewards") && args[1].equalsIgnoreCase("help")) {
			if (args.length == 1) {
				sender.sendMessage("Just testing!");
				return true;
			}
			
			if(args.length == 2) {
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public String getUsage() { // Displays usage info if return false;
		return "/eventhandler set rewards - Opens an inventory in which you place rewards to be handed to players after the given event ends.";
	}

	@Override
	public String getPermission() { // Displays permission needed for usage of command if sender.hasPermission() is null
		return "eh.commands.set.rewards";
	}

}
