package com.debellonavali.Classes.Communicator.DTO.DTOBuilder;

import com.debellonavali.Classes.Communicator.DTO.DTO;
import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Communicator.DTO.IDTO;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class responsible to create different DTO Objects based on their purpose
 */
public class DTOBuilder implements IDTOBuilder {

    private static DTOBuilder instance = null;

    /**
     * Creates a DTO that contains an attack. It is used by an attacker that wants to attack
     * @param attackedSquares List of attacked coordinates
     * @return An Attack DTO
     */
    @Override
    public IDTO createAttackDTO(List<int[]> attackedSquares) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack DTO");
        DTO attackDTO = new DTO(DTOMessages.ATTACK_MESSAGE);
        attackDTO.addObjectToMap("AttackedSquares", attackedSquares);
        return attackDTO;
    }

    /**
     * Creates a DTO that contains an attack's result. It is used by a defender after receiving an attack
     * @param attackResults Map of attack results
     * @return An AttackResult DTO
     */
    @Override
    public IDTO createAttackResultsDTO(Map<int[], Boolean> attackResults) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack result DTO for who has attacked");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACK_RESULT_MESSAGE_ATTACKER);
        attackResultsDTO.addObjectToMap("AttackResults", attackResults);
        return attackResultsDTO;
    }

    /**
     * Creates a DTO that contains a defence result. It is used by a defender to update its View in order to
     * visualize a received attack
     * @param defenceResult Map of attack results
     * @return A DefenceResult DTO
     */
    @Override
    public IDTO createDefenceResultsDTO(Map<int[], Boolean> defenceResult) {
        Logger.getLogger(getClass().getSimpleName()).info("Created attack result DTO for who has defended");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACK_RESULT_MESSAGE_DEFENDER);
        attackResultsDTO.addObjectToMap("AttackResults", defenceResult);
        return attackResultsDTO;
    }

    /**
     * Creates a DTO that will be recognized as a game win
     * @param attackerWin Map that contains a game win information
     * @return An AttackWin DTO
     */
    @Override
    public IDTO createAttackWinDTO(Map<int[], Boolean> attackerWin) {
        Logger.getLogger(getClass().getSimpleName()).info("Created WIN DTO for attacker ");
        DTO attackResultsDTO = new DTO(DTOMessages.ATTACKER_WIN);
        attackResultsDTO.addObjectToMap("AttackResults", attackerWin);
        return attackResultsDTO;
    }

    /**
     * Creates a DTO that will be recognized as a game loss
     * @param defenderLose Map that contains a game loss information
     * @return A DefenderLose DTO
     */
    @Override
    public IDTO createDefenceLoseDTO(Map<int[], Boolean> defenderLose) {
        Logger.getLogger(getClass().getSimpleName()).info("Created LOSE DTO for defender ");
        DTO attackResultsDTO = new DTO(DTOMessages.DEFENDER_LOSE);
        attackResultsDTO.addObjectToMap("AttackResults", defenderLose);
        return attackResultsDTO;

    }

    /**
     * Returns the only instance allowed to be instantiated
     * @return The DTOBuilder instance
     */
    public static  DTOBuilder getInstance() {
        if (instance == null) {
            instance = new DTOBuilder();
        }
        return instance;
    }
}
