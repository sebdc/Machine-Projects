/********************************************************************************************
    This is to certify that this project is my own work, based on my personal efforts in
    studying and applying the concepts I have learned. I have constructed the functions
    and their respective algorithms and corresponding code by myself. The program was run,
    tested, and debugged by my own efforts. I further certify that I have not copied in
    part or whole or otherwise plagiarized the work of other students and/or persons.

    Full Name: Sebastien Michael V. Dela Cruz
    DLSU ID#: 12112658
    
    Course: CCPROG2 - S11B
    Submission Date: June 22, 2022

********************************************************************************************/

// Libraries used
#include <stdio.h>
#include <string.h>
#include <Windows.h>

// Terminal dimensions
#define TERM_WIDTH 150

// ANSI Escape Codes
#define DFLT    "\033[0m"       // Default 
#define BBLK    "\033[30;1m"    // Bold, Black
#define BGRN    "\033[32;1m"    // Bold, Green
#define BRED    "\033[31;1m"    // Bold, Red
#define BWHT    "\033[37;1m"    // Bold, White
#define BYEL    "\033[33;1m"    // Bold, Yellow
#define BBLU    "\033[34;1m"    // Bold, Blue

#define NBLK    "\033[30;22m"   // Normal, Black
#define NGRN    "\033[32;22m"   // Normal, Green
#define NRED    "\033[31;22m"   // Normal, Red
#define NWHT    "\033[37;22m"   // Normal, White
#define NYEL    "\033[33;1m"    // Normal, Yellow
#define NBLU    "\033[34;1m"    // Normal, Blue

// Pokedex variables sizes
#define NAME 21                 // maximum of 20 letters for a name
#define TYPE 11                 // maximum of 10 letters for a type
#define DESC 51                 // maximum of 50 letters for a description
#define ENTRIES 150             // maximum of 150 Pokedex entries
#define TASK 20                 // maximum of 20 research tasks

// Size for file names
#define FILE_SIZE 31            // maximum of 30 letters for a file name

// Structure for research tasks
typedef struct
{
    char name[DESC];
    char desc[DESC];
    int progress;            
} research;

// Structure for a single pokedex entry
typedef struct
{
    char name[NAME];
    char type[TYPE];
    char desc[DESC];
    research task[TASK];
} entry;    


/*********************************************************
 
                Pokedex Design Functions
                
*********************************************************/

/*
    * Capitalize the first letter of a string.

    @param text[] is a string.
*/
void toUpper( char text[] ) 
{
    // Check if name is lower cased
    if( (int)text[0] >= 97 && (int)text[0] <= 122 )
        text[0] = (int)text[0] - 32;
}

/*
    * Gradually print a string by adding a time delay
    after printing each character.


    @param text[] is a string.
    @param time refers to the duration of the delay 
*/
void gradualPrint( char text[], int time ) 
{
    for( int i = 0; i < strlen(text); i++ ) {
        printf( "%c", text[i] );
        Sleep( time );
    }
}

/*
    * Prints a text in the center of the terminal
    Precondition: TERM_WIDTH is defined

    @param text[] is a string
*/
void centerText( char text[] )
{
    int length = strlen( text );
    for( int i = 0; i < (TERM_WIDTH-length)/2; i++ )
        printf( " " );
    printf( "%s", text );
}

/*
    * Centers a text and gradually print a string
    Precondition: TERM_WIDTH is defined

    @param time refers to the duration of the delay 
    @param text[] is a string
*/
void gradualCenterText( char text[], int time )
{
    int length = strlen( text );
    for( int i = 0; i < (TERM_WIDTH-length)/2; i++ )
        printf( " " );
    gradualPrint( text, time );
}

/*
    * Prints an ellipsis (...)

    @param time refers to the duration of the delay
*/
void printEllipsis( int time )
{
    for( int i = 0; i < 3; i++ ) {
        printf( "." );
        Sleep( time );
    }
}

/*
    * Prints the certify screen
*/
void printCertify()
{
    system( "cls" );
    printf( "\n\n\n" BWHT );

    centerText( (char*)"This is to certify that this project is my own work, based on my personal efforts in\n" );
    Sleep(500);
    centerText( (char*)"studying and applying the concepts I have learned. I have constructed the functions\n" );
    Sleep(500);
    centerText( (char*)"and their respective algorithms and corresponding code by myself. The program was run\n" );
    Sleep(500);
    centerText( (char*)"tested, and debugged by my own efforts. I further certify that I have not copied in\n" );
    Sleep(500);
    centerText( (char*)"part or whole or otherwise plagiarized the work of other students and/or persons.\n" );
    Sleep(500);
    printf( "\n\n\t\t\t\t\t" );
    gradualPrint( (char*)"Full Name: Sebastien Michael V. Dela Cruz", 20 );
    printf( "\n\t\t\t\t\t" );
    gradualPrint( (char*)"DLSU ID#: 12112658", 20 );
    printf( "\n\n\t\t\t\t\t" );
    gradualPrint( (char*)"Course: CCPROG2 - S11B", 20 );
    printf( "\n\t\t\t\t\t" );
    gradualPrint( (char*)"Submission Date: June 22, 2022", 20 );
    printf( "\n\n\t\t\t\t\t" );
}

/*
    * Prints the logo with gradual animation

    @param time refers to the duration of the delay
*/
void printLogo( int time )
{
    printf( BRED );
    char ASCII_1[] = " ____    ___   _  __ _____  ____   _____ __  __\n";
    char ASCII_2[] = "|  _ \\  / _ \\ | |/ /| ____||  _ \\ | ____|\\ \\/ /\n";
    char ASCII_3[] = "| |_) || | | || ' / |  _|  | | | ||  _|   \\  / \n";
    char ASCII_4[] = "|  __/ | |_| || . \\ | |___ | |_| || |___  /  \\ \n";
    char ASCII_5[] = "|_|     \\___/ |_|\\_\\|_____||____/ |_____|/_/\\_\\ ";

    gradualCenterText( ASCII_1, time );
    gradualCenterText( ASCII_2, time );
    gradualCenterText( ASCII_3, time );
    gradualCenterText( ASCII_4, time );
    gradualCenterText( ASCII_5, time );

    printf( DFLT "\n" );
}

/*
    * Prints the menu with animation

    @param time refers to the duration of the delay
*/
void printMenu( int time )
{
    // '\t' = 7
    char TITLE[] = "POKEDEX MENU: \n";
    char TEXT_1[] = "\t\t\t\t\t\t\t  [1] Display Pokedex Entries \n";
    char TEXT_2[] = "\t\t\t\t\t\t\t  [2] Manage Pokedex Entries \n";
    char TEXT_3[] = "\t\t\t\t\t\t\t  [3] Display Research Tasks \n";
    char TEXT_4[] = "\t\t\t\t\t\t\t  [4] Manage Research Tasks \n";
    char TEXT_5[] = "\t\t\t\t\t\t\t  [5] Manage Pokedex Data \n";
    char TEXT_6[] = "\t\t\t\t\t\t\t  [0] Exit \n\n";
    char TEXT_7[] = "\t\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    gradualPrint( TEXT_5, time );
    gradualPrint( TEXT_6, time );
    gradualPrint( TEXT_7, time );
    printf( DFLT );
}

