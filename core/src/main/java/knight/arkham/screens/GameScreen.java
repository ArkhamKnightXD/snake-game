package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.SnakeGame;
import knight.arkham.objects.Snake;
import knight.arkham.objects.Food;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private final SnakeGame game;
    public SpriteBatch batch;
    private final Snake snake;
    private final Food food;
    public static boolean isGamePaused;

    public GameScreen() {

        game = SnakeGame.INSTANCE;

        batch = new SpriteBatch();

        snake = new Snake(game.cellSize, game.cellCount);

        food = new Food(new Rectangle(800, 800, 32, 32));

        isGamePaused = false;
    }

    private void update(float deltaTime) {

        snake.update(deltaTime);
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

        if (snake.isPlayerInsideScreenBounds())
            game.setScreen(new GameScreen());

        else if (!isGamePaused) {

            update(deltaTime);
            draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isGamePaused = !isGamePaused;
    }

    private void draw() {

        batch.begin();

        snake.draw(batch);

//        food.draw(batch);

        batch.end();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        food.dispose();
    }
}
