package useLogger;

import java.io.IOException;
import java.util.logging.*;

/**
 * This class is used to configure the logger
 */
public class MyLoggerConfiguration {
    // Creating a logger object.
    private static Logger LOGGER = Logger.getLogger(MyLoggerConfiguration.class.getName());
    // private static FileHandler fileHandlerHTML = null;
    // private static MyHTMLFormatter formatter = null;
    private static FileHandler fileHandler = null;

    /**
     * It creates a logger and logs the message "Hello World" to a file called logs.txt. It also creates a file called
     * logs.html and logs the message "Info Log" to it.
     */
    private static void setup() {
        try {
            if (LOGGER == null || fileHandler == null) {
                LOGGER = Logger.getLogger(MyLoggerConfiguration.class.getName());
                // fileHandlerHTML = new FileHandler("logs.html");
                // formatter = new MyHTMLFormatter();
                fileHandler = new FileHandler("logs.txt");
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLog(Level level, String message) {
        try {
            setup();
            // create a HTML formatter
            fileHandler.setFormatter(new SimpleFormatter());
            // fileHandlerHTML.setFormatter(formatter);
            // fileHandlerHTML.setLevel(Level.INFO);
            // LOGGER.addHandler(fileHandlerHTML);
            LOGGER.setUseParentHandlers(false);
            // fileHandlerHTML.setFormatter(formatter);
            LOGGER.log(level, message);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Cannot read logger file", exception);
        }
    }
}