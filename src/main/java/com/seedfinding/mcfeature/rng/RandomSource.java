package com.seedfinding.mcfeature.rng;

public interface RandomSource {
	// Very simplified, base interface for both random number generators

	void setSeed(long seed);

	int nextBits(int bits);

	void skip(int states);
}
