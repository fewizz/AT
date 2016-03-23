package fewizz.at.proxy;

import fewizz.at.block.IATBlock;
import fewizz.at.init.ATBlocks;
import static fewizz.at.init.ATItemStacks.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends Proxy {

	@Override
	public void registerRenders() {
		registerRender(ATBlocks.bubbleDirt);
		registerRender(ATBlocks.bubbleGrass);
		registerRender(ATBlocks.candyLeaves, 0);
		registerRender(ATBlocks.candyLeaves, 4);
		registerRender(ATBlocks.candyLeaves, 8);
		registerRender(ATBlocks.candyLeaves, 12);
	}
	
	@Override
	public void registerItemVariants() {
		registerItemVariants(candyLeaves_0, candyLeaves_4, candyLeaves_8, candyLeaves_12);
	}

	/** Without meta **/
	public void registerRender(Block block) {
		if (block instanceof IATBlock)
			this.registerRender(Item.getItemFromBlock(block), ((IATBlock) block).getName());
		else
			this.registerRender(Item.getItemFromBlock(block), block.getUnlocalizedName().substring(5));
	}

	public void registerRender(Item item, String name) {
		this.registerRender(item, name, false, 0);
	}

	/** With meta **/
	public void registerRender(Block block, int meta) {
		if (block instanceof IATBlock)
			this.registerRender(Item.getItemFromBlock(block), ((IATBlock) block).getName(), meta);
		else
			this.registerRender(Item.getItemFromBlock(block), block.getUnlocalizedName().substring(5), meta);
	}

	public void registerRender(Item item, String name, int meta) {
		this.registerRender(item, name, true, meta);
	}

	/** Main Registrator **/
	public void registerRender(Item item, String name, boolean hasMeta, int meta) {
		if (hasMeta) {
			System.out.println("at:" + name + "_" + Integer.toString(meta));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("at:" + name + "_" + meta, "inventory"));
		}
		else {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("at:" + name, "inventory"));
		}
	}

	/** For items/blocks with meta **/
	public void registerItemVariants(ItemStack... stacks) {
		for (ItemStack stack : stacks) {
			Block block = Block.getBlockFromItem(stack.getItem());
			if(block instanceof IATBlock)
				ModelBakery.registerItemVariants(stack.getItem(), new ResourceLocation("at:" + ((IATBlock) block).getName() + "_" + stack.getMetadata()));
			else
				ModelBakery.registerItemVariants(stack.getItem(), new ResourceLocation("at:" + block.getUnlocalizedName().substring(5) + "_" + stack.getMetadata()));
		}
	}
}
