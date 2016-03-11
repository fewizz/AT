package fewizz.at.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import fewizz.at.world.gen.layer.ATGenLayer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class ATWorldChunkManager extends WorldChunkManager {
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> biomesToSpawnIn;
	private String generatorOptions;

	public ATWorldChunkManager(World world) {
		String options = world.getWorldInfo().getGeneratorOptions();
		long seed = world.getSeed();
		WorldType worldType = world.getWorldInfo().getTerrainType();
		this.biomeCache = new BiomeCache(this);
		this.generatorOptions = "";
		this.biomesToSpawnIn = Lists.<BiomeGenBase> newArrayList();
		this.biomesToSpawnIn.addAll(allowedBiomes);
		this.generatorOptions = options;
		GenLayer[] agenlayer = ATGenLayer.initializeAllBiomeGenerators(seed, worldType, options);
		agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
		this.genBiomes = agenlayer[0];
		this.biomeIndexLayer = agenlayer[1];
	}
	
	public BiomeGenBase getBiomeGenerator(BlockPos pos, BiomeGenBase biomeGenBaseIn)
    {
        return this.biomeCache.func_180284_a(pos.getX(), pos.getZ(), biomeGenBaseIn);
    }

	@Override
	public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new float[width * length];
		}

		int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

		for (int i = 0; i < width * length; ++i) {
			try {
				float f = (float) BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.field_180279_ad).getIntRainfall() / 65536.0F;

				if (f > 1.0F) {
					f = 1.0F;
				}

				listToReuse[i] = f;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
				crashreportcategory.addCrashSection("biome id", Integer.valueOf(i));
				crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(listToReuse.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("w", Integer.valueOf(width));
				crashreportcategory.addCrashSection("h", Integer.valueOf(length));
				throw new ReportedException(crashreport);
			}
		}

		return listToReuse;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int x, int z, int width, int height) {
		IntCache.resetIntCache();

		if (biomes == null || biomes.length < width * height) {
			biomes = new BiomeGenBase[width * height];
		}

		int[] aint = this.genBiomes.getInts(x, z, width, height);

		try {
			for (int i = 0; i < width * height; ++i) {
				biomes[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.field_180279_ad);
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
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new BiomeGenBase[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
			BiomeGenBase[] abiomegenbase = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(abiomegenbase, 0, listToReuse, 0, width * length);
			return listToReuse;
		}
		else {
			int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

			for (int i = 0; i < width * length; ++i) {
				listToReuse[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.field_180279_ad);
			}

			return listToReuse;
		}
	}

	@Override
	public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_) {
		IntCache.resetIntCache();
		int i = p_76940_1_ - p_76940_3_ >> 2;
		int j = p_76940_2_ - p_76940_3_ >> 2;
		int k = p_76940_1_ + p_76940_3_ >> 2;
		int l = p_76940_2_ + p_76940_3_ >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] aint = this.genBiomes.getInts(i, j, i1, j1);

		try {
			for (int k1 = 0; k1 < i1 * j1; ++k1) {
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k1]);

				if (!p_76940_4_.contains(biomegenbase)) {
					return false;
				}
			}

			return true;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
			crashreportcategory.addCrashSection("x", Integer.valueOf(p_76940_1_));
			crashreportcategory.addCrashSection("z", Integer.valueOf(p_76940_2_));
			crashreportcategory.addCrashSection("radius", Integer.valueOf(p_76940_3_));
			crashreportcategory.addCrashSection("allowed", p_76940_4_);
			throw new ReportedException(crashreport);
		}
	}

	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<BiomeGenBase> biomes, Random random) {
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
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[l1]);

			if (biomes.contains(biomegenbase) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
				blockpos = new BlockPos(i2, 0, j2);
				++k1;
			}
		}

		return blockpos;
	}

	/**
	 * Calls the WorldChunkManager's biomeCache.cleanupCache()
	 */
	@Override
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens event = new net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newBiomeGens;
	}
}
