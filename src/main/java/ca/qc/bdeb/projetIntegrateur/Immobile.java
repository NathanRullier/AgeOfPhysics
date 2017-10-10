/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathi
 */
public class Immobile extends Etat {

    public Immobile(Unite unite, boolean bouge, Direction direction) {
        super(unite, bouge, direction);

    }
/**
 * Dessine les unités qui sont dans l'état immobile
 * @param g 
 */
    @Override
    public void render(Graphics g) {

        if (super.getDirection() == Direction.GAUCHE) {
            if (bouge) {
                unite.dessineBougeGauche();
            } else {
                unite.dessinerImmoGauche();
            }
        } else if (bouge) {
            unite.dessinerBougeDroite();
        } else {
            unite.dessinerImmoDroite();
        }

        this.bouge = false;

    }
/**
 * Lorsque l'utilisateur appuie sur la barre d'espace
 */
    @Override
    public void space() {

        if (unite.isSelectionne()) {
            if (!unite.isaTirerCeTour()) {
                try {
                    //si appuie sur la spacebarre
                    unite.setEtat(new Tir(unite, false, unite.getEtat().getDirection()));
                } catch (SlickException ex) {
                    Logger.getLogger(Immobile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
/**
 * Lorsque l'utilisateur appuie sur les fleches
 */
    @Override
    public void fleche() {
        if (unite instanceof UniteOffensive) {

            UniteOffensive x = (UniteOffensive) unite;
            if ((x.getJoueur().isSonTour())) {
                if (x.isSelectionne()) {
                    if (!x.isaTirerCeTour()) {
                        x.bouge();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "etat immobile";
    }
/**
 * Cette methode n'est pas necessaire pour cet Etat
 * @return 
 */
    @Override
    public float getAngle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
