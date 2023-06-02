package Design;

/*******************************************************************
 *     
 *  The Art class stores and displays all the ASCII art used in 
 *  the program.
 * 
*******************************************************************/
public class Art {
    
    /// Class Attributes
    private Design design;
    
    /// ANSII Escape Color Codes
    private String BLACK = "\u001b[30m";
    private String RED = "\u001b[31m";
    private String GREEN = "\u001b[32m";
    private String YELLOW = "\u001b[33m";
    private String BLUE = "\u001b[34m"; 
    private String MAGENTA = "\u001b[35m";
    private String WHITE = "\u001b[37m";
    private String DFLT = "\u001b[0m";
    private String BOLD = "\u001B[1m";

    /*|*******************************************************
     
                        Constructor Methods

    *********************************************************/
    public Art() {
        this.design = new Design();
    }

    /*|*******************************************************
     
                        Print ASCII Art Methods

    *********************************************************/
    /**
     *   ` Prints a taxi ASCII art
     *   Source : https://saravitaya.tripod.com/_ArtTransportation.html#taxi
    */
    public void printIntroCab() {
        String[] cab = { 
            "                   [\\                      ",
            "              .----' `-----.               ",
            "             //^^^^;;^^^^^^`\\              ",
            "     _______//_____||_____()_\\________     ",
            "    /MCO1   :      : ___              `\\   ",
            "   |>   ____;      ;  |/\\><|   ____   _<)  ",
            "  {____/    \\_________________/    \\____}  ",
            "       \\ '' /                 \\ '' /       ",
            "        '--'                   '--'        "
        };

        System.out.printf( YELLOW + "\n" );
        for( int i = 0; i < cab.length; i++ ) {
            System.out.printf( "\t\t\t%s\n", cab[i] );
        } System.out.printf( WHITE + DFLT + "\n" );
    }

    /**
     *   ` Prints a "MYFARM" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
    */
    public void printTitleLogo() {
        String[] logo = {
            "  ___      ___ ___  ___ _______  __       _______  ___      ___ ",
            " |\"  \\    /\"  |\"  \\/\"  /\"     \"|/\"\"\\     /\"      \\|\"  \\    /\"  |", 
            "  \\   \\  //   |\\   \\  (: ______)    \\   |:        |\\   \\  //   |", 
            "  /\\\\  \\/.    | \\\\  \\/ \\/    |/' /\\  \\  |_____/   )/\\\\  \\/.    |", 
            " |: \\.        | /   /  // ___)/  __'  \\  //      /|: \\.        |", 
            " |.  \\    /:  |/   /  (:  ( /   /  \\\\  \\|:  __   \\|.  \\    /:  |", 
            " |___|\\__/|___|___/    \\__/(___/    \\___)__|  \\___)___|\\__/|___|", 
        };

        String[] colors = { DFLT+GREEN, DFLT+GREEN, BOLD+GREEN, BOLD+GREEN, BOLD+WHITE, DFLT+WHITE };
        char[] string;
        
        for( int i = 0; i < logo.length; i++ ) {
            string = logo[i].toCharArray();
            System.out.printf( "\n\t\t" );
            for( int j = 0; j < string.length; j++ ) {
                System.out.printf( "%s%c", colors[j%colors.length], string[j] );
            }
        }
        System.out.printf( DFLT + "\n\n" );
    }          

