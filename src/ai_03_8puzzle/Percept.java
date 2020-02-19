/**
 * Ryan Harris and Aj Hammond
 * 02-03-2020
 */
package ai_03_8puzzle;

import ai_02_MandC.*;

/**
 *
 * @author ryanharris
 */
public class Percept {
    private String percept;
    
    public Percept(String percept){
        this.percept = percept;
    }
    
    @Override
    public String toString(){
        return percept;
    }
            
}