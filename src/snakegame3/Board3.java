/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author shivr
 */
public class Board3 extends JPanel implements ActionListener{
    //to catch the actions by clicking we use ActionListener interface
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    private  final int All_Dots = 900;
    private final int dot_Size = 10;
    private static int apple_x;
    private static int apple_y;
    private final int random_Position=29;
    private Timer timer;
    //for moving in particular direction we 
    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;
    private boolean inGame=true;
    private final int x[]= new int[All_Dots];
    private final int y[]= new int[All_Dots];
    
    Board3()
    {
        //give size because the new page begin here
        setSize(500, 500);
        //initialize TAdapter class through function of interface ActionListener
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        //to focus on screen from beginning before clicking
        loadImages();
         initGame();
        //to initialize game,but they dots are actually images so firstly you load the image
        
    }
    
    public void loadImages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakeGame3/icons/apple.png"));
        //taking  image from objects to varible that is Image type
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakeGame3/icons/dot.png"));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakeGame3/icons/head.png")); 
        head = i3.getImage();
    }
    public void initGame()
        {
            dots =3;
            for(int i=0;i<dots;i++)
            {
                y[i]=50;
                x[i]=50-i*dot_Size;
                
            }
            locateApple();
            //use timer to set up time
            timer =new Timer(140,this);
            timer.start();//then it peform action so goining in actionPerformed method
            
        }

    public void locateApple()
    {
        int r =  (int)(Math.random()*random_Position);
        apple_x = r*dot_Size;
        r =  (int)(Math.random()*random_Position);
        apple_y = r*dot_Size;
    }
     // to show the images on frame we use paintComponent method of Graphic class
     //super method get a parent color
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
       draw(g);
       
    }
    
      public void draw(Graphics g )
    {
        if(inGame){
        g.drawImage(apple, apple_x, apple_y, this);
        for(int i=0;i<dots;i++)
            {
               if(i==0)
               {
                   g.drawImage(head, x[i], y[i], this);
               }
               else
               {
                   g.drawImage(dot, x[i], y[i], this);
               }
                
            }
        Toolkit.getDefaultToolkit().sync();
        }
        else
        {
            gameOver(g);
        }
    }
      public void gameOver(Graphics g)
    {
        String msg ="Game Over!!";
        Font font = new Font("SAN_SERIF",Font.BOLD,20);
        FontMetrics metrices =getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,((500-metrices.stringWidth(msg))/2), 500/2);
        
    }
      
      public void move()
      {
          for(int i=dots;i>0;i--)
          {
              x[i]=x[i-1];
              y[i]=y[i-1];
             
          }
          //for moving in particular direction we 
         // x[0]+=dot_Size;
         // y[0]+=dot_Size;
         if(leftDirection)
         {
             x[0]-=dot_Size;
         }
          if(rightDirection)
         {
             x[0]+=dot_Size;
         }
           if(upDirection)
         {
             y[0]-=dot_Size;
         }
            if(downDirection)
         {
             y[0]+=dot_Size;
         }
            // but for  capturing action we use inner class and that extends KeyAdapter
      }
      public void checkApple(){
          if(x[0]==apple_x && y[0]==apple_y)
          {
              dots++;
              locateApple();
          }
      }
      public void checkCollision()
      {
          for(int i = dots ;i>0;i--)
          {
              if((i>4) && x[0]==x[i] && y[0]==y[i])
              {
                  inGame=false;
              }
              if(x[0]>=500)
              {
                 inGame=false;
 
              }
              if(y[0]>=500)
              {
                 inGame=false;
 
              }
              if(x[0]<0)
              {
                 inGame=false;
 
              }
              if(y[0]<0)
              {
                 inGame=false;
 
              }
          }
          if(!inGame)
              timer.stop();
          
      }
//override the actionPerformed
      
      public void actionPerformed(ActionEvent ae)
      {
         if(inGame){ //for check snake got apple
          checkApple();
          checkCollision();
          //for snake move
          move();
         }
          repaint();
          //refresh the screeen
      }
     
     public class TAdapter extends KeyAdapter{
         public void keyPressed(KeyEvent e)
         {
             int key = e.getKeyCode();
             //when left can only happen when right if false
             if(key==KeyEvent.VK_LEFT && !(rightDirection))
             {
                 leftDirection=true;
                 upDirection=false;
                 downDirection=false;
                 
             }
              if(key==KeyEvent.VK_RIGHT && !(leftDirection) )
             {
                 rightDirection=true;
                 upDirection=false;
                 downDirection=false;
             }
               if(key==KeyEvent.VK_UP && !(downDirection) )
             {
                 upDirection=true;
                 rightDirection=false;
                 leftDirection=false;
             }
                if(key==KeyEvent.VK_DOWN && !(upDirection) )
             {
                 downDirection=true;
                 rightDirection=false;
                 leftDirection=false;
             }
         }
         
     }
    
     
     
    
    

    
}
