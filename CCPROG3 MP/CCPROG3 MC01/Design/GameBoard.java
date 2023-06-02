package Design;
import Farm.*;
import Farmer.*;
import java.util.ArrayList;
import java.lang.String;

/*******************************************************************
 *     
 *  The GameBoard class is used to display the game board found 
 *  in the "Tile Navigation" portion of the program. All methods 
 *  related to "Tile Navigation" is found in this class.
 * 
*******************************************************************/

public class GameBoard {
    
    /// ANSII Escape Color Codes
    private String BLACK = "\u001b[30m";
    private String RED = "\u001b[31m";
    private String GREEN = "\u001b[32m";
    private String YELLOW = "\u001b[33m";
    private String MAGENTA = "\u001b[35m";
    private String CYAN = "\u001b[36m";
    private String WHITE = "\u001b[37m";
    private String DFLT = "\u001b[0m";
    private String BOLD = "\u001B[1m";
    private String BGCYN = "\u001b[46m";
    private String BGCLR = "\u001b[49m";
    private String BORDER_COLOR = BOLD + WHITE;

    /// Terminal Dimensions
    private int border_length = 6;
    private int tile_length = 8;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public GameBoard() {}

    /*|*******************************************************
     
                          Display Methods

    *********************************************************/ 
    /**
     *   ` Prints the game board for the "Tile Navigation" portion of the program. 
     * 
     *    1. The tile's design varies depending on its condition:
     *      - The selected tile (determined using selRow and selCol) is highlighted
     *      through bolded and colored text
     *      - Watered tiles are colored blue
     * 
     *    2. Appends the farmer's details on the top-right side of the board
     * 
     *    3. Appends the selected tile's details on the middle-right side of the board
     * 
     *    4. Appends the recommended tool's details on the lower-right side of the board
     * 
     *   @param farm                used to retrieve the relevant FarmTile details
     *   @param farmer              used to retrieve the farmer details
     *   @param selRow              refers to the selected row of a FarmTile[col][row] in farm
     *   @param selCol              refers to the selected column of a FarmTile[col][row] in farm
     *   @param addDetails          if 'true', then print details ; if 'false' then don't print details
    */
    public void printGameBoard( FarmLot farm, Farmer farmer, int selRow, int selCol, Boolean addDetails ) {

        // - Loop counters
        int curRow;     
        int maxRow = farm.getWidth();
        int maxCol = farm.getLength();

        // - Parts of the tile
        String tileTop = " ______ ";
        String tileSid = "|      |";
        String tileMid = "| %s%s |";
        String tileBot = "|______|";
        
        // - Tile details
        FarmTile selectedTile = farm.getTile(selCol, selRow);
        ArrayList <String> details = new ArrayList<String>();
        getTileDetails( selectedTile, details );

        // - Print the upper horizontal border
        printHorizontalBorder( farm );

        // - Prints the entire gameboard with details
        if( addDetails == true ) { 
            // - Print a complete row of tiles with details
            for( curRow = 0; curRow < maxRow; curRow++ ) {
                printTileRow( tileTop, curRow, maxCol, selRow, selCol, farm, farmer, details, 0 );
                printTileRow( tileSid, curRow, maxCol, selRow, selCol, farm, farmer, details, 1 );
                printTileRow( tileMid, curRow, maxCol, selRow, selCol, farm, farmer, details, 2 );
                printTileRow( tileBot, curRow, maxCol, selRow, selCol, farm, farmer, details, 3 );
            } 
        }
        // - Prints the entire gameboard without details
        else if( addDetails == false ) {
            // - Print a complete row of tiles without the details
            for( curRow = 0; curRow < maxRow; curRow++ ) {
                printTileNoDetails( tileTop, curRow, maxCol, selRow, selCol, farm );
                printTileNoDetails( tileSid, curRow, maxCol, selRow, selCol, farm );
                printTileNoDetails( tileMid, curRow, maxCol, selRow, selCol, farm );
                printTileNoDetails( tileBot, curRow, maxCol, selRow, selCol, farm );
            } 
        }
        // - Print the last vertical border
        printLastVertBorder( farm );

        // - Print the lower horizontal border
        printHorizontalBorder( farm );
    }



