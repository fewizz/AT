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
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 1).withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, true));
		this.leavesFancy = true;
		this.setUnlocalizedName(getName());
		GameRegistry.register(this, new ResourceLocation("at", getName()));
		GameRegistry.register(new ItemBlockWithMeta(this), new ResourceLocation("at", getName()));
		
		ATItemStacks.candyLeaves0 = new ItemStack(this, 1, 0);
		ATItemStacks.candyLeaves1 = new ItemStack(this, 1, 1);
		ATItemStacks.candyLeaves2 = new ItemStack(this, 1, 2);
		ATItemStacks.candyLeaves3 = new ItemStack(this, 1, 3);
	}
	
	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean fancy) {
		this.leavesFancy = true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE, DECAYABLE, CHECK_DECAY });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int type = state.getValue(TYPE);
		int decayable = state.getValue(DECAYABLE) ? 1 : 0;
		int checkDecay = state.getValue(CHECK_DECAY) ? 1 : 0;
		
		return (decayable << 3) | (checkDecay << 2) | type;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean decayable = (meta & 0b1000) == 1;
		boolean checkDecay = (meta & 0b0100) == 1;
		int type = meta & 0b11;
		
		return getDefaultState().withProperty(TYPE, type).withProperty(DECAYABLE, decayable).withProperty(CHECK_DECAY, checkDecay);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
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
