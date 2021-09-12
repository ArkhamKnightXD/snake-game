package knight.arham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arham.screens.MainMenuScreen;

public class Snake extends Game {

	public static Snake INSTANCE;

	public SpriteBatch batch;
	public BitmapFont font;

	private int screenWidth;
	private int screenHeight;

	public Snake() {

		INSTANCE = this;
	}

	@Override
	public void create() {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		font = new BitmapFont();

		OrthographicCamera globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, screenWidth, screenHeight);

		setScreen(new MainMenuScreen(globalCamera));
	}
}