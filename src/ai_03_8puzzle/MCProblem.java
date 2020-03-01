/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_03_8puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class MCProblem extends Problem{
    private State initialState;
    private State goalState;
    
    
    public MCProblem(State initialState, State goalState){
        super(initialState, goalState);
        this.initialState = initialState;
        this.goalState = goalState;
        
    }
        
    @Override
    public State getInitialState(){
        return initialState;
    }
    
    public int pathCost(Node node){
        return node.getPathCost();
    }
    
    @Override
    public int step_cost(State state, Action action){
        return 1;
    }
    

    public  String[] getAllLists(String[] elements, int lengthOfList )
    {


        if(lengthOfList == 1) return elements; 
        else {
            //initialize our returned list with the number of elements calculated above
            String[] allLists = new String[(int)Math.pow(elements.length, lengthOfList)];

            //the recursion--get all lists of length 3, length 2, all the way up to 1
            String[] allSublists = getAllLists(elements, lengthOfList - 1);

            //append the sublists to each element
            int arrayIndex = 0;

            for(int i = 0; i < elements.length; i++){
                for(int j = 0; j < allSublists.length; j++){
                    //add the newly appended combination to the list
                    allLists[arrayIndex] = elements[i] + allSublists[j];
                    arrayIndex++;
                }
            }
            return allLists;
        }
    }
    


    @Override
    public List<Action> actions(State s){

            int leftBoat = Integer.parseInt(""+s.toString().charAt(2));
            int rightBoat = Integer.parseInt(""+s.toString().charAt(3));
            int numMissonariesToMove;
            int numCannibalsToMove;
            String[] possibleMoves = {"0","1","2"};
            String[] combonations =  getAllLists(possibleMoves, possibleMoves.length -1);
            ArrayList<Action> theResult = new ArrayList<>();
            Action tempAction;

            if(leftBoat ==1 ){

                for(String combo: combonations){
                     
            int leftMissonaries;
            int leftCannibals;
            int rightMissonaries;
            int rightCannibals;
                    leftMissonaries = Integer.parseInt(""+s.toString().charAt(0));
                    leftCannibals = Integer.parseInt(""+s.toString().charAt(1));
                    rightMissonaries = Integer.parseInt(""+s.toString().charAt(4));
                    rightCannibals = Integer.parseInt(""+s.toString().charAt(5));
                    numMissonariesToMove = Integer.parseInt(""+combo.charAt(0));
                    numCannibalsToMove = Integer.parseInt(""+combo.charAt(1));
                    leftMissonaries -= numMissonariesToMove;
                    leftCannibals -= numCannibalsToMove;
                    rightMissonaries += numMissonariesToMove;
                    rightCannibals += numCannibalsToMove;

                    if(numMissonariesToMove + numCannibalsToMove <= 2 && numMissonariesToMove + numCannibalsToMove > 0){
                        if(leftCannibals >= 0 && leftMissonaries >= 0){
                            //System.out.println(rightMissonaries + "        " + rightCannibals);
                            if(rightMissonaries == 0 && leftMissonaries >= leftCannibals){
                               tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                               theResult.add(tempAction);
                            }
                            else if(leftMissonaries == 0 &&rightMissonaries >= rightCannibals ){
                                    tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                    theResult.add(tempAction);
                            }
                            else if(leftMissonaries >= leftCannibals &&rightMissonaries >= rightCannibals){
                                tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                theResult.add(tempAction);
                                
                            }
                       }
                    }
                }
            }
            else if(rightBoat ==1){
                for(String combo: combonations){
  
                    int leftMissonaries = Integer.parseInt(""+s.toString().charAt(0));
                    int leftCannibals = Integer.parseInt(""+s.toString().charAt(1));
                    int rightMissonaries = Integer.parseInt(""+s.toString().charAt(4));
                    //System.out.println(s.toString());
                    int rightCannibals = Integer.parseInt(""+s.toString().charAt(5));
                    numMissonariesToMove = Integer.parseInt(""+combo.charAt(0));
                    numCannibalsToMove = Integer.parseInt(""+combo.charAt(1));
                    leftMissonaries += numMissonariesToMove;
                    leftCannibals += numCannibalsToMove;
                    rightMissonaries -= numMissonariesToMove;
                    rightCannibals -= numCannibalsToMove;

                    if(numMissonariesToMove + numCannibalsToMove <= 2 && numMissonariesToMove + numCannibalsToMove > 0){
                        if(rightCannibals >= 0 && rightMissonaries >= 0){
                                       Action a = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                System.out.println(a);
                                                            System.out.println(Integer.toString(leftMissonaries) + Integer.toString(leftCannibals) + Integer.toString(rightMissonaries) + Integer.toString(rightCannibals)  );
                     
                            if(rightMissonaries == 0 && leftMissonaries >= leftCannibals){
                                    tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                    theResult.add(tempAction);
                            }
                            else if(leftMissonaries == 0 &&rightMissonaries >= rightCannibals ){
                                    tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                    theResult.add(tempAction);
                            }
                            else if(leftMissonaries >= leftCannibals &&rightMissonaries >= rightCannibals){
                                tempAction = new Action("row#" + Integer.toString(numMissonariesToMove) + Integer.toString(numCannibalsToMove));
                                 theResult.add(tempAction);
                        }
                    }
                }
            }

    }
    return theResult;

}

    @Override
    public State result(State s, Action a){
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
        boolean goalReached = isGoalState(state);
        System.out.println("state.toString() " + state.toString());
        System.out.println("goal reached " + goalReached + "\n=========");

    }
    public void testGoalTest(){
        tryOneGoalTest("000133");
        tryOneGoalTest("331000");
        tryOneGoalTest("321001");
    }
    
    
    @Override
    public boolean isGoalState(State s){
        return s.toString().equals(this.goalState.toString());
      
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
        State state = testProblem.result(startState, new Action("row#10"));
        System.out.println("================================================================================");
        List<Action> actionList = testProblem.actions(new State("331000"));
        System.out.println("Possible actions for state 331000 " + Arrays.toString(actionList.toArray()));
        
        actionList = testProblem.actions(new State("110122"));
        System.out.println("Possible actions for state 100122" + Arrays.toString(actionList.toArray()));
        
        actionList = testProblem.actions(new State("110121"));
        System.out.println("Possible actions for state 110121 " + Arrays.toString(actionList.toArray()));
        
        actionList = testProblem.actions(new State("221001"));
        System.out.println("Possible actions for state 221001 " + Arrays.toString(actionList.toArray()));

        System.out.println("End of test for ProblemMC class");


    }
}
