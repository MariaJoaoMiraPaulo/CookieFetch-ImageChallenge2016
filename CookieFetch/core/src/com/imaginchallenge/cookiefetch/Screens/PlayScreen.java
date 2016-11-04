package com.imaginchallenge.cookiefetch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.imaginchallenge.cookiefetch.CookieFetch;

/**
 * Created by Nuno on 04/11/2016.
 */

public class PlayScreen implements Screen, InputProcessor {

    private CookieFetch game;

    private Texture img;
    private TextureRegion[] cookiesRegion;
    private Vector2 imgPosition;

    private Texture backgroundImg;

    //Variables relationed to the touch events
    private Vector2 finishingPoint;
    private Vector2 startingPoint;
    private float distance;

    public PlayScreen(CookieFetch game){

        this.game = game;

        img = new Texture("cookieBlack.png");
        TextureRegion[][] tmp = TextureRegion.split(img,img.getWidth()/5, img.getHeight());
        cookiesRegion = new TextureRegion[5];
        int index = 0;
        for(int i=0;i<5;i++){
            cookiesRegion[index] = tmp[0][i];
            index++;
        }

        backgroundImg = new Texture("game.png");

        finishingPoint = new Vector2(0,0);
        startingPoint = new Vector2(0,0);
        imgPosition = new Vector2(Gdx.graphics.getWidth()/2-cookiesRegion[0].getRegionWidth()/2,0);

        //Telling Libgdx what it input process so it can be called when a new input event arrives
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundImg,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(cookiesRegion[0], imgPosition.x, imgPosition.y);
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

        Gdx.app.log("Toque", " "+ screenX + " " + screenY);

        startingPoint.set(screenX, screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        distance = startingPoint.dst(finishingPoint.x, finishingPoint.y);

        Gdx.app.log("CENAS", "Distancia "+distance);

        double screenDistance = Math.sqrt(Math.pow(Gdx.graphics.getWidth(),2) + Math.pow(Gdx.graphics.getHeight(),2));

        Gdx.app.log("CENAS", "Screen Distancia "+screenDistance);

        if(distance > (screenDistance*0.2)){
            Gdx.app.log("CENAS", "Aqui vai");
            imgPosition.set(finishingPoint.x, Gdx.graphics.getHeight()-img.getHeight());
        }

        startingPoint.set(0,0);
        finishingPoint.set(0,0);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        finishingPoint.set(screenX, screenY);

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
