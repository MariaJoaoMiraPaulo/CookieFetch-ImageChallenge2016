package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.imaginchallenge.cookiefetch.Sprite.Animation;

import java.util.ArrayList;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Cookie {

    private float MOVEMENT = -300;

    public static final int COOKIES_DIVISON=5;

    public enum State {
        IDLE, BTHROWING, INPLATE
    }

    public enum Type {
        RED,BLACK,GREEN,BROWN
    }

    public static int value = 5;
    public Body body;

    private Vector2 position;
    private Vector2 velocity;

    private Rectangle bounds;

    private Animation cookieAnimation;
    private Type cookieType;
    private Texture sequenceFrames;
    private int cookieWidth;
    private int cookieHeight;
    private float cookieSpeedX;
    private float cookieSpeedY;
    private Boolean cookiePressed=false;

    public Cookie(int widht,int height,Type type){

        sequenceFrames=selectTexture(type);

        cookieWidth=sequenceFrames.getWidth()/5;
        cookieHeight=sequenceFrames.getHeight();

        float x=widht/2-cookieWidth/2;
        int y=100;


        position=new Vector2(x,y);
        velocity=new Vector2(0,0);

        cookieType=type;

        bounds=new Rectangle(x,y,sequenceFrames.getWidth()/5,sequenceFrames.getHeight());

        cookieAnimation=new Animation(new TextureRegion(sequenceFrames),5,0.1f);

        cookieSpeedX=0;
        cookieSpeedY=0;



    }

    public Texture selectTexture(Type type){

        if(type==Type.BLACK)
            return (new Texture("cookie1.png"));
        else if(type==Type.BROWN)
            return (new Texture("cookie2.png"));
        else if(type==Type.GREEN)
            return (new Texture("cookie3.png"));
        else if(type==Type.RED)
            return (new Texture("cookie4.png"));
        else
            return null;

    }

    public void update(float delta){

        if(cookiePressed)
            cookieAnimation.update(delta);

        //if(cookiePressed)
            move(cookieSpeedX,cookieSpeedY,0);

        bounds.setPosition(position.x,position.y);

    }

    public void render(SpriteBatch batch){
        batch.draw(cookieAnimation.getCurrFrame(),position.x,position.y);
    }

    public Boolean getCookiePressed(){
        return cookiePressed;
    }

    public void setCookiePressed(Boolean pressed){
        cookiePressed=true;
    }

    public void move(float x,float y,float delta){

        velocity.x = x;
        velocity.y = y;

        position.add(velocity.x, velocity.y);

        position.add(0, MOVEMENT * delta);
    }

    public int getCookieWidth(){
        return cookieWidth;
    }

    public int getCookieHeight(){
        return cookieHeight;
    }

    public void setCookieSpeed(float vx,float vy){
        cookieSpeedY=vy;
        cookieSpeedX=vx;
    }

}
