/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_05_TicTacToe;


import ai_04_RouteFinder.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author ryanharris
 * @param <T>
 */
public class QueueFIFO<T>{

    private List<T> list;
    
    public QueueFIFO(){list = new ArrayList<>();}

          
    public void insert(T node){
        list.add(node);
    }
    public int size(){
        return list.size();
    }
    public void pop() throws NoSuchElementException {
        if(list.isEmpty())
            throw new NoSuchElementException("Trying to delete from an empty queue");
        
        list.remove(0);
   
    }
    public T getNode(){
        return list.get(0);
    }
    public boolean contains(T s){
        return list.contains(s.toString());
    }
    public void clear(){
        list.clear();
    }
    

    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    @Override
    public String toString(){
        return Arrays.toString(list.toArray());
    }


    
    public static void main(String[] args){
        QueueFIFO queue = new QueueFIFO();
        Action string = new Action("Hello");
        System.out.println("Before adding to queue");
        System.out.println("isEmpty()=" + queue.isEmpty());
        System.out.println("After adding items to queue");
        queue.insert(string);
        queue.insert(string);
        System.out.println(queue.toString());
        queue.pop();
        System.out.println("Pop one from queue");
        System.out.println(queue.toString());        
        System.out.println("isEmpty() =" + queue.isEmpty());
   
        

        
        
        
        
   
    }
            
  
    
}


