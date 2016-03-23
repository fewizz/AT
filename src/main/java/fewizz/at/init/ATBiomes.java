package fewizz.at.init;

import java.util.ArrayList;

import fewizz.at.world.biome.ATBiome;
import fewizz.at.world.biome.BiomeGenBubble;
import fewizz.at.world.biome.BiomeGenMountain;
import fewizz.at.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ATBiomes {
	public static ATBiome bubble;
	public static ATBiome stoneMount;
	public static ATBiome river;
	
	public static void init() {
		bubble = new BiomeGenBubble();
		stoneMount = new BiomeGenMountain();
		river = new BiomeGenRiver();
	}

}
