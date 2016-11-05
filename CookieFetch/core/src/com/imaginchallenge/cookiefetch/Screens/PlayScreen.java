package com.imaginchallenge.cookiefetch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.imaginchallenge.cookiefetch.CookieFetch;
import com.imaginchallenge.cookiefetch.Logic.Cookie;
import com.imaginchallenge.cookiefetch.Logic.Highscore;
import com.imaginchallenge.cookiefetch.Logic.Plate;
import com.imaginchallenge.cookiefetch.Logic.Player;
import com.imaginchallenge.cookiefetch.Sprite.Hud;

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
    private Highscore highscore;
    private Label scoreLabel;
    private Stage stage;
    private Table table;
    public Viewport viewport;


    private Texture pauseButtonImage;
    private Texture heart;

    private Cookie cookie;


    public PlayScreen(CookieFetch game){

        this.game = game;
        player = new Player();


        finishingPoint = new Vector2(0,0);
        startingPoint = new Vector2(0,0);

        background=new Texture("game"+game.getWorld()+".png");
        heart = new Texture("heart.png");
        width = background.getWidth();
        height = background.getHeight();

        pauseButtonImage = new Texture("pauseButton.png");

        //Creation of Plates and cookie
        plates = new ArrayList<Plate>();
        fillPlatesArray();
        cookie=new Cookie(background.getWidth(),background.getHeight());

        highscore=new Highscore(game.getWorld());

        viewport=new StretchViewport(CookieFetch.V_WIDTH,CookieFetch.V_HEIGHT);
        stage=new Stage(viewport,game.batch);

        scoreLabel = new Label(String.format("%06d", highscore.getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setFontScale(3);

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(scoreLabel).right().top().expandX().padTop(20).padRight(width/2-50-scoreLabel.getWidth()/2);

        stage.addActor(table);




        //Telling Libgdx what it input process so it can be called when a new input event arrives
        Gdx.input.setInputProcessor(this);
    }

    public void fillPlatesArray(){
        for(int i = 0;i < 3; i++){
            Plate p = new Plate(width,height,i*300+1000);
            p.setRandomTexture();
            plates.add(i,p);
        }
    }

    @Override
    public void show() {

    }

    public void update(float delta){

        cookie.update(delta);
        for(int i = 0;i < 3;i++){
            plates.get(i).update(delta);
            //Gdx.app.log("ola"," " + plates.get(i).checkColision(cookie));
            player.setLives(plates.get(i).checkColision(cookie));
            Gdx.app.log("Vidas"," "+player.getLives());
            Gdx.app.log("BOUNDS"," " + plates.get(0).getBounds().x);

            int col=plates.get(i).checkColision(cookie);

            if(col==-1)
            {
                player.setLives(col);
            }
            else if(col==1){
                highscore.update(10);
            }
        }

        scoreLabel.setText(String.format("%06d", highscore.getScore()));

        if(player.getLives() == 0){
            game.setScreen(new GameOverScreen(game));
        }

        //TODO: Condição para acabar o jogo
        //highscore.saveScore();
    }


    @Override
    public void render(float delta) {

        update(delta);

        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(pauseButtonImage, (int)(width-width*0.1),0, (int)(width*0.1),(int)(height*0.05));
        for ( int i= 0 ;i < player.getLives();i++){
            game.batch.draw(heart,0+100*i, 1800, 100, 100);
        }
        cookie.render(game.batch);
        for(int i = 0;i < 3;i++){
            plates.get(i).render(game.batch);
        }
        game.batch.end();
        stage.draw();
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
            Screen screen = new PauseScreen(game, this);
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

        float dist = finishingPoint.dst(startingPoint);

        if(dist > height * 0.05) {

            Vector2 tmpVetor1 = new Vector2(finishingPoint.x - startingPoint.x, finishingPoint.y - startingPoint.y);

            Vector2 tmpVetor2 = new Vector2(0, 0 - startingPoint.y);

            float ang = ((float) Math.PI * tmpVetor1.angle(tmpVetor2)) / 180;

            if(ang <=Math.PI/2 && ang>=-Math.PI/2){
            cookie.setCookiePressed(true);
            cookie.setCookieSpeed(-1 * (float) (25 * Math.sin(ang)), (float) (25 * Math.cos(ang)));
         }
            //distance = startingPoint.dst(finishingPoint.x, finishingPoint.y);

            Gdx.app.log("Toque", "Angulo " + ang);
            Gdx.app.log("Toque", "Valores " + (float) (100 * Math.sin(ang)) + " " + (float) (100 * Math.cos(ang)));

      /*  double screenDistance = Math.sqrt(Math.pow(Gdx.graphics.getWidth(),2) + Math.pow(Gdx.graphics.getHeight(),2));

        Gdx.app.log("CENAS", "Screen Distancia "+screenDistance);

        if(distance > (screenDistance*0.2)){
            Gdx.app.log("CENAS", "Aqui vai");
            //imgPosition.set(finishingPoint.x, Gdx.graphics.getHeight()-img.getHeight());
        }*/
        }
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

    public void input() {
        Gdx.input.setInputProcessor(this);
    }
}
