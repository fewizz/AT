package fewizz.at.item;

import java.util.Random;

import fewizz.at.AT;
import fewizz.at.util.ATConfiguration;
import fewizz.at.util.IHasName;
import fewizz.at.world.ATTeleporter;
import fewizz.at.world.gen.feature.WorldGenCandyTree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemGeneratorTester extends Item implements IHasName {

	public final Random rnd = new Random();
	
	@Override
	public String getName() {
		return "generatorTester";
	}
	
	public ItemGeneratorTester() {
		setCreativeTab(AT.tab);
		setUnlocalizedName(getName());
		GameRegistry.register(this, new ResourceLocation("at", getName()));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			new WorldGenCandyTree().generate(worldIn, rnd, new BlockPos(playerIn));
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
