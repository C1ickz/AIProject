/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_02_MandC;

import java.awt.TextField;
import java.util.Arrays;

/**
 *
 * @author Ryan
 */
public class ValidityChecker {
    
    
    /**
     * This method checks the 8puzzle that the user has inputted after it has been processed 
     * I chose strings instead of ints because I found strings to be much easier to work with and there is no need to convert strings to ints in this program
     * @param puzzle array of two strings that the user inputted (processed in MainFrame.java)
     * @return String to user whether or not input was valid
     */
    
    InputParser parser = new InputParser();
    public String check8puzzle(String input){
        
        //current state is first nine digits desired state is next 9 digits
        //Ex. 8puzzle#currentState#desiredState
        String[] hash = InputParser.parseInput(input);
        String currentState = hash[0];
        String desiredState = hash[1];
        String[] range = {"0","1","2","3","4","5","6","7","8"}; //range of numbers that can be used in 8puzzle
        boolean contains = true; //default value
        
        //loops through each number in the range and makes sure the states both contain them
        for(String kw: range){
            if(!currentState.contains(kw) || !desiredState.contains(kw)){
                contains = false;
            }
        }
        
        // if contains = false send hint to user
        if(contains == false){
           return "Inputted puzzle is not valid, \n \"Ex: 8puzzle#XXXXXXXXX#XXXXXXXXX. Please make sure the range of numbers is between 0 and 8 with no repeats,";
        }
        //Since boolean only has two outcomes else if is not needed
        //Return a message to the user telling them the puzzle is valid       
        else{
           return "Inputted puzzle is valid";
        }
   
        
       
        
    }
    
    
    
    public String checkMC(String input){
        //Check if M&C
        //Check that it uses ints
        //Only characters 0 through 9
        String[] hash = InputParser.parseInput(input);
        String currentState = hash[0];
        String desiredState = hash[1]; 
        
        if(currentState.length() == 6 && desiredState.length() == 6){
            if(currentState.equals(new StringBuilder(desiredState).reverse().toString())){
                return "Puzzle is valid";
            }
        }
        else{
            return "Puzzle is not valid";
        }
        return null;
 
    }
    
    
    public static void main(String[] args){
        ValidityChecker checker = new ValidityChecker();
        System.out.println(checker.checkMC("M&C#331000#000133"));
        System.out.println(checker.checkMC("M&C#33100#000133"));

    }
    
    
   
    
    
    

}
