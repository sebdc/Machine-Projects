package source.game.states;
import source.framework.gamestate.*;
import source.framework.resources.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends GameState {

    private JButton startGame, exitGame;
    private JLabel background, title;

    public TitleScreen( GameManager manager ) {
        super( manager );
        super.setLayout( null );

        // - Initialize Button
        startGame = new JButton();
        exitGame = new JButton();
        initButtons();
        
        // - Background
        background = new JLabel();
        title = new JLabel();
        initBackground();
    }

    private void initBackground() {
        Image image, newImage;

        /******[ Title Initialization ]******/
        image = Resources.TITLE_SCREEN.get( Resources.TITLE_MENU ).getImage();
        newImage = image.getScaledInstance( 655, 340, java.awt.Image.SCALE_DEFAULT );
        title.setIcon( new ImageIcon( newImage ) ); 
        title.setLayout( null );
        title.setBounds( 300, 50, 655, 340 );
        title.setVisible( true );
        super.add( title );

        /******[ Background Initialization ]******/
        image = Resources.TITLE_SCREEN.get( Resources.TITLE_BG ).getImage();
        newImage = image.getScaledInstance( 1280, 720, java.awt.Image.SCALE_DEFAULT );
        background.setIcon( new ImageIcon( newImage ) ); 
        background.setLayout( null );
        background.setBounds( 0, 0, 1280, 720 );
        background.setVisible( true );
        super.add( background );

    }

    private void initButtons() {
        /**************************************** 
                    [ Start Button ] 
        ****************************************/
        ActionListener start = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == startGame ) {
                    manager.stackState( new GameMenu(manager) );
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                    manager.setData( new GameData() );
                }
            }
        }; startGame.addActionListener( start );

        /**************************************** 
                    [ Exit Button ] 
        ****************************************/
        ActionListener exit = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == exitGame ) {
                    System.exit(0);
                }
            }
        }; exitGame.addActionListener( exit );

        Image image, newImage; 

        /******[ Start Button Initialization ]******/
        image = Resources.TITLE_SCREEN.get( Resources.TITLE_START ).getImage();
        newImage = image.getScaledInstance( 300, 150, java.awt.Image.SCALE_DEFAULT );
        startGame.setIcon( new ImageIcon( newImage )); 
        startGame.setContentAreaFilled(false);
        startGame.setBounds( 275, 450, 300, 150 );
        super.add(startGame);

        /******[ Exit Button Initialization ]******/
        image = Resources.TITLE_SCREEN.get( Resources.TITLE_EXIT ).getImage();
        newImage = image.getScaledInstance( 300, 150, java.awt.Image.SCALE_DEFAULT );
        exitGame.setIcon( new ImageIcon( newImage )); 
        exitGame.setContentAreaFilled(false);
        exitGame.setBounds( 675, 450, 300, 150 );
        super.add(exitGame);
    }
}