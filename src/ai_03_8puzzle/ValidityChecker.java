/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_03_8puzzle;

import ai_02_MandC.*;
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
    
    private InputParser parser = new InputParser();
    public boolean check8puzzle(String input){
     
        //current state is first nine digits desired state is next 9 digits
        //Ex. 8puzzle#currentState#desiredState
        String[] hash = parser.parseInput(input);

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
            return false;
        }
        //Since boolean only has two outcomes else if is not needed
        //Return a message to the user telling them the puzzle is valid       
        else{
           return true;
        }
   
        
       
   
    }
    
    
    
    public boolean checkMC(String input){
        //Check if M&C
        //Check that it uses ints
        //Only characters 0 through 9
        String[] hash = parser.parseInput(input);
        String currentState = hash[0];
        String desiredState = hash[1]; 
         String[] range = {"0","1","2","3"};
         
        boolean contains = false; //default value
 
        //loops through each number in the range and makes sure the states both contain them
//        for(String kw: range){
//            if(!currentState.contains(kw) || !desiredState.contains(kw)){
//                contains = false;
//                break;
//               
//            }
//        }

      
        
        
        try{
            Integer.parseInt(hash[0]);
            Integer.parseInt(hash[1]);
            for(int i = 0; i < currentState.length(); i++){
                int num = Integer.parseInt(""+currentState.charAt(i));
                if(num > 3){
                    return false;
                 }
                else{
                    contains = true;
                }
               
            
            }
            for(int i = 0; i < desiredState.length(); i++){
                int num = Integer.parseInt(""+desiredState.charAt(i));
                if(num > 3){
                    return false;
                 }
                else{
                    contains = true;
                }
               
            
            }           
            

            if(currentState.length() == 6 && desiredState.length() == 6 && contains == true){
                    return true;

            }
  
        
   
        }
        
        catch(NumberFormatException e){
                return false;

        }
        return true;
 
    }
    
    
    public static void main(String[] args){
        ValidityChecker checker = new ValidityChecker();
        System.out.println("M&C");
        System.out.println(checker.checkMC("M&C#331000#000133"));
        System.out.println(checker.checkMC("M&C#221000#000111"));

        System.out.println(checker.checkMC("M&C#33100#000133"));
        System.out.println(checker.checkMC("M&C#123rgre100#000133"));
        System.out.println(checker.checkMC("M&C#531000#000133"));

        System.out.println("=====================================\n");
        System.out.println("8puzzle");
        System.out.println(checker.check8puzzle("8puzzle#123456780#087654321"));
        System.out.println(checker.check8puzzle("8puzzle#012345687#012345678"));
        
        System.out.println(checker.check8puzzle("8puzzle#1r3456780#087654321"));
        System.out.println(checker.check8puzzle("8puzzle#12234567#087654321"));




    }
    
    
   
    
    
    

}
