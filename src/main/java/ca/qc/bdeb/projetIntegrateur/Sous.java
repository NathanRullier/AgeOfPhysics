/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
public class Sous implements Bougeable{
    
   private int positionX,positionY,prix;
   private float hauteur,largeur;
   private final double tempsInitial;
   private double tempsActuel;
   private final Image sous;
   private static int compteur=50;
/**
 * Construceur de la classe Sous qui est instancie lorsqu'une unite est elimine
 * @param positionX
 * @param positionY
 * @param tempsInitial
 * @param prix 
 */
    public Sous(int positionX, int positionY, double tempsInitial,int prix) {
        this.positionX = positionX;
        this.positionY = positionY-compteur;
        this.tempsInitial = tempsInitial;
        this.prix = prix;
        sous = ResolutionImage.getSous().getImage();
        this.hauteur = ResolutionImage.getSous().getHauteur();
        this.largeur =ResolutionImage.getSous().getLargeur();
        compteur += 75;
        
    }
   
   
/**
 * Dessine l'image du sous ainsi que le montant de la recompense
 * @param g
 * @throws SlickException 
 */
    @Override
    public void render(Graphics g) throws SlickException {
        sous.draw(positionX, positionY,hauteur,largeur);
        g.setColor(Color.black);
        g.drawString("+ "+prix + " $"  , positionX,positionY);
    }
/**
 * Met a jour la position du sous. Il se met a descendre apres un certain temps.
 * @throws SlickException 
 */
    @Override
    public void update() throws SlickException {
        if(tempsActuel > tempsInitial+2){
            positionY +=10;
        }
        
       isRenderValide();

    }
/**
 * Determine si le sous doit etre dessine. 
 */
    @Override
    public void isRenderValide() {
       if(positionY > Main.getHauteur()){
            Constante.listeEnlever.add(this);
       }
    }

    public void setTempsActuel(double tempsActuel) {
        this.tempsActuel = tempsActuel;
    }
/**
 * reinitialise le compteur du nombre de sous
 */    
    public static void resetCompteur(){
        compteur=50;
    }
    
}
