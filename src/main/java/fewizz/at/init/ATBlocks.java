package fewizz.at.init;

import fewizz.at.block.BlockBubbleDirt;
import fewizz.at.block.BlockBubbleGrass;
import fewizz.at.block.BlockBubbleLeaves;
import fewizz.at.block.BlockBubbleTallGrass;
import fewizz.at.block.BlockCandyGrass;
import fewizz.at.block.BlockCandyLeaves;
import fewizz.at.block.BlockSedge;
import fewizz.at.block.BlockCandyLog;
import net.minecraft.block.Block;

public class ATBlocks {

	public static Block bubbleGrass;
	public static Block bubbleDirt;
	public static Block bubbleTallGrass;
	public static Block bubbleLeaves;
	public static Block candyGrass;
	public static Block candyLeaves;
	public static Block candyLog;
	public static Block sedge;

	public static void init() {
		bubbleGrass = new BlockBubbleGrass();
		bubbleDirt = new BlockBubbleDirt();
		bubbleTallGrass = new BlockBubbleTallGrass();
		bubbleLeaves = new BlockBubbleLeaves();
		candyGrass = new BlockCandyGrass();
		candyLeaves = new BlockCandyLeaves();
		candyLog = new BlockCandyLog();
		sedge = new BlockSedge();
	}

}
