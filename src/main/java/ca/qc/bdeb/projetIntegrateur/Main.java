package ca.qc.bdeb.projetIntegrateur;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.StateBasedGame;

/**
 *
 */
public class Main extends StateBasedGame {

    GameContainer container;
    private static final String nomJeu = "Projet Integrateur ";
    private static final int menu = 0;
    private static final int jeu = 1;
    private static final int gameOver = 2;
    private static final int instructions =3;
    private static int hauteur;
    private static int largeur;
    private static AppGameContainer app;
    private static MenuPrincipal etatMenu;
    private static Jeu etatJeu;
    private static GameOver etatGameOver;
    private static Instructions tuto;

/**
 * Constructeur de la classe Main
 * @param s
 * @throws SlickException 
 */
    public Main(String s) throws SlickException {
        super(s);
        }
/**
 * Main qui initialise le thread principal 
 * @param args
 * @throws SlickException 
 */
    public static void main(String args[]) throws SlickException {
        try {
            app = new AppGameContainer(new Main(nomJeu));
            largeur = app.getScreenWidth();
            hauteur = app.getScreenHeight();
            app.setTargetFrameRate(60);
            app.setDisplayMode(largeur, hauteur, false);
            app.setShowFPS(true);
            app.start();

        } catch (SlickException e) {

        }

    }

    @Override
    public void keyReleased(int key, char c) {//ajout d'evenement
        if (Input.KEY_ESCAPE == key) {
            
            
        }
    }
/**
 * initialise tous les composants du jeu
 * @param gc
 * @throws SlickException 
 */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
      //  gc.setVSync(true);
     
        //pour load les images des differents perso (sinon ya un null pointer exception)
        ResolutionImage.initialiserImage();
        ConstanteImage.initSingleTon();
        etatMenu = new MenuPrincipal(menu);
        etatJeu=new Jeu(jeu);
        etatGameOver = new GameOver(gameOver);
        tuto = new Instructions(instructions);
        this.addState(etatMenu);
        this.addState(etatJeu);
        this.addState(etatGameOver);
        this.addState(tuto);
        this.getState(jeu).init(gc, this);
        this.getState(menu).init(gc, this);
        this.getState(gameOver).init(gc, this);
        this.getState(instructions).init(gc, this);
        this.enterState(menu);
    }

    public static int getLargeur() {
        return largeur;
    }

    public static int getHauteur() {
        return hauteur;
    }

    public static AppGameContainer getApp() {
        return app;
    }

    public static Jeu getEtatJeu() {
        return etatJeu;
    }

    public static GameOver getEtatGameOver() {
        return etatGameOver;
    }

 

   

    
}
