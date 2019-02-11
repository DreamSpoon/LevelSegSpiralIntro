package levelsegintrospiral.spiralworld.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import levelsegintrospiral.spiralworld.Arc;
import levelsegintrospiral.spiralworld.Spiral;

/*
 * Notes:
 * -each slice is one tile wide
 */
public class LevelSegSpiral {
	private Spiral spiral;
	private LevelSegAtlas lsAtlas;
	private double sliceTheta;
	private double startOffsetMap;
	private int minDrawSlice;
	private int maxSliceHeight;

	public LevelSegSpiral(Spiral spiral, String[] filenames, int maxSliceHeight) {
		this.spiral = spiral;
		this.maxSliceHeight = maxSliceHeight;

		lsAtlas = new LevelSegAtlas(filenames);
		sliceTheta = 0f;
		startOffsetMap = 0f;
		minDrawSlice = -1;	// -1 = ignore min draw slice
	}

	public void drawSlicesInView(SpriteBatch batch, Vector2 viewPos, double viewRadius) {
		Arc[] viewArcs = spiral.getArcsInView(viewPos, viewRadius);
		for(Arc a : viewArcs)
			drawSlices(batch, a.start, a.end);
	}

	public void drawSlices(SpriteBatch batch, double startTheta, double endTheta) {
		LevelSegBlock currentBlock = null;
		int totalSliceNum = 0;
		int blockSliceNum = 0;

		batch.begin();
		while(startTheta + totalSliceNum*sliceTheta <= endTheta) {
			// need to calculate a new block of level segs?
			if(currentBlock == null || blockSliceNum >= lsAtlas.getTotalWidth()) {
				int blockMult = (int) (((startTheta+startOffsetMap) / sliceTheta + totalSliceNum) / lsAtlas.getTotalWidth());
				currentBlock = new LevelSegBlock(lsAtlas, blockMult);
				blockSliceNum = Math.floorMod((int) ((startTheta+startOffsetMap) / sliceTheta)+totalSliceNum,
						lsAtlas.getTotalWidth());
			}

			if(minDrawSlice == -1 || (int) ((startTheta+startOffsetMap) / sliceTheta)+totalSliceNum >= minDrawSlice) {
				// create and draw the slice
				LevelSegSlice lss = currentBlock.getSliceForTileX(blockSliceNum);
				// calculate an inter-slice offset, because of startOffsetMap
				double offs = startTheta + startOffsetMap - ((int) ((startTheta + startOffsetMap) / sliceTheta)) * sliceTheta;
				double aNuStart = startTheta - offs;
				SpiralLevelSegSlice slss = SpiralLevelSegSlice.createSlice(spiral, lss,
						aNuStart + totalSliceNum*sliceTheta, sliceTheta, maxSliceHeight);
				slss.draw(batch);
			}

			blockSliceNum++;
			totalSliceNum++;
		}
		batch.end();
	}

	public void setSliceTheta(double sliceTheta) {
		this.sliceTheta = sliceTheta;
	}

	public double getSliceTheta() {
		return sliceTheta;
	}

	public void setStartOffsetMap(double startOffsetMap) {
		this.startOffsetMap = startOffsetMap;
	}

	public double getStartOffsetMap() {
		return startOffsetMap;
	}

	public void setMinDrawSlice(int minDrawSlice) {
		this.minDrawSlice = minDrawSlice;
	}
}
