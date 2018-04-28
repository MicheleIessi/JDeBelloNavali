package com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.CellGrid;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.PlaceShip.Stage.zoneStage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GridStage extends zoneStage {


    //Game grid
    private ArrayList<ArrayList<CellGrid>> grid;
    private ArrayList<CellGrid> lastCells;

    private Table fleetWeightTable;
    private Table gridTable;

    public enum orientation{
        Horizontal_Right,Horizontal_Left,
        Vertical_Up,Vertical_Down
    }

    private orientation standardOrientation=orientation.Vertical_Down;

    public GridStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);
        fleetWeightTable= new Table();
        gridTable= new Table();

        grid= new ArrayList<>(8);
        for(int i=0; i<8; i++) {
            grid.add(new ArrayList<>(8));
        }


    }


    @Override
    public void setUpLayout() {
        zoneTable.add(fleetWeightTable).width(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_WIDTH).height(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_HEIGHT);
        zoneTable.row();
        zoneTable.add(gridTable).width(ConstantsPlaceShips.GRID_TABLE_WIDTH).height(ConstantsPlaceShips.GRID_TABLE_HEIGHT);
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRID_ZONE_WIDTH).height(ConstantsPlaceShips.GRID_ZONE_HEIGHT);


        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                grid.get(i).add(new CellGrid(i,j,this));
                gridTable.addActor(grid.get(i).get(j));
            }
        }



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

    private ArrayList<CellGrid> getCells(int x,int y,int dim, orientation o){
        Method method;

            try {
                method =this.getClass().getDeclaredMethod("getNext"+o,int.class,int.class,int.class );
                return (ArrayList<CellGrid>) method.invoke(this,x,y,dim);

            } catch (SecurityException e) { return null; }
            catch (NoSuchMethodException e) {
                System.out.println("Method not found...");
                return null;
            }
            catch (IllegalAccessException e){ return null;}
            catch(InvocationTargetException e){return null;}


    }

    public void showPreview(int x, int y, int dim){
        lastCells=getCells(x,y,dim,standardOrientation);
        for (CellGrid cell:lastCells
             ) {
                cell.setState(CellGrid.CellState.NOT_EMPTY);
        }



    }

    public void hidePreview(){

        for (CellGrid cell:lastCells
                ) {
            cell.setState(CellGrid.CellState.EMPTY);
        }
    }


    public ArrayList<CellGrid> getNextHorizontal_Right(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            cells.add(grid.get(x+i).get(y));
        }
       return cells;
    }

    public ArrayList<CellGrid> getNextHorizontal_Left(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            cells.add(grid.get(x-i).get(y));
        }
        return cells;
    }

    public ArrayList<CellGrid> getNextVertical_Up(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            cells.add(grid.get(x).get(y+i));
        }
        return cells;
    }
    public ArrayList<CellGrid> getNextVertical_Down(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            cells.add(grid.get(x).get(y-i));
        }
        return cells;
    }

}
