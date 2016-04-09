package fewizz.at.world.biome;

import fewizz.at.util.ATConfiguration;
import fewizz.at.util.IHasName;
import net.minecraft.world.biome.BiomeGenBase;

public class ATBiome extends BiomeGenBase implements IHasName{
	
	public int biomeID;
	public String name;
	
	public ATBiome(String name, BiomeGenBase.BiomeProperties prop) {
		super(prop);
		biomeID = ATConfiguration.getBiomeID(name);
		BiomeGenBase.registerBiome(biomeID, name, this);
		this.name = name;
	}
	
	public ATBiome(String name) {
		this(name, new BiomeProperties(name));
	}
	
	public ATBiome(String name, float temp) {
		this(name, new BiomeProperties(name).setTemperature(temp));
	}
	
	public ATBiome(String name, float height, float var) {
		this(name, new BiomeProperties(name).setBaseHeight(height).setHeightVariation(var));
	}
	
	public ATBiome(String name, float height, float var, float temt) {
		this(name, new BiomeProperties(name).setBaseHeight(height).setHeightVariation(var).setTemperature(temt));
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

	@Override
	public String getName() {
		return name;
	}
}
