/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Graphics;

/**
 *
 * @author mathi
 */
public abstract class Etat {
    
     protected Unite unite;
     protected boolean bouge;
     protected boolean renderValide;
     private boolean flecheBouge=true;
     
     /**
      * Type de direction possible pour les unites
      */
     public enum Direction{
         GAUCHE,DROITE;
     }
     
     Direction direction;
/**
 * Constructeur de la classe Etat
 * @param unite
 * @param bouge
 * @param direction 
 */
    public Etat(Unite unite, boolean bouge, Direction direction) {
        this.unite = unite;
        this.bouge = bouge;
        this.direction = direction;
    }

    
    
   /**
    * methode a redefinir par les Etat Tir et Immobile
    * @param g 
    */
    public abstract void render(Graphics g);
    public abstract void space();
    public abstract void fleche();


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if(unite.isSelectionne()){
        this.direction = direction;
    }
    }
    public boolean isBouge() {
        return bouge;
    }

    public void setBouge(boolean bouge) {
        this.bouge = bouge;
    }

    public boolean isFlecheBouge() {
        return flecheBouge;
    }

    public void setFlecheBouge(boolean flecheBouge) {
        this.flecheBouge = flecheBouge;
    }
    
    
    public abstract float getAngle();
    
}  
