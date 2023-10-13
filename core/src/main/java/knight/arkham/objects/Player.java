package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
    public static int score;
    private final int speed;
    private final Vector2 velocity;

    public Player(Rectangle bounds) {
        super(bounds,  "laser.wav", "images/structure.png");
        score = 0;
        speed = 300;
        velocity = new Vector2(0,0);
    }

    public void update(float deltaTime) {

        if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x == 0) {
            velocity.y = 0;
            velocity.x = speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x == 0) {
            velocity.y = 0;
            velocity.x = -speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.W) && velocity.y == 0) {
            velocity.x = 0;
            velocity.y = speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.S) && velocity.y == 0) {
            velocity.x = 0;
            velocity.y = -speed;
        }

        actualBounds.x += velocity.x * deltaTime;
        actualBounds.y += velocity.y * deltaTime;

        if (actualBounds.x > 1400)
            velocity.set(0, 0);
    }
}
