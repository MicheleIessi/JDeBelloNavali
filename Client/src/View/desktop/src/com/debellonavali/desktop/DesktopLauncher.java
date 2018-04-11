package com.debellonavali.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.debellonavali.MainView;

public class DesktopLauncher {

	private static final String title = "De Bello Navali";

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = title;
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new MainView(), config);
	}
}
