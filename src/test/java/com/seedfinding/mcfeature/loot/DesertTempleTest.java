package com.seedfinding.mcfeature.loot;

import com.seedfinding.mcfeature.rng.WorldgenRandom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesertTempleTest {

	// Passed as of 17/09/2024
	@Test
	public void smallCorrectnessTest_1_21() {
		final long worldseed = -2752445844961526872L;
		WorldgenRandom wgRand = new WorldgenRandom(WorldgenRandom.Type.XOROSHIRO);

		List<TempleData> dataset = Arrays.asList(
			new TempleData(37, 176, Arrays.asList(5263133367870012016L, 343607249671263964L, 3825594365775757486L, -6838179548583512369L)),
			new TempleData(235, 111, Arrays.asList(-1716585375698199764L, -1962712080712569293L, 8479729077322452861L, -3438898103040492034L)),
			new TempleData(-819, 302, Arrays.asList(3118490990552244433L, -1756548195744242347L, 3224034487956847626L, -2340087342843729864L)),
			new TempleData(-652, -1140, Arrays.asList(-7730986811590757340L, -7656545743977083705L, -2753569511936659235L, 6315569313403279602L)),
			new TempleData(1033, 1461, Arrays.asList(6121307490481667324L, 2341366926896804477L, 8085947187729368142L, -1107323280883619817L))
		);

		for (TempleData data : dataset) {
			assertTrue(data.test(wgRand, worldseed));
		}
	}

	private static class TempleData {
		public int cx;
		public int cz;
		public List<Long> targetLootseeds;

		public TempleData(int cx, int cz, List<Long> lootseeds) {
			this.cx = cx;
			this.cz = cz;
			this.targetLootseeds = lootseeds;
		}

		public boolean test(WorldgenRandom decoratorRandom, long worldseed) {
			decoratorRandom.setDecoratorSeed(worldseed, cx, cz, 40001);
			decoratorRandom.nextInt(3);

			List<Long> generatedSeeds = new ArrayList<>();
			for (int i = 0; i < 4; i++)
				generatedSeeds.add(decoratorRandom.nextLong());

			boolean failedAny = false;

			for (long lootseed : generatedSeeds) {
				if (!targetLootseeds.contains(lootseed)) {
					System.out.println("Failed " + lootseed + ", expected any of " + targetLootseeds);
					failedAny = true;
				}
			}

			return !failedAny;
		}
	}
}
