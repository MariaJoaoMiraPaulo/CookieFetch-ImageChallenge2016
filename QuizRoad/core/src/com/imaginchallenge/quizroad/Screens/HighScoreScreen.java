package com.imaginchallenge.quizroad.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.imaginchallenge.quizroad.QuizRoad;

/**
 * Created by Jose on 04/11/2016.
 */
public class HighScoreScreen implements Screen{

    private String HIGHSCORE_FILE_NAME="world";
    private String HIGHSCORE_FILE_EXTENSION=".dat";

    private QuizRoad game;
    private OrthographicCamera highScoreCam;
    private Viewport highScorePort;

    private ImageButton backButton;
    private TextureAtlas ButtonsPack;
    private Label highScoreLabel1;
    private Label highScoreLabel2;
    private Label highScoreLabel3;
    private int highScore1;
    private int highScore2;
    private int highScore3;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    public HighScoreScreen(QuizRoad game)
    {
        this.game=game;
        background = new Texture("testImage.jpg");
        width = background.getWidth();
        height = background.getHeight();
        highScoreCam=new OrthographicCamera();
        highScorePort = new StretchViewport(background.getWidth(),background.getHeight(),highScoreCam);
        stage = new Stage(highScorePort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ButtonsPack = new TextureAtlas("ChooseWorldButtons.pack");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("homeButton");
        style.down = skin.getDrawable("homeButtonPressed");
        backButton = new ImageButton(style);
        backButton.setPosition(20,height-80);

        loadHighScores();

        highScoreLabel1 = new Label(String.format("%06d", highScore1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highScoreLabel1.setFontScale(3);
        highScoreLabel1.setPosition(width/2-90,height/2+100);

        highScoreLabel2 = new Label(String.format("%06d", highScore2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highScoreLabel2.setFontScale(3);
        highScoreLabel2.setPosition(width/2-90,height/2-35);

        highScoreLabel3 = new Label(String.format("%06d", highScore3), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        highScoreLabel3.setFontScale(3);
        highScoreLabel3.setPosition(width/2-90,height/2-170);

        stage.addActor(backButton);
        stage.addActor(highScoreLabel1);
        stage.addActor(highScoreLabel2);
        stage.addActor(highScoreLabel3);

        loadListeneres();
    }

    public void loadListeneres(){
        backButton.addListener(new ClickListener() {
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

    public void loadHighScores(){

        FileHandle highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + 1 + HIGHSCORE_FILE_EXTENSION);

        if(highscoreF.exists()) {
            String highscoreString = highscoreF.readString();
            highScore1 = Integer.parseInt(highscoreString);

        }
        else{
            highScore1 = 0;
        }

        highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + 2 + HIGHSCORE_FILE_EXTENSION);

        if(highscoreF.exists()) {
            String highscoreString = highscoreF.readString();
            highScore2 = Integer.parseInt(highscoreString);
        }
        else{
            highScore2 = 0;
        }

        highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + 3 + HIGHSCORE_FILE_EXTENSION);

        if(highscoreF.exists()) {
            String highscoreString = highscoreF.readString();
            highScore3 = Integer.parseInt(highscoreString);
        }
        else{
            highScore3 = 0;
        }
    }

    public void handleInput(float delta){

    }

    public void update(float delta){
        handleInput(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        highScoreCam.update();
        game.batch.setProjectionMatrix(highScoreCam.combined);
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
        skin.dispose();
        ButtonsPack.dispose();
    }

}
