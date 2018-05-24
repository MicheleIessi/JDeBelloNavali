package com.debellonavali.Screens.BattlePhase.Observers;

import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Screens.BattlePhase.BattlePhaseScreen;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;
import com.debellonavali.Screens.PlaceShip.CellGrid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class BattleScreenObserver implements IScreenObserver {

    @Override
    public void update(IDTO dto, INotifiableScreen notifiableScreen) {
        String function=dto.getFunctionString();
        Method method;

        try {
            method =this.getClass().getDeclaredMethod(function+"Handler",IDTO.class,INotifiableScreen.class );
           method.invoke(this,dto,notifiableScreen);

        } catch (SecurityException e) { System.out.println("Security exception"); }
        catch (NoSuchMethodException e) {
            System.out.println("Method not found...");

        }
        catch (IllegalAccessException e){System.out.println("Illegal access exception"); }
        catch(InvocationTargetException e){System.out.println("Invocation target exception");}

    }


    /*I'm the attacker so i Have to update the enemy field
     *
     *
     */
    private void attackResultHandler(IDTO dto, INotifiableScreen notifiableScreen){
        // accedi alla table di notifiableScreen
        // modifica le square della table in base al dto

        Map<int[], Boolean> attackResults = (Map<int[], Boolean>) dto.getObjectFromMap("AttackResults");

        //Get the enemy field
        ArrayList<ArrayList<CellGrid>> enemyGrid = ((BattlePhaseScreen) notifiableScreen).getEnemyField();
        for (Map.Entry<int[], Boolean> attackedSquare : attackResults.entrySet()) {
            int x = attackedSquare.getKey()[0];
            int y = attackedSquare.getKey()[1];
            boolean outcome = attackedSquare.getValue();
            CellGrid cellTarget=  enemyGrid.get(x).get(y);

            if (outcome)
            {
                //I have hit something here for the first time

                cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.HIT);
                cellTarget.setState(CellGrid.CellState.HIT);
            }
            else
            {
                //Either I have not hit something here or I've already hit this cell

                //I have already hit this cell
                if (cellTarget.getState()==CellGrid.CellState.HIT){
                    System.out.println("already hit");
                    cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.ALREADY_HIT);

                }
                //this is the first time but the cell is empty
                else
                {
                    System.out.println("whole in water");
                    cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.WATER_HOLE);
                }

            }
        }

    }

    /*I'm the defender so i Have to update the player field
     *
     */
    private void defenceResultHandler(IDTO dto, INotifiableScreen notifiableScreen){

    }


    private void attackerWinHandler(IDTO dto, INotifiableScreen notifiableScreen){

    }

    private void defenderLoseHandler(IDTO dto, INotifiableScreen notifiableScreen){

    }

}