/*
    * Prints the first option of the menu
     [1] Display Entries

    @param time refers to the duration of the delay
*/
void printDisplayEntry( int time )
{
    // '\t' = 7
    char TITLE[] = "DISPLAY POKEDEX ENTRIES: \n";
    char TEXT_1[] = "\t\t\t\t\t\t\t  [1] Display all entries \n";
    char TEXT_2[] = "\t\t\t\t\t\t\t  [2] Display entries by keyword \n";
    char TEXT_3[] = "\t\t\t\t\t\t\t  [3] Display entries by type \n";
    char TEXT_4[] = "\t\t\t\t\t\t\t  [0] Back to menu \n\n";
    char TEXT_5[] = "\t\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    gradualPrint( TEXT_5, time );
    printf( DFLT );
}

/*
    * Prints the second option of the menu
     [2] Manage Entry

    @param time refers to the duration of the delay
*/
void printManageEntry( int time )
{
    // '\t' = 7
    char TITLE[] = "MANAGE POKEDEX ENTRIES: \n";
    char TEXT_1[] = "\t\t\t\t\t\t\t  [1] Add a new entry \n";
    char TEXT_2[] = "\t\t\t\t\t\t\t  [2] Modify an existing entry \n";
    char TEXT_3[] = "\t\t\t\t\t\t\t  [3] Delete an existing entry \n";
    char TEXT_4[] = "\t\t\t\t\t\t\t  [0] Back to menu \n\n";
    char TEXT_5[] = "\t\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    gradualPrint( TEXT_5, time );
    printf( DFLT );
}

/*
    * Prints the second option of the menu
     [3] Display Research

    @param time refers to the duration of the delay
*/
void printDisplayResearch( int time )
{
    char TITLE[] = "DISPLAY RESEARCH TASKS: \n";
    char TEXT_1[] = "\t\t\t\t\t\t  [1] Display all research tasks available\n";
    char TEXT_2[] = "\t\t\t\t\t\t  [2] Display all research task of a Pokemon\n";
    char TEXT_3[] = "\t\t\t\t\t\t  [3] Display all Pokemon with a specific research task\n";
    char TEXT_4[] = "\t\t\t\t\t\t  [0] Back to menu \n\n";
    char TEXT_5[] = "\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    gradualPrint( TEXT_5, time );
    printf( DFLT );
}

/*
    * Prints the third option of the menu
     [4] Manage Research Tasks

    @param time refers to the duration of the delay
*/
void printManageResearch( int time )
{
    char TITLE[] = "MANAGE RESEARCH TASKS: \n";
    char TEXT_1[] = "\t\t\t\t\t\t\t  [1] Create a new research task\n";
    char TEXT_2[] = "\t\t\t\t\t\t\t  [2] Update progress on a research task\n";
    char TEXT_3[] = "\t\t\t\t\t\t\t  [3] Delete an existing research task\n";
    char TEXT_4[] = "\t\t\t\t\t\t\t  [0] Back to menu \n\n";
    char TEXT_5[] = "\t\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    gradualPrint( TEXT_5, time );
    printf( DFLT );
}

/*
    * Prints the third option of the menu
     [5] Manage Pokedex Data

    @param time refers to the duration of the delay
*/
void printManageData( int time ) 
{
    char TITLE[] = "MANAGE POKEDEX DATA: \n";
    char TEXT_1[] = "\t\t\t\t\t\t\t  [1] Import data from a .txt file\n";
    char TEXT_2[] = "\t\t\t\t\t\t\t  [2] Export data to a .txt file\n";
    char TEXT_3[] = "\t\t\t\t\t\t\t  [0] Back to menu \n\n";
    char TEXT_4[] = "\t\t\t\t\t\t\t  [>] ";

    system( "cls" );
    printLogo( 0 );

    printf( BWHT "\n" );
    gradualCenterText( TITLE, time );
    gradualPrint( TEXT_1, time );
    gradualPrint( TEXT_2, time );
    gradualPrint( TEXT_3, time );
    gradualPrint( TEXT_4, time );
    printf( DFLT );
}

/*
    * Prints a buffer screen
*/
void enterToContinue()
{
    printf( "\n\t\t\t\t\t\t\t" );   
    printf( BBLK "[ Enter any key to go back to the menu ]" );
    fflush( stdin );
    getchar();
    fflush( stdin );
    printf( "\n" DFLT );
}

/*********************************************************
  
                Pokedex Logic Functions

*********************************************************/

/*
    * Counts the number of valid entries inside the pokedex.
    Precondition: All entry names have been initialized to ""
    
    @param Pokedex[] is an array of the "entry" structure.
    @return the amount of valid entries.
*/ 
int countEntries( entry Pokedex[] )
{
    int pdSize = 0;
    
    // Goes through all entries inside the Pokedex
    for( int i = 0; i < ENTRIES; i++ ) {
        // Checks if the first character of the entry name is a letter 
        // in the Latin alphabet: ASCII decimal value from 65 to 122.
        if( (int)Pokedex[i].name[0] >= 65 && (int)Pokedex[i].name[0] <= 122 ) 
            pdSize++;
    }

    return pdSize;
}

/*
    * Checks if a name is currently being used for a valid Pokedex entry

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
    @param name[] refers to the string being checked.
    @returns 1 if the name is unique, 
             0 if the name is currently being used for another entry
*/ 
int isNameUnique( entry Pokedex[], int pdSize, char name[] )
{
    // If the entry name has been created, return 0
    for( int i = 0; i < pdSize; i++ ) {
        if( strcmp(Pokedex[i].name, name) == 0 ) {
            printf( "\t\t\t\t\t     " );
            printf( BRED "The name %s has already been used for Pokedex Entry %d.", name, i+1 );
            printf( "\n" DFLT );
            return 0;
        }
    }
    return 1;
}  

/*
    * Checks if a name begins with a letter from the alphabet or not.

    @param name[] refers to the string being checked.
    @returns 1 if the name is valid,
             0 if the name begins with a number or special character
*/ 
int isNameValid( char name[] )
{
    // Checks if the first character of the entry name is a letter 
    // in the Latin alphabet: ASCII decimal value from 65 to 122.
    if( (int)name[0] >= 65 && (int)name[0] <= 122 )
        return 1;
    else
        return 0;
}

/*
    * Checks if the entry type is valid or not.
    Precondition: The entry type must be a string.
    
    @param type[] refers to the string being checked
    @return 1 if the entry type is valid,
            0 if the entry type is invalid.
*/ 
int isTypeValid( char type[TYPE] )
{
    if( strcmp(type, "Electric") == 0 || strcmp(type, "electric") == 0 )
        return 1;
    else if( strcmp(type, "Fire") == 0 || strcmp(type, "fire") == 0 )
        return 1;
    else if( strcmp(type, "Grass") == 0 || strcmp(type, "grass") == 0 )
        return 1;
    else if( strcmp(type, "Water") == 0 || strcmp(type, "water") == 0 )
        return 1;
    else {
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "The following type \"%s\" is invalid.\n", type );
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "Pokemon type can only be: Electric, Fire, Grass or Water" );
        printf( "\n" DFLT );
        return 0;
    }
}

/*********************************************************
  
                Manage Pokedex Entries

*********************************************************/

