package fewizz.at.world.gen.layer;

import fewizz.at.init.ATBiomes;
import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerRandom extends GenLayer {

	public GenLayerRandom(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] toReturn = new int[areaWidth * areaHeight];

		for (int x = 0; x < areaWidth; x++) {
			for (int y = 0; y < areaHeight; y++) {
				this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
				toReturn[x + (y * areaHeight)] = ATBiomes.biomeIDs.get(this.nextInt(ATBiomes.biomeIDs.size())).biomeID;
			}
		}
		return toReturn;
	}

}
