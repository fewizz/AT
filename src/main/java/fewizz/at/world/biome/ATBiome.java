package fewizz.at.world.biome;

import fewizz.at.util.ATConfiguration;
import fewizz.at.util.IHasName;
import net.minecraft.world.biome.BiomeGenBase;

public class ATBiome extends BiomeGenBase implements IHasName{
	
	public int biomeID;
	public String name;
	
	public ATBiome(String name) {
		//BiomeGenBase.BiomeProperties properties = new BiomeProperties(name);
		super(new BiomeProperties(name));
		biomeID = ATConfiguration.getBiomeID(name);
		BiomeGenBase.registerBiome(biomeID, name, this);
		this.name = name;
	}
	
	public ATBiome(String name, float height, float var) {
		//BiomeGenBase.BiomeProperties properties = new BiomeProperties(name);
		super(new BiomeProperties(name).setBaseHeight(height).setHeightVariation(var));
		biomeID = ATConfiguration.getBiomeID(name);
		BiomeGenBase.registerBiome(biomeID, name, this);
		this.name = name;
	}
	
	public ATBiome(String name, float height, float var, float temt) {
		//BiomeGenBase.BiomeProperties properties = new BiomeProperties(name);
		super(new BiomeProperties(name).setBaseHeight(height).setHeightVariation(var).setTemperature(temt));
		biomeID = ATConfiguration.getBiomeID(name);
		BiomeGenBase.registerBiome(biomeID, name, this);
		this.name = name;
	}
	
	public ATBiome(String name, BiomeGenBase.BiomeProperties prop) {
		//BiomeGenBase.BiomeProperties properties = new BiomeProperties(name);
		super(prop);
		biomeID = ATConfiguration.getBiomeID(name);
		BiomeGenBase.registerBiome(biomeID, name, this);
		this.name = name;
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