    /**
     *   ` Prints a part of the row of tiles that composes the game board. 
     *   It appends different details about the Farmer, the FarmTile and 
     *   the tool recommendations on the right side of the board. 
     *    1. Intended to be used inside the class method printGameBoard()
     * 
     *   @param tilePart        the tile part to be printed (top/side/middle/bottom)
     *   @param curRow          the nth row of the farm being printed
     *   @param maxCol          the width of the farm 
     *   @param selRow          refers to the selected row of the FarmTile[col][row] in farm
     *   @param selCol          refers to the selected column of the FarmTile[col][row] in farm
     *   @param farm            added as a parameter to retrieve the current tile's details
     *   @param farmer          added as a paremeter retrieve farmer details 
     *   @param details         contains all the selected tile's details and tool recommendations
     *   @param index           refers to what index of param 'details' should be printed (0/1/2/3)
    */
    public void printTileRow( String tilePart, int curRow, int maxCol, int selRow, int selCol, 
                            FarmLot farm, Farmer farmer, ArrayList<String> details, int index ) {

        // - Print the left vertical border
        System.out.printf( "\t" + BORDER_COLOR + " | " + DFLT + BLACK );

        // - Print the row of tiles
        for( int curCol = 0; curCol < maxCol; curCol++ ) {
            // - Highlight the selected tile (uses BOLD + GREEN)
            if( curRow == selRow && curCol == selCol ) {
                System.out.print( BOLD + GREEN );
                System.out.printf( tilePart, getTileObject(farm.getTile(curCol,curRow)), GREEN );
                System.out.print( DFLT + BLACK );
            }
            // - Highlight the watered tiles (uses CYAN)
            else if( farm.getTile(curCol, curRow).isWatered() ) {
                System.out.print( CYAN );
                System.out.printf( tilePart, getTileObject(farm.getTile(curCol,curRow)), CYAN );
                System.out.print( DFLT + BLACK );
            }
            // - Print the other tiles normally (uses BLACK)
            else System.out.printf( tilePart, getTileObject(farm.getTile(curCol,curRow)), BLACK );
        }

        // - Print the right vertical border
        System.out.printf( BORDER_COLOR + " |  " + DFLT );

        /// If the farm has two or more rows...
        // - Append the farmer details to the right side of first row
        if( farm.getWidth() >= 2 && curRow == 0 ) {
            switch( index ) {
                case 1: System.out.printf( BOLD + "%s %s", farmer.getType().getFarmerType(), farmer.getName() ); 
                        break; 
                case 2: System.out.printf( MAGENTA + "Level %d (%.2f exp)", farmer.getLevel(), farmer.getExp()  ); 
                        break;
                case 3: System.out.printf( YELLOW + "%d coins", farmer.getCoins() );
                        break;
            }
        } 

        /// Regardless of the farm's row size...
        // - Append the tile details to the right side of the middle row
        if( curRow == farm.getWidth()/2 ) {
            System.out.printf( details.get(index) );
        } 

        /// If the farm has three or more rows...
        // - Append tool details below the middle row to the right side of the middle row
        if( farm.getWidth() >= 3 && curRow == (farm.getWidth()/2) + 1 ) {
            System.out.printf( details.get(index+4) );
        } 
        
        System.out.printf( "\n" );
    }

    /**
     *   ` Prints a part of the row of tiles that composes the game board.
     *   Unlike printTileRow(), this method does not append any details on
     *   the right side of the board. 
     *    1. Intended to be used inside the class method printGameBoard()
     * 
     *   @param tilePart        the tile part to be printed (top/side/middle/bottom)
     *   @param curRow          the nth row of the farm being printed
     *   @param maxCol          the width of the farm 
     *   @param selRow          refers to the selected row of the FarmTile[col][row] in farm
     *   @param selCol          refers to the selected column of the FarmTile[col][row] in farm
     *   @param farm            added as a parameter to retrieve the current tile's details
    */
    public void printTileNoDetails( String tilePart, int curRow, int maxCol, 
                                    int selRow, int selCol, FarmLot farm  ) {
        // - Print the left vertical border
        System.out.printf( "\t" + BORDER_COLOR + " | " + DFLT + BLACK );

        // - Print the row of tiles
        for( int curCol = 0; curCol < maxCol; curCol++ ) {
            // - Highlight the watered tiles (uses CYAN)
            if( farm.getTile(curCol, curRow).isWatered() ) {
                System.out.print( CYAN );
                System.out.printf( tilePart, getTileObject(farm.getTile(curCol,curRow)), CYAN );
                System.out.print( DFLT + BLACK );
            }
            // - Print the other tiles normally (uses BLACK)
            else System.out.printf( tilePart, getTileObject(farm.getTile(curCol,curRow)), BLACK );
        }

        // - Print the right vertical border
        System.out.printf( BORDER_COLOR + " |  " + DFLT  + "\n" );
    }