    /**
     *   ` Prints a House ASCII art and a "HOME" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
     *   House Art Source : https://www.asciiart.eu/buildings-and-places/houses 
    */
    public void printHomeLogo() {
        String[] logo = { 
            "        _                                                               ",
            "      _|=|__________                                                    ",
            "     /              \\         __    __    ______   .___  ___.  _______  ",
            "    /                \\       |  |  |  |  /  __  \\  |   \\/   | |   ____| ",
            "   /__________________\\      |  |__|  | |  |  |  | |  \\  /  | |  |__    ",
            "    ||  || /--\\ ||  ||       |   __   | |  |  |  | |  |\\/|  | |   __|   ",
            "    ||[]|| | .| ||[]||       |  |  |  | |  `--'  | |  |  |  | |  |____  ",
            "  ()||__||_|__|_||__||()     |__|  |__|  \\______/  |__|  |__| |_______| ",
            " ( )|-|-|-|====|-|-|-|( )                                               ",
            " ^^^^^^^^^^====^^^^^^^^^^^                                              "
        };

        String[] color = { BOLD+GREEN, BOLD+RED, BOLD+RED, BOLD+GREEN, BOLD+WHITE };
        char[] string;

        // Loop counters
        int i = 0, j = 0;
        
        // Declare Boolean conditions
        Boolean isRoof, isLowerBody, isGrass, isText, isBush;  

        // Loop through logo[]
        for( i = 0; i < logo.length; i++ ) {

            // Convert String to char[]
            string = logo[i].toCharArray();
            System.out.printf( "\n\t\t" + BOLD );

            for( j = 0; j < string.length; j++ ) {
                // Check Boolean conditions
                isRoof = i < 5;
                isLowerBody = i == logo.length-2 || i == logo.length-3;
                isGrass = i == logo.length-1;
                isText = j > 26;
                isBush = j < 4 || (j > 21 && j < 26);

                // Print text
                if( isRoof ) {
                    if( isText )        System.out.printf( "%s%c", color[j%color.length], string[j] );
                    else                System.out.printf( "%s%c", RED, string[j] );
                } else if( isLowerBody ) {
                    if( isBush )        System.out.printf( "%s%c", GREEN, string[j] );
                    else if( isText )   System.out.printf( "%s%c", color[j%color.length], string[j] );
                    else                System.out.printf( "%s%c", DFLT+WHITE, string[j] );
                } else if( isGrass ) {
                                        System.out.printf( "%s%c", GREEN, string[j] );
                } else {
                    if( isText )        System.out.printf( "%s%c", color[j%color.length], string[j] );
                    else                System.out.printf( "%s%c", DFLT+WHITE, string[j] );
                }
            }
        } System.out.printf( DFLT + "\n\n" );
    }          
    
    /**
     *   ` Prints a dollar sign ASCII art and a "STORE" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
     *   House Art Source : https://textart4u.blogspot.com/2012/03/dollar-sign-text-art-ascii-art.html
    */
    public void printStoreLogo() {

        String[] logo = {

            "           * *            ",
            "         ******            ",
            "     *****************      ______   ________ ______   _______   ________  ",
            "   ******* * * *******     /      \\ /        /      \\ /       \\ /        |",
            "   ******* * *  ****      /$$$$$$  |$$$$$$$$/$$$$$$  |$$$$$$$  |$$$$$$$$/",
            "    **********            $$ \\__$$/    $$ | $$ |  $$ |$$ |__$$ |$$ |__    ",
            "      *************       $$      \\    $$ | $$ |  $$ |$$    $$< $$    |  ",
            "           ********        $$$$$$  |   $$ | $$ |  $$ |$$$$$$$  |$$$$$/   ",
            "   *****   * * *******    /  \\__$$ |   $$ | $$ \\__$$ |$$ |  $$ |$$ |_____ ",
            "   *********** *******    $$    $$/    $$ | $$    $$/ $$ |  $$ |$$       |",
            "    *****************      $$$$$$/     $$/   $$$$$$/  $$/   $$/ $$$$$$$$/",
            "          ****             ",
            "           * *            ",
        }; 
        
        String[] color = { BOLD + GREEN, DFLT + GREEN, BOLD + GREEN, BOLD + GREEN };
        char[] string;

        // Loop through logo[]
        for( int i = 0; i < logo.length; i++ ) {

            // Convert String to char[]
            string = logo[i].toCharArray();
            System.out.printf( "\n\t      " + BOLD);

            // Declare Boolean conditions for the logo and the text
            Boolean isCenterLogo, isHighlightTop, isHighlightBot, isLogo;
            Boolean isCenterText, isHighlightText;

            for( int j = 0; j < string.length; j++ ) {
                // Check Boolean conditions
                isCenterLogo = j == 11 || j == 13;
                isHighlightTop = i == 2 && j > 7 && j < 33;
                isHighlightBot = i == 7 && j > 13 && j < 24;
                isLogo = !isHighlightTop && !isHighlightBot && j < 41;
                isCenterText = j >= 41 && j < 54;
                isHighlightText = j > 53 && j < 70 && i <= 10;

                // Print text
                if( isCenterLogo )          System.out.print( BOLD + WHITE + string[j] );
                else if( isLogo )           System.out.print( color[j%color.length] + string[j] );
                else if( isHighlightTop )   System.out.print( BOLD + BLACK + string[j]);
                else if( isHighlightBot )   System.out.print( BOLD + BLACK + string[j]);     
                else if( isCenterText )     System.out.print( WHITE + string[j] );
                else if( isHighlightText )  System.out.print( BOLD + BLACK + string[j]);     
                else                        System.out.print( GREEN + string[j] );
            }
        } System.out.printf( DFLT + "\n" );
    }

