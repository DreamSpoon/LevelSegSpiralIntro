package levelsegintrospiral.spiralworld.map;

public class LevelSegSlice {
	public LevelSeg levelSeg;
	public int offsetX;

	public LevelSegSlice(LevelSeg levelSeg, int offsetX) {
		this.levelSeg = levelSeg;
		this.offsetX = offsetX;
	}

	public LevelSeg getLevelSeg() {
		return levelSeg;
	}
}
