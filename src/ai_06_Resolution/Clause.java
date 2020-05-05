/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_06_Resolution;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class Clause {
    
    private ArrayList<Literal> clause;
    
    public Clause(){
        clause = new ArrayList<>();
    }
    
    public Clause(ArrayList<Literal> clause){
        this.clause = clause;
    }
    
    @Override
    public String toString(){
        String answer = "(";
        Literal temp = null;
        
        for(int i =  0; i < clause.size() - 1; i++){
            temp = clause.get(i);
            answer += temp.toString() + " v ";
        }
        
        if(clause.size() > 0)
            answer += clause.get(clause.size() -1).toString();
           
        
        
        return answer + ")";
    }
    
    public boolean isEmpty(){
        return clause.isEmpty();
    }
    
    public Sentence getNegation(){
        
        Sentence negatedClause = new Sentence();
        
        // TODO: Add fmore descriptive name
        for(Literal lit : clause){
            Literal literal = lit.getNegation();
            Clause clause1 = new Clause();
            clause1.add(lit.getNegation());
            negatedClause.add(clause1);
        }
        return negatedClause;
    }
    
    public Literal getLiteral(int i){
        return clause.get(i);
    }
    
      public ArrayList<Literal> getLiterals(){
        ArrayList<Literal> literals = new ArrayList<>();
        
        for(Literal lit : clause)
            literals.add(lit);
        
          
        return literals;
    }
    public int size(){
        return clause.size();
    }
    public void add(Literal literal){
        clause.add(literal);
    }
    
    public String printClause(){
        String answer = "";
        
        if(clause.isEmpty())
            return "Empty clause";
        
                
        for(int i = 0; i < clause.size(); i++) {     
            if(clause.size() > 1 && i < clause.size() - 1)
                answer += clause.get(i).printLiteral() + "_or";
            else
              answer += clause.get(i).printLiteral(); 
        }
       
        return answer;
    }
    
      public String printClause(String negationAttempted){
        String answer = "";
        
        if(clause.isEmpty())
            return "Empty clause";
        
                
        for(int i = 0; i < clause.size(); i++) {     
            if(clause.size() > 1 && i < clause.size() - 1)
                answer += clause.get(i).printLiteral() + "_or";
            else
              answer += clause.get(i).printLiteral(); 
        }
       
        if(negationAttempted.equals("negate"))
            answer += ".getNegation()";
        
        
        return answer;
    }
      
    public static void main(String[] args){
        Clause clause = new Clause();
        System.out.println(clause.printClause() + " is: " + clause);
        
        clause = new Clause();
        clause.add(new Literal("", "R"));
        System.out.println("clause" + clause.printClause() + " is: " + clause);
        
        clause.add(new Literal("~", "P"));
        System.out.println("clause" + clause.printClause() + " is: " + clause);

        clause.add(new Literal("~", "Q"));
        System.out.println("clause" + clause.printClause() + " is: " + clause);
        
        System.out.println("\n Negation Testing");
        
        
       clause = new Clause();

        clause.add(new Literal("", "R"));
        System.out.println("clause" + clause.printClause("negate") + " is: " + clause.getNegation());
        
        clause.add(new Literal("~", "P"));
        System.out.println("clause" + clause.printClause("negate") + " is: " + clause.getNegation());
        
        clause.add(new Literal("~", "Q"));
        System.out.println("clause" + clause.printClause("negate") + " is: " + clause.getNegation());
        
        System.out.println(clause.getNegation());
        
    }
    
}
