package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeBody {

    private float positionX;
    private float positionY;
    private final int height;
    private final int width;
    private final Texture snakeBodyTexture;

    public SnakeBody(float positionX, float positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = 32;
        this.height = 32;

        snakeBodyTexture = new Texture("white.png");
    }


    public void update(float headPositionX, float headPositionY, float directionX, float directionY, int bodyPartsCounter){

        if (directionX == 1){

            positionX = headPositionX - (bodyPartsCounter * 32);
            positionY = headPositionY;
        }

        if (directionX == -1){

            positionX = headPositionX + (bodyPartsCounter * 32);
            positionY = headPositionY;
        }

        if (directionY == 1){

            positionX = headPositionX;
            positionY = headPositionY - (bodyPartsCounter * 32);
        }

        if (directionY == -1){

            positionX = headPositionX;
            positionY = headPositionY + (bodyPartsCounter * 32);
        }
    }


    public void render(SpriteBatch batch){

        batch.draw(snakeBodyTexture, positionX, positionY, width, height);
    }


    public Texture getSnakeBodyTexture() { return snakeBodyTexture; }
}
