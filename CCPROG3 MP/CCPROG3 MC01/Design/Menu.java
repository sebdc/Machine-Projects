package Design;
import Farm.*;
import Farmer.*;
import Store.*;
import Time.*;
import java.util.ArrayList;

/*******************************************************************
 *     
 *  The Menu class handles all the interactive menus found in the
 *  MCO1 program prototype. It helps the Game class navigate through
 *  the different game states or features of the terminal game.
 * 
*******************************************************************/

public class Menu {

    /// Class Attributes
    private Design design;
    private Art art;

    /// ANSII Escape Codes
    private String BLACK = "\u001b[30m";
    private String RED = "\u001b[31m";
    private String GREEN = "\u001b[32m";
    private String YELLOW = "\u001b[33m";
    private String BLUE = "\u001b[34m"; 
    private String MAGENTA = "\u001b[35m";
    private String WHITE = "\u001b[37m";
    private String DFLT = "\u001b[0m";
    private String BOLD = "\u001B[1m";

    /// Terminal Dimensions
    private int farm_length;
    private int border_length = 6;
    private int tile_length = 8;

    /// Summary Details
    private int summary_produce = 0;
    private int summary_coins = 0;

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Menu() {
        this.design = new Design();
        this.art = new Art();
    }
    
    /*|*******************************************************
     
                        Behaviour Methods

    *********************************************************/
    /**
     *   ` This method is called for every user input during the game's 
     *   running time. Depending on the input and the current game_state,
     *   the method will return a new game_state to allow the program to
     *   move on to a different menu or part of the game.
     *
     *    @param option             user input
     *    @param game_state         conditional expression in Game's switch statement
     *    @return game_state        move to the same/different part of the game
    */
    public int switchGameState( String option, int game_state ) {
        
        if( game_state == 1 ) {             /// HOME MENU
            if( option.equals("1") )        // [1] Navigate Farm 
                game_state = 2;             //   --> go to Farm Navigation 
            else if( option.equals("2") )   // [2] Visit Store
                game_state = 5;             //   --> go to Store
            else if( option.equals("3") )   // [3] Sleep
                game_state = 8;             //   --> go to Next Day
            else if( option.equals("0"))    // [0] Exit game
                game_state = 0;             //   --> stop the program
        }
        else if( game_state == 2 ) {                    /// FARM NAVIGATION
            if( option.equalsIgnoreCase("F") )          // "F" interact with tile
                game_state = 3;                         //   --> move to Tile Interaction
            else if( option.equalsIgnoreCase("X") )     // "X" to go back home
                game_state = 1;                         //   --> move to Home Menu
        }
        else if( game_state == 3 ) {        /// TILE INTERACTION
            if( option.equals("1") )        // "1" Buy and Plant Seeds
                game_state = 4;             //   --> move to Planting Menu
            else if( option.equals("0") )   // "0" Exit Tile Interaction
                game_state = 2;             //   --> move back to Farm Navigation 
        }
        else if( game_state == 4 ) {        /// PLANTING MENU
            if( option.equals("0") )        // "0" Exit Planting Menu
                game_state = 3;             //  --> move back to Tile Interaction
        }
        else if( game_state == 5 ) {        /// STORE
            if( option.equals("1") )        // "1" Display Crops
                game_state = 6;             //   --> move to Display Crops Menu
            else if (option.equals("2"))    // "2" Upgrade Farmer Type
                game_state = 7;             //   --> move to Upgrade Farmer Menu
            else if( option.equals("0") )   // "0" Exit Store
                game_state = 1;             //   --> move to Home Menu
        }
        else if( game_state == 6 ) {        /// DISPLAY CROPS MENU
            if( option.equals("0") )        // "0" Exit Crops Menu
                game_state = 5;             //   --> move to Store
        }
        else if( game_state == 7 ) {        /// UPGRADE FARMER
            if( option.equals("0") )        // "0" Exit Upgrade Farmer Menu
                game_state = 5;             //   --> move to Store
        }
        else if( game_state == 8 ) {        /// GO TO SLEEP
            game_state = 1;                 // return to Home Menu after nextDay()
        }           
        return game_state;
    }

