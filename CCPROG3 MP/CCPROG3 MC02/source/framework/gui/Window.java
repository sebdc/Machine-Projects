package source.framework.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

    private JFrame frame;
    private JPanel panel;

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public Window() {
        frame = new JFrame( "MCO2 - MyFarm" );
        frame.setBounds( 125, 60, 0, 0 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setSize( WIDTH, HEIGHT );
    }

    public void changePanel( JPanel panel ) {
        this.panel = panel;
        this.panel.setPreferredSize( new Dimension(WIDTH,HEIGHT) );
        this.panel.setFocusable( true );
        this.panel.requestFocusInWindow();
    }

    public void createWindow() {
        frame.setContentPane( panel );
        frame.revalidate();
        frame.pack();
        frame.setVisible( true );
    }
}