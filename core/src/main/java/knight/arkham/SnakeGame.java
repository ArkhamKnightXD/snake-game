package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import knight.arkham.screens.GameScreen;

public class SnakeGame extends Game {
	public static SnakeGame INSTANCE;
	public OrthographicCamera camera;
	public int screenWidth;
	public int screenHeight;
	public int cellSize = 30;
	public int cellCount = 25;
	public AssetDescriptor<Skin> uiSkin;

	public SnakeGame() {
		INSTANCE = this;
	}

	@Override
	public void create() {

		camera = new OrthographicCamera();

		screenWidth = cellSize * cellCount;
		screenHeight = cellSize * cellCount;

		uiSkin = new AssetDescriptor<>("ui/uiskin.json", Skin.class, new SkinLoader.SkinParameter("ui/uiskin.atlas"));

		setScreen(new GameScreen());
	}
}