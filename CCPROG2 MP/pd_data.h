// Includes all the libraries used and header files
#include "pd_logic.h"

/*********************************************************
 
                Pokedex Data Functions

*********************************************************/

/*
    * Check the number of lines inside a given file
    Precondition: The file must be a .txt file.
    
    @param *fp is a file pointer
    @param fileName[] refers to the name of the file being checked
    @returns the total number of lines inside a .txt file
*/ 
int getLines( FILE *fp, char fileName[] )
{
    int nTotal = 1;
    char ch;

    // Opens the file
    fp = fopen( fileName, "r" );
    ch = getc( fp );

    // Loops until the EOF (end of file) is found           
    while( ch!= EOF ) {
        ch = getc( fp );
        if( ch == '\n' )
            nTotal++;
    } fclose( fp );             
    // Closes the file after the loop ends

    return nTotal+1;
}

/*
    * Check if a file name is valid. It is valid if the
    last four characters are '.txt'.

    @param fileName[] refers to a potential name of a file
    @return 1 if file name is valid,
            0 if file name is invalid
*/
int isFileNameValid( char fileName[] )
{
    int nNameSize = strlen( fileName );
    int nValid = 0;
    
    if( fileName[nNameSize-4] == '.' )
        nValid++;
    if( fileName[nNameSize-3] == 't' )
        nValid++;
    if( fileName[nNameSize-2] == 'x' )
        nValid++;
    if( fileName[nNameSize-1] == 't' )
        nValid++;
    
    if( nValid == 4 )
        return 1;
    else 
        return 0;
}

/*
    * Imports Pokedex entry data from an external .txt file
    Precondition: The data inside must strictly follow the format provided

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
*/ 
void importData( entry Pokedex[ENTRIES], int pdSize )
{
    // Initialize structure and variables;
    entry importData[ENTRIES];                  // Temporary entry structure
    FILE *fp;                                   // *fp is a file pointer
    char fileName[FILE_SIZE];                   // fileName[] refers to the name of the file being checked
    int nLines;                                 // number of lines inside the file

    // Introduction sequence
    char title[] = "Importing data into the Pokedex";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n\n\n" DFLT );
    Sleep( 1000 );

    // Ask user to enter file name
    printf( "\t\t\t\t\t\t" );
    printf( BWHT "Please enter the name of the .txt file: " );
    scanf( "%s", &fileName );
    printf( "\n" DFLT );

    // Opens file
    fp = fopen( fileName, "r" );

    if( fp == NULL ) {
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "Error! The file could not be opened.\n" );
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "Going back to the manage data menu." );
        printf( "\n" DFLT );
        Sleep( 3000 );
        fclose( fp );
    }
    else {
        printf( "\t\t\t\t\t\t\t" );
        printf( BGRN "%s was successfully opened!", fileName );
        printf( "\n\n" DFLT );
        Sleep( 2000 );

        nLines = getLines( fp, fileName );
        // Loops until all lines are read
        for( int i = 0; i < nLines/4; i++ ) {
            // Stores all the data in a temporary entry structure
            fscanf( fp, "Name: %s\n", importData[i].name );
            fscanf( fp, "Type: %s\n", importData[i].type );
            fscanf( fp, "Description: %[^\n]s\n", importData[i].desc );
            fscanf( fp, "\n" );
        } fclose( fp );

        // Initialize pointer and variables;
        entry *ptr;
        int new_entry = 0;
        char check_choice[5];

        // Displays all entries found in the file one by one
        for( int i = 0; i < nLines/4; i++ ) {
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Name: %s \n", importData[i].name );
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Type: %s \n", importData[i].type );
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Desc: %s \n", importData[i].desc );
            printf( "\n" DFLT );

            // Check if the entry name is valid and unique
            if( isNameValid( importData[i].name) == 0 ) 
                strcpy( check_choice, "No" );
            if( isNameUnique(Pokedex, pdSize+new_entry, importData[i].name) == 0 ) 
                strcpy( check_choice, "No" );
            
            // Asks if the user if they want to add the entry to the Pokedex
            while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                    (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) ) {
                printf( "\t\t\t\t\t\t" );        
                printf( BWHT "Do you want to add the new entry to the Pokedex?\n" );
                char text[] = "[Yes/No] ";
                centerText( text );
                scanf( "%s", &check_choice );
            } 
                
            printf( "\n" ); 
            // If user types "Yes" or "yes", add the entry to the Pokedex.
            if( (strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) ) {
                ptr = &importData[i]; 
                Pokedex[pdSize+new_entry] = *ptr;
                new_entry++;

                char desc_1[] = "The entry has been added to Pokedex Entry #";
                printf( BGRN );      
                centerText( desc_1 );
                printf( BGRN "%d!\n", pdSize+new_entry );
                printf( "\n\n" DFLT );
            } 
            else if( (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0) ) {
                char desc_2[] = "The entry will not be added to the Pokedex.";
                printf( BBLK );      
                centerText( desc_2 );
                printf( "\n\n" DFLT );
            }
            strcpy( check_choice, "" );
            Sleep(1000);
        }
    }
    fclose( fp );
}

/*
    * Exports all data inside the Pokedex to an external .txt file

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
    @param *fp is a file pointer
    @param fileName[] refers to the name of the file being checked
*/ 
void exportData( entry Pokedex[ENTRIES], int pdSize )
{
    // Initialize structure and variables;
    entry importData[ENTRIES];                  // Temporary entry structure
    FILE *fp;                                   // *fp is a file pointer
    char fileName[FILE_SIZE];                   // fileName[] refers to the name of the file being checked
    int nLines;                                 // number of lines inside the file
    int check_file;                             // variable to check if the file name is valid

    // Introduction sequence
    char title[] = "Exporting data from the Pokedex";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n\n\n" DFLT );
    Sleep( 1000 );

    // Ask user to enter file name
    printf( "\t\t\t\t\t\t" );
    printf( BWHT "Please enter the name of the .txt file: " );
    scanf( "%s", &fileName );
    printf( "\n" DFLT );

    // Check if the file name is valid
    check_file = isNameValid( fileName );
    if( check_file == 0 ) {
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "Error! The file could not be opened.\n" );
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "Going back to the manage data menu." );
        printf( "\n" DFLT );
        Sleep( 3000 );
    }
    else {
        // Open file
        fp = fopen( fileName, "w" );
        // The output will be valid because it comes from the Pokedex
        for( int i = 0; i < pdSize; i++ ) {
            fprintf( fp, "Name: %s\n", Pokedex[i].name );
            fprintf( fp, "Type: %s\n", Pokedex[i].type );
            fprintf( fp, "Description: %s\n", Pokedex[i].desc );
            fprintf( fp, "\n" );
        } 

        char desc[] = "The data was succesfully exported.";
        printf( "\n" BGRN );
        centerText( desc );
        printf( "\n\n" DFLT );
    }

    fclose( fp );
}
