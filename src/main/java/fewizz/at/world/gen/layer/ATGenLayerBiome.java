package fewizz.at.world.gen.layer;

import fewizz.at.init.ATBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.IntCache;

public class ATGenLayerBiome extends GenLayer {

	@SuppressWarnings("unchecked")
	private java.util.List<net.minecraftforge.common.BiomeManager.BiomeEntry>[] biomes = new java.util.ArrayList[net.minecraftforge.common.BiomeManager.BiomeType.values().length];

	private final ChunkProviderSettings chunkProviderSettings;

	public ATGenLayerBiome(long seed, GenLayer layer, WorldType worldType, String p_i45560_5_) {
		super(seed);
		this.parent = layer;

		for (net.minecraftforge.common.BiomeManager.BiomeType type : net.minecraftforge.common.BiomeManager.BiomeType.values()) {
			com.google.common.collect.ImmutableList<net.minecraftforge.common.BiomeManager.BiomeEntry> biomesToAdd = net.minecraftforge.common.BiomeManager.getBiomes(type);
			int idx = type.ordinal();

			if (biomes[idx] == null)
				biomes[idx] = new java.util.ArrayList<net.minecraftforge.common.BiomeManager.BiomeEntry>();
			if (biomesToAdd != null)
				biomes[idx].addAll(biomesToAdd);
		}

		int desertIdx = net.minecraftforge.common.BiomeManager.BiomeType.DESERT.ordinal();

		biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.desert, 30));
		biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.savanna, 20));
		biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.plains, 10));

		if (worldType == WorldType.DEFAULT_1_1) {
			biomes[desertIdx].clear();
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.desert, 10));
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.forest, 10));
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.extremeHills, 10));
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.swampland, 10));
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.plains, 10));
			biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.taiga, 10));
			this.chunkProviderSettings = null;
		}
		else if (worldType == WorldType.CUSTOMIZED) {
			this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(p_i45560_5_).func_177864_b();
		}
		else {
			this.chunkProviderSettings = null;
		}
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] arrayToFill = IntCache.getIntCache(areaWidth * areaHeight);

		for (int i = 0; i < areaHeight; ++i) {
			for (int j = 0; j < areaWidth; ++j) {
				this.initChunkSeed((long) (j + areaX), (long) (i + areaY));
				arrayToFill[j + i * areaWidth] = ATBiomes.bubble.biomeID;
			}

		}

		return arrayToFill;

	}

	protected net.minecraftforge.common.BiomeManager.BiomeEntry getWeightedBiomeEntry(net.minecraftforge.common.BiomeManager.BiomeType type) {
		java.util.List<net.minecraftforge.common.BiomeManager.BiomeEntry> biomeList = biomes[type.ordinal()];
		int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
		int weight = net.minecraftforge.common.BiomeManager.isTypeListModded(type) ? nextInt(totalWeight) : nextInt(totalWeight / 10) * 10;
		return (net.minecraftforge.common.BiomeManager.BiomeEntry) net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
	}
}
