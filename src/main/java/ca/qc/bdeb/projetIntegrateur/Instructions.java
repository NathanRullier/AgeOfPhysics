/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author mathi
 */
public class Instructions extends BasicGameState {

    private final int STATE;
    private boolean retour;
    private Input input;
    private boolean instruction = false, bouger = false, attaquer = false;
    private int compteur, i;
    private int consigne;

    public Instructions(int state) {
        this.STATE = state;
    }

    @Override
    public int getID() {
        return STATE;
    }

    /**
     * Initialise les composants de la classe
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        retour = false;
        input = gc.getInput();
        i = 0;
        compteur = 0;
    }

    /**
     * Dessine les composants de la classe
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        int xpos = input.getAbsoluteMouseX();
        int ypos = input.getAbsoluteMouseY();
        if (!instruction && !bouger && !attaquer) {
            ResolutionImage.getBackgroundInstruction().render(0);
            g.setColor(Color.black);
            ResolutionImage.getBtnRetour().intersect(xpos, ypos, false);
            ResolutionImage.getBtnRetour().render(0);
            ResolutionImage.getBtnBouger().intersect(xpos, ypos, false);
            ResolutionImage.getBtnBouger().render(0);
            ResolutionImage.getBtnAttaquer().intersect(xpos, ypos, false);
            ResolutionImage.getBtnAttaquer().render(0);
            ResolutionImage.getBtnInstruction().intersect(xpos, ypos, false);
            ResolutionImage.getBtnInstruction().render(0);
        } else if (bouger || attaquer) {
            
            
            compteur++;
            if (consigne == 0) {
                ResolutionImage.getBackgroundTutoriel().render(0);
                ResolutionImage.getParchemin().render(0);
                g.setColor(Color.black);
                ResolutionImage.dessinerStringParchemin(g, consigne);
                g.setColor(Color.yellow);
                g.drawRect(ResolutionImage.getPositionXRectangle(), ResolutionImage.getPositionYRectangle(), ResolutionImage.getLargeurRectangle(), ResolutionImage.getHauteurRectangle());
                ResolutionImage.dessinerCurseur(compteur);
                if (compteur == 60) {

                    i++;
                    compteur = 0;

                    ResolutionImage.setHauteurRectangle(ResolutionImage.getHauteurRectangle() + ResolutionImage.getConstanteY() * 5 / 2 * i);
                    ResolutionImage.setLargeurRectangle(ResolutionImage.getLargeurRectangle() + ResolutionImage.getConstanteX() * 2 * i);

                    if (i == 7) {
                        ResolutionImage.setHauteurRectangle(0);
                        ResolutionImage.setLargeurRectangle(0);

                        i = 0;
                    }

                }
            } else {
                ResolutionImage.getBackgroundVideTutoriel().render(0);
            }
                        switch (consigne) {
                case 1:
                    ResolutionImage.getParchemin().render(0);
                    g.setColor(Color.black);
                    ResolutionImage.dessinerStringParchemin(g, consigne);
                    ResolutionImage.dessinerAnimationTutoriel1Attaque();
                    break;
                case 2:
                    ResolutionImage.getParchemin().render(0);
                    g.setColor(Color.black);
                    ResolutionImage.dessinerStringParchemin(g, consigne);
                    ResolutionImage.dessinerAnimationTutoriel2Attaque();
                    ResolutionImage.dessinerFleche(g);
                    break;
                case 3:
                    ResolutionImage.dessinerAnimationTutoriel4Attaque(g);
                    ResolutionImage.getParchemin().render(0);
                    g.setColor(Color.black);
                    ResolutionImage.dessinerStringParchemin(g, consigne);
                    
                    break;
                    
                case 4:
                    
                    ResolutionImage.getParchemin().render(0);
                    g.setColor(Color.black);
                    ResolutionImage.dessinerStringParchemin(g, consigne);
                    ResolutionImage.dessinerAnimationTutorielBouger(g);
                    
                    break;
                    
               
                   
            }
            ResolutionImage.getBtnRetour().intersect(xpos, ypos, false);
            ResolutionImage.getBtnRetour().render(0);
            if (consigne != 3&&consigne!=4) {
          
                ResolutionImage.getKeyRight().intersect(xpos, ypos, false);
                ResolutionImage.getSpriteFlecheRight().intersect(xpos, ypos, false);
                ResolutionImage.getKeyRight().render(0);
                 ResolutionImage.getSpriteFlecheRight().render(0);
            }
            if (consigne != 0) {

                ResolutionImage.getKeyLeft().intersect(xpos, ypos, false);
                ResolutionImage.getSpriteFlecheLeft().intersect(xpos, ypos, false);
                ResolutionImage.getKeyLeft().render(0);
                ResolutionImage.getSpriteFlecheLeft().render(0);
            }


        }
    }

    /**
     * Met a jour les composants de la classe
     *
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (retour) {

            if (!bouger && !attaquer) {

                sbg.getState(0).init(gc, sbg);
                sbg.enterState(0);
            } else {

                compteur = 0;
                i = 0;
                ResolutionImage.setHauteurRectangle(0);
                ResolutionImage.setLargeurRectangle(0);
                bouger = false;
                attaquer = false;
                retour = false;
            }
        }
    }

    /**
     * Detecte si la souris a ete appuie
     *
     * @param button
     * @param x
     * @param y
     */
    @Override
    public void mousePressed(int button, int x, int y) {
        if (ResolutionImage.getBtnRetour().intersect(x, y, true)) {
            retour = true;

        }
        if (!bouger && !attaquer && !instruction) {
            if (ResolutionImage.getBtnAttaquer().intersect(x, y, true)) {
                attaquer = true;
                consigne = 0;
            } else if (ResolutionImage.getBtnBouger().intersect(x, y, true)) {
                bouger = true;
                consigne = 0;
            } else if (ResolutionImage.getBtnInstruction().intersect(x, y, true)) {
                instruction = true;
            }
        } else if (bouger || attaquer) {
            if (ResolutionImage.getKeyRight().intersect(x, y, true)) {

                if (consigne == 0) {

                    if (bouger) {
                        consigne = 4;
                    } else if (attaquer) {
                        consigne = 1;

                    }
                } else if (consigne == 3||consigne==4) {

                    
                } else {

                    consigne = consigne +1;
                }

            } else if (ResolutionImage.getKeyLeft().intersect(x, y, true)) {
                if (consigne == 4||consigne==1) {
                    compteur = 0;
                    i = 0;
                    ResolutionImage.setHauteurRectangle(0);
                    ResolutionImage.setLargeurRectangle(0);
                    consigne = 0;
                }
                if (consigne == 0) {

                } else {
                    consigne = consigne -1;
                }

            }
        }
    }

}
