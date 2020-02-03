/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */

package package2;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author ryanharris
 */
public class Sequence {
    private ArrayList<State> actionsTaken;

    public Sequence(){
        actionsTaken = new ArrayList<>();
    }
    
    public void add(State state)
    {
        actionsTaken.add(state);
    }  
    
    public ArrayList<State> getSequence(ArrayList<State> sequence){
        return sequence;
    }
    
    @Override
    public String toString(){
        return Arrays.toString(actionsTaken.toArray());
    }
    
    
    public static void main(String[] args){
        Sequence actions = new Sequence();
        for(int i = 0; i <= 10; i++){
            if(i % 2 == 0){
                actions.add(new State("Left"));
            }
            else{
                actions.add(new State("Right"));
            }
        }
        System.out.println(actions.toString());

        
    }
    
}