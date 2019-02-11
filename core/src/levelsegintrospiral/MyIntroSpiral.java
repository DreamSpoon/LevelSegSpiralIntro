package levelsegintrospiral;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import levelsegintrospiral.screen.ThumbnailScreen;
import levelsegintrospiral.tools.Info;

/*
 * With help from:
 *   https://gamedev.stackexchange.com/questions/108476/how-to-draw-a-polygon-with-picture-in-libgdx
 *   https://gist.github.com/mattdesl/4255476
 *   https://gist.github.com/Aashijit/f011766a62357c8dec7e
 */
public class MyIntroSpiral extends Game {
	public AssetManager manager;
	public SpriteBatch batch;
	public ShapeRenderer sr;

	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load(Info.INTRO_MUSIC, Music.class);
		manager.finishLoading();

		sr = new ShapeRenderer();
		setScreen(new ThumbnailScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		sr.dispose();
		batch.dispose();
		manager.dispose();
	}
}
