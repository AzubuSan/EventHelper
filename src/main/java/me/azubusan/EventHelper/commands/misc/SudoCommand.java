package me.azubusan.EventHelper.commands.misc;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.IEventHelperCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author AzubuSan
 * 
 */
public class SudoCommand implements IEventHelperCommand {

	private EventHelper plugin;

	public SudoCommand(EventHelper plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!sender.hasPermission("eh.commands.sudo")) {
			sender.sendMessage(ChatColor.RED
					+ "Sorry, you don't have enough permissions to use this command.");
			return true;
		}

		if (args.length == 0 || args.length == 1
				&& args[0].equalsIgnoreCase("help")) {
			return false;
		}

		if (args.length < 2) {
			return false;
		}

		String targetName = args[0];
		Player target = plugin.getServer().getPlayer(targetName);
			if (target == null) {
				sender.sendMessage(ChatColor.RED + targetName
						+ " is not online!");
				return true;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]);
				if (i < args.length) {
					sb.append(" ");
				}
			}
			sender.sendMessage(ChatColor.RED + "You forced " + targetName
					+ " to run the command " + "/" + args[1]);
			plugin.getServer().dispatchCommand(target, sb.toString());
			return true;
		}

	@Override
	public String getUsage() {
		// // Shown when return is false
		return "/eh sudo [player] [commandtosudo]";
	}

	@Override
	public String getPermission() {
		return "eh.commands.sudo";
	}

	}
