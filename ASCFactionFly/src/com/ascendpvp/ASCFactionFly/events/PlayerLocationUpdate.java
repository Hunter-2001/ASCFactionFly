package com.ascendpvp.ASCFactionFly.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.ascendpvp.ASCFactionFly.ASCFactionFlyMain;

public class PlayerLocationUpdate implements Listener {
	
	ASCFactionFlyMain plugin;
	public PlayerLocationUpdate(ASCFactionFlyMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerLoc(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;
		
		if(plugin.help().pNear(p)) {
			if(p.getAllowFlight() == false) return;
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(plugin.help().cc(plugin.getConfig().getString("messages.enemy_nearby")));
			return;
		}
		
		if(plugin.help().inAllowedLand(p)) {
			if(p.getAllowFlight() == true) return;
			p.setAllowFlight(true);
			p.sendMessage(plugin.help().cc(plugin.getConfig().getString("messages.fly_enable")));
		} else {
			if(p.getAllowFlight() == false) return;
			p.setFlying(false);
			p.setAllowFlight(false);
			p.sendMessage(plugin.help().cc(plugin.getConfig().getString("messages.fly_disable")));
		}
	}
}
