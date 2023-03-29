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
public class Mines extends Case {
    
    public Mines(){
        super(0,0,true); 
    }
    
    public String toString(){
        String s="";
        if(this.visible)
            s+="[*]";
        else
            if(!this.fin){
                if(this.d)
                    s+="[0]";
         
                else
                    s+="["+(this.l-1)+":"+(this.c-1)+"]";
            }
            else
                s+="[m]";
        return s;
    }
}

