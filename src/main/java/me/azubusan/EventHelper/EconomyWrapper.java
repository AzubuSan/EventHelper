package me.azubusan.EventHelper;

import net.milkbowl.vault.economy.Economy;

/**
 * 
 * Vault economy wrapper
 * 
 * @author AzubuSan
 * 
 */

public class EconomyWrapper {

	private Economy economy;

	public EconomyWrapper(Economy economy) {
		this.economy = economy;
	}

	public double getBalance(String playerName) {
		return economy.getBalance(playerName);
	}

	public String format(double amount) {
		return economy.format(amount);
	}

}
