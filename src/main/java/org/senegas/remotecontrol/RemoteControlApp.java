/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.senegas.remotecontrol;

import com.formdev.flatlaf.FlatDarkLaf;

import org.llschall.ardwloop.ArdwloopStarter;
import org.llschall.ardwloop.IArdwConfig;
import org.senegas.remotecontrol.remote.ArdwProgram;
import org.senegas.remotecontrol.view.RemoteControlController;
import org.senegas.remotecontrol.view.RemoteControlView;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.*;

public class RemoteControlApp {

    private static final Logger LOGGER = Logger.getLogger(RemoteControlApp.class.getName());

    public static final String TITLE = "Remote Control App";
    public static final String VERSION = "0.1.0";

    static {
        try {
            // Ensure the logs directory exists
            Files.createDirectories(Paths.get("logs"));

            // Load the custom logging configuration from resources
            InputStream loggingConfig =
                    RemoteControlApp.class
                            .getClassLoader()
                            .getResourceAsStream("logging.properties");
            if (loggingConfig == null) {
                LOGGER.severe("Logging configuration file not found.");
                throw new RuntimeException("Logging configuration is required.");
            }
            LogManager.getLogManager().readConfiguration(loggingConfig);
        } catch (IOException e) {
            LOGGER.severe("Failed to load logging configuration: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        final String asciiArtTitle =
                "\n _        _     _                                 \n"
                + "| |_ ___ | |__ (_) __ _ ___  __ _ _ __ __ _ _   _ \n"
                + "| __/ _ \\| '_ \\| |/ _` / __|/ _` | '__/ _` | | | |\n"
                + "| || (_) | |_) | | (_| \\__ \\ (_| | | | (_| | |_| |\n"
                + " \\__\\___/|_.__/|_|\\__,_|___/\\__, |_|  \\__,_|\\__,_|\n"
                + "                            |___/                 ";
        LOGGER.log(Level.INFO, asciiArtTitle);
        LOGGER.log(Level.INFO, TITLE + " has started.");

        EventQueue.invokeLater(
                () -> {
                    ArdwloopStarter.get().start(new ArdwProgram(), IArdwConfig.BAUD_9600);
                    new RemoteControlApp().create();
                });
    }

    private void create() {
        FlatDarkLaf.setup();

        final RemoteControlController controller = new RemoteControlController();

        final String title = MessageFormat.format("{0} v{1}", TITLE, VERSION);
        final JFrame f = new RemoteControlView(controller);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        f.setPreferredSize(new Dimension(200, 350));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
