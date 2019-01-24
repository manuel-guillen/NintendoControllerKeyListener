import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

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
        
        GlobalScreen.addNativeKeyListener(new ControllerListener());
    }
    
    private static void setGlobalScreenLogger(boolean value) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(value ? Level.INFO : Level.OFF);
        logger.setUseParentHandlers(value);
    }

}
