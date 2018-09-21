package com.ascendpvp.ASCFactionFly.utils;

import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.ascendpvp.ASCFactionFly.ASCFactionFlyMain;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class Helpers {

	public String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	private ASCFactionFlyMain plugin;
	public Helpers(ASCFactionFlyMain plugin) {
		this.plugin = plugin;
	}

	public boolean pNear(Player p) {
		if (!plugin.getConfig().getBoolean("radius_disable")) return false;
		//Math to determine radius
		Collection<Entity> nearbyEntity = p.getNearbyEntities(plugin.getConfig().getInt("radius"), 256.0D, plugin.getConfig().getInt("radius"));
		if(nearbyEntity.size() == 0) return false;
		for(Entity e : nearbyEntity) {
			if(!(e instanceof Player)) continue;
			Player p2 = (Player) e;
			if(p2.getName().toLowerCase().contains("pvplogger")) continue;
			Faction ownFac = MPlayer.get(p).getFaction();
			Faction otherFac = MPlayer.get(p2).getFaction();
			if(ownFac.getRelationTo(otherFac) != Rel.ENEMY) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean inAllowedLand(Player p) {
		MPlayer mPlayer = MPlayer.get(p);
		Faction fac = BoardColl.get().getFactionAt(PS.valueOf(p.getLocation()));
		Faction ownFac = MPlayer.get(p).getFaction();

		if ((mPlayer.isInOwnTerritory()) && (p.hasPermission("ascfly.own")) && (!ownFac.getName().contains("Wilderness"))) return true;
		if ((fac.getRelationTo(ownFac) == Rel.ALLY) && (p.hasPermission("ascfly.ally"))) return true;
		if ((mPlayer.isInEnemyTerritory()) && (p.hasPermission("ascfly.enemy"))) return true;
		if ((fac.getName().contains("SafeZone")) && (p.hasPermission("ascfly.safezone"))) return true;
		if ((fac.getRelationTo(ownFac) == Rel.TRUCE) && (p.hasPermission("ascfly.truce"))) return true;
		return false;
	}
}
