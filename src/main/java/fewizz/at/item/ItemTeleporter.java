package fewizz.at.item;

import fewizz.at.world.dimension.ATDimensionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemTeleporter extends Item {
	
	public ItemTeleporter(Settings item$Settings_1) {
		super(item$Settings_1);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
		if(!world_1.isClient) {
			playerEntity_1.changeDimension(ATDimensionType.INSTANCE);
		}
		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
	}
}
