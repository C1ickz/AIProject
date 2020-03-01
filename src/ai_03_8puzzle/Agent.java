/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_03_8puzzle;

import ai_02_MandC.*;
import java.util.Arrays;

/**
 *
 * @author Ryan
 */
public class Agent {

    private Sequence explored = new Sequence();
    private InputParser parser = new InputParser();
    State childState = null;
    Problem problem = null;
    Sequence actionsTaken;
    
//        public Action simple_problem_solving_agent(Percept percept){
//        state = update_state(state, percept);
//        if(seq == null){
//            goal = formulate_goal(state);
//            problem = formulate_problem(state,goal);
//            seq = seaarach(proboem);
//            if(seq)
//        }
//    }
    
    public Agent(String originalData){
        if(originalData.startsWith("M&C#")){
            problem = new MCProblem(new State("331000"), new State("000133"));
            actionsTaken = bfs(problem);     
            System.out.println(actionsTaken.toString());
        }
        else if(originalData.startsWith("8puzzle#")){
            problem = new EightPuzzleProblem(new State("182043765"), new State("123456780"));
            actionsTaken = bfs(problem);     
            //System.out.println(actionsTaken.toString());
        }
        else{
            System.out.println("Problem not currently implemented in the ");
        }
    }
    
  public Sequence getSolution(){
           return actionsTaken;     
  }
        

    private Node childNode(Problem problem, Node parent, Action action){
        //System.out.println(childState.toString());
  
        int pathCost = parent.getPathCost() + problem.step_cost(parent.getState(),action);
        Node childNode = new Node(problem.result(parent.getState(),action), parent,action, pathCost);
        //System.out.println(parent.getState());
        return childNode;
    }
    
    
    
    
    public Sequence bfs(Problem problem){
        QueueFIFO<Node> frontier = new QueueFIFO<>();
        QueueFIFO<State> explored = new QueueFIFO<>();
        int timesLooped= 0;
        //Node node = root;
        Node node = new Node(problem.getInitialState());
        Node child =null;

        if(node.getState().toString().equals("000133")){

            return node.getSolution();
        }
        frontier.insert(node);
        while(!frontier.isEmpty()){
            node = frontier.getNode();

            frontier.pop();
            explored.insert(node.getState());
            for(Action action: problem.actions(node.getState())){
                
                //System.out.println(action);

                child = childNode(problem, node,action);

                if(!explored.contains(child.getState()) || !frontier.contains(child)){
                    if(problem.isGoalState(child.getState())){
                        System.out.println("The size of the frontier is " + frontier.size());
                        System.out.println("The size of the explored states is " + explored.size());
                        return child.getSolution();
                    }

                }

                System.out.print(child.getState() + " ");

                //System.out.println(child.toString());
                explored.insert(child.getState());
                frontier.insert(child);
                timesLooped++;

            }


        }
        
        return null;
    }
    
    public static void main(String[] args){
        Agent agent = new Agent("8puzzle#");

        
    }
}
