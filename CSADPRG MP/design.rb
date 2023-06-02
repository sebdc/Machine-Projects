##
#   Project : Tax Calculator Philippines 2023 
#   Course  : CSADPRG - S15
#   Date    : April 2, 2023
#
#   Members :
#       1. DELA CRUZ, Sebastien Michael, V.
#       2. GIRON, John Joseph, F.
#       3. HERCE, Dominic Marcus, R.
#       4.TAM, Wayne, G.
#

class Design

    public ### ANSII Escape Codes
    attr_accessor :bold, :norm
    attr_accessor :black, :red, :green, :yellow
    attr_accessor :blue, :magenta, :cyan, :white
    
    #===============================================#
    #               Constructor Method              #
    #===============================================#
    def initialize() 
        init_text_format()
    end

    ##
    #   ` Initializes the instance variables with ANSI escape codes 
    #   that can be used for text formatting. Each one  represents a 
    #   different color and/or style for text output in the console
    #
    #   NOTE: The ANSI escape codes will only work in consoles that
    #   support it. 
    # 
    def init_text_format() 
        @bold    = "\e[1m"
        @norm    = "\e[0m"
        @black   = "\e[30m"
        @red     = "\e[31m"
        @green   = "\e[32m"
        @yellow  = "\e[33m"
        @blue    = "\e[34m"
        @magenta = "\e[35m"
        @cyan    = "\e[36m"
        @white   = "\e[37m"
    end 

    #===============================================#
    #                Animation Methods              #
    #===============================================#
    ##
    #   ` Sleep for a given amount of time before clearing the console. 
    #
    #   @param before       duration of sleep before clearing
    #   @param after        duration of sleep after clearing   
    # 
    def clear_console( before = 0, after = 0 )
        sleep(before)
        system("cls")
        sleep(after)
    end

    ##
    #   ` Gradually prints a message with a specified delay between 
    #   each character.
    #   
    #   @param message      message to be printed
    #   @param delay        delay (in seconds) between each character  
    #
    def gradual_print( message, delay = 0 ) 
        for i in 1..message.length
            sleep(delay)
            print "#{message[i-1]}"
        end
    end 

    #===============================================#
    #                  Print Methods                #
    #===============================================#
    ##
    #   ` Appends a specified number of spaces to the current output
    #
    #   @param spaces    number of spaces to be printed
    #
    def append_spaces( spaces ) 
        for i in 1..spaces 
            print " "
        end
    end

    ##
    #   ` Adds a border around a given text with a specified color.
    #   Calling border_text(num) would simply print a white '=' num times 
    #
    #   @param text         text to be displayed 
    #   @param color        color of the border
    #   @param num          total length of the border (text included)
    #
    def border_text( text = "", color = @white, num ) 
        border_length = num - text.length 
        border = color + ("=" * (border_length.abs/2))
        print( bold + border + text + border )
    end

    ##
    #   ` Formats the given text with a specified formatting before
    #   and after the text. Default formatting is bold before and
    #   normal after.
    #
    #   @param text             text to be displayed 
    #   @param format_before    format before the text is printed
    #   @param formater_after   format after the text is printed
    #
    def format_text( text, format_before = @bold, format_after = @norm )  
        print( "#{format_before}#{text}#{format_after}" )
    end

    #===============================================#
    #                Introduction                   #
    #===============================================#
    ##
    #   ` Displays a hard-coded certification statement with a specified 
    #   amount of spaces to be printed before and after the message.
    #
    #   # @param spaces     number of spaces to be printed
    #
    def display_certify( spaces = 100 )
        message = [
            "This is to cerify that this project is a collaborative work, based on the collective efforts",
            "in studying and applying the concepts we have learned in CSADPRG. We have constructed the",
            "classes and their respective algorithms and corresponding code as a group. The program was",
            "designed, run tested, and debugged as a collective effort. We further certify that we have",
            "not copied in part or whole or otherwise plagiarized work of other students and/or persons.\n",
        ]
        
        clear_console()
        print( "\n\n" )

        for i in 0..message.length-1
            print( "\t\t #{@bold}" )
            append_spaces( (spaces - message[i].length)/2 )
            print( "#{message[i]}\n")
            sleep( 0.3 )
        end 
        print( "#{@norm}" ) 
    end 

    ##
    #   ` Displays a hard-coded list of names with a specified amount 
    #   of spaces to be printed before and after the message.
    #
    #   # @param spaces     number of spaces to be printed
    #
    def display_names( spaces = 30 ) 
        names = [
            "DELA CRUZ, Sebastien Michael, V.",
            "GIRON, John Joseph, F.",
            "HERCE, Dominic Marcus, R.",
            "TAM, Wayne, G."
        ]

        for i in 0..names.length-1 
            append_spaces( spaces )
            print( "#{i+1} - #{names[i]}\n")
            sleep( 0.3 )
        end
        print( "\n" ) 
    end 

    ##
    #   ` Prompts the user to press the ENTER key to continue. The
    #   console is cleared after the user presses ENTER. 
    #
    #   @param spaces     number of spaces to indent the prompt
    #
    def enter_to_continue( spaces = 50 )
        append_spaces( spaces )
        print( "#{@black}#{@bold}[ Please ENTER to continue ]#{@norm}" )
        gets.chomp
        clear_console( 0, 1 )
    end 

    #===============================================#
    #                  Calculator                   #
    #===============================================#
    ##
    #   ` Displays a formatted title with a specified, text, color
    #   and indentation.
    #
    #   @param title     string to be displayed as a title
    #   @param color     color for the title text
    #   @param spaces    number of spaces to indent the title
    #
    def format_title( title, color = @white, spaces = 0 ) 
        append_spaces(spaces)
        print( "\n#{@bold}#{color}#{title}#{@norm}\n" )
    end 

    ##
    #   ` Prints the output of a calculation with a given name and
    #   color. The output indentation is based on the length of the
    #   title.
    #
    #   @param name      name of the output
    #   @param output    calculated output 
    #   @param spaces    color to be used for the name and output
    #
    def indent_output( name, output, color = @white )
        l = name.length
        indent = l <= 5 ? "\t\t\t" : l <= 10 ? "\t\t" : "\t"
        print( "\t#{@bold}#{color}#{name}:#{indent}" )
        print( "#{@norm}#{color}#{'%.2f' %output}\n" )
    end
end