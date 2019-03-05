package fewizz.at.world.gen.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fewizz.at.world.biome.ATBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.BiomeLayerSampler;
import net.minecraft.world.biome.layer.BiomeLayers;

public abstract class ATGenLayerMain {

	public static final boolean PRINT_BIOMES = false;
	public static final int IMAGE_SIZE = 1024;
	final long seed;

	public ATGenLayerMain(long seed) {
		this.seed = seed;
	}

	public static BiomeLayerSampler initializeAllBiomeGenerators(long seed) {
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

		if (PRINT_BIOMES) {
			BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			int[] ints = mix.getInts(0, 0, IMAGE_SIZE, IMAGE_SIZE);

			for (int x = 0; x < IMAGE_SIZE; x++) {
				for (int y = 0; y < IMAGE_SIZE; y++) {
					Biome biome = Biome.getBiome(ints[x + (y * IMAGE_SIZE)]);
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
