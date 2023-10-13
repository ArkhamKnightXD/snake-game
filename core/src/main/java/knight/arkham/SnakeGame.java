package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.screens.GameScreen;

public class SnakeGame extends Game {
	public static SnakeGame INSTANCE;
	public OrthographicCamera camera;
	public Viewport viewport;
	public int screenWidth;
	public int screenHeight;

	public SnakeGame() {
		INSTANCE = this;
	}

	@Override
	public void create() {

		camera = new OrthographicCamera();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		viewport = new FitViewport(screenWidth, screenHeight, camera);

		camera.position.set(screenWidth, screenHeight, 0);

		setScreen(new GameScreen());
	}
}