    /*|*******************************************************
     
                          Title Menu

    *********************************************************/
    /**
     *   ` Displays the MyFarm title logo and the available 
     *   options for the title menu.
    */
    public void displayTitleMenu() {
        String TitleMenu[] = {
            GREEN + "[1] Start Game",
            RED   + "[0] Exit Program",
            BLACK + "[>] " + DFLT
        }; art.printTitleLogo();

        // - Center text
        for( int i = 0; i < TitleMenu.length; i++ ) {
            design.appendSpaces( 65/2 );
            System.out.printf( "\t%s", TitleMenu[i] );
            if( i != TitleMenu.length-1 ) 
                System.out.print( "\n" );
        }
    }

    /*|*******************************************************
     
                      Introduction Sequence

    *********************************************************/
    /**
     *   ` Prints a cab ASCII art and display the menu for the 
     *   character creation portion of the game. The options being 
     *   displayed varies depending on the method parameter 'part'.
     *
     *    @param part       the part of the menu to be displayed
    */
    public void goToFarmerMenu( int part ) {
        String CreateFarmerMenu[][] = {
            // - [0] What is your name?
            {         
                BOLD + "What is your name?" + DFLT,
                BLACK + "Enter Your Name [>] " + DFLT
            },
            // - [1] What is your level?
            {
                BOLD + "What is your level?" + DFLT,
                "[1] Level 0",
                "[2] Level 5",
                "[3] Level 10",
                BLACK + "Enter 1-3 [>] " + DFLT
            },
            // - [2] How much coins do you have?
            {
                BOLD + "How much coins do you have?" + DFLT,
                "[1] 100 coins",
                "[2] 500 coins",
                "[3] 1000 coins",
                BLACK + "Enter 1-3 [>] " + DFLT
            }
        };

        art.printIntroCab();
        for( int i = 0; i < CreateFarmerMenu[part].length; i++ ) {
            System.out.printf( "\n\t\t\t" + "%s", CreateFarmerMenu[part][i] );
        }
    }

    /**
     *   ` Return a value that varies depending on the options the
     *   user selected during a specific part of the character creation. 
     *   The values returned would be use to initialize a farmer object.
     *
     *    @param part       the part of the character creation 
     *    @param option     the input the user selected
    */
    public int selectFarmerAtr( int part, String option ) {
        int value = 0;
        Boolean isLevel = part == 1;
        Boolean isCoins = part == 2;

        if( isLevel ) {
            switch( option ) { 
                case "1": value = 0; break;
                case "2": value = 5; break;
                case "3": value = 10; break;
            }
        }
        else if( isCoins ) {
            switch( option ) { 
                case "1": value = 100; break;
                case "2": value = 500; break;
                case "3": value = 1000; break;
            }
        }
        return value;
    }

    /*|*******************************************************
     
                    game_state "1" : Home Menu

    *********************************************************/
    /**
     *   ` Prints the Home title logo and display the available 
     *   options for the Home menu.
    */
    public void goToHomeMenu() {
        String GameMenu[] = {
            "[1] Navigate Farm",
            "[2] Visit Store",
            "[3] Go to Sleep",
            "[0] Exit Game",
            "[>] " 
        }; art.printHomeLogo();

        for( int i = 0; i < GameMenu.length; i++ ) {
            design.appendSpaces( 65/2 );
            System.out.printf( "\t%s", GameMenu[i] );
            if( i != GameMenu.length-1 ) 
                System.out.print( "\n" );
        }
    }

    /*|*******************************************************
     
                 game_state "2" : Farm Navigation

    *********************************************************/
    /**
     *   ` Displays the instructions for the Farm Navigation
     *   part of the program.
    */
    public void goToFarmNavigation() {
        String FarmNavigation[] = {
            "FARM NAVIGATION",
            "\"WASD\" to move between tiles",
            "\"F\" to interact with selected tile",
            "\"X\" to go back home"
        }; System.out.printf( "\n" );
        
        for( int i = 0; i < FarmNavigation.length; i++ ) {
            System.out.printf( DFLT + "\t" );
            design.appendSpaces( ((border_length + farm_length * tile_length) - FarmNavigation[i].length())/2 ); 
            if( i == 0 ) System.out.print( BOLD );
            System.out.printf( "%s\n", FarmNavigation[i] );
        } 
    }

