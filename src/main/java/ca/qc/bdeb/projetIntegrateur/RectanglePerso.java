/*
Cette classe sert seulement pour le carre de selection. Selon notre analyse, la classe
Rectangle de slick2d contenait une erreur dans sa fonction contains. Nous avons donc cree une
nouvelle classe et reecrit la methode en question
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author mathi
 */
public class RectanglePerso extends Rectangle {
    
/**
 * Constructeur de la classe RectanglePerso
 * @param x
 * @param y
 * @param width
 * @param height 
 */
    public RectanglePerso(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
/**
 * Sert a determiner si l'autre hitbox se trouve a l'interieur du carre de selection
 * @param other
 * @return 
 */
    @Override
    public boolean contains(Shape other) {
        boolean x = false;
        if (other.getCenterX() > this.minX) {
            if (other.getCenterX() < this.maxX) {
                if (other.getCenterY() > this.minY) {
                    if (other.getCenterY() < this.maxY) {
                        x = true;

                    }
                }
            }
        }

        return x;
    }

}
