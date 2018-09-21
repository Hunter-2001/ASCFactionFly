package com.ascendpvp.ASCFactionFly;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ascendpvp.ASCFactionFly.events.CancelPearl;
import com.ascendpvp.ASCFactionFly.events.LoginFly;
import com.ascendpvp.ASCFactionFly.events.PlayerLocationUpdate;
import com.ascendpvp.ASCFactionFly.utils.Helpers;

public class ASCFactionFlyMain extends JavaPlugin {

	private Helpers help;

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new LoginFly(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLocationUpdate(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelPearl(this), this);
		help = new Helpers(this);
		saveDefaultConfig();
	}
	public Helpers help() {
		return this.help;
	}
}
