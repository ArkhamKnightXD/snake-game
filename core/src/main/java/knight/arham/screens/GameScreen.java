package knight.arham.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arham.Snake;

public class GameScreen extends ScreenAdapter {

    private final Snake game = Snake.INSTANCE;

    private final OrthographicCamera camera;


    public GameScreen(OrthographicCamera globalCamera) {

        camera = globalCamera;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);

        game.batch.begin();

        game.font.draw(game.batch, "Game Screen!!! ", 400, 400);

        game.batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
