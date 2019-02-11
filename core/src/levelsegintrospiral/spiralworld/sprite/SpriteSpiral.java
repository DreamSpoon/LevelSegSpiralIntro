package levelsegintrospiral.spiralworld.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import levelsegintrospiral.spiralworld.Arc;
import levelsegintrospiral.spiralworld.Spiral;
import levelsegintrospiral.tools.SpriteInfo;

public class SpriteSpiral {
	// in pixels
	private static final float SPRITE_UP_OFFSET = 32f;

	private Spiral spiral;
	private SpriteAtlas sAtlas;
	// spriteTheta is the angular distance between sprites.
	private double spriteTheta;
	private double startOffsetSprite;

	private double moveFactorSprite;

	public SpriteSpiral(Spiral spiral) {
		this.spiral = spiral;
		sAtlas = new SpriteAtlas(SpriteInfo.TEXATLAS_FILENAME);
		spriteTheta = 0f;
		startOffsetSprite = 0f;
		moveFactorSprite = 0f;
	}

	public void drawSpritesInView(SpriteBatch batch, Vector2 viewPos, double viewRadius, float globalTimer) {
		Arc[] test = spiral.getArcsInView(viewPos, viewRadius);
		for(Arc a : test)
			drawSprites(batch, a.start, a.end, globalTimer);
	}

	private void drawSprites(SpriteBatch batch, double startTheta, double endTheta, float globalTimer) {
		SpriteBlock currentBlock = null;
		int totalSpriteNum = 0;
		int blockSpriteNum = 0;

		// get the offset based on distance travelled over time from starting point
		double totalDistMoved = globalTimer*moveFactorSprite - startOffsetSprite;
		// the sprites move to the right, therefore the stationary observer appears to move left
		int firstSpriteIndex = (int) Math.ceil((startTheta - totalDistMoved) / spriteTheta);
		double firstSpriteTheta = firstSpriteIndex * spriteTheta + totalDistMoved;
		batch.begin();
		while(startTheta + totalSpriteNum*spriteTheta <= endTheta) {
			// need to calculate a new block of sprites?
			if(currentBlock == null || blockSpriteNum >= sAtlas.getNumTemplates()) {
				int blockMult = (firstSpriteIndex+totalSpriteNum) / sAtlas.getNumTemplates();
				currentBlock = new SpriteBlock(this, sAtlas, blockMult);
				blockSpriteNum = Math.floorMod(firstSpriteIndex+totalSpriteNum,
						sAtlas.getNumTemplates());
			}

			// draw the sprite
			RunningSprite sprite = currentBlock.getSpriteForIndex(blockSpriteNum);
			drawSprite(sprite, batch,
					firstSpriteTheta+totalSpriteNum*spriteTheta + currentBlock.getRandThetaOffsetForIndex(blockSpriteNum),
					globalTimer + currentBlock.getRandAnimFrameOffsetForIndex(blockSpriteNum));

			blockSpriteNum++;
			totalSpriteNum++;
		}
		batch.end();
	}

	private void drawSprite(RunningSprite sprite, SpriteBatch batch, double theta, float globalTimer) {
		double scale = spiral.getScaleForTheta(theta);
		Vector2 up = spiral.getUpVecForTheta(theta);
		Vector2 pos = spiral.getPositionForTheta(theta).add(up.cpy().scl((float) (scale * SPRITE_UP_OFFSET)));
		sprite.draw(batch, pos, up.angle()-90f, (float) scale, globalTimer);
	}

	public void setSpriteTheta(double spriteTheta) {
		this.spriteTheta = spriteTheta;
	}

	public double getSpriteTheta() {
		return spriteTheta;
	}

	public void setStartOffsetSprite(double startOffsetSprite) {
		this.startOffsetSprite = startOffsetSprite;
	}

	public double getStartOffsetSprite() {
		return startOffsetSprite;
	}

	public void setMoveFactorSprite(double moveFactorSprite) {
		this.moveFactorSprite = moveFactorSprite;
	}

	public double getMoveFactorSprite() {
		return moveFactorSprite;
	}
}
