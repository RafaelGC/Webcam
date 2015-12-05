/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcam;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Rafa
 */
public class ConfigDialog extends JFrame implements ActionListener, WindowListener{
    private GridBagLayout layout;
    private JTextField url, ruta, intervalo;
    private JButton conectar;
    
    private ConexionWebcam conexion;
    
    public ConfigDialog() {
        super("Configuración");
        
        setupUI();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==conectar) {
            
            //¿Intervalo es un número entero?
            try {
                VistaWebcam ventanaWebcam = new VistaWebcam();
                ventanaWebcam.setVisible(true);
                
                conexion = new ConexionWebcam(url.getText(),ruta.getText(),Integer.parseInt(intervalo.getText()),ventanaWebcam);
                conexion.empieza();
                

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.getContentPane(), "El intervalo debe ser un número entero.", "Error.",JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (conexion!=null) {
            conexion.termina();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    private void setupUI() {
        layout = new GridBagLayout();
        
        url = new JTextField("turiscam.gva.es");
        ruta = new JTextField("/webcams/cullera.jpg");
        intervalo = new JTextField("3");
        
        conectar = new JButton("Conectar");
        conectar.addActionListener(this);
        
        this.getContentPane().setLayout(layout);
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Primera columna.
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 1;
        
        this.getContentPane().add(new JLabel("URL:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.getContentPane().add(new JLabel("Ruta:"),gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.getContentPane().add(new JLabel("Intervalo:"),gbc);
        
        
        //Segunda columna
        
        gbc.weightx = 2;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.getContentPane().add(url, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.getContentPane().add(ruta,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.getContentPane().add(intervalo,gbc);
        
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.getContentPane().add(conectar,gbc);
        
        
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.addWindowListener(this);
    }
    
}
