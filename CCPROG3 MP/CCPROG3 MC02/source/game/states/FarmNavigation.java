package source.game.states;
import source.game.farm.*; 
import source.game.farmer.*; 
import source.game.store.*;
import source.framework.gamestate.*;
import source.framework.resources.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class FarmNavigation extends GameState {

    /// Game Attributes 
    private FarmLot farm;
    private Farmer farmer;
    private Store store;
    private int selCol;
    private int selRow;

    /// JPanels
    private FarmerDetails farmerDetails;
    private TileDetails tileDetails;

    /// GUI Attributes
    private JLabel background;
    private JButton tiles[][];
    private JButton tools[];
    private JButton exitButton;

    /// JButton Array Sizes
    private static final int ROW = 5;
    private static final int COL = 10;
    private static final int TOOLS = 8;

    /// JButton Attributes
    private static final int tilesWidth = 75; 
    private static final int tilesHeight = 75;

    public FarmNavigation( GameManager manager ) {
        super( manager );
        super.setLayout(null);

        // - Game Attributes
        farm = manager.getData().getFarm();
        farmer = manager.getData().getFarmer();
        store = manager.getData().getStore();

        // - Declare Buttons
        tiles = new JButton[COL][ROW];
        tools = new JButton[TOOLS];
        exitButton = new JButton();

        // - Initialize Buttons
        initTileButtons();
        initToolButtons();
        initUtilButtons();
        updateAllTileIcons();

        // - Farmer Details
        farmerDetails = new FarmerDetails( farmer );
        super.add( farmerDetails );
        farmerDetails.setVisible( true );

        // - Tile Details
        tileDetails = new TileDetails();
        super.add( tileDetails );
        tileDetails.setVisible( true );

        // - Background
        background = new JLabel();
        initBackground();
        
    }

    /*|*******************************************************
     
                        Behavior Methods

    *********************************************************/ 
    public void updateAllTileIcons() {

        for( int curCol = 0; curCol < COL; curCol++ ) {
            for( int curRow = 0; curRow < ROW; curRow++ ) {
                updateTileIcon( farm.getTile(curCol,curRow), curCol, curRow );
            }
        }   
    }

    public void updateTileIcon( FarmTile tile, int col, int row ) {
        
        /**************************************** 
            Rock 
        ****************************************/
        if( tile.isObstructed() ) {
            ImageIcon obstructedTile = Resources.resizeImage( Resources.TILE.get(0), tilesWidth, tilesHeight );
            tiles[col][row].setIcon( obstructedTile );                 
        }
        /**************************************** 
            Free Tile 
        ****************************************/
        else if( !tile.isPlowed() ) {
            ImageIcon unplowedTile = Resources.resizeImage( Resources.TILE.get(1), tilesWidth, tilesHeight );
            tiles[col][row].setIcon( unplowedTile );
        }
        /**************************************** 
            Plowed Tile 
        ****************************************/
        else if( tile.isPlowed() && !tile.isCropPlanted() ) {
            ImageIcon plowedTile = Resources.resizeImage( Resources.TILE.get(2), tilesWidth, tilesHeight );
            tiles[col][row].setIcon( plowedTile );                 
        }
        /**************************************** 
            Withered Crops
        ****************************************/
        else if( tile.isCropPlanted() && tile.getCrop().isWithered() ) {
            ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(8), tilesWidth, tilesHeight );
            tiles[col][row].setIcon( crop );
        }
        /**************************************** 
            Growing Crops
        ****************************************/
        else if( tile.isCropPlanted() && !tile.getCrop().isHarvestable() ) {
        
            Crop crop = tile.getCrop();
            Boolean isSeed = crop.getHarvestTime() == store.findCrop( crop.getName() ).getHarvestTime();
            Boolean isGrowing = crop.getHarvestTime() < store.findCrop( crop.getName() ).getHarvestTime() && crop.getHarvestTime() != 0;

            if( isSeed ) {
                if( !tile.isFertilized() && !tile.isWatered() ) {
                    ImageIcon unfUnwSeed = Resources.resizeImage( Resources.SEEDS.get(0), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( unfUnwSeed );       
                }        
                else if( !tile.isFertilized() && tile.isWatered() ) {
                    ImageIcon unfWatSeed = Resources.resizeImage( Resources.SEEDS.get(1), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( unfWatSeed );    
                }
                else if( tile.isFertilized() && !tile.isWatered() ) {
                    ImageIcon ferUnwSeed = Resources.resizeImage( Resources.SEEDS.get(2), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( ferUnwSeed );
                }
                else if( tile.isFertilized() && tile.isWatered() ) {
                    ImageIcon ferWatSeed = Resources.resizeImage( Resources.SEEDS.get(3), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( ferWatSeed );
             }
            }
            else if( isGrowing ) {
                if( !tile.isFertilized() && !tile.isWatered() ) {
                    ImageIcon unfUnwCrop = Resources.resizeImage( Resources.SEEDS.get(4), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( unfUnwCrop );       
                }        
                else if( !tile.isFertilized() && tile.isWatered() ) {
                    ImageIcon unfWatCrop = Resources.resizeImage( Resources.SEEDS.get(5), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( unfWatCrop );    
                }
                else if( tile.isFertilized() && !tile.isWatered() ) {
                    ImageIcon ferUnwCrop = Resources.resizeImage( Resources.SEEDS.get(6), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( ferUnwCrop );
                }
                else if( tile.isFertilized() && tile.isWatered() ) {
                    ImageIcon ferWatCrop = Resources.resizeImage( Resources.SEEDS.get(7), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( ferWatCrop );
                }
            }
        }
        /**************************************** 
            Harvestable Crops
        ****************************************/
        else if( tile.isCropPlanted() && tile.getCrop().isHarvestable() ) {
            switch( tile.getCrop().getName() ) {
                case "Turnip"    :  {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(0), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Carrot"    : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(1), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Potato"    :  {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(2), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Rose"      : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(3), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Tulip"     : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(4), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Sunflower" : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(5), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Mango"     : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(6), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
                case "Apple"     : {
                    ImageIcon crop = Resources.resizeImage( Resources.CROPS.get(7), tilesWidth, tilesHeight );
                    tiles[col][row].setIcon( crop );
                } break;
            }
        }
    }

    /*|*******************************************************
     
                      Initialization Methods

    *********************************************************/    
    private void initBackground() {
        Image image, newImage;

        image = Resources.FARM.get( Resources.FARM_BG ).getImage();
        newImage = image.getScaledInstance( 1280, 720, java.awt.Image.SCALE_DEFAULT );
        background.setIcon( new ImageIcon( newImage ) ); 
        background.setLayout( null );
        background.setBounds( 0, 0, 1280, 720 ); 
        background.setVisible( true );
        super.add( background );
    }

    private void initTileButtons() {

        /**************************************** 
                    [ Tile Buttons ] 
        ****************************************/
        ActionListener clickTile = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                for( int curCol = 0; curCol < COL; curCol++ ) {
                    for( int curRow = 0; curRow < ROW; curRow++ ) {
                        if( e.getSource() == tiles[curCol][curRow] ) {
                            System.out.printf( "You Pressed [%d][%d]\n", curCol, curRow );
                            selCol = curCol;
                            selRow = curRow;
                            tileDetails.updateTileDetails( farm.getTile( selCol, selRow ) );
                            farmerDetails.updateFarmerDetails();
        }   }   }   }   };  

        /******[ Tiles Button Initialization ]******/
        for( int curCol = 0; curCol < COL; curCol++ ) {
            for( int curRow = 0; curRow < ROW; curRow++ ) {
                tiles[curCol][curRow] = new JButton();
                tiles[curCol][curRow].setBounds( curCol*75+90, curRow*75+100, tilesWidth, tilesHeight );    
                tiles[curCol][curRow].addActionListener( clickTile );
                tiles[curCol][curRow].setFocusable( false );
                super.add( tiles[curCol][curRow]);
        }   }
    }

    private void initToolButtons() {

        /**************************************** 
                      [ Plow Button ] 
        ****************************************/
        ActionListener plow = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );
                Boolean canFarmerAfford = farmer.getCoins() >= farmer.getFarmerTools().getPlow().getCost();

                if( e.getSource() == tools[0] ) {
                    if( !selTile.isObstructed() && !selTile.isPlowed() && canFarmerAfford ) {
                        farmer.usePlow( selTile );
                        System.out.printf( "Tile[%d][%d] plowed ", selCol, selRow );
                        updateTileIcon( selTile, selCol, selRow );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails();
                    } 
                    else if( selTile.isPlowed() ) {
                        JOptionPane.showMessageDialog( null, "\t     The tile is already plowed", "ERROR", JOptionPane.ERROR_MESSAGE );
                    } 
                    else if( selTile.isObstructed() ) {
                        JOptionPane.showMessageDialog( null, "\t     There is a rock blocking the tile", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( !canFarmerAfford ) {
                            JOptionPane.showMessageDialog( null, "\t     You do not have enough coins", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   };

        /**************************************** 
                      [ Plant Button ] 
        ****************************************/
        ActionListener plant = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );

                if( e.getSource() == tools[1] ) {
                    if( selTile.isPlowed() && !selTile.isCropPlanted() ) {
                        farmer.plantCrop( farm, store.buyCrop( farmer.getFarmerTools().getCropIndex() ), selRow, selCol );
                        if( selTile.isCropPlanted() ) {
                            updateTileIcon( selTile, selCol, selRow );
                            tileDetails.updateTileDetails(selTile);
                            farmerDetails.updateFarmerDetails();
                        }
                    }   }   }   };

        /**************************************** 
                  [ WateringCan Button ] 
        ****************************************/
        ActionListener water = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );
                Boolean isTileWaterable = selTile.isCropPlanted() && !selTile.getCrop().isHarvestable() && !selTile.isWatered();
                Boolean canFarmerAfford = farmer.getCoins() >= farmer.getFarmerTools().getWateringCan().getCost();

                if( e.getSource() == tools[2] ) {
                    if( isTileWaterable && canFarmerAfford ) {
                        farmer.useWateringCan( selTile );
                        System.out.printf( "Crop has been watered" );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails();
                        updateTileIcon( selTile, selCol, selRow );
                    } 
                    else if ( selTile.isWatered() ) {
                        JOptionPane.showMessageDialog( null, "\t     The tile is already watered", "ERROR", JOptionPane.ERROR_MESSAGE );
                    } 
                    else if ( !selTile.isPlowed() ) {
                        JOptionPane.showMessageDialog( null, "\t     The tile is still unplowed", "ERROR", JOptionPane.ERROR_MESSAGE );
                    } 
                    else if ( selTile.getCrop().isHarvestable() ) {
                        JOptionPane.showMessageDialog( null, "\t     The crop has already matured", "ERROR", JOptionPane.WARNING_MESSAGE );
                    } 
                    else if ( !selTile.isCropPlanted() ) {
                        JOptionPane.showMessageDialog( null, "\t     There is no crop planted", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( !canFarmerAfford ) {
                            JOptionPane.showMessageDialog( null, "\t     You do not have enough coins", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   };

        /**************************************** 
                  [ Fertilizer Button ] 
        ****************************************/
        ActionListener fertilize = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );
                Boolean isFertilizeable = selTile.isCropPlanted() && !selTile.getCrop().isHarvestable() && !selTile.isFertilized();
                Boolean canFarmerAfford = farmer.getCoins() >= farmer.getFarmerTools().getFertilizer().getCost();

                if( e.getSource() == tools[3] ) {
                    if( isFertilizeable && canFarmerAfford ) {
                        farmer.useFertilizer(selTile);
                        System.out.printf( "Crop has been fertilized" );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails();
                        updateTileIcon( selTile, selCol, selRow );

                    } 
                    else if( selTile.isFertilized() ) {
                        JOptionPane.showMessageDialog( null, "\t     The tile is already fertilized", "ERROR", JOptionPane.ERROR_MESSAGE );
                    } 
                    else if( !selTile.isPlowed() ) {
                        JOptionPane.showMessageDialog( null, "\t     The tile is still unplowed", "ERROR", JOptionPane.ERROR_MESSAGE );
                    } 
                    else if( selTile.getCrop().isHarvestable() ) {
                        JOptionPane.showMessageDialog( null, "\t     The crop has already matured", "ERROR", JOptionPane.WARNING_MESSAGE );
                    } 
                    else if( !selTile.isCropPlanted() ) {
                        JOptionPane.showMessageDialog( null, "\t     There is no crop planted", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( !canFarmerAfford ) {
                        JOptionPane.showMessageDialog( null, "\t     You do not have enough coins", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   };

        /**************************************** 
                   [ Harvest Button ] 
        ****************************************/
        ActionListener harvest = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );

                if( e.getSource() == tools[4] ) {
                    if( selTile.isCropPlanted() && selTile.getCrop().isHarvestable() ) {
                        farmer.harvestCrop(selTile);
                        System.out.printf( "Crop has been harvested!" );
                        updateTileIcon( selTile, selCol, selRow );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails(); 
                    } 
                    else if( !selTile.getCrop().isHarvestable() ) {
                        JOptionPane.showMessageDialog( null, "\t     The crop is not yet harvestable", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   }; 

        /**************************************** 
                [ Crop Inventory Button ] 
        ****************************************/
        ActionListener crop =  new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                if( e.getSource() == tools[5] ) {
                    manager.stackState( new CropMenu(manager) );
                    manager.getWindow().changePanel( manager.peekStack() );
                    manager.getWindow().createWindow();
        }   }   }; 

        /**************************************** 
                   [ Shovel Button ] 
        ****************************************/
        ActionListener dig = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {

                // - Create temporary variables
                FarmTile selTile = farm.getTile( selCol,selRow );
                Boolean canFarmerAfford = farmer.getCoins() >= farmer.getFarmerTools().getShovel().getCost();

                if( e.getSource() == tools[6] ) {
                    if( selTile.isCropPlanted() && canFarmerAfford ) {
                        farmer.useShovel(selTile);
                        System.out.printf( "Crop has been removed!" );
                        updateTileIcon( selTile, selCol, selRow );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails();
                    } else if( !selTile.isCropPlanted() ){
                        JOptionPane.showMessageDialog( null, "\t     There is no crop planted", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( !canFarmerAfford ) {
                            JOptionPane.showMessageDialog( null, "\t     You do not have enough coins", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   };

        /**************************************** 
                     [ Pickaxe Button ] 
        ****************************************/
        ActionListener mine = new ActionListener() {
            @Override public void actionPerformed( ActionEvent e ) {
                
                // - Create temporary variables 
                FarmTile selTile = farm.getTile( selCol,selRow );
                Boolean canFarmerAfford = farmer.getCoins() >= farmer.getFarmerTools().getShovel().getCost();

                if( e.getSource() == tools[7] ) {
                    if( selTile.isObstructed() && canFarmerAfford ) {
                        farmer.usePickaxe(selTile);
                        System.out.printf( "Rock has been destroyed!" );
                        updateTileIcon( selTile, selCol, selRow );
                        tileDetails.updateTileDetails(selTile);
                        farmerDetails.updateFarmerDetails();
                    } 
                    else if( !selTile.isObstructed() ) {
                        JOptionPane.showMessageDialog( null, "\t     There is no rock", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }
                    else if( !canFarmerAfford ) {
                            JOptionPane.showMessageDialog( null, "\t     You do not have enough coins", "ERROR", JOptionPane.ERROR_MESSAGE );
        }   }   }   };

        /******[ Tools Button Initialization ]******/
        for( int curTool = 0; curTool < TOOLS; curTool++ ) {
            tools[curTool] = new JButton();
            tools[curTool].setBounds( curTool*100+80, 500, 75, 75 );    
            tools[curTool].setFocusable(false);
            tools[curTool].setFont( new Font("Serif",Font.PLAIN,15) );
            tools[curTool].setBackground(new Color(51,51,51));
            tools[curTool].setIcon( Resources.TOOLS.get(curTool) ); 
            super.add( tools[curTool] );
        }

        tools[0].addActionListener(plow);
        tools[1].addActionListener(plant);
        tools[2].addActionListener(water);
        tools[3].addActionListener(fertilize);
        tools[4].addActionListener(harvest);
        tools[5].addActionListener(crop);
        tools[6].addActionListener(dig);
        tools[7].addActionListener(mine);
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
        }   }   };

        /******[ Utility Button Initialization ]******/
        exitButton.setBounds( 5, 5, 150, 50);
        exitButton.setFocusable(false);
        exitButton.setFont( new Font("Serif",Font.PLAIN,15) );
        exitButton.setText( "Exit to Game Menu" ); 
        exitButton.addActionListener( exitToGameMenu );  
        super.add( exitButton );  
    }
}