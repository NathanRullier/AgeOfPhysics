/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.Animation;

/**
 *
 * @author miguelgascon
 */
public class ResolutionImage {

    private static Image lance, backgroundMenuPrincipal, cloud, cloud2, flecheVentDroite, flecheVentGauche, terminerTour, boutonTerminerAchat, worldMap, background1;
    //FIXME : doit refactor les noms de ces constantes
    private static final float RATIO_X = Main.getLargeur() / 1920.0f;
    private static final float RATIO_Y = Main.getHauteur() / 1080.0f;
    public static final int POSITION_SOL = (int) (625 * RATIO_Y);
    public static final int MOUVEMENT_FLECHE = (int) (8 * RATIO_X);
    public static final int MOUVEMENT_FLECHE_UNITE_SIEGE = (int) (4 * RATIO_X);
    private static float positionCloud1 = 0, positionCloud2;
    private static int mouvementNuage; //nombre que les nuages se deplacent a cause du vent
    private static final Random RND = new Random();
    private static float stringArgentX, stringArgentY, largeurCloud, hauteurCloud;
    public static final float WORLD_MAP_LARGEUR = 5760 * RATIO_X;
    private static Rectangle hitbox1J1, hitbox2J1, hitbox3J1, hitbox1J2, hitbox2J2, hitbox3J2;
    public static final float powerMaxFronde = (int) (250 * RATIO_X);
    public static final float mobiliteFronde = (int) (1500 * RATIO_X);
    public static final float mobiliteArcher = (int) (1700 * RATIO_X);
    public static final float powerMaxArcher = (int) (300 * RATIO_X);
    public static float powerMaxLancier = (int) (250 * RATIO_X);
    public static final float mobiliteLancier = (int) (1400 * RATIO_X);
    public static final int HAUTEUR_CHAMPIGON_ATOMIQUE = (int) (336 * RATIO_X);
    public static final int LARGEUR_CHAMPIGON_ATOMIQUE = (int) (286 * RATIO_Y);
    public static final float HAUTEUR_UNITE_OFFENSIVE = 51 * RATIO_Y;
    public static final float LARGEUR_UNITE_OFFENSIVE = 32 * RATIO_X;
    public static final float LARGEUR_TIR_UNITE_OFFENSIVE = 51 * RATIO_X;
    public static final float HAUTEUR_CATAPULTE_ATTAQUE = 82 * RATIO_Y;
    public static final float LARGEUR_CATAPULTE_ATTAQUE = 103 * RATIO_X;
    public static final float HAUTEUR_CATAPULTE_DEFENSE = 73 * RATIO_Y;
    public static final float LARGEUR_CATAPULTE_DEFENSE = 125 * RATIO_X;
    private static Sprite backgroundInstruction, btnRetour, btnInstruction, btn1Joueur, btn2Joueur, btnInterdit, btnCatapulteAttaque, btnCatapuleDefense, btnTerminerTour, btnTerminerAchat, sous, btnNouvellePartie, btnFronde, btnArcher, btnLancier, spriteWorldMap, spriteBackground, spriteMenuAchat, btnBouger, btnAttaquer, btnDifficulte1, btnDifficulte2, btnDifficulte3, btnDifficulte4, btnDifficulte5, btnAvionNucleaire, backgroundTutoriel, parchemin, curseur, keyRight, keyLeft, backgroundVideTutoriel, btnQuitter, btnReprendre, spriteFlecheLeft, spriteFlecheRight;
    public static TrueTypeFont font34, font23;
    private static Rectangle jauge1, jauge2;
    public static final int POSITION_Y_CATAPULTE = (int) (385 * RATIO_Y);
    public static final int POSITION_X1_CATAPULTE = (int) (55 * RATIO_X);
    public static final int POSITION_X2_CATAPULTE = (int) (5595 * RATIO_X);
    private static float positionXRectangle, positionYRectangle, largeurRectangle, hauteurRectangle, constanteX, constanteY;
    private static float largeurCurseur, hauteurCurseur;
    private static Image i;
    private static String s1,s2;
    public static final float HAUTEUR_FLECHE = 63 * RATIO_Y;
    public static final float LARGEUR_FLECHE = 64 * RATIO_X;
    private static float positionInitialeLancier = 0, deplacementLancier = 2f;
    private static Image curseurImage;

    private static boolean sensLancier = true;

    public static void dessinerBackgroundMenuPrincipal() {
        backgroundMenuPrincipal.draw(0, 0, Main.getLargeur(), Main.getHauteur());
    }

    public static void dessinerBoutonNouvellePartie(boolean gameOver, int cameraX) {
        if (gameOver == false) {
            btnNouvellePartie.render(0);
        } else {
            btnNouvellePartie.render(cameraX);
        }
    }

