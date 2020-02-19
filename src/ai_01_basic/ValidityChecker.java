package ai_01_basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryanharris
 */
public class ValidityChecker {
    
    /**
     * This method checks the 8puzzle that the user has inputted after it has been processed 
     * I chose strings instead of ints because I found strings to be much easier to work with and there is no need to convert strings to ints in this program
     * @param hash array of two strings that the user inputted (processed in MainFrame.java)
     * @return String to user whether or not input was valid
     */
    public String check8puzzle(String[] hash){
        
        //current state is first nine digits desired state is next 9 digits
        //Ex. 8puzzle#currentState#desiredState
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
    
    
    
    public String checkMC(String[] hash){
        //M&C#331000#000133 valid ex M&C#hash(6 digits)# 6dig
        //M&C332000#hello not valid
        //Only validate 
        String[] currentState = hash[0].trim().split("");
        String[] desiredState = hash[1].trim().split("");
        Integer[] currentStateInt = new Integer[currentState.length];
        Integer[] desiredStateInt = new Integer[desiredState.length];
        String[] allowedStrings = {"0", "1", "2", "3"};
        System.out.println(Arrays.toString(currentState));
        System.out.println(Arrays.toString(desiredState));
        
        
        if(currentState.length == 6 && desiredState.length == 6){
            for(int i =0; i < currentState.length; i++){
                try{
                    currentStateInt[i] = Integer.parseInt(currentState[i].trim());
                    desiredStateInt[i] = Integer.parseInt(desiredState[i].trim());

                }

                catch(NumberFormatException nfe){
                    return "You can only use ints";
                }
            }
        }
        
        else{
            return "The current state and desired state must be the same length";
        }

        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(currentStateInt));
        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(desiredStateInt));
        int boatLeft = arrayList1.get(2);
        int boatRight = arrayList1.get(3);
        boolean good = true;
        boolean first = true;
        boolean second = false;
        Integer[] ints = new Integer[2];
        ArrayList<Integer> row = new ArrayList<>();
        ArrayList<Integer[]> combos = new ArrayList<Integer[]>();
        int timesLooped = 0;
        
        row.add(0);
        row.add(0);
        
        if(boatLeft >= 1 && boatRight >= 1){
            return "Only one boat is allowed";
        }
        
        //makes sure boat can only be on one side at a time
        else if((boatLeft == 1 && boatRight ==0) || (boatLeft ==0 && boatRight ==1)){
            
        
            while(good){
                if(boatLeft == 1){
             
                    if((row.get(0) >=0 &&row.get(0) <= 2) && (row.get(1) >=0 &&row.get(1) <= 2)){
                        if(row.get(0) + row.get(1) >= 2){
                            if(row.get(1) >= 2){
                            row.set(1, row.get(1)- 1);}
                            if(row.get(0) >= 2){
                                row.set(0,row.get(0)- 1);
                            }

                    }
                        if(timesLooped == 2 &&row.get(1) == 1){
                            first = false;
                            second = true;
                        }
                        if(first){
                            //6 digits only one boat 
                
                            if(arrayList1.get(0) > 0 && row.get(0) <= 2){
                            if(row.get(1) == 2){
                                    row.set(0,0);
                                }
                                row.set(0, row.get(0) + 1);
                                arrayList1.set(1, arrayList1.get(1) -1);
                                if(row.get(0) + row.get(1) >= 2){
                                    row.set(1, row.get(1)- 1);
                                }
                                System.out.println(Arrays.toString(row.toArray()));
                                
                                }
                                first = false;
                                second = true;

                            }
                        }
                        if(timesLooped == 10){
                            break;
                        }
                        if(second){
                         
                                if(arrayList1.get(1) >= 0){
                                    
                                    
                                    row.set(1, row.get(1) + 1);
                             
                                    if(row.get(0) == 2){
                                        row.set(0,0);
                                    }
                                    System.out.println(Arrays.toString(row.toArray()));
                                    first = true;
                                    second = false;
                                    if(row.get(1) == 2){
                                        break;
                                    }


                                }
                            
                        }
                    
                        
                        timesLooped += 1;

                        

                    }
               
              


                
                else if(boatRight == 1){
                    if((row.get(0) >=0 && row.get(0) <= 2) && (row.get(1) >=0 &&row.get(1) <= 2)){
                        if(row.get(0) + row.get(1) >= 2){
                            if(row.get(1) >= 2){
                            row.set(1, row.get(1)- 1);}
                            if(row.get(0) >= 2){
                                row.set(0,row.get(0)- 1);
                            }

                    }
                        if(timesLooped == 2 &&row.get(1) == 1){
                            first = false;
                            second = true;
                        }
                        if(first){
                
                            if(arrayList1.get(0) > 0 && row.get(0) <= 2){
                            if(row.get(1) == 2){
                                    row.set(0,0);
                                }
                                row.set(0, row.get(0) + 1);
                                arrayList1.set(1, arrayList1.get(1) -1);
                                if(row.get(0) + row.get(1) >= 2){
                                    row.set(1, row.get(1)- 1);
                                }
                                System.out.println(Arrays.toString(row.toArray()));
                                
                                }
                                first = false;
                                second = true;

                            }
                        }
                        if(timesLooped == 10){
                            break;
                        }
                        if(second){
                         
                                if(arrayList1.get(4) >= 0){
                                    
                                    
                                    row.set(1, row.get(1) + 1);
                             
                                    if(row.get(0) == 2){
                                        row.set(0,0);
                                    }
                                    System.out.println(Arrays.toString(row.toArray()));
                                    first = true;
                                    second = false;
                                    if(row.get(1) == 2){
                                        break;
                                    }


                                }
                            
                        }
                    
                        
                        timesLooped += 1;

                        


                }
            }
            }
        return "Check console output";

        }


        
      
    }
    
   
    
    

