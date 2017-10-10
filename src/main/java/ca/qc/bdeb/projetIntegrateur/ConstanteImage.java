/*
Cette classe contient toutes les images utilisées pour dessiner les unites
 */
package ca.qc.bdeb.projetIntegrateur;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mathieu
 */
public class ConstanteImage {

    //Fronde :
    public final Animation animationFrondeDroite, animationFrondeGauche, animationFrondeTirGauche, animationFrondeTirDroite;
    public final Image frondeImmoDroite, frondeImmoGauche;//image de la fronde immobile

    //Archer :
    public final Animation animationArcherDroite, animationArcherGauche;
    public final Image archerImmoDroite, archerImmoGauche, archerArcGauche, archerArcDroite;

    //lancier :
    public final Animation animationLancierDroite, animationLancierGauche, animationInstructionAttaque;
    public final Image lancierImmoDroite, lancierImmoGauche, lancierTirDroite, lancierTirGauche;

    //Catapulte defense :
    public final Image catapulteDefImmoDroite, catapulteDefImmoGauche, catapulteDefTirGauche, catapulteDefTirDroite;

    //Catapulte attaque : 
    public final Image catapulteAttaqueImmoGauche, catapulteAttaqueImmoDroite;
    public final Image catapulteAttaqueTirGauche, catapulteAttaqueTirDroite;
    public final Animation animationCatapulteAttaqueBougeGauche, animationCatapulteAttaqueBougeDroite;

    //Avion :
    public final Image avionDroite, avionGauche, bombeDroite, bombeGauche, champignionAtomique;
    

    //Keyboard :
    public final Image keyRight;
    public final Image flecheDroite, flecheGauche;

    private static ConstanteImage INSTANCE;

