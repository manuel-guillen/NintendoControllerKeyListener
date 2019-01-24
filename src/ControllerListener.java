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
    private BufferedImage controller;
    private BufferedImage bluePress, redPress, greenPress, yellowPress, cyanPress, cyanPress2;
    
    private boolean drawX, drawY, drawA, drawB, drawSTART, drawSELECT;
    
    public ControllerListener() throws IOException {
        controller = ImageIO.read(new File("resources/overlay.png"));
        bluePress = ImageIO.read(new File("resources/blue.png"));
        redPress = ImageIO.read(new File("resources/red.png"));
        greenPress = ImageIO.read(new File("resources/green.png"));
        yellowPress = ImageIO.read(new File("resources/yellow.png"));
        cyanPress = ImageIO.read(new File("resources/cyan.png"));
        cyanPress2 = ImageIO.read(new File("resources/cyan2.png"));
        
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
                g.drawImage(controller, 0, 0, this.getWidth(), this.getHeight(), null);
                
                if(drawA)       g.drawImage(redPress, 511, 76, redPress.getWidth(), redPress.getHeight(), null);
                if(drawB)       g.drawImage(yellowPress, 486, 100, yellowPress.getWidth(), yellowPress.getHeight(), null);
                if(drawX)       g.drawImage(bluePress, 486, 49, bluePress.getWidth(), bluePress.getHeight(), null);
                if(drawY)       g.drawImage(greenPress, 460, 76, greenPress.getWidth(), greenPress.getHeight(), null);
                if(drawSTART)   g.drawImage(cyanPress, 460, 173, cyanPress.getWidth(), cyanPress.getWidth(), null);
                if(drawSELECT)  g.drawImage(cyanPress2, 460, 205, cyanPress2.getWidth(), cyanPress2.getWidth(), null);
            }
        };
        
        contentPane.setPreferredSize(new Dimension(controller.getWidth(), controller.getHeight()));
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
        }
        contentPane.repaint();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}

}
