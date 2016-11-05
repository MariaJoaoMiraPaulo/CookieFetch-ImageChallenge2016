package com.imaginchallenge.cookiefetch.Logic;

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

    private Type plateType;
    private Texture plateImage;
    private Rectangle bounds;
    private Vector2 position;
    private ArrayList<Texture> platesTextures;  // 0- green, 1- brown, 2- black, 3- red
    private Random rand;
    private int min=0, max=3;
    private int plateWidth = 270 ;
    private int plateLength = 150 ;


    public enum Type {
        GREEN,BROWN,BLACK,RED
    }
    public Plate(int x, int y){
        platesTextures = new ArrayList<Texture>();
        position = new Vector2();
        rand = new Random();
        fillTexturesArray();
        setRandomTexture();
        setPosition(x,y);
        bounds = new Rectangle(x,y,plateImage.getWidth(),plateImage.getHeight());
    }

     public void setBoundsPosition(int x, int y){
        bounds.set(x,y,plateImage.getWidth(),plateImage.getHeight());
     }
    public void setPosition(int x,int y){
        position.set(x,y);
    }

    public void fillTexturesArray(){
        platesTextures.add(0, new Texture("plate1.png"));
        platesTextures.add(1, new Texture("plate2.png"));
        platesTextures.add(2, new Texture("plate5.png"));
        platesTextures.add(3, new Texture("plate3.png"));
    }

    public void setTexture(){
        switch (plateType){
            case BROWN:
                this.plateImage = new Texture("plate2.png");
                break;
            case GREEN:
                this.plateImage = new Texture("plate1.png");
                break;
            case RED:
                this.plateImage = new Texture("plate3.png");
                break;
            case BLACK:
                this.plateImage = new Texture("plate5.png");
                break;
        }
    }

    public void setRandomTexture(){
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch (randomNum){
            case 0:
                plateType=Type.GREEN;
                setTexture();
                break;
            case 1:
                plateType=Type.BROWN;
                setTexture();
                break;
            case 2:
                plateType=Type.BLACK;
                setTexture();
                break;
            case 3:
                plateType= Type.RED;
                setTexture();
                break;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(plateImage,position.x,position.y,plateWidth,plateLength);
    }

    public boolean collides(Rectangle cookie){
        return cookie.overlaps(bounds);
    }



}