    public final Animation animationProjectileCatapuleGauche, animationProjectileCatapuleDroite;
/**
 * Constructeur privé de la classe puisqu'il s'agit d'un singleton.
 * @throws SlickException 
 */
    private ConstanteImage() throws SlickException {
        animationFrondeDroite = new Animation();
        animationFrondeGauche = new Animation();
        animationFrondeTirGauche = new Animation();
        animationFrondeTirDroite = new Animation();

        //flèche :
        flecheDroite = new Image("gr/menu/fleche.png");
        flecheGauche = flecheDroite.getFlippedCopy(true, false);
        
        
        //fronde :
        Image[] imageFronde = new Image[22];
        imageFronde[0] = new Image("gr/units/fronde/fronde stand.png");
        imageFronde[1] = new Image("gr/units/fronde/fronde walk_1.png");
        imageFronde[2] = new Image("gr/units/fronde/fronde walk_2.png");
        imageFronde[3] = new Image("gr/units/fronde/fronde walk_3.png");
        imageFronde[4] = new Image("gr/units/fronde/fronde walk_4.png");
        imageFronde[5] = imageFronde[0].getFlippedCopy(true, false);
        imageFronde[6] = imageFronde[1].getFlippedCopy(true, false);
        imageFronde[7] = imageFronde[2].getFlippedCopy(true, false);
        imageFronde[8] = imageFronde[3].getFlippedCopy(true, false);
        imageFronde[9] = imageFronde[4].getFlippedCopy(true, false);
        for (int i = 1; i <= 4; i++) {
            animationFrondeGauche.addFrame(imageFronde[i], 50);
        }

        for (int i = 6; i <= 9; i++) {
            animationFrondeDroite.addFrame(imageFronde[i], 50);
        }
        imageFronde[10] = new Image("gr/units/fronde/fronde attack_1.png");
        imageFronde[11] = new Image("gr/units/fronde/fronde attack_2.png");
        imageFronde[12] = new Image("gr/units/fronde/fronde attack_3.png");
        imageFronde[13] = new Image("gr/units/fronde/fronde attack_4.png");
        imageFronde[14] = new Image("gr/units/fronde/fronde attack_5.png");
        imageFronde[15] = new Image("gr/units/fronde/fronde attack_6.png");
        imageFronde[16] = imageFronde[10].getFlippedCopy(true, false);
        imageFronde[17] = imageFronde[11].getFlippedCopy(true, false);
        imageFronde[18] = imageFronde[12].getFlippedCopy(true, false);
        imageFronde[19] = imageFronde[13].getFlippedCopy(true, false);
        imageFronde[20] = imageFronde[14].getFlippedCopy(true, false);
        imageFronde[21] = imageFronde[15].getFlippedCopy(true, false);

        for (int i = 10; i <= 15; i++) {
            animationFrondeTirGauche.addFrame(imageFronde[i], 50);
        }

        for (int i = 16; i <= 21; i++) {
            animationFrondeTirDroite.addFrame(imageFronde[i], 50);
        }
        frondeImmoGauche = imageFronde[0];
        frondeImmoDroite = imageFronde[5];

        //Archer : 
        animationArcherDroite = new Animation();
        animationArcherGauche = new Animation();

        Image[] imageAnimation = new Image[12];
        imageAnimation[0] = new Image("gr/units/archer/archer stand.png");
        imageAnimation[1] = new Image("gr/units/archer/archer walk_1.png");
        imageAnimation[2] = new Image("gr/units/archer/archer walk_2.png");
        imageAnimation[3] = new Image("gr/units/archer/archer walk_3.png");
        imageAnimation[4] = new Image("gr/units/archer/archer walk_4.png");
        imageAnimation[5] = imageAnimation[0].getFlippedCopy(true, false);
        imageAnimation[6] = imageAnimation[1].getFlippedCopy(true, false);
        imageAnimation[7] = imageAnimation[2].getFlippedCopy(true, false);
        imageAnimation[8] = imageAnimation[3].getFlippedCopy(true, false);
        imageAnimation[9] = imageAnimation[4].getFlippedCopy(true, false);
        imageAnimation[10] = new Image("gr/units/archer/arc.png");
        imageAnimation[11] = imageAnimation[10].getFlippedCopy(true, false);

        for (int i = 1; i <= 4; i++) {
            animationArcherGauche.addFrame(imageAnimation[i], 50);
        }

        for (int i = 6; i <= 9; i++) {
            animationArcherDroite.addFrame(imageAnimation[i], 50);
        }
        archerImmoDroite = imageAnimation[5];
        archerImmoGauche = imageAnimation[0];
        archerArcGauche = imageAnimation[10];
        archerArcDroite = imageAnimation[11];

        //Lancier :
        animationLancierGauche = new Animation();
        animationLancierDroite = new Animation();
        animationInstructionAttaque = new Animation();

        Image[] imageLancier = new Image[22];
        lancierTirGauche = new Image("gr/units/lancier/lancierTir.png");
        lancierTirDroite = lancierTirGauche.getFlippedCopy(true, false);
        imageLancier[10] = lancierTirDroite;
        imageLancier[0] = new Image("gr/units/lancier/lancier stand.png");
        imageLancier[1] = new Image("gr/units/lancier/Lancier walk_1.png");
        imageLancier[2] = new Image("gr/units/lancier/lancier walk_2.png");
        imageLancier[3] = new Image("gr/units/lancier/lancier walk_3.png");
        imageLancier[4] = new Image("gr/units/lancier/lancier walk_4.png");
        imageLancier[5] = imageLancier[0].getFlippedCopy(true, false);
        imageLancier[6] = imageLancier[1].getFlippedCopy(true, false);
        imageLancier[7] = imageLancier[2].getFlippedCopy(true, false);
        imageLancier[8] = imageLancier[3].getFlippedCopy(true, false);
        imageLancier[9] = imageLancier[4].getFlippedCopy(true, false);

        for (int i = 1; i <= 4; i++) {
            animationLancierGauche.addFrame(imageLancier[i], 50);
        }
        for (int i = 6; i <= 9; i++) {
            animationLancierDroite.addFrame(imageLancier[i], 50);
        }
        for(int i=5; i<11;i=i+5){
            
            animationInstructionAttaque.addFrame(imageLancier[i],2000);
            
        }

        
        lancierImmoDroite = imageLancier[5];
        lancierImmoGauche = imageLancier[0];

        //Catapule defensive : 
        Image[] imageCatapuleDef = new Image[6];
        imageCatapuleDef[0] = new Image("gr/units/CatapulteDefense/catapulteRepos2.png");
        imageCatapuleDef[1] = new Image("gr/units/CatapulteDefense/catapulTir1.png");
        imageCatapuleDef[2] = new Image("gr/units/CatapulteDefense/catapultTir2.png");
        imageCatapuleDef[3] = imageCatapuleDef[0].getFlippedCopy(true, false);
        imageCatapuleDef[4] = imageCatapuleDef[1].getFlippedCopy(true, false);
        imageCatapuleDef[5] = imageCatapuleDef[2].getFlippedCopy(true, false);

        catapulteDefImmoDroite = imageCatapuleDef[0];
        catapulteDefImmoGauche = imageCatapuleDef[3];
        catapulteDefTirDroite = imageCatapuleDef[2];
        catapulteDefTirGauche = imageCatapuleDef[5];

        animationProjectileCatapuleGauche = new Animation();
        animationProjectileCatapuleDroite = new Animation();
        Image[] imageProjCata = new Image[16];
        imageProjCata[0] = new Image("gr/units/CatapulteDefense/projCata1.png");
        imageProjCata[1] = new Image("gr/units/CatapulteDefense/projCata2.png");
        imageProjCata[2] = new Image("gr/units/CatapulteDefense/projCata3.png");
        imageProjCata[3] = new Image("gr/units/CatapulteDefense/projCata4.png");
        imageProjCata[4] = new Image("gr/units/CatapulteDefense/projCata5.png");
        imageProjCata[5] = new Image("gr/units/CatapulteDefense/projCata6.png");
        imageProjCata[6] = new Image("gr/units/CatapulteDefense/projCata7.png");
        imageProjCata[7] = new Image("gr/units/CatapulteDefense/projCata8.png");
        imageProjCata[8] = imageProjCata[0].getFlippedCopy(true, false);
        imageProjCata[9] = imageProjCata[1].getFlippedCopy(true, false);
        imageProjCata[10] = imageProjCata[2].getFlippedCopy(true, false);
        imageProjCata[11] = imageProjCata[3].getFlippedCopy(true, false);
        imageProjCata[12] = imageProjCata[4].getFlippedCopy(true, false);
        imageProjCata[13] = imageProjCata[5].getFlippedCopy(true, false);
        imageProjCata[14] = imageProjCata[6].getFlippedCopy(true, false);
        imageProjCata[15] = imageProjCata[7].getFlippedCopy(true, false);

        for (int i = 0; i < 8; i++) {
            animationProjectileCatapuleGauche.addFrame(imageProjCata[i], 50);
        }
        for (int i = 8; i < imageProjCata.length; i++) {
            animationProjectileCatapuleDroite.addFrame(imageProjCata[i], 50);
        }

        // Catapule offensive :
        catapulteAttaqueImmoDroite = new Image("gr/units/CatapulteAttaque/CatapulteArret.png");
        catapulteAttaqueImmoGauche = catapulteAttaqueImmoDroite.getFlippedCopy(true, false);
        catapulteAttaqueTirDroite = new Image("gr/units/CatapulteAttaque/CatapulteTir.png");
        catapulteAttaqueTirGauche = catapulteAttaqueTirDroite.getFlippedCopy(true, false);

        Image[] imageCataAttaque = new Image[10];
        imageCataAttaque[0] = new Image("gr/units/CatapulteAttaque/CataputeBouge.png");
        imageCataAttaque[1] = new Image("gr/units/CatapulteAttaque/CatapulteBouge2.png");
        imageCataAttaque[2] = new Image("gr/units/CatapulteAttaque/CatapulteBouge3.png");
        imageCataAttaque[3] = new Image("gr/units/CatapulteAttaque/CatapulteBouge4.png");
        imageCataAttaque[4] = new Image("gr/units/CatapulteAttaque/CatapulteBouge5.png");
        imageCataAttaque[5] = imageCataAttaque[0].getFlippedCopy(true, false);
        imageCataAttaque[6] = imageCataAttaque[1].getFlippedCopy(true, false);
        imageCataAttaque[7] = imageCataAttaque[2].getFlippedCopy(true, false);
        imageCataAttaque[8] = imageCataAttaque[3].getFlippedCopy(true, false);
        imageCataAttaque[9] = imageCataAttaque[4].getFlippedCopy(true, false);

        animationCatapulteAttaqueBougeDroite = new Animation();
        animationCatapulteAttaqueBougeGauche = new Animation();
        for (int i = 0; i <= 4; i++) {
            animationCatapulteAttaqueBougeDroite.addFrame(imageCataAttaque[i], 175);
        }

        for (int i = 5; i <= 9; i++) {
            animationCatapulteAttaqueBougeGauche.addFrame(imageCataAttaque[i], 175);
        }

        avionDroite = new Image("gr/units/avion/avionNucleaire.png");
        avionGauche = avionDroite.getFlippedCopy(true, false);
        bombeDroite = new Image("gr/units/avion/bombe.png");
        bombeGauche = bombeDroite.getFlippedCopy(true, false);

        champignionAtomique = new Image("gr/units/avion/champignonAtomique.png");

        keyRight = new Image("gr/menu/menuInstructions/key.png");
    }

    public static ConstanteImage getInstance() {
        return INSTANCE;
    }
/**
 * initialise le singleton dans la classe Main
 */
    public static void initSingleTon() {
        try {
            INSTANCE = new ConstanteImage();
        } catch (SlickException ex) {
            Logger.getLogger(ConstanteImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getLancierImmoDroite() {
        return lancierImmoDroite;
    }

    public Image getLancierTirDroite() {
        return lancierTirDroite;
    }

}