    /**
     *   ` Prints the horizontal border of the gameboard.
     *    a. The method is intended to be used inside the class method printGameBoard()
     * 
     *   @param farm            used to retrieve the farm's length
    */
    public void printHorizontalBorder( FarmLot farm ) {
        System.out.printf( "\n" + BORDER_COLOR + "\t" );
        for( int i = 0; i < border_length + (farm.getLength() * tile_length); i++ ) {
            System.out.printf( "=" );
        } System.out.printf( "\n" + DFLT );
    }

    /**
     *   ` Prints the last vertical border of the gameboard.
     *    a. Intended to be used inside the class method printGameBoard()
     *    b. The rest of the vertical border is printed inside printTileRow()
     * 
     *   @param farm            used to retrieve the farm's length
    */
    public void printLastVertBorder( FarmLot farm ) {
        System.out.printf( "\t" + BORDER_COLOR + " | " + DFLT );
        for( int i = 0; i < (farm.getLength() * tile_length); i++ ) 
            System.out.printf( " " );
        System.out.printf( BORDER_COLOR + " | " + DFLT );
    }

    /**
     *   ` Determines the appropriate tile object to be displayed on the tiles of the
     *   gameboard based on the information retrieved from the tile 
     *    
     *    1. Intended to be used inside the class method printTileRow()
     *
     *   @param farm            used to retrieve the farm's length
    */
    public String getTileObject( FarmTile tile ) {

        // - Initialize tile objects
        String OBJECT = "";
        String EMPTY = "    ";
        String ROCK = BLACK + "ROCK";
        String PLOWED = WHITE + "\u2593\u2593\u2593\u2593";
        String CROP = GREEN + "CROP";
        String VEGTABLE = GREEN + "ROOT";
        String TREE = GREEN + "TREE";
        String FLOWER = GREEN + "FLWR";
        String WITHERED = RED + "DEAD";
        String WATERED = BGCYN;
        String CLEAR = BGCLR;
        
        // - Check the tile's condition and add the appropriate information to OBJECT
        if( tile.isObstructed() ) 
            OBJECT += ROCK;
        else if( tile.isPlowed() && !tile.isCropPlanted() ) {
            OBJECT += PLOWED;
        }
        else if( tile.isCropPlanted() ) {
            if( tile.isWatered() ) 
                OBJECT += WATERED;
            if( tile.getCrop().isWithered() )
                OBJECT += WITHERED;
            else if( tile.getCrop().getType().equalsIgnoreCase("Root Crop") )
                OBJECT += VEGTABLE;
            else if( tile.getCrop().getType().equalsIgnoreCase("Flower") )
                OBJECT += FLOWER;
            else if( tile.getCrop().getType().equalsIgnoreCase("Fruit Tree") )
                OBJECT += TREE;
            else
                OBJECT += CROP;
            if( tile.isWatered() ) 
                OBJECT += CLEAR;
        }
        else
            OBJECT += EMPTY;

        return OBJECT + WHITE;
    }

