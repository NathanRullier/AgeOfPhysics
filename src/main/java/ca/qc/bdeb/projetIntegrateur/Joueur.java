/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mathieu
 */
public class Joueur {

    private final String nom;
    private boolean premierTour=true;
    private int vie = (int)Constante.vieDepart; //valeur de départ
    private int argent = Constante.ARGENT_DEPART;
    private final int numeroPosition;//1 ou 2 pour gauche ou droite
    private boolean sonTour; //si cest le tour du joueur
    private Rectangle hitbox1 = null, hitboxCentre = null, hitbox2 = null; //chateau a une hit box
    private boolean aUneDefense;//pour ne pas avoir plusieurs catapulte
/**
 * Constructeur de la classe Joueur
 * @param nom
 * @param numeroPosition
 * @param SonTour 
 */
    public Joueur(String nom, int numeroPosition, boolean SonTour) {
        this.nom = nom;
        this.numeroPosition = numeroPosition;
        this.sonTour = SonTour;
        switch (this.numeroPosition) {
            case 1:
                hitbox1 = ResolutionImage.getHitbox1J1();
                hitboxCentre = ResolutionImage.getHitbox2J1();
                hitbox2 = ResolutionImage.getHitbox3J1();
                break;
            case 2:
                hitbox1 = ResolutionImage.getHitbox1J2();
                hitboxCentre = ResolutionImage.getHitbox2J2();
                hitbox2 = ResolutionImage.getHitbox3J2();
        }
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getVie() {
        return vie;
    }

    public String getNom() {
        return nom;
    }

    public int getArgent() {
        return argent;
    }


    public int getNumeroPosition() {
        return numeroPosition;
    }

    public boolean isSonTour() {
        return sonTour;
    }

    public void setSonTour(boolean SonTour) {
        this.sonTour = SonTour;
    }

    @Override
    public String toString() {
        return nom + vie;
    }
/**
 * Enleve les dommages subit et termine la partie s'il ne lui reste plus de vie
 * @param dommage 
 */
    public void setDommageSubit(int dommage) {
        this.vie -= dommage;
        if (this.vie <= 0) {
            vie = 0;
            Constante.gameOver = true;
        }

    }
/**
 * Methode qui valide s'il y a eu une collision entre un projectile et un chateau
 * @param rectangle
 * @return 
 */
    public boolean collision(Rectangle rectangle) {
        boolean x = false;
        if (hitbox1.intersects(rectangle) || hitbox2.intersects(rectangle) || hitboxCentre.intersects(rectangle)) {
            x = true;
        }

        return x;
    }
/**
 * Lorsqu'une unite de ce joueur en elimine une autre
 * @param unite 
 */
    public void elimierUnite(UniteOffensive unite) {
        argent += unite.getRecompense();

    }

    public boolean isaUneDefense() {
        return aUneDefense;
    }

    public void setaUneDefense(boolean aUneDefense) {
        this.aUneDefense = aUneDefense;
    }
/**
 * Valide si le joueur peut acheter l'unite en question
 * @param prix
 * @return  
 */    
    public boolean peutAcheter(int prix){
        boolean x = false;
        if(argent-prix >=0){
            x=true;
        }
        
        return x;
    }
    
    public void achete(int prix){
        argent = argent-prix;
    }
/**
 * Cette methode donne de l'argent au joueur pour pouvoir achete l'avion
 * @return 
 */
    public boolean cheatCode(){
        boolean x = true;
        
        if(argent >= Constante.COUT_AVION){
            x=false;
        }else {
            argent+=25;
        }
        return x;
    }
    
    public void changementTour(){
        
        
        if(this.sonTour){
            sonTour=false;
            premierTour=false;
            
        }else{
            if(!premierTour){
            this.argent += (Constante.ARGENT_DEPART/4);
            }
            sonTour = true;
            MenuAchat.setJoueur(this);
            switch(numeroPosition){
                case 2 : 
                    Jeu.setCameraX((int) (-(ResolutionImage.WORLD_MAP_LARGEUR - Main.getLargeur())));
                    break;
                case 1 : 
                    Jeu.setCameraX(0);
            }
        }
    }
}
