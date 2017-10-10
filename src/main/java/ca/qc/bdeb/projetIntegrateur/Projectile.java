/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mathieu
 */
public class Projectile implements Bougeable {

    private Unite unite;
    private double positionProjectile_X = 0, positionProjectile_Y = 0, temps = 0;
    protected double x1, y1;
    private final double vitesseX1, vitesseY1;//vitesse initial
    private double vitesseY2, angleRotation; //vitesse a un insant T et angle de rotation de la lance
    protected Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    private boolean collision = false;
    private  int rayonX, rayonY;//rayon de la hitbox
    private final static Rectangle HITBOX_SOL = new Rectangle(0, ResolutionImage.POSITION_SOL, ResolutionImage.WORLD_MAP_LARGEUR, 10);
/**
 * Constructeur de la classe projectile
 * @param unite
 * @param vitesseX
 * @param vitesseY 
 */
    public Projectile(Unite unite, double vitesseX, double vitesseY) {
        rayonX = unite.getRayonX();
        rayonY = unite.getRayonY();
        this.unite = unite;
        this.vitesseX1 = vitesseX;
        this.vitesseY1 = vitesseY;
        if (unite instanceof AvionNucleaire) {
            this.x1 = unite.getPositionX() + ((AvionNucleaire) unite).largeur / 2;
            this.y1 = unite.positionY + unite.hauteur / 3;;
        } else {
            this.x1 = unite.positionX;
            this.y1 = unite.positionY;
        }
        positionProjectile_X = unite.positionX;
        positionProjectile_Y = unite.positionY;

    }

    @Override
    public void render(Graphics g) {
        unite.dessinerProjectile(g, (float) angleRotation, positionProjectile_X, positionProjectile_Y);
        //g.draw(HITBOX_SOL);
        //g.draw(hitBox);
    }

    @Override
    public void update() {
        collision();//regarde les collisions
        isRenderValide();//valide si le projectile doit etre dessiner
        temps += 0.05;
        temps += (1/60.00);
        positionProjectile_X = (vitesseX1 * temps) + x1; //équation 1
        vitesseY2 = vitesseY1 + (Constante.ACCELERATION * temps); //équation 4
        positionProjectile_Y = (((vitesseY1 + vitesseY2) / 2) * temps) + y1; //équation 5
        angleRotation = Math.atan((vitesseY2 / vitesseX1));
        angleRotation = Math.toDegrees(angleRotation);
        hitBox.setBounds((int) positionProjectile_X, (int) positionProjectile_Y, rayonX, rayonY);//hitbox du projectile

    }

    protected void collision() {
        for (Bougeable unitBougeable : Constante.listeBougeable) {//collision avec une unite
            if (unitBougeable instanceof UniteOffensive) {
                UniteOffensive uniteTemp = (UniteOffensive) unitBougeable;
                if (!uniteTemp.getJoueur().equals(this.unite.getJoueur())) {
                    if (uniteTemp.getHitbox().intersects(this.hitBox)) {
                        if (uniteTemp != this.unite) {
                            if (!collision) {
                                collision = true;
                                uniteTemp.setDommageSubit(this.unite);
                                if (this.unite.isProjectileTransperceCible()) {
                                    collision = false;
                                }

                            }
                        }
                    }
                }

            }
        }

        for (Joueur joueur : Constante.listeJoueur) {//collision avec un chateau
            if (joueur.collision(hitBox)) {
                if (joueur.getNumeroPosition() != this.unite.getJoueur().getNumeroPosition()) {
                    if (!collision) {
                        joueur.setDommageSubit(this.unite.getDegats());
                        collision = true;
                    }
                } else {
                    collision = false;
                }

            }
        }
    }

    @Override
    public void isRenderValide() {
        if (hitBox.intersects(HITBOX_SOL) || collision || positionProjectile_X < 0 || positionProjectile_X > ResolutionImage.WORLD_MAP_LARGEUR) {
            Constante.listeEnlever.add(this);
            if (unite instanceof AvionNucleaire) {
                ChampignonAtomique champ = new ChampignonAtomique(unite, hitBox.getCenterX());
                Constante.listeProjectiles.add(champ);
            }
        }

    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public double getTemps() {
        return temps;
    }

    public void setRayonX(int rayonX) {
        this.rayonX = rayonX;
    }

    public void setRayonY(int rayonY) {
        this.rayonY = rayonY;
    }

    @Override
    public String toString() {
        return "projectile";
    }
    
    
    
    
    
    

    
    

}
