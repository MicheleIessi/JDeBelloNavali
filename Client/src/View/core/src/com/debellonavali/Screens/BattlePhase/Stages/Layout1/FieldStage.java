package com.debellonavali.Screens.BattlePhase.Stages.Layout1;

import com.debellonavali.Classes.Model.Battlefield;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;

import com.debellonavali.Screens.BattlePhase.Stages.Layout1.FieldStageDescendant.EnemyField;
import com.debellonavali.Screens.BattlePhase.Stages.Layout1.FieldStageDescendant.PlayerField;
import com.debellonavali.Screens.zoneStage;

import java.util.HashMap;


public class FieldStage extends zoneStage {


    private EnemyField enemyField;
    private PlayerField playerField;
    private DeBelloGame deBelloGame;

   public FieldStage (zoneStage parent, DeBelloGame dbg){
       super(parent);

       zoneTable.pad(0);

       this.deBelloGame = dbg;
       enemyField= new EnemyField(this, deBelloGame);
       playerField= new PlayerField(this, deBelloGame);

       stages.add(enemyField);
       stages.add(playerField);

   }


    @Override
    public void setUpLayout() {


        for (zoneStage stage : stages) {
            stage.setUpLayout();
            zoneTable.row();
        }

        super.setZoneTableDimension(ConstantsBattlePhase.FIELD_TABLE_WIDTH,ConstantsBattlePhase.FIELD_TABLE_HEIGHT);
    }

    public void setFleet(HashMap fleet) {

       playerField.setFleet(fleet);
    }
}
