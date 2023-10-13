package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.SnakeGame;
import knight.arkham.objects.Player;
import knight.arkham.objects.Food;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private final SnakeGame game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final Player player;
    private final Food food;
    public static boolean isGamePaused;

    public GameScreen() {

        game = SnakeGame.INSTANCE;

        camera = game.camera;

        batch = new SpriteBatch();

        player = new Player(new Rectangle(950, 600, 32, 32));

        food = new Food(new Rectangle(800, 800, 32, 32));

        isGamePaused = false;
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update(float deltaTime) {

        player.update(deltaTime);

        if (player.getBounds().overlaps(food.getBounds())) {

            createFoodAtRandomPosition();
        }
    }

    private void createFoodAtRandomPosition() {

        Random randomPosition = new Random();

        int minPositionX = 480;
        int maxPositionX = 1410;

        int minPositionY = 320;
        int maxPositionY = 930;

        food.getBounds().x = randomPosition.nextInt(maxPositionX - minPositionX) + minPositionX;
        food.getBounds().y = randomPosition.nextInt(maxPositionY - minPositionY) + minPositionY;
    }

    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 0);

        if (!isGamePaused) {

            update(deltaTime);
            draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isGamePaused = !isGamePaused;
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        player.draw(batch);

        food.draw(batch);

        batch.end();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        batch.dispose();
        food.dispose();
    }
}
