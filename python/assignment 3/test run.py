# Part 1
infile = open("TopUni.csv","r")     # opens files
outfile = open("output.txt","w")
count = 0
next(infile)        # skips header row
line = infile.readline()
while line != "":   # counts how many rows of universities are listed in TopUni.csv
    count += 1
    line = infile.readline()
outfile.write("Total number of universities => " + str(count)+ "\n\n")  # adds to output file
infile.close()

# Part 2
infile = open("TopUni.csv","r")
countries = []
line = infile.readline()

while line != "":
    line = infile.readline()
    line = line.split(",")
    if line == ['']:
        break
    country = line[2]
    country = country.upper()
    if country in countries:
        continue
    else:
        countries.append(country)

outfile.write("Available countries => ")
for country in countries:
    if country == countries[-1]:
        outfile.write(country + "\n\n")
    else:
        outfile.write(country + ", ")

infile.close()

# Part 3
infile = open("capitals.csv","r")
continents = []      # creates an empty list
line = infile.readline()

while line != "":
    line = infile.readline()
    line = line.split(",")
    if line == ['']:
        break
    continent = line[5]
    continent = continent.upper()
    if continent in continents:
        continue
    else:
        continents.append(continent)

outfile.write("Available continents => ")
for continent in continents:
    if continent == continents[-1]:
        outfile.write(continent + "\n\n")
    else:
        outfile.write(continent + ", ")

infile.close()

# 4. University w top international rank
selectedCountry = input("What country are you looking for universities in?")
infile = open("TopUni.csv","r")
info = []
line = infile.readline()

while line != "":
    line = infile.readline()
    line = line.split(",")
    if line == ['']:
        break
    topUniInCountry = line[2]
    country = country.upper()
    if selectedCountry in info:

    else:
        countries.append(country)

outfile.write("Available countries => ")
for country in countries:
    if country == countries[-1]:
        outfile.write(country + "\n\n")
    else:
        outfile.write(country + ", ")

infile.close()

# outfile.write("At international rank => %d the university name is => %s\n" % )

# 5. university w top national rank
# 6. average score
# 7. continent relative score
# 8. capital city
# 9. universities that hold the capital name