    /*|*******************************************************
     
                game_state "3" : Tile Interaction

    *********************************************************/
    /**
     *   ` Displays all the appropriate tile interaction options
     *   options for the selected tile. 
     * 
     *    @param tile           the selected tile being checked
    */
    public void goToTileInteraction( FarmTile tile ) {

        // - Check all the available and appropriate options for tile interactions
        ArrayList<String> TileInteraction = new ArrayList<String>();
        createTileOptions( tile, TileInteraction );

        // - Add different amounts of "\n" depending on number of options
        if( TileInteraction.size() == 1 || TileInteraction.size() == 2 ) 
            System.out.printf( "\n\n" );
        else if( TileInteraction.size() == 3 )
            System.out.printf( "\n" );
        
        // - Prints the title for tile interactions
        System.out.printf( "\t" );
        design.appendSpaces( ((border_length + farm_length * tile_length) - "TILE INTERACTIONS OPTIONS".length()) / 2 );
        System.out.printf( BOLD + "TILE INTERACTIONS OPTIONS\n" + DFLT );

        // - Prints all the available options for tile interactions starting from index 1
        for( int i = 1; i < TileInteraction.size(); i++ ) {
            System.out.printf( "\t" );  
            design.appendSpaces( ((border_length + farm_length * tile_length) - TileInteraction.get(i).length()) / 2 );
            System.out.printf( TileInteraction.get(i) + "\n", i ); 
        } 

        // - Prints the last option for tile interactions located at index 0 
        System.out.printf( "\t" );  
        design.appendSpaces( ((border_length + farm_length * tile_length) - TileInteraction.get(0).length()) / 2 );
        System.out.printf( TileInteraction.get(0) + "\n", 0 );
    }

    /**
     *   ` Finds all the appropriate tile interaction options available
     *  for the selected tile and add it to a String ArrayList.
     *   
     *    @param tile                   the selected tile being checked
     *    @param TileInteraction        the String ArrayList
    */
    public void createTileOptions( FarmTile tile, ArrayList<String> TileInteraction ) {
        String[] TileOptions = {
            "[%s] Go back to Farm Navigation",               // 0
            "[%s] Use Pickaxe to destroy the rock",          // 1
            "[%s] Use Plow to plow the tile",                // 2
            "[%s] Buy and plant seeds",                      // 3
            "[%s] Harvest the crop",                         // 4
            "[%s] Use Watering Can to water the crop",       // 5
            "[%s] Use Fertilizer to fertilize the crop",     // 6
            "[R] Use Shovel to remove the crop",            // 7
            "[R] Use Shovel to remove the withered crop"    // 8
        }; TileInteraction.add( TileOptions[0] );
        
        /**************************************** 
            Rock 
            [1] "Use Pickaxe to destroy the rock
        ****************************************/
        if( tile.isObstructed() ) {
            TileInteraction.add( TileOptions[1] );
        }
        /**************************************** 
            Free Tile 
            [1] Use Plow to plow the tile
        ****************************************/
        else if( !tile.isPlowed() ) {
            TileInteraction.add( TileOptions[2] );
        }
        /**************************************** 
            Plowed Tile 
            [1] Buy and plant seeds
        ****************************************/
        else if( tile.isPlowed() && !tile.isCropPlanted() ) {
            TileInteraction.add( TileOptions[3] );
        }
        /**************************************** 
            Harvestable Crop 
            [1] Harvest the crop                        
        ****************************************/
        else if( tile.isCropPlanted() && tile.getCrop().isHarvestable() ) {
            TileInteraction.add( TileOptions[4] );
        }
        /**************************************** 
            Growing Crop                 
            [1] Use Watering Can to water the crop     
            [3] Use Fertilizer to fertilize the crop 
            [3] Use Shovel to remove the crop
        ****************************************/
        else if( tile.isCropPlanted() && !tile.getCrop().isHarvestable() && !tile.getCrop().isWithered() ) {
            // If not watered and if not fertilized
            if( !tile.isWatered() && !tile.isFertilized() ) {
                TileInteraction.add( TileOptions[5] );
                TileInteraction.add( TileOptions[6] );
                TileInteraction.add( TileOptions[7] );
            }
            // If not watered and if fertilized
            else if( !tile.isWatered() && tile.isFertilized() ) {
                TileInteraction.add( TileOptions[5] );
                TileInteraction.add( TileOptions[7] );
            }
            // If watered and if not fertilized
            else if( tile.isWatered() && !tile.isFertilized() ) {
                TileInteraction.add( TileOptions[6] );
                TileInteraction.add( TileOptions[7] );
            }
            // If watered and fertilized
            else if( tile.isWatered() && tile.isFertilized() ) {
                TileInteraction.add( TileOptions[7] );
            }
        }
        /**************************************** 
            Withered Crop                 
            [1] Use Shovel to remove the withered crop
        ****************************************/
        else if( tile.isCropPlanted() && tile.getCrop().isWithered() ) {
            TileInteraction.add( TileOptions[8] );
        }
    }

