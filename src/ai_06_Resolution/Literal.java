/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_06_Resolution;

/**
 *
 * @author Ryan
 */
public class Literal {
    
    private String name;
    private String negation;
    
    

    public Literal(String negation, String name ){
        this.negation = negation;
        this.name = name;
    }
    
 
    
    public Literal getNegation(){
        
        String newNegate;
        if(negation.equals("~"))
            newNegate = "";
        else
            newNegate = "~";
        
        Literal negatedLiteral = new Literal(newNegate, name);
        
        return negatedLiteral;
 
    }
    
    @Override
    public String toString(){
        return negation + name;
    }
    
    /**
     * Prints out literal in word form
     * @return literal in word form
     */
    public String printLiteral(){
        
        String printedLiteral = "_";
        
        if(negation.equals("~"))
            printedLiteral += "NOT";
    
        printedLiteral += name;
        
        return printedLiteral;

    }
    
    /**
     * Method prints out the literal in word form. For use with the .getNegation() method
     * @param attemptedNegation If string negate is passed through it will add on .getNegation() 
     * @return literal in word form
     */
    public String printLiteral(String attemptedNegation){
        String printedLiteral = "_";
        
        if(negation.equals("~"))
            printedLiteral += "NOT";
        if(attemptedNegation.equals("negate"))
            printedLiteral += name + ".getNegation()";
            return printedLiteral;

    }
     
    public static void main(String[] args){
        Literal test1 = new Literal("~", "P");
        System.out.println("lit" + test1.printLiteral() + " is: " + test1.toString());
        
        Literal test2 = new Literal("", "P");
        System.out.println("lit" + test2.printLiteral() + " is: " + test2.toString());
        
        Literal test3 = new Literal("~", "Q");
        System.out.println("lit" + test3.printLiteral() + " is: " + test3.toString());
        
        System.out.println("\n");
        
        Literal test4 = new Literal("~", "P");
        System.out.println("lit" + test4.printLiteral("negate") + " is: " + test4.getNegation());
        
        Literal test5 = new Literal("", "P");
        System.out.println("lit" + test5.printLiteral("negate") + " is: " + test5.getNegation());
        
        Literal test6 = new Literal("~", "Q");
        System.out.println("lit" + test6.printLiteral("negate") + " is: " + test6.getNegation());

    }
}
