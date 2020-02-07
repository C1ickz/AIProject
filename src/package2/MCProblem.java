/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package2;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class MCProblem {
    private State initialState;
    private State goalState;
    
    public MCProblem(State initialState, State goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        
    }
        
    public State getInitialState(){
        return initialState;
    }
    
    public int pathCost(Node node){
        return node.getPathCost();
    }
    
    public int step_cost(State state, Action action){
        return 1;
    }
    
    private void showStateAndAction(State state)
    {
       // ArrayList<Action> allActions = actiosn(state);
    }
       


public ArrayList<Action> actions(State s){
           
        int leftMissonaries = Integer.parseInt(""+s.toString().charAt(0));
        int leftCannibals = Integer.parseInt(""+s.toString().charAt(1));
        int leftBoat = Integer.parseInt(""+s.toString().charAt(2));
        int rightBoat = Integer.parseInt(""+s.toString().charAt(3));
        int rightMissonaries = Integer.parseInt(""+s.toString().charAt(4));
        int rightCannibals = Integer.parseInt(""+s.toString().charAt(5));
        ArrrayList<Action> theResult = new ArrayList<>();
        Action tempAction = null;
        
        if(leftBoat ==1 && rightBoat == 0){
            
            
        }
        else if(leftBoat ==0 && rightBoat == 1)
            
            return null;
}

    public static State result(State s, Action a){
        if(a == null || a.toString() == null){
            return s;
        }
        
        int leftMissonaries = Integer.parseInt(""+s.toString().charAt(0));
        int leftCannibals = Integer.parseInt(""+s.toString().charAt(1));
        int leftBoat = Integer.parseInt(""+s.toString().charAt(2));
        int rightBoat = Integer.parseInt(""+s.toString().charAt(3));
        int rightMissonaries = Integer.parseInt(""+s.toString().charAt(4));
        int rightCannibals = Integer.parseInt(""+s.toString().charAt(5));
        
        int numMissonariesToMove= Integer.parseInt(""+a.toString().charAt(4));
        int numCannibalsToMove= Integer.parseInt(""+a.toString().charAt(5));
        
        
        if(leftBoat == 1){
            leftMissonaries -= numMissonariesToMove;
            leftCannibals -= numCannibalsToMove;
            //same with cannibals
            leftBoat = 0;
            rightBoat = 1;
            rightMissonaries += numMissonariesToMove;
            rightCannibals += numCannibalsToMove;
        }

        else if(rightBoat ==1){
            leftMissonaries += numMissonariesToMove;
            leftCannibals += numCannibalsToMove;
            //same with cannibals
            leftBoat = 1;
            rightBoat = 0;
            rightMissonaries -= numMissonariesToMove;
            rightCannibals -= numCannibalsToMove;
        }
              
        String data = Integer.toString(leftMissonaries) + Integer.toString(leftCannibals) +  Integer.toString(leftBoat) +
                Integer.toString(rightBoat) + Integer.toString(rightMissonaries) + Integer.toString(rightCannibals);
      
      if((leftMissonaries < 0 && leftMissonaries<leftCannibals) || (rightMissonaries <0 && rightMissonaries > rightCannibals)){
          return null;
      }
  
        
        return new State(data);



      
    }
    public void tryOneGoalTest(String stateString){
        State state = new State(stateString);
        boolean goalReached = goalTest(state);
        System.out.println("state.toString() " + state.toString());
        System.out.println("goal reached " + goalReached + "\n=========");

    }
    public void testGoalTest(){
        tryOneGoalTest("000133");
        tryOneGoalTest("331000");
        tryOneGoalTest("321001");
    }
    
    
    public boolean goalTest(State s){
        return s.toString().equals(goalState.toString());
      
    }
    
    @Override
    public String toString(){
        return "Initial State " + initialState + " Goal state " + goalState;
    }
    
    
    
    public static void main(String[] args){
        System.out.println("test main for ProblemMC class");
        State startState = new State("331000");
        State goalState = new State("000133");
        MCProblem testProblem = new MCProblem(startState, goalState);
        System.out.println(testProblem.toString());
        
        State initial = testProblem.getInitialState();
        System.out.println("The initial state is " + initial);
        //testProblem.testActions();
        //testProblem.testResult();
        testProblem.testGoalTest();
        System.out.println("End of test for ProblemMC class");
        State state = result(startState, new Action("row#10"));
        System.out.println(state.toString());
    }
}
