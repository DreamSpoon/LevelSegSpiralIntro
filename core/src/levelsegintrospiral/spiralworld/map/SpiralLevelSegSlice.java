package levelsegintrospiral.spiralworld.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import levelsegintrospiral.spiralworld.Spiral;
import levelsegintrospiral.tools.MapInfo;

/*
 * One-tile wide "slice" of a map.
 */
public class SpiralLevelSegSlice {
	private Vector2 botLeft;
	private Vector2 botRight;
	private Vector2 upLeft;
	private Vector2 upRight;
	private LevelSegSlice lss;
	private int maxSliceHeight;

	public SpiralLevelSegSlice(LevelSegSlice lss, Vector2 botLeft, Vector2 botRight, Vector2 upLeft, Vector2 upRight,
			int maxSliceHeight) {
		this.lss = lss;
		this.botLeft = botLeft;
		this.botRight = botRight;
		this.upLeft = upLeft;
		this.upRight = upRight;
		this.maxSliceHeight = maxSliceHeight;
	}


	public void draw(SpriteBatch batch) {
		int h = lss.getLevelSeg().getHeight();
		if(h > maxSliceHeight)
			h = maxSliceHeight;
		Array<TiledMapTileLayer> ls = lss.getLevelSeg().getMap().getLayers().getByType(TiledMapTileLayer.class);
		for(TiledMapTileLayer layer : ls) {
			// draw each tile, distorted to fit it's spot in the spiral
			for(int i=0; i<h; i++) {
				// skip empty cells/tiles
				if(layer.getCell(lss.offsetX,  i) == null || layer.getCell(lss.offsetX,  i).getTile() == null)
					continue;
				// draw the tile
				TextureRegion reg = layer.getCell(lss.offsetX,  i).getTile().getTextureRegion();
				float[] vertices = createQuadVerts(i, reg, Color.WHITE);
				batch.draw(reg.getTexture(), vertices, 0, vertices.length);
			}
		}
	}

	private float[] createQuadVerts(int tileY, TextureRegion region, Color color) {
		float c = color.toFloatBits();
		float u = region.getU();
		float v = region.getV();
		float u2 = region.getU2();
		float v2 = region.getV2();
		Vector2 quadBotLeft = botLeft.cpy().add(upLeft.cpy().scl((float) tileY * MapInfo.TILE_HEIGHT));
		Vector2 quadBotRight = botRight.cpy().add(upRight.cpy().scl((float) tileY * MapInfo.TILE_HEIGHT));
		Vector2 quadTopLeft = quadBotLeft.cpy().add(upLeft.cpy().scl(MapInfo.TILE_HEIGHT));
		Vector2 quadTopRight = quadBotRight.cpy().add(upRight.cpy().scl(MapInfo.TILE_HEIGHT));

		return new float[] {
				quadBotLeft.x,  quadBotLeft.y,  c, u,  v2,
				quadBotRight.x, quadBotRight.y, c, u2, v2,
				quadTopRight.x, quadTopRight.y, c, u2, v,
				quadTopLeft.x,  quadTopLeft.y,  c, u,  v
			};
	}

	public Vector2 getBotLeft() {
		return botLeft;
	}

	public Vector2 getBotRight() {
		return botRight;
	}

	public static SpiralLevelSegSlice createSlice(Spiral spiral, LevelSegSlice lss, double startTheta,
			double sliceTheta, int maxSliceHeight) {
		Vector2 posLeft = new Vector2();
		Vector2 upLeft = new Vector2();
		Vector2 posRight = new Vector2();
		Vector2 upRight = new Vector2();

		posLeft = spiral.getPositionForTheta(startTheta);
		upLeft = spiral.getPositionForTheta(startTheta + Math.PI*2f);
		upLeft.sub(posLeft).scl(1f / (MapInfo.TILE_HEIGHT * maxSliceHeight));

		posRight = spiral.getPositionForTheta(startTheta + sliceTheta);
		upRight = spiral.getPositionForTheta(startTheta + sliceTheta + Math.PI*2f);
		upRight.sub(posRight).scl(1f / (MapInfo.TILE_HEIGHT * maxSliceHeight));

		return new SpiralLevelSegSlice(lss, posLeft, posRight, upLeft, upRight, maxSliceHeight);
	}
}
