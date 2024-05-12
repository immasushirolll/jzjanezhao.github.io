
prices = []
def generateReceipt(pizzaOrder):    # generateReceipt generates a receipt for pizzaOrder
    
    if pizzaOrder == []:    # No pizzas were ordered/are in the list
        print("You did not order anything")
        return 
    else:
        print("Your order:")
        pizzaCount = 1
        for pizza in pizzaOrder:
            offset = 20         # default offset
            # reassigning variables for convenience
            size = pizza[0]
            toppingList = pizza[1]

            pizzaOut = 'Pizza ' + str(pizzaCount) + ': ' + size     # Storing pizza order information
            baseCost = cost(size)   # Stores cost of pizza based on its size
            prices.append(baseCost)

            baseCost = str(round(baseCost, 2))  # Stores cost of pizza as a string and rounds to 2 decimals
            if size == ("XL"):
                offset -= 1     # special offset when size == XL
            if pizzaCount > 9:
                offset -= 1     # accounts for additional number introduced by double digits
            
            pizzaCount += 1
            print(pizzaOut + baseCost.rjust(offset))
            printToppings(size, toppingList)

        # Calculates tax cost
        taxPrice = 0.00
        finalPrice = 0.00
        for price in prices:
            finalPrice += price

        taxPrice = finalPrice * 0.13
        taxPrice = round(taxPrice, 2)   # Rounds tax cost to 2 decimals

        print("Tax:", end="")
        print('%.2f'.rjust(26) %taxPrice)

        # Calculates final total price
        finalPrice = taxPrice + finalPrice
        finalOffset = 24
        if finalPrice > 9.99:
            finalOffset -= 1    # Ensures total is correctly aligned
        finalPrice = round(finalPrice, 2)
        print("Total:", end="")
        print("%.2f".rjust(finalOffset) %(finalPrice))
    return


def cost(size):     # cost function determines base cost of pizza based on size
    if size == "S":
        return 7.99

    elif  size == "M":
        return 9.99

    elif size == "L":
        return 11.99

    elif  size == "XL":
        return 13.99

def printToppings(size, toppingList):   # printToppings function states the user selected pizza size and toppings
    if toppingList == []:
        return

    offset = 13
    if size == "S":
        extraCost = 0.50

    elif  size == "M":
        extraCost = 0.75

    elif size == "L":
        extraCost = 1.00

    elif  size == "XL":
        extraCost = 1.25
        offset -= 1

    for topping in toppingList:
        print("- " + topping)

    extraToppings = len(toppingList) - 3    # Checks to see if there's more than three toppings

    if extraToppings > 0:   # Accounts for the additional cost for extra toppings
        for i in range(extraToppings):
            prices.append(extraCost)
            print("Extra Topping (" + size, end=")")
            print('%.2f'.rjust(offset) %extraCost)
    
    return
