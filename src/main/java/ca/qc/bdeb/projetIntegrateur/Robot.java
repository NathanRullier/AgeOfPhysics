package ca.qc.bdeb.projetIntegrateur;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nathan
 */
public class Robot {

    private static int compteur = 0;
    public static boolean tourFini = false;
    public static boolean achatFini;
    private static UniteOffensive unite1;
    private static UniteOffensive unite2;
    private static double positionUniteLaPlusProche;
    private static UniteOffensive uniteLaPlusProche;
    public static boolean finiDeBouger = true;
    private static double argent;
    private static final Random rn = new Random();

    /**
     * Met a jour le robot
     *
     * @throws SlickException
     */
    public static void update() throws SlickException {
        compteur++;
        argent= Main.getEtatJeu().getJoueur2().getArgent();
        if (compteur == 5) {
            System.out.println(achatFini);
            if (argent >= 50 && !achatFini) {
                robotAchete();
            } else {
                achatFini=true;
                robotLocalise();
            }
            compteur = 0;
        }

    }

    private static void robotAchete() throws SlickException {
        if (argent >= 50) {

            int unite = rn.nextInt(Constante.NOMBRE_UNITE);
            switch (unite) {
                case 0:
                    MenuAchat.achatFronde();
                    argent = argent - Constante.COUT_FRONDE;
                    break;
                case 1:
                    MenuAchat.achatArcher();
                    argent = argent - Constante.COUT_ARCHER;
                    break;
                case 2:
                    MenuAchat.achatLancier();
                    argent = argent - Constante.COUT_LANCIER;
                    break;
                case 3:
                    MenuAchat.achatCatapulteAttaque();
                    argent = argent - Constante.COUT_CATAPULTE_ATTAQUE;
                    break;

            }
        } else {
            achatFini = true;
        }
    }

    private static void robotLocalise() throws SlickException {
        positionUniteLaPlusProche = 0;

        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof UniteOffensive) {
                unite1 = (UniteOffensive) bouge;

                if (unite1.getJoueur() == Main.getEtatJeu().getJoueur2()) {
                    unite1.setSelectionne(true);

                    for (Bougeable ennemi : Constante.listeBougeable) {
                        if (ennemi instanceof UniteOffensive) {
                            unite2 = (UniteOffensive) ennemi;
                            if (unite2.getJoueur() == Main.getEtatJeu().getJoueur1()) {

                                if (unite2.getPositionX() > positionUniteLaPlusProche && unite2.getPointDevieFutur() >= 0) {
                                    positionUniteLaPlusProche = unite2.getPositionX();
                                    uniteLaPlusProche = unite2;
                                }

                            }

                        }
                        finiDeBouger = false;

                        unite1.setSelectionne(false);
                    }
                    robotBouge();
                }

            }
        }
    }

    private static void robotBouge() throws SlickException {
        Random rn = new Random();
        int signe = rn.nextInt(2);
        if (signe == 0) {
            signe = -1;
        }
        if (unite1.getPositionX() - positionUniteLaPlusProche > getDistanceTirMax(unite1)) {
            unite1.bouge();
        }  else if(positionUniteLaPlusProche==0 && !unite1.isaTirerCeTour()){
            unite1.setEtat(new Tir(unite1, false, unite1.getEtat().getDirection()));
            Tir tir = (Tir) unite1.getEtat();
            try {
                double pourcentageDistance = Math.sqrt((unite1.getPositionX() - positionUniteLaPlusProche) / getDistanceTirMax(unite1));
                tir.initProjectileRobot(100 * pourcentageDistance, -45 + (rn.nextInt(Constante.incertitudeTir)) * signe);
            } catch (SlickException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
            finiDeBouger = true;
        }else if (!unite1.isaTirerCeTour() && uniteLaPlusProche.getPointDevieFutur() >= 0) {

            unite1.setEtat(new Tir(unite1, false, unite1.getEtat().getDirection()));
            Tir tir = (Tir) unite1.getEtat();
            uniteLaPlusProche.setPointDeVieFutur(uniteLaPlusProche.getPointDevieFutur() - unite1.getDegats());
            try {
                double pourcentageDistance = Math.sqrt((unite1.getPositionX() - positionUniteLaPlusProche) / getDistanceTirMax(unite1));
                tir.initProjectileRobot(100 * pourcentageDistance, -45 + (rn.nextInt(Constante.incertitudeTir)) * signe);
            } catch (SlickException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
            finiDeBouger = true;
        }

    }

    private static int getDistanceTirMax(UniteOffensive unite) {
        int distanceTirMax = 0;
        if (unite instanceof Archer) {
            distanceTirMax = (Constante.POWERMAX_ARCHER * Constante.POWERMAX_ARCHER / 20) - (int) ((double) Constante.CONSTANTE_VENT * (2 * (double) Constante.POWERMAX_ARCHER * Math.sin(45) / 20));
        } else if (unite instanceof Fronde) {
            distanceTirMax = (Constante.POWERMAX_FRONDE * Constante.POWERMAX_FRONDE / 20) - (int) ((double) Constante.CONSTANTE_VENT * (2 * (double) Constante.POWERMAX_FRONDE * Math.sin(45) / 20));
        } else if (unite instanceof Lancier) {
            distanceTirMax = (Constante.POWERMAX_LANCIER * Constante.POWERMAX_LANCIER / 20) - (int) ((double) Constante.CONSTANTE_VENT * (2 * (double) Constante.POWERMAX_LANCIER * Math.sin(45) / 20));
        } else if (unite instanceof Catapulte) {
            distanceTirMax = (Constante.POWERMAX_CATAPULTE_ATTAQUE * Constante.POWERMAX_CATAPULTE_ATTAQUE / 20) - (int) ((double) Constante.CONSTANTE_VENT * (2 * (double) Constante.POWERMAX_CATAPULTE_ATTAQUE * Math.sin(45) / 20));
        }

        return distanceTirMax;
    }

    /**
     * Lorsque le tour se termine
     *
     * @return
     */
    public static boolean finiTour() {
        boolean finiTour = false;
        int nbrUniteAttaque = 0;
        int nbrUniteFini = 0;

        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof UniteOffensive) {
                unite1 = (UniteOffensive) bouge;

                if (unite1.getJoueur() == Main.getEtatJeu().getJoueur2()) {
                    nbrUniteAttaque++;
                    if (unite1.isaTirerCeTour() || unite1.getNbMouvementTour() <= 0) {
                        nbrUniteFini++;
                    }
                }

            }

        }
        if (nbrUniteAttaque == nbrUniteFini) {
            finiTour = true;
        }
        return finiTour;
    }
    public static void setAchatFini(){
    achatFini=false;
    }
}
