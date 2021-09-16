package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.SnakeGame;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.Snake;
import knight.arkham.objects.Wall;

import java.util.Random;

public class GameScreen extends ScreenAdapter {

    private final SnakeGame game = SnakeGame.INSTANCE;

    private final OrthographicCamera camera;

    private final Texture snakeFoodTexture;
    private final Rectangle snakeFoodBody;

    private final Snake snake;

    private final World world;
    private final Box2DDebugRenderer box2DDebugRenderer;

    private final Wall topWall;
    private final Wall bottomWall;
    private final Wall rightWall;
    private final Wall leftWall;

    private final GameContactListener gameContactListener;


    public GameScreen(OrthographicCamera globalCamera) {

        camera = globalCamera;

        snakeFoodTexture = new Texture("white.png");
        snakeFoodBody = new Rectangle(400,400,32,32);

        world = new World(new Vector2(0,0),false);
        box2DDebugRenderer = new Box2DDebugRenderer();

        //poner siempre los objetos al final cuando se desee enviar un this, para asi asegurarnos que todos los elementos
        //de nuestra pantalla esten instanciados
        snake = new Snake(this, Constants.MID_SCREEN_WIDTH, Constants.MID_SCREEN_HEIGHT);


        topWall = new Wall(Constants.MID_SCREEN_WIDTH, Constants.FULL_SCREEN_HEIGHT, Constants.FULL_SCREEN_WIDTH, 16, this);
        bottomWall = new Wall(Constants.MID_SCREEN_WIDTH, 0, Constants.FULL_SCREEN_WIDTH, 16, this);
        rightWall = new Wall(Constants.FULL_SCREEN_WIDTH, Constants.MID_SCREEN_HEIGHT, 16, Constants.FULL_SCREEN_HEIGHT, this);
        leftWall = new Wall(0, Constants.MID_SCREEN_HEIGHT, 16, Constants.FULL_SCREEN_HEIGHT, this);

        gameContactListener = new GameContactListener(this);

        world.setContactListener(gameContactListener);
    }

    @Override
    public void show() {

    }

    private void update(){

//        Aqui defino que mi world correra a 60 fps
        world.step(1/60f, 6, 2);

        //para que los box2d se visualicen correctamente con el debugrenderer camera.update debe de implementarse
        camera.update();

        snake.update();

//        snakeBody.forEach(Snake::update);

        if(snake.getSnakeFakeBody().overlaps(snakeFoodBody)){

            snakeFoodRandomPositionGenerator();

//            snakeBody.add(new Snake(this, snakeHead.getPositionX(), snakeHead.getPositionY()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){

            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {

        //la convencion es que el update se realice primero y luego se renderiza
        update();

        ScreenUtils.clear(0,0,0,0);

        game.batch.begin();

        topWall.render(game.batch);
        leftWall.render(game.batch);
        rightWall.render(game.batch);
        bottomWall.render(game.batch);

//        snakeBody.forEach(snakeBodyPart -> snakeBodyPart.render(game.batch));

        snake.render(game.batch);

        game.batch.draw(snakeFoodTexture,snakeFoodBody.x,snakeFoodBody.y,snakeFoodBody.width,snakeFoodBody.height);

        game.batch.end();

//        box2DDebugRenderer.render(world, camera.combined.scl(Constants.PIXELS_PER_METER));
    }


    private void snakeFoodRandomPositionGenerator(){

        Random randomPosition = new Random();
        //the max position is the difference between widthScreen or heightScreen and the width and height
        int minPositionX = 10;
        int maxPositionX = 928;

        int minPositionY = 10;
        int maxPositionY = 608;

        snakeFoodBody.x = randomPosition.nextInt(maxPositionX-minPositionX) + minPositionX;
        snakeFoodBody.y = randomPosition.nextInt(maxPositionY-minPositionY) + minPositionY;
    }


    public void gameOverScreen(){

        game.setScreen(new MainMenuScreen(camera));
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        snake.getSnakeTexture().dispose();
        snakeFoodTexture.dispose();
    }


    public World getWorld() { return world; }
}
