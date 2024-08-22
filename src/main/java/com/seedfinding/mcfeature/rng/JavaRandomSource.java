package com.seedfinding.mcfeature.rng;

public class JavaRandomSource implements RandomSource {
	private static final long MULTIPLIER = 25214903917L;
	private static final long ADDEND = 11L;
	private static final long MODULUS = (1L << 48);
	private static final long MASK = MODULUS - 1;

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
	public float nextFloat() {
		return this.nextBits(24) / ((float)(1 << 24));
	}

	@Override
	public double nextDouble() {
		return (((long)(nextBits(26)) << 27) + nextBits(27)) * 0x1.0p-53;
	}

	@Override
	public void skip(int states) {
		long adv = Math.floorMod(states, MODULUS);
		long iMul = MULTIPLIER;
		long iAdd = ADDEND;

		while (adv != 0L) {
			if ((adv & 1L) != 0L) {
				this.seed = (this.seed * iMul + iAdd) & MASK;
			}

			adv >>= 1;

			iAdd += iMul * iAdd;
			iMul *= iMul;
			iAdd &= MASK;
			iMul &= MASK;
		}
	}

	// -----------------------------------------------------

	private void nextSeed() {
		this.seed = (this.seed * MULTIPLIER + ADDEND) & MASK;
	}
}
