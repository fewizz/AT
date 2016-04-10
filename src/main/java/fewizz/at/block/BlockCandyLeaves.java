package fewizz.at.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fewizz.at.AT;
import fewizz.at.init.ATItemStacks;
import fewizz.at.item.block.ItemBlockWithMeta;
import fewizz.at.util.IHasName;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyLeaves extends BlockLeaves implements IHasName {

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);

	@Override
	public String getName() {
		return "leaves_candy";
	}

	public BlockCandyLeaves() {
		super();
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(AT.tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 1));
		this.leavesFancy = true;
		this.setUnlocalizedName(getName());
		GameRegistry.register(this, new ResourceLocation("at", getName()));
		GameRegistry.register(new ItemBlockWithMeta(this), new ResourceLocation("at", getName()));
		
		ATItemStacks.candyLeaves_0 = new ItemStack(this, 1, 0);
		ATItemStacks.candyLeaves_4 = new ItemStack(this, 1, 4);
		ATItemStacks.candyLeaves_8 = new ItemStack(this, 1, 8);
		ATItemStacks.candyLeaves_12 = new ItemStack(this, 1, 12);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		
	}

	@Override
	public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {
		
	}
	
	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean fancy) {
		this.leavesFancy = true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
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
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
}