    /**
     *   ` Make the farmer interact with the tile and perform an action
     *   depending on the user input. The result varies depending on the
     *   game state, the option the user selected, and the selected tile's
     *   status.
     *     1. It prints an error message based on the lack of changes in
     *     farmer coins. If the farmer action method was called but there
     *     were no changes in the coins, then it implies that the farmer
     *     did not have enough coins to use the tool.
     *   
     *    @param tile               the selected tile being interacted with
     *    @param farmer             the farmer interacting with the tile
     *    @param coins              the coins of the farmer before the method was called
     *    @param option             the tile
     *    @param game_state         the current game_state
     *    @return game_state        the new game_state
    */
    public int interactTile( FarmTile tile, Farmer farmer, int coins, String option, int game_state ) {

        /**************************************** 
            Rock 
            [1] "Use Pickaxe to destroy the rock"
        ****************************************/
        if( game_state == 3 && tile.isObstructed() ) {
            if( option.equals("1") ) {
                farmer.usePickaxe(tile);
                farmer.updateLevel();
            }
            // Prints error message
            if( coins == farmer.getCoins() && option.equals("1") ) {
                System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                design.Sleep(1500);
            }
        } 
        /**************************************** 
            Free Tile 
            [1] "Use Plow to plow the tile"
        ****************************************/
        else if( game_state == 3 && !tile.isPlowed() ) {
            if( option.equals("1") ) {
                 farmer.usePlow(tile);
                 farmer.updateLevel();
            }
        }
        /**************************************** 
            Plowed Tile 
            [1] Buy and plant seeds
            -> move to Planting Crops Menu
        ****************************************/
        else if( game_state == 3 && tile.isPlowed() && !tile.isCropPlanted() ) {
            if( option.equals("1") ) 
                game_state = 4;
        }
        /**************************************** 
            Harvestable Crop 
            [1] Harvest the crop                        
        ****************************************/
        else if( game_state == 3 && tile.isCropPlanted() && tile.getCrop().isHarvestable() ) {
            if( option.equals("1") ) {
                Crop crop = tile.getCrop();
                int produce = farmer.harvestCrop(tile);
                int coins_gained = farmer.sellCrop( crop, produce );
                farmer.updateLevel();
                summary_coins = summary_coins + coins_gained;
                summary_produce = summary_produce + produce;

                System.out.printf( "\tFarmer %s harvested x%d %s.\n", farmer.getName(), produce, crop.getName() );
                System.out.printf( "\tFarmer %s gained %.2f exp.\n", farmer.getName(), crop.getExp() );
                System.out.printf( "\tFarmer %s earned %d coins.\n", farmer.getName(), coins_gained );
                design.Sleep( 2000 );
            }
        }       
        /**************************************** 
            Growing Crop                 
            [1] Use Watering Can to water the crop     
            [2] Use Fertilizer to fertilize the crop 
            [R] Use Shovel to remove the crop
        ****************************************/
        else if( game_state == 3 && tile.isCropPlanted() && !tile.getCrop().isWithered() ) {
            // - If not watered and if not fertilized
            if( !tile.isWatered() && !tile.isFertilized() ) {
                if( option.equals("1") ) {
                    farmer.useWateringCan(tile);
                    farmer.updateLevel();
                }   
                else if( option.equals("2") ) {
                    farmer.useFertilizer(tile);
                    farmer.updateLevel();
                }
                else if( option.equalsIgnoreCase("R") ) {
                    farmer.useShovel(tile);
                    farmer.updateLevel();
                }
                // - Prints error message
                if( coins == farmer.getCoins() && (option.equals("2") || option.equalsIgnoreCase("R")) ) {
                    System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                    design.Sleep(1500);
                }
            }
            // - If not watered and if fertilized
            else if( !tile.isWatered() && tile.isFertilized() ) {
                if( option.equals("1") ) {
                    farmer.useWateringCan(tile);
                    farmer.updateLevel();
                }   
                else if( option.equalsIgnoreCase("R") ) {
                    farmer.useShovel(tile);
                    farmer.updateLevel();
                }
                // - Prints error message
                if( coins == farmer.getCoins() && option.equalsIgnoreCase("R") ) {
                    System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                    design.Sleep(1500);
                }
            }
            // - If watered and if not fertilized
            else if( tile.isWatered() && !tile.isFertilized() ) {
                if( option.equals("1") ) {
                    farmer.useFertilizer(tile);
                    farmer.updateLevel();
                }
                else if( option.equalsIgnoreCase("R") ) {
                    farmer.useShovel(tile);
                    farmer.updateLevel();
                }
                // - Prints error message
                if( coins == farmer.getCoins() && (option.equals("1") || option.equalsIgnoreCase("R")) ) {
                    System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                    design.Sleep(1500);
                }
            }    
            // - If watered and fertilized
            else if( tile.isWatered() && tile.isFertilized() ) {
                if( option.equalsIgnoreCase("R") ) {
                    farmer.useShovel(tile);
                    farmer.updateLevel();
                }
                // - Prints error message
                if( coins == farmer.getCoins() && option.equalsIgnoreCase("R") ) {
                    System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                    design.Sleep(1500);
                 }    
            }
        }
        /**************************************** 
            Withered Crop                 
            [R] Use Shovel to remove the withered crop
        ****************************************/
        else if( game_state == 3 && tile.isCropPlanted() && tile.getCrop().isWithered() ) {
            if( option.equalsIgnoreCase("R") ) {
                farmer.useShovel(tile);
                farmer.updateLevel();
            }
            // - Prints error message
            if( coins == farmer.getCoins() && option.equals("R") ) {
                System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins." + DFLT );
                design.Sleep(1500);
            }
        }
        else 
            game_state = 3;
                
        return game_state;
    }

