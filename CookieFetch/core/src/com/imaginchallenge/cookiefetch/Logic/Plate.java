package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.imaginchallenge.cookiefetch.CookieFetch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Plate {

    private static final int NUM_TEXTURES_PLATES=4;

    public enum TypePlate {
        GREEN,BROWN,BLACK,RED
    }

    private static final int V_WIDTH=1080;
	private static final int V_HEIGHT=1920;
    private TypePlate plateType;

    public Rectangle getBounds() {
        return bounds;
    }

    private Rectangle bounds;
    private Vector2 position;
    private float width;
    private float height;
  //  private int platesWidth;
  //  private int platesHeight;
    private ArrayList<Texture> platesTextures;  // 0- green, 1- brown, 2- red, 3- black
    private int platesIndice;
    private Random rand;
    private int min=0, max=3;
    private int plateWidth = 270 ;
    private int plateHeight = 150 ;
    private int velX;
    private int minimumVelocity;
    private double gameTime;

    public Plate(float width,float height,int y){

        this.width=width;
        this.height=height;

        platesTextures = new ArrayList<Texture>();
        fillTexturesArray();

        gameTime = 0;
        minimumVelocity = 4;
        rand = new Random();
        setRandomVelocity(true);

        int x=plateWidth;



        position=new Vector2(x,y);

        setRandomTexture();

        bounds = new Rectangle(x,y,plateWidth,plateHeight);
    }

    public void setRandomVelocity(boolean positive){
        if(positive)
            velX = rand.nextInt(6) +minimumVelocity;
        else
            velX = - (rand.nextInt(6) + minimumVelocity);
    }

    public void setBoundsPosition(float x, float y){
        bounds.set(x,y,plateWidth,plateHeight);
     }

    public void fillTexturesArray(){

        for(int i=1;i<=NUM_TEXTURES_PLATES;i++){
            platesTextures.add(i-1,new Texture("plate"+i+".png"));
        }

    }

    public void setRandomTexture(){
        int randomNum = rand.nextInt(4);
        platesIndice=randomNum;
        setPlateType();
    }

    public void render(SpriteBatch batch){
        batch.draw(platesTextures.get(platesIndice),position.x,position.y, plateWidth,plateHeight);
    }

    public void move(int x){
        position.add(x,0);
    }

    public void update(float delta){
        gameTime += delta;

        if(gameTime > 10){
            minimumVelocity = 8;
        }
        if(gameTime > 15){
            minimumVelocity = 12;
        }
        if(gameTime > 20){
            minimumVelocity = 16;
        }

        if((position.x +plateWidth) >= V_WIDTH && velX>0){
            setRandomVelocity(false);
            setRandomTexture();
        }
        else if (position.x <= 0 && velX<0){
            setRandomVelocity(true);
            setRandomTexture();
        }

            move(velX);
            setBoundsPosition(position.x, position.y);


    }

    public int checkColision(Cookie cookie) {

        if (collides(cookie.getBounds())) {

            if (plateType == TypePlate.BLACK) {
                cookie.resetCookie();
                return -1;
            } else {
                cookie.resetCookie();
                return 0;
            }

        }

        return 1;
    }

    public boolean collides(Rectangle cookie){

        return cookie.overlaps(bounds);
    }

    public void setPlateType(){

        switch(platesIndice){

            case 0:
                plateType=TypePlate.GREEN;
                break;
            case 1:
                plateType=TypePlate.BROWN;
                break;
            case 2:
                plateType= TypePlate.RED;
                break;
            case 3:
                plateType=TypePlate.BLACK;
                break;
            default:
                break;
        }
    }



}
