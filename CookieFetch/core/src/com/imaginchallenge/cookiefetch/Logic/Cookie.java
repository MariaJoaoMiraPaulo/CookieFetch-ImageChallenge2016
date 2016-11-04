package com.imaginchallenge.cookiefetch.Logic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.imaginchallenge.cookiefetch.CookieFetch.V_WIDTH;

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
    private GameLogic gameLogic;

    public Body body;

    public Cookie(GameLogic gameLogic){
        //Type type;
        this.gameLogic = gameLogic;
        defineBody();
    }
    public void defineBody(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);


        bdef.type = BodyDef.BodyType.DynamicBody;
        body = this.gameLogic.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape cshape = new PolygonShape();

        cshape.setAsBox(50,50);
        fdef.shape = cshape;
        fdef.friction = 0.7f;
        body.createFixture(fdef).setUserData(this);
    }
}
