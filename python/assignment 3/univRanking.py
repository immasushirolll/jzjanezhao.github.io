# Print file not found to output file if any of the files is missing

def getInformation(selectedCountry,TopUniInfile,capitalsInfile"):

    # 1. Universities count
    infile = open(TopUniInfile,"r")     # opens files
    outfile = open("output.txt","w")
    count = 0
    next(infile)        # skips header row
    line = infile.readline()

    while line != "":   # counts how many rows of universities are listed in TopUni.csv
        count += 1
        line = infile.readline()
    outfile.write("Total number of universities => " + str(count) + "\n\n")  # adds to output file
    infile.close()      # closes file

    # 2. Available countries
    infile = open(TopUniInfile,"r")
    countries = []
    line = infile.readline()

    while line != "":
        line = infile.readline()
        line = line.split(",")      # recognizes elements separated by commas
        if line == ['']:            # stop when reached
            break
        country = line[2]           # reads index two from the table
        country = country.upper()   # capitalizes elements
        if country in countries:    # ignores the country if it's already in the list
            continue
        else:
            countries.append(country)   # adds the country to the list

    outfile.write("Available countries => ")    # writes to output file
    for country in countries:
        if country == countries[-1]:
            outfile.write(country + "\n\n")
        else:
            outfile.write(country + ", ")

    infile.close()

    # 3. Available continents
    infile = open(capitalsInfile,"r")
    continents = []
    line = infile.readline()

    while line != "":
        line = infile.readline()
        line = line.split(",")
        if line == ['']:
            continue
        continent = line[5]     # reads index 5 of line
        continent = continent.upper()
        if continent in continents:
            continue
        else:
            continents.append(continent)    # adds continent if not in list

    outfile.write("Available continents => ")
    for continent in continents:
        if continent == continents[-1]:
            outfile.write(continent + "\n\n")
        else:
            outfile.write(continent + ", ")

    infile.close()      # closes files
    outfile.close()

# 4. University w top international rank
# 5. university w top national rank
# 6. average score
# 7. continent relative score
# 8. capital city
# 9. universities that hold the capital name
