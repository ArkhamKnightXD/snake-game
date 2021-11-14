package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SnakeBody {

    private final Vector2 position;
    private final Vector2 direction;
    private final float speed;
    private final int height;
    private final int width;
    private final Texture snakeBodyTexture;

    public SnakeBody(float positionX, float positionY, float directionX, float directionY) {

        position = new Vector2(positionX, positionY);
        direction = new Vector2(directionX, directionY);

        this.speed = 5;
        this.width = 32;
        this.height = 32;

        snakeBodyTexture = new Texture("white.png");
    }


    public void update(float headPositionX, float headPositionY, float directionX, float directionY, int bodyPartsCounter){

        if (directionX > 0){

            position.x = headPositionX - (bodyPartsCounter * 32);
            position.y = headPositionY;

            direction.x = directionX;
            direction.y = 0;
        }

        if (directionX < 0){

            position.x = headPositionX + (bodyPartsCounter * 32);
            position.y = headPositionY;

            direction.x = directionX;
            direction.y = 0;
        }

        if (directionY > 0){

            position.x = headPositionX;
            position.y = headPositionY - (bodyPartsCounter * 32);

            direction.x = 0;
            direction.y = directionY;
        }

        if (directionY < 0){

            position.x = headPositionX;
            position.y = headPositionY + (bodyPartsCounter * 32);

            direction.x = 0;
            direction.y = directionY;
        }
    }


    public void render(SpriteBatch batch){

        batch.draw(snakeBodyTexture, position.x, position.y, width, height);
    }


    public Texture getSnakeBodyTexture() { return snakeBodyTexture; }
}
