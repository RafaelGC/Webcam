/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafa
 */
public class Almacenador implements ManejadorImagen{
    String nombreArchivo;
    int indice;
    
    Almacenador(String archivo) {
        this.nombreArchivo = archivo;
        indice = 1;
    }
    
    @Override
    public void manejarImagen(DataInputStream dataInputStream, int length) {
        try {
            String nombreArchivoCompleto = nombreArchivo+indice+".jpg";
            FileOutputStream output = new FileOutputStream(new File(nombreArchivoCompleto));
            
            System.out.println("Grabada la imagen "+nombreArchivoCompleto+" de "+length+" bytes");
            
            byte[] bytes = new byte[length];
            dataInputStream.readFully(bytes, 0, length);
            output.write(bytes);
            
            
            indice++;
            
        }
        catch (FileNotFoundException ex) {
        }
        catch (IOException e) {
            
        }
        
    }
    
}
