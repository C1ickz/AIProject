/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_05_TicTacToe;

import ai_04_RouteFinder.*;
import ai_03_8puzzle.*;
import ai_02_MandC.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan
 */
public abstract class Problem {    
    
    
    public Problem(State initialState, State goalState){
        
    }
    
    public abstract State getInitialState();
    
    public abstract  List<Action> actions(State s);
    
    public  abstract  State result(State s, Action a);
    
    public abstract boolean isGoalState(State s);
    
    public abstract int step_cost(State state, Action action);
    
    public abstract void setEnvironment(String data);
    
    public abstract void visualizePuzzle(State state);
    
     public abstract String visualizePuzzletoString(State state);
     
    public abstract boolean terminalTest(State state);
    
    public abstract Action miniMax(State state);
    
    public abstract int utility(State s, String player);
    
}
