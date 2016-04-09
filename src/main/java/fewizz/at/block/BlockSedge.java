package fewizz.at.block;

import java.util.ArrayList;
import java.util.List;

import fewizz.at.AT;
import fewizz.at.init.ATBlocks;
import fewizz.at.item.block.ItemBlockWithMeta;
import fewizz.at.util.IHasName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSedge extends Block implements IPlantable, IHasName {

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 2);

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

	@Override
	public String getName() {
		return "sedge";
	}

	public BlockSedge() {
		super(Material.plants);
		this.setStepSound(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Integer.valueOf(1)));
		this.setUnlocalizedName(getName());
		GameRegistry.register(this, new ResourceLocation("at", getName()));
		GameRegistry.register(new ItemBlockWithMeta(this), new ResourceLocation("at", getName()));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		BlockPos posBot = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		BlockPos posTop = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		IBlockState botState = worldIn.getBlockState(posBot);
		IBlockState topState = worldIn.getBlockState(posTop);

		if (botState.getBlock().getMaterial(botState) == Material.air) {
			worldIn.destroyBlock(pos, true);
		}

		if (topState.getBlock().getMaterial(topState) == Material.air) {
			if (worldIn.getBlockState(pos).getBlock() == ATBlocks.sedge && ATBlocks.sedge.getMetaFromState(worldIn.getBlockState(pos)) != 2) {
				worldIn.destroyBlock(pos, true);
			}
		}
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World pos, BlockPos state) {
		return AABB;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Beach;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return this.getDefaultState();
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
