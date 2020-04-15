/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_04_RouteFinder;

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
    

    
    //public abstract void setEnvironment(String data);
    
    //public abstract String getEnvironmentDisplayString(String[][] data);
    
    
    //public abstract void testActions();
   // public abstract void testResult();
    //public abstract void testGoalTest();
}
