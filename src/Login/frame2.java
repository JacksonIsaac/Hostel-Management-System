/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frame2 extends Applet implements ActionListener {

    frame f;
    Button b1, b2;

    public void init() {

        f = new frame("Frame window");
        b1 = new Button("show");
        b2 = new Button("hide");
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        f.setSize(200, 200);

        f.setBackground(Color.cyan);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.setVisible(false);
            }
        });

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.setVisible(true);
        }
        if (ae.getSource() == b2) {
            f.setVisible(false);
        }
    }
}