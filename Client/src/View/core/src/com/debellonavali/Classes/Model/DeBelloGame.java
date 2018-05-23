package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.FacadeClientController;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * DeBelloGame represents the current game of DeBelloNavali.
 */
public class DeBelloGame {

    private Battlefield playerBattlefield;
    private static FacadeClientController facadeController;
    private static DeBelloGame instance;
    private boolean playerTurn;
    private INotifiableScreen currentScreen;
    private Logger logger;




    public DeBelloGame() {
        playerBattlefield = new Battlefield();
        playerTurn = false;

        logger = Logger.getLogger(DeBelloGame.class.getSimpleName());
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void attachFacadeController(FacadeClientController facadeClientController) {
        facadeController = facadeClientController;
        logger.info("Facade Controller Attached");
    }

    public Battlefield getPlayerBattlefield() {
        return playerBattlefield;
    }

    public void attack(int shipID, int weaponID, int[] position) {
        //check if ship can attack, thus return the squares involved
        List<int[]> attackedSquares = playerBattlefield.attack(shipID, weaponID, position[0], position[1]);
        if(attackedSquares.size() > 0) {
            IDTO attackDTO = DTOBuilder.getInstance().createAttackDTO(attackedSquares);
            ConnectionManagerImpl.getInstance().sendMessage(attackDTO);
        }
    }

    public IDTO incomingMessage(IDTO incomingDTO) {
        facadeController.incomingRequest(incomingDTO);
        return AnswerContainer.getInstance().getStoredDTO();
    }

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


    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public HashMap<String,String> getShipPosition(int shipId) {
        return this.playerBattlefield.getShipPosition(shipId);
    }
}