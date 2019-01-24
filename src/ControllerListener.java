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
    private BufferedImage pressA, pressB, pressX, pressY, pressSTART, pressSELECT, pressLEFT, pressRIGHT, pressUP, pressDOWN, pressL, pressR, pressZL, pressZR;
    
    private boolean drawA, drawB, drawX, drawY, drawSTART, drawSELECT, drawLEFT, drawRIGHT, drawUP, drawDOWN, drawL, drawR, drawZL, drawZR;
    
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
        pressL = ImageIO.read(new File("resources/L.png"));
        pressR = ImageIO.read(new File("resources/R.png"));
        pressZL = ImageIO.read(new File("resources/ZL.png"));
        pressZR = ImageIO.read(new File("resources/ZR.png"));
        
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
                
                if(drawA)       g.drawImage(pressA, 511, 129, pressA.getWidth(), pressA.getHeight(), null);
                if(drawB)       g.drawImage(pressB, 486, 153, pressB.getWidth(), pressB.getHeight(), null);
                if(drawX)       g.drawImage(pressX, 486, 102, pressX.getWidth(), pressX.getHeight(), null);
                if(drawY)       g.drawImage(pressY, 460, 129, pressY.getWidth(), pressY.getHeight(), null);
                if(drawSTART)   g.drawImage(pressSTART, 460, 226, pressSTART.getWidth(), pressSTART.getWidth(), null);
                if(drawSELECT)  g.drawImage(pressSELECT, 460, 258, pressSELECT.getWidth(), pressSELECT.getWidth(), null);
                if(drawLEFT)    g.drawImage(pressLEFT, 30, 193, pressLEFT.getWidth(), pressLEFT.getHeight(), null);
                if(drawRIGHT)   g.drawImage(pressRIGHT, 67, 193, pressRIGHT.getWidth(), pressRIGHT.getHeight(), null);
                if(drawUP)		g.drawImage(pressUP, 49, 174, pressUP.getWidth(), pressUP.getHeight(), null);
                if(drawDOWN)    g.drawImage(pressDOWN, 49, 211, pressDOWN.getWidth(), pressDOWN.getHeight(), null);
                if(drawL)		g.drawImage(pressL, 5, 8, pressL.getWidth(), pressL.getHeight(), null);
                if(drawR)		g.drawImage(pressR, 478, 8, pressR.getWidth(), pressR.getHeight(), null);
                if(drawZL)		g.drawImage(pressZL, 94, 20, pressZL.getWidth(), pressZL.getHeight(), null);
                if(drawZR)		g.drawImage(pressZR, 428, 20, pressZR.getWidth(), pressZR.getHeight(), null);
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
        case NativeKeyEvent.VC_L:           drawL = true;       break;
        case NativeKeyEvent.VC_R:           drawR = true;       break;
        case NativeKeyEvent.VC_Q:           drawZL = true;      break;
        case NativeKeyEvent.VC_W:           drawZR = true;      break;
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
        case NativeKeyEvent.VC_L:           drawL = false;       break;
        case NativeKeyEvent.VC_R:           drawR = false;       break;
        case NativeKeyEvent.VC_Q:           drawZL = false;      break;
        case NativeKeyEvent.VC_W:           drawZR = false;      break;
        }
        contentPane.repaint();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}

}
