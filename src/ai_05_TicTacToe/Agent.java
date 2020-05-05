/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_05_TicTacToe;


import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ryan
 */
public class Agent {

    private Sequence explored = new Sequence();
    private InputParser parser = new InputParser();
    private ValidityChecker checker = new ValidityChecker();
    State childState = null;
    Problem problem = null;
    Sequence actionsTaken;
    private MainFrame frame;
    
//        public Action simple_problem_solving_agent(Percept percept){
//        state = update_state(state, percept);
//        if(seq == null){
//            goal = formulate_goal(state);
//            problem = formulate_problem(state,goal);
//            seq = seaarach(proboem);
//            if(seq)
//        }
//    }
    
    /**
     * Constructor for the Agent class
     * @param originalData 
     */
    public Agent(String originalData) throws InterruptedException{
        State startState = new State(parser.parseInput(originalData)[0]);
        State goalState = new State(parser.parseInput(originalData)[1]);
        
        if(originalData.startsWith("M&C#")){
            if(checker.checkMC(originalData)){
            problem = new MCProblem(startState, goalState);
            actionsTaken = bfs(problem);     
            System.out.println(actionsTaken.toString());
            }
            else{
                System.out.println("Puzzle is not in the correct form");
            }
        }
        else if(originalData.startsWith("8puzzle#")){
            if(checker.check8puzzle(originalData)){
            problem = new EightPuzzleProblem(startState, goalState);

            actionsTaken = bfs(problem);
            
            State temp = startState;
            //print out the actions taken step by step with a thread.sleep between each step to delay
            System.out.println("Step by step");
            System.out.println("======================================");
            for(int i = 0; i < actionsTaken.size(); i++){
                System.out.println("\n");
                State puzzle = problem.result(temp, actionsTaken.get(i));
                temp = puzzle;
                System.out.println(actionsTaken.get(i).toString());
                problem.visualizePuzzle(puzzle);
                TimeUnit.SECONDS.sleep(2);
           }
            }
            else{
                System.out.println("Puzzle is not in the right form");
            }
        }
        
        else if(originalData.startsWith("routeFinder#")){
            problem = new RouteFinderProblem(startState, goalState);

            String environment = "Oradea 71 Zerind\n" +
                            "Oradea 151 Sibiu\n" +
                            "Zerind 75 Arad\n" +
                            "Arad 140 Sibiu\n" +
                            "Arad 118 Timisoara\n" +
                            "Timisoara 111 Lugoj\n" +
                            "Lugoj 70 Mehadia\n" +
                            "Mehadia 75 Drobeta\n" +
                            "Drobeta 120 Craiova\n" +
                            "Craiova 146 Rimnicu_Vilcea\n" +
                            "Rimnicu_Vilcea 80 Sibiu\n" +
                            "Craiova 138 Pitesti\n" +
                            "Pitesti 97 Rimnicu_Vilcea\n" +
                            "Pitesti 101 Bucharest\n" +
                            "Sibiu 99 Fagaras\n" +
                            "Fagaras 211 Bucharest\n" +
                            "Bucharest 90 Giurgiu\n" +
                            "Bucharest 85 Urziceni\n" +
                            "Urziceni 98 Hirsova\n" +
                            "Hirsova 86 Eforie\n" +
                            "Urziceni 142 Vaslui\n" +
                            "Vaslui 92 Iasi\n" +
                            "Iasi 87 Neamt\n";
            problem.setEnvironment(environment);
            actionsTaken = bfs(problem);
            System.out.println(actionsTaken.toString());

            
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

        if(problem.isGoalState(node.getState())){

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
                
             

                if(!explored.contains(child.getState()) && !frontier.contains(child)){
                    if(problem.isGoalState(child.getState())){
                        System.out.println("The size of the frontier is " + frontier.size());
                        System.out.println("The size of the explored states is " + explored.size());
                        System.out.print(child.getState() + " ");

                        return child.getSolution();
                    }

                }

                //System.out.print(child.getState() + " ");

                //System.out.println(child.toString());
                explored.insert(child.getState());
                frontier.insert(child);
                timesLooped++;

            }


        }
        
        return null;
    }
    
    public static void main(String[] args) throws InterruptedException{
        //Make sure to uncomment these before running
        //Agent MandC1 = new Agent("M&C#331000#000133");
        //Agent MandC2 = new Agent("M&C#331000#0002155");
        
        //Agent Eightpuzzle1 = new Agent("8puzzle#012345678#102345678");
        //Agent EightPuzzle2 = new Agent("8puzzle#012345678#125348607");
        //Agent EightPuzzle3 = new Agent("8puzzle#182043765#123456780");
        
        Agent RouteFinder1 = new Agent("routeFinder#Arad#Neamt");
        //Agent RouteFinder2 = new Agent("routeFinder#Sibiu#Giurgiu");
        //Agent RouteFinder3 = new Agent("routeFinder#Urziceni#Neamt");
                


        
    }
}
