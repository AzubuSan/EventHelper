package me.azubusan.EventHelper.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.event.getCommand;
import me.azubusan.EventHelper.commands.event.setCommand;
import me.azubusan.EventHelper.commands.misc.ReloadConfigCommand;
import me.azubusan.EventHelper.commands.misc.SudoCommand;

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
	private Map<String, IEventHelperCommand> subCommands = new HashMap<>();

	public EventHelperCommand(EventHelper plugin) {
		subCommands.put("set", new setCommand(plugin));
		subCommands.put("get", new getCommand(plugin));
		subCommands.put("sudo", new SudoCommand(plugin));
		subCommands.put("reload", new ReloadConfigCommand(plugin));

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// It is assumed that entering the menu command without parameters is an
		// attempt to get information about it. So let's give it to them.
		if (args.length == 0) {
			for (IEventHelperCommand eventHelperCommand : subCommands.values()) {
				String permission = eventHelperCommand.getPermission();
				if (permission != null && sender.hasPermission(permission)) {
					sender.sendMessage(ChatColor.GOLD + eventHelperCommand.getUsage());
				}
			}
			return true;
		}

		String subCommandName = args[0];
		IEventHelperCommand eventHelperCommand = subCommands.get(subCommandName
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
		if (!eventHelperCommand.onCommand(sender, cmd, label,
				Arrays.copyOfRange(args, 1, args.length))) {
			// A subCommand returning a false should display usage information
			// for that subCommand
			sender.sendMessage(eventHelperCommand.getUsage());
		}
		return true;
	}
}
