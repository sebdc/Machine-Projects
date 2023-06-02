
package source.game.states;
import source.framework.gamestate.*;
import source.framework.resources.*;
import source.game.farm.*;
import source.game.farmer.*;
import source.game.store.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;


public class StoreMenu extends GameState {
    
    /// Game Attributes 
    private Farmer farmer;
    private Store store;

    // Buttons
    private JButton exitButton;
    private JButton FarmerType;
    private JButton[] Crops;
    private JButton buy_crop;

    // Labels
    private JLabel[] currType;
    private JLabel[] nextType;
    private JLabel cropType;
    
    // Panels
    private JPanel top;
    private JPanel left;
    private JPanel right;

    // Designs
    private Font cusFont;
    private Font custom_font;
    private Font crop_font;
    private JLabel tractoricon;
    private JLabel tractoricon2;
    private JLabel farmericon;
    private JLabel[] cloudicon;
    private JLabel rockicon;
    private JLabel signicon;
    private JLabel treeicon;
    private JLabel buyboardicon;
    

    public StoreMenu( GameManager manager ) {
        super( manager );
        super.setLayout(new BorderLayout(10,10));
        farmer = manager.getData().getFarmer();
        store = manager.getData().getStore();

        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        
        currType = new JLabel[5];
        nextType = new JLabel[7];
        cropType = new JLabel();

        tractoricon = new JLabel();
        farmericon = new JLabel();
        tractoricon2 = new JLabel();
        cloudicon = new JLabel[2];
        rockicon = new JLabel();
        signicon = new JLabel();
        treeicon = new JLabel();
        buyboardicon = new JLabel();
        
        exitButton = new JButton();
        FarmerType = new JButton();
        buy_crop = new JButton();
        Crops = new JButton[8];
        
        super.add( top, BorderLayout.NORTH );
        super.add( left, BorderLayout.WEST);
        super.add( right, BorderLayout.EAST);
        super.setBackground(Color.PINK);

        initUtilButtons();
        initIcons();
        initPanels();
        updateFarmerTypeDetails();
    }



    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/
    public void initPanels(){
        left.setLayout(null);
        right.setLayout(null);
        
        top.setBackground(Color.BLACK);

        left.setBackground(Color.BLACK);
        left.setPreferredSize(new Dimension(635,0));

        right.setBackground(Color.BLACK);
        right.setPreferredSize(new Dimension(635,0));
        
        for (int i = 0; i <currType.length;i++){
            currType[i] = new JLabel();
            currType[i].setBounds(100, 110+i*25, 700, 20);   
            currType[i].setForeground(new Color(255,102,0));  
            currType[i].setFont(custom_font);
            left.add(currType[i]);
        }
        for (int i = 0; i <nextType.length;i++){
            nextType[i] = new JLabel();
            nextType[i].setBounds(100, 400+i*25, 700, 20);   
            nextType[i].setForeground(new Color(0,0,204));  
            nextType[i].setFont(custom_font);
            left.add(nextType[i]);
        }

        for (int i = 0; i <cloudicon.length;i++){
            cloudicon[i] = new JLabel();
            cloudicon[i].setIcon(Resources.STORE_MENU.get(3));
            cloudicon[i].setBounds(5+(i*400) , 250, 300, 100);
            left.add(cloudicon[i]);
        }

      
        
        updateFarmerTypeDetails();
       
        left.add(FarmerType);
        left.add(farmericon);
        left.add(rockicon);
        left.add(signicon);
        left.add(treeicon);
        left.add(tractoricon);
        left.add(tractoricon2);
        right.add(buyboardicon);
        right.add(cropType);
        right.add(buy_crop);
        top.add( exitButton);
    }

    public void updateFarmerTypeDetails() {

        FarmerType currFarmerType = farmer.getType();
        FarmerType nextFarmerType = store.findNextFarmerType( currFarmerType.getFarmerType() );

        // CURRENT FARMER TYPE DETAILS
        currType[0].setText( "Farmer Type : "    + currFarmerType.getFarmerType());
        currType[1].setText( "Bonus Earning Produce : "  + currFarmerType.getBonusEarningPerProduce());
        currType[2].setText( "Seed Cost Reduction : "  + currFarmerType.getSeedCostReduction());
        currType[3].setText( "Water Bonus Increase : "  + currFarmerType.getWaterBonusInc());
        currType[4].setText( "Fertilzer Bonus Increase : " + currFarmerType.getFertilizerBonusInc());

        // NEXT FARMER TYPE DETAILS
        if( farmer.getType().getFarmerType().equals( "Legendary Farmer") == false ) { 
            nextType[0].setText( "Farmer Type : "          + nextFarmerType.getFarmerType());
            nextType[1].setText( "Level Requirement : "  + nextFarmerType.getLevelReq());
            nextType[2].setText( "Registration Fee : "  + nextFarmerType.getRegisterFee());
            nextType[3].setText( "Bonus Earning Produce : "  + nextFarmerType.getBonusEarningPerProduce());
            nextType[4].setText( "Seed Cost Reduction : "  + nextFarmerType.getSeedCostReduction());
            nextType[5].setText( "Water Bonus Increase : "  + nextFarmerType.getWaterBonusInc());
            nextType[6].setText( "Fertilzer Bonus Increase : " + nextFarmerType.getFertilizerBonusInc());
        }
    }

