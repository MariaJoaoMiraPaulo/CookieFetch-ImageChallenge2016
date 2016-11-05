package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Plate {

    private Type plateType;
    private Texture plateImage;
    private Rectangle bounds;
    private Vector2 position;

    public enum Type {
        GREEN,BROWN,BLACK,RED
    }
    public Plate(Type type){
        this.plateType = type;
        setTexture();
        bounds=new Rectangle(position.x,position.y,this.plateImage.getWidth(),this.plateImage.getHeight());
    }

    public void setTexture(){
        switch (plateType){
            case BROWN:
                this.plateImage = new Texture("plate2.png");
            case GREEN:
                this.plateImage = new Texture("plate1.png");
            case RED:
                this.plateImage = new Texture("plate3.png");
            case BLACK:
                this.plateImage = new Texture("plate5.png");
        }
    }

    public boolean collides(Rectangle cookie){
        return cookie.overlaps(bounds);
    }

}
