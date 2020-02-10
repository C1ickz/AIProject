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
    
    
        public Action simple_problem_solving_agent(Percept percept){
        state = update_state(state, percept);
        if(seq == null){
            goal = formulate_goal(state);
            problem = formulate_problem(state,goal);
            seq = seaarach(proboem);
            if(seq)
        }
    }
    
    public Agent(){}
    
    	public boolean isValidState(int misLeft, int canLeft, int misRight, int canRight ){
        if((0 < misLeft && 0 < canLeft) || 0 < misRight && 0< canRight){
                return false;	
        }

        if(misLeft <0 ||canLeft<0 || misRight<0 || canRight<0){
                return false;
        }
        return true;
        }
        

    private Node childNode(MCProblem problem, Node parent, Action action){
        State childState = problem.result(parent.getState(),action);
        if(childState == null){
            return null;
        }
        Node childNode = new Node(childState, parent,action, 1);
        return childNode;
    }
    
    
    
    
    public String bfs(Node root){
        frontier = new QueueFIFO();
        QueueFIFO explored = new QueueFIFO();
        //Node node = root;
        Node node = new Node(problem.getInitialState());
        if(problem.goalTest(node.getState()){
            return node.getSolution();
        }
        frontier.clear();
        frontier.insert(root);
        while(!frontier.isEmpty()){
            frontier.pop();
            explored.insert(node.getState());
        }
        
        
        
        
        return null;
    }
    
    public static void main(String[] args){
        Node node = new Node(new State("331000"));
        Agent agent = new Agent();
        Node found= agent.nextStates(node.getState());
    }
}
