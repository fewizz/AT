package fewizz.at.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBubbleDirt extends Block{

	public BlockBubbleDirt() {
		super(Material.cloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeGrass);
		this.setUnlocalizedName("dirt_bubble");
		GameRegistry.registerBlock(this, "dirt_bubble");
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return true;
	}

}
