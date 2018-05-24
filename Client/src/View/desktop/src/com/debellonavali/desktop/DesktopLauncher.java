package com.debellonavali.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.debellonavali.Constants;
import com.debellonavali.MainView;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Constants.APP_TITLE;
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;
		config.fullscreen = Constants.APP_FULLSCREEN;
		config.resizable = Constants.APP_RESIZABLE;


		new LwjglApplication(new MainView(), config);


	}
}
