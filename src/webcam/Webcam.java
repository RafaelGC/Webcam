/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Rafael González Carrizo y José Manuel Mañogil López
 */
public class Webcam {

    /**
     * @param args the command line arguments
     */
    //java Webcam turiscam.gva.es /webcams/cullera.jpg 1800 cullera

    public static void main(String[] args) {
        
        String url = "", ruta = "", nombreArchivo = "";
        int intervalo = 0;
        
        if (args.length==4) {
            //MODO CONSOLA
            url = args[0];
            ruta = args[1];
            intervalo = Integer.parseInt(args[2]);
            nombreArchivo = args[3];
            
            ManejadorImagen almacenador = new Almacenador(nombreArchivo);
            ConexionWebcam conexion = new ConexionWebcam(url,ruta,intervalo,almacenador);
            conexion.empieza();
            
        }
        else {
            //MODO GRÁFICO
            configurarLookAndFeel();
            
            ConfigDialog dialog = new ConfigDialog();
            dialog.setVisible(true);
            
        }
        
    }
    
    public static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
