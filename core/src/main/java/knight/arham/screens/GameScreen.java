package knight.arham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arham.Snake;

public class GameScreen extends ScreenAdapter {

    private final Snake game = Snake.INSTANCE;

    //private final OrthographicCamera camera;
    
    private final Texture snakeTexture;

    private final Rectangle snakeBody;


    public GameScreen(OrthographicCamera globalCamera) {

        //I won't be using the camera for now
       // camera = globalCamera;
        snakeTexture = new Texture("white.png");
        snakeBody = new Rectangle(20,20,32,32);
    }

    @Override
    public void show() {

    }

    private void update(){

        snakeMovement();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);

        game.batch.begin();

        game.batch.draw(snakeTexture,snakeBody.x,snakeBody.y,snakeBody.width,snakeBody.height);

        game.batch.end();

        update();
    }


    private void snakeMovement() {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            snakeBody.x -= 600 * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            snakeBody.x += 600 * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            snakeBody.y += 600 * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            snakeBody.y -= 600 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
