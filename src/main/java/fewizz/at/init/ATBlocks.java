package fewizz.at.init;

import fewizz.at.block.BlockBubbleDirt;
import fewizz.at.block.BlockBubbleGrass;
import fewizz.at.block.BlockBubbleTallGrass;
import fewizz.at.block.BlockCandyLeaves;
import fewizz.at.block.BlockSedge;
import net.minecraft.block.Block;

public class ATBlocks {

	public static Block bubbleGrass;
	public static Block bubbleDirt;
	public static Block bubbleTallGrass;
	public static Block candyLeaves;
	public static Block sedge;

	public static void init() {
		bubbleGrass = new BlockBubbleGrass();
		bubbleDirt = new BlockBubbleDirt();
		bubbleTallGrass = new BlockBubbleTallGrass();
		//candyLeaves = new BlockCandyLeaves();
		sedge = new BlockSedge();
	}

}
