package com.debellonavali.Classes.Communicator.DTO.DTOBuilder;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

import java.util.List;
import java.util.Map;

public interface IDTOBuilder {

    IDTO createAttackDTO(List<int[]> attackedSquares);
    IDTO createAttackResultsDTO(Map<int[], Boolean> attackResults);
}
