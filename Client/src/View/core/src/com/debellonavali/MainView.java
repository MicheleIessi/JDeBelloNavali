package com.debellonavali;

import com.badlogic.gdx.Game;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Controller.FacadeClientController;
import com.debellonavali.Classes.Controller.Observers.AttackResultsObserver;
import com.debellonavali.Classes.Controller.Observers.ChatObserver;
import com.debellonavali.Classes.Controller.Observers.LoginObserver;
import com.debellonavali.Classes.Controller.Observers.ReceiveAttackObserver;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Classes.Model.Factories.GameFactory;
import com.debellonavali.Screens.SplashScreen;

public class MainView extends Game {

	private int port;

	@Override
	public void create () {
		DeBelloGame dbg = GameFactory.getInstance().createDeBelloGame();

		FacadeClientController clientController = new FacadeClientController();
		clientController.attachObserver(new ReceiveAttackObserver());
		clientController.attachObserver(new ChatObserver());
		clientController.attachObserver(new LoginObserver());
		clientController.attachObserver(new AttackResultsObserver());
		dbg.attachFacadeController(clientController);

		ConnectionManagerImpl connectionManager = ConnectionManagerImpl.getInstance();
		connectionManager.initialize(Constants.DEFAULT_ENEMY_PORT);
		connectionManager.setEnemyInformation("localhost", Constants.DEFAULT_PLAYER_PORT);
		connectionManager.startMonitoringThread();

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
