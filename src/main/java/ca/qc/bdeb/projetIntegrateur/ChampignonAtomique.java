/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author mathieu
 */
public class ChampignonAtomique extends Projectile {

    private final Image champignon;

    /**
     * Constructeur de la classe ChampignonAtomique qui apparait lorsque de
     * projectile de l'avion nucleaire touche le sol.
     *
     * @param unite
     * @param positionX
     */
    public ChampignonAtomique(Unite unite, double positionX) {
        super(unite, 0, 0);
        champignon = ConstanteImage.getInstance().champignionAtomique;
        x1 = positionX - ResolutionImage.LARGEUR_CHAMPIGON_ATOMIQUE/2;
        y1 = (float) ResolutionImage.POSITION_SOL - ResolutionImage.HAUTEUR_CHAMPIGON_ATOMIQUE;
        super.setRayonX(ResolutionImage.LARGEUR_CHAMPIGON_ATOMIQUE);
        super.setRayonY(ResolutionImage.HAUTEUR_CHAMPIGON_ATOMIQUE);

    }

    /**
     * Dessine le champigon atomique
     * @param g
     */
    @Override
    public void render(Graphics g) {
        isRenderValide();
        champignon.draw((float) x1, (float) y1,ResolutionImage.LARGEUR_CHAMPIGON_ATOMIQUE ,ResolutionImage.HAUTEUR_CHAMPIGON_ATOMIQUE);
    }


    /**
     * S'occupe de determiner si le champigon doit etre dessiner. Il doit etre
     * dessine seulement un certain temps.
     */
    @Override
    public void isRenderValide() {

        if (super.getTemps() > 10) {
            Constante.listeEnlever.add(this);
        }
    }

}
