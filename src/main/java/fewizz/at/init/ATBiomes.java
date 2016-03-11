package fewizz.at.init;

import fewizz.at.world.biome.ATBiome;
import fewizz.at.world.biome.BiomeGenBubble;
import fewizz.at.world.biome.BiomeGenMountain;
import fewizz.at.world.biome.BiomeGenRiver;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ATBiomes {
	public static ATBiome bubble;
	public static ATBiome stoneMount;
	public static ATBiome river;
	
	public static void init() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(bubble = new BiomeGenBubble(), 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(stoneMount = new BiomeGenMountain(), 5));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(river = new BiomeGenRiver(), 5));
	}

}
