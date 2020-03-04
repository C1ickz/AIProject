/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_03_8puzzle;

import ai_02_MandC.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ryanharris
 */
public class Node {
    
    private State state;
    private Node parent;
    private Action action;
    private int pathCost;
    private int depth;
    
    /**
     * Constructor for the root node
     * @param state 
     */
    public Node(State state){
        this.state=state;
        this.parent = null;
    }
    
    /**
     * 
     * @param state
     * @param parent
     * @param action
     * @param pathCost 
     */
    public Node(State state, Node parent, Action action, int pathCost){
        this.action = action;
        this.state = state;
        this.parent = parent;
        this.pathCost = pathCost;
    
    }
    
    /**
     * 
     * @return Sequence of actions to take to reach goalState
     */
    public Sequence getSolution(){
        ArrayList<Action> answer = new ArrayList<>();
        Node temp = this;
        Action theAction = temp.getAction();
        do{
            if(theAction!= null){
                answer.add(theAction);
                temp = temp.getParentNode();
                if(temp == null){
                    theAction = null;
                }
                else{
                    theAction = temp.getAction();
                }
            }
            
        }
        while(theAction!=null);
        Collections.reverse(answer);
        return new Sequence(answer);//reversed solution
    }
    
    @Override
    public String toString(){
        String result = " Node:\nstate " + state;
        result+= "\naction: " + action;
        result+= "\n path cost:" + pathCost;
        
        return result;
        
        
    }
    
    /**
     * Gets the parent node of a child node
     * @return The parent node of the child selected
     */
    public Node getParentNode(){
        return this.parent;
    }
            

    
    public Node getParent(){
        return parent;
    }
    
    public Action getAction(){
        return action;
    }
    
    public State getState(){
        return state;
    }
    
    public int getPathCost(){
        return pathCost;
    }

    
    
    public static void main(String[] args){
        
        Node root = new Node(new State("331000"));//Initial State
        
        Node child1 = new Node(new State("310102"), root,new Action("row#02"),1);
        Node child2 = new Node(new State("3210001"), child1, new Action("row#01"),1);
        System.out.println(child1.toString());
        System.out.println(child2.getSolution());
 

        


    }
}