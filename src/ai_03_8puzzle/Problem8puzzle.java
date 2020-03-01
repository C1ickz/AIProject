/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_03_8puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ryanharris
 */
public class Problem8puzzle extends Problem{

    private State initialState;
    private State goalState;
    
    
    public Problem8puzzle(State initialState, State goalState) {
        super(initialState, goalState);
        this.initialState= initialState;
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
        
                if(a == null || a.toString() == null){
            return s;
        }
                
        int columns, rows = 4;        
        int[][] puzzle = new int[4][4];
        
        
        for(int i = 0; i < rows;i++){     
            for(int j = 0; i < columns; j++){
                puzzle[i][j] = Integer.parseInt(""+s.toString.charAt(j));
            }
            
        
        }
        
        
        
        switch(a.toString()){
            case "moveRight":
                break;
                
                
                
            case "moveLeft":
                break;
                
            case "moveDown":
                break;
                
            case  "moveUp":
                
                
                    
                
                
                
                
                
                    
        }
        
        


  
        
        return new State(data);  
    }
    
    
       
        public int findBlank(int[][] puzzle){
            int index = 0;
            return index;
        }
        
        public int[][] moveRight(int[][] puzzle){
        return null;
            
        }

        public int[][] moveLeft(int[][] puzzle){
        return null;
            
        }

        public int[][] moveUp(int[][] puzzle){
        return null;
            
        }

        public int[][] moveDown(int[][] puzzle){
        	int [][] a= state;
		 int tile = a[row][column];
		a [row][column] = a [row+1][column];
		a [row+1][column] = tile;	
		total++;
		return a;
        }

        public boolean isValidMove(String move){
        return false;
            
        }
    @Override
    public boolean isGoalState(State s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int step_cost(State state, Action action) {
        return 1;
    }
    
    public static void main(String[] args){
        
    }
}
