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

import joystick.JoystickListener;
import net.java.games.input.Event;

@SuppressWarnings("serial")
public class VisualController extends JFrame implements NativeKeyListener, JoystickListener {
           
    private JPanel contentPane;
    private BufferedImage overlay;
    private BufferedImage pressA, pressB, pressX, pressY, pressSTART, pressSELECT, pressLEFT, pressRIGHT, pressUP, pressDOWN, pressL, pressR, pressZL, pressZR;
    
    private boolean drawA, drawB, drawX, drawY, drawSTART, drawSELECT, drawLEFT, drawRIGHT, drawUP, drawDOWN, drawL, drawR, drawZL, drawZR;
    private int keyA = NativeKeyEvent.VC_A, 
                keyB = NativeKeyEvent.VC_B,
                keyX = NativeKeyEvent.VC_X,
                keyY = NativeKeyEvent.VC_Y,
                keySTART = NativeKeyEvent.VC_ENTER,
                keySELECT = NativeKeyEvent.VC_BACKSPACE,
                keyLEFT = NativeKeyEvent.VC_LEFT,
                keyRIGHT = NativeKeyEvent.VC_RIGHT,
                keyUP = NativeKeyEvent.VC_UP,
                keyDOWN = NativeKeyEvent.VC_DOWN,
                keyL = NativeKeyEvent.VC_L,
                keyR = NativeKeyEvent.VC_R,
                keyZL = NativeKeyEvent.VC_Q,
                keyZR = NativeKeyEvent.VC_W;
    
    public VisualController() throws IOException {
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

    private void keyRespond(int keyCode, boolean keyHeld) {
        if (keyCode == keyA)             drawA = keyHeld;
        else if (keyCode == keyB)        drawB = keyHeld;
        else if (keyCode == keyX)        drawX = keyHeld;
        else if (keyCode == keyY)        drawY = keyHeld;
        else if (keyCode == keySTART)    drawSTART = keyHeld;
        else if (keyCode == keySELECT)   drawSELECT = keyHeld;
        else if (keyCode == keyLEFT)     drawLEFT = keyHeld;
        else if (keyCode == keyRIGHT)    drawRIGHT = keyHeld;
        else if (keyCode == keyUP)       drawUP = keyHeld;
        else if (keyCode == keyDOWN)     drawDOWN = keyHeld; 
        else if (keyCode == keyL)        drawL = keyHeld;
        else if (keyCode == keyR)        drawR = keyHeld;
        else if (keyCode == keyZL)       drawZL = keyHeld;
        else if (keyCode == keyZR)       drawZR = keyHeld;
        contentPane.repaint();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keyRespond(e.getKeyCode(), true);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keyRespond(e.getKeyCode(), false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}

    @Override
    public void joystickMoved(Event e) {
        System.out.println(e.getValue());        
    }

}
