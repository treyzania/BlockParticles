package com.treyzania.mc.blockparticles;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.treyzania.mc.blockparticles.data.Point3i;

public class BlockPlacementListener implements Listener {
	
	private DataManager manager;
	
	public BlockPlacementListener(DataManager man) {
		this.manager = man;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if (event.isCancelled()) return;
		
		Player p = event.getPlayer();
		World w = p.getWorld();
		
		PlacementSession ps = this.manager.getSession(p);
		
		if (ps != null && ps.worldData.getWorld() == w) {
			
			Block b = event.getBlock();
			Point3i pos = new Point3i(b.getX(), b.getY(), b.getZ());
			
			ps.group.addPoint(pos);
			event.setCancelled(true);
			
		}
		
	}
	
}
