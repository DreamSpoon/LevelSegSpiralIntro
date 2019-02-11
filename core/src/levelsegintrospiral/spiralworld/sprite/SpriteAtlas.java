package levelsegintrospiral.spiralworld.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import levelsegintrospiral.tools.SpriteInfo;

public class SpriteAtlas implements Disposable {
	private RunningSprite[] spriteTemplates;
	private TextureAtlas atlas;

	public SpriteAtlas(String taMainFilename) {
		atlas = new TextureAtlas(SpriteInfo.TEXATLAS_FILENAME);
		createTemplates();
	}

	private void createTemplates() {
		spriteTemplates = new RunningSprite[SpriteInfo.TEMPLATES.length];
		for(int i=0; i<SpriteInfo.TEMPLATES.length; i++) {
			spriteTemplates[i] = new RunningSprite(atlas, SpriteInfo.TEMPLATES[i].animName,
					SpriteInfo.TEMPLATES[i].animSpeed);
		}
	}

	public int getNumTemplates() {
		return spriteTemplates.length;
	}

	public RunningSprite getSpriteByIndex(int index) {
		return spriteTemplates[index];
	}

	@Override
	public void dispose() {
		atlas.dispose();
	}
}
