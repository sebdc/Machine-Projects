require_relative 'calculator.rb'
require_relative 'design.rb'

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

d = Design.new()
d.display_certify()
d.enter_to_continue()

#===============================================#
#                  Calculator                   #
#===============================================#
input = 0
c = Calculator.new()

begin
    d.clear_console()
    income = input

    ### Print title
    d.format_title( "Tax Calculator Philippines 2023", d.green )
    d.border_text( "", d.black, 50 )
        
    ### Display output
    if( income != 0 )

        ### Monthly income
        print( "\n\n" )
        d.append_spaces(10)
        d.format_text( "Monthly Income: ", d.bold+d.white )
        d.format_text( "#{'%d'%income}\n\n", d.norm )

        ### Computation result
        d.border_text( d.green + " Computation Result ", d.black, 56 )

        ### Tax computation
        d.format_title( "\nTax Computation", d.blue, 10 )
        d.indent_output( "Income Tax", c.income_tax(income), d.blue )
        d.indent_output( "Net Pay After Tax", c.after_tax(income), d.blue )

        ### Monthly contributions
        d.format_title( "Monthly Contributions", d.cyan, 10 )
        d.indent_output( "SSS", c.sss(income), d.cyan )
        d.indent_output( "PhilHealth", c.phil_health(income), d.cyan )
        d.indent_output( "Pag-IBIG", c.pag_ibig(income), d.cyan )
        d.indent_output( "Total Contributions", c.total_contribution(income), d.cyan )

        ### Deductions
        d.format_title( "Deductions", d.magenta, 10 )
        d.indent_output( "Total Deductions", c.total_deductions(income), d.magenta )
        d.indent_output( "Net Pay", income - c.total_deductions(income), d.magenta )
        
        print( "\n" )
        d.border_text( "", d.black, 50 )
    end
    
    ### Ask for input
    print( "\n\n" )
    d.append_spaces(10)
    d.format_text( "Monthly Income: ", d.bold+d.white )
    input = gets.chomp.to_f
    print( "\n" )

end while( input != 0 )

#===============================================#
#                Export Results                 #
#===============================================#
### Export the results into a .txt file
if( income != 0 )
    
    category = [ 
        "Monthly Income = ",
        "Income Tax = ",
        "Net Pay After Tax = ",
        "SSS = ",
        "PhilHealth = ",
        "Pag-IBIG = ",
        "Total Contributions = ",
        "Total Deductions = ",
        "Net Pay = "
    ]

    computation = [
        "#{income}",
        "#{c.income_tax(income)}",
        "#{c.after_tax(income)}\n",
        "#{c.sss(income)}",
        "#{c.phil_health(income)}",
        "#{c.pag_ibig(income)}",
        "#{c.total_contribution(income)}\n",
        "#{c.total_deductions(income)}",
        "#{income-c.total_deductions(income)}"
    ]

    output_file = File.open( "output_color.txt", "w" )
    for i in 0..category.length-1
        output_file.write( category[i] + computation[i] + "\n" )
    end 
    
    output_file.close()
end