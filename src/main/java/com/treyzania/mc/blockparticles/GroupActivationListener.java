package com.treyzania.mc.blockparticles;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.treyzania.mc.blockparticles.data.ParticleGroup;
import com.treyzania.mc.blockparticles.data.Point3i;
import com.treyzania.mc.blockparticles.data.WorldData;

public class GroupActivationListener implements Listener {
	
	private DataManager manager;
	private double minUpdateDistanceSq;
	
	private Map<Player, Location> lastUpdateLocations = new HashMap<>();
	
	public GroupActivationListener(DataManager man, double minDist) {
		
		this.manager = man;
		this.minUpdateDistanceSq = Math.pow(minDist, 2);
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		
		Player p = event.getPlayer();
		Location loc = p.getLocation();
		Location lastUpdate = this.lastUpdateLocations.get(p);
		
		if (lastUpdate == null || loc.distanceSquared(lastUpdate) > this.minUpdateDistanceSq) {
			
			this.lastUpdateLocations.put(p, loc);
			this.updatePositions(p);
			
		}
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player p = event.getPlayer();
		
		this.lastUpdateLocations.put(p, p.getLocation());
		this.updatePositions(p);
		
	}
	
	private void updatePositions(Player p) {
		
		World w = p.getWorld();
		Location loc = p.getLocation();
		
		for (ParticleGroup pg : this.manager.getWorldData(w).getGroups().values()) {
			
			Point3i ctr = pg.getCenter();
			if (ctr == null) continue;
			boolean inRange = loc.distanceSquared(new Location(w, ctr.x, ctr.y, ctr.z)) < Math.pow(pg.getRange(), 2);
			
			// Not too clean, but works well.
			if (inRange) {
				pg.nearbyPlayers.add(p);
			} else {
				pg.nearbyPlayers.remove(p);
			}
			
		}
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		Player p = event.getPlayer();
		WorldData wd = this.manager.getWorldData(p.getWorld());
		
		this.lastUpdateLocations.remove(p);
		for (ParticleGroup pg : wd.getGroups().values()) {
			pg.nearbyPlayers.remove(p);
		}
		
	}
	
}
