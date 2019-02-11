package levelsegintrospiral.spiralworld.map;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class LevelSeg {
	private static final String KEY_WIDTH = "width";
	private static final String KEY_HEIGHT = "height";

	private TiledMap tiledMap;
	private int width;
	private int height;

	public LevelSeg() {
		tiledMap = null;
		width = height = 0;
	}

	public void setMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
		if(tiledMap == null)
			return;
		width = (Integer) tiledMap.getProperties().get(KEY_WIDTH, 0, Integer.class);
		height = (Integer) tiledMap.getProperties().get(KEY_HEIGHT, 0, Integer.class);
	}

	public TiledMap getMap() {
		return tiledMap;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