    /*|*******************************************************
     
                game_state "4" : Planting Crops

    *********************************************************/
    /**
     *   ` Displays all the seeds available in the store. It also
     *   shows the crop characteristics for the selected seed/crop.
     * 
     *    @param store          the object containing the crop information
     *    @param cropIndex      the index of the selected crop
    */
    public void goToPlantingMenu( Store store, int cropIndex ) {
        int numCrops = 8;
        Crop seed = store.buyCrop(cropIndex);

        String PlantingCrops[] = {
            "\"WS\" to move up and down",
            "\"1-8\" to choose a crop to plant",
            "\"0\" to go back to farm navigation"
        }; 
        
        String SeedDetails[] = {
            "The " + BOLD + GREEN + seed.getName() + DFLT + " costs " + YELLOW + seed.getSeedCost() + " coins." + DFLT +
            " It is a " + BOLD + GREEN + seed.getType() + DFLT + " that matures in " + BOLD +  seed.getHarvestTime() + " days.\n" + DFLT,
            "To properly grow, it must be at least " + BOLD + BLUE + "watered " + seed.getWaterNeeds() + " times" + DFLT + 
            " and " + BOLD + YELLOW + "fertilized " + seed.getFertilizeNeeds() + " times.\n" + DFLT,
            "Each harvest can " + BOLD + "produce " + seed.getMinProduce() + "-" + seed.getMaxProduce() + DFLT + " crops and can be sold for " 
            + BOLD + YELLOW + seed.getSellPrice() + " coins" + DFLT + " each.\n",
            "Harvesting a " + BOLD + GREEN + seed.getName() + DFLT + " also yields " + BOLD + MAGENTA + seed.getExp() + " experience" + DFLT + ".\n" 
        };
        
        System.out.printf( "\n" );
        art.printSeedLogo();

        // - Print the instructions
        for( int i = 0; i < PlantingCrops.length; i++ ) {
            System.out.printf( DFLT );
            design.appendSpaces( (((border_length + farm_length * tile_length) - PlantingCrops[i].length())/2)-3 ); 
            System.out.printf( "%s\n", PlantingCrops[i] );
        } System.out.printf( "\n" );

        // - Print the crop options
        for( int i = 0; i < numCrops; i++ ) {
            if( i == cropIndex ) 
                System.out.printf( GREEN + "\t\t\t  [%d] %s (costs %d coins)\n" + DFLT, i+1, store.buyCrop(i).getName(), store.buyCrop(i).getSeedCost() );
            else 
                System.out.printf( "\t\t\t  [%d] %s (costs %d coins)\n", i+1, store.buyCrop(i).getName(), store.buyCrop(i).getSeedCost() );
        }

        // - Print the seed details
        System.out.printf( "\n" );
        for( int i = 0; i < SeedDetails.length; i++ ) {
            System.out.printf( DFLT + "\t" );
            System.out.printf( "%s", SeedDetails[i] );
        } 
    }

