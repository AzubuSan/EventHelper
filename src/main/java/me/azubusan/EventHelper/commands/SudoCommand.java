package me.azubusan.EventHelper.commands;

import me.azubusan.EventHelper.EventHelper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author AzubuSan
 * 
 */
public class SudoCommand implements CommandExecutor {

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
			sender.sendMessage(ChatColor.GOLD
					+ "Used to force another play to run a command. Usage: "
					+ ChatColor.GREEN
					+ "/<command> [help] OR <playername> <commandtosudo> <- No slash in argument");
			return true;
		}

		if (args.length < 2) {
			return false;
		}

		String targetName = args[0];
		Player target = plugin.getServer().getPlayer(targetName);
		if (target == null) {
			sender.sendMessage(ChatColor.RED + targetName + " is not online!");
			return true;
		}
		
		StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length) {
                sb.append(" ");
            }
        }
		
        plugin.getServer().dispatchCommand(target, sb.toString());
		return true;
	}

}
