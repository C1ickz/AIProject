/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package package2;

import java.util.Arrays;

/**
 *
 * @author Ryan
 */
public class Agent {

    private Sequence explored = new Sequence();
    private InputParser parser = new InputParser();
    State childState = null;
    
    
//        public Action simple_problem_solving_agent(Percept percept){
//        state = update_state(state, percept);
//        if(seq == null){
//            goal = formulate_goal(state);
//            problem = formulate_problem(state,goal);
//            seq = seaarach(proboem);
//            if(seq)
//        }
//    }
    
    public Agent(){}
    
  
        

    private Node childNode(MCProblem problem, Node parent, Action action){
        //System.out.println(childState.toString());
  
        int pathCost = parent.getPathCost() + problem.step_cost(parent.getState(),action);
        Node childNode = new Node(problem.result(parent.getState(),action), parent,action, pathCost);
        //System.out.println(parent.getState());
        return childNode;
    }
    
    
    
    
    public Sequence bfs(MCProblem problem){
        QueueFIFO<Node> frontier = new QueueFIFO<>();
        QueueFIFO<State> explored = new QueueFIFO<>();
        int timesLooped= 0;
        //Node node = root;
        Node node = new Node(problem.getInitialState());
        Node child =null;
        if(problem.goalTest(new State("331000"))){
            return node.getSolution();
        }
        frontier.insert(node);
        explored.insert(node.getState());
        while(!frontier.isEmpty()){
            
            node = frontier.getNode();
            frontier.pop();
            explored.insert(node.getState());
            for(Action action: problem.actions(node.getState())){
                //System.out.println(action);
                child = childNode(problem, node,action);
                if(!explored.contains(child.getState()) || !frontier.contains(child)){
                    if(problem.goalTest(child.getState())){
                        System.out.println("The size of the frontier is " + frontier.size());
                        System.out.println("The size of the explored states is " + explored.size());
                        return child.getSolution();
                    }
                }
                System.out.println(child.toString());
                explored.insert(child.getState());
                frontier.insert(child);
                timesLooped++;

            }
                           

        }
        
        
        
        
        return null;
    }
    
    public static void main(String[] args){
        MCProblem problem = new MCProblem(new State("331000"), new State("000133"));
        Agent agent = new Agent();
        Sequence actionsTaken = agent.bfs(problem);
       
        System.out.println(actionsTaken.toString());
        
    }
}
