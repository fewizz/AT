package fewizz.at.world.gen.layer;

import fewizz.at.init.ATBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class ATGenLayerRiverMix extends GenLayer {
	private GenLayer biomeLayer;
	private GenLayer riverLayer;

	public ATGenLayerRiverMix(long seed, GenLayer biomeLayer, GenLayer riverLayer) {
		super(seed);
		this.biomeLayer = biomeLayer;
		this.riverLayer = riverLayer;
	}

	public void initWorldGenSeed(long seed) {
		this.biomeLayer.initWorldGenSeed(seed);
		this.riverLayer.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}

	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomeIDs = this.biomeLayer.getInts(areaX, areaY, areaWidth, areaHeight);
		int[] riverIDs = this.riverLayer.getInts(areaX, areaY, areaWidth, areaHeight);
		int[] toReturn = IntCache.getIntCache(areaWidth * areaHeight);

		for (int i = 0; i < areaWidth * areaHeight; ++i) {

			if (riverIDs[i] == ATBiomes.river.biomeID) {
				
				if (biomeIDs[i] == ATBiomes.greenHill.biomeID) {
					toReturn[i] = ATBiomes.greenHillRiver.biomeID;
				}
				else {
					toReturn[i] = riverIDs[i];
				}
				
			}
			else {
				toReturn[i] = biomeIDs[i];
			}

		}

		return toReturn;
	}
}