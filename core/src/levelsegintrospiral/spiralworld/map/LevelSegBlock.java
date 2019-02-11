package levelsegintrospiral.spiralworld.map;

import levelsegintrospiral.tools.ExRandIntList;

public class LevelSegBlock {
	private LevelSeg[] randomLevelSegs;

	public LevelSegBlock(LevelSegAtlas lsAtlas, long seed) {
		createRandomLevelSegs(lsAtlas, seed);
	}

	/*
	 * Create a randomly ordered (exhaustively randomly ordered) list of level segs, from the list of
	 * all level segs.
	 */
	private void createRandomLevelSegs(LevelSegAtlas lsAtlas, long seed) {
		randomLevelSegs = new LevelSeg[lsAtlas.getNumLevelSegs()];
		ExRandIntList eril = new ExRandIntList(seed, lsAtlas.getNumLevelSegs());
		for(int i=0; i<lsAtlas.getNumLevelSegs(); i++)
			randomLevelSegs[i] = lsAtlas.getLevelSegByIndex(eril.getIntAtIndex(i));
	}

	public LevelSegSlice getSliceForTileX(int tileX) {
		int remaining = tileX;
		int lsIndex = 0;
		while(lsIndex < randomLevelSegs.length) {
			if(remaining < randomLevelSegs[lsIndex].getWidth()) {
				return new LevelSegSlice(randomLevelSegs[lsIndex], remaining);
			}
			else {
				remaining -= randomLevelSegs[lsIndex].getWidth();
				lsIndex++;
			}
		}
		return null;
	}
}
