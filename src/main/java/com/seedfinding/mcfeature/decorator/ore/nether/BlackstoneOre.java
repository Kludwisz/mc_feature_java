package com.seedfinding.mcfeature.decorator.ore.nether;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.decorator.ore.HeightProvider;
import com.seedfinding.mcfeature.decorator.ore.RegularOreDecorator;

public class BlackstoneOre extends RegularOreDecorator<RegularOreDecorator.Config, RegularOreDecorator.Data<BlackstoneOre>> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<Config>()
		.add(MCVersion.v1_16, new Config(12, 7, 33, 2, HeightProvider.range(5, 10, 37), Blocks.BLACKSTONE, NETHERRACK)
			.add(9, 7, Biomes.CRIMSON_FOREST)
			.add(10, 7, Biomes.WARPED_FOREST))
		.add(MCVersion.v1_17, new Config(12, 7, 33, 2, HeightProvider.uniformRange(5, 31), Blocks.BLACKSTONE, NETHERRACK)
			.add(9, 7, Biomes.CRIMSON_FOREST)
			.add(10, 7, Biomes.WARPED_FOREST));

	public BlackstoneOre(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	@Override
	public String getName() {
		return name();
	}

	public static String name() {
		return "blackstone_ore";
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.NETHER_WASTES || biome == Biomes.SOUL_SAND_VALLEY || biome == Biomes.CRIMSON_FOREST || biome == Biomes.WARPED_FOREST;
	}

}
