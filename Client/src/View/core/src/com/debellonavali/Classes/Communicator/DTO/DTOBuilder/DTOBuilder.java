package com.debellonavali.Classes.Communicator.DTO.DTOBuilder;

import com.debellonavali.Classes.Communicator.DTO.DTO;
import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Communicator.DTO.IDTO;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DTOBuilder implements IDTOBuilder {

    private static DTOBuilder instance = null;

    @Override
    public IDTO createAttackDTO(List<int[]> attackedSquares) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack DTO");
        DTO attackDTO = new DTO(DTOMessages.ATTACK_MESSAGE);
        attackDTO.addObjectToMap("AttackedSquares", attackedSquares);
        return attackDTO;
    }

    @Override
    public IDTO createAttackResultsDTO(Map<int[], Boolean> attackResults) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack result DTO");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACK_RESULT_MESSAGE);
        attackResultsDTO.addObjectToMap("AttackResults", attackResults);
        return attackResultsDTO;
    }



    public static  DTOBuilder getInstance() {
        if (instance == null) {
            instance = new DTOBuilder();
        }
        return instance;
    }
}
