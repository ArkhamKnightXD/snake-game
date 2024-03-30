package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    private final Vector2 velocity = new Vector2(1,0);
    private final Deque<Vector2> bodyParts = new ArrayDeque<>();
    private boolean shouldAddSegment;
    private final int cellSize;
    private final int cellCount;
    private final Texture sprite =  new Texture("images/structure.png");

    public Snake(int cellSize, int cellCount) {
        this.cellSize = cellSize;
        this.cellCount = cellCount;

        bodyParts.addFirst(new Vector2(6, 9));
        bodyParts.addFirst(new Vector2(5, 9));
        bodyParts.addFirst(new Vector2(4, 9));
    }

    public void update(float deltaTime) {

        if (!shouldAddSegment)
        {
            // we remove the last element (The tail of the snake) and we push the head back and add the new direction as a head.
            bodyParts.pollLast();
            Vector2 sum = bodyParts.getFirst().add(velocity);
            bodyParts.addFirst(sum);
        }
        else
        {
            //it's better to add the new element at the head, cuz this gives a better visual effect.
            // When the element is added we need to stop the snake movement, to complete the visual effect.
            Vector2 sum = bodyParts.getFirst().add(velocity);
            bodyParts.addFirst(sum);

            shouldAddSegment = false;
        }

        int speed = 300;

        if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x == 0) {
            velocity.y = 0;
            velocity.x = speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x == 0) {
            velocity.y = 0;
            velocity.x = -1 * speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.W) && velocity.y == 0) {
            velocity.x = 0;
            velocity.y = speed;
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.S) && velocity.y == 0) {
            velocity.x = 0;
            velocity.y = -1 * speed;
        }

        velocity.scl(deltaTime);
    }

    public boolean isPlayerInsideScreenBounds() {

//        return bounds.x > 1410 || bounds.x < 480 || bounds.y > 950 || bounds.y < 300;

        return  false;
    }

    public void draw(Batch batch) {

        for (Vector2 bodyPart : bodyParts) {

            batch.draw(sprite, bodyPart.x * cellSize, bodyPart.y * cellSize, cellSize, cellSize);
        }
    }
}