    /**
     *   ` Prints a "SEED" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
    */
    public void printSeedLogo() {
        String [] logo = {            
        "       _______. _______  _______  _______      ",
        "      /       ||   ____||   ____||       \\    ",
        "     |   (----`|  |__   |  |__   |  .--.  |    ",
        "      \\   \\    |   __|  |   __|  |  |  |  |  ",
        "  .----)   |   |  |____ |  |____ |  '--'  |    ",
        "  |_______/    |_______||_______||_______/     "                
        }; char[] string;
       
        for (int i =0 ; i < logo.length;i++){
            string = logo[i].toCharArray();
            System.out.printf( "\n\t\t  " + BOLD);
            for (int j=0; j <string.length;j++){
                if (i > 1 && j > 23){
                    System.out.print(BOLD+GREEN+ string[j]);
                }else if (i > 3 && j > 0){
                    System.out.print(BOLD+GREEN+ string[j]);
                }else if ( i < 3 && j > 32 ){
                    System.out.print(BOLD+GREEN+ string[j]);
                }else if (i == 1 && j > 25){
                    System.out.print(BOLD+GREEN+ string[j]);
                }
                else{
                    System.out.print(BLACK+ string[j]);
                }
            }
        }
        System.out.printf( DFLT + "\n\n" );
    }

    /**
     *   ` Prints a WAKING UP IN text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
    */
    public void printWakingUpLogo() {
        System.out.print( BLACK+ """
          $$\\      $$\\  $$$$$$\\  $$\\   $$\\ $$$$$$\\ $$\\   $$\\  $$$$$$\\        $$\\  $$\\ $$$$$$$\\          $$$$$$\\ $$\\   $$\\           
          $$ | $\\  $$ |$$  __$$\\ $$ | $$  |\\_$$  _|$$$\\  $$ |$$  __$$\\       $$ |  $$ |$$  __$$\\       \\_$$  _|$$$\\  $$ |          
          $$ |$$$\\ $$ |$$ /  $$ |$$ |$$  /   $$ |  $$$$\\ $$ |$$ /  \\__|      $$ |  $$ |$$ |  $$ |        $$ |  $$$$\\$$ |      $$\\ 
          $$ $$ $$\\$$ |$$$$$$$$ |$$$$$  /    $$ |  $$ $$\\$$ |$$ |$$$$\\       $$ |  $$ |$$$$$$$  |        $$ |  $$ $$\\$$ |      \\__|
          $$$$  _$$$$ |$$  __$$ |$$  $$<     $$ |  $$ \\$$$$ |$$ |\\_$$ |      $$ |  $$ |$$  ____/         $$ |  $$ \\$$$$ |          
          $$$  / \\$$$ |$$ |  $$ |$$ |\\$$\\    $$ |  $$ |\\$$$ |$$ |  $$ |      $$ |  $$ |$$ |              $$ |  $$ |\\$$$ |      $$\\ 
          $$  /   \\$$ |$$ |  $$ |$$ | \\$$\\ $$$$$$\\ $$ | \\$$ |\\$$$$$$  |      \\$$$$$$  |$$ |            $$$$$$\\ $$ | \\$$ |      \\__|
          \\__/     \\__|\\__|  \\__|\\__|  \\__|\\______|\\__|  \\__| \\______/        \\______/ \\__|            \\______|\\__|  \\__|                                                                                                                                                                                           
        """ + DFLT + "\n" );
    }

