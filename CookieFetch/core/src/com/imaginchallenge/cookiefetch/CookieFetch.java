package com.imaginchallenge.cookiefetch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.imaginchallenge.cookiefetch.Screens.MenuScreen;

public class CookieFetch extends Game {

	public static final int V_WIDTH=1080;
	public static final int V_HEIGHT=1920;

	public SpriteBatch batch;

	private int world=1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public int getWorld(){
		return world;
	}

	public void setWord(int world){
		this.world=world;
		Gdx.app.log("AQUI:",""+this.world);
	}
}
