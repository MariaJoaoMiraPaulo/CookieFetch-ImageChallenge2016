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

    private static final int COOKIES_DIVISON=5;
    private static final int NUM_TEXTURES_COOKIE=4;

    public enum Type {
        RED,GREEN,BROWN,BLACK
    }

    private Vector2 position;
    private Vector2 velocity;

    private Rectangle bounds;

    private Animation cookieAnimation;

    public Type getCookieType() {
        return cookieType;
    }

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

    public Cookie(int width,int height){
        cookieTextures = new ArrayList<Texture>();
        rand = new Random();

        this.width = width;
        this.heigth = height;

        fillTextures();

        randomColor();

        cookieWidth=texture.getWidth()/COOKIES_DIVISON;
        cookieHeight=texture.getHeight();

        float x=width/2-cookieWidth/2;


        position=new Vector2(x,startingY);
        velocity=new Vector2(0,0);


        bounds=new Rectangle(x,startingY,cookieWidth,cookieHeight);

        cookieAnimation=new Animation(new TextureRegion(texture),COOKIES_DIVISON,0.1f);

        cookieSpeedX=0;
        cookieSpeedY=0;
    }
    public Rectangle getBounds(){
        return bounds;
    }

    private void fillTextures(){

        for(int i=1;i<=NUM_TEXTURES_COOKIE;i++){

            cookieTextures.add(i-1,new Texture("cookie"+i+".png"));
        }
    }

    public void resetCookie(){
        float x=width/2-cookieWidth/2;
        position.set(x,startingY);
        velocity.set(0,0);
        bounds.set(x,startingY,cookieWidth,cookieHeight);
        cookieSpeedX=0;
        cookieSpeedY=0;
        cookiePressed = false;
        cookieAnimation.dispose();
        randomColor();
    }

    public void randomColor(){

        int randNum = (rand.nextInt(3));

        Gdx.app.log("AQUI: ","Color"+randNum);

        switch (randNum){
            case 0:
                cookieType = Type.BROWN;
                selectTexture(Type.BROWN);
                break;
            case 1:
                cookieType = Type.GREEN;
                selectTexture(Type.GREEN);
                break;
            case 2:
                cookieType = Type.RED;
                selectTexture(Type.RED);
                break;
            case 3:
                cookieType = Type.BLACK;
                selectTexture(Type.BLACK);
                break;
            default:
                break;
        }

        cookieAnimation=new Animation(new TextureRegion(texture),5,0.1f);
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


        move(cookieSpeedX,cookieSpeedY,0);

        bounds.setPosition(position.x,position.y);

        if(outOfBounds()){
            resetCookie();
        }

    }

    public void render(SpriteBatch batch){

        batch.draw(cookieAnimation.getCurrFrame(),position.x,position.y);

        //Gdx.app.log("ERRO","X: "+position.x+", Y :"+position.y);

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

    public boolean outOfBounds(){

        if(position.x + cookieWidth < 0 || position.x > width || position.y < 0 || position.y > heigth + cookieHeight){
            return true;
        }

        return false;
    }

}
