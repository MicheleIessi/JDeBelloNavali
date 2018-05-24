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
        Logger.getLogger(getClass().getSimpleName()).info("Created attack result DTO for who has attacked");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACK_RESULT_MESSAGE_ATTACKER);
        attackResultsDTO.addObjectToMap("AttackResults", attackResults);
        return attackResultsDTO;
    }

    @Override
    public IDTO createDefenceResultsDTO(Map<int[], Boolean> defenceResult) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack result DTO for who has defended");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACK_RESULT_MESSAGE_DEFENDER);
        attackResultsDTO.addObjectToMap("AttackResults", defenceResult);
        return attackResultsDTO;
    }

    @Override
    public IDTO createAttackWinDTO(Map<int[], Boolean> attackerWin) {
        Logger.getLogger(getClass().getSimpleName()).info("Created WIN DTO for attacker ");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACKER_WIN);
        attackResultsDTO.addObjectToMap("AttackResults", attackerWin);
        return attackResultsDTO;
    }

    @Override
    public IDTO createDefenceLoseDTO(Map<int[], Boolean> defenderLose) {
        Logger.getLogger(getClass().getSimpleName()).info("Created LOSE DTO for defender ");
        DTO attackResultsDTO = new DTO(DTOMessages.DEFENDER_LOSE);
        attackResultsDTO.addObjectToMap("AttackResults", defenderLose);
        return attackResultsDTO;

    }

    public static  DTOBuilder getInstance() {
        if (instance == null) {
            instance = new DTOBuilder();
        }
        return instance;
    }
}
