package com.ascendpvp.ASCFactionFly.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ascendpvp.ASCFactionFly.ASCFactionFlyMain;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

public class LoginFly implements Listener {
	
	private ASCFactionFlyMain plugin;
	public LoginFly(ASCFactionFlyMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Faction pFac = MPlayer.get(p).getFaction();
		
		if(!p.hasPermission("ascfly.fly")) return;
		if(pFac.getName().contains("Wilderness")) return;
		if(!plugin.help().inAllowedLand(p)) return;
		
		//Runnable is in place to allow time for player to load into server before enabling flight.
		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			public void run() {
				p.setAllowFlight(true);
				p.sendMessage(plugin.help().cc(plugin.getConfig().getString("messages.fly_enable")));
			};
		}, 20);
	}
}
