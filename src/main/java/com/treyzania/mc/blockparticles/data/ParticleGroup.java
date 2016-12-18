package com.treyzania.mc.blockparticles.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleGroup {
	
	private Particle type;
	private float range = 0;
	private int density = 1;
	
	private List<Point3i> points = new ArrayList<>();;
	
	private transient Runnable flushCallback;
	public transient Set<Player> nearbyPlayers = new HashSet<>();
	
	public void setType(Particle p) {
		this.type = p;
	}
	
	public Particle getType() {
		return this.type;
	}
	
	public void setRange(float r) {
		this.range = r;
	}
	
	public float getRange() {
		return this.range;
	}
	
	public void setDensity(int d) {
		this.density = d;
	}
	
	public int getDensity() {
		return this.density;
	}
	
	public void addPoint(Point3i p) {
		this.points.add(p);
	}
	
	public List<Point3i> getPoints() {
		return new ArrayList<>(this.points);
	}
	
	public Point3i getCenter() {
		
		int num = this.getNumPoints();
		if (num == 0) return null;
		
		int x = 0;
		int y = 0;
		int z = 0;
		
		for (Point3i p : this.points) {
			
			x += p.x;
			y += p.y;
			z += p.z;
			
		}
		
		return new Point3i(x / num, y / num, z / num);
		
	}
	
	public int getNumPoints() {
		return this.points.size();
	}
	
	public void setFlushCallback(Runnable cb) {
		this.flushCallback = cb;
	}
	
	public void flush() {
		this.flushCallback.run();
	}
	
	public boolean isActive() {
		return this.nearbyPlayers.size() > 0;
	}
	
}
