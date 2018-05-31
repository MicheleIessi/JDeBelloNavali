package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.FacadeClientController;
import com.debellonavali.Classes.Model.Factories.FleetFactory.FleetFactory;
import com.debellonavali.Classes.Model.Factories.GameFactory;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * DeBelloGame represents the current game of DeBelloNavali.
 */
public class DeBelloGame {

    private Battlefield playerBattlefield;
    private FleetFactory playerFleetFactory;
    private static FacadeClientController facadeController;
    private static DeBelloGame instance;
    private boolean playerTurn;
    private INotifiableScreen currentScreen;
    private Logger logger;
    private boolean gameEnded;


    /**
     * Default constructor, instantiates game variables
     */
    private DeBelloGame() {
        playerBattlefield = GameFactory.getInstance().createBattlefield();
        playerTurn = false;
        gameEnded = false;
        logger = Logger.getLogger(DeBelloGame.class.getSimpleName());
    }

    /**
     * Sets the player's turn to the given boolean value
     * @param playerTurn True if it is the player's turn, false otherwise
     */
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Attaches a FacadeClientController object to the Game
     * @param facadeClientController The FacadeClientController object to be attached
     */
    public void attachFacadeController(FacadeClientController facadeClientController) {
        facadeController = facadeClientController;
        logger.info("Facade Controller Attached");
    }

    /**
     * Performs an attack on the battlefield with the ship with the given identifier,
     * firing the weapon with the given identifier, on the given position
     * @param shipID The ID of the ship that has to attack
     * @param weaponID The ID of the weapon that has to fire
     * @param position The position on which to attack
     */
    public void attack(int shipID, int weaponID, int[] position) {
        //check if ship can attack, thus return the squares involved
        List<int[]> attackedSquares = playerBattlefield.attack(shipID, weaponID, position[0], position[1]);
        if(attackedSquares.size() > 0) {
            setPlayerTurn(false);
            IDTO attackDTO = DTOBuilder.getInstance().createAttackDTO(attackedSquares);
            ConnectionManagerImpl.getInstance().sendMessage(attackDTO);
        }
    }

    /**
     * Delegates an incoming communication IDTO object to the Facade
     * @param incomingDTO An incoming communication IDTO object
     * @return A response IDTO object
     */
    public IDTO incomingMessage(IDTO incomingDTO) {
        facadeController.incomingRequest(incomingDTO);
        return AnswerContainer.getInstance().getStoredDTO();
    }

    /**
     * Sets the current screen to the given INotifiableScreen, so that it will be possible
     * to update it when a communication is received
     * @param screen The screen to set
     */
    public void setCurrentScreen(INotifiableScreen screen) {
        this.currentScreen = screen;
    }

    /**
     * Method called from one of the observer controllers to notifyScreen the client's view of the game.
     * @param notifyingDTO The DTO containing data that will be used to notifyScreen the view
     */
    public void notifyScreen(IDTO notifyingDTO) {
        currentScreen.notifyScreen(notifyingDTO);
    }

    /**
     * Returns information about the player's turn
     * @return True if it is the player's turn, false otherwise
     */
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    /**
     * Returns the player's battlefield
     * @return The player's battlefield
     */
    public Battlefield getPlayerBattlefield() {
        return playerBattlefield;
    }

    /**
     * Returns information about the ending of the game
     * @return True if the game has ended, false otherwise
     */
    public boolean getGameEnded() {
        return gameEnded;
    }

    /**
     * Sets information about the ending of the game
     * @param end The end game information to set
     */
    public void setGameEnded(boolean end) {
        gameEnded = end;
    }

    /**
     * Gets the position on the battlefield of the ship with the given ID
     * @param shipId The ID of the ship to search
     * @return
     */
    public HashMap<String,String> getShipPosition(int shipId) {
        return this.playerBattlefield.getShipPosition(shipId);
    }

    /**
     * Returns the FleetFactory used in this game
     * @return The FleetFactory used in this game
     */
    public FleetFactory getPlayerFleetFactory() {
        return playerFleetFactory;
    }

    /**
     * Singleton: only one instance of this class is allowed to be instantiated
     * @return The single DeBelloGame instance.
     */
    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }

}