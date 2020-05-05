/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_06_Resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class Resolution {
    Sentence kb;
    Sentence alpha; 
    Sentence combinedSentences;
    
    public Resolution(Sentence kb, Sentence alpha){
    
        System.out.println("Resolution Constructor");
        System.out.println("KB " + kb.toString());
        System.out.println("alpha " + alpha.toString());
  
        combinedSentences  = combineSentences(kb, alpha);
        System.out.println("resolutionSentence" + combinedSentences.toString());
        System.out.println("boolean_KB_entails_alpha is " + PL_Resolution(kb, alpha));
    }
    
    public boolean areNegatedPair(Literal Ci, Literal Cj){
        //System.out.println(Ci.toString().equals(Cj.toString()));
        return Ci.getNegation().toString().equals(Cj.toString());
    }
    
    
    public ArrayList<Clause> union(ArrayList<Clause> c1, ArrayList<Clause> c2){
        ArrayList<Clause> answer = new ArrayList<Clause>(c1);
        //System.out.println("Clauses: " + c1.toString()+"\t"+ c2.toString());
        for(Clause clause2: c2){ //smaller array
            Boolean isInList = false;
            for(Clause clause1: c1){ //larger base array
                if(clause1.toString().equals(clause2.toString())){
                    isInList = true;
                }
            }
            if(!isInList){
                answer.add(clause2);
            }
        }
        //System.out.println("Answer:" + answer.toString());
        return answer;
    }
    
    public Boolean isSubset(ArrayList<Clause> c1, ArrayList<Clause> c2){
        for(Clause clause1: c1){
            boolean match = false;
            for(Clause clause2: c2){
                if(!(clause1.toString().equals(clause2.toString()))){
                    match = true;
                }
            }
            if(!match){
                return false;
            }
        }
        return true;
    }
    
    public Clause resolveTwoClauses(Clause Ci, Clause Cj, Literal lit){
        Clause solution = new Clause();
        ArrayList<Literal> CiList = Ci.getLiterals();
        ArrayList<Literal> CjList = Cj.getLiterals();
        for(int i = 0; i < Ci.size(); i++){
            Literal temp = CiList.get(i);
            if(!temp.toString().equals(lit.toString())){
                solution.add(temp);
            }
        }
        for(int i = 0; i < Cj.size(); i++){
            Literal temp = CjList.get(i);
            if(!temp.toString().equals(lit.toString())){
                solution.add(temp);
            }
        }
        return solution;
    }
    
    
    public ArrayList<Clause> PL_Resolve(Clause Ci, Clause Cj){
        
        
        ArrayList<Clause> answer = new ArrayList<>();
        
        ArrayList<Literal> CiList = Ci.getLiterals();
        ArrayList<Literal> CjList = Cj.getLiterals();
        
        for(int i = 0; i < CiList.size(); i++){
            Literal literalCi = CiList.get(i);
            
            for(int j = 0; j < CjList.size(); j++){
                
                Literal literalCj = CjList.get(j);
                //System.out.println(literalCi + "LiteralCi");
                //System.out.println(literalCj + "literalCj");
                if(areNegatedPair(literalCi, literalCj)){
                    
                    //System.out.println(clauseResolve);
                    answer.add(resolveTwoClauses(Ci, Cj, literalCi));
                    
                }
            }
        }
 
        return answer;
    }
    
    public Sentence combineSentences(Sentence first, Sentence second){
        Sentence sentence = first;
       ArrayList<Clause> clauses = second.getNegation().getClauses();
        //System.out.println(second.getNegation().toString() + "Hi");
        for(Clause c: clauses)
            first.add(c);
        

      return sentence;
       
       
        
    }
    
    public boolean resolventsContainEmptyClause(ArrayList<Clause> resolvents){
        //System.out.println("ad");
        for(Clause clause: resolvents){
            if(clause.getLiterals().isEmpty()){
                return true;
            }
        }
        
        return false;
    }
    public boolean PL_Resolution(Sentence KB, Sentence alpha){
        
        Sentence alphaNegate = alpha;
        Sentence resSentence = combineSentences(KB, alpha);

        ArrayList<Clause> clauses = resSentence.getClauses();
        ArrayList<Clause> newL = new ArrayList<>();
                
        while(true){
            //System.out.println("Step in while");
            for(int i = 0; i < clauses.size(); i++){
                Clause Ci = clauses.get(i);
                for(int j = i + 1; j < clauses.size(); j++){

                    Clause Cj = clauses.get(j);
                    ArrayList<Clause> resolvents = PL_Resolve(Ci, Cj);
                    if(resolventsContainEmptyClause(resolvents)){
                        //System.out.println("true");
                        return true;
                    }
                     newL = union(newL, resolvents);
                }
                

                
            }
            if(isSubset(newL, clauses)){
                //System.out.println("false");
                return false;
            }
            clauses = union(clauses, newL);            
        }
    }
    
    public static void main(String[] args){
        Clause clause1 = new Clause();
        Clause clause2 = new Clause();
        
        //Problem 1
        clause1.add(new Literal("", "P"));
        clause1.add(new Literal("~","P"));
        clause2.add(new Literal("", "P"));
        //clause2.add(new Literal("", "R"));
        //System.out.println(clause1);
       // System.out.println(clause2);
        ArrayList<Clause> clauseList1 = new ArrayList<>();
        ArrayList<Clause> clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList2.add(clause2);
       // ArrayList<Clause> a = resolution.union(clauseList1, clauseList2);
      //  System.out.println(a);
        Sentence sentence1 = new Sentence(clauseList1);
        Sentence sentence2 = new Sentence(clauseList2);

       Resolution resolution = new Resolution(sentence1, sentence2);
        System.out.println("\n");
       
       //Problem 2
        clause1 = new Clause();
        clause2 = new Clause();
        clause1.add(new Literal("", "P"));
        clause2.add(new Literal("", "P"));
        clause2.add(new Literal("~", "P"));
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList2.add(clause2);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
 
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n");
        
        
        //Problem 3
        clause1 = new Clause();
        clause2 = new Clause();
        Clause clause3 = new Clause();
        clause1.add(new Literal("", "P"));
        clause1.add(new Literal("", "Q"));
        clause2.add(new Literal("~", "P"));
        clause2.add(new Literal("", "R"));
        clause3.add(new Literal("", "Q"));
        clause3.add(new Literal("", "R"));
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList1.add(clause2);
        clauseList2.add(clause3);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n");
        
        //Problem 4
        clause1 = new Clause();
        clause2 = new Clause();
        clause3 = new Clause();
        Clause clause4 = new Clause();
        Clause clause5 = new Clause(); 
        Clause clause6 = new Clause();
        Clause clause7 = new Clause();       
        clause1.add(new Literal("~", "P"));
        clause1.add(new Literal("", "Q"));
        clause1.add(new Literal("", "R"));
        clause2.add(new Literal("~", "S"));
        clause3.add(new Literal("", "P"));
        clause4.add(new Literal("", "X"));
        clause5.add(new Literal("~", "Q"));
        clause6.add(new Literal("", "R"));
        clause7.add(new Literal("", "X"));        
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList1.add(clause2);
        clauseList1.add(clause3);
        clauseList1.add(clause4);
        clauseList1.add(clause5);
        clauseList2.add(clause6);      
        clauseList2.add(clause7);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n"); 
        
        //Problem 5
        clause1 = new Clause();
        clause2 = new Clause();
        clause3 = new Clause();
        clause4 = new Clause();
        clause5 = new Clause(); 
        clause6 = new Clause();
        clause1.add(new Literal("~", "P"));
        clause2.add(new Literal("", "Q"));
        clause3.add(new Literal("~", "R"));
        clause3.add(new Literal("", "S"));
        clause4.add(new Literal("~", "S"));
        clause5.add(new Literal("", "Q"));
        clause6.add(new Literal("~", "P"));
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList1.add(clause2);
        clauseList1.add(clause3);
        clauseList1.add(clause4);
        clauseList2.add(clause5);      
        clauseList2.add(clause6);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n");         
        
        //Problem 6
        clause1 = new Clause();
        clause2 = new Clause();
        clause3 = new Clause();
        clause1.add(new Literal("~", "P"));
        clause1.add(new Literal("~", "Q"));
        clause2.add(new Literal("", "Q"));
        clause2.add(new Literal("", "R"));
        clause3.add(new Literal("~", "P"));
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList1.add(clause2);
        clauseList2.add(clause3);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n");        
        
        
        
        //Problem 7
        clause1 = new Clause();
        clause2 = new Clause();
        clause3 = new Clause();
        clause4 = new Clause();
        clause5 = new Clause(); 
        clause6 = new Clause();
        clause7 = new Clause();
        clause1.add(new Literal("~", "P"));
        clause1.add(new Literal("", "Q"));
        clause1.add(new Literal("", "R"));
        clause2.add(new Literal("~", "S"));
        clause3.add(new Literal("", "P"));
        clause4.add(new Literal("", "X"));
        clause5.add(new Literal("~", "Q"));
        clause6.add(new Literal("", "R"));
        clause7.add(new Literal("", "X"));
        clauseList1 = new ArrayList<>();
        clauseList2 = new ArrayList<>();   
        clauseList1.add(clause1);
        clauseList1.add(clause2);
        clauseList1.add(clause3);
        clauseList1.add(clause4);
        clauseList1.add(clause5);      
        clauseList2.add(clause6);
        clauseList2.add(clause7);
        sentence1 = new Sentence(clauseList1);
        sentence2 = new Sentence(clauseList2);
        resolution = new Resolution(sentence1, sentence2); 
        System.out.println("\n");    
        
        System.out.println("end test main for Resolution class");
    }
    
}
