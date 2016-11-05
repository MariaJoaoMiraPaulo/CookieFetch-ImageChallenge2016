package com.imaginchallenge.cookiefetch.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

/**
 * Created by Jose on 05/11/2016.
 */

public class Highscore {

    /** Name of the file */
    private String HIGHSCORE_FILE_NAME="world";
    /** Extension of the file */
    private String HIGHSCORE_FILE_EXTENSION=".dat";
    /** HighScore value */
    private int highscore;
    /** Actual score */
    private int score;
    /** World the score was made */
    private int world;

    /**
     * HighScore constructor
     * @param world World the score was made
     */
    public Highscore(int world){

        this.score=0;
        this.world=world;
        loadHighscore();

    }

    /**
     * Returns the score
     * @return score value
     */
    public int getScore(){
        return score;
    }

    /**
     * Update score value
     * @param val value of the update
     */
    public void update(int val){

        score+=val;
    }

    /**
     * reads the HigScore in this world
     */
    public void loadHighscore() {
        Gdx.app.log("entrou load","entrou load");
        FileHandle highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + world + HIGHSCORE_FILE_EXTENSION);

        Gdx.app.log("FILE","FILE"+HIGHSCORE_FILE_NAME + world + HIGHSCORE_FILE_EXTENSION);

        if(highscoreF.exists()) {
            String highscoreString = highscoreF.readString();
            highscore = Integer.parseInt(highscoreString);
            Gdx.app.log("existe","existe: "+highscore);
        }
        else{
            highscore = 0;
            Gdx.app.log("highscore load: ","highscore load: "+highscore);
        }
    }

    /**
     * Saves the score made
     */
    public void saveScore() {
        Gdx.app.log("score","score: "+score);
        Gdx.app.log("highscore","highscore: "+highscore);
        if(score > highscore) {
            FileHandle highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + world + HIGHSCORE_FILE_EXTENSION);

            try {
                if(!highscoreF.exists()) {
                    highscoreF.file().createNewFile();
                }

                highscoreF.writeString(new Integer(score).toString(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
