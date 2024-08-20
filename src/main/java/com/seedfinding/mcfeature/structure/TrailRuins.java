package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.UnsupportedVersion;
import com.seedfinding.mccore.version.VersionMap;

public class TrailRuins extends UniformStructure<TrailRuins> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_20, new RegionStructure.Config(34, 8, 83469867));

	public TrailRuins(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public TrailRuins(RegionStructure.Config config, MCVersion version) {
		super(config, version);

		if(this.getVersion().isOlderThan(MCVersion.v1_20)) {
			throw new UnsupportedVersion(this.getVersion(), "trail ruins");
		}
	}

	public static String name() {
		return "trail_ruins";
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
