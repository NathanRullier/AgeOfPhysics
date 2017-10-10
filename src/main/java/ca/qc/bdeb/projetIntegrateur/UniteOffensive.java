/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import ca.qc.bdeb.projetIntegrateur.Etat.Direction;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
abstract public class UniteOffensive extends Unite {

    protected int pointDeVie, pointDeVieFutur;
    protected boolean renderValide = true; //si on doit dessiner ce perso (false s'il est mort
    private int nbMouvementTour = 0; //nombre de deplacement (en pixel) qu'a fait l'unite durant un tour
    private int destination = 0;
    private final int recompense;//lorsqu'un joueur elimine cet unite
    private final int mobiliteMax;
    private int mouvementFleche;

    /**
     * Constructeur de la classe UniteOffensive
     *
     * @param pointDeVie
     * @param mobiliteMax
     * @param selectionne
     * @param aTirerCeTour
     * @param degats
     * @param powerMax
     * @param joueur
     * @param coutUnite
     * @param recompense
     */
    public UniteOffensive(int pointDeVie, int mobiliteMax, boolean selectionne, boolean aTirerCeTour, int degats, int powerMax, Joueur joueur, double coutUnite, int recompense) {
        super(selectionne, aTirerCeTour, degats, powerMax, joueur, coutUnite);
        this.pointDeVie = pointDeVie;
        this.mobiliteMax = mobiliteMax;
        this.nbMouvementTour = mobiliteMax;
        this.recompense = recompense;
        this.pointDeVieFutur = pointDeVie;
        this.mouvementFleche = ResolutionImage.MOUVEMENT_FLECHE;

    }

    /**
     * S'occupe d'afficher les points de vie ainsi que le nombre de mouvement
     * restant lorsque l'unite est selectionne
     *
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g);
        if (super.isSelectionne()) {
            g.drawString("" + nbMouvementTour, positionX, (float) (ResolutionImage.POSITION_SOL * 1.025));
        }

        g.drawString("" + pointDeVie, positionX, positionY - 15);

    }

    /**
     * Cette methode sert a faire bouger les differentes unites
     *
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        super.update();
        isRenderValide();
        if (destination != 0 && nbMouvementTour >= mouvementFleche) {
            positionX = positionX + destination;
            nbMouvementTour -= mouvementFleche;
            destination -= mouvementFleche;

        } else if (destination != 0 && nbMouvementTour < mouvementFleche && nbMouvementTour > 0) {
            if (destination < 0) {
                positionX = positionX - nbMouvementTour;
                nbMouvementTour = 0;

            } else {
                positionX = positionX + nbMouvementTour;
                nbMouvementTour = 0;
            }

        }

        if (positionX < 0) {
            positionX = 0;

        } else if (positionX + largeur > ((int) (ResolutionImage.WORLD_MAP_LARGEUR))) {
            positionX = (int) (ResolutionImage.WORLD_MAP_LARGEUR - largeur);
        }
        if (nbMouvementTour == 0) {
            super.getEtat().setBouge(false);
        }

        destination = 0;

    }

    /**
     * Cette methode est appele lorsque l'uniteOffensive subit des degats. Si
     * l'unite n'a plus de point de vie, un sous est cree.
     *
     * @param unite
     */
    public void setDommageSubit(Unite unite) {
        this.pointDeVie -= unite.degats;
        if (pointDeVie <= 0 && renderValide) {
            renderValide = false;
            Sous x = new Sous(positionX, positionY, Jeu.temps, recompense);
            unite.getJoueur().elimierUnite(this);
            Constante.listesous.add(x);
        }
    }

    public int getNbMouvementTour() {
        return nbMouvementTour;
    }
/**
 *remet le mouvement a la constante de depart apres chaque tour
 */
    @Override
    public void resetTour() {
        super.resetTour();
        nbMouvementTour = mobiliteMax;
    }
/**
 * valide si l'unite doit etre dessine
 */
    @Override
    public void isRenderValide() {
        if (!renderValide) {
            Constante.listeEnlever.add(this);
        }
    }

    public void bouge() {
        Direction direction = this.getEtat().getDirection();
        switch (direction) {
            case DROITE:
                destination = mouvementFleche;
                break;
            case GAUCHE:
                destination = -mouvementFleche;
                break;
        }
        super.getEtat().setBouge(true);

    }

    public int getRecompense() {
        return recompense;
    }

    public void setNbMouvementTour(int nbMouvementTour) {
        this.nbMouvementTour = nbMouvementTour;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setPointDeVieFutur(int temp) {
        pointDeVieFutur = temp;
    }

    public int getPointDevieFutur() {
        return pointDeVieFutur;
    }

    public int getPointDevie() {
        return pointDeVie;
    }

    public void setMouvementFleche(int mouvementFleche) {
        this.mouvementFleche = mouvementFleche;
    }

}
