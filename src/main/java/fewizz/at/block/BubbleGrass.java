package fewizz.at.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.FernBlock;

public class BubbleGrass extends FernBlock {
	
	public BubbleGrass() {
		super(FabricBlockSettings.copy(Blocks.GRASS).build());
	}

}
