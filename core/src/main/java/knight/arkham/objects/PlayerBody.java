package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerBody extends GameObject {
    public static int score;
    private final Vector2 velocity;

    public PlayerBody(Rectangle bounds) {
        super(bounds,  "laser.wav", "images/structure.png");
        score = 0;
        velocity = new Vector2(0,0);
    }

    public void update(Vector2 headPosition, Vector2 headVelocity, int bodyPartsCounter) {

        if (headVelocity.x > 0){

            bounds.x = headPosition.x - (bodyPartsCounter * bounds.width);
            bounds.y = headPosition.y;

            velocity.x = headVelocity.x;
            velocity.y = 0;
        }

        if (headVelocity.x < 0){

            bounds.x = headPosition.x + (bodyPartsCounter * bounds.width);
            bounds.y = headPosition.y;

            velocity.x = headVelocity.x;
            velocity.y = 0;
        }

        if (headVelocity.y > 0){

            bounds.x = headPosition.x;
            bounds.y = headPosition.y - (bodyPartsCounter * bounds.height);

            velocity.x = 0;
            velocity.y = headVelocity.y;
        }

        if (headVelocity.y < 0){

            bounds.x = headPosition.x;
            bounds.y = headPosition.y + (bodyPartsCounter * bounds.height);

            velocity.x = 0;
            velocity.y = headVelocity.y;
        }
    }
}
