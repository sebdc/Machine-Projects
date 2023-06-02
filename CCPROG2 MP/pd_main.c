// Includes all the libraries used and header files
#include "pd_data.h"

/*********************************************************
 
                       Menu Function

*********************************************************/

/*
    * Go to the main menu and prompts the user what feature of
    the Pokedex to run.

    @param delay refers to the duration of the animation
    @returns the option selected[]
*/
int goToMenu( int delay ) 
{
    int pdMenu; 
    // Loop until there's a valid input
    do {
        printMenu( delay );
        
        scanf( "%d", &pdMenu );
        if( pdMenu < 0 || pdMenu > 5 ) 
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 4." DFLT );
        
        Sleep( 500 );
    } while( !(pdMenu >= 0 && pdMenu <= 5) );
    return pdMenu;
}

/*
    * Access the "Display Pokedex" category

    @param delay refers to the duration of the animation
    @returns the option selected in the category
*/
int goToDisplayPokedex( int delay )
{
    int pdState;
    // Loop until there's valid input
    do {
        printDisplayEntry( delay );
        scanf( "%d", &pdState );   

        if( pdState < 0 || pdState > 3 )
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 3." DFLT );   
                      
        Sleep( 500 );
    } while( !(pdState >= 0 && pdState <= 3) );
    return pdState;
}

/*
    * Access the "Manage Pokedex" category

    @param delay refers to the duration of the animation
    @returns the option selected in the category
*/
int goToManageEntry( int delay )
{
    int pdState;
    // Loop until there's valid input
    do {
        printManageEntry( delay );
        scanf( "%d", &pdState );

        if( pdState < 0 || pdState > 3 )
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 3." DFLT );   
        
        Sleep( 500 );
    } while( !(pdState >= 0 && pdState <= 3) );
    return pdState;
}

/*
    * Access the "Display Research" category

    @param delay refers to the duration of the animation
    @returns the option selected in the category
*/
int goToDisplayResearch( int delay )
{
    int pdState;
    // Loop until there's valid input
    do {
        printDisplayResearch( delay );
        scanf( "%d", &pdState );

        if( pdState < 0 || pdState > 3 )
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 3." DFLT );   
        
        Sleep( 500 );
    } while( !(pdState >= 0 && pdState <= 3) );
    return pdState;
}

/*
    * Access the "Manage Research" category

    @param delay refers to the duration of the animation
    @returns the option selected in the category
*/
int goToManageResearch( int delay )
{
        int pdState;
    // Loop until there's valid input
    do {
        printManageResearch( delay );
        scanf( "%d", &pdState );

        if( pdState < 0 || pdState > 3 )
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 3." DFLT );   
        
        Sleep( 500 );
    } while( !(pdState >= 0 && pdState <= 3) );
    return pdState;
}

/*
    * Access the "Manage Data" category

    @param delay refers to the duration of the animation
    @returns the option selected in the category
*/
int goToManageData( int delay )
{
    int pdState;
    // Loop until there's valid input
    do {
        printManageData( delay );
        scanf( "%d", &pdState );

        if( pdState < 0 || pdState > 2 )
            printf( BRED "\t\t\t\t\t\t\tThe input should be between 0 and 3." DFLT );   
        
        Sleep( 500 );
    } while( !(pdState >= 0 && pdState <= 2) );
    return pdState;
}

/*********************************************************
 
                       Main Function

*********************************************************/

