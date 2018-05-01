package com.debellonavali.PlaceShip;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GridChecker {

    private ArrayList<ArrayList<CellGrid>> grid;
    private ArrayList<ArrayList<CellGrid>> invalidCells;
    private ArrayList<CellGrid> validCells;
    private Orientation lastOrientation;

    private Orientation standardOrientation=Orientation.Horizontal_Right;




    public enum ERROR_MESSAGE{
        OUT_OF_BOUNDARY,
        OVERLAP_SHIP,
        OVER_WEIGHT
    }

    public enum Orientation {
        Horizontal_Right,Horizontal_Left,
        Vertical_Up,Vertical_Down
    }

    public GridChecker(ArrayList<ArrayList<CellGrid>> grid) {
        this.grid = grid;
    }


    public ArrayList<ArrayList<CellGrid>> getPreviewInvalidCells(int x, int y, int dim){

        invalidCells= new ArrayList<>();
        Orientation orientation=standardOrientation;
        int count=0;
        do {

            invalidCells.add(getCells(x,y,dim,orientation));
            orientation=nextOrientation(orientation);
            count++;
        }while (!isPlaceable(invalidCells.get(invalidCells.size()-1),dim) && count<5);

        lastOrientation=prevOrientation(orientation);
        invalidCells.remove(invalidCells.size()-1);

        return invalidCells;

    }

    public ArrayList<CellGrid> getPreviewValidCells(int x, int y, int dim){


        validCells = getCells(x,y,dim,lastOrientation);
        if (!isPlaceable(validCells,dim)){
            validCells=null;
        }

        return validCells;
    }

    public ArrayList<CellGrid> getPreviewedValidCells() {
        return this.validCells;
    }

    public ArrayList<ArrayList<CellGrid>> getPreviewedInvalidCells() {
        return invalidCells;
    }


    private boolean isPlaceable(ArrayList<CellGrid> cells,int dim){

        if (!isOutOfBoundary(cells,dim))
                if ( !isOverlap(cells))
                    return true;

        return false;
    }

    private boolean isOverlap(ArrayList<CellGrid> c){
        for (CellGrid cell: c) {
            if ( cell.getState()==CellGrid.CellState.NOT_EMPTY)
                return true;
        }
        return false;
    }

    private  boolean  isOutOfBoundary(ArrayList<CellGrid> c,int dim){
        if (c.size()<dim)
            return true;
        else
            return false;
    }



    public ArrayList<ArrayList<CellGrid>> getInvalidCells() {
        return invalidCells;
    }


    public ArrayList<CellGrid> getCells(int x, int y, int dim, Orientation o){
        Method method;

        try {
            method =this.getClass().getDeclaredMethod("getNext"+o,int.class,int.class,int.class );
            return  (ArrayList<CellGrid>) method.invoke(this,x,y,dim);

        } catch (SecurityException e) { return null; }
        catch (NoSuchMethodException e) {
            System.out.println("Method not found...");
            return null;
        }
        catch (IllegalAccessException e){ return null;}
        catch(InvocationTargetException e){return null;}



    }

    private ArrayList<CellGrid> getNextHorizontal_Right(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            if (!(x+i>7)){
                cells.add(grid.get(x+i).get(y));
            }


        }
        return cells;
    }

    private ArrayList<CellGrid> getNextHorizontal_Left(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            if (!(x-i<0)){
                cells.add(grid.get(x-i).get(y));
            }


        }
        return cells;
    }

    private ArrayList<CellGrid> getNextVertical_Up(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            if (!(y+1>7)){
                cells.add(grid.get(x).get(y+i));
            }

        }
        return cells;
    }

    private ArrayList<CellGrid> getNextVertical_Down(int x, int y, int dim){
        ArrayList<CellGrid>cells=new ArrayList<>(dim);
        for (int i=0;i<dim;i++){
            if (!(y-i<0)){
                cells.add(grid.get(x).get(y-i));
            }

        }
        return cells;
    }

    private Orientation nextOrientation(Orientation orientation){
        if (orientation==Orientation.Horizontal_Right)
            return Orientation.Vertical_Down;
            else if (orientation== Orientation.Vertical_Down)
                return Orientation.Horizontal_Left;
                else if (orientation==Orientation.Horizontal_Left)
                    return Orientation.Vertical_Up;
                    else if (orientation==Orientation.Vertical_Up)
                        return Orientation.Horizontal_Right;
                    else return standardOrientation;
    }

    private Orientation prevOrientation(Orientation orientation){
        if (orientation==Orientation.Horizontal_Right)
            return Orientation.Vertical_Up;
        else if (orientation== Orientation.Vertical_Up)
            return Orientation.Horizontal_Left;
        else if (orientation==Orientation.Horizontal_Left)
            return Orientation.Vertical_Down;
        else if (orientation==Orientation.Vertical_Down)
            return Orientation.Horizontal_Right;
        else return standardOrientation;
    }

}
