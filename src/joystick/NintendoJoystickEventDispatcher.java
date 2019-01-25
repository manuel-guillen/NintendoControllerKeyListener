package joystick;

import java.util.Arrays;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class NintendoJoystickEventDispatcher implements Runnable {
    
    private static NintendoJoystickEventDispatcher instance = null;
    
    public static NintendoJoystickEventDispatcher getInstance() {
        if (instance == null) instance = new NintendoJoystickEventDispatcher();
        return instance;
    }
    
    private NintendoJoystickEventDispatcher() {}
    
    // ============================================================================
    
    private JoystickListener listener;
    
    public void addJoystickListener(JoystickListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void run() {
        Controller joystick = Arrays.stream(ControllerEnvironment.getDefaultEnvironment().getControllers())
                                .filter(c -> c.getType() == Controller.Type.STICK && c.getName().equals("vJoy Device"))
                                .findFirst().get();
        
        while (true) {
            joystick.poll();
            
            EventQueue queue = joystick.getEventQueue();
            Event event = new Event();
            
            while (queue.getNextEvent(event)) listener.joystickMoved(event);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
