# Helllo, I am Jane (Jin) Zhao in CS 1026 002. Student number is 251138547.

year = int(input("Please enter the year that you want to calculate the personal interest rate for:"))   # Asks user to input the year they are calculating personal inflation rates for and stores in 'year'.


amtCategories = int(input("Please enter the number of expenditure categories:"))        # Prompts user to enter the number of expenditure categories they would like to enter.
if amtCategories > 0:       # Number of categories must be more than 0 or program stops.
    prevExp = float(input("Please enter expenses for previous year:"))      # User enters expense from 1st category for previous year.
    curExp = float(input("Please enter expenses for year of interest:"))    # User enters expense from 1st category for year of interest.
    totalprevExp = prevExp      # Creates variable for total expenses from all categories of previous year.
    totalcurExp = curExp        # Creates variable for total expenses from all categories of year of interest.

    counter = 0         # Creates a counter against which the amount of categories remaining after each loop can be compared.
    while counter < (amtCategories - 1):        # The following will continue looping until this statement is false.
        prevExp2 = float(input("Please enter expenses for previous year:"))     # User enters expense from 2nd category for previous year.
        curExp2 = float(input("Please enter expenses for year of interest:"))   # User enters expense from 2nd category for year of interest.
        totalprevExp = totalprevExp + prevExp2      # Takes the previous value of total expenses and adds the value that was just inputted for every loop.
        totalcurExp = totalcurExp + curExp2
        counter = counter + 1       # Counter increases by 1 for every loop until the while statement is false.
    infRate = (totalcurExp - totalprevExp) / totalcurExp * 100      # Inflation rate formula.
    print("Personal inflation rate for {} is {}%". format(year, infRate))       # States personal inflation rate for the year of interest.

    # Categorizing the levels of inflation
    if infRate < 3:         # Inflation type is considered low if this statement stands.
        typeInf = "Low"
    elif infRate < 5:       # Inflation type is considered moderate if this statement stands.
        typeInf = "Moderate"
    elif infRate < 10:      # Inflation type is considered high if this statement stands.
        typeInf = "High"
    else:                   # Otherwise, inflation type is considered hyper.
        typeInf = "Hyper"
    print("Type of Inflation: {}". format(typeInf))     # States the type of inflation.
