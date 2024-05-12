class Airport:
    def __init__(self,code,city,country):
        # initialize instance variables based on parameters in constructor
        self._code = code
        self._city = city
        self._country = country
    def __repr__(self):
        # return representation of this airport in format: code(city, country)
        return self._code + "(" + self._city + ", " + self._country + ")"
    def getCode(self):
        # getter returns airport code
        return self._code
    def getCity(self):
        # getter returns airport city
        return self._city
    def getCountry(self):
        # getter returns airport country
        return self._country
    def setCity(self,city):
        # setter updates airport city
        self._city = city
    def setCountry(self,country):
        # setter updates airport country
        self._country = country
