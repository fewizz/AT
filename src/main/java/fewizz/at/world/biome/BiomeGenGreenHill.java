package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.world.SimplexNoise;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class BiomeGenGreenHill extends ATBiome {
	public static SimplexNoise noise = new SimplexNoise();

	public BiomeGenGreenHill() {
		super("ATGreenHill", 0.3f, 0.4f);
		
		this.fillerBlock = Blocks.dirt.getDefaultState();
		this.topBlock = Blocks.grass.getDefaultState();
		this.theBiomeDecorator.grassPerChunk = 128;
		this.theBiomeDecorator.treesPerChunk = 2;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x079400;
	}
	
	@Override
	public float getFrequency() {
		return 0.05F;
	}
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenBigTree(false);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zCoord, int xCoord, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState top = this.topBlock;
		IBlockState fill = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int z = zCoord & 15;
		int x = xCoord & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		/** Hilling **/
		IBlockState[] states = new IBlockState[256];

		int offset = ((int) ((((float) noise.noise((float)xCoord / 64f, (float)zCoord / 64f) + 1) / 2f) * 6)) * 3;

		for (int y = 0; y < 256; y++) {
			states[y] = chunkPrimerIn.getBlockState(x, y, z);
		}

		for (int y = 0; y < 256; y++) {
			if (y + offset < 256) {
				IBlockState state = states[y];
				if(state.getBlock() == Blocks.water) {
					state = Blocks.flowing_water.getDefaultState();
				}
				chunkPrimerIn.setBlockState(x, y + offset, z, state);
			}
		}

		for (int y = 0; y < offset; y++) {
			chunkPrimerIn.setBlockState(x, y, z, Blocks.stone.getDefaultState());
		}

		/*************/

		for (int height = 255; height >= 0; --height) {
			if (height <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(x, height, z, BEDROCK);
			}
			else {
				IBlockState state = chunkPrimerIn.getBlockState(x, height, z);

				if (state.getMaterial() == Material.air) {
					j = -1;
				}
				else if (state.getBlock() == Blocks.stone) {
					if (j == -1) {
						if (k <= 0) {
							top = AIR;
							fill = STONE;
						}
						else if (height >= seaLevel - 4 && height <= seaLevel + 1) {
							top = this.topBlock;
							fill = this.fillerBlock;
						}

						if (height < seaLevel && (top == null || top.getMaterial() == Material.air)) {
							if (this.getFloatTemperature(blockpos$mutableblockpos.set(zCoord, height, xCoord)) < 0.15F) {
								top = ICE;
							}
							else {
								top = WATER;
							}
						}

						j = k;

						if (height >= seaLevel - 1) {
							chunkPrimerIn.setBlockState(x, height, z, top);
						}
						else if (height < seaLevel - 7 - k) {
							top = AIR;
							fill = STONE;
							chunkPrimerIn.setBlockState(x, height, z, GRAVEL);
						}
						else {
							chunkPrimerIn.setBlockState(x, height, z, fill);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(x, height, z, fill);

						if (j == 0 && fill.getBlock() == Blocks.sand) {
							j = rand.nextInt(4) + Math.max(0, height - 63);
							fill = fill.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
					}
				}
			}
		}
	}

}
