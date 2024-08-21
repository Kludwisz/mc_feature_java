package com.seedfinding.mcfeature.rng;

public class JavaRandomSource implements RandomSource {
	private static final long MULTIPLIER = 25214903917L;
	private static final long ADDEND = 11L;
	private static final long MASK = (1L << 48) - 1;

	private long seed;

	public JavaRandomSource() {
		this.seed = 0L;
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed ^ MULTIPLIER & MASK;
	}

	@Override
	public int nextBits(int bits) {
		this.nextSeed();
		return (int)(this.seed >>> (48 - bits));
	}

	@Override
	public void skip(int states) {
		// TODO implement efficient version
		for (int i = 0; i < states; i++)
			this.nextSeed();
	}

	// -----------------------------------------------------

	private void nextSeed() {
		this.seed = (this.seed * MULTIPLIER + ADDEND) & MASK;
	}
}