int main()
{
    /*******************************
       Initialization of Variables 
    *******************************/
    research ResearchTask[TASK] = { "", "", 0 };            // research structure array with empty variables 
    entry Pokedex[ENTRIES] = { "", "", "", {"", "", 0} };   // entry structure array with empty variables
    int pdSize = 0;                                         // number of valid entries in Pokedex[]
    int rtSize = 0;                                         // number of research tasks in Pokedex[]
    int pdMenu;                                             // state of the Pokedex menu
    int pdState;                                            // state of the Pokedex program
    int delay = 0;                                          // refers to the duration of animation

    /*******************************
          Introduction Sequence 
    *******************************/
    printCertify();
    enterToContinue();

    // Allow the user to navigate the menu
    pdMenu = goToMenu( 10 );

    // Loop until the user chooses to kill the program
    do {
    // Menu options
    switch( pdMenu ) 
    {
        /*******************************
                 Display Pokedex    
        *******************************/        
        case 1: 
            // Loop until the user exits to the menu
            do {
                pdState = goToDisplayPokedex( 10 );

                // Display all pokedex entries
                if( pdState == 1 ) {
                    system( "cls" );
                    displayPokedex( Pokedex, pdSize );
                    enterToContinue();
                }
                // Display pokedex entries by keyword
                else if( pdState == 2 ) {
                    system( "cls" );
                    displayByName( Pokedex, pdSize );                
                    enterToContinue();  
                }
                // Display pokedex entries by type
                else if( pdState == 3 ) {
                    system( "cls" );
                    displayByType( Pokedex, pdSize );
                    enterToContinue();
                }
            } while( pdState != 0 ); 
            
            if( pdState == 0 )
                pdMenu = goToMenu( delay );
            break;
            
        /*******************************
              Manage Pokedex Entries                  
        *******************************/               
        case 2: 
            // Loop until the user exits to the menu
            do {
                pdState = goToManageEntry( 10 ); 

                // Add a new pokedex entry
                if( pdState == 1 ) {
                    system( "cls" );
                    pdSize = addEntry( Pokedex, pdSize );
                    enterToContinue();
                }
                // Modify an existing entry
                else if( pdState == 2 ) {
                    system( "cls" );
                    modifyEntry( Pokedex, pdSize );
                    enterToContinue();
                }
                // Delete an existing entry
                else if( pdState == 3 ) {
                    system( "cls" );
                    pdSize = deleteEntry( Pokedex, pdSize );
                    enterToContinue();
                } 
            } while( pdState != 0 );

            // Update pdSize
            pdSize = countEntries( Pokedex );

            if( pdState == 0 )
                pdMenu = goToMenu( delay );
            break;

        /*******************************
              Display Research Tasks
        *******************************/                
        case 3: 
            // Loop until user exits to menu
            do {
                pdState = goToDisplayResearch( 10 );

                // Display all research tasks available
                if( pdState == 1 ) {
                    system( "cls" );
                    reviewResearchTask( ResearchTask, rtSize );
                    enterToContinue();
                }
                // Display all research tasks of a Pokemon
                else if( pdState == 2 ) {
                    system( "cls" );    
                    reviewTaskPerPokemon( Pokedex, pdSize, ResearchTask, rtSize );
                    enterToContinue();
                }
                // Display all Pokemon with a specific research
                else if( pdState == 3 ) {
                    system( "cls" );
                    reviewTaskPerType( Pokedex, pdSize, ResearchTask, rtSize );
                    enterToContinue();
                }

            } while( pdState != 0 );
    
            if( pdState == 0 )
                pdMenu = goToMenu( delay );
            break;

        /*******************************
              Manage Research Tasks
        *******************************/
        case 4:   
            // Loop until user exits to menu
            do {
                pdState = goToManageResearch( 10 );

                // Create a new research task
                if( pdState == 1 ) {
                    system( "cls" );
                    rtSize = addResearch( Pokedex, pdSize, ResearchTask, rtSize );
                    enterToContinue();
                }
                // Update progress on a research task
                else if( pdState == 2 ) {
                    system( "cls" );
                    updateResearch( Pokedex, pdSize, ResearchTask, rtSize );
                    enterToContinue();
                }
                // Delete an existing research task
                else if( pdState == 3 ) {
                    system( "cls" );
                    rtSize = deleteResearch( Pokedex, pdSize, ResearchTask, rtSize );
                    enterToContinue();
                }
            } while( pdState != 0 );
    
            if( pdState == 0 )
                pdMenu = goToMenu( delay );
            break;

        /*******************************
          Pokedex Data (Import/Export)
        *******************************/    
        case 5: 
            // Loop until user exits to menu
            do {
                pdState = goToManageData( 10 );

                // Import data from a .txt file
                if( pdState == 1 ) {
                    system( "cls" );
                    importData( Pokedex, pdSize );
                    enterToContinue();
                }
                // Export data to a .txt file
                else if( pdState == 2 ) {
                    system( "cls" );
                    exportData( Pokedex, pdSize );
                    enterToContinue();
                }

                pdSize = countEntries( Pokedex );
            } while( pdState != 0 );
    
            if( pdState == 0 )
                pdMenu = goToMenu( delay );
            break;
            
        /*******************************
                  Exit Program    
        *******************************/    
        case 0: 
            break;
        }
    } while( pdMenu != 0 );

    printf( BWHT "\n\n" );
    centerText( "Thanks for using the Pokedex!" );
    printf( "\n\n" DFLT );
    return 0;
}