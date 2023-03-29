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
public class Plateau{
    private int Ligne_default=10;
    private int Colonne_default=10; 
    private int NbMines_default=10; 
    private int Ligne; 
    private int Colonne;
    private int nbMines; 
    private int niveau; 
    private Case[][] plateau;
    
    public int getLigne(){return this.Ligne;}
    public void setLigne(int lig){this.Ligne=lig;}
    
    public int getColonne(){return this.Colonne;}
    public void setColonne(int col){this.Colonne=col;}
    
    public int getNbMines(){return this.nbMines;}
    public void setNbMines(int nbm){this.nbMines=nbm;}
    
    public int getNiveau(){return this.niveau;}
    public void setNiveau(int niv){this.niveau=niv;}
    
    public Case[][] getPlateau(){return this.plateau;}
    public void setPlateau(Case[][] p){this.plateau=p;}
    
    public void setFin(boolean b){
        for(int i=1; i<this.Ligne-1; i++){
            for(int j=1; j<this.Colonne-1; j++){
                this.plateau[i][j].setFin(b);
            }
        }
    }
    
    public Plateau(){
        this.Ligne=Ligne_default; 
        this.Colonne=Colonne_default;
        this.nbMines=NbMines_default;
        this.niveau=1; 
        this.plateau=new Case[Ligne_default][Ligne_default];      
    }
    
    public Plateau(int ligne, int colonne, int niv){
        this.Ligne=ligne+2; 
        this.Colonne=colonne+2;  
        this.niveau=niv; 
        this.plateau=new Case[ligne+2][colonne+2]; 
        this.nbMines=((ligne*colonne*niv)/10); 
        initPlateau();
        
    }
    
    public void initPlateau(){
        int compteur=0; 
        for(int i=0; i<this.Ligne; i++){
            for(int j=0; j<this.Colonne; j++){
                if(i==0 || i==this.Ligne-1 || j==0 || j==this.Colonne-1)
                    this.plateau[i][j]=null;
                else{
                    if(compteur<this.nbMines)
                        this.plateau[i][j]=new Mines();
                    else
                        this.plateau[i][j]=new Vide();
                    compteur++;
                }
                   
            }
        }
        repartition();
    }
    
    public void repartition(){
        for(int i=0; i<1000; i++){
            int l1=(int)(Math.random()*(this.Ligne-2)+1);
            int c1=(int)(Math.random()*(this.Colonne-2)+1);
            int l2; 
            int c2; 
            do{
                l2=(int)(Math.random()*(this.Ligne-2)+1);
                c2=(int)(Math.random()*(this.Colonne-2)+1);
            }
            while(l1==l2 && c1==c2);
            Case c=this.plateau[l1][c1];
            this.plateau[l1][c1]=this.plateau[l2][c2];
            this.plateau[l2][c2]=c;
        }
        initCase();
    }
    
    public void initCase(){
        for(int i=1; i<this.Ligne-1; i++){
            for(int j=1; j<this.Colonne-1; j++){
                this.plateau[i][j].setLig(i);
                this.plateau[i][j].setCol(j);
                if(!this.plateau[i][j].getPiegee())
                    this.plateau[i][j].setnbMines(getnbMineAutour(this.plateau[i][j]));
            }
        }
    }
    
