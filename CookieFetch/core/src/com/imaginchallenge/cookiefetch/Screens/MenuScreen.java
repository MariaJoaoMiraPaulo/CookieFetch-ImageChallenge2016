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
    private ImageButton exitButton;
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
        playButton.setPosition(width/2-playButton.getWidth()/2-10,height/2+80);
        playButton.setSize(150,150);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("HighScoresButton");
        style.down = skin.getDrawable("HighScoresPressed");
        highScoresButton = new ImageButton(style);
        highScoresButton.setPosition(width/2-playButton.getWidth()/2,height/2-120);
        highScoresButton.setSize(150,150);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("SettingsButton");
        style.down = skin.getDrawable("SettingsPressed");
        exitButton = new ImageButton(style);
        exitButton.setPosition(width/2-playButton.getWidth()/2, height/2-300);
        exitButton.setSize(150,150);


        stage.addActor(playButton);
        stage.addActor(highScoresButton);
        stage.addActor(exitButton);

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

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
    }

    public void handleInput(float delta){

    }

    public void update(float delta){
        handleInput(delta);
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































    /*private QuizRoad game;
    private OrthographicCamera playScreenCam;
    private Viewport playScreenPort;

    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    private ImageButton playButton;
    private ImageButton highScoresButton;
    private ImageButton exitButton;
    private ImageButton musicButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;

    public MenuScreen(QuizRoad game){

        this.game=game;
        background = new Texture("testImage.jpg");
        width = background.getWidth();
        height = background.getHeight();
        stage = new Stage(new FillViewport(1920,1080));
        //buttons
        ButtonsPack = new TextureAtlas("menuButtons1.pack");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("playButton");
        style.down = skin.getDrawable("playButtonPressed");
        playButton = new ImageButton(style);
        playButton.setPosition(1920/2,1080/2-playButton.getWidth()/2);

        /*** Creating buttons ***/
        /*playTexture = new Texture(Gdx.files.internal("menu/play.png"));
        ImageButton btnSingleplayer = new ImageButton(new SpriteDrawable(new Sprite(playTexture)));
        btnSingleplayer.getImage().setScaling(Scaling.fit);

        settingsAtlas = new TextureAtlas(Gdx.files.internal("packs/settings/settings.pack"));
        Animation settingsAnimation = new Animation(1f/20f, settingsAtlas.getRegions());
        AnimatedDrawable animatedDrawable = new AnimatedDrawable(settingsAnimation);
        ImageButton btnSettings = new ImageButton(animatedDrawable, animatedDrawable);

        highscoreAtlas = new TextureAtlas(Gdx.files.internal("packs/highscore/highscore.pack"));
        Animation highscoreAnimation = new Animation(1f/20f, highscoreAtlas.getRegions());
        animatedDrawable = new AnimatedDrawable(highscoreAnimation);
        ImageButton btnHighscore = new ImageButton(animatedDrawable, animatedDrawable);*/

        /*** Creating stage ***/
        /*Table buttonsTable = new Table();

        background = new Texture("testImage.jpg");
        buttonsTable.background(new SpriteDrawable(new Sprite(background)));

        stage = new Stage(new FillViewport(1920,1080));
        stage.addActor(buttonsTable);


        //stage.addActor(playButton);

       //loadListeneres();

    }

    public void loadListeneres(){
        playButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //if(game.getIsMusicOn())
                //    music.stop();
                //Screen screen = new ChooseWorldScreen(game);
                //game.setScreen(screen);
                dispose();
            }
        });

    }

    @Override
    public void show() {

    }

    public void update(float delta){

    }

    @Override
    public void render(float delta) {
        update(delta);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    }*/
}