/*
    * Sort the Pokedex entries by removing the invalid entries

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
*/
void sortPokedex( entry Pokedex[], int pdSize ) 
{
    int i, k = 0;

    // Initialize temporary entry structures
    entry newPokedex[ENTRIES];
    entry empty = { "", "", "" };          
    entry *ptr;

    // Put all valid entries in "newPokedex"
    for( i = 0; i < pdSize; i++ ) {
        if( (int)Pokedex[i].name[0] >= 65 && (int)Pokedex[i].name[0] <= 122 ) {
            ptr = &Pokedex[i];
            newPokedex[k] = *ptr;
            k++;
        }
    }

    // Update "Pokedex" 
    for( i = 0; i < pdSize; i++ ) {
        if( i <= k ) {
            ptr = &newPokedex[i];
            Pokedex[i] = *ptr;
        }
        else {
            ptr = &empty;
            Pokedex[i] = *ptr;
        }
    }
}

/*
    * Creates a new entry inside the Pokedex. The details
    for the new entry will be manually added by the user.
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
    @returns the new number of valid entries in Pokedex[].
*/ 
int addEntry( entry Pokedex[], int pdSize )
{
    // Declare variables
    entry newEntry;
    int check_name;
    int check_type;
    char check_choice[5];
    int nameSize;
    entry *ptr;

    // Introduction sequence
    char title[] = "Creating a new entry for the Pokedex";
    char text[] = "Created entry #";

    // Loop until the user wants to stop adding a new entry
    do {
        printf( BWHT "\n" );
        gradualCenterText( title, 25 );
        printEllipsis( 500 );
        Sleep( 500 );
        
        printf( BWHT "\n" );
        gradualCenterText( text, 25 );
        printf( "%d!", pdSize+1 );
        printf( "\n\n\n" DFLT );
        Sleep( 1000 );

        // Loop until the user enters a unique and valid name
        do {
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Enter a name: " );
            scanf( "%s", newEntry.name );

            check_name = isNameValid( newEntry.name );
            if( check_name == 1 ) 
                check_name = isNameUnique( Pokedex, pdSize, newEntry.name );
            else {
                printf( "\t\t\t\t\t\t\t" );
                printf( BRED "The name %s is invalid.\n", newEntry.name ); 
                printf( "\t\t\t\t\t\t\t" );
                printf( BRED "Names must not begin with numbers or special characters." );
                printf( "\n" DFLT );
            }

        } while( check_name != 1 );
        toUpper( newEntry.name );

        // Loop until the user enters a valid pokemon type
        do {
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Enter a type: ");
            scanf( "%s", newEntry.type );
            check_type = isTypeValid( newEntry.type );
        } while( check_type != 1 );
        toUpper( newEntry.type );

        // Add a description
        printf( "\t\t\t\t\t\t" );
        printf( BWHT "Enter a description: " );
        fflush( stdin );
        fgets( newEntry.desc, DESC, stdin );
        printf( "\n" DFLT );

        // Appends a null byte on the last character '\n'
        nameSize = strlen( newEntry.desc );
        newEntry.desc[nameSize-1] = '\0';

        // Adds newEntry to Pokedex[]
        ptr = &newEntry; 
        Pokedex[pdSize] = *ptr; 
        pdSize++;

        // Ask if user wants to continue adding new entries, loop until input is valid
        do {
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Do you want to add another entry? [Yes/No]: " );
            scanf( "%s", &check_choice );
            printf( "\n" DFLT );

            if( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ||
                (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0)) ) {
                printf( "\t\t\t\t\t\t" );
                printf( BRED "\nThe input is invalid. Please try again." DFLT );
            }
        } while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                   (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );

    } while( !((strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );

    return pdSize;
}

/*
    * Checks if the entry name being used is valid or not.
    Precondition: The entry being modified should already exist 

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
    @returns 1 if the entry was succesfully modified.
             0 if the entry cannot be found.
*/ 
int modifyEntry( entry Pokedex[], int pdSize )
{
    // Declare variables
    entry newEntry;
    entry *ptr;
    int nEntry;
    int nEdit;
    int nDescSize;
    char check_choice[5];
    char check_choice_2[5];
    int check_name;
    int check_type;

    // Introduction sequence
    char title[] = "Modifying an existing entry inside the Pokedex";

    // Loop until the user wants to stop modifying an entry
    do {
        printf( BWHT "\n" );
        gradualCenterText( title, 25 );
        printEllipsis( 500 );
        printf( "\n\n\n" DFLT );
        Sleep( 1000 );

        // If there are no entries, end the function
        if( pdSize == 0 ) {
            printf( BRED "\n" );
            centerText( "The Pokedex is empty." );
            printf( "\n" DFLT );
            return 0;
        }

        // Print all valid entries
        for( int i = 0; i < pdSize; i++ ) {
            if( i % 2 == 0 ) 
                printf( "\n\t\t\t\t\t\t\t" );
            printf( BWHT "Entry #%d - %s\t", i+1, Pokedex[i].name );
            Sleep( 10 );
        } printf( "\n\n" );

        // Loop until user enters a valid input
        do {
            // Ask the user which entry they want to modify
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Enter the entry number you want to modify " );
            printf( "[1 - %d]: ", pdSize );
            scanf( "%d", &nEntry );
            printf( "\n" DFLT );

            if( !(nEntry > 0) || !(nEntry < pdSize) ) {
                printf( "\t\t\t\t\t\t\t" );
                printf( BRED "Entry %d does not exist. Please choose a valid entry.", nEntry );
            }
        } while( !( nEntry > 0 && nEntry < pdSize) );

        // Copies Pokedex[nEntry-1] to newEntry
        ptr = &Pokedex[nEntry-1]; 
        newEntry = *ptr;

        // Print entry details
        printf( "\t\t\t\t\t\t\t" );
        printf( BWHT "Pokedex Entry #%d is being modified.\n", nEntry );
        printf( "\n\t\t\t\t\t\t\t" );
        printf( BWHT "Name: %s\n", Pokedex[nEntry-1].name );
        printf( "\t\t\t\t\t\t\t" );
        printf( BWHT "Type: %s\n", Pokedex[nEntry-1].type );
        printf( "\t\t\t\t\t\t\t" );
        printf( BWHT "Desc: %s\n", Pokedex[nEntry-1].desc );

        // Loop until the user wants to stop modifying an entry
        do{ 
            printf( "\n" DFLT );
            // Loop until the user selects a valid option
            do {
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Which type of information do you want to modify?\n" );
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BWHT "1 - Name\n" );
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BWHT "2 - Type\n" );
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BWHT "3 - Description\n" );
                printf( "\n\t\t\t\t\t\t\t\t\t" );
                printf( BWHT "Enter [1-3]: " );
                scanf( "%d", &nEdit );

                if( nEdit < 1 || nEdit > 3 )
                    printf( BRED "\t\t\t\t\t\t\tThe input should be between 1 and 3." DFLT );   

            } while( !(nEdit >= 1 && nEdit <= 3) );

            printf( "\n" );
            switch( nEdit )
            {
                // Modify name
                case 1: 
                    // Loop until the user enters a unique and valid name
                    do {
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BWHT "Enter a name: " );
                        scanf( "%s", newEntry.name );

                        check_name = isNameValid( newEntry.name );
                        if( check_name == 1 ) 
                            check_name = isNameUnique( Pokedex, pdSize, newEntry.name );
                        else {
                            printf( "\t\t\t\t\t\t\t" );
                            printf( BRED "The name %s is invalid.\n", newEntry.name );
                            printf( "\t\t\t\t\t\t\t" );
                            printf( BRED "Names must not begin with numbers or special characters." );
                            printf( "\n" DFLT );
                        }

                        // Print success message
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BGRN "The Pokemon name was succesfully changed to %s.", newEntry.name );
                        printf( "\n\n" DFLT );

                    } while( check_name != 1 );
                    toUpper( newEntry.name );
                    break;

                // Modify type
                case 2:
                    // Loop until the user enters a valid pokemon type
                    do {
                        printf( "\t\t\t\t\t\t" );
                        printf( BWHT "Enter a new type: ");
                        scanf( "%s", newEntry.type );
                        check_type = isTypeValid( newEntry.type);
                    } while( check_type != 1 );
                    toUpper( newEntry.type );

                    // Print success message
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BGRN "The Pokemon type was succesfully changed to %s.", newEntry.type );
                    printf( "\n" DFLT );

                    break;

                // Modify description
                case 3:
                    printf( "\t\t\t\t\t\t" );
                    printf( "Enter a new description: " );
                    fflush( stdin );
                    fgets( newEntry.desc, DESC, stdin );
                    printf( "\n" DFLT );
                    
                    nDescSize = strlen( newEntry.desc );
                    // Remove trailing new line from fgets()
                    for( int i = 0; i < nDescSize; i++ ) {
                        if( newEntry.desc[i] == '\n' )
                            newEntry.desc[i] = '\0';
                    }

                    // Print success message
                    printf( "\t\t\t\t\t\t\t\t" );
                    printf( BGRN "The entry description was succesfully modified.", newEntry.desc );
                    printf( "\n" DFLT );
            }

            // Ask user if they want to continue editing the details, loop until input is valid
            do {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Do you want to continue editing the details? [Yes/No]: " );
                scanf( "%s", &check_choice );
                printf( "\n" );

                if( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ||
                    (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0)) ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BRED "\nThe input is invalid. Please try again." DFLT );
                }
            } while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                    (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
                    
        } while( !((strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
        
        // Updates entry
        ptr = &newEntry; 
        Pokedex[nEntry-1] = *ptr;

        // Print success message
        printf( "\t\t\t\t\t" );
        printf( BGRN "The entry has been succesfully been modified with all the changes made." );
        printf( "\n" DFLT );

        // Ask if user wants to continue adding new entries, loop until input is valid
        do {
            printf( "\t\t\t\t\t\t" );
            printf( BWHT "Do you want to add another entry? [Yes/No]: " );
            scanf( "%s", &check_choice_2 );

            if( !((strcmp(check_choice_2,"Yes") == 0) || (strcmp(check_choice_2,"yes") == 0) ||
                (strcmp(check_choice_2,"No") == 0) || (strcmp(check_choice_2,"no") == 0)) ) {
                printf( "\t\t\t\t\t\t" );
                printf( BRED "\nThe input is invalid. Please try again." DFLT );
            }
        } while( !((strcmp(check_choice_2, "Yes") == 0) || (strcmp(check_choice_2, "yes") == 0) || 
                (strcmp(check_choice_2, "No") == 0) || (strcmp(check_choice_2, "no") == 0)) );

    } while( !((strcmp(check_choice_2, "No") == 0) || (strcmp(check_choice_2, "no") == 0)) );

    return 1; 
}

/*
    * Asks the user to select a valid entry. Afterwards, clear
    the data from the selected entry and sort the Pokedex
    using sortPokedex();

    Precondition: sortPokedex() has already been created
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
    @returns the new number of valid entries in Pokedex[].
*/ 
int deleteEntry( entry Pokedex[], int pdSize ) 
{
    // Initialize variables
    entry empty = { "", "", "" };        
    entry *ptr;      
    int nEntry;
    char check_choice[5];

    // Introduction sequence
    char title[] = "Deleting an existing entry inside the Pokedex";

    // Loop until user wants to stop deleting an entry
    do {
        printf( BWHT "\n" );
        gradualCenterText( title, 25 );
        printEllipsis( 500 );
        printf( "\n\n" DFLT );
        Sleep( 1000 );

        // If there are no entries, end the function
        if( pdSize == 0 ) {
            printf( BRED "\n" );
            centerText( "The Pokedex is empty." );
            printf( "\n" DFLT );
            return 0;
        }
        else {

            // Print all valid entries
            for( int i = 0; i < pdSize; i++ ) {
                if( i % 2 == 0 ) 
                    printf( "\n\t\t\t\t\t\t\t" );
                printf( BWHT "Entry #%d - %s\t", i+1, Pokedex[i].name );
                Sleep( 10 );
            } printf( "\n\n" );

            // Loop until nEntry is valid
            do {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Enter the entry number you want to delete " );
                printf( "[1 - %d]: ", pdSize );
                scanf( "%d", &nEntry );

                if( !(nEntry > 0) || !(nEntry <= pdSize) ) {
                    printf( "\t\t\t\t\t\t" );
                    printf( BRED "Entry %d does not exist. Please choose a valid entry.", nEntry );
                    printf( "\n" DFLT );
                }
            } while( !( nEntry > 0 && nEntry <= pdSize) );

            // Print a success message
            printf( "\t\t\t\t\t\t" );
            printf( BRED "Entry #%d - %s has been removed ", nEntry, Pokedex[nEntry-1].name );
            printf( "\n" DFLT );

            // Deletes Pokedex[nEntry-1]
            ptr = &empty;
            Pokedex[nEntry-1] = *ptr; 

            // Sort Pokedex
            sortPokedex( Pokedex, pdSize );
            pdSize--;

            // Ask user if they want to continue deleting an entry, loop until input is valid
            do {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Do you want to continue deleting more entries? [Yes/No]: " );
                scanf( "%s", &check_choice );
                printf( "\n" );

                if( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ||
                    (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0)) ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BRED "\nThe input is invalid. Please try again." DFLT );
                }
            } while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                    (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
        }
    } while( !((strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );

    return pdSize;
}

/*********************************************************
 
                      Display Entry 

*********************************************************/

/*
    * Displays all the valid entries inside the Pokedex
    Precondition: There must be valid entries in the Pokedex
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
*/ 
void displayPokedex( entry Pokedex[], int pdSize )
{
    // Introduction sequence
    char title[] = "Displaying all Pokedex entries";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   


    // If there are no entries 
    if( pdSize == 0 ) {
        printf( BRED "\n" );
        centerText( "The Pokedex is empty." );
        printf( "\n" DFLT );
    }
    // If there are valid entries
    else {
        // Go through every single Pokedex entry
        for( int i = 0; i < pdSize; i++ ) {
            if( (int)Pokedex[i].name[0] >= 65 && (int)Pokedex[i].name[0] <= 122 ) {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Pokedex Entry #%d\n", i+1 );
                Sleep( 300 );
                printf( "\t\t\t\t\t\t\t" );            
                printf( BWHT "Name: " );
                printf( NWHT "%s\n", Pokedex[i].name ); 
                Sleep( 300 );
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Type: " );
                printf( NWHT "%s\n", Pokedex[i].type ); 
                Sleep( 300 );
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Desc: " );
                printf( NWHT "%s\n", Pokedex[i].desc ); 
                Sleep( 300 );
                printf( "\n" DFLT );
                Sleep( 1000 );

                // Clear terminal every 5 entries
                if( ( i != 0 ) && ((i % 5 ) == 0) )
                    system( "cls" ); 
            }
        }
    }
}

/*
    * Displays all the valid entries inside the Pokedex that falls
    under a specific type category given as a parameter.

    Precondition: type[] must either be "Electric", "Fire", 
                  "Grass", or "Water"
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries in Pokedex[].
*/ 
void displayByType( entry Pokedex[], int pdSize ) 
{
    char type[TYPE];  
    int check_type;      
    int nTotal = 0;

    // Introduction sequence
    char title[] = "Displaying Pokedex entries by Pokemon type";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   

    // If there are no entries 
    if( pdSize == 0 ) {
        printf( BRED "\n" );
        centerText( "The Pokedex is empty." );
        printf( "\n" DFLT );
    }
    // If there are valid entries
    else {
        // Display valid Pokemon types
        printf( "\t\t\t\t\t\t" );
        printf( BWHT "The valid pokemon types are: " );
        printf( BYEL "Electric, " );
        printf( BRED "Fire, " );
        printf( BGRN "Grass, " );
        printf( BBLU "and Water." );
        printf( "\n\n\n" DFLT );


        // Loop until the user enters a valid pokemon type
        do {
            printf( "\t\t\t\t\t\t\t" );
            printf( BWHT "Enter a new type: ");
            scanf( "%s", &type );
            check_type = isTypeValid( type );
        } while( check_type != 1 );
        toUpper( type );
        Sleep( 1000 );    

        printf( "\n\n" DFLT );
        printf( "\t\t\t\t\t\t\t" );
        printf( BWHT "These are all of the %s type Pokemon: \n\n", type );
        for( int i = 0; i < pdSize; i++ ) {
            if( strcmp( Pokedex[i].type, type ) == 0 ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BWHT "Pokedex Entry #%d - %s \n", i+1, Pokedex[i].name );
                    nTotal++;
                    Sleep( 300 );
            } 
        }

        // If there are no Pokemon with the specified type
        if( nTotal == 0 ) {
            printf( "\t\t\t\t\t\t\t" );
            printf( BBLK "There are no %s type Pokemon\n", type );
        }
    }
}

/*
    * Displays all the Pokedex entries where the name contains the keyword
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
    @returns the total number of entries that includes the keyWord
*/ 
int displayByName( entry Pokedex[], int pdSize )
{
    // Initialize variables
    char keyWord[NAME];             // the keyword being searched
    int check_keyword;              // variable used to check if the keyword is valid
    int nKeySize, nNameSize;        // variable used to store string sizes
    int check_valid;                // variable used to check if the keyword has been found
    int a_valid[ENTRIES];           // array used to store the "Pokedex[]" index of valid names
    int check_dup;                  // variable used to check for duplicate names in "a_valid[]"
    int nValid = 0;                 // number of elements in "a_valid[]"

    // Introduction sequence
    char title[] = "Displaying Pokedex entries by a keyword";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   

    // If there are no entries
    if( pdSize == 0 ) {
        printf( BRED "\n" );
        centerText( "The Pokedex is empty." );
        printf( "\n" DFLT );
    }
    // If there are valid entries
    else {
        // Prompt user to enter a keyword, loop until keyword is valid
        do {
            printf( "\t\t\t\t\t\t\t" );
            printf( BWHT "Please enter a keyword: " );
            scanf( "%s", &keyWord );

            check_keyword = isNameValid( keyWord );
            if( check_keyword == 0 ) {
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BRED "The keyword %s is invalid.", keyWord );
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BRED "A keyword must not begin with numbers or special characters." );
            }
        } while( check_keyword != 1 );

        printf( "\n\n" DFLT );

        nKeySize = strlen ( keyWord );
        // Selects an entry from the pokedex
        for( int i = 0; i < pdSize; i++ ) {
            nNameSize = strlen ( Pokedex[i].name );
            // Selects a character from Pokedex.name
            for( int j = 0; j < nNameSize; j++ ) {
                check_valid = 0;
                // Selects a character from keyWord
                for( int k = 0; k < nKeySize; k++ ) {
                    // If found, then increase check_valid
                    if( Pokedex[i].name[j+check_valid] == keyWord[k] )
                        check_valid++;
                }
                // * If check_valid is equal to nKeySize, then the
                // keyWord has been found inside an entry name
                if( check_valid == nKeySize ) {
                    a_valid[nValid] = i;
                    nValid++;
                }
            }
        }

        // End function if there are no matches
        if( nValid == 0 ) {
            printf( "\t\t\t\t\t\t\t\t" );
            printf( BRED "There are no valid matches." );
            printf( "\n" DFLT );
            return nValid;
        }

        // Check for duplicates
        for( int i = 0; i < nValid; i++ ) {
            for( int j = i + 1; j <nValid; j++ ) {
                // Check if the name is a duplicate
                if( strcmp( Pokedex[a_valid[i]].name, Pokedex[a_valid[j]].name ) == 0 ) {
                    // Delete current position of duplicate elmenet
                    for( int k = j; k < nValid; k++ ) 
                        a_valid[k] = a_valid[k+1];
                    // Decrease size of nValid because the duplicate has been removed
                    nValid--;
                    // Decrease size of j to remain on the current element
                    j--;
                }
            }
        }

        printf( "\t\t\t\t\t\t\t" );
        printf( BGRN "Found %d entries with the keyword \"%s\" \n", nValid, keyWord );
        // Prints all entries that contains the keyword
        for( int i = 0; i < nValid; i++ ) {
            printf( "\t\t\t\t\t\t\t" );
            printf( BWHT "Pokedex Entry #%d - %s\n", a_valid[i]+1, Pokedex[a_valid[i]].name );
        }
    }

    return nValid;
}

