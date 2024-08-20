package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.UnsupportedVersion;
import com.seedfinding.mccore.version.VersionMap;

public class AncientCity extends UniformStructure<AncientCity> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_19, new RegionStructure.Config(24, 8, 20083232));

	public AncientCity(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public AncientCity(RegionStructure.Config config, MCVersion version) {
		super(config, version);

		if(this.getVersion().isOlderThan(MCVersion.v1_19)) {
			throw new UnsupportedVersion(this.getVersion(), "ancient city");
		}
	}

	public static String name() {
		return "ancient_city";
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		// biomes are unsupported for new versions
		return true;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}
}
