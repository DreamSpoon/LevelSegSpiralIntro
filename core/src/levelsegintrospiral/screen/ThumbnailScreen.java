package levelsegintrospiral.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import levelsegintrospiral.MyIntroSpiral;
import levelsegintrospiral.spiralworld.Spiral;
import levelsegintrospiral.spiralworld.SpiralWorld;
import levelsegintrospiral.spiralworld.map.LevelSegSpiral;
import levelsegintrospiral.tools.Info;
import levelsegintrospiral.tools.MapInfo;

public class ThumbnailScreen implements Screen {
	public static final double SPIRAL_SCALE = 1000f;
	public static final double SPIRAL_EXP = 0.03f;

	public static final double START_OFFSET_CAMERA = Math.PI*30f;
	private static final double START_ZOOM_FACTOR = 1.1f;

	public static final double SLICE_THETA = 0.00109995f;
	public static final double START_OFFSET_MAP = 0f;	

	private MyIntroSpiral game;
	private OrthographicCamera gamecam;
	private Viewport gameport;

	private Spiral mainSpiral;
	private LevelSegSpiral sliceSpiral;

	public ThumbnailScreen(MyIntroSpiral game) {
		this.game = game;

		gamecam = new OrthographicCamera();
		gameport = new FitViewport(Info.V_WIDTH, Info.V_HEIGHT, gamecam);
		gamecam.position.set(0f, 0f, 0f);
		gamecam.update();

		mainSpiral = new Spiral(false, SPIRAL_SCALE, SPIRAL_EXP);

		sliceSpiral = new LevelSegSpiral(mainSpiral, MapInfo.THUMB_FILENAMES, MapInfo.MAX_SLICE_HEIGHT_CHARS);
		sliceSpiral.setSliceTheta(SLICE_THETA);

		setExp(SPIRAL_EXP);

		Vector2 pos = mainSpiral.getPositionForTheta(START_OFFSET_CAMERA).add(
				mainSpiral.getPositionForTheta(START_OFFSET_CAMERA+Math.PI*2f)).scl(0.5f);
		gamecam.setToOrtho(false);
		gamecam.rotate(-mainSpiral.getUpVecForTheta(START_OFFSET_CAMERA).angle()+0f);
		gamecam.position.set(pos.x, pos.y, 0f);
		gamecam.zoom = (float) (mainSpiral.getScaleForTheta(START_OFFSET_CAMERA) * START_ZOOM_FACTOR);
		gamecam.update();
	}

	private void setExp(double exp) {
		double factor = exp / 0.002f;
		double whatevs = factor - 1f;

		mainSpiral.setExp(SpiralWorld.SPIRAL_EXP*factor);

		sliceSpiral.setSliceTheta(SLICE_THETA*factor);
		sliceSpiral.setStartOffsetMap(START_OFFSET_MAP + whatevs * (100.531f+START_OFFSET_MAP));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.sr.setProjectionMatrix(gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);

		// draw stuff
		drawThumbnailSpiral(game.batch);

		// switch to the next screen when the user clicks
		if(Gdx.input.isTouched()) {
			game.setScreen(new SpiralScreen(game));
			dispose();
		}
	}

	private void drawThumbnailSpiral(SpriteBatch batch) {
		Vector2 pos = new Vector2(gamecam.position.x, gamecam.position.y);
		double viewRadius = gamecam.zoom * SpiralWorld.VIEW_RADIUS;
		sliceSpiral.drawSlicesInView(batch, pos, viewRadius);
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
