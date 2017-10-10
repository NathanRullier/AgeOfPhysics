package ca.qc.bdeb.projetIntegrateur;

import java.util.ArrayList;

/*
Cette classe contient toute les constantes pour les differentes unites ainsi que les 
liste utilise.
 */
/**
 *
 * @author miguelgascon
 */
public class Constante {

    public static final int ACCELERATION = 20;
    public static final int NOMBRE_UNITE = 4;
    public static int CONSTANTE_VENT = 50;
    public static TypeForceVent forceDuVent = TypeForceVent.moyen;
    
    /**
     * Enum des type de force du vent
     */
    public static enum TypeForceVent {
        très_fort,
        fort,
        moyen,
        faible,
        très_faible;
    }

    public TypeForceVent typeForceVent;

    //Stats Fronde :
    public static int PV_Fronde = 100;
    public static final int POWERMAX_FRONDE = (int) (ResolutionImage.powerMaxFronde);//max 300 pour la catapultes
    public static final int DEGAT_FRONDE = 225;
    public static final int MOBILITE_FRONDE = (int) (ResolutionImage.mobiliteFronde);
    public static final int COUT_FRONDE = 50;
    public static int RECOMPENSE_FRONDE = (int) (COUT_FRONDE * 1.5);
    public static final int POSITION_X_FRONDE = 575;//position du bouton pour acheter
    public static final int POSITION_Y_FRONDE = 400;

    //Stats Lancier : 
    public static int PV_LANCIER = 150;
    public static final int POWERMAX_LANCIER = (int) (ResolutionImage.powerMaxLancier);
    public static final int DEGAT_LANCIER = 150;
    public static final int MOBILITE_LANCIER = (int) (ResolutionImage.mobiliteLancier);
    public static final int COUT_LANCIER = 50;
    public static int RECOMPENSE_LANCIER = (int) (COUT_LANCIER * 1.5);
    public static final int POSITION_X_LANCIER = 575;
    public static final int POSITION_Y_LANCIER = 700;

    //Stats Archer :
    public static int PV_ARCHER = 75;
    public static final int POWERMAX_ARCHER = (int) (ResolutionImage.powerMaxArcher);
    public static final int DEGAT_ARCHER = 50;
    public static final int MOBILITE_ARCHER = (int) (ResolutionImage.mobiliteArcher);
    public static final int COUT_ARCHER = 60;
    public static int RECOMPENSE_ARCHER = (int) (Constante.COUT_ARCHER * 1.5);
    public static final int POSITION_X_ARCHER = 575;
    public static final int POSITION_Y_ARCHER = 550;

    //Stats catapulte Defense :
    public static final int POWERMAX_CATAPULTE_DEFENSE = 600;
    public static final int DEGAT_CATAPULTE_DEFENSE = 500;
    public static final int COUT_CATAPULTE_DEFENSE = 300;
    public static final int POSITION_X_CATAPULTE_DEFENSE = 1000;
    public static final int POSITION_Y_CATAPULTE_DEFENSE = 400;

    //Stats catapule Attaque :
    public static final int PV_CATAPULTE_ATTAQUE = 300;
    public static final int COUT_CATAPULTE_ATTAQUE = 100;
    public static final int POWERMAX_CATAPULTE_ATTAQUE = 400;
    public static final int DEGAT_CATAPULTE_ATTAQUE = 500;
    public static int RECOMPENSE_CATAPULTE_ATTAQUE = (int)(COUT_CATAPULTE_ATTAQUE * 1.5);
    public static final int POSITION_X_CATAPULTE_ATTAQUE = 1000;
    public static final int POSITION_Y_CATAPULTE_ATTAQUE = 550;
    public static final int MOBILITE_CATAPULTE_ATTAQUE = 250;
    
    //Stats avion nucleaire :
    public static final int PV_AVION = 1;
    public static final int COUT_AVION = 5000;
    public static final int DEGAT_AVION = 100;
    public static final int RECOMPENSE_AVION = (int) (COUT_AVION * 1.5);
    public static final int MOBILITE_AVION = 100000;
    public static final int POWERMAX_AVION = 0;
    public static final int POSITION_X_AVION_NUCLEAIRE = 1000;
     public static final int POSITION_Y_AVION_NUCLEAIRE = 700;

    public static String nomJ1 = "Joueur 1";
    public static String nomJ2 = "Joueur 2";
    public static double vieDepart = 10000;
    public static boolean gameOver = false;
    public static final int ARGENT_DEPART = 1000;

    public static ArrayList<Bougeable> listeBougeable = new ArrayList<>();
    public static ArrayList<Bougeable> listeEnlever = new ArrayList<>();
    public static ArrayList<Projectile> listeProjectiles = new ArrayList<>();
    public static ArrayList<Joueur> listeJoueur = new ArrayList<>();
    public static ArrayList<Bougeable> listesous = new ArrayList<>();

    public static int incertitudeTir;
}
