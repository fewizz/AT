package fewizz.at.world.gen.feature;

import java.util.Random;

import fewizz.at.block.BlockBubbleTallGrass;
import fewizz.at.init.ATBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBubbleTallGrass extends WorldGenerator {

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Block block;

		do {
			block = worldIn.getBlockState(position).getBlock();
			if (!block.isAir(worldIn.getBlockState(position), worldIn, position) && !block.isLeaves(worldIn.getBlockState(position), worldIn, position))
				break;
			position = position.down();
		}
		while (position.getY() > 0);

		int h = worldIn.getChunkFromBlockCoords(position).getHeight(position) + 2;
		
		for (int i = 0; i < h; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && ((BlockBubbleTallGrass)ATBlocks.BUBBLE_TALL_GRASS).canBlockStay(worldIn, blockpos, ATBlocks.BUBBLE_TALL_GRASS.getDefaultState())) {
				worldIn.setBlockState(blockpos, ATBlocks.BUBBLE_TALL_GRASS.getStateFromMeta(worldIn.rand.nextInt(8)), 2);
			}
		}

		return true;
	}
}