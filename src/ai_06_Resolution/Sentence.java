/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_06_Resolution;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class Sentence {
    
    private ArrayList<Clause> clauses;
    
    public Sentence(){
        clauses = new ArrayList<>();
    }
    
    public Sentence(ArrayList<Clause> clauses){
        this.clauses = clauses;
    }
    
    public void add(Clause clause){
        clauses.add(clause);
    }
    
    @Override
    public String toString(){
        String answer = "(";
        Clause temp = null;
        
        for(int i =  0; i < clauses.size() - 1; i++){
            temp = clauses.get(i);
            answer += temp.toString() + " ^ ";
        }
        
        if(clauses.size() > 0)
            answer += clauses.get(clauses.size() -1).toString();
           
        
        
        return answer + ")";
    }
    
    public int size(){
        return clauses.size();
    }
    
    public Clause get(int i){
        return clauses.get(i);
    }
    
  
    
    public ArrayList<Literal> getAllLiterals(){
        ArrayList<Literal> literals = new ArrayList<>();
        for(int i = 0; i < clauses.size(); i++){
            for(int j = 0; j < clauses.get(i).getLiterals().size(); j++) {
                literals.add(clauses.get(i).getLiterals().get(j));
            }
        }
        return literals;
    }
    
    public ArrayList<Clause> getClauses(){
        ArrayList<Clause> clauseList = new ArrayList<>();
        
        for(Clause clause : clauses)
            clauseList.add(clause);
        
        return clauseList;
    }

   
    public Sentence getNegation(){
       //determine arraylist size;
        int numOfClauses = 1;
        int[] sizeOfEachClause = new int[clauses.size()];
        //negate into sentences
        ArrayList<Sentence> sentences = new ArrayList<>();
        for(int i = 0;  i < clauses.size(); i++){
            sentences.add(clauses.get(i).getNegation());
            int size = clauses.get(i).getLiterals().size();
            numOfClauses *= size;
            sizeOfEachClause[i] = size;
        }

        //remove duplicates
        //You must compared each literal of each clause. If a clause in another sentence has the same literal, you must
        //remove one of the literals and take off one from the number of clauses;
        for(int i = 1; i < sentences.size(); i++){
            Sentence currentSentence = sentences.get(i);
            Sentence comparedSentence = sentences.get(i-1);
            for(int selectedClause = 0; selectedClause< currentSentence.getClauses().size(); selectedClause++)
            {
                Clause currentClause = currentSentence.getClauses().get(selectedClause);
                for(int selectedLiteral = 0; selectedLiteral< currentClause.getLiterals().size(); selectedLiteral++)
                {
                    Literal currentLiteral = currentClause.getLiterals().get(selectedLiteral);
                    for(int cClause = 0; cClause< comparedSentence.getClauses().size(); cClause++)
                    {
                        Clause comparedClause = comparedSentence.getClauses().get(cClause);
                        for(int cLiteral = 0; cLiteral< comparedClause.getLiterals().size(); cLiteral++)
                        {
                            Literal comparedLiteral = comparedClause.getLiterals().get(cLiteral);
                            if(comparedLiteral.equals(currentLiteral)){
                                sentences.get(i).getClauses().get(selectedClause).getLiterals().remove(selectedLiteral);
                                numOfClauses--;
                            }//comparing
                        }//compared literal
                    }
                }
            }
        }

        //initialize all the Clauses in
        ArrayList<Clause> negatedClauses = new ArrayList<>();
        ArrayList<Literal> literals = sentences.get(0).getAllLiterals();

        for(int x = 0; x < numOfClauses; x++){
            negatedClauses.add(new Clause());
        }

        //add the rest of the literals to the clause
        int divider = numOfClauses;
        for(int i = 0; i <sentences.size(); i++ ){
            divider /= sizeOfEachClause[i];
            literals = sentences.get(i).getAllLiterals();

            for(int x = 0; x < numOfClauses; x++){
                int selectedNum= Math.floorDiv(x, divider)%sizeOfEachClause[i];
                negatedClauses.get(x).add(literals.get(selectedNum));
            }
        }
        return new Sentence(negatedClauses);
    }
    
    public String printSentence(){
        String answer = "";
        
        if(clauses.isEmpty())
            return "Empty sentence";
        
        for(int i = 0; i < clauses.size(); i++){
        
            if(clauses.size() > 1 && i < clauses.size() - 1)
                answer += clauses.get(i).printClause() + "_and";
            else
              answer += clauses.get(i).printClause(); 
        }
                
      
        
        
        return answer;
    }
    
    
        public String printSentence(String attemptedNegation){
            String answer = "";

            if(clauses.isEmpty())
                return "Empty sentence";

            for(int i = 0; i < clauses.size(); i++){

                if(clauses.size() > 1 && i < clauses.size() - 1)
                    answer += clauses.get(i).printClause() + "_and";
                else
                  answer += clauses.get(i).printClause(); 
            }
            
            if(attemptedNegation.equals("negate")){
                answer += ".getNegation()";
            }




            return answer;
    }
    
    
    public static void main(String[] args){
        System.out.println("Start test main for Sentence class");
        System.out.println("Start clause testing");
        //Clause testing
        Clause testClause1 = new Clause();
        testClause1.add(new Literal("", "P"));
        System.out.println("clause" + testClause1.printClause() + " is: " + testClause1);  
        testClause1.add(new Literal("~", "Q"));       
        System.out.println("clause" + testClause1.printClause() + " is: " + testClause1);
        testClause1.add(new Literal("~", "R"));       
        System.out.println("clause" + testClause1.printClause() + " is: " + testClause1);
        //end of clause testing
        System.out.println("end clause testing\n\n");

        System.out.println("Start Setence testing");
        //  ( ~P v Q ) ^  ( ~R ) )
        Clause testClause2 = new Clause();
        testClause2.add(new Literal("~", "P"));
        testClause2.add(new Literal("", "Q"));        
        Clause testClause3 = new Clause();
        testClause3.add(new Literal("~", "R"));
        Sentence sentence = new Sentence();
        sentence.add(testClause2);
        sentence.add(testClause3);
        System.out.println("sentence" + sentence.printSentence() + " is: " + sentence);
        System.out.println("sentence" + sentence.printSentence("negate") + " is: " + sentence.getNegation());
        System.out.println("\n");
        // ( ( P ) ^  ( Q ) ^  ( ~R ) )
        Sentence sentence2 = new Sentence();
        testClause1 = new Clause();
        testClause2 = new Clause();
        testClause3 = new Clause();
        testClause1.add(new Literal("", "P"));
        testClause2.add(new Literal("", "Q"));
        testClause3.add(new Literal("~", "R"));    
        sentence2.add(testClause1);
        sentence2.add(testClause2);
        sentence2.add(testClause3);
        System.out.println("sentence" + sentence2.printSentence() + " is: " + sentence2);
        System.out.println("sentence" + sentence2.printSentence("negate") + " is: " + sentence2.getNegation());
        System.out.println("\n");
        
        // ( ( ~P v Q ) ^  ( R v S ) ^  ( A v B v ~C ) )
        Sentence sentence3 = new Sentence();
        testClause1 = new Clause();
        testClause2 = new Clause();
        testClause3 = new Clause();       
        testClause1.add(new Literal("~","P"));
        testClause1.add(new Literal("","Q"));
        testClause2.add(new Literal("","R"));
        testClause2.add(new Literal("", "S"));
        testClause3.add(new Literal("", "A"));
        testClause3.add(new Literal("", "B"));
        testClause3.add(new Literal("~", "C"));
        sentence3.add(testClause1);
        sentence3.add(testClause2);
        sentence3.add(testClause3);        
        System.out.println("sentence" + sentence3.printSentence() + " is: " + sentence3);
        System.out.println("sentence" + sentence3.printSentence("negate") + " is: " + sentence3.getNegation());
        System.out.println("end test main for Sentence class");
    }
}
    

