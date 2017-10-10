/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
public class Tir extends Etat {

    private float angle = 0;//angle a la quelle l'unite tir
    private int sourisX, sourisY, cameraX;
    private double vitesseX, vitesseY;
    private double oppose = 0;
    private double adjacent = 0;
    private double deltaX, deltaY;
    private final boolean FLECHE;
    private final float HAUTEUR= ResolutionImage.HAUTEUR_FLECHE;
    private final float LARGEUR= ResolutionImage.LARGEUR_FLECHE;
/**
 * Constructeur de la classe Tir qui est un Etat
 * @param unite
 * @param bouge
 * @param direction
 * @throws SlickException 
 */
    public Tir(Unite unite, boolean bouge, Direction direction) throws SlickException {
        super(unite, bouge, direction);
       
        boolean temp=true;
        if(unite instanceof AvionNucleaire){
            Jeu.avionNucleaire=true;
        }
        
        FLECHE=!Jeu.avionNucleaire;
    }
/**
 * Cette methode est appele lorsqu'une unite est en mode Tir
 * @param g 
 */
    @Override
    public void render(Graphics g) {    
        if (super.getDirection() == Direction.GAUCHE) {
            unite.tirGauche(); 
            if(FLECHE){
            fleche(g, ConstanteImage.getInstance().flecheGauche);
            }
        } else {
           
            unite.tirDroite();
            if(FLECHE){
            fleche(g, ConstanteImage.getInstance().flecheDroite);
            
            }
        }
    }
/**
 * Cette methode est appele lorsque l'unite est dans l'Etat Tir et que l'utilisateur
 * appuie sur la touche Espace
 */
    @Override
    public void space() {//lorsqu'il appuie sur la spacebarre
        if ((unite.getJoueur().isSonTour())) {
            if (unite.isSelectionne()) {
                unite.setEtat(new Immobile(unite, false, unite.getEtat().getDirection()));
            }
        }
    }

    public void setSourisX(int sourisX) {
        this.sourisX = sourisX;
    }

    public void setSourisY(int sourisY) {
        this.sourisY = sourisY;
    }

    private void fleche(Graphics g, Image i) {//s'occupe de la rotation de la fleche 
        if (super.isFlecheBouge()) {
            if (super.getDirection() == Direction.DROITE) {
                deltaX = (adjacent / Math.sqrt(oppose * oppose + adjacent * adjacent));
                deltaY = (-oppose / Math.sqrt(oppose * oppose + adjacent * adjacent));
                if (sourisX - unite.positionX - cameraX < 0) {
                    deltaX = 0;
                }
                if (unite.positionY - sourisY < 0) {
                    deltaY = 0;
                }

            } else if (super.getDirection() == Direction.GAUCHE) {

                deltaX = (-adjacent / Math.sqrt(oppose * oppose + adjacent * adjacent));
                deltaY = (-oppose / Math.sqrt(oppose * oppose + adjacent * adjacent));
                if (-sourisX + unite.positionX + cameraX < 0) {
                    deltaX = 0;
                }
                if (unite.positionY - sourisY < 0) {
                    deltaY = 0;
                }
                
            }

            if (direction == Direction.DROITE) {
                oppose = unite.positionY - sourisY;
                adjacent = sourisX - unite.positionX - cameraX;

            } else {
                oppose = unite.positionY - sourisY;
                adjacent = unite.positionX - sourisX + cameraX;

            }
            double angleD;
            if (direction == Direction.DROITE) {
                angleD = Math.atan(oppose / adjacent);
            } else {
                angleD = Math.atan(-oppose / adjacent);
            }
            angleD = Math.toDegrees(angleD);
            angle = (float) angleD;

            if (direction == Direction.DROITE) {
                if (angle < 0 && deltaX == 0) {
                    angle = 89;
                } else if (angle < 0 && deltaY == 0) {
                    angle = 0;
                }
                i.setRotation(-angle);//doit etre un float
            } else {
                if (angle > 0 && deltaX == 0) {
                    angle = -89;
                } else if (angle > 0 && deltaY == 0) {
                    angle = 0;
                }
                i.setRotation(-angle);       
            }
        }
         //g.drawImage(i, (float) (unite.getPositionX() - 20 + 70 * deltaX), (float) (unite.positionY - 20 + 70 * deltaY));
         i.draw((float) (unite.getPositionX() - 20 + 70 * deltaX), (float) (unite.positionY - 20 + 70 * deltaY), LARGEUR,HAUTEUR);
         
    }

/**
 * Cette methode creer un projectile lorsque l'utilisateur relache la souris 
 * @param pourcentage
 * @throws SlickException 
 */
    public void initProjectile(double pourcentage) throws SlickException {//lorsque l'utilisateur fait enter
        double taux = pourcentage / 100.0;
        angle = (float) Math.toRadians(angle);
        if (direction == Direction.DROITE) {
            vitesseY = -unite.getPowerMax() * taux * Math.sin(angle);//équation 3
            vitesseX = unite.getPowerMax() * taux * Math.cos(angle);//équation 2
        } else {
            vitesseY = -unite.getPowerMax() * taux * Math.sin(-angle);
            vitesseX = -unite.getPowerMax() * taux * Math.cos(angle);
        }
        vitesseX = vitesseX + Constante.CONSTANTE_VENT;
        Projectile objProjectil = new Projectile(unite, vitesseX, vitesseY);
        resetElement();
        Constante.listeProjectiles.add(objProjectil);

    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    private void resetElement() throws SlickException {//lorsque le tir est fini
        unite.setSelectionne(false);//cette ligne pour faire une mitraillette
        unite.setEtat(new Immobile(unite, false, direction));
        unite.setaTirerCeTour(true); //pour empecher de tirer plusieurs fois de suite
        if (unite instanceof UniteOffensive) {
            UniteOffensive x = (UniteOffensive) unite;
            x.setNbMouvementTour(0);
        }

    }
/**
 * Cette methode est appele lorsque l'utilisateur appuie sur une fleche 
 */
    @Override
    public void fleche() {
        unite.setEtat(new Immobile(unite, bouge, direction));
    }

    @Override
    public String toString() {
        return "etat tir";
    }

    /**
     * Cette methode est appele par le robot lorsqu'il tire
     * @param pourcentage
     * @param angle
     * @throws SlickException 
     */
    public void initProjectileRobot(double pourcentage, double angle) throws SlickException {
        double x = pourcentage / 100.0;
        angle = (float) Math.toRadians(angle);
        if (direction == Direction.DROITE) {
            vitesseY = -unite.getPowerMax() * x * Math.sin(angle);
            vitesseX = unite.getPowerMax() * x * Math.cos(angle);
        } else {
            vitesseY = -unite.getPowerMax() * x * Math.sin(-angle);
            vitesseX = -unite.getPowerMax() * x * Math.cos(angle);
        }
        vitesseX = vitesseX + Constante.CONSTANTE_VENT;
        Projectile objProjectil = new Projectile(unite, vitesseX, vitesseY);
        resetElement();
        Constante.listeProjectiles.add(objProjectil);

    }

    @Override
    public float getAngle() {
        return angle;
    }

}
