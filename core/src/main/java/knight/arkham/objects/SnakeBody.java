package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SnakeBody {

    private float positionX;
    private float positionY;
    private final int height;
    private final int width;
    private final Texture snakeBodyTexture;
    private final Rectangle fakeBody;


    public SnakeBody(float positionX, float positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = 32;
        this.height = 32;

        snakeBodyTexture = new Texture("white.png");
        fakeBody = new Rectangle(positionX, positionY, width, height);
    }


    public void update(){

    }


    public void render(SpriteBatch batch){

        batch.draw(snakeBodyTexture, positionX, positionY, width, height);
    }
}
