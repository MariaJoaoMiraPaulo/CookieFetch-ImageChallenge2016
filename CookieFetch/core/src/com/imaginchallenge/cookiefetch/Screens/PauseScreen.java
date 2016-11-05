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

public class PauseScreen implements Screen {

    private CookieFetch game;
    private OrthographicCamera pauseCam;
    private Viewport pausePort;

    private Texture background;
    private float width;
    private float height;

    private Stage stage;

    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;

    private ImageButton homeButton;
    private ImageButton playButton;

    public PauseScreen(CookieFetch game){
        this.game = game;

        background=new Texture("game"+game.getWorld()+".png");
        width = background.getWidth();
        height = background.getHeight();

        pauseCam=new OrthographicCamera();
        pausePort = new StretchViewport(background.getWidth(),background.getHeight(),pauseCam);

        stage = new Stage(pausePort);
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
        playButton.setPosition(width/2-playButton.getWidth()/2-10,height/2+80);
        playButton.setSize(150,150);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("HomePressed");
        style.down = skin.getDrawable("HomeButton");
        homeButton = new ImageButton(style);
        homeButton.setPosition(width/2-playButton.getWidth()/2, height/2-300);
        homeButton.setSize(150,150);


        stage.addActor(playButton);
        stage.addActor(homeButton);

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

        homeButton.addListener(new ClickListener() {
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

        pauseCam.update();
        game.batch.setProjectionMatrix(pauseCam.combined);
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
