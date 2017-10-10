/*
Ce classe est le carre jaune qui sert a selectionner les unites qu'un des 
utilisateur selectionne. Il s'agit d'un classe static puisque nous avons besoin
qu'une seule instance.
*/

package ca.qc.bdeb.projetIntegrateur;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
public class CarreSelection {

    public static ArrayList<Unite> listSelect = new ArrayList<>();
    public static ArrayList<Unite> listeDeSelectionne = new ArrayList<>();//si l'unite etait deja selctionne
    public static int x1 = 0, y1 = 0, hauteur = 0, largeur = 0, cameraX = 0;
    public static boolean affiche;
    private static RectanglePerso rect = new RectanglePerso(x1, y1, largeur, hauteur);
   
/**
 * S'occupe de dessiner le carre de selection
 * @param g
 * @throws SlickException 
 */
    public static void render(Graphics g) throws SlickException {
        g.setColor(Color.yellow);
        g.draw(rect);
       
        if (!listSelect.isEmpty()) {
            int i = 15;
            for (Unite unite : listSelect) {
                i = i + 15;
                g.drawString("  " + unite.toString(), x1, y1 - i);
            }
        } 
    }
    /**
     * Cette methode est appele lorsque l'utilisateur relache la souris. Il faut
     * alors reinitialiser les attributs du carre.
     * @throws SlickException 
     */
    public static void reset() throws SlickException {//lorsque affiche devient false
        x1 = 0;
        y1 = 0;
        hauteur = 0;
        largeur = 0;
        cameraX = 0;
        rect.setBounds(x1, y1, largeur, hauteur);
        affiche = false;
        listeDeSelectionne.addAll(listSelect);
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
                Unite unite = (Unite) bouge;
                if (!listSelect.contains(unite)) {                  
                    unite.setSelectionne(false);
                } else if (!unite.isaTirerCeTour()) {
                    unite.setSelectionne(true);
                }
            }
        }

        listSelect.clear();

    }
    /**
     * S'occupe de la mise a jour continue du carre de selection
     * @throws SlickException 
     */
    public static void update() throws SlickException {
         
        rect.setBounds(x1, y1, largeur, hauteur);
        for (Bougeable bouge : Constante.listeBougeable) {
            if (bouge instanceof Unite) {
               
                Unite unite = (Unite) bouge;
                if (unite.getJoueur().isSonTour()) { 
                    
                    if (rect.contains(unite.getHitbox())) {
                        
                        if (!listSelect.contains(unite)) {
                            listSelect.add(unite);
                              
                        }
                        
                    }
                    else if (listSelect.contains(unite)) {
                        listSelect.remove(unite);
                        
                    }

                }
            }
        }

    }
    
}
