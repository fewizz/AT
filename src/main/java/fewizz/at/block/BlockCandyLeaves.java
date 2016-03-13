package fewizz.at.block;

import java.util.List;

import fewizz.at.item.block.ItemBlockWithMeta;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyLeaves extends BlockLeavesBase {

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);

	public BlockCandyLeaves() {
		super(Material.leaves, true);
		this.setStepSound(soundTypeGrass);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 1));
		this.setUnlocalizedName("leaves_candy");
		this.fancyGraphics = true;
		GameRegistry.registerBlock(this, ItemBlockWithMeta.class, "leaves_candy");
	}

	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean fancy) {
		this.fancyGraphics = true;
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TYPE });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE) * 4;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, meta / 4);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 8));
		list.add(new ItemStack(this, 1, 12));
	}

	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
}
