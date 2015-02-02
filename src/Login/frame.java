package Login;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
/*
 <applet code=frame1.class width=300 height=200>
 </applet>
 */

class frame extends Frame implements ActionListener {

    Button b;
    TextField tf;
    String msg = "hello from frame window";

    frame(String s) {
        super(s);
        setLayout(new FlowLayout());
        b = new Button("click");
        add(b);
        b.addActionListener(this);
        tf = new TextField(15);
        add(tf);
    }

    public void actionPerformed(ActionEvent ae) {
        msg = "button click";
        repaint();
    }

    public void paint(Graphics g) {
        g.drawString(msg, 50, 150);
    }
}