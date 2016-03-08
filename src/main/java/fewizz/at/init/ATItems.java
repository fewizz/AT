package fewizz.at.init;

import fewizz.at.item.ItemTeleporter;
import net.minecraft.item.Item;

public class ATItems {
	
	public static Item teleporter;

	public static void init() {
		teleporter = new ItemTeleporter();
	}
	
}
