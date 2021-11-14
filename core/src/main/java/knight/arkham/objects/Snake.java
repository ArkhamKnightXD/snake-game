package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Snake {

    private float positionX;
    private float positionY;
    private float directionX;
    private float directionY;
    private final float speed;
    private final int height;
    private final int width;
    private final Texture snakeTexture;
    private final Array<SnakeBody> snakeBodyParts;
    //intento de body mientras no estoy utilizando fisicas
    private final Rectangle snakeHeadFakeBody;

    public Snake(float positionX, float positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        //los elementos velocity se utilizan para determinar la direccion que ira el player
        // si es positivo ira derecha en X y para arriba en Y, sino sera lo contrario
        this.directionX = 0;
        this.directionY = 0;
        //esta sera la velocidad del player con la que se movera
        this.speed = 7;
        this.width = 32;
        this.height = 32;

        this.snakeHeadFakeBody = new Rectangle(positionX, positionY, width, height);

        snakeTexture = new Texture("white.png");

        this.snakeBodyParts = new Array<SnakeBody>();
    }


    private void snakeMovement() {

        //los else if son para evitar que se puedan cumplir mas
        // de 1 una condicion al mismo tiempo, es decir si presiono 2 teclas al mismo tiempo, solo se cumplira una condicion a la vez
        //Aqui debido a que tengo un speed definido simplemente debo alterar la direccion mediante los inputs
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && directionX != -1) {

            directionY = 0;
            directionX = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && directionX != 1) {

            directionY = 0;
            directionX = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && directionY != -1) {

            directionX = 0;
            directionY = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && directionY != 1) {

            directionX = 0;
            directionY = -1;
        }

        snakeHeadFakeBody.x = positionX;
        snakeHeadFakeBody.y = positionY;
    }


    public void update() {

        snakeMovement();

        int bodyPartsCounter = 0;

        for (SnakeBody bodyPart : snakeBodyParts) {

            bodyPartsCounter++;
            bodyPart.update(positionX, positionY, directionX, directionY, bodyPartsCounter);
        }
    }


    public void render(SpriteBatch batch) {

        batch.draw(snakeTexture, positionX, positionY, width, height);

        for (SnakeBody body : snakeBodyParts) {

            body.render(batch);
        }
    }


    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public Texture getSnakeTexture() {
        return snakeTexture;
    }

    public Rectangle getSnakeHeadFakeBody() {
        return snakeHeadFakeBody;
    }

    public Array<SnakeBody> getSnakeBodyParts() {
        return snakeBodyParts;
    }
}
