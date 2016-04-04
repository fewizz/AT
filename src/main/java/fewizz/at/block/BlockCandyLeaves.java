package fewizz.at.block;

import java.util.List;

import fewizz.at.AT;
import fewizz.at.init.ATItemStacks;
import fewizz.at.item.block.ItemBlockWithMeta;
import fewizz.at.util.IHasName;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyLeaves { //extends BlockLeavesBase implements IHasName {

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);

	//@Override
	//public String getName() {
	//	return "leaves_candy";
	//}

	public BlockCandyLeaves() {
		//super(Material.leaves, true);
		//this.setStepSound(soundTypeGrass);
		//this.setCreativeTab(AT.tab);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 1));
		//this.fancyGraphics = true;
		//this.setUnlocalizedName(getName());
		//GameRegistry.registerBlock(this, ItemBlockWithMeta.class, getName());
		
		//ATItemStacks.candyLeaves_0 = new ItemStack(this, 1, 0);
		//ATItemStacks.candyLeaves_4 = new ItemStack(this, 1, 4);
		//ATItemStacks.candyLeaves_8 = new ItemStack(this, 1, 8);
		//ATItemStacks.candyLeaves_12 = new ItemStack(this, 1, 12);
	}

	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean fancy) {
		//this.fancyGraphics = true;
	}

	//@SideOnly(Side.CLIENT)
	//public EnumWorldBlockLayer getBlockLayer() {
	//	return EnumWorldBlockLayer.CUTOUT_MIPPED;
	//}

	//@Override
	//protected BlockState createBlockState() {
//		return new BlockState(this, new IProperty[] { TYPE });
	//}

	//@Override
	//public int getMetaFromState(IBlockState state) {
	//	return state.getValue(TYPE) * 4;
	//}

	//@Override
	//public IBlockState getStateFromMeta(int meta) {
	//	return getDefaultState().withProperty(TYPE, meta / 4);
	//}

	//@Override
	//public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
	//	list.add(new ItemStack(this, 1, 0));
	//	list.add(new ItemStack(this, 1, 4));
	//	list.add(new ItemStack(this, 1, 8));
	//	list.add(new ItemStack(this, 1, 12));
	//}

	//@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		return true;
	}
}
