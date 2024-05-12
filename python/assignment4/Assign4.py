# import from Flight.py and Airport.py
from Flight import *
from Airport import *

# create containers for Airport and Flight objects
allAirports = []
allFlights = {}

# Reads in and adds Airport and Flight objects to containers
def loadData(airportFile,flightFile):
    try: # handle exceptions using try-except
        # open file where aiport information is stored
        airports = open(airportFile,"r")
        # read in all data from airport file
        tempAirports = airports.readlines()
        for airport in tempAirports: # list of lists
            # recognize each element by comma
            airport = airport.split(",")
            # remove white spaces
            airport[0] = airport[0].strip()
            airport[1] = airport[1].strip()
            airport[2] = airport[2].strip().strip("\n")

            # add airport object to allAirports container
            allAirports.append(Airport(airport[0], airport[2], airport[1]))     # aiport[0] is code, airport[1] is country, airport[2] is city

        # opens file where flight information is stored
        flights = open(flightFile,"r")
        # read in all data from flight file of given name
        tempFlights = flights.readlines()
        # create flight object
        for flight in tempFlights:
            # recognize elements by comma
            flight = flight.split(",")
            # remove white spaces
            flight[0] = flight[0].strip()
            flight[1] = flight[1].strip()
            flight[2] = flight[2].strip().strip("\n")

            origin = flight[1]
            destination = flight[2]

            for obj in allAirports:
                if origin == obj._code:
                    origin = obj

                if destination == obj._code:
                    destination = obj
            
            flightObj = Flight(flight[0], origin, destination)     # flightNo, origin object, destination object
            
            # add flight object to allFlights containers
            # first creates key and initializes empty list if key does not already exist
            if origin._code not in allFlights:
                allFlights[origin._code] = []
            # always adds flight object to value list
            allFlights[origin._code].append(flightObj)
        
        # print for checking to ensure containers are created correctly
        # print(allAirports)
        # print("\n")
        # print(allFlights)

        # if both files load properly w/o errors, return True
        return True
    except:
        # if there's any exception, return False
        return False

# Return Airport object given airport code
def getAirportByCode(code):
    for airport in allAirports:
        if code == airport._code:   # if the entered code is same as the code of a particular object in allAirports
            return airport
    # if no airport found, return -1
    return -1

# Return all flights from or going to a city
def findAllCityFlights(city):
    cityFlights = []    # create an empty list
    for key in allFlights:
        for i in range(len(allFlights[key])):
            # if city parameter is same as the origin city or destination city, add this flight to our list
            if allFlights[key][i]._origin.getCity() == city or allFlights[key][i]._destination.getCity() == city:
                cityFlights.append(allFlights[key][i])
    return cityFlights  # returns the list of flights

# Return all flight objects from or going to (or both) a certain country
def findAllCountryFlights(country):
    countryFlights = [] # creates empty list
    for key in allFlights:  # for each object in allFlights dictionary
        for i in range(len(allFlights[key])):
            # if country parameter is same as the origin country or destination country, add this flight to our list
            if allFlights[key][i]._origin.getCountry() == country or allFlights[key][i]._destination.getCountry() == country:
                countryFlights.append(allFlights[key][i])
    return countryFlights   # returns list of flights

# Check if this is a direct flight from origAirport to destAirport
def findFlightBetween(origAirport,destAirport):
    connectingAirports = set()  # create an empty set

    for originKey in allFlights:
        for destKey in allFlights:
            for flight1 in range(len(allFlights[originKey])):
                for flight2 in range(len(allFlights[destKey])):
                    # (If direct flight) If origin airport in object is the origAirport parameter and destination airport in object is the destAirport, return
                    if getAirportByCode(allFlights[originKey][flight1]._origin.getCode()) == origAirport and getAirportByCode(allFlights[originKey][flight1]._destination.getCode()) == destAirport:
                        return "Direct Flight: " + allFlights[originKey][flight1]._origin.getCode() + " to " + allFlights[originKey][flight1]._destination.getCode()

                    # Check if there's one transfer, return set of all possible transfer airports
                    # (If one transfer) If flight1 origin airport is origAirport parameter and destination of flight1 is origin of flight2 and destination of flight2 is destAirport parameter,
                    # add the airport code of flight2 origin airport (flight1 destination airport also works)
                    elif getAirportByCode(allFlights[originKey][flight1]._origin.getCode()) == origAirport and \
                        getAirportByCode(allFlights[originKey][flight1]._destination.getCode()) == getAirportByCode(allFlights[destKey][flight2]._origin.getCode()) and \
                        getAirportByCode(allFlights[destKey][flight2]._destination.getCode()) == destAirport:
                        connectingAirports.add(allFlights[destKey][flight2]._origin.getCode())
    # If transfer airport was found, return the set of transfer airports (by code)
    if len(connectingAirports) != 0:
        return connectingAirports
    else:   # if no direct flight and no transfer, return -1
        return -1

# Find a flight object that does the return trip B -> A
def findReturnFlight(firstFlight):
    # new destination is the origin airport of the first flight, new origin is the destination airport
    newDest = firstFlight._origin.getCode()
    newOrig = firstFlight._destination.getCode()

    # if flight object with origin same as destination airport of the first flight and flight object destination same as origin airport of first flight,
    # return the flight object
    for originKey in allFlights:
        for flight in range(len(allFlights[originKey])):
            if allFlights[originKey][flight]._origin.getCode() == newOrig and allFlights[originKey][flight]._destination.getCode() == newDest:
                return allFlights[originKey][flight]
    # if no such flight object makes the return trip, return -1
    return -1
