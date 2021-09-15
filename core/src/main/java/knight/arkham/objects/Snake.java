package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

public class Snake {

    private Body snakeBody;
    private float positionX;
    private float positionY;
    private float directionX;
    private float directionY;
    private float speed;
    private final int height;
    private final int width;
    private final Texture snakeTexture;
    private GameScreen localGameScreen;

    //intento de body mientras no estoy utilizando fisicas
    private final Rectangle snakeFakeBody;

    public Snake(GameScreen gameScreen) {

        localGameScreen = gameScreen;

        this.positionX = Constants.MID_SCREEN_WIDTH;
        this.positionY = Constants.MID_SCREEN_HEIGHT;
        //los elementos velocity se utilizan para determinar la direccion que ira el player
        // si es positivo ira derecha en X y para arriba en Y, sino sera lo contrario
        this.directionX = 1;
        this.directionY = 0;
        //esta sera la velocidad del player con la que se movera
        this.speed = 7;
        this.width = 32;
        this.height = 32;

        this.snakeFakeBody = new Rectangle(positionX, positionY, width, height);

        snakeTexture = new Texture("white.png");

        this.snakeBody = BodyHelper.createBody(positionX, positionY, width, height, false,
                0,  localGameScreen.getWorld(), ContactType.SNAKE);
    }


    private void snakeMovement() {

        //los else if son para evitar que se puedan cumplir mas
        // de 1 una condicion al mismo tiempo, es decir si presiono 2 teclas al mismo tiempo, solo se cumplira una condicion a la vez
        //Aqui debido a que tengo un speed definido simplemente debo alterar la direccion mediante los inputs
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && directionX != -1){

            directionY = 0;
            directionX = 1;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && directionX != 1){

            directionY = 0;
            directionX = -1;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP) && directionY != -1){

            directionX = 0;
            directionY = 1;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && directionY != 1){

            directionX = 0;
            directionY = -1;
        }

        positionX = snakeBody.getPosition().x * Constants.PIXELS_PER_METER - (width / 2);
        positionY = snakeBody.getPosition().y * Constants.PIXELS_PER_METER - (height / 2);

        snakeFakeBody.x = positionX;
        snakeFakeBody.y = positionY;

        //aqui multiplico speed * velocity para indicar la velocidad y direccion que tendra mi player
        this.snakeBody.setLinearVelocity(directionX * speed, directionY * speed);
    }


    private void screenBoundary() {

        if (positionX <= 0)
            positionX = 0;

        if (positionX >= Constants.FULL_SCREEN_WIDTH - 32)
            positionX = Constants.FULL_SCREEN_WIDTH - 32;

        if (positionY <= 0)
            positionY = 0;

        if (positionY >= Constants.FULL_SCREEN_HEIGHT - 32)
            positionY = Constants.FULL_SCREEN_HEIGHT - 32;
    }


    public void update(){

        snakeMovement();

        screenBoundary();
    }


    public void render(SpriteBatch batch){

        batch.draw(snakeTexture, positionX, positionY, width, height);
    }


    public Texture getSnakeTexture() { return snakeTexture; }

    public Rectangle getSnakeFakeBody() { return snakeFakeBody; }
}
