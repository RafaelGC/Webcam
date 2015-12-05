/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Rafa
 */
public class VistaWebcam extends JFrame implements ManejadorImagen{
    private BufferedImage imagen;
    private JLabel label;
    
    VistaWebcam() {
        super("Webcam");
        imagen = null;
        
        this.setSize(new Dimension(500,500));
        label = new JLabel();
        this.add(label);
        
    }
    
    @Override
    public void manejarImagen(DataInputStream dataInputStream) {
        try {
            //Cargar imagen.
            imagen = ImageIO.read(dataInputStream);
            
            //Actualizar imagen.
            ImageIcon icon = new ImageIcon(imagen);
            label.setIcon(icon);
            this.setSize(imagen.getWidth(), imagen.getHeight());
        } catch (Exception ex) {
        }
        
    }
    
    
}
