package com.imaginchallenge.cookiefetch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.imaginchallenge.cookiefetch.CookieFetch;
import com.imaginchallenge.cookiefetch.Logic.Cookie;
import com.imaginchallenge.cookiefetch.Logic.Plate;
import com.imaginchallenge.cookiefetch.Logic.Player;

import java.util.ArrayList;

/**
 * Created by Nuno on 04/11/2016.
 */

public class PlayScreen implements Screen, InputProcessor {

    private CookieFetch game;
    private ArrayList<Plate> plates;
    private Player player;

    //Variables relationed to the touch events
    private Vector2 finishingPoint;
    private Vector2 startingPoint;

    private Texture background;
    private float width;
    private float height;

    private Texture pauseButtonImage;

    private Cookie cookie;


    public PlayScreen(CookieFetch game){

        this.game = game;
        player = new Player();


        finishingPoint = new Vector2(0,0);
        startingPoint = new Vector2(0,0);

        background=new Texture("game"+game.getWorld()+".png");
        width = background.getWidth();
        height = background.getHeight();

        pauseButtonImage = new Texture("pauseButton.png");

        //Creation of Plates and cookie
        plates = new ArrayList<Plate>();
        fillPlatesArray();
        setPlatesInitialPosition();
        cookie=new Cookie(background.getWidth(),background.getHeight());



        //Telling Libgdx what it input process so it can be called when a new input event arrives
        Gdx.input.setInputProcessor(this);
    }

    public void fillPlatesArray(){
        for(int i = 0;i < 3; i++){
            Plate p = new Plate(0,700 +100*i);
            p.setRandomTexture();
            plates.add(i,p);
        }
    }

    public void setPlatesInitialPosition(){
        for(int i =0;i < 3;i++){
            plates.get(i).setPosition(1,975 +325*i);


        }
    }

    @Override
    public void show() {

    }

    public void update(float delta){

        cookie.update(delta);
        for(int i = 0;i < 3;i++){
            plates.get(i).update(delta);
            player.setLives(plates.get(i).checkColision(cookie));
        }
    }


    @Override
    public void render(float delta) {

        update(delta);

        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(pauseButtonImage, (int)(width-width*0.1),0, (int)(width*0.1),(int)(height*0.05));
        cookie.render(game.batch);
        for(int i = 0;i < 3;i++){
            plates.get(i).render(game.batch);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX > Gdx.graphics.getWidth() - width*0.1 && screenX < Gdx.graphics.getWidth() &&
                screenY > Gdx.graphics.getHeight() - height*0.05 && screenY < Gdx.graphics.getHeight()){
            Screen screen = new PauseScreen(game);
            game.setScreen(screen);
            dispose();
        }

        Gdx.app.log("Toque", " "+ screenX + " " + screenY);

        startingPoint.set(screenX, screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        finishingPoint.set(screenX, screenY);

        Gdx.app.log("Toque", "FINAL "+ screenX + " " + screenY);

        Gdx.app.log("Toque", "INICIAL "+ startingPoint.x + " " + startingPoint.y);

        Vector2 tmpVetor1=new Vector2(finishingPoint.x-startingPoint.x,finishingPoint.y-startingPoint.y);

        Vector2 tmpVetor2=new Vector2(0,0-startingPoint.y);

        cookie.setCookiePressed(true);

        float ang = ((float)Math.PI*tmpVetor1.angle(tmpVetor2))/180;


        cookie.setCookieSpeed(-1*(float)(100*Math.sin(ang)),(float)(100*Math.cos(ang)));

        //distance = startingPoint.dst(finishingPoint.x, finishingPoint.y);

        Gdx.app.log("Toque", "Angulo "+ang);
        Gdx.app.log("Toque", "Valores "+(float)(100*Math.sin(ang)) + " " + (float)(100*Math.cos(ang)));

      /*  double screenDistance = Math.sqrt(Math.pow(Gdx.graphics.getWidth(),2) + Math.pow(Gdx.graphics.getHeight(),2));

        Gdx.app.log("CENAS", "Screen Distancia "+screenDistance);

        if(distance > (screenDistance*0.2)){
            Gdx.app.log("CENAS", "Aqui vai");
            //imgPosition.set(finishingPoint.x, Gdx.graphics.getHeight()-img.getHeight());
        }*/

        startingPoint.set(0,0);
        finishingPoint.set(0,0);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //finishingPoint.set(screenX, screenY);

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
