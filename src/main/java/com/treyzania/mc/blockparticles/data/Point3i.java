package com.treyzania.mc.blockparticles.data;

public final class Point3i {
	
	public final int x, y, z;
	
	public Point3i(int x, int y, int z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public Point3i add(Point3i o) {
		return new Point3i(this.x + o.x, this.y + o.y, this.z + o.z);
	}
	
}
