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

// Libraries Used
#include <stdio.h>
#include <string.h>
#include <Windows.h>

// Terminal Dimensions
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