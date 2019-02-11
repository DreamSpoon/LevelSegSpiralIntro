package levelsegintrospiral.spiralworld.sprite;

import java.util.Random;

import levelsegintrospiral.tools.ExRandIntList;

public class SpriteBlock {
	private static final double MAX_THETA_OFFSET_FACTOR = 0.5f;
	private static final float MAX_FRAME_OFFSET = 1f;

	private RunningSprite[] randomSprites;
	private double[] randomThetaOffsets;
	private float[] randomAnimFrameOffsets;

	private SpriteSpiral parent;

	public SpriteBlock(SpriteSpiral parent, SpriteAtlas sAtlas, long seed) {
		this.parent = parent;
		createRandomSprites(sAtlas, seed);
	}

	private void createRandomSprites(SpriteAtlas sAtlas, long seed) {
		randomSprites = new RunningSprite[sAtlas.getNumTemplates()];
		randomThetaOffsets = new double[sAtlas.getNumTemplates()];
		randomAnimFrameOffsets = new float[sAtlas.getNumTemplates()];
		ExRandIntList eril = new ExRandIntList(seed, sAtlas.getNumTemplates());
		Random rand = new Random();
		rand.setSeed(seed);
		for(int i=0; i<sAtlas.getNumTemplates(); i++) {
			randomSprites[i] = sAtlas.getSpriteByIndex(eril.getIntAtIndex(i));
			randomThetaOffsets[i] = this.parent.getSpriteTheta() * MAX_THETA_OFFSET_FACTOR * rand.nextDouble();
			randomAnimFrameOffsets[i] = MAX_FRAME_OFFSET * rand.nextFloat();
		}
	}

	public RunningSprite getSpriteForIndex(int index) {
		return randomSprites[index];
	}

	public int size() {
		return randomSprites.length;
	}

	public double getRandThetaOffsetForIndex(int index) {
		return randomThetaOffsets[index];
	}

	public float getRandAnimFrameOffsetForIndex(int index) {
		return randomAnimFrameOffsets[index];
	}
}
