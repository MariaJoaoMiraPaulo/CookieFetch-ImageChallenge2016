package Logic;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Cookie {

    public enum State {
        IDLE, BTHROWING, INPLATE
    }

    public enum Type {
        BLUE,GREEN,BROWN
    }
    public static int value = 5;

    public Body body;

    public Cookie(){
        //Type type;

    }
}
