package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Snake {

    //para manejar el movimiento sin fisicas necesito las variables position y velocidad en vectores y tambien speed
    private final Vector2 position;
    private final Vector2 direction;
    private final float speed;
    private final int height;
    private final int width;
    private final Texture snakeTexture;
    private final Array<SnakeBody> snakeBodyParts;
    private final Rectangle snakeHeadBody;

    public Snake(float positionX, float positionY) {

        this.position = new Vector2(positionX, positionY);

        //el vector direction se utilizan para determinar la direccion que ira el player
        this.direction = new Vector2(0, 0);

        //esta sera la velocidad del player con la que se movera
        this.speed = 5;
        this.width = 32;
        this.height = 32;

        this.snakeHeadBody = new Rectangle(positionX, positionY, width, height);

        snakeTexture = new Texture("white.png");

        this.snakeBodyParts = new Array<SnakeBody>();
    }


    private void snakeMovement() {

        //los else if son para evitar que se puedan cumplir mas
        // de 1 una condicion al mismo tiempo, es decir si presiono 2 teclas al mismo tiempo, solo se cumplira una condicion a la vez
        //Aqui debido a que tengo un speed definido simplemente debo alterar la direccion mediante los inputs
        //en cada input simplemente sumare o restare la velocidad dependiendo de la tecla que pulse
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction.x == 0) {

            direction.y = 0;
            direction.x = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction.x == 0) {

            direction.y = 0;
            direction.x = -speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction.y == 0) {

            direction.x = 0;
            direction.y = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction.y == 0) {

            direction.x = 0;
            direction.y = -speed;
        }

        //al final le sumo los valores de mi direccion a mi vector posicion
        position.add(direction);
        snakeHeadBody.x = position.x;
        snakeHeadBody.y = position.y;
    }


    public void update() {

        snakeMovement();

        int bodyPartsCounter = 0;

        for (SnakeBody bodyPart : snakeBodyParts) {

            bodyPartsCounter++;
            bodyPart.update(position.x, position.y, direction.x, direction.y, bodyPartsCounter);
        }
    }


    public void render(SpriteBatch batch) {

        batch.draw(snakeTexture, position.x, position.y, width, height);

        for (SnakeBody body : snakeBodyParts) {

            body.render(batch);
        }
    }

    public Vector2 getPosition() {return position;}

    public Vector2 getDirection() {return direction;}

    public Texture getSnakeTexture() {
        return snakeTexture;
    }

    public Rectangle getSnakeHeadBody() {
        return snakeHeadBody;
    }

    public Array<SnakeBody> getSnakeBodyParts() {
        return snakeBodyParts;
    }
}
