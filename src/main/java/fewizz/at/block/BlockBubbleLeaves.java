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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBubbleLeaves extends BlockLeaves implements IHasName {

	@Override
	public String getName() {
		return "leaves_bubble";
	}

	public BlockBubbleLeaves() {
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, Boolean.valueOf(true)).withProperty(CHECK_DECAY, Boolean.valueOf(true)));
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(AT.tab);
		this.leavesFancy = true;
		this.setUnlocalizedName(getName());
		GameRegistry.register(this, new ResourceLocation("at", getName()));
		GameRegistry.register(new ItemBlock(this), new ResourceLocation("at", getName()));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DECAYABLE, CHECK_DECAY });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int decayable = state.getValue(DECAYABLE) ? 1 : 0;
		int checkDecay = state.getValue(CHECK_DECAY) ? 1 : 0;
		
		return (decayable << 1) | checkDecay;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean decayable = (meta & 0b10) == 1;
		boolean checkDecay = (meta & 0b1) == 1;
		
		return getDefaultState().withProperty(DECAYABLE, decayable).withProperty(CHECK_DECAY, checkDecay);
	}

	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean fancy) {
		this.leavesFancy = true;
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
