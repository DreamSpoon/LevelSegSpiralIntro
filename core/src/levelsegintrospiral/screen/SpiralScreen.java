package levelsegintrospiral.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import levelsegintrospiral.MyIntroSpiral;
import levelsegintrospiral.spiralworld.SpiralWorld;
import levelsegintrospiral.tools.Info;

public class SpiralScreen implements Screen {
	private MyIntroSpiral game;
	private OrthographicCamera gamecam;
	private Viewport gameport;
	private SpiralWorld sworld;

	public SpiralScreen(MyIntroSpiral game) {
		this.game = game;

		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Info.V_WIDTH, Info.V_HEIGHT, gamecam);
		gamecam.position.set(0f, 0f, 0f);
		gamecam.update();

		sworld = new SpiralWorld(gamecam);

		// start the intro music
		Music music = game.manager.get(Info.INTRO_MUSIC, Music.class);
		music.play();
	}

	// to prevent problems when render frames are dropped
	private static final float MAX_FRAME_DELTA = 1f/50f;
	@Override
	public void render(float delta) {
		if(delta > MAX_FRAME_DELTA)
			delta = MAX_FRAME_DELTA; 
				
		sworld.update(delta);

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.sr.setProjectionMatrix(gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);
		sworld.draw(game.batch, game.sr);
	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
}
