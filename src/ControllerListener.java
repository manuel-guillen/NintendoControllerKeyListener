import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

@SuppressWarnings("serial")
public class ControllerListener extends JFrame implements NativeKeyListener {
           
    private JPanel contentPane;
    private BufferedImage overlay;
    private BufferedImage pressA, pressB, pressX, pressY, pressSTART, pressSELECT, pressLEFT, pressRIGHT, pressUP, pressDOWN;
    
    private boolean drawA, drawB, drawX, drawY, drawSTART, drawSELECT, drawLEFT, drawRIGHT, drawUP, drawDOWN;
    
    public ControllerListener() throws IOException {
        overlay = ImageIO.read(new File("resources/overlay.png"));
        pressA = ImageIO.read(new File("resources/A.png"));
        pressB = ImageIO.read(new File("resources/B.png"));
        pressX = ImageIO.read(new File("resources/X.png"));
        pressY = ImageIO.read(new File("resources/Y.png"));
        pressSTART = ImageIO.read(new File("resources/START.png"));
        pressSELECT = ImageIO.read(new File("resources/SELECT.png"));
        pressLEFT = ImageIO.read(new File("resources/LEFT.png"));
        pressRIGHT = ImageIO.read(new File("resources/RIGHT.png"));
        pressUP = ImageIO.read(new File("resources/UP.png"));
        pressDOWN = ImageIO.read(new File("resources/DOWN.png"));
        
        setTitle("Nintendo Controller KeyListener");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menu = new JMenu("Menu");
//        menuBar.add(menu);
//        setJMenuBar(menuBar);
        
        contentPane = new JPanel() {
            @Override
            public void paintComponent(Graphics o) {
                super.paintComponent(o);
                Graphics2D g = (Graphics2D) o;
                g.drawImage(overlay, 0, 0, this.getWidth(), this.getHeight(), null);
                
                if(drawA)       g.drawImage(pressA, 511, 76, pressA.getWidth(), pressA.getHeight(), null);
                if(drawB)       g.drawImage(pressB, 486, 100, pressB.getWidth(), pressB.getHeight(), null);
                if(drawX)       g.drawImage(pressX, 486, 49, pressX.getWidth(), pressX.getHeight(), null);
                if(drawY)       g.drawImage(pressY, 460, 76, pressY.getWidth(), pressY.getHeight(), null);
                if(drawSTART)   g.drawImage(pressSTART, 460, 173, pressSTART.getWidth(), pressSTART.getWidth(), null);
                if(drawSELECT)  g.drawImage(pressSELECT, 460, 205, pressSELECT.getWidth(), pressSELECT.getWidth(), null);
                if(drawLEFT)    g.drawImage(pressLEFT, 30, 140, pressLEFT.getWidth(), pressLEFT.getHeight(), null);
                if(drawRIGHT)   g.drawImage(pressRIGHT, 67, 140, pressRIGHT.getWidth(), pressRIGHT.getHeight(), null);
                if(drawUP)		g.drawImage(pressUP, 49, 121, pressUP.getWidth(), pressUP.getHeight(), null);
                if(drawDOWN)    g.drawImage(pressDOWN, 49, 158, pressDOWN.getWidth(), pressDOWN.getHeight(), null);
            }
        };
        
        contentPane.setPreferredSize(new Dimension(overlay.getWidth(), overlay.getHeight()));
        setContentPane(contentPane);
        pack();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch(e.getKeyCode()) {
        case NativeKeyEvent.VC_A:           drawA = true;       break;
        case NativeKeyEvent.VC_B:           drawB = true;       break;
        case NativeKeyEvent.VC_X:           drawX = true;       break;
        case NativeKeyEvent.VC_Y:           drawY = true;       break;
        case NativeKeyEvent.VC_ENTER:       drawSTART = true;   break;
        case NativeKeyEvent.VC_BACKSPACE:   drawSELECT = true;  break;
        case NativeKeyEvent.VC_LEFT:        drawLEFT = true;    break;
        case NativeKeyEvent.VC_RIGHT:       drawRIGHT = true;   break;
        case NativeKeyEvent.VC_UP:       	drawUP = true;   	break;
        case NativeKeyEvent.VC_DOWN:        drawDOWN = true;    break;
        }
        contentPane.repaint();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        switch(e.getKeyCode()) {
        case NativeKeyEvent.VC_A:           drawA = false;       break;
        case NativeKeyEvent.VC_B:           drawB = false;       break;
        case NativeKeyEvent.VC_X:           drawX = false;       break;
        case NativeKeyEvent.VC_Y:           drawY = false;       break;
        case NativeKeyEvent.VC_ENTER:       drawSTART = false;   break;
        case NativeKeyEvent.VC_BACKSPACE:   drawSELECT = false;  break;
        case NativeKeyEvent.VC_LEFT:        drawLEFT = false;    break;
        case NativeKeyEvent.VC_RIGHT:       drawRIGHT = false;   break;
        case NativeKeyEvent.VC_UP:       	drawUP = false;   	 break;
        case NativeKeyEvent.VC_DOWN:        drawDOWN = false;    break;
        }
        contentPane.repaint();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}

}
