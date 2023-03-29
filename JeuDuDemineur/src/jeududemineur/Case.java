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
public abstract class Case {
    //attributs
    public boolean visible; 
    public boolean Piegee; 
    public boolean d; 
    public boolean fin; 
    public int nbMines;
    public int l; public int c;  
    
    public boolean getD(){return this.d;}
    public void setD(boolean d){this.d=d;}
    
    public boolean getVisible(){return this.visible;}
    public void setVisible(boolean v){this.visible=v;}
    
    public int getnbMines(){return this.nbMines;}
    public void setnbMines(int nbm){this.nbMines=nbm;}
    
    public boolean getPiegee(){return this.Piegee;}
    public void setPiegee(boolean m){this.Piegee=m;}
    
    public boolean getFin(){return this.fin;}
    public void setFin(boolean f){this.fin=f;}
    
    public int getLig(){return this.l;}
    public void setLig(int lig){this.l=lig;}
    
    public int getCol(){return this.c;}
    public void setCol(int col){this.c=col;}
    
    
    public Case(){
        this.visible=false; 
        this.nbMines=0; 
            
    }
    
    //constructeur d'une case
    public Case(int l, int c, boolean mine){
        this.l=l;
        this.c=c;
        this.Piegee=mine;
        this.visible=false;
        this.d=false;
        this.fin=false;
    }
    
    public abstract String toString(); 
    
}
