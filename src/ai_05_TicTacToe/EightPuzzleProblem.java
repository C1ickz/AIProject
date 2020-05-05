/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_05_TicTacToe;

import ai_04_RouteFinder.*;
import ai_03_8puzzle.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class EightPuzzleProblem extends Problem{
    private State initialState;
    private State goalState;
    
        public EightPuzzleProblem(State initialState, State goalState){
        super(initialState, goalState);
        this.initialState = initialState;
        this.goalState = goalState;
        
    }
    @Override    
    public void visualizePuzzle(State state){
        String puzzle = state.toString();
        for(int i = 0; i < puzzle.length(); i += 3){
            String[] temp = puzzle.substring(i, Math.min(puzzle.length(), i + 3)).split("");
            for (int j = 0; j < temp.length; j++) {
                System.out.print(temp[j] + " ");

            }        
            System.out.println("");

        }
    }
    public static int[][] convertToArr (State state){
        String puzzle = state.toString();
        int[][] puzzleAsInts = new int[3][3];
        int index = 0;
        for(int i = 0; i < puzzle.length(); i += 3){
            String[] temp = puzzle.substring(i, Math.min(puzzle.length(), i + 3)).split("");
            for (int j = 0; j < temp.length; j++) {
                puzzleAsInts[index][j] = Integer.parseInt(temp[j]);

            }        

        index++;
        }
       return puzzleAsInts; 
    }
    
  
    
    public static int[] getZeroIndex(int[][] puzzle){
        int[] index= new int[2];
        for(int i = 0; i < puzzle.length; i++){
            for(int j = 0; j < puzzle[i].length; j++){
                if(puzzle[i][j] == 0){
                    index[0] = i;
                    index[1] = j;
                    
                }
            }
        }
        return index;
    }
        
    @Override
    public State getInitialState() {
        return initialState;
    }
    
    

    @Override
    public List<Action> actions(State s) {
//        
//        System.out.println("=================================");
//        System.out.println("Original state");
//        visualizePuzzle(s);
//        System.out.println("---------");
        List<Action> actions = new ArrayList<>();
        int[][] puzzle = convertToArr(s);
        int row = getZeroIndex(puzzle)[0];
        int column = getZeroIndex(puzzle)[1];
       // System.out.println("Row " + row + " Column " + column);
        //move up 
        if(row > 0){
            actions.add(new Action("mu"));
        }
        
        //move down
        if(row < puzzle[0].length - 1){
            actions.add(new Action("md"));
        }
             
        //move left
        if(column > 0){
            actions.add(new Action("ml"));
        }

        //move right
        if(column < puzzle[0].length - 1){
            actions.add(new Action("mr"));
        }
        System.out.println("There are " + actions.size() + " possible actions");

        return actions;
    }

    @Override
    public State result(State s, Action a) {
        int[][] puzzle = convertToArr(s);
        int row = getZeroIndex(puzzle)[0];
        int column = getZeroIndex(puzzle)[1];
        int temp;
        switch(a.toString()){
            case "mr":
               // System.out.println("right");
                //System.out.println(Arrays.deepToString(puzzle));

                temp = puzzle[row][column];
                puzzle[row][column] = puzzle[row][column + 1];
                puzzle[row][column + 1] = temp;
               // System.out.println(Arrays.deepToString(puzzle));
                break;
            case "ml":
               // System.out.println("left");
               // System.out.println(Arrays.deepToString(puzzle));
                temp = puzzle[row][column];
                puzzle[row][column] = puzzle[row][column - 1];
                puzzle[row][column - 1] = temp;
               // System.out.println(Arrays.deepToString(puzzle));

                break;
            case "mu":
              //  System.out.println("up");
              //  System.out.println(Arrays.deepToString(puzzle));
                temp = puzzle[row][column];
                puzzle[row][column] = puzzle[row - 1][column];
                puzzle[row - 1][column] = temp;
              //  System.out.println(Arrays.deepToString(puzzle));
                break;
            case "md":
               // System.out.println("down");
               // System.out.println(Arrays.deepToString(puzzle));
                temp = puzzle[row][column];
                puzzle[row][column] = puzzle[row + 1][column];
                puzzle[row + 1][column] = temp;
                puzzle[row+1][column] = temp;
               // System.out.println(Arrays.deepToString(puzzle));
                
        }
        String original = "";
        for(int i = 0; i < puzzle.length; i++){
            for(int j = 0; j < puzzle[i].length; j++){
                original += puzzle[i][j];
            }
        }
       // System.out.println(a.toString());
       // visualizePuzzle(new State(original));
       // System.out.println("-----------");
       // State original = backToOriginal(puzzle);
        return new State(original);
    }
    

    @Override
    public boolean isGoalState(State s) {
        return s.toString().equals(this.goalState.toString());

    }

    @Override
    public int step_cost(State state, Action action) {
        return 1;
    }
    
    public static void main(String[] args){
       // State state = new State("123456780");
      //State state = new State("182630745");
        State state = new State("512346780");


        Problem eightPuzzle = new EightPuzzleProblem(state, state);

        int[][] puzzle = convertToArr(state);
        int[] zeroIndex = getZeroIndex(puzzle);
        System.out.println(Arrays.deepToString(puzzle));
        System.out.println(Arrays.toString(zeroIndex));
        List<Action> actions = eightPuzzle.actions(state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        eightPuzzle.visualizePuzzle(state);
        State yes = eightPuzzle.result(state, new Action("mu"));
        System.out.println("\n");
        eightPuzzle.visualizePuzzle(yes);
    }

    @Override
    public void setEnvironment(String data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean terminalTest(ai_05_TicTacToe.State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ai_05_TicTacToe.Action miniMax(ai_05_TicTacToe.State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int utility(ai_05_TicTacToe.State s, String player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visualizePuzzletoString(ai_05_TicTacToe.State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
