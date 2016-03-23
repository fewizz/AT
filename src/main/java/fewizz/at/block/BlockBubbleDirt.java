package fewizz.at.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBubbleDirt extends Block implements IATBlock {

	@Override
	public String getName() {
		return "dirt_bubble";
	}
	
	public BlockBubbleDirt() {
		super(Material.cloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeGrass);
		this.setHardness(0.5F);
		this.setUnlocalizedName(getName());
		GameRegistry.registerBlock(this, getName());
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return true;
	}

}
