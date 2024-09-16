package de.student.game.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerImpl implements ActionListener {

    private JFrame frame;

    public ActionListenerImpl(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}
