# Imports from Airport.py
from Airport import *

class Flight:
    def __init__(self,flightNo,origin,destination):
        # checks that origin and destination are airport objects
        if isinstance(origin,Airport) and isinstance(destination,Airport):
            self._flightNo = flightNo
            self._origin = origin
            self._destination = destination
        # raise TypeError
        else:
            raise TypeError("The origin and destination must be Airport objects")
    def __repr__(self):
        # return representation of flight, indicating domestic or international
        if self.isDomesticFlight():
            typeFlight = "domestic"
        else:
            typeFlight = "international"
            
        return "Flight: " + self._flightNo + " from " + self._origin.getCity() + " to " + self._destination.getCity() + " {" + typeFlight +"}"
    def __eq__(self, other):
        # returns True if self and other are considered the same flight
        if isinstance(other,Flight):
            return self._origin.getCity() == other._origin.getCity() and self._destination.getCity() == other._destination.getCity()
        else: # return False if not a Flight object
            return False
    def getFlightNumber(self):
        # getter returns flight number code
        return self._flightNo
    def getOrigin(self):
        # getter returns flight origin
        return self._origin
    def getDestination(self):
        # getter returns flight destination
        return self._destination
    def isDomesticFlight(self):
        # returns True if flight is domestic (origin and destination are in same country)
        # returns False if flight is international (origin and destination not in same country)
        return self._origin.getCountry() == self._destination.getCountry()
    def setOrigin(self,origin):
        # setter updates flight origin
        self._origin = origin
    def setDestination(self,destination):
        # setter updates flight destination
        self._destination = destination
