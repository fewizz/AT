package fewizz.at.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.IntegerProperty;
import net.minecraft.util.math.BoundingBox;

public class BlockSedge extends Block {

	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);

	protected static final BoundingBox AABB = new BoundingBox(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	
	public BlockSedge(Block.Settings block$Settings_1) {
		super(block$Settings_1);
		this.setDefaultState(getDefaultState().with(TYPE, 0));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.with(TYPE);
	}
	
	public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
	}
}
