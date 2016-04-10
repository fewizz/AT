package fewizz.at.world.gen.feature;

import com.google.common.collect.Lists;

import fewizz.at.block.BlockCandyLeaves;
import fewizz.at.init.ATBlocks;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

/** WARNING! Copypaste! **/
public class WorldGenBigCandyTree extends WorldGenAbstractTree {
	int leafType = 0;
	Random rand;
	World world;
	BlockPos basePos = BlockPos.ORIGIN;
	int heightLimit;
	int height;
	double heightAttenuation = 0.618D;
	double branchSlope = 0.381D;
	double scaleWidth = 1.0D;
	double leafDensity = 1.0D;
	int heightLimitLimit = 20;
	/** Sets the distance limit for how far away the generator will populate leaves from the base leaf node. */
	int leafDistanceLimit = 6;
	List<WorldGenBigCandyTree.FoliageCoordinates> foliageCoords;
	IBlockState state;

	public WorldGenBigCandyTree(int leafType) {
		super(false);
		this.leafType = leafType;
		state = ATBlocks.candyLeaves.getDefaultState().withProperty(BlockCandyLeaves.TYPE, leafType);
	}

	/**
	 * Generates a list of leaf nodes for the tree, to be populated by generateLeaves.
	 */
	void generateLeafNodeList() {
		this.height = (int) ((double) this.heightLimit * this.heightAttenuation);

		if (this.height >= this.heightLimit) {
			this.height = this.heightLimit - 1;
		}

		int i = (int) (1.382D + Math.pow(this.leafDensity * (double) this.heightLimit / 13.0D, 2.0D));

		if (i < 1) {
			i = 1;
		}

		int j = this.basePos.getY() + this.height;
		int k = this.heightLimit - this.leafDistanceLimit;
		this.foliageCoords = Lists.<WorldGenBigCandyTree.FoliageCoordinates> newArrayList();
		this.foliageCoords.add(new WorldGenBigCandyTree.FoliageCoordinates(this.basePos.up(k), j));

		for (; k >= 0; --k) {
			float f = this.layerSize(k);

			if (f >= 0.0F) {
				for (int l = 0; l < i; ++l) {
					double d0 = this.scaleWidth * (double) f * ((double) this.rand.nextFloat() + 0.328D);
					double d1 = (double) (this.rand.nextFloat() * 2.0F) * Math.PI;
					double d2 = d0 * Math.sin(d1) + 0.5D;
					double d3 = d0 * Math.cos(d1) + 0.5D;
					BlockPos blockpos = this.basePos.add(d2, (double) (k - 1), d3);
					BlockPos blockpos1 = blockpos.up(this.leafDistanceLimit);

					if (this.checkBlockLine(blockpos, blockpos1) == -1) {
						int i1 = this.basePos.getX() - blockpos.getX();
						int j1 = this.basePos.getZ() - blockpos.getZ();
						double d4 = (double) blockpos.getY() - Math.sqrt((double) (i1 * i1 + j1 * j1)) * this.branchSlope;
						int k1 = d4 > (double) j ? j : (int) d4;
						BlockPos blockpos2 = new BlockPos(this.basePos.getX(), k1, this.basePos.getZ());

						if (this.checkBlockLine(blockpos2, blockpos) == -1) {
							this.foliageCoords.add(new WorldGenBigCandyTree.FoliageCoordinates(blockpos, blockpos2.getY()));
						}
					}
				}
			}
		}
	}

