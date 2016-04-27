package fewizz.at.world.gen.feature;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCandyTree extends WorldGenAbstractTree {
	public World world;
	public Random rnd;
	public BlockPos pos;
	public IBlockState log;
	public IBlockState leaves;

	public static final int TREE_HEIGHT = 16;
	public static final int NORMAL_BRANCH_RADIUS = 4;
	public static final int BRANCH_RADIUS_VARIATION = 3;
	public static final int LEAVES_OFFSET = 5;
	public static final float BRANCH_WIDTH_SCALE = 1.4F;
	public static final float BRANCH_HEIGHT_SCALE = 0.8F;

	public WorldGenCandyTree(IBlockState log, IBlockState leaves) {
		this();
		this.log = log;
		this.leaves = leaves;
	}

	public WorldGenCandyTree() {
		super(true);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.world = worldIn;
		this.rnd = rand;
		this.pos = position;

		if (!this.validTreeLocation()) {
			this.world = null;
			return false;
		}

		if (leaves == null)
			leaves = ATBlocks.candyLeaves.getStateFromMeta(rnd.nextInt(4) << 2); // 4 variants
		if (log == null) {
			log = Blocks.LOG.getDefaultState();
		}

		generateTrunk();
		generateBranches();

		return true;
	}

	private boolean validTreeLocation() {
		BlockPos down = this.pos.down();
		IBlockState state = this.world.getBlockState(down);
		Block block = state.getBlock();

		if (block == ATBlocks.bubbleTallGrass || block == ATBlocks.bubbleGrass || block == Blocks.DIRT || block == Blocks.GRASS) {
			return true;
		}

		return false;
	}

	public void generateTrunk() {
		for (int i = 0; i < TREE_HEIGHT; i++) {
			world.setBlockState(pos.add(0, i, 0), log);
		}
	}

	public void generateBranches() {
		for (int i = LEAVES_OFFSET; i < TREE_HEIGHT; i ++) {
			generateBranch(i);
		}
	}

	public void generateBranch(int offsetY) {
		final float radius = NORMAL_BRANCH_RADIUS + (rnd.nextInt(BRANCH_RADIUS_VARIATION * 2 + 1)) - BRANCH_RADIUS_VARIATION;
		final float angle = (float) Math.toRadians(rnd.nextInt(360));
		final float scale = (float) rnd.nextDouble();
		final int offsetX = (int) (MathHelper.sin(angle) * radius * scale);
		final int offsetZ = (int) (MathHelper.cos(angle) * radius * scale);

		if (radius * BRANCH_HEIGHT_SCALE > LEAVES_OFFSET) {
			return;
		}

		for (float y = -radius * BRANCH_HEIGHT_SCALE; y <= radius * BRANCH_HEIGHT_SCALE; y++) {
			for (float z = -radius * BRANCH_WIDTH_SCALE; z <= radius * BRANCH_WIDTH_SCALE; z++) {
				for (float x = -radius * BRANCH_WIDTH_SCALE; x <= radius * BRANCH_WIDTH_SCALE; x++) {
					float normalizedX = x / BRANCH_WIDTH_SCALE;
					float normalizedZ = z / BRANCH_WIDTH_SCALE;
					float normalizedY = y / BRANCH_HEIGHT_SCALE;

					float hyp = MathHelper.sqrt_float((normalizedX * normalizedX) + (normalizedY * normalizedY) + (normalizedZ * normalizedZ));
					if (hyp > radius) {
						continue;
					}


					if (world.getBlockState(pos.add(offsetX + x, offsetY + y, offsetZ + z)).getMaterial() != Material.AIR) {
						continue;
					}

					world.setBlockState(pos.add(offsetX + x, offsetY + y, offsetZ + z), leaves);
				}
			}
		}

	}

}
