/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame3;

/**
 *
 * @author shivr
 */

import javax.swing.*;
import java.awt.*;

public class SnakeGame3 extends JFrame{

     public SnakeGame3(){
       super("Snake Game");
       add(new Board3());
       pack();//refresh screen
        setSize(500, 500);
       setLocationRelativeTo(null);
       setVisible(true);
       
     }
    public static void main(String[] args) {
          new SnakeGame3();
    }
    
}
