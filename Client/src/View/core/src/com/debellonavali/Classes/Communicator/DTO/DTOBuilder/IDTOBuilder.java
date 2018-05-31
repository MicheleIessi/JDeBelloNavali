package com.debellonavali.Classes.Communicator.DTO.DTOBuilder;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

import java.util.List;
import java.util.Map;

/**
 * Interface that a DTOBuilder class must implement
 */
public interface IDTOBuilder {

    IDTO createAttackDTO(List<int[]> attackedSquares);
    IDTO createAttackResultsDTO(Map<int[], Boolean> attackResults);
    IDTO createDefenceResultsDTO(Map<int [], Boolean> defenceResult);
    IDTO createAttackWinDTO(Map<int [], Boolean> defenseResult);
    IDTO createDefenceLoseDTO(Map<int [], Boolean> defenseResult);


}