    public void updateCropDetails(int index){
       Crop seed = store.buyCrop(index);
       String GREEN = " <font size='null' color='green'>";
       String YELLOW = " <font size='null' color='yellow'>";
       String BLUE = " <font size='null' color='blue'>";
       String RED = " <font size='null' color='red'>";
       String tag = "</font>";

       cropType.setBounds(95, 320, 500, 300); 
       cropType.setFont(crop_font);
       cropType.setText("<html>The "+GREEN+seed.getName()+tag+" cost "+YELLOW+seed.getSeedCost()+" coins."+tag+" It is a "+GREEN+seed.getType()+tag
       +" that matures in "+RED+seed.getHarvestTime()+tag+" days. To properly grow, it must be at least "+BLUE+"watered "+seed.getWaterNeeds()
       +" times"+tag+" and"+YELLOW+" fertilized "+seed.getFertilizeNeeds()+" times."+tag+"Each harvest can produce "+GREEN+seed.getMinProduce()+" - "+seed.getMaxProduce()+tag
       +" crops and can be sold for "+YELLOW+seed.getSellPrice()+" coins"+tag+" each. This also yields "+RED+seed.getExp()+tag+" experience!!</html>");
       cropType.setForeground(Color.white);

        
        
    }
    
    public void initIcons(){
        
        // ICONS AND FONTS
        String font_name = "resource\\textures\\font\\PixelEmulator-xq08.ttf";
        custom_font = customFont(font_name, custom_font,16f);
        cusFont = customFont(font_name, cusFont, 17f);
        crop_font = customFont(font_name, crop_font, 15f);

        tractoricon.setIcon( Resources.STORE_MENU.get(1) );
        tractoricon2.setBounds(0, 510, 600, 200); 
        tractoricon2.setIcon( Resources.STORE_MENU.get(0) );
        tractoricon.setBounds(315, 510, 600, 200); 

        farmericon.setIcon(Resources.STORE_MENU.get(2));
        farmericon.setBounds(180, 10, 700, 250); 
        rockicon.setIcon(Resources.STORE_MENU.get(4));
        rockicon.setBounds(250, 15, 700, 250); 
        signicon.setIcon(Resources.STORE_MENU.get(5));
        signicon.setBounds(320, 17, 700, 250); 
        treeicon.setIcon(Resources.STORE_MENU.get(6));
        treeicon.setBounds(390,17,700,250);

        buyboardicon.setIcon(Resources.STORE_MENU.get(7));
        buyboardicon.setBounds(75, 0, 500, 500);
        buy_crop.setIcon(Resources.STORE_MENU.get(8));
        buy_crop.setBorder(null);
        
        for( int curCrop = 0; curCrop < 8; curCrop++ ) {
            Crops[curCrop].setIcon( Resources.CROP_MENU.get(curCrop) );
        }

    }

    private void initUtilButtons() {
        /**************************************** 
                      [ Exit Button ] 
        ****************************************/
        ActionListener exitToGameMenu = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == exitButton ) {
                    manager.popStack();
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
                    buy_crop.setVisible(false);
        }   }   };

         /**************************************** 
              [ Upgrade Farmer Type Button ] 
        ****************************************/
        ActionListener upgradeFarmerType = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == FarmerType ) {
                    store.upgradeFarmerType( farmer );
                    updateFarmerTypeDetails();
                    if( farmer.getType().getFarmerType().equals("Legendary Farmer") ) {
                        for( int i = 0; i < nextType.length;i++ ){
                            nextType[i].setVisible(false);
                        }
                    }
        }   }   };

         /**************************************** 
              [ View Crop Store Buttons ] 
        ****************************************/
        ActionListener viewCrop = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                for( int i = 0; i < Crops.length; i++ ){
                    if( e.getSource() == Crops[i] ) {
                        System.out.println( "You Pressed Button " + i );
                        updateCropDetails(i);                   
                        farmer.getFarmerTools().setCropIndex(i);     
                    }
        }   }   };

        /**************************************** 
              [ Buy Crop  ] 
        ****************************************/
        ActionListener buyCrop = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                if( e.getSource() == buy_crop ) {
                    // TO - DO
            
        }}   };

        

        /******[ Utility Button Initialization ]******/
        exitButton.setBounds( 5, 5, 150, 50);
        exitButton.setFocusable(false);
        exitButton.setFont( new Font("Serif",Font.PLAIN,15) );
        exitButton.setText( "Exit to Game Menu" ); 
        exitButton.addActionListener( exitToGameMenu );  
                
        FarmerType.setBounds( 200,630, 200, 30);
        FarmerType.setText("Upgrade Farmer Type");
        FarmerType.setFocusable(false);
        FarmerType.setFont( new Font("Serif",Font.PLAIN,15) );
        FarmerType.addActionListener(upgradeFarmerType);

        buy_crop.setBounds( 350,570, 300, 100);
        buy_crop.setFocusable(false);
        buy_crop.setFont( new Font("Serif",Font.PLAIN,15) );
        buy_crop.addActionListener(buyCrop);
        buy_crop.setBackground(Color.black);
        buy_crop.setVisible(true);
        
        

        for (int i = 0; i<Crops.length-4;i++){
            Crops[i] = new JButton();
            Crops[i].setBounds( i*75+180, 100, 75, 75 ); 
            Crops[i].addActionListener(viewCrop);
            Crops[i].setFocusable( false );
            Crops[i].setBackground(new Color(255,255,153));
            right.add(Crops[i]);
            
        }

        for (int i = 0;i <Crops.length-4;i++){
            Crops[i+4] = new JButton();
            Crops[i+4].setBounds( i*75+180, 250, 75, 75 ); 
            Crops[i+4].addActionListener(viewCrop);
            Crops[i+4].setFocusable( false );
            Crops[i+4].setBackground(new Color(255,255,153));
            right.add(Crops[i+4]);
        }
        
        
        

    }

    public Font customFont(String font_name, Font custom_font, float size){
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
