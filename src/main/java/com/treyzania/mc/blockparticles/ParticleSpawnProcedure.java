package com.treyzania.mc.blockparticles;

import java.util.List;
import java.util.Random;

import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.World;

import com.treyzania.mc.blockparticles.data.ParticleGroup;
import com.treyzania.mc.blockparticles.data.Point3i;
import com.treyzania.mc.blockparticles.data.WorldData;

public class ParticleSpawnProcedure implements Runnable {
	
	private Random r = new Random();
	
	private DataManager manager;
	private Server server;
	
	public ParticleSpawnProcedure(DataManager man, Server serv) {
		
		this.manager = man;
		this.server = serv;
		
	}
	
	@Override
	public void run() {
		
		List<World> ws = this.server.getWorlds();
		
		for (World w : ws) {
			
			WorldData wd = this.manager.getWorldData(w);
			if (wd == null) continue;
			if (wd.getGroups() == null) continue;
			
			for (ParticleGroup pg : wd.getGroups().values()) {
				
				if (!pg.isActive()) continue;
				
				Particle type = pg.getType();
				int density = pg.getDensity();
				
				for (Point3i block : pg.getPoints()) {
					w.spawnParticle(type, block.x, block.y, block.z, density, r.nextDouble(), r.nextDouble(), r.nextDouble(), 0D, null);
				}
				
			}
			
		}
		
	}
	
}
