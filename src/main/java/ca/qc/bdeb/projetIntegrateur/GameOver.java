/*
Cette classe est utilise lorsque la partie est termine
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author miguelgascon
 */
public class GameOver extends BasicGameState {

    private int cameraX = 0;
    private int vieJ1,vieJ2;
    private boolean nouvellePartie = false;
    private Input input;

    GameOver(int gameOver) {

    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //worldMap = Main.getEtatJeu().getWorldMap();
        input = gc.getInput();
    }
/**
 * Dessine les composants de la classe
 * @param gc
 * @param sbg
 * @param g
 * @throws SlickException 
 */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Main.getEtatJeu().getBackground1().draw(0, -250);
        g.translate(cameraX, 0);
        ResolutionImage.dessinerWorldMap(cameraX);
        ResolutionImage.dessinerNuage();
        ResolutionImage.dessinerBoutonNouvellePartie(true, cameraX);
        dessineUnite(g);
        ResolutionImage.jaugeVie(g, vieJ1, vieJ2);
    }
/**
 * Met a jour les composants de la classse
 * @param gc
 * @param sbg
 * @param i
 * @throws SlickException 
 */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        mouseListenerCamera();
        if (nouvellePartie) {
            sbg.getState(1).init(gc, sbg);
            ResolutionImage.initJauge();
            nouvellePartie = false;
            sbg.enterState(1);
        }
    }
/**
 * Detecte si la souris a ete appuie 
 * @param button
 * @param x
 * @param y 
 */
    @Override
    public void mousePressed(int button, int x, int y) {
        if (ResolutionImage.getBtnNouvellePartie().intersect(x, y,true)) {
            nouvellePartie = true;
            Constante.gameOver = false;
        }
    }

    private void mouseListenerCamera() {
        int xpos = input.getAbsoluteMouseX();
        if (xpos < 150) {
            if (cameraX >= 0) {
                cameraX = 0;
            } else if (xpos < 50) {
                cameraX += 35;
            } else if (xpos < 100 && xpos > 50) {
                cameraX += 20;
            } else if (xpos < 150 && xpos > 100) {
                cameraX += 10;
            }
        } else if (xpos > Main.getLargeur() - 150) {
            if (cameraX <= (int) ((-ResolutionImage.WORLD_MAP_LARGEUR + Main.getLargeur()))) {
                cameraX = (int) (-(ResolutionImage.WORLD_MAP_LARGEUR - Main.getLargeur()));
            } else if (xpos > Main.getLargeur() - 50) {
                cameraX -= 35;
            } else if (xpos > Main.getLargeur() - 100 && xpos < Main.getLargeur() - 50) {
                cameraX -= 20;
            } else if (xpos > Main.getLargeur() - 150 && xpos < Main.getLargeur() - 100) {
                cameraX -= 10;
            }
        }
    }

    private void dessineUnite(Graphics g) throws SlickException {
        for (Bougeable bougeable : Constante.listeBougeable) {
            bougeable.render(g);
        }
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public void setVieJ1(int vieJ1) {
        this.vieJ1 = vieJ1;
    }

    public void setVieJ2(int vieJ2) {
        this.vieJ2 = vieJ2;
    }
    
}
