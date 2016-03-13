package fewizz.at.world.biome;

import java.util.Random;

import fewizz.at.world.gen.feature.WorldGenBigCandyTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityBreakingFX.SnowballFactory;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenMountain extends ATBiome {
	IBlockState snow;

	public BiomeGenMountain() {
		super(35);

		this.biomeName = "ATMountain";
		this.topBlock = Blocks.stone.getStateFromMeta(3); // Diorite
		this.fillerBlock = Blocks.stone.getStateFromMeta(3);
		this.snow = Blocks.snow.getDefaultState();
		this.temperature = -10F;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		int newX = pos.getX() + rand.nextInt(16);
		int newZ = pos.getZ() + rand.nextInt(16);
		BlockPos dirtPos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(newX, 0, newZ)).down();
		worldIn.setBlockState(dirtPos, Blocks.dirt.getDefaultState());
		if (worldIn.getBlockState(dirtPos).getBlock() != Blocks.snow || !new WorldGenTaiga2(false).generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(new BlockPos(newX, 0, newZ)))) {
			worldIn.setBlockToAir(dirtPos);
		}
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return new WorldGenTaiga2(false);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int zCoord, int xCoord, double noiseVal) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState top = this.topBlock;
		IBlockState filler = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int z = zCoord & 15;
		int x = xCoord & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int height = 255; height >= 0; --height) {
			if (height <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(x, height, z, Blocks.bedrock.getDefaultState());
			}
			else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(x, height, z);

				if (iblockstate2.getBlock().getMaterial() == Material.air) {
					j = -1;
				}
				else if (iblockstate2.getBlock() == Blocks.stone) {
					if (j == -1) {
						if (k <= 0) {
							top = null;
							filler = Blocks.stone.getDefaultState();
						}
						else if (height >= seaLevel - 4 && height <= seaLevel + 1) {
							top = this.topBlock;
							filler = this.fillerBlock;
						}

						if (height >= seaLevel + 20) {
							top = snow;
							filler = snow;
						}

						j = k;

						if (height >= seaLevel - 1) {
							chunkPrimerIn.setBlockState(x, height, z, top);
						}
						else if (height < seaLevel - 12 - k) {
							top = null;
							filler = Blocks.stone.getDefaultState();
							chunkPrimerIn.setBlockState(x, height, z, Blocks.gravel.getDefaultState());
						}
						else {
							chunkPrimerIn.setBlockState(x, height, z, filler);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(x, height, z, filler);

						if (j == 0 && filler.getBlock() == Blocks.sand) {
							j = rand.nextInt(4) + Math.max(0, height - 63);
							filler = filler.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? Blocks.red_sandstone.getDefaultState() : Blocks.sandstone.getDefaultState();
						}
					}
				}
			}
		}

	}

}