    /**
     *   ` Allows the user to navigate the Planting Menu by inputing 
     *    "W" for up and "S" for down. The value of the cropIndex  
     *    changes depending on the input.
     * 
     *    @param move           the user input
     *    @param cropIndex      the index of the selected crop
    */
    public int navigatePlantingMenu( String move, int cropIndex ) {
        int numCrops = 8;

        if( move.equalsIgnoreCase( "W" ) ) {
            cropIndex = cropIndex-1;
            if( cropIndex < 0 ) 
                cropIndex = numCrops-1;
        } 
        else if( move.equalsIgnoreCase( "S" ) ) {
            cropIndex = cropIndex+1;
            if( cropIndex > numCrops-1 )
                cropIndex = 0;
        }
        return cropIndex;
    }

    /**
     *   ` Plant a crop on a certain tile on the farm. The crop being planted
     *   depends on the user input during the planting menu.
     * 
     *     1. It prints an error message based on the lack of changes in
     *     farmer coins. If the farmer action method was called but there
     *     were no changes in the coins, then it implies that the farmer
     *     did not have enough coins to use the tool.
     * 
     *   @param farm                the farm where the crop is being planted
     *   @param farmer              the farmer planting the crop
     *   @param coins               the coins of the farmer before the method was called
     *   @param store               used to retrieve the appropriate crop object
     *   @param row                 refers to the selected row of the FarmTile[col][row] in farm
     *   @param col                 refers to the selected column of the FarmTile[col][row] in farm
     *   @param options             the user input
     *   @param game_state          the current game_state  
     *   return game_state          the new game_state
     *
    */
    public int plantCrop( FarmLot farm, Farmer farmer, int coins, Store store, int row, int col, String option, int game_state ) {
        if( option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") ||
            option.equals("5") || option.equals("6") || option.equals("7") || option.equals("8") ) {
            int index = Integer.parseInt( option );
            Crop crop = store.buyCrop( index-1 );
            farmer.plantCrop( farm, crop, row, col );

            if( (option.equals("7") || option.equals("8")) && farm.getTile(col,row).isCropPlanted() == false ) {
                System.out.printf( BOLD + RED + "\n\t" + "You can't plant the fruit tree here." + DFLT );
                design.Sleep(1500);   
                game_state = 3;
            }
            else if( coins == farmer.getCoins() ) {
                System.out.printf( BOLD + RED + "\n\t" + "You don't have enough coins!." + DFLT );
                design.Sleep(1500);
            }
            else 
                game_state = 3;
        } 
        return game_state;
    }

