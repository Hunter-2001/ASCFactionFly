package com.ascendpvp.ASCFactionFly.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.ascendpvp.ASCFactionFly.ASCFactionFlyMain;

public class CancelPearl implements Listener {

	ASCFactionFlyMain plugin;
	public CancelPearl(ASCFactionFlyMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPeal(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.getConfig().getBoolean("allow_pearl_while_flying")) return;
		if(e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL && p.isFlying()) {
			e.setCancelled(true);
			p.sendMessage(plugin.help().cc(plugin.getConfig().getString("messages.cancel_pearl")));
		}
	}
	
}
