package com.seedfinding.mcfeature.rng;

public interface RandomSource {

	void setSeed(long seed);

	int nextBits(int bits);

	float nextFloat();

	double nextDouble();

	void skip(int states);
}
