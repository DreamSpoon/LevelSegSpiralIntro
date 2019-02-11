package levelsegintrospiral.spiralworld.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;

public class LevelSegLoader {
	public static LevelSeg loadMap(String mapFilename) {
		LevelSeg ret = new LevelSeg();
		Parameters tileParams = new Parameters();
		tileParams.generateMipMaps = true;
		tileParams.textureMinFilter = Texture.TextureFilter.MipMapLinearLinear;
		tileParams.textureMagFilter = Texture.TextureFilter.Nearest;
		TiledMap tiledMap = (new TmxMapLoader()).load(mapFilename, tileParams);
		ret.setMap(tiledMap);
		return ret;
	}
}
