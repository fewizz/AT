package fewizz.at.world.gen.layer;

import fewizz.at.init.ATBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class ATGenLayerRiver extends GenLayer {
	public ATGenLayerRiver(long seed, GenLayer parent) {
		super(seed);
		super.parent = parent;
	}

	public int[] getInts(int areaX, int areaZ, int areaWidth, int areaHeight) {
		int startX = areaX - 1;
		int startZ = areaZ - 1;
		int width = areaWidth + 2;
		int height = areaHeight + 2;
		int[] aint = this.parent.getInts(startX, startZ, width, height);
		int[] toReturn = IntCache.getIntCache(areaWidth * areaHeight);

		for (int z = 0; z < areaHeight; ++z) {
			for (int x = 0; x < areaWidth; ++x) {
				int id1 = this.getID(aint[x + 0 + (z + 1) * width]);
				int id2 = this.getID(aint[x + 2 + (z + 1) * width]);
				int id3 = this.getID(aint[x + 1 + (z + 0) * width]);
				int id4 = this.getID(aint[x + 1 + (z + 2) * width]);
				int id5 = this.getID(aint[x + 1 + (z + 1) * width]);

				if (id5 == id1 && id5 == id3 && id5 == id2 && id5 == id4) {
					toReturn[x + z * areaWidth] = -1;
				}
				else {
					toReturn[x + z * areaWidth] = ATBiomes.river.biomeID;
				}
			}
		}

		return toReturn;
	}

	private int getID(int id) {
		return id >= 2 ? 2 + (id & 1) : id;
	}
}