    /*|*******************************************************
     
                    game_state "5" : Store

    *********************************************************/
    /**
     *   ` Prints the store ASCII art and display the available
     *   options for the store menu.
    */
    public void goToStore() {
       String store[] = {
        " ",
        WHITE + "[1] Display Crop Store",
        GREEN + "[2] Upgrade Farmer Type",
        WHITE + "[0] Go back to (Game Menu)",
        BLACK + "[>] " +DFLT
       }; art.printStoreLogo();
       
       for( int i = 0; i < store.length; i++ ) {
        design.appendSpaces( 90/2 );
        System.out.printf( "\t%s", store[i] );
        if( i != store.length-1 ) 
            System.out.print( "\n" );
       }
    }

    /*|*******************************************************
     
                    game_state "6" : Crops Menu

    *********************************************************/
    /**
     *   ` Displays the details for all the available crops in 
     *   the game and all the available options for the crops 
     *   menu.
    */
    public void goToDisplayCrops() {
        String DisplayCrops[] = {
            BOLD + MAGENTA + "Seed Name" + DFLT + "   --"+ BOLD + MAGENTA + "   Crop Type" + DFLT + "   --" + BOLD + MAGENTA + 
            "   HarvestTime" + DFLT + "   --" + BOLD + MAGENTA + "   WaterNeeds(BonusLimit)"+ DFLT + "   --" + BOLD + MAGENTA + 
            "   FertilizerNeeds(BonusLimit)" + DFLT + "   --" + BOLD + MAGENTA + "   ProductsProduced" + DFLT + "   --" + BOLD +
            MAGENTA + "   Seed Cost" + DFLT + "   --" + BOLD + MAGENTA +"   Base Price" + DFLT + "   --"+ BOLD + MAGENTA + "   Exp Yield"+ DFLT,
            "[1] Turnip       Root Crop            2                      1(2)                             0(1)                         1 - 2                  5                6                5",
            "[2] Carrot       Root Crop            3                      1(2)                             0(1)                         1 - 2                  10               9               7.5",
            "[3] Potato       Root Crop            5                      3(4)                             1(2)                         1 - 10                 20               3               12.5",
            "[4] Rose         Flower               1                      1(2)                             0(1)                           1                    5                5               2.5",
            "[5] Tulips       Flower               2                      2(3)                             0(1)                           1                    10               9               5",
            "[6] Sunflower    Flower               3                      2(3)                             1(2)                           1                    20               19              7.5",
            "[7] Mango        Fruit Tree           10                     7(7)                             4(4)                         5 - 15                 100              8               25",
            "[8] Apple        Fruit Tree           10                     7(7)                             5(5)                        10 - 15                 200              5               25",
            " ",
     
            "[0] Go back to Store " 
        }; 

        for( int i = 0; i < DisplayCrops.length; i++ ) 
        System.out.printf( "%s\n", DisplayCrops[i] );
    } 