    /**
     * S'occupe d'initialiser les differentes images utilise par le programme
     *
     * @throws SlickException
     */
    public static void initialiserImage() throws SlickException {
        i = new Image("gr/menu/fleche.png");
        lance = new Image("gr/units/lancier/lance3.png");
        backgroundMenuPrincipal = new Image("gr/menu/menuPrincipal/Background.jpg");
        cloud = new Image("gr/map/cloud.1.png");
        cloud2 = new Image("gr/map/cloud.1.png");
        flecheVentDroite = new Image("gr/menu/fleche vent7.png");
        flecheVentGauche = flecheVentDroite.getFlippedCopy(true, false);
        boutonTerminerAchat = new Image("gr/menu/BoutonTerminer.png");
        worldMap = new Image("gr/map/map.1.2.png");
        background1 = new Image("gr/map/background1.png");
        terminerTour = new Image("gr/menu/BoutonTerminer.png");
        hauteurCloud = cloud.getHeight() * RATIO_Y;
        largeurCloud = cloud.getWidth() * RATIO_X;
        positionCloud1 = 0;
        positionCloud2 = largeurCloud;
        stringArgentX = 550 * RATIO_X;
        stringArgentY = 890 * RATIO_Y;
        initJauge();
        initNouvellePartie();
        initTerminerTour();
        initSous();
        initTerminerAchat();
        initBtnFronde();
        initBtnArcher();
        initBtnLancier();
        initWolrdMap();
        initBackground();
        initMenuAchat();
        initHitbox();
        initInterdit();
        initBtnCatapulteDefense();
        initBtnCatapulteAttaque();
        initBtnAvionNucleaire();
        initBtnJoueur();
        initBtnDifficulte();
        initBtnInstruction();
        intiBtnRetour();
        initBtnMenuInstruction();
        initBackGroundInstructions();
        initTutoriel();
        initParchemin();
        initRectangle();
        initCurseur();
        initKeyMenuInstruction();
        initMapVide();
        initMenuPause();

        Font awtFont = new Font("Times New Roman", Font.BOLD, (int) (34 * RATIO_X));
        font34 = new TrueTypeFont(awtFont, false);
        Font astFont = new Font("Times New Roman", Font.BOLD, (int) (23 * RATIO_X));
        font23 = new TrueTypeFont(astFont, false);
    }

