package fewizz.at.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import fewizz.at.world.gen.layer.ATGenLayerMain;
import fewizz.at.world.gen.layer.ATGenLayerMain;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public class ATBiomeProvider extends BiomeProvider {
	private GenLayer genBiomes;
	private BiomeCache biomeCache;

	public ATBiomeProvider(World world) {
		long seed = world.getSeed();
		WorldType worldType = world.getWorldInfo().getTerrainType();
		this.biomeCache = new BiomeCache(this);
		this.genBiomes = ATGenLayerMain.initializeAllBiomeGenerators(seed);
	}

	@Override
	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
		IntCache.resetIntCache();

		if (biomes == null || biomes.length < width * height) {
			biomes = new Biome[width * height];
		}

		int[] aint = this.genBiomes.getInts(x, z, width, height);

		try {
			for (int i = 0; i < width * height; ++i) {
				biomes[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
			}

			return biomes;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
			crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
			crashreportcategory.addCrashSection("x", Integer.valueOf(x));
			crashreportcategory.addCrashSection("z", Integer.valueOf(z));
			crashreportcategory.addCrashSection("w", Integer.valueOf(width));
			crashreportcategory.addCrashSection("h", Integer.valueOf(height));
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public Biome[] getBiomeGenAt(Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new Biome[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
			Biome[] abiomegenbase = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(abiomegenbase, 0, listToReuse, 0, width * length);
			return listToReuse;
		}
		else {
			int[] aint = this.genBiomes.getInts(x, z, width, length);

			for (int i = 0; i < width * length; ++i) {
				listToReuse[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
			}

			return listToReuse;
		}
	}

	@Override
	public boolean areBiomesViable(int posX, int posZ, int radius, List<Biome> list) {
		IntCache.resetIntCache();
		int i = posX - radius >> 2;
		int j = posZ - radius >> 2;
		int k = posX + radius >> 2;
		int l = posZ + radius >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] aint = this.genBiomes.getInts(i, j, i1, j1);

		try {
			for (int k1 = 0; k1 < i1 * j1; ++k1) {
				Biome biomegenbase = Biome.getBiome(aint[k1]);

				if (!list.contains(biomegenbase)) {
					return false;
				}
			}

			return true;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
			crashreportcategory.addCrashSection("x", Integer.valueOf(posX));
			crashreportcategory.addCrashSection("z", Integer.valueOf(posZ));
			crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
			crashreportcategory.addCrashSection("allowed", list);
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		IntCache.resetIntCache();
		int i = x - range >> 2;
		int j = z - range >> 2;
		int k = x + range >> 2;
		int l = z + range >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] aint = this.genBiomes.getInts(i, j, i1, j1);
		BlockPos blockpos = null;
		int k1 = 0;

		for (int l1 = 0; l1 < i1 * j1; ++l1) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			Biome biomegenbase = Biome.getBiome(aint[l1]);

			if (biomes.contains(biomegenbase) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
				blockpos = new BlockPos(i2, 0, j2);
				++k1;
			}
		}

		return blockpos;
	}

	@Override
	public GenLayer[] getModdedBiomeerators(WorldType worldType, long seed, GenLayer[] original) {
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.getNewBiomeGens();
	}

	@Override
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}

}