    /**
     *   ` Determines the appropriate tile details to be displayed on the right side
     *    of the gameboard based on the information retrieved from selectedTile.
     * 
     *    1. Intended to be used inside the class method printGameBoard()
     * 
     *    2. The tile sections are divided every fourth index ([0-3],[4-7],[8-11])
     * 
     *    3. Every section of the tile details comes in a set of 4 
     *      - Index [0-3] : Tile Object Details
     *      - Index [4-7] : Tool Details & Recommendation
     *
     *   @param selectedTile            added as a parameter to retrieve tile details
     *   @param details                 stores the tile details
    */
    public void getTileDetails( FarmTile selectedTile, ArrayList<String> details ) {
        
        // - Intialize temporary crop variable
        Crop crop = selectedTile.getCrop();

        /// Rock
        if( selectedTile.isObstructed() ) {
            // - Tile Object Details
            details.add( "" );
            details.add( BLACK + "Rock" + DFLT  );
            details.add( "Use your pickaxe to destroy it" );
            details.add( "" );
            // - Tool Details & Recommendation
            details.add( "Pickaxe"  );
            details.add( YELLOW + "Costs 50 coins " + DFLT + "and " + MAGENTA + "yields 15.0 exp" + DFLT );
            details.add( "" );
            details.add( "" );
        }
        /// Plowed Tile, No Crop Planted
        else if( selectedTile.isPlowed() && !selectedTile.isCropPlanted() ) {
            // - Tile Object Details
            details.add( "" );
            details.add( "Plowed Tile" );
            details.add( "You can plant seeds on this tile" );
            details.add( "" );
            // - Tool Details & Recommendation
            details.add( "" );
            details.add( "" );
            details.add( "" );
            details.add( "" );
        }
        /// Plowed Tile, Crop is Planted, Not Withered
        else if( selectedTile.isPlowed() && selectedTile.isCropPlanted() && !crop.isWithered() ) {
            // - Tile Object Details
            details.add( "" );
            details.add( GREEN + crop.getName() + DFLT );
            if( crop.getHarvestTime() == 0 && crop.isHarvestable() ) {
                details.add( BOLD + YELLOW + "The " + crop.getName() + "is ready to be harvested!" + DFLT );
            } else if( crop.getHarvestTime() == 0 && !crop.isHarvestable() ) {
                details.add( BLACK + "The crop is starting to wither." + DFLT );
            } else { 
                details.add( "The crop will mature in " + BOLD + selectedTile.getCrop().getHarvestTime() + " days" + DFLT );
            }
            details.add( "" );
            // - Tool Details & Recommendation
            if( !selectedTile.isWatered() ) {
                details.add( "Watering Can"  );
                details.add( MAGENTA + "Yields 0.5 exp" + DFLT );
            } else if( selectedTile.isWatered() ) {
                details.add( "The tile is watered" );
                details.add( "..." );
            } if( !selectedTile.isFertilized() ) {
                details.add( "Fertilizer" );
                details.add( YELLOW + "Costs 10 coins " + DFLT + "and " + MAGENTA + "yields 4.0 exp" + DFLT );
            } else if( selectedTile.isFertilized() ) {
                details.add( "The tile is fertilized" );
                details.add( "..." );
            }
        }
        /// Plowed Tile, Crop is Withered
        else if( selectedTile.isPlowed() && selectedTile.isCropPlanted() && selectedTile.getCrop().isWithered() ) {
            // - Tile Object Details
            details.add( "" );
            details.add( BLACK + selectedTile.getCrop().getName() + DFLT );
            details.add( "The crop is withered" );
            details.add( "" );
            // - Tool Details & Recommendation
            details.add( "Shovel" );
            details.add( YELLOW + "Costs 7 coins " + DFLT + "and " + MAGENTA + "yields 2.0 exp" + DFLT );
            details.add( "" );
            details.add( "" );
        }
        /// Free Tile
        else {
            // - Tile Object Details
            details.add( "" );
            details.add( "Free Tile" + DFLT );
            details.add( "Plow the tile to plant seeds" );
            details.add( "" );
            // - Tool Details & Recommendation
            details.add( "Plow" );
            details.add( MAGENTA + "Yields 0.5 exp" + DFLT );
            details.add( "" );
            details.add( "" );
        }
    }

    /*|*******************************************************
     
                        Navigation Methods

    *********************************************************/ 
    /**
     *   ` Returns a new row position based on the current row position.
     *    1. Intended to be used inside the driver class
     *    2. The new row position is used to determine the selected tile
     *
     *   @param farm            added as a parameter to retrieve farm's width
     *   @param move            user inputs "W/S" to navigate the gameboard vertically
     *   @param row             refers to the current row position
     *   @return row            returns the new row position
    */
    public int navigateTileRow( FarmLot farm, String move, int row ) {
        if( move.equalsIgnoreCase( "W" ) ) {
            row = row-1;
            if( row < 0 ) 
                row = farm.getWidth()-1;
        } 
        else if( move.equalsIgnoreCase( "S" ) ) {
            row = row+1;
            if( row > farm.getWidth()-1 )
                row = 0;
        }
        return row;
    }

    /**
     *   ` Returns a new column position based on the current column position
     *    1. Intended to be used inside the driver class
     *    2. The new column position is used to determine the selected tile
     *
     *   @param farm            added as a parameter to retrieve farm's width
     *   @param move            user inputs "A/D" to navigate the gameboard horizontally
     *   @param col             refers to the current column position
     *   @return col            returns the new column position
    */
    public int navigateTileCol( FarmLot farm, String move, int col ) {
        if( move.equalsIgnoreCase( "A" ) ) {
            col = col-1;
            if( col < 0 ) 
                col = farm.getLength()-1;
        }
        else if( move.equalsIgnoreCase( "D" ) ) {
            col = col+1;
            if( col > farm.getLength()-1 )
                col = 0;
        }
        return col;
    }
}