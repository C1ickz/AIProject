/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */

package ai_03_8puzzle;

import ai_02_MandC.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author ryanharris
 */
public class Sequence {
    private List<Action> actionsTaken;

    
    public Sequence(){
        actionsTaken = new ArrayList<>();
    }
    
    public Sequence(ArrayList<Action> actionsTaken){
        this.actionsTaken = actionsTaken;
    }
    
    public void add(Action state)
    {
        actionsTaken.add(state);
    }  
    
    public List<Action> getSequence(ArrayList<Action> sequence){
        return sequence;
    }
    
    public Action getFirst(){
        if(actionsTaken == null || actionsTaken.isEmpty()){
            return null;
        }
        //actionsTaken.get(0)
        else{
        return actionsTaken.get(actionsTaken.size() -1);
        }
    }
    
    public List<Action> getRest(){
        return actionsTaken.subList(1, actionsTaken.size());
    }
    public void remove(){
        
        actionsTaken.remove(actionsTaken.size() -1);
    }
    public int size(){
        return  actionsTaken.size();
    }
    
    @Override
    public String toString(){
        return Arrays.toString(actionsTaken.toArray());
    }
    
    public Action get(int index){
        return actionsTaken.get(index);
    }
    
    //return the sequence of actions
    public static void main(String[] args){
        Sequence actions = new Sequence();
        for(int i = 0; i <= 10; i++){
            if(i % 2 == 0){
                actions.add(new Action("Left"));
            }
            else{
                actions.add(new Action("Right"));
            }
        }
        System.out.println(actions.toString());
        System.out.println(actions.getFirst());
        System.out.println(actions.getRest());
        
       
        
    }
    
}