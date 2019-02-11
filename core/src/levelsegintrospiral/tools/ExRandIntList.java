package levelsegintrospiral.tools;

import java.util.Random;

/*
 * Exhaustive random integer list of static size.
 */
public class ExRandIntList {
	private int[] exRandInt;

	/*
	 * Use seed to start the random number generator, then generate a list of integers.
	 */
	public ExRandIntList(long seed, int size) {
		exRandInt = new int[size];
		createList(seed, size);
	}

	private void createList(long seed, int size) {
		int numUnused = size;
		boolean[] unused = new boolean[size];
		for(int i=0; i<size; i++)
			unused[i] = true;

		Random rand = new Random();
		rand.setSeed(seed);
		for(int i=0; i<size; i++) {
			int r = Math.floorMod(rand.nextInt(), numUnused);
			int u = 0;
			while(u < unused.length) {
				if(unused[u]) {
					if(r == 0) {
						exRandInt[i] = u;
						unused[u] = false;
						numUnused--;
						break;
					}
					r--;
				}
				u++;
			}
		}
	}

	public int getIntAtIndex(int index) {
		return exRandInt[index];
	}

	public int size() {
		return exRandInt.length;
	}
}
