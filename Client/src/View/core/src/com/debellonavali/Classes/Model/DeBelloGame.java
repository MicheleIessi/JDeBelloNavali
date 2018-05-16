package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.*;
import com.debellonavali.Classes.Model.Factories.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DeBelloGame {

    private Battlefield playerBattlefield;
    private static FacadeClientController facadeController;
    private static DeBelloGame instance;
    private Logger logger;

    public DeBelloGame() {
        playerBattlefield = new Battlefield();
        logger = Logger.getLogger(DeBelloGame.class.getSimpleName());
    }

    public void attachFacadeController(FacadeClientController facadeClientController) {
        facadeController = facadeClientController;
        logger.info("Facade Controller Attached");
    }

    public Battlefield getPlayerBattlefield() {
        return playerBattlefield;
    }

    public void attack(int shipID, int weaponID, int[] position) {
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

    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }
}