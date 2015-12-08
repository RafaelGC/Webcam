/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Rafa
 */
public class ConexionWebcam implements Runnable{
    String url, recurso;
    int intervalo;
    ManejadorImagen manejador;
    
    Socket socket;
    PrintWriter writer;
    DataInputStream entrada;
    
    Thread hilo;
    
    
    ConexionWebcam(String url, String recurso, int intervalo, ManejadorImagen manejador) {
        this.url = url;
        this.recurso = recurso;
        this.intervalo = intervalo;
        this.manejador = manejador;
        
        hilo = new Thread(this);
    }

    @Override
    public void run() {
        
        while (true) {
            
            if (Thread.interrupted()) break;
            
            try {
                socket = new Socket(url,80);
                writer = new PrintWriter(socket.getOutputStream());
                
                writer.println("GET "+recurso+" HTTP/1.0");
                writer.println("");
                writer.flush();

                entrada = new DataInputStream(socket.getInputStream());
                
                int length = 0;
                
                String estado = entrada.readLine();
                if (estado.contains("OK")) {
                    while (true) {
                        
                        String line = entrada.readLine();
                        if (line.contains("Content-Length")) {
                            length = Integer.parseInt(line.split("\\s+")[1]);
                        }
                        
                        if (line.isEmpty()) {
                            manejador.manejarImagen(entrada,length);
                            break;
                        }
                        
                    }
                    
                }

                writer.close();
                entrada.close();
                socket.close();
            
            }
            catch (IOException e) {
                System.out.println("Error al establecer la conexión.");
            }

            
            try {
                Thread.sleep(intervalo*1000);
            } catch (InterruptedException ex) {
                break;
            }
        }

    }
    
    public void empieza() {
        hilo.start();
    }
    
    public void termina() {
        try {
            writer.close();
            entrada.close();
            socket.close();
            hilo.interrupt();
        }
        catch (Exception e) {}
    }
    
    
}