	void genLeaves(BlockPos pos, float leafSize, IBlockState blockstate) {
		int i = (int) ((double) leafSize + 0.618D);

		for (int j = -i; j <= i; ++j) {
			for (int k = -i; k <= i; ++k) {
				if (Math.pow((double) Math.abs(j) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k) + 0.5D, 2.0D) <= (double) (leafSize * leafSize)) {
					BlockPos blockpos = pos.add(j, 0, k);
					net.minecraft.block.state.IBlockState state = this.world.getBlockState(blockpos);

					if (state.getBlock().isAir(state, this.world, blockpos) || state.getBlock().isLeaves(state, this.world, blockpos)) {
						this.setBlockAndNotifyAdequately(this.world, blockpos, blockstate);
					}
				}
			}
		}
	}

	/**
	 * Gets the rough size of a layer of the tree.
	 */
	float layerSize(int p_76490_1_) {
		if ((float) p_76490_1_ < (float) this.heightLimit * 0.3F) {
			return -1.0F;
		}
		else {
			float f = (float) this.heightLimit / 2.0F;
			float f1 = f - (float) p_76490_1_;
			float f2 = MathHelper.sqrt_float(f * f - f1 * f1);

			if (f1 == 0.0F) {
				f2 = f;
			}
			else if (Math.abs(f1) >= f) {
				return 0.0F;
			}

			return f2 * 0.5F;
		}
	}

	float leafSize(int p_76495_1_) {
		return p_76495_1_ >= 0 && p_76495_1_ < this.leafDistanceLimit ? (p_76495_1_ != 0 && p_76495_1_ != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
	}

	/**
	 * Generates the leaves surrounding an individual entry in the leafNodes list.
	 */
	void generateLeafNode(BlockPos pos) {
		for (int i = 0; i < this.leafDistanceLimit; ++i) {
			this.genLeaves(pos.up(i), this.leafSize(i), state);
		}
	}

	void genTrunk(BlockPos posTop, BlockPos posBot, Block block) {
		BlockPos blockpos = posBot.add(-posTop.getX(), -posTop.getY(), -posTop.getZ());
		int dist = this.getGreatestDistance(blockpos);
		float x = (float) blockpos.getX() / (float) dist;
		float y = (float) blockpos.getY() / (float) dist;
		float z = (float) blockpos.getZ() / (float) dist;

		for (int i = 0; i <= dist; ++i) {
			BlockPos blockpos1 = posTop.add((double) (0.5F + (float) i * x), (double) (0.5F + (float) i * y), (double) (0.5F + (float) i * z));
			BlockLog.EnumAxis blocklog$enumaxis = this.func_175938_b(posTop, blockpos1);
			this.setBlockAndNotifyAdequately(this.world, blockpos1, block.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
		}
	}

	/**
	 * Returns the absolute greatest distance in the BlockPos object.
	 */
	private int getGreatestDistance(BlockPos posIn) {
		int i = MathHelper.abs_int(posIn.getX());
		int j = MathHelper.abs_int(posIn.getY());
		int k = MathHelper.abs_int(posIn.getZ());
		return k > i && k > j ? k : (j > i ? j : i);
	}

	private BlockLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {
		BlockLog.EnumAxis blocklog$enumaxis = BlockLog.EnumAxis.Y;
		int i = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
		int j = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
		int k = Math.max(i, j);

		if (k > 0) {
			if (i == k) {
				blocklog$enumaxis = BlockLog.EnumAxis.X;
			}
			else if (j == k) {
				blocklog$enumaxis = BlockLog.EnumAxis.Z;
			}
		}

		return blocklog$enumaxis;
	}

	/**
	 * Generates the leaf portion of the tree as specified by the leafNodes list.
	 */
	void generateLeaves() {
		for (WorldGenBigCandyTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.foliageCoords) {
			this.generateLeafNode(worldgenbigtree$foliagecoordinates);
		}
	}

	/**
	 * Indicates whether or not a leaf node requires additional wood to be added to preserve integrity.
	 */
	boolean leafNodeNeedsBase(int p_76493_1_) {
		return (double) p_76493_1_ >= (double) this.heightLimit * 0.2D;
	}

	/**
	 * Places the trunk for the big tree that is being generated. Able to generate double-sized trunks by changing a field that is always 1 to 2.
	 */
	void generateTrunk() {
		BlockPos blockpos = this.basePos;
		BlockPos blockpos1 = this.basePos.up(this.height);
		Block block = Blocks.LOG;
		this.genTrunk(blockpos, blockpos1, block);
	}

	/**
	 * Generates additional wood blocks to fill out the bases of different leaf nodes that would otherwise degrade.
	 */
	void generateLeafNodeBases() {
		for (WorldGenBigCandyTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.foliageCoords) {
			int i = worldgenbigtree$foliagecoordinates.getHeight();
			BlockPos blockpos = new BlockPos(this.basePos.getX(), i, this.basePos.getZ());

			if (!blockpos.equals(worldgenbigtree$foliagecoordinates) && this.leafNodeNeedsBase(i - this.basePos.getY())) {
				this.genTrunk(blockpos, worldgenbigtree$foliagecoordinates, Blocks.LOG);
			}
		}
	}

	/**
	 * Checks a line of blocks in the world from the first coordinate to triplet to the second, returning the distance (in blocks) before a non-air, non-leaf block is encountered and/or the end is encountered.
	 */
	int checkBlockLine(BlockPos posOne, BlockPos posTwo) {
		BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
		int i = this.getGreatestDistance(blockpos);
		float f = (float) blockpos.getX() / (float) i;
		float f1 = (float) blockpos.getY() / (float) i;
		float f2 = (float) blockpos.getZ() / (float) i;

		if (i == 0) {
			return -1;
		}
		else {
			for (int j = 0; j <= i; ++j) {
				BlockPos blockpos1 = posOne.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));

				if (!this.isReplaceable(world, blockpos1)) {
					return j;
				}
			}

			return -1;
		}
	}

	public void func_175904_e() {
		this.leafDistanceLimit = 5;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.world = worldIn;
		this.basePos = position;
		this.rand = new Random(rand.nextLong());

		if (this.heightLimit == 0) {
			this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
		}

		if (!this.validTreeLocation()) {
			this.world = null;
			return false;
		}
		else {
			this.generateLeafNodeList();
			this.generateLeaves();
			this.generateTrunk();
			this.generateLeafNodeBases();
			this.world = null;
			return true;
		}
	}

	/**
	 * Returns a boolean indicating whether or not the current location for the tree, spanning basePos to to the height limit, is valid.
	 */
	private boolean validTreeLocation() {
		BlockPos down = this.basePos.down();
		net.minecraft.block.state.IBlockState state = this.world.getBlockState(down);

		if (state.getBlock() == ATBlocks.bubbleTallGrass || state.getBlock() == ATBlocks.bubbleGrass) {
			return true;
		}

		boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, EnumFacing.UP, ((net.minecraft.block.BlockSapling) Blocks.SAPLING));

		if (!isSoil) {
			return false;
		}
		else {
			int i = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));

			if (i == -1) {
				return true;
			}
			else if (i < 6) {
				return false;
			}
			else {
				this.heightLimit = i;
				return true;
			}
		}
	}

	static class FoliageCoordinates extends BlockPos {
		private final int h;

		public FoliageCoordinates(BlockPos pos, int height) {
			super(pos.getX(), pos.getY(), pos.getZ());
			this.h = height;
		}

		public int getHeight() {
			return this.h;
		}
	}
}