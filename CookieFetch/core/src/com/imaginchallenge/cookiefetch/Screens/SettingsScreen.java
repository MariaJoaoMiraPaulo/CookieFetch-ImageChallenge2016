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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.imaginchallenge.cookiefetch.CookieFetch;
import com.imaginchallenge.cookiefetch.Logic.Cookie;

/**
 * Created by Jose on 05/11/2016.
 */

public class SettingsScreen implements Screen {

    private static final int NUM_GAME_MODES=3;

    private CookieFetch game;
    private OrthographicCamera menuCam;
    private Viewport menuPort;

    private ImageButton leftButton;
    private ImageButton rightButton;
    private ImageButton homeButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Array<Texture> gameModes;
    private Stage stage;
    private float width;
    private float height;
    private int currMode;

    public SettingsScreen(CookieFetch game){

        this.game=game;

        this.game=game;

        background = new Texture("menu.png");
        width = background.getWidth();
        height = background.getHeight();

        menuCam=new OrthographicCamera();
        menuPort = new StretchViewport(background.getWidth(),background.getHeight(),menuCam);

        stage = new Stage(menuPort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        gameModes=new Array<Texture>();
        currMode=1;

        for(int i=1;i<=NUM_GAME_MODES;i++){

            gameModes.add(new Texture("game"+i+".png"));

        }

        //buttons
        ButtonsPack = new TextureAtlas("SettingsButtons.atlas");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("LeftButton");
        style.down = skin.getDrawable("LeftPressed");
        leftButton = new ImageButton(style);
        leftButton.setPosition(75,height/2-200);
        leftButton.setSize(150,150);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("RightButton");
        style.down = skin.getDrawable("RightPressed");
        rightButton = new ImageButton(style);
        rightButton.setPosition(width-80-rightButton.getWidth(),height/2-200);
        rightButton.setSize(150,150);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("HomePressed");
        style.down = skin.getDrawable("HomeButton");
        homeButton = new ImageButton(style);
        homeButton.setPosition(60,height-80-homeButton.getHeight());
        homeButton.setSize(150,150);


        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(homeButton);

        loadListeneres();


    }

    public void loadListeneres(){
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateCurrMode(-1);
            }
        });

        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateCurrMode(1);
            }
        });

        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setWord(currMode);
                Screen screen = new MenuScreen(game);
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
        game.batch.draw(gameModes.get(currMode-1),250,400,600,750);
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

    public void updateCurrMode(int change){

        if(change<0)
        {
            if(currMode==1)
                currMode=NUM_GAME_MODES;
            else
                currMode--;
        }
        else{
            if(currMode==NUM_GAME_MODES)
                currMode=1;
            else
                currMode++;
        }
    }
}
