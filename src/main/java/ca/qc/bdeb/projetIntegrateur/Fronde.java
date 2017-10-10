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
 * @author mathieu
 */
public class Fronde extends UniteOffensive {

    private final Image immoGauche, immoDroite;
    private final Animation bougeGauche, bougeDroite, tirGauche, tirDroite;
    private final float largeurTir;
    private final int RAYONX=10, RAYONY=10;
    
/**
 * Constructeur de la classe Fronde
 * @param joueur
 * @throws SlickException 
 */
    public Fronde(Joueur joueur) throws SlickException {
        super(Constante.PV_Fronde, Constante.MOBILITE_FRONDE, false, false, Constante.DEGAT_FRONDE, Constante.POWERMAX_FRONDE, joueur, Constante.COUT_FRONDE, Constante.RECOMPENSE_FRONDE);
        this.immoDroite = ConstanteImage.getInstance().frondeImmoDroite;
        this.immoGauche = ConstanteImage.getInstance().frondeImmoGauche;
        this.bougeGauche = ConstanteImage.getInstance().animationFrondeGauche;
        this.bougeDroite = ConstanteImage.getInstance().animationFrondeDroite;
        this.tirGauche = ConstanteImage.getInstance().animationFrondeTirGauche;
        this.tirDroite = ConstanteImage.getInstance().animationFrondeTirDroite;
        this.largeurTir = ResolutionImage.LARGEUR_TIR_UNITE_OFFENSIVE;
        this.hauteur = ResolutionImage.HAUTEUR_UNITE_OFFENSIVE;
        this.largeur = ResolutionImage.LARGEUR_UNITE_OFFENSIVE;
        this.positionY= (int) (ResolutionImage.getPositionSol()-hauteur);
    }
/**
 * Dessine le projectile de la fronde
 * @param g
 * @param i
 * @param positionX
 * @param positionY 
 */
    @Override
    public void dessinerProjectile(Graphics g, float i, double positionX, double positionY) {
        g.setColor(super.getColor());
        g.fillOval((float) positionX, (float) positionY, RAYONX, RAYONY);
    }

    @Override
    public String toString() {
        return "Fronde : " + pointDeVie + " vie " + super.toString();
    }

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
        tirGauche.draw(positionX - 18, positionY, largeurTir, hauteur);
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
