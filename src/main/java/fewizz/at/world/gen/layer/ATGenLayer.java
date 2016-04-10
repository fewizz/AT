package fewizz.at.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

/** WARNING! Copypaste! **/
public abstract class ATGenLayer extends GenLayer {

	public ATGenLayer(long seed) {
		super(seed);
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String worldSettings) {
		GenLayer genlayer = new GenLayerIsland(1L);
		genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
		GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
		GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
		GenLayerAddIsland genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
		genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
		GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
		GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
		GenLayerAddIsland genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddsnow);
		GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
		genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
		genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
		GenLayerZoom genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
		genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
		GenLayerAddIsland genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
		GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddisland3);
		GenLayer genlayer4 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
		ChunkProviderSettings chunkprovidersettings = null;
		int i = 4;
		int j = i;

		if (worldType == WorldType.CUSTOMIZED && worldSettings.length() > 0) {
			chunkprovidersettings = ChunkProviderSettings.Factory.jsonToFactory(worldSettings).build();
			i = chunkprovidersettings.biomeSize;
			j = chunkprovidersettings.riverSize;
		}

		if (worldType == WorldType.LARGE_BIOMES) {
			i = 6;
		}

		i = getModdedBiomeSize(worldType, i);

		GenLayer lvt_8_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
		GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, lvt_8_1_);
		GenLayer lvt_10_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
		GenLayer genlayerhills = new ATGenLayerBiome(200, genlayer4, worldType, worldSettings);
		genlayerhills = GenLayerZoom.magnify(1000L, genlayerhills, 2);
		genlayerhills = new GenLayerBiomeEdge(1000L, genlayerhills);
		GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
		genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, j);
		ATGenLayerRiver genlayerriver = new ATGenLayerRiver(1L, genlayer5);
		GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
		genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

		for (int k = 0; k < i; ++k) {
			genlayerhills = new GenLayerZoom((long) (1000 + k), genlayerhills);

			if (k == 0) {
				genlayerhills = new GenLayerAddIsland(3L, genlayerhills);
			}

			if (k == 1 || i == 1) {
				genlayerhills = new GenLayerShore(1000L, genlayerhills);
			}
		}

		GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
		ATGenLayerRiverMix genlayerrivermix = new ATGenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
		GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
		genlayerrivermix.initWorldGenSeed(seed);
		genlayer3.initWorldGenSeed(seed);
		return new GenLayer[] { genlayer3, genlayer3, genlayerrivermix };
	}

}
