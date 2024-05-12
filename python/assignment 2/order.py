import pizzaReceipt as pr   # Calling pizzaReceipt.py as pr

TOPPINGS = ('ONION','TOMATO','GREEN PEPPER','MUSHROOM','OLIVE','SPINACH','BROCCOLI','PINEAPPLE',
            'HOT PEPPER','PEPPERONI','HAM','BACON','GROUND BEEF','CHICKEN','SAUSAGE')
pizzaOrder = []


def order(repeat = False, orderConfirm = ''):   # order function prompts user to order
    if not repeat:
        orderConfirm = input("Do you want to order a pizza? ").upper()
    if orderConfirm in ("Q", "NO"):
        pr.generateReceipt(pizzaOrder)
    else:
        pizza = (sizeSel(),toppingsInput())     # Stores user desired pizza size and toppings as a tuple
        pizzaOrder.append(pizza)    # Adds user selected pizza to their pizza order

        additionalPizza()


def sizeSel():      # sizeSel function prompts user to pick a valid size of pizza
    size = ""
    while size not in ("S", "M", "L", "XL"):
        size = input("Choose a size: S, M, L, or XL: ").upper()
    return size

def additionalPizza():  # additionalPizza function asks user if they want to order another pizza
    proceed = input('Do you want to continue ordering? ').upper()
    order(True, proceed)    # Indicates to the order function to skip initial orderConfirm input


def toppingsInput():    # toppingsInput function prompts user to add as many desired toppings as they want
    addedToppings = []
    while True:
        topping = input('Type in one of our toppings to add it to your pizza. To see the list of toppings, enter "LIST". When you are done adding toppings, enter "X"\n').upper()
        if topping in TOPPINGS:
            addedToppings.append(topping)   # Adds inputted topping to list of toppings
            print("Added " + topping + " to your pizza")
        elif topping == "LIST":     # prints menu of toppings
            print(TOPPINGS)
        elif topping == "X":
            return addedToppings
        else:
            print('Invalid topping')

order()
