/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author miguelgascon
 */
public class Lancier extends UniteOffensive {

    private final Image immoGauche, immoDroite, tirDroite, tirGauche;
    private final Animation bougeGauche, bougeDroite;
    private final float largeurTir;
    private final int RAYONX = 40, RAYONY = 3;
    private final Image LANCE;

    /**
     * Constructeur de la classe Lancier
     *
     * @param joueur
     * @throws SlickException
     */
    public Lancier(Joueur joueur) throws SlickException {
        super(Constante.PV_LANCIER, Constante.MOBILITE_LANCIER, false, false, Constante.DEGAT_LANCIER, Constante.POWERMAX_LANCIER, joueur, Constante.COUT_LANCIER, Constante.RECOMPENSE_LANCIER);
        this.immoDroite = ConstanteImage.getInstance().lancierImmoDroite;
        this.immoGauche = ConstanteImage.getInstance().lancierImmoGauche;
        this.bougeGauche = ConstanteImage.getInstance().animationLancierGauche;
        this.bougeDroite = ConstanteImage.getInstance().animationLancierDroite;
        this.tirDroite = ConstanteImage.getInstance().lancierTirDroite;
        this.tirGauche = ConstanteImage.getInstance().lancierTirGauche;
        this.largeurTir = ResolutionImage.LARGEUR_TIR_UNITE_OFFENSIVE;
        this.LANCE = ResolutionImage.getLance();
        this.hauteur = ResolutionImage.HAUTEUR_UNITE_OFFENSIVE;
        this.largeur = ResolutionImage.LARGEUR_UNITE_OFFENSIVE;
        this.positionY = (int) (ResolutionImage.getPositionSol() - hauteur);
        
    }
/**
 * Dessine le projectile du lancier
 * @param g
 * @param i
 * @param positionX
 * @param positionY 
 */
    @Override
    public void dessinerProjectile(Graphics g, float i, double positionX, double positionY) {
        LANCE.setRotation((float) i);
        LANCE.draw((float) positionX, (float) positionY, RAYONX, RAYONY);
    }

    @Override
    public String toString() {
        return "Lancier : " + pointDeVie + " vie" + super.toString();
    }

    /**
     * Toutes les methodes suivantes sont appelees par l'Etat qu'il represente.
     * Par exemple, dessineBougeGauche() est appele par l'Etat Immobile lorsque
     * la direction est Gauche et que le boolean bouge est a true
     **/
    @Override
    public void dessineBougeGauche() {
        bougeGauche.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void dessinerImmoGauche() {
        immoGauche.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void dessinerBougeDroite() {
        bougeDroite.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void dessinerImmoDroite() {
        immoDroite.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void tirGauche() {
        tirGauche.draw(positionX, positionY, largeurTir, hauteur);
    }

    @Override
    public void tirDroite() {
        tirDroite.draw(positionX - 18, positionY, largeurTir, hauteur);
    }

    @Override
    public int getRayonX() {
        return RAYONX;
    }

    @Override
    public int getRayonY() {
        return RAYONY;
    }

}
