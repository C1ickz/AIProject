/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_03_8puzzle;

import ai_02_MandC.*;
import java.util.Arrays;


/**
 *
 * @author Ryan
 */
public class InputParser {
        private static String input;
        private static String[] inputArr;
    
        public String[] parseInput(String puzzle) {

        if(puzzle.startsWith("8puzzle#")){
            //parse input here
            input = puzzle.replace("8puzzle#", "").replace("#", " ");
            inputArr = input.split(" ");
            
        
            if(inputArr.length == 2){
                return inputArr;
            }
      
            else{
                System.out.println("Make sure puzzle is in form 8puzzle#XXXXXXXXX#XXXXXXXXX");
            }
        }
        
  
        else if(puzzle.startsWith("M&C#")){
            input = puzzle.replace("M&C#", "").replace("#", " ");
            inputArr = input.split(" ");
            
        

            if(inputArr.length == 2){
                return inputArr;
         
            }
            else{
                System.out.println("Make sure puzzle is in form M&C#XXXXXX#XXXXXX");
            }
        }
        
        return null;
        
    }
        
        public Integer[] actionParser(String action){
                action = action.replace("row#", "");
                Integer[] actionTaken = new Integer[2];
                for(int i =0; i< actionTaken.length;i++){
                    actionTaken[i] = Integer.parseInt(action.split("")[i]); //breaks Action row#11 down to  a String 11 then an Integer arr[1,1]
                }
                System.out.println(Arrays.toString(actionTaken));
                return actionTaken;
            
        }
        
        
        public String[] getStates(){
            return this.inputArr;
        }
}
