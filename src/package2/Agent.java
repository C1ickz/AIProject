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
    private Node root = new Node(new State("331000"));
    private State goal = new State("000133");
    private Sequence explored = new Sequence();
    private QueueFIFO frontier;
    private InputParser parser = new InputParser();
    private MCProblem problem = new MCProblem(new State("331000"), new State("331000"));
    
    
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
        State childState = problem.result(parent.getState(),action);
        if(childState == null){
            return null;
        }
        int pathCost = parent.getPathCost() + problem.step_cost(parent.getState(),action);
        Node childNode = new Node(childState, parent,action, 1);
        return childNode;
    }
    
    
    
    
    public Sequence bfs(Node root){
        frontier = new QueueFIFO();
        QueueFIFO explored = new QueueFIFO();
        //Node node = root;
        Node node = new Node(problem.getInitialState());
        if(problem.goalTest(new State("Yes"))){
            return node.getSolution();
        }
        frontier.clear();
        frontier.insert(root);
        while(!frontier.isEmpty()){
            frontier.pop();
            explored.insert(node.getState());
            for(Action action: problem.actions(node.getState())){
                Node child = childNode(problem, node,action);
                if(!(explored.contains(child.getState()) || frontier.contains(child.getState()))){
                    if(problem.goalTest(child.getState())){
                        return child.getSolution();
                    }
                    frontier.insert(child);
                }
            }
        }
        
        
        
        
        return null;
    }
    
    public static void main(String[] args){
        Node node = new Node(new State("331000"));
        Agent agent = new Agent();
        agent.bfs(node)
        
    }
}
