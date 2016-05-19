package fewizz.at.init;

import java.util.ArrayList;

import fewizz.at.world.biome.ATBiome;
import fewizz.at.world.biome.BiomeGenBubble;
import fewizz.at.world.biome.BiomeGenCandy;
import fewizz.at.world.biome.BiomeGenGreenHill;
import fewizz.at.world.biome.BiomeGenGreenHillRiver;
import fewizz.at.world.biome.BiomeGenMountain;
import fewizz.at.world.biome.BiomeGenRiver;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ATBiomes {
	public static ATBiome greenHillRiver;
	public static ATBiome river;
	public static ATBiome stoneMount;
	public static ATBiome bubble;
	public static ATBiome greenHill;
	public static ATBiome candy;
	
	public static ArrayList<ATBiome> biomeIDs = new ArrayList<ATBiome>();
	
	public static void init() {
		/** Technical **/
		greenHillRiver = new BiomeGenGreenHillRiver();
		stoneMount = new BiomeGenMountain();
		river = new BiomeGenRiver();
		
		/** Base **/
		bubble = register(new BiomeGenBubble());
		greenHill = register(new BiomeGenGreenHill());
		candy = register(new BiomeGenCandy());
	}
	
	public static ATBiome register(ATBiome biome) {
		biomeIDs.add(biome);
		return biome;
	}

}
