package fewizz.at.util;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import scala.util.control.Exception;

public class ATConfiguration {
	public static Configuration config;

	public static int dimID;

	public static void init(File file) {
		config = new Configuration(file);

		dimID = config.getInt("dimensionID", "GENERAL", DimensionManager.getNextFreeDimId(), 0, 256, "");

		config.save();
	}
	
	public static int getBiomeID(String biomeName) {
		int id = config.getInt(biomeName + "biomeID", "GENERAL", getFreeBiomeID(), 0, 256, "");
		config.save();
		return id;
	}
	
	public static int getFreeBiomeID() {
		for(int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
			if(BiomeGenBase.getBiome(i) == null) {
				return i;
			}
		}
		
		throw new UnsupportedOperationException("No available id for biome =( ");
	}
}
