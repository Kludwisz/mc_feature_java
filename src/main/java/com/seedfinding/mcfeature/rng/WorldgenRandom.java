package com.seedfinding.mcfeature.rng;

import java.util.Random;

public class WorldgenRandom {
	private final RandomSource source;

	public WorldgenRandom(WorldgenRandom.Type type) {
		this.source = type == WorldgenRandom.Type.JAVA ? new JavaRandomSource() : new XoroshiroRandomSource();
	}

	// ---------------------------------------------------
	// Seeding functions
	// ---------------------------------------------------

	public void setSeed(long seed) {
		this.source.setSeed(seed);
	}

	public void setPopulationSeed(long structureSeed, int chunkX, int chunkZ) {
		this.setSeed(structureSeed);
		long a = this.nextLong();
		long b = this.nextLong();
		this.setSeed(((long)chunkX << 4) * a + ((long)chunkZ << 4) * b ^ structureSeed);
	}

	public void setDecoratorSeed(long structureSeed, int chunkX, int chunkZ, int index, int step) {
		this.setDecoratorSeed(structureSeed, chunkX, chunkZ, step * 10000 + index);
	}

	public void setDecoratorSeed(long structureSeed, int chunkX, int chunkZ, int salt) {
		this.setSeed(structureSeed);
		long a = this.nextLong();
		long b = this.nextLong();
		long populationSeed = ((long)chunkX << 4) * a + ((long)chunkZ << 4) * b ^ structureSeed;
		this.setSeed(populationSeed + salt);
	}

	// ---------------------------------------------------
	// RNG functions
	// ---------------------------------------------------

	public int nextInt(int bound) {
		int bits, val;
		final int m = bound - 1;

		new Random().nextInt(3);
		if ((bound & m) == 0) {
			long x = bound * (long)source.nextBits(31);
			return (int) (x >> 31);
		}

		do {
			bits = source.nextBits(31);
			val = bits % bound;
		}
		while (bits - val + m < 0);

		return val;
	}

	public long nextLong() {
		return ((long) source.nextBits(32) << 32) + source.nextBits(32);
	}

	public float nextFloat() {
		return 0F;
		// TODO
		//return source.nextFloat();
	}

	public double nextDouble() {
		return 0D;
		// TODO
		//return source.nextDouble();
	}


	// ---------------------------------------------------

	public enum Type {
		JAVA,
		XOROSHIRO
	}
}
