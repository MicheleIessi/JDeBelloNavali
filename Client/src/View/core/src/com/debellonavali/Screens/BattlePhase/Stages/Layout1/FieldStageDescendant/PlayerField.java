package com.debellonavali.Screens.BattlePhase.Stages.Layout1.FieldStageDescendant;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Classes.Model.Ship;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.PlaceShip.CellGrid;
import com.debellonavali.Screens.PlaceShip.GridChecker;
import com.debellonavali.Screens.zoneStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PlayerField extends zoneStage {

    private ArrayList<ArrayList<CellGrid>> playerGrid;

    private DeBelloGame deBelloGame;

    private Table playerGridTable;


    public PlayerField(zoneStage parent, DeBelloGame dbg) {
        super(parent);
        this.deBelloGame = dbg;
        zoneTable.pad(0);

        playerGridTable = new Table();
        playerGridTable.pad(0);

        playerGrid = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            playerGrid.add(new ArrayList<>(8));
        }
    }

    @Override
    public void setUpLayout() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                playerGrid.get(i).add(new CellGrid(i, j, this, ConstantsBattlePhase.PLAYER_GRID_TABLE_WIDTH));
                playerGridTable.addActor(playerGrid.get(i).get(j));
            }
        }
        zoneTable.add(playerGridTable).expand().left().width(ConstantsBattlePhase.PLAYER_GRID_TABLE_WIDTH).height(ConstantsBattlePhase.PLAYER_GRID_TABLE_HEIGHT);
        super.setZoneTableDimension(ConstantsBattlePhase.PLAYER_FIELD_TABLE_WIDTH, ConstantsBattlePhase.PLAYERFIELD_TABLE_HEIGHT);
    }

    /* Place the ships in the player grid
     *
     *
     */
    public void setFleet(HashMap<Integer, Ship> fleet) {
        /*Come ricava le informazioni a partire dallo ship id?
         * ovvero le giuste texture ect ect
         */
        GridChecker checker = new GridChecker(this.playerGrid);

        for (Map.Entry<Integer, Ship> entry : fleet.entrySet()) {
            int shipId = entry.getKey();
            Ship ship = entry.getValue();

            //TODO: come ottengo questa informazione??
            String civil = "RomanFleet";

            //TODO: dovrebbe ritornare battlefield oppure richiamare un metodo come adesso??
            HashMap<String, String> shipInfo = deBelloGame.getShipPosition(shipId);

            String[] position = shipInfo.get("position").split("-");
            int x = Integer.parseInt(position[0]);
            int y = Integer.parseInt(position[1]);

            ArrayList<CellGrid> cells = checker.getCells(y, x, ship.getDimension(), GridChecker.Orientation.Horizontal_Right);

            int count = 1;

            if (cells != null) {
                for (CellGrid cell : cells) {
                    cell.dropShip(civil, ship.getShipName(), count);
                    count++;
                }
            }
        }
    }

    public ArrayList<ArrayList<CellGrid>> getPlayerField() {
        return this.playerGrid;
    }
}