    public int getnbMineAutour(Case c){
        int compteur=0; 
        if(this.plateau[c.getLig()-1][c.getCol()]!=null)
            if(this.plateau[c.getLig()-1][c.getCol()].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()-1][c.getCol()+1]!=null)
            if(this.plateau[c.getLig()-1][c.getCol()+1].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()][c.getCol()+1]!=null)
            if(this.plateau[c.getLig()][c.getCol()+1].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()+1][c.getCol()+1]!=null)
            if(this.plateau[c.getLig()+1][c.getCol()+1].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()+1][c.getCol()]!=null)
            if(this.plateau[c.getLig()+1][c.getCol()].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()+1][c.getCol()-1]!=null)
            if(this.plateau[c.getLig()+1][c.getCol()-1].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()][c.getCol()-1]!=null)
            if(this.plateau[c.getLig()][c.getCol()-1].getPiegee())
                compteur+=1;
        if(this.plateau[c.getLig()-1][c.getCol()-1]!=null)
            if(this.plateau[c.getLig()-1][c.getCol()-1].getPiegee())
                compteur+=1;
        return compteur; 
    }
    
    //retourne le plateau
    public String toString(){
        String s=""; 
        for(int i=1; i<this.Ligne-1; i++){
            for(int j=1; j<this.Colonne-1; j++)
                s+=this.plateau[i][j].toString();
            s+="\n";
        }
        return s; 
    }
    
    public String toStringTester(){
        String s=""; 
        for(int i=1; i<this.Ligne-1; i++){
            for(int j=1; j<this.Colonne-1; j++)
                if(this.plateau[i][j].getPiegee())
                    s+="[ m ]";
                else
                    if(this.plateau[i][j].getnbMines()==0)
                        s+="[  ]";
                    else
                        s+="[ "+this.plateau[i][j].getnbMines()+" ]";
            
        s+="\n";    
        }
        return s;    
    }
    
    public boolean FinJeu(){
        boolean finjeu=true; 
        int compteur=0; 
        for(int i=1; i<this.Ligne-1; i++){
            for(int j=1; j<this.Colonne-1; j++){
                if(this.plateau[i][j].getPiegee())
                    if(this.plateau[i][j].getD())
                        compteur++;   
            }
        }
        if(compteur!=this.nbMines){
            for(int i=1; i<this.Ligne-1; i++){
                for(int j=1; j<this.Colonne-1; j++){
                    if(!this.plateau[i][j].getPiegee()&&!this.plateau[i][j].getVisible())
                        finjeu=this.plateau[i][j].getVisible();
                }
            }
        }
        return finjeu;
    }
    
    public void propage(Case c){
        if(!c.getD()){
            c.setVisible(true);
            if(c.getnbMines()==0){
                if(this.plateau[c.getLig()-1][c.getCol()]!=null)
                    if(!this.plateau[c.getLig()-1][c.getCol()].getPiegee() && !this.plateau[c.getLig()-1][c.getCol()].getVisible())
                        propage(this.plateau[c.getLig()-1][c.getCol()]);
                if(this.plateau[c.getLig()-1][c.getCol()+1]!=null)
                    if(!this.plateau[c.getLig()-1][c.getCol()+1].getPiegee() && !this.plateau[c.getLig()-1][c.getCol()+1].getVisible())
                        propage(this.plateau[c.getLig()-1][c.getCol()+1]);
                if(this.plateau[c.getLig()][c.getCol()+1]!=null)    
                    if(!this.plateau[c.getLig()][c.getCol()+1].getPiegee() && !this.plateau[c.getLig()][c.getCol()+1].getVisible())
                        propage(this.plateau[c.getLig()][c.getCol()+1]);
                if(this.plateau[c.getLig()+1][c.getCol()+1]!=null)    
                    if(!this.plateau[c.getLig()+1][c.getCol()+1].getPiegee() && !this.plateau[c.getLig()+1][c.getCol()+1].getVisible())
                        propage(this.plateau[c.getLig()+1][c.getCol()+1]);
                if(this.plateau[c.getLig()+1][c.getCol()]!=null)    
                    if(!this.plateau[c.getLig()+1][c.getCol()].getPiegee() && !this.plateau[c.getLig()+1][c.getCol()].getVisible())
                        propage(this.plateau[c.getLig()+1][c.getCol()]);
                if(this.plateau[c.getLig()+1][c.getCol()-1]!=null)    
                    if(!this.plateau[c.getLig()+1][c.getCol()-1].getPiegee() && !this.plateau[c.getLig()+1][c.getCol()-1].getVisible())
                        propage(this.plateau[c.getLig()+1][c.getCol()-1]);
                if(this.plateau[c.getLig()][c.getCol()-1]!=null)    
                    if(!this.plateau[c.getLig()][c.getCol()-1].getPiegee() && !this.plateau[c.getLig()][c.getCol()-1].getVisible())
                        propage(this.plateau[c.getLig()][c.getCol()-1]);
                if(this.plateau[c.getLig()-1][c.getCol()-1]!=null)    
                    if(!this.plateau[c.getLig()-1][c.getCol()-1].getPiegee() && !this.plateau[c.getLig()-1][c.getCol()-1].getVisible())
                        propage(this.plateau[c.getLig()-1][c.getCol()-1]); 
        
            }    
        }
    }

    
    public boolean CoupsValides(String v){
        boolean valable=false;
        if(v.length()==3){
            String c0=""+v.charAt(0);
            String c1=""+v.charAt(1);
            String c2=""+v.charAt(2);
            int lig=Integer.parseInt(c0)+1;
            int col=Integer.parseInt(c2)+1;
            if(lig>0 && lig<this.Ligne-1)
                if(c1.equals(":"))
                    if(col>0 && col<this.Colonne-1)
                        if(!this.plateau[lig][col].getVisible())
                            valable=true;
        }
        return valable;
    }
    
    //renvoie une case
    public Case getCase(int x, int y){
        return this.plateau[x][y]; 
    }
    
    public void setCase(int x, int y, Case c){
        this.plateau[x][y]=c;
    }
    
    public void afficherPlateau(Plateau p){
        System.out.println(p.toString()); 
    }
    
    public boolean choixMode(String s){
        if(s.length()==1){
            int lig=Integer.parseInt(s.charAt(0)+"");
            return (lig==1 || lig==2);
        }
        else
            return false;
    }
    
}
