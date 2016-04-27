package fewizz.at.init;

import java.util.ArrayList;

import fewizz.at.world.biome.ATBiome;
import fewizz.at.world.biome.BiomeGenBubble;
import fewizz.at.world.biome.BiomeGenGreenHill;
import fewizz.at.world.biome.BiomeGenGreenHillRiver;
import fewizz.at.world.biome.BiomeGenMountain;
import fewizz.at.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ATBiomes {
	public static ATBiome bubble;
	public static ATBiome stoneMount;
	public static ATBiome greenHill;
	public static ATBiome greenHillRiver;
	public static ATBiome river;
	
	public static ArrayList<ATBiome> biomeIDs = new ArrayList<ATBiome>();
	
	public static void init() {
		bubble = register(new BiomeGenBubble());
		greenHill = register(new BiomeGenGreenHill());
		greenHillRiver = new BiomeGenGreenHillRiver();
		stoneMount = new BiomeGenMountain();
		river = new BiomeGenRiver();
		
		ArrayList list = new ArrayList<Integer>();
		list.add(bubble);
	}
	
	public static ATBiome register(ATBiome biome) {
		biomeIDs.add(biome);
		return biome;
	}

}
