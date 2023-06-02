package source.game.states;
import source.framework.gamestate.*;
import source.framework.resources.Resources;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class NextDay extends GameState {

    /// GUI Attributes
    private JButton nextDay;
    private JButton backBTN;
    private JPanel component;
    private JLabel background;
    private JLabel losingbackground;
    private JLabel dayText;
    private Font custom_font;

    public NextDay( GameManager manager ) {
        super( manager );
        super.setLayout(new BorderLayout());
        nextDay();

        // - Declare GUI 
        nextDay = new JButton();
        backBTN = new JButton();
        component = new JPanel();
        background = new JLabel();
        losingbackground = new JLabel();
        dayText = new JLabel();

        // - Initialize GUI 
        initView();
        initButtons();

        super.add(component, BorderLayout.CENTER);
    }
    
    /*|*******************************************************
     
                        Behavior Methods

    *********************************************************/ 
    public void nextDay() {
        manager.getData().getTime().nextDay( manager.getData().getFarm() );
    }

    
    /** 
     * @return Boolean
     */
    public Boolean checkGameStatus() {
        Boolean lose = false, ongoing = true;
        Boolean doesFarmerHaveCoins = manager.getData().getFarmer().getCoins() > 5;
        Boolean isThereAGrowingCrop = manager.getData().getFarm().areCropsPlanted();

        if( doesFarmerHaveCoins == false && isThereAGrowingCrop == false ) 
            return lose;
        else    
            return ongoing;
    }

    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/
    private void initView(){
        // Initialize custom font style
        String font_name = "resource\\textures\\font\\PixelEmulator-xq08.ttf";
        custom_font = customFont(font_name, custom_font, 80f);
        // - Initialize nextDay
       
        // nextDay.setText( "Proceed to Day " + (manager.getData().getTime().getDay() + 1) );
        nextDay.setBounds( 635, 340, 110, 110 );
        nextDay.setIcon(Resources.NEXTDAY.get(0));
        nextDay.setBackground(Color.white);
        nextDay.setBorder(null);
        nextDay.setFocusable(false);
        nextDay.setVisible(true);

        backBTN.setText("Back to Title Screen");
        backBTN.setBounds( 600, 70, 150, 30 );
        backBTN.setFocusable(false);
        backBTN.setVisible(false);

        dayText.setText("Day "+(manager.getData().getTime().getDay())+"...");
        dayText.setForeground(new Color(255,102,0));
        dayText.setBounds(520, 40, 500, 100);
        dayText.setFont(custom_font);
        dayText.setVisible(true);
       
        component.setLayout(null);
        component.setBackground(Color.black);

        background.setBounds(0, 0, 1280, 720);
        background.setIcon(Resources.NEXTDAY.get(1));
        background.setVisible(true);

        losingbackground.setBounds(0, 0, 1280, 720);
        losingbackground.setIcon(Resources.NEXTDAY.get(2));
        losingbackground.setVisible(false);
        
        component.add(dayText);
        component.add(nextDay);
        component.add(background);
        component.add(backBTN);
        component.add(losingbackground);
    }   

    private void initButtons() {
        /**************************************** 
                    [ nextDay Button ] 
        ****************************************/
        ActionListener nextDayAction = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == nextDay ) {
                    if( checkGameStatus() == true ) {
                        manager.popStack();
                        manager.getWindow().changePanel( manager.peekStack() );
                        manager.getWindow().createWindow();
                    } 
                    else if( checkGameStatus() == false ) {
                        losingbackground.setVisible(true);
                        backBTN.setVisible(true);
                        nextDay.setVisible(false);
                        background.setVisible(false);
                        dayText.setVisible(false);
                       
        }   }   }   }; 
        nextDay.addActionListener( nextDayAction );

          /**************************************** 
                    [ Back to Title Screen Button] 
        ****************************************/
        ActionListener backtoTitleScreen = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == backBTN ) {
                    
                     manager.popStack();
                     manager.popStack();
                     manager.getWindow().changePanel( manager.peekStack() );
                     manager.getWindow().createWindow();
                }
            }
        }; backBTN.addActionListener( backtoTitleScreen );
    }
    

    private Font customFont(String font_name, Font custom_font, float size) {
        try{
            // load a custom font in your project folder
			custom_font = Font.createFont(Font.TRUETYPE_FONT, new File(font_name)).deriveFont(size);	
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(font_name)));			
		}
		catch(IOException | FontFormatException e){
			
		}

        return custom_font;
    }
}
