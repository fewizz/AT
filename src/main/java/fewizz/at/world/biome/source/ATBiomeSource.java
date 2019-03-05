package fewizz.at.world.biome.source;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import fewizz.at.init.ATBiomes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.feature.StructureFeature;

public class ATBiomeSource extends BiomeSource {

	@Override
	public Biome getBiome(int var1, int var2) {
		return ATBiomes.BUBBLE;
	}

	@Override
	public Biome[] sampleBiomes(int var1, int var2, int var3, int var4, boolean var5) {
		Biome[] a = new Biome[var3*var4];
		Arrays.fill(a, ATBiomes.BUBBLE);
		return a;
	}

	@Override
	public Set<Biome> getBiomesInArea(int var1, int var2, int var3) {
		return Sets.newHashSet(ATBiomes.BUBBLE);
	}

	@Override
	public BlockPos locateBiome(int var1, int var2, int var3, List<Biome> var4, Random var5) {
		return null;
	}

	@Override
	public boolean hasStructureFeature(StructureFeature<?> var1) {
		return false;
	}

	@Override
	public Set<BlockState> getTopMaterials() {
		return null;
	}

}