    /**
     * S'occupe de dessiner le MenuAchat selon les bonnes proportions
     *
     * @param cameraX
     * @param g
     * @param joueur
     * @param xpos
     * @param ypos
     */
    public static void dessinerMenuAchat(int cameraX, Graphics g, Joueur joueur, int xpos, int ypos) {
        spriteMenuAchat.render(cameraX);
        g.setColor(Color.black);
        sous.render(cameraX);
        g.setFont(font34);
        g.drawString("" + joueur.getArgent() + " $", -cameraX + stringArgentX, stringArgentY);
        g.resetFont();
        btnCatapuleDefense.intersect(xpos, ypos, false);
        btnCatapuleDefense.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_CATAPULTE_DEFENSE, (Constante.POSITION_X_CATAPULTE_DEFENSE + 110) * RATIO_X - cameraX, Constante.POSITION_Y_CATAPULTE_DEFENSE * RATIO_Y);
        g.drawString("PV : Aucun", (Constante.POSITION_X_CATAPULTE_DEFENSE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_CATAPULTE_DEFENSE + 20) * RATIO_Y);
        g.drawString("Puissance : " + Constante.POWERMAX_CATAPULTE_DEFENSE, (Constante.POSITION_X_CATAPULTE_DEFENSE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_CATAPULTE_DEFENSE + 40) * RATIO_Y);
        g.drawString("Mobilité : Aucune", (Constante.POSITION_X_CATAPULTE_DEFENSE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_CATAPULTE_DEFENSE + 60) * RATIO_Y);
        g.drawString("Dégâts : " + Constante.DEGAT_CATAPULTE_DEFENSE, (Constante.POSITION_X_CATAPULTE_DEFENSE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_CATAPULTE_DEFENSE + 80) * RATIO_Y);
        btnTerminerAchat.intersect(xpos, ypos, false);
        btnTerminerAchat.render(cameraX);
        btnFronde.intersect(xpos, ypos, false);
        btnFronde.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_FRONDE, (Constante.POSITION_X_FRONDE + 110) * RATIO_X - cameraX, Constante.POSITION_Y_FRONDE * RATIO_Y);
        g.drawString("PV : " + Constante.PV_Fronde, (Constante.POSITION_X_FRONDE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_FRONDE + 20) * RATIO_Y);
        g.drawString("Puissance : " + Constante.POWERMAX_FRONDE, (Constante.POSITION_X_FRONDE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_FRONDE + 40) * RATIO_Y);
        g.drawString("Mobilité : " + Constante.MOBILITE_FRONDE, (Constante.POSITION_X_FRONDE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_FRONDE + 60) * RATIO_Y);
        g.drawString("Dégâts : " + Constante.DEGAT_FRONDE, (Constante.POSITION_X_FRONDE + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_FRONDE + 80) * RATIO_Y);

        btnArcher.intersect(xpos, ypos, false);
        btnArcher.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_ARCHER, (Constante.POSITION_X_ARCHER + 110) * RATIO_X - cameraX, Constante.POSITION_Y_ARCHER * RATIO_Y);
        g.drawString("PV : " + Constante.PV_Fronde, (Constante.POSITION_X_ARCHER + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_ARCHER + 20) * RATIO_Y);
        g.drawString("Puissance : " + Constante.POWERMAX_ARCHER, (Constante.POSITION_X_ARCHER + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_ARCHER + 40) * RATIO_Y);
        g.drawString("Mobilité : " + Constante.MOBILITE_ARCHER, (Constante.POSITION_X_ARCHER + 110) * RATIO_X - cameraX, (Constante.POSITION_Y_ARCHER + 60) * RATIO_Y);
        g.drawString("Dégâts : " + Constante.DEGAT_ARCHER, ((Constante.POSITION_X_ARCHER + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_ARCHER + 80) * RATIO_Y));

        btnLancier.intersect(xpos, ypos, false);
        btnLancier.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_LANCIER, ((Constante.POSITION_X_LANCIER + 110) * RATIO_X) - cameraX, (Constante.POSITION_Y_LANCIER * RATIO_Y));
        g.drawString("PV : " + Constante.PV_Fronde, ((Constante.POSITION_X_LANCIER + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_LANCIER + 20) * RATIO_Y));
        g.drawString("Puissance : " + Constante.POWERMAX_LANCIER, ((Constante.POSITION_X_LANCIER + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_LANCIER + 40) * RATIO_Y));
        g.drawString("Mobilité : " + Constante.MOBILITE_LANCIER, ((Constante.POSITION_X_LANCIER + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_LANCIER + 60) * RATIO_Y));
        g.drawString("Dégâts : " + Constante.DEGAT_LANCIER, ((Constante.POSITION_X_LANCIER + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_LANCIER + 80) * RATIO_Y));

        btnCatapulteAttaque.intersect(xpos, ypos, false);
        btnCatapulteAttaque.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_CATAPULTE_ATTAQUE, ((Constante.POSITION_X_CATAPULTE_ATTAQUE + 110) * RATIO_X) - cameraX, (Constante.POSITION_Y_CATAPULTE_ATTAQUE * RATIO_Y) + 0);
        g.drawString("PV : " + Constante.PV_CATAPULTE_ATTAQUE, ((Constante.POSITION_X_CATAPULTE_ATTAQUE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_CATAPULTE_ATTAQUE + 20) * RATIO_Y));
        g.drawString("Puissance : " + Constante.POWERMAX_CATAPULTE_ATTAQUE, ((Constante.POSITION_X_CATAPULTE_ATTAQUE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_CATAPULTE_ATTAQUE + 40) * RATIO_Y));
        g.drawString("Mobilité : " + Constante.MOBILITE_CATAPULTE_ATTAQUE, ((Constante.POSITION_X_CATAPULTE_ATTAQUE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_CATAPULTE_ATTAQUE + 60) * RATIO_Y));
        g.drawString("Dégâts : " + Constante.DEGAT_CATAPULTE_ATTAQUE, ((Constante.POSITION_X_CATAPULTE_ATTAQUE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_CATAPULTE_ATTAQUE + 80) * RATIO_Y));

        btnAvionNucleaire.intersect(xpos, ypos, false);
        btnAvionNucleaire.render(cameraX);
        g.drawString("Coût : " + Constante.COUT_AVION, ((Constante.POSITION_X_AVION_NUCLEAIRE + 110) * RATIO_X) - cameraX, (Constante.POSITION_Y_AVION_NUCLEAIRE * RATIO_Y) + 0);
        g.drawString("PV : " + Constante.PV_AVION, ((Constante.POSITION_X_AVION_NUCLEAIRE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_AVION_NUCLEAIRE + 20) * RATIO_Y));
        g.drawString("Puissance : " + Constante.POWERMAX_AVION, ((Constante.POSITION_X_AVION_NUCLEAIRE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_AVION_NUCLEAIRE + 40) * RATIO_Y));
        g.drawString("Mobilité : " + Constante.MOBILITE_AVION, ((Constante.POSITION_X_AVION_NUCLEAIRE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_AVION_NUCLEAIRE + 60) * RATIO_Y));
        g.drawString("Dégâts : " + Constante.DEGAT_AVION, ((Constante.POSITION_X_AVION_NUCLEAIRE + 110) * RATIO_X) - cameraX, ((Constante.POSITION_Y_AVION_NUCLEAIRE + 80) * RATIO_Y));

        if (joueur.isaUneDefense()) {
            btnInterdit.render(cameraX);
        }
    }

    /**
     * Calcul la position des nuages.
     */
    public static void nuage() {
        positionCloud1 = positionCloud1 + mouvementNuage;
        if (positionCloud1 <= -largeurCloud) {
            positionCloud1 = largeurCloud;
        } else if (positionCloud1 >= largeurCloud) {
            positionCloud1 = -largeurCloud;
        }

        positionCloud2 = positionCloud2 + mouvementNuage;
        if (positionCloud2 <= -largeurCloud) {
            positionCloud2 = largeurCloud;
        } else if (positionCloud2 >= largeurCloud) {
            positionCloud2 = -largeurCloud;
        }
    }

    /**
     * Dessine les nuages
     *
     * @throws SlickException
     */
    public static void dessinerNuage() throws SlickException {
        cloud.draw(positionCloud1, 0, (largeurCloud), hauteurCloud);
        cloud2.draw((positionCloud2), 0, (largeurCloud), hauteurCloud);
    }

    public static void vent(Graphics g, int cameraX) {

        g.setFont(font23);

        if (Constante.CONSTANTE_VENT < 0) {
            flecheVentGauche.draw(-cameraX + (1700 * RATIO_X), (50 * RATIO_Y), (217 * RATIO_X), (133 * RATIO_Y));
        } else {
            flecheVentDroite.draw(-cameraX + (1700 * RATIO_X), (50 * RATIO_Y), (217 * RATIO_X), (133 * RATIO_Y));

        }
        g.setColor(Color.blue);
        g.drawString("Vent " + Constante.forceDuVent, (-cameraX + (1725 * RATIO_X)), (105 * RATIO_Y));

    }

    /**
     * Dessine les images en arriere-plan
     *
     * @param cameraX
     */
    public static void dessinerWorldMap(int cameraX) {
        spriteBackground.render(cameraX);
        spriteWorldMap.render(0);
    }

    /**
     * Place les unites selon les proportions. Cette methode est appele par le
     * MenuAchat.
     *
     * @param unite
     */
    public static void placeUnite(UniteOffensive unite) {

        Etat.Direction direction = null;
        int x = 0;
        switch (unite.getJoueur().getNumeroPosition()) {
            case 1:

                x = (int) ((RND.nextInt(150) + 125) * RATIO_X);
                direction = Etat.Direction.DROITE;
                break;
            case 2:
                direction = Etat.Direction.GAUCHE;
                x = (int) ((RND.nextInt(150) + 5500) * RATIO_X);
        }
        unite.setEtat(new Immobile(unite, false, direction));
        unite.positionX = x;
        Constante.listeBougeable.add(unite);
    }

    private static void initNouvellePartie() throws SlickException {
        Image boutonNouvellePartie = new Image("gr/menu/menuPrincipal/BoutonNouvellePartie.png");
        float btnNouvellePartieX = (1920f / 2) - (boutonNouvellePartie.getWidth() / 2);
        float btnNouvellePartieY = (1080f / 2) - (boutonNouvellePartie.getHeight() / 2);
        float btnNouvellePartieLargeur = (boutonNouvellePartie.getWidth());
        float btnNouvellePartieHauteur = (boutonNouvellePartie.getHeight());
        btnNouvellePartie = new Sprite(btnNouvellePartieX, btnNouvellePartieY, btnNouvellePartieLargeur, btnNouvellePartieHauteur, boutonNouvellePartie);

    }

    private static void initTerminerTour() {
        float btnTerminerTourX = (50);
        float btnTerminerTourY = (50);
        float btnTerminerTourHauteur = (terminerTour.getHeight());
        float btnTerminerTourLargeur = (terminerTour.getWidth());
        btnTerminerTour = new Sprite(btnTerminerTourX, btnTerminerTourY, btnTerminerTourLargeur, btnTerminerTourHauteur, terminerTour);
    }

    private static void initSous() throws SlickException {
        float sousX = (475);
        float sousY = (875);
        float sousHauteur = (50);
        float sousLargeur = (66);
        Image imagePiece = new Image("gr/menu/menuAchat/peice-de-monnaie-du-dollar.png");
        sous = new Sprite(sousX, sousY, sousLargeur, sousHauteur, imagePiece);
    }

    private static void initTerminerAchat() {

        float btnTerminerAchatX = (475);
        float btnTerminerAchatY = (230);
        float btnTerminerAchatHauteur = (boutonTerminerAchat.getHeight());
        float btnTerminerAchatLargeur = (boutonTerminerAchat.getWidth());
        btnTerminerAchat = new Sprite(btnTerminerAchatX, btnTerminerAchatY, btnTerminerAchatLargeur, btnTerminerAchatHauteur, terminerTour);
    }

    private static void initHitbox() {
        float positionHitbox1J1X = (int) (255 * RATIO_X);
        float positionHitbox1J1Y = (int) (375 * RATIO_Y);
        float positionHitbox1J2X = (int) (5440 * RATIO_X);
        float positionHitbox1J2Y = (int) (375 * RATIO_Y);

        float positionHitbox2J1X = (int) (62 * RATIO_X);
        float positionHitbox2J1Y = (int) (456 * RATIO_Y);
        float positionHitbox2J2X = (int) (5504 * RATIO_X);
        float positionHitbox2J2Y = (int) (456 * RATIO_Y);

        float positionHitbox3J1X = (int) (0 * RATIO_X);
        float positionHitbox3J1Y = (int) (375 * RATIO_Y);
        float positionHitbox3J2X = (int) (5700 * RATIO_X);
        float positionHitbox3J2Y = (int) (375 * RATIO_Y);

        float hitbox1Largeur = (int) (65 * RATIO_X);
        float hitbox1Hauteur = (int) (250 * RATIO_Y);
        float hitbox2Largeur = (int) (190 * RATIO_X);
        float hitbox2Hauteur = (int) (5 * RATIO_Y);
        float hitbox3Largeur = (int) (65 * RATIO_X);
        float hitbox3Hauteur = (int) (250 * RATIO_Y);

        hitbox1J1 = new Rectangle(positionHitbox1J1X, positionHitbox1J1Y, hitbox1Largeur, hitbox1Hauteur);
        hitbox2J1 = new Rectangle(positionHitbox2J1X, positionHitbox2J1Y, hitbox2Largeur, hitbox2Hauteur);
        hitbox3J1 = new Rectangle(positionHitbox3J1X, positionHitbox3J1Y, hitbox3Largeur, hitbox3Hauteur);

        hitbox1J2 = new Rectangle(positionHitbox1J2X, positionHitbox1J2Y, hitbox1Largeur, hitbox1Hauteur);
        hitbox2J2 = new Rectangle(positionHitbox2J2X, positionHitbox2J2Y, hitbox2Largeur, hitbox2Hauteur);
        hitbox3J2 = new Rectangle(positionHitbox3J2X, positionHitbox3J2Y, hitbox3Largeur, hitbox3Hauteur);
    }

    private static void initBtnFronde() throws SlickException {
        Image uniteFronde = new Image("gr/menu/menuAchat/unite fronde.png");
        float positionFrondeX = (Constante.POSITION_X_FRONDE);
        float positionFrondeY = (Constante.POSITION_Y_FRONDE);
        float positionFrondeLargeur = (uniteFronde.getWidth());
        float positionFrondeHauteur = (uniteFronde.getHeight());
        btnFronde = new Sprite(positionFrondeX, positionFrondeY, positionFrondeLargeur, positionFrondeHauteur, uniteFronde);

    }

    private static void initBtnArcher() throws SlickException {
        Image uniteArcher = new Image("gr/menu/menuAchat/unite archer.png");
        float positionArcherX = (Constante.POSITION_X_ARCHER);
        float positionArcherY = (Constante.POSITION_Y_ARCHER);
        float positionArcherLargeur = (uniteArcher.getWidth());
        float positionArcherHauteur = (uniteArcher.getHeight());
        btnArcher = new Sprite(positionArcherX, positionArcherY, positionArcherLargeur, positionArcherHauteur, uniteArcher);
    }

    private static void initBtnLancier() throws SlickException {
        Image uniteLancier = new Image("gr/menu/menuAchat/unite lancier.png");
        float positionLancierX = (Constante.POSITION_X_LANCIER);
        float positionLancierY = (Constante.POSITION_Y_LANCIER);
        float positionLancierLargeur = (uniteLancier.getWidth());
        float positionLancierHauteur = (uniteLancier.getHeight());
        btnLancier = new Sprite(positionLancierX, positionLancierY, positionLancierLargeur, positionLancierHauteur, uniteLancier);

    }

    private static void initWolrdMap() {
        float worldMapHauteur = 1088;
        spriteWorldMap = new Sprite(0, 0, (WORLD_MAP_LARGEUR / RATIO_X), worldMapHauteur, worldMap);
    }

    private static void initBackground() {
        float positionBackgroundX = 0;
        float positionBackgroundY = (-250);
        float backgroundLargeur = (1920);
        float backgroundHauteur = (1080);
        spriteBackground = new Sprite(positionBackgroundX, positionBackgroundY, backgroundLargeur, backgroundHauteur, background1);
    }

    private static void initMenuAchat() throws SlickException {
        Image menuAchat = new Image("gr/menu/menuAchat/menuUnite1.2.png");
        spriteMenuAchat = new Sprite(0, 0, (menuAchat.getWidth()), (menuAchat.getHeight()), menuAchat);
    }

    private static void initMenuPause() throws SlickException {

        Image quitter = new Image("gr/menu/menuPause/quitter.png");
        Image reprendre = new Image("gr/menu/menuPause/reprendre.png");
        float hauteur = (quitter.getHeight());
        float largeur = (quitter.getWidth());
        float x = 1920f / 2 - quitter.getWidth() / 2;
        float y = 1080f / 2 - quitter.getHeight();
        float y2 = y + (hauteur) + hauteur / 2;

        btnQuitter = new Sprite(x, y2, largeur, hauteur, quitter);
        btnReprendre = new Sprite(x, y, largeur, hauteur, reprendre);
    }

    public static void jaugeVie(Graphics g, int vieJ1, int vieJ2) {
        double fraction1 = vieJ1 / Constante.vieDepart;
        g.setColor(Color.red);
        g.drawRect((int) (5 * RATIO_X), (int) (350 * RATIO_Y), (int) (50 * RATIO_X), (int) (-200 * RATIO_Y));
        jauge1.setBounds(jauge1.getX(), jauge1.getY(), jauge1.getWidth(), (float) (fraction1) * (int) (-200 * RATIO_Y));
        g.fill(jauge1);
        g.drawString(" " + vieJ1, (int) (1 * RATIO_X), (int) (355 * RATIO_Y));
        double fraction2 = vieJ2 / Constante.vieDepart;
        g.setColor(Color.blue);
        g.drawRect((int) (5690 * RATIO_X), (int) (350 * RATIO_Y), (int) (50 * RATIO_X), (int) (-200 * RATIO_Y));
        jauge2.setBounds(jauge2.getX(), jauge2.getY(), jauge2.getWidth(), (float) (fraction2) * (int) (-200 * RATIO_Y));
        g.fill(jauge2);
        g.drawString("" + vieJ2, (int) (5690 * RATIO_X), (int) (355 * RATIO_Y));
    }

    public static void initJauge() {
        jauge1 = new Rectangle((int) (5 * RATIO_X), (int) (350 * RATIO_Y), (int) (50 * RATIO_X), (int) (-200 * RATIO_Y));
        jauge2 = new Rectangle((int) (5690 * RATIO_X), (int) (350 * RATIO_Y), (int) (50 * RATIO_X), (int) (-200 * RATIO_Y));
    }

    private static void initBtnCatapulteDefense() throws SlickException {
        Image uniteCatapulteDefense = new Image("gr/menu/menuAchat/unite catapulte defense.png");
        btnCatapuleDefense = new Sprite((Constante.POSITION_X_CATAPULTE_DEFENSE), (Constante.POSITION_Y_CATAPULTE_DEFENSE), (100), (100), uniteCatapulteDefense);
    }

    private static void initInterdit() throws SlickException {
        Image interdit = new Image("gr/menu/menuAchat/interdit.png");
        btnInterdit = new Sprite((965), (400), (35), (35), interdit);
    }

    private static void initBtnDifficulte() throws SlickException {
        Image difficulte1 = new Image("gr/menu/menuPrincipal/difficulte1.png");
        Image difficulte2 = new Image("gr/menu/menuPrincipal/difficulte2.png");
        Image difficulte3 = new Image("gr/menu/menuPrincipal/difficulte3.png");
        Image difficulte4 = new Image("gr/menu/menuPrincipal/difficulte4.png");
        Image difficulte5 = new Image("gr/menu/menuPrincipal/difficulte5.png");
        float hauteur = (difficulte1.getHeight());
        float largeur = (difficulte1.getWidth());
        float x = 1920f / 5 - difficulte1.getWidth() / 2;
        float y = 1080f / 2 - difficulte1.getHeight();

        btnDifficulte1 = new Sprite(x, y, largeur, hauteur, difficulte1);
        btnDifficulte2 = new Sprite(2 * x, y, largeur, hauteur, difficulte2);
        btnDifficulte3 = new Sprite(3 * x, y, largeur, hauteur, difficulte3);
        btnDifficulte4 = new Sprite(4 * x, y, largeur, hauteur, difficulte4);
        btnDifficulte5 = new Sprite(5 * x, y, largeur, hauteur, difficulte5);

    }

    private static void initBtnJoueur() throws SlickException {
        Image joueur2 = new Image("gr/menu/menuPrincipal/2joueurs.png");
        Image joueur1 = new Image("gr/menu/menuPrincipal/1joueur.png");
        float hauteur = (joueur1.getHeight());
        float largeur = (joueur1.getWidth());
        float x = 1920f / 2 - joueur1.getWidth() / 2;
        float y = 1080f / 2 - joueur1.getHeight();
        float y2 = y + (hauteur) + hauteur / 2;

        btn1Joueur = new Sprite(x, y, largeur, hauteur, joueur1);
        btn2Joueur = new Sprite(x, y2, largeur, hauteur, joueur2);
    }

    private static void initBtnInstruction() throws SlickException {
        Image instruction = new Image("gr/menu/menuPrincipal/instruction.png");
        float hauteur = instruction.getHeight();
        float largeur = instruction.getWidth();
        float y = ((1920f / 2) - (instruction.getWidth() / 2)) + instruction.getHeight() / 2;
        float x = (1920f / 2) - (instruction.getWidth() / 2);
        btnInstruction = new Sprite(x, y, largeur, hauteur, instruction);
    }

    private static void intiBtnRetour() throws SlickException {
        Image retour = new Image("gr/menu/menuPrincipal/retour.png");
        float hauteur = (retour.getHeight());
        float largeur = (retour.getWidth());
        float x = (1920f - largeur * 1.5f);
        float y = (1080f - hauteur * 1.5f);

        btnRetour = new Sprite(x, y, largeur, hauteur, retour);

    }

    private static void initBackGroundInstructions() throws SlickException {
        Image instructionBackground = new Image("gr/menu/menuInstructions/backgroundTuto.jpg");
        backgroundInstruction = new Sprite(0, 0, (instructionBackground.getWidth()), (instructionBackground.getHeight()), instructionBackground);
    }

    private static void initBtnCatapulteAttaque() throws SlickException {
        Image imageCata = new Image("gr/menu/menuAchat/unite catapulte.png");
        float positionCataX = (Constante.POSITION_X_CATAPULTE_ATTAQUE);
        float positionCataY = (Constante.POSITION_Y_CATAPULTE_ATTAQUE);
        float positionCataLargeur = 100;
        float positionCataHauteur = 100;
        btnCatapulteAttaque = new Sprite(positionCataX, positionCataY, positionCataLargeur, positionCataHauteur, imageCata);
    }

    private static void initBtnAvionNucleaire() throws SlickException {

        Image imageAvion = new Image("gr/menu/menuAchat/unite avion.png");

        float positionX = Constante.POSITION_X_AVION_NUCLEAIRE;
        float positionY = Constante.POSITION_Y_AVION_NUCLEAIRE;
        float largeur = 100;
        float hauteur = 100;
        btnAvionNucleaire = new Sprite(positionX, positionY, largeur, hauteur, imageAvion);

    }

    public static Sprite getBtn2Joueur() {
        return btn2Joueur;
    }

    public static Sprite getBtnDifficulte1() {
        return btnDifficulte1;
    }

    public static Sprite getBtnDifficulte2() {
        return btnDifficulte2;
    }

    public static Sprite getBtnDifficulte3() {
        return btnDifficulte3;
    }

    public static Sprite getBtnDifficulte4() {
        return btnDifficulte4;
    }

    public static Sprite getBtnDifficulte5() {
        return btnDifficulte5;
    }

    public static Sprite getBtnCatapuleDefense() {
        return btnCatapuleDefense;
    }

    public static Sprite getSous() {
        return sous;
    }

    public static int getPositionSol() {
        return POSITION_SOL;
    }

    public static Sprite getBtn1Joueur() {
        return btn1Joueur;
    }

    public static Sprite getBtnRetour() {
        return btnRetour;
    }

    public static Sprite getBtnInstruction() {
        return btnInstruction;
    }

    public static Sprite getBackgroundInstruction() {
        return backgroundInstruction;
    }

    public static Sprite getBtnTerminerTour() {
        return btnTerminerTour;
    }

    public static float getHauteurCloud() {
        return hauteurCloud;
    }

    public static Sprite getBtnTerminerAchat() {
        return btnTerminerAchat;
    }

    public static Sprite getBtnNouvellePartie() {
        return btnNouvellePartie;
    }

    public static Sprite getBtnArcher() {
        return btnArcher;
    }

    public static Sprite getBtnFronde() {
        return btnFronde;
    }

    public static Sprite getBtnLancier() {
        return btnLancier;
    }

    public static Rectangle getHitbox1J1() {
        return hitbox1J1;
    }

    public static Rectangle getHitbox1J2() {
        return hitbox1J2;
    }

    public static Rectangle getHitbox2J1() {
        return hitbox2J1;
    }

    public static Rectangle getHitbox2J2() {
        return hitbox2J2;
    }

    public static Rectangle getHitbox3J1() {
        return hitbox3J1;
    }

    public static Rectangle getHitbox3J2() {
        return hitbox3J2;
    }

    public static void setMouvementNuage(int mouvementNuage) {
        ResolutionImage.mouvementNuage = mouvementNuage;
    }

    public static Image getLance() {
        return lance;
    }

    public static Sprite getBtnCatapulteAttaque() {
        return btnCatapulteAttaque;
    }

    public static double getRatioX() {
        return RATIO_X;
    }

    public static double getRatioY() {
        return RATIO_Y;
    }

    public static Sprite getBtnBouger() {
        return btnBouger;
    }

    public static Sprite getBtnAttaquer() {
        return btnAttaquer;
    }

    public static Sprite getBtnAvionNucleaire() {
        return btnAvionNucleaire;
    }

    private static void initBtnMenuInstruction() throws SlickException {

        Image Bouger = new Image("gr/menu/menuInstructions/Bouger.png");
        Image Attaquer = new Image("gr/menu/menuInstructions/Attaquer.png");
        float largeur = Attaquer.getWidth();
        float hauteur = Attaquer.getHeight();
        float positionX = 1920 / 2 - largeur / 2, positionYBouger = 1080 / 2 - 2 * hauteur, positionYAttaquer = positionYBouger + 3 * hauteur / 2;
        btnBouger = new Sprite(positionX, positionYBouger, largeur, hauteur, Bouger);
        btnAttaquer = new Sprite(positionX, positionYAttaquer, largeur, hauteur, Attaquer);

    }

    private static void initTutoriel() throws SlickException {

        Image background = new Image("gr/menu/menuInstructions/tutorielSelectUnit.png");
        float positionX = 0;
        float positionY = 0;
        float largeur = 1920;
        float hauteur = 1080;
        backgroundTutoriel = new Sprite(positionX, positionY, largeur, hauteur, background);

    }

    public static Sprite getBackgroundTutoriel() {
        return backgroundTutoriel;
    }

    private static void initParchemin() throws SlickException {

        Image imageParchemin = new Image("gr/menu/menuAchat/parchemin2.png");
        float positionX = 0.6f * 1920;
        float positionY = 0.125f * 1080;
        float largeur = 700;
        float hauteur = 400;
        parchemin = new Sprite(positionX, positionY, largeur, hauteur, imageParchemin);

    }

    public static Sprite getParchemin() {
        return parchemin;
    }

    private static void initRectangle() {

        positionXRectangle = 0.45f * Main.getLargeur();
        positionYRectangle = (0.125f * Main.getHauteur() + (400 + 10 * (RATIO_Y)));
        largeurRectangle = 0;
        hauteurRectangle = 0;
        constanteX = 10 * (RATIO_X);
        constanteY = 5 * (RATIO_Y);

    }

    public static float getPositionXRectangle() {
        return positionXRectangle;
    }

    public static float getPositionYRectangle() {
        return positionYRectangle;
    }

    public static Sprite getBtnReprendre() {
        return btnReprendre;
    }

    public static Sprite getBtnQuitter() {
        return btnQuitter;
    }

    public static float getLargeurRectangle() {
        return largeurRectangle;
    }

    public static float getHauteurRectangle() {
        return hauteurRectangle;
    }

    public static void setHauteurRectangle(float hauteurRectangle) {
        ResolutionImage.hauteurRectangle = hauteurRectangle;
    }

    public static void setLargeurRectangle(float largeurRectangle) {
        ResolutionImage.largeurRectangle = largeurRectangle;
    }

    public static float getConstanteX() {
        return constanteX;
    }

    public static float getConstanteY() {
        return constanteY;
    }

    public static void dessinerCurseur(int compteur) throws SlickException {

        curseur = new Sprite((largeurRectangle + positionXRectangle) / (RATIO_X) - 10f * (RATIO_X), (hauteurRectangle + positionYRectangle) / (RATIO_Y), largeurCurseur, hauteurCurseur, curseurImage);
        curseur.render(0);

    }

    private static void initCurseur() throws SlickException {
        curseurImage = new Image("gr/menu/menuInstructions/curseur.png");
        largeurCurseur = 35 * (RATIO_X);
        hauteurCurseur = 35 * (RATIO_Y);

    }

    public static void dessinerStringParchemin(Graphics g, double consigne) {

        g.setFont(font34);
        if (consigne == 0) {
            g.drawString("La première étape est de sélectio-", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() / 8);
            g.drawString("nner l'unité qui sera utilisée. On", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 2 / 8);
            g.drawString("peut le faire en enfonçant le", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 3 / 8);
            g.drawString("bouton de la souris et en glissant", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 4 / 8);
            g.drawString("la souris comme démontrer", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 5 / 8);
            g.drawString("ci-dessous.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 6 / 8);

        } else if (consigne == 1) {
            g.drawString("La deuxième étape est de mettre", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() / 8);
            g.drawString("l'unité en mode attaque. Pour se", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 2 / 8);
            g.drawString("faire, il faut appuyer sur le bouton", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 3 / 8);
            g.drawString("espace et l'unité devrait changer ", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 4 / 8);
            g.drawString("d'état comme démontrer", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 5 / 8);
            g.drawString("ci-dessous.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 6 / 8);

        } else if (consigne == 2) {
            g.drawString("La troisième étape est de choisir", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() / 8);
            g.drawString("l'angle que  la trajectoire aura", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 2 / 8);
            g.drawString("initialement. Pour se faire, on doit", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 3 / 8);
            g.drawString("bouger la souris dans la direction", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 4 / 8);
            g.drawString("que nous voulons et la flèche va", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 5 / 8);
            g.drawString("s'ajuster en conséquence.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 6 / 8);

        } else if (consigne == 3) {
            g.drawString("La quatrième étape est de choisir", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 1 / 10);
            g.drawString("la puissance initiale du projectile.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 2 / 10);
            g.drawString("Pour se faire, il faut appuyer sur", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 3 / 10);
            g.drawString("la souris ce qui va permettre de", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 4 / 10);
            g.drawString("verrouiller l'orientation de la", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 5 / 10);
            g.drawString("trajectoire initiale. Ensuite, il", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 6 / 10);
            g.drawString("faut relacher la souris lorsque la", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 7 / 10);
            g.drawString("puissance initiale semble adéquate.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 8 / 10);

        } else if (consigne == 4) {
            g.drawString("Après avoir sélectionner l'unité, il", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() / 8);
            g.drawString("faut appuyer sur la flèche gauche", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 2 / 8);
            g.drawString("sur le clavier pour aller à gauche", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 3 / 8);
            g.drawString("et il faut appuyer sur la flèche ", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 4 / 8);
            g.drawString("droite sur le clavier pour aller à", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 5 / 8);
            g.drawString("droite.", parchemin.getPositionX() + parchemin.getLargeur() / 6, parchemin.getPositionY() + parchemin.getHauteur() * 6 / 8);

        }
    }

    private static void initKeyMenuInstruction() throws SlickException {

        Image keyrightImage = new Image("gr/menu/MenuInstructions/key.png");
        Image keyLeftImage = new Image("gr/menu/MenuInstructions/key.png");
        Image flecheRight = new Image("gr/menu/fleche.png");
        Image flecheLeft = flecheRight.getFlippedCopy(true, false);
        float largeur = 100;
        float hauteur = 100;
        float positionX = 1920 / 10;
        float positionY = 1080 / 10;
        keyRight = new Sprite(positionX + largeur * 5 / 2, positionY, largeur, hauteur, keyrightImage);
        spriteFlecheRight = new Sprite(positionX + largeur * 5 / 2, positionY, largeur, hauteur, flecheRight);
        keyLeft = new Sprite(positionX, positionY, largeur, hauteur, keyLeftImage);
        spriteFlecheLeft = new Sprite(positionX, positionY, largeur, hauteur, flecheLeft);

    }

    public static Sprite getKeyRight() {
        return keyRight;
    }

    public static Sprite getKeyLeft() {
        return keyLeft;
    }

    private static void initMapVide() throws SlickException {

        Image mapVide = new Image("gr/menu/menuInstructions/mapSansUnite.png");

        float largeur = 1920;
        float hauteur = 1080;
        float positionX = 0;
        float positionY = 0;

        backgroundVideTutoriel = new Sprite(positionX, positionY, largeur, hauteur, mapVide);

    }

    public static Sprite getBackgroundVideTutoriel() {
        return backgroundVideTutoriel;
    }

    /**
     * Dessine l'animation pour le tutoriel
     *
     * @param modeAttaque
     * @throws SlickException
     */
    public static void dessinerAnimationTutoriel1Attaque() throws SlickException {

        ConstanteImage.getInstance().animationInstructionAttaque.draw(825 * RATIO_X, 590 * RATIO_Y, 66 * RATIO_X, 140 * RATIO_Y);
    }

    public static void dessinerAnimationTutoriel2Attaque() throws SlickException {

        ConstanteImage.getInstance().lancierTirDroite.draw(825 * RATIO_X, 590 * RATIO_Y, 66 * RATIO_X, 140 * RATIO_Y);
    }

    /**
     * Dessine la fleche de tir pour le menu instruction
     *
     * @param g
     * @throws SlickException
     */
    public static void dessinerFleche(Graphics g) throws SlickException {

        ConstanteImage.getInstance().flecheDroite.setRotation(-45);

        ConstanteImage.getInstance().flecheDroite.draw((800 + 100) * (RATIO_X), (540 - 60) * (RATIO_Y), i.getWidth() * (RATIO_X), i.getHeight() * (RATIO_Y));

    }

    public static void dessinerAnimationTutoriel4Attaque(Graphics g) throws SlickException {

        Image tutoriel4 = new Image("gr/menu/menuInstructions/tutorielAttaque4.png");

        tutoriel4.draw(0, 0, Main.getLargeur(), Main.getHauteur());

    }

    public static Sprite getSpriteFlecheLeft() {
        return spriteFlecheLeft;
    }

    public static Sprite getSpriteFlecheRight() {
        return spriteFlecheRight;
    }

    public static void dessinerAnimationTutorielBouger(Graphics g) throws SlickException {
        positionInitialeLancier = positionInitialeLancier + deplacementLancier;

        if (sensLancier) {
            ConstanteImage.getInstance().animationLancierDroite.draw(positionInitialeLancier, 590 * RATIO_Y, 66 * RATIO_X, 140 * RATIO_Y);
        } else {
            ConstanteImage.getInstance().animationLancierGauche.draw(positionInitialeLancier, 590 * RATIO_Y, 66 * RATIO_X, 140 * RATIO_Y);
        }
        if (positionInitialeLancier >= Main.getLargeur()) {
            sensLancier = false;
            deplacementLancier = -2f;
        } else if (positionInitialeLancier <= (0 - 66 * RATIO_X)) {
            sensLancier = true;
            deplacementLancier = 2f;
        }
    }

}
