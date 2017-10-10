/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author miguelgascon
 */
public class Catapulte extends UniteOffensive {

    private final Image immoGauche, immoDroite, tirGauche, tirDroite;
    private final Animation bougeDroite, bougeGauche;
    private final int RAYONX = 12, RAYONY = 12;
    private Animation animationProjectile;

    /**
     * Constructeur de la classe Catapulte.
     *
     * @param joueur
     */
    public Catapulte(Joueur joueur) {
        super(Constante.PV_CATAPULTE_ATTAQUE, Constante.MOBILITE_CATAPULTE_ATTAQUE, false, false, Constante.DEGAT_CATAPULTE_ATTAQUE, Constante.POWERMAX_CATAPULTE_ATTAQUE, joueur, Constante.COUT_CATAPULTE_ATTAQUE, Constante.RECOMPENSE_CATAPULTE_ATTAQUE);
        super.setMouvementFleche(ResolutionImage.MOUVEMENT_FLECHE_UNITE_SIEGE);
        this.immoDroite = ConstanteImage.getInstance().catapulteAttaqueImmoDroite;
        this.immoGauche = ConstanteImage.getInstance().catapulteAttaqueImmoGauche;
        this.hauteur = ResolutionImage.HAUTEUR_CATAPULTE_ATTAQUE;
        this.largeur = ResolutionImage.LARGEUR_CATAPULTE_ATTAQUE;
        this.positionY = (int) (ResolutionImage.getPositionSol() - hauteur);
        switch (joueur.getNumeroPosition()) {
            case 1:
                this.animationProjectile = ConstanteImage.getInstance().animationProjectileCatapuleGauche;
                break;
            case 2:
                this.animationProjectile = ConstanteImage.getInstance().animationProjectileCatapuleDroite;
        }
        this.tirDroite = ConstanteImage.getInstance().catapulteAttaqueTirDroite;
        this.tirGauche = ConstanteImage.getInstance().catapulteAttaqueTirGauche;
        this.bougeDroite = ConstanteImage.getInstance().animationCatapulteAttaqueBougeDroite;
        this.bougeGauche = ConstanteImage.getInstance().animationCatapulteAttaqueBougeGauche;
        super.setProjectileTransperceCible(true);
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Catapulte " + pointDeVie + " vie " + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Toutes les methodes suivantes sont appelees par l'Etat qu'il represente.
     * Par exemple, dessineBougeGauche() est appele par l'Etat Immobile lorsque
     * la direction est Gauche et que le boolean bouge est a true
     */
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
        tirGauche.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void tirDroite() {
        tirDroite.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public int getRayonX() {
        return RAYONX;
    }

    @Override
    public int getRayonY() {
        return RAYONY;
    }
/**
 * Dessine le projectile de la catapulte
 * @param g
 * @param i
 * @param positionX
 * @param positionY 
 */
    @Override
    public void dessinerProjectile(Graphics g, float i, double positionX, double positionY) {
        animationProjectile.draw((float) positionX, (float) positionY, RAYONX, RAYONY);
    }

}
