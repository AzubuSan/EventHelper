package me.azubusan.EventHelper.commands;

import org.bukkit.command.CommandExecutor;


/**
 * 
 * Provides permissions and usage support for commands.
 * 
 * @author AzubuSan
 *
 */
public interface IEventHelperCommand extends CommandExecutor {

	/**
	 * @return Returns usage message for command
	 */
	public String getUsage();
	
	/**
	 *@return The permission.node needed to use the command
	 *returns null if nothing is required
	 */
	public String getPermission();
}
