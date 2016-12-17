package com.treyzania.mc.blockparticles.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Particle;

public class ParticleGroup {
	
	private Particle type;
	private int range = 0;
	private int density = 1;
	
	private List<Point3i> points = new ArrayList<>();;
	
	public void setType(Particle p) {
		this.type = p;
	}
	
	public Particle getType() {
		return this.type;
	}
	
	public void setRange(int r) {
		this.range = r;
	}
	
	public int getRange() {
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
	
}
