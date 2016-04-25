package fewizz.at.world.gen.feature;

import java.util.Random;

import fewizz.at.init.ATBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCandyTree extends WorldGenAbstractTree {
	public boolean generateWithCheck;
	public World world;
	public Random rnd;
	public BlockPos pos;
	public IBlockState log = Blocks.LOG.getDefaultState();
	public IBlockState leaves;

	public static final int treeHight = 32;
	public static final int normalBranchRadius = 4;
	public static final int branchRadiusVariation = 3;
	public static final int leavesOffset = 10;
	public static final float branchWidthScale = 1.4F;
	public static final float branchHeightScale = 0.8F;

	public WorldGenCandyTree(boolean generateWithCheck) {
		super(true);
		this.generateWithCheck = generateWithCheck;
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
		
		leaves = ATBlocks.candyLeaves.getStateFromMeta(rnd.nextInt(4) << 2); // 4 variants

		generateTrunk();
		generateBranches();

		return true;
	}
	
	private boolean validTreeLocation() {
		BlockPos down = this.pos.down();
		IBlockState state = this.world.getBlockState(down);

		if (state.getBlock() == ATBlocks.bubbleTallGrass || state.getBlock() == ATBlocks.bubbleGrass) {
			return true;
		}
		
		return false;
	}

	public void generateTrunk() {
		for (int i = 0; i < treeHight; i++) {
			world.setBlockState(pos.add(0, i, 0), log);
		}
	}

	public void generateBranches() {
		for (int i = leavesOffset; i < treeHight; i += rnd.nextInt(2) + 1) {
			generateBranch(i);
		}
	}

	public void generateBranch(int offsetY) {

		final float radius = normalBranchRadius + (rnd.nextInt(branchRadiusVariation * 2 + 1)) - branchRadiusVariation;
		final float angle = (float) Math.toRadians(rnd.nextInt(360));
		final float scale = (float) rnd.nextDouble();
		final int offsetX = (int) (MathHelper.sin(angle) * radius * scale);
		final int offsetZ = (int) (MathHelper.cos(angle) * radius * scale);

		for (float y = -radius * branchHeightScale; y <= radius * branchHeightScale; y++) {
			for (float z = -radius * branchWidthScale; z <= radius * branchWidthScale; z++) {
				for (float x = -radius * branchWidthScale; x <= radius * branchWidthScale; x++) {
					float normalizedX = x / branchWidthScale;
					float normalizedZ = z / branchWidthScale;
					float normalizedY = y / branchHeightScale;

					float hyp = MathHelper.sqrt_float((normalizedX * normalizedX) + (normalizedY * normalizedY) + (normalizedZ * normalizedZ));
					if (hyp > radius) {
						continue;
					}

					if (generateWithCheck) {
						if (world.getBlockState(pos.add(offsetX + x, offsetY + y, offsetZ + z)).getMaterial() != Material.AIR) {
							continue;
						}
					}

					world.setBlockState(pos.add(offsetX + x, offsetY + y, offsetZ + z), leaves);
				}
			}
		}

	}

}
