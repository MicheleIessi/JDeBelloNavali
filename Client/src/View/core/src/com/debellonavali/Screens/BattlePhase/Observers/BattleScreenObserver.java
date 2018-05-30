package com.debellonavali.Screens.BattlePhase.Observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Screens.BattlePhase.BattlePhaseScreen;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;
import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.PlaceShip.CellGrid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class BattleScreenObserver implements IScreenObserver {

    @Override
    public void update(IDTO dto, INotifiableScreen notifiableScreen) {
        String function = dto.getFunctionString();
        Method method;
        System.out.println("Messaggio ricevuto: " + function);
        try {
            method = this.getClass().getDeclaredMethod(function + "Handler", IDTO.class, INotifiableScreen.class);
            method.invoke(this, dto, notifiableScreen);

        } catch (SecurityException e) {
            System.out.println("Security exception");
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found...");
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access exception");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /*I'm the attacker so i Have to update the enemy field
     *
     *
     */
    private void attackResultHandler(IDTO dto, INotifiableScreen notifiableScreen) {
        // accedi alla table di notifiableScreen
        // modifica le square della table in base al dto

        Map<int[], Boolean> attackResults = (Map<int[], Boolean>) dto.getObjectFromMap("AttackResults");

        //Get the enemy field
        ArrayList<ArrayList<CellGrid>> enemyGrid = ((BattlePhaseScreen) notifiableScreen).getEnemyField();
        for (Map.Entry<int[], Boolean> attackedSquare : attackResults.entrySet()) {
            int x = attackedSquare.getKey()[0];
            int y = attackedSquare.getKey()[1];
            boolean outcome = attackedSquare.getValue();
            CellGrid cellTarget = enemyGrid.get(x).get(y);

            if (outcome) {
                //I have hit something here for the first time
                cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.HIT);
                cellTarget.setState(CellGrid.CellState.HIT);
            } else {
                //Either I have not hit something here or I've already hit this cell
                //I have already hit this cell
                if (cellTarget.getState() == CellGrid.CellState.HIT) {
                    System.out.println("already hit");
                    cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.ALREADY_HIT);
                }
                //this is the first time but the cell is empty
                else {
                    System.out.println("hole in water");
                    cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.WATER_HOLE);
                }

            }
        }
    }

    /**
     * This method is used by the defender to update its screen
     *
     * @param dto              The DTO object containing information about the received attack
     * @param notifiableScreen The screen that has to be modified to show the outcome of the attack
     */
    private void defenceResultHandler(IDTO dto, INotifiableScreen notifiableScreen) {
        Map<int[], Boolean> attackResults = (Map<int[], Boolean>) dto.getObjectFromMap("AttackResults");
        ArrayList<ArrayList<CellGrid>> playerGrid = ((BattlePhaseScreen) notifiableScreen).getPlayerField();
        for (Map.Entry<int[], Boolean> attackedSquare : attackResults.entrySet()) {
            int x = attackedSquare.getKey()[0];
            int y = attackedSquare.getKey()[1];
            CellGrid cellTarget = playerGrid.get(x).get(y);
            cellTarget.showPreviewBattle(CellGrid.previewStyleBattle.ALREADY_HIT);
            cellTarget.setState(CellGrid.CellState.HIT);
        }
    }


    private void attackerWinHandler(IDTO dto, INotifiableScreen notifiableScreen) {
        notifiableScreen.showEndGameDialog(true);
    }

    private void defenderLoseHandler(IDTO dto, INotifiableScreen notifiableScreen) {
        notifiableScreen.showEndGameDialog(false);
    }

}
