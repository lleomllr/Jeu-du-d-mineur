/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeududemineur;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author meill
 */
public class Vide extends Case {
    
    public Vide(){
        super(0, 0, false); 
    }
    
    public String toString(){
        String s=""; 
        if(this.visible)
            if(this.nbMines!=0)
                s+="[ "+this.nbMines+" ]"; 
            else
                s+="[ ]"; 
        else
            if(this.d)
                s+="[ 0 ]";
            else
                s+="[ "+(this.l-1)+":"+(this.c-1)+" ]";
        return s; 
    }
}
