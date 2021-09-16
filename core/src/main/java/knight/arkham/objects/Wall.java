package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

public class Wall {

    private final Body body;
    private final float positionX;
    private final float positionY;
    private final int width;
    private final int height;
    private final Texture wallTexture;

    public Wall(float positionX, float positionY, int width, int height, GameScreen gameScreen) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.wallTexture = new Texture("white.png");

        this.body = BodyHelper.createBody(positionX, positionY, width, height, true,
                0, gameScreen.getWorld(), ContactType.WALL);
    }

    public void render(SpriteBatch batch){

        batch.draw(wallTexture, positionX - (width / 2), positionY - (height / 2), width, height);
    }
}
