package fewizz.at.world;

import java.util.List;
import java.util.Random;

import fewizz.at.init.ATBiomes;
import fewizz.at.world.biome.ATBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ATChunkProvider implements IChunkGenerator, IChunkProvider {
	private static final SimplexNoise noise = new SimplexNoise();
	private static final SimplexNoise noiseMount = new SimplexNoise();
	private Random rand;
	private World worldObj;
	private int waterLevel = 48;
	private int strength = 24;
	private int offset = 5;
	private int length = 16 + (offset * 2);
	private float frequency = 0.03F;
	private float amplitude = 1.0F;
	private float heightRatio = 1.0F;

	public ATChunkProvider(World worldIn, long seed, boolean generateStructures) {
		this.worldObj = worldIn;
		this.worldObj.setSeaLevel(waterLevel);
		this.rand = new Random(seed);
	}

	public Chunk provideChunk(int x, int z) {
		BiomeGenBase[] biomes = new BiomeGenBase[length * length];
		float[] heights = new float[length * length];
		byte[] biomeIds = new byte[256];

		this.worldObj.getBiomeProvider().getBiomesForGeneration(biomes, (x << 4) - offset, (z << 4) - offset, length, length);
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();

		for (int chX = 0; chX < length; chX++) {
			for (int chZ = 0; chZ < length; chZ++) {
				int index = chX + (chZ * length);
				BiomeGenBase biome = biomes[index];
				float tempFrequency = frequency;
				float noiseMountValue = 0;
				boolean hasMountains = false;

				/** For AT Biomes ****************/
				if (biome instanceof ATBiome) {
					frequency = ((ATBiome) biome).getFrequency();

					if (((ATBiome) biome).hasMountains()) {
						hasMountains = true;
						float atMountFrequency = ((ATBiome) biome).getMountainFrequency();
						float atMountAmplitude = ((ATBiome) biome).getMountainAmplitude();
						float atMountOffset = ((ATBiome) biome).getMountainOffset();

						noiseMountValue = (float) ((noiseMount.noise(((x << 4) + chX - offset) * atMountFrequency, ((z << 4) + chZ - offset) * atMountFrequency) + atMountOffset) * atMountAmplitude);
						noiseMountValue += (float) (noiseMount.noise(((x << 4) + chX - offset) * atMountFrequency * 8, ((z << 4) + chZ - offset) * atMountFrequency * 8) * atMountAmplitude / 4);
					}
				}
				/*********************************/

				float noiseValue = (float) noise.noise(((x << 4) + chX - offset) * tempFrequency, ((z << 4) + chZ - offset) * tempFrequency) * amplitude;

				if (noiseMountValue > noiseValue) {
					noiseValue = noiseMountValue;
					if (hasMountains) {
						biomes[index] = ATBiomes.stoneMount;
					}
				}

				heights[index] = (int) (waterLevel + (biome.getBaseHeight() * heightRatio * strength) + (noiseValue * (biome.getHeightVariation() * strength)));
			}
		}

		for (int chX = 0; chX < 16; chX++) {
			for (int chZ = 0; chZ < 16; chZ++) {
				int index = (chX + offset) + ((chZ + offset) * length);
				BiomeGenBase curBiome = biomes[index];
				biomeIds[chX | (chZ << 4)] = (byte) BiomeGenBase.getIdForBiome(curBiome);// (byte) curBiome.;

				/** Smoothing ********************/
				float count = 0;
				int height = 0;
				int willBeHeight = 0;
				for (int x1 = -offset; x1 <= offset; x1++) {
					for (int z1 = -offset; z1 <= offset; z1++) {
						count++;
						willBeHeight += heights[(chX + offset + x1) + ((chZ + offset + z1) * length)];
					}
				}
				height += willBeHeight / count;
				/*********************************/

				for (int chY = 0; chY < 256; chY++) {
					if (chY < height) {
						chunkprimer.setBlockState(chX, chY, chZ, Blocks.STONE.getDefaultState());
					}
					else if (chY <= waterLevel) {
						chunkprimer.setBlockState(chX, chY, chZ, Blocks.WATER.getDefaultState());
					}
				}

				curBiome.genTerrainBlocks(worldObj, rand, chunkprimer, (z << 4) + chZ, (x << 4) + chX, 0);
			}
		}

		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		chunk.setBiomeArray(biomeIds);
		chunk.generateSkylightMap();
		
		return chunk;
	}

	public boolean chunkExists(int x, int z) {
		return true;
	}

	public void populate(int chX, int chZ) {
		int x = chX * 16;
		int z = chZ * 16;
		BlockPos blockpos = new BlockPos(x, 0, z);
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
		biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(x, 0, z));
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public Chunk getLoadedChunk(int x, int z) {
		return worldObj.getChunkFromChunkCoords(x, z);
	}

}
