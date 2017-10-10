/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Image;

/**
 *
 * @author mathieu
 */
public class Sprite {

    private float positionX, positionY, largeur, hauteur;
    private final Image image;
    private static final float RATIOX=(float) (ResolutionImage.getRatioX());
    private final float RATIOY=(float) (ResolutionImage.getRatioY());
    
    float positionXGrand, positionYGrand, hauteurGrand, largeurGrand;
    float positionXInit, positionYInit,largeurInit,hauteurInit;
         

    public Sprite(float positionX, float positionY, float largeur, float hauteur, Image image) {
        this.positionX = positionX*RATIOX ;
        this.positionY = positionY*RATIOY;
        this.largeur = largeur*RATIOX;
        this.hauteur = hauteur*RATIOY;
        this.image = image;
        this.positionXGrand=(positionX-(largeur*1.2f-largeur)/2)*RATIOX;
        this.positionYGrand= (positionY-(hauteur*1.2f-hauteur)/2)*RATIOY;
        hauteurGrand=RATIOY*hauteur*1.2f;
        largeurGrand=RATIOX*largeur*1.2f;
        this.positionXInit = positionX*RATIOX ;
        this.positionYInit = positionY*RATIOY;
        this.largeurInit = largeur*RATIOX;
        this.hauteurInit = hauteur*RATIOY;
    }

 public boolean intersect(int x, int y,boolean cliquer){
      boolean temp= false;
     if(cliquer){
    
     
     if(x > positionX && x < positionX+largeur && y > positionY && y < positionY+hauteur ){
         
         temp = true; //collision entre souris et image
     }
     }else{
         

         if(x > positionX && x < positionX+largeur && y > positionY && y < positionY+hauteur ){
             this.positionX=positionXGrand;
        this.positionY=positionYGrand;
         this.largeur=largeurGrand;
         this.hauteur=hauteurGrand;
         }
         else{
             this.positionX=positionXInit;
              this.positionY=positionYInit;
              this.hauteur=hauteurInit;
              this.largeur=largeurInit;
         }
             
         
        
         
     }
     return temp;
 }
 
 
 public void render(int cameraX){
     
     image.draw(-cameraX + positionX, positionY, largeur, hauteur);
    
 }

    public Image getImage() {
        return image;
    }

    public float getHauteur() {
        return hauteur;
    }

    public float getLargeur() {
        return largeur;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

  
    
    
    
}
