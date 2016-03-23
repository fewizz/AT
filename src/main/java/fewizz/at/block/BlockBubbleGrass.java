package fewizz.at.block;

import java.util.ArrayList;
import java.util.List;

import fewizz.at.init.ATBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBubbleGrass extends Block implements IATBlock {
	
	@Override
	public String getName() {
		return "grass_bubble";
	}

	public BlockBubbleGrass() {
		super(Material.grass);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeGrass);
		this.setHardness(0.5F);
		setUnlocalizedName(getName());
		GameRegistry.registerBlock(this, getName());
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return true;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(ATBlocks.bubbleDirt));
		return list;
	}
}
