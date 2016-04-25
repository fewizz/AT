package fewizz.at.init;

import fewizz.at.item.ItemGeneratorTester;
import fewizz.at.item.ItemTeleporter;
import net.minecraft.item.Item;

public class ATItems {
	
	public static Item teleporter;
	public static Item generatorTester;

	public static void init() {
		teleporter = new ItemTeleporter();
		generatorTester = new ItemGeneratorTester();
	}
	
}
