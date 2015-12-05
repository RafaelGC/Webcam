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
    public void manejarImagen(DataInputStream dataInputStream) {
        try {
            FileOutputStream output = new FileOutputStream(new File(nombreArchivo+indice+".jpg"));
            
            while (true) {
                try {
                    output.write(dataInputStream.readByte());
                }
                catch (IOException e) {
                    break; //Fin de la lectura.
                }
            }
            
            indice++;
            
        } catch (FileNotFoundException ex) {
        }
    }
    
}
