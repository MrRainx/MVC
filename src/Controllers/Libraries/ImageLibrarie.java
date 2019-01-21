package Controllers.Libraries;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author MrRainx
 */
public class ImageLibrarie {

    public static ImageIcon JFCToImageIcon(JFileChooser findFile) {
        ImageIcon image = null;

        try {

            URI uri = findFile.getSelectedFile().toURI();

            try (FileInputStream input = new FileInputStream(new File(uri))) {
                
                BufferedImage bffImage = ImageIO.read(input);
                
                if (bffImage == null) {

                    return null;
                    
                } else {
                    
                    image = new ImageIcon(bffImage);
                    
                }

            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageLibrarie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageLibrarie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;

    }

    public static FileInputStream getImgFileFromJFC(JFileChooser JFileChooser) {

        try {
            return new FileInputStream(new File(JFileChooser.getSelectedFile().toPath().toUri()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageLibrarie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    
    public static void SetImageInLabel(ImageIcon image, JLabel label){
        
        label.setSize(label.getWidth(), label.getHeight()-2);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icon);
    }
    
    public static String getStringPathFromJFC(JFileChooser JfileChooser){
        return JfileChooser.getSelectedFile().toPath().toString();
    }
    
    
}