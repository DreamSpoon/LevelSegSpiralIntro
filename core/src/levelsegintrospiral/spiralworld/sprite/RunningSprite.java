package levelsegintrospiral.spiralworld.sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/*
 * For some info on mipmapping and sprites:
 *   https://gamedev.stackexchange.com/questions/103687/how-to-use-mipmap-with-textureatlas-and-assetmanager
 */
public class RunningSprite extends Sprite {
	private Animation<TextureRegion> runAnim;

	public RunningSprite(TextureAtlas atlas, String animName, float animSpeed) {
		runAnim = new Animation<TextureRegion>(animSpeed, atlas.findRegions(animName), PlayMode.LOOP);
		TextureRegion region = runAnim.getKeyFrame(0);
		setRegion(region);
		setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());
	}

	public void draw(Batch batch, Vector2 pos, float rot, float scl, float frameTime) {
		setRegion(runAnim.getKeyFrame(frameTime, true));
		setPosition(pos.x, pos.y);
		setRotation(rot);
		setScale(scl);

		super.draw(batch);
	}
}
