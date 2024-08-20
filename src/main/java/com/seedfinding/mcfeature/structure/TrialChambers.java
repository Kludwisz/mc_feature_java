package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.UnsupportedVersion;
import com.seedfinding.mccore.version.VersionMap;

public class TrialChambers extends UniformStructure<TrialChambers> {

	public static final VersionMap<Config> CONFIGS = new VersionMap<RegionStructure.Config>()
		.add(MCVersion.v1_21, new RegionStructure.Config(34, 12, 94251327));

	public TrialChambers(MCVersion version) {
		super(CONFIGS.getAsOf(version), version);
	}

	public TrialChambers(RegionStructure.Config config, MCVersion version) {
		super(config, version);

		if(this.getVersion().isOlderThan(MCVersion.v1_21)) {
			throw new UnsupportedVersion(this.getVersion(), "trial chambers");
		}
	}

	public static String name() {
		return "trial_chambers";
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
