package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.screens.GameScreen;

public class SnakeGame extends Game {

	public static SnakeGame INSTANCE;

	public SpriteBatch batch;
	public BitmapFont font;

	private int screenWidth;
	private int screenHeight;

	public SnakeGame() {

		INSTANCE = this;
	}

	@Override
	public void create() {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		font = new BitmapFont();

		setScreen(new GameScreen());
	}


	public int getScreenWidth() { return screenWidth; }

	public int getScreenHeight() { return screenHeight; }
}