package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.imaginchallenge.cookiefetch.Sprite.Animation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Cookie {

    private float MOVEMENT = -300;
    private ArrayList<Texture> cookieTextures;

    public static final int COOKIES_DIVISON=5;

    public enum State {
        IDLE, BTHROWING, INPLATE
    }

    public enum Type {
        RED,GREEN,BROWN,BLACK
    }

    public static int value = 5;
    public Body body;

    private Vector2 position;
    private Vector2 velocity;

    private Rectangle bounds;

    private Animation cookieAnimation;
    private Type cookieType;
    private int cookieWidth;
    private int cookieHeight;
    private float cookieSpeedX;
    private float cookieSpeedY;
    private Boolean cookiePressed=false;
    private Random rand;

    private static int startingY = 100;
    private int width;
    private int heigth;
    private Texture texture;

    public Cookie(int width,int height,Type type){
        cookieTextures = new ArrayList<Texture>();

        this.width = width;
        this.heigth = height;

        fillTextures();
        selectTexture(type);

        cookieWidth=texture.getWidth()/5;
        cookieHeight=texture.getHeight();

        float x=width/2-cookieWidth/2;


        position=new Vector2(x,startingY);
        velocity=new Vector2(0,0);

        cookieType=type;

        bounds=new Rectangle(x,startingY,cookieWidth,cookieHeight);

        cookieAnimation=new Animation(new TextureRegion(texture),5,0.1f);

        cookieSpeedX=0;
        cookieSpeedY=0;
    }

    private void fillTextures(){
        Texture tex1 = new Texture("cookie1.png");
        Texture tex2 = new Texture("cookie2.png");
        Texture tex3 = new Texture("cookie3.png");
        Texture tex4 = new Texture("cookie4.png");
        cookieTextures.add(1,tex2);
        cookieTextures.add(2,tex3);
        cookieTextures.add(3,tex4);
    }

    public void resetCookie(){
        float x=width/2-cookieWidth/2;
        position.set(0,startingY);
        velocity.set(0,0);
        bounds.set(x,startingY,cookieWidth,cookieHeight);
        cookieSpeedX=0;
        cookieSpeedY=0;
        randomColor();
    }

    public void randomColor(){
        int randNum = (rand.nextInt(3));
        switch (randNum){
            case 0:
                selectTexture(Type.BROWN);
                break;
            case 1:
                selectTexture(Type.GREEN);
                break;
            case 2:
                selectTexture(Type.RED);
                break;
            case 3:
                selectTexture(Type.BLACK);
                break;
            default:
                break;
        }
    }

    public void selectTexture(Type type){

        switch (type){
            case BLACK:
                texture = cookieTextures.get(0);
                break;
            case BROWN:
                texture = cookieTextures.get(1);
                break;
            case GREEN:
                texture = cookieTextures.get(2);
                break;
            case RED:
                texture = cookieTextures.get(3);
                break;
            default:
                break;
        }
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
        cookiePressed=pressed;
    }

    public void move(float x,float y,float delta){

        velocity.x = x;
        velocity.y = y;

        position.add(velocity.x, velocity.y);
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
