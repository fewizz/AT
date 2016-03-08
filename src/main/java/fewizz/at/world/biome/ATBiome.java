package fewizz.at.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class ATBiome extends BiomeGenBase {
	
	public ATBiome(int id) {
		super(id);
	}

	/** Default: 0.03F*/
	public float getFrequency() {
		return 0.03F;
	}
	
	/** Default: false*/
	public boolean hasMountains() {
		return false;
	}
	
	/** Default: 0.01F*/
	public float getMountainFrequency() {
		return 0.008F;
	}
	
	/** Default: 50F*/
	public float getMountainAmplitude() {
		return 10.0F;
	}
	
	/** Default: 0.6F*/
	public float getMountainOffset() {
		return -0.2F;
	}
}
