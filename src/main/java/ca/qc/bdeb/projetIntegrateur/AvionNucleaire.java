/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author miguelgascon
 */
public class AvionNucleaire extends UniteOffensive {

    private final Image gauche, droite, bombe;
    private int temps = 0;
    private final int DEPLACEMENT;

    /**
     * Constructeur de la classe AvionNucleaire qui est un bonus obtenu par un
     * code de triche
     *
     * @param joueur
     */
    public AvionNucleaire(Joueur joueur) {
        super(Constante.PV_AVION, Constante.MOBILITE_AVION, false, false, Constante.DEGAT_AVION, Constante.POWERMAX_AVION, joueur, Constante.COUT_AVION, Constante.RECOMPENSE_AVION);
        this.largeur = 483;
        this.hauteur = 143;
        this.positionY = (int) (hauteur * 1.1);
        gauche = ConstanteImage.getInstance().avionGauche;
        droite = ConstanteImage.getInstance().avionDroite;
        Image bombeTemp = null;
        int temp = 0;
        switch (joueur.getNumeroPosition()) {
            case 1:
                positionX = (int) (largeur * 0.5);
                bombeTemp = ConstanteImage.getInstance().bombeDroite;
                temp = 5;
                break;
            case 2:
                positionX = (int) (ResolutionImage.WORLD_MAP_LARGEUR - largeur * 1.5);
                bombeTemp = ConstanteImage.getInstance().bombeGauche;
                temp = -5;

        }
        this.DEPLACEMENT = temp;
        bombe = bombeTemp;
        super.setProjectileTransperceCible(true);
    }

    /**
     *
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        hitbox.setBounds(positionX, positionY, largeur, hauteur);   
        if (Jeu.avionNucleaire) {
            positionX += DEPLACEMENT;
            temps++;
            if (temps % 75 == 0) {
                temps = 0;
                Projectile proj = new Projectile(this, DEPLACEMENT, 0);
                Constante.listeProjectiles.add(proj);
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Avion nucleaire : " + pointDeVie + " vie" + super.toString();
    }

    /**
     *
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        isRenderValide();
        if (super.isSelectionne() && !Jeu.avionNucleaire) {
            g.drawString("Appuyer sur Espace pour demarrer", positionX, (float) (positionY * 0.95));
        }
        this.getEtat().render(g);

    }

    /**
     * Toutes les methodes suivantes sont appelees par l'Etat qu'il represente.
     * Par exemple, dessineBougeGauche() est appele par l'Etat Immobile lorsque
     * la direction est Gauche et que le boolean bouge est a true. Cependant,
     * certaines methodes ne sont pas utilises pour cette unite.
     */
    @Override
    public void dessineBougeGauche() {

    }

    @Override
    public void dessinerImmoGauche() {
        gauche.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void dessinerBougeDroite() {

    }

    @Override
    public void dessinerImmoDroite() {
        droite.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public void tirGauche() {
        gauche.draw(positionX, positionY, largeur, hauteur);

    }

    @Override
    public void tirDroite() {
        droite.draw(positionX, positionY, largeur, hauteur);
    }

    @Override
    public int getRayonX() {
        return 30;
    }

    @Override
    public int getRayonY() {
        return 66;
    }

    /**
     * Dessine le projectile de l'avion nucleaire
     *
     * @param g
     * @param i
     * @param positionX
     * @param positionY
     */
    @Override
    public void dessinerProjectile(Graphics g, float i, double positionX, double positionY) {
        bombe.draw((float) positionX, (float) positionY, 30, 66);
    }

    /**
     * Determine si l'avion doit etre dessine
     */
    @Override
    public void isRenderValide() {
        if (positionX > ResolutionImage.WORLD_MAP_LARGEUR + largeur || positionX < -largeur) {
            Constante.listeEnlever.add(this);
            Jeu.avionNucleaire = false;
        }
    }


}