/*********************************************************
  
                   Manage Research Tasks

*********************************************************/

/*
    * Counts the number of all available research task inside ResearchTask[].
    Precondition: All research names have been initialized to " "
    
    @param ResearchTask[] contains all available research task
    @return the amount of available research tasks.
*/ 
int countAllTasks( research ResearchTask[] )
{
    int rtSize = 0;
    
    // Goes through "research" array inside the "entry" 
    for( int i = 0; i < TASK; i++ ) {
        // Checks if the first character of the task name is a letter 
        if( isNameValid(ResearchTask[i].name) == 1 ) {
            rtSize++;
        }
    }

    return rtSize;
}

/*
    * Counts the number of available research task inside a 
    given Pokedex entry.

    @param Pokedex[] contains all valid Pokedex entries
    @param nEntry refers to the entry number of a Pokedex
    @return the amount of valid research task.
*/ 
int countTaskPerPokemon( entry Pokedex[], int nEntry )
{
    int rtSize = 0;

    // Goes through "research" array inside the "entry" 
    for( int i = 0; i < TASK; i++ ) {
        // Checks if the first character of the task name is a letter 
        if( isNameValid(Pokedex[nEntry-1].task[i].name) == 1 ) {
            rtSize++;
        }
    }

    return rtSize;
}

