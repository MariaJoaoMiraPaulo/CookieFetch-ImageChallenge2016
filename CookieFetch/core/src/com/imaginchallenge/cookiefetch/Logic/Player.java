package com.imaginchallenge.cookiefetch.Logic;

/**
 * Created by antoniomelo on 11/4/16.
 */

public class Player {

    public enum State {
        IDLE, THROWING, DEAD
    }
    private boolean alive;
    private int lives;

    public Player(){
        alive = true;
        lives = 3;
    }

    public boolean isAlive(){
        return alive;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int l){
        lives+=l;
    }

    public void killPlayer(){
        alive = false;
    }
}
