package levelsegintrospiral.spiralworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import levelsegintrospiral.spiralworld.map.LevelSegSpiral;
import levelsegintrospiral.spiralworld.sprite.SpriteSpiral;
import levelsegintrospiral.tools.MapInfo;

public class SpiralWorld {
	public static final double SPIRAL_SCALE = 1000f;
	public static final double SPIRAL_EXP = 0.002f;

	public static final double VIEW_RADIUS = 500f;

	private OrthographicCamera gamecam;
	private Spiral mainSpiral;
	private LevelSegSpiral sliceSpiral;
	private SpriteSpiral spriteSpiral;
	private float globalTimer;

	private ScriptRunner scriptRunner;

	public SpiralWorld(OrthographicCamera gamecam) {
		this.gamecam = gamecam;
		mainSpiral = new Spiral(true, SPIRAL_SCALE, SPIRAL_EXP);
		sliceSpiral = new LevelSegSpiral(mainSpiral, MapInfo.FILENAMES, MapInfo.MAX_SLICE_HEIGHT_MAIN);
		spriteSpiral = new SpriteSpiral(mainSpiral);
		globalTimer = 0f;
		scriptRunner = new ScriptRunner(gamecam, mainSpiral, sliceSpiral, spriteSpiral);
	}

	public void update(float delta) {
		scriptRunner.update(delta);
		globalTimer += delta;
	}

	public void draw(SpriteBatch batch, ShapeRenderer sr) {
		Vector2 pos = new Vector2(gamecam.position.x, gamecam.position.y);
		double viewRadius = gamecam.zoom * VIEW_RADIUS;
		sliceSpiral.drawSlicesInView(batch, pos, viewRadius);
		spriteSpiral.drawSpritesInView(batch, pos, viewRadius, globalTimer);
	}
}
