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
public class Archer extends UniteOffensive {

    private final Image immoGauche, immoDroite, arcDroite, arcGauche;
    private final Animation bougeGauche, bougeDroite;
    private final int RAYONX = 10, RAYONY = 3;//pour le projectile
    
/**
 * Constructeur de la classe Archer
 * @param joueur
 * @throws SlickException 
 */
    public Archer(Joueur joueur) throws SlickException {
        super(Constante.PV_ARCHER, Constante.MOBILITE_ARCHER, false, false, Constante.DEGAT_ARCHER, Constante.POWERMAX_ARCHER, joueur, Constante.COUT_ARCHER, Constante.RECOMPENSE_ARCHER);
        this.immoDroite = ConstanteImage.getInstance().archerImmoDroite;
        this.immoGauche = ConstanteImage.getInstance().archerImmoGauche;
        this.bougeGauche = ConstanteImage.getInstance().animationArcherGauche;
        this.bougeDroite = ConstanteImage.getInstance().animationArcherDroite;
        this.arcDroite = ConstanteImage.getInstance().archerArcDroite;
        this.arcGauche = ConstanteImage.getInstance().archerArcGauche;
        this.hauteur = ResolutionImage.HAUTEUR_UNITE_OFFENSIVE;
        this.largeur = ResolutionImage.LARGEUR_UNITE_OFFENSIVE;
        this.positionY= (int) (ResolutionImage.getPositionSol()-hauteur);
    }
    /**
     * Dessine le projectile de l'unite archer
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

        return "Archer : " + pointDeVie + " vie" + super.toString();
    }

    /*
    Toutes les methodes suivantes sont appelees par l'Etat qu'il represente.
    Par exemple, dessineBougeGauche() est appele par l'Etat Immobile lorsque la direction
    est Gauche et que le boolean bouge est a true
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
        immoGauche.draw(positionX, positionY, largeur, hauteur);
        arcGauche();
    }

    @Override
    public void tirDroite() {
        immoDroite.draw(positionX, positionY, largeur, hauteur);
        arcDroite();
    }

    private void arcGauche() {
        arcGauche.setRotation(-super.getEtat().getAngle());
        arcGauche.draw(positionX, positionY + 5);
    }

    private void arcDroite() {
        arcDroite.setRotation(-super.getEtat().getAngle());
        arcDroite.draw(positionX + 18, positionY + 10);
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
