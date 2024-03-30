package knight.arkham.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.helpers.AssetsHelper;

public abstract class GameObject {
    protected final Rectangle bounds;
    private final Sound actionSound;
    protected final Texture sprite;

    protected GameObject(Rectangle rectangle, String soundPath, String spritePath) {
        bounds = rectangle;
        actionSound = AssetsHelper.loadSound(soundPath);
        sprite = new Texture(spritePath);
    }

    public void draw(Batch batch) {

        batch.draw(sprite, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

//    public void playActionSound() {actionSound.play();}

    public void dispose() {
        sprite.dispose();
        actionSound.dispose();
    }
}