/*
    * Sort the Research Task by removing invalid entries

    @param ResearchTask[] contains all Research Tasks
    @param rtSize is the number of valid entries in ResearchTask[]
*/
void sortResearch( research ResearchTask[], int rtSize )
{
    int i, k = 0;

    // Initialize temporary entry structures
    research newResearchTask[TASK];
    research empty = { "", "", 0 };
    research *ptr;

    // Put all valid entries in "newResearchTask"
    for( i = 0; i < rtSize; i++ ) {
        if( (int)ResearchTask[i].name[0] >= 65 && (int)ResearchTask[i].name[0] <= 122 ) {
            ptr = &ResearchTask[i];
            newResearchTask[k] = *ptr;
            k++;
        }
    }

    // Update "ResearchTask"
    for( i = 0; i < rtSize; i++ ) {
        if( i <= k ){
            ptr = &newResearchTask[i];
            ResearchTask[i] = *ptr;
        }
        else {
            ptr = &empty;
            ResearchTask[i] = *ptr;
        }
    }
}

/*
    * Adds a new research task for entries inside the Pokedex.
    The new task created will be added inside ResearchTask[] and
    the specified Pokedex entries.

    Precondition: The available research task must not exceed 
                the maximum of task available (check macro TASK)
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
    @param ResearchTask[] contains all valid research tasks.
    @param rtSize is the number of valid research tasks.
    @returns the new number of valid research tasks
*/ 
int addResearch( entry Pokedex[], int pdSize, 
                 research ResearchTask[], int rtSize ) 
{
    // Initialize a temporary structure
    research newTask = { "", "", 0 };

    // Introduction sequence
    char title[] = "Creating a new research task";
    printf( BWHT "\n" );
    gradualCenterText( title, 25 );
    printEllipsis( 500 );
    printf( "\n\n\n" DFLT );
    Sleep( 1000 );

    // Prompts user to enter the task name
    printf( "\t\t\t\t\t\t\t" );
    printf( BWHT "Creating a new research task.\n" );
    printf( "\t\t\t\t\t\t" );
    printf( BWHT "Please enter the task name: " );
    fflush( stdin );
    fgets( newTask.name, DESC, stdin );

    // Appends a null byte on the last character '\n'
    int nameSize;
    nameSize = strlen( newTask.name );
    newTask.name[nameSize-1] = '\0';

    // Prompts user to enter a task description
    printf( "\t\t\t\t\t\t" );
    printf( BWHT "Please enter the task description: " );
    fflush( stdin );
    fgets( newTask.desc, DESC, stdin );
    printf( "\n" DFLT );

    // Prompt user to select an option
    printf( "\t\t\t\t\t\t" );
    printf( BWHT "Would you like to add the new research task to: \n" );
    printf( "\t\t\t\t\t\t\t" );
    printf( NWHT "[1] A single entry of your choice\n" );
    printf( "\t\t\t\t\t\t\t" );
    printf( NWHT "[2] All valid entries inside the Pokedex\n" );
    printf( "\t\t\t\t\t\t\t" );
    printf( NWHT "[3] All entries, including valid and future entries\n\n" );

    // Initialize option variable
    int nOption;

    do {
        printf( "\t\t\t\t\t\t\t" );
        printf( BWHT "Please choose an option [1-3]: " );
        scanf( "%d", &nOption );
    } while( !(nOption == 1 || nOption == 2 || nOption == 3) );
    printf( "\n" DFLT );

    // Initialize pointer 
    research *ptr;
    ptr = &newTask; 

    switch( nOption ) 
    {
        // For single entry
        case 1:
            int nEntry;
                // Displays the number and name of all entries inside the Pokedex
                for( int i = 0; i < pdSize; i++ ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BWHT "Entry #%d - %s\n", i+1, Pokedex[i].name );
                }
                // Prompt to enter the entry number
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Enter the entry number you want to add the task to: " );
                scanf( "%d", &nEntry );

                // Update Pokedex
                strcpy( Pokedex[nEntry-1].task[rtSize].name, newTask.name );
                strcpy( Pokedex[nEntry-1].task[rtSize].desc, newTask.desc );
                Pokedex[nEntry-1].task[rtSize].progress = newTask.progress;

                // Print a success message
                printf( "\t\t\t\t\t\t\t" );
                printf( BGRN "The research task has been added to:\n" );
                printf( "\t\t\t\t\t\t\t\t" );
                printf( BGRN "Entry #%d - %s\n", nEntry, Pokedex[nEntry-1].name );
                printf( "\n" DFLT );
                break;
        // Adds the new tasks to all valid entries
        case 2:
            for( int i = 0; i < pdSize; i++ ) {
                strcpy( Pokedex[i].task[rtSize].name, newTask.name );
                strcpy( Pokedex[i].task[rtSize].desc, newTask.desc );
                Pokedex[i].task[rtSize].progress = newTask.progress;
            } 

            // Print a success message
            printf( "\t\t\t\t\t\t\t" );
            printf( BGRN "The research task has been added to all valid entries!" );
            printf( "\n" DFLT );
            break;
        // Adds the new tasks for all valid and future entries
        case 3:
            for( int j = 0; j < ENTRIES; j++ ) {
                strcpy( Pokedex[j].task[rtSize].name, newTask.name );
                strcpy( Pokedex[j].task[rtSize].desc, newTask.desc );
                Pokedex[j].task[rtSize].progress = newTask.progress;
            } 
            printf( "\t\t\t\t\t\t\t" );
            printf( BGRN "The research task has been added to all current and future entries!" );
            printf( "\n" DFLT );
            break;
    }

    // Adds the task to ResearchTask[]
    ResearchTask[rtSize] = *ptr;
    return rtSize+1;
}

