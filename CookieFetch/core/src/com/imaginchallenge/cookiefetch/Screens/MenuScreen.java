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
 * Created by Jose on 04/11/2016.
 */
public class MenuScreen implements Screen{

    private CookieFetch game;
    private OrthographicCamera menuCam;
    private Viewport menuPort;

    private ImageButton playButton;
    private ImageButton highScoresButton;
    private ImageButton settingButton;
    private ImageButton musicButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    public MenuScreen(CookieFetch game){

        this.game=game;

        background = new Texture("menu.png");
        width = background.getWidth();
        height = background.getHeight();

        menuCam=new OrthographicCamera();
        menuPort = new StretchViewport(background.getWidth(),background.getHeight(),menuCam);

        stage = new Stage(menuPort);
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
        playButton.setPosition(width/2-playButton.getWidth()/2-400,height/2-120);
        playButton.setSize(250,250);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("HighScoresButton");
        style.down = skin.getDrawable("HighScoresPressed");
        highScoresButton = new ImageButton(style);
        highScoresButton.setPosition(width/2-playButton.getWidth()/2,height/2-120);
        highScoresButton.setSize(250,250);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("SettingsButton");
        style.down = skin.getDrawable("SettingsPressed");
        settingButton = new ImageButton(style);
        settingButton.setPosition(width/2-playButton.getWidth()/2+335, height/2-120);
        settingButton.setSize(250,250);


        stage.addActor(playButton);
        stage.addActor(highScoresButton);
        stage.addActor(settingButton);

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

        highScoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new HighScoreScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new SettingsScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });
    }

    public void update(float delta){

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        update(delta);

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);
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
        background.dispose();
        stage.dispose();
        ButtonsPack.dispose();
        skin.dispose();
        //music.dispose();
    }

}
