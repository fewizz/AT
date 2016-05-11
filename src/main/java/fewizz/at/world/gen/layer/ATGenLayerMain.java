package fewizz.at.world.gen.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fewizz.at.world.biome.ATBiome;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class ATGenLayerMain extends GenLayer {

	public static final boolean printBiomes = false;
	public static final int imageSize = 1024;

	public ATGenLayerMain(long seed) {
		super(seed);
	}

	public static GenLayer initializeAllBiomeGenerators(long seed) {
		GenLayer random = new GenLayerRandom(seed);
		GenLayer zoom1 = GenLayerZoom.magnify(seed + 1, random, 1);
		GenLayerSmooth smooth1 = new GenLayerSmooth(seed + 1, zoom1);
		GenLayer zoom2 = GenLayerZoom.magnify(seed + 2, smooth1, 3);
		GenLayerSmooth smooth2 = new GenLayerSmooth(seed + 2, zoom2);
		GenLayer zoom3 = GenLayerZoom.magnify(seed + 3, smooth2, 3);
		GenLayerSmooth smooth3 = new GenLayerSmooth(seed + 3, zoom3);

		GenLayerRiverInit riverInit = new GenLayerRiverInit(seed, smooth3);
		GenLayer riverZoom1 = GenLayerZoom.magnify(seed + 1, riverInit, 2);
		GenLayer riverZoom2 = GenLayerZoom.magnify(seed + 2, riverZoom1, 2);
		ATGenLayerRiver river = new ATGenLayerRiver(seed, riverZoom2);
		GenLayer riverZoom3 = GenLayerZoom.magnify(seed + 3, river, 3);
		GenLayerSmooth riverSmmoth1 = new GenLayerSmooth(seed, riverZoom3);

		ATGenLayerRiverMix mix = new ATGenLayerRiverMix(seed, smooth3, riverSmmoth1);
		mix.initWorldGenSeed(seed);

		if (printBiomes) {
			BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			int[] ints = mix.getInts(0, 0, imageSize, imageSize);

			for (int x = 0; x < imageSize; x++) {
				for (int y = 0; y < imageSize; y++) {
					BiomeGenBase biome = BiomeGenBase.getBiome(ints[x + (y * imageSize)]);
					int color = 0xFFFFFF;

					if (biome instanceof ATBiome) {
						color = ((ATBiome) biome).getBiomeColor();
					}

					g.setColor(new Color(color));
					g.drawRect(x, y, 1, 1);
				}
			}

			File file = new File("biomes/layers.png");

			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				ImageIO.write(image, "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return mix;
	}

}
