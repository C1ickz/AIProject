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
        
        
    public Node nextStates(State state){
        String[] possibleActions = {"row#11", "row#02", "row#20", "row#01", "row#10"};
        Integer[][] actions = new Integer[possibleActions.length][2]; //create a 6x2 array 
        Integer[] stateAsArr = state.toIntArr();
        System.out.println(Arrays.toString(stateAsArr));
        for(int i = 0; i < possibleActions.length;i++){
           actions[i] = parser.actionParser(possibleActions[i]);
        }
        System.out.println(actions[3][0]);
        
        boolean looped = false;
        for(int i = 0; i< possibleActions.length; i++){
            for(int j = 0; j < 1;j++){
                 if(isValidState(stateAsArr[0],stateAsArr[1],stateAsArr[4],stateAsArr[5]) == false){
                //move to right
                if(looped == false){
                    stateAsArr[2] -= 1;
                    stateAsArr[3] += 1;
                    stateAsArr[4] += actions[i][j];
                    stateAsArr[5] += actions[i][j+1];
                    stateAsArr[0] -= actions[i][j];
                    stateAsArr[1] -= actions[i][j + 1];


                }
           
                //move to left
                else if(looped){
                    stateAsArr[2] += 1;
                    stateAsArr[3] -= 1;
                    stateAsArr[0] += actions[i][j];
                    stateAsArr[1] += actions[i][j];
                    stateAsArr[4] -= actions[i][j];
                    stateAsArr[5] -= actions[i][j];

                }
                
                for(Integer why: stateAsArr){
                    System.out.print(why);
                }
                System.out.println("\n");


                looped = !looped; //looped is equal to the inverse of itslef
               
                 }
                }
            }
        
   
        
        return null;
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
            explored.add(node.getState());
        }
        
        
        
        
        return null;
    }
    
    public static void main(String[] args){
        Node node = new Node(new State("331000"));
        Agent agent = new Agent();
        Node found= agent.nextStates(node.getState());
    }
}
