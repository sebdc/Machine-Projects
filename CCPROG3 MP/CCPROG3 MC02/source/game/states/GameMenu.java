package source.game.states;
import source.framework.gamestate.*;
import source.framework.resources.Resources;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameMenu extends GameState {

    private JButton visitFarm, visitStore, goToNextDay, exitToTitleScreen;

    private JPanel[] panel;
    private JLabel[] background;

    public GameMenu( GameManager manager ) {
        super( manager );
        super.setLayout(null);
    
        // - Initialize Buttons
        visitFarm = new JButton();
        visitStore = new JButton();
        goToNextDay = new JButton();
        exitToTitleScreen = new JButton();
       
        // - Initialize Panels and Backgrounds 
        panel = new JPanel[4];
        background = new JLabel[4];
       
        initView();
        initButtons();
    }

    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/
    public void initView(){
        
        /******[ View Initialization ]******/
        for( int i= 0; i < panel.length; i++) {
            background[i] = new JLabel();
            panel[i] = new JPanel();
            panel[i].setBounds(0, -20, 320*(i+1), 820);
            super.add(panel[i]);
        }
       
        /**************************************** 
                       Visit Farm
        ****************************************/
        visitFarm.setBounds( 80, 100, 150, 50 );
        visitFarm.setText( "Visit Farm" );
        background[0].setBounds(0,0,320,760);
        background[0].setIcon(Resources.GAME_MENU.get(0));
        panel[0].setLayout(null);
        panel[0].add(visitFarm);
        panel[0].add(background[0]);

        /**************************************** 
                       Visit Store
        ****************************************/
        visitStore.setText( "Visit Store" );    
        visitStore.setBounds( 400, 100, 150, 50 );
        visitStore.setFocusable(false);
        background[1].setIcon(Resources.GAME_MENU.get(1));
        background[1].setBounds(320,0,320,750);
        panel[1].setLayout(null);
        panel[1].add(visitStore);
        panel[1].add(background[1]);
        
        /**************************************** 
                         Sleep
        ****************************************/
        goToNextDay.setText( "Sleep" );
        goToNextDay.setBounds( 720, 100, 150, 50 );
        goToNextDay.setFocusable(false);
        background[2].setIcon(Resources.GAME_MENU.get(2));
        background[2].setBounds(640,0,320,750);
        panel[2].setLayout(null);
        panel[2].add(goToNextDay);
        panel[2].add(background[2]);
        
        /**************************************** 
                         Exit
        ****************************************/
        exitToTitleScreen.setText( "Exit to Title Screen" );
        exitToTitleScreen.setBounds( 1040, 100, 150, 50 );
        exitToTitleScreen.setFocusable(false);
        background[3].setIcon(Resources.GAME_MENU.get(3));
        background[3].setBounds(960,0,320,750);
        panel[3].setLayout(null);
        panel[3].add(exitToTitleScreen);
        panel[3].add(background[3]);
    }
    
    private void initButtons() {
        /**************************************** 
                    [ Visit Farm Button ] 
        ****************************************/
        ActionListener start = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == visitFarm ) {
                    manager.stackState( new FarmNavigation(manager) );
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                }
            }
        }; visitFarm.addActionListener( start );

        /**************************************** 
                   [ Visit Store Button ] 
        ****************************************/
        ActionListener goToStore = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == visitStore ) {
                    manager.stackState( new StoreMenu(manager) );
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                }
            }
        }; visitStore.addActionListener( goToStore );

        /**************************************** 
                  [ goToNextDay Button ] 
        ****************************************/
        ActionListener nextDay = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == goToNextDay ) {
                    manager.stackState( new NextDay(manager) );
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                }
            }
        }; goToNextDay.addActionListener( nextDay );

        /**************************************** 
                    [ Exit Button ] 
        ****************************************/
        ActionListener exit = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == exitToTitleScreen ) {
                    manager.popStack();
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                }
            }
        }; exitToTitleScreen.addActionListener( exit );
    }
}