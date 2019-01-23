package Controllers.Libraries;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import org.postgresql.util.Base64;

/**
 *
 * @author MrRainx
 */
public class ImgLib {

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
            Logger.getLogger(ImgLib.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImgLib.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;

    }

    public static FileInputStream getImgFileFromJFC(JFileChooser JFileChooser) {

        try {
            return new FileInputStream(new File(JFileChooser.getSelectedFile().toPath().toUri()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImgLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void SetImageInLabel(ImageIcon image, JLabel label) {

        label.setSize(label.getWidth(), label.getHeight() - 2);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icon);
    }

    public static String getStringPathFromJFC(JFileChooser JfileChooser) {
        return JfileChooser.getSelectedFile().toPath().toString();
    }

    public static Image getImageFrom(byte[] bytes, boolean isThumbnail) {

        try {
            ByteArrayInputStream byteAIS = new ByteArrayInputStream(bytes);
            Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
            ImageReader reader = (ImageReader) readers.next();

            Object souce = byteAIS;

            ImageInputStream iIS = ImageIO.createImageInputStream(souce);

            reader.setInput(iIS, true);

            ImageReadParam param = reader.getDefaultReadParam();

            if (isThumbnail) {
                param.setSourceSubsampling(4, 4, 0, 0);
            }

            return reader.read(0, param);
        } catch (IOException ex) {
            Logger.getLogger(ImgLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        BufferedImage buffImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = buffImage.createGraphics();
        g2D.drawImage(image, 0, 0, null);
        g2D.dispose();
        return buffImage;

    }
    
    
    public static Image getImageFromFileInputStrem(FileInputStream file){
        
        BufferedImage bffImage = null;
        ImageIcon image = null;
        try {
            bffImage = ImageIO.read(file);
            image = new ImageIcon(bffImage);
            
        } catch (IOException ex) {
            Logger.getLogger(ImgLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image.getImage();
        
        
    }
    
    
    public static String setImageInBase64(Image image){
        
        String base = null;
        
        ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
        
        try {
            BufferedImage img = toBufferedImage(image);
            
            ImageIO.write(img, "PNG", byteAOS);
            
            byte[] imagByte = byteAOS.toByteArray();
            
            base = Base64.encodeBytes(imagByte);
            
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return base;
    }
    
    
    
}
