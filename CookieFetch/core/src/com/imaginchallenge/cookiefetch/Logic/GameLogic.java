package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class GameLogic {

    public World world;
    public Player player;
    public Cookie cookie;

    public GameLogic(){
        world = new World(new Vector2(0,0),false);
        player = new Player();
        cookie = new Cookie(this);
    }

}
