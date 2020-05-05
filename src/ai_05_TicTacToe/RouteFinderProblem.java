/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_05_TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Road{
    String fromCity;
    int miles;
    String toCity;
     
     public Road(String fromCity, int miles, String toCity){
         this.fromCity = fromCity;
         this.miles = miles;
         this.toCity = toCity;
         
     }
     
    @Override
     public String toString(){
         return fromCity + " " + miles + " " + toCity;
     }
}
public class RouteFinderProblem extends Problem{
    private State initialState;
    private State goalState;
    
    private Road[] environment;
    public RouteFinderProblem(State initialState, State goalState){
        super(null, null);
        this.initialState = initialState;
        this.goalState = goalState;
        environment = new Road[0];
    }



    @Override
    public State getInitialState() {
        return initialState;
    }
    
    public int pathCost(Node node){
        return node.getPathCost();
    }

    @Override
    public List<Action> actions(State s) {
        String theCity = s.toString();
        List<Action>  listOfActions = new ArrayList<>();
        
        for(Road road : environment){
            
            if(road.fromCity.equals(theCity)){
                listOfActions.add(new Action("go "+ road.toCity));
            }
        }
        return listOfActions;
     }

    @Override
    public State result(State s, Action a) {
        String city1 = s.toString();
        String info[] = a.toString().split(" ");
        String city2 = info[1];

        for(Road road : environment){
            if(road.fromCity.equals(city1) ){
                if(road.toCity.equals(city2)){
                    return new State(city2);
                }
            }
        }
        
        return null;
                
    }
    
    
    @Override
    public String toString(){
        
        if(environment != null){
            return "ProblemRouteFinder initialState " + initialState + " goalState" + goalState+
                    "  environment:\n" + getEnvironment();
        }
        else{
            return "ProblemRouteFinder initialState  " + initialState + " goalState " + goalState;
        }
    }
    
    @Override
    public void setEnvironment(String data){
        String info[] = data.split("\n");
        
        environment = new Road[info.length * 2];
        
        
        int index = 0;
        
        for(String part:info){
            
            String[] linePart = part.split(" ");
            Road theRoad = new Road(linePart[0], Integer.parseInt(linePart[1]), linePart[2]);
            environment[index] = theRoad;
            index++;
            theRoad = new Road(linePart[2], Integer.parseInt(linePart[1]), linePart[0]);  
            environment[index] = theRoad;
            index++;
        }
    }
    
    
    public String getEnvironment(){
        String answer = "ProblemRouteFinder environment\n";
        if(environment != null){
            for(Road road : environment){
                answer += road.toString()+"\n";
            }
            
        }
        return answer;
    }

    @Override
    public boolean isGoalState(State s) {
        return s.toString().equals(goalState.toString());
    }

    @Override
    public int step_cost(State state, Action action) {
        String city = state.toString();
        String[] info = action.toString().split(" ");
        String actionCity = info[1];
        
        for(Road road : environment){
            if(road.fromCity.equals(city) && road.toCity.equals(actionCity)){
                return road.miles;
            }
        }
        
        return 1;
    }
    
    public void testActions(){
        showStateAndActions(new State("Siblu"));
        showStateAndActions(new State("Bucharest"));
        showStateAndActions(new State("Oradea"));

    }
    public void showStateAndActions(State state){
        List<Action> allActions = actions(state);
        
        System.out.println("\n\n -------------------");
        System.out.println("state.toString() " + state.toString());
        
        System.out.println("-----------allActions");
        
        if(allActions == null){
            System.out.println("null");
        }
        else{
            for(Action action : allActions){
                System.out.println("action.toString() " + action.toString());
            }
        }
    }
    
    private void tryOneResult(String stateString, String actionString){
        State state = new State(stateString);
        Action action = new Action(actionString);
        
        State nextState = result(state, action);
        
        System.out.println("result( " + stateString+ ", " + actionString + "}");
        if(nextState == null)
            System.out.println("null");
        else
            System.out.println(nextState.toString());
    }
    
    public void testResult(){
        
        tryOneResult("Bucharest", "go Siblu");
        tryOneResult("Bucharest", "go Giugiu");
        tryOneResult("Giugiu", "go Bucharest");
        tryOneResult("Bucharest", "go Oradea");
        tryOneResult("Oradea", "go Siblu");
        tryOneResult("Oradea", "go Zerind");
        
    }
    
    private void tryOneGoalTest(String stateString){
        State state = new State(stateString);
        boolean goalReached = isGoalState(state);
        System.out.println(stateString.toString());
        System.out.println("goalReached = " + goalReached);
    }
    
    private void testGoalTest(){
        tryOneGoalTest("Bucharest");
        tryOneGoalTest("Lugoi");
        tryOneGoalTest("Drobeta");
        tryOneGoalTest("Siblu");
        tryOneGoalTest("Hirsova");
        tryOneGoalTest("Rimnicu_Vilcea");

    }

    @Override
    public void visualizePuzzle(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void main(String[] args){
        RouteFinderProblem myTestProblem = new RouteFinderProblem(new State("Zerind"), new State("Siblu"));
    
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
        myTestProblem.setEnvironment(environment);
        
       // myTestProblem.testGoalTest();
        myTestProblem.testResult();
        //myTestProblem.testActions();
        
    
        
    }

    @Override
    public boolean terminalTest(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action miniMax(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int utility(State s, String player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visualizePuzzletoString(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
