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
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mathieu
 */
public class CatapulteDefense extends Unite {

    private final Image immoDroite, immoGauche, tirGauche, tirDroite;
    private final float hauteur, largeur;
    private final int RAYONX = 16, RAYONY = 16;
    private Animation animationProjectile;
/**
 * Constructeur de la classe CatapulteDefense
 * @param joueur
 * @throws SlickException 
 */
    public CatapulteDefense(Joueur joueur) throws SlickException {
        super(false, false, Constante.DEGAT_CATAPULTE_DEFENSE, Constante.POWERMAX_CATAPULTE_DEFENSE, joueur, Constante.COUT_CATAPULTE_DEFENSE);
        this.immoDroite = ConstanteImage.getInstance().catapulteDefImmoDroite;
        this.immoGauche = ConstanteImage.getInstance().catapulteDefImmoGauche;
        this.hauteur = ResolutionImage.HAUTEUR_CATAPULTE_DEFENSE;
        this.largeur = ResolutionImage.LARGEUR_CATAPULTE_DEFENSE;
        switch (joueur.getNumeroPosition()) {
            case 1:
                this.positionX = ResolutionImage.POSITION_X1_CATAPULTE;
                this.animationProjectile = ConstanteImage.getInstance().animationProjectileCatapuleGauche;
                break;
            case 2:
                this.positionX = ResolutionImage.POSITION_X2_CATAPULTE;               
                this.animationProjectile = ConstanteImage.getInstance().animationProjectileCatapuleDroite;
        }
        this.positionY = ResolutionImage.POSITION_Y_CATAPULTE;
        super.setHitbox(new Rectangle(positionX, positionY, largeur, hauteur));
        this.tirDroite = ConstanteImage.getInstance().catapulteDefTirDroite;
        this.tirGauche = ConstanteImage.getInstance().catapulteDefTirGauche;
        super.setProjectileTransperceCible(true);
    }

    @Override
    public void dessinerProjectile(Graphics g, float i, double positionX, double positionY) {
        animationProjectile.draw((float) positionX, (float) positionY, RAYONX, RAYONY);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g);

    }

    @Override
    public String toString() {
        return "Catapule" + super.toString();
    }

    @Override
    public void dessinerImmoGauche() {
        if (joueur.getNumeroPosition() == 1) {
            return;
        } else {
            immoGauche.draw(positionX, positionY - 13, largeur, hauteur + 13);
        }
    }

    @Override
    public void dessinerImmoDroite() {
        if (joueur.getNumeroPosition() == 2) {
            return;
        } else {
            immoDroite.draw(positionX, positionY - 13, largeur, hauteur + 13);
        }
    }

    @Override
    public void dessinerBougeDroite() {
        throw new UnsupportedOperationException("Not supported"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dessineBougeGauche() {
        throw new UnsupportedOperationException("Not supported"); //To change body of generated methods, choose Tools | Templates.
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

}
