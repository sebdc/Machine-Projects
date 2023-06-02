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

class Calculator

    attr_accessor :design 

    #===============================================#
    #               Constructor Method              #
    #===============================================#
    def initialize()
        design = Design.new() 
    end

    #===============================================#
    #                Behaviour Methods              #
    #===============================================#
    def sss(monthly_income)
        salary = monthly_income.to_f

        if salary < 4250
            salary_credit = 4000.00
        elsif salary >= 4250 && salary < 29750
            range = (salary/1000).to_i * 1000.0
            if salary < range + 250
                salary_credit = range 
            elsif salary >= range + 250 && salary < range + 750 
                salary_credit = range + 500.0
            else 
                salary_credit = range + 1000.0
            end
        else 
            salary_credit = 30000.0
        end 

        monthly_contribution = salary_credit * 0.045
        monthly_contribution
    end

    def phil_health(monthly_income)
        salary = monthly_income.to_f

        if salary < 10000
            monthly_contribution = 225.0
        elsif salary.between?(10000.0, 90000.0)
            monthly_contribution = salary * 0.0225
        else
            monthly_contribution = 4050.0
        end

        monthly_contribution
    end
    
    def pag_ibig(monthly_income)
        salary = monthly_income.to_f

        if salary <= 1500
            monthly_contribution = salary * 0.01
        elsif salary > 1500 && salary <= 5000
            monthly_contribution = salary * 0.02
        else
            monthly_contribution = 100.0
        end

        monthly_contribution
    end

    def total_contribution(monthly_income)
        sss(monthly_income) + phil_health(monthly_income) + pag_ibig(monthly_income)
    end
    
    def income_tax(monthly_income)
        salary = monthly_income.to_f
        total = total_contribution(monthly_income)
        taxable_income = salary - total
    
        if taxable_income.between?(20833, 33332)
            income_tax = (taxable_income - 20833) * 0.15
        elsif taxable_income.between?(33333, 66666)
            income_tax = (taxable_income - 33333) * 0.2 + 1875.00
        elsif taxable_income.between?(66667, 166666)
            income_tax = (taxable_income - 66667) * 0.25 + 8541.80
        elsif taxable_income.between?(166667, 666666)
            income_tax = (taxable_income - 166667) * 0.3 + 33541.80
        elsif taxable_income >= 666667
            income_tax = (taxable_income - 666667) * 0.35 + 8541.80
        else
            income_tax = 0.0
        end
        
        income_tax
    end
    
    def after_tax(monthly_income)
        salary = monthly_income.to_f
        salary - income_tax(monthly_income)
    end
    
    def total_deductions(monthly_income)
        total_contribution(monthly_income) + income_tax(monthly_income)
    end
    
    def final_total(monthly_income)
        salary = monthly_income.to_f
        salary - total_deductions(monthly_income)
    end
end