    /**
     *   ` Prints a Gaming Console ASCII art and a "GAME OVER" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
     *   Console Art Source : https://www.asciiart.eu/computers/game-consoles
    */
    public void printGameOverLogo() {

        String [] logo = { 
            "_____________________________     ________    _____      _____   ___________ ",
            "/        _____________        \\    /  _____/   /  _  \\    /     \\  \\_   _____/ ",
            "| == .  |             |     o |   /   \\  ___  /  /_\\  \\  /  \\ /  \\  |    __)_ ",
            "|   _   |     MCO1    |    B  |   \\    \\_\\  \\/    |    \\/    Y    \\ |        \\ ",
            "|  / \\  |    MyFarm   | A   O |    \\______  /\\____|__  /\\____|__  //_______  / ",
            "| | O | |             |  O    |           \\/         \\/         \\/         \\/ ",
            "|  \\_/  |   Made by:  |       |   ________   ____   _________________________ ",
            "|       |     Dei     | . . . |   \\_____  \\  \\   \\ /   /\\_   _____/\\______   \\ ",
            "|  :::  |     Seb     | . . . |    /   |   \\  \\   Y   /  |    __)_  |       _/ ",
            "|  :::  |_____________| . . . |   /    |    \\  \\     /   |        \\ |    |   \\ ",
            "|        C C P R O G 3        |   \\_______  /   \\___/   /_______  / |____|_  / ",
            "\\_____________________________/           \\/                    \\/         \\/ ",
        };

        char[] string;
        String[] color = { RED,  MAGENTA, BLUE, GREEN };
        String[] random = { BOLD+BLACK,BOLD+BLACK,MAGENTA };
        String[] random2 = {MAGENTA,MAGENTA,BOLD+BLACK };
        for (int i =0 ; i < logo.length;i++){
            string = logo[i].toCharArray();
            System.out.printf( "\n\t" + BOLD);
            for (int j=0; j <string.length;j++){
                if (j > 50 && j <131){
                    System.out.print(BOLD+RED+ string[j]);
                }else if (j>46 && j <51){
                    System.out.print(random[j%random.length] +string[j]);
                }else if (j > 130){
                    System.out.print(random2[j%random2.length] +string[j]);
                }
                else{
                    System.out.print(color[j%color.length] +string[j]);
                }
            }
        }
    }

    /**
     *   ` Prints a "Thank you" text ASCII art
     *   Text Art Source : https://patorjk.com/software/taag/
    */
    public void printThankYouLogo() {
        design.gradualPrint( BOLD + YELLOW + """
            \t\t\t    ________  __                            __                                            __ 
            \t\t\t   |        \\|  \\                          |  \\                                          |  \\
            \t\t\t    \\$$$$$$$$| $$____    ______   _______  | $$   __        __    __   ______   __    __ | $$
            \t\t\t      | $$   | $$    \\  |      \\ |       \\ | $$  /  \\      |  \\  |  \\ /      \\ |  \\  |  \\| $$
            \t\t\t      | $$   | $$$$$$$\\  \\$$$$$$\\| $$$$$$$\\| $$_/  $$      | $$  | $$|  $$$$$$\\| $$  | $$| $$
            \t\t\t      | $$   | $$  | $$ /      $$| $$  | $$| $$   $$       | $$  | $$| $$  | $$| $$  | $$ \\$$
            \t\t\t      | $$   | $$  | $$|  $$$$$$$| $$  | $$| $$$$$$\\       | $$__/ $$| $$__/ $$| $$__/ $$ __ 
            \t\t\t      | $$   | $$  | $$ \\$$    $$| $$  | $$| $$  \\$$\\       \\$$    $$ \\$$    $$ \\$$    $$|   
            \t\t\t       \\$$    \\$$   \\$$  \\$$$$$$$ \\$$   \\$$ \\$$   \\$$       _\\$$$$$$$  \\$$$$$$   \\$$$$$$  \\$$
            \t\t\t                                                          |  \\__| $$                        
            \t\t\t                                                            \\$$    $$                        
            \t\t\t                                                             \\$$$$$$
            \n\t\t\t\t\t\t\t\t -- Dei and Seb :)
                    """+ DFLT, 1, 1 );
    }
}