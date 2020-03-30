/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_04_RouteFinder;

import ai_04_RouteFinder.Action;
import ai_04_RouteFinder.Problem;
import ai_04_RouteFinder.State;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class RouteFinderProblem extends Problem{
    private State initialState;
    private State goalState;
    
    public RouteFinderProblem(State initialState, State goalState){
        super(initialState, goalState);
        this.initialState = initialState;
        this.goalState = goalState;
    }

    @Override
    public State getInitialState() {
        return initialState;
    }
    

    @Override
    public List<Action> actions(State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State result(State s, Action a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGoalState(State s) {
        return s.toString().equals(this.goalState.toString());
    }

    @Override
    public int step_cost(State state, Action action) {
        return 1;
    }

    @Override
    public void visualizePuzzle(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        
    }
    
}
