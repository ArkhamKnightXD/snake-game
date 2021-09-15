package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.SnakeGame;

public class MainMenuScreen extends ScreenAdapter {

	private final SnakeGame game = SnakeGame.INSTANCE;

	private final OrthographicCamera camera;

	public MainMenuScreen(OrthographicCamera globalCamera) {

		camera = globalCamera;
	}

	@Override
	public void show() {
		// Prepare your screen here.
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0,0,0,0);

		game.batch.begin();

		game.font.draw(game.batch, "Welcome to Snake Game!!! ", 400, 400);

		game.batch.end();

		if (Gdx.input.isTouched())
			game.setScreen(new GameScreen(camera));
	}

	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}

	@Override
	public void dispose() {
		// Destroy screen's assets here.
	}
}