package fewizz.at.block;

import java.util.ArrayList;
import java.util.List;

import fewizz.at.AT;
import fewizz.at.init.ATBlocks;
import fewizz.at.item.block.ItemBlockWithMeta;
import fewizz.at.util.IHasName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	@Override
	public String getName() {
		return "sedge";
	}

	public BlockSedge() {
		super(Material.plants);
		//this.setStepSound(soundTypeGrass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, Integer.valueOf(1)));
		this.setUnlocalizedName(getName());
		GameRegistry.registerBlock(this, ItemBlockWithMeta.class, getName());
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

	/*@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		BlockPos posBot = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		BlockPos posTop = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		
		if (worldIn.getBlockState(posBot).getBlock().getMaterial() == Material.air) {
			worldIn.destroyBlock(pos, true);
		}
		
		if (worldIn.getBlockState(posTop).getBlock().getMaterial() == Material.air) {
			if (worldIn.getBlockState(pos).getBlock() == ATBlocks.sedge && ATBlocks.sedge.getMetaFromState(worldIn.getBlockState(pos)) != 2) {
				worldIn.destroyBlock(pos, true);
			}
		}
	}*/

	//@Override
	//public boolean isOpaqueCube() {
	//	return false;
	//}

	//@Override
	//public boolean isFullCube() {
	//	return false;
	//}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}

	//@SideOnly(Side.CLIENT)
	//public EnumWorldBlockLayer getBlockLayer() {
	//	return EnumWorldBlockLayer.CUTOUT;
	//}

	//@Override
	//public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
	//	return null;
	//}

	//@Override
	//public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
	//	return EnumPlantType.Beach;
	//}

	//@Override
	//public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
	//	return this.getDefaultState();
	//}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, net.minecraft.util.math.BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, net.minecraft.util.math.BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

}
