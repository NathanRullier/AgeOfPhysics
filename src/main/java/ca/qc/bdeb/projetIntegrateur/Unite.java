/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mathieu
 */
abstract public class Unite implements Bougeable {

    private boolean selectionne, aTirerCeTour,projectileTransperceCible=false;
    protected final int degats, powerMax;
    protected final Joueur joueur;
    protected int positionX,positionY;
    protected float hauteur=0,largeur=0;
    private Etat etat;
    private double coutUnite;
    protected Rectangle hitbox;
    private final Color color;
/**
 * Constructeur de la classe unite
 * @param selectionne
 * @param aTirerCeTour
 * @param degats
 * @param powerMax
 * @param joueur
 * @param coutUnite 
 */
    public Unite(boolean selectionne, boolean aTirerCeTour, int degats, int powerMax, Joueur joueur, double coutUnite) {
        this.selectionne = selectionne;
        this.aTirerCeTour = aTirerCeTour;
        this.degats = degats;
        this.powerMax = powerMax;
        this.joueur = joueur;
        this.coutUnite = coutUnite;
        Color temp;
        if (joueur.getNumeroPosition() == 1) {
            temp = Color.red;
        } else {
            temp = Color.blue;
        }
        this.color = temp;
        hitbox= new Rectangle(positionX, positionY, largeur, hauteur);
    }
    /**
     * Dessine 'Select' si l'unite est selectionne
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(Graphics g) throws SlickException { 
        g.setColor(color);
        if (selectionne) {
            g.drawString("SELECT",positionX, positionY - 50);
        }
        etat.render(g);
    }
    /**
     * met a jour la hibtox de l'unite
     * @throws SlickException 
     */
    @Override
    public void update() throws SlickException {
    hitbox.setBounds(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void isRenderValide() {

    }

    public boolean isSelectionne() {
        return selectionne;
    }

    public boolean isaTirerCeTour() {
        return aTirerCeTour;
    }

    public Etat getEtat() {
        return etat;
    }
/**
 * Lorsque l'unite est selectionne par le carre de selection
 * @return 
 */
    @Override
    public String toString() {
        String s;
        if (aTirerCeTour) {
            s = "| ne peut plus tirer";
        } else {
            s = "| peut tirer";
        }

        return s;

    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getPowerMax() {
        return powerMax;
    }

    public void setaTirerCeTour(boolean aTirerCeTour) {
        this.aTirerCeTour = aTirerCeTour;
    }

    public void setSelectionne(boolean selectionne) {
        this.selectionne = selectionne;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getDegats() {
        return degats;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
   
     
    /**
     * lorsqu'un tour se termine, certains attributs d'une unite doivent etre 
     * reinitialise
     */
    public void resetTour() {
        this.setSelectionne(false);
        this.setaTirerCeTour(false);
        this.setEtat(new Immobile(this, false, this.getEtat().getDirection()));//moyen 2  
    }
/**
 * Methode a redefinir pour chaque unite. Elle serviront a lorsque l'Etat dessine l'unite
 */
    public abstract void dessineBougeGauche();

    public abstract void dessinerImmoGauche();

    public abstract void dessinerBougeDroite();

    public abstract void dessinerImmoDroite();

    public abstract void tirGauche();

    public abstract void tirDroite();


    public Color getColor() {
        return color;
    }

    public abstract int getRayonX();

    public abstract int getRayonY();
    
    public abstract void dessinerProjectile(Graphics g, float i, double positionX,double positionY);

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isProjectileTransperceCible() {
        return projectileTransperceCible;
    }

    public void setProjectileTransperceCible(boolean projectileTransperceCible) {
        this.projectileTransperceCible = projectileTransperceCible;
    }
    
    
    
    
}
