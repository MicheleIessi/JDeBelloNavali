package com.debellonavali;

import com.badlogic.gdx.Game;
import com.debellonavali.Screens.SplashScreen;

public class MainView extends Game {



	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
