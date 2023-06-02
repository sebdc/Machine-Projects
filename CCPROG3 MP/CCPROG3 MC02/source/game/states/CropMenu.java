package source.game.states;
import source.game.farmer.*; 
import source.framework.gamestate.*;
import source.framework.resources.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CropMenu extends GameState {
    
    /// Game Attributes
    Farmer farmer;

    /// GUI Attributes
    private JButton exitButton;
    private JButton crops[];

    /// JButton Array Sizes
    private static final int CROPS = 8;

    // DESIGNS
    private JLabel background;
    private JPanel container;

    public CropMenu( GameManager manager ) {
        super( manager );
        super.setLayout(null);

        // - Game Attributes
        farmer = manager.getData().getFarmer();

        // - Declare Buttons
        crops = new JButton[CROPS];
        exitButton = new JButton();
        background = new JLabel();
        container = new JPanel();

        // - Initialize Buttons
       
        initCropInventory();
        initUtilButtons();
        initBackground();
        
        super.add(background);
    }


    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/ 
    public void initBackground(){
        container.setBounds(0, 0, 1280, 720);
        background.setBounds(0, 0, 1280, 720);
        background.setIcon(Resources.CROP_MENU.get(8));
    }
    
    private void initCropInventory() {
        /**************************************** 
                    [ Crop Buttons ] 
        ****************************************/
        ActionListener clickCrop = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                for( int curCrop = 0; curCrop < CROPS; curCrop++ ) {
                    if( e.getSource() == crops[curCrop] ) {
                        farmer.getFarmerTools().setCropIndex(curCrop);
                        System.out.printf( "Crop[%d] - %s selected\n", curCrop,
                            manager.getData().getStore().buyCrop(curCrop).getName() );
        }   }   }   };

        /******[ Crop Button Initialization ]******/
        for( int curCrop = 0; curCrop < CROPS; curCrop++ ) {
            crops[curCrop] = new JButton();
            crops[curCrop].setBounds( curCrop*100+55, 500, 75, 75 );  
            crops[curCrop].addActionListener( clickCrop );  
            crops[curCrop].setFocusable( false );
            crops[curCrop].setIcon( Resources.CROP_MENU.get(curCrop) );
            crops[curCrop].setBackground(new Color(204,204,204));
            super.add( crops[curCrop] );
        }
    }

    private void initUtilButtons() {
        /**************************************** 
                      [ Exit Button ] 
        ****************************************/
        ActionListener exitInventory = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == exitButton ) {
                    manager.popStack();
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
        }   }   };

        /******[ Utility Button Initialization ]******/
        exitButton.setBounds( 5, 5, 150, 50);
        exitButton.setFocusable(false);
        exitButton.setFont( new Font("Serif",Font.PLAIN,15) );
        exitButton.setText( "Go Back" ); 
        exitButton.addActionListener( exitInventory );    
        super.add( exitButton );
    }
}