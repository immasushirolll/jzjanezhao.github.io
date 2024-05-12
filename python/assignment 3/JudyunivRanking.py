def getInformation(selectedCountry,topUniFile,capitalsFile):
    output = open("output.txt", "w")

    #step 1: universities count
    output.write("Total number of universities => 100" + "\n")

    #step 2: available countries
    topUni = open("TopUni.csv", "r")
    availCountry=[]
    for line in topUni:
        s=line.split(",")
        if s[2] not in availCountry:
            availCountry.append(s[2])
    availCountry.remove('Country')
    output.write("Available countries => ")
    for a in range(len(availCountry)-1):
        output.write(availCountry[a].upper()+", ")
    output.write(availCountry[-1].upper()+"\n")
    topUni.close()

    #step 3: available continents
    capitals = open("capitals.csv", "r")
    output.write("Available continents => ")
    availContinents=[]
    for line in capitals:
        t=line.split(",")
        t[5]=t[5].rstrip("\n")
        for c in range(len(availCountry)):
            if t[5] not in availContinents:
                if t[0] == availCountry[c]:
                    availContinents.append(t[5])
    for u in range(len(availContinents)-1):
        output.write(availContinents[u].upper()+", ")
    output.write(availContinents[-1].upper()+"\n")
    capitals.close()

    #step 4: top ranked university for each country
    topUni=open("TopUni.csv","r")
    output.write("At international rank => ")
    findCountry=topUni.readline()
    while findCountry:
        if selectedCountry.upper() in findCountry.upper():
            break
        findCountry=topUni.readline()
    findCountry = findCountry.split(",")
    output.write("%s the university name is => %s\n" % (findCountry[0], findCountry[1].upper()))
    topUni.close()

    #step 5: the university with top national rank
    topUni=open("TopUni.csv","r")
    output.write("At national rank => ")
    rank = 100
    topUniFound = ""
    for line in topUni:
        findTopUni=line.split(",")
        if selectedCountry.upper() in findTopUni[2].upper():
            if int(findTopUni[3]) < rank:
                rank=int(findTopUni[3])
                topUniFound=findTopUni[1]
    output.write("%s the university name is => %s\n"%(rank,topUniFound.upper()))
    topUni.close()

    #step 6: average score
    topUni = open("TopUni.csv", "r")
    allScores=[]
    sum=0
    #variables relationship: avgScore = sum/numOfUnis
    for line in topUni:
        score=line.split(",")
        if selectedCountry.upper() == score[2].upper():
            score[-1]=score[-1].rstrip("\n")
            allScores.append(float(score[-1]))
    for p in range(len(allScores)):
        sum=sum+allScores[p]
        avgScore=sum/(len(allScores))
    output.write(f"The average score => {round(avgScore,2)}%\n")
    topUni.close()

    #step 7: the continent relative score
    capitals=open("capitals.csv","r")

    continent=""
    for line in capitals:
        findContinent=line.split(",")
        if selectedCountry.upper() == findContinent[0].upper():
            continent=findContinent[-1].rstrip("\n")
    capitals.close()

    capitals = open("capitals.csv", "r")
    topUni = open("TopUni.csv", "r")
    countriesList = []


    # find all countries in said continent
    for line in capitals:
        findCountries=line.split(",")
        if continent.upper() == findCountries[-1].rstrip("\n").upper():
            countriesList.append(findCountries[0])
    # find the highest score in continent
    topScore = -1
    for line in topUni:
        findContScore=line.split(",")
        if findContScore[2] in countriesList:
            if float(findContScore[-1].rstrip("\n")) > topScore:
                topScore=float(findContScore[-1].rstrip("\n"))
    relativeScore=(avgScore/topScore)*100
    output.write(f"The relative score to the top university in {continent.upper()} is => ({round(avgScore,2)} / {round(topScore,2)}) x 100% = {round(relativeScore,2)}%\n")
    capitals.close()
    topUni.close()

    #step 8: the capital city
    capitals=open("capitals.csv","r")
    capitalCity=""
    for line in capitals:
        findCapital=line.split(",")
        if selectedCountry.upper() == findCapital[0].upper():
            capitalCity=findCapital[1]
            break
    output.write("The capital is => %s\n"%capitalCity.upper())
    capitals.close()

    #step 9: universities that hold capital name
    topUni=open("TopUni.csv","r")
    topUni.readline()

    lineNum = 0
    for line in topUni:
        findUni=line.split(",")
        if capitalCity.upper() in findUni[1].upper():
            lineNum += 1
            output.write(f"    #{lineNum} {findUni[1].upper()}\n")


    output.close()


