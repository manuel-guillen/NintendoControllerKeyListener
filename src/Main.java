import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import joystick.NintendoJoystickEventDispatcher;

public class Main {

    public static void main(String[] args) throws IOException {
        setGlobalScreenLogger(false);
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Unable to register the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        
        VisualController c = new VisualController();
        GlobalScreen.addNativeKeyListener(c);
        
        NintendoJoystickEventDispatcher eventDispatcher = NintendoJoystickEventDispatcher.getInstance();
        eventDispatcher.addJoystickListener(c);
        new Thread(eventDispatcher).start();
    }
    
    private static void setGlobalScreenLogger(boolean value) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(value ? Level.INFO : Level.OFF);
        logger.setUseParentHandlers(value);
    }

}
