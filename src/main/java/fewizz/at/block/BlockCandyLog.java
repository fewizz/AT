package fewizz.at.block;

import fewizz.at.util.IHasName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockCandyLog extends BlockLog implements IHasName {

	@Override
	public String getName() {
		return "log_candy";
	}

	public BlockCandyLog() {
		super();
		this.setUnlocalizedName(getName());
		this.setDefaultState(getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		GameRegistry.register(this, new ResourceLocation("at", getName()));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

		switch (meta) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
				break;
			case 1:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
				break;
			case 2:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState state) {

		switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
			case Y:
				return 0;
			case X:
				return 1;
			case Z:
				return 2;
			default:
				return 3;
		}
	}
}
