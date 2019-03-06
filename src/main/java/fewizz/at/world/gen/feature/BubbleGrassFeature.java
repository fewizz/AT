package fewizz.at.world.gen.feature;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class BubbleGrassFeature extends Feature<FeatureConfig> {

	public BubbleGrassFeature() {
		super(DefaultFeatureConfig::deserialize);
	}

	@Override
	public boolean generate(IWorld w, ChunkGenerator<? extends ChunkGeneratorConfig> var2, Random rand, BlockPos position, FeatureConfig var5) {
		position = new BlockPos(position.getX(), 0, position.getZ());
		Chunk c = w.getChunk(position.getX() >> 4, position.getZ() >> 4);
		

		BlockPos p = new BlockPos(position)
			.add(
				0, 
				c.getHeightmap(Heightmap.Type.WORLD_SURFACE)
					.get(position.getX()&0xF, position.getZ()&0xF),
				0
			);
		w.setBlockState(p, ATBlocks.BUBBLE_GRASS.getDefaultState(), 2);

		return true;
	}
}