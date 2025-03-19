package org.senegas.remotecontrol.view;

import org.senegas.remotecontrol.RemoteControlApp;
import org.senegas.remotecontrol.model.RemoteControlButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import java.awt.event.ActionListener;

public class RemoteControlView extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(RemoteControlView.class.getName());
    private RemoteControlController controller;

    public RemoteControlView(RemoteControlController controller) {
        this.controller = controller;
        setTitle("Remote Control");
        setIconImage(createImage("/images/remote-control_icon256.png", "icon"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 3));

        for (RemoteControlButton button : RemoteControlButton.values()) {
            JButton btn = new JButton(button.getDisplayName());
            btn.putClientProperty("JButton.buttonType", "roundRect");
            btn.setBackground(button.getBackgroundColor());
            btn.setForeground(button.getTextColor());

            btn.addActionListener(new ButtonListener(button));
            add(btn);
        }

        pack();
        setVisible(true);
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = RemoteControlApp.class.getResource(path);
        if (imageURL == null) {
            LOGGER.log(Level.SEVERE, "Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    private class ButtonListener implements ActionListener {
        private RemoteControlButton button;

        public ButtonListener(RemoteControlButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.buttonPressed(this.button);
        }
    }
}
