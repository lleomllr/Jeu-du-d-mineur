/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeududemineur;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author meill
 */
public class Jeu {
    private int joueur;
    private int niveau;//pour gérer les niveaux du jeu
    private int nbmines;//pour gérer le nombre de mines présentes sur le plateau
    private Plateau plateau; //pour gérer un plateau 
    private int choixMode; 
    private int lig, col, drapeau=0;
    private int coupa, coupc, compteur=1, compteurD;
    private boolean perdu, finjeu, drap; 
    
    //accesseurs
    public int getNiveau(){return this.niveau;}
    public void setNiveau(int niv){this.niveau=niv;}
    
    public int getChoixMode(){return this.choixMode;}
    public void setChoixMode(int cm){this.choixMode=cm;}
    
    public static int getLigne(String l){return Integer.parseInt(l.charAt(0)+"");}
    public static int getColonne(String c){return Integer.parseInt(c.charAt(2)+"");}
    
    
    //méthodes//
    
    //méthode permettant à l'utilisateur de jouer une partie de démineur en choisissant les cases qu'il veut découvrir
    public void Joue(){
        perdu=false; finjeu=false; drap=false;
        compteur=1; drapeau=0;
        String s="";
        joueur=1;
        do{
            System.out.println("Choisissez le nombre de lignes que vous voulez pour le plateau");
            lig=Lire.i();
        }
        while(lig<=5);
        do{
            System.out.println("Choisissez le nombre de colonnes que vous voulez pour le plateau");
            col=Lire.i();
        }
        while(col<=5);
        do{
            System.out.println("Choisissez un niveau de jeu entre 1 (pour le plus simple) et 9 (pour le plus difficile)");
            niveau=Lire.i();
        }
        while(niveau<1 || niveau>9);
        Plateau p=new Plateau(lig, col, niveau);
        compteurD=p.getNbMines();
        nbmines=p.getNbMines();
        String coup="";
        System.out.println("Il vous reste à trouver "+nbmines+" mines dans le plateau de jeu");
        System.out.println(p.toString());
        System.out.println();
        System.out.println(p.toStringTester());
        do{
            do{
                System.out.println("Veuillez joueur pour le coup "+compteur);
                System.out.print("Choix d'une case sous le format <ligne:colonne> : ");
                coup=Lire.S();
            }
            while(!p.CoupsValides(coup));
            coupa=getLigne(coup)+1;
            coupc=getColonne(coup)+1;
            if(!p.getCase(coupa, coupc).getD()){
                do{
                    do{
                        System.out.println("Souhaitez vous positionner un drapeau ? 1: OUI   2: NON");
                        s=Lire.S();
                    }
                    while(!p.choixMode(s));
                    drapeau=Integer.parseInt(s);
                    if(drapeau==1)
                        if(compteurD>0){
                            compteurD--;
                            drap=true;
                        }
                        else{
                            System.out.println("Nombre de drapeau épuisé... Le coup voulu a malheureusement été annulé");
                            drap=false;
                            compteur--;
                        }
                }
                while(drapeau!=1 && drapeau!=2);
                if(drapeau==1){
                    if(drap)
                        p.getCase(coupa, coupc).setD(true);
                }
                else{
                    perdu=p.getCase(coupa, coupc).getPiegee();
                    p.propage(p.getCase(coupa, coupc));
                }
            }
            else{
                p.getCase(coupa, coupc).setD(false);
                compteurD++;
            }
            if(perdu){
                System.out.println("Vous avez perdu !");
                finjeu=true;
                joueur=0; 
                p.setFin(finjeu);
                System.out.println(p.toString());
            }
            else{
                finjeu=p.FinJeu();
                p.setFin(finjeu);
                if(finjeu){
                    joueur=0; 
                    System.out.println("Vous avez gagné en "+compteur+" coups");
                    System.out.println(p.toString());
                }
                else{
                    System.out.println(p.toString());
                    System.out.println();
                    System.out.println(p.toStringTester());
                }
            }
        compteur++;    
        }
        while(!finjeu);
        System.out.println("Le jeu est fini ! Merci d'y avoir joué !");
    }
    
}
