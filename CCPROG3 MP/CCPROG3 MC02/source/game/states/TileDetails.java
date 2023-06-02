package source.game.states;
import source.game.farm.*; 
import source.framework.resources.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;


import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class TileDetails extends JPanel {
    
    // Tile Details
    private JLabel detail;
    private JLabel detail2;
    private JLabel detail3;
    private JLabel seedname;
    private JLabel harvest;
   
    
    //DESIGNS
    private JPanel upperborder;
    private JPanel lowerborder;
    private JPanel rightborder;
    private JPanel leftborder;
    private JLabel icon_title;
    private Font cusFont;


    public TileDetails() {
        setLayout(null);
        setBackground(new Color(51,0,0));
        setBounds(900, 360, 500, 360);

        upperborder = new JPanel();
        lowerborder = new JPanel();
        rightborder = new JPanel();
        leftborder = new JPanel();
        icon_title = new JLabel();
        detail = new JLabel();
        detail2 = new JLabel();
        detail3 = new JLabel();
        seedname = new JLabel();
        harvest = new JLabel();
        
        initTileDetails();
    }

    /*|*******************************************************
     
                        Behavior Methods

    *********************************************************/
   public void updateTileDetails (FarmTile tile){

    seedname.setVisible(false);
    harvest.setVisible(false);
    detail.setVisible(false);
    detail2.setVisible(false);
    detail3.setVisible(false);
    
   
    String [] TileOptions ={
            "Use Pickaxe",              //0
            "Use Plow",                    //1
            "Buy and plant seeds",                          //2
            "Harvest the crop",                             //3
            "Use Watering Can",           //4
            "Use Fertilizer",         //5
            "Use Shovel",                //6
            "Use Shovel"        //7
        }; 

        /**************************************** 
            Rock 
            [0] "Use Pickaxe to destroy the rock
        ****************************************/
        if( tile.isObstructed() ) {
            detail.setText(TileOptions[0]);
            detail.setVisible(true);
        }/**************************************** 
            Free Tile 
            [1] Use Plow to plow the tile
        ****************************************/
        else if( !tile.isPlowed() ) {
            detail.setText(TileOptions[1]);
            detail.setVisible(true);
        } /**************************************** 
            Plowed Tile 
            [2] Buy and plant seeds
        ****************************************/
    else if( tile.isPlowed() && !tile.isCropPlanted() ) {
        detail.setText(TileOptions[2]);
        detail.setVisible(true);
    }
    /**************************************** 
        Harvestable Crop 
        [3] Harvest the crop                        
    ****************************************/
    else if( tile.isCropPlanted() && tile.getCrop().isHarvestable() ) {
        seedname.setText(tile.getCrop().getName());
        harvest.setText(tile.getCrop().getHarvestTime()+" day/s ( harvest time )");
        seedname.setVisible(true);
        harvest.setVisible(true);
        detail.setText(TileOptions[3]);
        detail.setVisible(true);
    }
    /**************************************** 
        Growing Crop                 
        [4] Use Watering Can to water the crop     
        [5] Use Fertilizer to fertilize the crop   
        [6] Use Shovel to remove the crop           
    ****************************************/
    else if( tile.isCropPlanted() && !tile.getCrop().isHarvestable() && !tile.getCrop().isWithered() ) {
        // If not watered and if not fertilized
        seedname.setText(tile.getCrop().getName());
        harvest.setText(tile.getCrop().getHarvestTime()+" day/s ( harvest time )");
        seedname.setVisible(true);
        harvest.setVisible(true);

        if( !tile.isWatered() && !tile.isFertilized() ) {
            
            detail.setText(TileOptions[4]);   
            detail2.setText(TileOptions[5]);
            detail3.setText(TileOptions[6]);
            detail.setVisible(true);
            detail2.setVisible(true);
            detail3.setVisible(true);
        }
        // If not watered and if fertilized
        else if( !tile.isWatered() && tile.isFertilized() ) {
            detail.setText(TileOptions[4]);
            detail2.setText(TileOptions[6]);
            detail.setVisible(true);
            detail2.setVisible(true);
        }
        // If watered and if not fertilized
        else if( tile.isWatered() && !tile.isFertilized() ) {
            detail.setText(TileOptions[5]);
            detail2.setText(TileOptions[6]);
            detail.setVisible(true);
            detail2.setVisible(true);
        }
        // If watered and fertilized
        else if( tile.isWatered() && tile.isFertilized() ) {
            detail2.setText(TileOptions[6]);
            detail2.setVisible(true);
        }
    }
    /**************************************** 
        Withered Crop                 
        [7] Use Shovel to remove the withered crop
    ****************************************/
    else if( tile.isCropPlanted() && tile.getCrop().isWithered() ) {
        seedname.setText(tile.getCrop().getName());
        harvest.setText( "The crop is withered");
        seedname.setVisible(true);
        harvest.setVisible(true);
        detail.setText(TileOptions[7]);
        detail.setVisible(true);
    }

   }

    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/
    public void initTileDetails(){
        // Initialize Border of the Panel
        upperborder.setBackground(new Color (255,255,153)); // Light Yellow
        upperborder.setBounds(0,0,500,5);
        leftborder.setBackground(new Color (255,255,153));  // Light Yellow
        leftborder.setBounds(0,0,5,500);
        rightborder.setBounds(375,0,5,500);
        rightborder.setBackground(new Color (255,255,153));
        lowerborder.setBounds(0,355,500,5);
        lowerborder.setBackground(new Color (255,255,153));

        // Customize Font Style
        String font_name = "resource\\textures\\font\\PixelEmulator-xq08.ttf";
        cusFont = customFont(font_name, cusFont, 15f);

        // Initialize Icon
        icon_title.setIcon( Resources.FARM.get(0) );
        icon_title.setBounds(130,30,250,240);

        // Initialize TileDetails

        seedname.setFont(cusFont);
        seedname.setBounds(50, 105, 500, 20);
        seedname.setForeground(new Color(255,204,0));
    
        harvest.setFont(cusFont);
        harvest.setBounds(50, 135, 500, 20);
        harvest.setForeground(Color.PINK);

        detail.setFont(cusFont);
        detail.setBounds(50,215,500,20);
        detail.setForeground(new Color(255,102,0));

        detail2.setFont(cusFont);
        detail2.setBounds(50, 245, 500, 20);
        detail2.setForeground(new Color(255,102,0));

        detail3.setFont(cusFont);
        detail3.setBounds(50, 275, 500, 20);
        detail3.setForeground(new Color(255,102,0));

        //Add all
        super.add(icon_title);
        super.add(upperborder);
        super.add(lowerborder);
        super.add(leftborder);
        super.add(rightborder);
        super.add(seedname);
        super.add(harvest);
        super.add(detail);
        super.add(detail2);
        super.add(detail3);
        super.validate();
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
