package com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Screens.PlaceShip.CellGrid;
import com.debellonavali.Screens.Util.DragNDrop;
import com.debellonavali.Screens.zoneStage;

import com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips;

import  com.debellonavali.Screens.PlaceShip.GridChecker;

import java.util.ArrayList;
import java.util.Map;

public class GridStage extends zoneStage {


    //Game grid
    private ArrayList<ArrayList<CellGrid>> grid;

    private com.debellonavali.Screens.PlaceShip.GridChecker checker;


    private Table fleetWeightTable;
    private Table gridTable;





    public GridStage(zoneStage parent) {
        super(parent);

        zoneTable.pad(0);
        fleetWeightTable= new Table();
        gridTable= new Table();

        grid= new ArrayList<>(8);
        for(int i=0; i<8; i++) {
            grid.add(new ArrayList<>(8));
        }

        checker= new GridChecker(grid);



    }


    @Override
    public void setUpLayout() {
        zoneTable.add(fleetWeightTable).width(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_WIDTH).height(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_HEIGHT);
        zoneTable.row();
        zoneTable.add(gridTable).width(ConstantsPlaceShips.GRID_TABLE_WIDTH).height(ConstantsPlaceShips.GRID_TABLE_HEIGHT);
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRID_ZONE_WIDTH).height(ConstantsPlaceShips.GRID_ZONE_HEIGHT);


        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                grid.get(i).add(new CellGrid(i,j,this,ConstantsPlaceShips.GRID_TABLE_WIDTH));
                gridTable.addActor(grid.get(i).get(j));
            }
        }

        setUpDrop();



    }

    public ArrayList<ArrayList<CellGrid>> getGrid() {
        return grid;
    }

    public Table getFleetWeightTable() {
        return fleetWeightTable;
    }

    public Table getGridTable() {
        return gridTable;
    }


    public void showPreview(int x, int y, int dim){
        ArrayList<ArrayList<CellGrid>> invalidCells=checker.getPreviewInvalidCells(x,y,dim);
        ArrayList<CellGrid> validCells=checker.getPreviewValidCells(x,y,dim);


        if (invalidCells!=null){
            for (ArrayList<CellGrid> position:invalidCells) {
                for (CellGrid cell:position) {
                    cell.showPreviewPlacement(CellGrid.previewStylePlacement.NOT_VALID);
                }
            }
        }


        if (validCells!=null){
            for (CellGrid cell:validCells){
                cell.showPreviewPlacement(CellGrid.previewStylePlacement.VALID);
            }
        }



    }


    public void hidePreview(){
        ArrayList<ArrayList<CellGrid>> invalidCells=checker.getPreviewedInvalidCells();
        ArrayList<CellGrid> validCells=checker.getPreviewedValidCells();


        if (invalidCells!=null){
            for (ArrayList<CellGrid> position:invalidCells) {
                for (CellGrid cell:position) {
                    cell.hidePreviewPlacement();
                }
            }
        }


        if (validCells!=null){
            for (CellGrid cell:validCells){
                cell.hidePreviewPlacement();
            }
        }

    }





    private void setUpDrop() {

        DragNDrop dragAndDrop=DragNDrop.getInstance();
        //set each cell of the grid as droppable
        for (ArrayList<CellGrid> cells:grid
                ) {
            for (CellGrid cell: cells){
                dragAndDrop.addTarget(new DragAndDrop.Target(cell) {
                    CellGrid cellTarget=(CellGrid)getActor();
                    //called when the payload is over the target
                    public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        Map<String,String> pack=(Map)payload.getObject();

                        showPreview(cellTarget.getXCoordinate(),cellTarget.getYCoordinate(),Integer.parseInt((pack.get("dim"))));
                        return true;
                    }
                    //called when the payload is no longer over the target
                    public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {

                        hidePreview();
                    }
                    // called when the payload is dropped on the target
                    public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        Map<String,String> pack=(Map)payload.getObject();
                        ArrayList<CellGrid>cells=checker.getPreviewedValidCells();
                        int count=1;

                        if (cells!=null){
                            for (CellGrid cell: cells
                                    ) {
                                cell.dropShip(pack,count);
                                count++;
                            }

                        }

                    }
                });
            }

        }

    }



}
