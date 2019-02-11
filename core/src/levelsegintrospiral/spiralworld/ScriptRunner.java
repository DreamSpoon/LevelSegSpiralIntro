package levelsegintrospiral.spiralworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import levelsegintrospiral.spiralworld.map.LevelSegSpiral;
import levelsegintrospiral.spiralworld.sprite.SpriteSpiral;

public class ScriptRunner {
	public static final double START_OFFSET_CAMERA = Math.PI*32f;
	private static final double START_ZOOM_FACTOR = 0.12f;

	// theta in radians
	public static final double SLICE_THETA = 0.00131869321f;

	public static final double START_OFFSET_MAP = SLICE_THETA * 579f;

	public static final double SPRITE_THETA = 1f * Math.PI/180f;
	public static final double START_OFFSET_SPRITE = 0f;
	// the rotation speed of things around the spiral, to create the illusion of movement
	public static final double MOVE_FACTOR_SPRITE = 1f/128f;

	private OrthographicCamera gamecam;
	private Spiral mainSpiral;
	private LevelSegSpiral sliceSpiral;
	private SpriteSpiral spriteSpiral;
	private double cameraMoveSpeed;
	private float stateTimer;

	private enum ScriptState {
			SCENE1(5.2f), SCENE2(0f), SCENE3(2f), SCENE4(3.0f), SCENE5(5.45f), SCENE6(1.5f), SCENE7(12f);
			private double t;
			ScriptState(double t) {
				this.t = t;
			}
			public double getTime() {
				return t;
			};
		}
	private ScriptState curScriptState;
	private double curCameraOffset;
	private double curZoomFactor;

	public ScriptRunner(OrthographicCamera gamecam, Spiral mainSpiral, LevelSegSpiral sliceSpiral, SpriteSpiral spriteSpiral) {
		this.gamecam = gamecam;
		this.mainSpiral = mainSpiral;
		this.sliceSpiral = sliceSpiral;
		this.spriteSpiral = spriteSpiral;

		stateTimer = 0f;
		curScriptState = ScriptState.SCENE1;

		sliceSpiral.setSliceTheta(SLICE_THETA);
		sliceSpiral.setStartOffsetMap(START_OFFSET_MAP);
		spriteSpiral.setSpriteTheta(SPRITE_THETA);
		spriteSpiral.setStartOffsetSprite(START_OFFSET_SPRITE);
		spriteSpiral.setMoveFactorSprite(MOVE_FACTOR_SPRITE);
		setExp(0.001f);

		cameraMoveSpeed = 0f;
		curCameraOffset = START_OFFSET_CAMERA;
		curZoomFactor = START_ZOOM_FACTOR;
	}

	public void update(float delta) {
		ScriptState nextScriptState = getNextState();
		stateTimer = nextScriptState == curScriptState ? stateTimer : 0f;
		switch(nextScriptState) {
			case SCENE1:
				break;
			case SCENE2:
				cameraMoveSpeed = -1f/512f;
			case SCENE3:
				curZoomFactor = LerpFunc.lerp(new LinearLerpFunc(), stateTimer, START_ZOOM_FACTOR, 0.198f, 0,
						ScriptState.SCENE3.getTime());
				break;
			case SCENE4:
				break;
			case SCENE5:
				curZoomFactor = LerpFunc.lerp(new LinearLerpFunc(), stateTimer, 0.198f, 1.0f, 0,
						ScriptState.SCENE5.getTime());
				break;
			case SCENE6:
				break;
			case SCENE7:
				setExp(mainSpiral.getExp() + stateTimer*stateTimer / 500000f);
				break;
			default:
				break;
		}

		curCameraOffset += delta*cameraMoveSpeed;
		updateCamera(curCameraOffset, curZoomFactor);

		stateTimer += delta;
		curScriptState = nextScriptState;
	}

	private ScriptState getNextState() {
		// is current script state finished?
		if(stateTimer > curScriptState.getTime()) {
			// return next script state, or final script state if the current script state is the final state 
			if(curScriptState.ordinal()+1 >= ScriptState.values().length)
				return ScriptState.values()[ScriptState.values().length-1];
			else
				return ScriptState.values()[curScriptState.ordinal()+1];
		}
		// current script state is not finished
		else
			return curScriptState;
	}

	private void updateCamera(double theta, double zoomFactor) {
		Vector2 pos = mainSpiral.getPositionForTheta(theta).add(
				mainSpiral.getPositionForTheta(theta+Math.PI*2f)).scl(0.5f);
		gamecam.setToOrtho(false);
		gamecam.rotate(-mainSpiral.getUpVecForTheta(theta).angle()+90f);
		gamecam.position.set(pos.x, pos.y, 0f);
		gamecam.zoom = (float) (mainSpiral.getScaleForTheta(theta) * zoomFactor);
		gamecam.update();
	}

	/*
	 * Update the main spiral exponent value, and set other values that are related visually
	 * e.g. When the exponent changes, then the distance between each spiral layer changes -
	 * so fix the level seg slice theta - which means the map start offset needs to be updated - 
	 * then the sprites need updating...etc.
	 */
	private void setExp(double exp) {
		double factor = exp / 0.002f;
		double whatevs = factor - 1f;

		mainSpiral.setExp(SpiralWorld.SPIRAL_EXP*factor);

		sliceSpiral.setSliceTheta(SLICE_THETA*factor);
		sliceSpiral.setStartOffsetMap(START_OFFSET_MAP + whatevs * (100.531f+START_OFFSET_MAP));

		spriteSpiral.setSpriteTheta(SPRITE_THETA*factor);
		spriteSpiral.setStartOffsetSprite(START_OFFSET_SPRITE+ whatevs * (100.531f+START_OFFSET_SPRITE));
		spriteSpiral.setMoveFactorSprite(MOVE_FACTOR_SPRITE*factor);
	}
}
