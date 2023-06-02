/*|******************************************************************************************
    This is to certify that this project is a colleborative work, based on the collective
    efforts in studying and applying the concepts we have learned in CCPROG3. We have 
    constructed the classes and their respective algorithms and corresponding code as a
    pair. The program was designed, run, tested, and debugged as a collective effort. We
    further certify that we have not copied in part or whole or otherwise plagiarized the
    work of other students and/or persons.

    Full Name: Sebastien Michael V. Dela Cruz
    DLSU ID#: 12112658

    Full Name: Izar Andrei C. Castillo  
    DLSU ID#: 12109933
    
    Course: CCPROG3 - S19
    Submission Date: November 7, 2022

********************************************************************************************/
import Farm.*;
import Farmer.*;
import Store.*;
import Time.*;
import java.util.Scanner;
import Design.*;

public class Game {
    
    public static void main( String[] args ) {
        
        /**********************************************
                      Start of Program
        **********************************************/
        // - Initialization of design and scanner objects
        GameBoard screen = new GameBoard();
        Design design = new Design();
        Menu menu = new Menu();
        Scanner scan = new Scanner( System.in );

        // - Prints the declaration of original work
        design.displayCertifyScreen();
        design.printEnterToContinue(50);
        scan.nextLine();
        design.clearConsole(0);

        String program_state;
        // - Display title menu
        do {
            menu.displayTitleMenu();
            program_state = scan.nextLine();
            design.clearConsole(0);
        } while( !program_state.equals("1") && !program_state.equals("0"));

        String farmerName, farmerLevel, farmerCoins;

        /**********************************************
                      Introduction Sequence 
        **********************************************/
        // - Farmer Name
        menu.goToFarmerMenu(0);
        farmerName = scan.nextLine();
        design.clearConsole(0);

        // - Farmer Level
        do {
            menu.goToFarmerMenu(1);
            farmerLevel = scan.nextLine();
            design.clearConsole(0);
        } while( !farmerLevel.equals("1") && !farmerLevel.equals("2") && !farmerLevel.equals("3") );

        // - Farmer Coins
        do {
            menu.goToFarmerMenu(2);
            farmerCoins = scan.nextLine();
            design.clearConsole(0);
        } while( !farmerCoins.equals("1") && !farmerCoins.equals("2") && !farmerCoins.equals("3") );

        // - Initialize Farmer, FarmLot, Store, and Time
        Farmer farmer = new Farmer( farmerName, menu.selectFarmerAtr(1,farmerLevel), menu.selectFarmerAtr(2,farmerCoins) );
        farmer.setFarmerTools( new FarmerTools(farmer) );

        FarmLot farm = new FarmLot( 5, 10 );
        farm.generateRocks(2);
        menu.setFarmLength( farm.getLength() );

        Store store = new Store();
        int day = 1;
        Time time = new Time(day);

        // - Initialize game variables
        int game_state = 1, row = 0, col = 0;
        String option;
        design.clearConsole(0);
        
        /**********************************************
                        Start of Game
        **********************************************/
        while( program_state.equals("1") ) {
            switch( game_state ) 
            {
                /*************************************
                               Home Menu
                        [1] Farm Navigation
                        [2] Visit Store
                        [3] Next Day
                        [0] Go to Sleep
                *************************************/ 
                case 1: {
                    do {
                        design.clearConsole(0);
                        menu.goToHomeMenu();
                        option = scan.nextLine();
                    } while( !option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("0"));
            
                    game_state = menu.switchGameState( option, game_state );
                    break;
                }

                /*************************************
                            Farm Navigation  
                    [WASD] Navigate the Farm
                    [F] Interact with a Tile
                    [X] Go Back Home 
                *************************************/ 
                case 2: {
                    do {
                        design.clearConsole(0);
                        menu.goToFarmNavigation();
                        screen.printGameBoard( farm, farmer, row, col, true );

                        System.out.printf( "\n\tEnter Option : " );
                        option = scan.nextLine();
                        row = screen.navigateTileRow( farm, option, row );
                        col = screen.navigateTileCol( farm, option, col );

                    } while( !option.equalsIgnoreCase("F") && !option.equalsIgnoreCase("X") );
             
                    game_state = menu.switchGameState( option, game_state ); 
                    break;
                }

                /*************************************
                            Tile Interaction 
                    [1-2] Farmer Actions (dynamic options)
                    [R] Use Shovel (dynamic option)
                    [0] Go Back to Farm Navigation
                *************************************/ 
                case 3: {
                    do {
                        design.clearConsole(0);
                        menu.goToTileInteraction( farm.getTile(col,row) );
                        screen.printGameBoard( farm, farmer, row, col, true );

                        System.out.printf( "\n\tEnter Option [>] " );
                        option = scan.nextLine();
                        game_state = menu.interactTile( farm.getTile(col,row), farmer, farmer.getCoins(), option, game_state );

                        if( game_state == 4 ) 
                            option = "0";

                        design.Sleep(200);
                    } while( !option.equals("0") );

                    if( game_state != 4 )
                        game_state = menu.switchGameState( option, game_state );

                    break;
                }

                /*************************************
                             Planting Menu
                    [1-8] Plant a Crop
                    [0] Go Back to Tile Interaction 
                *************************************/
                case 4: {
                    int cropIndex = 0;
                    do {
                        design.clearConsole(0);
                        menu.goToPlantingMenu( store, cropIndex );

                        System.out.printf( "\n\tEnter Option [>] " );
                        option = scan.nextLine();
                        cropIndex = menu.navigatePlantingMenu( option, cropIndex );
                        game_state = menu.plantCrop( farm, farmer, farmer.getCoins(), store, row, col, option, game_state );

                        if( game_state == 3 ) 
                            option = "0";
                    } while( !option.equals("0") );

                    if( game_state != 3 )
                        game_state = menu.switchGameState( option, game_state ); 
                    break;
                }

                /*************************************
                                Store
                        [1] Display Crop Store
                        [2] Upgrade Farmer Type
                        [0] Go Back Home
                *************************************/
                case 5: {
                    do {
                        design.clearConsole(0);
                        menu.goToStore();

                        option = scan.nextLine();
                        game_state = menu.switchGameState(option, game_state);
                        
                        if( game_state == 6 || game_state == 7 ) 
                            option = "0";
                    } while( !option.equals("0") );
                    break;
                }

                /*************************************
                             Display Crops
                     [0] Go Back to Store Menu
                *************************************/
                case 6: {
                    do {
                        design.clearConsole(0);
                        menu.goToDisplayCrops();

                        System.out.printf( "\n\tEnter Option [>] " );
                        option = scan.nextLine();
                        game_state = menu.switchGameState( option, game_state );

                        if( game_state == 5 )
                            option = "0";
                    } while( !option.equals("0") );
                    break; 
            }

                /*************************************
                           Display Farmer Type
                     [1] Upgrade Farmer Type
                     [0] Go Back to Store Menu
                *************************************/
                case 7: {
                    do {
                        design.clearConsole(0);
                        menu.goToUpgradeFarmerType( farmer );

                        System.out.printf( "\n\nEnter Option [>] " );
                        option = scan.nextLine();
                        game_state = menu.switchGameState( option, game_state );

                        if( option.equals("1") )
                            store.upgradeFarmerType( farmer );
                            
                    } while( !option.equals("0") );
                    break;
                }

                /*************************************
                              Next Day
                *************************************/
                case 8: {
                    design.clearConsole( 0 );
                    menu.goToNextDay(time, farm, farmer);
                    System.out.println();
                    design.printEnterToContinue(37);

                    option = scan.nextLine();
                    game_state = menu.switchGameState(option, game_state);

                    /// - Lose Scenario
                    if( farmer.getCoins() < 5 && !farm.isThereAGrowingCrop() )  
                        game_state = 9; 
                    break;
                }

                /*************************************
                            Lose Scenario    
                *************************************/
                case 9: {
                    design.clearConsole(0);
                    menu.displayLoseScenario(farmer);
                    screen.printGameBoard( farm, farmer, row, col, false );

                    design.printEnterToContinue(36);
                    option = scan.nextLine();
                    design.clearConsole(0);

                    menu.goToGameOver();
                    design.printEnterToContinue(40);
                    option = scan.nextLine();
                    design.clearConsole(0);
                }

                /*************************************
                               Exit Game    
                *************************************/
                case 0: {
                    design.clearConsole(0);
                    menu.goToExitGame();
                    program_state = "0";
                    break;
                }
            }
        } 

        // Prevents resource leaks    
        scan.close();
    }
}