/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package djikstra;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author macbookpro
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new PathfinderPanel());
        window.setTitle("DIJKSTRA");
        
        JOptionPane.showConfirmDialog(window, "Como desear activar el juego?\n1. Presiona espacio para ejecutar automaticamente.\n", "Bienvenido", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
}