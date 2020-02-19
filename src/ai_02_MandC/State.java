/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_02_MandC;

/**
 *
 * @author ryanharris
 */
public class State {
    
    private String state;
    private Integer[] stateInt = new Integer[6];
    public State(String state){
        this.state = state;
    }
    
    public Integer[] toIntArr(){
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