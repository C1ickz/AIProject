/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_04_RouteFinder;

import ai_03_8puzzle.*;
import ai_02_MandC.*;

/**
 *
 * @author ryanharris
 */
public class State {
    
    private String state;
    private int[] stateInt = new int[6];
    public State(String state){
        this.state = state;
    }
    
    public int[] toIntArr(){
        String[] toArr = state.trim().split("");
        for(int i = 0; i < stateInt.length; i++){
            stateInt[i] = Integer.parseInt(toArr[i]);
        }
        return stateInt;
    }
    
    
    @Override
    public String toString(){
        return state;
    }
    
    
}