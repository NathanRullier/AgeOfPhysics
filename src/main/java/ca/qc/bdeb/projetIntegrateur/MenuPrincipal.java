/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import static jdk.nashorn.tools.ShellFunctions.input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mathieu
 */
public class MenuPrincipal extends BasicGameState {

    private static String nom1, nom2; //demander le nom des deux joueur lorsque le jeux commence
    private boolean newGame = false; // si l'utilisateur clique sur new Game
    private boolean choixJoueur = false;
    private boolean instruction = false;
    private boolean vsBot = false;
    private static int etat = 0;
    private static TextField txtNom;
    private static UnicodeFont font;
    private Music background;
    private Sound click;
    private final int STATE;
    private Input input;
    private int xpos, ypos;
/**
 * Constructeur de la classe MenuPrincipal
 * @param state 
 */
    public MenuPrincipal(int state) {
        this.STATE = state;
    }

    @Override
    public int getID() {
        return STATE;
    }
/**
 * Initialise les differents composants de la classe
 * @param gc
 * @param sbg
 * @throws SlickException 
 */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //background = new Music("sounds/GameOfThrones_HBO_.ogg");
        input = gc.getInput();
        choixJoueur = false;
        newGame = false;
        instruction = false;
        //background= new Music("sounds/GameOfThrones_HBO_.ogg");
        //click = new Sound("sounds/Click2.ogg");
        //background.loop();

        //background.setVolume(0.2f);
    }
/**
 * Dessine les differents boutons et image 
 * @param gc
 * @param sbg
 * @param g
 * @throws SlickException 
 */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        xpos = input.getAbsoluteMouseX();
        ypos = input.getAbsoluteMouseY();
        ResolutionImage.dessinerBackgroundMenuPrincipal();
        if (!newGame) {
            ResolutionImage.getBtnNouvellePartie().intersect(xpos, ypos, false);
            ResolutionImage.getBtnInstruction().intersect(xpos, ypos, false);
            ResolutionImage.dessinerBoutonNouvellePartie(false, 0);
            ResolutionImage.getBtnInstruction().render(0);
        } else if (vsBot) {
            ResolutionImage.getBtnDifficulte1().intersect(xpos, ypos, false);
            ResolutionImage.getBtnDifficulte2().intersect(xpos, ypos, false);
            ResolutionImage.getBtnDifficulte3().intersect(xpos, ypos, false);
            ResolutionImage.getBtnDifficulte4().intersect(xpos, ypos, false);
            ResolutionImage.getBtnDifficulte5().intersect(xpos, ypos, false);
            ResolutionImage.getBtnRetour().intersect(xpos, ypos, false);
            ResolutionImage.getBtnDifficulte1().render(0);
            ResolutionImage.getBtnDifficulte2().render(0);
            ResolutionImage.getBtnDifficulte3().render(0);
            ResolutionImage.getBtnDifficulte4().render(0);
            ResolutionImage.getBtnDifficulte5().render(0);
            ResolutionImage.getBtnRetour().render(0);
        } else if (newGame) {
            ResolutionImage.getBtn1Joueur().intersect(xpos, ypos, false);
            ResolutionImage.getBtn2Joueur().intersect(xpos, ypos, false);
            ResolutionImage.getBtnRetour().intersect(xpos, ypos, false);
            ResolutionImage.getBtn1Joueur().render(0);
            ResolutionImage.getBtn2Joueur().render(0);
            ResolutionImage.getBtnRetour().render(0);
        }

    }
/**
 * Met a jour l'image, si un bouton a ete appuie
 * @param gc
 * @param sbg
 * @param i
 * @throws SlickException 
 */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (newGame && choixJoueur) {
            //background.stop();
            sbg.enterState(1);//passe a l'etat jeux
        }

        if (instruction) {
           // background.pause();
            sbg.getState(3).init(gc, sbg);
            sbg.enterState(3);//passe a l'etat tuto

        }

    }
/**
 * Methode qui detecte si la souris a ete clique
 * @param button
 * @param x
 * @param y 
 */
    @Override
    public void mousePressed(int button, int x, int y) {
        //click.play();
        if (!newGame) {
            if (ResolutionImage.getBtnNouvellePartie().intersect(x, y, true)) {
                //click.play();
                newGame = true;
            } else if (ResolutionImage.getBtnInstruction().intersect(x, y, true)) {
                instruction = true;
                //click.play();
            }
        } else if (vsBot) {
            if (ResolutionImage.getBtnDifficulte1().intersect(x, y, true)) {
                Constante.incertitudeTir = 5;
                choixJoueur = true;
            } else if (ResolutionImage.getBtnDifficulte2().intersect(x, y, true)) {
                Constante.incertitudeTir = 4;
                choixJoueur = true;
            } else if (ResolutionImage.getBtnDifficulte3().intersect(x, y, true)) {
                Constante.incertitudeTir = 3;
                choixJoueur = true;
            } else if (ResolutionImage.getBtnDifficulte4().intersect(x, y, true)) {
                Constante.incertitudeTir = 2;
                choixJoueur = true;
            } else if (ResolutionImage.getBtnDifficulte5().intersect(x, y, true)) {
                Constante.incertitudeTir = 1;
                choixJoueur = true;
            } else if (ResolutionImage.getBtnRetour().intersect(x, y, true)) {
                vsBot=false;
            }

        } else if (newGame) {
            if (newGame && ResolutionImage.getBtnRetour().intersect(x, y, true)) {
                newGame = false;
                // click.play();
            } else if (ResolutionImage.getBtn2Joueur().intersect(x, y, true)) {
                choixJoueur = true;
                //click.play();
                Jeu.robotJoue = false;
            } else if (ResolutionImage.getBtn1Joueur().intersect(x, y, true)) {
                Jeu.robotJoue = true;
                //click.play();
                vsBot = true;

            }
        }

    }

}
