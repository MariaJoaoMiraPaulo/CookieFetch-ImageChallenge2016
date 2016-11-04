package com.imaginchallenge.cookiefetch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.imaginchallenge.cookiefetch.Screens.MenuScreen;
import com.imaginchallenge.cookiefetch.Screens.PlayScreen;

public class CookieFetch extends Game {

	public static final int V_WIDTH=1080;
	public static final int V_HEIGHT=1920;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//setScreen(new MenuScreen(this));
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
