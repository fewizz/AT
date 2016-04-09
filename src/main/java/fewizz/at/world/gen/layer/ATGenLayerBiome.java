package fewizz.at.world.gen.layer;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

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
import net.minecraftforge.common.BiomeManager;

/** WARNING! Copypaste! **/
public class ATGenLayerBiome extends GenLayer {
	
	private List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];
	private final ChunkProviderSettings chunkProviderSettings;

	public ATGenLayerBiome(long seed, GenLayer layer, WorldType worldType, String settings) {
		super(seed);
		this.parent = layer;

		for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
			ImmutableList<BiomeManager.BiomeEntry> biomesToAdd = BiomeManager.getBiomes(type);
			int idx = type.ordinal();

			if (biomes[idx] == null)
				biomes[idx] = new ArrayList<BiomeManager.BiomeEntry>();
			if (biomesToAdd != null)
				biomes[idx].addAll(biomesToAdd);
		}

		int desertIdx = BiomeManager.BiomeType.DESERT.ordinal();

		biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.desert, 30));
		biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.savanna, 20));
		biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.plains, 10));

		if (worldType == WorldType.DEFAULT_1_1) {
			biomes[desertIdx].clear();
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.desert, 10));
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.forest, 10));
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.extremeHills, 10));
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.swampland, 10));
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.plains, 10));
			biomes[desertIdx].add(new BiomeManager.BiomeEntry(Biomes.taiga, 10));
			this.chunkProviderSettings = null;
		}
		else if (worldType == WorldType.CUSTOMIZED) {
			this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(settings).func_177864_b();
		}
		else {
			this.chunkProviderSettings = null;
		}
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] parent = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
		int[] arrayToFill = IntCache.getIntCache(areaWidth * areaHeight);

		for (int z = 0; z < areaHeight; ++z) {
			for (int x = 0; x < areaWidth; ++x) {
				this.initChunkSeed((long) (x + areaX), (long) (z + areaY));
				int k = parent[x + z * areaWidth];
				if(k < 2) {
					arrayToFill[x + z * areaWidth] = ATBiomes.bubble.biomeID;
				}
				else {
					arrayToFill[x + z * areaWidth] = ATBiomes.greenHill.biomeID;
				}
			}

		}

		return arrayToFill;

	}

	protected BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type) {
		List<BiomeManager.BiomeEntry> biomeList = biomes[type.ordinal()];
		int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
		int weight = BiomeManager.isTypeListModded(type) ? nextInt(totalWeight) : nextInt(totalWeight / 10) * 10;
		return (BiomeManager.BiomeEntry) net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
	}
}
