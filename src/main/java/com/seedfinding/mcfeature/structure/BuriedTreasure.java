package com.seedfinding.mcfeature.structure;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.loot.ILoot;
import com.seedfinding.mcfeature.structure.generator.Generator;
import com.seedfinding.mcfeature.structure.generator.structure.BuriedTreasureGenerator;

public class BuriedTreasure extends RegionStructure<BuriedTreasure.Config, RegionStructure.Data<BuriedTreasure>> implements ILoot {

	public static final VersionMap<BuriedTreasure.Config> CONFIGS = new VersionMap<BuriedTreasure.Config>()
		.add(MCVersion.v1_13, new BuriedTreasure.Config(0.01F, 10387320));

	public BuriedTreasure(MCVersion version) {
		this(CONFIGS.getAsOf(version), version);
	}

	public BuriedTreasure(BuriedTreasure.Config config, MCVersion version) {
		super(config, version);
	}

	public static String name() {
		return "buried_treasure";
	}

	public float getChance() {
		return this.getConfig().chance;
	}

	@Override
	public boolean canStart(RegionStructure.Data<BuriedTreasure> data, long structureSeed, ChunkRand rand) {
		rand.setSeed(data.baseRegionSeed + structureSeed);
		return rand.nextFloat() < this.getChance();
	}

	@Override
	public CPos getInRegion(long structureSeed, int regionX, int regionZ, ChunkRand rand) {
		rand.setRegionSeed(structureSeed, regionX, regionZ, this.getSalt(), this.getVersion());
		return rand.nextFloat() < this.getChance() ? new CPos(regionX, regionZ) : null;
	}

	@Override
	public Dimension getValidDimension() {
		return Dimension.OVERWORLD;
	}

	@Override
	public boolean isValidBiome(Biome biome) {
		return biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
	}

	@Override
	public RegionStructure.Data<BuriedTreasure> at(int chunkX, int chunkZ) {
		return new RegionStructure.Data<>(this, chunkX, chunkZ);
	}

	// TODO update to new version
	@Override
	public int getDecorationSalt() {
		return this.getVersion().isOlderOrEqualTo(MCVersion.v1_13_2) ? 20002 : 30001;
	}

	@Override
	public boolean isCorrectGenerator(Generator generator) {
		return generator instanceof BuriedTreasureGenerator;
	}

	@Override
	public SpecificCalls getSpecificCalls() {
		return null;
	}

	@Override
	public boolean shouldAdvanceInChunks() {
		return false;
	}

	public static class Config extends RegionStructure.Config {
		public static final int SPACING = 1;
		public static final int SEPARATION = 0;

		private final float chance;

		public Config(float chance, int salt) {
			this(chance, SPACING, SEPARATION, salt);
		}

		public Config(float chance, int spacing, int separation, int salt) {
			super(spacing, separation, salt);
			this.chance = chance;
		}
	}

}
