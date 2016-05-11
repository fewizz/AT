package fewizz.at.proxy;

import fewizz.at.init.ATBlocks;
import fewizz.at.init.ATItems;
import fewizz.at.util.IHasName;

import static fewizz.at.init.ATItemStacks.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends Proxy {

	@Override
	public void registerRenders() {
		/** Blocks **/
		registerRender(ATBlocks.bubbleDirt);
		registerRender(ATBlocks.bubbleGrass);
		registerRender(ATBlocks.bubbleLeaves);
		registerRender(ATBlocks.candyGrass);
		registerRender(ATBlocks.candyLeaves, 0);
		registerRender(ATBlocks.candyLeaves, 1);
		registerRender(ATBlocks.candyLeaves, 2);
		registerRender(ATBlocks.candyLeaves, 3);

		/** Items **/
		registerRender(ATItems.teleporter);
	}

	@Override
	public void registerItemVariants() {
		registerItemVariants(candyLeaves0, candyLeaves1, candyLeaves2, candyLeaves3);
	}

	/** Without meta **/
	public void registerRender(Item item) {
		if (item instanceof IHasName)
			this.registerRender(item, ((IHasName) item).getName());
		else
			this.registerRender(item, item.getUnlocalizedName().substring("item.".length()));
	}

	/** Without meta **/
	public void registerRender(Block block) {
		if (block instanceof IHasName)
			this.registerRender(Item.getItemFromBlock(block), ((IHasName) block).getName());
		else
			this.registerRender(Item.getItemFromBlock(block), block.getUnlocalizedName().substring("tile.".length()));
	}

	public void registerRender(Item item, String name) {
		this.registerRender(item, name, false, 0);
	}

	/** With meta **/
	public void registerRender(Block block, int meta) {
		if (block instanceof IHasName)
			this.registerRender(Item.getItemFromBlock(block), ((IHasName) block).getName(), meta);
		else
			this.registerRender(Item.getItemFromBlock(block), block.getUnlocalizedName().substring("tile.".length()), meta);
	}

	public void registerRender(Item item, String name, int meta) {
		this.registerRender(item, name, true, meta);
	}

	/** Main Registrator **/
	public void registerRender(Item item, String name, boolean hasMeta, int meta) {
		if (hasMeta)
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("at:" + name + "_" + meta, "inventory"));
		else
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("at:" + name, "inventory"));

	}

	/** For items/blocks with meta **/
	public void registerItemVariants(ItemStack... stacks) {
		for (ItemStack stack : stacks) {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block instanceof IHasName)
				ModelBakery.registerItemVariants(stack.getItem(), new ResourceLocation("at:" + ((IHasName) block).getName() + "_" + stack.getMetadata()));
			else
				ModelBakery.registerItemVariants(stack.getItem(), new ResourceLocation("at:" + block.getUnlocalizedName().substring(5) + "_" + stack.getMetadata()));
		}
	}
}
