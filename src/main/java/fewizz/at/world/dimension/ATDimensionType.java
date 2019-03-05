package fewizz.at.world.dimension;

import net.minecraft.world.dimension.DimensionType;

public class ATDimensionType extends DimensionType {
	public static final ATDimensionType INSTANCE = new ATDimensionType();

	protected ATDimensionType() {
		super(3, "", "at", ATDimension::new, true);
	}

}
