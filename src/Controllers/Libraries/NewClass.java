package Controllers.Libraries;

import java.awt.Image;
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
public class NewClass {

    public static void ImgFromFileChosser(JFileChooser findFile, JLabel label) {
        
        
        label.setIcon(null);
        
        label.setSize(label.getWidth(), label.getHeight()-2);
        
        try {

            URI uri = findFile.getSelectedFile().toURI();
            
            try (FileInputStream input = new FileInputStream(new File(uri))) {
                ImageIcon photo = new ImageIcon(ImageIO.read(input));
                
                Icon icon = new ImageIcon(photo.getImage().getScaledInstance(label.getWidth(), label.getHeight(), 0));
                
                label.setIcon(icon);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