    /*|*******************************************************
     
            game_state "7" : Upgrade Farmer Type Menu

    *********************************************************/
    /**
     *   ` Displays the details for all the available farmer 
     *   types in the game and all the available options for 
     *   the upgrade farmer type menu.
    */
    public void goToUpgradeFarmerType( Farmer farmer ) {
        String UpgradeFarmerType[] = {
            BOLD + MAGENTA + "Farmer Type" + DFLT + "    --" + BOLD + MAGENTA + "\tLevel Requirement" + DFLT + "  --"+ BOLD + MAGENTA + "  Bonus Earning per Produce"+ 
            DFLT+ "  --" + BOLD + MAGENTA + "  Seed Cost Reduction" + DFLT + "  --" + BOLD + MAGENTA + "  Water Bonus Limit Increase" + DFLT + "  --" + BOLD + MAGENTA +
            "  Fertilizer Bonus Limit Incerase"+ DFLT + "  --" + BOLD + MAGENTA + "  Reg. Fee" + DFLT,
            "Registered Farmer               5                         +1                          -1                         +0\t\t\t\t      +0                      200",
            "Distinguished Farmer            10                        +2                          -2                         +1\t\t\t\t      +0                      300",
            "Legendary Farmer                15                        +4                          -3                         +2\t\t\t\t      +1                      400\n",
            "[1] Upgrade Farmer Type",
            "[0] Go back to Store"
        };    

        if( farmer.getType().getFarmerType().equals("Farmer") ) {
            for( int i = 0; i < UpgradeFarmerType.length; i++ ) {
                System.out.printf( "%s\n", UpgradeFarmerType[i] );
            }
        } else if( farmer.getType().getFarmerType().equals("Registered Farmer") ) {
            for( int i = 0; i < UpgradeFarmerType.length; i++ ) {
                if( i != 1 )
                    System.out.printf( "%s\n", UpgradeFarmerType[i] );
            }
        } else if( farmer.getType().getFarmerType().equals("Distinguished Farmer") ){
            for( int i = 0; i < UpgradeFarmerType.length; i++ ) {
                if( i != 1 && i != 2 ) {
                    System.out.printf( "%s\n", UpgradeFarmerType[i] );
                }
            }
        } else {
            System.out.print( UpgradeFarmerType[3] + "\n" + "You reached the " + BOLD + YELLOW + "HIGHEST" + DFLT + 
            "type of farmer!" + "Congratulations!" + "\n" + "[0] Go Back to Store\n" );
        }

        System.out.print( BOLD + "\n" + "Current Farmer Type: " + YELLOW + farmer.getType().getFarmerType() + DFLT +
                          BOLD + "\n" + "Current Level: " + YELLOW + farmer.getLevel() + DFLT +
                          BOLD + "\n" + "Current Coins: " + YELLOW + farmer.getCoins() + DFLT );
    }
    
    /*|*******************************************************
     
                    game_state "8" : Next Day

    *********************************************************/
    /**
     *   ` Prints the Next Day title logo and a short countdown
     *   animation before displaying a short summary for the day. 
    */
    public void goToNextDay( Time time, FarmLot farm, Farmer farmer ) {
        // - Display logo and countdown animation
        art.printWakingUpLogo();
        design.countDown(5);
        time.nextDay( farm );

        // - Display short Summary
        System.out.print("\n\n");
        design.appendSpaces(70/2);
        design.gradualPrint( BOLD + "Congrats " + farmer.getName() + " on advancing on " + YELLOW + "DAY " + time.getDay() + DFLT + "\n\n", 30, 1000 );
        design.appendSpaces(84/2);
        design.gradualPrint( BOLD + "Crop Harvested : " + GREEN + summary_produce + DFLT + "\n", 10, 1000);
        design.appendSpaces(84/2);
        design.gradualPrint( BOLD + "Coins Gained : " + YELLOW + summary_coins + DFLT, 10, 1000);
        summary_produce = 0;
        summary_coins = 0;
    }

    /*|*******************************************************
     
                   game_state "9" : Game Over

    *********************************************************/
    /**
     *   ` Prints the Game Over logo
    */
    public void goToGameOver() {
        art.printGameOverLogo();
        System.out.println("\n");
    }
    
    /**
     *   ` Displays a few details regarding the lose scenario
    */
    public void displayLoseScenario( Farmer farmer ) {
        System.out.printf( "\tYou lose...\n" );
        System.out.printf( "\t%s %s has %d coins", farmer.getType().getFarmerType(), farmer.getName(), farmer.getCoins() );
    }

    /*|*******************************************************
     
                   game_state "0" : Exit Game

    *********************************************************/
    /**
     *   ` Prints the Thank You logo
    */
    public void goToExitGame() {
        art.printThankYouLogo();
    }
    
    /*|*******************************************************

                        Getters and Setters
                        
    *********************************************************/
    public void setFarmLength( int farm_length ) {
        this.farm_length = farm_length;
    }
}