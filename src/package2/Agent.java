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
    private InputParser parser = new InputParser();
    private MCProblem problem = new MCProblem(new State("331000"), new State("331000"));
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
    
    
    
    
    public Sequence bfs(Node root){
        QueueFIFO<Node> frontier = new QueueFIFO<>();
        QueueFIFO<State> explored = new QueueFIFO<>();
        //Node node = root;
        Node node = new Node(problem.getInitialState());
        Node child =null;
        if(problem.goalTest(goal)){
            return node.getSolution();
        }
        frontier.insert(root);
        explored.insert(root.getState());
        while(!frontier.isEmpty()){
            node = frontier.getNode();
            frontier.pop();
            explored.insert(node.getState());
            for(Action action: problem.actions(node.getState())){
                //System.out.println(action);
                child = childNode(problem, node,action);
                if(!explored.contains(child.getState()) || !frontier.contains(child)){
                    if(problem.goalTest(child.getState())){
                        return child.getSolution();
                    }
                }
                System.out.println(child.toString());
                frontier.insert(child);

            }
                           

        }
        
        
        
        
        return null;
    }
    
    public static void main(String[] args){
        Node node = new Node(new State("331000"));
        Agent agent = new Agent();
        Sequence hi = agent.bfs(node);
        System.out.println(hi.toString());
        
    }
}
