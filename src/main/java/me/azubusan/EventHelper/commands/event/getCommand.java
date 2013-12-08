package me.azubusan.EventHelper.commands.event;

import me.azubusan.EventHelper.EventHelper;
import me.azubusan.EventHelper.commands.CommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class getCommand implements CommandHandler {

	@SuppressWarnings("unused")
	private EventHelper plugin;
	
	public getCommand(EventHelper plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
