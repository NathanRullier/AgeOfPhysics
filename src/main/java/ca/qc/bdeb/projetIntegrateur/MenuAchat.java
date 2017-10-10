/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mathi
 */
public class MenuAchat {

    private static Joueur joueur;
    private static int cameraX = 0;
    private static int cameraY = 0;
    private static Random rnd = new Random();
    private static boolean cheatCode; //si le cheat code est active

    /**
     * Dessine le menuAchat
     *
     * @param gc
     * @param sbg
     * @param g
     * @param xpos
     * @param ypos
     * @throws SlickException
     */
    public static void render(GameContainer gc, StateBasedGame sbg, Graphics g, int xpos, int ypos) throws SlickException {
        ResolutionImage.dessinerMenuAchat(cameraX, g, joueur, xpos, ypos);
    }

    /**
     * Met a jour les differents composants du MenuAchat
     *
     * @param input
     */
    public static void update(Input input) {
        evenementClavier(input);
        if (cheatCode) {
            cheatCode = joueur.cheatCode();

        }
    }

    public static void setCameraX(int cameraX) {
        MenuAchat.cameraX = cameraX;
    }

    public static void setJoueur(Joueur joueur) {
        MenuAchat.joueur = joueur;
    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * fronde.Elle valide si le joueur a assez d'argent et procede a la
     * transaction.
     *
     * @throws SlickException
     */
    public static void achatFronde() throws SlickException {
        if (joueur.peutAcheter(Constante.COUT_FRONDE)) {
            joueur.achete(Constante.COUT_FRONDE);
            Fronde fronde = new Fronde(joueur);
            ResolutionImage.placeUnite(fronde);
        }
    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * lancier.Elle valide si le joueur a assez d'argent et procede a la
     * transaction.
     *
     * @throws SlickException
     */
    public static void achatLancier() throws SlickException {
        if (joueur.peutAcheter(Constante.COUT_LANCIER)) {
            joueur.achete(Constante.COUT_LANCIER);
            Lancier lancier = new Lancier(joueur);
            ResolutionImage.placeUnite(lancier);
        }
    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * archer.Elle valide si le joueur a assez d'argent et procede a la
     * transaction.
     *
     * @throws SlickException
     */
    public static void achatArcher() throws SlickException {
        if (joueur.peutAcheter(Constante.COUT_ARCHER)) {
            joueur.achete(Constante.COUT_ARCHER);
            Archer archer = new Archer(joueur);
            ResolutionImage.placeUnite(archer);
        }

    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * catapulte defense.Elle valide si le joueur a assez d'argent et procede a
     * la transaction.
     *
     * @throws SlickException
     */
    public static void achatCatapulteDefense() throws SlickException {
        if (joueur.peutAcheter(Constante.COUT_CATAPULTE_DEFENSE) && !joueur.isaUneDefense()) {
            joueur.achete(Constante.COUT_CATAPULTE_DEFENSE);
            CatapulteDefense cat = new CatapulteDefense(joueur);
            placeTourDefense(cat);
            joueur.setaUneDefense(true);
        }

    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * catapulte attaque.Elle valide si le joueur a assez d'argent et procede a
     * la transaction.
     */
    public static void achatCatapulteAttaque() {
        if (joueur.peutAcheter(Constante.COUT_CATAPULTE_ATTAQUE)) {
            joueur.achete(Constante.COUT_CATAPULTE_ATTAQUE);
            Catapulte cat = new Catapulte(joueur);
            ResolutionImage.placeUnite(cat);
        }
    }

    /**
     * Cette methode est appele lorsque l'utilisateur clique sur le bouton
     * avion.Elle valide si le joueur a assez d'argent et procede a la
     * transaction.
     */
    public static void achatAvion() {
        if (joueur.peutAcheter(Constante.COUT_AVION)) {
            joueur.achete(Constante.COUT_AVION);
            AvionNucleaire cat = new AvionNucleaire(joueur);
            placeAvion(cat);
        }
    }

    public static Joueur getJoueur() {
        return joueur;
    }

    private static void evenementClavier(Input input) {
        if (input.isKeyDown(Input.KEY_1) && input.isKeyDown(Input.KEY_2) && input.isKeyDown(Input.KEY_3)) {
            cheatCode = true;
        }
    }

    private static void placeTourDefense(Unite unite) {
        Etat.Direction direction = null;
        switch (unite.getJoueur().getNumeroPosition()) {
            case 1:
                direction = Etat.Direction.DROITE;
                break;
            case 2:
                direction = Etat.Direction.GAUCHE;
        }
        unite.setEtat(new Immobile(unite, false, direction));
        Constante.listeBougeable.add(unite);
    }

    private static void placeAvion(Unite unite) {
        Etat.Direction direction = null;
        switch (unite.getJoueur().getNumeroPosition()) {
            case 1:
                direction = Etat.Direction.DROITE;
                break;
            case 2:
                direction = Etat.Direction.GAUCHE;
                break;
        }
        Constante.listeBougeable.add(unite);
        unite.setEtat(new Immobile(unite, false, direction));
    }

}
