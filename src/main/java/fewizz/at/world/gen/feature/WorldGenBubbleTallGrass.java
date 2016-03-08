package fewizz.at.world.gen.feature;

import java.util.Random;

import fewizz.at.block.BlockBubbleTallGrass;
import fewizz.at.init.ATBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBubbleTallGrass extends WorldGenerator {

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		Block block;

		do {
			block = worldIn.getBlockState(position).getBlock();
			if (!block.isAir(worldIn, position) && !block.isLeaves(worldIn, position))
				break;
			position = position.down();
		}
		while (position.getY() > 0);

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && ((BlockBubbleTallGrass)ATBlocks.bubbleTallGrass).canBlockStay(worldIn, blockpos, ATBlocks.bubbleTallGrass.getDefaultState())) {
				worldIn.setBlockState(blockpos, ATBlocks.bubbleTallGrass.getDefaultState(), 2);
			}
		}

		return true;
	}
}