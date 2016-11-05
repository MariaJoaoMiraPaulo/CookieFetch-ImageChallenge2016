package com.imaginchallenge.cookiefetch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.imaginchallenge.cookiefetch.CookieFetch;

/**
 * Created by Nuno on 05/11/2016.
 */

public class GameOverScreen implements Screen{

    private CookieFetch game;
    private OrthographicCamera gameOverCam;
    private Viewport gameOverPort;

    private ImageButton playButton;
    private ImageButton menuButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Stage stage;

    private Texture background;
    private float width;
    private float height;

    public GameOverScreen(CookieFetch game){

        this.game=game;

        background = new Texture("gameOver.png");
        width = background.getWidth();
        height = background.getHeight();

        gameOverCam=new OrthographicCamera();
        gameOverPort = new StretchViewport(background.getWidth(),background.getHeight(),gameOverCam);

        stage = new Stage(gameOverPort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        //buttons
        ButtonsPack = new TextureAtlas("menuButtons.atlas");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("PlayButton");
        style.down = skin.getDrawable("PlayPressed");
        playButton = new ImageButton(style);
        playButton.setPosition(width/2-playButton.getWidth()/2-240,height/2-120);
        playButton.setSize(250,250);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("HomePressed");
        style.down = skin.getDrawable("HomeButton");
        menuButton = new ImageButton(style);
        menuButton.setPosition(width/2-playButton.getWidth()/2+200,height/2-120);
        menuButton.setSize(250,250);

        stage.addActor(playButton);
        stage.addActor(menuButton);

        loadListeneres();
    }

    public void loadListeneres(){
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen=new PlayScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new MenuScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        gameOverCam.update();
        game.batch.setProjectionMatrix(gameOverCam.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
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
}
