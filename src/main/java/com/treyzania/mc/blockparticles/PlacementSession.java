package com.treyzania.mc.blockparticles;

import com.treyzania.mc.blockparticles.data.ParticleGroup;
import com.treyzania.mc.blockparticles.data.WorldData;

public class PlacementSession {
	
	public final WorldData worldData;
	public final ParticleGroup group;
	
	public PlacementSession(WorldData wd, ParticleGroup g) {
		
		this.worldData = wd;
		this.group = g;
		
	}
	
	public void flush() {
		this.group.flush();
	}
	
}
