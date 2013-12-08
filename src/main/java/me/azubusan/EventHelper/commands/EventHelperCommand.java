package me.azubusan.EventHelper.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.event.getCommand;
import me.azubusan.EventHelper.commands.event.setCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * 
 * The base of all EventHelper commands
 * 
 * @author AzubuSan
 * 
 */
public class EventHelperCommand implements CommandExecutor {

	/**
	 * 
	 * Subcommands of the base /EventHelper command
	 * 
	 */
	private Map<String, CommandHandler> subCommands = new HashMap<>();

	public EventHelperCommand(EventHelper plugin) {
		subCommands.put("set", new setCommand(plugin));
		subCommands.put("get", new getCommand(plugin));

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// Entering a command without parameters is seen as a request for
		// command help
		// so lets show that.
		if (args.length == 0) {
			for (CommandHandler eventHelperCommand : subCommands.values()) {
				String permission = eventHelperCommand.getPermission();
				if (permission != null && sender.hasPermission(permission)) {
					sender.sendMessage(eventHelperCommand.getUsage());
				}
			}
			return true;
		}

		String subCommandName = args[0];
		CommandHandler eventHelperCommand = subCommands.get(subCommandName
				.toLowerCase());
		if (eventHelperCommand == null) {
			return false; // Sender mistyped command or command was an invalid
							// subCommand
		}
		// Permissions check handling
		String permission = eventHelperCommand.getPermission();
		if (permission != null && !sender.hasPermission(permission)) {
			sender.sendMessage(ChatColor.RED
					+ "Sorry, you dont have permission to use this command.");
			return true;
		}
		// Remove the subCommand from the args list and pass along the rest
		if(!eventHelperCommand.onCommand(sender, cmd, label, Arrays.copyOfRange(args, 1, args.length))) {
			// A subCommand returning a false should display usage information for that subCommand
			sender.sendMessage(eventHelperCommand.getUsage());
		}
		return true;
	}
}
