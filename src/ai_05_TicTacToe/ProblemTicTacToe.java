/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_05_TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class ProblemTicTacToe extends Problem{
    private State initialState;
    boolean winner = false;
    public ProblemTicTacToe(State initialState){
        super(initialState,  null); // The goalState is unknown
        this.initialState = initialState;
    }
    
    @Override
    public State getInitialState() {
        return initialState;
    }
    
    
    @Override
    public Action miniMax(State state){
        List<Action> actions = actions(state);
        int v = Integer.MIN_VALUE;
        Action maxAct = null;
        
        for(Action action : actions){
            int u = minValue(result(state, action));
            if(u > v){
                
                v = u;
                maxAct = action; 
            
            }
            
        }
        return maxAct;        
    }
    public int maxValue(State state){
        List<Action> actions = actions(state);
        if(terminalTest(state)){
            return utility(state, "O");
        }
        
        int v = Integer.MIN_VALUE;
        for(Action action : actions){
            v = Integer.max(v, minValue(result(state, action)));
        }
       // System.out.println(v);
        return v;
    }
    
    public int minValue(State state){
        List<Action> actions = actions(state);
        if(terminalTest(state)){
            return utility(state, "O");
        }
        
        int v = Integer.MAX_VALUE;
        for(Action action : actions){
            v = Integer.min(v, maxValue(result(state, action)));
        }
        
        return v;
    }
    public static String[][] convertToArr (State state){
        String puzzle = state.toString();
        String[][] board = new String[3][3];
        int index = 0;
        for(int i = 0; i < puzzle.length(); i += 3){
            String[] temp = puzzle.substring(i, Math.min(puzzle.length(), i + 3)).split("");
            for (int j = 0; j < temp.length; j++) {
                board[index][j] = temp[j];

            }        

        index++;
        }
       return board; 
    }
    @Override
    public List<Action> actions(State s) {
        List<Action> actions = new ArrayList<>();
        String[][] board = convertToArr(s);
        
        for(int row = 0; row< board.length; row++){
            for(int column =0; column < board[row].length; column++){
                if(board[row][column].equals("-")){
                    actions.add(new Action(String.valueOf(row) + " " + String.valueOf(column)));
                }
            }
        }
        
        return actions;
    }
    
    public String getNextPlayer(State currentState){
        String board = currentState.toString();
        String player;
        int x = board.length() - currentState.toString().replace("X", "").length();
        int o = board.length() - currentState.toString().replace("O", "").length();;
        
        if(x == 0 && o == 0)
            player = "X";
        else if(x == o)
            player = "X";
        else
            player = "O";
        
        return player;
    }

    @Override
    public State result(State s, Action a) {
        State puzzle = s;
        String converted = "";
        String[][] board = convertToArr(s);
        int row = Integer.parseInt(a.toString().substring(0,1));
        int column = Integer.parseInt(a.toString().substring(2));
        String nextPlayer = getNextPlayer(s);
        
        if(row >= 0 && row < 3){
            if(column >= 0 && column < 3){
                if(board[row][column].equals("-")){
                    board[row][column] = nextPlayer;
                for(int i = 0; i < board.length; i++){
                     for(int j = 0; j < board[i].length; j++){
                        converted += board[i][j];
                        }
                    }
                }
            }
        }
        

        return new State(converted);
    }
    
    @Override
    public boolean terminalTest(State s){
        String[][] board = convertToArr(s);

        for (int row = 0; row < 3; row++) {
        if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]) && !board[row][0].equals("-"))    {  
          //  System.out.println("Row win");
            winner = true;
            return true;
            }
        }
    
        for (int column = 0; column < 3; column++) {
            if (board[0][column].equals(board[1][column]) && board[1][column].equals(board[2][column]) && !board[0][column].equals("-")) {
            //    System.out.println("Column win");
                winner = true; 
                return true;
            }
        }
        //checks diagonals for win-condition
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("-")){
           //System.out.println("Diag left to right win");
            winner = true; 
            return true;
        }
        else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("-")){
          //  System.out.println("Diag right to left win");
            winner = true;
            return true;
        }
        else if(!s.toString().contains("-")){
          // System.out.println("No winner. Board is full");
            return true;
        }
        return false;

    }
    
    @Override
    public int utility(State s, String player ){
        String[][] board = convertToArr(s);
        int score;
        boolean playerXWin = false;
        boolean playerYWin = false;
        if(terminalTest(s) == true){
      
            for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]) && !board[row][0].equals("-"))    {  
                if(board[row][0].equals("X"))
                    playerXWin = true;               
                else
                    playerYWin = true;
                }
            }

            for (int column = 0; column < 3; column++) {
                if (board[0][column].equals(board[1][column]) && board[1][column].equals(board[2][column]) && !board[0][column].equals("-")) {
                    if(board[0][column].equals("X"))
                        playerXWin = true;               
                    else
                        playerYWin = true;


                }
            }
            //checks diagonals for win
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("-")){
                    if(board[0][0].equals("X"))
                        playerXWin = true;               
                    else
                        playerYWin = true;


            }
            if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("-")){
                if(board[0][2].equals("X"))
                    playerXWin = true;               
                else
                    playerYWin = true;


            }
            if(!s.toString().contains("-")){
                playerXWin = true;
                playerYWin = true;
                
            }
        }
        
        
        if(playerXWin && playerYWin )
            score = 0;
        else if((playerXWin == true && player.equals("X")) || playerYWin == true && player.equals("O"))
            score = 1;        
        else
            score = -1;
        return score;
    }
    
    
    @Override
    public boolean isGoalState(State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int step_cost(State state, Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnvironment(String data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizePuzzle(State state) {
        String puzzle = state.toString();
        for(int i = 0; i < puzzle.length(); i += 3){
            String[] temp = puzzle.substring(i, Math.min(puzzle.length(), i + 3)).split("");
           //System.out.println(Arrays.toString(temp) + "temp");
            for (int j = 0; j < temp.length; j++) {
                System.out.print(temp[j] + " ");

            }        
            System.out.println();

        }
    }
    
    
@Override
    public String visualizePuzzletoString(State state) {
        String answer = "";
        String puzzle = state.toString();
        for(int i = 0; i < puzzle.length(); i += 3){
            String[] temp = puzzle.substring(i, Math.min(puzzle.length(), i + 3)).split("");
           //System.out.println(Arrays.toString(temp) + "temp");
            for (int j = 0; j < temp.length; j++) {
                answer += temp[j] + " ";

            }        
            answer += "\n";

        }
        return answer;
    }
    
    public static void main(String[] args){
        State state = new State("-OOXXOXX-");


        Problem problem = new ProblemTicTacToe(state);

        String[][] puzzle = convertToArr(state);
        System.out.println(Arrays.deepToString(puzzle));
        
        List<Action> actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
        System.out.println("Utility value for O = " + problem.utility(state, "O"));        
        State afterResult = problem.result(state, new Action("0 0"));
        System.out.println("After action 0 0 " + afterResult);
    
         problem.visualizePuzzle(state);
             
        System.out.println("\n");
        state = new State("XOXOXOXOX");
        problem = new ProblemTicTacToe(state);  
        actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
        System.out.println("Utility value for O = " + problem.utility(state, "O"));
        problem.visualizePuzzle(state);

        
        System.out.println("\n");
        state = new State("XO-XO----");
        problem = new ProblemTicTacToe(state);  
        actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));   
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
        System.out.println("Utility value for O = " + problem.utility(state, "O"));
        Action bestAction = problem.miniMax(state);
        System.out.println("Best action " + bestAction);
        System.out.println("After using this action the puzzle looks like this");
        afterResult = problem.result(state, bestAction);
        problem.visualizePuzzle(afterResult);       
        
        System.out.println("\n");
        state = new State("XO-------");
        problem = new ProblemTicTacToe(state);  
        actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));   
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
        System.out.println("Utility value for O = " + problem.utility(state, "O"));
         bestAction = problem.miniMax(state);
        System.out.println("Best action " + bestAction);
        System.out.println("After using this action the puzzle looks like this");
        afterResult = problem.result(state, bestAction);
        problem.visualizePuzzle(afterResult);             
  
        
        System.out.println("\n");
        state = new State("XO--X--O-");
        problem = new ProblemTicTacToe(state);  
        actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));   
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
         bestAction = problem.miniMax(state);
        System.out.println("Best action " + bestAction);
        System.out.println("After using this action the puzzle looks like this");
        afterResult = problem.result(state, bestAction);
        problem.visualizePuzzle(afterResult);    
        
        System.out.println("\n");
        state = new State("XO--X--OX");
        problem = new ProblemTicTacToe(state);  
        actions = problem.actions(state);
        System.out.println("There are " + actions.size() + " possible actions for for state " + state);
        System.out.println("Possible actions " + Arrays.toString(actions.toArray()));
        System.out.println("Terminal test = " + problem.terminalTest(state));   
        System.out.println("Utility value for X = " + problem.utility(state, "X"));
        System.out.println("Utility value for O = " + problem.utility(state, "O"));
         bestAction = problem.miniMax(state);
        System.out.println("Best action " + bestAction);
        System.out.println("After using this action the puzzle looks like this");
        afterResult = problem.result(state, bestAction);
        problem.visualizePuzzle(afterResult);   
        System.out.println("Utility value for X after this move= " + problem.utility(state, "X"));
        
    }


}