/*
    * Update the progress of a research task for a given entry

    @param Pokedex[] contains all valid Pokedex entries
    @param pdSize is the number of valid Pokedex entries.
*/
void updateResearch( entry Pokedex[], int pdSize,
                     research ResearchTask[], int rtSize )
{
    int nEntry;
    int nType;
    int nProgress;
    char check_choice[5];

    // Introduction sequence
    char title[] = "Updating progress on a research task";
    
    // If there are no research tasks available.
    if( rtSize == 0 ) {
        printf( "\t\t\t\t\t\t\t" );
        printf( BRED "There are no research tasks available.\n" ); 
    }
    else {
        // Loop until the user wants to stop choosing an entry and updating its progress
        do {
            printf( BWHT "\n" );
            gradualCenterText( title, 25 );
            printEllipsis( 500 );
            printf( "\n\n\n" DFLT );
            Sleep( 1000 );

            // Print all valid entries
            for( int i = 0; i < pdSize; i++ ) {
                if( i % 2 == 0 ) 
                    printf( "\n\t\t\t\t\t\t\t" );
                printf( BWHT "Entry #%d - %s\t", i+1, Pokedex[i].name );
                Sleep( 10 );
            } printf( "\n\n" );

                // Prompt user to enter the entry number to be updated
                // Loop until the input is valid
            do {
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Enter the entry number: " );
                scanf( "%d", &nEntry );
            } while( !((nEntry > 0) && (nEntry <= pdSize)) );

            // If there are no available research task for the entry
            if( rtSize == 0 ) {
                printf( BRED "The entry does not have any research tasks.\n" ); 
                printf( "\t\t\t\t\t\t\t" );
            }
            // If there are research tasks available for the entry
            else {
                // Display all research tasks available for the entry
                for( int j = 0; j < rtSize; j++ ) {
                    if( isNameValid( Pokedex[nEntry-1].task[j].name ) == 1 )
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BWHT "Research Task #%d: %s\n", j+1, Pokedex[nEntry-1].task[j].name );
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BWHT "Description: %s\n", Pokedex[nEntry-1].task[j].desc );
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BWHT "Progress: %d\n", Pokedex[nEntry-1].task[j].progress );
                        Sleep( 1000 );
                } printf( "\n\n" DFLT );

                // Prompt user to select a research task, loop until the input is valid
                do {    
                    printf( "\t\t\t\t\t\t\t" );
                    printf( "Choose a research task to update [1-%d]: ", rtSize );
                    scanf( "%d", &nType );

                    if( !(nType >= 1 && nType <= rtSize ) ) {
                        printf( "\t\t\t\t\t\t\t" );
                        printf( BRED "\nThe input is invalid. Please try again." DFLT );
                    }

                } while( !(nType >= 1 && nType <= rtSize ) );

                // Prompt user to update the progress of the selected research task.
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Research Task #%d: %s\n", nType, Pokedex[nEntry-1].task[nType-1].name );
                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Progress: %d\n", Pokedex[nEntry-1].task[nType-1].progress );
                printf( "\n" DFLT );

                printf( "\t\t\t\t\t\t\t" );
                printf( BWHT "Enter the new progress for the research task: " );
                scanf( "%d", &nProgress );
                
                printf( "\t\t\t\t\t\t\t" );
                printf( BGRN "The progress for research task %d has been updated: ", nType );
                printf( BGRN "From %d to %d!", Pokedex[nEntry-1].task[nType-1].progress, nProgress );
                printf( "\n\n" );

                // Update the progress
                Pokedex[nEntry-1].task[nType-1].progress = nProgress;
            }

                // Ask if user wants to update another entry
            do {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Do you want to update another research task? [Yes/No]: " );
                scanf( "%s", &check_choice );

                if( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ||
                    (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0)) ) {
                    printf( "\t\t\t\t\t\t" );
                    printf( BRED "\nThe input is invalid. Please try again." DFLT );
                }

            } while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                        (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );

        } while( !((strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
    } 
}

/*
    * Asks the user to select a valid entry. Afterwards, clear
    the data from the selected entry and sort ResearchTask[]
    using sortResearch();

    @param Pokedex[] contains all valid Pokedex entries
    @param pdSize is the number of valid Pokedex entries.
    @param ResearchTask[] contains all available research task
    @return the new number of available research tasks.
*/
int deleteResearch( entry Pokedex[], int pdSize,
                    research ResearchTask[], int rtSize )
{
    // Initialize variables
    research empty = { "", "", 0 };
    research *ptr;
    int nEntry;
    char check_choice[5];

    // Loop until user wants to stop deleting a research task
    do { 
        printf( BWHT "\n" );
        printf( "\t\t\t\t\t" );
        printf( BWHT "Deleting an existing research task" );
        printEllipsis( 500 );
        printf( "\n\n\n" DFLT );
        Sleep( 1000 );

        // If there are no research tasks, end the function
        if( rtSize == 0 ) {
            printf( BRED "\n" );
            centerText( "There are no research tasks available." );
            printf( "\n" DFLT );
            return rtSize;
        }
        else {
            // Loop until nEntry is valid
            do {
                // Display all available research tasks
                for( int i = 0; i < rtSize; i++ ) {
                    printf( "\n\t\t\t\t\t\t" );
                    printf( BWHT "Task #%d - %s", i+1, ResearchTask[i].name );
                    Sleep( 10 );
                }

                printf( "\n\t\t\t\t\t\t\t" );
                printf( BWHT "Enter the entry number you want to delete " );
                printf( BWHT "[1 - %d]: ", rtSize );
                scanf( "%d", &nEntry );

                if( !(nEntry > 0) || !(nEntry <= rtSize) ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BRED "Research task %d does not exist. Please choose a valid entry.", nEntry );
                    printf( "\n" );
                }
            } while( !( nEntry > 0 && nEntry <= rtSize) );

            ptr = &empty;
            // Sort through all the Pokedex entries
            for( int i = 0; i < pdSize; i++ ) {
                // Sort through each research tasks
                for( int j = 0; j < rtSize; j++ ) {
                    if( strcmp( Pokedex[i].task[j].name, ResearchTask[nEntry-1].name) == 0 ) {
                        Pokedex[i].task[j] = *ptr;
                    }
                }
            }

            // Deletes ResearchTask[nEntry-1]
            ptr = &empty;
            ResearchTask[nEntry-1] = *ptr;

            // Sort ResearchTask
            sortResearch( ResearchTask, rtSize );
            rtSize--;

            // Print a success message
            printf( "\t\t\t\t\t\t\t" );
            printf( BRED "Research task # has been removed from all entries.", nEntry );
            printf( "\n" DFLT );

            // Ask user if they want to continue deleting an research task, loop until input is valid
            do {
                printf( "\t\t\t\t\t\t" );
                printf( BWHT "Do you want to continue deleting research tasks? [Yes/No]: " );
                scanf( "%s", &check_choice );
                printf( "\n" );

                if( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ||
                    (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0)) ) {
                    printf( "\t\t\t\t\t\t\t" );
                    printf( BRED "\nThe input is invalid. Please try again." DFLT );
                }
            } while( !((strcmp(check_choice, "Yes") == 0) || (strcmp(check_choice, "yes") == 0) || 
                    (strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
        } 

    } while( !((strcmp(check_choice, "No") == 0) || (strcmp(check_choice, "no") == 0)) );
    return rtSize;
}

/*********************************************************
  
                   Display Research Tasks

*********************************************************/

/*
    * Displays all the available research task inside ResearchTask[]
    
    @param ResearchTask[] contains all available research tasks
    @param rtSize is the number of valid research tasks in ResearchTask[]
*/
void reviewResearchTask( research ResearchTask[], int rtSize )
{
    // Introduction sequence
    printf( "\n" );
    printf( "\t\t\t\t\t\t" );   // 5 tabs
    printf( BWHT "Reviewing all available research tasks" );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   

    // If there are no entries 
    if( rtSize == 0 ) {
        printf( "\n" );
        printf( "\t\t\t\t\t\t\t" );   // 6 tabs
        printf( BRED "There are no research tasks available.");
        printf( "\n" DFLT );
    }
    else {
        // Go through every ResearchTask[] entry
        for( int i = 0; i < rtSize; i++ ) {
            if( (int)ResearchTask[i].name[0] >= 65 && (int)ResearchTask[i].name[0] <= 122 ) {
                printf( "\t\t\t\t\t\t\t" );     // 7 tabs
                printf( BWHT "Research Task #%d\n", i+1 );
                printf( "\n" DFLT );
                Sleep( 300 );
                printf( "\t\t\t\t\t\t" );       // 6 tabs            
                printf( BWHT "Name: " );
                printf( NWHT "%s\n", ResearchTask[i].name ); 
                Sleep( 300 );
                printf( "\t\t\t\t\t\t" );       // 6 tabs    
                printf( BWHT "Desc: " );
                printf( NWHT "%s\n", ResearchTask[i].desc ); 
                Sleep( 300 );
                printf( "\n" DFLT );
                Sleep( 1000 );

                // Clear terminal every 5 entries
                if( ( i != 0 ) && ((i % 5 ) == 0) )
                    system( "cls" ); 
            }
        }
    }

}

/*
    * Displays all the available research task for a given entry.

    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
    @param ResearchTask[] contains all valid research tasks.
*/ 
void reviewTaskPerPokemon( entry Pokedex[], int pdSize, 
                           research ResearchTask[], int rtSize )
{
    int nEntry;

    // Introduction sequence
    printf( "\n\t\t\t\t\t" );   // 5 tabs
    printf( BWHT "Reviewing all available research tasks of a pokemon" );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   

    // If there are no entries 
    if( rtSize == 0 ) {
        printf( "\n\t\t\t\t\t\t" );   // 6 tabs
        printf( BRED "There are no research tasks available." );
        printf( "\n" DFLT );
    }
    else {
        // Displays the entry number and name of all entries inside the Pokedex
        for( int i = 0; i < pdSize; i++ ) {
            printf( "\t\t\t\t\t\t\t" );    // 7 tabs
            printf( BWHT "Entry #%d - %s\n", i+1, Pokedex[i].name );
        }
        
        // Prompts user to choose an entry
        do {
            printf( "\n\t\t\t\t\t\t" );    // 6 tabs
            printf( BWHT "Enter the entry number you want to review: " );
            scanf( "%d", &nEntry );
            printf( "\n" DFLT );

            if( nEntry < 0 || nEntry > pdSize ) {
                printf( "\t\t\t\t\t\t" );    // 6 tabs
                printf( BRED "\nThe input is invalid. Please try again." DFLT );
            }
        } while( !(nEntry > 0 && nEntry <= pdSize ) );

        rtSize = countTaskPerPokemon( Pokedex, nEntry );
        if( rtSize == 0 ) {
            printf( "\n\t\t\t\t\t\t" );    // 6 tabs
            printf( BRED "The entry does not have any research tasks.\n" ); 
        }
        else {
            printf( "\t\t\t\t\t\t" );    // 6 tabs
            printf( BWHT "Entry #%d - %s\n", nEntry, Pokedex[nEntry-1].name );
            // Displays all research data for the selected pokemon
            for( int j = 0; j < rtSize; j++ ) {
                printf( "\n\t\t\t\t\t" );    // 5 tabs
                printf( BWHT "\tResearch Task #%d: %s\n", j+1, Pokedex[nEntry-1].task[j].name );
                printf( "\t\t\t\t\t" );    // 5 tabs
                printf( BWHT "\tDescription: %s", Pokedex[nEntry-1].task[j].desc );
                printf( "\t\t\t\t\t" );    // 5 tabs
                printf( BWHT "\tProgress: %d\n", Pokedex[nEntry-1].task[j].progress );
                printf( "\n" );
            }
        } 
    }
}

/*
    * Displays all the entries that has a specific research task.
    Precondition: There is at least one valid research task
    
    @param Pokedex[] contains all valid Pokedex entries.
    @param pdSize is the number of valid entries.
    @param ResearchTask[] contains all valid research tasks.
    @param rtSize is the number of valid research tasks.
*/ 
void reviewTaskPerType( entry Pokedex[], int pdSize, 
                        research ResearchTask[], int rtSize )
{
    // Introduction sequence
    printf( "\n\t\t\t\t\t" );   // 5 tabs
    printf( BWHT "Reviewing all available research tasks of a pokemon" );
    printEllipsis( 500 );
    printf( "\n" DFLT );
    Sleep( 1000 );   

    // If there are no research tasks, don't run the function
    if( rtSize == 0 ) {
        printf( "\n\t\t\t\t\t\t" );
        printf( BRED "There are no available research tasks.\n" );
    }
    else {
        // Display all research tasks available
        for( int i = 0; i < rtSize; i++ ) {
            printf( "\n\t\t\t\t\t\t" );
            printf( BWHT "Task #%d - %s\n", i+1, ResearchTask[i].name );
        } printf( "\n" DFLT );

        int nType = 0;
        // Prompt user which research task to display, loop until input is valid
        printf( "\t\t\t\t\t\t" );
        printf( BWHT "Choose a research task to display [1-%d]: ", rtSize );
        while( !(nType >= 1 && nType <= rtSize) ) 
            scanf( "%d", &nType );
        printf( "\n" DFLT );

        char check_choice[5];
        // Prompt user if they want to include the Pokemons without any progress, loop until input is valid
        do {
        printf( "\t\t\t\t\t\t" );
        printf( BWHT "Should entries without any progress on research task %d be displayed? [Yes/No] ", nType );
        scanf( "%s", &check_choice );
        } while( !((strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) || 
                (strcmp(check_choice,"No") == 0)  || (strcmp(check_choice,"no") == 0)) );

        printf( "\n\t\t\t\t\t\t" );
        printf( BWHT "Displaying all \"%s\" tasks:\n", ResearchTask[nType-1].name );
        printf( "\t\t\t\t\t\t" );
        printf( BWHT "Task Description: %s\n", ResearchTask[nType-1].desc );
        printf( "\n" );

        int nTotal = 0;
        // If input is "Yes", display all entries with the same research task
        if( (strcmp(check_choice,"Yes") == 0) || (strcmp(check_choice,"yes") == 0) ) {
            // Display all entries with the chosen research task
            // Go through all valid entries inside Pokedex[]
            for( int i = 0; i < pdSize; i++ ) {
                // Go through all the research tasks inside entry
                for( int j = 0; j < rtSize; j++ ) {
                    // Check if the chosen research can be found inside the entry
                    if( (strcmp(Pokedex[i].task[j].name, ResearchTask[nType-1].name ) == 0) ) {   
                        printf( "\t\t\t\t\t\t" );
                        printf( BWHT "Entry #%d - %s\n", i+1, Pokedex[i].name );
                        printf( "\t\t\t\t\t\t" );
                        printf( BWHT "Progress: %d\n", Pokedex[i].task[j].progress );
                        printf( "\n" DFLT );
                        nTotal++;
                    }
                } printf( "\n" );
            }
        }
        // If input is "No", don't display entries without any progress on the research task
        else if( (strcmp(check_choice,"No") == 0) || (strcmp(check_choice,"no") == 0) ) {
            // Display all entries with progress on the chosen research task
            // Go through all valid entries inside the Pokedex[]
            for( int i = 0; i < pdSize; i++ ) {
                // Go through all the research tasks inside entry
                for( int j = 0; j < rtSize; j++ ) {
                    // Check if the chosen research can be found inside the entry
                    if( (strcmp(Pokedex[i].task[j].name, ResearchTask[nType-1].name ) == 0) ) {   
                        // Check if the entry has progress on the research
                        if( Pokedex[i].task[j].progress != 0 ) {
                            printf( "\t\t\t\t\t\t" );
                            printf( BWHT "Entry #%d - %s\n", i+1, Pokedex[i].name );
                            printf( "\t\t\t\t\t\t" );
                            printf( BWHT "Progress: %d\n", Pokedex[i].task[j].progress );
                            printf( "\n" );
                            nTotal++;
                        }
                    }
                } printf( "\n" );
            }
        }

        if( nTotal == 0 ) {
            printf( "\t\t\t\t\t\t" );
            printf( BRED "There aren't any entries that satisfies the condition.\n" ); 
        }
    }
}

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