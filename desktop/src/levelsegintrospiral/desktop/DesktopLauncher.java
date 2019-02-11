package levelsegintrospiral.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import levelsegintrospiral.MyIntroSpiral;
import levelsegintrospiral.tools.Info;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.x = 32;
		config.y = 32;
		config.width = Info.V_WIDTH;
		config.height = Info.V_HEIGHT;
		new LwjglApplication(new MyIntroSpiral(), config);
	}
}
