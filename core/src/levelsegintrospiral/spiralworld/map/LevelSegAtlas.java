package levelsegintrospiral.spiralworld.map;

public class LevelSegAtlas {
	private LevelSeg[] allLevelSegs;
	private int totalWidthLevelSegs;

	/*
	 * Load all known level segment files, and calculate the total of all segment widths.
	 */
	public LevelSegAtlas(String[] filenames) {
		totalWidthLevelSegs = 0;
		allLevelSegs = new LevelSeg[filenames.length];
		for(int i=0; i<filenames.length; i++) {
			allLevelSegs[i] = LevelSegLoader.loadMap(filenames[i]);
			totalWidthLevelSegs += allLevelSegs[i].getWidth();
		}
	}

	public LevelSeg[] getAllLevelSegs() {
		return allLevelSegs;
	}

	public int getNumLevelSegs() {
		return allLevelSegs.length;
	}

	public int getTotalWidth() {
		return totalWidthLevelSegs;
	}

	public LevelSeg getLevelSegByIndex(int index) {
		return allLevelSegs[index];
	}
}
