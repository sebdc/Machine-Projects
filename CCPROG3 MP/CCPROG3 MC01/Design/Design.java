package Design;

/*******************************************************************
 *     
 *  The Design class has a variety of customized console and string
 *  methods to make the game look better.
 * 
*******************************************************************/

public class Design {
    
    /// ANSII Escape Codes
    private String BLACK = "\u001b[30m";
    private String RED = "\u001b[31m";
    private String GREEN = "\u001b[32m";
    private String YELLOW = "\u001b[33m";
    private String DFLT = "\u001b[0m";
    private String BOLD = "\u001B[1m";

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Design() {}

    /*|*******************************************************
     
                      Console & String Methods

    *********************************************************/
    /**
     *   ` Pause the execution of the current thread for a specified
     *   amount of time in milliseconds. The argument must not be 
     *   negative, otherwise it throws an IllegalArgumentException.
     *
     *    @param millis         the duration for the method runtime in milliseconds 
     */
    public void Sleep( int millis ) {
        try {
            Thread.sleep( millis );
        } catch( Exception e ) { } 
    }

    /**
     *   ` Sleep for a given amount of time before clearing the
     *   console or the terminal. The flush() method is also used 
     *   to clear the stream.
     *
     *    @param millis         the duration for the method runtime in milliseconds 
     */
    public void clearConsole( int millis ) {
        Sleep( millis );
        System.out.print( "\033[H\033[2J" );  
        System.out.flush(); 
    }

    /**
     *   ` Gradually print an ellipsis. The printing speed can be
     *   controlled by changing the value of the delay parameter.
     *   Additionally, you can pause the execution of the thread
     *   for a given amount of the time by giving a value to the
     *   pause parameter.
     *
     *    @param delay          the speed of the print in milisseconds
     *    @param pause          the duration of the pause
     */
    public void gradualEllipsis( int delay, int pause ) {
        for( int i = 0; i < 3; i++ ) {
            Sleep( delay );
            System.out.printf( "." );
        } Sleep( pause );
    }

    /**
     *   ` Gradually print a string. The printing speed can be
     *   controlled by changing the value of the delay parameter.
     *   Additionally, you can pause the execution of the thread
     *   for a given amount of the time by giving a value to the
     *   pause parameter.
     *
     *    @param string         the string to be printed
     *    @param delay          the speed of the print in milisseconds
     *    @param pause          the duration of the pause
     */
    public void gradualPrint( String string, int delay, int pause ) {
        for( int i = 0; i < string.length(); i++ ) {
            Sleep( delay );
            System.out.printf( "%c", string.charAt(i) );
        } Sleep( pause );
    }

    /**
     *   ` Prints a given amount of white spaces.
     *
     *    @param width          the number of spaces to be printed
     */
    public void appendSpaces( int width ) {
        for( int i = 0; i < width; i++ )
            System.out.printf( " " );
    }

    /*|*******************************************************
     
                      Display Animation Methods

    *********************************************************/
    /**
     *   ` Prints the certify screen
     */
    public void displayCertifyScreen() {
        
        String[] message = {
            "This is to cerify that this project is a collaborative work, based on the collective efforts",
            "in studying and applying the concepts we have learned in CCPROG3. We have constructed the",
            "classes and their respective algorithms and corresponding code as a pair. The program was",
            "designed, run tested, and debugged as a collective effort. We further certify that we have",
            "not copied in part or whole or otherwise plagiarized work of other students and/or persons.\n",
        }; clearConsole( 0 );

        System.out.printf( "\n\n" );
        for( int i = 0; i < message.length; i++ ) {
            System.out.printf( BOLD + "\t\t" );
            appendSpaces( (100 - message[i].length())/2 );
            System.out.printf( "%s\n", message[i] );
            Sleep( 300 );
        } 
    }
    
    /**
     *   ` Prints "Please ENTER to continue "
     *    @param width          the amount of white space to be printed
     */
    public void printEnterToContinue( int width ) {
        String message = "[ Please ENTER to continue ]";
        System.out.printf( "\n" + BLACK );
        appendSpaces( width );
        System.out.printf( BOLD + message + DFLT );
    }

    /**
     *   ` Performs a count down sequence
     *    @param time             countdown from time to 0
     */
    public void countDown( int time ) {
        while( time != 0 ) {
            appendSpaces( 98/2 );
            System.out.print( BOLD );

            if( time == 3 ) 
                System.out.println( RED + time + "..." );
            else if( time == 2 ) 
                System.out.println( YELLOW + time + ".."  );
            else if( time == 1 )
                System.out.println( GREEN + time + "."  );
            else 
                System.out.println( time );

            Sleep( 500 );
            time--;
        } System.out.printf( "\n" );
        appendSpaces( 90/2 );
        gradualPrint( DFLT + BOLD + "Timer Over!" + DFLT, 30, 1000 );
    }
}