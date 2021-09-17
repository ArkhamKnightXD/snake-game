package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

public class SnakeBody {

    private float positionX;
    private float positionY;
    private final int height;
    private final int width;
    private final Texture snakeBodyTexture;
    private final Rectangle fakeBody;
    private final Body snakeHeadBody;

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public SnakeBody(GameScreen screen, float positionX, float positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = 32;
        this.height = 32;

        snakeBodyTexture = new Texture("white.png");
        fakeBody = new Rectangle(positionX, positionY, width, height);

        this.snakeHeadBody = BodyHelper.createBody(positionX, positionY, width, height, false,
                0, screen.getWorld(), ContactType.SNAKE);
    }


    public void update(){

    }

    public Body getSnakeHeadBody() {
        return snakeHeadBody;
    }

    public void render(SpriteBatch batch){

        batch.draw(snakeBodyTexture, positionX, positionY, width, height);
    }
}
