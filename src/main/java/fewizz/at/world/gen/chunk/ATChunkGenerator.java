package fewizz.at.world.gen.chunk;

import fewizz.at.world.biome.source.ATBiomeSource;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

public class ATChunkGenerator extends SurfaceChunkGenerator<ATChunkGeneratorConfig> {

	public ATChunkGenerator(IWorld iWorld_1) {
		super(
			iWorld_1,
			new ATBiomeSource(),
			4,
			8,
			256,
			new ATChunkGeneratorConfig(),
			false
		);
	}

	@Override
    protected double computeNoiseFalloff(double depth, double scale, int y) {
        double double7 = 8.5;
        double double9 = (y - (8.5 + depth * 8.5 / 8.0 * 4.0)) * 12.0 * 128.0 / 256.0 / scale;
        if (double9 < 0.0) {
            double9 *= 4.0;
        }
        return double9;
    }
    
    @Override
    protected double[] computeNoiseRange(int x, int z) {
        double[] var3 = new double[2];
        float float5 = 0.0f;
        float float6 = 0.0f;
        float float7 = 0.0f;
        int integer8 = 2;
        float float9 = this.biomeSource.getBiomeForNoiseGen(x, z).getDepth();
        for (int var9 = -2; var9 <= 2; ++var9) {
            for (int var10 = -2; var10 <= 2; ++var10) {
                Biome biome12 = this.biomeSource.getBiomeForNoiseGen(x + var9, z + var10);
                float float13 = biome12.getDepth();
                float float14 = biome12.getScale();
                if (float13 > 0.0f) {
                    float13 = 1.0f + float13 * 2.0f;
                    float14 = 1.0f + float14 * 4.0f;
                }
                float float15 = 0.5F;//OverworldChunkGenerator.BIOME_WEIGHT_TABLE[var9 + 2 + (var10 + 2) * 5] / (float13 + 2.0f);
                if (biome12.getDepth() > float9) {
                    float15 /= 2.0f;
                }
                float5 += float14 * float15;
                float6 += float13 * float15;
                float7 += float15;
            }
        }
        float5 /= float7;
        float6 /= float7;
        float5 = float5 * 0.9f + 0.1f;
        float6 = (float6 * 4.0f - 1.0f) / 8.0f;
        var3[0] = float6;// + this.c(x, z);
        var3[1] = float5;
        return var3;
    }

    @Override
    protected void sampleNoiseColumn(double[] buffer, int x, int z) {
        double double5 = 684.4119873046875;
        double double7 = 684.4119873046875;
        double double9 = 8.555149841308594;
        double double11 = 4.277574920654297;
        int i1 = -10;
        int i2 = 3;
        this.sampleNoiseColumn(buffer, x, z, double5, double7, double9, double11, i2, i1);
    }

	@Override
	public int getSpawnHeight() {
		return 100;
	}

}
