package com.debellonavali.Screens.BattlePhase.Stages.Layout1.FieldStageDescendant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.PlaceShip.CellGrid;
import com.debellonavali.Screens.Util.DragNDrop;
import com.debellonavali.Screens.zoneStage;

import java.util.ArrayList;
import java.util.Map;

public class EnemyField extends zoneStage {

    private ArrayList<ArrayList<CellGrid>> enemyGrid;

    private Table enemyGridTable;
    private DeBelloGame deBelloGame;

    public EnemyField(zoneStage parent, DeBelloGame dbg){

        super(parent);

        this.deBelloGame=dbg;
        zoneTable.pad(0);

        enemyGridTable= new Table();
        enemyGridTable.pad(0);

        enemyGridTable.padRight(20);





        enemyGrid= new ArrayList<>(8);
        for(int i=0; i<8; i++) {
            enemyGrid.add(new ArrayList<>(8));
        }

    }



    @Override
    public void setUpLayout() {

        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                enemyGrid.get(i).add(new CellGrid(i,j,this,ConstantsBattlePhase.ENEMY_GRID_TABLE_WIDTH));
                enemyGridTable.addActor(enemyGrid.get(i).get(j));
            }
        }
        zoneTable.add(enemyGridTable).expand().right().width(ConstantsBattlePhase.ENEMY_GRID_TABLE_WIDTH).height(ConstantsBattlePhase.ENEMY_GRID_TABLE_HEIGHT);
        super.setZoneTableDimension(ConstantsBattlePhase.ENEMYFIELD_TABLE_WIDTH,ConstantsBattlePhase.ENEMYFIELD_TABLE_HEIGHT);

        setUpDroppable();
    }

    private void setUpDroppable() {
        DragNDrop dragAndDrop=DragNDrop.getInstance();
        //set each cell of the grid as droppable
        for (ArrayList<CellGrid> cells:enemyGrid
                ) {
            for (CellGrid cell: cells){
                dragAndDrop.addTarget(new DragAndDrop.Target(cell) {
                    CellGrid cellTarget=(CellGrid)getActor();
                    //called when the payload is over the target
                    public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        Map<String,String> pack=(Map)payload.getObject();

                        cellTarget.showPreviewPlacement(CellGrid.previewStylePlacement.VALID);
                        return true;
                    }
                    //called when the payload is no longer over the target
                    public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {

                        cellTarget.hidePreviewPlacement();
                    }
                    // called when the payload is dropped on the target
                    public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {


                        //check if it's my turn
                        if (deBelloGame.isPlayerTurn())
                        {
                            Map<String,Integer> pack=(Map)payload.getObject();
                            int position[]={cellTarget.getXCoordinate(),cellTarget.getYCoordinate()};
                            int shipID=pack.get("shipID");
                            int weaponID=pack.get("weaponID");
                            //it's my turn i can attack
                            deBelloGame.attack(shipID,weaponID,position);
                            deBelloGame.setPlayerTurn(false);
                        }
                        else{
                            Skin uiSkin = new Skin(Gdx.files.internal("Dialog/uiskin.json"));
                            Dialog dialog = new Dialog("Warning", uiSkin) {
                                public void result(Object obj) {
                                    System.out.println("result "+obj);
                                }
                            };
                            dialog.text("Sorry it's not your turn, please wait your enemy attack...");
                            dialog.button("Got it", false).sizeBy(50,50); //sends "false" as the result
                            dialog.show(parent.getParent());
                        }
                    }
                });
            }

        }
    }

    public ArrayList<ArrayList<CellGrid>> getEnemyField() {
        return this.enemyGrid;
    }
}
