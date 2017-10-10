/*
Cette classe constitue l'element central de notre programme
 */
package ca.qc.bdeb.projetIntegrateur;

import ca.qc.bdeb.projetIntegrateur.Etat.Direction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ca.qc.bdeb.projetIntegrateur.Constante.TypeForceVent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 *
 * @author mathieu
 */
public class Jeu extends BasicGameState {

    public static boolean robotJoue = false;
    public static boolean avionNucleaire = false;
    public static boolean pause = false;
    private boolean etatMenu = true;//affiche le menu achat - false pour bouger
    private static int cameraX = 0;
    private final int cameraY = 0;
    private int positionCloud = 0;
    private Joueur joueur1, joueur2;
    private int tempsTir = 0;//pour le tir
    private TypeForceVent forceDuVent = TypeForceVent.moyen;
    private Random rnd = new Random();
    private boolean compteur = false;
    private Rectangle jaugeTir;
    private Unite uniteTir;
    private double poucentage;
    public static NumberFormat nf = new DecimalFormat("0");
    private Input input;
    private boolean tirEnCours;
    public static double temps;
    private final int STATE;

    public Jeu(int state) {
        this.STATE = state;
    }

    @Override
    public int getID() {
        return STATE;
    }

    /**
     * initialise les composants
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        tirEnCours = false;
        input = gc.getInput();
        calculVent();
        Constante.listeBougeable.clear();
        Constante.listeEnlever.clear();
        Constante.listeJoueur.clear();
        Constante.listeProjectiles.clear();
        etatMenu = true;
        cameraX = 0;
        joueur1 = new Joueur(Constante.nomJ1, 1, true);
        if (robotJoue) {
            joueur2 = new Joueur("robot", 2, false);
        } else {
            joueur2 = new Joueur(Constante.nomJ2, 2, false);
        }
        MenuAchat.setJoueur(joueur1);//joueur 1 qui commence
        Constante.listeJoueur.add(joueur1);
        Constante.listeJoueur.add(joueur2);
        jaugeTir = new Rectangle(0, 0, 0, 0);
    }

    /**
     * Dessine tous les composants du jeu
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
        affichageComposantConstant(g);
        gestionComposantBougeable(g);
        //fin des trucs de base
        if (etatMenu) {
            MenuAchat.setCameraX(cameraX);
            MenuAchat.render(gc, sbg, g, xpos, ypos);
        }
        if (!etatMenu && !tirEnCours && !pause) {//bouton termine en haut a gauche
            if (Robot.finiTour()) {
                ResolutionImage.getBtnTerminerTour().intersect(xpos, ypos, false);
                ResolutionImage.getBtnTerminerTour().render(cameraX);
            } else if (robotJoue && joueur1.isSonTour()) {
                ResolutionImage.getBtnTerminerTour().intersect(xpos, ypos, false);
                ResolutionImage.getBtnTerminerTour().render(cameraX);
            } else if (!robotJoue) {
                ResolutionImage.getBtnTerminerTour().intersect(xpos, ypos, false);
                ResolutionImage.getBtnTerminerTour().render(cameraX);
            }

        }
        if (CarreSelection.affiche && !tirEnCours) {
            CarreSelection.render(g);
        }
        if (robotJoue && joueur2.isSonTour()) {
            etatMenu = false;
            Robot.update();
        }

        if (compteur) {
            jaugeTir(g);
        }
        ResolutionImage.vent(g, cameraX);
        if (pause) {
            ResolutionImage.getBtnQuitter().intersect(xpos, ypos, false);
            ResolutionImage.getBtnQuitter().render(cameraX);
            ResolutionImage.getBtnReprendre().intersect(xpos, ypos, false);
            ResolutionImage.getBtnReprendre().render(cameraX);
        }
    }

    /**
     * mets a jour tous les composants du jeu
     *
     * @param gc
     * @param sbg
     * @param delta
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!pause) {
            temps += delta / 1000.0;
            mouseListeneurCamera();
            if (!etatMenu) {//bouge les unites
                evenementClavier();
            }

            if (compteur) {
                tempsTir++;
            }

            if (etatMenu) {
                MenuAchat.update(input);
            }
            if (CarreSelection.affiche && !tirEnCours) {
                CarreSelection.update();
            }

            ResolutionImage.nuage();//calcul de la position des nuages selon le vent
            updateUnite();//met a jour la position des unites et des projectiles

            if (Constante.gameOver) {
                //ici pour afficher les stats ou l'ecran final lorsqu'un joueur est n'a plus de vie
                for (Bougeable bougeable : Constante.listeBougeable) {
                    if (bougeable instanceof Projectile) {
                        Constante.listeEnlever.add(bougeable);//supprime les projectiles restant
                    }
                }
                Main.getEtatGameOver().setCameraX(cameraX);
                Main.getEtatGameOver().setVieJ1(joueur1.getVie());
                Main.getEtatGameOver().setVieJ2(joueur2.getVie());
                sbg.enterState(2);

            }

        }
    }

    private void mouseListeneurCamera() {//evenement pour deplacer la camera
        int xpos = input.getAbsoluteMouseX();
        int ypos = input.getAbsoluteMouseY();
        //System.out.println("x : " + xpos + "  Y: " + ypos);
        //System.out.println("CameraX" + cameraX);

        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
                Unite unite = (Unite) bouge;
                if (unite.getEtat() instanceof Tir) {//si l'unite tir on lui donne la position de la souris pour la fleche et de la camera
                    Tir temp = (Tir) unite.getEtat();
                    temp.setSourisX(xpos);
                    temp.setSourisY(ypos);
                    temp.setCameraX(cameraX);
                }
            }
        }

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

    /**
     * Detecte si la souris a ete deplace en appuyant. Cette methode est utile
     * pour le carre de selection.
     *
     * @param oldx
     * @param oldy
     * @param newx
     * @param newy
     */
    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if (!etatMenu) {
            CarreSelection.hauteur = newy - CarreSelection.y1;
            CarreSelection.largeur = newx - CarreSelection.x1 - cameraX;
            CarreSelection.cameraX = cameraX;

        }
    }

    /**
     * Detecte si la souris a eta relache. Cette methode est utile pour la jauge
     * de tir.
     *
     * @param button
     * @param x
     * @param y
     */
    @Override
    public void mouseReleased(int button, int x, int y) {
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
                Unite unite = (Unite) bouge;
                if (unite.getEtat() instanceof Tir) {
                    Tir tir = (Tir) unite.getEtat();
                    try {
                        tir.initProjectile(poucentage);

                    } catch (SlickException ex) {
                        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        compteur = false;
        tempsTir = 0;
        try {
            CarreSelection.reset();
        } catch (SlickException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cette methode detecte si la souris a ete clique
     *
     * @param button
     * @param x
     * @param y
     */
    @Override
    public void mousePressed(int button, int x, int y) {
        if (!etatMenu && !avionNucleaire) {
            CarreSelection.affiche = true;
            CarreSelection.x1 = x - cameraX;
            CarreSelection.y1 = y;
            isTir();

        }
        try {
            if (etatMenu) {
                if (ResolutionImage.getBtnFronde().intersect(x, y, true)) {//mettre des constantes

                    //pour acheter la fronde
                    MenuAchat.achatFronde();
                } else if (ResolutionImage.getBtnLancier().intersect(x, y, true)) {
                    //pour acheter lancier
                    MenuAchat.achatLancier();// Les images ne sont pas toutes faites
                } else if (ResolutionImage.getBtnArcher().intersect(x, y, true)) {
                    //pour acheter lancier
                    MenuAchat.achatArcher();// Les images ne sont pas toutes faites
                } else if (ResolutionImage.getBtnCatapuleDefense().intersect(x, y, true)) {
                    MenuAchat.achatCatapulteDefense();
                } else if (ResolutionImage.getBtnCatapulteAttaque().intersect(x, y, true)) {
                    MenuAchat.achatCatapulteAttaque();
                } else if (ResolutionImage.getBtnAvionNucleaire().intersect(x, y, true)) {
                    MenuAchat.achatAvion();
                }

                if (ResolutionImage.getBtnTerminerAchat().intersect(x, y, true)) {
                    etatMenu = false; // ferme le menu
                }
            }

            if (!etatMenu && !tirEnCours) {
                if (ResolutionImage.getBtnTerminerTour().intersect(x, y, true)) {//bouton termier en haut a gauche + tour suivant
                    changementTour();
                }
                if (pause) {
                    if (ResolutionImage.getBtnQuitter().intersect(x, y, true)) {//bouton termier en haut a gauche + tour suivant
                        System.exit(0);
                    }
                    if (ResolutionImage.getBtnReprendre().intersect(x, y, true)) {//bouton termier en haut a gauche + tour suivant
                        pause = false;
                    }
                }

            }
        } catch (SlickException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void evenementClavier() throws SlickException {//space barre pour tirer ?
        Direction direction = null;
        if (input.isKeyDown(Input.KEY_LEFT)) {
            direction = Direction.GAUCHE;
            bougeUnite(direction);

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            direction = Direction.DROITE;
            bougeUnite(direction);
        } else if (input.isKeyDown(Input.KEY_ESCAPE)) {

            pause = true;
        }

    }

    private void changementTour() throws SlickException {
        temps = 0;
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof UniteOffensive) {
                ((UniteOffensive) bouge).setPointDeVieFutur(((UniteOffensive) bouge).getPointDevie());
            }
        }

        for (Joueur joueur : Constante.listeJoueur) {
            joueur.changementTour();
            Robot.setAchatFini();//remplace le gros bout de code en commentaire
        }

        etatMenu = true;
        calculVent();
        //reset les attributs
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
                ((Unite) bouge).resetTour();
            } else if (bouge instanceof Projectile) {
                Constante.listeEnlever.add(bouge);
            }
        }
    }

    private void bougeUnite(Direction direction) throws SlickException {
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof UniteOffensive) {
                UniteOffensive unite = (UniteOffensive) bouge;
                unite.getEtat().setDirection(direction);
                unite.getEtat().fleche();
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!etatMenu) {
            switch (key) {
                case Input.KEY_SPACE:
                    for (Bougeable bouge : Constante.listeBougeable) {
                        if (bouge instanceof Unite) {
                            Unite unite = (Unite) bouge;
                            unite.getEtat().space();
                        }
                    }
                    break;

            }
        }
    }

    private void isTir() {
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
                Unite unite = (Unite) bouge;
                if (unite.getEtat() instanceof Tir) {
                    unite.getEtat().setFlecheBouge(false);
                    compteur = true;
                    uniteTir = unite;
                    CarreSelection.affiche = false;
                }
            }
        }

    }

    private void gestionComposantBougeable(Graphics g) throws SlickException {//bouge les unite 
        for (Bougeable bouge : Constante.listeBougeable) {
            bouge.render(g);
        }
        Constante.listeBougeable.addAll(Constante.listesous);
        Constante.listeBougeable.addAll(Constante.listeProjectiles);
        Constante.listeProjectiles.clear();
        Constante.listesous.clear();
        Constante.listeBougeable.removeAll(Constante.listeEnlever);//enleve les morts apres avoir parcourue la liste
        Constante.listeEnlever.clear();
    }

    private void affichageComposantConstant(Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.translate(cameraX, cameraY);
        ResolutionImage.dessinerWorldMap(cameraX);
        ResolutionImage.dessinerNuage();
        ResolutionImage.jaugeVie(g, joueur1.getVie(), joueur2.getVie());
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    private void jaugeTir(Graphics g) {
        jaugeTir.setBounds(uniteTir.getPositionX(), uniteTir.positionY - 100, 50, (float) poucentage * -2);

        poucentage = (tempsTir / 250.0) * 100.0;
        if (poucentage < 33) {
            g.setColor(Color.red);
        } else if (poucentage >= 33) {
            g.setColor(Color.yellow);
        }
        if (poucentage >= 66) {
            g.setColor(Color.green);
        }
        if (poucentage >= 100) {
            poucentage = 100;
        }
        String s = nf.format(poucentage);

        g.fill(jaugeTir);
        g.setColor(Color.black);
        g.drawString(" " + s + " %", uniteTir.getPositionX(), uniteTir.positionY - 100);

    }

    private void updateUnite() {

        for (Bougeable bouge : Constante.listeBougeable) {
            try {
                bouge.update();
                if (bouge instanceof Projectile || avionNucleaire) {
                    tirEnCours = true;
                } else {
                    tirEnCours = false;
                    Sous.resetCompteur();
                }
                if (bouge instanceof Sous) {
                    Sous x = (Sous) bouge;
                    x.setTempsActuel(temps);
                }
            } catch (SlickException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Constante.listeBougeable.isEmpty()) {
            tirEnCours = false;
        }
    }

    private void calculVent() {//a chaque changement de tour
        int mouvementNuage = 0; //nombre que les nuages se deplacent a cause du vent
        int choix = 0;
        choix = rnd.nextInt(8);
        switch (choix) {//pour la force du vent           
            case 0:
                Constante.forceDuVent = Constante.TypeForceVent.très_faible;
                Constante.CONSTANTE_VENT = 5;
                mouvementNuage = 1;
                break;
            case 1:
            case 2:
            case 3://plus de cchtionnge que le vent soit faible que tres fort
                Constante.forceDuVent = Constante.TypeForceVent.faible;
                Constante.CONSTANTE_VENT = 10;
                mouvementNuage = 3;
                break;
            case 4:
            case 5:
                Constante.forceDuVent = Constante.TypeForceVent.moyen;
                Constante.CONSTANTE_VENT = 15;
                mouvementNuage = 5;
                break;
            case 6:
                Constante.forceDuVent = Constante.TypeForceVent.fort;
                Constante.CONSTANTE_VENT = 25;
                mouvementNuage = 7;
                break;
            case 7:
                Constante.forceDuVent = Constante.TypeForceVent.très_fort;
                Constante.CONSTANTE_VENT = 40;
                mouvementNuage = 10;
                break;

        }

        choix = rnd.nextInt(3);
        switch (choix) {//pour le sens du vent
            case 0:
            case 1:
                Constante.CONSTANTE_VENT = -Constante.CONSTANTE_VENT;
                mouvementNuage = -mouvementNuage;
                break;

        }
        ResolutionImage.setMouvementNuage(mouvementNuage);
    }

    public static void setCameraX(int cameraX) {
        Jeu.cameraX = cameraX;
    }

}
