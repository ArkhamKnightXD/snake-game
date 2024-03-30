package knight.arkham.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import knight.arkham.SnakeGame;

public class Lwjgl3Launcher {
	public static void main(String[] args) {
		createApplication();
	}

	private static void createApplication() {
		new Lwjgl3Application(new SnakeGame(), getDefaultConfiguration());
	}

	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {

		Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

		configuration.setTitle("Snake");
		configuration.setWindowedMode(750, 750);
		configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

		return configuration;
	}
}