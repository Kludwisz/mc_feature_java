package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;

public class BastionRemnant extends UniformStructure<BastionRemnant> {

	public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_16, new RegionStructure.Config(30, 4, 30084232))
		.add(MCVersion.v1_16_1, new RegionStructure.Config(27, 4, 30084232));

	public BastionRemnant(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public BastionRemnant(RegionStructure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "bastion_remnant";
	}

	@Override
	public boolean canStart(Data<BastionRemnant> data, long structureSeed, ChunkRand rand) {
		if(!super.canStart(data, structureSeed, rand)) return false;
		return rand.nextInt(5) >= 2;
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		CPos bastion = super.getInRegion(structureSeed, regionX, regionZ, rand);
		return rand.nextInt(5) >= 2 ? bastion : null;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.NETHER;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.NETHER_WASTES || biome == Biomes.SOUL_SAND_VALLEY || biome == Biomes.WARPED_FOREST
			|| biome == Biomes.CRIMSON_FOREST;
	}

	// TODO getDecorationSalt
}
