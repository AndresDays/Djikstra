/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package djikstra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author macbookpro
 */
public class Keys implements KeyListener {
    private final PathfinderPanel dp;
    
    public Keys(PathfinderPanel dp) {
        this.dp = dp;
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_SPACE)
            dp.autoSearch();
        else if(key == KeyEvent.VK_BACK_SPACE)
            dp.search();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}