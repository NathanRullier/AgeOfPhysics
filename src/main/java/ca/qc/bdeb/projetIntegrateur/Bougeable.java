/*
Interface Bougeable 
 */
package ca.qc.bdeb.projetIntegrateur;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
public interface Bougeable {
    
    
    public void render(Graphics g) throws SlickException;
    public void update() throws SlickException;
    public void isRenderValide();
}
