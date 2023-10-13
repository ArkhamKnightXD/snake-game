package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.SnakeGame;
import knight.arkham.objects.Snake;
import knight.arkham.objects.SnakeBody;

import java.util.Random;

public class GameScreen2 extends ScreenAdapter {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Texture snakeFoodTexture;
    private final Rectangle snakeFoodBody;
    private final Snake snake;
//    private final Wall topWall;
//    private final Wall bottomWall;
//    private final Wall rightWall;
//    private final Wall leftWall;

    public GameScreen2() {

        SnakeGame game = SnakeGame.INSTANCE;
        camera = game.camera;

        batch = new SpriteBatch();

        snakeFoodTexture = new Texture("white.png");
        snakeFoodBody = new Rectangle(400, 400, 32, 32);

        //poner siempre los objetos al final cuando se desee enviar un this, para asi asegurarnos que todos los elementos
        //de nuestra pantalla esten instanciados
        snake = new Snake(500, 400);

//        topWall = new Wall(MID_SCREEN_WIDTH, FULL_SCREEN_HEIGHT, FULL_SCREEN_WIDTH, 16);
//        bottomWall = new Wall(MID_SCREEN_WIDTH, 0, FULL_SCREEN_WIDTH, 16);
//        rightWall = new Wall(FULL_SCREEN_WIDTH, MID_SCREEN_HEIGHT, 16, FULL_SCREEN_HEIGHT);
//        leftWall = new Wall(0, MID_SCREEN_HEIGHT, 16, FULL_SCREEN_HEIGHT);
    }

    private void update() {

        snake.update();

        Rectangle snakeHead = snake.getSnakeHeadBody();

        if (snakeHead.overlaps(snakeFoodBody)) {

            snakeFoodRandomPositionGenerator();

            snake.getSnakeBodyParts().add(new SnakeBody(snake.getPosition().x, snake.getPosition().y,
                    snake.getDirection().x, snake.getDirection().y));
        }

//        if (snakeHead.overlaps(bottomWall.getWallBody()) || snakeHead.overlaps(topWall.getWallBody())
//                || snakeHead.overlaps(rightWall.getWallBody()) || snakeHead.overlaps(leftWall.getWallBody())){
//
//            //lose
//        }


        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {

        //la convencion es que el update se realice primero y luego se renderiza
        update();

        ScreenUtils.clear(0, 0, 0, 0);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

//        topWall.render(batch);
//        leftWall.render(batch);
//        rightWall.render(batch);
//        bottomWall.render(batch);

        snake.render(batch);

        batch.draw(snakeFoodTexture, snakeFoodBody.x, snakeFoodBody.y, snakeFoodBody.width, snakeFoodBody.height);

        batch.end();
    }


    private void snakeFoodRandomPositionGenerator() {

        Random randomPosition = new Random();
        //the max position is the difference between widthScreen or heightScreen and the width and height
        int minPositionX = 10;
        int maxPositionX = 928;

        int minPositionY = 10;
        int maxPositionY = 608;

        snakeFoodBody.x = randomPosition.nextInt(maxPositionX - minPositionX) + minPositionX;
        snakeFoodBody.y = randomPosition.nextInt(maxPositionY - minPositionY) + minPositionY;
    }


    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        snake.getSnakeTexture().dispose();
        snakeFoodTexture.dispose();
//        topWall.getWallTexture().dispose();
    }
}
