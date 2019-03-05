package fewizz.at.world.dimension;

import fewizz.at.world.gen.chunk.ATChunkGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ATDimension extends Dimension {

	public ATDimension(World world_1, DimensionType dimensionType_1) {
		super(world_1, dimensionType_1);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return new ATChunkGenerator(world);
	}

	@Override
	public BlockPos getSpawningBlockInChunk(ChunkPos var1, boolean var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockPos getTopSpawningBlockPosition(int var1, int var2, boolean var3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getSkyAngle(long var1, float var3) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasVisibleSky() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vec3d getFogColor(float var1, float var2) {
		return new Vec3d(1F, 0.1F, 0.8F);
	}

	@Override
	public boolean canPlayersSleep() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRenderFog(int var1, int var2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DimensionType getType() {
		return ATDimensionType.INSTANCE;
	}

}
