package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.SnakeGame;

public class Snake {

    private int positionX;
    private int positionY;
    private final int height;
    private final int width;
    private final Texture snakeTexture;

    //intento de body mientras no estoy utilizando fisicas
    private final Rectangle snakeBody;

    public Snake() {

        this.positionX = 10;
        this.positionY = 10;
        this.width = 32;
        this.height = 32;

        this.snakeBody = new Rectangle(positionX, positionY, width, height);

        snakeTexture = new Texture("white.png");
    }


    private void snakeMovement() {

        //los else if son para evitar que se puedan cumplir mas
        // de 1 una condicion al mismo tiempo, es decir si presiono 2 teclas al mismo tiempo, solo se cumplira una condicion a la vez
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

            positionX += 300 * Gdx.graphics.getDeltaTime();
            snakeBody.x = positionX;
        }


        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            positionX -= 300 * Gdx.graphics.getDeltaTime();
            snakeBody.x = positionX;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){

            positionY += 300 * Gdx.graphics.getDeltaTime();
            snakeBody.y = positionY;
        }


        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){

            positionY -= 300 * Gdx.graphics.getDeltaTime();
            snakeBody.y = positionY;
        }
    }


    private void screenBoundary() {

        if (positionX <= 0)
            positionX = 0;

        if (positionX >= SnakeGame.INSTANCE.screenWidth - 32)
            positionX = SnakeGame.INSTANCE.screenWidth - 32;

        if (positionY <= 0)
            positionY = 0;

        if (positionY >= SnakeGame.INSTANCE.screenHeight - 32)
            positionY = SnakeGame.INSTANCE.screenHeight - 32;
    }


    public void update(){

        snakeMovement();

        screenBoundary();
    }


    public void render(SpriteBatch batch){

        batch.draw(snakeTexture, positionX, positionY, width, height);
    }


    public Texture getSnakeTexture() { return snakeTexture; }

    public Rectangle getSnakeBody() { return snakeBody; }
}
