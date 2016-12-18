package com.treyzania.mc.blockparticles;

public interface ConfigSource {
	
	/**
	 * Gets the range at which groups will activate by default.
	 * 
	 * @return The distance, in blocks
	 */
	int getDefaultActivationRange();
	
	/**
	 * The maximum size for groups, or negative for unlimited.
	 * 
	 * @return The size, in blocks
	 */
	int getMaxGroupSize();
	
	/**
	 * @return If there is no limit to block sizes
	 */
	public default boolean isUnlimitedBlockSize() {
		return this.getMaxGroupSize() < 0;
	}
	
	/**
	 * Gets the distance that players have to move before doing updates.
	 * 
	 * @return The update distance.
	 */
	float getUpdateDistance();
	
}
