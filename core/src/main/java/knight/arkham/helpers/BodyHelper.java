package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;

public class BodyHelper {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, float density, World world, ContactType type){

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = !isStatic ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;

        bodyDef.position.set(x / Constants.PIXELS_PER_METER, y /Constants.PIXELS_PER_METER);

        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        //dividimos entre 2 para colocarlo en el medio y tambien debemos de dividir por nuestro pixel
        shape.setAsBox(width / 2 / Constants.PIXELS_PER_METER, height /2 /Constants.PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;

        body.createFixture(fixtureDef).setUserData(type);

        shape.dispose();

        return body;
    }
}
